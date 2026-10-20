"""Scratch dumper for report_ui.md evidence: string->methods mapping + method details."""
import json, sys, re, os

HERE = os.path.dirname(os.path.abspath(__file__))
M = json.load(open(os.path.join(HERE, 'featuremap.json'), encoding='utf-8'))
MD = M['method_data']
ST = M['string_to_methods']
if 'type_to_methods' in M:
    TT = M['type_to_methods']
else:
    TT = {}

mode = sys.argv[1]
args = sys.argv[2:]

if mode == 'str':
    for t in args:
        print('====', t)
        for k in sorted(ST.get(t, [])):
            print('    ', k)
        for k in sorted(TT.get(t, [])):
            print('   T', k)
elif mode == 're':
    for pat in args:
        rx = re.compile(pat)
        for key in sorted(MD):
            if not rx.search(key):
                continue
            i = MD[key]
            print('=' * 100)
            print(key)
            print('-' * 100)
            if i['strings']:
                print('STRINGS:')
                for s in i['strings']:
                    print('    ', repr(s)[:300])
            ts = [t for t in i.get('types', []) if t]
            if ts:
                print('TYPES:', ts)
            print('CALLEES:')
            for c in i['callees']:
                print('    ', c)
            print()
elif mode == 'classes':
    import collections
    byc = collections.defaultdict(list)
    for k in MD:
        c = k.split('->')[0].split('/')[-1].rstrip(';')
        byc[c].append(k)
    for c in args:
        print('==', c, len(byc.get(c, [])))
        for k in sorted(byc.get(c, [])):
            print('   ', k.split('->')[1][:200])
