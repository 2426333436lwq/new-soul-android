# New Soul 1.34.0 — LSPosed 模块逆向分析报告

**分析对象**: `new-soul-1.34.0.apk`
**SHA-256**: `6681a37eb4f7f145a4070e5a464d74f1f09f458d8f58419cfdc005c5bb316dd8`
**模块包名**: `dev.local.newsoul.public`（versionName 1.34.0 / versionCode 88）
**宿主目标**: `cn.soulapp.android`（Soul 社交 App）
**模块入口**: `dev.local.newsoul.SoulHook`（`META-INF/xposed/java_init.list`）
**分析日期**: 2026-10-13
**分析方法**: REA MCP 工具链（`open_binary` / `inspect_artifact` / `extract_artifact`）+ 自研 DEX / AXML 解析器

---

## 0. 证据等级说明

本报告所有结论按以下等级标注，请勿混用：

| 标记 | 含义 |
|---|---|
| **【观测】** | DEX 字符串表 / 方法表 / 类表 / 资源中**字面存在**的证据 |
| **【推断】** | 基于观测证据的方法体调用关系推导，未执行 |
| **【未知】** | 当前静态证据无法确定，需运行时验证 |

> **重要限制**: 本分析**未执行** APK，未连接真实设备。所有宿主方法签名、hook 行为均为静态推导。宿主 `cn.soulapp.android` 的类名全部以**字符串**形式存在于 DEX 中并经反射加载，因此宿主的实际方法签名无法从本 APK 独立验证 —— 模块自身也为此在每个 hook 点做了运行时签名校验（见 §3.6）。

---

## 1. 模块性质与架构

### 1.1 这是一个 Xposed/LSPosed 模块，不是独立 App

**【观测】** `META-INF/xposed/module.prop`:

```properties
minApiVersion=102
targetApiVersion=102
staticScope=true
exceptionMode=protective
```

**【观测】** `META-INF/xposed/scope.list` 只有一行：`cn.soulapp.android`

**【观测】** `AndroidManifest.xml` 解码结果——无任何 `<activity>`、无任何 `<uses-permission>`：

```xml
<manifest versionCode="88" versionName="1.34.0"
          compileSdkVersion="35" package="dev.local.newsoul.public">
  <uses-sdk minSdkVersion="26" targetSdkVersion="35"/>
  <application label="New Soul" icon="@0x7f020000" debuggable="false"
               allowBackup="false" supportsRtl="true"/>
</manifest>
```

**【推断】** 该 APK 没有启动图标、没有权限声明，唯一作用是被 LSPosed 在 `cn.soulapp.android` 进程内加载。`staticScope=true` 表示作用域静态锁定为 `scope.list` 中的包，用户无法在 LSPosed 管理器中自行增删。

**【观测】** `minApiVersion=102` 对应 libxposed API 102（现代 LSPosed 的稳定 API）。

### 1.2 代码规模与混淆

**【观测】** DEX 统计：

| 项目 | 数量 |
|---|---|
| 类 | 216 |
| 方法 | 1766 |
| 字段 | 1010 |
| 类型 | 563 |
| 字符串 | 3025 |
| 方法原型 | 699 |

**【观测】** 全部业务类被混淆为单/双字符短名（`a`, `b`, `c`, …, `A0`, `Z2`），但保留了少量可读标识符：
- 入口类 `SoulHook` 未混淆
- 日志 TAG `SoulHook102`
- hook id 前缀 `new-soul/`、`soul-demo/`
- 全部 SharedPreferences 开关键名
- 全部中文 UI 文案
- 全部宿主类名字符串

**【推断】** 混淆仅重命名了**类/方法/字段符号**，没有加密字符串。因此字符串表是本分析最可靠的意图来源 —— 攻击者显然认为字符串加密收益不足，或该项目已放弃进一步加固。

### 1.3 初始化流程

**【观测】** `SoulHook.onModuleLoaded` 的字面行为：读取 `getProcessName()`、`isSystemServer()`、`getFrameworkVersion()`、`getApiVersion()`，拼接日志 `"module loaded; framework=" ... "; version=" ... "; API=" ...`，并与字符串 `cn.soulapp.android` 做 `String.equals` 比较。

**【观测】** `SoulHook.onPackageReady` 调用以下安装器（观测到的 callee 列表）：

```
B2.c(module, cl)          F.h(module, cl)          Q0.A(module, cl)
Q0.B(module, cl)          Q0.C(module, cl)         Q0.D(module, cl)
Q0.E(module, cl)          Q0.F(module, cl)         Q0.K(module, cl, class)
Q0.x(module, cl)          Q0.z(module, cl)         g1.a(module, cl)
o.c(module, cl)           p0.e(module, cl)         u1.g(module, cl)
SoulHook.installSettingsHook(cl)
```

**【观测】** 该方法的字符串包含 `"Skipping duplicate module; active="`、`"active variant=dev.local.newsoul.public"`、`"newsoul.active.module"`，以及 `System.setProperty` 调用 —— 即用一个**系统属性做多模块重复加载去重**。

**【推断】** `onPackageReady` 是唯一的 hook 安装总入口；每个 `Q0.<字母>` 负责一个子系统。

---

## 2. 功能开关全集（44 项）

**【观测】** `G.<clinit>` 中两个字面字符串就是完整的功能键目录，以空格分隔：

```
ad_feed ad_detail ad_video ad_tag ad_tag_web ad_search ad_group ad_planet ad_me
ad_conversation ad_envelope ad_notice ad_chat_tools ad_float ad_interstitial
ad_reward_chat ad_reward_drama ad_reward_web privacy_allow_download
privacy_allow_screenshot privacy_allow_repeat feed_filter_enabled
feed_filter_keep_unknown feed_keywords_enabled_v1 square_like_all_loaded
square_auto_like

block_splash game_dice game_finger chat_anti_recall chat_own_recall_no_time_limit
chat_remove_gift_gate chat_remove_bell_gift_gate post_remove_share_limit
chat_remove_social_score_limit chat_hide_typing match_mute_background_music
download_image_no_watermark download_video_no_watermark square_visible_like
page_log startup_toast
```

**【推断】** `G` 类整体是**配置导入/导出**模块：`G.a(Map) -> String` 序列化为 JSON（含 `"format":"newsoul-config"`、`"schemaVersion"`、`"settings"`），`G.c(String) -> Map` 反序列化并校验，`G.d` 做逐键类型/取值校验（含 `"ANY"/"MALE"/"FEMALE"` 性别枚举、正则 `"\\R"` 分隔屏蔽词、`ai_reply_auto_image_threshold_v1` 数值范围）。

### 2.1 开关读取点（观测到的字符串 → 方法归属）

