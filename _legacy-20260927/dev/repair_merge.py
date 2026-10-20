# -*- coding: utf-8 -*-
"""修复 merge_casetwins.py 的误合并：
- 若 flip 类已有别名文件（flip ∈ RENAME），则该组无效：文件恢复为仅上位类
- 有效组（真正大小写冲突）保持上+下合并（重新生成一遍确保干净）
"""
import io, os, re, shutil, subprocess, zipfile

BASE = r"E:\student\project\new-soul-1.34.0"
JADX = r"D:\NX-apk\jadx\bin\jadx.bat"
JAR = os.path.join(BASE, "dev", "_classes.jar")
DEST = os.path.join(BASE, "dev", "app", "src", "main", "java")
WORK = os.path.join(BASE, "dev", "_repair_work")

# 与 merge_casetwins.py 相同
MISSING = set("""A1 B0 C D1 F2 H1 I J1 J K1 L1 M2 M N2 N O1 O2 O Q1 R1 R2 S0 S1 S2 T1 T2 U0 U1
X0 X1 X2 Y1 Y2 Z1 Z2 a1 b0 b1 c0 c2 c d h0 k2 l0 l2 l m0 m2 n0 n2 o2 p q1 r1
s1 s2 u0 v0 v w0 y2 z2 W2""".split())

RENAME = {
 "a":"C0000a","a0":"C0001a0","a2":"C0003a2","b":"C0004b","b2":"C0007b2",
 "c1":"DialogInterfaceOnDismissListenerC0010c1","d0":"RunnableC0013d0","d1":"C0014d1",
 "d2":"RunnableC0015d2","e":"C0016e","e0":"RunnableC0017e0","e1":"C0018e1","e2":"C0019e2",
 "f":"C0020f","f0":"C0021f0","f1":"C0022f1","f2":"C0023f2","g":"RunnableC0024g",
 "g0":"AbstractC0025g0","g1":"AbstractC0026g1","g2":"C0027g2","h":"RunnableC0028h",
 "h1":"ViewOnLayoutChangeListenerC0030h1","h2":"C0031h2","i":"C0032i",
 "i0":"ViewOnClickListenerC0033i0","i1":"C0034i1","i2":"C0035i2","j":"AbstractC0036j",
 "j0":"DialogInterfaceOnDismissListenerC0037j0","j1":"AbstractC0038j1","j2":"C0039j2",
 "k":"AbstractC0040k","k0":"C0041k0","k1":"C0042k1","l1":"ViewOnClickListenerC0046l1",
 "m":"RunnableC0048m","m1":"RunnableC0050m1","n":"ViewOnLongClickListenerC0052n",
 "n1":"RunnableC0054n1","o":"AbstractC0056o","o0":"C0057o0","o1":"RunnableC0058o1",
 "p0":"AbstractC0061p0","p1":"RunnableC0062p1","p2":"ViewOnAttachStateChangeListenerC0063p2",
 "q":"RunnableC0064q","q0":"ViewOnLongClickListenerC0065q0","q2":"C0067q2",
 "r0":"AbstractC0068r0","r2":"C0070r2","s":"C0071s","s0":"ViewOnClickListenerC0072s0",
 "t":"AbstractC0075t","t0":"C0076t0","t1":"C0077t1","t2":"C0078t2","u":"C0079u",
 "u1":"AbstractC0081u1","u2":"C0082u2","v1":"C0085v1","v2":"C0086v2","w":"C0087w",
 "w1":"C0089w1","w2":"AbstractC0090w2","x":"C0091x","x0":"ViewOnClickListenerC0092x0",
 "x1":"C0093x1","x2":"C0094x2","y":"C0095y","y0":"C0096y0","y1":"C0097y1",
 "z":"C0099z","z0":"AbstractC0100z0","z1":"AbstractC0101z1",
}
MULTI = {k: v for k, v in RENAME.items() if len(k) >= 2}

def apply_renames(text):
    for old, new in MULTI.items():
        text = re.sub(r"(?<![\w$])" + re.escape(old) + r"(?![\w$])", new, text)
    return text

