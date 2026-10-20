package dev.local.newsoul.config;

/**
 * 设置键名与键值常量。
 *
 * <p>所有字符串键都是与原模块共享的<b>持久化契约</b>（写入宿主的 SharedPreferences，
 * 并被设置界面读写），因此必须逐字保持不变；这里只做集中声明，避免散落在各处。</p>
 */
public final class SettingsKeys {

    private SettingsKeys() {
    }

    // ---------------------------------------------------------------- 存储名称

    /** 主设置存储：全部功能开关。 */
    public static final String PREFS_MAIN = "dev.local.soulhookdemo.public.settings.v1";

    /** 撤回消息保留库。 */
    public static final String PREFS_RECALLED = "dev.local.soulhookdemo.recalled.v1";

    /** 书签（插眼）。 */
    public static final String PREFS_BOOKMARK = "newsoul_bookmark";

    /** 调试与诊断记录。 */
    public static final String PREFS_DEBUG = "newsoul_debug";

    /** 设置入口是否已注入的标记键（写入主设置存储）。 */
    public static final String KEY_SETTINGS_ENTRY_MARKER = "dev.local.newsoul.settings.entry.v2";

    /** 消息转发一次性令牌（Intent extra / 临时缓存键）。 */
    public static final String KEY_MESSAGE_FORWARD_TOKEN = "dev.local.newsoul.message_forward_token";

    /** 多模块共存去重用的系统属性名。 */
    public static final String PROPERTY_ACTIVE_MODULE = "newsoul.active.module";

    // ---------------------------------------------------------------- 广告屏蔽

    public static final String AD_FEED = "ad_feed";
    public static final String AD_DETAIL = "ad_detail";
    public static final String AD_VIDEO = "ad_video";
    public static final String AD_TAG = "ad_tag";
    public static final String AD_TAG_WEB = "ad_tag_web";
    public static final String AD_SEARCH = "ad_search";
    public static final String AD_GROUP = "ad_group";
    public static final String AD_PLANET = "ad_planet";
    public static final String AD_ME = "ad_me";
    public static final String AD_CONVERSATION = "ad_conversation";
    public static final String AD_ENVELOPE = "ad_envelope";
    public static final String AD_NOTICE = "ad_notice";
    public static final String AD_CHAT_TOOLS = "ad_chat_tools";
    public static final String AD_FLOAT = "ad_float";
    public static final String AD_INTERSTITIAL = "ad_interstitial";
    public static final String AD_REWARD_CHAT = "ad_reward_chat";
    public static final String AD_REWARD_DRAMA = "ad_reward_drama";
    public static final String AD_REWARD_WEB = "ad_reward_web";

    /** 全部广告开关（顺序与设置面板一致）。用于「任一广告开关开启即兜底屏蔽」的判定。 */
    public static final String[] AD_ALL = {
            AD_FEED, AD_DETAIL, AD_VIDEO, AD_TAG, AD_TAG_WEB, AD_SEARCH,
            AD_GROUP, AD_PLANET, AD_ME, AD_CONVERSATION, AD_ENVELOPE, AD_NOTICE,
            AD_CHAT_TOOLS, AD_FLOAT, AD_INTERSTITIAL,
            AD_REWARD_CHAT, AD_REWARD_DRAMA, AD_REWARD_WEB,
    };

    // ---------------------------------------------------------------- 隐私

    public static final String PRIVACY_ALLOW_DOWNLOAD = "privacy_allow_download";
    public static final String PRIVACY_ALLOW_SCREENSHOT = "privacy_allow_screenshot";
    public static final String PRIVACY_ALLOW_REPEAT = "privacy_allow_repeat";

    // ---------------------------------------------------------------- 信息流过滤

    public static final String FEED_FILTER_ENABLED = "feed_filter_enabled";
    public static final String FEED_FILTER_KEEP_UNKNOWN = "feed_filter_keep_unknown";
    public static final String FEED_FILTER_GENDER = "feed_filter_gender";
    public static final String FEED_KEYWORDS_ENABLED_V1 = "feed_keywords_enabled_v1";
    public static final String FEED_KEYWORDS_V1 = "feed_keywords_v1";

