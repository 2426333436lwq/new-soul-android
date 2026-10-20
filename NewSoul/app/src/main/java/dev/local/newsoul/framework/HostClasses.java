package dev.local.newsoul.framework;

/**
 * 宿主 ({@code cn.soulapp.android}) 目标类名常量表。
 *
 * <p>模块以字符串形式引用宿主类并在运行时反射加载，因此这些名字是模块与宿主之间
 * 唯一稳定的耦合点 —— 宿主升级导致类名变化时，只会在对应功能域上产生「Hook 未就绪」
 * 日志，而不会影响其它功能。</p>
 */
public final class HostClasses {

    private HostClasses() {
    }

    /** 宿主包名。 */
    public static final String PACKAGE = "cn.soulapp.android";

    /**
     * 宿主 IM 库包名。
     *
     * <p>注意：IM 库位于 {@code cn.soulapp.imlib}，<b>不在</b> {@code cn.soulapp.android} 之下，
     * 命名空间与 {@link #PACKAGE} 平行。</p>
     */
    public static final String IMLIB_PACKAGE = "cn.soulapp.imlib";

    // ---------------------------------------------------------------- 广告

    public static final String AD_FLOAT_VIEW_PROVIDER = PACKAGE + ".ad.base.view.AdFloatViewProvider";
    public static final String AD_DRAGGABLE_FLOAT_VIEW = PACKAGE + ".ad.base.view.DraggableFloatAdView";
    public static final String AD_FLOAT_VIEW = PACKAGE + ".ad.base.view.FloatAdView";
    public static final String AD_FLOAT_VIEW_V2 = PACKAGE + ".ad.base.view.FloatAdViewV2";
    public static final String AD_FLOAT_ENTRANCE_VIEW = PACKAGE + ".ad.base.view.FloatEntranceView";
    public static final String AD_SEARCH_BANNER_VIEW = PACKAGE + ".ad.base.view.SearchBannerAdView";
    public static final String AD_INTERSTITIAL_BOTTOM_DIALOG = PACKAGE + ".ad.dialog.InterstitialAdBottomDialog";
    public static final String AD_INTERSTITIAL_DIALOG = PACKAGE + ".ad.dialog.InterstitialAdDialog";
    public static final String AD_SOUL_AD_REQUEST_LISTENER =
            PACKAGE + ".ad.core.services.plaforms.listener.SoulAdRequestListener";

    /** 信息流内嵌广告视图（Express 渲染层）。 */
    public static final String AD_EXPRESS_ENVELOPE_MESSAGE =
            PACKAGE + ".ad.soulad.ad.views.express.render.EnvelopeMessageFeedExpressView";
    public static final String AD_EXPRESS_FEED_DRAW =
            PACKAGE + ".ad.soulad.ad.views.express.render.FeedDrawExpressView";
    public static final String AD_EXPRESS_FEED_WEB =
            PACKAGE + ".ad.soulad.ad.views.express.render.FeedWebExpressView";
    public static final String AD_EXPRESS_GROUP_BANNER =
            PACKAGE + ".ad.soulad.ad.views.express.render.GroupBannerExpressView";
    public static final String AD_EXPRESS_ME_CELL =
            PACKAGE + ".ad.soulad.ad.views.express.render.MeCellExpressView";
    public static final String AD_EXPRESS_PLANT_GAME =
            PACKAGE + ".ad.soulad.ad.views.express.render.PlantGameExpressView";
    public static final String AD_EXPRESS_PLANT_GAME_TAG =
            PACKAGE + ".ad.soulad.ad.views.express.render.PlantGameTagExpressView";
    public static final String AD_EXPRESS_POST_DETAIL =
            PACKAGE + ".ad.soulad.ad.views.express.render.PostDetailExpressView";
    public static final String AD_EXPRESS_POST_FEED_COMPONENT =
            PACKAGE + ".ad.soulad.ad.views.express.render.PostFeedExpressComponent";
    public static final String AD_EXPRESS_SYSTEM_NOTICE =
            PACKAGE + ".ad.soulad.ad.views.express.render.SystemNoticeFeedExpressView";
    public static final String AD_EXPRESS_TAG_BANNER =
            PACKAGE + ".ad.soulad.ad.views.express.render.TagBannerExpressView";

    // ---------------------------------------------------------------- 聊天 / IM

