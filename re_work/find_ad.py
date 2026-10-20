import sys, re, struct, json, collections
from dexparse import DEX
d = DEX(open('classes.dex','rb').read())
# which hooker classes reference ad strings
adstr = [s for s in d.strings if s.startswith('cn.soulapp.android.ad.')]
print('AD TYPE STRINGS:', len(adstr))
for s in sorted(adstr): print('  ', s)
print()
print('=== methods referencing each ad string ===')
for c in d.classes:
    cd = d.parse_class_data(c['class_data_off'])
    for m in cd['direct_methods']+cd['virtual_methods']:
        if not m['code_off']: continue
        code = d.parse_code_item(m['code_off'])
        insns = code['insns']
        hits=[]
        for off,op,size,raw in __import__('dalvik').iter_code_units(insns):
            try:
                if op==0x1a:
                    i=struct.unpack_from('<H',raw,2)[0]
                    if d.strings[i].startswith('cn.soulapp.android.ad.'): hits.append((off,d.strings[i]))
                elif op==0x1b:
                    i=struct.unpack_from('<I',raw,2)[0]
                    if d.strings[i].startswith('cn.soulapp.android.ad.'): hits.append((off,d.strings[i]))
            except Exception: pass
        if hits:
            print(m['sig'])
            for off,h in hits: print('   @%04x %s'%(off,h))
