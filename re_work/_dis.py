import sys,re,json
sys.stdout.reconfigure(encoding="utf-8")
from dexparse import DEX
from dalvik import iter_code_units
d=DEX(open("classes.dex","rb").read())
STR=0x00
def disasm(sigsub):
    for c in d.classes:
        cd=d.parse_class_data(c["class_data_off"])
        for m in cd["direct_methods"]+cd["virtual_methods"]:
            if sigsub in m["sig"]:
                print("#"*110)
                print(m["sig"])
                print("#"*110)
                code=d.parse_code_item(m["code_off"])
                insns=code["insns"]
                strs=[];meths=[]
                for off,op,size,raw in iter_code_units(insns):
                    line="%04x  %-28s"%(off,raw.hex())
                    try:
                        if op==0x1a:
                            i=int.from_bytes(raw[2:4],"little"); line+=" const-string  %r"%d.strings[i]
                        elif op==0x1b:
                            i=int.from_bytes(raw[2:4],"little"); line+=" const-string/jumbo %r"%d.strings[i]
                        elif op in (0x6e,0x6f,0x70,0x71,0x72,0x74,0x75,0x76,0x77,0x78):
                            i=int.from_bytes(raw[2:4],"little"); line+=" invoke        %s"%d.method_sig(i)
                        elif op in (0x1c,0x1f,0x20,0x21,0x22):
                            i=int.from_bytes(raw[2:4],"little"); line+=" type          %s"%d.type_of(i)
                        elif 0x52<=op<=0x6d:
                            i=int.from_bytes(raw[2:4],"little")
                            f=d.fields[i]; line+=" field         %s->%s:%s"%(f["class"],f["name"],f["type"])
                        elif op==0x13:
                            line+=" const/16      %d"%int.from_bytes(raw[2:4],"little",signed=True)
                        elif op==0x12:
                            line+=" const/4       %d"%((raw[1]>>4) if (raw[1]>>4)<8 else (raw[1]>>4)-16)
                        elif op==0x14:
                            line+=" const         %d"%int.from_bytes(raw[2:6],"little",signed=True)
                        elif op==0x15:
                            line+=" const/high16"
                        elif op==0x17:
                            line+=" const-wide/16"
                    except Exception as e: line+=" <err %s>"%e
                    print(line)
disasm(sys.argv[1])
