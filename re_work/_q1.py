import json,re,sys,io
sys.stdout.reconfigure(encoding="utf-8")
m=json.load(open("featuremap.json",encoding="utf-8"))
md=m["method_data"]
# every method whose strings mention ad_ toggles or cn.soulapp.android.ad
pat=re.compile(r"ad_|cn\.soulapp\.android\.ad|setData|replaceData|setNewData|setDiffNewData|setList|addData|setNewInstance|onAdFailed|loadAds|getVisibility|setVisibility|setId|ad-")
rows=[]
for k,v in md.items():
    hits=[s for s in v["strings"] if pat.search(s)]
    if hits: rows.append((k,hits,len(v["callees"])))
print("methods touching ad patterns:",len(rows))
for k,h,n in sorted(rows):
    print("-"*100)
    print(k, " callees=",n)
    for s in h: print("    ",repr(s)[:160])
