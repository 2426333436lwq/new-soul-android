# INSTALLERS_TAIL — `SoulHook.onPackageReady` 后半段安装规格

> 来源文件：`re_work/smali_out/dev/local/newsoul/SoulHook.smali`
> 覆盖范围：第 **988 行 ~ 第 2469 行**（`onPackageReady` 后半段，至 `.end method`）。
> 说明：本文只做「规格翻译」，所有字符串常量、hook ID、异常文案均**逐字保留**，未意译。
> 寄存器上下文（在 `onPackageReady` 开头 579–987 行建立，进入本段时有效）：
> - `v1` = `p0` = `this`（`Ldev/local/newsoul/SoulHook;`，即 `XposedModule` 实例）
> - `v2` = `4`（`Log.INFO` 等级）、`v3` = `0`、`v4` = `1`
> - `v6` = `ClassLoader`（来自 `p1.getClassLoader()`）
> - `v7` = `"new soul: splash hook installed (Soul 6.37.0)"`（进入 `:goto_187` 后被复用）
> - `v8` = 字段名 `"a"`；`:goto_1e7` 后被复用为 `j.b`（`Set`）
> - `v9` = `"SoulHook102"`；`v10` = `6`（`Log.ERROR` 等级）；`v11` / `v12` 为临时量
> - 本段中 `v7/v8/v9/v11` 会被反复复用，下文每个块单独标注其当时取值。

---

## 一、按出现顺序的「安装块」

### 块 1 — 启动广告跳过（MainScheduler 冷启动调度）· `new-soul/skip-startup-ad`

- **范围**：`:goto_187` / `:try_start_187` → `:goto_1e7`（第 989–1087 行）
- **功能名**：跳过 Soul 启动页广告（首页 `MainScheduler` 调度方法拦截）
- **目标宿主类名（逐字）**：`cn.soulapp.android.component.startup.main.MainScheduler`
- **目标方法/字段**：
  - 方法 `v`（`getDeclaredMethod("v", (Class[])null)`，零参）
  - 方法 `s`（`getDeclaredMethod("s", (Class[])null)`，零参）
  - 字段 `a`（`getDeclaredField("a")`，`v8` = `"a"`）
- **hook 目标**：对方法 `v` 进行 `hook(...)`（即 `this.hook(Method v)`）
- **hook 稳定 ID**：`new-soul/skip-startup-ad`
- **Hooker 构造**：`new Ldev/local/newsoul/e;` → `e.<init>(AnnotatedElement, AccessibleObject, Object, I)`
  - 实参：`(v0 = 字段 a 的 Field, v11 = 方法 s 的 Method, v1 = this SoulHook, 3)`
  - 即 `new e(field a, method s, this@SoulHook, 3)`；`case = 3`
- **前置签名校验**：
  - 断言方法 `v` 的 `getReturnType() == java.lang.Void.TYPE`
  - 断言方法 `s` 的 `getReturnType() == java.lang.Void.TYPE`
  - 通过后：对 `Field a` 与 `Method s` 调用 `setAccessible(true)`（实参 `v4` = `1`）
  - 断言失败：`throw new IllegalStateException("Unexpected Soul startup method signature")`（`:cond_1d5`，第 1069–1075 行）
- **异常模式**：`XposedInterface$ExceptionMode.PROTECTIVE`
- **成功日志（逐字）**：`"new soul: splash hook installed (Soul 6.37.0)"`
  - `Landroid/util/Log;->i("SoulHook102", msg)`
  - `this.log(4 /*INFO*/, "SoulHook102", msg)`
- **失败日志（逐字）**：
  - `Landroid/util/Log;->e("SoulHook102", "new soul: splash hook unavailable; original startup retained", e)`
  - `this.log(6 /*ERROR*/, "SoulHook102", "new soul: splash hook unavailable", e)`
- **是否 deoptimize**：否
- **涉及的静态字段赋值**：`Ldev/local/newsoul/Q0;->W:Z = true`（`sput-boolean v4=1`，第 1055 行）

---

### 块 2 — 广告 Feed 数据过滤（SquareAdapter 列表方法动态 hook）· `new-soul/feed-data/<name>/<n>`

- **范围**：`:goto_1e7` 起 → `:goto_31c`（第 1088–1444 行）
- **功能名**：拦截 Square 信息流 Adapter 的数据变更方法（广告数据过滤）
- **目标宿主类名（逐字）**：
  - `cn.soulapp.android.component.square.main.squarepost.SquareAdapter`
  - 依赖类：`com.chad.library.adapter.base.BaseQuickAdapter`、`la.a`、`ia.a`
- **目标方法**：`BaseQuickAdapter` 中**动态筛选**出的方法，满足全部条件：
  1. 方法名 ∈ `{addData, setData, setList, replaceData, setNewInstance, setNewData, setDiffNewData}`（`java.util.HashSet`）
  2. `getReturnType() == Void.TYPE`
  3. 参数个数 ≥ 1
  4. 若方法名为 `setDiffNewData`，参数个数必须 `== 1`
  5. 最后一个参数类型可赋值给 `java.util.Collection`，**或** 最后一个参数类型 `== java.lang.Object`
  - 命中后逐个 `hook(method)`
