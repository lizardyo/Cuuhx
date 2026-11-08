import zipfile,json,os,re,collections,sys
# usage: python3 tools/convert_data.py <original-1.20.1-jar> <output data dir>
J=sys.argv[1]
OUT=sys.argv[2]
z=zipfile.ZipFile(J)
DIRMAP={'recipes':'recipe','loot_tables':'loot_table','tags':'tags','advancements':'advancement','structures':'structure'}
TAGMAP={'blocks':'block','items':'item','fluids':'fluid','entity_types':'entity_type','game_events':'game_event'}
stats=collections.Counter()
def conv_result(r,count=None):
    if isinstance(r,str): o={'id':r}
    elif isinstance(r,dict):
        o={'id':r.get('item',r.get('id'))}
        count=r.get('count',count)
    else: raise ValueError(r)
    if count is not None and count!=1: o['count']=count
    elif count==1: o['count']=1
    return o
def fix_tagref(s): return re.sub(r'(#?)forge:',r'\1c:',s)
for n in z.namelist():
    if not (n.startswith('data/') and n.endswith('.json')): continue
    p=n.split('/'); ns,kind=p[1],p[2]
    raw=z.read(n).decode('utf-8-sig')
    d=json.loads(raw)
    rest=p[3:]
    if kind=='recipes':
        t=d.get('type')
        if t=='minecraft:stonecutting':
            d['result']=conv_result(d['result'],d.pop('count',None)); stats['stonecutting']+=1
        elif t=='minecraft:crafting_shaped':
            d['result']=conv_result(d['result']); stats['shaped']+=1
        else: stats['UNHANDLED '+str(t)]+=1
    elif kind=='tags':
        sub=TAGMAP.get(rest[0],rest[0]); rest=[sub]+rest[1:]
        if ns=='forge': ns='c'
        d['values']=[fix_tagref(v) if isinstance(v,str) else v for v in d.get('values',[])]
        stats['tag']+=1
    elif kind=='loot_tables': stats['loot']+=1
    else: stats['other '+kind]+=1
    newkind=DIRMAP.get(kind,kind)
    dst=os.path.join(OUT,ns,newkind,*rest)
    os.makedirs(os.path.dirname(dst),exist_ok=True)
    json.dump(d,open(dst,'w',encoding='utf-8'),indent=None,separators=(',',':'))
print(dict(stats))
