# -*- coding: utf-8 -*-
"""单独处理 k -> AbstractC0040k 的插入（含别名映射）"""
import re, io, os, json, glob

BASE = r"E:\student\project\new-soul-1.34.0"
SRC = os.path.join(BASE, "dev", "app", "src", "main", "java")
fix = json.load(io.open(os.path.join(BASE, "dev", "gaps_fix.json"), encoding="utf-8"))

def find_class_span(src, cname):
    m = re.search(r"\bclass\s+" + re.escape(cname) + r"\b[^;{}]*\{", src)
    if not m:
        return None
    start = m.end() - 1
    depth, i, n = 0, start, len(src)
    while i < n:
        c = src[i]
        if c == '"':
            i += 1
            while i < n:
                if src[i] == "\\":
                    i += 2; continue
                if src[i] == '"':
                    break
                i += 1
        elif c == "/" and i + 1 < n and src[i + 1] == "/":
            while i < n and src[i] != "\n":
                i += 1
        elif c == "/" and i + 1 < n and src[i + 1] == "*":
            i = src.find("*/", i) + 1
        elif c == "{":
            depth += 1
        elif c == "}":
            depth -= 1
            if depth == 0:
                return (start, i)
        i += 1
    return None

for dex_name, real in [("k", "AbstractC0040k")]:
    methods = fix[dex_name]
    fp = os.path.join(SRC, real + ".java")
    src = io.open(fp, encoding="utf-8", errors="replace").read()
    span = find_class_span(src, real)
    s, e = span
    body = src[s:e + 1]
    to_add = []
    for mth in methods:
        decl = r"(?m)^[ \t]+(?:public|private|protected|static|final|\s)*[\w<>\[\].]+\s+" + re.escape(mth["name"]) + r"\s*\("
        if re.search(decl, body):
            continue
        to_add.append(mth["text"])
    if to_add:
        src = src[:e] + "\n\n" + "\n\n".join(to_add) + "\n" + src[e:]
        io.open(fp, "w", encoding="utf-8", newline="\n").write(src)
    print(real, "added", len(to_add))
