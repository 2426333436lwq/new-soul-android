# New Soul 1.34.0 逆向分析报告

> 分析对象：`new-soul-1.34.0.apk`（SHA-256: `6681A37E...B316DD8`，1,655,750 字节）
> 分析方法：APK 解包 → jadx 1.5.6 反编译 → 源码级逻辑梳理
> 分析日期：2026-09-27

---

## 一、样本概况

| 项目 | 内容 |
|---|---|
| 应用名 | New Soul |
| 包名 | `dev.local.newsoul.public`（宿主目标：`cn.soulapp.android`，即 **Soul App**） |
| 版本 | 1.34.0（versionCode 88，minSdk 26 / targetSdk 35） |
| 本质 | **Xposed/LSPosed 注入模块**（非独立应用，需框架宿主加载） |
| 框架 API | `io.github.libxposed.api`（LSPosed 的 libxposed 接口，API 102） |
| 入口类 | `dev.local.newsoul.SoulHook` |
| 模块文件 | `META-INF/xposed/`（module.prop / scope.list / java_init.list） |
| 资源 | 仅图标 + 支付宝/微信收款码（捐赠功能），无 Activity |

**定位**：这是一个针对 Soul 陌生人社交 App 的"辅助增强"外挂模块，功能覆盖广告屏蔽、聊天防撤回、广场自动化、资料伪装、隐私媒体解锁、限制绕过等。

---

## 二、技术栈与运行框架

- **注入机制**：LSPosed（libxposed）现代 Xposed 框架，`staticScope=true`（静态作用域，仅注入 `cn.soulapp.android` 进程），`exceptionMode=protective`（Hook 回调异常不崩溃宿主）。
- **生命周期回调**：`onModuleLoaded`（框架加载）→ `onPackageReady`（宿主进程就绪，`isFirstPackage` 保证单次安装）→ `onResume` 兜底。
- **防重复加载**：`System.setProperty("newsoul.active.module", ...)` 全局标记，避免多实例重复注入。
- **Hook 能力**：`hook()` 建 Hook 点、`deoptimize()` 关闭方法内联（保证 Hook 生效）、反射定位私有/混淆方法，全部 `PROTECTIVE` 异常模式。
- **代码风格**：全部类名混淆为单字母（A/Z0/AbstractCxxxx），字符串明文保留；主逻辑集中在约 140 个类中。

---

## 三、模块加载与入口逻辑

`SoulHook.onPackageReady()` 是总装配线，按顺序安装以下 Hook 组：

1. **启动链路**：`Application.onCreate` → `MainActivity.onCreate/onNewIntent`（冷/热启动意图处理，`Q0.n`）
2. **开屏广告**：`tc.e$a.j/m/onApplicationForeground`（热启动门控）+ `MainScheduler.v/s`（启动调度器，`skip-startup-ad`）
3. **广告引擎**：`AbstractC0036j`（19 个广告位注册）
4. **聊天游戏**：`DiceFingerMsg` 构造器 + `y4` 类（自定义骰子/猜拳）
5. **IM 防撤回**：`ChatManager.O(List)` + `RoamHandler.a` + 大量 IM 消息反射引用（`Q0`）
6. **其余模块**：自撤回绕过（`AbstractC0026g1`）、无水印下载（`Q0.A`）、复读/转发/语音库（`F`/`C0014d1`/`d3`）、帖子筛选（`B2`）、自动点赞（`Q0.F`/`Z0`）、分享限制（`Q0.C`）、礼仪分限制（`Q0.E`）、隐私媒体（`Q0.D`）等
7. **收尾**：全局 `Activity.onResume` Hook（页面日志、启动 Toast）→ `installSettingsHook`（H5 设置页注入）→ `installed=true`

---

## 四、功能模块拆解

### 4.1 广告屏蔽引擎（AbstractC0036j）—— 最重头的模块

19 个广告位定义（键 → 名称）：

| 广告位键 | 位置 | 广告位键 | 位置 |
|---|---|---|---|
| block_splash | 冷/热启动开屏 | ad_envelope | 通知信封 |
| ad_feed | 广场信息流 | ad_notice | 系统通知 |
| ad_detail | 帖子详情 | ad_chat_tools | 聊天面板推广 |
| ad_video | 竖屏视频流 | ad_float | 悬浮广告 |
| ad_tag / ad_tag_web | 话题横幅/网页 | ad_interstitial | 插屏与底部弹窗 |
| ad_search | 搜索顶部横幅 | ad_reward_chat | 聊天激励广告 |
| ad_group | 群组横幅 | ad_reward_drama | 短剧激励广告 |
| ad_planet | 星球游戏推广 | ad_reward_web | 网页与任务激励 |
| ad_me | 我的页面推广 | ad_conversation | 聊天会话列表 |

