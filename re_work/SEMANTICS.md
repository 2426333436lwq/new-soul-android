# New Soul 可读重构 · 语义规格

本文件是**实现依据**（非交付文档），记录从 `re_work/smali_out/dev/local/newsoul/` 逆向确认的
功能语义与工程映射约定。每确认一项即追加，避免跨上下文重复解析 400KB smali。

---

## 0. 构建与工程约定

构建命令：

```powershell
$env:JAVA_HOME="E:\Tools\jdk-21"; $env:GRADLE_USER_HOME="D:\Tools\gradle-home"; $env:ANDROID_HOME="D:\Android\Sdk";
& "D:\Tools\gradle-8.11.1\bin\gradle.bat" -p "e:\student\NX\new-soul-1.34.0\NewSoul" assembleRelease -q --console=plain
```

- 输出：`NewSoul\app\build\outputs\apk\release\app-release.apk`
- 命名约定：**禁止保留混淆名**。原单字母类按功能域重命名为语义类，钩子 ID（`new-soul/...`、`soul-demo/...`）逐字保持。
- 原文件对照：大写类 = `X.smali`，小写类 = `x.1.smali`（apktool 处理大小写冲突时加 `.1`）。

## 1. framework 原语（原 `Q0`）

| 原方法 | 可读实现 | 语义 |
| --- | --- | --- |
| `Q0.H` | `Hooks.installFlagOverride` ✅ | 目标须 **static + 返回 void**，否则 `IllegalStateException("Unexpected download signature: "+m)`。hook id = `"new-soul/"+key`；回调 `X0(targetArgIdx=p3, ctxMethod=p5, key, module, ctxIdx=p4)`。ctxIdx≥0 取 `getArg(ctxIdx)` 否则 `ctxMethod.invoke(null)`；Context 为 null 或开关(false) → `proceed()`；否则 `args[targetArgIdx]=FALSE`，日志 `watermark bypass: <key>; original=<原值>`，`proceed(args)`。成功日志 `watermark control installed: <key>`（INFO）。 |
| `Q0.X0` | （已内联进 `installFlagOverride`） | 见上 |
| `Q0.G` | `Hooks.installRepeatBoundary` ❌ | 见 §2.7 |
| `Q0.I` | `Hooks.installPostActionMarks` ❌ | 见 §2.2 |
| `Q0.J` | `Hooks.installPrivacyRepeatGuard` ❌ | 见 §2.4 |
| `Q0.K` | `Hooks.installChatRecallMark` ❌ | 见 §2.8 |
| `Q0.L` | `ValueCodec.recallFingerprint` ❌ | 拼接 `Q0.m.invoke(null)`\n`Q0.p.invoke(msg)`\n`Q0.q.invoke(msg)`\n`Q0.r.invoke(msg)`（静态方法，`m` 取会话标识），UTF-8 → SHA-256 → 小写十六进制（`%02x`）。 |
| `Q0.N` | `ValueCodec.isDirectHttpUrl` ❌ | URI scheme 为 http/https 且 host 非空且 userInfo 为 null。 |
| `Q0.O` | `ValueCodec.describeMessage` ❌ | 未开启诊断 → `""`；null → `message_present=false`；`getChatMessage()` 为 null → `message_present=true; chat_present=false`；否则 `message_present=true; type=<getMsgType()>`（非 Integer 记 `unknown`）；异常 → `...type=unavailable; error=<类名>`。 |
| `Q0.P` | `ValueCodec.parseInt` ❌ | `trim` 后 `Integer.parseInt`；`signed=false` 时 `<0` 抛 `NumberFormatException` → 转 `IllegalArgumentException`（文案：`请输入 0 至 2147483647 的整数` / `请输入 -2147483648 至 2147483647 的整数`）。 |
| `Q0.o` / `Q0.p` | `ValueCodec.readInt` ❌ | 从 Context/Activity 读 int（设置界面用）。 |
| `Q0.q` | `Prefs.getBoolean(ctx,key,true)` ❌ | `prefs.getBoolean(key,true)`。 |
| `Q0.R` | `ValueCodec.isRepeatBlocked` ❌ | 见 §2.4。 |
| `Q0.w` / `Q0.r` / `Q0.s` | `Logger` ❌ | 带诊断前缀的日志封装。 |
| `Q0.X` | `ValueCodec.describeCallSite` ❌ | 诊断「来源」字符串。 |

