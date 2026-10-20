import sys;sys.stdout.reconfigure(encoding="utf-8")
from dexparse import DEX
from dalvik import iter_code_units
d=DEX(open("classes.dex","rb").read())
N=len(d.methods)
for c in d.classes:
    cd=d.parse_class_data(c["class_data_off"])
    for m in cd["direct_methods"]+cd["virtual_methods"]:
        if not m["code_off"]: continue
        code=d.parse_code_item(m["code_off"]); insns=code["insns"]
        for off,op,size,raw in iter_code_units(insns):
            if op in (0x6e,0x6f,0x70,0x71,0x72,0x74,0x75,0x76,0x77,0x78):
                if len(raw)<4: continue
                i=int.from_bytes(raw[2:4],"little")
                if i>=N: continue
                s=d.method_sig(i)
                if "newsoul/a;-><init>" in s or "newsoul/b;-><init>" in s or "newsoul/d;-><init>" in s or "newsoul/e;-><init>" in s:
                    print("%-95s @%04x  %s"%(m["sig"],off,s))
