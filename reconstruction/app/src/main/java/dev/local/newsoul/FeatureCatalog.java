package dev.local.newsoul;

import io.github.libxposed.api.XposedModule;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 功能目录与 SharedPreferences 开关读取。
 *
 * 对应原始 DEX 的 dev.local.newsoul.G（FeatureCatalog）+ Z0.g（SettingsSwitchboard）。
 *
 * 原始实现要点：
 *   - G.<clinit> 用两个空格分隔的长字符串声明全部 44 个键
 *   - G.a(Map) -> String：序列化为 JSON {"format":"newsoul-config","schemaVersion":N,"settings":{...}}
 *   - G.c(String) -> Map：反序列化并校验（JSONTokener + unmodifiableMap）
 *   - G.d(key, value, defaults)：逐键类型校验
 *       · feed_filter_gender 只接受 ANY / MALE / FEMALE
 *       · feed_keywords_v1 用 "\\R" 正则分隔，校验「最多保留 N 个」「每个屏蔽词长度」
 *       · ai_reply_auto_image_threshold_v1 校验数值范围
 *       · profile_disguise_* 校验 uid 后缀
 *   - 导出的配置文件明确「不包含密钥、聊天记录」
 */
public final class FeatureCatalog {

    private FeatureCatalog() {}

    /** SharedPreferences 文件名（原始实现里多个类都读同一个文件） */
    public static final String PREFS = "newsoul_settings";

    public static final String KEY_ACTIVE_MODULE = "newsoul.active.module";
    public static final String KEY_DEBUG = "newsoul_debug";
    public static final String KEY_BOOKMARK = "newsoul_bookmark";
    public static final String KEY_SETTINGS_ENTRY = "dev.local.newsoul.settings.entry.v2";
    public static final String KEY_FORWARD_TOKEN = "dev.local.newsoul.message_forward_token";
    public static final String KEY_RECALL_MARK = "dev.local.soulhookdemo.recalled.v1";

    public static final int CONFIG_SCHEMA_VERSION = 1;

    /** 广告（18） */
    public static final String[] ADS = {
            "ad_feed", "ad_detail", "ad_video", "ad_tag", "ad_tag_web", "ad_search",
            "ad_group", "ad_planet", "ad_me", "ad_conversation", "ad_envelope",
            "ad_notice", "ad_chat_tools", "ad_float", "ad_interstitial",
            "ad_reward_chat", "ad_reward_drama", "ad_reward_web",
    };

    /** 隐私 / 下载（3） */
    public static final String[] PRIVACY = {
            "privacy_allow_download", "privacy_allow_screenshot", "privacy_allow_repeat",
    };

    /** 广场过滤 / 点赞（5） */
    public static final String[] FEED = {
            "feed_filter_enabled", "feed_filter_keep_unknown", "feed_keywords_enabled_v1",
            "square_like_all_loaded", "square_auto_like",
    };

    /** 其他开关（18） */
    public static final String[] MISC = {
            "block_splash", "game_dice", "game_finger", "chat_anti_recall",
            "chat_own_recall_no_time_limit", "chat_remove_gift_gate",
            "chat_remove_bell_gift_gate", "post_remove_share_limit",
            "chat_remove_social_score_limit", "chat_hide_typing",
            "match_mute_background_music", "download_image_no_watermark",
            "download_video_no_watermark", "square_visible_like",
            "page_log", "startup_toast",
    };

    /** 全部 44 个布尔开关 */
    public static final String[] ALL_BOOLS = concat(ADS, PRIVACY, FEED, MISC);

    /** 带参数/字符串值的开关 */
    public static final String[] PARAM_KEYS = {
            "feed_filter_gender",            // ANY / MALE / FEMALE
            "feed_keywords_v1",              // "\\R" 分隔
            "chat_recall_style",
            "chat_limit_session_type",
            "ai_reply_enabled_v1",
            "ai_reply_model_v1",
            "ai_reply_profile_v1",
            "ai_reply_auto_image_threshold_v1",
    };

    /** 按 uid 后缀的参数键 */
    public static final String[] PROFILE_DISGUISE_PREFIXES = {
            "profile_disguise_days_",
            "profile_disguise_follow_",
            "profile_disguise_followed_",
            "profile_disguise_visitor_",
    };

    // ------------------------------------------------------------------ 读取

    /** 原 Z0.g / 各 hook 里都通过 SharedPreferences.getBoolean(key, false) 读取 */
    public static boolean isEnabled(android.content.Context ctx, String key) {
        return prefs(ctx).getBoolean(key, false);
    }

    public static boolean isEnabled(android.content.Context ctx, String key, boolean def) {
        return prefs(ctx).getBoolean(key, def);
    }

    public static String getString(android.content.Context ctx, String key, String def) {
        return prefs(ctx).getString(key, def);
    }

    public static int getInt(android.content.Context ctx, String key, int def) {
        return prefs(ctx).getInt(key, def);
    }

    public static void put(android.content.Context ctx, String key, boolean value) {
        prefs(ctx).edit().putBoolean(key, value).apply();
    }

    public static void put(android.content.Context ctx, String key, String value) {
        prefs(ctx).edit().putString(key, value).apply();
    }