### 1.1 宿主方法常量绑定（`Q0` 静态 `Method` 字段）

由 `SoulHook.smali` 1985–2052 行初始化，`ValueCodec` 需以同样的反射目标实现：

| 字段 | 宿主方法 | 语义 |
| --- | --- | --- |
| `Q0.m` | `cn.soulapp.imlib.j0.g()`（静态无参） | 当前会话标识 |
| `Q0.n` | `ImMessage.getChatMessage()` | 会话对象 |
| `Q0.p` | `ImMessage.getFrom()` | 发送方 |
| `Q0.q` | `ImMessage.getTo()` | 接收方 |
| `Q0.r` | `ImMessage.getMsgId()` | 消息 ID |
| `Q0.s` | `ImMessage.isAnonymityChat()` | 是否匿名聊天 |
| `Q0.l` | （`SoulHook.smali` 1998 行前一项，待确认） | — |

> 注意：§1 表中 `Q0.r`/`Q0.s` 早先被误记为「日志封装」，实为上述 `Method` 字段；
> 日志封装方法是 `Q0.w`（`Log.i` + 模块 log）、`Q0.x` 等。

### 1.2 精确语义（已逐行确认，实现依据）

**`Q0.L` → `ValueCodec.recallFingerprint(Object msg)`**
拼接 `String.valueOf(j0.g())` + `"\n"` + `msg.getFrom()` + `"\n"` + `msg.getTo()` + `"\n"` + `msg.getMsgId()`
（均为 `String.valueOf`，即 null 变字面量 `"null"`）；UTF-8 → `MessageDigest("SHA-256")`
→ 每字节 `String.format(Locale.ROOT, "%02x", b & 0xff)` 小写十六进制。

**`Q0.N` → `ValueCodec.isDirectHttpUrl(String s)`**
`s == null` → false；`new URI(s)`；`scheme.equalsIgnoreCase("https") || equalsIgnoreCase("http")`
且 `host != null && !host.isEmpty()` 且 `userInfo == null` → true；`URISyntaxException` → false。

**`Q0.O` → `ValueCodec.describeMessage(Object msg)`**
诊断会话未激活 → `""`；`msg == null` → `"message_present=false"`；
`msg.getChatMessage() == null` → `"message_present=true; chat_present=false"`；
否则取 `getChatMessage().getMsgType()`，是 `Integer` 则用其值、否则 `"unknown"`，
结果 `"message_present=true; type=" + v`；抛异常 → `"message_present=true; type=unavailable; error=" + e.getClass().getName()`。
（`getChatMessage` / `getMsgType` 用 `Class.getMethod`，即 public 无参。）

**`Q0.P` → `ValueCodec.parseInt(String s, boolean signed)`**
`s.trim()` → `Integer.parseInt`；`!signed && v < 0` 抛 `NumberFormatException`；
捕获 `NumberFormatException` → 抛 `IllegalArgumentException`，文案
`signed ? "请输入 -2147483648 至 2147483647 的整数" : "请输入 0 至 2147483647 的整数"`。

**`Q0.v` → `ValueCodec.matchesRecallSession(Object msg)`**
`msg == null` → false；`msg.isAnonymityChat()` → false；`msg.getChatMessage() == null` → false；
`session = j0.g()`、`from = msg.getFrom()`、`to = msg.getTo()`；
`session` 为 null/空 → false；`from` 为 null/空 → false；`from.equals(session)` → false；
`!session.equals(to)` → false；否则 true。

**`Q0.R` → `ValueCodec.isRepeatAllowed(Activity a)`**
`a.getIntent().getBooleanExtra("isSender", false)` 为 true → false；
否则返回 `a.getSharedPreferences("dev.local.soulhookdemo.public.settings.v1", 0)
.getBoolean("privacy_allow_repeat", false)`。

