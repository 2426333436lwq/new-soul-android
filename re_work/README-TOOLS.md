# 逆向工具链使用说明

本目录的工具用于把 DEX 还原为可读代码，以及宿主升级后重新定位签名。

## 环境

- JDK 17（`C:\Program Files\Java\jdk-17`）
- Python 3.12（DSH 自带）

## 工具清单

| 工具 | 来源 | 用途 |
|---|---|---|
| `tools/baksmali.jar` | Android SDK cmdline-tools | DEX → smali（反汇编） |
| `tools/smali-3.0.3-for-baksmali.jar` | Google Maven | smali → DEX（汇编，改完能重打包） |
| `tools/cfr-0.152.jar` | Maven Central | class → Java（备选） |
| `tools/vineflower.jar` | Maven Central | class → Java（备选） |
| `tools/dexlib2-2.5.2.jar` | Maven Central | DEX 读取 API |
| `dexparse.py` | 自研 | 纯 Python DEX 解析器（MUTF-8 正确解码） |
| `dalvik.py` | 自研 | Dalvik 指令长度表 + 交叉引用提取 |
| `smali2java.py` | 自研 | smali → Java 伪代码 |
| `features2.py` | 自研 | 字符串/类型 → 方法归属 |
| `axml.py` | 自研 | 二进制 AndroidManifest 解码 |

## 常用命令

```powershell
$w = "E:\student\NX\new-soul-1.34.0\re_work"
$base = "C:\Users\刘玮琦\Doubao\chats\2026-09-04\new-chat\usb_tether\android-sdk\cmdline-tools\latest\lib\external"
$tools = "$w\tools"

# classpath（baksmali + smali + 依赖）
$cp = @(
  "$tools\smali-3.0.3-for-baksmali.jar"
  "$tools\baksmali.jar"
  "$tools\antlr-runtime-3.5.3.jar"
  "$base\com\android\tools\smali\smali-baksmali\3.0.3\smali-baksmali-3.0.3.jar"
  "$base\com\android\tools\smali\smali-dexlib2\3.0.3\smali-dexlib2-3.0.3.jar"
  "$base\com\android\tools\smali\smali-util\3.0.3\smali-util-3.0.3.jar"
  "$base\com\beust\jcommander\1.78\jcommander-1.78.jar"
  "$base\com\google\guava\guava\31.1-jre\guava-31.1-jre.jar"
) -join ';'

# ---------- DEX -> smali ----------
java -cp $cp com.android.tools.smali.baksmali.Main d "$w\classes.dex" -o "$w\smali_out"

# ---------- smali -> DEX（改完重打包） ----------
java -cp $cp com.android.tools.smali.smali.Main a "$w\smali_out" -o "$w\patched.dex" --api 26

# ---------- smali -> Java 伪代码 ----------
$py = "E:\Deepseek_Harness\dsh-home\dsh-runtimes\dsh-primary-runtime\dependencies\python\python.exe"
$env:PYTHONIOENCODING = "utf-8"
& $py "$w\smali2java.py" "$w\smali_out" "$w\java_out"

# ---------- 功能归属映射 ----------
& $py "$w\features2.py" "$w\classes.dex" > "$w\feat2.txt"

# ---------- 查某个方法用了什么 ----------
& $py "$w\show.py" "Q0;->z\(" "SoulHook;->onPackageReady"

# ---------- Manifest ----------
& $py "$w\axml.py" "$w\AndroidManifest.bin.xml"
```

## 宿主升级后重新定位签名

```powershell
# 1. 反编译新版宿主 APK 的 dex
java -cp $cp com.android.tools.smali.baksmali.Main d "path\to\new\classes.dex" -o host_smali

# 2. 在 smali 里搜宿主类/方法
Select-String -Path host_smali\cn\soulapp\android\component\chat\helper\MsgLimitManager.smali -Pattern "\.method"

# 3. 对照 re_work/java_out/.../Q0.java 看原版怎么找的，更新 Host.java 候选名
```

## 已知坑（踩过）

1. **路径含中文**：`C:\Users\刘玮琦\...` 传给 java 会因控制台编码失败。
   解决：把 jar 复制到纯 ASCII 路径。
2. **baksmali jar 不是 fat jar**：`java -jar baksmali.jar` 报 "找不到主类"。
   必须用 `-cp` + `com.android.tools.smali.baksmali.Main`，并带上
   jcommander / guava / smali-dexlib2 / smali-util。
3. **smali 汇编器缺 ANTLR**：报 `NoClassDefFoundError: org/antlr/runtime/TokenStream`。
   需加 `antlr-runtime-3.5.3.jar`。
4. **DEX 字符串是 MUTF-8 不是 UTF-8**：直接用 utf-8 解码中文全乱码
   （`检测通过�`）。必须按 UTF-16 代码单元解码并合并代理对。
   见 `dexparse.py` 的 `_decode_mutf8`。
5. **Dalvik `const-string`(0x1a) 长度是 2 个 16-bit unit**：
   误设成 1 会截断操作数，导致**字符串交叉引用全部为 0**。
   见 `dalvik.py` 的 `_fmt` 表。
6. **AXML 属性偏移**：`Res_value` 在属性偏移 +12 处是
   `size(2)+res0(1)+dataType(1)+data(4)`。读错位置所有属性变成不存在的 type 0x08。
7. **Windows 文件名大小写不敏感**：模块同时有 `q0` 和 `Q0` 类，
   直接写会互相覆盖。`smali2java.py` 用 `_case_safe()` 编码路径规避。
8. **GitHub 被墙，Maven Central 通**：jadx 下载不了，改用 baksmali + 自研 decompiler。

## 验证重建保真度

```powershell
# smali -> dex 后与原版比对
$a = (Get-FileHash "$w\classes.dex" -Algorithm SHA256).Hash
$b = (Get-FileHash "$w\rebuilt.dex" -Algorithm SHA256).Hash
# 285796 -> 285748 字节，SHA 不同但仅因字符串池/对齐差异
# 行为等价：用同一个 dex 加载器比对 class/method/string 数量
```
