# TASK4 行为语义规格（LSPosed 模块 `dev.local.newsoul`）

> 本文档是对 `re_work/smali_out/dev/local/newsoul/` 下 apktool smali 反编译产物的**行为语义规格**，用于把模块复刻为可读的 Android 工程。
> 只描述「做什么 / 什么条件 / 什么文案 / 什么异常」，不做代码转写。
> 所有字符串常量、Toast、异常消息**逐字保留**（含中英文标点）。

## 文件命名约定（Windows 大小写不敏感）

apktool 在 Windows 上会把同名不同大小写的文件加 `.1` 后缀。本工程约定：

| 磁盘文件名 | 实际类名 |
|---|---|
| `<Name>.smali`（首字母大写） | 类 `dev/local/newsoul/<Name>` |
| `<name>.1.smali`（小写名） | 类 `dev/local/newsoul/<name>` |

关键映射（已用 `^\.class` 逐个确认）：

- `b3.smali` → 类 `b3`
- `x1.1.smali` → 类 `x1`
- `q2.1.smali` → 类 `q2`
- `w2.1.smali` → 类 `w2`
- `H2.smali` → 类 `H2`；`U2.smali` → 类 `U2`
- `m1.1.smali` → 类 `m1`（注意 `M1.smali` 是另一个类 `M1`）
- `c3.smali` → 接口 `c3`；`P2.smali` → 接口 `P2`
- `h3.smali` → 类 `h3`；`C0.smali` → 类 `C0`；`A0.smali` → 类 `A0`
- `L2.smali` → 类 `L2`；`k.1.smali` → 类 `k`
- `a1.1.smali` → 类 `a1`；`e1.1.smali` → 类 `e1`；`d1.1.smali` → 类 `d1`
- `a.1.smali` → 类 `a`；`u.1.smali` → 类 `u`；`u0.1.smali` → 类 `u0`（模块自身的内部类，**不是**宿主 `cn.soulapp...u0`）
- `C.smali` → 类 `C`；`D.smali` → 类 `D`；`F.smali` → 类 `F`

公共约定：

- 日志 TAG：`SoulHook102`
- 事件埋点：`cn.soulapp...V.d(key, value)` / `V.c(Throwable)→String`（统一异常→字符串）
- 设置存储：`SharedPreferences` 名 `dev.local.soulhookdemo.public.settings.v1`（见 `w2.o`）
- 宿主包前缀：`cn.soulapp.android.*`、`cn.soulapp.imlib.*`

---

# A. 语音库功能域

主题：**聊天长按菜单 → 把语音消息「存入语音库」**；本地按账号存储、可试听 / 改备注 / 删除 / 发送到当前聊天。

## A.1 类 `b3`（`b3.smali`）— 语音库对话框控制器

`public final`，`super Object`。它是 `d3` 的合成内部类（由 `d3` 在 onResume 钩子里 new 出来），整个语音库 UI 的状态机都在这里。

### 字段

| 字段 | 类型 | 含义 |
|---|---|---|
| `a` | Object | 宿主会话/Activity 宿主对象（`thisObject`） |
| `b` | Object | 会话对象（conversation），构造时由 `d3.c(a)` 取得 |
| `c` | Activity | 当前 Activity（`a.getActivity()`） |
| `d` | String | 宿主当前账号 id |
| `e` | String | 当前聊天对象 id（`a` 的字段 `L`） |
| `f` | `x1` | 本地语音库存储（`new x1(filesDir, d, 1)`） |
| `g` | Z | 是否暗色主题 |
| `h` | `q2` | 当前打开的对话框（通用项，如子对话框） |
| `i` | `q2` | 语音库列表对话框 |
| `j` | int | ScrollView 滚动位置（`scrollY`） |
| `k` | String | 搜索关键词，初始 `""` |
| `l` | MediaPlayer | 试听播放器 |
| `m` | Z | 已关闭/销毁 |
| `n` | Z | 有后台任务进行中（防止重复提交） |
| `o` | Z | 是否有后台操作尚未完成（用于提示） |
| `p` | TextView | 试听按钮 |
| `q` | `d3` | 外部类 |

### 方法

**构造器 `<init>(d3, Object)`**
1. `c = a.getActivity()`（反射 `getActivity`）。
2. `d = d3.d` 反射 Method invoke → 宿主当前账号 id。
3. 取对象字段 `L` → `e`（聊天对象 id）。
4. `b = d3.c(obj)`（会话对象）。
5. 调 `j()` 做有效性校验。
6. 新建 `x1 = new x1(filesDir, d, 1)`（**p3=1 → 语音库模式**）。
7. 判断暗色 `g`：先按宿主资源 id `tv_title` 找到 TextView，取其文字颜色 luminance > 0.45 判为亮色（`g=false`）；否则读 `Configuration.uiMode`，`(uiMode & 0x30) == 0x20`（夜间）→ `g=true`。

**`a(c3 p1, c3 p2)` — 提交后台任务（异步保存/批量操作）**
1. 若 `n==true` 直接返回（已有任务在跑）。
2. 置 `n=true`、`o=true`。
3. 取当前对话框 `h`，`addView(w2.n(ctx, "操作已提交，关闭后仍会继续；请稍后打开语音库查看结果。", 13, j))`。
4. 负按钮（`BUTTON_NEGATIVE`，-2）`setText("关闭")`。
5. 正按钮（-1）`setEnabled(false)` + `setText("处理中…")`。
6. 提交 `d3.b`（ExecutorService）：`new m1(this, p1, null, null, p2, 5)` 并 `execute`。

**`b()` — 关闭**
1. 若 `m==true` 返回。
2. `m=true`；调 `i()`（停止播放/复位按钮）。
3. `h.e` dismiss；若 `d3.f==this` 置 `d3.f=null`。

**`c()` — 未完成时点关闭**
1. 若 `o==true` → `d3.i(activity, "操作仍在后台进行，请稍后打开语音库查看结果")`（Toast）。
2. 再 `b()`。

**`d(h3)` — 打开单条语音「详情」对话框**
1. `j()` 校验。
2. `g()` 得到对话框 `v0`。
3. 文本 `w2.n(ctx, h3.b /*备注*/, 17, q2.i)`（17 = 文字大小，`q2.i` = 标题色）。
4. `q2.t = "点击试听可播放本机语音。修改备注和删除只影响本机语音库；只有点击“发送到当前聊天”才会发送。"`（说明按钮文本，**注意此处为中文引号 `“”`**）。
5. 按钮：
   - `Q2` type0 = 试听
   - `Q2` type4 = 修改备注
   - `Q2` type5 = 删除（颜色：`g ? -0x5c5d : -0x4cd9e2`）
   - `Q2` type7 = 发送到当前聊天