| 开关 | 读取方法 |
|---|---|
| `ad_feed` … `ad_tag_web`（12 项） | `SoulHook.onPackageReady`, `Z0.g`, `j.<clinit>` |
| `ad_float`, `ad_interstitial` | `j.h`（`new-soul/ad-loader/`） |
| `ad_reward_web` | `a.intercept` |
| `block_splash` | `D.intercept`, `L1.getAsBoolean`, `Q0.n` |
| `chat_anti_recall` | `a.intercept` |
| `chat_limit_session_type` | `a.intercept` |
| `chat_remove_social_score_limit` | `a.intercept` |
| `chat_remove_bell_gift_gate` | `Q0.e`, `a.intercept` |
| `chat_remove_gift_gate` | `Q0.e`, `Z0.g` |
| `chat_own_recall_no_time_limit` | `f1.intercept`, `Z0.g` |
| `chat_recall_style` | `B.intercept`, `G.d`, `H.run`, `R1.accept` |
| `chat_hide_typing` | `Z0.g`（hook id `new-soul/hide-typing`） |
| `game_finger` | `p.intercept`, `Z0.g` |
| `game_dice` | `Z0.g` |
| `match_mute_background_music` | `a.intercept` |
| `privacy_allow_download` | `a.intercept` |
| `privacy_allow_repeat` | `Q0.R`, `a.intercept` |
| `privacy_allow_screenshot` | `a.intercept` |
| `download_image_no_watermark` | `Q0.A` |
| `download_video_no_watermark` | `Q0.A` |
| `feed_filter_enabled` | `B2.a`, `w2.d` |
| `feed_filter_gender` | `B2.a`, `G.d`, `P1.accept`, `m1.run` |
| `feed_filter_keep_unknown` | `B2.a`, `w2.d` |
| `feed_keywords_enabled_v1` | `w2.d` |
| `feed_keywords_v1` | `B2.a`, `G.d`, `W1.onClick`, `g.run` |
| `square_auto_like` | `I2.b`, `I2.d`, `K.run` |
| `square_visible_like` | `I2.g` |
| `square_like_all_loaded` | `I2.a`, `w2.d` |
| `post_remove_share_limit` | `Z0.g` |
| `profile_disguise_*` | `l0.intercept`, `m0.intercept`, `n0.intercept`, `p0.b`, `z0.a` |
| `startup_toast` | `SoulHook.lambda$onPackageReady$0` |

**【观测】** `Z0.g(int)` 是**设置界面总开关板**，其字符串表含 118 条，覆盖上述绝大部分键名加中文标签。

---

## 3. Hook 安装机制

### 3.1 安装器总览

**【观测】** 调用 `XposedModule->hook(...)` 的方法共 **29 个**：

```
SoulHook.onPackageReady          SoulHook.installSettingsHook
Q0.A  Q0.B  Q0.C  Q0.D  Q0.E  Q0.F          （子系统安装）
Q0.G  Q0.H  Q0.I  Q0.J  Q0.K                （通用安装原语）
Q0.x  Q0.z
j.f   j.g   j.h                             （通用反射 hook 原语）
p0.e  p0.f  p0.g                            （宿主资料页反射原语）
u1.g   B2.c   F.h   g1.a   o.c   d1.e
S.a   g0.a   t.a
```

**【观测】** 每次安装都使用 `HookBuilder.setId(String)` 设置稳定 ID，并 `setExceptionMode(...)`。这解释了 59 个 `new-soul/*` ID 的存在。

### 3.2 Hook ID 清单（59 项，观测）

```
new-soul/action-marks-chat              new-soul/action-marks-post
new-soul/ad-loader/                     new-soul/bell-gift-popup-config
new-soul/bottom-settings-entry          new-soul/chat-anti-recall
new-soul/chat-anti-recall-roam          new-soul/chat-recall-mark
new-soul/cold-launch-flag               new-soul/config-file-result
new-soul/debug-export-result            new-soul/debug-init
new-soul/feed-data/                     new-soul/feed-filter-attach
new-soul/feed-filter-bind               new-soul/feed-filter-recycle
new-soul/follow-disguise-               new-soul/follow-disguise-header
new-soul/game-message-result            new-soul/game-picker/
new-soul/gift-chat-dialog               new-soul/gift-chat-model
new-soul/gift-chat-session              new-soul/hide-typing
new-soul/hot-splash-gate                new-soul/match-music-
new-soul/message-forward-cleanup        new-soul/message-forward-complete-
new-soul/message-forward-list           new-soul/message-forward-navigation-
new-soul/message-forward-select         new-soul/message-forward-ui
new-soul/own-recall-age-read            new-soul/own-recall-entry
new-soul/post-copy-action               new-soul/post-copy-click
new-soul/post-share-permission          new-soul/privacy-photo-preview
new-soul/privacy-repeat-                new-soul/privacy-repeat-snap-preview
new-soul/profile-bookmark-click         new-soul/profile-bookmark-icon
new-soul/profile-bookmark-menu          new-soul/relaunch-flag
new-soul/repeat-click                   new-soul/repeat-context
new-soul/repeat-menu                    new-soul/skip-startup-ad
new-soul/social-score-                  new-soul/social-score-dialog
new-soul/square-like-envelope-create    new-soul/square-like-envelope-visibility
new-soul/square-like-lifecycle          new-soul/voice-library-close
new-soul/voice-library-entry
soul-demo/activity-onResume             soul-demo/private-h5-m1
```

### 3.3 宿主目标类（96 个，全部为字符串，反射加载）

**【观测】** 完整列表：

**广告 (ad)**：`ad.base.view.{AdFloatViewProvider, DraggableFloatAdView, FloatAdView, FloatAdViewV2, FloatEntranceView, SearchBannerAdView, e}`、`ad.dialog.{InterstitialAdBottomDialog, InterstitialAdDialog}`、`ad.soulad.ad.views.express.render.{EnvelopeMessageFeedExpressView, FeedDrawExpressView, FeedWebExpressView, GroupBannerExpressView, MeCellExpressView, PlantGameExpressView, PlantGameTagExpressView, PostDetailExpressView, PostFeedExpressComponent, SystemNoticeFeedExpressView, TagBannerExpressView}`、`ad.core.services.plaforms.listener.SoulAdRequestListener`、`ad.h5.moduel.*`

