# -*- coding: utf-8 -*-
"""WaveD: 终局语义修复"""
import io, os, re

BASE = r"E:\student\project\new-soul-1.34.0"
SRC = os.path.join(BASE, "dev", "app", "src", "main", "java")

def load(f): return io.open(os.path.join(SRC, f), encoding="utf-8", errors="replace").read()
def save(f, t): io.open(os.path.join(SRC, f), "w", encoding="utf-8", newline="\n").write(t)

ok = miss = 0
def fix(fname, old, new, count=0, tag=""):
    global ok, miss
    t = load(fname)
    c = t.count(old)
    if c == 0:
        print(f"[MISS] {fname} {tag}: {old[:70]!r}")
        miss += 1
        return
    t = t.replace(old, new)
    save(fname, t)
    ok += c

# 1. P2 imports
fix("P2.java", "package dev.local.newsoul;\n",
    "package dev.local.newsoul;\n\nimport java.lang.reflect.IllegalAccessException;\nimport java.lang.reflect.InvocationTargetException;\n", tag="P2 imports")

# 2. c3 接口 throws
fix("c3.java", "    void run();", "    void run() throws IllegalAccessException, InvocationTargetException;", tag="c3 throws")

# 3. M0 @Override 归位
fix("M0.java",
    "    @Override // android.view.ViewGroup, android.view.View\n    private void superDispatchDraw(Canvas canvas) {\n        super.dispatchDraw(canvas);\n    }\n\n    public final void dispatchDraw(Canvas canvas) {",
    "    private void superDispatchDraw(Canvas canvas) {\n        super.dispatchDraw(canvas);\n    }\n\n    @Override // android.view.ViewGroup, android.view.View\n    public final void dispatchDraw(Canvas canvas) {", tag="M0 override")

# 4. H catch9 结构
fix("H.java",
    "                    } catch (Throwable th9) {\n                        th = th9;\n                        i = 0;\n                    }\n                    d3.f(activity7, \"发送未完成，请查看消息状态\", th9);\n                    return;",
    "                    } catch (Throwable th9) {\n                        i = 0;\n                        d3.f(activity7, \"发送未完成，请查看消息状态\", th9);\n                        return;\n                    }", tag="H th9")

# 5. K
fix("K.java", "final Map map = this.c;", "final Map<Object, Object> map = this.c;", tag="K map")
fix("K.java", "Map map2 = this.c;", "Map<Object, Object> map2 = this.c;", tag="K map2")
fix("K.java", "new O(p2, i, activity2)", "new O(p2, zCommit, activity2)", tag="K O args")

# 6. L0 cast
fix("L0.java", "C0039j2 j2Var = this.b;", "C0039j2 j2Var = (C0039j2) this.b;", tag="L0 cast")

# 7. N0 boolean
fix("N0.java", "setAccessibilityHeading(1);", "setAccessibilityHeading(true);", tag="N0 bool")

# 8. N2 / Z2 g -> RunnableC0024g
fix("N2.java", "new g(weakReference, d3Var, 14)", "new RunnableC0024g(weakReference, d3Var, 14)", tag="N2 g")
fix("Z2.java", "new g(view, (Object) obj, 12)", "new RunnableC0024g(view, (Object) obj, 12)", tag="Z2 g")

# 9. P0 泛型
fix("P0.java", "public final List c;", "public final List<String> c;", tag="P0 list")

# 10. P1 cast
fix("P1.java", "(List) this.e", "(List<C0027g2>) this.e", tag="P1 cast")

# 11. Q0 各项
q0fixes = [
    ("V.i(str2, null);", "dev.local.newsoul.V.i(str2, null);"),
    ("V.i(str2, th);", "dev.local.newsoul.V.i(str2, th);"),
    ("V.j(str.concat(\".deoptimize\")", "dev.local.newsoul.V.j(str.concat(\".deoptimize\")"),
    ("V.i(str.concat(\".deoptimize\"), th);", "dev.local.newsoul.V.i(str.concat(\".deoptimize\"), th);"),
    ("V.d(str, \"trace=\" + (c0099z == null ? 0L : c0099z.a) + \"; \" + str2);",
     "dev.local.newsoul.V.d(str, \"trace=\" + (c0099z == null ? 0L : c0099z.a) + \"; \" + str2);"),
    ("V.d(str, (\"trace=\" + (c0099z == null ? 0L : c0099z.a)) + \"\\n\" + V.c(th));",
     "dev.local.newsoul.V.d(str, (\"trace=\" + (c0099z == null ? 0L : c0099z.a)) + \"\\n\" + dev.local.newsoul.V.c(th));"),
    ("A.b.get()", "dev.local.newsoul.A.b.get()"),
    ("Boolean.valueOf(F.o)", "Boolean.valueOf(dev.local.newsoul.F.o)"),
    ("Boolean.valueOf(F.p)", "Boolean.valueOf(dev.local.newsoul.F.p)"),
    ("new X0(size, method2, method2, method2, size)", "new X0(i2, method2, str, xposedModule, i3)"),
    ("            th = th4;\n            z2 = false;", "            z2 = false;"),
]
for old, new in q0fixes:
    fix("Q0.java", old, new, tag=old[:40])
# Q0 中剩余裸 V.a() 调用（373/884 等）
t = load("Q0.java")
t2 = re.sub(r"(?<![\w.])V\.(a|i|j|d)\(", r"dev.local.newsoul.V.\1(", t)
if t2 != t:
    save("Q0.java", t2)
    ok += 1
    print("Q0: qualified remaining V. calls")

# 12. Q2
fix("Q2.java", "b3Var2", "b3Var", tag="Q2 b3Var2 all")
fix("Q2.java", "new Z2(8, 8, i)", "new Z2(b3Var4, h3Var3, i)", tag="Q2 Z2#1")
fix("Q2.java", "new Z2(3, 3, i2)", "new Z2(b3Var4, h3Var3, i2)", tag="Q2 Z2#2")