6. `w2.a(...)` 把「试听 + 修改备注」排成一行。
7. 标题 `"语音详情"`，正按钮 `"返回语音库"`。
8. `k(new U2(3, this))` 绑定 dismiss/取消回调。
9. 正按钮 onClick = `A0(5, this)`。

**`e(String p1, String p2, P2 p3, c3 p4)` — 通用「输入框 + 保存」子对话框（改备注 / 改昵称）**
1. 新建 EditText，hint `"给这条语音写个备注"`，`setText(p2)`。
2. `minLines=2`、`maxLines=4`，加 `LengthFilter(120)`。
3. `q2.d.setNegativeButton("取消", L(1))`。
4. 标题 = `p1`，正按钮 `"保存"`。
5. `k(p4)`。
6. 正按钮 onClick = `C0(this, p4, 6)`。
7. 负按钮 onClick = `G1(this, p3, v3, 2)`。

**`f()` — 打开「语音库」主列表对话框**
1. `j()` 校验。
2. `q2.t = "保存语音\n长按聊天中的语音消息，选择「存入语音库」。\n\n打开语音库\n进入普通私聊，长按聊天输入区的语音图标。\n\n使用已保存的语音\n点击条目可试听、修改备注或删除。只有点击「发送到当前聊天」才会发送。\n\n语音保存在本机，按账号分别管理。"`（**逐字，含 `\n` 与中文引号 `「」`**）。
3. `addView(w2.n(ctx, "正在读取语音库…", 14, j))`（加载占位）。
4. `i = v0`（记录为主对话框）。
5. 标题 `"语音库"`，负按钮 `"关闭"`。
6. `k(new U2(1, this))`。
7. 执行 `V2(this, v0, 0)`（异步读取语音库列表）。

**`g()` — 创建/刷新通用对话框容器**
1. `i()`（先停止播放）。
2. 关闭旧 `h`（保存 `scrollY` 到 `j`）。
3. `p = null`。
4. `h = new q2(activity, g)`；返回 `h`。

**`h(q2 p1, LinearLayout p2, ArrayList<h3> p3)` — 渲染语音库列表**
1. `p2.removeAllViews()`。
2. `keyword = k.trim().toLowerCase(Locale.ROOT)`。
3. 遍历 `h3`：若 `remark.toLowerCase().contains(keyword)` 则：
   - 文本 = `remark` + `"\n"` + 时长 + `" 秒"`（即 `"<remark>\n<秒数> 秒"`）。
   - `Q2` type6 作为可点击行。
   - 用 `SpannableString` 把首行（remark）加粗、次要色、`0.87` 相对字号。
   - `setTextSize(16f)`；行距 `w2.i(ctx, 4)`。
   - `w2.b(...)` 加入列表。
4. 无匹配时：
   - 列表为空 → 文本 `"还没有保存语音"`（`w2.n` size14）。
   - 有匹配但未渲染出 → `"没有匹配的语音，请尝试其他备注"`（`w2.n` size14）。
5. 计数 `v3`。

**`i()` — 停止试听 / 复位**
- 若 `l`（MediaPlayer）非空 → `release()` 并置空。
- 若 `p`（试听按钮）非空 → `setText("试听")`。

**`j()` — 有效性校验（任一不满足即抛 `IllegalStateException("聊天或账号已变化，请重新打开语音库")`）**
1. `m==false`（未关闭）
2. `c != null`
3. `!c.isFinishing()`
4. `!c.isDestroyed()`
5. 反射宿主 `isResumed` 为 true
6. 反射取当前账号 == `d`
7. `e` 非空且非空串
8. `e != d`
9. 反射取 `L` == `e`
10. `b != null`
11. `d3.c(a) == b`
12. 宿主 `getChatType() == 0`（普通私聊）
13. 宿主 `getToUserId() == e`

**`k(c3 p1)` — 绑定对话框生命周期的取消/关闭监听**
1. `h.e.setCanceledOnTouchOutside(false)`。
2. `setOnCancelListener(new X2(this, q2, p1))`。
3. `setOnDismissListener(new c1(this, q2, 2))`。

---

## A.2 类 `x1`（`x1.1.smali`）— 本地存储（双模式）

`public final`。**双模式**：由构造器第三参 `p3` 决定是「语音库」模式（p3=1）还是「插眼/profile 书签」模式（其它）。

### 字段
- 静态 `b`：Object（全局锁，`synchronized` 目标）
- 实例 `a`：File（目录）

### 构造器 `<init>(File p1, String p2, int p3)`

按 `p3` packed-switch：

- **default（p3 != 1）= 插眼模式**：
  - `sha = SHA-256(accountId)` 的十六进制字符串。
  - 文件 = `<p1>/newsoul-profile-bookmarks/<sha>.bin`
  - 若 `accountId` 为空/null → 抛 `IllegalArgumentException("请先登录 Soul")`。
- **case 1 = 语音库模式**：
  - 目录 = `<p1>/newsoul-voice-library/<sha>`
  - 若 `accountId` 为空 → 抛 `IllegalArgumentException("Missing account")`。
  - 目录不存在则 `mkdirs()`，失败 → 抛 `IOException("Cannot create library")`。

### 静态方法

**`b(InputStream, FileOutputStream)`** — 分块复制语音数据
1. 缓冲 `16 KB`（`0x4000`）循环复制。
2. 累计字节 `> 0x1400000`（**20 MB**）→ 抛 `IOException("语音文件超过 20 MB")`。
3. 总长为 `0` → 抛 `IOException("语音文件为空")`。

**`c(File) → String`** — 按文件头魔数推断扩展名

读前 16 字节：
| 魔数 | 扩展名 |
|---|---|
| `#!AMR` | `.amr` |
| `RIFF` | `.wav` |
| `OggS` | `.ogg` |
| `bytes[4..8]=="ftyp"` | `.m4a` |
| `fLaC` | `.flac` |
| `(b0&0xff==0xff && b1&0xf6==0xf0)` | `.aac` |
| `ID3` 或 `(0xff,0xe0)` | `.mp3` |
| 其它 | `.audio` |

**`e(String) → String`** — 备注规范化
1. null → 返回 `""`。
2. `trim()`。
3. 空 → 抛 `IllegalArgumentException("请填写备注")`。
4. 长度 `> 120`（`0x78`）→ 抛 `IllegalArgumentException("备注最多 120 字")`。
5. 否则返回 `trim` 后的值。

**`j(String)`** — id 校验
- null / 空 / 长度 `> 512`（`0x200`）/ `trim` 后不等于自身 → 抛 `IllegalArgumentException("主页标识不可用")`。

