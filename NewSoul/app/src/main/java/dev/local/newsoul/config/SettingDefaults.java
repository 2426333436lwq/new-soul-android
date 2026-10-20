package dev.local.newsoul.config;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 全部设置的默认值表。
 *
 * <p>对应原模块 {@code G.<clinit>} 中的两个字面字符串目录。此处的<b>键顺序即导出顺序</b>，
 * 因此顺序被刻意保留（导出 JSON 的 settings 对象按键序排列），修改顺序会改变导出文件内容。</p>
 */
public final class SettingDefaults {

    private static final LinkedHashMap<String, Object> DEFAULTS = new LinkedHashMap<>();

    static {
        // ---- 默认开启的开关（原 G.a 第一段） ----
        DEFAULTS.put(SettingsKeys.BLOCK_SPLASH, Boolean.TRUE);
        DEFAULTS.put(SettingsKeys.GAME_DICE, Boolean.TRUE);
        DEFAULTS.put(SettingsKeys.GAME_FINGER, Boolean.TRUE);
        DEFAULTS.put(SettingsKeys.CHAT_ANTI_RECALL, Boolean.TRUE);
        DEFAULTS.put(SettingsKeys.CHAT_OWN_RECALL_NO_TIME_LIMIT, Boolean.TRUE);
        DEFAULTS.put(SettingsKeys.CHAT_REMOVE_GIFT_GATE, Boolean.TRUE);
        DEFAULTS.put(SettingsKeys.CHAT_REMOVE_BELL_GIFT_GATE, Boolean.TRUE);
        DEFAULTS.put(SettingsKeys.POST_REMOVE_SHARE_LIMIT, Boolean.TRUE);
        DEFAULTS.put(SettingsKeys.CHAT_REMOVE_SOCIAL_SCORE_LIMIT, Boolean.TRUE);
        DEFAULTS.put(SettingsKeys.CHAT_HIDE_TYPING, Boolean.TRUE);
        DEFAULTS.put(SettingsKeys.MATCH_MUTE_BACKGROUND_MUSIC, Boolean.TRUE);
        DEFAULTS.put(SettingsKeys.DOWNLOAD_IMAGE_NO_WATERMARK, Boolean.TRUE);
        DEFAULTS.put(SettingsKeys.DOWNLOAD_VIDEO_NO_WATERMARK, Boolean.TRUE);
        DEFAULTS.put(SettingsKeys.SQUARE_VISIBLE_LIKE, Boolean.TRUE);
        DEFAULTS.put(SettingsKeys.PAGE_LOG, Boolean.TRUE);
        DEFAULTS.put(SettingsKeys.STARTUP_TOAST, Boolean.TRUE);

        // ---- 默认关闭的开关（原 G.a 第二段） ----
        DEFAULTS.put(SettingsKeys.AD_FEED, Boolean.FALSE);
        DEFAULTS.put(SettingsKeys.AD_DETAIL, Boolean.FALSE);
        DEFAULTS.put(SettingsKeys.AD_VIDEO, Boolean.FALSE);
        DEFAULTS.put(SettingsKeys.AD_TAG, Boolean.FALSE);
        DEFAULTS.put(SettingsKeys.AD_TAG_WEB, Boolean.FALSE);
        DEFAULTS.put(SettingsKeys.AD_SEARCH, Boolean.FALSE);
        DEFAULTS.put(SettingsKeys.AD_GROUP, Boolean.FALSE);
        DEFAULTS.put(SettingsKeys.AD_PLANET, Boolean.FALSE);
        DEFAULTS.put(SettingsKeys.AD_ME, Boolean.FALSE);
        DEFAULTS.put(SettingsKeys.AD_CONVERSATION, Boolean.FALSE);
        DEFAULTS.put(SettingsKeys.AD_ENVELOPE, Boolean.FALSE);
        DEFAULTS.put(SettingsKeys.AD_NOTICE, Boolean.FALSE);
        DEFAULTS.put(SettingsKeys.AD_CHAT_TOOLS, Boolean.FALSE);
        DEFAULTS.put(SettingsKeys.AD_FLOAT, Boolean.FALSE);
        DEFAULTS.put(SettingsKeys.AD_INTERSTITIAL, Boolean.FALSE);
        DEFAULTS.put(SettingsKeys.AD_REWARD_CHAT, Boolean.FALSE);
        DEFAULTS.put(SettingsKeys.AD_REWARD_DRAMA, Boolean.FALSE);
        DEFAULTS.put(SettingsKeys.AD_REWARD_WEB, Boolean.FALSE);
        DEFAULTS.put(SettingsKeys.PRIVACY_ALLOW_DOWNLOAD, Boolean.FALSE);
        DEFAULTS.put(SettingsKeys.PRIVACY_ALLOW_SCREENSHOT, Boolean.FALSE);
        DEFAULTS.put(SettingsKeys.PRIVACY_ALLOW_REPEAT, Boolean.FALSE);
        DEFAULTS.put(SettingsKeys.FEED_FILTER_ENABLED, Boolean.FALSE);
        DEFAULTS.put(SettingsKeys.FEED_FILTER_KEEP_UNKNOWN, Boolean.FALSE);
        DEFAULTS.put(SettingsKeys.FEED_KEYWORDS_ENABLED_V1, Boolean.FALSE);
        DEFAULTS.put(SettingsKeys.SQUARE_LIKE_ALL_LOADED, Boolean.FALSE);
        DEFAULTS.put(SettingsKeys.SQUARE_AUTO_LIKE, Boolean.FALSE);

        // ---- 枚举与文本型（原 G.a 尾部） ----
        DEFAULTS.put(SettingsKeys.FEED_FILTER_GENDER, SettingsKeys.GENDER_FEMALE);
        DEFAULTS.put(SettingsKeys.FEED_KEYWORDS_V1, "");
        DEFAULTS.put(SettingsKeys.CHAT_RECALL_STYLE, SettingsKeys.RECALL_STYLE_BELOW);

        // ---- 资料页伪装：四个计数项各四键（原 G.b 前段） ----
        String[] disguisePrefixes = {
                SettingsKeys.PROFILE_DISGUISE_FOLLOW,
                SettingsKeys.PROFILE_DISGUISE_FOLLOWED,
                SettingsKeys.PROFILE_DISGUISE_VISITOR,
                SettingsKeys.PROFILE_DISGUISE_DAYS,
        };
        for (String prefix : disguisePrefixes) {
            DEFAULTS.put(prefix + SettingsKeys.SUFFIX_ENABLED, Boolean.FALSE);
            DEFAULTS.put(prefix + SettingsKeys.SUFFIX_MODE, SettingsKeys.DISGUISE_MODE_ABSOLUTE);
            DEFAULTS.put(prefix + SettingsKeys.SUFFIX_ABSOLUTE, 0);
            DEFAULTS.put(prefix + SettingsKeys.SUFFIX_DELTA, 0);
        }

        // ---- AI 回复（原 G.b 尾段） ----
        DEFAULTS.put(SettingsKeys.AI_REPLY_ENABLED_V1, Boolean.TRUE);
        DEFAULTS.put(SettingsKeys.AI_REPLY_AUTO_IMAGE_THRESHOLD_V1, 0);
        DEFAULTS.put(SettingsKeys.AI_REPLY_PROFILE_V1, "");
        DEFAULTS.put(SettingsKeys.AI_REPLY_MODEL_V1, "");
    }

    private SettingDefaults() {
    }

    /** 默认值表的只读视图，键序即导出顺序。 */
    public static Map<String, Object> defaults() {
        return Collections.unmodifiableMap(DEFAULTS);
    }

    /** 查询某键的默认值；未知键返回 {@code null}。 */
    public static Object defaultValue(String key) {
        return DEFAULTS.get(key);
    }

    /** 判断是否为受支持的设置键。 */
    public static boolean isKnownKey(String key) {
        return DEFAULTS.containsKey(key);
    }
}