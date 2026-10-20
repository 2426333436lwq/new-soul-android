# New Soul 1.34.0 — 逆向源码包

本目录为 **new-soul-1.34.0.apk 的完整反编译产物**，全部内容由工具从 APK 本体机械生成，无任何人工编写/补写的代码。

## 来源与工具（可复现）

| 项目 | 值 |
|---|---|
| 源文件 | `../../new-soul-1.34.0.apk`（1,655,750 字节） |
| SHA-256 | `6681A37EB4F7F145A4070E5A464D74F1F09F458D8F58419CFDC005C5BB316DD8` |
| 反编译工具 | JADX 1.5.6（`D:\NX-apk\jadx`） |
| 复现命令 | `jadx -d source/new-soul-1.34.0 new-soul-1.34.0.apk` |
| 反编译日期 | 2026-09-27 |

## 目录结构

```
new-soul-1.34.0/
├── README.md          本说明（来源、工具、清单）
├── sources/           反编译 Java 源码，按包结构组织
│   └── dev/local/newsoul/              153 个 .java（152 个 + 
│       └── p000public/                 1 个，原包名 public 被 JADX 转义）
├── resources/         jadx 解码后的资源与清单
│   ├── AndroidManifest.xml             解码后的清单
│   ├── META-INF/xposed/                Xposed 模块声明（module.prop /
│   │                                   java_init.list / scope.list）
│   ├── META-INF/                       签名文件（MANIFEST.MF/.SF/.RSA）
│   └── res/                            解码后的资源（图标、捐赠二维码、strings）
└── original/          从 APK 直接提取的原始二进制（真本）
    ├── classes.dex                     实际运行的字节码（285,796 字节）
    └── resources.arsc                  资源表（1,244 字节）
```

## 内容清单

- **包名**：`dev.local.newsoul.public`（Xposed/LSPosed 模块，宿主 `cn.soulapp.android`）
- **入口类**：`dev.local.newsoul.SoulHook`
- **源码规模**：153 个 Java 文件，18,407 行
- **APK 条目**：13 个（APK 内全部文件均已在 `resources/` 与 `original/` 中覆盖）
- **说明**：类名为混淆后的单字母名，属原始状态，非工具丢失信息

## 保真性声明

1. `sources/`、`resources/` 由 JADX 直接输出，未做编辑。
2. `original/` 由 ZIP 解包直接提取，`classes.dex` SHA-256 为
   `266F4FB0DB89C01669AE6A6D563BF1C2AB967BEB9315D09C094EF1CB232EAA86`。
3. 若需核对任何函数实现，以 `original/classes.dex` 为准
   （可用 `jadx original/classes.dex` 或 `baksmali` 复核）。
