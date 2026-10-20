# -*- coding: utf-8 -*-
"""C0093x1.java 12 错误修复：sneaky 包裹 + g() 声明 throws + 不可达 return 修正"""
import io, os

BASE = r"E:\student\project\new-soul-1.34.0"
FP = os.path.join(BASE, "dev", "app", "src", "main", "java", "C0093x1.java")
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

def wrap_method(text, sig, catch):
    p = text.find(sig)
    if p < 0:
        print("MISS sig:", sig[:50]); return text
    ob = text.index("{", p + len(sig) - 1)
    cb = match_brace(text, ob)
    body = text[ob + 1:cb]
    out = text[:ob + 1] + "\n        try {" + body + "        } " + catch + "\n    }" + text[cb + 1:]
    print("WRAPPED:", sig.strip()[:60])
    return out

SNEAKY_CATCH_NS = ("catch (NoSuchAlgorithmException e) {\n"
                   "            sneakyThrow(e);\n"
                   "            throw new AssertionError();\n"
                   "        }")
SNEAKY_CATCH_IO = ("catch (IOException e) {\n"
                   "            sneakyThrow(e);\n"
                   "            throw new AssertionError();\n"
                   "        }")

# 1) 构造器包裹 (NSA)
src = wrap_method(src, "public C0093x1(File file, String str, int i) throws IOException {", SNEAKY_CATCH_NS)

# 2) a(): throw th4 → sneaky
old = "        } catch (Throwable th4) {\n            throw th4;\n        }"
new = "        } catch (Throwable th4) {\n            sneakyThrow(th4);\n            throw new AssertionError();\n        }"
assert old in src, "a() pattern"
src = src.replace(old, new, 1)
print("FIXED: a() rethrow")

# 3) f(): throw th → sneaky (12空格版)
old = "            } catch (Throwable th) {\n                throw th;\n            }"
new = "            } catch (Throwable th) {\n                sneakyThrow(th);\n                throw new AssertionError();\n            }"
assert old in src, "f() pattern"
src = src.replace(old, new, 1)
print("FIXED: f() rethrow")

# 4) d() 包裹 (IOException)
src = wrap_method(src, "public synchronized ArrayList d() {", SNEAKY_CATCH_IO)

# 5) g(): throws IOException + 成功路径 return + 删不可达 return
old = "public synchronized h3 g(String str) {"
new = "public synchronized h3 g(String str) throws IOException {"
assert old in src, "g sig"
src = src.replace(old, new, 1)

old = ("                    throw th;\n"
       "                }\n"
       "            }\n"
       "        }\n"
       "        throw new IllegalArgumentException(\"Invalid voice id\");\n"
       "        return h3Var;\n")
new = ("                     throw th;\n"
       "                }\n"
       "                return h3Var;\n"
       "            }\n"
       "        }\n"
       "        throw new IllegalArgumentException(\"Invalid voice id\");\n")
assert old in src, "g return pattern"
src = src.replace(old, new, 1)
print("FIXED: g() throws + return")

# 6) i() 包裹 (IOException from h()/l())
src = wrap_method(src, "public void i(String str) {", SNEAKY_CATCH_IO)

# 7) sneakyThrow helper
if "private static <T extends Throwable> void sneakyThrow" not in src:
    last = src.rfind("}")
    helper = ("\n    @SuppressWarnings(\"unchecked\")\n"
              "    private static <T extends Throwable> void sneakyThrow(Throwable ex) throws T {\n"
              "        throw (T) ex;\n"
              "    }\n")
    src = src[:last] + helper + src[last:]
    print("helper added")

io.open(FP, "w", encoding="utf-8", newline="\n").write(src)
print("C0093x1.java done")