### 实例方法（语音库模式）

**`a(FileInputStream, String remark, int duration)` （synchronized）— 保存一条语音**
1. `remark = e(remark)`。
2. `duration <= 0` → 抛 `IllegalArgumentException("Invalid duration")`。
3. `d()` 求当前总条数与总字节。
4. 若条数 `>= 200`（`0xc8`）或 `累计字节 + 20MB > 0x1f400000`（**500 MB**）→ 抛 `IOException("语音库已满，请先删除部分语音")`。
5. 目标 `<uuid>.audio`、临时 `<uuid>.part`；`new h3(uuid, remark, duration, now, target)`。
6. `b()` 写 `.part`；`fsync`；`ATOMIC_MOVE` 改名为 `.audio`；`k(h3)` 写 `.meta`。
7. 出错则删除 `.audio` 与 `.meta` 两者。

**`d() → ArrayList<h3>` （synchronized）— 读取全部语音记录**
1. `listFiles(f3)`，`f3` 过滤器（见下）只接受以 `".meta"` 结尾的文件。
2. 每个文件名 `substring(0, len-5)` 作为 id（**`len-5` 正好剥掉 `".meta"` 的 5 个字符**；此前疑似与 `.audio` 不符的问题已由 `f3.smali` 澄清）。
3. `g(id)` 读入 `h3` 并加入。
4. `IllegalArgumentException` / `IOException` → 抛 `IOException("语音库记录损坏，请保留数据后排查", cause)`。
5. 按 `g3` 比较器（取 `h3.d` 时间戳 long）**倒序**排序。
6. `listFiles` 为 null → 抛 `IOException("Cannot read library")`。

**`g(String id) → h3` （synchronized）— 读取单条语音**
1. id 须匹配正则 `[a-f0-9]{8}(-[a-f0-9]{4}){3}-[a-f0-9]{12}`（UUID 形式），否则抛 `IllegalArgumentException("Invalid voice id")`。
2. 读 `<id>.meta`：`readInt` 魔数须 `0x4e535631`（`"NSV1"`），不符 → 抛 `IOException("Unknown voice record")`。
3. 依次 `readUTF` remark、`readInt` duration、`readLong` ts。
4. 语音文件 = `<id>.audio`；校验 `duration>0` / `remark.length<=120` / `isFile` / `length>0` / `length<=20MB`，任不符 → 抛 `IOException("Missing voice file")`。

### 插眼模式方法

**`f(String id, String remark)`** — 新增/更新插眼记录
1. `j(id)`。
2. remark `trim` 空或 `>120` → 抛 `IllegalArgumentException("昵称不可用")`。
3. 同步 `x1.b`；`h()` 读列表。
4. 找到 `a==id` → 替换（保留原字段 c）；否则若 `size<1000`（`0x3e8`）头部插入；再否则抛 `IOException("插眼列表已满，请先移除部分记录")`。

**`h() → ArrayList`** — 读插眼列表
1. 文件 = 目录本身 `a`；不存在返回空。
2. `readInt` 魔数 `0x4e535031`（`"NSP1"`）。
3. `count<0` 或 `>1000` → 抛 `IOException("插眼记录数量异常")`。
4. 每项 `readUTF` id、`readUTF` name、`readLong` ts；并校验 `j(id)`、name 非空且 `<=120`、ts`>=0`、id 唯一。
5. 魔数不符 / 格式异常 → `"插眼记录格式异常"`；记录值非法 → `"插眼记录损坏"`。

**`i(String id)`** — 删除插眼记录：`j(id)`；`removeIf(w1.a==id)`；删除成功则 `l()`。

**`l(ArrayList)`** — 写插眼列表
1. 父目录 `mkdirs` 失败 → 抛 `IOException("无法创建插眼列表")`。
2. 临时 `<dirName>.tmp`；`writeInt 0x4e535031`、`writeInt size`；每项 `writeUTF a`、`writeUTF b`、`writeLong c`。
3. `fsync`；`move REPLACE_EXISTING`。

### 相关辅助（本任务读取确认）
- `f3.smali`：`FilenameFilter`，`accept` 返回 `name.endsWith(".meta")`。
- `g3.smali`：`ToLongFunction`，返回 `((h3)o).d`（时间戳，用于倒序排序）。

---

## A.3 类 `q2`（`q2.1.smali`）— 自定义对话框框架

`public final`。语音库所有对话框都基于它（**不是** AlertDialog 原生）。

### 字段
| 字段 | 类型 | 含义 |
|---|---|---|
| `a` | Activity | 宿主 |
| `b` | Z | 暗色 |
| `c` | `L2` | 圆角/背景工具 |
| `d` | `AlertDialog.Builder` | 内部 builder |
| `e` | `AlertDialog` | 实际对话框 |
| `f` | LinearLayout | 标题行容器 |
| `g` | Context | 上下文（builder 的 context） |
| `h` | LinearLayout | 内容容器（vertical） |
| `i` | int | 标题颜色 |
| `j` | int | 次要文字颜色 |
| `k` | int | 按钮文字颜色 |
| `l` | IdentityHashMap | View → Drawable 记录 |
| `m` | ArrayList\<Runnable\> | 待执行回调 |
| `n` | ArrayList\<j2\> | 子项/交互单元 |
| `o` | `q2` | 父对话框 |
| `p` | ArrayList\<q2\> | 子对话框 |
| `q` | Z | 标志 |
| `r` | BooleanSupplier | 草稿保护判定 |
| `s` | `q2` | 草稿确认子对话框 |
| `t` | CharSequence | 「说明」按钮文本 |

### 构造器 `(Activity, boolean dark)`
- 标题色 `i`：dark → `0xFFE6E6EF`；否则 `rgb(0x33,0x33,0x33)`。
- 次要色 `j`：基色 dark → `0xFF282838`，否则 `0xFFEDEDED`；尝试解析宿主 color 资源 `"color_s_04"`，用 `w2.q(base, dark, dark?0xFF181828:-1)` 取足对比度的插值色。
- 按钮色 `k`：基色 dark → `w2.b`（`0xFF00B5A2`）否则 `0xFF00796B`；同样 `w2.q(...)`。
- Builder theme：dark → `0x1030226`，否则 `0x103023a`。
- `g = builder.getContext()`。
- `c = new L2(dark, density)`。
- `h = LinearLayout(vertical)`，`tag=this`，padding `(16,8,16,16)dp`。

### 方法

**`a(Runnable)`** — 加入待执行回调 `m`。

