# -*- coding: utf-8 -*-
import re, io

BASE = r"E:\student\project\new-soul-1.34.0"
lines = io.open(BASE + r"\dev\_dexdump.txt", encoding="utf-16", errors="replace").read().splitlines()
idx = []
pat = re.compile(r"  Class descriptor  : 'Ldev/local/newsoul/(.+);'")
for n, l in enumerate(lines):
    m = pat.match(l)
    if m:
        idx.append((n, m.group(1)))
print("total classes matched:", len(idx))
for j, (n, name) in enumerate(idx):
    if name in ("A1", "a1", "C", "c", "Z2", "z2"):
        end = idx[j + 1][0] if j + 1 < len(idx) else len(lines)
        print(j, n, name, "section lines", end - n)

# 成员对比（去掉地址与 Class# 后比较成员清单）
def members(name):
    j = next(j for j, (n, nm) in enumerate(idx) if nm == name)
    n = idx[j][0]
    end = idx[j + 1][0] if j + 1 < len(idx) else len(lines)
    seg = lines[n:end]
    out = []
    for l in seg:
        s = l.strip()
        if s.startswith(("name ", "type ", "access ", "registers", "insns size")):
            # 只留签名级信息，去掉代码体偏移
            out.append(s)
    return out

def sig_only(name):
    j = next(j for j, (n, nm) in enumerate(idx) if nm == name)
    n = idx[j][0]
    end = idx[j + 1][0] if j + 1 < len(idx) else len(lines)
    seg = lines[n:end]
    out = []
    grab = False
    for l in seg:
        s = l.strip()
        if s.startswith(("name ", "type ", "access ")):
            out.append(s)
    return out

for u, l in [("A1","a1"),("C","c"),("Z2","z2"),("B0","b0"),("S1","s1")]:
    su, sl = sig_only(u), sig_only(l)
    print(u, l, "sig equal:", su == sl, "| members", len(su), len(sl))