def decompile(clsname):
    z = zipfile.ZipFile(JAR)
    data = z.read(f"dev/local/newsoul/{clsname}.class")
    cls_path = os.path.join(WORK, f"{clsname}.class")
    with open(cls_path, "wb") as f:
        f.write(data)
    res = os.path.join(WORK, "out")
    if os.path.exists(res):
        shutil.rmtree(res)
    subprocess.run([JADX, "-d", res, cls_path], capture_output=True)
    for dp, _, fs in os.walk(res):
        for fn in fs:
            if fn.endswith(".java"):
                with io.open(os.path.join(dp, fn), encoding="utf-8") as f:
                    return apply_renames(f.read())
    raise RuntimeError("decompile failed: " + clsname)

def split_java(src):
    pkg, imports, body = None, [], []
    for line in src.splitlines():
        if pkg is None and line.startswith("package "):
            pkg = line
        elif line.startswith("import "):
            imports.append(line)
        else:
            body.append(line)
    while body and not body[0].strip():
        body.pop(0)
    return pkg, imports, "\n".join(body)

def strip_public(text):
    return re.sub(r"(?m)^public (final |abstract |final /\* synthetic \*/ |/\* synthetic \*/ )?",
                  lambda m: (m.group(1) or ""), text, count=1)

def dex_classes():
    names = set()
    with io.open(os.path.join(BASE, "dev", "_dexdump.txt"), encoding="utf-16", errors="replace") as f:
        for line in f:
            m = re.match(r"  Class descriptor  : 'Ldev/local/newsoul/(.+);'", line)
            if m:
                names.add(m.group(1))
    return names

def main():
    dex = dex_classes()
    # 重算所有大小写组（与上次相同的组集合）
    groups = {}
    for n in MISSING:
        if n not in dex:
            continue
        f = n.swapcase()
        if f == n or f not in dex:
            continue
        # 上次的分组条件在 Windows 上恒真，只要 flip 是 dex 类就分了组
        u = n if (n[0].isupper() or not f[0].isupper()) else f
        if f[0].isupper() and n[0].isupper():
            u = min(n, f)
        if n[0].isupper() and not f[0].isupper():
            u = n
        if f[0].isupper() and not n[0].isupper():
            u = f
        groups[u] = f if f != n else n
        # 规范：groups[U] = L
        if u == f:
            groups[u] = n
        else:
            groups[u] = f
    # 有效 = flip 不在 RENAME（即 flip 没有自己的别名文件）
    valid = {u: l for u, l in groups.items() if l not in RENAME}
    invalid = {u: l for u, l in groups.items() if l in RENAME}
    print(f"valid={len(valid)} invalid={len(invalid)}")
    print("invalid:", sorted(invalid))

    if os.path.exists(WORK):
        shutil.rmtree(WORK)
    os.makedirs(WORK)

    for u, l in sorted(valid.items()):
        upper = decompile(u)
        lower = decompile(l)  # l ∉ MULTI，重命名安全
        upkg, uimp, ubody = split_java(upper)
        lpkg, limp, lbody = split_java(lower)
        imports = list(dict.fromkeys(uimp + limp))
        merged = [upkg or lpkg, ""] + imports + [
            "",
            f"/* 本文件合并了仅大小写不同的两个类 {u} / {l}（原 dex 类名保持不变；文件系统大小写不敏感所致） */",
            ubody.rstrip(), "", strip_public(lbody).rstrip(), "",
        ]
        with io.open(os.path.join(DEST, u + ".java"), "w", encoding="utf-8", newline="\n") as f:
            f.write("\n".join(merged))
        print(f"OK merged {u} + {l}")

    for u, l in sorted(invalid.items()):
        upper = decompile(u)
        with io.open(os.path.join(DEST, u + ".java"), "w", encoding="utf-8", newline="\n") as f:
            f.write(upper)
        print(f"OK restored {u} (dropped erroneous {l})")

if __name__ == "__main__":
    main()
