# SoulHook 委派安装器规格（DELEGATES）

> 本文件由对 `re_work\smali_out\dev\local\newsoul\` 下 smali 的静态精读整理而成。
> 所有字符串常量**逐字保留**，不做意译。发现于 `SoulHook.onPackageReady` 尾部依次委派的静态安装方法。
> 通用日志 TAG：`SoulHook102`。模块日志通道：`XposedModule->log(int, String, String[, Throwable])`；Android 日志通道：`android.util.Log`。

## 目录

- [0. 通用约定](#0-通用约定)
- [1. `g1.a(XposedModule, ClassLoader)` — 自己消息撤回时限](#1-g1axposedmodule-classloader--自己消息撤回时限)
- [2. `Q0.x(...)` — 隐藏正在输入 + 匹配音乐](#2-q0x--隐藏正在输入--匹配音乐)
- [3. `Q0.z(...)` — 礼物聊天限制](#3-q0z--礼物聊天限制)
- [4. `Q0.E(...)` — 社交分数限制移除](#4-q0e--社交分数限制移除)
- [5. `Q0.D(...)` — 隐私照片预览](#5-q0d--隐私照片预览)
- [6. `Q0.A(...)` — 去水印下载](#6-q0a--去水印下载)
- [7. `Q0.B(...)` — 帖子复制](#7-q0b--帖子复制)
- [8. `Q0.C(...)` — 帖子分享权限控制](#8-q0c--帖子分享权限控制)
- [9. `Q0.F(...)` — 广场点赞/信封](#9-q0f--广场点赞信封)
- [10. `F.h(XposedModule, ClassLoader)` — 语音库 + 复读 + 消息转发](#10-fhxposedmodule-classloader--语音库--复读--消息转发)
- [11. `u1.g(XposedModule, ClassLoader)` — 资料页书签](#11-u1gxposedmodule-classloader--资料页书签)
- [12. `p0.e(XposedModule, ClassLoader)` — 资料页伪装](#12-p0exposedmodule-classloader--资料页伪装)
- [13. `B2.c(XposedModule, ClassLoader)` — 广场信息流过滤](#13-b2cxposedmodule-classloader--广场信息流过滤)
- [14. `o.c(XposedModule, ClassLoader)` — 底部设置入口](#14-ocxposedmodule-classloader--底部设置入口)
- [15. `S.a` 字段与配置文件回传](#15-sa-字段与配置文件回传)
- [16. `SoulHook.installSettingsHook` 与 lambda](#16-soulhookinstallsettingshook-与-lambda)
- [17. 参考：hooker 分派器 `a` 的 case 表](#17-参考hooker-分派器-a-的-case-表)
- [18. 未确定项](#18-未确定项)

---

## 0. 通用约定

- **Hook 安装链**：`module.hook(Executable)` → `setId(String)` → `setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE)` → `intercept(Hooker)`。
- **hooker 构造参数携带 case 整数**：如 `Ldev/local/newsoul/a;-><init>(I)V`，`intercept` 内用 `packed-switch` 按该 int 分派。case 值即 `a`（小写）实例字段 `a:I`。
- **大小写文件约定**（Windows 不敏感）：小写类存于 `*.1.smali`（`a.1.smali`=类 `a`），大写类存于 `*.smali`（`a.smali`=类 `A`）。
- **`log(6, TAG, msg)`** 为模块日志 error 级别（`XposedModule.log`）；`Log.i/e/w(TAG, msg)` 为 Android 日志。

---

## 1. `g1.a(XposedModule, ClassLoader)` — 自己消息撤回时限

- **文件**：`g1.1.smali`（类 `dev/local/newsoul/g1`，abstract）
- **功能名**：own recall time limit（自己消息撤回时限）
- **TAG**：`"SoulHook102"`

**目标宿主（完整类名字符串，逐字）**
| 类名 | 成员 | 签名 |
|---|---|---|
| `cn.soulapp.imlib.msg.ImMessage` | 方法 `getLocalTime` | getDeclaredMethod，无参 |
| `cn.soulapp.imlib.msg.ImMessage` | 方法 `getFrom` | getMethod，无参 |
| `cn.soulapp.imlib.msg.ImMessage` | 方法 `isAnonymityChat` | getMethod，无参 |
| `cn.soulapp.imlib.j0` | 方法 `g` | getDeclaredMethod，无参 |
| `lf.b` | 方法 `b` | getDeclaredMethod，无参 |
| `cn.soulapp.android.component.chat.helper.WithDrawHandler` | 方法 `d` | getDeclaredMethod，参数 `ImMessage` |

**Hook 1**
- ID：`new-soul/own-recall-age-read`
- hooker：`Ldev/local/newsoul/a;-><init>(I)V`，case = `0xf`（15）
- 作用目标：`ImMessage.getLocalTime`
- ExceptionMode：`PROTECTIVE`

**Hook 2**
- ID：`new-soul/own-recall-entry`
- hooker：`Ldev/local/newsoul/f1;-><init>(Ljava/lang/reflect/AccessibleObject;Ljava/lang/reflect/AccessibleObject;Ljava/lang/reflect/AccessibleObject;Ljava/lang/reflect/Executable;I)V`，I = `0x0`
- 作用目标：`WithDrawHandler.d(ImMessage)`
- 随后对目标 `deoptimize(p1)`

**断言**：无显式签名断言（仅 catchall）。

**静态字段赋值**：`sput-boolean 1 → Ldev/local/newsoul/g1;->a:Z`（g1.a 标记已安装）

**日志**
- 成功（Android Log.i）：`"own recall time limit hook installed"`
- 失败（模块 log error）：`"own recall time limit unavailable"`（附 throwable）

---

## 2. `Q0.x(...)` — 隐藏正在输入 + 匹配音乐

- **文件**：`Q0.smali`（类 `dev/local/newsoul/Q0`，abstract），`x` 约 5960–6203 行

### 功能 1：隐藏正在输入（hide typing）

**目标宿主**
| 类名 | 成员 |
|---|---|
| `lf.b` | 方法 `"b"` |
| `cn.soulapp.imlib.msg.ImMessage` | 方法 `"getChatMessage"`、`"getMsgType"` |
| `cn.soulapp.imlib.msg.chat.ChatMessage` | 方法 `"getMsgType"` |
| `cn.soulapp.imlib.ChatManager` | 方法 `p0(ImMessage, boolean)`、`o0(ImMessage)` |

- Hook ID：`new-soul/hide-typing`
- hooker：`Ldev/local/newsoul/e;-><init>(Ljava/lang/reflect/Method;Ljava/lang/reflect/Method;Ljava/lang/reflect/Method;)V`
  - 参数：`ImMessage.getMsgType(ImMessage)`、`ImMessage.getChatMessage()`、`ChatMessage.getMsgType()`
- `deoptimize(o0)` 与 `deoptimize(p0)`
- 静态字段：`Q0.b=true`
- 日志：成功 `"typing command control installed"`；失败 `"typing control unavailable"`

### 功能 2：匹配音乐（match music）

**目标宿主**：`cn.soulapp.android.component.planet.soulmatch.utils.SoulMusicHelper`，方法 `k()`、`onPrepared(MediaPlayer)`

- 逐个 hook，Hook ID = `new-soul/match-music-<name>`（name 为方法名）
- hooker：`Ldev/local/newsoul/a;-><init>(I)V`，case = `0x3`（3）
- 各目标 `deoptimize`；随后 `deoptimize(SoulMusicHelper.l())`
- 静态字段：`Q0.c=true`
- 日志：成功 `"soul match music control installed"`；失败 `"match music control unavailable"`

---

## 3. `Q0.z(...)` — 礼物聊天限制

- **文件**：`Q0.smali`，`z` 约 6334 行至文件末

**目标宿主与 Hook**
| 类名 | 成员 | Hook ID | hooker | case |
|---|---|---|---|---|
| `cn.soulapp.android.lib.common.bean.ChatLimitModel` | 字段 `"getType"`（`i`）、`"getItemIdentity"`（`j`）、`isLimit` | `new-soul/gift-chat-model` | `Ldev/local/newsoul/a;-><init>(I)V` | `0x5`（5） |
| `cn.soulapp.imlib.Conversation` | 方法 `getIntExt(String)` | `new-soul/gift-chat-session` | `Ldev/local/newsoul/a;-><init>(I)V` | `0x6`（6） |
| `ChatUtils` | static void 方法，参数 `(ChatLimitModel, Conversation, LimitGiftDialog$OnStartGiftSendListener, String, String, boolean)` | `new-soul/gift-chat-dialog` | `Ldev/local/newsoul/a;-><init>(I)V` | `0x8`（8） |
| `cn.soulapp.android.component.home.lovebell.bean.LoveBellProConfig` | 方法 `getShowPopup()` | `new-soul/bell-gift-popup-config` | `Ldev/local/newsoul/a;-><init>(I)V` | `0x7`（7） |

- 静态字段：`Q0.e=true`（model）、`Q0.g=true`（session）、`Q0.h=true`（dialog，仅当 e 成功）、`Q0.f=true`（bell）
- 断言（逐字）：
  - dialog 若匹配到多个 static void 方法：`IllegalStateException("ambiguous gift dialog entry")`
  - `getShowPopup()` 返回类型非 Boolean/boolean：`NoSuchMethodException("unexpected getShowPopup return type")`
- 附加委派：`Q0->j(...)` 处理 `LoveBellProGiftDialog` 内部类中参数含 config 的方法；随后 `Q0->l(..., "BaseConversationStyleProcess", "checkLimitOpt")`、`Q0->l(..., "MsgLimitManager", null)`、`Q0->l(..., "ConversationFragment", "v4")`
- 日志：成功（Android Log.i）`"gift chat hooks: model=...; session=...; dialog=...; bell=..."`

---

## 4. `Q0.E(...)` — 社交分数限制移除

- **文件**：`Q0.smali`，`E` 约 707–1075 行

**目标宿主（第一段）**：`cn.soulapp.android.component.chat.bean.SocialGraceScoreBean`，方法 `getCanNotSendBeforeReply`、`getCreateSessionLimit`、`getForbidCall`、`getForbidPic`

- 逐个 hook，Hook ID = `new-soul/social-score-<methodName>`
- hooker：`Ldev/local/newsoul/f;-><init>(ILjava/lang/Object;)V`，I = `2`
- 断言（逐字）：若某方法返回类型非 boolean：`NoSuchMethodException("Unexpected social score gate <name>")`
- `deoptimize` 以下类中含该 bean 参数（或 `$d1`）的方法：`uj.c0`、`uj.c0$q`、`cn.soulapp.android.component.chat.fragment.ConversationFragment$d1`
- 静态字段：`Q0.U=true`

**目标宿主（第二段）**：`cn.soulapp.android.component.planet.soulmatch.utils.SocialScoreUtils`，方法 `b(FragmentManager, SocialScoreUtils$a)`

- 断言（逐字）：须为 static 且返回 void，否则 `NoSuchMethodException("Unexpected social score dialog signature")`
- Hook ID：`new-soul/social-score-dialog`
- hooker：`Ldev/local/newsoul/a;-><init>(I)V`，case = `0x12`（18）
- `deoptimize` 该类全部方法
- 静态字段：`Q0.V=true`

**日志**
- 成功（Android Log.i）：`"social score hooks: model=" + U + "; dialog=" + V`
- 失败（模块 log error）：`"social score model unavailable"` / `"social score dialog unavailable"`

---

## 5. `Q0.D(...)` — 隐私照片预览

- **文件**：`Q0.smali`，`D` 约 606–707 行

**目标宿主**：`cn.soulapp.android.component.chat.snapchat.PrivacyProtectPreviewActivity`，方法 `init(Bundle)`

- Hook ID：`new-soul/privacy-photo-preview`
- hooker：`Ldev/local/newsoul/a;-><init>(I)V`，case = `0x10`（16）
- 静态字段：`Q0.S=p0`、`Q0.T=p1`（保存 module 与 classLoader）；`Q0.P=true`
- 日志：成功经 `Q0->w(String)` → `"privacy photo controls installed"`；失败（模块 log error）`"privacy photo controls unavailable"`
- 后续：`Q0->J(classLoader, PrivacyProtectPreviewActivity, "G", "privacy")` → Q；`Q0->J(classLoader, "cn.soulapp.android.component.chat.snapchat.SnapChatActivity", "C", "snap")` → R

---

## 6. `Q0.A(...)` — 去水印下载

- **文件**：`Q0.smali`，`A` 约 123–253 行

**目标宿主**
| 功能 | 目标签名 | Hook ID | 静态字段 |
|---|---|---|---|
| 图片去水印 | `ImageDownloader.f(String, boolean, boolean, ImageMarkProcessor, b)` | `download_image_no_watermark` | `Q0.L=true` |
| 视频去水印 | `square.utils.h2.b(String, Context, long, boolean)` | `download_video_no_watermark` | `Q0.M=true` |

- 均经助手 `Q0->H(...)` 挂载
- 日志：失败（模块 log error）`"image watermark control unavailable"` / `"video watermark control unavailable"`

---

## 7. `Q0.B(...)` — 帖子复制

- **文件**：`Q0.smali`，`B` 约 253–464 行

**目标宿主**
| 类名 | 成员 |
|---|---|
| `SeedsShareDialogFragment` | 字段 `"E"` |
| `BaseSeedsDialogFragment` | 字段 `"q"` |
| `<BaseSeedsDialogFragment>$Operate` | 字段 `"a"` |
| `square.post.bean.Post` | 字段 `"content"` |

**Hook**
- Hook ID：`new-soul/post-copy-click`
  - hooker：`Ldev/local/newsoul/b;-><init>(Ljava/lang/reflect/Field;Ljava/lang/reflect/Field;Ljava/lang/reflect/Field;Ljava/lang/reflect/Method;I)V`，I = `1`
  - 作用目标：`P0(<Operate>, View, int)`
- Hook ID：`new-soul/post-copy-action`
  - hooker：`Ldev/local/newsoul/f1;-><init>(Ljava/lang/reflect/AccessibleObject;Ljava/lang/reflect/AccessibleObject;Ljava/lang/reflect/AccessibleObject;Ljava/lang/reflect/Executable;I)V`，I = `1`
  - 作用目标：`initViews(View)`
- 构造器引用：`<init>(int, String, boolean)`；方法引用：`initViews(View)`、`P0(<Operate>, View, int)`、`dismiss()`；经助手 `Q0->I(...)`
- `deoptimize(P0)`
- 静态字段：`Q0.N=true`
- 日志：成功 `"post copy hooks installed"`；失败 `"post copy hooks unavailable"`

---

## 8. `Q0.C(...)` — 帖子分享权限控制

- **文件**：`Q0.smali`，`C` 约 464–606 行

**目标宿主**：`SeedsShareDialogFragment`

- 字段 `"E"` 类型须为 `square.post.bean.Post`，否则 `NoSuchFieldException("Unexpected share post field")`
- 方法 `r0()` 须返回 void 且 private，否则 `NoSuchMethodException("Unexpected share permission signature")`
- 方法 `initViews(View)`
- Hook ID：`new-soul/post-share-permission`
  - hooker：`Ldev/local/newsoul/k1;-><init>(Ljava/lang/reflect/Field;I)V`，I = `0`
  - 作用目标：`r0()`
- `deoptimize(initViews)`
- 静态字段：`Q0.O=true`
- 日志：成功 `"post share control installed"`；失败 `"post share control unavailable"`

---

## 9. `Q0.F(...)` — 广场点赞/信封

- **文件**：`Q0.smali`，`F` 约 1075–1274 行

**目标宿主**：`square.post.bean.Post`

- 静态字段：`Q0.Z=Post`；`Q0.a0` = 字段 `"id"`；`Q0.b0` = 方法 `"hasDeclare"`；`Q0.c0` = 方法 `"getAdResponse"`；`Q0.d0` = 字段 `"incentiveModel"`
- 经助手 `Q0->y(loader, Post)`

**Hook**
| Hook ID | hooker | case | 作用目标 |
|---|---|---|---|
| `new-soul/square-like-lifecycle` | `Ldev/local/newsoul/a;-><init>(I)V` | `0x13`（19） | `MainActivity.onCreate(Bundle)` |
| `new-soul/square-like-envelope-create` | `Ldev/local/newsoul/a;-><init>(I)V` | `0x14`（20） | `SquareFloatingButton.<init>(Context, AttributeSet, int)` |
| `new-soul/square-like-envelope-visibility` | `Ldev/local/newsoul/a;-><init>(I)V` | `0x15`（21） | `SquareFloatingButton.onVisibilityChanged(View, int)` |

- 静态字段：`Q0.Y=true`
- 日志：失败（Android Log.e 与模块 log error 同文本）`"square like unavailable"`

**辅助 `Q0.y(ClassLoader, Class)`（被 F 调用）**
- 加载：`androidx.recyclerview.widget.RecyclerView`→C、`SquareAdapter`→D、`io.reactivex.functions.Consumer`→E、`cn.soulapp.android.component.square.post.b1`→F（ctor `<init>(Class)`）
- 方法引用：`b1.h(int)`→I、`RecyclerView.getAdapter`→G、`SquareAdapter.getData`→H、`notifyDataSetChanged`→K、`I.getReturnType().getMethod("subscribe", Consumer, Consumer)`→J
- 静态字段：`Q0.B=true`
- 失败日志：`Log.e("loaded post likes unavailable")`

---

## 10. `F.h(XposedModule, ClassLoader)` — 语音库 + 复读 + 消息转发

- **文件**：`F.smali`（类 `dev/local/newsoul/F`，final），`h` 约 1375–2132 行
- **静态字段**：`o:Z`（repeat installed）、`p:Z`（message forward）

### 内部初始化字段
| 字段 | 引用 |
|---|---|
| `l` | `ChatLongClickPopUp$a` 构造器 |
| `c` | `ChatLongClickPopUp$a.b()` |
| `b` | `imlib.j0.g()` |
| `d` | `ChatMessage.create(String)` |
| `e` | `ImMessage.createChatSendMsg(ChatMessage, String)` |
| `f` | `ChatMessage.setMsgType(int)` |
| `g` | `ChatMessage.setMsgContent(Serializable)` |
| `h` | `y4.u(ChatMessage)` |
| `i` | `y4.p1(ImMessage)`（setAccessible） |
| `j` | `y4.Y1(String, IMediaKeyBoard, ImUserBean)` |
| `k` | `cn.soulapp.android.chat.utils.f.a(ImMessage)` |

### 10.1 语音库（voice library）
- 目标：`ConversationFragment` 方法 `onResume()` / `onPause()`
- Hook ID：`new-soul/voice-library-entry`（hooker `Ldev/local/newsoul/N2;-><init>(Ldev/local/newsoul/d3;I)V`，I = `0`，onResume）；`new-soul/voice-library-close`（N2，I = `1`，onPause）
- `deoptimize(onResume/onPause)`
- 静态字段：`d3->g=true`；`F.m` = new d1，`d1->e(module, loader)` + `u=true`；`F.p=d1.u`
- 日志：成功 `"voice library installed; local storage and native audio upload"`；失败 `"voice library unavailable"`；`V.i("message.forward")`

### 10.2 复读（repeat）—— 已按 smali 逐行校正
- 目标方法（**全部经 `u0 = cn.soulapp.android.component.chat.utils.u0` 与 `ConversationFragment` 类加载器反射**）：
  - `u0.p(ImMessage, ConversationFragment, Context, uj.c0, View, int, View, ChatLongClickPopUp$a, int)` → `new-soul/repeat-click`（hooker `C`，I = `0`）；`getArg(7)` 是菜单项对象，调 `ChatLongClickPopUp$a.b()` 得 action int
  - `u0.k(ConversationFragment, View, ImMessage)` → `new-soul/repeat-menu`（hooker `C`，I = `1`；走 C 的 default 分支）
  - `ConversationFragment.ac(View, ImMessage, int)` → `new-soul/repeat-context`（hooker `a`，case = `0xb`（11））
  - `ChatLongClickPopUp` 中「返回该类型且参数为 `List`」的方法 → `new-soul/action-marks-chat`（hooker `D`，I = `0`）
  - 断言（逐字）：重复则 `IllegalStateException("Ambiguous menu renderer")`；无则 `NoSuchMethodException("Menu renderer")`
- 附加埋点：`Q0->k(module, m, "repeat.click"/"repeat.menu"/"repeat.context"/"repeat.renderer")`；`Q0->G(module, ConversationFragment, "ac", {View, ImMessage, int}, true, 1, -1)`（id `new-soul/repeat.caller`）与 `Q0->G(module, u0, "y", {ConversationFragment, List, View, ImMessage, int}, false, 3, 1)`（id `new-soul/repeat.show`）。`Q0.G` 的 hooker `v` 仅在诊断启用（`V.a()`，恒 false）时动作 → 等价 no-op 埋点。
- 静态字段：`F.o=true`
- 日志：`"repeat native menu installed; text/image/audio"` / `"chat action marks unavailable"` / `"message forward unavailable"`

---

## 11. `u1.g(XposedModule, ClassLoader)` — 资料页书签

- **文件**：`u1.1.smali`（类 `dev/local/newsoul/u1`，abstract）
- **静态字段**：`a:Handler`、`b:ExecutorService`、`c:WeakHashMap`、`d:Method`、`e:Method`、`f:Z`

**目标宿主**
| 类名 | 成员 |
|---|---|
| `cn.soulapp.imlib.j0` | 方法 `g()` → `u1.d` |
| `UserHomeActivity` | 方法 `y(String, String)` → `u1.e` |
| `SeedsShareDialogFragment` | 字段 `"D"`, `"E"`, `"C"`, `"y"` |
| `BaseSeedsDialogFragment` | 字段 `"q"` |
| `<BaseSeedsDialogFragment>$Operate` | 构造器 `(int, String, boolean)`；字段 `"a"`, `"b"`, `"f"` |
| `TypeAdapter$a` | 字段 `"c"`, `"a"`；方法 `a(<Operate>)` |
| `TypeAdapter` | — |
| `square.a0` | — |

**Hook**
| Hook ID | hooker | 作用目标 | deoptimize |
|---|---|---|---|
| `new-soul/profile-bookmark-icon` | `Ldev/local/newsoul/e;-><init>(Ljava/lang/reflect/AnnotatedElement;Ljava/lang/reflect/AccessibleObject;Ljava/lang/Object;I)V`，I = `2` | `TypeAdapter$a.a(<Operate>)` | 该方法 + 全部 `TypeAdapter` 方法 |
| `new-soul/profile-bookmark-click` | `Ldev/local/newsoul/k1;-><init>(Ljava/lang/reflect/Field;I)V`，I = `1` | `P0(Operate, View, int)` | — |
| `new-soul/profile-bookmark-menu` | `Ldev/local/newsoul/s1;-><init>(...多个 reflect 参数...)V`（传入 D,E,C,q,a,b,f,y 等字段 + ctor） | `initViews(View)` | `initViews`、`P0`、全部 `square.a0` 方法 |

- 静态字段：`u1.f=true`
- 日志：成功 `V.i("profile.bookmarks")` 与 `Log.i("SoulHook102", "profile bookmark menu and navigation installed")`；失败 `Log.e("profile bookmarks unavailable")`

**辅助方法**
- `d()`：取当前用户名，空则 `IllegalStateException("请先登录 Soul")`
- `e(Activity, Exception)`：Toast `"操作失败，原有记录已保留"`
- `h(String)`：账号切换检测，`IllegalStateException("账号已切换，请重新打开页面")`

---

## 12. `p0.e(XposedModule, ClassLoader)` — 资料页伪装

- **文件**：`p0.1.smali`（类 `dev/local/newsoul/p0`，abstract）
- **静态字段**：`a,b,c,d,e:Z`、`f:WeakHashMap`、`g:XposedModule`、`h:HashSet`、`i,j,k:Field`、`l,m:Method`

**目标宿主**
| 类名 | 成员 |
|---|---|
| `component.home.api.user.user.bean.UserFollowCount` | 字段 `followNum`→i、`beFollowNum`→j、`beViewNum`→k |
| `client...utils.m2` | 方法 `f(int)`→l、`e(int)`→m |
| `component.home.me.UserHomeHeaderHelper` | 字段 M, j, l, n, o, q, r；方法 `setUserFollowData(<UserFollowCount>)` |
| `RevisionProfileProvider` | 方法 `S1(User, ViewHold)`；字段 `"o"`, `"q"`, `"g0"`, `"h0"`, `"b"` |
| `MetaFollowFansContainerView` | 方法 `"w"`；字段 `"e"`, `"g"`, `"q"`, `"r"`, `"k"` |

**Hook**
- `new-soul/follow-disguise-header`：hooker `Ldev/local/newsoul/l0;-><init>(Field;Field;Field;Field;Field;Field;Field)V`，作用目标 `setUserFollowData(<UserFollowCount>)`；`deoptimize` 该方法 + 全部方法；静态 `p0.c=true`；`V.i("profile.disguise.header")`
- `p0->g(...)` 三次（revision / meta）→ 静态 `a, b`；`p0->f(...)` 两次（revision `V`、meta `y`）→ 静态 `d, e`
- 辅助 `f(...)`：Hook ID `new-soul/days-revision` 或 `new-soul/days-meta`；hooker `Ldev/local/newsoul/n0;-><init>(Z, Method, Field, Field, Field, String)V`；尾注 `V.i("profile.disguise.<name>")`；失败 `p0->c(name, throwable)`
- 辅助 `g(...)`：Hook ID `new-soul/follow-disguise-<p2>`；hooker `Ldev/local/newsoul/m0;-><init>(Z, Method, String, Field×6)V`；尾注 `V.i("profile.disguise.<p2>")`；失败 `p0->c(...)`

**日志助手**
- `c`：模块 log error `"follow disguise <name> unavailable"` + Android `Log.e` 同文本
- `j`：`"follow disguise <name> failed"`
- `k`：模块 log `"follow disguise <name>"`
- 成功：`log(4, ...) "days disguise registered: revision=..;meta=.."` 与 `"follow disguise registered: revision=..;meta=..;header=.."`

**相关类**：`UserFollowCount`、`com.soul.component.componentlib.service.user.bean.User`（字段 `registerDay`）等

---

## 13. `B2.c(XposedModule, ClassLoader)` — 广场信息流过滤

- **文件**：`B2.smali`（类 `dev/local/newsoul/B2`，abstract）
- **静态字段**：`a:Z`, `i:Z`, `o:Z`, `p:I`, `l:Class`, `m,n:Field`, `g:String`, `h:k0`, `b,c,d,e,f:Field`, `j:Handler`, `k:WeakHashMap`

**目标宿主**：`cn.soulapp.android.square.post.bean.Post`
- `l=Post`；`m=Post.postAuthorModel`；`n=postAuthorModel.gender`；`b=content`、`c=title`、`d=contentTitle`、`e=tags`；`f=cn.soulapp.android.square.bean.Tag.name`；`a=true`

**Hook**（目标类 `SquareAdapter`）
| 方法 | Hook ID | hooker | I |
|---|---|---|---|
| `d(BaseSquareViewHolder, Object)` | `new-soul/feed-filter-bind` | `Ldev/local/newsoul/z2;-><init>(Ljava/lang/Class;I)V` | `0` |
| `j(BaseSquareViewHolder)` | `new-soul/feed-filter-recycle` | `z2` | `1` |
| `h(BaseSquareViewHolder)` | `new-soul/feed-filter-attach` | `z2` | `2` |

- 静态字段：`B2.i=true`
- 日志：成功 `Log.i("square feed filter ready")`；字段异常 `Log.w("feed keyword fields unavailable")`；失败 `Log.e("square feed filter unavailable")`
- SharedPreferences 键：`feed_keywords_enabled_v1`、`feed_keywords_v1`、`feed_filter_enabled`、`feed_filter_gender`、`feed_filter_keep_unknown`；辅助类 `k0`

---

## 14. `o.c(XposedModule, ClassLoader)` — 底部设置入口

- **文件**：`o.1.smali`（类 `dev/local/newsoul/o`，abstract）
- **静态字段**：`a:Z`、`b:[String]`（= `main_tab_planet,main_tab_square,main_tab_msg,main_tab_me`）、`c:WeakHashMap`、`d:Method`

**目标宿主**：`cn.soulapp.android.component.startup.main.MainActivity`，方法 `onCreate(Bundle)`
- `d = cn.soulapp.android.lib.common.utils.settings.SoulSettings.isNightMode()`
- Hook ID：`new-soul/bottom-settings-entry`
- hooker：`Ldev/local/newsoul/a;-><init>(I)V`，case = `0x2`（2）
- `deoptimize` 作用目标
- 静态字段：`o.a=true`
- 日志：成功 `Log.i("bottom settings entry installed")`；失败 `Log.e("bottom settings entry unavailable")`

**辅助方法**
- `a(Activity)`：查找 `main_bottom_tab_bar` 下 tab 设置长按监听，失败 `Log.w("bottom settings binding failed")`
- `b(Activity)`：夜间模式判断

---

## 15. `S.a` 字段与配置文件回传

- **文件**：`S.smali`（类 `dev/local/newsoul/S`，abstract）
- **静态字段**：`a:XposedModule`（在 `onPackageReady` 末尾通过 `sput-object v1, Ldev/local/newsoul/S;->a` 赋值）、`b:Handler`、`c:ExecutorService`、`d:WeakHashMap`、`e:HashSet`、`f:SecureRandom`

**结论**：`S` 本身**无独立安装方法**。`a` 字段是模块实例，供 `S.a(Activity)` 挂 `onActivityResult`。
- `a(Activity)`：用反射找 Activity 的 `onActivityResult(int, int, Intent)`；无 `a` 则 `IllegalStateException`；无方法则 `NoSuchMethodException`
- Hook ID：`new-soul/config-file-result`
- hooker：`Ldev/local/newsoul/a;-><init>(I)V`，case = `0xc`（12）
- 加入 HashSet `e`
- `b/c/d` 为配置导出/导入流程，字符串逐字：`"NewSoul-config-"`、`"dev.local.soulhookdemo.public.settings.v1"`、`"android.intent.action.CREATE_DOCUMENT"`、`"android.intent.action.OPEN_DOCUMENT"`、`"请选择保存位置"`、`"请选择配置文件"`、`"配置操作进行中，请先完成或取消"`、`"无法打开配置文件操作，请重试"`

---

## 16. `SoulHook.installSettingsHook` 与 lambda

- **文件**：`SoulHook.smali`（类 `dev/local/newsoul/SoulHook`，final，extends XposedModule）
- **静态字段**：`TAG="SoulHook102"`、`TARGET="cn.soulapp.android"`、`settingsHookReady:Z`；实例字段：`installed:Z`、`targetProcess:Z`、`toastShown:AtomicBoolean`

### 16.1 `installSettingsHook(ClassLoader)`（约 79–176 行）
- 目标：`cn.soulapp.android.h5.activity.H5Activity` 方法 `m1(boolean)`
- 断言（逐字）：须 private，否则 `IllegalStateException("Unexpected H5Activity.m1 signature")`
- Hook ID：`soul-demo/private-h5-m1`
- hooker：`Ldev/local/newsoul/x2;-><init>(Ldev/local/newsoul/SoulHook;I)V`，I = `1`
- 静态字段：`settingsHookReady=true`
- 日志：成功 `info("private hook installed: H5Activity.m1(boolean)")`；失败（模块 log error）`"Soul settings hook unavailable; check app version"`

> 注：`x2.smali` 文件实为类 `X2`（大写，`DialogInterface$OnCancelListener`）；小写类 `x2` 存于 `x2.1.smali`。

### 16.2 `lambda$installSettingsHook$1(Activity)`（约 176–360 行）
- UI 观察：`topic_title` / `rlWebRoot` / `contentLayout` / `titleLay`
- 注入：E1 / F1 / I1 / a2 / M1
- 布局不匹配：`Log.w("Settings layout does not match; leaving page unchanged")`
- 异常：`Log.e("Settings observation failed")`

### 16.3 `lambda$installSettingsHook$2(Chain)`（约 360–396 行）
- `proceed` 后对 H5Activity 视图 post：`Ldev/local/newsoul/m;-><init>(Landroid/app/Activity;I)V`，I = `5`
- 日志（模块 log info）：`"private hook hit: H5Activity.m1(boolean)"`

### 16.4 `lambda$onPackageReady$0(Chain)`（约 396–503 行）
- 时机：`Activity.onResume` 之后
- 若 `packageName == "cn.soulapp.android"`：`V->h(context)`、`V->d("page.resume", className)`、`Q0->f(activity)`
- 读 SharedPreferences `dev.local.soulhookdemo.public.settings.v1`
- `page_log`（默认 true）→ `info("onResume: " + className)`
- `startup_toast`（默认 true）且 `toastShown` CAS 成功 → Toast `"New Soul 已生效"`

### 16.5 相关 hook 汇总（`onPackageReady`）
| Hook ID | hooker | 目标 |
|---|---|---|
| `new-soul/debug-init` | `a`，case=`0xd`（13） | `Application.onCreate` |
| `new-soul/cold-launch-flag` | `y2`，I = `0` | `MainActivity.onCreate(Bundle)` |
| `new-soul/relaunch-flag` | `y2`，I = `1` | `MainActivity.onNewIntent(Intent)` |
| `new-soul/hot-splash-gate` | `D`，I = `1` | `tc.e$a.j(String)`；`deoptimize` `m` / `onApplicationForeground` |
| `new-soul/skip-startup-ad` | `e`，I = `3` | `MainScheduler.v()` / `s()`，字段 `"a"` |
| `new-soul/feed-data/<name>/<n>` | `c` | `SquareAdapter` |
| （广告控件，18 个 View 类） | `f`，I = `0` | 各 setId 无 / 经 `j->g` 处理 |
| `new-soul/game-message-result` | `a`，case=`4` | `DiceFingerMsg(int)` 构造器 / 字段 `"number"` |
| `new-soul/chat-anti-recall` | `a`，case=`0x9`（9） | `ChatManager.O(List)`，`deoptimize` |
| `new-soul/chat-anti-recall-roam` | `a`，case=`0xa`（10） | `RoamHandler.a(ImMessage)`，`deoptimize` |

- 静态字段：`SoulHook.k=true`
- 日志：`"private chat anti-recall hooks installed"` / `"private chat anti-recall unavailable"`
- Activity.onResume Hook ID：`soul-demo/activity-onResume`（hooker `x2`，I = `0`），`installed=true`，`info("hook installed: android.app.Activity.onResume")`

---

## 17. 参考：hooker 分派器 `a` 的 case 表

- **文件**：`a.1.smali`（类 `dev/local/newsoul/a`，`.class public final synthetic Ldev/local/newsoul/a;`，implements `XposedInterface$Hooker`）
- 字段 `a:I`；`<init>(I)V`；`intercept(Chain)` 用 `packed-switch v12, :pswitch_data_52e`

| case（十进制） | 语义（据字符串与调用推断） |
|---|---|
| `2`（0x2） | 底部设置入口观察（`bottom settings observation failed`；`main_bottom_tab_bar` 相关） |
| `3`（0x3） | 匹配音乐（`match_mute_background_music`、`soul match background music blocked`） |
| `4` | 游戏消息结果（`game message constructed with selected result=`） |
| `5`（0x5） | 礼物聊天 model（`chat_limit_session_type`、`chat_remove_gift_gate`） |
| `6`（0x6） | 礼物聊天 session（`chat gate observed; type=..; bypass=..`） |
| `7`（0x7） | 铃声礼物弹窗配置（`chat_remove_bell_gift_gate`、`special bell gift popup bypassed`） |
| `8`（0x8） | 礼物聊天 dialog（`gift chat dialog suppressed`） |
| `9`（0x9） | 私聊防撤回（`chat_anti_recall`、`recall interception failed`） |
| `10`（0xa） | 漫游防撤回（`recall retained; source=roam`、`roam recall interception failed`） |
| `11`（0xb） | 复读上下文（`repeat` 相关） |
| `12`（0xc） | 配置文件回传（`正在保存记录…`、`已取消导出`、`正在导出配置…`、`正在读取配置…`、`未取得配置文件，请重新选择`、`dev.local.soulhookdemo.recalled.v1`） |
| `13`（0xd） | debug-init |
| `15`（0xf） | 自己消息撤回时限（`own recall local age check bypassed`） |
| `16`（0x10） | 隐私照片预览（`privacy snap preview: repeat=`、`limitViewTimes`、`privacy_allow_screenshot`、`privacy_allow_download`、`privacy_allow_repeat`、`privacy photo preview: download=..; screenshot=..; repeat=..`） |
| `18`（0x12） | 社交分数 dialog（`chat_remove_social_score_limit`、`social score dialog suppressed`） |
| `19`（0x13） | 广场点赞生命周期（`square like lifecycle registered`） |
| `20`（0x14） | 广场信封创建 |
| `21`（0x15） | 广场信封可见性 |

其余字符串：`SoulHook102`、`" type="`、`"type"`。

---

## 18. 未确定项

- 小写 hooker 类 `a` / `e` / `c` / `f` / `b` / `m` / `n` / `o` 等的**内部具体行为**仅从字符串常量与 packed-switch 分派推断，未逐行展开每个 case 的完整实现。
- 下列 hooker 类的内部实现未逐一展开：`x2`（=X2 已核实为 DialogInterface 监听器，小写 x2 在 `x2.1.smali`）、`y2`、`N2`、`C`、`D`、`f1`、`b`、`s1`、`k1`、`z2`、`n0`、`m0`、`l0`、`d1`、`d3`。
- `Q0` 中部分助手（如 `H`、`I`、`J`、`k`、`l`、`G`、`y`、`w`、`q`）仅描述了调用点与传出字符串，内部细节未逐行核验。

---

## 19. 任务4 深度语义（复读 / 语音库 / 消息转发）—— 逐行核验

### 19.1 `F`（类 `dev/local/newsoul/F`，final）实例/静态字段

| 字段 | 绑定 |
|---|---|
| static `o:Z` | repeat 已安装 |
| static `p:Z` | message forward 就绪（= `d1.u`） |
| `a:WeakHashMap` | 菜单项对象 → `E`（复读上下文） |
| `b:Method` | `imlib.j0.g()`（静态无参 → 当前会话/账号 id） |
| `c:Method` | `ChatLongClickPopUp$a.b()`（菜单项 → action int） |
| `d:Method` | `ChatMessage.create(String)`（public） |
| `e:Method` | `ImMessage.createChatSendMsg(ChatMessage, String)`（public） |
| `f:Method` | `ChatMessage.setMsgType(int)`（public） |
| `g:Method` | `ChatMessage.setMsgContent(Serializable)`（public） |
| `h:Method` | `y4.u(ChatMessage)`（declared；落库/发送前处理） |
| `i:Method` | `y4.p1(ImMessage)`（declared + setAccessible；发送） |
| `j:Method` | `y4.Y1(String, IMediaKeyBoard, ImUserBean)`（declared；文本回填输入框） |
| `k:Method` | `cn.soulapp.android.chat.utils.f.a(ImMessage)`（public；唤起发送确认？） |
| `l:Constructor` | `ChatLongClickPopUp$a(String, int, int)` |
| `m` | `d1` 实例 |
| `n` | `d3` 实例 |

`F.h` 安装前对 `ConversationFragment` 断言存在字段 `L`/`Q`/`O`/`s`（仅 `getDeclaredField` 验证）。

### 19.2 类 `E`（`dev/local/newsoul/E`）— 复读上下文

`a:Object`=宿主 fragment/owner，`b:Object`=ImMessage，`c:String`=账号 id（`j0.g()` 结果），`d:String`=对端会话 id（owner 字段 `L`），`e:boolean`=已处理标记（幂等）。

### 19.3 `F.k(E):Activity`（2466–2553）

`act = E.a.getActivity()`；要求 `act!=null`、`!isFinishing`、`!isDestroyed`、`act.isResumed()`、`E.c.equals(j0.g())`、`E.d.equals(owner 字段 "L")`、`F.f(E.b, E.c, E.d)`；否则抛 `IllegalStateException("Chat changed")`。返回 `act`。

### 19.4 `F.j(E)`（2275–2464）复读执行

`act=F.k(E)`；`chat=E.b.getChatMessage()`；`body=F.d(chat)`（= `F.c(getMsgType, getMsgContent)`）；`type=chat.getMsgType()`；`sender=E.a.getDeclaredField("Q")`（若 null → `IllegalStateException("Sender unavailable")`）。
- `type==1`：取 owner 字段 `O`（IMediaKeyBoard）与 `s`（ImUserBean），任一 null → `IllegalStateException("Chat not ready")`；否则 `F.j.invoke(sender, body.get("text"), s, O)`（把文本回填到输入框）。
- 其它 type：`msg=ChatMessage.create(E.d)` → `setMsgType(type)` → `setMsgContent(body)` → `y4.u(msg)` → `im=sender.createChatSendMsg(msg, E.d)` → `y4.p1` 判定为 false 则 return；否则 `y4.p1.invoke(sender, im)` 真正发送。
- 结束日志（Android Log.i）：`"repeat dispatched; type=" + type`。

### 19.5 `F.i(Object)`（2134–2273）菜单回退渲染

依赖 `ThreadLocal u.g` 中的 `u`（由 hooker `a` case 0xb 在 `ConversationFragment.ac` 中填充）。取 `u.b`(msg)、`u.a`(owner)、`u.c`(view)；要求 `F.o` 为真、`u.c` 是已 attach 的 View。构造 `E(owner, msg, j0.g(), owner 字段 "L")`，`F.k(E)`，再 `F.a(menuList, owner, msg)`；若返回值 ≠ 入参则 `proceed(args)`，否则 `proceed()`。埋点 `repeat.fallback` → `"added=" + changed`；异常埋点 `repeat.fallback.skipped`。

### 19.6 `F.a(Object result, Object owner, Object msg)`（891–1372）菜单渲染

入参非 `List` → 埋点 `repeat.menu.skip` + `reason=not_list`，原样返回；空列表 → `reason=empty_menu`；已含 `0x4e5301/0x4e5302/0x4e5303` 之一 → `reason=already_added`。取会话 id + owner 字段 `L`，`F.f(msg, session, viewL)` 不通过则返回。`getContext()` 后取资源 id `c_ct_resend_menu`（drawable），缺失 → `reason=missing_repeat_icon`。复制 `ArrayList`，用构造器 `l` 追加菜单项：
- 「复读」icon=`c_ct_resend_menu`，action=`0x4e5301`，并 `a.put(item, new E(ctx,msg,session,viewL))`
- 若 `d1.u` 且资源 `c_ct_longclick_menu_share` 存在 → 「转发」action=`0x4e5302`
- 若 `d3.g` 且 `msg.getMsgType()==5` → 「存入语音库」action=`0x4e5303`
日志（Android Log.i）：`"repeat menu added; type=" + type + "; own=" + own`。

### 19.7 `F.f(msg, account, peer):boolean`（525–865）归属校验

`getFrom`/`getTo` 与账号 id 双向匹配；`getChatMessage` 非空；按 `getMsgType` 组合校验；排除 `isSnap`/`isAnonymityChat`/`isSnapChat`。埋点 `repeat.eligible`。

### 19.8 `F.c(int type, Object body)`（77–365）消息体重建

| type | 语义 |
|---|---|
| 1 | `TextMsg(text)`：`trim` 非空且长度 ≤500，否则 `IllegalArgumentException("Invalid text")` |
| 2 | `ImgMsg` → `F.e` |
| 3 | `ImgMsgs(imgMsgList)` 逐个 `F.e`；空 → `Empty images` |
| 5 | `AudioMsg(url, duration)`：url 须 `isDirectHttpUrl` 且 duration>0，否则 `Invalid audio` |
| 其它 | `Unsupported message body` |
| null | `Missing body` |

`F.e(ImgMsg)`：校验 `imageUrl`/`imageW`/`imageH`（否则 `Image not uploaded`/`Invalid image`），`new ImgMsg()` 后逐字段复制。`F.d(Object msg)` = `F.c(msg.getMsgType(), msg.getMsgContent())`。

### 19.9 hooker `C`（复读）

- case 0（`u0.p`，repeat-click）：`item = getArg(7)`；null → proceed；`action = F.c.invoke(item)`；非 0x4e5301/2/3 → proceed。埋点 `repeat.action.enter` → `"action=" + action`。从 `F.a` 取 `E`；`E.e` 已真 → 返回 null；置 `E.e=true`：
  - `0x4e5303` → `F.k(E)` 后 `F.n.h(E.a, F.d(E.b.getChatMessage()))`（存入语音库）
  - `0x4e5302` → `F.k(E)` 后 `F.m.g(act, E.c, E.d, chat.getMsgType(), F.d(chat))`（转发）
  - 其它 → `F.j(E)`（复读）
  - 异常：埋点 `repeat.action.failed` + Android Log.w `"repeat failed; no automatic retry"` + Toast（`0x4e5303`=`无法保存语音，请重新选择`、`0x4e5302`=`无法打开转发，请重新选择`、其它=`复读未完成，请检查消息状态`）
- case 1（`u0.k`，repeat-menu）：诊断计数；`r=proceed()`；`F.a(r, getArg(0), getArg(2))`；异常埋点 `repeat.menu.skipped` + Android Log.w `"repeat menu skipped"`。另有埋点 `repeat.menu.enter`。

### 19.10 hooker `D`（action-marks-chat，`ChatLongClickPopUp` 渲染器）

`F.i(getArg(0))` → 若变化则 `proceed(args)` 否则 `proceed()`；随后遍历结果 List，用 `F.c`（`ChatLongClickPopUp$a.b()`）取 action，统计 0x4e5301/2/3 命中数（诊断 `repeat.render` → `count=..; custom=..`）。再遍历 `getThisObject().getClass().getDeclaredFields()` 中类型为 `android.widget.GridLayout` 的字段：若 `childCount == entries.size()`，逐子项比对 action，`0x4e5303` 时用 `e3(density)` 替换 ImageView 的 drawable，并对 view 调 `j1.b(view, isCustom)`。异常：埋点 `repeat.render.failed` + Android Log.w `"chat action marks unavailable"`。诊断入口埋点 `repeat.render.enter`，字段埋点 `repeat.render.grid` → `"children=..; entries=.."`。

### 19.11 语音库核心 `d3`（`dev/local/newsoul/d3`）

静态 `g:Z`（已安装）。实例：`a:Handler`（主线程）、`b:ExecutorService`（单线程）、`c:WeakHashMap<View,Boolean>`、`d:Method`=`j0.g()`、`e:Method`=`y4.q2(String,int,ImMessage)`、`f:b3`（当前对话框）。

- `d(Object fragment)`：取 `getActivity()`/`isResumed()`/`getView()`；在视图里找 id `menu_tab_voice_inner_outside` / `menu_tab_voice_inner` / `menu_tab_voice`；未处理且 `k.h(view)` 为 false 时挂 `O2` 长按监听并标记；对 `menu_tab_voice_inner_outside` 额外 `setOnTouchListener(null)`。
- `h(Object host, Object audioMsg)`：`b3` 构造 → 取 `url`/`duration`；合法（`isDirectHttpUrl` 且 duration>0）则先 `f.b()` 关旧框，`f = new b3`，`b3.e("保存到语音库", "", H2, U2)`；否则抛 `IllegalArgumentException("Invalid voice")`。
- `b(File, String url)`：最多 6 次重定向（`setInstanceFollowRedirects(false)`，connect 15000ms/read 20000ms）；内容长度 >`0x1400000` → `语音文件过大`；3xx 无 Location → `Invalid redirect`；非 200 → `音频下载失败（code）`；非直链 → `Invalid audio address`；超 6 次 → `Too many redirects`。
- `c(Object)`：沿继承链找类型为 `cn.soulapp.imlib.Conversation` 的实例字段；多个 → `IllegalStateException("Ambiguous conversation field")`；无 → `NoSuchFieldException("cn.soulapp.imlib.Conversation")`。
- `e(obj,name)`：`getMethod(name).invoke(obj)`。`g(obj,name)`：沿父类 `getDeclaredField`。`f(Context,String,Throwable)`：Android Log.w `"voice library operation failed; no automatic send retry"` + Toast。`i(Context,String)`：Toast。`a(q2,String,c3)`：构建 TextView（`w2.n(ctx,msg,15,q2.i)`，居中、padding 12dp、minHeight 48dp、单行）并挂点击。

### 19.12 语音库对话框 `b3`（`d3` 的合成内部类；**不是**确认框）

字段：`a:Object`(owner)、`b:Object`(会话，`d3.c(owner)`)、`c:Activity`、`d:String`(账号 id)、`e:String`(owner 字段 `L`)、`f:x1`(本机语音存储 `new x1(filesDir, d, 1)`)、`g:boolean`(深色)、`h:q2`(当前框)、`i:q2`(主列表框)、`j:int`(滚动 Y)、`k:String`(搜索词，初始 `""`)、`l:MediaPlayer`、`m:boolean`(已关闭)、`n:boolean`(已提交)、`o:boolean`(后台进行中)、`p:TextView`(试听按钮)、`q:d3`。

方法：`a(c3,c3)` 提交保存（置 `n=o=true`，提示 `"操作已提交，关闭后仍会继续；请稍后打开语音库查看结果。"`，negative 改 `"关闭"`，positive `"处理中…"` 且 disabled，投递 `m1(...)` 到 `d3.b`）；`b()` 关闭（`i()`，`h.e.dismiss()`，清 `d3.f`）；`c()` 若 `o` 为真先 Toast `"操作仍在后台进行，请稍后打开语音库查看结果"` 再 `b()`；`d(h3)` 详情视图；`e(String title, String text, P2, c3)` 备注编辑框（hint `"给这条语音写个备注"`，maxLen 120）；`f()` 打开主语音库（`"语音库"`/`"关闭"`）；`g()` 取/建对话框（保存滚动位置）；`h(q2,LinearLayout,ArrayList)` 渲染列表（命中 `"name\nX 秒"`，空 → `"还没有保存语音"`，无命中 → `"没有匹配的语音，请尝试其他备注"`）；`i()` 释放 MediaPlayer 并复位 `"试听"`；`j()` 状态守卫（失败 `IllegalStateException("聊天或账号已变化，请重新打开语音库")`）；`k(c3)` 绑定生命周期。

### 19.13 hooker `N2(d3, I)`

- I=0（`ConversationFragment.onResume`）：`proceed()`；对 `getThisObject()` 建 `WeakReference`；用 `d3.a` 在延迟 `0 / 350 / 1000`（ms）各 post 一个 `g(case 14)` runnable（→ `d3.d(fragment)`）。
- default（`onPause`）：若 `d3.f` 的 owner == `getThisObject()` → `d3.f.b()`（关框）；再 `proceed()`。

### 19.14 消息转发 `d1`（`dev/local/newsoul/d1`）

字段（全部 public，`e()` 内赋值）：`a:Class`=`SelectConversationActivity`、`b:Class`=`cn.soulapp.android.chat.listener.OnClickConfirmListener`、`c:Class`=`com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter$OnItemClickListener`、`d:Constructor`=`dialog.j1(Context,String)`、`e:Method`=`ConversationAdapter.setOnItemClickListener(OnItemClickListener)`、`f:Method`=`dialog.j1.d(OnClickConfirmListener)`、`g:Method`=`imlib.j0.g()`、`h:Method`=`ChatMessage.create(String)`、`i:Method`=`setMsgContent(Serializable)`、`j:Method`=`setMsgType(int)`、`k:Method`=`setTransExtMap(Map)`、`l:Method`=`y4.u(ChatMessage)`、`m:Method`=`ImMessage.createChatSendMsg(ChatMessage,String)`、`n:Method`=`imlib.i0.v()`、`o:Method`=`imlib.i0.o()`、`p:Method`=`ChatManager.q0(ImMessage)`、`q:Method`=`uj.k1.g2(UserConversation)`、`r:String`(一次性 UUID token)、`s:e1`(转发上下文)、`t:Dialog`(确认框)、`u:boolean`(就绪标志，本文件只读不写)。

Hook（hooker `a1(d1,index)`，均 PROTECTIVE + `deoptimize`）：
| id | 目标 | index |
|---|---|---|
| `new-soul/message-forward-select` | `SelectConversationActivity.F(int)` | 0 |
| `new-soul/message-forward-list` | `SelectConversationActivity.loadConversationList(List,List,boolean)` | 1 |
| `new-soul/message-forward-ui` | `SelectConversationActivity.init(Bundle)` | 2 |
| `new-soul/message-forward-complete-<方法名>` | `SelectConversationActivity.E(View)`、`S()` | 3 |
| `new-soul/message-forward-navigation-<方法名>` | `SelectConversationActivity.D(Object)`、`onClick(View)` | 4 |
| `new-soul/message-forward-cleanup` | `android.app.Activity.onDestroy()` | 5 |

`g(Activity, String account, String peer, int type, Object payload)`：要求 `u`、未 finishing/destroyed、`j0.g() == account`，否则 `IllegalStateException("Forward unavailable")`；`s = new e1(account, peer, type, F.c(type, payload), SystemClock.elapsedRealtime())`；`r = UUID.randomUUID().toString()`；`Intent(activity, SelectConversationActivity)` 附 `dev.local.newsoul.message_forward_token=r`、`isChoice=false`、`isFromPreview=true`、`onlyFinishAfterShare=false`；`startActivity`。埋点 `message.forward.picker_open`。

其余：`b(Activity)` 弹确认框（文案 `"将这条"+type+"转发给以下 "+N+" 人？\n\n"+names`，type 1=`文字消息`/5=`语音`/其它=`照片`，join `"、"`）；`k(e1,String)` 逐条发送 `ChatManager.q0(i0.o(i0.v()), ImMessage.createChatSendMsg(...))`；`n(Activity)` 刷新 `"完成(n)"` 按钮；`m(Activity,int)` 选中（上限 9 人，`"最多选择9人"`）；失败 Toast `"转发未完成，请重新选择消息"`；过期 Toast `"转发已过期，请重新选择消息"`；`R.string` 无关常量：`"所选用户"`、`"Recipient changed"`、`"Invalid selection"`、`"Forward unavailable"`。

### 19.15 未读清单（本轮未逐行核验）

`a.1.smali` case 0xb（repeat-context，写 `u.g` ThreadLocal）、`a1.1.smali`（转发 hooker）、`u.1.smali`、`O2/H2/U2/Q2/C0/A0/G1/m1/V2/X2/c1/e1/c3/P2/h3/x1/q2/L2/w2/k` 辅助类。