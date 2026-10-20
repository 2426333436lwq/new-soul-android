import sys
from dexparse import DEX
import dutil

d = DEX(open('classes.dex', 'rb').read())

def find(target):
    for c in d.classes:
        cd = d.parse_class_data(c['class_data_off'])
        for m in cd['direct_methods'] + cd['virtual_methods']:
            if m['sig'] == target:
                return m
    return None

def dump(target, show_types=True, show_fields=True):
    m = find(target)
    print('=' * 110)
    if not m:
        print('NOT FOUND:', target); return
    code = d.parse_code_item(m['code_off'])
    if not code:
        print('NO CODE:', target); return
    print(target, ' regs=%d ins=%d outs=%d units=%d' % (code['registers_size'], code['ins_size'], code['outs_size'], code['insns_size']))
    print('-' * 110)
    ev = []
    for off, s in dutil.strings_in_order(d, code):
        ev.append((off, 'STR', repr(s)))
    if show_types:
        for off, t in dutil.types_in_order(d, code):
            ev.append((off, 'TYP', t))
    if show_fields:
        for off, f in dutil.fields_in_order(d, code):
            ev.append((off, 'FLD', f))
    for off, sig, op in dutil.calls_in_order(d, code):
        ev.append((off, 'CALL', sig))
    ev.sort()
    for off, kind, v in ev:
        print('  @%04x %s %s' % (off, kind, v))
    print()

for t in sys.argv[1:]:
    dump(t)