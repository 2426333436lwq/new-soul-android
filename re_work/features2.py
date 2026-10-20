"""Feature map: attribute every Chinese label + pref key to the method using it,
and every hooked Soul class to the hook method that references it."""
import sys, json, re, collections
from dexparse import DEX
from dalvik import refs_from_code, iter_code_units

DEX_PATH = sys.argv[1] if len(sys.argv) > 1 else 'classes.dex'
with open(DEX_PATH, 'rb') as f:
    d = DEX(f.read())

string_to_methods = collections.defaultdict(set)
type_to_methods = collections.defaultdict(set)
method_data = {}

for c in d.classes:
    cd = d.parse_class_data(c['class_data_off'])
    for m in cd['direct_methods'] + cd['virtual_methods']:
        if not m['code_off']:
            continue
        code = d.parse_code_item(m['code_off'])
        if not code:
            continue
        r = refs_from_code(code['insns'])
        strs = [d.strings[i] for i in sorted(r['strings']) if i < len(d.strings)]
        types = [d.type_of(i) for i in sorted(r['types'])]
        callees = [d.method_sig(i) for i in sorted(r['methods']) if i < d.method_ids_size]
        fields = [d.fields[i] for i in sorted(r['fields']) if i < d.field_ids_size]
        key = m['sig']
        method_data[key] = {'strings': strs, 'types': types,
                            'callees': callees, 'fields': fields}
        for s in strs:
            string_to_methods[s].add(key)
        for t in types:
            if t:
                type_to_methods[t].add(key)


def short(sig):
    cls = sig.split('->')[0].split('/')[-1].rstrip(';')
    rest = sig.split('->')[1]
    return f"{cls}.{rest.split('(')[0]}"


def coord(sig):
    return ', '.join(short(u) for u in sorted(string_to_methods.get(sig, []))[:6]) or '(none)'


print("=" * 100)
print("A. CHINESE UI LABELS  (feature surface exposed to the user)")
print("=" * 100)
cn = sorted({s for s in d.strings if re.search(r'[\u4e00-\u9fff]', s)})
for s in cn:
    users = sorted(string_to_methods.get(s, []))
    loc = ', '.join(short(u) for u in users[:3]) or '-'
    print(f"  {s!r:<64} <- {loc}")

print()
print("=" * 100)
print("B. FEATURE TOGGLE KEYS (SharedPreferences)")
print("=" * 100)
PREF_RE = re.compile(r'^(ad_|chat_|feed_|square_|privacy_|profile_disguise_|post_|block_|download_|main_|menu_|game_|match_|ai_reply|newsoul_|startup_|hook_|debug_|c_ct_|c_ic_)')
prefs = sorted({s for s in d.strings if PREF_RE.match(s)})
for s in prefs:
    users = sorted(string_to_methods.get(s, []))
    loc = ', '.join(short(u) for u in users[:4]) or '-'
    print(f"  {s!r:<48} <- {loc}")

print()
print("=" * 100)
print("C. HOOKED SOUL TARGET CLASSES  ->  hook method referencing them")
print("=" * 100)
soul = {t: v for t, v in type_to_methods.items() if 'soulapp' in t.lower() or 'soul' in t.lower()}
for t in sorted(soul):
    print(f"\n  {t}")
    for m in sorted(soul[t]):
        print(f"        {short(m)}")

print()
print("=" * 100)
print("D. HOOK BODIES: every intercept() and what it references")
print("=" * 100)
for key, info in sorted(method_data.items()):
    if '.intercept' not in key and 'intercept(' not in key:
        continue
    cls = short(key)
    print(f"\n--- {cls}  [{key}]")
    print(f"    callees: {', '.join(sorted(set(info['callees']))[:14])}")
    ts = [t for t in info['types'] if t and ('soul' in t.lower() or 'soulapp' in t.lower())]
    if ts:
        print(f"    SOUL TYPES: {ts}")

with open('featuremap.json', 'w', encoding='utf-8') as f:
    json.dump({
        'chinese': cn, 'prefs': prefs,
        'soul_classes': {k: sorted(v) for k, v in soul.items()},
        'string_to_methods': {k: sorted(v) for k, v in string_to_methods.items()},
        'method_data': {k: {'strings': v['strings'], 'types': v['types'],
                            'callees': v['callees']} for k, v in method_data.items()},
    }, f, indent=2, ensure_ascii=False)
print("\n[+] wrote featuremap.json")
