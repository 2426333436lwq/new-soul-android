# New Soul 1.34.0 — 语义命名映射表（二次开发用）

本表把 216 个混淆类名映射到可读名称。**命名依据全部来自 DEX 字面证据**（字符串、方法、字段、调用关系），不是猜测。

## 命名规则

- `dev.local.newsoul.<Name>`：模块自己的类
- 每个名称后面的「证据」列列出该命名的直接来源
- 无法确定语义的类标记为 `Ctx`（上下文/工具类）并给出已知用途

---

## 一、核心框架

| 混淆名 | 建议命名 | 证据 |
|---|---|---|
| `SoulHook` | `SoulHook`（原名保留） | `java_init.list` 指定入口；字段 `TAG="SoulHook102"`, `TARGET="cn.soulapp.android"` |
| `Q0` | `HookRegistry`（hook 注册中心） | 唯一持有 29 个安装器方法的类；`loadClass`+`getDeclaredMethod`+`hook` 三件套 |
| `Q0.A` | `installImageWatermarkRemoval` | 字符串 `download_image_no_watermark`, `ImageDownloader`, `ImageMarkProcessor` |
| `Q0.B` | `installPostCopyUnlock` | `new-soul/post-copy-action`, `SeedsShareDialogFragment`, `initViews` |
| `Q0.C` | `installPostShareUnlock` | `SeedsShareDialogFragment`, `post copy hooks` |
| `Q0.D` | `installPrivacyPhotoControls` | `new-soul/privacy-photo-preview`, `PrivacyProtectPreviewActivity`, `SnapChatActivity` |
| `Q0.E` | `installSocialScoreUnlock` | `new-soul/social-score-`, `SocialScoreUtils`, `getCanNotSendBeforeReply` |
| `Q0.F` | `installSquareLike` | `new-soul/square-like-*`, `SquareFloatingButton`, `getAdResponse` |
| `Q0.G` | `hookMethodBySignature` | 通用：`getDeclaredMethod` + 参数类型匹配 |
| `Q0.H` | `hookNamedMethod` | 通用：`getDeclaredMethod(name)` |
| `Q0.I` | `hookFieldWrite` | 泛型字段 hook 原语 |
| `Q0.J` | `findMethodByParamTypes` | 泛型方法查找 |
| `Q0.K` | `installChatRowBinding` | `AbsChatDualItem`, `"Ambiguous chat row binding"` |
| `Q0.L` | `sha256Hex` | `MessageDigest("SHA-256")` + `%02x` |
| `Q0.M` | `rsaPublicKey` | `X509EncodedKeySpec`, `KeyFactory("RSA")` |
| `Q0.Q` | `describeSignature` | 构造 `"签名存在 ·"` 诊断串 |
| `Q0.R` | `isSnapChat` | `isSnap` |
| `Q0.S` | `logRetainedRecall` | `"recall retained; source=private; type="` |
| `Q0.U` | `buildHookDiagnostics` | 全部宿主类名 + `"Hook 已注"`/`"Hook 未"` |
| `Q0.W` | `newProxyOf` | `Proxy.newProxyInstance` |
| `Q0.X` | `hostPackageName` | `"dev.local.newsoul."` + `"cn.soulapp."` |
| `Q0.x` | `installChatTypingAndMusic` | `new-soul/hide-typing`, `new-soul/match-music-` |
| `Q0.z` | `installGiftChatUnlock` | `new-soul/gift-chat-*`, `MsgLimitManager`, `checkLimitOpt` |

## 二、Hook 执行体（`XposedInterface$Hooker` 实现，23 个）

