# New Soul 1.34.0 — 重建工程（二次开发基线）

这是从 `new-soul-1.34.0.apk` 逆向还原出的 **LSPosed 模块重建工程**。
架构、功能开关、hook 点、宿主目标全部来自原始字节码证据，可读实现按现代 Java 重写。

---

## 1. 与原版的关系

| 项目 | 原版 APK | 本重建工程 |
|---|---|---|
| 包名 | `dev.local.newsoul.public` | 相同 |
| LSPosed API | 102 | 相同 |
| 宿主 | `cn.soulapp.android`（Soul 6.37.0） | 相同 |
| 入口 | `dev.local.newsoul.SoulHook` | 相同 |
| 功能开关 | 44 个 SharedPreferences 键 | 相同键名 |
| hook ID | 59 个 `new-soul/*` | 相同 ID |
| 宿主目标类 | 96 个（反射加载） | 相同字符串 |
| 实现 | 混淆为 a/b/c，216 类 1766 方法 | 语义命名，12 个类 |

**本工程不是原版的逐字节复刻**，而是**行为等价的重写**：
- 保留了全部外部契约（包名、键名、hook ID、宿主类名、加密格式）
- 内部结构按可维护性重新组织
- 原版里"遍历候选方法名 + 参数类型断言"的自适应机制被保留并显式化

---

## 2. 目录结构

```
reconstruction/
├── build.gradle                  # 根 Gradle
├── settings.gradle
├── app/
│   ├── build.gradle              # compileSdk 35, minSdk 26, libxposed api:100
│   └── src/main/
│       ├── AndroidManifest.xml   # 无权限、无 Activity
│       ├── assets/
│       │   ├── xposed_init       # 模块入口声明
│       │   └── scope.list        # cn.soulapp.android
│       └── java/dev/local/newsoul/
│           ├── SoulHook.java          # 入口：生命周期 + 安装调度
│           ├── Host.java              # 宿主常量（96 个类名 + 方法候选）
│           ├── Reflect.java           # 反射原语（查找/读写/调用）
│           ├── HostViews.java         # 宿主 View 定位
│           ├── FeatureCatalog.java    # 44 个开关 + 配置导入导出
│           ├── HookRegistry.java      # 12 个 install* 安装器
│           ├── Hookers.java           # 全部 Hooker 实现
│           ├── SettingsEntryHooker.java
│           ├── SettingsUi.java        # 设置面板
│           ├── ToggleSwitch.java      # 自定义开关
│           ├── FeedFilter.java        # 信息流过滤引擎
│           ├── ProfileDisguise.java   # 主页计数伪装
│           ├── VoiceLibrary.java      # 语音库
│           ├── VoiceIo.java           # 语音库 IO
│           ├── HybridCrypto.java      # 混合加密 + 加密诊断导出
│           └── ContextHolder.java     # Context 持有者
```

---

## 3. 构建

```powershell
cd E:\student\NX\new-soul-1.34.0\reconstruction
gradle assembleRelease
# 产物：app/build/outputs/apk/release/app-release.apk
```

依赖只有 `io.github.libxposed:api`（compileOnly，运行时由 LSPosed 提供）。

安装：LSPosed 管理器 → 模块 → 勾选作用域 `cn.soulapp.android` → 强制停止 Soul。

---

## 4. 二次开发指南

### 4.1 加一个新功能开关

三步，不用碰其他代码：

**1)** `FeatureCatalog.java` 里加键名：
```java
public static final String[] MISC = {
        ...,
        "my_new_feature",     // 加这里
};
```

**2)** `HookRegistry.java` 加安装器：
```java
public static void installMyFeature(XposedModule self, ClassLoader cl) {
    Class<?> c = Reflect.loadOrNull(cl, Host.SOME_CLASS);
    if (c == null) return;
    try {
        Method m = Reflect.findMethod(c, new String[]{"someMethod"});
        hook(self, m, "new-soul/my-feature", new MyFeatureHooker());
    } catch (Throwable ignore) {
        // 宿主升级导致签名变化时，只禁用本功能
    }
}
```

**3)** `SoulHook.onPackageReady` 注册：
```java
tryInstall(() -> HookRegistry.installMyFeature(this, cl), "my feature");
```

**4)** `Hookers.java` 写 Hooker：
```java
public static final class MyFeatureHooker implements XposedInterface.Hooker {
    @Override
    public Object intercept(XposedInterface.Chain chain) {
        Object self = chain.getThisObject();
        if (!FeatureCatalog.isEnabled(ctxOf(self), "my_new_feature")) {
            return chain.proceed();          // 开关关着，原样放行
        }
        // ... 改写宿主行为 ...
        return chain.proceed();
    }
}
```

