# 黑屏问题诊断与修复

## 现象
安装模块后 **Soul 无法打开，黑屏**。

## 根因（已定位，共 4 个）

黑屏 = 模块在宿主启动路径上抛了**未被捕获的异常**，杀死宿主进程。
我审计了全部 20 个 hook 实现，找到 4 个真实缺陷：

### ① Hooker 异常逃逸（系统性、最致命）
**问题**：20 个 `intercept` 里，**15 个没有把整个方法体包在 try/catch 里**。
只要 hook 体内抛任何异常（含 `NoSuchMethodError` / `NoClassDefFoundError` 这类
`Error`），异常就会穿透到宿主调用栈，直接杀进程。

**修复**：在 `Hooks.intercept` 这个**唯一入口**给所有 hooker 套保护壳
（`Hooks.wrap`）。保护壳捕获 `Throwable`（连 `Error` 一起），异常时回落执行宿主原实现：

```java
return chain -> {
    try {
        return hooker.intercept(chain);
    } catch (Throwable t) {
        Guard.report("hook:" + id, t);
        return chain.proceed();   // 回落宿主原逻辑
    }
};
```
即使某个 hooker 自己漏写 try/catch，这一层也能兜住 —— 从"逐个文件改"变成"改一个入口"。

### ② 在布局/绘制关键路径上操作视图树
**问题**：`AdBlockFeature` 在广告视图上 hook 了
`onMeasure` / `onDraw` / `dispatchTouchEvent`，并在回调里
`parent.removeView()`。在布局遍历中摘除视图会让 `ViewGroup` 子视图索引失效，
表现为黑屏或 ANR。

**修复**：
- 只保留 `onAttachedToWindow` 一个 hook 点（唯一"已入树但未参与本轮布局"的安全时机）
- **不再 `removeView`**，改为 `GONE` + 尺寸归零
- 隐藏动作 post 到下一轮消息循环，且先 `chain.proceed()` 放行宿主原逻辑

### ③ 顶掉宿主的监听器
**问题**：`SquareLikeFeature` 在广场容器上 `setOnLongClickListener`,
直接覆盖了宿主自己的长按处理，信息流手势失效。

**修复**：移除容器手势接管。改为只在"自动点赞"开启时挂 `OnGlobalLayoutListener`
——纯增量，不覆盖宿主任何监听器。

同时修掉两个附带问题：
- 不再用 `View.setTag(自造key, ...)` 做标记（宿主 tag 槽位语义未知，可能破坏其 ViewHolder 复用），改用 `WeakHashMap`
- `MAIN.removeCallbacksAndMessages(this)` 传的是监听器对象，**token 永远匹配不上**（无效调用），改为独立 token 的 `removeCallbacks`

### ④ 全局 Context 是死代码
**问题**：`GlobalContextHolder` 定义了但**从未被注册**，`AdBlockFeature` 的适配器路径
拿不到 `Context`。

**修复**：在 `AdBlockFeature.install` 里把宿主提供者登记进 `GlobalContextHolder`；
同时**去掉** `ActivityLifecycleCallbacks` 注册方案（在宿主 `Application` 上注册回调会与
宿主生命周期耦合，时机不当反而拖垮启动）。

---

## 新增的防护

### `Guard`（全局兜底守卫）
- 覆盖**安装器隔离**（每个功能域独立，`Error` 也拦）
- 记录失败次数，同一 key 最多详述 3 次，避免刷爆 logcat 反而拖慢宿主
- 首次出现 `Error` 时输出总体告警：`module may be incompatible with this host build`

### 安全模式（关键！）
宿主打不开时你**没法通过 UI 关模块**。所以给了一条旁路：
只要命中下面任一条件，模块**不安装任何 hook**，行为等同于"装了但不生效"：

1. **系统属性**（推荐，进程外可设）
   ```
   adb shell setprop newsoul.safe.mode true
   adb shell am force-stop cn.soulapp.android
   ```
2. **设置存储键** `newsoul_safe_mode=true`（可通过配置导入写入）

这能用来**确认黑屏是否由模块引起**：开安全模式后 Soul 能正常打开 → 就是模块问题。

---

## 请按这个顺序验证

### 第 1 步：先确认是不是模块的锅
```powershell
$adb = "D:\Android\Sdk\platform-tools\adb.exe"
& $adb shell setprop newsoul.safe.mode true
& $adb shell am force-stop cn.soulapp.android
# 然后手动打开 Soul
```
- **能打开** → 确认是模块问题，继续第 2 步
- **还是黑屏** → 不是本模块引起（可能是 LSPosed 本身或其它模块），先排查环境

### 第 2 步：关安全模式，装新版，抓日志
```powershell
& $adb shell setprop newsoul.safe.mode false
& $adb uninstall dev.local.newsoul.public
& $adb install -r "<新 APK 路径>"
& $adb shell am force-stop cn.soulapp.android
# 先清日志再启动，避免刷屏
& $adb logcat -c
& $adb shell am start -n cn.soulapp.android/.component.startup.main.MainActivity
# 抓 60 秒
& $adb logcat -d -v time > E:\soul_boot.log
```
把 `E:\soul_boot.log` 给我。重点关注：
- 关键字 `SoulHook102`（模块日志）
- 关键字 `guarded failure`（被 Guard 拦下的异常）
- 关键字 `module may be incompatible`
- 任何 `FATAL EXCEPTION` / `AndroidRuntime`

### 第 3 步：如果还黑屏，二分定位
配置导入里写 `newsoul_safe_mode` 不便操作时，可用系统属性配合**逐功能排除**。
但我更建议先看日志 —— `guard` 会把肇事的功能域 id 打出来，不必盲猜。

---

## 我无法替你验证的部分（必须说清楚）

- 我**没有设备**：`adb devices` 为空，无法实机复现黑屏，也无法抓日志。
- 上述 4 个缺陷是**代码审计**发现的确定性缺陷（尤其 ①：15/20 个 hooker 缺异常保护，
  这在任何宿主上都是隐患），但我**不能保证修完就一定不黑屏** ——
  还可能存在第 5 个我没看出来的问题。
- 如果日志显示是**宿主方法签名不匹配**（`NoSuchMethodException` / `NoClassDefFoundError`），
  那说明你装的 Soul 版本不是 6.37.0，需要针对性适配。**日志能直接告诉我这一点**，
  所以第 2 步的日志是关键。

---

## 产品信息
- 新 APK：`NewSoul/app/build/outputs/apk/release/app-release.apk`（1,382,104 字节）
- 构建：Gradle 8.11.1 + JDK 21 + android-35，`clean assembleRelease` 通过
- 签名：`CN=New Soul` SHA-256 `81e7eb38a387ff9d69c922e8031af420c09f002d33847d2f51871e8959eabc88`
  （**与上一版相同**，可直接覆盖安装）