- **hook 稳定 ID**：`"new-soul/feed-data/" + method.getName() + "/" + 计数器(v10)`
  - 常量前缀逐字：`new-soul/feed-data/`；分隔常量：`/`
- **Hooker 构造**：`new Ldev/local/newsoul/c;` → `c.<init>(Class, I, Z, Class, Class, Method, Z)`
  - 实参：`(v0 = SquareAdapter Class, v4 = 末参数下标(=参数个数-1), v5 = 末参数可赋值给 Collection 的布尔, v20 = la.a Class, v21 = ia.a Class, v2 = 命中的 Method, v19 = 末参数是否为 Object 的布尔)`
- **前置签名校验**：见上「目标方法」第 1–5 条；循环结束后若命中计数为 0：
  - `throw new IllegalStateException("no adapter mutation methods")`（`:cond_30f`，第 1429–1436 行）
- **异常模式**：`XposedInterface$ExceptionMode.PROTECTIVE`
- **成功日志（逐字）**：`"feed data filter installed; methods=" + 命中数`
  - `Ldev/local/newsoul/j;->e(Ljava/lang/String;)V`（模块 info 级封装）
- **失败日志（逐字）**：
  - `Ldev/local/newsoul/j;->c("feed data filter", e)`（第 1441–1443 行，模块 error 封装）
- **是否 deoptimize**：否
- **涉及的静态字段赋值**：
  - `Ldev/local/newsoul/j;->g:Lio/github/libxposed/api/XposedModule; = this`（第 1101 行）
  - `Ldev/local/newsoul/j;->b:Ljava/util/Set;` 集合 `.add("ad_feed")`（第 1409 行，仅当命中数 > 0）

---

### 块 3 — 请求方法反射预载（fa.a / jb.e / mb.d / lb.h）

- **范围**：`:try_start_31c` → `:goto_345`（第 1446–1489 行）
- **功能名**：预载网络/请求相关方法引用（供 `j` 的工具方法后续 hook）
- **目标宿主类名（逐字）**：`fa.a`、`jb.e`、`mb.d`、`lb.h`
- **目标方法/字段**：
  - `fa.a.getDeclaredMethod("b", (Class[])null)`（零参方法 `b`）→ 存入 `j.f`
  - `j.h(ClassLoader, String, String, String, String)` 三次调用（逐字实参）：
    1. `j.h(v6, "jb.e", "a", "b", "e")`
    2. `j.h(v6, "mb.d", "a", "b", "f")`
    3. `j.h(v6, "lb.h", "b", "c", "a")`
- **hook 稳定 ID**：无（本块不直接 `setId`；实际 hook 由 `j.h` 内部完成）
- **Hooker 构造**：无（委托 `j.h`）
- **前置签名校验**：无显式断言
- **成功日志**：无
- **失败日志（逐字）**：`Ldev/local/newsoul/j;->c("request hooks", e)`（第 1486–1488 行）
- **是否 deoptimize**：否
- **涉及的静态字段赋值**：`Ldev/local/newsoul/j;->f:Ljava/lang/reflect/Method; = fa.a.b`（第 1459 行）

---

### 块 4 — 广告视图 / 广告控件安装（视图构造 + 持有者 + 弹窗 + setVisibility）

本块是「广告控件总安装」，含 4 个子安装 + 汇总日志，范围第 1490–1812 行。

#### 块 4a — Express 广告视图构造器拦截（16 组 类名/键名）

- **功能名**：对一批广告 Express View 的**所有构造器**统一 hook，用于广告隐藏/过滤
- **数据表（逐字，按数组顺序；8 组用 `v27..v42` 组装为 `[[Ljava/lang/String;`）**：