**`b()`** — 隐藏键盘或关闭
- API `>= 30` 且 insets 含 `IME` → 取 `WindowInsetsController` 隐藏 IME 后返回。
- 否则调 `f()`（走草稿确认流程）。

**`c(BooleanSupplier)`** — 注册草稿保护
- 若 `e==null` → 设 `r=supplier` 且负按钮 `"取消"`（`L(1)`）。
- 否则抛 `IllegalStateException("Protect the draft before showing it")`。

**`d()`** — 先执行 `n` 中每个 `j2.b()`，再执行 `m` 中每个 `run()`。

**`e()`** — 生命周期通知：有父则递归父 `e()`；否则 `d()` + 子 `d()`。

**`f()`** — 显示「未保存确认」子对话框
- 文本 `"有尚未保存的修改。放弃后这些修改不会生效。"`
- 负按钮 `"放弃修改"` → 回调 `o2`
- 标题 `"放弃未保存的修改？"`
- 正按钮 `"继续编辑"`
- `setCanceledOnTouchOutside(false)`
- `onDismiss` = `c1(this, focusView, 1)`

**`g(title, positive)`** — 建 ScrollView 包裹 `h`，再调 `i()`。

**`h(View anchor, String[] options, boolean enabled, IntConsumer)`** — 长按弹出 PopupWindow
- 含圆角、elevation、分隔线；点击回调 `l2`；`showAsDropDown(anchor, 0, 4dp, 0x800005)`。

**`i(title, positive, ViewGroup)`** — 构建标题行 + 内容 + 按钮
- 标题行 `f`：标题用 `w2.n(...)` size 18sp、颜色 `i`。
- 字体：`p` 为空用 `Typeface.DEFAULT` bold，否则加载 `"fonts/tivf-bold-round.ttf"`（weight500，失败回退 `"sans-serif-medium"`）。
- `t` 非空时加 `w2.l` 帮助按钮。
- 标题最小高 58 / 70 dp。
- `setCustomTitle` / `setView` / 正按钮。
- SDK `>= 33` 处理 `OnBackInvokedDispatcher`。
- 窗口圆角。
- 按钮 `[-1,-2,-3]`：`L2.b(9, ...)`、`setTextColor(k)`、`minHeight/minWidth = 48dp`。

---

## A.4 语音库辅助类

### `H2.smali`（类 `H2`，`public final synthetic`，implements `P2`）
字段：`a:int`、`b:Object`、`c:Object`。
- **`a(String)`**：先 `b3.j()`；`new W2(b3, appCtx, c(String), arg0(String), a(int))`；再 `b3.a(W2, new U2(2, b3))`。
- **`b(Z)`**：`I2` 上下文点赞回调，含 `Log.i("SoulHook102", "loaded like response: success="+n)` 与 `"点赞失败，已停止；本批成功 " + n + " 条"`。

### `U2.smali`（类 `U2`，`public final synthetic`，implements `c3`）
字段：`a:int`、`b:Object`。
- **`a(Canvas)`** → `M0.a(...)`。
- **`run()`**（packed-switch on `a`）：
  - default / case0 → `b3.f()`（打开语音库主列表）
  - case1 → `b3.b()`（关闭）
  - case2 → `d3.i(ctx, "已保存到语音库")` + `b3.f()`

### `m1.1.smali`（类 `m1`，`public final synthetic`，implements `Runnable`）
字段：`a:int`、`b,c,d,e,f:Object`（5 个对象 + 1 个 int）。
构造器 `(Object,Object,Object,Object,Object,int)`。
- **`run()`**（packed-switch on `a`，0..5）：
  - **case5（`:pswitch_72`）**：执行 `c3.run()` 后 `post` 自身（`a=6`，即轮询/继续）。
  - **default（保存语音主流程）**：置 `n=o=false`；若 `m` 或 `h!=c` 则返回；`h.h.removeView(d)`；负按钮(-2) `setText("取消")`；正按钮(-1) `setEnabled(true)` + `setText("重试")`；`j()`；成功则 `e(c3).run()`，失败 `throw`；catch → `d3.f(activity, "操作未完成，请重试", t)`。
  - case0（`:pswitch_20c`）、case1（`:pswitch_1b8`）、case2（`:pswitch_180`）、case3（`:pswitch_113`）、case4（`:pswitch_a4`）：属设置/开关 UI 更新逻辑（含文案 `"（未就绪）"`、`"未就绪，原因尚未确认"`、`"勾选表示屏蔽"`，pref key `"feed_filter_gender"` 等），**非语音库主链路**。

> 不确定项：`m1` 构造器 5 个 Object 参数的具体语义（推断 `b=this`，`c`/`f` 与 `P2`/`c3` 回调相关），case 编号与任务给的 `a=5` 含义一致（`b3.a` 传 `5`）。`null` 占位对应未使用的对话框/按钮引用。

### `c3.smali`（接口 `c3`）：单方法 `run()`。

### `P2.smali`（接口 `P2`）：单方法 `a(String)`。

### `h3.smali`（类 `h3`，`public final`）
字段：`a:String`(id)、`b:String`(remark)、`c:int`(时长秒)、`d:J`(时间戳)、`e:File`。构造器 `(String,String,int,long,File)`。

### `C0.smali`（类 `C0`，`public final synthetic`，implements `OnClickListener`）
字段：`a:int`、`b,c:Object`。`onClick` packed-switch 0..5：
- default(5)：`b3` 相关 —— 若 `n` 则 `c()`，否则 `j()` + `c3.run()`（try/catch → `b()`）。
- case5（`:pswitch_20`）：`c3.run()`，catch → `d3.f(q2.a, "操作未完成，请重新打开语音库", t)`。
- case1（`:pswitch_b7`）：`d1.b(activity)`，catch → `d1.c(...)`。
- case2（`:pswitch_59`）：把 `"New Soul 功能兼容性检测"` 文本复制到剪贴板 + Toast `"检测结果已复制"`。
- case3（`:pswitch_41`）：对 Switch 的处理。
- case4（`:pswitch_35`）：对 Consumer 的处理。
- case0（`:pswitch_cb`）：清空数组并 `run`。

### `A0.smali`（类 `A0`，`public final synthetic`，implements `OnClickListener`）
字段：`a:int`、`b:Object`。`onClick` packed-switch 0..4：
- default：`b3` —— 若 `n` 则 `c()`，否则 `f()`（try/catch → `b()`）。
- case0（`:pswitch_70`）：`Dialog.dismiss()`。
- case1（`:pswitch_65`）：`runnables[0].run()`。
- case2（`:pswitch_45`）：打开 `https://t.me/+3Hl2lJ1X-oZlZTZl`，失败 Toast `"没有可打开链接的应用"`。
- case3（`:pswitch_3d`）：`BooleanSupplier`。
- case4（`:pswitch_19`）：`I2` 自动点赞（Toast `"自动点赞已开启，长按爱心可关闭"`）。