**`Q0.X` → `ValueCodec.describeCallSite()`**
遍历当前线程栈；跳过类名以 `dev.local.newsoul.` 开头、或类名等于 `java.lang.Thread` 的帧，
以及不满足「以 `cn.soulapp.` 开头 或 以 `uj.` 开头 或 含 `patch`」的帧；
保留帧拼接 `类名.方法名:行号`，帧间以 `" <- "` 连接，最多 6 帧；为空 → `"unavailable"`。

**`V.a()` → `Diagnostics.isActive()`**
`V.f`（会话截止时间戳）减当前时间在 `(0, 1800000]`（30 分钟）内 → true，默认 `f = 0` → false。
`V` 整体属作者专属诊断上报子系统，本次不实现，`Diagnostics.isActive()` 恒返回 false。

## 2. 功能域语义

### 2.1 无水印（原 `Q0.A`）— ✅ 已实现 `feature/download/NoWatermarkFeature`

- 图片：`lf.b.b()`（静态无参→Context）作 ctxProvider；`ImageDownloader.f(String,boolean,boolean,ImageMarkProcessor,square.utils.b)`；flagIdx=2，ctxIdx=-1。
- 视频：`square.utils.h2.b(String,Context,long,boolean)`；flagIdx=3，ctxIdx=1，ctxProvider=null。
- 失败日志（ERROR）：`image watermark control unavailable` / `video watermark control unavailable`。

### 2.2 帖子复制 + 动作标记（原 `Q0.B` / `Q0.I`）

类：`SeedsShareDialogFragment`、`BaseSeedsDialogFragment`；内部类名 = `BaseSeedsDialogFragment.getName()+"$Operate"`。

- `Q0.I(module, cl, baseSeeds, operateFieldA)`：hook `square.adapter.TypeAdapter$a.a(Post)`（`getDeclaredMethod("a",{Post})`）、字段 `TypeAdapter$a.c`；id `new-soul/action-marks-post`；hooker `B(TypeAdapter$a.c, Post.content, case=1)`；随后 `deoptimize(a)`。失败日志（**Android Log.w**）`post action mark unavailable`。
- `Q0.B` 主体：字段 `SeedsShareDialogFragment.E`、`BaseSeedsDialogFragment.q`、`Post.content`(public)、`Operate.a`(public)；`ctor=Operate(int,String,boolean)`；`initViews(View)`、`P0(Operate,View,int)`、`dismiss()`。
  - `P0` → id `new-soul/post-copy-click`，hooker `b(Operate.a, SeedsShare.E, Post.content, initViews, case=1)`。
  - `initViews` → id `new-soul/post-copy-action`，hooker `f1(SeedsShare.E, BaseSeeds.q, Operate.a, ctor, case=1)`。
  - `deoptimize(P0)`；成功日志（Android Log.i）`post copy hooks installed`；失败 `post copy hooks unavailable`（ERROR）。
- **hooker `B`**（`constructor(Field b, Field c, int case)`）：
  - `case==0`（聊天撤回标记）：`view = b.get(getArg(0))`；`z1.b(view)`；`r = proceed()`；`msg = getArg(1)`；若 `Q0.v(msg)` 且 `recalledPrefs("dev.local.soulhookdemo.recalled.v1").contains(Q0.L(msg))` → 取 `c.get(getThisObject())` 的 `alias`/`signature` 公共字符串字段作显示名（空则默认 `对方`）；`style = w2.o(ctx).getString("chat_recall_style","below")`；`z1.a(view, style, name)`。异常日志 `recall message style unavailable`（ERROR）。返回 `r`。
  - `default`（帖子动作标记）：`view = b.get(getThisObject())`；`r = proceed()`；`v = c.getInt(getArg(0))`；`j1.b(view, v == -0x35efa /* -221946 */)`。异常日志（Android Log.w）`post action mark unavailable`。返回 `r`。

### 2.3 分享权限（原 `Q0.C`）

