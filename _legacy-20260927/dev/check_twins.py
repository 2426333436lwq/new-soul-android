# -*- coding: utf-8 -*-
import re

BASE = r"E:\student\project\new-soul-1.34.0"
lines = open(BASE + r"\dev\_dexdump.txt", encoding="utf-16", errors="replace").read().splitlines()

idx = []
for n, l in enumerate(lines):
    m = re.match(r"  Class descriptor  : 'Ldev/local/newsoul/(.+);'", l)
    if m:
        idx.append((n, m.group(1)))
secs = {}
for j, (n, name) in enumerate(idx):
    end = idx[j + 1][0] if j + 1 < len(idx) else len(lines)
    secs[name] = lines[n:end]

pairs = [("A1","a1"),("B0","b0"),("C","c"),("M2","m2"),("N2","n2"),("O2","o2"),
         ("Q1","q1"),("R1","r1"),("S1","s1"),("S2","s2"),("U0","u0"),("Y2","y2"),("Z2","z2")]

def norm(sec, myname):
    out = []
    for x in sec:
        x = re.sub(r"\b" + re.escape(myname) + r"\b", "CLS", x)
        x = re.sub(r"Class #\d+", "#", x)
        out.append(x)
    return out

data = open(BASE + r"\source\new-soul-1.34.0\original\classes.dex", "rb").read()

for u, l in pairs:
    su, sl = secs.get(u), secs.get(l)
    if not su or not sl:
        print(u, l, "MISSING SECTION", bool(su), bool(sl))
        continue
    same = norm(su, u) == norm(sl, l)
    # 字符串引用检查（点形式 = 反射用法）
    strrefs = []
    for n in (u, l):
        dotted = data.count(b"dev.local.newsoul." + n.encode())
        strrefs.append(f"{n}.dotted={dotted}")
    print(f"{u} vs {l}: lines {len(su)}/{len(sl)} identical={same} | {' '.join(strrefs)}")