**聊天/IM**：`imlib.{ChatManager, Conversation, i0, j0}`、`imlib.handler.RoamHandler`、`imlib.listener.MsgListener`、`imlib.msg.{ImMessage}`、`imlib.msg.chat.{ChatMessage, DiceFingerMsg, TextMsg, AudioMsg, ImgMsg, ImgMsgs}`、`component.chat.{SelectConversationActivity}`、`component.chat.adapter.ConversationAdapter`、`component.chat.bean.SocialGraceScoreBean`、`component.chat.dialog.{LimitGiftDialog$OnStartGiftSendListener, j1}`、`component.chat.fragment.{ConversationFragment, ConversationFragment$d1}`、`component.chat.helper.{MsgLimitManager, WithDrawHandler}`、`component.chat.limitdialog.RewardAdHelper`、`component.chat.snapchat.{PrivacyProtectPreviewActivity, SnapChatActivity}`、`component.chat.style.BaseConversationStyleProcess`、`component.chat.utils.{ChatUtils, u0, y4}`、`component.chat.widget.AbsChatDualItem`、`component.chat.window.{ChatLongClickPopUp, ChatLongClickPopUp$a}`、`chat.bean.UserConversation`、`chat.listener.OnClickConfirmListener`、`chat.utils.f`、`lib_input.view.IMediaKeyBoard`、`lib.common.bean.ChatLimitModel`

**主页/资料**：`client.component.middle.platform.utils.m2`、`client.component.middle.platform.model.api.user.ImUserBean`、`component.home.api.user.user.bean.UserFollowCount`、`component.home.databinding.CUsrMetaFollowFansContainerBinding`、`component.home.me.UserHomeHeaderHelper`、`component.home.meta.view.MetaFollowFansContainerView`、`component.home.revision.{RevisionProfileProvider, RevisionProfileProvider$ViewHolder}`、`component.home.user.UserHomeActivity`、`component.home.lovebell.{LoveBellProGiftDialog, bean.LoveBellProConfig}`

**广场/帖子**：`component.square.main.squarepost.{SquareAdapter, viewholder.BaseSquareViewHolder}`、`component.square.main.ad.{AdExpressViewHolder, AdPostViewHolder}`、`component.square.video.adapter.a`、`component.square.video.drama.o0`、`component.square.widget.SquareFloatingButton`、`square.{BaseSeedsDialogFragment, SeedsShareDialogFragment, a0}`、`square.adapter.{TypeAdapter, TypeAdapter$a}`、`square.bean.Tag`、`square.post.bean.Post`、`component.square.post.b1`、`square.utils.{ImageDownloader, ImageMarkProcessor, b, h2}`

**启动/其他**：`component.startup.main.{MainActivity, MainScheduler}`、`component.planet.soulmatch.utils.{SocialScoreUtils, SocialScoreUtils$a, SoulMusicHelper}`、`h5.activity.H5Activity`、`lib.common.utils.settings.SoulSettings`

### 3.4 反射辅助层

**【观测】** `Q0.Q(...)` 逐个 `loadClass` + `getDeclaredMethod`/`getMethod` 探测签名，把结果加入 `ArrayList`，并构造中文诊断串 `"签名存在 ·"`、`"：Hook "`。

**【观测】** `Q0.W(Activity, W0, Object, H2)` 使用 `java.lang.reflect.Proxy.newProxyInstance` + `InvocationHandler`（`b1`/`V0` 实现）动态代理宿主接口。

**【推断】** 这是**版本自适应**设计：模块不硬编码宿主方法签名，而是运行时遍历候选方法名列表（如 `Q0.z` 里的 `getShowPopup`、`isLimit`、`getType`、`getItemIdentity`、`getIntExt`、`checkLimitOpt`），按参数类型/返回类型/static 修饰符筛选，命中后才挂 hook。宿主升级导致签名变化时，模块会**优雅降级**并打日志，而不是崩溃。

### 3.5 版本兼容与自更新

**【观测】** 字符串：
- `"new soul: hot splash hook installed (Soul 6.37.0)"` / `"new soul: splash hook installed (Soul 6.37.0)"`
- `"Mozilla/5.0 NewSoul-UpdateCheck"`（`S0.a`）
- `"https://wwbnj.lanzoul.com/b007utki2d"`（`S0.a`, `T0.b`, `g.run`）—— 蓝奏云更新分发
- `"https://t.me/+3Hl2lJ1X-oZlZTZl"`（`A0.onClick`）—— Telegram 群组
- `"https://app.soulapp.cn"` + JS 片段（`E1.run`）—— 检测 H5 设置页
- `"donation_alipay"` / `"donation_wechat"`（`h0.run`）

**【推断】** 模块针对 **Soul 6.37.0** 适配。自更新走蓝奏云，社区走 Telegram，并有内置打赏入口（资源文件 `res/drawable-nodpi-v4/donation_alipay.jpg`、`donation_wechat.png`，与清单一致）。

**【观测】** `Q0.V` 通过 `createPackageContext("dev.local.newsoul.public", ...)` + `getResources().getIdentifier(..., "drawable", ...)` 从**模块自身 APK** 加载收款码资源 —— 这是 LSPosed 模块访问自身资源的常见手法。

### 3.6 自我保护与失败处理

**【观测】** 多个安装器含明确的失败串与异常类型：
- `module.prop` 的 `exceptionMode=protective` —— hook 抛异常时保护宿主
- `"Soul settings hook unavailable; check app version"`
- `"Unexpected H5Activity.m1 signature"`、`"Unexpected Soul hot startup signature"`、`"Unexpected Soul startup method signature"`、`"Unexpected social score dialog signature"`
- 大量 `IllegalStateException` / `NoSuchMethodException` 构造调用
- `XposedModule.deoptimize(Executable)` 调用 —— 强制去优化目标方法以允许 hook

**【推断】** 模块对关键 hook 做**严格签名断言**：签名不符就抛 `IllegalStateException` 或降级，而不是静默 hook 错误的方法。这是一个成熟工程的做法，也说明作者预期宿主会频繁变化。

**【观测】** 设置入口通过 hook 宿主**私有方法** `cn.soulapp.android.h5.activity.H5Activity.m1(boolean)` 注入：
- `installSettingsHook` 字符串含 `"m1"`、`"private hook installed: H5Activity.m1(boolean)"`、`"soul-demo/private-h5-m1"`
- `I1.run` 字符串含 `"settings entry inserted; trigger=private H5Activity.m1"`、`"dev.local.newsoul.settings.entry.v2"`、`"Settings entry failed"`
- 代码显式检查 `Modifier.isPrivate(method.getModifiers())`

**【推断】** 选择 hook 一个**私有** H5 方法作为触发器很巧妙：该方法是宿主自身 WebView 设置页的入口，保证只在用户打开 Soul 设置页时才注入模块入口按钮，避免常驻 UI 被检测。

---

## 4. 功能子系统

### 4.1 广告屏蔽（`Q0.A`/`j`/`a`/`SoulHook.onPackageReady`）

**【观测】** 18 个 `ad_*` 开关；被 hook 的广告视图类见 §3.3。

**【观测】** `j.h` 处理 `SoulAdRequestListener`，字符串含 `"loadAds"`、`"onAdFailed"`、`"new-soul/ad-loader/"`，并把 `ad_float`/`ad_interstitial` 作为键。

