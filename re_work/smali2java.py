"""
Smali -> Java translator for the new-soul LSPosed module.

Parses baksmali output text directly (baksmali emits a very regular format)
and emits readable Java-ish source with:
  - real type descriptors resolved to Java types
  - string/int/float constants inlined
  - method/field references resolved
  - register-tracked expressions
  - labelled jumps preserved where control flow cannot be structured

This is a *reconstruction aid*, not a compilable-program generator. It is
intended to let a human read every hook's logic and re-implement it.
"""
import re, os, sys, json
from collections import defaultdict

# ------------------------------------------------------------------ types
PRIM = {
    'V': 'void', 'Z': 'boolean', 'B': 'byte', 'S': 'short',
    'C': 'char', 'I': 'int', 'J': 'long', 'F': 'float', 'D': 'double',
}

def java_type(d: str) -> str:
    """DEX type descriptor -> Java source type."""
    if d is None:
        return 'void'
    if d in PRIM:
        return PRIM[d]
    if d.startswith('['):
        # []Lfoo/Bar;  ->  Foo.Bar[]
        inner = d[1:]
        base = java_type(inner)
        return base + '[]'
    if d.startswith('L') and d.endswith(';'):
        cls = d[1:-1]
        simple = cls.split('/')[-1]
        # keep known framework/short names, full-qualify our own package
        if '/' in cls and cls.startswith('dev/local/newsoul/'):
            return simple
        if '/' in cls:
            return simple
        return cls
    return d

def short_cls(d: str) -> str:
    if d is None:
        return 'void'
    if d.startswith('['):
        return short_cls(d[1:]) + '[]'
    if d.startswith('L') and d.endswith(';'):
        return d[1:-1].split('/')[-1]
    return d

def esc(s: str) -> str:
    return json.dumps(s, ensure_ascii=False)

# ------------------------------------------------------------------ smali parsing
HDR_CLASS = re.compile(r'^\.class\s+([\w\s]+)\s+(\S+);$')
HDR_SUPER = re.compile(r'^\.super\s+(\S+);$')
HDR_SOURCE = re.compile(r'^\.source\s+"(.*)"$')
FIELD_RE = re.compile(
    r'^\.field\s+([\w\s]+?)\s+(\S+):(\S+?)(?:\s*=\s*(.+))?$')
METHOD_RE = re.compile(r'^\.method\s+(.+?)\s+(\S+)\((.*?)\)(\S+)$')
IMPL_RE = re.compile(r'^\.implements\s+(\S+);$')

INSN_RE = re.compile(r'^\s+([a-z0-9\-]+(?:\s*/\s*[a-z0-9]+)?)\s*(.*)$')
LABEL_RE = re.compile(r'^\s*(:[a-z0-9_]+)$')
DIRECTIVE_RE = re.compile(r'^\s+\.(registers|locals|line|prologue|epilogue|local|restart|end)')

class Smali:
    def __init__(self, text: str):
        self.lines = text.split('\n')
        self.i = 0
        self.cls = None
        self.access = ''
        self.super = None
        self.source = None
        self.ifaces = []
        self.fields = []        # (access, name, type, init)
        self.methods = []       # dict

    def parse(self):
        while self.i < len(self.lines):
            ln = self.lines[self.i].rstrip()
            if ln.startswith('.class '):
                m = HDR_CLASS.match(ln)
                self.access, self.cls = m.group(1).strip(), m.group(2)
            elif ln.startswith('.super '):
                self.super = HDR_SUPER.match(ln).group(1)
            elif ln.startswith('.source '):
                self.source = HDR_SOURCE.match(ln).group(1)
            elif ln.startswith('.implements '):
                self.ifaces.append(IMPL_RE.match(ln).group(1))
            elif ln.startswith('.field '):
                m = FIELD_RE.match(ln)
                if m:
                    self.fields.append((m.group(1).strip(), m.group(2), m.group(3), m.group(4)))
            elif ln.startswith('.method '):
                self.parse_method()
                continue
            self.i += 1
        return self

    def parse_method(self):
        ln = self.lines[self.i]
        m = METHOD_RE.match(ln)
        name_sig, mname, params_raw, ret = m.group(1).strip(), m.group(2), m.group(3), m.group(4)
        params = [p for p in params_raw.split() if p] if params_raw.strip() else []
        m = {
            'access': name_sig, 'name': mname, 'params': params, 'ret': ret,
            'registers': 0, 'insns': [], 'annotations': [], 'raw_params': params_raw,
            'catches': [],
        }
        self.i += 1
        # header directives
        while self.i < len(self.lines):
            s = self.lines[self.i].strip()
            if s.startswith('.registers'):
                m['registers'] = int(s.split()[-1])
            elif s.startswith('.annotation'):
                # capture one-line annotation blocks
                blk = [s]
                self.i += 1
                while self.i < len(self.lines) and not self.lines[self.i].strip().startswith('.end annotation'):
                    blk.append(self.lines[self.i].strip())
                    self.i += 1
                m['annotations'].append(blk)
            elif s == '' or s.startswith('.prologue') or s.startswith('.line'):
                pass
            elif s.startswith('.locals'):
                pass
            else:
                break
            self.i += 1
        # body
        while self.i < len(self.lines):
            s = self.lines[self.i]
            st = s.strip()
            if st.startswith('.end method'):
                break
            if st.startswith('#'):
                self.i += 1
                continue
            lm = LABEL_RE.match(s)
            if lm:
                m['insns'].append(('label', lm.group(1), None))
                self.i += 1
                continue
            if st.startswith('.'):
                # array-data / packed-switch / etc. directives
                if st.startswith('.array-data') or st.startswith('.packed-switch') or st.startswith('.sparse-switch'):
                    payload = [st]
                    self.i += 1
                    while self.i < len(self.lines) and not self.lines[self.i].strip().startswith('.end'):
                        payload.append(self.lines[self.i].strip())
                        self.i += 1
                    m['insns'].append(('data', None, payload))
                elif st.startswith('.catch'):
                    m['catches'].append(st)
                self.i += 1
                continue
            im = INSN_RE.match(s)
            if im:
                op = im.group(1).replace(' ', '')
                args = im.group(2).strip()
                m['insns'].append(('insn', op, args))
            self.i += 1
        self.methods.append(m)

