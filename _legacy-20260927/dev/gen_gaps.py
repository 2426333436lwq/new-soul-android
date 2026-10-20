# -*- coding: utf-8 -*-
"""缺口方法翻译器 v2 —— 输出 gaps_fix_proposal.txt
修正: 寄存器键统一字符串 / 参数名 pN / const-string / move /
     丢弃返回值的 invoke / 构造器 super() 置顶且不生成 return
"""
import re, io, os

BASE = r"E:\student\project\new-soul-1.34.0"
SRC = os.path.join(BASE, "dev", "gaps_dump.txt")
OUT = os.path.join(BASE, "dev", "gaps_fix_proposal.txt")
SKIP_HAND = {"T0": {"c"}}  # 含控制流, 手写

JAVA_KW = {"int","long","float","boolean","class","new","return","void","this","static","public","private","final"}

def jtype(t):
    t = t.strip()
    dims = ""
    while t.startswith("["):
        dims += "[]"; t = t[1:]
    if t.startswith("L"):
        return t[1:-1].replace("/", ".") + dims
    return {"I":"int","Z":"boolean","V":"void","J":"long","F":"float",
            "D":"double","B":"byte","C":"char","S":"short"}.get(t,"Object") + dims

def parse_sig(proto):
    m = re.match(r"\((.*?)\)(.*)", proto)
    params, ret = m.group(1), m.group(2)
    out, i = [], 0
    while i < len(params):
        start = i
        while i < len(params) and params[i] == "[":
            i += 1
        if params[i] == "L":
            i = params.index(";", i) + 1
        else:
            i += 1
        out.append(jtype(params[start:i]))
    return out, jtype(ret)

def escape_str(s):
    return '"' + s.replace("\\", "\\\\").replace('"', '\\"') + '"'

def translate(cname, mname, proto, access, body_lines):
    is_static = "STATIC" in access
    is_ctor = mname == "<init>"
    params, ret = parse_sig(proto)
    argnames = [f"p{i}" for i in range(len(params))]
    nregs = nins = None
    insns = []
    for l in body_lines:
        m = re.match(r"        registers     : (\d+)", l)
        if m: nregs = int(m.group(1)); continue
        m = re.match(r"        ins           : (\d+)", l)
        if m: nins = int(m.group(1)); continue
        m = re.search(r"\|\d{4}: (.*)$", l)
        if m: insns.append(m.group(1).strip())
    if nregs is None or nins is None:
        return None

    rm = {}
    cur = nregs - nins
    if not is_static:
        rm[f"v{cur}"] = "this"; cur += 1
    for an in argnames:
        if cur < nregs:
            rm[f"v{cur}"] = an; cur += 1

    stmts = []
    for ins in insns:
        if ins.startswith("["):
            continue
        code = ins.split("//")[0].strip()
        if not code:
            continue
        m = re.match(r"const-string(?:/jumbo)? (v\d+), (\".*?\")$", code)
        if m:
            # dexdump 已带引号但转义形式, 直接用其字面（dexdump 输出为可读形式）
            stmts.append(("assign", m.group(1), m.group(2))); continue
        m = re.match(r"const(?:/4|/16|/high16)? (v\d+), #int (-?\d+)", code)
        if m:
            stmts.append(("assign", m.group(1), m.group(2))); continue
        m = re.match(r"const (v\d+), #float (\S+)", code)
        if m:
            stmts.append(("assign", m.group(1), m.group(2))); continue
        m = re.match(r"move(?:/16)? (v\d+), (v\d+)$", code)
        if m:
            stmts.append(("movereg", m.group(1), m.group(2))); continue
        m = re.match(r"move-object(?:/16)? (v\d+), (v\d+)$", code)
        if m:
            stmts.append(("movereg", m.group(1), m.group(2))); continue
        m = re.match(r"iget(?:-wide|-object|-boolean|-byte|-char|-short)? (v\d+), (v\d+), L([\w/$]+);\.(.+?):(.+)", code)
        if m:
            dst, src, owner, fld, ftype = m.groups()
            stmts.append(("load", dst, src, fld)); continue
        m = re.match(r"sget(?:-wide|-object|-boolean)? (v\d+), L([\w/$]+);\.(.+?):(.+)", code)
        if m:
            dst, owner, fld, ftype = m.groups()
            stmts.append(("loads", dst, owner.replace("/", ".").replace("$", "."), fld)); continue
        m = re.match(r"iput(?:-wide|-object|-boolean)? (v\d+), (v\d+), L([\w/$]+);\.(.+?):(.+)", code)
        if m:
            src, dst, owner, fld, ftype = m.groups()
            stmts.append(("store", dst, fld, src)); continue
        m = re.match(r"invoke-direct \{([^}]*)\}, Ljava/lang/Object;\.<init>:\(\)V", code)
        if m:
            stmts.append(("super",)); continue
        m = re.match(r"invoke-(?:virtual|direct|static|interface|super)(?:/range)? \{([^}]*)\}, L([\w/$]+);\.(.+?):\((.*?)\)(.+)", code)
        if m:
            regs, owner, mname2, ptypes, rtype = m.groups()
            regs = regs.strip()
            if ".." in regs:
                a, b = re.findall(r"v(\d+)", regs)
                reglist = [f"v{n}" for n in range(int(a), int(b) + 1)]
            else:
                reglist = [x.strip() for x in regs.split(",") if x.strip()]
            stmts.append(("invoke", owner, mname2, rtype, reglist)); continue
        m = re.match(r"move-result(?:-object|-wide|-boolean)? (v\d+)", code)
        if m:
            stmts.append(("moveresult", m.group(1))); continue
        if code == "return-void":
            stmts.append(("returnvoid",)); continue
        m = re.match(r"return(?:-object|-wide|-boolean)? (v\d+)", code)
        if m:
            stmts.append(("return", m.group(1))); continue
        if code == "pop":
            stmts.append(("pop",)); continue
        stmts.append(("raw", code))

    def E(reg):
        """寄存器 -> 表达式"""
        if reg in rm:
            return rm[reg]
        return f"/*{reg}*/"

    java = []
    i = 0
    while i < len(stmts):
        st = stmts[i]
        if st[0] == "assign":
            rm[st[1]] = st[2]; i += 1; continue
        if st[0] == "movereg":
            rm[st[1]] = E(st[2]); i += 1; continue
        if st[0] == "load":
            rm[st[1]] = f"{E(st[2])}.{st[3]}"; i += 1; continue
        if st[0] == "loads":
            rm[st[1]] = f"{st[2]}.{st[3]}"; i += 1; continue
        if st[0] == "store":
            _, obj, fld, src = st
            java.append(f"        {E(obj)}.{fld} = {E(src)};"); i += 1; continue
        if st[0] == "super":
            java.append("        super();"); i += 1; continue
        if st[0] == "invoke":
            _, owner, mname2, rtype, reglist = st
            if reglist:
                recv = E(reglist[0])
                args = [E(r) for r in reglist[1:]]
                call = f"{recv}.{mname2}({', '.join(args)})"
            else:
                own = owner.replace("/", ".").replace("$", ".")
                call = f"{own}.{mname2}()"
            nxt = stmts[i + 1] if i + 1 < len(stmts) else None
            if nxt and nxt[0] == "moveresult":
                rm[nxt[1]] = call; i += 2; continue
            java.append(f"        {call};")
            i += 1; continue
        if st[0] == "moveresult":
            # 孤立 move-result（invoke 分支已消费）—— 兜底
            i += 1; continue
        if st[0] == "returnvoid":
            if not is_ctor:
                java.append("        return;")
            i += 1; continue
        if st[0] == "return":
            java.append(f"        return {E(st[1])};"); i += 1; continue
        if st[0] == "pop":
            i += 1; continue
        if st[0] == "raw":
            java.append(f"        // TODO-RAW: {st[1]}"); i += 1; continue
        i += 1

    if is_ctor:
        # super() 置顶 (dex 可能在 iput 后才调 super, 语义等价调整)
        body = [l for l in java if l.strip() != "super();"]
        sig = f"    public {cname}({', '.join(f'{t} {n}' for t, n in zip(params, argnames))}) {{"
        text = ["        super();"] + body + ["    }"]
    else:
        mods = []
        if "PUBLIC" in access: mods.append("public")
        if "PRIVATE" in access: mods.append("private")
        if "STATIC" in access: mods.append("static")
        if "FINAL" in access and "STATIC" not in access: mods.append("final")
        sig = f"    {' '.join(mods)} {ret} {mname}({', '.join(f'{t} {n}' for t, n in zip(params, argnames))}) {{"
        text = java + ["    }"]
    return "\n".join([sig] + text), all("TODO-RAW" not in l for l in text)