**【观测】** `SoulHook.onPackageReady` 字符串含 `"setVisibility"`、`"not a View"`、`"view visibility"`、`"no adapter mutation methods"`、`"setData"`、`"replaceData"`、`"setNewData"`、`"setDiffNewData"`、`"setList"`、`"setNewInstance"`、`"addData"`、`"isAnnotated"`、`"Adapter mutation"`。

**【推断】** 三重屏蔽策略：
1. **视图层**：hook 广告 View 类并强制 `setVisibility(GONE)`（含 `"not a View"` 的守卫说明它检查了实例类型）；
2. **适配器层**：hook 列表 Adapter 的数据变更方法（`setData`/`replaceData`/`setDiffNewData` 等一组候选名），从数据源移除广告条目 —— 这解释了 `"no adapter mutation methods"` 的探测失败日志；
3. **请求层**：拦截 `SoulAdRequestListener.loadAds` / `onAdFailed`，让广告请求失败。

**【推断】** `j.h` 使用 `getDeclaredConstructors()` 遍历构造器 + `getDeclaredMethod`，说明它对广告 loader 的构造与加载方法都做了 hook，形成双保险。

### 4.2 聊天反撤回（`Q0.K`/`F.h`/`g1.a`）

**【观测】** 开关 `chat_anti_recall`、`chat_own_recall_no_time_limit`、`chat_recall_style`。
**【观测】** Hook ID: `new-soul/chat-anti-recall`、`new-soul/chat-anti-recall-roam`、`new-soul/chat-recall-mark`、`new-soul/own-recall-entry`、`new-soul/own-recall-age-read`。
**【观测】** 宿主类含 `MsgListener`、`RoamHandler`（漫游）、`ChatManager`、`ChatMessage`、`ImMessage`、`WithDrawHandler`。
**【观测】** 方法名候选（`F.h` 字符串）：`getMsgContent`、`getMsgType`、`getFrom`、`getTo`、`getMsgId`、`getChatMessage`、`getMessage`、`getStringTransExt`、`isAnonymityChat`。
**【观测】** `g1.a` 单独 hook `WithDrawHandler` + `imlib.j0` + `ImMessage`。
**【观测】** UI 文案：`"仅保留开启后收到的普通私聊消息，自己的撤回不受影响。"`、`"仅移除自己消息的本地"`、`"消息"`、`"撤回"`。

**【推断】** 两个反撤回 ID 的区别（`chat-anti-recall` vs `-roam`）：前者针对**在线实时消息**（`MsgListener` 路径），后者针对**历史漫游消息**（`RoamHandler` 路径）。`WithDrawHandler` 是宿主的撤回处理器，hook 它可阻止撤回动作生效。中文文案表明：开启后**仅保留收到的**普通私聊消息，用户自己发出的消息撤回仍正常 —— 即单向反撤回。

### 4.3 聊天限制绕过（`Q0.z`）

**【观测】** 开关 `chat_limit_session_type`、`chat_remove_gift_gate`、`chat_remove_bell_gift_gate`、`chat_remove_social_score_limit`。
**【观测】** Hook ID: `new-soul/gift-chat-model`、`new-soul/gift-chat-dialog`、`new-soul/gift-chat-session`、`new-soul/bell-gift-popup-config`、`new-soul/social-score-`、`new-soul/social-score-dialog`。
**【观测】** 宿主方法与类：`ChatLimitModel`、`MsgLimitManager`、`LimitGiftDialog$OnStartGiftSendListener`、`LoveBellProGiftDialog`、`LoveBellProConfig`、`BaseConversationStyleProcess`、`ChatUtils`、`Conversation`。
**【观测】** 方法名候选：`isLimit`、`getShowPopup`、`getType`、`getItemIdentity`、`getIntExt`、`checkLimitOpt`。
**【观测】** `Q0.E` 针对社交分：`SocialGraceScoreBean`、`SocialScoreUtils`、`SocialScoreUtils$a`，方法候选 `getCanNotSendBeforeReply`、`getCreateSessionLimit`、`getForbidCall`、`getForbidPic`，并有若干私有类 `uj.c0`、`uj.c0$q`。

**【推断】**
- `chat_remove_gift_gate`：hook `MsgLimitManager.isLimit` / `ChatLimitModel`，令 `isLimit` 返回 false，绕过送礼/会话限制；
- `chat_remove_bell_gift_gate`：hook `LoveBellProGiftDialog` + `LoveBellProConfig.getShowPopup`，强制弹窗显示；
- `chat_remove_social_score_limit`：hook `SocialScoreUtils` 的 `getCanNotSendBeforeReply` / `getCreateSessionLimit` / `getForbidCall` / `getForbidPic`，解除社交分对发言、通话、图片、建会话的封锁。

### 4.4 骰子/猜拳（`game_dice`、`game_finger`、`p.intercept`）

**【观测】** 宿主类 `imlib.msg.chat.DiceFingerMsg`；Hook ID `new-soul/game-message-result`、`new-soul/game-picker/`；UI 文案 `"骰子与猜拳\n私聊发"`、`"finger_cloth"`、`"finger_scissor"`、`"finger_stone"`。
**【观测】** `p.intercept` 的 callee 含 `Activity.isDestroyed`、`getSharedPreferences`、`getBoolean`、`Class.getMethod`、`ThreadLocal.get`、`Method.invoke`。

**【推断】** `p.intercept` 在消息发送前根据开关与用户选择的点数/手势（石头/剪刀/布）改写 `DiceFingerMsg` 的内容字段，实现指定结果。`ThreadLocal` 用于在调用链上传递"本次应出什么"的上下文，避免并发串扰。

### 4.5 资料页伪装（`profile_disguise_*`）

**【观测】** 键：`profile_disguise_days_`、`profile_disguise_follow_`、`profile_disguise_followed_`、`profile_disguise_visitor_`。
**【观测】** 宿主类：`component.home.me.UserHomeHeaderHelper`、`component.home.meta.view.MetaFollowFansContainerView`、`component.home.api.user.user.bean.UserFollowCount`、`component.home.revision.RevisionProfileProvider`(+`$ViewHolder`)、`component.home.databinding.CUsrMetaFollowFansContainerBinding`、`client.component.middle.platform.utils.m2`。
**【观测】** Hook ID: `new-soul/follow-disguise-`、`new-soul/follow-disguise-header`、`new-soul/profile-bookmark-menu`、`new-soul/profile-bookmark-icon`、`new-soul/profile-bookmark-click`。
**【观测】** DEX 类 `l0` 持有 **7 个 `java.lang.reflect.Field`**（对应关注/粉丝/访客/天数等计数字段），`m0` 持有 1 个 `Field`，`n0` 持有 1 个 `Field`，均实现 `XposedInterface$Hooker`。
**【观测】** `p0` 提供 `a(TextView, Object, boolean, String, Field, Method)` 与 `b(...)`，将 hook 到的整数值写入 TextView 文本。
**【观测】** UI 文案：`"只改变本机自己个人主页的关注、被关注、访客数量及加入天数，"`、`"查看时不扣减本"`、`"在用户主页打开右上角菜单，点击「插眼」。"`、`"昵"`（昵称）。

