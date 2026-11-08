#!/usr/bin/env python3
"""
Generates NeoForge model files for the four loom blocks so that the old TranslatedModelLoaderMixin
is no longer needed.

For every loom variant in the ORIGINAL blockstate (e.g. "facing=east,size=2,position=3") it writes
  * a model  assets/conquest/models/block/looms/dynamic/<loom>_<size>_p<position>.json
        {"loader":"conquest:loom","base":..._weave..,"extra":..._threaded..,"size":..,"translation":[..]}
  * a new blockstate in which every old key is split in two:
        "<key>,has_thread=true"  -> the dynamic model (same "y" rotation as the original variant)
        "<key>,has_thread=false" -> the original model
The decision table (size -> small/medium/large, position 2 -> *_45 models, position 3/4 -> +/- X shift:
0.5 for small+medium, 0.9 for large) was read out of the original mixin.

usage: python3 tools/gen_loom_models.py <original-jar> <resources dir, e.g. src/main/resources>
"""
import json, os, re, sys, zipfile

LOOMS = ["warp_weighted_loom", "simple_warp_weighted_loom", "two_beam_loom", "standing_two_beam_loom"]
SIZE = {"1": ("small", "_small", 0.5), "2": ("medium", "", 0.5), "3": ("large", "_large", 0.9)}

def main(jar, res):
    z = zipfile.ZipFile(jar)
    names = set(z.namelist())
    bs_dir = os.path.join(res, "assets/conquest/blockstates")
    mdl_dir = os.path.join(res, "assets/conquest/models/block/looms/dynamic")
    os.makedirs(bs_dir, exist_ok=True); os.makedirs(mdl_dir, exist_ok=True)
    made = {}; problems = []
    for loom in LOOMS:
        src = f"assets/conquest/blockstates/{loom}.json"
        d = json.loads(z.read(src).decode("utf-8-sig"))
        new = {}
        for key, val in d["variants"].items():
            props = dict(p.split("=") for p in key.split(","))
            if "has_thread" in props:
                problems.append(f"{loom}: key already has has_thread: {key}"); continue
            size_name, suffix, shift = SIZE[props["size"]]
            pos = int(props["position"])
            sfx = suffix + ("_45" if pos == 2 else "")
            base = f"conquest:block/looms/{loom}_weave{sfx}"
            extra = f"conquest:block/looms/{loom}_threaded{sfx}"
            for m in (base, extra):
                if f"assets/conquest/models/{m.split(':')[1]}.json" not in names:
                    problems.append(f"{loom}: referenced model missing in jar: {m}")
            model = {"loader": "conquest:loom", "base": base, "extra": extra, "size": size_name}
            if pos == 3: model["translation"] = [shift, 0.0, 0.0]
            elif pos == 4: model["translation"] = [-shift, 0.0, 0.0]
            dyn = f"{loom}_{size_name}_p{pos}"
            if dyn in made and made[dyn] != model:
                problems.append(f"{loom}: conflicting definitions for {dyn}")
            made[dyn] = model
            dyn_variant = {"model": f"conquest:block/looms/dynamic/{dyn}"}
            if "y" in val: dyn_variant["y"] = val["y"]
            new[key + ",has_thread=true"] = dyn_variant
            new[key + ",has_thread=false"] = val
        d["variants"] = new
        with open(os.path.join(bs_dir, f"{loom}.json"), "w") as f: json.dump(d, f, indent=1)
    for dyn, model in made.items():
        with open(os.path.join(mdl_dir, dyn + ".json"), "w") as f: json.dump(model, f, indent=1)
    print(f"wrote {len(LOOMS)} blockstates and {len(made)} loom models")
    for p in problems: print("PROBLEM:", p)
    return 1 if problems else 0

if __name__ == "__main__":
    sys.exit(main(sys.argv[1], sys.argv[2]))
