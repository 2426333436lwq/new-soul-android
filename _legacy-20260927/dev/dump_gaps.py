# -*- coding: utf-8 -*-
"""导出所有缺口方法/构造器的 dexdump 反汇编到 gaps_dump.txt"""
import re, io, os, subprocess, sys

BASE = r"E:\student\project\new-soul-1.34.0"
DEX = os.path.join(BASE, "dev", "_dexdump.txt")

GAPS = {
 "H0": ["a","b","c","e","f","g","h","i","j"],
 "I0": ["a","c","d","e","f","g","h","i","j","k","l"],
 "J0": ["a"],
 "N0": ["a","b"],
 "T0": ["c"],
 "k":  ["a","b","c","d","e","f","g","h","i","j"],
 "D1": ["<init>"], "H1": ["<init>"], "L": ["<init>"], "L1": ["<init>"],
 "R2": ["<init>"], "S0": ["<init>"], "S2": ["<init>"], "T2": ["<init>"],
 "b0": ["<init>"], "k2": ["<init>"], "l": ["<init>"], "n2": ["<init>"],
 "o2": ["<init>"], "s2": ["<init>"],
}

lines = io.open(DEX, encoding="utf-16", errors="replace").read().splitlines()
idx = []
pat = re.compile(r"  Class descriptor  : 'Ldev/local/newsoul/(.+);'")
for n, l in enumerate(lines):
    m = pat.match(l)
    if m:
        idx.append((n, m.group(1)))

out = []
for cname, meths in GAPS.items():
    hits = [k for k, (n, nm) in enumerate(idx) if nm == cname]
    if not hits:
        out.append(f"#### {cname}: CLASS NOT FOUND")
        continue
    j = hits[0]
    n = idx[j][0]
    end = idx[j + 1][0] if j + 1 < len(idx) else len(lines)
    seg = lines[n:end]
    out.append(f"\n########## class {cname} ##########")
    # 字段声明
    out.append("-- fields --")
    section = None
    i = 0
    while i < len(seg):
        l = seg[i]
        if re.match(r"  (Static|Instance) fields", l):
            section = "f"; i += 1; continue
        if re.match(r"  Direct methods|  Virtual methods", l):
            section = "m"; i += 1; continue
        if re.match(r"  (Static fields|Instance fields|source_file)", l):
            section = None
        if section == "f" and re.match(r"      name ", l):
            out.append("  " + l.strip() + " | " + seg[i + 1].strip())
        if section == "m" and l.strip().startswith("name          : '"):
            nm = re.match(r"      name          : '(.+)'", l).group(1)
            proto = seg[i + 1].strip()
            if nm in meths:
                out.append(f"-- method {nm} {proto} --")
                # 打印到下一个 name / 段尾
                k = i
                while k < len(seg):
                    if k > i and re.match(r"      name ", seg[k]):
                        break
                    if re.match(r"  (Static|Instance) fields|  Direct methods|  Virtual methods|  source_file", seg[k]) and k > i:
                        break
                    out.append("  " + seg[k])
                    k += 1
                i = k
                continue
        i += 1

with io.open(os.path.join(BASE, "dev", "gaps_dump.txt"), "w", encoding="utf-8", newline="\n") as f:
    f.write("\n".join(out))
print("written", len(out), "lines")
