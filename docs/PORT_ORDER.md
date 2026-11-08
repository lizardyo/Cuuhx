# Suggested order for fixing compile errors

Work top to bottom. Compile (`./gradlew compileJava`) after each group; do not try to fix everything at once.

## Group 1 - get a minimal build compiling
1. `RefabricatedMod.java` (the @Mod entry point): port to NeoForge (`IEventBus` constructor parameter, `@Mod("conquest")`).
2. Registries: everything that uses `ForgeRegistries` / `RegistryObject` / `DeferredRegister` -> NeoForge `DeferredRegister` / `BuiltInRegistries`.
3. `content/blocks/tileentity/TileEntityTypes.java` and `content/blocks/group/ModGroups.java` (block entity types, creative tabs).
4. Temporarily comment out client-only classes (everything under `client/`) so the server side compiles first.

## Group 2 - mechanical 1.20.5+ changes across many block classes
There are about 167 classes that extend a Block type. Typical fixes: add `codec()`, split `use()` into `useWithoutItem`/`useItemOn`,
`new ResourceLocation(a,b)` -> `ResourceLocation.fromNamespaceAndPath(a,b)`, BlockEntity `load/save` -> `loadAdditional/saveAdditional(tag, registries)`.
Fix one representative class of each kind by hand, then ask Claude Code to apply the same pattern to the rest.

## Group 3 - networking
`client/gui/config/forge/ConfigSyncPacket` and any `SimpleChannel` use -> NeoForge payload handlers (`CustomPacketPayload`).

## Group 4 - client rendering (re-enable `client/`)
Color handlers, particle providers, gui overlays, pack finders, model events. The loom/duplicate-down models are already converted.

## Group 5 - mixins
Re-target the 11 mixins in `conquest.mixins.json` one at a time; `defaultRequire` is 0 so a bad target will not crash, it will just not apply.

## Files that import net.minecraftforge.* (53 files)
- `./`: ConfigScreenEvents.java, ModBuiltinRP.java, ModEvents.java, ModGameRulesInit.java, RefabricatedMod.java
- `client/`: ModBinds.java
- `client/events/`: BlockPicker.java
- `client/gui/config/forge/`: ConfigSyncPacket.java, ConquestConfigImpl.java
- `client/gui/palette/component/`: PaletteSettings.java
- `client/gui/palette/forge/`: PaletteGuiEvents.java
- `client/gui/palette/texture/`: TextureGroupManager.java
- `client/tutorial/forge/`: TutorialRenderEvent.java
- `content/blocks/block/decor/`: Animal.java, Campfire.java, Kiln.java, Smoke.java
- `content/blocks/block/glass/`: Glass.java, VerticalCornerCutout.java, VerticalQuarterCutout.java, VerticalSlabCutout.java
- `content/blocks/block/tracery/`: GlassTracery.java, VerticalCornerTracery.java, VerticalQuarterTracery.java, VerticalSlabTracery.java
- `content/blocks/group/forge/`: ModGroupsEvent.java
- `content/blocks/init/forge/`: BlockClientInit.java, BlockFamilyInitEvent.java, BlockRegistrarEvent.java, LiliesInit.java, ParticleRegistrarEvent.java, QParticleFactory.java, ScaffoldingInit.java
- `content/blocks/particles/`: AnimalParticleFactory.java
- `content/blocks/tileentity/enchantment/`: ModdedTileEntityEnchanterRenderer.java
- `content/blocks/tileentity/forge/`: TileEntityTypesImpl.java
- `content/effects/forge/`: EffectsInit.java
- `content/entities/init/`: EntityClientInit.java, EntityCommonInit.java
- `content/entities/painting/`: EntityPainting.java
- `content/items/init/`: ItemClientInit.java
- `content/items/init/forge/`: ItemCommonInit.java
- `core/block/builder/forge/`: PropsImpl.java
- `core/block/data/forge/`: BlockDataImpl.java
- `core/client/input/forge/`: BindInit.java, Bindings.java
- `core/client/render/`: RenderLayerHelper.java
- `core/init/`: Context.java, InitClient.java, InitCommon.java, Registrar.java
- `core/item/group/manager/`: DelegateGroup.java, ItemGroupManager.java
