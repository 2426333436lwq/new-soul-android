import json, sys
m = json.load(open(r'E:\student\NX\new-soul-1.34.0\re_work\featuremap.json', encoding='utf-8'))
md = m['method_data']; st = m['string_to_methods']
pats = sys.argv[1:]
for k in sorted(md):
    if not any(p in k for p in pats):
        continue
    i = md[k]
    print('=' * 100); print(k)
    print('  STRINGS:')
    for s in i['strings']: print('     ', repr(s))
    print('  CALLEES:')
    for c in i['callees']: print('     ', c)
print()
print('### STRING -> METHODS')
for s in ('MainScheduler', 'MainActivity'):
    for sig in st.get(s, []): print('  ', s, '->', sig)