**【推断】** `l0`/`m0`/`n0` 分别 hook 不同宿主显示路径（Revision 新版资料页 / MetaFollowFansContainerView / UserHomeHeaderHelper），把 `UserFollowCount` 的计数字段替换为本地设定值。`p0.b` 处理"访客数量"路径。中文文案确认：**仅本机自己主页显示**，不修改服务器数据，且"插眼"（关注/访客簿）查看时不扣减次数。

### 4.6 隐私与截图（`Q0.D`、`a.intercept`）

**【观测】** 开关 `privacy_allow_screenshot`、`privacy_allow_repeat`、`privacy_allow_download`。
**【观测】** `Q0.D` hook `component.chat.snapchat.PrivacyProtectPreviewActivity` 与 `SnapChatActivity` 的 `init()`，ID `new-soul/privacy-photo-preview`，日志 `"privacy photo controls installed"`。
**【观测】** 另有 ID `new-soul/privacy-repeat-`、`new-soul/privacy-repeat-snap-preview`。
**【观测】** `Q0.R(Activity)` 返回 boolean，被 `f.intercept` 与 `privacy_allow_repeat` 关联。
**【观测】** UI 文案：`"仅适用于收到的隐私图片和视频；开"`、`"隐私照"`、`"查看时不扣减本"`、`"保留性"`、`"包含屏"`。

**【推断】**
- `privacy_allow_screenshot`：移除窗口的 `FLAG_SECURE`（隐私图/闪照页），允许截屏；
- `privacy_allow_repeat`：闪照（SnapChat）本应"阅后即焚、仅看一次"，此处让重复查看可行且不扣减剩余次数（`Q0.R` 判定是否允许重复）；
- `privacy_allow_download`：允许保存收到的隐私图/视频。

### 4.7 无水印下载（`Q0.A`）

**【观测】** `Q0.A` hook：`cn.soulapp.android.square.utils.ImageDownloader`、`ImageMarkProcessor`、`cn.soulapp.android.square.utils.b`（方法 `b`）、`h2`（方法 `f`）；ID `"lf.b"`；日志 `"image watermark control unavailable"`、`"video watermark control unavailable"`；键 `download_image_no_watermark`、`download_video_no_watermark`。
**【观测】** UI 文案：`"无水印下载：使用 Soul 原有保存按钮，仍存入原相册目录。原文件自"`、`"原图"`、`"去除"`。

**【推断】** 图片路径 hook `ImageMarkProcessor`（水印合成器）使其直接返回原图；视频路径 hook `square.utils.b`/`h2` 的 `f` 方法。特点：**复用宿主原有保存按钮和相册目录**，不改变用户操作习惯。

### 4.8 广场点赞（`I2`、`Q0.F`）

**【观测】** 开关 `square_auto_like`、`square_visible_like`、`square_like_all_loaded`。
**【观测】** `I2` 方法：`a()`、`b()`、`d(Object)`、`f()`、`g()`、`k()`；`I2` 引用 `A0`、`H2`、`M1`、`Q0`、`n`、`p2`。
**【观测】** `Q0.F` hook `component.square.widget.SquareFloatingButton`（`onCreate`、`onVisibilityChanged`）、`component.startup.main.MainActivity`、`square.post.bean.Post`（`getAdResponse`、`hasDeclare`、`incentiveModel`、`id`）；ID `new-soul/square-like-lifecycle`、`new-soul/square-like-envelope-create`、`new-soul/square-like-envelope-visibility`。
**【观测】** UI 文案：`"一键点赞与自动点赞分别保存开关。自动点赞开启且功能就绪时，一键点赞配置暂不生效；一键点赞开关仍可修改，原范围设置保留。\n\n一键点赞：单击广场爱心开始，再次单"`、`"性别筛选与屏蔽词过滤独立开启，隐藏的帖子不参与点赞"`、`"条点赞，"`、`"一键点赞可用"`。

**【推断】** 三种模式：
- `square_visible_like`：对当前可见卡片点赞；
- `square_like_all_loaded`：对已加载的全部卡片点赞；
- `square_auto_like`：自动点赞（滚动/加载时自动触发）。
  
文案明确二者**互斥优先级**：自动点赞开启且就绪时，一键点赞配置暂不生效。另确认**与内容过滤联动**：被性别筛选或屏蔽词隐藏的帖子不参与点赞。

### 4.9 信息流过滤（`B2`、`w2`）

**【观测】** 开关 `feed_filter_enabled`、`feed_filter_gender`（`ANY`/`MALE`/`FEMALE`）、`feed_filter_keep_unknown`、`feed_keywords_enabled_v1`、`feed_keywords_v1`。
**【观测】** `B2` 方法 `a`、`b`、`c`、`d`、`e`；`B2.c` 引用 `SquareAdapter`、`BaseSquareViewHolder`、`square.bean.Tag`、`square.post.bean.Post`。
**【观测】** 宿主相关：`component.square.main.squarepost.SquareAdapter`、`component.square.main.squarepost.viewholder.BaseSquareViewHolder`、`component.square.main.ad.{AdExpressViewHolder, AdPostViewHolder}`、`component.square.video.adapter.a`、`com.chad.library.adapter.base.BaseQuickAdapter`、`square.adapter.TypeAdapter`。
**【观测】** Hook ID: `new-soul/feed-filter-attach`、`new-soul/feed-filter-bind`、`new-soul/feed-filter-recycle`、`new-soul/feed-data/`。
**【观测】** 适配器方法候选：`setData`、`replaceData`、`setDiffNewData`、`setList`、`setNewData`、`setNewInstance`、`addData`、`onRefreshUi`。

**【推断】** 在 RecyclerView 的 attach / bind / recycle 三个生命周期点分别挂钩，对被过滤的条目直接隐藏或回收；同时 hook `BaseQuickAdapter` 系列数据方法以从源头剔除条目。`feed_filter_keep_unknown` 决定性别未知的帖子是否保留 —— 这是避免误杀的保守选项。

### 4.10 帖子分享/复制限制（`Q0.B`、`Q0.C`）

**【观测】** `Q0.B` hook `square.BaseSeedsDialogFragment`、`square.SeedsShareDialogFragment`、`square.post.bean.Post`，字段 `content`，方法 `dismiss`、`initViews`；ID `new-soul/post-copy-action`、`new-soul/post-copy-click`；日志 `"post copy hooks installed"`。
**【观测】** 另有 ID `new-soul/post-share-permission`、`new-soul/action-marks-post`。
**【观测】** `Q0.C` 同样 hook `SeedsShareDialogFragment` + `Post`。
**【观测】** `Q0.B` 使用 `Hooker` 类 `b`（4 个 Field + 1 Method）与 `f1`（3 个 AccessibleObject + 1 Executable）。