| # | 类名（逐字） | 键名（逐字） |
|---|---|---|
| 1 | `cn.soulapp.android.ad.soulad.ad.views.express.render.PostFeedExpressComponent` | `ad_feed` |
| 2 | `cn.soulapp.android.ad.soulad.ad.views.express.render.PostDetailExpressView` | `ad_detail` |
| 3 | `cn.soulapp.android.ad.soulad.ad.views.express.render.FeedDrawExpressView` | `ad_video` |
| 4 | `cn.soulapp.android.ad.soulad.ad.views.express.render.TagBannerExpressView` | `ad_tag` |
| 5 | `cn.soulapp.android.ad.soulad.ad.views.express.render.FeedWebExpressView` | `ad_tag_web` |
| 6 | `cn.soulapp.android.ad.soulad.ad.views.express.render.GroupBannerExpressView` | `ad_group` |
| 7 | `cn.soulapp.android.ad.soulad.ad.views.express.render.PlantGameExpressView` | `ad_planet` |
| 8 | `cn.soulapp.android.ad.soulad.ad.views.express.render.PlantGameTagExpressView` | `ad_planet` |
| 9 | `cn.soulapp.android.ad.soulad.ad.views.express.render.MeCellExpressView` | `ad_me` |
| 10 | `cn.soulapp.android.ad.soulad.ad.views.express.render.EnvelopeMessageFeedExpressView` | `ad_envelope` |
| 11 | `cn.soulapp.android.ad.soulad.ad.views.express.render.SystemNoticeFeedExpressView` | `ad_notice` |
| 12 | `cn.soulapp.android.ad.base.view.SearchBannerAdView` | `ad_search` |
| 13 | `cn.soulapp.android.ad.base.view.FloatAdView` | `ad_float` |
| 14 | `cn.soulapp.android.ad.base.view.FloatAdViewV2` | `ad_float` |
| 15 | `cn.soulapp.android.ad.base.view.DraggableFloatAdView` | `ad_float` |
| 16 | `cn.soulapp.android.ad.base.view.FloatEntranceView` | `ad_float` |

- **目标方法**：上述每个类的 `getDeclaredConstructors()` 返回的**全部构造器**，逐个 `j.g.hook(ctor)`
- **hook 稳定 ID**：无（仅 `setExceptionMode(PROTECTIVE)`，未调用 `setId`）
- **Hooker 构造**：`new Ldev/local/newsoul/f;` → `f.<init>(I, Object)`
  - 实参：`(0, 该组键名 String)`，即 `case = 0`，`Object = 键名`
- **前置签名校验**：`Class.isAssignableFrom(cls)` 需满足 `android.view.View` 可赋值；
  - 否则 `throw new IllegalStateException("not a View")`（`:cond_401`，第 1695–1702 行）
- **成功记录**：`j.b.add(键名)`（`v8` 集合，第 1688 行）
- **失败日志（逐字）**：`Ldev/local/newsoul/j;->c(该组类名, e)`（第 1707 行）
- **是否 deoptimize**：否

#### 块 4b — 广告 ViewHolder / Adapter 类安装（`j.g` 静态委派）

逐字调用序列（第 1719–1737 行）：
1. `j.g(classloader, "cn.soulapp.android.component.square.main.ad.AdPostViewHolder", "ad_feed", false)`
2. `j.g(classloader, "cn.soulapp.android.component.square.main.ad.AdExpressViewHolder", "ad_feed", false)`
3. `j.g(classloader, "pi.k", "ad_conversation", true)`
4. `j.g(classloader, "cn.soulapp.android.component.square.video.adapter.a", "ad_detail", true)`
- 签名：`j.g(ClassLoader, String class, String key, boolean)`；hook 细节在 `j.g` 内部。

#### 块 4c — 插屏广告弹窗安装（`j.f` 静态委派）

逐字调用（第 1739–1745 行）：
1. `j.f(classloader, "cn.soulapp.android.ad.dialog.InterstitialAdDialog")`
2. `j.f(classloader, "cn.soulapp.android.ad.dialog.InterstitialAdBottomDialog")`
- 签名：`j.f(ClassLoader, String)`。

#### 块 4d — View 可见性 hook · `View.setVisibility(int)`

- **目标宿主类**：`android.view.View`（`v0`，第 1616 行 `const-class v0, Landroid/view/View;`）
- **目标方法**：`setVisibility`，参数 `[int]`（`getDeclaredMethod("setVisibility", new Class[]{Integer.TYPE})`）
- **hook ID**：无（仅 `setExceptionMode(PROTECTIVE)`）
- **Hooker 构造**：`new Ldev/local/newsoul/a;` → `a.<init>(I)`，`case = 0`
- **前置校验**：无
- **失败日志（逐字）**：`Ldev/local/newsoul/j;->c("view visibility", e)`（第 1787–1789 行）

#### 块 4e — 汇总日志

- **成功日志（逐字）**：`"ad controls installed; ready=" + j.b.size() + "/18 (excluding splash)"`
  - 拼接常量：`ad controls installed; ready=`、`/18 (excluding splash)`
  - `Ldev/local/newsoul/j;->e(String)V`（第 1812 行）

---

### 块 5 — 聊天小游戏（骰子/猜拳）结果 hook · `new-soul/game-message-result`

- **范围**：第 1814–1955 行
- **功能名**：聊天小游戏消息结果拦截（骰子/猜拳）
- **目标宿主类名（逐字）**：
  - `cn.soulapp.imlib.msg.chat.DiceFingerMsg`
  - `cn.soulapp.android.component.chat.utils.y4`
