# -*- coding: utf-8 -*-
"""b3.java 三个方法 sneaky-throw 包裹（签名不变 → 零级联）"""
import io, os, re

BASE = r"E:\student\project\new-soul-1.34.0"
FP = os.path.join(BASE, "dev", "app", "src", "main", "java", "b3.java")

src = io.open(FP, encoding="utf-8", errors="replace").read()
lines = src.splitlines(keepends=True)

def find_sig(indent_method):
    for i, l in enumerate(lines):
        if l.strip().startswith(indent_method):
            return i
    return -1

def match_brace(text, start):
    """从 text[start]='{' 起找配对 }（含字符串/注释感知）"""
    depth = 0
    i = start
    n = len(text)
    while i < n:
        c = text[i]
        if c == '"':
            i += 1
            while i < n:
                if text[i] == "\\": i += 2; continue
                if text[i] == '"': break
                i += 1
        elif c == "'" and i + 2 < n:
            i += 1
            while i < n:
                if text[i] == "\\": i += 2; continue
                if text[i] == "'": break
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
                return i
        i += 1
    raise RuntimeError("unbalanced")

def wrap(sig_prefix, catch_block):
    # 找签名行
    idx = None
    for i, l in enumerate(lines):
        if sig_prefix in l:
            idx = i
            break
    if idx is None:
        print(f"MISS sig: {sig_prefix}")
        return
    text = "".join(lines)
    # 签名行内 '{' 位置
    line_start = sum(len(x) for x in lines[:idx])
    rel = lines[idx].index("{", lines[idx].index(sig_prefix))
    abs_open = line_start + rel
    close = match_brace(text, abs_open)
    body = text[abs_open + 1:close]
    new_text = (text[:abs_open + 1]
                + "\n        try {" + body
                + "        } " + catch_block + "\n"
                + text[close + 1:])
    # 重建 lines（后续 wrap 用）
    lines[:] = new_text.splitlines(keepends=True)
    print(f"WRAPPED: {sig_prefix[:60]}")

# 1. 构造器（final 字段 → catch 必须不可正常结束）
wrap(
    "public b3(d3 d3Var, Object obj) throws IllegalAccessException, InvocationTargetException {",
    "catch (NoSuchFieldException | java.io.IOException e) {\n"
    "            sneakyThrow(e);\n"
    "            throw new AssertionError();\n"
    "        }"
)
# 2. f()
wrap(
    "public final void f() {",
    "catch (IllegalAccessException | InvocationTargetException e) {\n"
    "            sneakyThrow(e);\n"
    "            throw new AssertionError();\n"
    "        }"
)
# 3. j()
wrap(
    "public final void j() throws IllegalAccessException, InvocationTargetException {",
    "catch (NoSuchFieldException e) {\n"
    "            sneakyThrow(e);\n"
    "            throw new AssertionError();\n"
    "        }"
)

# 追加 sneakyThrow helper 到类尾（最后一个 } 前）
text = "".join(lines)
helper = (
    "\n    @SuppressWarnings(\"unchecked\")\n"
    "    private static <T extends Throwable> void sneakyThrow(Throwable ex) throws T {\n"
    "        throw (T) ex;\n"
    "    }\n"
)
if "sneakyThrow" not in text.split("private static <T extends Throwable>")[0] or "private static <T extends Throwable> void sneakyThrow" not in text:
    last = text.rfind("}")
    text = text[:last] + helper + text[last:]

io.open(FP, "w", encoding="utf-8", newline="\n").write(text)
print("b3.java written, lines:", text.count("\n"))
