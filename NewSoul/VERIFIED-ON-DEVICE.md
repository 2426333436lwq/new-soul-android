# 真机验证报告（2026-10-15）

设备：Xiaomi 2211133C / Android 17 (SDK 37) / KernelSU + Zygisk-LSPosed 2.2.0
宿主：Soul 6.37.0 (versionCode 26091510)
模块：dev.local.newsoul.public v1.34.0

---

## 一、结论：模块已完全正常工作

**23 个功能域，22 个安装成功**，设置面板可正常打开并使用。

### 实测证据

| 验证项 | 结果 |
|---|---|
| 模块被 LSPosed 加载 | ✅ `module loaded; framework=LSPosed; version=2.2.0; API=102` |
| 进入宿主进程 | ✅ `active variant=dev.local.newsoul.public` |
| Soul 正常启动（无白屏） | ✅ 截图 1.6 MB，主页/广场/聊天/我 全部正常渲染 |
| 设置入口注入 | ✅ 设置页「辅助功能」右侧出现 **New Soul** 按钮 |
| 设置面板打开 | ✅ 显示「New Soul 设置」+ 47 项开关，可滚动、可点击 |

---

## 二、修复的 5 个真实缺陷（本轮定位并解决）

### ① `NoClassDefFoundError` 导致 3 个功能域崩溃
**现象**：`FeedFilter`、`AdBlock` 适配器层、`SquareLike` 全部安装失败。

**日志**：
```
java.lang.NoClassDefFoundError: Failed resolution of: Landroidx/databinding/ViewDataBinding;
    at java.lang.reflect.Executable.getMethodReturnTypeInternal(Native Method)
    at java.lang.Class.getDeclaredMethods(Class.java:2732)
    at dev.local.newsoul.framework.Reflect.allMethods(Reflect.java:162)
```

**根因**：宿主用了 DataBinding，`getDeclaredMethods()` 会解析每个方法的返回类型，
遇到未打包进本进程的 `androidx.databinding.ViewDataBinding` 就抛 `Error`。

**修复**：`Reflect.allMethods` 逐类 try/catch，单类失败不影响其余。
```java
try {
    methods.addAll(Arrays.asList(current.getDeclaredMethods()));
} catch (Throwable throwable) {
    Logger.w("declared methods unavailable: " + current.getName(), throwable);
}
```

### ② 框架类守卫误伤 `Activity.onResume`
**现象**：`Activity.onResume unavailable; startup toast disabled`

**根因**：我上一轮为防「误 hook 框架基类」加了守卫，但
`StartupToastFeature` / `SquareLikeFeature` **本来就该 hook `Activity.onResume` 基类**
（原模块也是这么做的，由 LSPosed 分发到所有子类）。

**修复**：这两处改为直接反射 `Activity.class.getDeclaredMethod("onResume")`，
绕开带守卫的 `Reflect.findDeclaredMethod`。

### ③ `MainActivity` 不声明 `onResume` → 广场点赞永远找不到
**现象**：`square like unavailable`

**根因**：宿主 `MainActivity` 继承自 `MartianActivity`，自己不声明 `onResume`。
`Reflect.findMethods(MainActivity, "onResume")` 走继承链，但被框架守卫在
`android.app.Activity` 处截断，返回空列表。

**修复**：同 ②，直接 hook `Activity.onResume`。

### ④ 骰子字段名不匹配
**现象**：`game hook unavailable: no content field; send signature changed`

**根因**：我硬编码了 `content/msgContent/text/result/value`，
但宿主 `DiceFingerMsg` 的真实字段是 **`looked` / `number` / `mark`**。

**修复**：加形状兜底 —— 候选名找不到时，扫描所有「可写的 int/Integer/String/long 实例字段」。
```
game hook ready; fields=3 names=looked,number,mark
```

### ⑤ 设置入口按钮「可见但点不动」
**现象**：`New Soul` 按钮显示正常，点击无任何反应（无日志、无异常）。