| 混淆名 | 建议命名 | 职责 | 证据 |
|---|---|---|---|
| `a` | `ActivityDispatchHooker` | 主 hook 分发器（packed-switch 多路） | `intercept` 内含 `packed-switch`，分支覆盖 ad/chat/privacy/square |
| `a1` | `ConversationForwardHooker` | 会话转发 UI | `message_forward_token`, `SelectConversationActivity` |
| `b` | `ChatStyleHooker` | 撤回消息样式 | `chat_recall_style`, `recall message style unavailable` |
| `B` | `RecallMarkHooker` | 撤回标记 | `dev.local.soulhookdemo.recalled.v1` |
| `c` | `FeedDataFilterHooker` | 广场数据过滤 | `feed data filter`, `Class.forName` |
| `C` | `RepeatMenuHooker` | 复读菜单 | `repeat.menu.*`, `repeat unavailable` |
| `d` | `FieldValueHooker` | 泛型字段读取 hook | `Class.getMethod` + `Field.get` |
| `D` | `AdVisibilityHooker` | 广告视图隐藏 | `setVisibility`, `not a View`, `AdFloatViewProvider` |
| `e` | `LikeIconHooker` | 点赞图标 | `ImageView.getDrawable`, `BitmapDrawable` |
| `E`（类） | `MessageTypeSwitch` | 消息类型分派 | `getMsgType`, `ImMessage`, `ChatMessage` |
| `f` | `SplashGateHooker` | 启动页拦截 | `block_splash`, `soul match music` |
| `f1` | `RecallAgeHooker` | 撤回时限 | `chat_own_recall_no_time_limit`, `Boolean.booleanValue` |
| `F`（类） | `ImMessageAccessor` | IM 消息反射访问 | `AudioMsg`/`ImgMsg`/`ImgMsgs`/`TextMsg` |
| `k1` | `ChatGiftPickerHooker` | 礼物选择器 | `new-soul/game-picker/`, `Q0.q` |
| `l0` | `FollowCountDisguiseHooker` | 关注数伪装 | 7 个 `reflect.Field` |
| `m0` | `FollowedCountDisguiseHooker` | 粉丝数伪装 | `Field.getInt`, `new-soul/follow-disguise-` |
| `n0` | `VisitorCountDisguiseHooker` | 访客数伪装 | `new-soul/profile-bookmark-*` |
| `N2` | `VoiceLibraryHooker` | 语音库入口 | `voice-library-entry` |
| `p` | `DiceFingerResultHooker` | 骰子猜拳结果 | `game_finger`, `new-soul/game-message-result` |
| `p`（类，`p0`） | `ProfileDisguiseCore` | 资料伪装核心 | `UserFollowCount`, `UserHomeHeaderHelper` |
| `s1` | `ChatLimitDialogHooker` | 聊天限制弹窗 | `getShowPopup`, `MsgLimitManager` |
| `v` | `AutoCloseScopeHooker` | 资源自动关闭 | `AutoCloseable`, `y.close()` |
| `x2` | `SettingsEntryHooker` | 设置入口 | `SoulHook`, `settings entry` |
| `X0` | `GenericMethodHooker` | 通用方法 hook | `Method.invoke`, `List.toArray` |
| `y2` | `StartupIntentHooker` | 启动 Intent hook | `onNewIntent`, `new-soul/cold-launch-flag` |
| `z2` | `ViewHolderHooker` | ViewHolder 注入 | `WeakHashMap`, `Class.getField` |

## 三、功能子系统实现类