### `L2.smali`（类 `L2`，`public final`）
静态 `d,e:WeakHashMap`。字段：`a:Z`(暗色)、`b:F`(density)、`c:Z`。
- **静态 `e(int)`** 圆角半径映射（按 packed-switch）：
  - `type=1` → `16f`
  - `type=2` 或 `type=5` → `20f`
  - `type=7` → `12f`
  - `type=8` → `22f`
  - `type=10` → `16f`
  - `type=11` → `22f`
  - `type=0` → 抛 NPE（`throw null`）
  - 其它 → `14f`
- **`b(View, int type, float)`**：套用圆角/描边/水波纹背景。
- **`c(int, float)`** → 返回 Drawable。
- **`d()`** → 返回背景色。
- **`g(int, float, boolean)`** → 生成 `GradientDrawable`。
- **`i(View, Drawable)`** → 记录到 `WeakHashMap`。
- 颜色常量：暗色主色 `~ -0xff4a5e`，亮色主色 `~ -0xff8695`（即 `0xFF00796B`）等。

### `k.1.smali`（类 `k`，`public abstract synthetic`）— API level bridge
静态 bridge 方法：
- `a()` = `WindowInsets$Type.ime()`
- `b()` = `getInsetsIgnoringVisibility`
- `c()` = `WindowMetrics.getBounds`
- `d()` = `WindowMetrics.getWindowInsets`
- `e(View)` = `getWindowInsetsController`
- `f()` = `getCurrentWindowMetrics`
- `g(controller, i)` = `hide(i)`
- `h(View)` = `hasOnLongClickListeners`
- `i(insets, i)` = `isVisible`
- `j()` = `WindowInsets$Type.systemBars()`

---

## A.5 类 `w2`（`w2.1.smali`）— 设置/资源辅助（仅列语音库用到的静态方法）

`public abstract`。静态 `a:WeakHashMap`；静态 `b:int = rgb(0,181,162) = 0xFF00B5A2`（clinit 计算）。

| 方法 | 签名 | 语义 |
|---|---|---|
| `a` | `(q2, LinearLayout, TextView, TextView)` | 横向行容器（gravity=16），两按钮各 weight1，中间 1dp 宽 / 28dp 高分隔线，加入父（宽 MATCH_PARENT） |
| `b` | `(q2, LinearLayout, TextView)` | `L2` type4 圆角，minHeight 56dp，padding 16/14/16/14dp，margin L/R 2dp，topMargin 首子 2dp 否则 8dp，bottom 2dp |
| `g` | `(View)` | `focusable(true)` + `p()` + `setAccessibilityDelegate(e2)` |
| `h` | `(View) → Z` | 可访问性可见判断 |
| `i` | `(Context, int) → int` | dp → px：`round(density*dp)` |
| `l` | `(q2, String title, CharSequence desc) → ImageButton` | 48x48dp，padding15dp，FIT_CENTER；图标宿主 `"c_ic_icon_question"`，否则 `0x1080040`；`tint = q2.j`；`L2` type12；`contentDescription = title + "，查看说明"`；onClick `G1(q2, desc, title, 0)` |
| `n` | `(Context, String, int sizePx, int color) → TextView` | **核心 TextView 工厂**：new TextView、setText、setTextSize、setTextColor |
| `o` | `(Context) → SharedPreferences` | 名 `"dev.local.soulhookdemo.public.settings.v1"` |
| `p` | `(View)` | 按压反馈（前景/背景） |
| `q` | `(int base, boolean dark, int... refs) → int` | 在 base 与 `(dark ? 白 : 黑)` 之间找满足对比度 `>= 4.5` 的插值色；无则返回目标色 |

（`c,d,e,f,j,k,m,r,s,t,u,v,w,x` 等未在本任务逐条展开。）

---

# B. 消息转发功能域

主题：长按消息菜单 → 「转发」→ 选择会话列表（最多 9 人）→ 确认 → 发送。

## B.1 类 `a1`（`a1.1.smali`）— 转发 Hooker

`public final synthetic`，implements `XposedInterface$Hooker`。
字段：`a:int`（分派 index）、`b:d1`。

`intercept(Chain)` 按 `a` packed-switch 分派：

| index | 分支 | 宿主方法 | 行为 |
|---|---|---|---|
| default(5) | `:ondestroy` | `onDestroy` | 清理逻辑（见下） |
| 0 | `:pswitch_1ba` | `F(int)` 入口 | 判断 `f(activity)`；取 `getArg(0)` cast `Integer` → `d1.m(activity, int)`；返回 `null` |
| 1 | `:pswitch_13b` | `loadConversationList(List,List,boolean)` | 过滤会话列表后再 `proceed` |
| 2 | `:pswitch_8b` | 选择页 UI（`init`/`F`/`onClick` 相关） | 先 `proceed`，再改造 UI |
| 3 | `:pswitch_6b` | `E(View)` | `p/` 调 `d1.b(activity)` |
| 4 | `:pswitch_53` | `D(Object)`/`onClick` | 有 token → 返回 null（拦截），否则 `proceed` |

**default(5) — onDestroy 清理**
1. 取 `thisObject` 并 cast 为 Activity。
2. 若 `a.isInstance(it)` 且 `f(activity)`（intent 含 token extra）且 `!isChangingConfigurations()` 且 `d1.r != null` 且 `intent.getStringExtra("dev.local.newsoul.message_forward_token").equals(d1.r)` → 清 `d1.s`、`d1.r`。
3. 若 `d1.t`（Dialog）非空且其 `getContext()==activity` → `dismiss()` 并清空 `d1.t`。
4. `proceed()`。

**case0（`:pswitch_1ba`）**
- `thisObject` cast Activity；`!f(activity)` → `proceed()`。
- 否则 `getArg(0)` cast `Integer` → `d1.m(activity, int)`；返回 `null`。

**case1（`:pswitch_13b`）**
- `thisObject` cast Activity；`!f` → `proceed()`。
- 否则 new `ArrayList`；`e1 = d1.i(activity)`。
- 遍历 `chain.getArg(1)`（List），每项反射 `"conversation"`、`"user"`；反射 `conversation.getToUserId()` / `getChatType()`；`e1.c(toUserId, chatType)` 为真则加入结果列表。
- `getArgs().toArray()` 替换 `[1]` = 过滤后列表；`proceed(args)`。

