# New Soul RE · New Soul 1.34.0 逆向与重建工程

对 **New Soul 1.34.0**（`dev.local.newsoul.public`）的静态逆向分析、宿主注入逻辑还原，以及可编译的 LSPosed 模块重建工程。

- **样本**：`new-soul-1.34.0.apk`
  SHA-256 `6681a37eb4f7f145a4070e5a464d74f1f09f458d8f58419cfdc005c5bb316dd8`
- **性质**：Xposed / LSPosed 注入模块（非独立 App），宿主为 `cn.soulapp.android`（Soul App）
- **入口**：`dev.local.newsoul.SoulHook`（`META-INF/xposed/java_init.list`）
- **框架 API**：`io.github.libxposed.api`（API 102，`staticScope=true`，`exceptionMode=protective`）

## 目录结构

```
new-soul-re/
├── re_work/                  # 逆向工作区：解析脚本、DEX/AXML 工具、分析结论
│   ├── REPORT.md             # ★ 主分析报告（611 行，按证据等级标注）
│   ├── SEMANTICS.md          # 语义还原
│   ├── NAMING.md             # 混淆类名映射
│   ├── DELEGATES.md          # 委托关系
│   ├── TASK4_SPEC.md         # 重建规格
│   └── *.py                  # 自研 DEX / AXML 解析与 patch 脚本
├── NewSoul/                  # 可编译的 Gradle 重建工程
│   ├── ACCEPTANCE.md         # 验收标准
│   ├── VERIFIED-ON-DEVICE.md # 真机验证记录
│   ├── BLACKSCREEN-FIX.md
│   └── WHITESCREEN-FIX.md
├── reconstruction/           # 第二版重建工程（含 verify/）
├── newsoul-reverse-analysis/ # 可视化分析报告（HTML）
├── _legacy-20260927/         # 早期阶段产物（jadx/cfr 反编译、旧分析报告）
├── .device/                  # 真机抓取的截图与日志（本地留档，不入库）
├── base.apk                  # 宿主 Soul App 样本（164 MB，不入库）
└── new-soul-1.34.0.apk       # 被分析的模块样本（1.6 MB，不入库）
```

## 证据等级约定

报告中所有结论按三级标注，请勿混用：

| 标记 | 含义 |
|---|---|
| **【观测】** | DEX 字符串表 / 方法表 / 类表 / 资源中字面存在的证据 |
| **【推断】** | 基于观测证据的方法体调用关系推导，未执行 |
| **【未知】** | 当前静态证据无法确定，需运行时验证 |

## 重要限制

本分析**未执行** APK 本体、未连接真实设备，宿主方法签名与 hook 行为均为静态推导。
宿主 `cn.soulapp.android` 的类名以字符串形式存在于 DEX 中并经反射加载，
实际方法签名无法从本 APK 独立验证 —— 模块自身在每个 hook 点做了运行时签名校验。

## 关于样本文件

`*.apk` / `*.dex` / `*.jar` 等二进制样本与反编译转储**不纳入版本控制**
（见 `.gitignore`），保留在本地磁盘。仓库只跟踪分析脚本、结论文档与重建源码。

## 历史

本仓库由原 `project` monorepo 中的 `new-soul-1.34.0` 与独立目录 `NX/new-soul-1.34.0`
两份工程合并而来：NX 版为主体（较新，含重建工程与真机验证），
旧版独有产物收在 `_legacy-20260927/`，巨型反编译转储移至 `archive/new-soul-legacy-dumps/`。