| 混淆名 | 建议命名 | 证据 |
|---|---|---|
| `G` | `FeatureCatalog`（功能目录 + 配置序列化） | 44 个键名；`newsoul-config`, `schemaVersion`, `org.json.JSONObject` |
| `G.a` | `serializeSettings` | `JSONObject.toString(4)` |
| `G.c` | `deserializeSettings` | `JSONTokener`, `unmodifiableMap` |
| `G.d` | `validateEntry` | `ANY`/`MALE`/`FEMALE`, `\\R`, `最多保留`, `每个屏蔽` |
| `V` | `DiagnosticLog`（诊断日志） | `LinkedHashMap`+`UUID`, `scheduleAtFixedRate` |
| `V.e` | `exportEncrypted` | `ZipOutputStream` + `W.a` 混合加密, `Size limit`, `.chunk` |
| `V.h` | `startRecording` | `getSharedPreferences("newsoul_debug")` |
| `V.k` | `recordRegistration` | `"format=1; host="` 设备指纹串 |
| `W` | `HybridCrypto` | `AES/GCM/NoPadding`, `RSA/ECB/OAEPWithSHA-256AndMGF1Padding` |
| `W.a` | `encrypt` | `SecretKeySpec`, `GCMParameterSpec`, `updateAAD` |
| `w2` | `UiFactory`（设置 UI 工厂） | 24 个 UI 构件方法 `c/d/e/f/j/k/l/m/n/o/r/t/u/v/w/x` |
| `q2` | `SettingsPanel` | `AlertDialog$Builder`, `IdentityHashMap`, `BooleanSupplier` |
| `r2` | `ToggleSwitch` | `extends Switch`, `setChecked`/`toggle`/`setEnabled` |
| `v2` | `SettingsTabs` | `HorizontalScrollView`, `ScrollView[]`, `TextView[]` |
| `Z0` | `SettingsSwitchboard` | `Z0.g` 读 44 个键 |
| `Z1` | `HostViewLocator` | `findViewById`, `getIdentifier`, `drawable` |
| `I1` | `SettingsEntryInjector` | `settings entry inserted`, `settings.entry.v2` |
| `I2` | `SquareLikeEngine` | `square_auto_like`, `square_visible_like` |
| `B2` | `FeedFilterEngine` | `feed_filter_*`, `feed_keywords_v1` |
| `S0` | `UpdateChecker` | `Mozilla/5.0 NewSoul-UpdateCheck`, lanzoul |
| `T0` | `UpdateDownloader` | `HttpURLConnection`, `Location` |
| `d3` | `FileDownloader` | `Invalid redirect`, `getHeaderField("Location")` |
| `x1` | `VoiceLibraryStore` | `newsoul-voice-library/`, `.bin`, `SHA-256` |
| `j` | `HookInstaller`（通用安装原语） | `j.f/j.g/j.h` 三件套 |
| `p0` | `ReflectionHelper` | 通用反射读写 |
| `u1` | `TypeAdapterPatcher` | `TypeAdapter`, `TypeAdapter$a` |
| `d1` | `ForwardSessionBuilder` | `ConversationAdapter`, `ChatManager` |
| `t` | `StartupHook` | `MainActivity`, `SoulSettings` |
| `t1` | `ChatSessionDescriptor` | `String`+`String`+`String`+`Object` |
| `e1` | `ForwardRecord` | `nickname`, `J e`(时间戳), `Z f` |
| `h3` | `VoiceFileEntry` | `File e`, `J d` |
| `b3` | `ChatRowBinder` | `AbsChatDualItem`, `MediaPlayer` |
| `y0` | `EditablePref` | `EditText`, `SharedPreferences`, `r2` |

## 四、数据/工具类（`Ctx`）

| 混淆名 | 建议命名 | 用途 |
|---|---|---|
| `z` | `HookStats` | `J a` + `I b/c/d` → 命中计数 |
| `y` | `TwoSlotScope` | `AutoCloseable`，持有两个 `z` |
| `u` | `AutoCloseScope` | `ThreadLocal g`, 三层 `close()` |
| `w` | `Predicate1` | `Function`/`Predicate` lambda 载体 |
| `H`/`H1`/`H2` | `RunnableN` | 通用 `Runnable` 载体 |
| `K`/`K1` | `RunnableM` | 通用 `Runnable` 载体 |
| `Z`/`Z1`/`Z2` | `RunnableK` | 通用 `Runnable` 载体 |
| `e2`/`f2` | `AccessibilityDelegateN` | `AccessibilityDelegate` |
| `e3`/`i1`/`v1` | `DrawableN` | `Drawable`（指示器/角标/圆形） |
| `i2`/`j2`/`t2`/`u2`/`b2`/`C1`/`E0`/`E2`/`R0`/`Z0`(类)/`M0` | `CustomViewN` | `View`/`LinearLayout`/`FrameLayout` 子类 |
| `O`/`O0`/`C2`/`O0` | `LifecycleCallbacksN` | `Application.ActivityLifecycleCallbacks` |
| `c3` | `Task` | 接口：`run()` |
| `P2` | `TaskCallback` | 接口：`a(String)` |
| `Q2`/`U2`/`W2`/`r`(类) | `TaskImplN` | `c3` + `P2` 实现 |