**5)** `SettingsUi.addGroup(...)` 里加一行让用户能看到开关。

### 4.2 加一个新的宿主目标

只改 `Host.java`：

```java
public static final String MY_HOST_CLASS = "cn.soulapp.android.xxx.Yyy";
public static final String[] M_MY_METHOD = {"候选名A", "候选名B"};
```

### 4.3 宿主升级后功能失效怎么修

这是**最常见**的维护场景。原版的设计就是为此准备的：

1. 抓 logcat 看 `SoulHook102` 的日志，会告诉你哪个安装器失败了、失败原因
   （`NoSuchMethodException` / `Ambiguous ...` / `IllegalStateException`）
2. 用 `re_work` 里的工具反编译新版宿主，找新签名：
   ```powershell
   # 见 re_work/README-TOOLS.md
   ```
3. 在 `Host.java` 的候选名数组里加新名字，或改 `Reflect.findByReturn` 的匹配条件
4. **不要**改成硬编码签名 —— 宿主下次升级又会坏

### 4.4 加一个 Hooker 的注意事项

| 要点 | 原因 |
|---|---|
| hook 前 `setAccessible(true)` | 宿主很多是 private/包可见 |
| hook 前 `self.deoptimize(m)` | 否则 JIT 编译过的方法 hook 不上 |
| 一定要 `setId("new-soul/xxx")` | 重载模块时同 ID 才会替换而不是叠加 |
| `setExceptionMode(PROTECTIVE)` | hook 抛异常不拖垮宿主 |
| 不要在 hook 线程做 IO / 网络 | 会 ANR，用 `view.post()` 或线程池 |
| 每个安装器自己 try/catch | 单点失败不波及其他功能 |
| `intercept` 返回 `null` = 继续原逻辑 | 返回非 null = 替换返回值 |

### 4.5 反射查找的三种策略

按优先级从低到高：

```java
// 1. 名字 + 参数类型（最稳）
Reflect.findMethod(c, new String[]{"getMsgContent"}, ChatMessage.class);

// 2. 只给候选名（宿主可能改名，多给几个）
Reflect.findMethod(c, new String[]{"getMsgContent", "getMessage", "getContent"});

// 3. 按返回类型 + 参数个数（宿主连名都改了）
Reflect.findByReturn(c, Bitmap.class, 1);
```

原版在 `Q0.z`、`Q0.E` 里三种都用了，命中多个就抛 `Ambiguous`，
这是**故意**的 —— 宁可禁用功能也不要 hook 错方法。

---

## 5. 与原版行为的已知差异

| 差异 | 说明 |
|---|---|
| 混淆类名 | 原版 `a/b/c/Q0/w2` → 本工程 `Hookers/FeedFilter/HookRegistry/...` |
| 单文件 vs 多文件 | 原版 216 个类挤在一个 dex；本工程按职责拆 12 个类 |
| Hooker 组织 | 原版每个 hook 一个类；本工程集中到 `Hookers.java` 内部类 |
| 诊断日志 | 原版 `V` 类带完整记录+加密导出；本工程只保留加密格式，记录器需自行补 |
| 自更新 | 原版 `S0`/`T0` 走蓝奏云；本工程未实现（建议自行实现并校验签名） |
| 设置 UI | 原版 `w2`/`q2` 有 24 个构件工厂；本工程用原生 View 简化实现 |
| 打赏入口 | 原版用 `createPackageContext` 从自身 APK 读收款码；本工程未实现 |
| `soul-demo/*` 两个 hook | 原版保留为调试通道；本工程未实现 |

---

## 6. 证据链

所有实现依据都在 `../re_work/`：

| 文件 | 内容 |
|---|---|
| `REPORT.md` | 完整逆向报告（架构、44 开关、59 hook ID、安全评估） |
| `NAMING.md` | 语义命名映射表 + 宿主常量全表 |
| `smali_out/` | 全部 216 个类的 baksmali 反汇编（1.98 MB） |
| `java_out/` | 全部 216 个类的 Java 伪代码（自研 smali2java 生成） |
| `featuremap.json` | 每条字符串/类型 → 使用它的方法 |

核对某个功能的原版实现：
```powershell
# 例：反撤回
code "E:\student\NX\new-soul-1.34.0\re_work\java_out\dev\local\newsoul\B\B.java"
```

---

## 7. 免责与合规

- 本工程仅用于**安全研究与学习目的**
- 逆向对象为你本机持有的 APK，分析全程未执行该 APK
- 重新分发模块可能违反 Soul 服务条款与当地法律，请自行评估
- 原版内嵌的是**作者的 RSA 公钥**；如果你要导出发给原作者，才需要保留它。
  自用时请换成自己的密钥对，详见 `HybridCrypto.java` 注释
