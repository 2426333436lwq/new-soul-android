# -*- coding: utf-8 -*-
"""WaveB: 12 个手工内联站点 -> 真类实例化"""
import io, os, re, sys

BASE = r"E:\student\project\new-soul-1.34.0"
SRC = os.path.join(BASE, "dev", "app", "src", "main", "java")

# (文件, 定位子串(含 from class), 目标类, 实参)
SITES = [
    ("SoulHook.java", "// from class: dev.local.newsoul.c", "c",
     "cls2, length2, zIsAssignableFrom, clsLoadClass7, clsLoadClass8, method, z2"),
    ("AbstractC0061p0.java", "// from class: dev.local.newsoul.n0", "n0",
     "z, method, fieldD2, fieldD, field, str5"),
    ("AbstractC0061p0.java", "// from class: dev.local.newsoul.m0", "m0",
     "z, method, str, fieldD2, declaredField4, fieldD, declaredField, declaredField2, declaredField3"),
    ("AbstractC0075t.java", "// from class: dev.local.newsoul.p", "p",
     "field, z, declaredMethod"),
    ("AbstractC0081u1.java", "// from class: dev.local.newsoul.s1", "s1",
     "fieldF, fieldF2, fieldF3, constructor, fieldF5, fieldF7, fieldF4, fieldF6, fieldF8"),
    ("AbstractC0090w2.java", "// from class: dev.local.newsoul.O1", "O1",
     "c0067q2, activity, z, z2, str"),
    ("AbstractC0090w2.java", "// from class: dev.local.newsoul.T1", "T1",
     "activity, c0067q2, linearLayoutR, z2, str, jElapsedRealtime, z"),
    ("E1.java", "// from class: dev.local.newsoul.D1", "D1", "z"),
    ("F0.java", "// from class: dev.local.newsoul.B0", "B0", "r10, i10, z4"),
    ("H.java", "// from class: dev.local.newsoul.J", "J", "activity, p, i"),
    ("Q0.java", "// from class: dev.local.newsoul.v", "v", "i2, z2, str2, i3"),
]

def read(fp):
    return io.open(fp, encoding="utf-8", errors="replace").read()

def write(fp, t):
    io.open(fp, "w", encoding="utf-8", newline="\n").write(t)

def replace_site(text, anchor, cls, args):
    idx = text.find(anchor)
    if idx < 0:
        return text, "anchor 未找到"
    # 定位该行内 `new ... {` 的 '{'
    line_start = text.rfind("\n", 0, idx) + 1
    line = text[line_start:text.find("\n", idx)]
    m = re.search(r"new [\w.$]+\([^)]*\) \{", line)
    if not m:
        return text, "new 头未找到"
    brace_pos = line_start + line.index("{", m.start())
    # 括号匹配（含字符串/注释）
    depth = 0
    i = brace_pos
    n = len(text)
    while i < n:
        c = text[i]
        if c == '"':
            i += 1
            while i < n:
                if text[i] == "\\": i += 2; continue
                if text[i] == '"': break
                i += 1
        elif c == "/" and i + 1 < n and text[i + 1] == "/":
            while i < n and text[i] != "\n": i += 1
        elif c == "/" and i + 1 < n and text[i + 1] == "*":
            e = text.find("*/", i)
            i = (e + 1) if e >= 0 else n
        elif c == "{":
            depth += 1
        elif c == "}":
            depth -= 1
            if depth == 0:
                break
        i += 1
    else:
        return text, "括号未闭合"
    # 替换 [line_start, i] 为 head + new cls(args) ；尾巴 = text[i+1:line_end]
    tail_start = i + 1
    line_end = text.find("\n", tail_start)
    if line_end < 0:
        line_end = n
    tail = text[tail_start:line_end]
    head = text[line_start:brace_pos]
    # head 中去掉 `new XXX(...) {`
    head = re.sub(r"new [\w.$]+\([^)]*\) \{$", "", head)
    new_line = head + f"new {cls}({args})" + tail
    return text[:line_start] + new_line + text[line_end:], None

def main():
    total, fails = 0, []
    for fname, anchor, cls, args in SITES:
        fp = os.path.join(SRC, fname)
        text = read(fp)
        # H.java 有两个 J 站点 —— 循环处理直到没有
        while True:
            if anchor not in text:
                break
            text2, err = replace_site(text, anchor, cls, args)
            if err:
                fails.append(f"{fname} {cls}: {err}")
                break
            if text2 == text:
                fails.append(f"{fname} {cls}: 无变化")
                break
            text = text2
            total += 1
        write(fp, text)
    print(f"WaveB 转换 {total} 处")
    for f in fails:
        print(" FAIL", f)
    # 校验: 全库还有多少 from class
    remain = []
    for fp in os.listdir(SRC):
        if not fp.endswith(".java"):
            continue
        t = read(os.path.join(SRC, fp))
        for m in re.finditer(r"// from class: dev\.local\.newsoul\.(\w+)", t):
            remain.append(f"{fp}:{m.group(1)}")
    print(f"剩余 from class 站点: {len(remain)}")
    for r in remain:
        print("  -", r)

if __name__ == "__main__":
    main()