## 五、宿主类常量表（96 个，反射加载用）

按子系统分组，字符串形式存在于 DEX 中。二次开发时**直接复用这些字符串**即可定位宿主成员。

### 广告（21）
```
cn.soulapp.android.ad.base.view.AdFloatViewProvider
cn.soulapp.android.ad.base.view.DraggableFloatAdView
cn.soulapp.android.ad.base.view.FloatAdView
cn.soulapp.android.ad.base.view.FloatAdViewV2
cn.soulapp.android.ad.base.view.FloatEntranceView
cn.soulapp.android.ad.base.view.SearchBannerAdView
cn.soulapp.android.ad.base.view.e
cn.soulapp.android.ad.dialog.InterstitialAdBottomDialog
cn.soulapp.android.ad.dialog.InterstitialAdDialog
cn.soulapp.android.ad.soulad.ad.views.express.render.EnvelopeMessageFeedExpressView
cn.soulapp.android.ad.soulad.ad.views.express.render.FeedDrawExpressView
cn.soulapp.android.ad.soulad.ad.views.express.render.FeedWebExpressView
cn.soulapp.android.ad.soulad.ad.views.express.render.GroupBannerExpressView
cn.soulapp.android.ad.soulad.ad.views.express.render.MeCellExpressView
cn.soulapp.android.ad.soulad.ad.views.express.render.PlantGameExpressView
cn.soulapp.android.ad.soulad.ad.views.express.render.PlantGameTagExpressView
cn.soulapp.android.ad.soulad.ad.views.express.render.PostDetailExpressView
cn.soulapp.android.ad.soulad.ad.views.express.render.PostFeedExpressComponent
cn.soulapp.android.ad.soulad.ad.views.express.render.SystemNoticeFeedExpressView
cn.soulapp.android.ad.soulad.ad.views.express.render.TagBannerExpressView
cn.soulapp.android.ad.core.services.plaforms.listener.SoulAdRequestListener
```

### 聊天 / IM（33）
```
cn.soulapp.imlib.ChatManager                  cn.soulapp.imlib.Conversation
cn.soulapp.imlib.handler.RoamHandler          cn.soulapp.imlib.i0
cn.soulapp.imlib.j0                           cn.soulapp.imlib.listener.MsgListener
cn.soulapp.imlib.msg.ImMessage                cn.soulapp.imlib.msg.chat.ChatMessage
cn.soulapp.imlib.msg.chat.DiceFingerMsg       cn.soulapp.imlib.msg.chat.TextMsg
cn.soulapp.imlib.msg.chat.AudioMsg            cn.soulapp.imlib.msg.chat.ImgMsg
cn.soulapp.imlib.msg.chat.ImgMsgs             cn.soulapp.android.chat.bean.UserConversation
cn.soulapp.android.chat.listener.OnClickConfirmListener
cn.soulapp.android.chat.utils.f               cn.soulapp.android.component.chat.SelectConversationActivity
cn.soulapp.android.component.chat.adapter.ConversationAdapter
cn.soulapp.android.component.chat.bean.SocialGraceScoreBean
cn.soulapp.android.component.chat.dialog.LimitGiftDialog$OnStartGiftSendListener
cn.soulapp.android.component.chat.dialog.j1  cn.soulapp.android.component.chat.fragment.ConversationFragment
cn.soulapp.android.component.chat.fragment.ConversationFragment$d1
cn.soulapp.android.component.chat.helper.MsgLimitManager
cn.soulapp.android.component.chat.helper.WithDrawHandler
cn.soulapp.android.component.chat.limitdialog.RewardAdHelper
cn.soulapp.android.component.chat.snapchat.PrivacyProtectPreviewActivity
cn.soulapp.android.component.chat.snapchat.SnapChatActivity
cn.soulapp.android.component.chat.style.BaseConversationStyleProcess
cn.soulapp.android.component.chat.utils.ChatUtils
cn.soulapp.android.component.chat.utils.u0   cn.soulapp.android.component.chat.utils.y4
cn.soulapp.android.component.chat.widget.AbsChatDualItem
cn.soulapp.android.component.chat.window.ChatLongClickPopUp
cn.soulapp.android.component.chat.window.ChatLongClickPopUp$a
cn.soulapp.android.lib.common.bean.ChatLimitModel
cn.soulapp.android.lib_input.view.IMediaKeyBoard
```

