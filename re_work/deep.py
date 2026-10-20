"""Extract the high-value intelligence from the new-soul LSPosed module.

Focus:
  1. All non-type string constants (intent: URLs, keys, filenames, labels)
  2. The entry class SoulHook: its methods and every string/method/field it references
  3. Every class that implements XposedInterface$Hooker (the actual hook bodies)
  4. Cross-reference of target-app (cn.soulapp) class names
"""
import sys, json, re, collections
from dexparse import DEX

DEX_PATH = sys.argv[1] if len(sys.argv) > 1 else 'classes.dex'

with open(DEX_PATH, 'rb') as f:
    d = DEX(f.read())

def is_type_descriptor(s):
    return bool(re.fullmatch(r'\[*[LVIZBSJFD].*;?', s)) and (s.endswith(';') or len(s) == 1)

def looks_like_descriptor(s):
    # Lfoo/bar/Baz;  or [Lfoo;  or primitive desc
    return bool(re.match(r'^\[*L[\w/$]+;$', s)) or s in ('V','Z','B','S','C','I','J','F','D') or s.startswith('[')

strings = d.strings
non_type = [s for s in strings if not looks_like_descriptor(s)]

print(f"=== COUNTS ===")
print(f"classes={d.class_defs_size} methods={d.method_ids_size} fields={d.field_ids_size} "
      f"types={d.type_ids_size} strings={d.string_ids_size} protos={d.proto_ids_size}")

# ---------- 1. Hooker implementors ----------
print("\n=== CLASSES IMPLEMENTING XposedInterface$Hooker ===")
hookers = []
for c in d.classes:
    cd = d.parse_class_data(c['class_data_off'])
    ifaces = c['interfaces']
    if any('Hooker' in (i or '') for i in ifaces):
        hookers.append((c, cd))
        print(f"\n{c['name']}  super={c['super']}  access=0x{c['access']:x}")
        for f in cd['instance_fields']:
            print(f"    field {f['type']} {f['name']}")
        for f in cd['static_fields']:
            print(f"    static {f['type']} {f['name']}")
        for m in cd['direct_methods'] + cd['virtual_methods']:
            print(f"    method {m['name']}({', '.join(m['params'])}) -> {m['return']}  code=0x{m['code_off']:x}")
print(f"\n[hooker count = {len(hookers)}]")

# ---------- 2. SoulHook ----------
print("\n\n=== ENTRY CLASS SoulHook ===")
for c in d.classes:
    if c['name'] == 'Ldev/local/newsoul/SoulHook;':
        cd = d.parse_class_data(c['class_data_off'])
        print(f"super={c['super']} access=0x{c['access']:x} source={c['source_file']}")
        for f in cd['static_fields'] + cd['instance_fields']:
            print(f"    field {f['type']} {f['name']}")
        for m in cd['direct_methods'] + cd['virtual_methods']:
            print(f"    method {m['name']}({', '.join(m['params'])}) -> {m['return']}  code=0x{m['code_off']:x}")

# ---------- 3. Target-app references ----------
print("\n\n=== TYPES REFERENCING cn.soulapp / soul ===")
for t in d.types:
    if 'soulapp' in t.lower() or re.search(r'/soul', t, re.I):
        print(f"    {t}")

# ---------- 4. Strings bucketed ----------
print("\n\n=== STRING CONSTANTS BY BUCKET ===")
buckets = {
    'URL / ENDPOINT': r'(https?://|//[\w.-]+/|\.com|\.cn|\.net|/api/|www\.)',
    'FILE / PATH':    r'(\.(xml|json|db|txt|log|mp3|wav|m4a|amr|aac|png|jpg|jpeg|gif|webp|so|dex|apk)$|^/|sdcard|/data/|Android/)',
    'PREF / KEY':     r'(^[a-z][a-z0-9_]{3,}$)',
    'SOUL/APP WORD':  r'(?i)(soul|星球|灵魂|匹配|瞬间|聊天|广场|关注|粉丝)',
    'XPOSED API':     r'(?i)(xposed|lsposed|hook|module|scope|intercept|chain|hooker)',
    'CRYPTO/SIGN':    r'(?i)(^|_)(key|secret|sign|token|aes|rsa|md5|sha|iv|salt|encrypt|decrypt)(_|$)',
    'UI LABEL':       r'[\u4e00-\u9fff]',
}
for label, pat in buckets.items():
    hits = sorted({s for s in non_type if re.search(pat, s)})
    print(f"\n--- {label} ({len(hits)}) ---")
    for h in hits:
        print(f"    {h!r}")

with open('soulhook_analysis.json', 'w', encoding='utf-8') as f:
    json.dump({
        'counts': {'classes': d.class_defs_size, 'methods': d.method_ids_size,
                   'fields': d.field_ids_size, 'strings': d.string_ids_size},
        'hookers': [c['name'] for c, _ in hookers],
        'soul_types': [t for t in d.types if 'soulapp' in t.lower() or re.search(r'/soul', t, re.I)],
        'non_type_strings': non_type,
    }, f, indent=2, ensure_ascii=False)
