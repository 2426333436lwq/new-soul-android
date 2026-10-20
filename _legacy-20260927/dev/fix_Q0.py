# -*- coding: utf-8 -*-
"""Q0.java: T catch / U init / drawable init / L,M,a,e,v sneaky"""
import io, os, re

BASE = r"E:\student\project\new-soul-1.34.0"
FP = os.path.join(BASE, "dev", "app", "src", "main", "java", "Q0.java")
src = io.open(FP, encoding="utf-8", errors="replace").read()

def rep(old, new, tag, cnt=1):
    global src
    if old not in src:
        print("MISS", tag); return
    src = src.replace(old, new, cnt)
    print("OK", tag)

# A. T catch 补 delete + rethrow
rep("        } catch (Throwable th4) {\n            z2 = false;\n        }\n    }",
    "        } catch (Throwable th4) {\n            z2 = false;\n            if (!z2) {\n"
    "                contentResolver.delete(uriInsert, null, null);\n            }\n"
    "            throw th4;\n        }\n    }", "T catch")

# B. U() 初始化
rep("        String str;\n        ArrayList arrayList;\n        int i2;\n        int i3;\n        try {\n"
    "            PackageInfo packageInfo",
    "        String str;\n        ArrayList arrayList = new ArrayList();\n        int i2 = 0;\n"
    "        int i3 = 0;\n        try {\n            PackageInfo packageInfo", "U init")

# C. drawable 初始化
rep("        Drawable drawable;", "        Drawable drawable = null;", "drawable init")

# D. sneaky wraps
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
        print("MISS sig", sig[:60]); return
    ob = src.index("{", p + len(sig) - 1)
    cb = match_brace(src, ob)
    body = src[ob + 1:cb]
    src = (src[:ob + 1] + "\n        try {" + body +
           "        } " + catch + "\n    }" + src[cb + 1:])
    print("WRAPPED", sig.strip()[:60])

def C(types):
    head = (f"catch ({types[0]} e)" if len(types) == 1
            else "catch (" + " | ".join(types) + " e)")
    return head + " {\n            sneakyThrow(e);\n            throw new AssertionError();\n        }"

wrap("public static String L(Object obj) {",
     C(["java.security.NoSuchAlgorithmException", "IllegalAccessException"]))
wrap("public static PublicKey M() {",
     C(["java.security.NoSuchAlgorithmException", "java.security.spec.InvalidKeySpecException"]))
wrap("public static boolean a(Object obj) {", C(["IllegalAccessException"]))
wrap("public static boolean e(Object obj) {", C(["IllegalAccessException"]))
wrap("public static boolean v(Object obj) {", C(["IllegalAccessException"]))

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