- **目标方法/字段**：
  - 构造器：`DiceFingerMsg.<init>(int)`（`getDeclaredConstructor(new Class[]{Integer.TYPE})`）→ 存 `t.h`
  - 字段：`DiceFingerMsg.number`（`getField("number")`）→ 存 `t.i`
  - 字段：`y4.a`（`getDeclaredField("a")`，`setAccessible(true)`），再经 `t.a(...)` 探测其内部两个子字段：
    - `"w0"`（预期存在，`t.a(y4Class, fieldA, "w0", true)` → `t.a:Z`）
    - `"h1"`（`t.a(y4Class, fieldA, "h1", false)` → `t.b:Z`）
- **hook 目标**：`t.h`（`DiceFingerMsg(int)` 构造器）
- **hook 稳定 ID**：`new-soul/game-message-result`
- **Hooker 构造**：`new Ldev/local/newsoul/a;` → `a.<init>(I)`，`case = 4`
- **异常模式**：`PROTECTIVE`
- **成功日志（逐字）**：`"chat games installed; dice=" + t.a + " finger=" + t.b`
  - 拼接常量：`chat games installed; dice=`、` finger=`
  - `Landroid/util/Log;->i("SoulHook102", msg)` 与 `t.f.log(4 /*INFO*/, "SoulHook102", msg)`
- **失败日志（逐字）**：`t.f.log(6 /*ERROR*/, "SoulHook102", "chat games unavailable", e)`（第 1946–1954 行）
- **是否 deoptimize**：否
- **涉及的静态字段赋值**：
  - `Ldev/local/newsoul/t;->f:XposedModule = this`（第 1820 行）
  - `Ldev/local/newsoul/t;->h:Ljava/lang/reflect/Constructor; = DiceFingerMsg(int)`（第 1839 行）
  - `Ldev/local/newsoul/t;->i:Ljava/lang/reflect/Field; = DiceFingerMsg.number`（第 1847 行）
  - `Ldev/local/newsoul/t;->a:Z = t.a(y4, fieldA, "w0", true)`（第 1899 行）
  - `Ldev/local/newsoul/t;->b:Z = t.a(y4, fieldA, "h1", false)`（第 1909 行）

---

### 块 6 — 私聊防撤回（反射缓存 + 两处 hook）

- **范围**：第 1956–2268 行
- **功能名**：私聊消息防撤回（消息模型与监听器反射缓存，并 hook 撤回入口）
- **目标宿主类名（逐字）**：`cn.soulapp.imlib.msg.ImMessage`、`cn.soulapp.imlib.msg.chat.ChatMessage`、`cn.soulapp.imlib.ChatManager`、`lf.b`、`cn.soulapp.imlib.j0`、`cn.soulapp.imlib.Conversation`、`cn.soulapp.imlib.i0`、`cn.soulapp.imlib.listener.MsgListener`、`cn.soulapp.imlib.handler.RoamHandler`

#### 块 6a — 反射缓存（写入 `Q0` 静态字段）

| 目标 | 方法/字段（逐字） | 存入 |
|---|---|---|
| `lf.b` | `getDeclaredMethod("b", (Class[])null)` | `Q0.l` |
| `cn.soulapp.imlib.j0` | `getDeclaredMethod("g", (Class[])null)` | `Q0.m` |
| `ImMessage` | `getMethod("getChatMessage", null)` | `Q0.n` |
| `ImMessage` | `getMethod("getFrom", null)` | `Q0.p` |
| `ImMessage` | `getMethod("getTo", null)` | `Q0.q` |
| `ImMessage` | `getMethod("getMsgId", null)` | `Q0.r` |
| `ImMessage` | `getMethod("isAnonymityChat", null)` | `Q0.s` |
| `ChatMessage` | `getMethod("getMsgType", null)` | `Q0.o` |
| `ChatMessage` | `getMethod("getMsgContent", null)` | `Q0.t` |
| `ChatMessage` | `getMethod("getStringTransExt", [String])` | `Q0.u` |
| `ChatManager` | `getMethod("M", null)`（**结果被丢弃**，仅调用，第 2086 行） | —（未存） |
| `ChatManager` | `getMethod("F", [String])` | `Q0.v` |
| `Conversation` | `getMethod("getMessage", [String])` | `Q0.w` |
| `ChatManager` | `getMethod("K", [String])` | `Q0.x` |
| `cn.soulapp.imlib.i0` | `getMethod("v", null)` | `Q0.y` |
| `cn.soulapp.imlib.i0` | `getMethod("w", null)` | `Q0.z` |
| `MsgListener` | `getMethod("onRefreshUi", null)` | `Q0.A` |

- 之后调用 `Ldev/local/newsoul/Q0;->K(XposedModule, ClassLoader, Class)`，实参 `(this, classloader, ImMessage Class)`（第 2168 行）

#### 块 6b — 两处 hook

1. **会话消息处理**：`ChatManager.getDeclaredMethod("O", new Class[]{java.util.List})`
   - hook ID：`new-soul/chat-anti-recall`
   - Hooker：`new Ldev/local/newsoul/a;`，`case = 9`
   - `setExceptionMode(PROTECTIVE)`；随后 `this.deoptimize(method)`（**是**）
