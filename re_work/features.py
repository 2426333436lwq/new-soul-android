"""Feature-map extraction: Chinese labels + pref keys + Soul target classes,
paired with the hook bodies that implement them.

Strategy: for each method with code, disassemble enough to find
  const-string / sget-object(constants) and invoke-* target method ids,
so we can attribute a feature (by pref key + Chinese label) to a hook class.
"""
import sys, json, re, struct, collections
from dexparse import DEX

DEX_PATH = sys.argv[1] if len(sys.argv) > 1 else 'classes.dex'
with open(DEX_PATH, 'rb') as f:
    d = DEX(f.read())

# ---------------- string -> method attribution ----------------
# Build reverse index: string index -> list of methods whose code references it
def iter_instructions(code, data):
    """Yield (offset_in_insns, opcode, size_units, raw) for a code item's insns."""
    insns = code['insns']
    n = len(insns) // 2
    i = 0
    while i < n:
        off = i * 2
        b = insns[off]
        op = b & 0xFF
        if op == 0x00:  # nop / payload
            hi = insns[off + 1]
            if hi == 0x01:   # packed-switch payload
                size = struct.unpack_from('<H', insns, off + 2)[0]
                total = (size * 2) + 4
                yield off, op, total, insns[off:off + total * 2]
                i += total
                continue
            elif hi in (0x02, 0x03):
                size, = struct.unpack_from('<H', insns, off + 2)
                elem = 4 if hi == 0x02 else 8
                words = size * (elem // 2)
                total = words + 4
                yield off, op, total, insns[off:off + total * 2]
                i += total
                continue
            else:
                yield off, op, 1, insns[off:off + 2]
                i += 1
                continue
        # regular opcodes
        size = 1
        a = (b >> 8) & 0xFF
        if op in (0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09):     # moves
            size = 1
        elif op == 0x0a: size = 2   # move-result*
        elif op in (0x0b,): size = 2
        elif op in (0x0c, 0x0d, 0x0e, 0x0f, 0x10): size = 2
        elif op in (0x11,): size = 2
        elif op in (0x12, 0x13, 0x14, 0x15, 0x16, 0x17, 0x18, 0x19, 0x1a, 0x1b, 0x1c): size = 1
        elif op in (0x1d, 0x1e, 0x1f, 0x20, 0x21, 0x22): size = 1
        elif op == 0x23: size = 2
        elif op == 0x24: size = 2
        elif op in (0x25,): size = 2
        elif op == 0x26: size = 2
        elif op == 0x27: size = 1
        elif op == 0x28: size = 1
        elif op in (0x29, 0x2a): size = 2
        elif op in (0x2b, 0x2c, 0x2d, 0x2e, 0x2f, 0x30, 0x31, 0x32,
                    0x33, 0x34, 0x35, 0x36, 0x37, 0x38): size = 3
        elif op in (0x39, 0x3a, 0x3b, 0x3c, 0x3d): size = 2
        elif op in (0x3e,): size = 1
        elif op in (0x3f, 0x40, 0x41, 0x42, 0x43, 0x44): size = 2
        elif op in tuple(range(0x45, 0x52)): size = 2
        elif op in tuple(range(0x52, 0x5b)): size = 2
        elif op in tuple(range(0x5b, 0x60)): size = 2   # 5b..5f unused but safe
        elif op in tuple(range(0x60, 0x6e)): size = 2
        elif op in tuple(range(0x6e, 0x73)): size = 3
        elif op in tuple(range(0x73, 0x79)): size = 1
        elif op in tuple(range(0x79, 0x7a)): size = 2
        elif op in tuple(range(0x7a, 0x7c)): size = 1
        elif op in tuple(range(0x7c, 0x80)): size = 1
        elif op in tuple(range(0x80, 0x82)): size = 1
        elif op in tuple(range(0x82, 0x90)): size = 2
        elif op in tuple(range(0x90, 0xa0)): size = 2
        elif op in tuple(range(0xa0, 0xb0)): size = 2
        elif op in tuple(range(0xb0, 0xd0)): size = 2
        elif op in tuple(range(0xd0, 0xd8)): size = 2
        elif op in tuple(range(0xd8, 0xe3)): size = 2
        elif op == 0xe3: size = 2
        elif op in tuple(range(0xe4, 0xf0)): size = 2   # unused
        elif op in tuple(range(0xf0, 0xf7)): size = 4
        elif op in tuple(range(0xf7, 0xfa)): size = 4
        elif op in tuple(range(0xfa, 0xff)): size = 3
        yield off, op, size, insns[off:off + size * 2]
        i += size

def code_strings_and_methods(code):
    """Return (set of string indices, set of method indices, set of field indices, set of type indices)."""
    s_idx, m_idx, f_idx, t_idx = set(), set(), set(), set()
    insns = code['insns']
    for off, op, size, raw in iter_instructions(code, insns):
        try:
            if op == 0x1a:  # const-string
                idx = struct.unpack_from('<H', raw, 2)[0]
                s_idx.add(idx)
            elif op == 0x1b:  # const-string/jumbo
                idx = struct.unpack_from('<I', raw, 2)[0]
                s_idx.add(idx)
            elif op in (0x60, 0x61, 0x62, 0x63, 0x64, 0x65, 0x66, 0x67,
                        0x68, 0x69, 0x6a, 0x6b, 0x6c, 0x6d,   # sget/sput
                        0x6e, 0x6f, 0x70, 0x71, 0x72):         # invoke-*
                idx = struct.unpack_from('<H', raw, 2)[0]
                if op in (0x60, 0x61, 0x62, 0x63, 0x64, 0x65, 0x66, 0x67,
                          0x68, 0x69, 0x6a, 0x6b, 0x6c, 0x6d):
                    f_idx.add(idx)
                else:
                    m_idx.add(idx)
            elif op in (0x1c, 0x1f, 0x20, 0x22):  # const-class / check-cast / instance-of / new-instance
                idx = struct.unpack_from('<H', raw, 2)[0]
                t_idx.add(idx)
            elif op == 0x21:
                idx = struct.unpack_from('<H', raw, 2)[0]
                t_idx.add(idx)
        except Exception:
            pass
    return s_idx, m_idx, f_idx, t_idx

# Walk every method with code, collect its string/type/method refs
method_info = {}   # (class, name, sig) -> info
string_to_methods = collections.defaultdict(set)
type_to_methods = collections.defaultdict(set)

for c in d.classes:
    cd = d.parse_class_data(c['class_data_off'])
    for m in cd['direct_methods'] + cd['virtual_methods']:
        if not m['code_off']:
            continue
        code = d.parse_code_item(m['code_off'])
        if not code:
            continue
        key = m['sig']
        s_idx, m_idx, f_idx, t_idx = code_strings_and_methods(code)
        strs = [d.strings[i] for i in sorted(s_idx) if i < len(d.strings)]
        types = [d.type_of(i) for i in sorted(t_idx)]
        callees = [d.method_sig(i) for i in sorted(m_idx) if i < d.method_ids_size]
        method_info[key] = {'class': m['class'], 'name': m['name'],
                            'strings': strs, 'types': types, 'callees': callees}
        for s in strs:
            string_to_methods[s].add(key)
        for t in types:
            if t and 'soulapp' in t.lower():
                type_to_methods[t].add(key)

# ---------------- report ----------------
out = {}
out['counts'] = {'classes': d.class_defs_size, 'methods_with_code': len(method_info)}

# A) Chinese labels
cn = sorted({s for s in d.strings if re.search(r'[\u4e00-\u9fff]', s)})
out['chinese_strings'] = cn
print(f"=== CHINESE UI STRINGS ({len(cn)}) ===")
for s in cn:
    # which methods use it
    users = sorted(string_to_methods.get(s, []))
    loc = ', '.join(u.split('->')[0].split('/')[-1].rstrip(';') + '.' + u.split('->')[1].split('(')[0] for u in users[:4])
    print(f"  {s!r:<58} <- {loc}")

# B) Feature pref keys (the toggle names)
PREF_RE = re.compile(r'^(ad_|chat_|feed_|square_|privacy_|profile_disguise_|post_|settings|block_|download_|main_|menu_|game_|match_|ai_reply|newsoul_|startup_|hook_|debug_)')
prefs = sorted({s for s in d.strings if PREF_RE.match(s)})
out['feature_pref_keys'] = prefs
print(f"\n=== FEATURE PREF KEYS ({len(prefs)}) ===")
for s in prefs:
    users = sorted(string_to_methods.get(s, []))
    loc = ', '.join(u.split('->')[0].split('/')[-1].rstrip(';') + '.' + u.split('->')[1].split('(')[0] for u in users[:5])
    print(f"  {s!r:<46} <- {loc}")

# C) Soul target classes and the hook methods referencing them
print(f"\n=== SOUL TARGET CLASSES -> HOOK METHODS ===")
for t, ms in sorted(type_to_methods.items()):
    print(f"\n  {t}")
    for m in sorted(ms):
        print(f"      {m.split('->')[0].split('/')[-1].rstrip(';')}.{m.split('->')[1].split('(')[0]}")

# D) the update-check / self-update strings
print("\n=== REVIEW: update check context ===")
for key, info in method_info.items():
    for s in info['strings']:
        if 'NewSoul-UpdateCheck' in s or 'lanzoul' in s or 't.me' in s or 'app.soulapp.cn' in s:
            print(f"  {key}")
            for ss in info['strings']:
                print(f"      {ss!r}")
            print()

with open('featuremap.json', 'w', encoding='utf-8') as f:
    json.dump({'counts': out['counts'], 'chinese': cn, 'prefs': prefs,
               'type_to_methods': {k: sorted(v) for k, v in type_to_methods.items()},
               'string_to_methods': {k: sorted(v) for k, v in string_to_methods.items() if len(k) < 200}},
              f, indent=2, ensure_ascii=False)
print("\nwrote featuremap.json")