**三层屏蔽机制**：
1. **数据层过滤**：Hook `BaseQuickAdapter`（Soul 使用的通用 RecyclerView 适配器）的 `addData/setData/setList/replaceData/setNewInstance/setNewData/setDiffNewData`，识别广告数据模型（`la.a`、`ia.a` 类型）并从数据集合中剔除。
2. **视图层隐藏**：Hook 16 个广告 View 构造器（`PostFeedExpressComponent` 等）+ ViewHolder 的 `onBind/onCreate`，拿到 `itemView` 后执行 `setVisibility(GONE)`；同时全局 Hook `View.setVisibility` 兜底，任何广告 View 恢复可见都会被再次压回 GONE。
3. **生命周期拦截**：插屏广告 `onStart` 后立即 `dismiss`；广告加载器 `loadAds` 被 Hook（`C0004b`），调用栈分析（`StackTraceElement` 匹配类名前缀）动态识别广告归属位（`pi.d`→ad_conversation、`RewardAdHelper`→ad_reward_chat、`SRNAdPlugin`→ad_reward_web 等）。

### 4.2 聊天增强

- **消息防撤回（核心功能）**：Hook `ChatManager.O(List)`（收到撤回事件时遍历消息列表），对每条消息调用 `Q0.S()`：判定消息类型 `msgType==9`（撤回类型）且非闪照 → 从 `ChatManager` 会话中查找原消息 → 校验发送者/接收者/消息 ID 一致 → 计算 SHA-256 指纹（账号+from+to+msgId）存入 `recalled.v1` 去重 → 从撤回列表移除该消息（即"撤回被拦截"）。漫游通道由 `RoamHandler.a` 兜底，同样写入 `recalled.v1`。
- **自己撤回绕过**：Hook `ImMessage.getLocalTime` + `WithDrawHandler.d`，用 `ThreadLocal` 标记，跳过 2 分钟撤回时限（仅对自己消息生效）。
- **撤回样式标记**：Hook `AbsChatDualItem` 绑定方法，在聊天气泡上渲染"对方已撤回 / 已保留"标记，支持自定义样式。
- **自定义骰子/猜拳**：Hook `DiceFingerMsg` 构造器，用 `ThreadLocal` 中用户预设的数字替换随机结果，发送前可选择"随机/指定"。
- **聊天复读/转发/语音库**：向 Soul 原生长按菜单注入 3 个自定义菜单项（菜单 ID 5133057/5133058/5133059），复制消息对象（文本/图片/多图/语音）重新构造并发送；语音库模块（`d3`）支持把语音消息保存到本机、按账号管理备注、再次发送（"local storage and native audio upload"）。
- **隐藏输入状态**：拦截"正在输入/结束输入"上报（`ChatManager.p0`）。
- **礼物/礼仪分限制解除**：Hook `ChatLimitModel.isLimit`、`Conversation.getIntExt`（会话额度）、`LoveBellProConfig.getShowPopup`（奇遇铃弹窗）、`SocialGraceScoreBean` 四个门控方法（`getCanNotSendBeforeReply/getCreateSessionLimit/getForbidCall/getForbidPic`）+ `SocialScoreUtils` 弹窗，解除本地聊天限制。

### 4.3 广场自动化

- **一键/自动点赞**：`Z0` 是一个自定义 `FrameLayout` 触摸拦截层（含 VelocityTracker/触摸判定），注入广场爱心按钮；支持"屏幕内帖子/全部已加载帖子"范围、按性别筛选、失败重试、跳过已点赞帖；通过 Hook `Post` 字段（`getAdResponse/incentiveModel`）识别广告帖不点赞。
- **帖子筛选**（`B2`）：Hook `SquareAdapter.d/j/h`（bind/recycle/attach），对 `Post` 的正文/标题/话题标签匹配屏蔽词（`C0041k0`），按作者 `gender` 字段做性别过滤（仅女生/仅男生/不限），命中则把 itemView 高度置 0 + GONE（隐藏卡片，非删除）。
- **帖子文案复制**：Hook `SeedsShareDialogFragment` 分享面板，注入"复制文案"入口。
- **分享限制解除**：Hook 私有方法 `r0`，解除帖子分享面板的本地限制。
- **无水印下载**：Hook `ImageDownloader.f` / `h2.b`，把水印参数从 true 改为 false（`download_image_no_watermark` / `download_video_no_watermark`）。

