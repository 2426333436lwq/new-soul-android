package dev.local.newsoul.feature.feed;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Locale;

import android.content.Context;

import dev.local.newsoul.config.ConfigStore;
import dev.local.newsoul.config.SettingsKeys;
import dev.local.newsoul.framework.Reflect;

/**
 * 广场信息流过滤判定（原 {@code B2.a(Context, Object)}）。
 *
 * <p><b>原始证据</b>：{@code B2.a} 读取 {@code feed_filter_enabled}、
 * {@code feed_filter_gender}、{@code feed_filter_keep_unknown}、{@code feed_keywords_v1}
 * 四个键，并用 {@code k0.a(String)} 做关键词匹配。字段名证据来自 {@code B2.c}：
 * {@code content}、{@code contentTitle}、{@code title}、{@code name}、{@code gender}、
 * {@code tags}、{@code postAuthorModel}。</p>
 *
 * <p><b>判定顺序</b>（与原实现一致）：</p>
 * <ol>
 *   <li>{@code feed_filter_enabled} 关闭 → 一律保留</li>
 *   <li>取帖子作者性别：{@code gender} 字段，或 {@code postAuthorModel.gender}</li>
 *   <li>性别未知 → 由 {@code feed_filter_keep_unknown} 决定（true 保留）</li>
 *   <li>性别不匹配 {@code feed_filter_gender} → 隐藏</li>
 *   <li>{@code feed_keywords_enabled_v1} 开启时，匹配 {@code feed_keywords_v1} → 隐藏</li>
 * </ol>
 *
 * <p><b>安全取向</b>：任何字段读取失败都按「不是广告/不过滤」处理 —— 宁可漏杀不可误杀。
 * 用户看不到帖子会以为功能坏了，误杀则是明确的体验损伤。</p>
 */
public final class FeedFilter {

    /** 帖子正文字段候选。 */
    private static final String[] CONTENT_FIELDS = {
            "content", "contentTitle", "title", "text",
    };

    /** 作者性别字段候选。 */
    private static final String[] GENDER_FIELDS = {
            "gender", "sex", "userSex", "authorGender",
    };

    /** 作者对象字段候选（性别藏在其内）。 */
    private static final String[] AUTHOR_FIELDS = {
            "postAuthorModel", "author", "user", "authorModel",
    };

    /** 话题/标签字段候选（参与关键词匹配）。 */
    private static final String[] TAG_FIELDS = {
            "tags", "topic", "topicTitle", "topics",
    };

    private FeedFilter() {
    }

    /**
     * 判断该帖子是否应被隐藏。
     *
     * @param context 上下文，用于读取开关
     * @param post    宿主帖子对象（{@code cn.soulapp.android.square.post.bean.Post}）
     * @return {@code true} 表示应隐藏
     */
    public static boolean shouldHide(Context context, Object post) {
        if (context == null || post == null) {
            return false;
        }
        try {
            ConfigStore store = ConfigStore.from(context);
            if (!store.getBoolean(SettingsKeys.FEED_FILTER_ENABLED)) {
                return false;
            }

            // ---- 性别筛选 ----
            String want = store.getString(SettingsKeys.FEED_FILTER_GENDER);
            if (want != null && !SettingsKeys.GENDER_ANY.equalsIgnoreCase(want)) {
                String actual = readGender(post);
                if (actual == null || actual.isEmpty()) {
                    // 性别未知：由 keep_unknown 决定
                    if (!store.getBoolean(SettingsKeys.FEED_FILTER_KEEP_UNKNOWN)) {
                        return true;
                    }
                } else if (!want.equalsIgnoreCase(actual)) {
                    return true;
                }
            }

            // ---- 关键词过滤 ----
            if (store.getBoolean(SettingsKeys.FEED_KEYWORDS_ENABLED_V1)) {
                String keywords = store.getString(SettingsKeys.FEED_KEYWORDS_V1);
                if (keywords != null && !keywords.isEmpty() && matchesKeyword(post, keywords)) {
                    return true;
                }
            }
        } catch (Throwable ignored) {
            // 判定失败按保留处理
        }
        return false;
    }

    // ------------------------------------------------------------------ 字段读取

