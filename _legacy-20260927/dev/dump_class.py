# -*- coding: utf-8 -*-
"""用法: python dump_class.py <类名> [过滤正则] —— 从 _dexdump.txt 打印该类段落"""
import re, io, sys

BASE = r"E:\student\project\new-soul-1.34.0"
name = sys.argv[1]
filt = sys.argv[2] if len(sys.argv) > 2 else None

lines = io.open(BASE + r"\dev\_dexdump.txt", encoding="utf-16", errors="replace").read().splitlines()
idx = []
pat = re.compile(r"  Class descriptor  : 'Ldev/local/newsoul/(.+);'")
for n, l in enumerate(lines):
    m = pat.match(l)
    if m:
        idx.append((n, m.group(1)))
hits = [k for k, (n, nm) in enumerate(idx) if nm == name]
if not hits:
    print("class not found:", name)
    sys.exit(1)
j = hits[0]
n = idx[j][0]
end = idx[j + 1][0] if j + 1 < len(idx) else len(lines)
rx = re.compile(filt) if filt else None
for l in lines[n:end]:
    if rx is None or rx.search(l):
        print(l)
