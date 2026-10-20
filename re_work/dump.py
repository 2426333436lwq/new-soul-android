"""Dump full disassembly of a method by regex on its signature."""
import sys, re
from dexparse import DEX
from disasm import disasm

d = DEX(open('classes.dex', 'rb').read())
pats = [re.compile(p) for p in sys.argv[1:]]
if not pats:
    pats = [re.compile(r'SoulHook;->onPackageReady')]
for c in d.classes:
    cd = d.parse_class_data(c['class_data_off'])
    for m in cd['direct_methods'] + cd['virtual_methods']:
        if not m['code_off']:
            continue
        if not any(p.search(m['sig']) for p in pats):
            continue
        code = d.parse_code_item(m['code_off'])
        print('=' * 100)
        print(m['sig'])
        print('=' * 100)
        print(disasm(d, code))
        print()