    /**
     * 宿主私有的会话标识提供者（混淆类名）。
     *
     * <p>提供静态无参方法 {@code g()}，返回当前会话的标识字符串。</p>
     */
    public static final String IMLIB_SESSION_PROVIDER = IMLIB_PACKAGE + ".j0";

    public static final String IMLIB_CHAT_MANAGER = IMLIB_PACKAGE + ".ChatManager";
    public static final String IMLIB_CONVERSATION = IMLIB_PACKAGE + ".Conversation";
    public static final String IMLIB_ROAM_HANDLER = IMLIB_PACKAGE + ".handler.RoamHandler";
    public static final String IMLIB_MSG_LISTENER = IMLIB_PACKAGE + ".listener.MsgListener";
    public static final String IMLIB_MESSAGE_HUB = IMLIB_PACKAGE + ".i0";
    public static final String IMLIB_IM_MESSAGE = IMLIB_PACKAGE + ".msg.ImMessage";
    public static final String IMLIB_CHAT_MESSAGE = IMLIB_PACKAGE + ".msg.chat.ChatMessage";
    public static final String IMLIB_DICE_FINGER_MSG = IMLIB_PACKAGE + ".msg.chat.DiceFingerMsg";
    public static final String IMLIB_TEXT_MSG = IMLIB_PACKAGE + ".msg.chat.TextMsg";
    public static final String IMLIB_AUDIO_MSG = IMLIB_PACKAGE + ".msg.chat.AudioMsg";
    public static final String IMLIB_IMG_MSG = IMLIB_PACKAGE + ".msg.chat.ImgMsg";
    public static final String IMLIB_IMG_MSGS = IMLIB_PACKAGE + ".msg.chat.ImgMsgs";

    public static final String CHAT_WITH_DRAW_HANDLER =
            PACKAGE + ".component.chat.helper.WithDrawHandler";
    public static final String CHAT_MSG_LIMIT_MANAGER =
            PACKAGE + ".component.chat.helper.MsgLimitManager";
    public static final String CHAT_UTILS = PACKAGE + ".component.chat.utils.ChatUtils";
    public static final String CHAT_ABS_DUAL_ITEM =
            PACKAGE + ".component.chat.widget.AbsChatDualItem";
    public static final String CHAT_LONG_CLICK_POPUP =
            PACKAGE + ".component.chat.window.ChatLongClickPopUp";

    /** 长按菜单项类型（{@code ChatLongClickPopUp} 的内部类）。 */
    public static final String CHAT_LONG_CLICK_ITEM = CHAT_LONG_CLICK_POPUP + "$a";

    public static final String CHAT_LIMIT_MODEL = PACKAGE + ".lib.common.bean.ChatLimitModel";
    public static final String CHAT_SOCIAL_GRACE_SCORE_BEAN =
            PACKAGE + ".component.chat.bean.SocialGraceScoreBean";

    /** 礼物限制对话框的「开始发送」回调接口（{@code LimitGiftDialog} 内部类）。 */
    public static final String CHAT_LIMIT_GIFT_DIALOG_LISTENER =
            PACKAGE + ".component.chat.dialog.LimitGiftDialog$OnStartGiftSendListener";

    /** 聊天样式处理（{@code checkLimitOpt} 的宿主）。 */
    public static final String CHAT_STYLE_BASE_PROCESS =
            PACKAGE + ".component.chat.style.BaseConversationStyleProcess";

    /** 会话 Fragment 的内部类（社交分判定使用，类名以 {@code $d1} 结尾）。 */
    public static final String CHAT_CONVERSATION_FRAGMENT_D1 =
            PACKAGE + ".component.chat.fragment.ConversationFragment$d1";

    /** 宿主长按菜单回调上下文内部类（社交分 deopt 目标之一）。 */
    public static final String UJ_C0_Q = "uj.c0$q";

    /** androidx FragmentManager（社交分对话框方法 {@code SocialScoreUtils.b} 的形参）。 */
    public static final String FRAGMENT_MANAGER = "androidx.fragment.app.FragmentManager";

    // ---------------------------------------------------------------- 聊天：复读 / 转发

    /** 会话界面 Fragment（长按菜单与消息菜单渲染的宿主）。 */
    public static final String CHAT_CONVERSATION_FRAGMENT =
            PACKAGE + ".component.chat.fragment.ConversationFragment";

    /** 聊天工具类：长按条目处理与消息体工具。 */
    public static final String CHAT_UTILS_U0 = PACKAGE + ".component.chat.utils.u0";

