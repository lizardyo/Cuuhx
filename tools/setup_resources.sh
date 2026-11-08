#!/usr/bin/env bash
# Extracts assets from the ORIGINAL 1.20.1 jar and converts its data files to the 1.21.1 layout.
# Usage: bash tools/setup_resources.sh [path/to/ConquestReforged-forge-1_20_1-1_7_0.jar]
set -euo pipefail
JAR="${1:-inputs/ConquestReforged-forge-1_20_1-1_7_0.jar}"
if [ "$#" -eq 0 ] && [ ! -f "$JAR" ] && [ -f "inputs/ConquestReforged-forge-1.20.1-1.7.0.jar" ]; then
  JAR="inputs/ConquestReforged-forge-1.20.1-1.7.0.jar"
fi
[ -f "$JAR" ] || { echo "Jar not found: $JAR (put it in inputs/)"; exit 1; }
RES=src/main/resources
mkdir -p "$RES"
echo ">> extracting assets, resourcepacks, access transformer from $JAR"
unzip -q -o "$JAR" 'assets/*' 'resourcepacks/*' -d "$RES" || true
mkdir -p "$RES/META-INF"
unzip -q -o -j "$JAR" 'META-INF/accesstransformer.cfg' -d "$RES/META-INF" || true
# the mod's own pack.mcmeta: 1.21.1 resource pack format is 34
cat > "$RES/pack.mcmeta" <<'JSON'
{"pack":{"description":"Conquest Reforged (1.21.1 port)","pack_format":34}}
JSON
echo ">> converting data (recipes, loot tables, tags) to 1.21.1 layout"
python3 tools/convert_data.py "$JAR" "$RES/data"
echo ">> generating loom models for the NeoForge geometry loader (replaces the old mixin)"
python3 tools/gen_loom_models.py "$JAR" "$RES"
echo ">> done. Assets: $(find $RES/assets -type f | wc -l) files, data: $(find $RES/data -type f | wc -l) files"
