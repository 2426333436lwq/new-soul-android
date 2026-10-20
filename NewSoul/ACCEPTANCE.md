# New Soul 重建工程 — 验收清单

> 本轮新增 **6 个功能域**（共 23 个已注册），全部通过编译并产出签名 APK。
> 结论基于静态实现与编译验证；**运行时行为需要你在真机上验收**。

---

## 0. 产物

| 项目 | 值 |
|---|---|
| APK | `NewSoul/app/build/outputs/apk/release/app-release.apk` |
| 大小 | 1,381,414 字节 |
| 包名 | `dev.local.newsoul.public` (versionCode 88 / versionName 1.34.0) |
| 签名 | `CN=New Soul, OU=Dev, O=NewSoul` — SHA-256 `81e7eb38a387ff9d69c922e8031af420c09f002d33847d2f51871e8959eabc88` |
| 模块声明 | `minApiVersion=102` `targetApiVersion=102` `staticScope=true` `exceptionMode=protective` |
| 作用域 | `cn.soulapp.android` |
| 入口 | `dev.local.newsoul.SoulHook` |
| 构建 | Gradle 8.11.1 + JDK 21 + compileSdk 35，`clean assembleRelease` 通过 |

重新构建：
```powershell
$env:JAVA_HOME = "E:\Tools\jdk-21"
$env:ANDROID_HOME = "D:\Android\Sdk"
cd E:\student\NX\new-soul-1.34.0\NewSoul
D:\Tools\gradle-8.11.1\bin\gradle.bat clean assembleRelease
```

---

## 1. 本轮新增功能（6 项）

### ① 功能 6 — 设置入口注入
- **做法**：hook 宿主私有方法 `H5Activity.m1(boolean)`，在你打开 Soul 自身设置页时，
  在标题栏右侧插入「New Soul」按钮
- **验收**：
  1. 装好模块 → 重启 Soul → 进入 **设置** 页
  2. 看右上角是否出现「New Soul」文字按钮
  3. 点击 → 应弹出设置面板
- **可能失败**：宿主 `H5Activity.m1` 签名变化 → logcat 出现
  `settings entry unavailable; check app version`（此时功能降级，不影响其它）
- **锚点资源名**：`topic_title` / `rlWebRoot` / `contentLayout` / `titleLay`

### ② 功能 7 — 配置导入导出
- **做法**：设置面板底部「配置」区，导出→剪贴板，导入←剪贴板，另有恢复默认
- **验收**：
  1. 打开设置面板 → 改几个开关 → 点「导出配置（复制到剪贴板）」
  2. 粘贴到任意文本框，应是 `{"format":"newsoul-config","schemaVersion":1,"settings":{...}}`
  3. 点「恢复默认设置」→ 确认 → 开关应回到默认（如广告全关、防撤回开）
  4. 再把刚才复制的 JSON 贴回系统剪贴板 → 点「导入配置」→ 开关应恢复
- **为什么走剪贴板**：模块没有自己的 Activity，剪贴板是唯一免权限、免 FileProvider 的稳定通道

### ③ 功能 1 — 广告屏蔽（18 个开关）
- **做法**：三层
  1. **视图层** — hook 21 个 `cn.soulapp.android.ad.*` 视图类，命中后从父容器摘除；
     摘不掉就 `GONE` + 压尺寸为 0（避免留白）
  2. **适配器层** — hook `SquareAdapter` 等的数据方法，从列表剔除广告条目
  3. **请求层** — hook `SoulAdRequestListener.loadAds` 短路
- **验收**：逐个打开 18 个开关，看对应位置广告是否消失
  - `ad_feed` 广场信息流 / `ad_detail` 帖子详情 / `ad_video` 视频
  - `ad_float` 悬浮 / `ad_interstitial` 插屏 / `ad_me` 我的页
  - `ad_envelope` 信封消息 / `ad_notice` 系统通知 / `ad_search` 搜索页
- **注意**：默认**全关**（与原模块一致）。新版本新增的广告位，
  开了任意一个广告开关就会被兜底覆盖。

### ④ 功能 4 — 启动广告跳过
- **做法**：不杀 Activity，而是往当前 `Intent` 写 `display_ad=false`，
  宿主自己的启动逻辑据此跳过（与原 `Q0.n` 一致，不会打乱任务栈）
- **三条路径**：冷启动 `MainActivity.onCreate` / 重入 `onNewIntent` / 热启动 `MainScheduler` 闸门
- **验收**：
  1. 开 `block_splash` → 完全杀掉 Soul → 重新启动 → 应无启动广告页
  2. 从后台切回（热启动）→ 也不应弹广告
- **默认开启**
- **可能失败**：`MainScheduler` 闸门方法名不可知 → 只失去热启动路径，冷启动仍生效

### ⑤ 功能 2 — 信息流过滤 / 功能 3 — 广场点赞
**信息流过滤**
- **做法**：hook `BaseSquareViewHolder` 的 attach/bind/recycle + `SquareAdapter.onBindViewHolder`；
  按性别 + 屏蔽词判定，命中则 `GONE` + 压尺寸；**回收时复位**（否则 ViewHolder 复用会把隐藏状态带给下一项）
- **验收**：
  1. 开 `feed_filter_enabled` → 设 `feed_filter_gender`（面板里目前是固定值，改键需导出 JSON 编辑后导入）
  2. 广场应只显示对应性别帖子
  3. 开 `feed_keywords_enabled_v1` → 导入含 `feed_keywords_v1` 的配置（用 `\n` 分隔词）→ 含该词的帖子消失
- **注意**：性别未知的帖子由 `feed_filter_keep_unknown` 决定保留与否