# ------------------------------------------------------------------ register helpers
def parse_regs(argstr: str):
    """Extract {v0, v1} / {p0} / {v0 .. v3} register tokens from args."""
    regs = []
    # ranges: {v0 .. v3}
    for m in re.finditer(r'\{([vp]\d+)\s*\.\.\s*([vp]\d+)\}', argstr):
        a, b = m.group(1), m.group(2)
        pfx = a[0]
        i0, i1 = int(a[1:]), int(b[1:])
        for k in range(i0, i1 + 1):
            regs.append(f'{pfx}{k}')
    for m in re.finditer(r'\{([vp]\d+)\}', argstr):
        regs.append(m.group(1))
    return regs

def strip_braces(s: str) -> str:
    return s.replace('{', '').replace('}', '').strip()

# ------------------------------------------------------------------ decompiler
class Decompiler:
    def __init__(self, smali: Smali):
        self.s = smali
        self.out = []

    # ---- type rendering
    def t(self, d):
        if d is None:
            return 'void'
        return java_type(d)

    def own(self, d):
        """True if the type descriptor belongs to this module's package."""
        return d.startswith('Ldev/local/newsoul/')

    def mname(self, sig_desc, sig_name):
        """Return 'Owner.method' with our package shortened."""
        cls = short_cls(sig_desc)
        return f'{cls}.{sig_name}'

    def build_args(self, argstr, insn_op):
        """Split invoke args into receiver + arg list."""
        regs = parse_regs(argstr)
        # For range invokes the first reg is receiver, rest are args
        return regs

    def expr_for(self, reg, ctx):
        return ctx.get(reg, reg)

    def decompile_method(self, m, is_static, is_ctor):
        self._in_ctor = is_ctor
        self._chain_emitted = False
        self._pending = {}
        ctx = {}            # register -> expression string
        regtypes = {}
        params = m['params']
        if is_static:
            preg = [f'p{i}' for i in range(len(params))]
        else:
            preg = ['p0'] + [f'p{i}' for i in range(1, len(params) + 1)]
        for r, p in zip(preg, params):
            ctx[r] = r            # bare register name as the expression

        # PHASE 1: linear pass.
        #
        # Register tracking is a best-effort aid for reading the original logic.
        # Strategy: keep every expression we have seen for a register, but at a
        # label, drop the ones that were assigned *between* the preceding
        # conditional branch and this label (those are path-dependent). Values
        # assigned before the last branch survive, because both the branch
        # source and any other predecessor see the same value there.
        annotated = []
        pending_data = []
        # reg -> expression at the last conditional branch
        branch_state = {}
        pending_dirty = {}      # assigned after the last branch -> dirty
        for kind, op, args in m['insns']:
            if kind == 'label':
                annotated.append(f'      {op}:')
                ctx.clear()
                ctx.update(branch_state)
                for r in preg:
                    ctx.setdefault(r, r)
                pending_dirty = {}
                continue
            if kind == 'data':
                pending_data = args
                continue
            txt = self.translate(op, args, ctx, regtypes, is_static, m)
            if txt:
                annotated.append('      ' + txt)
            dst = self.write_target(op, args)
            if dst:
                ctx[dst] = ctx.get(dst, dst)
                pending_dirty[dst] = ctx[dst]
            if op.startswith('if-') or op.startswith('goto'):
                branch_state = dict(ctx)
                pending_dirty = {}
        if pending_data:
            annotated.append('')
            for p in pending_data:
                annotated.append(f'      // {p}')
        return annotated

    def write_target(self, op, args):
        """Return the destination register an instruction writes, if any."""
        mm = re.match(r'^([vp]\d+)', args.strip())
        if not mm:
            return None
        r = mm.group(1)
        if op.startswith('move') or op.startswith('const') or op.startswith('sget') \
           or op.startswith('iget') or op.startswith('aget') or op.startswith('check-cast') \
           or op.startswith('instance-of') or op.startswith('array-length') \
           or op.startswith('new-') or op.startswith('add-') or op.startswith('sub-') \
           or op.startswith('mul-') or op.startswith('div-') or op.startswith('rem-') \
           or op.startswith('and-') or op.startswith('or-') or op.startswith('xor-') \
           or op.startswith('shl-') or op.startswith('shr-') or op.startswith('ushr-') \
           or op.startswith('neg-') or op.startswith('not-') or op.startswith('int-to-') \
           or op.startswith('long-to-') or op.startswith('float-to-') or op.startswith('double-to-') \
           or op.startswith('cmp') or op.startswith('rsub'):
            return r
        return None

    def pname(self, reg, ptype):
        return f'{self.t(ptype)} {reg}'

    # ---- instruction translation
    def translate(self, op, args, ctx, regtypes, is_static, m):
        # normalise "invoke-virtual {a}, ..." forms
        parts = args.split(',', 1)
        regs = parse_regs(args)
        tail = args

        def S(x):
            return x

        # ---------- returns
        if op == 'return-void':
            return 'return;'
        if op == 'return':
            return f'return {ctx.get("v0", "v0")};'
        if op == 'return-object':
            return f'return {ctx.get("v0", "v0")};'
        if op == 'return-wide':
            return f'return {ctx.get("v0", "v0")};'

        # ---------- consts
        if op == 'const-string' or op == 'const-string/jumbo':
            mm = re.match(r'^([vp]\d+),\s*"(.*)"$', args.strip())
            if mm:
                ctx[mm.group(1)] = esc(_unesc(mm.group(2)))
                return None
        if op in ('const', 'const/4', 'const/16', 'const/high16', 'const-wide',
                  'const-wide/16', 'const-wide/32', 'const-wide/high16'):
            mm = re.match(r'^([vp]\d+),\s*(.+)$', args)
            if mm:
                ctx[mm.group(1)] = mm.group(2).strip()
                return None
        if op == 'const-class':
            mm = re.match(r'^([vp]\d+),\s*(\S+);?$', args)
            if mm:
                ctx[mm.group(1)] = f'{self.t(mm.group(2))}.class'
                return None
        if op == 'const-precision':
            return None

        # ---------- moves
        mm = re.match(r'^([vp]\d+),\s*([vp]\d+)$', args)
        if mm and op.startswith('move'):
            a, b = mm.group(1), mm.group(2)
            ctx[a] = ctx.get(b, b)
            if b in regtypes:
                regtypes[a] = regtypes[b]
            return None
        if op in ('move-result', 'move-result-object', 'move-result-wide'):
            # register operand comes from args, e.g. "move-result v0"
            mm = re.match(r'^([vp]\d+)$', args.strip())
            if mm:
                ctx[mm.group(1)] = ctx.get('__result__', '__result__')
            return None

        # ---------- field access
        # smali form: "v0, v1, Lcom/foo/Bar;->baz:Ljava/lang/String;"
        #             or     "v0, Lcom/foo/Bar;->baz:Ljava/lang/String;"
        # The field name is the segment between '->' and ':'.
        def field_name(fref):
            return fref.split(';->')[-1]

        if op in ('iget', 'iget-wide', 'iget-object', 'iget-boolean',
                  'iget-byte', 'iget-char', 'iget-short'):
            mm = re.match(r'^([vp]\d+),\s*([vp]\d+),\s*(.+?):(\S+)$', args)
            if mm:
                dst, obj, fref, ftype = mm.group(1), mm.group(2), mm.group(3), mm.group(4)
                ctx[dst] = f'{ctx.get(obj, obj)}.{field_name(fref)}'
                return None
        def recv_expr(r):
            if self._in_ctor and r == 'p0':
                return 'this'
            return ctx.get(r, r)

        if op in ('iput', 'iput-wide', 'iput-object', 'iput-boolean',
                  'iput-byte', 'iput-char', 'iput-short'):
            mm = re.match(r'^([vp]\d+),\s*([vp]\d+),\s*(.+?):(\S+)$', args)
            if mm:
                val, obj, fref, ftype = mm.group(1), mm.group(2), mm.group(3), mm.group(4)
                return f'{recv_expr(obj)}.{field_name(fref)} = {ctx.get(val, val)};'
        if op in ('sget', 'sget-wide', 'sget-object', 'sget-boolean',
                  'sget-byte', 'sget-char', 'sget-short'):
            mm = re.match(r'^([vp]\d+),\s*(.+?):(\S+)$', args)
            if mm:
                dst, fref, ftype = mm.group(1), mm.group(2), mm.group(3)
                owner = short_cls(fref.split(';->')[0] + ';')
                ctx[dst] = f'{owner}.{field_name(fref)}'
                return None
        if op in ('sput', 'sput-wide', 'sput-object', 'sput-boolean',
                  'sput-byte', 'sput-char', 'sput-short'):
            mm = re.match(r'^([vp]\d+),\s*(.+?):(\S+)$', args)
            if mm:
                val, fref, ftype = mm.group(1), mm.group(2), mm.group(3)
                owner = short_cls(fref.split(';->')[0] + ';')
                return f'{owner}.{field_name(fref)} = {ctx.get(val, val)};'

        # ---------- invoke
        if op.startswith('invoke-'):
            # args = "{vA, vB, vC}, Lowner;->name(params)ret"
            brace = re.search(r'\{([^}]*)\}', args)
            rest = args[brace.end():] if brace else args
            mm = re.match(r'^\s*,\s*(\S+);->(\S+)\((.*?)\)(\S+)$', rest)
            if mm and brace:
                cls, meth, _params, ret = mm.group(1), mm.group(2), mm.group(3), mm.group(4)
                reglist = brace.group(1)
                regs = [r.strip() for r in reglist.split(',') if r.strip()]
                # range form: {v0 .. v3}
                rng = re.match(r'^([vp]\d+)\s*\.\.\s*([vp]\d+)$', reglist)
                if rng:
                    pfx = rng.group(1)[0]
                    i0, i1 = int(rng.group(1)[1:]), int(rng.group(2)[1:])
                    regs = [f'{pfx}{k}' for k in range(i0, i1 + 1)]
                if not regs:
                    return None
                # static: all regs are args; else first is receiver
                if op == 'invoke-static':
                    recv = None
                    argr = regs
                else:
                    recv = regs[0]
                    argr = regs[1:]
                argexpr = ', '.join(ctx.get(r, r) for r in argr)
                owner = short_cls(cls)
                if op == 'invoke-static':
                    call = f'{owner}.{meth}({argexpr})'
                elif op == 'invoke-direct':
                    if meth == '<init>':
                        call = f'new {owner}({argexpr})'
                        if recv:
                            ctx[recv] = call
                        if self._in_ctor:
                            if not self._chain_emitted:
                                self._chain_emitted = True
                                if recv:
                                    ctx[recv] = call
                                return None
                            return call + ';'
                        return call + ';'
                    else:
                        call = f'{ctx.get(recv, recv)}.{meth}({argexpr})'
                elif op == 'invoke-super':
                    call = f'super.{meth}({argexpr})'
                else:
                    call = f'{ctx.get(recv, recv)}.{meth}({argexpr})'
                if ret == 'V':
                    return call + ';'
                ctx['__result__'] = call
                return None

        # ---------- new-instance
        if op == 'new-instance':
            mm = re.match(r'^([vp]\d+),\s*(\S+)$', args.strip())
            if mm:
                # record the pending allocation; the paired invoke-direct <init>
                # rebinds the register to `new T(args)`.
                self._pending[mm.group(1)] = short_cls(mm.group(2))
                return None

        # ---------- casts / type checks
        if op == 'check-cast':
            mm = re.match(r'^([vp]\d+),\s*(\S+)$', args.strip())
            if mm:
                r = mm.group(1)
                typ = mm.group(2)
                if typ.endswith(';') and typ.startswith('L'):
                    ctx[r] = f'({self.t(typ)}) {ctx.get(r, r)}'
                else:
                    ctx[r] = f'({self.t(typ)}) {ctx.get(r, r)}'
                return None
        if op == 'instance-of':
            mm = re.match(r'^([vp]\d+),\s*([vp]\d+),\s*(\S+);?$', args)
            if mm:
                ctx[mm.group(1)] = f'({ctx.get(mm.group(2), mm.group(2))} instanceof {self.t(mm.group(3))})'
                return None
        if op == 'array-length':
            mm = re.match(r'^([vp]\d+),\s*([vp]\d+)$', args)
            if mm:
                ctx[mm.group(1)] = f'{ctx.get(mm.group(2), mm.group(2))}.length'
                return None

        # ---------- in-place arithmetic (2addr: dst == src1)
        _sym = {'add': '+', 'sub': '-', 'mul': '*', 'div': '/', 'rem': '%',
                'and': '&', 'or': '|', 'xor': '^', 'shl': '<<', 'shr': '>>',
                'ushr': '>>>'}
        m2 = re.match(r'^([vp]\d+),\s*([vp]\d+)$', args.strip())
        m3 = re.match(r'^([vp]\d+),\s*([vp]\d+),\s*([vp]\d+)$', args.strip())
        for _t in ('int', 'long', 'float', 'double'):
            for _o in ('add', 'sub', 'mul', 'div', 'rem', 'and', 'or', 'xor', 'shl', 'shr', 'ushr'):
                if op == f'{_o}-{_t}/2addr':
                    if m2:
                        a, b = m2.group(1), m2.group(2)
                        ctx[a] = f'({ctx.get(a, a)} {_sym[_o]} {ctx.get(b, b)})'
                        return None
                if op == f'{_o}-{_t}/lit16' or op == f'{_o}-{_t}/lit8':
                    if m3:
                        a, b, lit = m3.group(1), m3.group(2), m3.group(3)
                        ctx[a] = f'({ctx.get(b, b)} {_sym[_o]} {lit})'
                        return None
                if op == f'{_o}-{_t}':
                    if m3:
                        a, b, c = m3.group(1), m3.group(2), m3.group(3)
                        ctx[a] = f'({ctx.get(b, b)} {_sym[_o]} {ctx.get(c, c)})'
                        return None
        # neg variants
        for _t in ('int', 'long', 'float', 'double'):
            if op == f'neg-{_t}':
                if m2:
                    a, b = m2.group(1), m2.group(2)
                    ctx[a] = f'-{ctx.get(b, b)}'
                    return None
            if op == f'not-{_t}':
                if m2:
                    a, b = m2.group(1), m2.group(2)
                    ctx[a] = f'~{ctx.get(b, b)}'
                    return None

        # ---------- move-exception (catch handler entry)
        if op == 'move-exception':
            mm = re.match(r'^([vp]\d+)$', args.strip())
            if mm:
                ctx[mm.group(1)] = 'e'      # exception variable
                return None

        # ---------- filled-new-array/range
        if op.startswith('filled-new-array'):
            brace = re.search(r'\{([^}]*)\}', args)
            typ = None
            mt = re.search(r'(\[*(?:L[\w/$]+;|[VZBSIJCF]))\s*$', args.strip())
            if mt:
                typ = mt.group(1)
            regs = []
            if brace:
                rl = brace.group(1)
                rng = re.match(r'^([vp]\d+)\s*\.\.\s*([vp]\d+)$', rl)
                if rng:
                    pfx = rng.group(1)[0]
                    i0, i1 = int(rng.group(1)[1:]), int(rng.group(2)[1:])
                    regs = [f'{pfx}{k}' for k in range(i0, i1 + 1)]
                else:
                    regs = [r.strip() for r in rl.split(',') if r.strip()]
            inner = ', '.join(ctx.get(r, r) for r in regs)
            if typ:
                ctx['__result__'] = f'new {self.t(typ)}[] {{{inner}}}'
            else:
                ctx['__result__'] = f'new Object[] {{{inner}}}'
            return None
        if op in ('add-int', 'sub-int', 'mul-int', 'div-int', 'rem-int',
                  'and-int', 'or-int', 'xor-int', 'shl-int', 'shr-int', 'ushr-int'):
            mm = re.match(r'^([vp]\d+),\s*([vp]\d+),\s*([vp]\d+)$', args)
            sym = {'add-int': '+', 'sub-int': '-', 'mul-int': '*', 'div-int': '/',
                   'rem-int': '%', 'and-int': '&', 'or-int': '|', 'xor-int': '^',
                   'shl-int': '<<', 'shr-int': '>>', 'ushr-int': '>>>'}[op]
            if mm:
                a, b, c = mm.group(1), mm.group(2), mm.group(3)
                ctx[a] = f'({ctx.get(b, b)} {sym} {ctx.get(c, c)})'
                return None
        if op in ('add-int/lit8', 'add-int/lit16', 'sub-int/lit8', 'rsub-int',
                  'mul-int/lit8', 'div-int/lit8', 'rem-int/lit8', 'and-int/lit8',
                  'or-int/lit8', 'xor-int/lit8', 'shl-int/lit8', 'shr-int/lit8', 'ushr-int/lit8'):
            mm = re.match(r'^([vp]\d+),\s*([vp]\d+),\s*(.+)$', args)
            base = op.split('/')[0].replace('rsub-int', 'sub-int')
            sym = {'add-int': '+', 'sub-int': '-', 'mul-int': '*', 'div-int': '/',
                   'rem-int': '%', 'and-int': '&', 'or-int': '|', 'xor-int': '^',
                   'shl-int': '<<', 'shr-int': '>>', 'ushr-int': '>>>'}.get(base, '+')
            if mm:
                a, b, lit = mm.group(1), mm.group(2), mm.group(3).strip()
                ctx[a] = f'({ctx.get(b, b)} {sym} {lit})'
                return None
        if op in ('neg-int', 'not-int'):
            mm = re.match(r'^([vp]\d+),\s*([vp]\d+)$', args)
            if mm:
                ctx[mm.group(1)] = f'{"-" if op == "neg-int" else "~"}{ctx.get(mm.group(2), mm.group(2))}'
                return None

        # ---------- comparisons
        if op.startswith('cmp'):
            mm = re.match(r'^([vp]\d+),\s*([vp]\d+),\s*([vp]\d+)$', args)
            if mm:
                a, b, c = mm.group(1), mm.group(2), mm.group(3)
                ctx[a] = f'compare({ctx.get(b, b)}, {ctx.get(c, c)})'
                return None

        # ---------- arrays
        if op in ('aget', 'aget-wide', 'aget-object', 'aget-boolean',
                  'aget-byte', 'aget-char', 'aget-short'):
            mm = re.match(r'^([vp]\d+),\s*([vp]\d+),\s*([vp]\d+)$', args)
            if mm:
                a, arr, idx = mm.group(1), mm.group(2), mm.group(3)
                ctx[a] = f'{ctx.get(arr, arr)}[{ctx.get(idx, idx)}]'
                return None
        if op in ('aput', 'aput-wide', 'aput-object', 'aput-boolean',
                  'aput-byte', 'aput-char', 'aput-short'):
            mm = re.match(r'^([vp]\d+),\s*([vp]\d+),\s*([vp]\d+)$', args)
            if mm:
                val, arr, idx = mm.group(1), mm.group(2), mm.group(3)
                return f'{ctx.get(arr, arr)}[{ctx.get(idx, idx)}] = {ctx.get(val, val)};'
        if op in ('new-array',):
            mm = re.match(r'^([vp]\d+),\s*([vp]\d+),\s*(\S+);?$', args)
            if mm:
                a, sz, t = mm.group(1), mm.group(2), mm.group(3)
                ctx[a] = f'new {self.t(t)}[{ctx.get(sz, sz)}]'
                return None
        if op == 'filled-new-array':
            regs = parse_regs(args)
            mm = re.search(r'(\S+);$', args)
            t = mm.group(1) if mm else 'Ljava/lang/Object;'
            ctx['__result__'] = f'new {self.t(t)}[] {{{", ".join(ctx.get(r, r) for r in regs[1:])}}}'
            return None

        # ---------- conversions
        if op.startswith('int-to-') or op.startswith('long-to-') or \
           op.startswith('float-to-') or op.startswith('double-to-') or \
           op in ('neg-float', 'neg-double', 'neg-long'):
            mm = re.match(r'^([vp]\d+),\s*([vp]\d+)$', args)
            if mm:
                a, b = mm.group(1), mm.group(2)
                ctx[a] = f'(({op.split("-")[-1].split("/")[0]}) {ctx.get(b, b)})'
                return None

        # ---------- control flow
        if op in ('if-eq', 'if-ne', 'if-lt', 'if-ge', 'if-gt', 'if-le',
                  'if-eqz', 'if-nez', 'if-ltz', 'if-gez', 'if-gtz', 'if-lez'):
            mm = re.match(r'^([vp]\d+),\s*([vp]\d+),\s*(:\S+)$', args)
            mz = re.match(r'^([vp]\d+),\s*(:\S+)$', args)
            cmpop = {'if-eq': '==', 'if-ne': '!=', 'if-lt': '<', 'if-ge': '>=',
                     'if-gt': '>', 'if-le': '<='}
            zop = {'if-eqz': '== 0', 'if-nez': '!= 0', 'if-ltz': '< 0',
                   'if-gez': '>= 0', 'if-gtz': '> 0', 'if-lez': '<= 0'}
            if mm:
                a, b, lbl = mm.group(1), mm.group(2), mm.group(3)
                return f'if ({ctx.get(a, a)} {cmpop[op]} {ctx.get(b, b)}) goto {lbl};'
            if mz:
                a, lbl = mz.group(1), mz.group(2)
                return f'if ({ctx.get(a, a)} {zop[op]}) goto {lbl};'
        if op == 'goto':
            return f'goto {args.strip()};'
        if op == 'goto/16':
            return f'goto {args.strip()};'
        if op == 'goto/32':
            return f'goto {args.strip()};'
        # ---------- switch / array payloads
        if op == 'packed-switch':
            mm = re.match(r'^([vp]\d+),\s*(\S+)$', args.strip())
            if mm:
                return f'switch ({ctx.get(mm.group(1), mm.group(1))}) {{ /* {mm.group(2)} */ }}'
            return f'// packed-switch {args}'
        if op == 'sparse-switch':
            mm = re.match(r'^([vp]\d+),\s*(\S+)$', args.strip())
            if mm:
                return f'switch ({ctx.get(mm.group(1), mm.group(1))}) {{ /* {mm.group(2)} */ }}'
            return f'// sparse-switch {args}'
        if op == 'fill-array-data':
            mm = re.match(r'^([vp]\d+),\s*(\S+)$', args.strip())
            if mm:
                return f'{ctx.get(mm.group(1), mm.group(1))} = /* array-data {mm.group(2)} */;'

        # ---------- lit16 arithmetic not covered above (and/or/xor/shl/shr/ushr)
        if op in ('and-int/lit16', 'or-int/lit16', 'xor-int/lit16',
                  'shl-int/lit16', 'shr-int/lit16', 'ushr-int/lit16'):
            mm = re.match(r'^([vp]\d+),\s*([vp]\d+),\s*(.+)$', args)
            if mm:
                a, b, lit = mm.group(1), mm.group(2), mm.group(3).strip()
                sym = {'and': '&', 'or': '|', 'xor': '^', 'shl': '<<',
                       'shr': '>>', 'ushr': '>>>'}[op.split('-')[0]]
                ctx[a] = f'({ctx.get(b, b)} {sym} {lit})'
                return None

        # ---------- move-object / move-wide fallback (non-comma form)
        mm = re.match(r'^([vp]\d+),\s*([vp]\d+)$', args.strip())
        if mm and op.startswith('move'):
            a, b = mm.group(1), mm.group(2)
            ctx[a] = ctx.get(b, b)
            return None
        if op == 'throw':
            return f'throw {ctx.get(strip_braces(args), strip_braces(args))};'
        if op == 'monitor-enter':
            return f'synchronized ({ctx.get(strip_braces(args), strip_braces(args))}) {{'
        if op == 'monitor-exit':
            return '}'

        # ---------- nop / unknown -> keep as comment
        if op == 'nop':
            return None
        return f'// {op} {args}'

    # ---- emit
    def emit(self):
        s = self.s
        L = []
        pkg = s.cls[1:].rsplit('/', 1)[0] if '/' in s.cls[1:] else ''
        simple = s.cls.split('/')[-1]
        L.append(f'package {pkg};')
        L.append('')
        L.append(f'// ==== reconstructed from {s.cls} ====')
        L.append(f'// super: {s.super}')
        if s.ifaces:
            L.append('// implements: ' + ', '.join(i[1:-1].split('/')[-1] for i in s.ifaces))
        if s.source:
            L.append(f'// source: "{s.source}"')
        L.append('')
        vis = 'public' if 'public' in s.access else ''
        fin = 'final' if 'final' in s.access else ''
        ab = 'abstract' if 'abstract' in s.access else ''
        if 'interface' in s.access:
            L.append(f'{vis} interface {simple} {{')
        else:
            L.append(f'{vis} {fin} {ab} class {simple} extends {short_cls(s.super)}' +
                     (' implements ' + ', '.join(short_cls(i) for i in s.ifaces) if s.ifaces else '') + ' {')
        L.append('')
        # fields
        for acc, name, typ, init in s.fields:
            mods = []
            for k in ('public', 'private', 'protected', 'static', 'final'):
                if k in acc:
                    mods.append(k)
            init_s = f' = {self.render_init(typ, init)}' if init else ''
            L.append(f'    {" ".join(mods)} {self.t(typ)} {name}{init_s};')
        if s.fields:
            L.append('')
        # methods
        for m in s.methods:
            L.append(self.emit_method(m))
            L.append('')
        L.append('}')
        return '\n'.join(L)

    def render_init(self, typ, init):
        if init is None:
            return 'null'
        init = init.strip()
        return init

    def emit_method(self, m):
        acc = m['access']
        mods = []
        for k in ('public', 'private', 'protected', 'static', 'final', 'abstract', 'synchronized'):
            if k in acc:
                mods.append(k)
        if '<init>' in m['name']:
            pass
        params = ', '.join(f'{self.t(p)} p{i}' for i, p in enumerate(m['params']))
        rett = self.t(m['ret'])
        name = m['name']
        if name == '<init>':
            # super( or this( depending on the constructor owner
            first_init = None
            for kind, op, args2 in m['insns']:
                if kind == 'insn' and op == 'invoke-direct':
                    first_init = args2
                    break
            if first_init and re.search(r';\-><init>\(', first_init):
                owner = re.search(r'\},\s*(\S+);-><init>', first_init).group(1)
                name = 'this' if owner == self.s.cls else 'super'
            else:
                name = short_cls(self.s.cls)
        elif name == '<clinit>':
            name = 'static {}'
        head = f'    {" ".join(mods)} {rett} {name}({params})'
        for ann in m.get('annotations', []):
            pass
        if not m['insns']:
            return head + ';'
        body = self.decompile_method(m, 'static' in acc, name in ('this', 'super'))
        if m.get('catches'):
            body.append('')
            for c in m['catches']:
                # .catch Lfoo/Bar; { :s .. :e } :h
                mm3 = re.match(r'^\.catch\s+(\S+);\s*\{\s*(:\S+)\s*\.\.\s*(:\S+)\s*\}\s*(:\S+)$', c)
                if mm3:
                    body.append(f'      // try {mm3.group(2)}..{mm3.group(3)} catch ({self.t(mm3.group(1))}) -> {mm3.group(4)}')
                else:
                    body.append(f'      // {c}')
        if name in ('this', 'super'):
            chain = self.ctor_chain(m)
            if chain:
                body = ['      ' + chain] + list(body)
        inner = '\n'.join(body) if body else '        // (empty body)'
        return head + ' {\n' + inner + '\n    }'

    def ctor_chain(self, m):
        """Render the this(...)/super(...) chaining call."""
        for kind, op, a in m['insns']:
            if kind == 'insn' and op == 'invoke-direct' and re.search(r';\-><init>\(', a):
                mm2 = re.match(r'^\{([^}]*)\},\s*(\S+);-><init>\((.*?)\)V$', a)
                if not mm2:
                    return None
                owner = short_cls(mm2.group(2))
                regs = [r.strip() for r in mm2.group(1).split(',') if r.strip()]
                args = ', '.join(regs[1:])      # drop p0
                nm = 'this' if owner == short_cls(self.s.cls) else 'super'
                return f'{nm}({args});'
        return None


