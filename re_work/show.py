"""Print full method detail (strings/types/callees) for selected methods."""
import json, sys, re

m = json.load(open('featuremap.json', encoding='utf-8'))
md = m['method_data']

pats = sys.argv[1:] or [r'SoulHook;->']
for pat in pats:
    rx = re.compile(pat)
    for key in sorted(md):
        if not rx.search(key):
            continue
        i = md[key]
        print('=' * 100)
        print(key)
        print('-' * 100)
        if i['strings']:
            print('STRINGS:')
            for s in i['strings']:
                print('    ', repr(s)[:200])
        if i['types']:
            ts = [t for t in i['types'] if t]
            if ts:
                print('TYPES:', ts)
        print('CALLEES:')
        for c in i['callees']:
            print('    ', c)
        print()
