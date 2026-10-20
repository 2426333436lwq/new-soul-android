# -*- coding: utf-8 -*-
"""从 _classes.jar 提取缺失类 -> jadx 单类反编译 -> 多字符旧名映射回现别名 -> 并入源码树"""
import os, re, shutil, subprocess, sys, zipfile

ROOT = os.path.dirname(os.path.abspath(__file__))
JADX = r"D:\NX-apk\jadx\bin\jadx.bat"
JAR = os.path.join(ROOT, "_classes.jar")
WORK = os.path.join(ROOT, "_batch")
DEST = os.path.join(ROOT, "app", "src", "main", "java")

MISSING = """A1 B0 C D1 F2 H1 I J1 J K1 L1 M2 M N2 N O1 O2 O Q1 R1 R2 S0 S1 S2 T1 T2 U0 U1
X0 X1 X2 Y1 Y2 Z1 Z2 a1 b0 b1 c0 c2 c d h0 k2 l0 l2 l m0 m2 n0 n2 o2 p q1 r1
s1 s2 u0 v0 v w0 y2 z2 W2""".split()

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
# 单字符旧名不做自动替换（易误伤变量），交给 javac 迭代
MULTI = {k: v for k, v in RENAME.items() if len(k) >= 2}

def apply_renames(text: str) -> str:
    for old, new in MULTI.items():
        text = re.sub(r"(?<![\w$])" + re.escape(old) + r"(?![\w$])", new, text)
    return text

def main():
    if os.path.exists(WORK):
        shutil.rmtree(WORK)
    os.makedirs(os.path.join(WORK, "cls"))
    out_root = os.path.join(WORK, "out")
    z = zipfile.ZipFile(JAR)
    ok, fail = [], []
    for n in MISSING:
        entry = f"dev/local/newsoul/{n}.class"
        try:
            data = z.read(entry)
        except KeyError:
            fail.append((n, "not in jar"))
            continue
        cls_path = os.path.join(WORK, "cls", f"{n}.class")
        with open(cls_path, "wb") as f:
            f.write(data)
        if os.path.exists(out_root):
            shutil.rmtree(out_root)
        r = subprocess.run([JADX, "-d", out_root, cls_path],
                           capture_output=True, text=True, encoding="utf-8", errors="replace")
        # 找输出 java
        found = []
        for dirpath, _, files in os.walk(out_root):
            for fn in files:
                if fn.endswith(".java"):
                    found.append(os.path.join(dirpath, fn))
        if not found:
            fail.append((n, (r.stdout + r.stderr)[-300:]))
            continue
        with open(found[0], encoding="utf-8") as f:
            src = f.read()
        src = apply_renames(src)
        with open(os.path.join(DEST, f"{n}.java"), "w", encoding="utf-8", newline="\n") as f:
            f.write(src)
        ok.append(n)
    print(f"OK: {len(ok)}/{len(MISSING)}")
    if fail:
        print("FAIL:")
        for n, msg in fail:
            print(f"  {n}: {msg}")

if __name__ == "__main__":
    main()