### 4.4 主页信息伪装（AbstractC0061p0）

- 支持对**旧个人页/新个人页**注入修改：关注数、被关注数、访客数、加入天数（键 `profile_disguise_follow_/followed_/visitor_/days_`）。
- 实现方式：Hook 个人主页数量显示区域的绑定方法，用本地配置值覆盖渲染数据（仅改本机显示，不改变真实关系/服务器数据，提示文案明确声明"不改变真实关注关系/他人看到的数据"）。

### 4.5 隐私媒体解锁

- **闪照/隐私图片多次查看**：Hook `PrivacyProtectPreviewActivity.init` / `SnapChatActivity`，把 Intent 中 `limitViewTimes` 置 0，解除本地查看次数限制。
- **防截屏解除**：`activity.getWindow().clearFlags(FLAG_SECURE)`（8192）。
- **隐私图片/视频下载**：Hook 预览页下载流程，定时（0/250/750/1500ms）自动触发下载按钮，通过 MediaStore 保存到相册 `Pictures/New Soul`。

### 4.6 更新与分发（T0 / d3 / RunnableC0024g）

- **更新源**：蓝奏云网盘分享页 `https://wwbnj.lanzoul.com/b007utki2d`（提取码 `3gin`）。
- **机制**：GET 分享页 → 正则解析出 `filemoreajax.php` 接口、fid 及 uid/puid/rep/t/k/up/ls 参数 → POST 拉取发布目录 JSON（每页 50 条，最多 20 页）→ 匹配 `new-soul-X.Y.Z.zip` 命名 → 选取版本号最大者返回下载页链接。
- **下载**：`d3` 类下载 ZIP（20MB 上限、跟随重定向、45 秒超时、512KB 页面响应上限），ZIP 内含新版本 APK（"覆盖安装"）。
- **社群**：内置 Telegram 群链接 `https://t.me/+3Hl2lJ1X-oZlZTZl`。

### 4.7 设置 UI 注入（三入口）

1. **底栏长按**：Hook `MainActivity.onCreate`，给底部 4 个 Tab（星球/广场/消息/我的）注册 `OnLongClickListener` → 弹出设置面板 `C0067q2`（自绘 Dialog，非宿主原生 UI）。
2. **H5 设置页注入**：Hook `H5Activity.m1(boolean)`（私有方法），在 Soul 的 H5 设置页布局（`topic_title/rlWebRoot/contentLayout/titleLay`）上挂载自绘开关组，并注入 JS 切换夜间/日间主题（`theme-night/theme-daytime`）。
3. **启动 Toast**："New Soul 已生效"。

设置项全部存入 `SharedPreferences("dev.local.soulhookdemo.public.settings.v1")`，按登录账号分别保存；支持配置导入/导出（JSON 文件、SAF 文件选择器）与迁移。

### 4.8 调试与反分析设计

- **功能兼容性检测**（`Q0.U`）：逐一反射验证 30+ Hook 点签名，输出"已注册/签名存在/缺失"状态与宿主版本（`hook_diagnostics_last_host_version`），用于适配 Soul 版本更新。
- **调试日志**（`V`）：事件级 JSON 日志（时间/会话 UUID/序号/点位/事实），上限 256 条、单条 6000 字符，可导出 ZIP。
- **日志加密**：导出时用**内置 RSA-2048 公钥**（X509，`RSA/ECB/OAEPWithSHA-256AndMGF1Padding`）加密会话密钥，日志正文用 **AES/GCM** 加密 —— 即只有作者私钥可读，防止日志内容被第三方截获/伪造。
- **调用栈溯源**（`Q0.X`）：记录 Hook 触发时宿主侧调用栈（过滤掉自身包名）用于排障。
- **签名公钥**：仅内置公钥（`Q0.M`），无私钥，无法被用来伪造/篡改。

---

## 五、Hook 点总表（核心）