2. **漫游消息处理**：`cn.soulapp.imlib.handler.RoamHandler.getDeclaredMethod("a", new Class[]{ImMessage})`
   - hook ID：`new-soul/chat-anti-recall-roam`
   - Hooker：`new Ldev/local/newsoul/a;`，`case = 10`
   - `setExceptionMode(PROTECTIVE)`；随后 `this.deoptimize(method)`（**是**）

- **成功日志（逐字）**：`Landroid/util/Log;->i("SoulHook102", "private chat anti-recall hooks installed")`（第 2252–2254 行）
- **失败日志（逐字）**：`this.log(6 /*ERROR*/, "SoulHook102", "private chat anti-recall unavailable", e)`（第 2260–2267 行）
- **涉及的静态字段赋值**：`Ldev/local/newsoul/Q0;->k:Z = true`（第 2250 行）

---

### 块 7 — 后续模块委派与 Activity.onResume hook

- **范围**：第 2269–2456 行（`return-void` 于 2456）

#### 块 7a — 静态委派调用（非本类直接 hook，按顺序）

| 顺序 | 调用（逐字） | 备注 |
|---|---|---|
| 1 | `Ldev/local/newsoul/g1;->a(this, classloader)V` | |
| 2 | `Ldev/local/newsoul/Q0;->x(this, classloader)V` | |
| 3 | `new Ldev/local/newsoul/F;` → `F.h(this, classloader)` | 包在 try 内，见 7b |
| 4 | `Q0.z(this, classloader)` | |
| 5 | `Q0.E(this, classloader)` | |
| 6 | `Q0.D(this, classloader)` | |
| 7 | `Q0.A(this, classloader)` | |
| 8 | `Q0.B(this, classloader)` | |
| 9 | `Q0.C(this, classloader)` | |
| 10 | `Ldev/local/newsoul/u1;->g(this, classloader)` | |
| 11 | `Ldev/local/newsoul/p0;->e(this, classloader)` | |
| 12 | `Ldev/local/newsoul/B2;->c(this, classloader)` | |
| 13 | `Q0.F(this, classloader)` | |
| 14 | `Ldev/local/newsoul/S;->a = this`（`sput-object`，第 2375 行） | 静态字段赋值 |
| 15 | `Ldev/local/newsoul/o;->c(this, classloader)` | |

#### 块 7b — `F.h` 的异常处理（"repeat"）

- 失败时：`Ldev/local/newsoul/V;->i("repeat", e)`（第 2302 行）
- 追加：`Landroid/util/Log;->e("SoulHook102", "repeat unavailable")` + `this.log(6, "SoulHook102", "repeat unavailable")`（第 2304–2312 行）

#### 块 7c — Activity.onResume hook · `soul-demo/activity-onResume`

- **目标宿主类名**：`android.app.Activity`（`const-class v0, Landroid/app/Activity;`）
- **目标方法**：`onResume`（`getDeclaredMethod("onResume", (Class[])null)`，零参）
- **hook 稳定 ID**：`soul-demo/activity-onResume`
- **Hooker 构造**：`new Ldev/local/newsoul/x2;` → `x2.<init>(Ldev/local/newsoul/SoulHook;I)`，实参 `(this, 0)`
- **异常模式**：`PROTECTIVE`
- **成功日志（逐字）**：`this.info("hook installed: android.app.Activity.onResume")`（第 2424–2426 行）
- **随后**：`this.installed = true`（`iput-boolean v4=1`，第 2422 行）；调用 `this.installSettingsHook(classloader)`（第 2432 行，方法体不在本段）
- **失败日志（逐字）**（`:catchall_6e7` 第 2438–2453 行）：
  - `Landroid/util/Log;->e("SoulHook102", "hook installation failed", e)`
  - `this.log(6 /*ERROR*/, "SoulHook102", "hook installation failed", e)`
- **是否 deoptimize**：否

---

## 二、本段出现的字符串常量清单（按出现顺序，逐字）

