import sys;sys.stdout.reconfigure(encoding="utf-8")
from dexparse import DEX
d=DEX(open("classes.dex","rb").read())
for c in d.classes:
    if c["name"]!="Ldev/local/newsoul/a;": continue
    cd=d.parse_class_data(c["class_data_off"])
    for m in cd["virtual_methods"]:
        if m["name"]!="intercept": continue
        code=d.parse_code_item(m["code_off"])
        print("code_off=0x%x regs=%d ins=%d tries=%d insns_size(units)=%d insns_bytes=%d"%(
            m["code_off"],code["registers_size"],code["ins_size"],code["tries_size"],code["insns_size"],len(code["insns"])))
        insns=code["insns"]
        print("tail 64 bytes:",insns[-64:].hex())