**case2（`:pswitch_8b`）**
1. 先 `proceed()`。
2. `thisObject` cast Activity；若 `!f` → 结束。
3. `e1 = d1.i(activity)`；为 null → `activity.finish()`。
4. 反射字段 `"b"`（adapter）→ `getClass().getMethod("k", boolean)` → `invoke(adapter, TRUE)`。
5. `Proxy.newProxyInstance(d1.c.getClassLoader(), {d1.c}, new V0(d1, activity))`；`d1.e.invoke(adapter, proxy)`（`setOnItemClickListener`）。
6. 隐藏宿主资源 id `{ic_user_follow, tv_mutily_choose, tv_share}`（`setVisibility(8)`）。
7. 反射字段 `"e"` 得 View，`setVisibility(0)`，`setOnClickListener(new C0(d1, activity, 1))`。
8. `d1.n(activity)`。
9. catch → `d1.c(activity, t)` + `activity.finish()`。

**case3（`:pswitch_6b`）**：`thisObject` cast Activity；`!f` → `proceed()`；否则 `d1.b(activity)`（try/catch → `d1.c`）；返回 `null`。

**case4（`:pswitch_53`）**：`thisObject` cast Activity；`f(activity)` 为真 → 返回 `null`，否则 `proceed()`。

## B.2 类 `e1`（`e1.1.smali`）— 转发上下文数据

`public final`。
字段：`a:String`（转发人/来源 id）、`b:String`（目标会话 id）、`c:int`（消息类型）、`d:Object`（内容）、`e:J`（创建时间戳）、`f:Z`（已消费）。

构造器 `(String p1, String p2, int p3, Object p4, long p5)`：
- 校验 `p1`/`p2` 非空、`p4 != null`、`p3 ∈ {1,2,3,5}`，否则抛 `IllegalArgumentException("Invalid forward")`。

方法：
- **`a(String p1, ArrayList p2, long p3)` 同步**：`b(p1,p3)` 为真且 list 非空且 `size<=9` → 用 `LinkedHashSet` 去重（每项 `c(x,0)` 需真且未重复），成功置 `f=true` 返回 `ArrayList(set)`；否则返回 `null`。
- **`b(String, long)`**：`!f` 且 `a.equals(p1)` 且 `p2 >= e` 且 `(p2-e) <= 0x927c0`（**600000ms = 10 分钟**）→ true。
- **`c(String, int)`**：`p2==0` 且 `p1` 非空 且 `a != p1` 且 `b != p1` → true（过滤非法/自身）。

## B.3 类 `d1`（`d1.1.smali`）— 转发核心（运行时方法）

`public final`。字段：`a:Class`（`SelectConversationActivity`）、`b:Class`（`OnClickConfirmListener`）、`c:Class`（`OnItemClickListener`）、`d:Constructor`、`e~q:Method`、`r:String`(token)、`s:e1`、`t:Dialog`、`u:Z`（初始化成功标志）。

### 静态方法

- **`a(Object, String)`** = `getMethod(name).invoke`。
- **`c(Activity, Throwable)`**：`V.c(t)` → `V.d("message.forward.failed", s)` → Toast `"转发未完成，请重新选择消息"`。
- **`d(Object, String)`**：逐级 `getDeclaredField` 向上找字段并 `setAccessible` 取值；找不到抛 `NoSuchFieldException(name)`。
- **`f(Activity)`**：`intent.hasExtra("dev.local.newsoul.message_forward_token")`。
- **`h(Activity, e1)`**：构建 `LinkedHashMap<toUserId, 显示名>`（取 `presenter.m0` 的 values，遍历 `"b".getAllData()`，匹配 `conversation.getToUserId/getChatType`，`e1.c` 校验，取 `user.alias`，否则 `user.signature`，否则 `"所选用户"`；异常抛 `IllegalStateException("Recipient changed")` / `("Invalid selection")`）。
- **`j(Activity)`**：反射 `"presenter"` → `"m0"` cast `Map`。
- **`l(Context, String)`**：Toast（`LENGTH_SHORT`）。
- **`n(Activity)`**：反射字段 `"e"` 的 TextView `setText(size>0 ? "完成(n)" : "完成")`，`enabled(size>0)`，`alpha = 1.0/0.5`。

### `e(XposedModule, ClassLoader)` — 初始化 / 反射绑定表

绑定（宿主类/方法）：

| 字段 | 绑定目标 |
|---|---|
| `a` | `cn.soulapp.android.component.chat.SelectConversationActivity` |
| `b` | `cn.soulapp.android.chat.listener.OnClickConfirmListener` |
| `d` | `cn.soulapp.android.component.chat.dialog.j1` 构造器 `(Context, String)` |
| `f` | `j1.d(OnClickConfirmListener)` |
| `g` | `cn.soulapp.imlib.j0.g()`（当前账号 getter，无参） |
| `h` | `cn.soulapp.imlib.msg.chat.ChatMessage.create(String)` |
| `i` | `ChatMessage.setMsgContent(Serializable)` |
| `j` | `ChatMessage.setMsgType(int)` |
| `k` | `ChatMessage.setTransExtMap(Map)` |
| `l` | `cn.soulapp.android.component.chat.utils.y4.u(ChatMessage)` |
| `m` | `cn.soulapp.imlib.msg.ImMessage.createChatSendMsg(ChatMessage, String)` |
| `n` | `cn.soulapp.imlib.i0.v()`（无参） |
| `o` | `cn.soulapp.imlib.i0.o()`（无参） |
| `p` | `cn.soulapp.imlib.ChatManager.q0(ImMessage)` |
| `q` | `uj.k1.g2(UserConversation)` |
| `c` | `com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter$OnItemClickListener` |
| `e` | `cn.soulapp.android.component.chat.adapter.ConversationAdapter.setOnItemClickListener(c)` |

另：`getDeclaredField "b"/"e"` on `a`；`getDeclaredMethod "F"(int)`、`"loadConversationList"(List,List,boolean)`、`"init"(Bundle)`、`"E"(View)`、`"S"()`、`"D"(Object)`、`"onClick"(View)`。

Hook id：`new-soul/message-forward-select`(case0)、`-list`(case1)、`-ui`(case2)、`-complete-<name>`(case3, E/S)、`-navigation-<name>`(case4, D/onClick)、`new-soul/message-forward-cleanup`(onDestroy, case5)；均 `ExceptionMode.PROTECTIVE` + `deoptimize`。

### 运行时方法