    /** 聊天工具类：消息体落库、发送与文本回填。 */
    public static final String CHAT_UTILS_Y4 = PACKAGE + ".component.chat.utils.y4";
    /** 消息发送辅助（{@code cn.soulapp.android.chat.utils}，注意不是 {@code component.chat.utils}）。 */
    public static final String CHAT_SEND_HELPER = PACKAGE + ".chat.utils.f";

    /** 选择会话页（转发目标选择）。 */
    public static final String CHAT_SELECT_CONVERSATION_ACTIVITY =
            PACKAGE + ".component.chat.SelectConversationActivity";

    /** 转发确认对话框。 */
    public static final String CHAT_CONFIRM_DIALOG = PACKAGE + ".component.chat.dialog.j1";

    /** 会话列表适配器。 */
    public static final String CHAT_CONVERSATION_ADAPTER =
            PACKAGE + ".component.chat.adapter.ConversationAdapter";

    /** 确认点击监听（转发确认框回调接口）。 */
    public static final String CHAT_ON_CLICK_CONFIRM_LISTENER =
            PACKAGE + ".chat.listener.OnClickConfirmListener";

    /** 会话数据 bean（宿主侧按用户维度聚合）。 */
    public static final String CHAT_USER_CONVERSATION = PACKAGE + ".chat.bean.UserConversation";

    /** 第三方 RecyclerView 适配器条目点击接口（转发选择页使用）。 */
    public static final String EASY_RECYCLER_ITEM_CLICK_LISTENER =
            "com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter$OnItemClickListener";

    /** 宿主会话视图模型（转发选择页的 presenter 持有类型）。 */
    public static final String UJ_K1 = "uj.k1";

    /** 宿主长按菜单回调上下文类型（{@code u0.p} 的第 4 个形参）。 */
    public static final String UJ_C0 = "uj.c0";

    /** 聊天输入区媒体键盘（文本复读回填目标）。 */
    public static final String LIB_INPUT_MEDIA_KEYBOARD = "cn.soulapp.lib_input.view.IMediaKeyBoard";

    /** 输入区用户信息（文本复读回填目标）。 */
    public static final String IMLIB_USER_BEAN =
            PACKAGE + ".client.component.middle.platform.model.api.user.ImUserBean";

    // ---------------------------------------------------------------- 主页 / 资料

    public static final String HOME_USER_HOME_ACTIVITY =
            PACKAGE + ".component.home.user.UserHomeActivity";
    public static final String HOME_USER_HOME_HEADER_HELPER =
            PACKAGE + ".component.home.me.UserHomeHeaderHelper";
    public static final String HOME_META_FOLLOW_FANS_CONTAINER_VIEW =
            PACKAGE + ".component.home.meta.view.MetaFollowFansContainerView";
    public static final String HOME_USER_FOLLOW_COUNT =
            PACKAGE + ".component.home.api.user.user.bean.UserFollowCount";
    public static final String HOME_REVISION_PROFILE_PROVIDER =
            PACKAGE + ".component.home.revision.RevisionProfileProvider";
    public static final String HOME_REVISION_PROFILE_VIEW_HOLDER =
            HOME_REVISION_PROFILE_PROVIDER + "$ViewHolder";

    /** 资料页 meta 关注/粉丝容器布局绑定类（关注数伪装 meta 分支的 view 类）。 */
    public static final String HOME_META_FOLLOW_FANS_CONTAINER_BINDING =
            PACKAGE + ".component.home.databinding.CUsrMetaFollowFansContainerBinding";

    /** 资料页 meta 用户信息提供者（注册天数伪装 meta 分支目标类）。 */
    public static final String HOME_META_USER_INFO_PROVIDER =
            PACKAGE + ".component.home.meta.me2.userinfo.UserInfoProvider";

    /** 注册天数伪装 meta 分支的 view 类（布局绑定）。 */
    public static final String HOME_META_USER_INFO_BINDING =
            PACKAGE + ".component.home.databinding.CUsrLayoutMeInfoNewBinding";

    /** 宿主中部平台工具类：计数文本格式化方法 {@code e(int)} / {@code f(int)}。 */
    public static final String LIB_MIDDLE_PLATFORM_UTILS_M2 =
            PACKAGE + ".client.component.middle.platform.utils.m2";

    /** 宿主用户 bean（注册天数伪装通过 {@code registerDay} 字段取值）。 */
    public static final String SOUL_COMPONENT_USER =
            "com.soul.component.componentlib.service.user.bean.User";

