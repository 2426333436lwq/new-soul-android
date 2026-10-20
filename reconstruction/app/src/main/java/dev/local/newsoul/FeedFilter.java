package dev.local.newsoul;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * 信息流过滤引擎。
 * 对应原始 DEX 的 dev.local.newsoul.B2（FeedFilterEngine）。
 *
 * 规则（来自 UI 文案与 G.d 校验逻辑）：
 *   - feed_filter_gender：ANY / MALE / FEMALE
 *   - feed_keywords_v1：用 "\\R" 正则分隔的屏蔽词列表
 *   - feed_filter_keep_unknown：性别未知的帖子是否保留（保守选项）
 *   - 被隐藏的帖子不参与点赞
 */
public final class FeedFilter {

    private FeedFilter() {}

    /** 判断一个帖子是否应被隐藏。 */
    public static boolean shouldHide(View itemView) {
        if (itemView == null) return false;
        Context ctx = itemView.getContext();

        if (!FeatureCatalog.isEnabled(ctx, "feed_filter_enabled")) {
            return false;
        }

        String gender = FeatureCatalog.getString(ctx, "feed_filter_gender", "ANY");
        String authorGender = readAuthorGender(itemView);

        if (authorGender == null) {
            // 未知性别：由 feed_filter_keep_unknown 决定
            return !FeatureCatalog.isEnabled(ctx, "feed_filter_keep_unknown", true);
        }
        if (!"ANY".equals(gender) && !gender.equalsIgnoreCase(authorGender)) {
            return true;
        }

        if (FeatureCatalog.isEnabled(ctx, "feed_keywords_enabled_v1")) {
            String text = readPostText(itemView);
            if (text != null && matchesKeyword(ctx, text)) {
                return true;
            }
        }
        return false;
    }

    /** 解析屏蔽词列表。 */
    public static List<String> keywords(Context ctx) {
        List<String> out = new ArrayList<>();
        String raw = FeatureCatalog.getString(ctx, "feed_keywords_v1", "");
        if (raw.isEmpty()) return out;
        for (String p : raw.split("\\R")) {
            String t = p.trim();
            if (!t.isEmpty()) out.add(t.toLowerCase(java.util.Locale.ROOT));
        }
        return out;
    }

    private static boolean matchesKeyword(Context ctx, String text) {
        String lower = text.toLowerCase(java.util.Locale.ROOT);
        for (String k : keywords(ctx)) {
            if (lower.contains(k)) return true;
        }
        return false;
    }

    // ------------------------------------------------------------------

    /** 从宿主 Post bean 反射取作者性别。 */
    private static String readAuthorGender(View itemView) {
        Object post = itemView.getTag();
        if (post == null) return null;
        try {
            Class<?> c = post.getClass();
            for (String n : new String[]{"getUserSex", "getSex", "getGender"}) {
                try {
                    MethodHolder m = find(c, n);
                    if (m != null) return String.valueOf(m.invoke(post));
                } catch (Throwable ignore) {
                }
            }
        } catch (Throwable ignore) {
        }
        return null;
    }

    private static String readPostText(View itemView) {
        Object post = itemView.getTag();
        if (post == null) return null;
        try {
            Class<?> c = post.getClass();
            for (String n : new String[]{"getContent", "getText", "getTitle"}) {
                try {
                    MethodHolder m = find(c, n);
                    if (m != null) {
                        Object v = m.invoke(post);
                        return v == null ? null : String.valueOf(v);
                    }
                } catch (Throwable ignore) {
                }
            }
        } catch (Throwable ignore) {
        }
        return null;
    }

    private static MethodHolder find(Class<?> c, String name) {
        try {
            return new MethodHolder(c.getMethod(name));
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    private static final class MethodHolder {
        final java.lang.reflect.Method m;

        MethodHolder(java.lang.reflect.Method m) {
            this.m = m;
            m.setAccessible(true);
        }

        Object invoke(Object target) throws Exception {
            return m.invoke(target);
        }
    }
}
