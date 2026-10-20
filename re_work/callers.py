import sys, collections, struct
from dexparse import DEX
from dalvik import iter_code_units

d = DEX(open('classes.dex', 'rb').read())
INVOKE = set(range(0x6e, 0x73)) | set(range(0x74, 0x79))

# name -> method index
want = {}
for i in range(d.method_ids_size):
    sig = d.method_sig(i)
    if sig.startswith('Ldev/local/newsoul/'):
        want.setdefault(sig, i)

callers = collections.defaultdict(set)
for c in d.classes:
    cd = d.parse_class_data(c['class_data_off'])
    for m in cd['direct_methods'] + cd['virtual_methods']:
        if not m['code_off']:
            continue
        code = d.parse_code_item(m['code_off'])
        for off, op, size, raw in iter_code_units(code['insns']):
            if op in INVOKE and len(raw) >= 4:
                idx = struct.unpack_from('<H', raw, 2)[0]
                callers[idx].add(m['sig'])

targets = sys.argv[1:]
for t in targets:
    # match any sig containing t
    print('### targets matching', t)
    for sig, idx in sorted(want.items()):
        if t in sig:
            print('   ', sig)
            for cc in sorted(callers.get(idx, [])):
                print('        <-', cc)