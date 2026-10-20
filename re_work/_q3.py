import sys;sys.stdout.reconfigure(encoding="utf-8")
from dexparse import DEX
d=DEX(open("classes.dex","rb").read())
# decode packed-switch at insns offset 0x0a5c in a.intercept code item
for c in d.classes:
    if c["name"]!="Ldev/local/newsoul/a;": continue
    cd=d.parse_class_data(c["class_data_off"])
    for m in cd["virtual_methods"]:
        if m["name"]!="intercept": continue
        code=d.parse_code_item(m["code_off"]); insns=code["insns"]
        off=0x0a5c
        ident=int.from_bytes(insns[off:off+2],"little")
        size=int.from_bytes(insns[off+2:off+4],"little")
        first=int.from_bytes(insns[off+4:off+8],"little",signed=True)
        print("packed-switch ident=0x%04x size=%d first=%d"%(ident,size,first))
        base=off
        for k in range(size):
            t=int.from_bytes(insns[off+8+4*k:off+12+4*k],"little",signed=True)
            print("   case %3d (0x%x) -> %04x"%(first+k,first+k,(base+t*2)&0xffff))