    /** 读取作者性别（先直接字段，再作者对象内字段）。 */
    public static String readGender(Object post) {
        String direct = readFirstString(post, GENDER_FIELDS);
        if (direct != null && !direct.isEmpty()) {
            return normalizeGender(direct);
        }
        Object author = readFirst(post, AUTHOR_FIELDS);
        if (author != null) {
            String nested = readFirstString(author, GENDER_FIELDS);
            if (nested != null && !nested.isEmpty()) {
                return normalizeGender(nested);
            }
            // 数值型性别：1=男 0/2=女（宿主各版本不一，只在能明确映射时才返回）
            Object numeric = readFirst(author, GENDER_FIELDS);
            if (numeric instanceof Number number) {
                int value = number.intValue();
                if (value == 1) {
                    return SettingsKeys.GENDER_MALE;
                }
                if (value == 0 || value == 2) {
                    return SettingsKeys.GENDER_FEMALE;
                }
            }
        }
        return null;
    }

    /** 把宿主的性别表示归一化为 ANY/MALE/FEMALE。 */
    private static String normalizeGender(String raw) {
        String value = raw.trim().toUpperCase(Locale.ROOT);
        if (value.isEmpty()) {
            return null;
        }
        if (value.startsWith("M") || "男".equals(raw) || "1".equals(value)) {
            return SettingsKeys.GENDER_MALE;
        }
        if (value.startsWith("F") || "女".equals(raw) || "0".equals(value) || "2".equals(value)) {
            return SettingsKeys.GENDER_FEMALE;
        }
        // 已经是 ANY/MALE/FEMALE
        if (SettingsKeys.GENDER_ANY.equals(value)
                || SettingsKeys.GENDER_MALE.equals(value)
                || SettingsKeys.GENDER_FEMALE.equals(value)) {
            return value;
        }
        return null;
    }

    // ------------------------------------------------------------------ 关键词

    /** 帖子文本（正文 + 标题 + 标签）是否命中任一屏蔽词。 */
    public static boolean matchesKeyword(Object post, String rawKeywords) {
        String haystack = collectText(post);
        if (haystack.isEmpty()) {
            return false;
        }
        String lower = haystack.toLowerCase(Locale.ROOT);
        for (String line : rawKeywords.split("\\R")) {
            String keyword = line.trim().toLowerCase(Locale.ROOT);
            if (!keyword.isEmpty() && lower.contains(keyword)) {
                return true;
            }
        }
        return false;
    }

    /** 汇总帖子所有可用于匹配的文本。 */
    private static String collectText(Object post) {
        StringBuilder builder = new StringBuilder();
        appendField(builder, post, CONTENT_FIELDS);
        appendField(builder, post, TAG_FIELDS);

        Object author = readFirst(post, AUTHOR_FIELDS);
        if (author != null) {
            appendField(builder, author, new String[]{"name", "nickname", "signature"});
        }
        return builder.toString();
    }

    private static void appendField(StringBuilder builder, Object target, String[] names) {
        for (String name : names) {
            Object value = readFirst(target, new String[]{name});
            if (value == null) {
                continue;
            }
            if (value instanceof CharSequence || value instanceof Number) {
                builder.append(value).append('\n');
            } else if (value instanceof List<?> list) {
                for (Object item : list) {
                    if (item instanceof CharSequence) {
                        builder.append(item).append('\n');
                    } else if (item != null) {
                        // Tag bean：取 name/title 字段
                        Object text = readFirst(item, new String[]{"name", "title", "tagName"});
                        if (text != null) {
                            builder.append(text).append('\n');
                        }
                    }
                }
            }
        }
    }

    // ------------------------------------------------------------------ 反射工具

    /** 按候选字段名取第一个非 null 值。 */
    private static Object readFirst(Object target, String[] names) {
        if (target == null) {
            return null;
        }
        for (String name : names) {
            Field field = Reflect.findField(target.getClass(), name);
            if (field == null) {
                continue;
            }
            Object value = Reflect.getFieldValue(target, field);
            if (value != null) {
                return value;
            }
        }
        return null;
    }

    /** 按候选字段名取第一个非空字符串。 */
    private static String readFirstString(Object target, String[] names) {
        Object value = readFirst(target, names);
        if (value instanceof CharSequence) {
            return value.toString();
        }
        return value == null ? null : String.valueOf(value);
    }
}