| # | 行号 | 常量（逐字） | 用途 |
|---|---|---|---|
| 1 | 991 | `cn.soulapp.android.component.startup.main.MainScheduler` | 类名 |
| 2 | 997 | `v` | 方法名 |
| 3 | 1003 | `s` | 方法名 |
| 4 | 1035 | `new-soul/skip-startup-ad` | hook ID |
| 5 | 1071 | `Unexpected Soul startup method signature` | 异常文案 |
| 6 | 1080 | `new soul: splash hook unavailable; original startup retained` | 日志 |
| 7 | 1084 | `new soul: splash hook unavailable` | 日志 |
| 8 | 1093 | `a` | 字段名（复用于生成 ID 前） |
| 9 | 1097 | `b` | 方法名 |
| 10 | 1099 | `ad_feed` | 键名 |
| 11 | 1104 | `cn.soulapp.android.component.square.main.squarepost.SquareAdapter` | 类名 |
| 12 | 1110 | `com.chad.library.adapter.base.BaseQuickAdapter` | 类名 |
| 13 | 1116 | `la.a` | 类名 |
| 14 | 1122 | `ia.a` | 类名 |
| 15 | 1130 | `addData` | 方法名白名单 |
| 16 | 1132 | `setData` | 方法名白名单 |
| 17 | 1134 | `setList` | 方法名白名单 |
| 18 | 1136 | `replaceData` | 方法名白名单 |
| 19 | 1138 | `setNewInstance` | 方法名白名单 |
| 20 | 1140 | `setNewData` | 方法名白名单 |
| 21 | 1142 | `setDiffNewData` | 方法名白名单 |
| 22 | 1313 | `new-soul/feed-data/` | hook ID 前缀 |
| 23 | 1319 | `/` | hook ID 分隔符 |
| 24 | 1415 | `feed data filter installed; methods=` | 日志 |
| 25 | 1432 | `no adapter mutation methods` | 异常文案 |
| 26 | 1441 | `feed data filter` | 日志标签 |
| 27 | 1447 | `fa.a` | 类名 |
| 28 | 1455 | `b` | 方法名 |
| 29 | 1461 | `jb.e` | 类名 |
| 30 | 1463 | `e` | 成员名 |
| 31 | 1467 | `mb.d` | 类名 |
| 32 | 1469 | `f` | 成员名 |
| 33 | 1473 | `lb.h` | 类名 |
| 34 | 1475 | `c` | 成员名 |
| 35 | 1486 | `request hooks` | 日志标签 |
| 36 | 1491 | `cn.soulapp.android.ad.soulad.ad.views.express.render.PostFeedExpressComponent` | 类名 |
| 37 | 1497 | `cn.soulapp.android.ad.soulad.ad.views.express.render.PostDetailExpressView` | 类名 |
| 38 | 1499 | `ad_detail` | 键名 |
| 39 | 1505 | `cn.soulapp.android.ad.soulad.ad.views.express.render.FeedDrawExpressView` | 类名 |
| 40 | 1507 | `ad_video` | 键名 |
| 41 | 1513 | `cn.soulapp.android.ad.soulad.ad.views.express.render.TagBannerExpressView` | 类名 |
| 42 | 1515 | `ad_tag` | 键名 |
| 43 | 1521 | `cn.soulapp.android.ad.soulad.ad.views.express.render.FeedWebExpressView` | 类名 |
| 44 | 1523 | `ad_tag_web` | 键名 |
| 45 | 1529 | `cn.soulapp.android.ad.soulad.ad.views.express.render.GroupBannerExpressView` | 类名 |
| 46 | 1531 | `ad_group` | 键名 |
| 47 | 1537 | `cn.soulapp.android.ad.soulad.ad.views.express.render.PlantGameExpressView` | 类名 |
| 48 | 1539 | `ad_planet` | 键名 |
| 49 | 1545 | `cn.soulapp.android.ad.soulad.ad.views.express.render.PlantGameTagExpressView` | 类名 |
| 50 | 1551 | `cn.soulapp.android.ad.soulad.ad.views.express.render.MeCellExpressView` | 类名 |
| 51 | 1553 | `ad_me` | 键名 |
| 52 | 1559 | `cn.soulapp.android.ad.soulad.ad.views.express.render.EnvelopeMessageFeedExpressView` | 类名 |
| 53 | 1561 | `ad_envelope` | 键名 |
| 54 | 1567 | `cn.soulapp.android.ad.soulad.ad.views.express.render.SystemNoticeFeedExpressView` | 类名 |
| 55 | 1569 | `ad_notice` | 键名 |
| 56 | 1575 | `cn.soulapp.android.ad.base.view.SearchBannerAdView` | 类名 |
| 57 | 1577 | `ad_search` | 键名 |
| 58 | 1583 | `cn.soulapp.android.ad.base.view.FloatAdView` | 类名 |
| 59 | 1585 | `ad_float` | 键名 |
| 60 | 1591 | `cn.soulapp.android.ad.base.view.FloatAdViewV2` | 类名 |
| 61 | 1597 | `cn.soulapp.android.ad.base.view.DraggableFloatAdView` | 类名 |
| 62 | 1603 | `cn.soulapp.android.ad.base.view.FloatEntranceView` | 类名 |
| 63 | 1698 | `not a View` | 异常文案 |
| 64 | 1719 | `cn.soulapp.android.component.square.main.ad.AdPostViewHolder` | 类名 |
| 65 | 1725 | `cn.soulapp.android.component.square.main.ad.AdExpressViewHolder` | 类名 |
| 66 | 1729 | `pi.k` | 类名 |
| 67 | 1731 | `ad_conversation` | 键名 |
| 68 | 1735 | `cn.soulapp.android.component.square.video.adapter.a` | 类名 |
| 69 | 1739 | `cn.soulapp.android.ad.dialog.InterstitialAdDialog` | 类名 |
| 70 | 1743 | `cn.soulapp.android.ad.dialog.InterstitialAdBottomDialog` | 类名 |
| 71 | 1750 | `setVisibility` | 方法名 |
| 72 | 1787 | `view visibility` | 日志标签 |
| 73 | 1798 | `ad controls installed; ready=` | 日志 |
| 74 | 1804 | `/18 (excluding splash)` | 日志 |
| 75 | 1818 | `chat games installed; dice=` | 日志 |
| 76 | 1823 | `cn.soulapp.imlib.msg.chat.DiceFingerMsg` | 类名 |
| 77 | 1841 | `number` | 字段名 |
| 78 | 1857 | `new-soul/game-message-result` | hook ID |
| 79 | 1877 | `cn.soulapp.android.component.chat.utils.y4` | 类名 |
| 80 | 1883 | `a` | 字段名 |
| 81 | 1893 | `w0` | 字段名 |
| 82 | 1901 | `h1` | 字段名 |
| 83 | 1919 | ` finger=` | 日志 |
| 84 | 1929 | `SoulHook102` | 日志 TAG |
| 85 | 1950 | `chat games unavailable` | 日志 |
| 86 | 1961 | `SoulHook102` | 日志 TAG |
| 87 | 1966 | `cn.soulapp.imlib.msg.ImMessage` | 类名 |
| 88 | 1972 | `cn.soulapp.imlib.msg.chat.ChatMessage` | 类名 |
| 89 | 1978 | `cn.soulapp.imlib.ChatManager` | 类名 |
| 90 | 1984 | `lf.b` | 类名 |
| 91 | 1990 | `b` | 方法名 |
| 92 | 2000 | `cn.soulapp.imlib.j0` | 类名 |
| 93 | 2006 | `g` | 方法名 |
| 94 | 2014 | `getChatMessage` | 方法名 |
| 95 | 2022 | `getFrom` | 方法名 |
| 96 | 2030 | `getTo` | 方法名 |
| 97 | 2038 | `getMsgId` | 方法名 |
| 98 | 2046 | `isAnonymityChat` | 方法名 |
| 99 | 2054 | `getMsgType` | 方法名 |
| 100 | 2062 | `getMsgContent` | 方法名 |
| 101 | 2070 | `getStringTransExt` | 方法名 |
| 102 | 2082 | `M` | 方法名 |
| 103 | 2088 | `F` | 方法名 |
| 104 | 2100 | `cn.soulapp.imlib.Conversation` | 类名 |
| 105 | 2106 | `getMessage` | 方法名 |
| 106 | 2118 | `K` | 方法名 |
| 107 | 2130 | `cn.soulapp.imlib.i0` | 类名 |
| 108 | 2136 | `v` | 方法名 |
| 109 | 2146 | `w` | 方法名 |
| 110 | 2154 | `cn.soulapp.imlib.listener.MsgListener` | 类名 |
| 111 | 2160 | `onRefreshUi` | 方法名 |
| 112 | 2170 | `O` | 方法名 |
| 113 | 2186 | `new-soul/chat-anti-recall` | hook ID |
| 114 | 2208 | `cn.soulapp.imlib.handler.RoamHandler` | 类名 |
| 115 | 2214 | `a` | 方法名 |
| 116 | 2228 | `new-soul/chat-anti-recall-roam` | hook ID |
| 117 | 2252 | `private chat anti-recall hooks installed` | 日志 |
| 118 | 2263 | `private chat anti-recall unavailable` | 日志 |
| 119 | 2300 | `repeat` | 日志标签 |
| 120 | 2304 | `SoulHook102` | 日志 TAG |
| 121 | 2306 | `repeat unavailable` | 日志 |
| 122 | 2388 | `onResume` | 方法名 |
| 123 | 2400 | `soul-demo/activity-onResume` | hook ID |
| 124 | 2424 | `hook installed: android.app.Activity.onResume` | 日志 |
| 125 | 2441 | `SoulHook102` | 日志 TAG |
| 126 | 2443 | `hook installation failed` | 日志 |
| 127 | 2447 | `SoulHook102` | 日志 TAG |
| 128 | 2449 | `hook installation failed` | 日志 |