    public static void put(android.content.Context ctx, String key, int value) {
        prefs(ctx).edit().putInt(key, value).apply();
    }

    public static void put(android.content.Context ctx, String key, long value) {
        prefs(ctx).edit().putLong(key, value).apply();
    }

    private static android.content.SharedPreferences prefs(android.content.Context ctx) {
        return ctx.getApplicationContext()
                .getSharedPreferences(PREFS, android.content.Context.MODE_PRIVATE);
    }

    // ------------------------------------------------------------------ 导入 / 导出

    /**
     * 导出为 JSON。对应 G.a。
     * 注意：原始实现明确排除密钥与聊天记录。
     */
    public static String export(android.content.Context ctx) {
        Map<String, Object> settings = new LinkedHashMap<>();
        for (String k : ALL_BOOLS) {
            settings.put(k, isEnabled(ctx, k));
        }
        for (String k : PARAM_KEYS) {
            settings.put(k, getString(ctx, k, ""));
        }
        for (String prefix : PROFILE_DISGUISE_PREFIXES) {
            for (Map.Entry<String, ?> e : prefs(ctx).getAll().entrySet()) {
                if (e.getKey().startsWith(prefix)) {
                    settings.put(e.getKey(), e.getValue());
                }
            }
        }
        try {
            org.json.JSONObject root = new org.json.JSONObject();
            root.put("format", "newsoul-config");
            root.put("schemaVersion", CONFIG_SCHEMA_VERSION);
            root.put("settings", new org.json.JSONObject(settings));
            return root.toString(4);
        } catch (org.json.JSONException e) {
            throw new IllegalArgumentException("配置格式错误");
        }
    }

    /** 导入并校验。对应 G.c + G.d。 */
    public static void importConfig(android.content.Context ctx, String json) {
        try {
            org.json.JSONObject root = (org.json.JSONObject) new org.json.JSONTokener(json).nextValue();
            if (!"newsoul-config".equals(root.optString("format"))
                    || root.optInt("schemaVersion", -1) != CONFIG_SCHEMA_VERSION) {
                throw new IllegalArgumentException("配置格式错误");
            }
            org.json.JSONObject settings = root.optJSONObject("settings");
            if (settings == null) {
                throw new IllegalArgumentException("配置格式错误");
            }
            android.content.SharedPreferences.Editor ed = prefs(ctx).edit();
            java.util.Iterator<String> it = settings.keys();
            while (it.hasNext()) {
                String key = it.next();
                validate(key, settings.opt(key));
                Object v = settings.opt(key);
                if (v instanceof Boolean) {
                    ed.putBoolean(key, (Boolean) v);
                } else if (v instanceof Integer) {
                    ed.putInt(key, (Integer) v);
                } else if (v instanceof Long) {
                    ed.putLong(key, (Long) v);
                } else if (v instanceof String) {
                    ed.putString(key, (String) v);
                }
            }
            ed.apply();
        } catch (org.json.JSONException e) {
            throw new IllegalArgumentException("配置格式错误");
        }
    }

    // ------------------------------------------------------------------ 校验

    /** 对应 G.d：逐键类型/取值校验。 */
    public static void validate(String key, Object value) {
        if (value == null) {
            throw new IllegalArgumentException("配置格式错误");
        }
        if ("feed_filter_gender".equals(key)) {
            String g = String.valueOf(value).toUpperCase(java.util.Locale.ROOT);
            if (!"ANY".equals(g) && !"MALE".equals(g) && !"FEMALE".equals(g)) {
                throw new IllegalArgumentException("feed_filter_gender 仅支持 ANY/MALE/FEMALE");
            }
            return;
        }
        if ("feed_keywords_v1".equals(key)) {
            String raw = String.valueOf(value);
            if (!raw.isEmpty()) {
                String[] parts = raw.split("\\R");
                for (String p : parts) {
                    if (p.trim().length() > 64) {
                        throw new IllegalArgumentException("每个屏蔽词最多 64 个字符");
                    }
                }
            }
            return;
        }
        if (key.startsWith("profile_disguise_")) {
            // profile_disguise_<field>_<uid>
            int last = key.lastIndexOf('_');
            if (last < 0 || last == key.length() - 1) {
                throw new IllegalArgumentException("资料伪装键缺少 uid 后缀");
            }
            return;
        }
        if ("ai_reply_auto_image_threshold_v1".equals(key)) {
            try {
                int n = Integer.parseInt(String.valueOf(value));
                if (n < 0 || n > 100) {
                    throw new IllegalArgumentException("阈值需在 0..100");
                }
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("阈值需为整数");
            }
            return;
        }
        if (!(value instanceof Boolean) && !(value instanceof String)) {
            throw new IllegalArgumentException("不支持的取值类型");
        }
    }

    // ------------------------------------------------------------------

    private static String[] concat(String[]... groups) {
        int n = 0;
        for (String[] g : groups) n += g.length;
        String[] out = new String[n];
        int p = 0;
        for (String[] g : groups) {
            System.arraycopy(g, 0, out, p, g.length);
            p += g.length;
        }
        return out;
    }
}