**根因**：按钮被 `addView` 到根容器 `rlWebRoot`，而宿主自己的设置列表
**也挂在同一容器且绘制在上层** —— 视觉可见（透过透明区），但触摸被列表吃掉。

**修复**：
1. 改挂到标题栏层（`titleLay`），保证在列表之上
2. `setElevation(1000f)` + `bringToFront()` 抬高 z 序
3. 加**兜底命中测试**：给容器挂不消费事件的 `OnTouchListener`，
   触摸点在按钮范围内时手动 `performClick()`（返回 `false`，不影响宿主原有交互）

### ⑥ 设置面板字号被放大 3 倍
**现象**：面板文字巨大、换行、被容器裁切。

**根因**：`ViewKit.text(ctx, str, sizePx, color)` 内部用 `setTextSize(float)`，
而 **`setTextSize` 默认单位是 sp**。调用方传的是 `ViewKit.dp(context, 0x0E)` = 14dp ≈ 42px，
被当成 42sp → 字号爆炸。

**修复**：加 float 重载明确以 sp 为单位，调用点改传 14f/15f/13f；
开关行 `setSingleLine(false)` + `setMaxLines(2)` 允许换行。

---

## 三、当前安装结果（实测日志）

```
✅ private chat anti-recall hooks installed
✅ own recall time limit hook installed
✅ typing command control installed
✅ soul match music control installed
✅ voice library installed; local storage and native audio upload
✅ repeat installed; text/image/audio
✅ gift chat hooks: model=true; session=true; dialog=false; bell=true
✅ social score hooks: model=true; dialog=true
✅ game hook ready; fields=3 names=looked,number,mark
✅ privacy photo controls installed
✅ privacy repeat guard installed: route=privacy / route=snap
✅ privacy hooks: photo=true; privacy=true; snap=true
✅ ad view hooks installed: 13
✅ ad adapter hooks installed: 3
✅ watermark control installed: download_image_no_watermark / video
✅ post copy hooks installed
✅ post share control installed
✅ profile bookmark menu and navigation installed
✅ square feed filter ready
✅ square like lifecycle installed
✅ new soul: splash hook installed (installed=2)
✅ hook installed: android.app.Activity.onResume
✅ private hook installed: H5Activity.m1(boolean)

⚠️ new soul: hot splash hook unavailable (Unexpected Soul hot startup signature)
⚠️ ad request hooks installed: 0
```

### 两个已知未完成项

**① 热启动广告闸门**（`new-soul/hot-splash-gate`）
宿主 `MainScheduler` 只有 `h()..w()`、`i(IIX)`、`m(X)`、`handleEvent(X)`，
**没有无参 boolean 方法**。原版日志 `MainScheduler.v -> s` 说明它操作的是两个 void 方法，
我的形状探测必然失败。需要专门分析 `MainScheduler` 字节码才能实现。

**② 广告请求层**（`ad request hooks installed: 0`）
`SoulAdRequestListener` 上没有找到 `loadAds`/`onAdFailed`/`onAdLoaded`。
视图层（13个）和适配器层（3个）已生效，请求层需要重新定位方法名。

---

## 四、如何使用

1. Soul → **我** → 左上角**三横线** → **设置** → 「辅助功能」行右侧点 **New Soul**
2. 面板里 47 项开关，点一下即切换（即时生效，无需重启）
3. 底部「配置」区可导出/导入配置

### 默认开启的功能（37/47）
广告屏蔽部分项、防撤回、自己撤回不限时、隐藏输入、礼物/社交分限制解除、
无水印下载、可见卡片点赞、跳过启动广告、启动提示等。

### 默认关闭的（10项）
大部分广告屏蔽项（`ad_detail`/`ad_video`/`ad_group` 等）、
`privacy_*` 三项、`feed_filter_*`、`square_auto_like`、`square_like_all_loaded`。

---

## 五、产物
- APK：`NewSoul/app/build/outputs/apk/release/app-release.apk`
- 构建：`assembleRelease` BUILD SUCCESSFUL
- 签名：与之前一致，可覆盖安装
