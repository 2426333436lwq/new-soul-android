import sys;sys.stdout.reconfigure(encoding="utf-8")
from dexparse import DEX
from dalvik import iter_code_units
d=DEX(open("classes.dex","rb").read())
for c in d.classes:
    if c["name"]!="Ldev/local/newsoul/a;": continue
    cd=d.parse_class_data(c["class_data_off"])
    for m in cd["virtual_methods"]:
        if m["name"]!="intercept": continue
        code=d.parse_code_item(m["code_off"]); insns=code["insns"]
        order=[0x1410,0x1314,0x12b6,0x1290,0x1228,0x1194,0x1140,0x1108,0x10dc,0x1016,0x0f70,0x0f24,0x0e34,0x0e18,0x0d42,0x0cdc,0x0b76,0x0b1a,0x0af4,0x0ab0,0x0a8a]
        allc=sorted(set(order+[0x0ae0]))
        n=len(insns)
        for idx,st in enumerate(order):
            nxt=min([x for x in allc if x>st]+[0x0ae0])
            seg=insns[st*2:min(nxt*2,n)]
            strs=[];meths=[]
            for off,op,size,raw in iter_code_units(seg):
                try:
                    if op in (0x1a,0x1b): strs.append(d.strings[int.from_bytes(raw[2:4],"little")])
                    elif op in (0x6e,0x6f,0x70,0x71,0x72,0x74,0x75,0x76,0x77,0x78):
                        i=int.from_bytes(raw[2:4],"little")
                        if i<len(d.methods): meths.append(d.method_sig(i).replace("Ldev/local/newsoul/","").replace("Lio/github/libxposed/api/",""))
                except Exception: pass
            print("="*100)
            print("MODE case %d  units 0x%04x-0x%04x"%(idx,st,nxt))
            print("  STR :",strs)
            print("  CALL:",meths)
