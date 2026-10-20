# -*- coding: utf-8 -*-
"""对比 dex 类方法清单 vs dev 源码树中类体的方法清单，输出缺口"""
import re, io, os, glob

BASE = r"E:\student\project\new-soul-1.34.0"
DEX = os.path.join(BASE, "dev", "_dexdump.txt")
SRC = os.path.join(BASE, "dev", "app", "src", "main", "java")

# ---- 1. dex 方法表 ----
dex_methods = {}   # class -> set(method names)  (不含 <init>)
dex_has_ctor = {}  # class -> bool
dex_sig = {}       # class -> list of "name(params)ret"
lines = io.open(DEX, encoding="utf-16", errors="replace").read().splitlines()
idx = []
pat = re.compile(r"  Class descriptor  : 'Ldev/local/newsoul/(.+);'")
for n, l in enumerate(lines):
    m = pat.match(l)
    if m:
        idx.append((n, m.group(1)))
for j, (n, name) in enumerate(idx):
    end = idx[j + 1][0] if j + 1 < len(idx) else len(lines)
    seg = lines[n:end]
    methods = set()
    has_ctor = False
    section = None
    for l in seg:
        if re.match(r"  Direct methods", l) or re.match(r"  Virtual methods", l):
            section = "m"
            continue
        if re.match(r"  (Static|Instance) fields", l):
            section = "f"
            continue
        if re.match(r"  source_file", l):
            section = None
            continue
        if section == "m":
            m = re.match(r"      name          : '(.+)'", l)
            if m:
                nm = m.group(1)
                if nm == "<init>":
                    has_ctor = True
                elif nm == "<clinit>":
                    pass
                else:
                    methods.add(nm)
    dex_methods[name] = methods
    dex_has_ctor[name] = has_ctor

# ---- 2. java 类体定位 ----
def load(path):
    with io.open(path, encoding="utf-8", errors="replace") as f:
        return f.read()

def find_class_body(src, cname):
    """返回类体文本（含方法），找不到返回 None"""
    m = re.search(r"\bclass\s+" + re.escape(cname) + r"\b[^;{}]*\{", src)
    if not m:
        return None
    i = m.end() - 1  # '{' 位置
    depth = 0
    for k in range(i, len(src)):
        c = src[k]
        if c == '"':
            # 跳过字符串
            k2 = k + 1
            while k2 < len(src):
                if src[k2] == "\\":
                    k2 += 2
                    continue
                if src[k2] == '"':
                    break
                k2 += 1
            # 用下一轮 for 无法跳，改用 while 手动 —— 简化：记录引号状态
            # 此处退回简单处理
        if c == "{":
            depth += 1
        elif c == "}":
            depth -= 1
            if depth == 0:
                return src[i:k + 1]
    return None

files = glob.glob(os.path.join(SRC, "*.java"))
class_files = {}
for fp in files:
    src = load(fp)
    for m in re.finditer(r"(?m)^[^\S\n]*\b(?:public |final |abstract |static |/\* synthetic \*/ )*class\s+(\w+)\b", src):
        cn = m.group(1)
        body = find_class_body(src, cn)
        if body is not None:
            class_files[cn] = (os.path.basename(fp), body, src)

# ---- 3. 对比 ----
report = []
for cn, methods in sorted(dex_methods.items()):
    if cn not in class_files:
        report.append(f"[无类] {cn}")
        continue
    fname, body, _ = class_files[cn]
    missing = []
    for nm in methods:
        # 方法调用形态： 方法名(  —— 排除字段访问
        if not re.search(r"(?<![\w$])" + re.escape(nm) + r"\s*\(", body):
            missing.append(nm)
    ctor_missing = dex_has_ctor.get(cn) and not re.search(
        r"(?<![\w$])" + re.escape(cn) + r"\s*\([^)]*\)\s*\{", body)
    if missing or ctor_missing:
        mm = ",".join(sorted(missing))
        report.append(f"[{fname}] {cn}: 缺方法[{mm}]{'+ctor' if ctor_missing else ''}")

print(f"扫描类数: {len(dex_methods)}, 有问题的: {len(report)}")
for r in report:
    print(r)
