import struct,sys,re
from dexparse import DEX
from dalvik import _fmt
d = DEX(open('classes.dex','rb').read())
OPNAME = {0x1a:'const-string',0x1b:'const-string/jumbo',0x6e:'invoke-virtual',0x6f:'invoke-super',0x70:'invoke-direct',0x71:'invoke-static',0x72:'invoke-interface',0x74:'invoke-virtual/range',0x75:'invoke-super/range',0x76:'invoke-direct/range',0x77:'invoke-static/range',0x78:'invoke-interface/range',0x52:'iget',0x59:'iput',0x54:'iget-object',0x5b:'iput-object',0x60:'sget',0x67:'sput',0x62:'sget-object',0x69:'sput-object',0x1c:'const-class',0x22:'new-instance',0x1f:'check-cast'}
def annotate(code):
    insns=code['insns']; n=len(insns)//2; i=0; out=[]
    while i<n:
        o=i*2; op=insns[o]; size=_fmt.get(op,1); raw=insns[o:o+size*2]; extra=''
        try:
            if op==0x1a: extra='  -> '+repr(d.strings[struct.unpack_from('<H',raw,2)[0]])
            elif op==0x1b: extra='  -> '+repr(d.strings[struct.unpack_from('<I',raw,2)[0]])
            elif op in (0x6e,0x6f,0x70,0x71,0x72,0x74,0x75,0x76,0x77,0x78): extra='  -> '+d.method_sig(struct.unpack_from('<H',raw,2)[0])
            elif op in (0x1c,0x1f,0x20,0x22): extra='  -> '+str(d.type_of(struct.unpack_from('<H',raw,2)[0]))
            elif op in tuple(range(0x52,0x6e)): 
                f=d.fields[struct.unpack_from('<H',raw,2)[0]]; extra='  -> '+f['class']+'->'+f['name']+':'+f['type']
        except Exception as e: extra=' !!'
        out.append('%04x  %-22s%s'%(o,OPNAME.get(op,'op%02x'%op),extra)); i+=size
    return out
pat=re.compile(sys.argv[1])
for c in d.classes:
    cd=d.parse_class_data(c['class_data_off'])
    for m in cd['direct_methods']+cd['virtual_methods']:
        if not pat.search(m['sig']): continue
        code=d.parse_code_item(m['code_off'])
        print('='*100); print(m['sig'])
        if not code: print('  (no code)'); continue
        for l in annotate(code): print('  '+l)