# 13. Q1/R1/U1 h/j
fix("Q1.java", "j.i.post(new h(0));", "AbstractC0036j.i.post(new RunnableC0028h(0));", tag="Q1")
fix("U1.java", "j.i.post(new h(0));", "AbstractC0036j.i.post(new RunnableC0028h(0));", tag="U1")
fix("R1.java", "post(new h(1));", "post(new RunnableC0028h(1));", tag="R1")

# 14. RunnableC0050m1 th 声明
fix("RunnableC0050m1.java",
    "                try {\n                    c3Var.run();",
    "                Throwable th = null;\n                try {\n                    c3Var.run();", tag="m1 th decl")

# 15. RunnableC0024g th 流
fix("RunnableC0024g.java", "                        th = th8;\n                        file = file4;", "                        file = file4;", tag="g th8 del")
fix("RunnableC0024g.java", "post(new RunnableC0024g(b3Var2, th, 15));", "post(new RunnableC0024g(b3Var2, th8, 15));", tag="g th8 use")
fix("RunnableC0024g.java", "                } catch (Throwable th9) {\n                    th = th9;\n                }\n                break;",
    "                } catch (Throwable th9) {\n                }\n                break;", tag="g th9 del")

# 16. RunnableC0054n1 q1 参数
fix("RunnableC0054n1.java", "new q1(activity2, i, textViewA, str, list, c0067q2, runnableArr)",
    "new q1(activity2, z2, textViewA, str, list, c0067q2, runnableArr)", tag="q1 args")

# 17. RunnableC0058o1 run(): 去 throws + sneaky 包裹
t = load("RunnableC0058o1.java")
old_decl = "    public final void run() throws JSONException, IOException {"
if old_decl in t:
    t = t.replace(old_decl, "    public final void run() {", 1)
    # 方法体起止
    i0 = t.index("public final void run() {")
    bstart = t.index("{", i0)
    depth = 0
    j = bstart
    while j < len(t):
        if t[j] == "{": depth += 1
        elif t[j] == "}":
            depth -= 1
            if depth == 0: break
        j += 1
    body = t[bstart+1:j]
    wrapped = ("{\n        try {" + body +
               "\n        } catch (JSONException | IOException e) {\n"
               "            sneakyThrow(e);\n        }\n    }")
    t = t[:bstart] + wrapped + t[j+1:]
    # 追加 sneakyThrow helper 到类尾（最后一个 } 前）
    last = t.rfind("}")
    helper = ("\n    private static <T extends Throwable> void sneakyThrow(Throwable ex) throws T {\n"
              "        throw (T) ex;\n    }\n")
    t = t[:last] + helper + t[last:]
    save("RunnableC0058o1.java", t)
    ok += 1
    print("o1: run wrapped")
else:
    print("[MISS] o1 decl"); miss += 1

# 18. S1 e
fix("S1.java", "            } catch (Exception C0019e2) {\n                e = C0019e2;\n                t1Var = null;",
    "            } catch (Exception C0019e2) {\n                t1Var = null;", tag="S1 e del")

# 19. V
fix("V.java",
    "                            try {\n                                zipOutputStream.close();\n                            } catch (Throwable th) {\n                                th.addSuppressed(th);\n                            }\n                            throw th;\n",
    "", tag="V junk")
fix("V.java", "Comparator.comparing(new Object())", "Comparator.comparing(new X())", tag="V sort")
fix("V.java", "new h(2)", "new RunnableC0028h(2)", tag="V h2")
fix("V.java", "new h(3)", "new RunnableC0028h(3)", tag="V h3")
fix("V.java", "public static final LinkedHashMap d = new LinkedHashMap();",
    "public static final LinkedHashMap<String, String> d = new LinkedHashMap<>();", tag="V field d")
fix("V.java", "LinkedHashMap linkedHashMap = d;", "LinkedHashMap<String, String> linkedHashMap = d;", tag="V local d")
fix("V.java", "y yVarA = A.a(z, arg);", "C0095y yVarA = A.a(z, arg);", tag="V y")
fix("V.java", "z zVar = yVarA.a;", "C0099z zVar = yVarA.a;", tag="V z")

# 20. W2 尾部
fix("W2.java", "        Files.deleteIfExists(fileCreateTempFile.toPath());\n        throw th4;\n    }",
    "        Files.deleteIfExists(fileCreateTempFile.toPath());\n    }", tag="W2 tail")

# 21. RunnableC0028h 局部泛型 + AbstractC0036j/B2 字段泛型
fix("RunnableC0028h.java", "Map map = AbstractC0036j.d;", "Map<Object, Object> map = AbstractC0036j.d;", tag="h map")
fix("AbstractC0036j.java", "public static final Map d = Collections.synchronizedMap(new WeakHashMap());",
    "public static final Map<Object, Object> d = Collections.synchronizedMap(new WeakHashMap<>());", tag="j field d")
fix("AbstractC0036j.java", "public static final Map e = Collections.synchronizedMap(new WeakHashMap());",
    "public static final Map<Object, Object> e = Collections.synchronizedMap(new WeakHashMap<>());", tag="j field e")
fix("B2.java", "public static final WeakHashMap k = new WeakHashMap();",
    "public static final WeakHashMap<Object, Object> k = new WeakHashMap<>();", tag="B2 k")

# 22. P 单字母
fix("P.java", "AbstractC0075t.e.post(new q(activity, z, this.c, thisObject));",
    "AbstractC0075t.e.post(new RunnableC0064q(activity, z, this.c, thisObject));", tag="P q")

print(f"WaveD: ok={ok} miss={miss}")
