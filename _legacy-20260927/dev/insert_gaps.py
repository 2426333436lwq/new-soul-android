# -*- coding: utf-8 -*-
"""把 gaps_fix.json 的方法插入对应类体的末尾（关闭大括号之前）"""
import re, io, os, json, glob

BASE = r"E:\student\project\new-soul-1.34.0"
SRC = os.path.join(BASE, "dev", "app", "src", "main", "java")
FIX = os.path.join(BASE, "dev", "gaps_fix.json")

def find_class_span(src, cname):
    m = re.search(r"\bclass\s+" + re.escape(cname) + r"\b[^;{}]*\{", src)
    if not m:
        return None
    start = m.end() - 1
    depth = 0
    i = start
    n = len(src)
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
        elif c == "'":
            i += 1
            while i < n:
                if src[i] == "\\":
                    i += 2; continue
                if src[i] == "'":
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

def main():
    fix = json.load(io.open(FIX, encoding="utf-8"))
    # 反查: 类名 -> 文件
    file_map = {}
    for fp in glob.glob(os.path.join(SRC, "*.java")):
        src = io.open(fp, encoding="utf-8", errors="replace").read()
        for m in re.finditer(r"\bclass\s+(\w+)\b", src):
            if m.group(1) not in file_map:
                file_map[m.group(1)] = fp
    done, fail = [], []
    for cname, methods in fix.items():
        fp = file_map.get(cname)
        if not fp:
            # 别名映射 (dex 名 -> 源码类名)
            ALIAS = {'k':'AbstractC0040k'}
            fp = file_map.get(ALIAS.get(cname, ''), None)
            if fp:
                cname_real = ALIAS[cname]
                src0 = io.open(fp, encoding='utf-8', errors='replace').read()
                span0 = find_class_span(src0, cname_real)
                if span0:
                    src, span, cname = src0, span0, cname_real
        if not fp:
            fail.append(f"{cname}: 文件未找到")
            continue
        src = io.open(fp, encoding="utf-8", errors="replace").read()
        span = find_class_span(src, cname)
        if not span:
            fail.append(f"{cname}: 类体未定位")
            continue
        s, e = span
        body = src[s:e + 1]
        to_add = []
        for mth in methods:
            name = mth["name"]
            if name == "<init>":
                pat = r"(?<![\w$])" + re.escape(cname) + r"\s*\([^;{}]*\)\s*(throws[^{]+)?\{"
            else:
                pat = r"(?<![\w$])" + re.escape(name) + r"\s*\("
            # 方法声明形态（前面是行首空白+修饰符）排除调用
            decl = r"(?m)^[ \t]+(?:public|private|protected|static|final|\s)*[\w<>\[\].]+\s+" + \
                   (re.escape(cname) if name == "<init>" else re.escape(name)) + r"\s*\("
            if re.search(decl, body):
                continue
            to_add.append(mth["text"])
        if not to_add:
            done.append(f"{cname}: 已存在, 跳过")
            continue
        insert = "\n\n" + "\n\n".join(to_add) + "\n"
        new_src = src[:e] + insert + src[e:]
        io.open(fp, "w", encoding="utf-8", newline="\n").write(new_src)
        done.append(f"{cname}: +{len(to_add)}")
    print("\n".join(done))
    if fail:
        print("FAIL:", fail)

if __name__ == "__main__":
    main()