**【推断】** `post_remove_share_limit` 解除帖子分享/复制的限制（如禁止保存、禁止转发到站外）。Seeds 相关 Fragment 是分享面板。

### 4.11 启动广告与冷启动（`o.c`、`D.intercept`、`Q0.F`）

**【观测】** 开关 `block_splash`、`startup_toast`、`page_log`。
**【观测】** Hook ID: `new-soul/skip-startup-ad`、`new-soul/cold-launch-flag`、`new-soul/hot-splash-gate`、`new-soul/relaunch-flag`。
**【观测】** `o.c` hook `component.startup.main.MainActivity` 与 `lib.common.utils.settings.SoulSettings`，并引用 `main_bottom_tab_bar`/`main_tab_*` 等资源名（`o.<clinit>`）。
**【观测】** `onPackageReady` 字符串含 `"onResume"`、`"onCreate"`、`"onNewIntent"`、`"new soul: splash hook installed (Soul 6.37.0)"`、`"new soul: hot splash hook installed (Soul 6.37.0)"`、`"new soul: startup intent hooks installed"`、`"/18 (excluding splash)"`。
**【观测】** DEX 资源 `res/mipmap-anydpi-v26/ic_launcher.xml`、`resources.arsc` 极小（1244 字节），说明模块自身资源极少。

**【推断】** 冷启动与热启动（`relaunch`）走不同路径：分别对 `MainActivity.onCreate` 与 `onNewIntent` 挂钩，判定本次是否为启动广告页面，是则跳过。`startup_toast` 是模块自己显示启动提示。

### 4.12 其他功能

**【观测】** 其余 Hook ID 揭示的功能：
- **语音库**：`new-soul/voice-library-entry`、`new-soul/voice-library-close`；文案 `"保存语音\n长按聊天中的语音消息，选择「存入语音库」。\n\n打开语音库\n进入普通私聊，"`；音频扩展名 `.mp3 .wav .m4a .amr .aac`；`x1` 类做文件读写。
- **消息转发**：`new-soul/message-forward-{list,select,navigation-,ui,cleanup,complete-}`；token `dev.local.newsoul.message_forward_token`；`a1.intercept` 读取 `Intent.getStringExtra` 并由 `d1` 弹出选择对话框。
- **聊天长按菜单/复读**：`new-soul/repeat-menu`、`new-soul/repeat-click`、`new-soul/repeat-context`；日志键 `repeat.menu`、`repeat.menu.enter`、`repeat.menu.forward`、`repeat.menu.original`、`repeat.menu.return`、`repeat.menu.resources`、`repeat.menu.skipped`、`repeat.menu.host_failed`。
- **打字状态隐藏**：`new-soul/hide-typing`（`Q0.x`，hook `ChatManager`/`ChatMessage.getMsgType`）。
- **匹配背景音乐**：`new-soul/match-music-`（`Q0.x`，hook `SoulMusicHelper` 的 `onPrepared`）。
- **书签/插眼**：`newsoul_bookmark`、`new-soul/profile-bookmark-*`。
- **AI 回复**：键 `ai_reply_enabled_v1`、`ai_reply_model_v1`、`ai_reply_profile_v1`、`ai_reply_auto_image_threshold_v1`（`G.d` 校验阈值）。
- **聊天设置样式**：`chat_recall_style`（由 `B.intercept` 通过 `m1`/`w1` 渲染撤回提示气泡样式）。
- **加密诊断日志导出**（`page_log` / `newsoul_debug`）：见 §6.3–6.4。
- **Hook 健康度界面**（`Q0.U`）：把 §3.3 全部宿主类名与方法签名整理为中文标签清单（`"Hook 已注"`、`"Hook 未"`、`"签名匹配"`、`"：Hook 未就绪 · 请查看 S"`），展示每个 hook 是否装好、签名是否匹配；并用 `"hook_diagnostics_last_host_version"` / `"hook_diagnostics_last_shown_version"` 记录宿主版本变化。

---

## 5. 设置界面

**【观测】** `Z0.g(int)` 构建设置页；`q2` 是对话框/设置面板容器（21 个字段，含 `AlertDialog$Builder`、`LinearLayout`、`IdentityHashMap`、`BooleanSupplier`）；`w2` 提供全部 UI 构件工厂方法（`c`/`d`/`e`/`f`/`j`/`k`/`l`/`m`/`n`/`o`/`r`/`t`/`u`/`v`/`w`/`x`）；`r2` 是自定义 `Switch`；`v2`/`t2`/`u2` 是顶部标签页。
**【观测】** UI 文案：`"导出插件设置，不包含密钥、聊天"`、`"导入失败：文件无"`、`"导出失败，"`、`"配置格"`、`"勾选表示屏蔽对应广告。点击分组旁的更多按钮或长按分"`、`"加入 TG 群组，"`。
**【观测】** `E1.run` 包含 JS 探针：
```js
(()=>{if(location.origin!=='https://app.soulapp.cn'||location.pathname!='/setting/'||!document.body)return false;const want='...
```
**【推断】** 该 JS 在宿主 H5 设置页执行，用于确认页面就绪后再注入模块入口（`"dev.local.newsoul.settings.entry.v2"`）。`Z1` 类（`a`–`g` 方法）负责在宿主 View 树中按 id 查找锚点并以模糊匹配定位插入位置。

**【观测】** 配置导入/导出明确**排除密钥与聊天记录**（文案 `"导出插件设置，不包含密钥、聊天"`）。

---

## 6. 安全性评估

### 6.1 该模块做了什么

| 类别 | 行为 | 证据 |
|---|---|---|
| 广告拦截 | hook 广告视图/适配器/请求监听器 | §4.1 |
| 内容解锁 | 撤回、隐私照、礼物、社交分、分享限制 | §4.2–4.6, 4.10 |
| 数据导出 | 无水印保存、语音库 | §4.7, 4.12 |
| 自动化 | 自动点赞、AI 回复 | §4.8, 4.12 |
| 本地伪造 | 资料页计数伪装 | §4.5 |
| 隐私增强 | 隐藏打字状态、屏蔽广告 | §4.3, 4.9 |

### 6.3 加密与网络行为（重要修正）

**【观测】** 模块**确实包含**完整的 JCA 加密实现（初次扫描曾遗漏，经方法内 cross-reference 复核后确认）：

