package dev.local.newsoul.framework;

/**
 * Hook 稳定 ID 常量表。
 *
 * <p>ID 是模块与 LSPosed 之间的契约（用于日志定位与 hook 去重），必须与原模块逐字一致。
 * 以常量形式集中声明，避免散落在各功能域中拼写漂移。</p>
 */
public final class HookIds {

    private HookIds() {
    }

    // ---------------------------------------------------------------- 聊天

    public static final String CHAT_ANTI_RECALL = "new-soul/chat-anti-recall";
    public static final String CHAT_ANTI_RECALL_ROAM = "new-soul/chat-anti-recall-roam";
    public static final String CHAT_RECALL_MARK = "new-soul/chat-recall-mark";
    public static final String OWN_RECALL_ENTRY = "new-soul/own-recall-entry";
    public static final String OWN_RECALL_AGE_READ = "new-soul/own-recall-age-read";
    public static final String HIDE_TYPING = "new-soul/hide-typing";

    public static final String GIFT_CHAT_MODEL = "new-soul/gift-chat-model";
    public static final String GIFT_CHAT_DIALOG = "new-soul/gift-chat-dialog";
    public static final String GIFT_CHAT_SESSION = "new-soul/gift-chat-session";
    public static final String BELL_GIFT_POPUP_CONFIG = "new-soul/bell-gift-popup-config";
    public static final String SOCIAL_SCORE_PREFIX = "new-soul/social-score-";
    public static final String SOCIAL_SCORE_DIALOG = "new-soul/social-score-dialog";

    public static final String GAME_MESSAGE_RESULT = "new-soul/game-message-result";
    public static final String GAME_PICKER_PREFIX = "new-soul/game-picker/";

    // ---------------------------------------------------------------- 帖子 / 广场

    public static final String POST_COPY_ACTION = "new-soul/post-copy-action";
    public static final String POST_COPY_CLICK = "new-soul/post-copy-click";
    public static final String POST_SHARE_PERMISSION = "new-soul/post-share-permission";
    public static final String ACTION_MARKS_POST = "new-soul/action-marks-post";
    public static final String ACTION_MARKS_CHAT = "new-soul/action-marks-chat";

    public static final String SQUARE_LIKE_LIFECYCLE = "new-soul/square-like-lifecycle";
    public static final String SQUARE_LIKE_ENVELOPE_CREATE = "new-soul/square-like-envelope-create";
    public static final String SQUARE_LIKE_ENVELOPE_VISIBILITY = "new-soul/square-like-envelope-visibility";

    // ---------------------------------------------------------------- 下载 / 无水印

    /** 与原模块一致：ID 由 {@code "new-soul/" + 开关键} 拼成。 */
    public static final String DOWNLOAD_IMAGE_NO_WATERMARK = "new-soul/download_image_no_watermark";
    public static final String DOWNLOAD_VIDEO_NO_WATERMARK = "new-soul/download_video_no_watermark";

    // ---------------------------------------------------------------- 隐私

    public static final String PRIVACY_PHOTO_PREVIEW = "new-soul/privacy-photo-preview";
    public static final String PRIVACY_REPEAT_PREFIX = "new-soul/privacy-repeat-";
    /** 重复消费守卫：隐私照片路由（原 {@code "new-soul/privacy-repeat-" + "privacy"}）。 */
    public static final String PRIVACY_REPEAT_PRIVACY = PRIVACY_REPEAT_PREFIX + "privacy";
    /** 重复消费守卫：闪照路由（原 {@code "new-soul/privacy-repeat-" + "snap"}）。 */
    public static final String PRIVACY_REPEAT_SNAP = PRIVACY_REPEAT_PREFIX + "snap";
    public static final String PRIVACY_REPEAT_SNAP_PREVIEW = "new-soul/privacy-repeat-snap-preview";

    // ---------------------------------------------------------------- 信息流