- `SeedsShareDialogFragment.E` 类型必须 == `Post`，否则 `NoSuchFieldException("Unexpected share post field")`。
- `r0()` 必须无参、返回 void、private，否则 `NoSuchMethodException("Unexpected share permission signature")`。
- hook `r0` → id `new-soul/post-share-permission`；hooker `k1(E, case=0)`；`deoptimize(initViews)`；成功日志 `post share control installed`；失败 `post share control unavailable`。
- **hooker `k1` case 0**：`post = fieldE.get(getThisObject())`；非 null 且 `post_remove_share_limit` 开启 → 日志 `post share restriction bypassed` 并返回 `null`（放行），否则 `proceed()`。

### 2.4 隐私（原 `Q0.D` / `Q0.J` / `Q0.R`）

- `Q0.D`：hook `PrivacyProtectPreviewActivity.init(Bundle)` → id `new-soul/privacy-photo-preview`，hooker `a(case=16)`；成功日志 `privacy photo controls installed`；失败 `privacy photo controls unavailable`。随后 `Q0.J(cl, PrivacyProtectPreviewActivity, "G", "privacy")` 与 `Q0.J(cl, SnapChatActivity, "C", "snap")`。
- `Q0.J(cl, className, methodName, route)`：`getDeclaredMethod(methodName,{})` 必须**返回 void 且非 static**，否则 `NoSuchMethodException("Unexpected repeat guard signature: <m>")`；id = `"new-soul/privacy-repeat-"+route`；hooker `f(case=1, route)`；随后对类及其全部声明内部类 `deoptimize`（`Q0.m`）；`route=="snap"` 时额外 hook `init(Bundle)` → id `new-soul/privacy-repeat-snap-preview`，hooker `a(case=17)`；成功日志 `privacy repeat guard installed: route=<route>`；失败 `privacy repeat guard unavailable: <route>`（ERROR）。
- **hooker `f` case 1**：`activity = getThisObject()`；`Q0.R(activity)` 为 false → `proceed()`；否则日志 `privacy repeat blocked consume: route=<field b>` 并返回 `null`。
- **hooker `f` case 0**：`r = proceed()`；`view = getThisObject()`；`ctx = view.getContext()`（null 则跳过）；`ctx = ctx.getApplicationContext() ?? ctx`；写入静态字段 `j.h`；`j.i(view, (String) field b)`；返回 `r`。
- **`Q0.R(activity)`**：判定「当前是否禁止重复消费（转发/保存）」。待读 smali 2468-2507。

### 2.5 社交分（原 `Q0.E`）

- 类 `SocialGraceScoreBean` 的四个无参 boolean getter：`getCanNotSendBeforeReply` / `getCreateSessionLimit` / `getForbidCall` / `getForbidPic`；任一返回类型非 `boolean` → `NoSuchMethodException("Unexpected social score gate <name>")`。
- 逐个 hook → id `new-soul/social-score-<name>`，hooker `f(case=2, m)`。
  - **`f` case 2**：`r = proceed()`；若 `r` 为 `Boolean.TRUE` 且 `chat_remove_social_score_limit` 开启 → 日志 `social score gate bypassed: <方法名>`（Android Log.i）并返回 `FALSE`；否则返回 `r`。
- 对 `uj.c0`、`uj.c0$q`、`ConversationFragment$d1` 的**参数含 SocialGraceScoreBean 的方法**（类名以 `$d1` 结尾则全部方法）`deoptimize`。
- `SocialScoreUtils.b(FragmentManager, SocialScoreUtils$a)` 必须 static + void，否则 `NoSuchMethodException("Unexpected social score dialog signature")`；hook → id `new-soul/social-score-dialog`，hooker `a(case=18)`；对 `SocialScoreUtils` 全部方法 `deoptimize`。
- 汇总日志 `social score hooks: model=<U>; dialog=<V>`；失败 `social score model unavailable` / `social score dialog unavailable`。
- `a` case 18 语义待读。

### 2.6 广场点赞（原 `Q0.F`）