---

## 三、本段引用的辅助类（定义在其它 smali 文件）

| smali 文件 | 类 | 本段使用方式（字段 / 方法） |
|---|---|---|
| `dev/local/newsoul/j.smali`（磁盘名 `j.1.smali`） | `j` | 静态字段 `f:Method`（写入 `fa.a.b`）、`g:XposedModule`（写入 `this`）、`b:Set`（`add("ad_feed")`）；静态方法 `e(String)`（info 日志）、`c(String,Throwable)`（error 日志）、`h(ClassLoader,String,String,String,String)`、`g(ClassLoader,String,String,boolean)`、`f(ClassLoader,String)` |
| `dev/local/newsoul/V.smali` | `V` | 静态方法 `i(String,Throwable)`（error 日志，块 7b） |
| `dev/local/newsoul/t.smali`（`t.1.smali`） | `t` | 静态字段 `f:XposedModule`、`h:Constructor`、`i:Field`、`a:Z`、`b:Z`；静态方法 `a(Class,Field,String,boolean)Z` |
| `dev/local/newsoul/Q0.smali` | `Q0` | 静态字段 `W:Z`、`k:Z`、`l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,A:Method`；静态方法 `K(XposedModule,ClassLoader,Class)`、`x/z/E/D/A/B/C/F(XposedModule,ClassLoader)`；实例方法 `f(Activity)`（不在本段） |
| `dev/local/newsoul/a.smali`（`a.1.smali`） | `a` | Hooker 实现 `a.<init>(int)`；本段 `case` = 0（setVisibility）、4（小游戏）、9（防撤回主）、10（防撤回漫游） |
| `dev/local/newsoul/c.smali`（`c.1.smali`） | `c` | Hooker 实现 `c.<init>(Class,int,boolean,Class,Class,Method,boolean)`（块 2） |
| `dev/local/newsoul/e.smali`（`e.1.smali`） | `e` | Hooker 实现 `e.<init>(AnnotatedElement,AccessibleObject,Object,int)`；本段 `case` = 3（块 1） |
| `dev/local/newsoul/f.smali`（`f.1.smali`） | `f` | Hooker 实现 `f.<init>(int,Object)`；本段 `case` = 0，Object = 键名（块 4a） |
| `dev/local/newsoul/x2.smali`（`x2.1.smali`） | `x2` | Hooker 实现 `x2.<init>(SoulHook,int)`；本段实参 `(this, 0)`（块 7c） |
| `dev/local/newsoul/D.smali` | `D` | Hooker 实现 `D.<init>(Object,Object,int)`（本段 988 行之前的热启动块使用，`case` = 1；范围外） |
| `dev/local/newsoul/y2.smali`（`y2.1.smali`） | `y2` | Hooker 实现 `y2.<init>(XposedModule,int)`（范围外，Cold/Relaunch 块） |
| `dev/local/newsoul/g0.smali`（`g0.1.smali`） | `g0` | 静态字段 `a:XposedModule`（第 751 行 `g0.a = this`，范围外） |
| `dev/local/newsoul/g1.smali`（`g1.1.smali`） | `g1` | 静态方法 `a(XposedModule,ClassLoader)`（块 7a#1） |
| `dev/local/newsoul/u1.smali`（`u1.1.smali`） | `u1` | 静态方法 `g(XposedModule,ClassLoader)`（块 7a#10） |
| `dev/local/newsoul/p0.smali`（`p0.1.smali`） | `p0` | 静态方法 `e(XposedModule,ClassLoader)`（块 7a#11） |
| `dev/local/newsoul/B2.smali` | `B2` | 静态方法 `c(XposedModule,ClassLoader)`（块 7a#12） |
| `dev/local/newsoul/F.smali` | `F` | 实例方法 `h(XposedModule,ClassLoader)`（块 7a#3 / 7b） |
| `dev/local/newsoul/S.smali` | `S` | 静态字段 `a:XposedModule`（块 7a#14，`S.a = this`） |
| `dev/local/newsoul/o.smali`（`o.1.smali`） | `o` | 静态方法 `c(XposedModule,ClassLoader)`（块 7a#15） |