### 主页 / 资料（10）
```
cn.soulapp.android.client.component.middle.platform.model.api.user.ImUserBean
cn.soulapp.android.client.component.middle.platform.utils.m2
cn.soulapp.android.component.home.api.user.user.bean.UserFollowCount
cn.soulapp.android.component.home.databinding.CUsrMetaFollowFansContainerBinding
cn.soulapp.android.component.home.lovebell.LoveBellProGiftDialog
cn.soulapp.android.component.home.lovebell.bean.LoveBellProConfig
cn.soulapp.android.component.home.me.UserHomeHeaderHelper
cn.soulapp.android.component.home.meta.view.MetaFollowFansContainerView
cn.soulapp.android.component.home.revision.RevisionProfileProvider
cn.soulapp.android.component.home.revision.RevisionProfileProvider$ViewHolder
cn.soulapp.android.component.home.user.UserHomeActivity
```

### 广场 / 帖子（19）
```
cn.soulapp.android.component.square.main.ad.AdExpressViewHolder
cn.soulapp.android.component.square.main.ad.AdPostViewHolder
cn.soulapp.android.component.square.main.squarepost.SquareAdapter
cn.soulapp.android.component.square.main.squarepost.viewholder.BaseSquareViewHolder
cn.soulapp.android.component.square.post.b1
cn.soulapp.android.component.square.video.adapter.a
cn.soulapp.android.component.square.video.drama.o0
cn.soulapp.android.component.square.widget.SquareFloatingButton
cn.soulapp.android.square.BaseSeedsDialogFragment
cn.soulapp.android.square.SeedsShareDialogFragment
cn.soulapp.android.square.a0
cn.soulapp.android.square.adapter.TypeAdapter
cn.soulapp.android.square.adapter.TypeAdapter$a
cn.soulapp.android.square.bean.Tag
cn.soulapp.android.square.post.bean.Post
cn.soulapp.android.square.utils.ImageDownloader
cn.soulapp.android.square.utils.ImageMarkProcessor
cn.soulapp.android.square.utils.b
cn.soulapp.android.square.utils.h2
```

### 启动 / 其他（8）
```
cn.soulapp.android.component.startup.main.MainActivity
cn.soulapp.android.component.startup.main.MainScheduler
cn.soulapp.android.component.planet.soulmatch.utils.SocialScoreUtils
cn.soulapp.android.component.planet.soulmatch.utils.SocialScoreUtils$a
cn.soulapp.android.component.planet.soulmatch.utils.SoulMusicHelper
cn.soulapp.android.h5.activity.H5Activity
cn.soulapp.android.lib.common.utils.settings.SoulSettings
com.chad.library.adapter.base.BaseQuickAdapter
```

---

## 六、宿主成员名候选表（反射查找用）

模块按参数类型/返回类型/static 修饰符从这些候选中筛选，**不是硬编码**：

| 用途 | 候选名 |
|---|---|
| 消息读取 | `getChatMessage`, `getMessage`, `getMsgContent`, `getMsgType`, `getMsgId`, `getFrom`, `getTo`, `getStringTransExt`, `isAnonymityChat` |
| 聊天限制 | `isLimit`, `checkLimitOpt`, `getShowPopup`, `getType`, `getItemIdentity`, `getIntExt` |
| 社交分 | `getCanNotSendBeforeReply`, `getCreateSessionLimit`, `getForbidCall`, `getForbidPic` |
| 点赞/广告 | `getAdResponse`, `hasDeclare`, `incentiveModel` |
| 广告加载 | `loadAds`, `onAdFailed` |
| 适配器数据 | `setData`, `replaceData`, `setNewData`, `setDiffNewData`, `setList`, `setNewInstance`, `addData`, `onRefreshUi` |
| 生命周期 | `onCreate`, `onNewIntent`, `onResume`, `onPrepared`, `init`, `initViews`, `onVisibilityChanged` |
| 私有设置入口 | `H5Activity.m1(boolean)` |