- 保存 `Post.id` 字段、`hasDeclare()`、`getAdResponse()`、`incentiveModel` 字段。
- hook `MainActivity.onCreate(Bundle)` → id `new-soul/square-like-lifecycle`，hooker `a(19)`。
- hook `SquareFloatingButton(Context,AttributeSet,int)` 构造 → id `new-soul/square-like-envelope-create`，hooker `a(20)`。
- hook `SquareFloatingButton.onVisibilityChanged(View,int)` → id `new-soul/square-like-envelope-visibility`，hooker `a(21)`。
- `a` case 19/20/21 语义待读。

### 2.7 复读（原 `Q0.G` / hooker `v`）

- `Q0.G(module, cls, name, paramTypes, isStatic, idSuffix, argIdx)`：`getDeclaredMethod(name,paramTypes)` 必须**返回 void 且 static 标志匹配**，否则 `NoSuchMethodException("Unexpected boundary signature")`；idSuffix = `isStatic ? "repeat.caller" : "repeat.show"`；id = `"new-soul/"+idSuffix`；hooker `v(argIdx=argIdx(?)… )`（构造 `v(p6, p4, idSuffix, p5)`）；成功日志 INFO `<idSuffix>`；随后 `Q0.k(module, m, idSuffix)`。失败日志 `<idSuffix>` + 异常。
- **hooker `v`（`constructor(int a, boolean b, String c, int d)`）**：若 `V.a()`（诊断开关）关闭 → 直接 `proceed()`。否则 `arg = a<0 ? null : getArg(a)`；`y = A.a(b /*isStatic*/, arg)`，`z = y.a`；`!isStatic` 时 `z.c++` 且 `A.b(arg)`；日志 `<c>.enter` = `<Q0.O(getArg(d))>; count=<List?size:-1>; source=<Q0.X()>`（`Q0.r`）；`proceed()`；日志 `<c>.exit` = `z.a()`；`y.close()`。异常 → `<c>.host_failed`（`Q0.s`），并保证 `.exit` 日志与 `close()`。

### 2.8 聊天撤回标记（原 `Q0.K` + `z1` + `j1`）

- 类 `AbsChatDualItem`，ViewHolder 名 = `<AbsChatDualItem>+"$ViewHolder"`；字段 `ViewHolder.itemView`(public)、`AbsChatDualItem.e`(private)。
- 在 `AbsChatDualItem` 的声明方法中匹配 `(ViewHolder, ImMessage, int, List)` 四参方法；**恰好 1 个**，多个抛 `IllegalStateException("Ambiguous chat row binding")`，0 个抛 `NoSuchMethodException("AbsChatDualItem bind")`。
- hook → id `new-soul/chat-recall-mark`，hooker `B(itemView, e, case=0)`；随后 `deoptimize`。语义见 §2.2 的 hooker `B` case 0。
- **`z1`（撤回气泡样式）**：`WeakHashMap<View, y1> a`。
  - `z1.b(view)`：移除缓存并还原视图（移除挂载的 TextView、还原 `bottomToBottom`、还原两个 TextView 文案与可见性）。失败日志（Android Log.w）`recall style reset failed`。
  - `z1.a(view, style, name)`：先 `b(view)`。取 `tv_nikename`、`tv_nikename_flag`、`container` 三个子视图（`z1.e` 按资源名查 id）。缺失则直接返回。
    - `style=="nickname"`：缓存两 TextView 的原文案/可见性；`tv_nikename` 空或不可见时设为 `name` 并可见；`tv_nikename_flag` 设为 `已撤回` 或 `<原flag文案> + " · 已撤回"` 并可见。
    - 否则（默认 `below`）：在 `container` 下方插入新 TextView（文案 `对方已撤回 · 已保留`，10sp，沿用 `tv_nikename` 文字色），用 `RelativeLayout.LayoutParams`（`topToBottom/startToStart = container.id`，`bottomToBottom=0`，top=2dp，bottom=8dp），并把 `container` 的 `bottomToBottom` 设为 -1。
  - `z1.e(view,name)`：`context.getResources().getIdentifier(name,"id","cn.soulapp.android")` → `findViewById`。
  - `z1.g(ctx,name,type)`：同 `getIdentifier`；为 0 抛 `IllegalStateException("Missing Soul resource: "+name)`。
  - `z1.d(ctx,dp)`：`dp * density` 取整。