**`b(Activity)` — 弹出确认对话框**
1. 若 `isFinishing()`/`isDestroyed()` 或 `t` 正在显示 → 返回。
2. `e1 = i(activity)`；null → Toast `"转发已过期，请重新选择消息"` + `finish()`。
3. `recipients = h(activity, e1)`；空或 `>9` → 返回。
4. 类型文案：`c==1 ? "文字消息" : (c==5 ? "语音" : "照片")`；名字用 `"、"` join。
5. `new j1` 对话框文本 = `"将这条" + 类型 + "转发给以下 " + n + " 人？\n\n" + names`。
6. `Proxy` 实现 `OnClickConfirmListener`（`new b1(...)`），`f.invoke(dialog, proxy)`；`dialog.setOnDismissListener(c1)`；`show()`。
7. `V.d("message.forward.confirmation", "type=" + c + "; targets=" + n)`。

**`g(Activity, String, String, int, Object)` — 打开选择页**
1. 若 `!u` 或 `isFinishing()`/`isDestroyed()` 或 `!String.equals(g.invoke())` → 抛 `IllegalStateException("Forward unavailable")`。
2. `s = new e1(p2, p3, p4, V.c(p4, p5), SystemClock.elapsedRealtime())`；`r = UUID`。
3. `Intent(activity, a)`；putExtra `token` / `isChoice=false` / `isFromPreview=true` / `onlyFinishAfterShare=true`；`startActivity`。
4. `V.d("message.forward.picker_open", "type=" + p4)`。
5. catch `RuntimeException` → 清 `s`/`r` 并 rethrow。

**`i(Activity) → e1`**：`s` 与 `r` 非空 且 `r.equals(intent token)` 且 `s.b(g.invoke(), elapsedRealtime)` → 返回 `s`；否则 null。

**`k(e1, String)` — 构造并发送消息**
1. `cm = h.invoke(null, msg)`（`ChatMessage.create(msg)`）。
2. `i.invoke(cm, V.c(e1.c, e1.d))`（setMsgContent）。
3. `k.invoke(cm, new HashMap())`（setTransExtMap）。
4. `j.invoke(cm, e1.c)`（setMsgType）。
5. `l.invoke(null, cm)`（`y4.u`）。
6. `p1 = m.invoke(null, cm, msg文本)`（`createChatSendMsg`）。
7. `p.invoke(o.invoke(n.invoke()), p1)`（发送）。

**`m(Activity, int)` — 选中/取消选中一个会话**
1. `i(activity)` null → Toast `"转发已过期，请重新选择消息"` + `finish()`。
2. 反射 `"b".getAllData()` 列表，索引越界/字段空 → 返回。
3. 取 `conversation.getToUserId/getChatType`，`e1.c` 为 false → 返回。
4. `j(activity)` Map 已含 value 或 `size>=9` 且不含 → Toast `"最多选择9人"`。
5. `q.invoke(presenter, p2)` 加该项；`notifyDataSetChanged()`；`n(activity)`。
6. `V.d("message.forward.selection", "count=" + size)`。

---

# C. 复读功能域（补充）

## C.1 `a.1.smali` 的 `:pswitch_278`（case `0xb`）+ 类 `u`

**确认结论：成立。**

- 该分支属类 `a`（Hooker），`index = 0xb`（11）。分派表 `:pswitch_data_52e` 中第 11 项（从 0 起）指向 `:pswitch_278`。
- 由 `F.h` 的注册处（`F.smali` 行 1894-1924）确认：`a` 类 index `0xb` 被挂到宿主方法
  **`cn.soulapp.android.component.chat.fragment.ConversationFragment.ac(View, ImMessage, int)`**
  （`getDeclaredMethod("ac", {View, ImMessage, Integer.TYPE})`，hook id `"new-soul/repeat-context"`，`PROTECTIVE`）。
- 分支行为（逐条）：
  1. `v0 = chain.getThisObject()`（即 ConversationFragment 实例）。
  2. `v2 = chain.getArg(1)`（ImMessage）。
  3. `v3 = chain.getArg(0)`（View）。
  4. `v4 = new u(v0, v2, v3)`（构造即把自身压入类 `u` 的 `ThreadLocal` 栈）。
  5. `try { r = chain.proceed(); } finally { u.close(); }` —— 异常路径用 `addSuppressed` 后 `throw`。
- 即：**把 `(getThisObject(), getArg(1), getArg(0))` 压入 `u` 的 `ThreadLocal` 栈 → proceed → finally `close()`**，与任务描述一致。

**类 `u`（`u.1.smali`）**
- `public final`，implements `AutoCloseable`。
- 静态 `g:ThreadLocal`；实例 `a,b,c:Object`（即 thisObject / ImMessage / View）、`d:u`（上一个栈帧）、`e:Z`、`f:Z`（已关闭标志）。
- 构造器 `(Object,Object,Object)`：保存 `a/b/c`；`d = g.get()`（前一帧）；`g.set(this)`（压栈）。
- `close()`：幂等（`f` 已置真则返回）；`f=true`；若 `d==null` → `g.remove()`，否则 `g.set(d)`（恢复上一帧）。

## C.2 宿主方法 `u0.p` 与 `u0.k` 的完整签名

来源：`F.smali` 初始化段（行 1783-1889），`v23 = cn.soulapp.android.component.chat.utils.u0`（行 1449-1472）。

**`u0.p`** — 注册 id `"new-soul/repeat-click"`，hooker `C` index 0，日志 `"repeat.click"`。
- 参数类型数组（按 `filled-new-array/range {v12 .. v20}`，行 1811）：
  | # | 类型 | 依据 |
  |---|---|---|
  | 0 | `ImMessage` | `v12 = v7` |
  | 1 | `ConversationFragment` | `v13 = v24` |
  | 2 | `Context` | `const-class v14` |
  | 3 | `uj.c0` | `loadClass("uj.c0")` |
  | 4 | `View` | `v16 = v3` |
  | 5 | `int` | `v17 = Integer.TYPE` |
  | 6 | `View` | `const-class v18` |
  | 7 | `ChatLongClickPopUp$a` | `v19 = 旧 v16 = ChatLongClickPopUp$a` |
  | 8 | `int` | `v20 = Integer.TYPE` |
- 即 **`u0.p(ImMessage, ConversationFragment, Context, uj.c0, View, int, View, ChatLongClickPopUp$a, int)`**（返回类型未在 smali 中体现，推断 void）。
- **交叉验证**：`C.smali` case 0（该 hook 的处理体）取 **`getArg(7)`**（行 231-233 `const/4 v1, 0x7`），并对其调用 `F.c`（= `ChatLongClickPopUp$a.b()`，返回 `int` action code），与上表 index 7 = `ChatLongClickPopUp$a` 完全吻合。

