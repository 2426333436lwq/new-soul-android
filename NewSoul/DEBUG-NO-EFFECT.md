# 「不生效」排查指南

设备当前离线，我无法直接抓日志。**请按下面步骤操作，把日志发我**，这比任何代码猜测都准。

---

## 第一步：先确认模块到底有没有被加载（最关键）

 Soul 启动后，在电脑上执行：

```powershell
$adb = "D:\Android\Sdk\platform-tools\adb.exe"
& $adb logcat -c
& $adb shell am force-stop cn.soulapp.android
& $adb shell am start -n cn.soulapp.android/cn.soulapp.android.component.startup.main.MainActivity
Start-Sleep -Seconds 12
& $adb logcat -d -v time > E:\student\NX\new-soul-1.34.0\.device\diag1.log
```

然后在日志里搜这两行：

```powershell
Select-String -Path "E:\student\NX\new-soul-1.34.0\.device\diag1.log" -Pattern "SoulHook102|newsoul|LSPosedLogDaemon" | Select-Object -First 30
```

**三种可能结果，对应不同结论：**

| 现象 | 结论 | 下一步 |
|---|---|---|
| **完全没有 `SoulHook102`** | 模块没被 LSPosed 加载 | 看第二步 |
| 有 `SoulHook102` 但只有 `module loaded`，没有 `active variant=` | `onPackageReady` 没进 | 看第三步 |
| 有 `active variant=` 和 `hook installation complete` | 模块加载成功，是**具体功能**没生效 | 看第四步 |

---

## 第二步：如果模块没被加载

LSPosed 需要**重启进程**才认新装的模块。请：

1. **完全重启手机**（不是只杀 Soul）
2. 或 LSPosed 管理器里确认模块作用域勾选了 `cn.soulapp.android`
3. 重启后再测一次第一步

> 我核对过 APK 元数据是正确的：
> `META-INF/xposed/module.prop` = `minApiVersion=102 / staticScope=true`
> `scope.list` = `cn.soulapp.android`
> `java_init.list` = `dev.local.newsoul.SoulHook`
> LSPosed 数据库里模块状态 = enabled，作用域 = `cn.soulapp.android`

---

## 第三步：如果卡在 `onPackageReady`

抓日志里搜 `guarded failure` 和 `Skipping duplicate module`。

`Skipping duplicate module` 表示去重属性已被写。你设备上还有**另一个 New Soul 模块**
（`io.github.juanzoran.newsoul` v1.47.1）。虽然系统属性是进程隔离的，
但如果 LSPosed 把两个模块加载进**同一进程**，先到者会占住属性。

**解决办法**：在 LSPosed 里临时禁用 `io.github.juanzoran.newsoul`，只留我的模块，再测。

---

## 第四步：如果模块加载成功但功能没生效

这时要看具体功能。**最可能「看起来没生效」的是这三个**：

### ① 设置入口（功能 6）
只有打开 Soul 的 **H5 设置页**时才出现「New Soul」按钮。主页和聊天页不会有。

验证：Soul → 我 → 右上角设置 → 看标题栏右侧

### ② 跳过启动广告（功能 4）
我上一轮改成了「先 proceed 后改 Intent」。**冷启动首次可能不生效**，
要等第二次进入（onResume/onNewIntent）才生效。这是已知取舍。

### ③ 广场点赞（功能 3）
**触发方式我改成了自动点赞**。`square_auto_like` 默认是 **关闭**的。
要手动开启：设置面板 → 广场 → 自动点赞。

---

## 第五步：怎么打开设置面板

如果连设置面板都找不到，说明功能 6 的入口注入失败。请抓日志搜：

```
Settings entry failed
settings entry unavailable
private hook installed
```

---

## 我已经核对过、确认没问题的部分

| 项 | 状态 |
|---|---|
| APK 含 `META-INF/xposed/` 三个文件 | ✅ |
| `java_init.list` = `dev.local.newsoul.SoulHook` | ✅ |
| `scope.list` = `cn.soulapp.android` | ✅ |
| `SoulHook extends XposedModule`，重写 `onPackageReady` | ✅ |
| `param.getPackageName()` / `isFirstPackage()` 存在于父接口 | ✅（javap 验证） |
| `Hooks.intercept` → `XposedModule.hook()` 调用链 | ✅ |
| LSPosed DB 里模块 enabled=1、scope 正确 | ✅ |
| APK 已签名（v1+v2+v3） | ✅ |

**代码层面我已找不到必然失败的缺陷。** 剩下的可能性都需要运行时日志：
- LSPosed 版本与 `minApiVersion=102` 不兼容
- 宿主进程名判断 `getProcessName()` 与预期不符
- 具体 hook 目标签名不匹配被 Guard 静默降级

---

## 日志发我时请附上

1. `diag1.log`（第一步产出的完整日志）
2. 你测的**具体是哪个功能**
3. Soul 的版本号（我在 `设置 → 关于` 看到的是 6.37.0）
4. 手机是否**完整重启过**
