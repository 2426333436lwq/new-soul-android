import sys;sys.stdout.reconfigure(encoding="utf-8")
from dexparse import DEX
d=DEX(open("classes.dex","rb").read())
for c in d.classes:
    if c["name"]!="Ldev/local/newsoul/a;": continue
    cd=d.parse_class_data(c["class_data_off"])
    for m in cd["virtual_methods"]:
        code=d.parse_code_item(m["code_off"]); insns=code["insns"]
        # scan for switch payloads (nop with hi=0x01 or 0x02) inside insns
        n=len(insns)//2
        i=0
        while i<n:
            off=i*2
            op=insns[off]
            if op==0x00 and insns[off+1] in (0x01,0x02):
                kind=insns[off+1]
                size=int.from_bytes(insns[off+2:off+4],"little")
                if kind==0x01:
                    first=int.from_bytes(insns[off+4:off+8],"little",signed=True)
                    print("PACKED-SWITCH payload @ unit 0x%04x size=%d first=%d"%(i,size,first))
                    for k in range(size):
                        t=int.from_bytes(insns[off+8+4*k:off+12+4*k],"little",signed=True)
                        print("    case %4d -> unit 0x%04x"%(first+k,(i+t)&0xffff))
                    break
                else:
                    print("SPARSE-SWITCH payload @ unit 0x%04x size=%d"%(i,size))
                    break
            i+=1