**`u0.k`** — 注册 id `"new-soul/repeat-menu"`，hooker `C` index 1，日志 `"repeat.menu"`。
- `getDeclaredMethod("k", {ConversationFragment, View, ImMessage})`（行 1851-1863：`v8=v24=ConversationFragment`、`v13=View`、`v7=ImMessage`）。
- 即 **`u0.k(ConversationFragment, View, ImMessage)`**。
- 该 index=1 落到 `C.smali` 的 **default 分支**（`packed-switch` 只有 case 0）：`repeat.menu.enter` 流程 —— 取 `getArg(2)`（= ImMessage）经 `Q0.O` → String，`proceed()` 后 `F.a(result, getArg(0), getArg(2))`。

> 不确定项：`u0.p` 返回类型、参数名无法从 smali 得到；`uj.c0` 的具体用途未展开（推断为某上下文/回调类型）。

## C.3 `C.smali` case 0 与 `D.smali` case 0

**`C.smali`（类 `C`，`implements Hooker`）**

- 字段 `a:int`、`b:F`。
- `packed-switch`（`:pswitch_data_164`）**只有 case 0 → `:pswitch_9d`**；其余落入 default 分支。
- **case 0（`:pswitch_9d`）= `u0.p` 的处理体**：
  1. **`v1 = getArg(7)`**（行 231-233，`const/4 v1, 0x7`）—— **确认 case 0 用 `getArg(7)`**。
  2. `v1 == null` → `proceed()`。
  3. `v2 = F.c.invoke(v1, null)` → `int`（`ChatLongClickPopUp$a.b()`）。
  4. 若 `v2 ∉ {0x4e5301, 0x4e5302, 0x4e5303}` → `proceed()`。
  5. 否则埋点 `"repeat.action.enter"`（`"action=" + v2`），从 `F.a`（WeakHashMap）按 `v1` 取 `E`；为空或其 `e` 已处理 → 返回 null。
  6. 置 `E.e=true`；按 action：
     - `0x4e5303` → `F.k(E)` 取 Activity；`d3.h(E.a, F.d(F.b(E,"getChatMessage")))`（保存语音路径）。
     - `0x4e5302` → `F.k(E)` 取 Activity；取 `getChatMessage`、`getMsgType`、`F.d(...)`；调 `d1.g(activity, E.c, E.d, msgType, content)`（**转发**路径）。
     - 其它（`0x4e5301`）→ `F.j(E)`。
  7. catch → `"repeat.action.failed"` + `Log.w("SoulHook102", "repeat failed; no automatic retry")` + 按 action 弹 Toast：`0x4e5303`→`"无法保存语音，请重新选择"`、`0x4e5302`→`"无法打开转发，请重新选择"`、其它→`"复读未完成，请检查消息状态"`。
- **default 分支**（`u0.k` index=1）：`"repeat.menu.enter"` —— `getArg(2)` → `Q0.O` 得 source；`proceed()`；`F.a(result, getArg(0), getArg(2))`；失败埋点 `"repeat.menu.skipped"` / `"repeat.menu.host_failed"`。

**`D.smali`（类 `D`，`implements Hooker`）**

- 字段 `a:int`、`b:Object`（= `F`）、`c:Object`（= 宿主 `ChatLongClickPopUp` 类）。
- 构造 `(Object,Object,int)`；注册在 `F.smali` 行 2033-2041（hook id `"new-soul/action-marks-chat"`，`PROTECTIVE`），挂到扫描得到的 **菜单渲染器方法**（`ChatLongClickPopUp` 中「返回类型 == ChatLongClickPopUp 且参数匹配」的方法）。
- `packed-switch`（`:pswitch_data_1ee`）**只有 case 0 → `:pswitch_3d`**；其余为 default（`block_splash` 拦截逻辑，非本任务）。
- **case 0（`:pswitch_3d`）**：
  1. `v0 = getArg(0)`。
  2. **`v9 = F.i(v0)`**（行 184-188）—— **确认 case 0 用 `F.i(getArg(0))`**。
  3. `F.i` 的绑定：`F.smali` 行 1561-1575 → **`cn.soulapp.android.component.chat.utils.y4.p1(ImMessage)`**（`getDeclaredMethod("p1", {ImMessage})` 并 `setAccessible(true)`）。
  4. `A.b(v9)`；若 `v9 == v0` → 普通 `proceed()`，否则 `proceed(new Object[]{v9})`（用替换后的单参数数组继续）。
  5. 之后尝试 `cast v9 → List`，遍历用 `F.c` 取每项 action code；对 `0x4e5303` 项套 `e3` 自定义 Drawable 并 `j1.b(view, Z)`；埋点 `"repeat.render"` / `"repeat.render.grid"`；失败 `"repeat.render.failed"` + `Log.w("SoulHook102", "chat action marks unavailable")`。

> 不确定项：`F.i` 绑定为 `y4.p1(ImMessage)`，但被 hook 的「菜单渲染器」参数在反射处表现为 `{List}`，两者类型不吻合。最佳推断：`p1` 的实际宿主参数为集合/ImMessage 之一，smali 反射绑定取的是 `ImMessage` 形参；**该项标注为「不确定」**，建议复刻时以运行时实际签名（`jadx` 反编译宿主 `y4`）为准。

---

# 不确定项汇总

1. **`x1.d()` 的 `substring(0, len-5)`**：已由 `f3.smali`（过滤 `.meta`）澄清——`len-5` 正好剥掉 `".meta"`（5 字符），故 d() 扫描的是 `.meta` 文件，id 取到 UUID。**已解决**。
2. **`m1` 构造器参数语义**：5 个 Object 参数中 `b=this`，`c`/`f` 与 `P2`/`c3` 回调相关，其余为对话框/按钮引用（`b3.a` 传 `null` 占位）。编号 `a=5` 对应「保存语音」异步过程。**部分不确定**。
3. **`b3.a` 中 `new m1(this, p1, null, null, p2, 5)`** 的 `null` 占位（原本可能是按钮/对话框引用，因上下文不需要而传 null）。
4. **`u0.p` 返回类型 / 参数名**：smali 仅给出 9 个形参类型数组，无返回类型与参数名。**不确定**。
5. **`F.i` ↔ 菜单渲染器参数类型不一致**（见 C.3 备注）。**不确定**，需以宿主反编译为准。
6. **`L2.e(int)` 圆角映射** 中 `type=0` 走 `throw null`（原 Java 为 `switch` 无 default 的编译产物），复刻时按「不合法入参」处理。
7. A 域辅助类 `A0.case2` 的外链 `https://t.me/+3Hl2lJ1X-oZlZTZl`、`C0.case2` 的 `"New Soul 功能兼容性检测"` 等属其它功能域，此处仅作交叉引用。