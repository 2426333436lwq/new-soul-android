package dev.local.newsoul;

/** 宿主常量。所有字符串均来自原始 DEX 字面证据，见 re_work/NAMING.md 第五节。 */
public final class Host {

    private Host() {}

    public static final String PACKAGE = "cn.soulapp.android";

    /** 私有设置页入口，模块的设置 UI 触发器 */
    public static final String H5_ACTIVITY = "cn.soulapp.android.h5.activity.H5Activity";
    public static final String H5_METHOD = "m1";           // m1(boolean)，私有

    // ---------------------------------------------------------------- 广告
    public static final String[] AD_VIEWS = {
            "cn.soulapp.android.ad.base.view.FloatAdView",
            "cn.soulapp.android.ad.base.view.FloatAdViewV2",
            "cn.soulapp.android.ad.base.view.DraggableFloatAdView",
            "cn.soulapp.android.ad.base.view.FloatEntranceView",
            "cn.soulapp.android.ad.base.view.SearchBannerAdView",
            "cn.soulapp.android.ad.base.view.AdFloatViewProvider",
            "cn.soulapp.android.ad.base.view.e",
            "cn.soulapp.android.ad.dialog.InterstitialAdDialog",
            "cn.soulapp.android.ad.dialog.InterstitialAdBottomDialog",
    };
    public static final String[] AD_EXPRESS_VIEWS = {
            "cn.soulapp.android.ad.soulad.ad.views.express.render.EnvelopeMessageFeedExpressView",
            "cn.soulapp.android.ad.soulad.ad.views.express.render.FeedDrawExpressView",
            "cn.soulapp.android.ad.soulad.ad.views.express.render.FeedWebExpressView",
            "cn.soulapp.android.ad.soulad.ad.views.express.render.GroupBannerExpressView",
            "cn.soulapp.android.ad.soulad.ad.views.express.render.MeCellExpressView",
            "cn.soulapp.android.ad.soulad.ad.views.express.render.PlantGameExpressView",
            "cn.soulapp.android.ad.soulad.ad.views.express.render.PlantGameTagExpressView",
            "cn.soulapp.android.ad.soulad.ad.views.express.render.PostDetailExpressView",
            "cn.soulapp.android.ad.soulad.ad.views.express.render.PostFeedExpressComponent",
            "cn.soulapp.android.ad.soulad.ad.views.express.render.SystemNoticeFeedExpressView",
            "cn.soulapp.android.ad.soulad.ad.views.express.render.TagBannerExpressView",
    };
    public static final String AD_REQUEST_LISTENER =
            "cn.soulapp.android.ad.core.services.plaforms.listener.SoulAdRequestListener";

    // ---------------------------------------------------------------- IM
    public static final String CHAT_MANAGER = "cn.soulapp.imlib.ChatManager";
    public static final String CONVERSATION = "cn.soulapp.imlib.Conversation";
    public static final String ROAM_HANDLER = "cn.soulapp.imlib.handler.RoamHandler";
    public static final String MSG_LISTENER = "cn.soulapp.imlib.listener.MsgListener";
    public static final String IM_MESSAGE = "cn.soulapp.imlib.msg.ImMessage";
    public static final String CHAT_MESSAGE = "cn.soulapp.imlib.msg.chat.ChatMessage";
    public static final String DICE_FINGER_MSG = "cn.soulapp.imlib.msg.chat.DiceFingerMsg";
    public static final String TEXT_MSG = "cn.soulapp.imlib.msg.chat.TextMsg";
    public static final String AUDIO_MSG = "cn.soulapp.imlib.msg.chat.AudioMsg";
    public static final String IMG_MSG = "cn.soulapp.imlib.msg.chat.ImgMsg";
    public static final String IMG_MSGS = "cn.soulapp.imlib.msg.chat.ImgMsgs";

    /** 宿主方法名候选（反射查找，非硬编码） */
    public static final String[] M_MSG_CONTENT = {"getMsgContent", "getMessage"};
    public static final String[] M_MSG_TYPE = {"getMsgType"};
    public static final String[] M_MSG_ID = {"getMsgId"};
    public static final String[] M_FROM = {"getFrom"};
    public static final String[] M_TO = {"getTo"};
    public static final String[] M_CHAT_MESSAGE = {"getChatMessage"};
    public static final String[] M_TRANS_EXT = {"getStringTransExt"};
    public static final String[] M_ANONYMITY = {"isAnonymityChat"};

    // ---------------------------------------------------------------- 聊天限制
    public static final String MSG_LIMIT_MANAGER =
            "cn.soulapp.android.component.chat.helper.MsgLimitManager";
    public static final String WITH_DRAW_HANDLER =
            "cn.soulapp.android.component.chat.helper.WithDrawHandler";
    public static final String CHAT_LIMIT_MODEL =
            "cn.soulapp.android.lib.common.bean.ChatLimitModel";
    public static final String[] M_IS_LIMIT = {"isLimit", "checkLimitOpt"};
    public static final String[] M_SHOW_POPUP = {"getShowPopup"};