T0_C_HAND = '''    public static int c(java.lang.String p0, java.lang.String p1) {
        Pattern pattern = a;
        if (!pattern.matcher(p0).matches() || !pattern.matcher(p1).matches()) {
            throw new IllegalArgumentException("无法识别版本号");
        }
        String[] split = p0.split("\\\\.");
        String[] split2 = p1.split("\\\\.");
        int i = 0;
        while (i < 3) {
            int compareTo = new java.math.BigInteger(split[i]).compareTo(new java.math.BigInteger(split2[i]));
            if (compareTo != 0) {
                return compareTo;
            }
            i++;
        }
        return 0;
    }'''

def main():
    content = io.open(SRC, encoding="utf-8").read()
    blocks = re.split(r"(?m)^########## class (\w+) ##########$", content)
    results = {}
    for i in range(1, len(blocks), 2):
        cname = blocks[i]
        body = blocks[i + 1]
        methods = re.split(r"(?m)^-- method (\S+) (type\s+: '.*?') --$", body)
        for j in range(1, len(methods), 3):
            mname = methods[j]
            if mname in SKIP_HAND.get(cname, set()):
                text = T0_C_HAND if cname == "T0" else "    // ===== 手写 ====="
                results.setdefault(cname, []).append((mname, text, True))
                continue
            proto = re.search(r"'(.+)'", methods[j + 1]).group(1)
            mtext = methods[j + 2]
            acc = re.search(r"access\s+: (0x\w+) \(([^)]+)\)", mtext)
            access = acc.group(2) if acc else "PUBLIC"
            res = translate(cname, mname, proto, access, mtext.splitlines())
            if res:
                text, ok = res
                results.setdefault(cname, []).append((mname, text, ok))
    with io.open(OUT, "w", encoding="utf-8", newline="\n") as f:
        for cname, items in results.items():
            f.write(f"\n// ========== {cname} ==========\n")
            for mname, text, ok in items:
                if not ok:
                    f.write(f"// !!! 未识别: {mname}\n")
                f.write(text + "\n")
    print("classes:", len(results), "methods:", sum(len(v) for v in results.values()))
    bad = [(c, m) for c, items in results.items() for m, t, ok in items if not ok]
    print("unrecognized:", bad)
    import json
    with io.open(os.path.join(BASE, "dev", "gaps_fix.json"), "w", encoding="utf-8") as f:
        json.dump({c: [{"name": m, "text": t} for m, t, _ in items]
                   for c, items in results.items()}, f, ensure_ascii=False, indent=1)
    print("json written")

if __name__ == "__main__":
    main()
