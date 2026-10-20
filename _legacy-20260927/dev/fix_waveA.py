# -*- coding: utf-8 -*-
"""WaveA 主修复脚本
1) 全限定类型别名映射
2) 内容级手工修复（catch流/int-boolean/super顺序/泛型字段/类型转换...）
3) 内联假匿名类 -> 真类实例化（通用规则 + MANUAL 表）
4) SoulHook 漏译循环重建 / M0/C0039j2 补方法
"""
import re, io, os, glob, sys

BASE = r"E:\student\project\new-soul-1.34.0"
SRC = os.path.join(BASE, "dev", "app", "src", "main", "java")

RENAME = {
 "a":"C0000a","a0":"C0001a0","a2":"C0003a2","b":"C0004b","b2":"C0007b2",
 "c1":"DialogInterfaceOnDismissListenerC0010c1","d0":"RunnableC0013d0","d1":"C0014d1",
 "d2":"RunnableC0015d2","e":"C0016e","e0":"RunnableC0017e0","e1":"C0018e1","e2":"C0019e2",
 "f":"C0020f","f0":"C0021f0","f1":"C0022f1","f2":"C0023f2","g":"RunnableC0024g",
 "g0":"AbstractC0025g0","g1":"AbstractC0026g1","g2":"C0027g2","h":"RunnableC0028h",
 "h1":"ViewOnLayoutChangeListenerC0030h1","h2":"C0031h2","i":"C0032i",
 "i0":"ViewOnClickListenerC0033i0","i1":"C0034i1","i2":"C0035i2","j":"AbstractC0036j",
 "j0":"DialogInterfaceOnDismissListenerC0037j0","j1":"AbstractC0038j1","j2":"C0039j2",
 "k":"AbstractC0040k","k0":"C0041k0","k1":"C0042k1","l1":"ViewOnClickListenerC0046l1",
 "m":"RunnableC0048m","m1":"RunnableC0050m1","n":"ViewOnLongClickListenerC0052n",
 "n1":"RunnableC0054n1","o":"AbstractC0056o","o0":"C0057o0","o1":"RunnableC0058o1",
 "p0":"AbstractC0061p0","p1":"RunnableC0062p1","p2":"ViewOnAttachStateChangeListenerC0063p2",
 "q":"RunnableC0064q","q0":"ViewOnLongClickListenerC0065q0","q2":"C0067q2",
 "r0":"AbstractC0068r0","r2":"C0070r2","s":"C0071s","s0":"ViewOnClickListenerC0072s0",
 "t":"AbstractC0075t","t0":"C0076t0","t1":"C0077t1","t2":"C0078t2","u":"C0079u",
 "u1":"AbstractC0081u1","u2":"C0082u2","v1":"C0085v1","v2":"C0086v2","w":"C0087w",
 "w1":"C0089w1","w2":"AbstractC0090w2","x":"C0091x","x0":"ViewOnClickListenerC0092x0",
 "x1":"C0093x1","x2":"C0094x2","y":"C0095y","y0":"C0096y0","y1":"C0097y1",
 "z":"C0099z","z0":"AbstractC0100z0","z1":"AbstractC0101z1",
}

MANUAL_ARGS = {
 "K1": "c0067q5, sharedPreferencesO2, arrayList",
 "L1": "strArr6",
 "k2": "this",
 "Q1": "c0027g2, r2, runnableC0050m1, sharedPreferencesO2",
 "A1": "this, f2, left, f, width",
 "v0": "this, c0067q2",
 "w0": "this, c1",
}

def read(fp):
    return io.open(fp, encoding="utf-8", errors="replace").read()

def write(fp, text):
    io.open(fp, "w", encoding="utf-8", newline="\n").write(text)

def replace_once(text, old, new, fname, tag):
    if old not in text:
        print(f"  [MISS] {tag} :: {fname} :: {old[:70]!r}")
        return text, False
    return text.replace(old, new, 1), True

