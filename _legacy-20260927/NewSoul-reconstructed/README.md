# New Soul 1.34.0 — 模块源码还原工程

> 还原对象：`new-soul-1.34.0.apk`（Soul App 的 Xposed/LSPosed 注入模块）
> 包名 `dev.local.newsoul.public` · 目标宿主 `cn.soulapp.android`（Soul）
> 还原日期：2026-09-27

---

## 一、还原结论（先看这里）

- **完整源码已还原**：dex 中共 **216 个类定义**，本工程 `sources/` 目录下 **216 个 Java 文件一一对应，覆盖率 100%**。
- 代码全部来自机器反编译（CFR 0.152 / jadx 1.5.6），**未做任何人工编造、补写或改写**。
- jadx 在 Windows 下因大小写不敏感的文件系统会丢弃 63 个类（如 `C`/`c`、`A1`/`a1` 等大小写孪生类），已用 **CFR 反编译 jar 补齐**（CFR 输出时对冲突类自动加 `_0` 后缀）。
- 内部类（Kotlin lambda 等）已被对应反编译器展开进宿主类文件，类数量以 dex `class_defs` 为准。

## 二、目录结构

```
NewSoul-reconstructed/
├── README.md                    本说明
├── sources/                     ★ 完整 Java 源码（216 类，CFR 0.152，原始混淆类名）
│   └── dev/local/newsoul/*.java
├── sources-jadx/                参考版 Java 源码（153 类，jadx 1.5.6，类名被重命名）
│   └── dev/local/newsoul/*.java
├── resources/                   AndroidManifest.xml + res/ 资源（jadx 还原）
├── META-INF/
│   ├── MANIFEST.MF / NEWSOUL-.RSA / NEWSOUL-.SF    APK 签名与清单
│   └── xposed/                  Xposed 模块声明（module.prop / scope.list / java_init.list）
└── (上级目录)
    ├── _unpacked/               原始 APK 解包（classes.dex、资源、签名）
    ├── _soul_classes.jar        dex2jar 中间产物（216 个 .class，类名完整）
    └── new-soul-1.34.0.apk      原样本
```

## 三、还原工具链与步骤

| 步骤 | 工具 | 产物 |
|---|---|---|
| 解包 APK | PowerShell `Expand-Archive` | `_unpacked/`（含 `classes.dex`） |
| Java 反编译（主） | jadx 1.5.6（`--show-bad-code`） | `sources-jadx/`（153 类） |
| 完整性核对 | androguard 4.1.4 解析 `class_defs` | 确认 dex 共 216 类 |
| Java 反编译（补齐） | dex2jar v2.4 → CFR 0.152 | `sources/`（216 类，完整） |
| 签名/元信息 | 原始解包直接复制 | `META-INF/` |

**为什么需要 CFR 补齐**：jadx 检测到 Windows 文件系统大小写不敏感（`fsCaseSensitive=false`），对大小写孪生的类名（如 `C` 与 `c`）在导出时丢弃了其中一组，共 63 个类（全部为真实业务类：Xposed `intercept(Chain)` 回调、UI 监听器等）。dex2jar 生成的 jar 内部大小写敏感、216 类完整，CFR 从 jar 读取并输出 Java 时对冲突文件名自动加 `_0` 后缀（如 `c_0.java`），因此 **216 个类全部落地**。

## 四、类名体系说明（重要，加工前必读）

原 APK 经过 R8/ProGuard 混淆，**两类源码的类名体系不同**：

| 体系 | 目录 | 类名示例 | 说明 |
|---|---|---|---|
| **原始混淆名** | `sources/`（完整版，**以此为准**） | `a`、`C`、`A1`、`Q0`、`SoulHook` | dex 中真实类名；同目录下大小写冲突者加 `_0` 后缀（如 `c_0.java` = 类 `c`） |
| **jadx 重命名** | `sources-jadx/`（参考版） | `C0000a`、`AbstractC0036j`、`RunnableC0013d0` | jadx 为避免 Windows 文件冲突自动加 `C<数字>` 前缀，并按继承关系加语义前缀（Abstract/Runnable/ViewOnClickListener 等） |

**对照示例**：`sources-jadx/C0000a.java` ⇔ `sources/ 中的 a.java`；`sources-jadx/AbstractC0036j.java` ⇔ `sources/ 中的 j.java`。

> 方法、字段名同样被混淆（单字母），`sources-jadx/` 中 Kotlin 元数据还原较好（如 `SoulBaiduLocationClient` 风格的方法名在 New Soul 模块中表现为 `c()`、`n()` 等装配方法），可交叉对照提高可读性。`SoulHook.java`（模块入口）、`Q0.java`、`Z0.java` 等未重命名，两版同名。

## 五、完整性验证

- dex `class_defs` 计数（androguard 权威解析）：**216**
- `sources/` Java 文件计数：**216**，且逐文件与 dex 类名核对无缺
- 关键入口与功能类均在：`SoulHook`（入口装配）、`Q0`（IM/防撤回）、`Z0`（触摸拦截/点赞）、`F`（复读/转发/语音库）、`B2`（帖子筛选）、`T0`（更新检查）、`V`（调试日志）等
- 资源：`AndroidManifest.xml`（versionCode 88 / versionName 1.34.0 / targetSdk 35）、`res/`、Xposed 元信息（`scope.list` 指向 `cn.soulapp.android`）

## 六、后期加工建议

1. **以 `sources/` 为工作副本**（类名与 dex 一一对应，加工后可直接回编译）。
2. 需要理解某功能时，先在 `sources-jadx/` 找同名/相近类看 jadx 还原（可读性更好），再回到 `sources/` 对应类修改。
3. 修改后重打包流程：`sources/` 编译 → dex 化 → 与 `resources/`、`META-INF/` 重新打包为 APK → 用 `META-INF/NEWSOUL-.RSA` 对应的原签名或自签名重签（模块签名需与 LSPosed 安装方式匹配）。
4. 本工程为静态逆向还原，不含任何虚构内容；混淆导致的语义不确定性以反编译输出为准。

## 七、声明

本工程仅用于技术学习与个人研究。该模块针对 Soul App 注入修改，违反其用户协议，存在账号封禁风险；相关功能涉及他人隐私与平台规则，请勿用于违法违规用途。
