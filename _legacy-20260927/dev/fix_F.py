# -*- coding: utf-8 -*-
"""F.java: b/c/e/f/g/a 六方法 sneaky 包裹"""
import io, os

BASE = r"E:\student\project\new-soul-1.34.0"
FP = os.path.join(BASE, "dev", "app", "src", "main", "java", "F.java")
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

def wrap(sig, catch):
    global src
    p = src.find(sig)
    if p < 0:
        print("MISS:", sig[:70]); return
    ob = src.index("{", p + len(sig) - 1)
    cb = match_brace(src, ob)
    body = src[ob + 1:cb]
    src = (src[:ob + 1] + "\n        try {" + body +
           "        } " + catch + "\n    }" + src[cb + 1:])
    print("WRAPPED:", sig.strip()[:70])

def C(ex_types):
    if len(ex_types) == 1:
        head = f"catch ({ex_types[0]} e)"
    else:
        head = "catch (" + " | ".join(ex_types) + " e)"
    return (head + " {\n"
            "            sneakyThrow(e);\n"
            "            throw new AssertionError();\n"
            "        }")

ITE = "java.lang.reflect.InvocationTargetException"
wrap("public static Object b(Object obj, String str) {",
     C(["NoSuchMethodException", "IllegalAccessException", ITE]))
wrap("public static Object c(int i, Object obj) {",
     C(["NoSuchFieldException", "NoSuchMethodException", "IllegalAccessException", ITE]))
wrap("public static Object e(Object obj) throws IllegalAccessException, InstantiationException, InvocationTargetException {",
     C(["NoSuchFieldException", "NoSuchMethodException"]))
wrap("public static boolean f(Object obj, String str, String str2) {",
     C(["NoSuchMethodException", "IllegalAccessException", ITE]))
wrap("public static Object g(Object obj, String str) throws NoSuchFieldException {",
     C(["IllegalAccessException"]))
wrap("public final Object a(Object obj, Object obj2, Object obj3) throws IllegalAccessException, InstantiationException, InvocationTargetException {",
     C(["NoSuchFieldException"]))

if "private static <T extends Throwable> void sneakyThrow" not in src:
    last = src.rfind("}")
    src = (src[:last] +
           "\n    @SuppressWarnings(\"unchecked\")\n"
           "    private static <T extends Throwable> void sneakyThrow(Throwable ex) throws T {\n"
           "        throw (T) ex;\n"
           "    }\n" + src[last:])
    print("helper added")

io.open(FP, "w", encoding="utf-8", newline="\n").write(src)
print("done")