| 方法 | 内容 |
|---|---|
| `Q0.M() -> java.security.PublicKey` | 硬编码 Base64 RSA 公钥，`X509EncodedKeySpec` + `KeyFactory("RSA").generatePublic` |
| `W.a(byte[], PublicKey) -> byte[]` | **混合加密**：随机 `SecretKeySpec("AES")` + `GCMParameterSpec` 加密载荷，用 `RSA/ECB/OAEPWithSHA-256AndMGF1Padding` 包裹 AES 密钥，`updateAAD` 绑定关联数据；`DataOutputStream.writeInt` 写长度前缀；`SecureRandom` 生成 IV/密钥；`Arrays.fill` 归零密钥缓冲区 |
| `Q0.L(Object) -> String` | `MessageDigest("SHA-256")` + `%02x` 十六进制编码（对象指纹） |

**【观测】** 硬编码公钥完整值（`Q0.M`，Base64，2048-bit RSA）：
```
MIIBojANBgkqhkiG9w0BAQEFAAOCAY8AMIIBigKCAYEAqrDmgObzyP43bK71v6HgV0OBDB6+HIfkXftRMhNOIwfWbfaNZRR0/HCNnI2QSn+bFWyGHUdu7fh67Cy9n0hCnhDQh36aa3nq7+jogQqSEquEqJHqyFIfxi+4/HRJXyWAhs39M6sKyS4fJGK2xKP6gjN3oDzsunro5+ldPx9hfy/J38zm5u7qIhx5s37KKn9JoE58czyAoGp6a476JcrqUf1z5F+dhRiFa/Q8Cs/Sa1U26OxSZ+EOSauQj19rZBaXPtHh7AAXyakRJfznNPHbaH9OWg5loOB0nWHLyb/RYhlmdx/WTb+n2MiYOI2EkY4g2NanTLW4/Hydi6tM4gVTzs1xfqjdhHaI52iOXEvLdEnnOlk06ha0hFbfULxfDS4usDDs3J5G70kte+qrMmeqrcEmygaZnIUqKbVW6Qx6WIP8yVdNY3sBh1PwHy3LnXdsCFQbdhFocSSjZqvx5AUVi0Z74E7WAQsvQioFR3oyuGjJdxaXF3PX5bE0gVX80wzXAgMBAAE=
```
**【推断】** 这是标准的**信封加密**结构。因为只有公钥而无私钥，本 APK **只能加密、不能解密**。

### 6.4 加密数据用于何处

**【观测】** 调用方 cross-reference（仅 4 个调用点）：

| 调用方 | 调用的加密原语 | 相关字符串 |
|---|---|---|
| `B.intercept` | `Q0.L`（SHA-256） | `"recall message style unavailable"`、`"post action mark unavailable"` |
| `Q0.S` | `Q0.L` | `"recall retained; source=private; type="`、`"isSnap"` |
| `V.e()` | `W.a`（混合加密） | `"Size limit"`、`".chunk"`、`"No records"` |
| `T.run` / `U.run` / `h.run` | `Q0.M`（取公钥） | `"newsoul-debug"`、`"记录已满"`、`"new.soul.privacy.photo.download"`、`"process_resume"` |

**【观测】** `V.e()` 的方法体：`ZipOutputStream` + `ZipEntry` 打包，按 `File.length()`/`getName()` 排序，`InputStream.read` 读取，`File.listFiles()` 遍历，最后 `W.a` 加密，超限抛 `IOException("Size limit")`，分片写 `.chunk`。
**【观测】** `V.h(Context)` 在 `getSharedPreferences("newsoul_debug")` 上下文中 `scheduleAtFixedRate` 周期执行；`V.i(String, Throwable)` 记录异常并调 `V.c` 格式化堆栈；`V.k(String)` 构建设备/框架指纹串 `"format=1; host=" ... "; framework=" ... "; library=" ... "; module=" ... "; sdk=" ... "; android=" ...`。
**【观测】** `page_log` 开关由 `SoulHook.lambda$onPackageReady$0` 与 `Z0.g` 读取。
**【观测】** `V.m(String)` 通过 `PackageManager.getPackageInfo` 解析宿主版本号。

**【推断】** 这是 `page_log` / `newsoul_debug` 的**加密诊断日志导出**功能：
- `V` 是日志记录器（LinkedHashMap + UUID + 单线程 ScheduledExecutor）；
- `V.e()` 把诊断记录打成 ZIP，再用 `W.a`（AES-GCM 载荷 + RSA-OAEP 包密钥）加密，分片写 `.chunk`；
- 明文日志只落本地，**导出的文件是加密的**，只有作者手持私钥能解密；
- 目的是让用户能提交崩溃/hook 失败诊断，同时**不泄露用户隐私数据**（聊天内容、账号信息等）。

**【推断】** 这解释了 `G.a` 导入导出界面文案 `"导出插件设置，不包含密钥、聊天"` 的同类设计意图 —— 作者在导出路径上系统性排除了敏感内容。

### 6.5 网络行为

**【观测】** 仅两处 `HttpURLConnection` 使用：

| 方法 | 目标 | 说明 |
|---|---|---|
| `S0.a(String, String)` | `https://wwbnj.lanzoul.com/b007utki2d` | 自更新检测，`User-Agent: Mozilla/5.0 NewSoul-UpdateCheck`，带 `Referer` |
| `d3.b(File, String)` | 跟随 `Location` 头 | 下载文件，日志 `"Invalid redirect"` |

**【观测】** 字符串层面**不存在**明文 `http://`（仅 5 条 `https://`：两条 JS 探针、TG 群、两条蓝奏云）。**【观测】** 无 `Socket`、`SocketFactory`、`SSLContext`、okhttp、第三方网络库。

**【推断】** 网络面**极窄**：只有自更新下载和 TG 社区链接。没有埋点、没有远端配置下发、没有第三方 SDK。日志导出是**本地加密文件**，不自动上传（未见上传路径的调用链）。

**【未知】** `V.e()` 产出的加密分片是否会被 `S0.a` / `d3.b` 的上传逻辑发送 —— 现有证据显示这两个方法只做**下载**（读响应），未观察到请求体写入聊天内容或日志的行为。

### 6.2 风险点

**【观测】** 该模块具备完整的**代码执行能力**（Xposed hook 全进程）与**网络能力**（以宿主身份请求蓝奏云、Telegram、`app.soulapp.cn`）。它**没有**任何 Android 权限声明，因此所有网络/存储操作都以宿主 Soul 的权限执行。

**【观测】** 模块可读取宿主的 `SharedPreferences`、`Activity`、`Intent` 内容，并可通过 `createPackageContext` 访问自身资源。

**【观测】** 未发现以下高风险行为的证据（在本次静态分析范围内）：
- 无 `Runtime.exec` / `ProcessBuilder` / `System.load` / `loadLibrary`（0 处）
- 无 `DexClassLoader` / `PathClassLoader` / `InMemoryDexClassLoader`（**无动态代码加载**）
- 无 `Socket` / `SocketFactory` / `SSLContext` / okhttp（仅系统 `HttpURLConnection`）
- 无第三方网络库、无埋点 SDK、无远端配置下发
- 明文 `http://` 0 处
- 加密仅用于**本地诊断日志导出**（§6.3–6.4），且为公钥-only，本 APK 无法解密自身产出