    // ---------------------------------------------------------------- 社交分
    public static final String SOCIAL_SCORE_UTILS =
            "cn.soulapp.android.component.planet.soulmatch.utils.SocialScoreUtils";
    public static final String SOCIAL_GRACE_BEAN =
            "cn.soulapp.android.component.chat.bean.SocialGraceScoreBean";
    public static final String[] M_NO_REPLY = {"getCanNotSendBeforeReply"};
    public static final String[] M_SESSION_LIMIT = {"getCreateSessionLimit"};
    public static final String[] M_FORBID_CALL = {"getForbidCall"};
    public static final String[] M_FORBID_PIC = {"getForbidPic"};

    // ---------------------------------------------------------------- 隐私
    public static final String SNAP_ACTIVITY =
            "cn.soulapp.android.component.chat.snapchat.SnapChatActivity";
    public static final String PRIVACY_PREVIEW_ACTIVITY =
            "cn.soulapp.android.component.chat.snapchat.PrivacyProtectPreviewActivity";

    // ---------------------------------------------------------------- 水印
    public static final String IMAGE_DOWNLOADER =
            "cn.soulapp.android.square.utils.ImageDownloader";
    public static final String IMAGE_MARK_PROCESSOR =
            "cn.soulapp.android.square.utils.ImageMarkProcessor";

    // ---------------------------------------------------------------- 广场
    public static final String SQUARE_ADAPTER =
            "cn.soulapp.android.component.square.main.squarepost.SquareAdapter";
    public static final String BASE_SQUARE_VH =
            "cn.soulapp.android.component.square.main.squarepost.viewholder.BaseSquareViewHolder";
    public static final String SQUARE_POST = "cn.soulapp.android.square.post.bean.Post";
    public static final String SQUARE_TAG = "cn.soulapp.android.square.bean.Tag";
    public static final String SQUARE_FLOATING_BUTTON =
            "cn.soulapp.android.component.square.widget.SquareFloatingButton";
    public static final String BASE_QUICK_ADAPTER =
            "com.chad.library.adapter.base.BaseQuickAdapter";
    public static final String[] M_ADAPTER_DATA = {
            "setData", "replaceData", "setNewData", "setDiffNewData",
            "setList", "setNewInstance", "addData", "onRefreshUi",
    };

    // ---------------------------------------------------------------- 分享
    public static final String SEEDS_SHARE_DIALOG =
            "cn.soulapp.android.square.SeedsShareDialogFragment";
    public static final String BASE_SEEDS_DIALOG =
            "cn.soulapp.android.square.BaseSeedsDialogFragment";

    // ---------------------------------------------------------------- 主页伪装
    public static final String USER_HOME_ACTIVITY =
            "cn.soulapp.android.component.home.user.UserHomeActivity";
    public static final String USER_HOME_HEADER_HELPER =
            "cn.soulapp.android.component.home.me.UserHomeHeaderHelper";
    public static final String META_FOLLOW_FANS_VIEW =
            "cn.soulapp.android.component.home.meta.view.MetaFollowFansContainerView";
    public static final String USER_FOLLOW_COUNT =
            "cn.soulapp.android.component.home.api.user.user.bean.UserFollowCount";
    public static final String REVISION_PROFILE_PROVIDER =
            "cn.soulapp.android.component.home.revision.RevisionProfileProvider";
    public static final String IM_USER_BEAN =
            "cn.soulapp.android.client.component.middle.platform.model.api.user.ImUserBean";

    // ---------------------------------------------------------------- 启动
    public static final String MAIN_ACTIVITY =
            "cn.soulapp.android.component.startup.main.MainActivity";
    public static final String MAIN_SCHEDULER =
            "cn.soulapp.android.component.startup.main.MainScheduler";
    public static final String SOUL_SETTINGS =
            "cn.soulapp.android.lib.common.utils.settings.SoulSettings";

    // ---------------------------------------------------------------- 其他
    public static final String SOUL_MUSIC_HELPER =
            "cn.soulapp.android.component.planet.soulmatch.utils.SoulMusicHelper";
    public static final String REWARD_AD_HELPER =
            "cn.soulapp.android.component.chat.limitdialog.RewardAdHelper";
    public static final String I_MEDIA_KEYBOARD =
            "cn.soulapp.android.lib_input.view.IMediaKeyBoard";
    public static final String ABS_CHAT_DUAL_ITEM =
            "cn.soulapp.android.component.chat.widget.AbsChatDualItem";

    /** 适配宿主版本（原始模块适配 Soul 6.37.0） */
    public static final String TARGET_SOUL_VERSION = "6.37.0";
}