- **`j1`（动作标记角标）**：`WeakHashMap<View, i1> a`。`j1.b(view, on)`：`on==false` → 从 `view.getOverlay()` 移除 drawable 并复位标志；`on==true` → 懒创建 `i1(density)`、加入 overlay、`setBounds(0,0,w,h)`、注册 `OnLayoutChangeListener`、`invalidate`。`j1.a(view)`：深度优先找第一个 `ImageView`。

## 3. 主安装器索引（已确认）

**规格真相源**（同目录，已落盘，勿重复解析 smali）：

- `INSTALLERS_TAIL.md` —— `SoulHook.onPackageReady` 第 988–2469 行的逐块安装规格。
- `DELEGATES.md` —— `onPackageReady` 尾部委派的 15 个静态安装器 + `installSettingsHook` 规格。

### 3.1 安装总顺序（Features.java 注册顺序的依据）

`onPackageReady` 内的实际执行顺序如下（块 9/10 在委派之前，故反撤回最先）：

| 序 | 原实现 | 功能域 | 目标 | Hook ID |
| --- | --- | --- | --- | --- |
| 0 | `Application.onCreate` | 调试初始化 | `android.app.Application` | `new-soul/debug-init` |
| 1 | `MainActivity.onCreate(Bundle)` / `onNewIntent(Intent)` | 冷/热启动标记 | `...startup.main.MainActivity` | `new-soul/cold-launch-flag` / `new-soul/relaunch-flag` |
| 2 | `tc.e$a.j(String)` | 热启动闪屏门 | `tc.e$a` + `ea.a.a()` | `new-soul/hot-splash-gate` |
| 3 | `MainScheduler.v()` / `s()` | 跳过启动广告 | `...startup.main.MainScheduler` | `new-soul/skip-startup-ad` |
| 4 | `BaseQuickAdapter` 数据变更方法 | 信息流数据过滤 | `...squarepost.SquareAdapter` | `new-soul/feed-data/<name>/<n>` |
| 5 | `fa.a` / `jb.e` / `mb.d` / `lb.h` | 请求方法预载 | — | 由 `j.h` 内部产生 |
| 6 | 18 组广告 View 构造器 | 广告屏蔽 | 见 INSTALLERS_TAIL §块4a | （无 ID，仅 PROTECTIVE） |
| 7 | `View.setVisibility(int)` | 广告屏蔽 | `android.view.View` | （无 ID） |
| 8 | `DiceFingerMsg(int)` | 小游戏骰子/猜拳 | `imlib.msg.chat.DiceFingerMsg` | `new-soul/game-message-result` |
| 9 | `ChatManager.O(List)` | **反撤回**（私聊） | `imlib.ChatManager` | `new-soul/chat-anti-recall` |
| 10 | `RoamHandler.a(ImMessage)` | **反撤回漫游** | `imlib.handler.RoamHandler` | `new-soul/chat-anti-recall-roam` |
| 11 | `g1.a` | 自己消息撤回时限 | `WithDrawHandler.d(ImMessage)` | `new-soul/own-recall-age-read` / `new-soul/own-recall-entry` |
| 12 | `Q0.x` | 隐藏正在输入 + 匹配音乐 | `ChatManager.o0/p0`、`SoulMusicHelper` | `new-soul/hide-typing` / `new-soul/match-music-<name>` |
| 13 | `F.h` | 语音库 + 复读 + 消息转发 | `ConversationFragment`、`uj.c0`、`ChatLongClickPopUp` | `new-soul/voice-library-*`、`new-soul/repeat-*`、`new-soul/action-marks-chat` |
| 14 | `Q0.z` | 礼物聊天限制 | `ChatLimitModel`、`Conversation`、`ChatUtils`、`LoveBellProConfig` | `new-soul/gift-chat-*`、`new-soul/bell-gift-popup-config` |
| 15 | `Q0.E` | 社交分限制 | `SocialGraceScoreBean`、`SocialScoreUtils` | `new-soul/social-score-*` |
| 16 | `Q0.D` | 隐私预览 / 重复消费 | `PrivacyProtectPreviewActivity`、`SnapChatActivity` | `new-soul/privacy-*` |
| 17 | `Q0.A` | **无水印** ✅ 已实现 | `ImageDownloader.f`、`utils.h2.b` | `new-soul/download_*` |
| 18 | `Q0.B` | 帖子复制 + 动作标记 | `SeedsShareDialogFragment` 等 | `new-soul/post-copy-*`、`new-soul/action-marks-post` |
| 19 | `Q0.C` | 帖子分享权限 | `SeedsShareDialogFragment.r0()` | `new-soul/post-share-permission` |
| 20 | `u1.g` | 资料页书签（插眼） | `UserHomeActivity.y(String,String)` 等 | `new-soul/profile-bookmark-*` |
| 21 | `p0.e` | 资料页伪装 | `UserHomeHeaderHelper`、`RevisionProfileProvider`、`MetaFollowFansContainerView` | `new-soul/follow-disguise-*`、`new-soul/days-*` |
| 22 | `B2.c` | 信息流过滤 | `SquareAdapter.d/j/h` | `new-soul/feed-filter-*` |
| 23 | `Q0.F` | 广场点赞 / 信封 | `Post`、`MainActivity`、`SquareFloatingButton` | `new-soul/square-like-*` |
| 24 | `S.a = module` | 配置文件回传 | `Activity.onActivityResult` | `new-soul/config-file-result` |
| 25 | `o.c` | 底部设置入口 | `MainActivity.onCreate(Bundle)` | `new-soul/bottom-settings-entry` |
| 26 | `Activity.onResume` | 生命周期 | `android.app.Activity` | `soul-demo/activity-onResume` |
| 27 | `installSettingsHook` | 设置 UI 注入 | `H5Activity.m1(boolean)` | `soul-demo/private-h5-m1` |

