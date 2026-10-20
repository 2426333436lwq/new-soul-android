"""Minimal Dalvik disassembler to resolve exact field/method/string operands
for selected methods (used as evidence for report_ui.md)."""
import sys, os, struct, re
sys.path.insert(0, os.path.dirname(os.path.abspath(__file__)))
from dexparse import DEX
from dalvik import iter_code_units, FIELD_OPS, METHOD_OPS

HERE = os.path.dirname(os.path.abspath(__file__))
with open(os.path.join(HERE, 'classes.dex'), 'rb') as f:
    D = DEX(f.read())

OPN = {
    0x1a: 'const-string', 0x1b: 'const-string/jumbo', 0x1c: 'const-class',
    0x1f: 'check-cast', 0x20: 'instance-of', 0x22: 'new-instance',
    0x6e: 'invoke-virtual', 0x6f: 'invoke-super', 0x70: 'invoke-direct',
    0x71: 'invoke-static', 0x72: 'invoke-interface',
    0x74: 'invoke-virtual/range', 0x75: 'invoke-super/range',
    0x76: 'invoke-direct/range', 0x77: 'invoke-static/range',
    0x78: 'invoke-interface/range',
}
FOPN = {
    0x52: 'iget', 0x53: 'iget-wide', 0x54: 'iget-object', 0x55: 'iget-boolean',
    0x56: 'iget-byte', 0x57: 'iget-char', 0x58: 'iget-short',
    0x59: 'iput', 0x5a: 'iput-wide', 0x5b: 'iput-object', 0x5c: 'iput-boolean',
    0x5d: 'iput-byte', 0x5e: 'iput-char', 0x5f: 'iput-short',
    0x60: 'sget', 0x61: 'sget-wide', 0x62: 'sget-object', 0x63: 'sget-boolean',
    0x64: 'sget-byte', 0x65: 'sget-char', 0x66: 'sget-short',
    0x67: 'sput', 0x68: 'sput-wide', 0x69: 'sput-object', 0x6a: 'sput-boolean',
    0x6b: 'sput-byte', 0x6c: 'sput-char', 0x6d: 'sput-short',
}

def field_sig(i):
    fl = D.fields[i]
    return "%s->%s:%s" % (fl['class'], fl['name'], fl['type'])

def disasm(insns):
    out = []
    for off, op, size, raw in iter_code_units(insns):
        if op == 0x00:
            continue
        txt = None
        if op in (0x1a,):
            v = struct.unpack_from('<H', raw, 2)[0]
            txt = 'const-string v?, %r' % D.strings[v]
        elif op == 0x1b:
            v = struct.unpack_from('<I', raw, 2)[0]
            txt = 'const-string/jumbo v?, %r' % D.strings[v]
        elif op in (0x1c, 0x1f, 0x20, 0x22):
            v = struct.unpack_from('<H', raw, 2)[0]
            txt = '%s %s' % (OPN[op], D.type_of(v))
        elif op in FIELD_OPS:
            v = struct.unpack_from('<H', raw, 2)[0]
            txt = '%s %s' % (FOPN.get(op, hex(op)), field_sig(v))
        elif op in METHOD_OPS:
            v = struct.unpack_from('<H', raw, 2)[0]
            txt = '%s %s' % (OPN[op], D.method_sig(v))
        if txt:
            out.append('  @%04x  %s' % (off, txt))
    return out

pat = sys.argv[1]
rx = re.compile(pat)
for c in D.classes:
    cd = D.parse_class_data(c['class_data_off'])
    for m in cd['direct_methods'] + cd['virtual_methods']:
        if not rx.search(m['sig']):
            continue
        if not m['code_off']:
            continue
        code = D.parse_code_item(m['code_off'])
        print('=' * 100)
        print(m['sig'])
        print('-' * 100)
        for line in disasm(code['insns']):
            print(line)
        print()