    public static final String HOME_LOVE_BELL_PRO_GIFT_DIALOG =
            PACKAGE + ".component.home.lovebell.LoveBellProGiftDialog";
    public static final String HOME_LOVE_BELL_PRO_CONFIG =
            PACKAGE + ".component.home.lovebell.bean.LoveBellProConfig";

    // ---------------------------------------------------------------- 广场 / 帖子

    public static final String SQUARE_POST_BEAN = PACKAGE + ".square.post.bean.Post";
    public static final String SQUARE_SEEDS_SHARE_DIALOG_FRAGMENT =
            PACKAGE + ".square.SeedsShareDialogFragment";
    public static final String SQUARE_BASE_SEEDS_DIALOG_FRAGMENT =
            PACKAGE + ".square.BaseSeedsDialogFragment";
    public static final String SQUARE_TYPE_ADAPTER = PACKAGE + ".square.adapter.TypeAdapter";
    public static final String SQUARE_TYPE_ADAPTER_INNER = PACKAGE + ".square.adapter.TypeAdapter$a";

    /** 分享面板宿主辅助类（资料页书签 deopt 目标之一）。 */
    public static final String SQUARE_A0 = PACKAGE + ".square.a0";
    public static final String SQUARE_SQUARE_ADAPTER =
            PACKAGE + ".component.square.main.squarepost.SquareAdapter";
    public static final String SQUARE_BASE_SQUARE_VIEW_HOLDER =
            PACKAGE + ".component.square.main.squarepost.viewholder.BaseSquareViewHolder";
    public static final String SQUARE_AD_EXPRESS_VIEW_HOLDER =
            PACKAGE + ".component.square.main.ad.AdExpressViewHolder";
    public static final String SQUARE_AD_POST_VIEW_HOLDER =
            PACKAGE + ".component.square.main.ad.AdPostViewHolder";
    public static final String SQUARE_FLOATING_BUTTON =
            PACKAGE + ".component.square.widget.SquareFloatingButton";

    public static final String SQUARE_IMAGE_DOWNLOADER = PACKAGE + ".square.utils.ImageDownloader";
    public static final String SQUARE_IMAGE_MARK_PROCESSOR = PACKAGE + ".square.utils.ImageMarkProcessor";
    public static final String SQUARE_UTILS_B = PACKAGE + ".square.utils.b";
    public static final String SQUARE_UTILS_H2 = PACKAGE + ".square.utils.h2";

    // ---------------------------------------------------------------- 启动 / 设置

    public static final String STARTUP_MAIN_ACTIVITY =
            PACKAGE + ".component.startup.main.MainActivity";

    /** 启动调度器：热启动广告闸门所在（原 new-soul/hot-splash-gate）。 */
    public static final String STARTUP_MAIN_SCHEDULER =
            PACKAGE + ".component.startup.main.MainScheduler";
    public static final String H5_ACTIVITY = PACKAGE + ".h5.activity.H5Activity";
    public static final String LIB_SOUL_SETTINGS = PACKAGE + ".lib.common.utils.settings.SoulSettings";
    public static final String PLANET_SOCIAL_SCORE_UTILS =
            PACKAGE + ".component.planet.soulmatch.utils.SocialScoreUtils";

    /** 社交分对话框回调接口（{@code SocialScoreUtils} 内部类，方法 {@code b} 的形参）。 */
    public static final String PLANET_SOCIAL_SCORE_UTILS_A = PLANET_SOCIAL_SCORE_UTILS + "$a";
    public static final String PLANET_SOUL_MUSIC_HELPER =
            PACKAGE + ".component.planet.soulmatch.utils.SoulMusicHelper";

    // ---------------------------------------------------------------- 隐私

    public static final String PRIVACY_PROTECT_PREVIEW_ACTIVITY =
            PACKAGE + ".component.chat.snapchat.PrivacyProtectPreviewActivity";
    public static final String SNAP_CHAT_ACTIVITY = PACKAGE + ".component.chat.snapchat.SnapChatActivity";

    // ---------------------------------------------------------------- 混淆辅助类

    /**
     * 宿主私有的全局上下文提供者。
     *
     * <p>不属于 {@code cn.soulapp.android} 包，是宿主内部混淆类；提供静态无参方法返回
     * 应用级 {@link android.content.Context}，供无法直接拿到上下文的水印开关回调使用。</p>
     */
    public static final String PRIVATE_CONTEXT_PROVIDER = "lf.b";
}