| Hook 目标（Soul 侧类.方法） | 功能 | 入口 |
|---|---|---|
| `MainActivity.onCreate/onNewIntent` | 启动意图/底栏入口/点赞生命周期 | C0000a(2/19) |
| `MainScheduler.v/s` | 跳过开屏广告 | C0016e |
| `tc.e$a.j/m` + `ea.a.a` | 热启动广告门控 | D |
| `H5Activity.m1(boolean)` | 设置页注入 | C0094x2 |
| `BaseQuickAdapter.addData/...` 系列 | 信息流广告数据过滤 | C（匿名 Hooker） |
| 16 个广告 View 构造器 + `View.setVisibility` | 广告视图隐藏 | C0020f / C0000a(0) |
| 广告加载器构造器 + `loadAds` | 广告位识别/激励广告 | C0000a(1) / C0004b |
| `InterstitialAdDialog.onStart` | 插屏跳过 | C0016e |
| `ChatManager.O(List)` | 消息防撤回 | C0000a(9) |
| `RoamHandler.a` | 漫游防撤回 | C0000a(10) |
| `ImMessage.getLocalTime` + `WithDrawHandler.d` | 自撤回时限绕过 | C0000a(15) |
| `DiceFingerMsg` 构造器 | 自定义骰子/猜拳 | C0000a(4) |
| `u0.p/k` + `ChatLongClickPopUp` 渲染器 | 复读/转发/语音库菜单 | F / C / D |
| `ConversationFragment.onResume/onPause` | 语音库入口 | N2 |
| `ChatManager.p0` | 隐藏输入状态 | C0022f1 |
| `ChatLimitModel.isLimit` / `Conversation.getIntExt` | 礼物会话限制 | C0000a(5/6) |
| `LoveBellProConfig.getShowPopup` | 奇遇铃弹窗 | C0000a(7) |
| `SocialGraceScoreBean.get*` ×4 + `SocialScoreUtils.b` | 礼仪分限制 | C0020f / C0000a(18) |
| `SquareAdapter.d/j/h` | 帖子筛选（词/性别） | z2 |
| `SquareFloatingButton` 构造/可见性 | 自动点赞触发 | C0000a(20/21) |
| `ImageDownloader.f` / `h2.b` | 无水印下载 | X0 |
| `SeedsShareDialogFragment.initViews/P0/r0` | 文案复制/分享限制 | C0022f1 / C0042k1 |
| `PrivacyProtectPreviewActivity.init/G`、`SnapChatActivity.C` | 隐私媒体多次查看/下载/截屏 | C0000a(16/17) |
| 个人主页数量绑定（多版本适配） | 关注/访客/天数伪装 | AbstractC0061p0 |
| `AbsChatDualItem` 绑定 | 撤回标记样式 | B |

---

## 六、数据存储

| 存储 | 内容 |
|---|---|
| SP `dev.local.soulhookdemo.public.settings.v1` | 全部功能开关与配置（按账号） |
| SP `dev.local.soulhookdemo.recalled.v1` | 已保留撤回消息的 SHA-256 指纹（去重） |
| SP `hook_diagnostics_*` | 兼容性检测状态/宿主版本 |
| `noBackupFilesDir/newsoul-debug/` | 调试日志（RSA+AES 加密导出） |
| 相册 `Pictures/New Soul` | 保存的隐私图片 |
| 语音库（本机文件） | 保存的语音消息 |

---

## 七、风险与合规评估

1. **违反用户协议**：对 Soul 客户端进行注入、数据篡改、自动化操作，违反 Soul 用户协议，账号存在被检测封禁的风险；模块自身也声明"实际效果以服务器处理为准"。
2. **隐私风险**：资料伪装、防撤回、闪照保存等功能涉及对他人信息的留存与篡改，可能侵犯他人隐私与平台规则。
3. **供应链风险**：更新走蓝奏云公开网盘 + 内置 RSA 公钥仅加密日志（**不校验更新包签名**），若发布者账号被盗或分发链被劫持，存在下发恶意版本的可能（但作者无私有签名体系，风险与普通网盘分发一致）。
4. **技术合规**：作为静态分析样本，本文仅描述其技术机制，不构成对破解/绕过防护的协助。

---

## 八、技术结论

- **架构**：单入口（`SoulHook`）→ 反射定位 + `hook/deoptimize` 装配 ≈ 40 个功能 Hook 点，全部 `PROTECTIVE` 模式，失败自动降级不影响宿主。
- **实现特色**：广告屏蔽采用"数据剔除 + 视图隐藏 + 生命周期拦截"三层纵深；防撤回采用"指纹去重 + 双通道（私聊/漫游）拦截"；设置 UI 全部自绘（不依赖宿主资源 ID）；对 30+ 关键 Hook 点做了版本兼容性自检。
- **工程质量**：异常处理完备（每个模块独立 try/catch）、日志体系专业（加密导出、调用栈溯源）、UI 适配多版本（旧/新个人页、日夜间模式）。整体是个人开发者作品中的较高水平，面向 Soul 特定版本（6.37.0 基线）持续适配。