### 3.2 hooker 分派器 `a`（原 `dev.local.newsoul.a`，`intercept` 按 `case` 分派）

| case | 语义 | 关键常量 |
| --- | --- | --- |
| 0 | `View.setVisibility` 广告隐藏 | — |
| 2 | 底部设置入口观察 | `main_bottom_tab_bar`、`bottom settings observation failed` |
| 3 | 匹配音乐静音背景音 | `match_mute_background_music`、`soul match background music blocked` |
| 4 | 小游戏结果 | `game message constructed with selected result=` |
| 5 | 礼物聊天 model | `chat_limit_session_type`、`chat_remove_gift_gate` |
| 6 | 礼物聊天 session | `chat gate observed; type=`、`; bypass=` |
| 7 | 铃声礼物弹窗 | `chat_remove_bell_gift_gate`、`special bell gift popup bypassed` |
| 8 | 礼物聊天 dialog | `gift chat dialog suppressed` |
| 9 | 私聊防撤回 | `chat_anti_recall`、`recall interception failed` |
| 10 | 漫游防撤回 | `recall retained; source=roam`、`roam recall interception failed` |
| 11 | 复读上下文 | `repeat` |
| 12 | 配置文件回传（导出/导入） | `正在保存记录…`、`已取消导出`、`正在导出配置…`、`正在读取配置…`、`未取得配置文件，请重新选择` |
| 13 | debug-init | — |
| 15 | 自己消息撤回时限 | `own recall local age check bypassed` |
| 16 | 隐私照片预览 | `limitViewTimes`、`privacy_allow_screenshot/download/repeat`、`privacy photo preview: download=` |
| 17 | snap 预览 | `privacy snap preview: repeat=` |
| 18 | 社交分对话框 | `chat_remove_social_score_limit`、`social score dialog suppressed` |
| 19 | 广场点赞生命周期 | `square like lifecycle registered` |
| 20 | 广场信封创建 | — |
| 21 | 广场信封可见性 | — |