    /** 性别筛选取值。 */
    public static final String GENDER_ANY = "ANY";
    public static final String GENDER_MALE = "MALE";
    public static final String GENDER_FEMALE = "FEMALE";

    // ---------------------------------------------------------------- 广场点赞

    public static final String SQUARE_LIKE_ALL_LOADED = "square_like_all_loaded";
    public static final String SQUARE_AUTO_LIKE = "square_auto_like";
    public static final String SQUARE_VISIBLE_LIKE = "square_visible_like";

    // ---------------------------------------------------------------- 聊天

    public static final String CHAT_ANTI_RECALL = "chat_anti_recall";
    public static final String CHAT_OWN_RECALL_NO_TIME_LIMIT = "chat_own_recall_no_time_limit";
    public static final String CHAT_REMOVE_GIFT_GATE = "chat_remove_gift_gate";
    public static final String CHAT_REMOVE_BELL_GIFT_GATE = "chat_remove_bell_gift_gate";
    public static final String CHAT_REMOVE_SOCIAL_SCORE_LIMIT = "chat_remove_social_score_limit";
    public static final String CHAT_HIDE_TYPING = "chat_hide_typing";
    public static final String CHAT_LIMIT_SESSION_TYPE = "chat_limit_session_type";
    public static final String CHAT_RECALL_STYLE = "chat_recall_style";

    /** 撤回气泡样式取值。 */
    public static final String RECALL_STYLE_BELOW = "below";
    public static final String RECALL_STYLE_NICKNAME = "nickname";

    // ---------------------------------------------------------------- 小游戏

    public static final String GAME_DICE = "game_dice";
    public static final String GAME_FINGER = "game_finger";

    // ---------------------------------------------------------------- 启动与其它

    public static final String BLOCK_SPLASH = "block_splash";
    public static final String STARTUP_TOAST = "startup_toast";
    public static final String PAGE_LOG = "page_log";
    public static final String POST_REMOVE_SHARE_LIMIT = "post_remove_share_limit";
    public static final String MATCH_MUTE_BACKGROUND_MUSIC = "match_mute_background_music";
    public static final String DOWNLOAD_IMAGE_NO_WATERMARK = "download_image_no_watermark";
    public static final String DOWNLOAD_VIDEO_NO_WATERMARK = "download_video_no_watermark";

    // ---------------------------------------------------------------- 资料页伪装

    /** 资料页伪装四项计数的键前缀，后接 {@code enabled} / {@code mode} / {@code absolute} / {@code delta}。 */
    public static final String PROFILE_DISGUISE_FOLLOW = "profile_disguise_follow_";
    public static final String PROFILE_DISGUISE_FOLLOWED = "profile_disguise_followed_";
    public static final String PROFILE_DISGUISE_VISITOR = "profile_disguise_visitor_";
    public static final String PROFILE_DISGUISE_DAYS = "profile_disguise_days_";

    public static final String PROFILE_DISGUISE_PREFIX = "profile_disguise_";
    public static final String SUFFIX_ENABLED = "enabled";
    public static final String SUFFIX_MODE = "mode";
    public static final String SUFFIX_ABSOLUTE = "absolute";
    public static final String SUFFIX_DELTA = "delta";

    /** 资料页伪装模式取值。 */
    public static final String DISGUISE_MODE_ABSOLUTE = "absolute";
    public static final String DISGUISE_MODE_DELTA = "delta";

    // ---------------------------------------------------------------- AI 回复

    public static final String AI_REPLY_ENABLED_V1 = "ai_reply_enabled_v1";
    public static final String AI_REPLY_MODEL_V1 = "ai_reply_model_v1";
    public static final String AI_REPLY_PROFILE_V1 = "ai_reply_profile_v1";
    public static final String AI_REPLY_AUTO_IMAGE_THRESHOLD_V1 = "ai_reply_auto_image_threshold_v1";
}