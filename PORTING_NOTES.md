# Conquest Reforged 1.20.1 (Forge) -> 1.21.1 (NeoForge): port notes

Permission: Conquest Studios (author) granted permission on Discord to decompile, modify with an AI tool,
and use the result for PERSONAL USE ONLY. Do not upload or share the result.

STATUS: scaffold only. The code has NOT been compiled or run by the author of this repo. Expect it to fail
to compile until steps 5-6 of docs/GITHUB_SETUP_GUIDE.md are done.

## Done already
- Decompiled 647 sources (Vineflower 1.12.0) into src/main/java/com/conquestrefabricated
- Removed Architectury @ExpectPlatform leftovers (7 files). Their bodies are already the Forge implementations.
- Fabric rendering API removed (3 files rewritten, 1 mixin replaced by a geometry loader + model events)
- Data conversion script (tools/convert_data.py): 18,325 stonecutting + 8 crafting recipes (result -> {id,count}),
  18,165 loot tables, 30 tags (forge: -> c:), folders renamed to singular. Run by tools/setup_resources.sh.
- NeoForge 21.1.265 + ModDevGradle 2.0.148 + Gradle 9.2.1 build (taken from the official NeoForge 1.21.1 MDK)
- tools/remap_srg.py: SRG -> Mojang renamer; fixed to parse TSRG2 and validated against the 1.20.1 mappings (64,225 entries)

## Known code work (from reading the source)
- ~1,500 SRG identifiers must be remapped first (tools/remap_srg.py).
- 53 files use net.minecraftforge APIs: @Mod/EventBusSubscriber, registries (ForgeRegistries/RegistryObject),
  lifecycle events, RegisterColorHandlersEvent, BuildCreativeModeTabContentsEvent, AddPackFindersEvent,
  reload listeners, SimpleChannel/NetworkHooks networking (rewrite to payload handlers),
  IEntityAdditionalSpawnData, gui overlays, particle providers, input/tick events.
- 1.20.5+ changes that hit many Block/Item/BlockEntity classes: Block.codec(), use() split into
  useWithoutItem/useItemOn, ResourceLocation.fromNamespaceAndPath, BlockEntity save/load with
  HolderLookup.Provider, ItemStack NBT -> data components.
- 11 mixins remain (src/main/resources/conquest.mixins.json, refmap removed, defaultRequire 0) and must be
  re-targeted. PackRepositoryMixin exists in source but is not listed in the mixin config.
  TranslatedModelLoaderMixin was REMOVED: see docs/FABRIC_API_CONVERSION.md.
- Fabric API usage: DONE (code written, not compiled). No net.fabricmc imports remain in src/. The two
  Fabric-renderer models were rewritten for NeoForge, and FabricLoader -> ModList. Details and the list of
  things to verify are in docs/FABRIC_API_CONVERSION.md. The jar-in-jar Fabric API is no longer needed.
- Access wideners in the jar use Yarn names; the Forge build's META-INF/accesstransformer.cfg (SRG names) is
  extracted by setup_resources.sh and remapped by remap_srg.py. Some entries will need manual fixing.
- Mod was built with Architectury (Fabric-first). Fabric/Yarn leftovers may exist in naming.

## Companion mods for 1.21.1 (checked by web search; NOT tested in-game)
| Original dependency | 1.21.1 situation |
|---|---|
| Continuity | NeoForge 1.21.1 build exists on CurseForge (3.0.0+1.21.neoforge). A separate native port "Continuity: NeoForged" lists 3.0.1+1.21.1, but its page says connected textures are not yet visually confirmed. |
| EMF / ETF / Polytone | A modpack compatibility table lists them as available for NeoForge 1.21.1. Second-hand; verify on each mod's page. |
| Puzzle | Modrinth lists it for Fabric/Quilt only. On NeoForge it needs Sinytra Connector + Forgified Fabric API (the original 1.20.1 pack also used Connector). |
| ForgeSkyboxes | Replacement: "NeoforgeSkyboxes" (unofficial NeoForge 1.21.1 port of Nuit) on CurseForge. Nuit's own official NeoForge builds start at 1.21.5. |
| BetterGrassify | Old NeoForge builds exist for 1.21.1 (e.g. 1.3.0+neo.1.21.1, needs Forgified Fabric API). Its page now recommends Sinytra Connector with the Fabric builds. |
| Iris (shaders) | Available for NeoForge 1.21.1 per a modpack table. |
| Shader pack "Conquest of the Sun" | Chocapic13-style `#version 120` GLSL pack; should load in Iris on 1.21.1 unchanged. Not tested. |
Sinytra Connector is the common thread: Puzzle, BetterGrassify and possibly Fabric API-dependent code all rely on it.
Check the current Connector release for 1.21.1 before committing to that route.

## Suggested order
1. Run setup_resources.sh and remap_srg.py (guide steps 4-5).
2. Delete/disable client-only classes to get a minimal registry-only build compiling.
3. Port Forge API files, then Block/Item/BlockEntity classes, then networking, then client rendering.
4. Re-target mixins one at a time.
5. Launch on a PC with NeoForge 21.1.x, fix crashes, then add companion mods.