## 七、SharedPreferences 键（44 个）

```
ad_feed  ad_detail  ad_video  ad_tag  ad_tag_web  ad_search  ad_group  ad_planet
ad_me  ad_conversation  ad_envelope  ad_notice  ad_chat_tools  ad_float
ad_interstitial  ad_reward_chat  ad_reward_drama  ad_reward_web
privacy_allow_download  privacy_allow_screenshot  privacy_allow_repeat
feed_filter_enabled  feed_filter_keep_unknown  feed_keywords_enabled_v1
square_like_all_loaded  square_auto_like
block_splash  game_dice  game_finger  chat_anti_recall
chat_own_recall_no_time_limit  chat_remove_gift_gate  chat_remove_bell_gift_gate
post_remove_share_limit  chat_remove_social_score_limit  chat_hide_typing
match_mute_background_music  download_image_no_watermark  download_video_no_watermark
square_visible_like  page_log  startup_toast
```

带参数的键：
- `profile_disguise_days_<uid>`
- `profile_disguise_follow_<uid>`
- `profile_disguise_followed_<uid>`
- `profile_disguise_visitor_<uid>`
- `feed_keywords_v1`（`\\R` 正则分隔）
- `feed_filter_gender`（`ANY`/`MALE`/`FEMALE`）
- `chat_recall_style`
- `chat_limit_session_type`
- `ai_reply_enabled_v1` / `ai_reply_model_v1` / `ai_reply_profile_v1` / `ai_reply_auto_image_threshold_v1`

## 八、Hook ID（59 个，`HookBuilder.setId`）

```
new-soul/action-marks-chat          new-soul/action-marks-post
new-soul/ad-loader/                 new-soul/bell-gift-popup-config
new-soul/bottom-settings-entry      new-soul/chat-anti-recall
new-soul/chat-anti-recall-roam      new-soul/chat-recall-mark
new-soul/cold-launch-flag           new-soul/config-file-result
new-soul/debug-export-result        new-soul/debug-init
new-soul/feed-data/                 new-soul/feed-filter-attach
new-soul/feed-filter-bind           new-soul/feed-filter-recycle
new-soul/follow-disguise-           new-soul/follow-disguise-header
new-soul/game-message-result        new-soul/game-picker/
new-soul/gift-chat-dialog           new-soul/gift-chat-model
new-soul/gift-chat-session          new-soul/hide-typing
new-soul/hot-splash-gate            new-soul/match-music-
new-soul/message-forward-cleanup    new-soul/message-forward-complete-
new-soul/message-forward-list       new-soul/message-forward-navigation-
new-soul/message-forward-select     new-soul/message-forward-ui
new-soul/own-recall-age-read        new-soul/own-recall-entry
new-soul/post-copy-action           new-soul/post-copy-click
new-soul/post-share-permission      new-soul/privacy-photo-preview
new-soul/privacy-repeat-            new-soul/privacy-repeat-snap-preview
new-soul/profile-bookmark-click     new-soul/profile-bookmark-icon
new-soul/profile-bookmark-menu      new-soul/relaunch-flag
new-soul/repeat-click               new-soul/repeat-context
new-soul/repeat-menu                new-soul/skip-startup-ad
new-soul/social-score-              new-soul/social-score-dialog
new-soul/square-like-envelope-create  new-soul/square-like-envelope-visibility
new-soul/square-like-lifecycle      new-soul/voice-library-close
new-soul/voice-library-entry
soul-demo/activity-onResume         soul-demo/private-h5-m1
```
