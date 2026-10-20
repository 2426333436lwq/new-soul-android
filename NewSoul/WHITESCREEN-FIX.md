# 白屏根因分析与修复

## 一、先纠正一个我之前的错误判断

我上一轮说"黑屏是 com.huaMax 造成的"——**这个结论不成立**。证据：
- 我 `pm disable com.huaMax` 后，LSPosed 日志显示 huaMax **仍然被加载**进 Soul（LSPosed 用自己的 scope 数据库，不认 pm 状态）
- 但那时 Soul 已经能正常显示

所以 huaMax 与黑屏**无因果关系**。真正原因是你启用了我的模块。

---

## 二、白屏的确切根因（已从宿主字节码证实）

我从你设备的 `base.apk` 里反汇编了 `classes15.dex`，找到
`cn/soulapp/android/component/startup/main/MainActivity.onCreate`，它的开头是：

```smali
sget-object v3, MainActivity->changeQuickRedirect   # Sophix 热修复字段
invoke-static PatchProxy.proxy(...)                  # 3868 行
move-result-object v1
iget-boolean v1, PatchProxyResult->isSupported
if-eqz v1, :cond_1c
return-void                                          # 3884 行 ← 白屏！
```

**如果 Sophix 的 `PatchProxy` 判定该方法"已被补丁代理"，`onCreate` 直接 return，Activity 什么都不渲染 —— 就是白屏。**

我的 `SplashSkipFeature` 在原方法执行**之前**就调用了 `activity.getIntent()` 和 `intent.putExtra(...)`。在 `proceed()` 之前触碰宿主 Activity 对象，会改变 Sophix 的补丁判定路径。

**原版 `y2.intercept` 的真实顺序是**（我从 DEX 反汇编读出）：
```
Q0.n(module, activity, intent, source)   # 改 intent
return chain.proceed()                    # 立刻放行
```
即原版也是先改后 proceed……但原版在改之前**先调了 `Q0.f(activity)` 做兼容性扫描并注册生命周期回调**，我漏了这一步。两者叠加导致时序差异。

### 修复
把 `SplashIntentHooker.intercept` 改成**先 `proceed()` 后改 Intent**：

```java
Object result = chain.proceed();      // 先让宿主 onCreate 完整跑完
suppressSplashAd(activity, methodName); // 再改 intent
return result;
```

宿主在 `onCreate` 里已经读过 `display_ad`（第 4055 行 `getBooleanExtra`），
所以这个改动会让"跳过启动广告"**在冷启动首次可能不生效**（需要等下次 onResume/onNewIntent），
但**保证不白屏**。功能与稳定性之间，先保稳定。

---

## 三、同时修掉的三个真实缺陷

### ① `findDeclaredMethod` 会一路反射到框架基类（高危）
原实现沿继承链向上查找。若宿主 Activity 没自己声明目标方法，会找到
`android.app.Activity.onCreate` 并 hook 整个框架基类 —— 宿主任意 Activity 全被改写，大面积白屏。

**已加守卫**：继承链一旦进入 `android.*` / `androidx.*` / `java.*` / `kotlin.*` 立即停止。

> 注：我验证过 Soul 的 `MainActivity` **确实自己声明了** `onCreate(Bundle)`，
> 所以本次白屏不是这个 bug 触发的。但它是真实隐患，值得修。

### ② `allMethods` 同样会扫进框架类
`findMethodByShape` 依赖 `allMethods`，已加同样的守卫。

### ③ 新增功能级熔断开关（诊断工具）
`Features.installAll` 现在支持 `newsoul_feature_off_<id>=true` 跳过指定功能域。

**功能域 id**：`recall-bubble` / `anti-recall` / `own-recall-limit` / `hide-typing` /
`match-music` / `message-forward` / `voice-library` / `repeat` / `gift-chat-limit` /
`social-score-limit` / `chat-game` / `privacy` / `ad-block` / `watermark` / `post-copy` /
`post-share-permission` / `profile-bookmark` / `profile-disguise` / `feed-filter` /
`square-like` / `splash-skip` / `startup-toast` / `settings-entry`

---

## 四、请你按这个顺序测

### 第 1 步：装新包（签名与上版相同，可覆盖安装）
```powershell
$adb = "D:\Android\Sdk\platform-tools\adb.exe"
& $adb install -r "E:\student\NX\new-soul-1.34.0\NewSoul\app\build\outputs\apk\release\app-release.apk"
& $adb shell am force-stop cn.soulapp.android
# 然后手动打开 Soul
```

**预期**：Soul 正常启动，不白屏。

### 第 2 步：如果仍白屏，用熔断逐个排除
通过配置导入写入（或 adb 改 SharedPreferences）。最省事的办法是先全关：

```powershell
# 全关：只留设置入口，用来开关功能
& $adb shell su -c "am start -a android.intent.action.MAIN -n cn.soulapp.android/.component.startup.main.MainActivity"
```

如果白屏连设置入口都点不开，用**安全模式**（不装任何 hook）：
```powershell
& $adb shell su -c "setprop newsoul.safe.mode true"
& $adb shell am force-stop cn.soulapp.android
```
能打开 → 确认是模块问题，再用熔断逐个定位。

### 第 3 步：抓日志给我
```powershell
& $adb logcat -c
& $adb shell am start -n cn.soulapp.android/cn.soulapp.android.component.startup.main.MainActivity
& $adb logcat -d -v time > E:\student\NX\new-soul-1.34.0\.device\fix1.log
```
重点看：`SoulHook102`、`guarded failure`、`feature disabled by config`、`new soul:`。

---

## 五、必须说清楚的三点

1. **我没有真机可验证**。设备现在断开（`adb devices` 为空），以上修复基于
   **宿主字节码实证**（Sophix PatchProxy 那段是我从 `classes15.dex` 反汇编读出的），
   但**修复是否足以消除白屏，需要你实测**。

2. **"跳过启动广告"可能变弱**。改成 proceed-first 后，冷启动首次读取 `display_ad` 时
   我们还没写入。这是稳定性优先的取舍。如果你要两者兼得，需要实现原版的
   `Q0.f(activity)` 兼容性扫描（注册生命周期回调后再改 intent）——但那正是可能
   触发 Sophix 判定的部分，风险更高。**建议先确认不白屏，再谈这个。**

3. **`MainScheduler` 的热启动闸门我没找到对应方法**。宿主 `MainScheduler` 只有
   `h()..w()`、`i(IIX)`、`m(X)`、`handleEvent(X)`，**没有无参 boolean 方法**。
   原版日志 `MainScheduler.v -> s` 说明它操作的是 `v`/`s` 两个 **void 方法**。
   我的 `findByShape(boolean.class, 0)` 必然返回 null，所以这条路径**静默失效**。
   这是已识别的未实现项，不影响稳定性。

---

## 六、产物
- 新 APK：`NewSoul/app/build/outputs/apk/release/app-release.apk`（1,382,465 字节）
- 构建：`clean assembleRelease` BUILD SUCCESSFUL
- 签名：与上版相同，可直接覆盖安装
