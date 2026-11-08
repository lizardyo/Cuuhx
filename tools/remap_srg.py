#!/usr/bin/env python3
"""
Rename Forge 1.20.1 SRG identifiers (m_12345_ / f_12345_) in the decompiled sources to
Mojang (official) names, so the code is readable and can be fixed up for 1.21.1.

Needs internet (run it in a Codespace). Data sources:
  * Mojang client mappings for 1.20.1 (from Mojang's version manifest)
  * MCPConfig 1.20.1 joined.tsrg  (obf -> srg), from the Minecraft Forge maven

Usage:  python3 tools/remap_srg.py [--src src/main/java] [--at src/main/resources/META-INF/accesstransformer.cfg]
Only touches identifiers matching m_<digits>_ and f_<digits>_. Parameter names (p_<digits>_) are left alone.
UNTESTED against the real files (author could not reach those servers). If a download URL fails,
open the URL in a browser, download the file, and pass --mojang-file / --tsrg-file.
"""
import argparse, io, json, os, re, sys, urllib.request, zipfile

MCP_URL = ("https://maven.minecraftforge.net/de/oceanlabs/mcp/mcp_config/"
           "1.20.1-20230612.114412/mcp_config-1.20.1-20230612.114412.zip")
MANIFEST = "https://piston-meta.mojang.com/mc/game/version_manifest_v2.json"
PRIM = {'byte':'B','char':'C','double':'D','float':'F','int':'I','long':'J','short':'S','boolean':'Z','void':'V'}

def get(url):
    print('GET', url, file=sys.stderr)
    req = urllib.request.Request(url, headers={'User-Agent': 'Mozilla/5.0 (X11; Linux x86_64)'})
    with urllib.request.urlopen(req, timeout=120) as r: return r.read()

def parse_mojang(text):
    """returns (m2o, o2m, fields{(ocls,oname)->mname}, methods[(ocls,oname,odesc)->mname]) after 2 passes"""
    m2o, classes = {}, []
    cur = None; raw = []
    for line in text.splitlines():
        if not line or line.startswith('#'): continue
        if not line.startswith(' '):
            a, b = line.rstrip(':').split(' -> ')
            m2o[a.strip().replace('.', '/')] = b.strip().replace('.', '/')
            cur = a.strip().replace('.', '/'); 
        else:
            raw.append((cur, line.strip()))
    def desc_type(t):
        arr = 0
        while t.endswith('[]'): arr += 1; t = t[:-2]
        if t in PRIM: d = PRIM[t]
        else:
            n = t.replace('.', '/'); d = 'L' + m2o.get(n, n) + ';'
        return '[' * arr + d
    fields, methods = {}, {}
    for cls, l in raw:
        ocls = m2o[cls]
        body, obf = l.split(' -> ')
        if '(' in body:
            body = re.sub(r'^\d+:\d+:', '', body)
            ret, rest = body.split(' ', 1)
            name, args = rest.split('(', 1); args = args.rstrip(')')
            argl = [a for a in args.split(',') if a]
            odesc = '(' + ''.join(desc_type(a) for a in argl) + ')' + desc_type(ret)
            methods[(ocls, obf, odesc)] = name
        else:
            t, name = body.split(' ', 1)
            fields[(ocls, obf)] = name
    return fields, methods

def parse_tsrg(text):
    """srg id -> (obf class, obf name, obf desc or None)"""
    out = {}; cls = None
    for line in text.splitlines():
        if not line.strip() or line.startswith('tsrg2'): continue
        if not line.startswith('\t'):
            cls = line.split(' ')[0]; continue
        if line.startswith('\t\t'): continue
        tok = line.strip().split(' ')
        if len(tok) >= 3 and re.fullmatch(r'f_\d+_', tok[1]):
            out[tok[1]] = (cls, tok[0], None)
        elif len(tok) >= 4 and re.fullmatch(r'm_\d+_', tok[2]):
            out[tok[2]] = (cls, tok[0], tok[1])
        elif len(tok) == 2 and re.fullmatch(r'f_\d+_', tok[1]):
            out[tok[1]] = (cls, tok[0], None)
        elif len(tok) == 3 and re.fullmatch(r'm_\d+_', tok[2]):
            out[tok[2]] = (cls, tok[0], tok[1])
    return out

def build_map(mojang_text, tsrg_text):
    fields, methods = parse_mojang(mojang_text)
    srg = parse_tsrg(tsrg_text); res = {}
    for sid, (ocls, oname, odesc) in srg.items():
        n = fields.get((ocls, oname)) if odesc is None else methods.get((ocls, oname, odesc))
        if n: res[sid] = n
    return res, len(srg)

def apply(path_glob_root, mapping, exts):
    pat = re.compile(r'\b([mf]_\d+_)\b'); changed = 0; missed = set()
    def sub(m):
        k = m.group(1)
        if k in mapping: return mapping[k]
        missed.add(k); return k
    for dp, _, fn in os.walk(path_glob_root):
        for f in fn:
            if f.endswith(exts):
                p = os.path.join(dp, f); t = open(p, encoding='utf-8').read(); n = pat.sub(sub, t)
                if n != t: open(p, 'w', encoding='utf-8').write(n); changed += 1
    return changed, missed

if __name__ == '__main__':
    ap = argparse.ArgumentParser()
    ap.add_argument('--src', default='src/main/java'); ap.add_argument('--at')
    ap.add_argument('--mojang-file'); ap.add_argument('--tsrg-file')
    a = ap.parse_args()
    if a.mojang_file: mj = open(a.mojang_file, encoding='utf-8').read()
    else:
        man = json.loads(get(MANIFEST)); v = next(x for x in man['versions'] if x['id'] == '1.20.1')
        vj = json.loads(get(v['url'])); mj = get(vj['downloads']['client_mappings']['url']).decode()
    if a.tsrg_file: ts = open(a.tsrg_file, encoding='utf-8').read()
    else:
        z = zipfile.ZipFile(io.BytesIO(get(MCP_URL))); ts = z.read('config/joined.tsrg').decode()
    mapping, total = build_map(mj, ts)
    if not mapping:
        raise SystemExit('error: no SRG names were mapped; check the mappings format and inputs')
    print(f'mapped {len(mapping)} of {total} srg ids found in mappings')
    c, missed = apply(a.src, mapping, ('.java',)); print('java files changed:', c)
    if a.at and os.path.exists(a.at):
        # AT lines look like:  public net.minecraft.X f_98507_ # selectedTab
        t = open(a.at, encoding='utf-8').read()
        t = re.sub(r'\b([mf]_\d+_)\b', lambda m: mapping.get(m.group(1), m.group(1)), t)
        open(a.at, 'w', encoding='utf-8').write(t); print('access transformer updated')
    if missed: print('unmapped srg ids left in source:', len(missed), sorted(missed)[:15])