    public static final String FEED_FILTER_ATTACH = "new-soul/feed-filter-attach";
    public static final String FEED_FILTER_BIND = "new-soul/feed-filter-bind";
    public static final String FEED_FILTER_RECYCLE = "new-soul/feed-filter-recycle";
    public static final String FEED_DATA_PREFIX = "new-soul/feed-data/";

    // ---------------------------------------------------------------- 广告

    public static final String AD_LOADER_PREFIX = "new-soul/ad-loader/";

    // ---------------------------------------------------------------- 资料页

    public static final String FOLLOW_DISGUISE_PREFIX = "new-soul/follow-disguise-";
    public static final String FOLLOW_DISGUISE_HEADER = "new-soul/follow-disguise-header";
    /** 注册天数伪装 ID 前缀（原模块以 {@code "new-soul/" + 后缀} 拼接）。 */
    public static final String DAYS_DISGUISE_PREFIX = "new-soul/";
    /** 注册天数伪装：资料页 revision 分支。 */
    public static final String DAYS_DISGUISE_REVISION = DAYS_DISGUISE_PREFIX + "days-revision";
    /** 注册天数伪装：meta 分支。 */
    public static final String DAYS_DISGUISE_META = DAYS_DISGUISE_PREFIX + "days-meta";
    public static final String PROFILE_BOOKMARK_MENU = "new-soul/profile-bookmark-menu";
    public static final String PROFILE_BOOKMARK_ICON = "new-soul/profile-bookmark-icon";
    public static final String PROFILE_BOOKMARK_CLICK = "new-soul/profile-bookmark-click";

    // ---------------------------------------------------------------- 启动

    public static final String SKIP_STARTUP_AD = "new-soul/skip-startup-ad";
    public static final String COLD_LAUNCH_FLAG = "new-soul/cold-launch-flag";
    public static final String RELAUNCH_FLAG = "new-soul/relaunch-flag";
    public static final String HOT_SPLASH_GATE = "new-soul/hot-splash-gate";

    // ---------------------------------------------------------------- 复读

    public static final String REPEAT_MENU = "new-soul/repeat-menu";
    public static final String REPEAT_CLICK = "new-soul/repeat-click";
    public static final String REPEAT_CONTEXT = "new-soul/repeat-context";

    // ---------------------------------------------------------------- 匹配音乐

    public static final String MATCH_MUSIC_PREFIX = "new-soul/match-music-";

    // ---------------------------------------------------------------- 消息转发

    public static final String MESSAGE_FORWARD_LIST = "new-soul/message-forward-list";
    public static final String MESSAGE_FORWARD_SELECT = "new-soul/message-forward-select";
    public static final String MESSAGE_FORWARD_NAVIGATION_PREFIX = "new-soul/message-forward-navigation-";
    public static final String MESSAGE_FORWARD_UI = "new-soul/message-forward-ui";
    public static final String MESSAGE_FORWARD_CLEANUP = "new-soul/message-forward-cleanup";
    public static final String MESSAGE_FORWARD_COMPLETE_PREFIX = "new-soul/message-forward-complete-";

    // ---------------------------------------------------------------- 语音库

    public static final String VOICE_LIBRARY_ENTRY = "new-soul/voice-library-entry";
    public static final String VOICE_LIBRARY_CLOSE = "new-soul/voice-library-close";

    // ---------------------------------------------------------------- 调试 / 设置

    public static final String CONFIG_FILE_RESULT = "new-soul/config-file-result";
    public static final String DEBUG_EXPORT_RESULT = "new-soul/debug-export-result";
    public static final String DEBUG_INIT = "new-soul/debug-init";
    public static final String BOTTOM_SETTINGS_ENTRY = "new-soul/bottom-settings-entry";

    /** 设置入口触发点：宿主私有方法 {@code H5Activity.m1(boolean)}。 */
    public static final String PRIVATE_H5_M1 = "soul-demo/private-h5-m1";

    /** Activity 生命周期触发点。 */
    public static final String ACTIVITY_ON_RESUME = "soul-demo/activity-onResume";
}