**广场点赞**
- **做法**：资源名 `pager_square` 定位广场，`key_item_post` 找帖子，`iv_like` 找赞按钮
- **三种范围**：`square_visible_like` 可见卡片 / `square_like_all_loaded` 已加载全部 /
  `square_auto_like` 滚动自动
- **验收**：
  1. 开 `square_visible_like` → 进广场 → **长按**广场区域 → 应提示「N 条点赞，已完成」
  2. 开 `square_auto_like` → 滚动广场 → 应自动点赞（提示间隔 2.5 秒防抖）
  3. 被过滤隐藏的帖子**不参与**点赞
- **注意**：触发方式我用了**长按广场容器**而不是原版的悬浮按钮 ——
  `SquareFloatingButton` 的签名在各版本不稳，`pager_square` 资源名可靠得多

### ⑥ 功能 5 — 骰子 / 猜拳
- **做法**：`ThreadLocal` 传递「本次出什么」，发送前写入宿主 `DiceFingerMsg` 内容字段
- **开关**：`game_dice` / `game_finger`（默认开启）
- **验收**：
  1. 在聊天里发骰子 → 结果应可控（需配合选择器 UI，见下）
  2. 发猜拳 → 同理
- **⚠️ 这是本轮最不完整的一项**：结果注入通道（`GameResultFeature.applyPending`）已实现，
  但**触发 UI 还没接** —— 原版的手势选择器对话框（`F0.c`，资源名
  `finger_stone`/`finger_scissor`/`finger_cloth`）需要宿主对话框框架配合。
  目前只有 `setPendingResult(int)` 这个 API 可供后续接线。
  **建议下一轮补 UI，或先按"结果由宿主随机、模块不干预"验收。**

---

## 2. 全量功能开关对照（44 项）

| 分组 | 开关 | 本轮状态 |
|---|---|---|
| 广告 18 | `ad_feed` … `ad_reward_web` | ✅ 新增实现 |
| 隐私 3 | `privacy_allow_download/screenshot/repeat` | 已有 |
| 下载 2 | `download_image/video_no_watermark` | 已有 |
| 信息流 3 | `feed_filter_enabled` / `_keep_unknown` / `feed_keywords_enabled_v1` | ✅ 新增实现 |
| 信息流键 2 | `feed_filter_gender` / `feed_keywords_v1` | ✅ 新增实现（值需导入配置改） |
| 广场 3 | `square_visible_like` / `square_like_all_loaded` / `square_auto_like` | ✅ 新增实现 |
| 聊天 7 | `chat_anti_recall` / `chat_own_recall_no_time_limit` / `chat_hide_typing` / `chat_remove_gift_gate` / `chat_remove_bell_gift_gate` / `chat_remove_social_score_limit` / `post_remove_share_limit` | 已有 |
| 启动 4 | `block_splash` ✅新增 / `startup_toast` 已有 / `page_log` 已有 / 广告跳过三路径 ✅新增 | 部分新增 |
| 游戏 2 | `game_dice` / `game_finger` | ⚠️ 注入通道已实现，选择器 UI 未接 |
| 其它 | `match_mute_background_music` | 已有 |

---

## 3. 仍未实现（明确记录）

| Hook ID | 功能 | 原因 |
|---|---|---|
| `new-soul/config-file-result` | 配置文件选择器（SAF） | 剪贴板方案已覆盖核心需求，优先级低 |
| `new-soul/debug-init` | 调试日志记录器 | **按你的决定延后**（加密方案待定） |
| `new-soul/debug-export-result` | 加密日志导出 | **按你的决定延后** |
| `new-soul/bottom-settings-entry` | 宿主「我的」页底部入口 | 已用设置页入口替代 |
| `new-soul/game-picker/` | 骰子/猜拳选择器 UI | 见功能 5 说明 |
| `new-soul/square-like-envelope-*` | 点赞信封动画 | 纯视觉，不影响功能 |
| `new-soul/feed-data/` | 信息流数据层 hook | 视图层已覆盖 |

---

## 4. 验收建议顺序

1. **装模块** → LSPosed 里勾选 `cn.soulapp.android` → 强制停止 Soul
2. **先验设置入口**（功能 6）—— 没有入口后面都没法开开关
3. **再验配置导入导出**（功能 7）—— 确认设置链路通
4. **启动广告跳过**（功能 4）—— 独立、见效快
5. **广告屏蔽**（功能 1）—— 逐个开，记录哪个位置没生效
6. **信息流 + 广场点赞**（功能 2/3）
7. **骰子猜拳**（功能 5）—— 预期只部分可用

### 出问题时抓日志
```powershell
adb logcat -s SoulHook102:V
```
所有安装器失败都会带明确原因（`... unavailable` / `signature changed`），
且**单个功能失败不影响其它功能**（每个安装器独立 try/catch）。

---

## 5. 诚实说明

- **编译通过 ≠ 功能生效**。本工程通过 Gradle 构建验证，但**没有任何运行时验证** ——
  我没有真机、没有安装过 APK。
- **宿主签名适配是主要风险**。所有 hook 都靠「候选方法名 + 参数/返回类型匹配」运行时定位，
  宿主 Soul 版本不对就可能大量降级。日志会明确告诉你哪个失败。
- **广告屏蔽的适配器层可能完全不生效**。原版日志里就有 `"no adapter mutation methods"`，
  说明这层在部分版本上本来就会探测失败；视图层是主力。
- **广场点赞的长按手势是我改的设计**，与原版（悬浮按钮）不同 —— 更稳但操作方式变了。
- **骰子猜拳只做了一半**（见功能 5）。
- 加密调试导出（功能 8）按你的要求**本轮完全没做**。
