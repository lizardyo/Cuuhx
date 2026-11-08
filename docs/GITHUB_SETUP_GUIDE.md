# Build Conquest Reforged for 1.21.1 on GitHub (no local setup)

Everything happens in your browser using GitHub Codespaces (a cloud VS Code with a terminal).
Free accounts get a monthly allowance of Codespaces hours; check github.com/settings/billing for
your current limits, and STOP the codespace when you are not using it.

IMPORTANT: Conquest Studios allowed personal use only. Keep the repository PRIVATE, never commit the
original jar or its extracted assets (the .gitignore already blocks them), and do not share the built jar.

## 0. What you need
- A GitHub account
- The original `ConquestReforged-forge-1_20_1-1_7_0.jar`
- `ConquestReforged_1.21.1_port_repo.zip` (this project)

## 1. Create the private repo
1. github.com -> **New repository**. Name it `conquest-reforged-1-21-1`. Choose **Private**.
2. Tick **Add a README file** (required: GitHub cannot open a Codespace on a completely empty repo). Click **Create repository**.

## 2. Open a Codespace
1. On the repo page: green **Code** button -> **Codespaces** tab -> **Create codespace on main**.
2. When it loads, open the machine menu (Codespaces tab -> "..." -> *Change machine type*) and pick **4-core** if offered. Gradle + Minecraft need memory.

## 3. Upload the project and the jar
1. Drag `ConquestReforged_1.21.1_port_repo.zip` into the Explorer panel (left side) of the Codespace.
2. Open the terminal (Ctrl+`) and run:
   ```
   unzip -o ConquestReforged_1.21.1_port_repo.zip && rm ConquestReforged_1.21.1_port_repo.zip
   chmod +x gradlew
   ```
   (If the zip created a subfolder, move its contents up so `build.gradle` is in the repo root.)
3. Drag the original jar into the `inputs/` folder in the Explorer.

## 4. Extract resources and convert data
```
bash tools/setup_resources.sh
```
This copies assets out of the original jar and converts ~36,000 recipes, loot tables and tags to the 1.21.1 format.
You should see roughly 155,000 asset files and 36,528 data files.
The script accepts either the underscore or dotted filename used for the original jar in `inputs/`.

## 5. Make the code readable (remap names)
```
python3 tools/remap_srg.py --at src/main/resources/META-INF/accesstransformer.cfg
```
This downloads Mojang's 1.20.1 mappings and the Forge 1.20.1 SRG mappings and renames the ~1,500 identifiers
like `m_46859_` to real names. If a download fails, the script tells you the URL; download it in your browser,
upload it, and pass `--mojang-file` / `--tsrg-file`.
Commit the result: `git add -A && git commit -m "remap to Mojang names" && git push`

## 6. Fix the code for 1.21.1 (the real work)
```
./gradlew compileJava
```
The first run downloads NeoForge and Minecraft and takes several minutes. Expect hundreds of compile errors the
first time. See `PORTING_NOTES.md` for the categories (Forge -> NeoForge events/registries/networking, the
1.20.5+ Block/Item/BlockEntity changes, mixins, Fabric rendering API usage).
Start with docs/PORT_ORDER.md for the order. Work through them in loops: compile -> fix -> compile. You can do this with Claude Code in the Codespace terminal:
```
npm install -g @anthropic-ai/claude-code
claude
```
then ask it to read PORTING_NOTES.md and fix compile errors a package at a time. (Claude Code needs your own
Anthropic login or API access.) Commit often.

## 7. Build the jar and test
```
./gradlew build
```
The jar is in `build/libs/`. Right-click it in the Explorer -> **Download**.
To test inside the Codespace instead: `./gradlew runClient` needs a graphical desktop, so test on your own PC.

## 8. Install on your PC
1. Install **NeoForge 21.1.x for Minecraft 1.21.1** (the project is built against 21.1.265).
2. Put your built jar in the `mods` folder, plus the companion mods from the table in PORTING_NOTES.md.
3. Enable the CR resource pack (`rp_crrp`) in the in-game resource pack screen.
4. Use Iris for shaders, and your shader pack in the `shaderpacks` folder.

## GitHub Actions
`.github/workflows/build.yml` runs `./gradlew compileJava` on every push (Actions tab). It proves the code compiles,
but its output is not playable because assets are not in the repo. Build the playable jar in the Codespace.

## Troubleshooting
- Out of memory: use the 4-core machine, or lower `org.gradle.jvmargs` in `gradle.properties`.
- `gradlew: Permission denied`: run `chmod +x gradlew`.
- Mixin errors at runtime: the mixin config uses relaxed `defaultRequire: 0`; each mixin must still be re-targeted.
- Stop the codespace (Codespaces menu -> Stop) when done.