# ---------- Part 1: 全限定类型映射 ----------
def part1():
    keys = sorted(RENAME, key=len, reverse=True)
    pat = re.compile(r"\bdev\.local\.newsoul\.(?:" + "|".join(re.escape(k) for k in keys) + r")\b")
    n = 0
    for fp in glob.glob(os.path.join(SRC, "*.java")):
        t = read(fp)
        t2, cnt = pat.subn(lambda m: "dev.local.newsoul." + RENAME[m.group(0).split(".")[-1]], t)
        if cnt:
            write(fp, t2)
            n += cnt
    print(f"[Part1] 全限定映射替换 {n} 处")

# ---------- Part 2: 内容级手工修复 ----------
def part2():
    def load(f): return read(os.path.join(SRC, f))
    def save(f, t): write(os.path.join(SRC, f), t)
    ok = miss = 0

    # C0067q2
    t = load("C0067q2.java")
    t, r = replace_once(t, "z != 0 ? c0067q2.i : c0067q2.j", "z ? c0067q2.i : c0067q2.j", "C0067q2", "z!=0")
    ok += r; miss += (not r)
    t, r = replace_once(t, "z ? R.style.Theme.Material.Dialog.Alert : R.style.Theme.Material.Light.Dialog.Alert",
                        "z ? 16974374 : 16974394", "C0067q2", "R.style->0x01030226/0x0103023a")
    ok += r; miss += (not r)
    t, r = replace_once(t, "public final ArrayList n = new ArrayList();",
                        "public final ArrayList<C0039j2> n = new ArrayList<>();", "C0067q2", "field n generic")
    ok += r; miss += (not r)
    save("C0067q2.java", t)

    # C0000a  catch流 / 1==true / z2
    t = load("C0000a.java")
    t, r = replace_once(t, "                                    th = th2;\n                                    Log.e(\"SoulHook102\", \"recall interception failed\", th);",
                        "                                    Log.e(\"SoulHook102\", \"recall interception failed\", th2);", "C0000a", "th flow")
    ok += r; miss += (not r)
    t, r = replace_once(t, "                    th = th3;\n                    z = false;",
                        "                    z = false;", "C0000a", "th3")
    ok += r; miss += (not r)
    before = t
    t = t.replace("1 == true ? 1 : 0", "1")
    c1 = before.count("1 == true")
    ok += 1 if c1 else 0; miss += 0 if c1 else 1
    before = t
    t = t.replace("z2 = 0;", "z2 = false;")
    t = t.replace("z2 = i;", "z2 = false;")
    print(f"  C0000a z2/fixed: {before.count('z2 = 0;') + before.count('z2 = i;')} 处")
    save("C0000a.java", t)

    # SoulHook th/r7循环重建
    t = load("SoulHook.java")
    t, r = replace_once(t, "                                        th = th5;\n                                        AbstractC0036j.c(\"feed data filter\", th);",
                        "                                        AbstractC0036j.c(\"feed data filter\", th5);", "SoulHook", "th5")
    ok += r; miss += (not r)
    t, r = replace_once(t, "                } catch (Throwable th13) {\n                    th = th13;\n                }",
                        "                } catch (Throwable th13) {\n                }", "SoulHook", "th13")
    ok += r; miss += (not r)
    # 漏译循环重建 (dex 03c0-040e)
    old_loop = '''                        if (View.class.isAssignableFrom(clsLoadClass)) {
                            throw new IllegalStateException("not a View");
                        }
                        while (i2 < r7) {
                            AbstractC0036j.g.hook(constructor).setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE).intercept(new C0020f(0, str2));
                        }
                        set.add(str2);
                    }'''
    new_loop = '''                        if (!View.class.isAssignableFrom(clsLoadClass)) {
                            throw new IllegalStateException("not a View");
                        }
                        try {
                            Constructor<?>[] declaredConstructors = clsLoadClass.getDeclaredConstructors();
                            int i2 = 0;
                            while (i2 < declaredConstructors.length) {
                                Constructor<?> constructor = declaredConstructors[i2];
                                AbstractC0036j.g.hook(constructor).setExceptionMode(XposedInterface.ExceptionMode.PROTECTIVE).intercept(new C0020f(0, str2));
                                i2++;
                            }
                            set.add(str2);
                        } catch (Throwable thx) {
                            AbstractC0036j.c(str, thx);
                        }
                        i++;
                    }'''
    t, r = replace_once(t, old_loop, new_loop, "SoulHook", "ad-loop rebuild")
    ok += r; miss += (not r)
    save("SoulHook.java", t)

    # K0 无效 catch 赋值
    t = load("K0.java")
    t, r = replace_once(t, "                    } catch (Throwable th) {\n                        th = th;\n                        canvas.restoreToCount(i3);",
                        "                    } catch (Throwable th) {\n                        canvas.restoreToCount(i3);", "K0", "th=th")
    ok += r; miss += (not r)
    t, r = replace_once(t, "                } catch (RuntimeException e2) {\n                    e = e2;\n                    i3 = iSave;",
                        "                } catch (RuntimeException e2) {\n                    i3 = iSave;", "K0", "e=e2")
    ok += r; miss += (not r)
    t, r = replace_once(t, "        } catch (Throwable th2) {\n            th = th2;\n            i3 = iSave;\n        }",
                        "        } catch (Throwable th2) {\n            i3 = iSave;\n        }", "K0", "th=th2")
    ok += r; miss += (not r)
    save("K0.java", t)

    # AbstractC0061p0
    t = load("AbstractC0061p0.java")
    t, r = replace_once(t, "                } catch (Throwable th) {\n                    th = th;\n                    c(\"header\", th);",
                        "                } catch (Throwable th) {\n                    c(\"header\", th);", "A61", "th=th")
    ok += r; miss += (not r)
    t, r = replace_once(t, "            } catch (Throwable th2) {\n                th = th2;\n                xposedModule2 = xposedModule;\n            }",
                        "            } catch (Throwable th2) {\n                xposedModule2 = xposedModule;\n            }", "A61", "th=th2")
    ok += r; miss += (not r)
    t, r = replace_once(t, "for (Map.Entry entry : f.entrySet())",
                        "for (Map.Entry<Object, Object> entry : f.entrySet())", "A61", "Entry")
    ok += r; miss += (not r)
    save("AbstractC0061p0.java", t)

    # AbstractC0081u1 catch流
    t = load("AbstractC0081u1.java")
    t, r = replace_once(t, "                                } catch (Exception e2) {\n                                    e = e2;\n                                    c0077t1 = c0077t2;\n                                    Log.w(\"SoulHook102\", \"profile bookmark menu unavailable\", e);",
                        "                                } catch (Exception e2) {\n                                    c0077t1 = c0077t2;\n                                    Log.w(\"SoulHook102\", \"profile bookmark menu unavailable\", e2);", "A81", "e flow")
    ok += r; miss += (not r)
    t, r = replace_once(t, "                    } catch (Exception e3) {\n                        e = e3;\n                    }",
                        "                    } catch (Exception e3) {\n                    }", "A81", "e3")
    ok += r; miss += (not r)
    save("AbstractC0081u1.java", t)

    # C1 super 顺序
    t = load("C1.java")
    t, r = replace_once(t, "        Context context = c0067q2.g;\n        super(context);",
                        "        super(c0067q2.g);\n        Context context = c0067q2.g;", "C1", "super order")
    ok += r; miss += (not r)
    save("C1.java", t)

    # M0 static super -> 实例辅助
    t = load("M0.java")
    t, r = replace_once(t, "        int iSaveLayer = canvas.saveLayer(0.0f, 0.0f, m0.getWidth(), m0.getHeight(), m0.p);\n        super.dispatchDraw(canvas);",
                        "        int iSaveLayer = canvas.saveLayer(0.0f, 0.0f, m0.getWidth(), m0.getHeight(), m0.p);\n        m0.superDispatchDraw(canvas);", "M0", "static super")
    ok += r; miss += (not r)
    if "superDispatchDraw" not in t:
        t, r = replace_once(t, "    public final void dispatchDraw(Canvas canvas) {",
                        "    private void superDispatchDraw(Canvas canvas) {\n        super.dispatchDraw(canvas);\n    }\n\n    public final void dispatchDraw(Canvas canvas) {", "M0", "helper add")
        ok += r; miss += (not r)
    save("M0.java", t)

    # B1 casts
    t = load("B1.java")
    t, r = replace_once(t, "                C1 DialogInterfaceOnDismissListenerC0010c1 = this.b;",
                        "                C1 DialogInterfaceOnDismissListenerC0010c1 = (C1) this.b;", "B1", "cast C1")
    ok += r; miss += (not r)
    t, r = replace_once(t, "                C0039j2 j2Var = this.b;",
                        "                C0039j2 j2Var = (C0039j2) this.b;", "B1", "cast j2")
    ok += r; miss += (not r)
    save("B1.java", t)

    # C0031h2 字段泛型
    t = load("C0031h2.java")
    t, r = replace_once(t, "public final ArrayList a = new ArrayList();",
                        "public final ArrayList<CompoundButton> a = new ArrayList<>();", "C0031h2", "generic")
    ok += r; miss += (not r)
    save("C0031h2.java", t)

    # C0014d1 final i -> int i
    t = load("C0014d1.java")
    t, r = replace_once(t, "        final int i = 0;", "        int i = 0;", "C0014d1", "final i")
    ok += r; miss += (not r)
    save("C0014d1.java", t)

    # C0039j2 显式构造器
    t = load("C0039j2.java")
    if "public C0039j2(" not in t and "C0039j2(android.content.Context" not in t:
        # 插入到类体开头（首个字段前）
        m = re.search(r"(public final class C0039j2 extends FrameLayout \{\n)", t)
        if m:
            t = t[:m.end()] + "    public C0039j2(android.content.Context context) {\n        super(context);\n    }\n\n" + t[m.end():]
            ok += 1
        else:
            miss += 1; print("  [MISS] C0039j2 ctor anchor")
    save("C0039j2.java", t)

    # C / D / L1 单字母类型与静态引用
    t = load("C.java")
    for old, new in [
        ("y yVarA = V.a()", "C0095y yVarA = V.a()"),
        ("Context context = j.h;", "Context context = AbstractC0036j.h;"),
        ("j.h = context3;", "AbstractC0036j.h = context3;"),
        ("context2 = j.h;", "context2 = AbstractC0036j.h;"),
        ("!j.b(context2, \"ad_feed\")", "!AbstractC0036j.b(context2, \"ad_feed\")"),
        ("j.e(\"feed ad models filtered=", "AbstractC0036j.e(\"feed ad models filtered="),
        ("j.e(\"feed ad model skipped", "AbstractC0036j.e(\"feed ad model skipped"),
    ]:
        t, r = replace_once(t, old, new, "C", old[:30]); ok += r; miss += (not r)
    save("C.java", t)

    t = load("D.java")
    for old, new in [
        ("y yVarA = V.a()", "C0095y yVarA = V.a()"),
        ("j.d(arg.getClass(), \"itemView\")", "AbstractC0036j.d(arg.getClass(), \"itemView\")"),
        ("j.d(arg.getClass(), \"binding\")", "AbstractC0036j.d(arg.getClass(), \"binding\")"),
        ("j.i(view, this.b)", "AbstractC0036j.i(view, this.b)"),
    ]:
        t, r = replace_once(t, old, new, "D", old[:30]); ok += r; miss += (not r)
    save("D.java", t)

    t = load("L1.java")
    t, r = replace_once(t, "zContains = j.b.contains(strArr[0]);",
                        "zContains = AbstractC0036j.b.contains(strArr[0]);", "L1", "j.b")
    ok += r; miss += (not r)
    save("L1.java", t)

    print(f"[Part2] 完成 ok={ok} miss={miss}")

