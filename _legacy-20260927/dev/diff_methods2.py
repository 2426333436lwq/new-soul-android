# -*- coding: utf-8 -*-
"""细化报告：
- 无类: 用 RENAME 映射按别名类名复查 (接口也识别)
- ctor: 仅 dex <init> 非 ()V 时才认为源码需要显式构造器
"""
import re, io, os, glob

BASE = r"E:\student\project\new-soul-1.34.0"
DEX = os.path.join(BASE, "dev", "_dexdump.txt")
SRC = os.path.join(BASE, "dev", "app", "src", "main", "java")

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

# ---- dex 解析（段头2空格；收集方法名+init协议） ----
lines = io.open(DEX, encoding="utf-16", errors="replace").read().splitlines()
idx = []
pat = re.compile(r"  Class descriptor  : 'Ldev/local/newsoul/(.+);'")
for n, l in enumerate(lines):
    m = pat.match(l)
    if m:
        idx.append((n, m.group(1)))
dex_m, dex_ctor_proto, dex_kind = {}, {}, {}
for j, (n, name) in enumerate(idx):
    end = idx[j + 1][0] if j + 1 < len(idx) else len(lines)
    seg = lines[n:end]
    methods, proto, section = set(), None, None
    kind = "class"
    for k, l in enumerate(seg):
        if "ABSTRACT" in l and "Access flags" in l and "INTERFACE" in l:
            kind = "interface"
        if "INTERFACE" in l and "Access flags" in l:
            kind = "interface"
        if re.match(r"  Direct methods", l):
            section = "m"; continue
        if re.match(r"  Virtual methods", l):
            section = "m"; continue
        if re.match(r"  (Static|Instance) fields", l):
            section = None; continue
        if section == "m":
            mm = re.match(r"      name          : '(.+)'", l)
            if mm:
                nm = mm.group(1)
                if nm == "<init>":
                    proto = seg[k + 1].split("'")[1]
                elif nm != "<clinit>":
                    methods.add(nm)
    dex_m[name] = methods
    dex_ctor_proto[name] = proto
    dex_kind[name] = kind

# ---- java 解析 ----
def load(p):
    return io.open(p, encoding="utf-8", errors="replace").read()

def find_body(src, cname):
    m = re.search(r"\b(?:class|interface)\s+" + re.escape(cname) + r"\b[^;{}]*\{", src)
    if not m:
        return None
    i = m.end() - 1
    depth = 0
    for k in range(i, len(src)):
        c = src[k]
        if c == '"':
            k2 = k + 1
            while k2 < len(src):
                if src[k2] == "\\":
                    k2 += 2; continue
                if src[k2] == '"':
                    break
                k2 += 1
            continue
        if c == "{":
            depth += 1
        elif c == "}":
            depth -= 1
            if depth == 0:
                return src[i:k + 1]
    return None

class_files = {}
for fp in glob.glob(os.path.join(SRC, "*.java")):
    src = load(fp)
    for m in re.finditer(r"\b(?:class|interface)\s+(\w+)\b", src):
        cn = m.group(1)
        if cn in class_files:
            continue
        body = find_body(src, cn)
        if body is not None:
            class_files[cn] = (os.path.basename(fp), body)

report = []
for cn, methods in sorted(dex_m.items()):
    lookup = cn if cn in class_files else RENAME.get(cn, cn)
    if lookup not in class_files:
        report.append(f"[真无类] {cn}")
        continue
    fname, body = class_files[lookup]
    missing = [nm for nm in sorted(methods)
               if not re.search(r"(?<![\w$])" + re.escape(nm) + r"\s*\(", body)]
    proto = dex_ctor_proto.get(cn)
    ctor_missing = False
    if proto and proto != "()V":
        # 源码需显式构造器：以类名( 开头的声明
        ctor_missing = not re.search(r"(?<![\w$])" + re.escape(lookup) + r"\s*\([^;{}]*\)\s*(throws[^{]+)?\{", body)
    if missing or ctor_missing:
        report.append(f"[{fname}] {cn}: 缺方法{missing}{' +构造器' if ctor_missing else ''}")

print(f"真实缺口类数: {len(report)}")
for r in report:
    print(r)
