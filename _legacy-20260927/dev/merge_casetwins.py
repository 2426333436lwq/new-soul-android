# -*- coding: utf-8 -*-
"""
修复大小写冲突类：
- 组：{U,L} 仅大小写不同、两者都是 dex 必需类、且文件名会互相覆盖
- 策略：U.java = public class U + package-private class L（同包可见性无损，类名与原 dex 一致）
- 上位类内容从 _classes.jar 重新反编译（下位类内容已在盘上，可能挂在 U.java 文件名下）
"""
import io, os, re, shutil, subprocess, zipfile

BASE = r"E:\student\project\new-soul-1.34.0"
JADX = r"D:\NX-apk\jadx\bin\jadx.bat"
JAR = os.path.join(BASE, "dev", "_classes.jar")
SRC = os.path.join(BASE, "source", "new-soul-1.34.0", "sources", "dev", "local", "newsoul")
DEST = os.path.join(BASE, "dev", "app", "src", "main", "java")
WORK = os.path.join(BASE, "dev", "_merge_work")

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

def flip(n):
    return n.swapcase()

def dex_classes():
    names = set()
    with io.open(os.path.join(BASE, "dev", "_dexdump.txt"), encoding="utf-16", errors="replace") as f:
        for line in f:
            m = re.match(r"  Class descriptor  : 'Ldev/local/newsoul/(.+);'", line)
            if m:
                names.add(m.group(1))
    return names

def decompile(jar, clsname, outdir):
    z = zipfile.ZipFile(jar)
    data = z.read(f"dev/local/newsoul/{clsname}.class")
    cls_path = os.path.join(outdir, f"{clsname}.class")
    with open(cls_path, "wb") as f:
        f.write(data)
    res = os.path.join(outdir, "out")
    if os.path.exists(res):
        shutil.rmtree(res)
    subprocess.run([JADX, "-d", res, cls_path], capture_output=True)
    for dp, _, fs in os.walk(res):
        for fn in fs:
            if fn.endswith(".java"):
                with io.open(os.path.join(dp, fn), encoding="utf-8") as f:
                    return apply_renames(f.read())
    raise RuntimeError(f"decompile failed: {clsname}")

def split_java(src):
    """-> (package_line, imports(set), class_text)"""
    pkg = None
    imports = []
    body = []
    for line in src.splitlines():
        if pkg is None and line.startswith("package "):
            pkg = line
        elif line.startswith("import "):
            imports.append(line)
        else:
            body.append(line)
    # 去掉 body 开头空行
    while body and not body[0].strip():
        body.pop(0)
    return pkg, imports, "\n".join(body)

def strip_public(class_text):
    # 顶层类去掉 public（变为包内可见）
    return re.sub(r"^public (/\* synthetic \*/ )?", lambda m: (m.group(1) or ""), class_text, count=1)

def main():
    dex = dex_classes()
    groups = {}
    for n in MISSING:
        if n not in dex:
            continue
        f = flip(n)
        if f == n or f not in dex:
            continue
        # 组判定：对侧文件名精确为 flip 的文件存在，或对侧也在缺失集
        exact_file_exists = os.path.exists(os.path.join(DEST, f + ".java")) or \
                            os.path.exists(os.path.join(SRC, f + ".java"))
        if exact_file_exists or f in MISSING:
            u, l = (n, f) if n.isupper() or not f.isupper() else (f, n)
            # 规范上位：取含大写的那个；两个都大写开头的取字母序（不太可能）
            if n[0].isupper():
                u, l = n, f
            elif f[0].isupper():
                u, l = f, n
            groups[u] = l
    print("groups:", {u: l for u, l in sorted(groups.items())})

    if os.path.exists(WORK):
        shutil.rmtree(WORK)
    os.makedirs(WORK)

    for u, l in sorted(groups.items()):
        # 上位类：从 jar 重新反编译
        upper_src = decompile(JAR, u, WORK)
        # 下位类：盘上 U.java 当前内容（应声明 class l）；若不对，从 jar 反编译
        cur_path = os.path.join(DEST, u + ".java")
        if not os.path.exists(cur_path):
            cur_path = os.path.join(SRC, u + ".java")
        lower_src = None
        if os.path.exists(cur_path):
            with io.open(cur_path, encoding="utf-8") as f:
                cur = f.read()
            if re.search(r"class " + re.escape(l) + r"\b", cur):
                lower_src = cur
        if lower_src is None:
            lower_src = decompile(JAR, l, WORK)

        upkg, uimp, ubody = split_java(upper_src)
        lpkg, limp, lbody = split_java(lower_src)
        imports = list(dict.fromkeys(uimp + limp))  # 去重保序
        lbody_ns = strip_public(lbody)
        merged = []
        merged.append(upkg or lpkg)
        merged.append("")
        merged.extend(imports)
        merged.append("")
        merged.append(f"/* 本文件合并了仅大小写不同的两个类 {u} / {l}（原 dex 类名不变，因文件系统大小写限制合并存放） */")
        merged.append(ubody.rstrip())
        merged.append("")
        merged.append(lbody_ns.rstrip())
        merged.append("")
        with io.open(cur_path, "w", encoding="utf-8", newline="\n") as f:
            f.write("\n".join(merged))
        print(f"merged {u}.java = {u} + {l}")

if __name__ == "__main__":
    main()