---

## 四、未确定项

- **块 2 中 `c` Hooker 的第 2 个 int 参数语义**：由 `array-length(paramTypes) - 1` 计算得出，判定为「**末参数下标**」（未在第 988–2469 行内进一步使用/校验），标注为**推断**，未确定其在 `c.intercept` 内的确切用途（第 1250–1251、1355 行）。
- **块 3 中 `j.h` 的后四个 `String` 参数语义**：三次调用的第 3/4/5 参顺序不一致（分别为 `("a","b","e")`、`("a","b","f")`、`("b","c","a")`），仅能确定其含义与「类名/方法名/字段名」相关，**具体角色未确定**（第 1461–1477 行）。
- **块 4a 中 `f` Hooker 的 `Object` 参数**：确定为该组键名 `String`（如 `ad_feed` / `ad_float`），但键名在 `f.intercept` 内的用途未在本段体现。
- **块 6a 第 2082–2086 行 `ChatManager.getMethod("M", null)`**：调用结果**未保存**（无 `move-result`），疑为存在性探测/副作用校验，语义**未确定**。
- **块 7a 各委派方法（`g1.a` / `u1.g` / `p0.e` / `B2.c` / `o.c` / `Q0.x,z,E,D,A,B,C,F` / `F.h`）内部**：其 hook 内容定义在各自 smali 文件中，**不在本段范围**，本规格仅记录调用入口。
- **`this.installSettingsHook(ClassLoader)`（第 2432 行）**：方法体不在第 988–2469 行内，仅记录调用点。