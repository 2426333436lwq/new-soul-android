# -*- coding: utf-8 -*-
"""d3.java: c/e/g/h 四方法 sneaky 包裹（签名不变）"""
import io, os

BASE = r"E:\student\project\new-soul-1.34.0"
FP = os.path.join(BASE, "dev", "app", "src", "main", "java", "d3.java")
src = io.open(FP, encoding="utf-8", errors="replace").read()

def match_brace(text, start):
    depth, i, n = 0, start, len(text)
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
                return i
        i += 1
    raise RuntimeError("unbalanced")

def wrap(sig, catch, indent="    "):
    global src
    p = src.find(sig)
    if p < 0:
        print("MISS:", sig[:60]); return
    ob = src.index("{", p + len(sig) - 1)
    cb = match_brace(src, ob)
    body = src[ob + 1:cb]
    src = (src[:ob + 1] + "\n        try {" + body +
           "        } " + catch + "\n" + indent + "}" + src[cb + 1:])
    print("WRAPPED:", sig.strip()[:60])

def sneaky(catches):
    parts = []
    for i, c in enumerate(catches):
        kw = "catch" if i == 0 else "catch"
        parts.append(f"{kw} ({c} e)")
    clause = " | ".join(f"({c} e)" for c in catches) if len(catches) > 1 else f"({catches[0]} e)"
    # multi-catch 语法: catch (A | B e)
    if len(catches) > 1:
        clause = "catch (" + " | ".join(catches) + " e)"
    else:
        clause = f"catch ({catches[0]} e)"
    return (clause + " {\n"
            "            sneakyThrow(e);\n"
            "            throw new AssertionError();\n"
            "        }")

# c(): field.get → IAE
wrap("public static Object c(Object obj) throws NoSuchFieldException {",
     sneaky(["IllegalAccessException"]))
# e(): getMethod+invoke → NSM/IAE/ITE
wrap("public static Object e(Object obj, String str) {",
     sneaky(["NoSuchMethodException", "IllegalAccessException",
             "java.lang.reflect.InvocationTargetException"]))
# g(): field.get → IAE（保留 NSFE throws）
wrap("public static Object g(Object obj, String str) throws NoSuchFieldException {",
     sneaky(["IllegalAccessException"]))
# h(): new b3→IAE + g→NSFE
wrap("public final void h(Object obj, Object obj2) {",
     sneaky(["IllegalAccessException", "NoSuchFieldException"]))

if "private static <T extends Throwable> void sneakyThrow" not in src:
    last = src.rfind("}")
    src = (src[:last] +
           "\n    @SuppressWarnings(\"unchecked\")\n"
           "    private static <T extends Throwable> void sneakyThrow(Throwable ex) throws T {\n"
           "        throw (T) ex;\n"
           "    }\n" + src[last:])
    print("helper added")

io.open(FP, "w", encoding="utf-8", newline="\n").write(src)
print("d3.java done")