**【推断】** 蓝奏云自更新链接（`https://wwbnj.lanzoul.com/b007utki2d`）是**供应链风险点**：若分发账号被接管，可推送任意新版模块。建议核对模块签名。

**【推断】** 混合加密的存在是**正向信号**而非风险信号：作者主动避免明文日志导出可能导致的用户隐私泄露。

**【未知】** 本次分析**未做** APK 签名验证（REA 的 `inspect_signature` 为 macOS-only，不可用），也未验证 `META-INF/NEWSOUL-.RSA` 证书链。**【观测】** 仅确认签名为 v1（JAR signing），**未发现 APK Signature Scheme v2/v3 块的证据** —— v1-only 签名在 Android 11+ 上仍可用但不推荐。

---

## 7. 方法论与工具

### 7.1 REA 工具使用情况

| 工具 | 结果 |
|---|---|
| `open_binary` | ✅ APK 打开，SHA-256 `6681a37e…` |
| `inspect_artifact` | ✅ 14 节点/13 边，完整清单 |
| `extract_artifact` | ✅ 13 文件落盘 |
| `inspect_android_package` | ❌ 需 `REA_JADX_MCP_JAR`（未配置） |
| `binary_session` | ✅ 能力矩阵；Ghidra 未配置（且仅支持 PE，不适用 DEX）；JADX/JEB 不可用 |
| `inspect_signature` | ❌ macOS-only |

**【推断】** REA 的 Android provider 全部需要外部工具（JADX/JEB），本次均不可用。因此我自行实现了 DEX 解析器，这在**证据可信度上是等价的**：DEX 格式是 AOSP 公开规范，字节级解析结果可直接与 `strings`/`method_ids` 表交叉验证。

### 7.2 自研工具

| 文件 | 作用 |
|---|---|
| `re_work/dexparse.py` | 完整 DEX 解析器（header/string**含 MUTF-8**/type/proto/field/method/class_def/class_data/code_item/annotation） |
| `re_work/dalvik.py` | Dalvik 指令长度表 + `refs_from_code()` 交叉引用提取 |
| `re_work/features2.py` | 将每条字符串/类型归属到使用它的方法 |
| `re_work/axml.py` | 二进制 AndroidManifest.xml 解码 |
| `re_work/show.py` | 按方法签名正则打印字符串/类型/callee |
| `re_work/featuremap.json` | 结果数据集（907 KB） |

**【观测】** 调试过程中修复的三个关键缺陷（对结论正确性至关重要）：
1. **MUTF-8 解码**：初版用 `utf-8` 解码导致中文全部乱码（`检测通过�`）。改为按 UTF-16 代码单元处理并合并代理对后恢复。
2. **指令长度表**：初版 `const-string` (0x1a) 长度误设为 1 单元，导致操作数被截断、**字符串交叉引用全部为 0**（2050 → 0）。修正为 2 单元后恢复。
3. **AXML 属性偏移**：初版把 `Res_value` 的 `dataType` 读成 `size`，所有属性显示为不存在的 type 0x08。修正后正确解出 `versionName="1.34.0"`。

**【推断】** 第 2 点尤其值得强调：若未发现该缺陷，本报告会得出"模块不含任何字符串引用"的**完全错误结论**。因此所有交叉引用类结论均基于修正后版本。

---

## 8. 待解未知项

1. **【未知】** 宿主 `cn.soulapp.android` 的真实方法签名 —— 需安装 Soul 6.37.0 并反编译宿主 APK 才能确认模块 hook 的具体方法与字段是否与实际一致。
2. **【未知】** 反撤回在**服务端**的行为 —— 模块仅影响本机显示还是阻止服务端撤回通知。
3. **【未知】** `p.intercept` 改写骰子结果的具体字节/字段布局。
4. **【未知】** APK 签名证书主体与 v1 签名完整性（工具不可用）。
5. **【未知】** `A2`/`B2`/`g` 类构成的 feed 过滤具体判定算法（性别/关键词匹配细节）。
6. **【未知】** 蓝奏云自更新包的当前内容与真实性。
7. **【未知】** `soul-demo/*` 两个 ID 与 `new-soul/*` 的关系（疑似开发期遗留或调试通道）。

---

## 9. 复现命令

```powershell
$py = "E:\Deepseek_Harness\dsh-home\dsh-runtimes\dsh-primary-runtime\dependencies\python\python.exe"
$env:PYTHONIOENCODING = "utf-8"
cd "E:\student\NX\new-soul-1.34.0\re_work"

# 清单概览
& $py dexparse.py classes.dex

# 功能归属映射（含中文标签 + 开关键 + hook 体）
& $py features2.py classes.dex > feat2.txt

# AndroidManifest 解码
& $py axml.py AndroidManifest.bin.xml

# 查询任意方法
& $py show.py "SoulHook;->onPackageReady" "newsoul/Q0;->z\("
```

---

## 附录 A：关键指标汇总

| 指标 | 值 |
|---|---|
| APK SHA-256 | `6681a37eb4f7f145a4070e5a464d74f1f09f458d8f58419cfdc005c5bb316dd8` |
| 模块包名 | `dev.local.newsoul.public` |
| 版本 | 1.34.0 (versionCode 88) |
| 宿主包名 | `cn.soulapp.android` |
| 适应宿主版本 | Soul 6.37.0 |
| LSPosed API | 102（min/target），staticScope，protective |
| minSdk / targetSdk | 26 / 35 |
| DEX 类 / 方法 / 字符串 | 216 / 1766 / 3025 |
| 功能开关 | 44 |
| Hook ID | 59 |
| 宿主目标类 | 96 |
| Hooking 类 | 23（实现 `XposedInterface$Hooker`） |
| 安装器方法 | 29 |
| 中文 UI 字符串 | 348 |
| APK 体积 | 1,655,750 字节 |

## 附录 B：Hooker 实现类（23 个，观测）

`a`, `a1`, `b`, `B`, `c`, `C`, `d`, `D`, `e`, `f`, `f1`, `k1`, `l0`, `m0`, `n0`, `N2`, `p`, `s1`, `v`, `x2`, `X0`, `y2`, `z2`

其中：
- `X0` — 通用方法 hook（4 构造参数含 `Method`/`String`/`XposedModule`）
- `z2`/`y2`/`x2` — 设置入口与 Activity 生命周期 hook
- `l0`/`m0`/`n0` — 资料页计数伪装（反射 `Field`）
- `B`/`C`/`D`/`v`/`f1`/`k1`/`p` — 视图/设置项 hook
- `a`/`b`/`c`/`d`/`e`/`f` — 通用反射原语（字段读写、方法调用、剪贴板、广告）
