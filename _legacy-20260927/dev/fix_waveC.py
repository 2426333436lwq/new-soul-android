# -*- coding: utf-8 -*-
"""WaveC: 语义级修复总表"""
import io, os, re

BASE = r"E:\student\project\new-soul-1.34.0"
SRC = os.path.join(BASE, "dev", "app", "src", "main", "java")

# (文件, 旧串, 新串, 出现次数期望[可选])
FIX = [
    # SoulHook 内层循环变量重命名（避免与外层 i2 冲突）
    ("SoulHook.java",
     "                            int i2 = 0;\n                            while (i2 < declaredConstructors.length) {\n                                Constructor<?> constructor = declaredConstructors[i2];",
     "                            int constructorIdx = 0;\n                            while (constructorIdx < declaredConstructors.length) {\n                                Constructor<?> constructor = declaredConstructors[constructorIdx];"),
    ("SoulHook.java",
     "                                i2++;\n                            }\n                            set.add(str2);",
     "                                constructorIdx++;\n                            }\n                            set.add(str2);"),
    # M0 补 superDispatchDraw helper
    ("M0.java",
     "    public final void dispatchDraw(Canvas canvas) {",
     "    private void superDispatchDraw(Canvas canvas) {\n        super.dispatchDraw(canvas);\n    }\n\n    public final void dispatchDraw(Canvas canvas) {"),
    # C0067q2 l2 / m2 参数
    ("C0067q2.java",
     "new l2(this, popupWindow3, intConsumer, intConsumer, i3)",
     "new l2(this, popupWindow3, view, intConsumer, i3)"),
    ("C0067q2.java",
     "new m2(viewOnAttachStateChangeListenerC0063p2, viewOnAttachStateChangeListenerC0063p2)",
     "new m2(view, viewOnAttachStateChangeListenerC0063p2)"),
    # AbstractC0036j d 参数
    ("AbstractC0036j.java", "new d(z, i2)", "new d(str2, z)"),  # x2
    # AbstractC0090w2 S1 / U1
    ("AbstractC0090w2.java", "new S1(c0070r4, i3)", "new S1(c0070r2, i3)"),
    ("AbstractC0090w2.java", "new U1(i, c0070r2, strTrim)", "new U1(sharedPreferences, c0070r2, str)"),
    # AbstractC0100z0 u0 末参
    ("AbstractC0100z0.java",
     "new u0(c0096y3, c0096y3, c0096y3, c0096y3, sharedPreferences, c0067q2, z)",
     "new u0(c0096y3, c0096y3, c0096y3, c0096y3, sharedPreferences, c0067q2, activity)"),
    # B2 z2 三站
    ("B2.java", "new z2(classLoader, i2)", "new z2(clsLoadClass3, i2)"),
    ("B2.java", "new z2(classLoader, i3)", "new z2(clsLoadClass3, i3)"),
    ("B2.java", "new z2(classLoader, i4)", "new z2(clsLoadClass3, i4)"),
    # Z0
    ("Z0.java", "final BooleanSupplier r2 = new L1(strArr6);", "final L1 r2 = new L1(strArr6);"),
    ("Z0.java", "final ArrayList arrayList = new ArrayList();", "ArrayList arrayList = new ArrayList();"),
    ("Z0.java", "new R1(sharedPreferencesO4, strArr9, c1)", "new R1(sharedPreferencesO4, strArr9, c3)"),
    # F N2
    ("F.java", "new N2(14, i3)", "new N2(d3Var, i3)"),
    # H th 流 + final i + Y2 参数
    ("H.java",
     "                            th = th8;\n                            d3.f(activity7, \"发送未完成，请查看消息状态\", th);",
     "                            d3.f(activity7, \"发送未完成，请查看消息状态\", th8);"),
    ("H.java",
     "                    th = th9;\n                    i = 0;",
     "                    i = 0;"),
    ("H.java",
     "                    d3.f(activity7, \"发送未完成，请查看消息状态\", th);\n                    return;",
     "                    d3.f(activity7, \"发送未完成，请查看消息状态\", th9);\n                    return;"),
    ("H.java", "final int i = 1;", "int i = 1;"),
    ("H.java", "new Y2(arrayList, c0067q2, scrollView, i7)", "new Y2(b3Var2, c0067q2, scrollView, i7)"),
    # F1 k2 别名
    ("F1.java", "C0043k2", "k2"),
    # H1/P/L 单字母静态引用
    ("H1.java", "return t.a;", "return AbstractC0075t.a;"),
    ("H1.java", "return t.b;", "return AbstractC0075t.b;"),
    ("P.java", "if (t.c.get() != null) {", "if (AbstractC0075t.c.get() != null) {"),
    ("P.java", "t.e.post(new q(activity, z, this.c, thisObject));", "AbstractC0075t.e.post(new q(activity, z, this.c, thisObject));"),
    ("L.java", "o.a(this.a);", "AbstractC0056o.a(this.a);"),
    # I0 静态方法
    ("I0.java",
     "return p0.createRuntimeShaderEffect(\"content\");",
     "return android.graphics.RenderEffect.createRuntimeShaderEffect(p0, \"content\");"),
    # G 字段泛型
    ("G.java",
     "public static final LinkedHashMap a = new LinkedHashMap();",
     "public static final LinkedHashMap<Object, Object> a = new LinkedHashMap<>();"),
    ("G.java",
     "public static final LinkedHashMap b = new LinkedHashMap();",
     "public static final LinkedHashMap<Object, Object> b = new LinkedHashMap<>();"),
    # K 字段泛型 + zCommit final
    ("K.java", "public final /* synthetic */ Map c;", "public final /* synthetic */ Map<Object, Object> c;"),
    ("K.java", "final boolean zCommit = false;", "boolean zCommit = false;"),
    # AbstractC0061p0 f 泛型
    ("AbstractC0061p0.java",
     "public static final WeakHashMap f = new WeakHashMap();",
     "public static final WeakHashMap<Object, Object> f = new WeakHashMap<>();"),
    # L0 casts
    ("L0.java", "M0 m0 = this.b;", "M0 m0 = (M0) this.b;"),
    ("L0.java", "Z0 AbstractC0100z0 = this.b;", "Z0 AbstractC0100z0 = (Z0) this.b;"),
]

def main():
    ok = miss = 0
    per_file = {}
    for fname, old, new in FIX:
        fp = os.path.join(SRC, fname)
        t = io.open(fp, encoding="utf-8", errors="replace").read()
        cnt = t.count(old)
        if cnt == 0:
            print(f"[MISS] {fname}: {old[:60]!r}")
            miss += 1
            continue
        t = t.replace(old, new)  # 全部替换（如 d(z,i2) x2）
        io.open(fp, "w", encoding="utf-8", newline="\n").write(t)
        ok += cnt
    # P2 接口加 throws
    fp = os.path.join(SRC, "P2.java")
    t = io.open(fp, encoding="utf-8", errors="replace").read()
    if "throws" not in t:
        t2 = re.sub(r"(void a\(String str\))(;)", r"\1 throws IllegalAccessException, InvocationTargetException\2", t, count=1)
        if t2 != t:
            io.open(fp, "w", encoding="utf-8", newline="\n").write(t2)
            ok += 1
            print("P2: throws added")
        else:
            print("[MISS] P2 pattern:", repr(t))
            miss += 1
    print(f"WaveC: 替换 {ok} 处, 失败 {miss}")

if __name__ == "__main__":
    main()