### 3.3 hooker `b`（原 `dev.local.newsoul.b`，`(Field,Field,Field,Method,int)`）

- `case 0` = 广告拦截（信息流广告槽位）：`getArg(0)` → 首参为 `Reference`/`Context`；`j.f.invoke(c.get(thisObj))` 取槽位名，`j.e.get(thisObj)` 取缓存名，缺失则 `j.c.get(slot)`；命中且 `j.b(ctx,name)` 为真 → `j.i.post(new g(method, d.get(thisObj), 0))`，日志 `ad blocked: <name> slot=<slot>`，返回 `null`；否则 `proceed()`。
- `case 1`（default）= 帖子文案复制：`getArg(0)` 的 `b` 字段 `== -0x35efa` 才处理；取 `c.get(thisObj)`（Post）→ `d.get(post)`（content）；写入剪贴板（`ClipData.newPlainText("帖子文案", content)`），校验后 `Log.i("SoulHook102","post text copied; chars=<n>; verified=<b>")`、Toast `已复制文案`、`method.invoke(thisObj)`；空内容 → Toast `该帖子暂无可复制文案` 返回 `Boolean.FALSE`；异常 → `Log.e("post text copied failed")` + Toast `复制失败，请重新打开分享页`；无剪贴板 → `IllegalStateException("Clipboard unavailable")`。

## 4. 剩余实现清单

按 §3.1 顺序，尚未落地的功能域（✅ 已完成：`framework` 原语、宿主适配、`feature/download/NoWatermarkFeature`、`feature/startup/StartupToastFeature`）：

1. 反撤回（块 9/10）+ 撤回时限（`g1.a`）+ 撤回气泡 UI（`z1`/`y1`/`j1`）
2. 隐藏正在输入 + 匹配音乐（`Q0.x`）
3. 语音库 + 复读 + 消息转发（`F.h`）
4. 礼物聊天限制（`Q0.z`）
5. 社交分（`Q0.E`）
6. 隐私（`Q0.D` + `Q0.J`/`Q0.R`）
7. 帖子复制 + 动作标记（`Q0.B`/`Q0.I`）+ 分享权限（`Q0.C`）
8. 资料页书签（`u1.g`）
9. 资料页伪装（`p0.e`）
10. 信息流过滤（`B2.c`）
11. 广场点赞（`Q0.F`）
12. 广告屏蔽（块 4/5/6/7 + `j`）
13. 启动 / 主页（块 0–3）
14. 小游戏（块 8）
15. 配置回传（`S`）、底部设置入口（`o.c`）、设置 UI 注入（`installSettingsHook`）

另需补的 framework 原语：`Hooks.installRepeatBoundary`(G)、`installPostActionMarks`(I)、`installPrivacyRepeatGuard`(J)、`installChatRecallMark`(K)、`ValueCodec`(L/N/O/P/o/p/q/R)、`Q0.v`/`Q0.X`。

**作者专属部分（不实现）**：蓝奏云自更新、Telegram 群、打赏二维码、RSA 诊断日志导出（`Q0.M`/`Q0.Q`）。

## 5. 待核实的实现细节

- `Q0.R`（2468–2507）：隐私「禁止重复消费」判定。
- `Q0.v`（5851–5944）、`Q0.X`（4154–4302）：复读诊断日志上下文。
- `a.1.smali` case 16 / 17 / 18 / 19 / 20 / 21 的逐行实现。
- hooker `f`（`(int,Object)`）case 0/1/2 的逐行实现（隐私预览、重复守卫、社交分）。
- hooker `f1`（`(AccessibleObject×3, Executable, int)`）、`k1`（`(Field,int)`）、`s1`、`z2`、`l0`/`m0`/`n0`（资料页伪装）的逐行实现。
- 大文件未精读：`Z0.smali`（设置 UI，116KB）、`F.smali` 其余段、`I2`/`o`/`u1`/`w2`/`d1`/`x1`、`j.1.smali`（广告助手）。