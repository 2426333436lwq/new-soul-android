import sys, struct
sys.path.insert(0, r'E:\student\NX\new-soul-1.34.0\re_work')
from dexparse import DEX
from dalvik import iter_code_units

d = DEX(open(r'E:\student\NX\new-soul-1.34.0\re_work\classes.dex','rb').read())
want = sys.argv[1:]
for c in d.classes:
    cd = d.parse_class_data(c['class_data_off'])
    for m in cd['direct_methods'] + cd['virtual_methods']:
        key = m['class'] + '->' + m['name']
        if not any(w in key for w in want):
            continue
        if not m['code_off']:
            continue
        code = d.parse_code_item(m['code_off'])
        print('=' * 100)
        print(m['sig'], ' regs=%d ins=%d' % (code['registers_size'], code['ins_size']))
        raw0 = code['insns']
        for off, op, size, raw in iter_code_units(raw0):
            txt = ''
            try:
                if op == 0x1a and len(raw) >= 4:
                    txt = '  ; string %r' % d.strings[struct.unpack_from('<H', raw, 2)[0]]
                elif op == 0x1b and len(raw) >= 6:
                    txt = '  ; string %r' % d.strings[struct.unpack_from('<I', raw, 2)[0]]
                elif op in (0x6e,0x6f,0x70,0x71,0x72,0x74,0x75,0x76,0x77,0x78) and len(raw) >= 4:
                    idx = struct.unpack_from('<H', raw, 2)[0]
                    if idx < d.method_ids_size: txt = '  ; call %s' % d.method_sig(idx)
                elif op in (0x1c,0x1f,0x20,0x21,0x22) and len(raw) >= 4:
                    txt = '  ; type %s' % d.type_of(struct.unpack_from('<H', raw, 2)[0])
                elif op in set(range(0x52,0x6e)) and len(raw) >= 4:
                    idx = struct.unpack_from('<H', raw, 2)[0]
                    if idx < d.field_ids_size:
                        f = d.fields[idx]; txt = '  ; field %s->%s' % (f['class'], f['name'])
                elif op == 0x14 and len(raw) >= 4:
                    val = struct.unpack_from('<h', raw, 2)[0]; txt = '  ; const %d (0x%x)' % (val, val & 0xffff)
                elif op == 0x13 and len(raw) >= 4:
                    txt = '  ; const/16 %d' % struct.unpack_from('<h', raw, 2)[0]
            except Exception as e:
                txt = '  ; <%s>' % e
            print('   %04x %02x %-24s%s' % (off, op, raw.hex(), txt))