def _unesc(s: str) -> str:
    return s.encode('utf-8').decode('unicode_escape', errors='replace') if '\\u' in s else s


# ------------------------------------------------------------------ driver
def _case_safe(name: str) -> str:
    """Encode a class name so that 'q0' and 'Q0' map to distinct paths on a
    case-insensitive filesystem. Lowercase letters become '_L', uppercase '_U'.
    """
    out = []
    for ch in name:
        if ch.islower():
            out.append(ch)
        elif ch.isupper():
            out.append('_U' + ch)
        elif ch.isdigit():
            out.append(ch)
        else:
            out.append('_' + str(ord(ch)))
    return ''.join(out)


def main(indir, outdir):
    files = []
    for root, dirs, fnames in os.walk(indir):
        for fn in fnames:
            if fn.endswith('.smali'):
                files.append(os.path.join(root, fn))
    files.sort()
    n = 0
    os.makedirs(outdir, exist_ok=True)
    for path in files:
        text = open(path, encoding='utf-8', errors='replace').read()
        try:
            sm = Smali(text).parse()
            dec = Decompiler(sm)
            src = dec.emit()
        except Exception as e:
            src = f'// DECOMPILE FAILED for {fn}: {e}\n'
        # output path mirrors package
        cls = sm.cls or f'L{os.path.basename(path)};'
        cls = cls.rstrip(';')
        pkg = cls[1:].rsplit('/', 1)[0] if '/' in cls[1:] else ''
        simple = cls.split('/')[-1]
        out = os.path.join(outdir, pkg.replace('/', os.sep))
        os.makedirs(out, exist_ok=True)
        # Windows/macOS filesystems are case-insensitive, and this module has
        # classes named both 'q0' and 'Q0' (and 'g'/'G', 'i'/'I', ...). Encode
        # the class name into an ASCII-safe path so no write can overwrite a
        # differently-cased sibling. Each directory holds exactly one class.
        safe = _case_safe(simple)
        fpath = os.path.join(out, safe, simple + '.java')
        os.makedirs(os.path.dirname(fpath), exist_ok=True)
        with open(fpath, 'w', encoding='utf-8') as f:
            f.write(src)
        n += 1
    print(f'decompiled {n} classes -> {outdir}')


if __name__ == '__main__':
    main(sys.argv[1], sys.argv[2])