# ---------- Part 3: 内联转换 ----------
def find_ctor(src, cname):
    # 构造器: 无返回类型 —— public /* synthetic */ cname(params) {
    pat = re.compile(
        r"(?m)^[ \t]+(?:(?:public|private|protected|static|final|abstract|/\* synthetic \*/)\s+)*"
        + re.escape(cname) + r"\s*\(([^)]*)\)\s*(?:throws [\w., ]+)?\{")
    m = pat.search(src)
    if not m:
        return None
    params = m.group(1).strip()
    if not params:
        return []
    out, depth = [], 0
    cur = ""
    for ch in params:
        if ch == "<": depth += 1
        if ch == ">": depth -= 1
        if ch == "," and depth == 0:
            out.append(cur.strip()); cur = ""
        else:
            cur += ch
    out.append(cur.strip())
    types = []
    for p in out:
        toks = p.split()
        t = " ".join(toks[:-1]) if len(toks) > 1 else toks[0]
        # 归一化: 去包前缀
        t = t.replace("dev.local.newsoul.", "")
        types.append(t)
    return types

def primary_class(src):
    m = re.search(r"\bclass\s+(\w+)", src)
    return m.group(1) if m else None

def convert_inlines():
    files = glob.glob(os.path.join(SRC, "*.java"))
    total, fails = 0, []
    for fp in files:
        src = read(fp)
        if "// from class:" not in src:
            continue
        fname = os.path.basename(fp)
        prim = primary_class(src)
        while True:
            m = re.search(r"(?m)^(?P<head>.*?)new (?P<iface>[\w.$]+)\((?P<args>[^)]*)\) \{ // from class: dev\.local\.newsoul\.(?P<cls>\w+)(?P<mid>.*)$", src)
            if not m:
                break
            # 上面的 head 贪婪跨行 —— 改用行级扫描
            break
        # 行级处理
        lines = src.splitlines(keepends=True)
        i = 0
        out_lines = []
        while i < len(lines):
            lm = re.search(r"^(?P<indent>[ \t]*)(?P<head>.*?)(?P<nl>\r?\n?)new (?P<iface>[\w.$]+)\((?P<args>[^)]*)\) \{ // from class: dev\.local\.newsoul\.(?P<cls>\w+)", lines[i])
            if not lm:
                out_lines.append(lines[i]); i += 1; continue
            # 找到起点 —— 从 head 中的 '{' 做括号匹配
            cls = lm.group("cls")
            cls_real = RENAME.get(cls, cls)
            # 定位起始行内 '{' 位置: head 部分之后的 iface(...) 后的 {
            line = lines[i]
            hm = re.search(r"new [\w.$]+\([^)]*\) \{ // from class:", line)
            if not hm:
                out_lines.append(lines[i]); i += 1; continue
            brace_pos_in_line = line.index("{", hm.start())
            # 从 (i, brace_pos) 起做括号匹配
            depth = 0
            j, col = i, brace_pos_in_line
            done = False
            while j < len(lines):
                L = lines[j]
                k = col
                while k < len(L):
                    ch = L[k]
                    if ch == '"':
                        k += 1
                        while k < len(L):
                            if L[k] == "\\": k += 2; continue
                            if L[k] == '"': break
                            k += 1
                    elif ch == "/" and k + 1 < len(L) and L[k + 1] == "/":
                        while k < len(L) and L[k] != "\n": k += 1
                    elif ch == "/" and k + 1 < len(L) and L[k + 1] == "*":
                        e = L.find("*/", k)
                        k = (e + 1) if e >= 0 else len(L)
                    elif ch == "{":
                        depth += 1
                    elif ch == "}":
                        depth -= 1
                        if depth == 0:
                            done = True
                            break
                    k += 1
                if done:
                    break
                j += 1
                col = 0
            if not done:
                fails.append(f"{fname}:{i+1} {cls} 括号匹配失败")
                out_lines.append(lines[i]); i += 1; continue
            # 块 = lines[i..j], 尾巴 = lines[j][k+1:]
            block_text = "".join(lines[i:j + 1])
            tail = lines[j][k + 1:]  # 通常 \n 或 );\n
            # 构造参数
            hdr_args = [a.strip() for a in lm.group("args").split(",") if a.strip()]
            args = list(hdr_args)
            if cls in MANUAL_ARGS:
                args = [a.strip() for a in MANUAL_ARGS[cls].split(",")]
            else:
                # 查构造器
                # 目标类可能在别的文件 —— 全局查
                ctor = None
                for fp2 in files:
                    s2 = read(fp2)
                    if re.search(r"\bclass\s+" + re.escape(cls_real) + r"\b", s2):
                        ctor = find_ctor(s2, cls_real)
                        if ctor is not None:
                            break
                if ctor is None:
                    fails.append(f"{fname}:{i+1} {cls} 构造器未找到")
                    out_lines.append(lines[i]); i += 1; continue
                while len(args) < len(ctor):
                    ptype = ctor[len(args)].replace(" ", "")
                    # int -> switch 变量或最近 final int
                    if ptype in ("int", "Integer", "boolean", "Boolean", "byte", "short", "long", "float", "double"):
                        sw = re.search(r"switch\s*\(\s*(\w+)\s*\)", block_text)
                        var = None
                        if sw and re.search(r"\b(?:final )?int " + re.escape(sw.group(1)) + r"\b", src):
                            var = sw.group(1)
                        if var is None:
                            # 就近向前找 final int iN = 
                            pref = "".join(lines[max(0, i - 40):i])
                            fins = re.findall(r"(?:final )?int (\w+) =", pref)
                            var = fins[-1] if fins else None
                        if var:
                            args.append(var); continue
                        fails.append(f"{fname}:{i+1} {cls} int 参数无法定位")
                        break
                    elif ptype == cls_real or ptype == prim:
                        args.append("this"); continue
                    elif ptype == "XposedModule" and prim == "SoulHook":
                        args.append("this"); continue
                    elif ptype in ("C0067q2", "q2") and re.search(r"\bc0067q2\b", src):
                        args.append("c0067q2"); continue
                    elif ptype == "C1" and re.search(r"\bc1\b", src):
                        args.append("c1"); continue
                    elif ptype in ("ArrayList", "List") and re.search(r"\barrayList\b", src):
                        args.append("arrayList"); continue
                    else:
                        # 按类型找同名声明局部变量: "Type varName"
                        simple = ptype.split(".")[-1]
                        pref = "".join(lines[max(0, i - 60):i + 1])
                        decls = re.findall(r"(?<![\w.])" + re.escape(simple) + r"[<>\[\], \w.]*\s+(\w+)\s*[=;,)]", pref)
                        decls = [d for d in decls if d not in ("class", "new", "return", "this")]
                        if decls:
                            args.append(decls[-1]); continue
                        fails.append(f"{fname}:{i+1} {cls} 参数类型 {ptype} 需手工")
                        break
                if len(args) != len(ctor):
                    # 参数不齐 -> 放弃该站点
                    out_lines.append(lines[i]); i += 1; continue
            # 生成替换: head(去掉 iface(...) {...注释) + new CLS(args) + tail
            head_full = line[:hm.start()]
            newline = head_full + f"new {cls_real}({', '.join(args)})" + tail
            out_lines.append(newline)
            total += 1
            i = j + 1
        new_src = "".join(out_lines)
        if new_src != src:
            write(fp, new_src)
    print(f"[Part3] 转换 {total} 处")
    if fails:
        print("[Part3] 失败清单:")
        for f in fails:
            print("  -", f)

def main():
    if "--p3" not in sys.argv:
        part1()
        part2()
    convert_inlines()

if __name__ == "__main__":
    main()
