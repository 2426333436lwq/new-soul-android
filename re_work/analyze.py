"""Structured analysis of the new-soul LSPosed module DEX.

Produces:
  - class inventory summary (counts, class list)
  - all string constants (the richest source of intent)
  - the entry class dev.local.newsoul.SoulHook fully expanded
  - all classes referencing the target app package / libxposed API
"""
import sys, json, re, collections
from dexparse import DEX

DEX_PATH = sys.argv[1] if len(sys.argv) > 1 else 'classes.dex'
OUT = sys.argv[2] if len(sys.argv) > 2 else 'analysis.json'

with open(DEX_PATH, 'rb') as f:
    d = DEX(f.read())

report = {}
report['header'] = {
    'version': d.version, 'file_size': d.file_size,
    'strings': d.string_ids_size, 'types': d.type_ids_size,
    'protos': d.proto_ids_size, 'fields': d.field_ids_size,
    'methods': d.method_ids_size, 'classes': d.class_defs_size,
}

# ---- class inventory ----
classes = []
for c in d.classes:
    cd = d.parse_class_data(c['class_data_off'])
    classes.append({
        'name': c['name'], 'super': c['super'], 'source': c['source_file'],
        'access': c['access'], 'interfaces': c['interfaces'],
        'n_static_fields': len(cd['static_fields']),
        'n_instance_fields': len(cd['instance_fields']),
        'n_direct_methods': len(cd['direct_methods']),
        'n_virtual_methods': len(cd['virtual_methods']),
        'methods': [m['sig'] for m in cd['direct_methods'] + cd['virtual_methods']],
    })
report['classes'] = classes
report['class_count'] = len(classes)

# ---- strings, grouped by interest ----
strs = d.strings
report['all_strings'] = strs

# Strings that look like identifiers/URLs/paths/prefs keys (not pure type descriptors)
def interesting(s):
    if s.startswith('L') and s.endswith(';') and '/' in s:
        return False
    return True
report['interesting_strings'] = [s for s in strs if interesting(s)]

# ---- keyword buckets ----
KW = {
    'soul_target': ['cn.soulapp', 'soulapp', 'soul'],
    'networking': ['http', 'https', 'api.', 'url', 'Url', 'URL', 'token', 'cookie'],
    'xposed': ['xposed', 'Xposed', 'lsposed', 'LSPosed', 'hook', 'Hook'],
    'storage': ['SharedPreferences', 'prefs', 'preference', '\.xml', 'files', 'cache'],
    'crypto_keys': ['key', 'Key', 'secret', 'Secret', 'aes', 'AES', 'md5', 'MD5', 'sign', 'Sign'],
    'media': ['\.mp3', '\.wav', '\.m4a', '\.amr', 'MediaPlayer', 'audio', 'record'],
    'image': ['\.png', '\.jpg', '\.jpeg', '\.gif', '\.webp', 'bitmap', 'Bitmap'],
}
buckets = {}
for label, pats in KW.items():
    hits = set()
    for s in strs:
        for p in pats:
            if re.search(p, s):
                hits.add(s); break
    buckets[label] = sorted(hits)
report['keyword_buckets'] = buckets

# ---- what classes reference the target app ----
target_refs = collections.defaultdict(list)
for i, c in enumerate(classes):
    for m in c['methods']:
        if 'soulapp' in m or 'soulapp' in c['name']:
            target_refs[c['name']].append(m)
report['target_class_refs'] = dict(target_refs)

# ---- find the entry class & all Hooker implementors ----
hookers = [c for c in classes if any('XposedInterface$Hooker' == x for x in c['interfaces'])]
report['hooker_classes'] = [{
    'name': c['name'], 'super': c['super'],
    'fields': None, 'methods': c['methods'],
} for c in hookers]

report['entry_class'] = next((c for c in classes if c['name'] == 'Ldev/local/newsoul/SoulHook;'), None)

with open(OUT, 'w', encoding='utf-8') as f:
    json.dump(report, f, indent=2, ensure_ascii=False)

print(f"classes={len(classes)} strings={len(strs)}")
print(f"hooker_classes={len(hookers)}")
print(f"target_class_refs={len(target_refs)}")
print(f"wrote {OUT}")
print()
print("=== CLASSES (by name) ===")
for c in sorted(classes, key=lambda x: x['name']):
    print(f"{c['name']}   super={c['super']}  ifaces={','.join(c['interfaces'])}")
