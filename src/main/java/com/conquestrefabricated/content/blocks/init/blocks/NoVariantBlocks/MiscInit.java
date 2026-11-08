package com.conquestrefabricated.content.blocks.init.blocks.NoVariantBlocks;

import com.conquestrefabricated.content.blocks.BlockVoxelShapes;
import com.conquestrefabricated.content.blocks.block.Cube;
import com.conquestrefabricated.content.blocks.block.Fence;
import com.conquestrefabricated.content.blocks.block.FenceGate;
import com.conquestrefabricated.content.blocks.block.Pillar;
import com.conquestrefabricated.content.blocks.block.PillarSmall;
import com.conquestrefabricated.content.blocks.block.Stairs;
import com.conquestrefabricated.content.blocks.block.Trapdoor;
import com.conquestrefabricated.content.blocks.block.WallNew;
import com.conquestrefabricated.content.blocks.block.damage.DirectionalModelDamage;
import com.conquestrefabricated.content.blocks.block.decor.Chains;
import com.conquestrefabricated.content.blocks.block.decor.ClothesHangerCorner;
import com.conquestrefabricated.content.blocks.block.decor.DoubleHorizontalDirectional;
import com.conquestrefabricated.content.blocks.block.decor.EnchantmentTable;
import com.conquestrefabricated.content.blocks.block.decor.Ladder;
import com.conquestrefabricated.content.blocks.block.decor.ModelBlock;
import com.conquestrefabricated.content.blocks.block.decor.ParallelConnecting;
import com.conquestrefabricated.content.blocks.block.decor.Rail;
import com.conquestrefabricated.content.blocks.block.decor.Railings;
import com.conquestrefabricated.content.blocks.block.decor.RusticFence;
import com.conquestrefabricated.content.blocks.block.decor.Shutters;
import com.conquestrefabricated.content.blocks.block.decor.ShuttersLarge;
import com.conquestrefabricated.content.blocks.block.directional.AxisDirectional;
import com.conquestrefabricated.content.blocks.block.directional.Directional;
import com.conquestrefabricated.content.blocks.block.directional.Half;
import com.conquestrefabricated.content.blocks.block.directional.HorizontalDirectional;
import com.conquestrefabricated.content.blocks.block.glass.Glass;
import com.conquestrefabricated.content.blocks.block.glass.GlassTranslucent;
import com.conquestrefabricated.content.blocks.block.glass.Pane;
import com.conquestrefabricated.content.blocks.block.glass.PaneInvisible;
import com.conquestrefabricated.content.blocks.block.glass.PaneOld;
import com.conquestrefabricated.content.blocks.block.glass.SlabInvisible;
import com.conquestrefabricated.content.blocks.block.glass.SlabTranslucent;
import com.conquestrefabricated.content.blocks.block.glass.StairsInvisible;
import com.conquestrefabricated.content.blocks.block.glass.StairsTranslucent;
import com.conquestrefabricated.content.blocks.block.glass.VerticalCornerTranslucent;
import com.conquestrefabricated.content.blocks.block.glass.VerticalQuarterTranslucent;
import com.conquestrefabricated.content.blocks.block.glass.VerticalSlabInvisible;
import com.conquestrefabricated.content.blocks.block.glass.VerticalSlabTranslucent;
import com.conquestrefabricated.content.blocks.block.tech.Dispenser;
import com.conquestrefabricated.content.blocks.block.tech.Dropper;
import com.conquestrefabricated.content.blocks.group.ModGroups;
import com.conquestrefabricated.core.block.builder.VanillaProps;
import com.conquestrefabricated.core.block.factory.TypeList;
import com.conquestrefabricated.core.util.RenderLayer;
import net.minecraft.world.level.block.Blocks;

public class MiscInit {
   public MiscInit() {
   }

   public static void init() {
      VanillaProps.planks().name("reinforced_wooden_trapdoor").manual().register(TypeList.of(Trapdoor.class));
      VanillaProps.planks().name("simple_wooden_trapdoor").manual().register(TypeList.of(Trapdoor.class));
      VanillaProps.planks().group(ModGroups.DECORATIONS).name("wooden_garderobe").manual().render(RenderLayer.CUTOUT).register(TypeList.of(Trapdoor.class));
      VanillaProps.metal()
         .name("metal_grate")
         .manual()
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.trapdoorShapes)
         .register(TypeList.of(Directional.Waterlogged.class));
      VanillaProps.metal()
         .name("iron_grate")
         .manual()
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.trapdoorShapes)
         .register(TypeList.of(Directional.Waterlogged.class));
      VanillaProps.metal().name("metal_dispenser").manual().register(TypeList.of(Dispenser.class));
      VanillaProps.metal().name("metal_dropper").manual().register(TypeList.of(Dropper.class));
      VanillaProps.metal().name("sandstone_dispenser").manual().register(TypeList.of(Dispenser.class));
      VanillaProps.metal().name("sandstone_dropper").manual().register(TypeList.of(Dropper.class));
      VanillaProps.metal().name("mesoamerican_dispenser").manual().register(TypeList.of(Dispenser.class));
      VanillaProps.stone()
         .name("small_marble_and_sandstone_plinth")
         .manual()
         .with("hitBox", BlockVoxelShapes.cubePartialShape)
         .register(TypeList.of(HorizontalDirectional.Toggle2.class));
      VanillaProps.planks().name("acacia_wood_shutters").manual().render(RenderLayer.CUTOUT).register(TypeList.of(ShuttersLarge.class));
      VanillaProps.planks().name("large_acacia_wood_shutters").manual().render(RenderLayer.CUTOUT).register(TypeList.of(ShuttersLarge.class));
      VanillaProps.planks().name("large_birch_wood_shutters").manual().render(RenderLayer.CUTOUT).register(TypeList.of(ShuttersLarge.class));
      VanillaProps.planks().name("large_oak_wood_shutters").manual().render(RenderLayer.CUTOUT).register(TypeList.of(ShuttersLarge.class));
      VanillaProps.planks().name("large_white_painted_shutters").manual().render(RenderLayer.CUTOUT).register(TypeList.of(ShuttersLarge.class));
      VanillaProps.planks()
         .name("overgrown_white_painted_wood_trellis")
         .manual()
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.trapdoorShapes)
         .register(TypeList.of(Directional.class));
      VanillaProps.planks().name("birch_wood_shutters").manual().render(RenderLayer.CUTOUT).register(TypeList.of(ShuttersLarge.class));
      VanillaProps.planks()
         .name("birch_wood_slotted_shutters")
         .manual()
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.trapdoorShapes)
         .register(TypeList.of(Directional.class));
      VanillaProps.planks().name("oak_wood_shutters").manual().render(RenderLayer.CUTOUT).register(TypeList.of(ShuttersLarge.class));
      VanillaProps.planks()
         .name("oak_wood_slotted_shutters")
         .manual()
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.trapdoorShapes)
         .register(TypeList.of(Directional.class));
      VanillaProps.planks().name("split_birch_wood_shutters").manual().register(TypeList.of(Shutters.class));
      VanillaProps.planks()
         .name("split_birch_wood_shutters")
         .family("split_birch_wood_shutters")
         .texture("block/2_advanced_refined/7_wood/split_birch_wood_shutters")
         .register(TypeList.of(Pane.class));
      VanillaProps.planks().name("split_acacia_wood_shutters").manual().register(TypeList.of(Shutters.class));
      VanillaProps.planks()
         .name("split_acacia_wood_shutters")
         .family("split_acacia_wood_shutters")
         .texture("block/2_advanced_refined/7_wood/split_acacia_wood_shutters")
         .register(TypeList.of(Pane.class));
      VanillaProps.planks().name("split_oak_wood_shutters").manual().register(TypeList.of(Shutters.class));
      VanillaProps.planks()
         .name("split_oak_wood_shutters")
         .family("split_oak_wood_shutters")
         .texture("block/2_advanced_refined/7_wood/split_oak_wood_shutters")
         .register(TypeList.of(Pane.class));
      VanillaProps.planks().name("birch_wood_panel_shutters").manual().render(RenderLayer.CUTOUT).register(TypeList.of(ShuttersLarge.class));
      VanillaProps.planks().name("split_birch_wood_panel_shutters").manual().register(TypeList.of(Shutters.class));
      VanillaProps.planks().name("oak_wood_panel_shutters").manual().render(RenderLayer.CUTOUT).register(TypeList.of(ShuttersLarge.class));
      VanillaProps.planks().name("split_oak_wood_panel_shutters").manual().register(TypeList.of(Shutters.class));
      VanillaProps.planks().name("spruce_wood_panel_shutters").manual().render(RenderLayer.CUTOUT).register(TypeList.of(ShuttersLarge.class));
      VanillaProps.planks().name("split_spruce_wood_panel_shutters").manual().register(TypeList.of(Shutters.class));
      VanillaProps.planks().name("split_white_painted_wood_shutters").manual().register(TypeList.of(Shutters.class));
      VanillaProps.planks()
         .name("split_white_painted_wood_shutters")
         .family("split_white_painted_wood_shutters")
         .texture("block/2_advanced_refined/7_wood/1_painted/split_white_painted_wood_shutters")
         .register(TypeList.of(Pane.class));
      VanillaProps.planks().name("simple_oak_wood_shutters").manual().register(TypeList.of(Shutters.class));
      VanillaProps.planks().name("white_painted_shutters").manual().render(RenderLayer.CUTOUT).register(TypeList.of(ShuttersLarge.class));
      VanillaProps.planks().name("green_painted_shutters").manual().render(RenderLayer.CUTOUT).register(TypeList.of(ShuttersLarge.class));
      VanillaProps.planks().name("blue_painted_shutters").manual().render(RenderLayer.CUTOUT).register(TypeList.of(ShuttersLarge.class));
      VanillaProps.planks().name("yellow_painted_shutters").manual().render(RenderLayer.CUTOUT).register(TypeList.of(ShuttersLarge.class));
      VanillaProps.planks().name("orange_painted_shutters").manual().render(RenderLayer.CUTOUT).register(TypeList.of(ShuttersLarge.class));
      VanillaProps.planks().name("red_painted_shutters").manual().render(RenderLayer.CUTOUT).register(TypeList.of(ShuttersLarge.class));
      VanillaProps.planks().name("thin_white_picket_fence").manual().register(TypeList.of(RusticFence.class));
      VanillaProps.metal().name("metal_railings").manual().register(TypeList.of(Railings.class, Railings.Corner.class, Railings.Diagonal.class));
      VanillaProps.metal().name("metal").manual().parent(Blocks.IRON_BLOCK.defaultBlockState()).render(RenderLayer.CUTOUT).register(TypeList.of(Stairs.class));
      VanillaProps.planks().name("oak_wood_railing").manual().register(TypeList.of(Railings.class, Railings.Corner.class, Railings.Diagonal.class));
      VanillaProps.planks().name("oak_plank_step").manual().parent(Blocks.OAK_PLANKS.defaultBlockState()).render(RenderLayer.CUTOUT).register(TypeList.of(Stairs.class));
      VanillaProps.planks()
         .name("rustic_spruce_plank_step")
         .manual()
         .parent(Blocks.OAK_PLANKS.defaultBlockState())
         .render(RenderLayer.CUTOUT)
         .register(TypeList.of(Stairs.class));
      VanillaProps.planks().name("spruce_wood_railing").manual().register(TypeList.of(Railings.class, Railings.Corner.class, Railings.Diagonal.class));
      VanillaProps.planks()
         .name("dark_spruce_plank_step")
         .manual()
         .parent(Blocks.OAK_PLANKS.defaultBlockState())
         .render(RenderLayer.CUTOUT)
         .register(TypeList.of(Stairs.class));
      VanillaProps.planks().name("spruce_beam_railing").family("horizontal_spruce_wood_railing").manual().register(TypeList.of(Railings.Diagonal.class));
      VanillaProps.planks().name("palm_wood_railing").manual().register(TypeList.of(Railings.class, Railings.Corner.class, Railings.Diagonal.class));
      VanillaProps.planks().name("palm_plank_step").manual().parent(Blocks.OAK_PLANKS.defaultBlockState()).render(RenderLayer.CUTOUT).register(TypeList.of(Stairs.class));
      VanillaProps.planks().name("asian_acacia_wood_railing").manual().register(TypeList.of(Railings.class, Railings.Corner.class, Railings.Diagonal.class));
      VanillaProps.planks()
         .name("red_acacia_plank_step")
         .manual()
         .parent(Blocks.OAK_PLANKS.defaultBlockState())
         .render(RenderLayer.CUTOUT)
         .register(TypeList.of(Stairs.class));
      VanillaProps.planks()
         .name("fancy_wood_railing")
         .manual()
         .render(RenderLayer.CUTOUT)
         .register(TypeList.of(Railings.class, Railings.Corner.class, Railings.Diagonal.class));
      VanillaProps.planks().name("fancy_wood_step").manual().parent(Blocks.OAK_PLANKS.defaultBlockState()).render(RenderLayer.CUTOUT).register(TypeList.of(Stairs.class));
      VanillaProps.planks()
         .name("ash_wood_railing")
         .manual()
         .render(RenderLayer.CUTOUT)
         .register(TypeList.of(Railings.class, Railings.Corner.class, Railings.Diagonal.class));
      VanillaProps.planks().name("ash_plank_step").manual().parent(Blocks.OAK_PLANKS.defaultBlockState()).render(RenderLayer.CUTOUT).register(TypeList.of(Stairs.class));
      VanillaProps.planks()
         .name("oak_log_railing")
         .manual()
         .render(RenderLayer.CUTOUT)
         .register(TypeList.of(Railings.class, Railings.Corner.class, Railings.Diagonal.class));
      VanillaProps.planks().name("oak_log_step").manual().parent(Blocks.OAK_PLANKS.defaultBlockState()).render(RenderLayer.CUTOUT).register(TypeList.of(Stairs.class));
      VanillaProps.planks().name("birch_wood_railing").manual().register(TypeList.of(Railings.class, Railings.Corner.class, Railings.Diagonal.class));
      VanillaProps.planks().name("birch_log_step").manual().parent(Blocks.OAK_PLANKS.defaultBlockState()).render(RenderLayer.CUTOUT).register(TypeList.of(Stairs.class));
      VanillaProps.planks().name("spruce_log_step").manual().parent(Blocks.OAK_PLANKS.defaultBlockState()).render(RenderLayer.CUTOUT).register(TypeList.of(Stairs.class));
      VanillaProps.planks()
         .name("asian_acacia_wood")
         .manual()
         .render(RenderLayer.CUTOUT)
         .register(TypeList.of(Fence.class, FenceGate.class, WallNew.class, Pillar.class));
      VanillaProps.planks()
         .name("birch_wood_railing")
         .manual()
         .render(RenderLayer.CUTOUT)
         .register(TypeList.of(Fence.class, FenceGate.class, PillarSmall.class));
      VanillaProps.planks()
         .name("spruce_wood_railing")
         .manual()
         .render(RenderLayer.CUTOUT)
         .register(TypeList.of(Fence.class, FenceGate.class, PillarSmall.class));
      VanillaProps.planks().name("rough_palm_board").manual().render(RenderLayer.CUTOUT).register(TypeList.of(Fence.class));
      VanillaProps.stone()
         .group(ModGroups.STONE)
         .name("pile_of_coal")
         .manual()
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.coverShape)
         .register(TypeList.of(ModelBlock.OffsetXYZ.class));
      VanillaProps.stone()
         .group(ModGroups.DECORATIONS)
         .name("elostirion_palantir")
         .manual()
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.cubePartialShape)
         .register(TypeList.of(ModelBlock.OffsetXYZ.class));
      VanillaProps.cloth()
         .name("wall_of_rope")
         .family("rope_wrapped_around_log")
         .texture("block/2_advanced_refined/7_wood/rope_wrapped_around_log")
         .register(TypeList.of(PaneOld.class));
      VanillaProps.cloth().group(ModGroups.CLOTH_AND_FIBERS).name("rigging").manual().blocking(false).register(TypeList.of(PaneOld.class));
      VanillaProps.cloth().group(ModGroups.CLOTH_AND_FIBERS).name("rope_on_the_ground").manual().blocking(false).register(TypeList.of(Rail.class));
      VanillaProps.cloth().group(ModGroups.CLOTH_AND_FIBERS).name("rope_ladder").manual().register(TypeList.of(Ladder.class));
      VanillaProps.cloth().group(ModGroups.CLOTH_AND_FIBERS).name("climbing_rope").manual().register(TypeList.of(Ladder.class));
      VanillaProps.metal().name("chains_on_the_ground").manual().blocking(false).register(TypeList.of(Rail.class));
      VanillaProps.metal()
         .name("metal_hook")
         .manual()
         .blocking(false)
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.cubePartialShape)
         .register(TypeList.of(ModelBlock.class));
      VanillaProps.cloth()
         .group(ModGroups.CLOTH_AND_FIBERS)
         .name("hook_with_rope")
         .manual()
         .blocking(false)
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.cubePartialShape)
         .register(TypeList.of(ModelBlock.class));
      VanillaProps.metal()
         .name("cage_bars")
         .manual()
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.trapdoorShapes)
         .register(TypeList.of(Directional.Waterlogged.class));
      VanillaProps.metal().name("iron_cage_bars").texture("block/3_decoration/2_metal/iron_cage_bars").register(TypeList.of(Glass.class, Pane.class));
      VanillaProps.cloth()
         .group(ModGroups.CLOTH_AND_FIBERS)
         .name("noose")
         .manual()
         .render(RenderLayer.CUTOUT)
         .blocking(false)
         .with("hitBox", BlockVoxelShapes.paneShape)
         .register(TypeList.of(HorizontalDirectional.class));
      VanillaProps.metal()
         .name("weather_vane")
         .manual()
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.axisShapes)
         .register(TypeList.of(AxisDirectional.class));
      VanillaProps.cloth()
         .group(ModGroups.CLOTH_AND_FIBERS)
         .name("hay_on_the_ground")
         .manual()
         .blocking(false)
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.coverShape)
         .register(TypeList.of(ModelBlock.OffsetXYZ.class));
      VanillaProps.cloth()
         .group(ModGroups.CLOTH_AND_FIBERS)
         .name("scarecrow_head")
         .manual()
         .blocking(false)
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.cubePartialShape)
         .register(TypeList.of(ModelBlock.class));
      VanillaProps.cloth()
         .group(ModGroups.CLOTH_AND_FIBERS)
         .name("leather_boots")
         .manual()
         .blocking(false)
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.cubePartialShape)
         .register(TypeList.of(HorizontalDirectional.Waterlogged.OffsetXYZ.Toggle4.class));
      VanillaProps.metal()
         .group(ModGroups.DECORATIONS)
         .name("silver_coins_on_the_ground")
         .manual()
         .blocking(false)
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.coverShape)
         .register(TypeList.of(ModelBlock.OffsetXYZ.class));
      VanillaProps.metal()
         .group(ModGroups.DECORATIONS)
         .name("gold_coins_on_the_ground")
         .manual()
         .blocking(false)
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.coverShape)
         .register(TypeList.of(ModelBlock.OffsetXYZ.class));
      VanillaProps.cloth()
         .group(ModGroups.DECORATIONS)
         .name("checker_board")
         .manual()
         .blocking(false)
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.coverShape)
         .register(TypeList.of(ModelBlock.OffsetXYZ.class));
      VanillaProps.wood()
         .group(ModGroups.DECORATIONS)
         .name("gwent_board")
         .manual()
         .blocking(false)
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.coverShape)
         .register(TypeList.of(HorizontalDirectional.OffsetXYZ.Toggle2.class));
      VanillaProps.cloth()
         .group(ModGroups.DECORATIONS)
         .name("playing_cards")
         .manual()
         .blocking(false)
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.coverShape)
         .register(TypeList.of(ModelBlock.OffsetXYZ.class));
      VanillaProps.wood()
         .group(ModGroups.DECORATIONS)
         .name("dice_game")
         .manual()
         .blocking(false)
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.coverShape)
         .register(TypeList.of(HorizontalDirectional.OffsetXYZ.Toggle2.class));
      VanillaProps.metal()
         .name("golden_bell")
         .manual()
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.cubePartialShape)
         .register(TypeList.of(ModelBlock.class));
      VanillaProps.metal().group(ModGroups.DECORATIONS).name("large_mirror_2").texture("minecraft:block/diamond_block").register(TypeList.of(Cube.class));
      VanillaProps.stone()
         .group(ModGroups.DECORATIONS)
         .name("stone_urn")
         .with("hitBox", BlockVoxelShapes.urnShape)
         .solid(false)
         .register(TypeList.of(ModelBlock.class));
      VanillaProps.stone()
         .group(ModGroups.DECORATIONS)
         .name("marble_urn")
         .with("hitBox", BlockVoxelShapes.urnShape)
         .solid(false)
         .register(TypeList.of(ModelBlock.class));
      VanillaProps.metal()
         .name("brass_door_knob")
         .manual()
         .blocking(false)
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.knobShapes)
         .register(TypeList.of(Directional.class));
      VanillaProps.metal()
         .name("iron_door_knob")
         .manual()
         .blocking(false)
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.knobShapes)
         .register(TypeList.of(Directional.class));
      VanillaProps.metal().name("barbed_wire").manual().blocking(false).register(TypeList.of(DirectionalModelDamage.class));
      VanillaProps.metal()
         .name("golden_door_knocker")
         .manual()
         .blocking(false)
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.doorKnocker)
         .register(TypeList.of(HorizontalDirectional.class));
      VanillaProps.metal()
         .name("iron_door_knocker")
         .manual()
         .blocking(false)
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.doorKnocker)
         .register(TypeList.of(HorizontalDirectional.class));
      VanillaProps.metal()
         .name("cannonball")
         .manual()
         .blocking(false)
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.cubeSmallPartialShape)
         .register(TypeList.of(ModelBlock.OffsetXYZ.class));
      VanillaProps.stone()
         .group(ModGroups.DECORATIONS)
         .name("hells_eye")
         .manual()
         .blocking(false)
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.cubeSmallPartialShape)
         .register(TypeList.of(ModelBlock.OffsetXYZ.class));
      VanillaProps.planks()
         .group(ModGroups.DECORATIONS)
         .name("wood_eisel")
         .manual()
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.cubePartialShape)
         .register(TypeList.of(ModelBlock.class));
      VanillaProps.planks()
         .group(ModGroups.DECORATIONS)
         .name("pendulum_clock")
         .manual()
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.verticalSlabShapes)
         .register(TypeList.of(HorizontalDirectional.OffsetXYZ.class));
      VanillaProps.planks()
         .group(ModGroups.DECORATIONS)
         .name("clavicytherium")
         .manual()
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.verticalSlabShapes)
         .register(TypeList.of(HorizontalDirectional.OffsetXYZ.class));
      VanillaProps.planks()
         .group(ModGroups.DECORATIONS)
         .name("sign_post")
         .manual()
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.pillarShape)
         .register(TypeList.of(ModelBlock.class));
      VanillaProps.planks()
         .name("wooden_supports")
         .manual()
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.slabShape)
         .register(TypeList.of(Half.DirectionalWaterlogged.class));
      VanillaProps.planks()
         .group(ModGroups.DECORATIONS)
         .name("mirror")
         .manual()
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.mirrorShape)
         .register(TypeList.of(HorizontalDirectional.OffsetXYZ.class));
      VanillaProps.planks()
         .group(ModGroups.DECORATIONS)
         .name("pinata")
         .manual()
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.cubePartialShape)
         .register(TypeList.of(HorizontalDirectional.class));
      VanillaProps.planks()
         .group(ModGroups.DECORATIONS)
         .name("chess_board")
         .manual()
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.coverShape)
         .register(TypeList.of(HorizontalDirectional.OffsetXYZ.class));
      VanillaProps.planks()
         .group(ModGroups.DECORATIONS)
         .name("clothes_hanger")
         .manual()
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.clothesHangarShape)
         .register(TypeList.of(HorizontalDirectional.OffsetXZ.Toggle5.class));
      VanillaProps.planks()
         .family("clothes_hanger")
         .name("clothes_hanger_corner")
         .manual()
         .render(RenderLayer.CUTOUT)
         .register(TypeList.of(ClothesHangerCorner.class));
      VanillaProps.planks()
         .group(ModGroups.DECORATIONS)
         .name("antique_wooden_shelf")
         .manual()
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.shelfShape)
         .register(TypeList.of(ParallelConnecting.Unique.class));
      VanillaProps.planks()
         .group(ModGroups.DECORATIONS)
         .name("towel_rack")
         .manual()
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.verticalSlabShapes)
         .register(TypeList.of(HorizontalDirectional.Toggle2.class));
      VanillaProps.planks().group(ModGroups.FURNITURE).name("floating_book").manual().register(TypeList.of(EnchantmentTable.class));
      VanillaProps.planks()
         .group(ModGroups.STORAGE)
         .name("empty_barrel_stand")
         .manual()
         .solid(false)
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.slabBottomShape)
         .register(TypeList.of(HorizontalDirectional.Toggle2.class));
      VanillaProps.stone()
         .group(ModGroups.UTILITY)
         .name("green_screen")
         .light(state -> 15)
         .texture("block/5_miscellaneous/green_screen")
         .register(TypeList.of(Cube.class));
      VanillaProps.stone()
         .group(ModGroups.UTILITY)
         .name("red_screen")
         .light(state -> 15)
         .texture("block/5_miscellaneous/red_screen")
         .register(TypeList.of(Cube.class));
      VanillaProps.stone()
         .group(ModGroups.UTILITY)
         .name("blue_screen")
         .light(state -> 15)
         .texture("block/5_miscellaneous/blue_screen")
         .register(TypeList.of(Cube.class));
      VanillaProps.stone()
         .group(ModGroups.UTILITY)
         .name("white_screen")
         .light(state -> 15)
         .texture("block/5_miscellaneous/white_screen")
         .register(TypeList.of(Cube.class));
      VanillaProps.stone()
         .group(ModGroups.UTILITY)
         .name("light_gray_screen")
         .light(state -> 15)
         .texture("block/5_miscellaneous/light_gray_screen")
         .register(TypeList.of(Cube.class));
      VanillaProps.stone()
         .group(ModGroups.UTILITY)
         .name("approved_planning_sign")
         .texture("block/5_miscellaneous/planning_approved")
         .register(TypeList.of(Cube.class));
      VanillaProps.stone()
         .group(ModGroups.UTILITY)
         .name("upper_class_planning_sign")
         .texture("block/5_miscellaneous/planning_upperclass")
         .register(TypeList.of(Cube.class));
      VanillaProps.stone()
         .group(ModGroups.UTILITY)
         .name("middle_class_planning_sign")
         .texture("block/5_miscellaneous/planning_middleclass")
         .register(TypeList.of(Cube.class));
      VanillaProps.stone()
         .group(ModGroups.UTILITY)
         .name("lower_class_planning_sign")
         .texture("block/5_miscellaneous/planning_lowerclass")
         .register(TypeList.of(Cube.class));
      VanillaProps.stone().group(ModGroups.UTILITY).name("name_planning_sign").texture("block/5_miscellaneous/planning_name").register(TypeList.of(Cube.class));
      VanillaProps.stone()
         .group(ModGroups.UTILITY)
         .name("comment_planning_sign")
         .texture("block/5_miscellaneous/planning_comment")
         .register(TypeList.of(Cube.class));
      VanillaProps.stone()
         .group(ModGroups.UTILITY)
         .name("other_planning_sign")
         .texture("block/5_miscellaneous/planning_other")
         .register(TypeList.of(Cube.class));
      VanillaProps.stone().group(ModGroups.UTILITY).name("planning_sign").texture("block/5_miscellaneous/planning_sign").register(TypeList.of(Cube.class));
      VanillaProps.stone()
         .group(ModGroups.UTILITY)
         .name("special_planning_sign")
         .texture("block/5_miscellaneous/planning_special")
         .register(TypeList.of(Cube.class));
      VanillaProps.stone()
         .group(ModGroups.UTILITY)
         .name("waiting_approval_planning_sign")
         .texture("block/5_miscellaneous/planning_waitingapproval")
         .register(TypeList.of(Cube.class));
      VanillaProps.stone()
         .group(ModGroups.UTILITY)
         .name("not_approved_planning_sign")
         .texture("block/5_miscellaneous/planning_notapproved")
         .register(TypeList.of(Cube.class));
      VanillaProps.stone()
         .group(ModGroups.UTILITY)
         .name("update_planning_sign")
         .texture("block/5_miscellaneous/planning_update")
         .register(TypeList.of(Cube.class));
      VanillaProps.glass()
         .group(ModGroups.STONE)
         .name("warped_red_calcite_crystal")
         .texture("block/8_topography/2_ores_crystals/warped_red_calcite_crystal")
         .register(TypeList.of(GlassTranslucent.class));
      VanillaProps.glass()
         .group(ModGroups.STONE)
         .name("warped_blue_calcite_crystal")
         .texture("block/8_topography/2_ores_crystals/warped_blue_calcite_crystal")
         .register(TypeList.of(GlassTranslucent.class));
      VanillaProps.glass()
         .group(ModGroups.STONE)
         .name("warped_green_calcite_crystal")
         .texture("block/8_topography/2_ores_crystals/warped_green_calcite_crystal")
         .register(TypeList.of(GlassTranslucent.class));
      VanillaProps.glass()
         .group(ModGroups.STONE)
         .name("warped_light_green_calcite_crystal")
         .texture("block/8_topography/2_ores_crystals/warped_light_green_calcite_crystal")
         .register(TypeList.of(GlassTranslucent.class));
      VanillaProps.glass()
         .group(ModGroups.STONE)
         .name("warped_purple_calcite_crystal")
         .texture("block/8_topography/2_ores_crystals/warped_purple_calcite_crystal")
         .register(TypeList.of(GlassTranslucent.class));
      VanillaProps.ice()
         .group(ModGroups.WATER_AND_AIR)
         .name("ice")
         .texture("minecraft:block/ice")
         .parent(Blocks.ICE.defaultBlockState())
         .register(
            TypeList.of(
               SlabTranslucent.class, StairsTranslucent.class, VerticalCornerTranslucent.class, VerticalQuarterTranslucent.class, VerticalSlabTranslucent.class
            )
         );
      VanillaProps.glass()
         .group(ModGroups.WATER_AND_AIR)
         .name("ice_bricks")
         .texture("block/8_topography/7_ice_snow/ice_bricks")
         .register(
            TypeList.of(
               GlassTranslucent.class,
               SlabTranslucent.class,
               StairsTranslucent.class,
               VerticalCornerTranslucent.class,
               VerticalQuarterTranslucent.class,
               VerticalSlabTranslucent.class
            )
         );
      VanillaProps.glass()
         .group(ModGroups.WATER_AND_AIR)
         .name("bright_glacier_ice")
         .texture("block/8_topography/7_ice_snow/bright_glacier_ice")
         .register(
            TypeList.of(
               GlassTranslucent.class,
               SlabTranslucent.class,
               StairsTranslucent.class,
               VerticalCornerTranslucent.class,
               VerticalQuarterTranslucent.class,
               VerticalSlabTranslucent.class
            )
         );
      VanillaProps.stone()
         .group(ModGroups.WATER_AND_AIR)
         .name("icicles")
         .manual()
         .blocking(false)
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.cubePartialShape)
         .register(TypeList.of(ModelBlock.class));
      VanillaProps.stone()
         .group(ModGroups.WATER_AND_AIR)
         .name("warped_icicles")
         .manual()
         .blocking(false)
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.cubePartialShape)
         .register(TypeList.of(ModelBlock.class));
      VanillaProps.stone().group(ModGroups.UTILITY).name("invisible_block").blocking(false).solid(false).manual().register(TypeList.of(PaneInvisible.class));
      VanillaProps.stone()
         .group(ModGroups.UTILITY)
         .name("invisible_block")
         .blocking(true)
         .solid(false)
         .parent(Blocks.BARRIER.defaultBlockState())
         .render(RenderLayer.CUTOUT)
         .manual()
         .register(TypeList.of(StairsInvisible.class));
      VanillaProps.stone()
         .group(ModGroups.UTILITY)
         .name("invisible_block")
         .blocking(false)
         .solid(false)
         .parent(Blocks.BARRIER.defaultBlockState())
         .render(RenderLayer.CUTOUT)
         .manual()
         .register(TypeList.of(SlabInvisible.class));
      VanillaProps.stone()
         .group(ModGroups.UTILITY)
         .name("invisible_block")
         .blocking(false)
         .solid(false)
         .parent(Blocks.BARRIER.defaultBlockState())
         .render(RenderLayer.CUTOUT)
         .manual()
         .register(TypeList.of(VerticalSlabInvisible.class));
      VanillaProps.planks()
         .name("iron_and_wood_gear_box")
         .manual()
         .render(RenderLayer.SOLID)
         .with("hitBox", BlockVoxelShapes.cubeFullShape)
         .register(TypeList.of(Directional.Toggle2.class));
      VanillaProps.metal()
         .name("metal_gear_box")
         .manual()
         .render(RenderLayer.SOLID)
         .with("hitBox", BlockVoxelShapes.cubeFullShape)
         .register(TypeList.of(Directional.Toggle2.class));
      VanillaProps.metal()
         .name("circuit_boards_and_screen")
         .manual()
         .render(RenderLayer.SOLID)
         .with("hitBox", BlockVoxelShapes.cubeFullShape)
         .register(TypeList.of(Directional.Toggle2.class));
      VanillaProps.planks()
         .group(ModGroups.DECORATIONS)
         .name("wall_mounted_moose_antler")
         .manual()
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.verticalSlabShapes)
         .register(TypeList.of(HorizontalDirectional.OffsetXZ.class));
      VanillaProps.planks()
         .group(ModGroups.DECORATIONS)
         .name("wall_mounted_deer_antler")
         .manual()
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.verticalSlabShapes)
         .register(TypeList.of(HorizontalDirectional.OffsetXZ.class));
      VanillaProps.planks()
         .group(ModGroups.DECORATIONS)
         .name("grandfather_clock")
         .manual()
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.cubeFullShape)
         .register(TypeList.of(DoubleHorizontalDirectional.class));
      VanillaProps.planks()
         .group(ModGroups.DECORATIONS)
         .name("tall_grandfather_clock")
         .manual()
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.cubeFullShape)
         .register(TypeList.of(DoubleHorizontalDirectional.class));
      VanillaProps.planks()
         .group(ModGroups.DECORATIONS)
         .name("scarecrow")
         .manual()
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.spinningWheelShape)
         .register(TypeList.of(DoubleHorizontalDirectional.class));
      VanillaProps.cloth().group(ModGroups.CLOTH_AND_FIBERS).name("silver_rope_ladder").manual().register(TypeList.of(Ladder.class));
      VanillaProps.planks()
         .group(ModGroups.DECORATIONS)
         .name("scroll_holder")
         .manual()
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.cubeMediumLargePartialShape)
         .register(TypeList.of(HorizontalDirectional.class));
      VanillaProps.planks()
         .group(ModGroups.TOOL_BLOCKS)
         .name("scroll_stand")
         .manual()
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.slabBottomShape)
         .register(TypeList.of(HorizontalDirectional.OffsetXYZ.Toggle6.class));
      VanillaProps.cloth()
         .group(ModGroups.TOOL_BLOCKS)
         .name("hanging_scroll")
         .manual()
         .blocking(false)
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.verticalSlabShapes)
         .register(TypeList.of(HorizontalDirectional.class));
      VanillaProps.cloth()
         .group(ModGroups.TOOL_BLOCKS)
         .name("sealed_scroll")
         .manual()
         .blocking(false)
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.coverShape)
         .register(TypeList.of(HorizontalDirectional.OffsetXYZ.Toggle3.class));
      VanillaProps.cloth()
         .group(ModGroups.TOOL_BLOCKS)
         .name("assorted_scrolls")
         .manual()
         .blocking(false)
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.coverShape)
         .register(TypeList.of(HorizontalDirectional.OffsetXYZ.Toggle5.class));
      VanillaProps.cloth()
         .group(ModGroups.TOOL_BLOCKS)
         .name("open_scroll")
         .manual()
         .blocking(false)
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.coverShape)
         .register(TypeList.of(HorizontalDirectional.OffsetXYZ.Toggle3.class));
      VanillaProps.cloth()
         .group(ModGroups.TOOL_BLOCKS)
         .name("scroll_case")
         .manual()
         .blocking(false)
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.coverShape)
         .register(TypeList.of(HorizontalDirectional.OffsetXYZ.class));
      VanillaProps.cloth()
         .group(ModGroups.CLOTH_AND_FIBERS)
         .name("hay_bundle")
         .manual()
         .blocking(true)
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.cubePartialShape)
         .register(TypeList.of(HorizontalDirectional.OffsetXYZ.Toggle4.class));
      VanillaProps.planks()
         .group(ModGroups.TOOL_BLOCKS)
         .name("wax_tablet")
         .manual()
         .blocking(false)
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.slabShape)
         .register(TypeList.of(HorizontalDirectional.OffsetXYZ.Toggle6.class));
      VanillaProps.planks()
         .group(ModGroups.DECORATIONS)
         .name("tailor_mirror")
         .manual()
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.cubePartialShape)
         .register(TypeList.of(HorizontalDirectional.OffsetXYZ.class));
      VanillaProps.metal()
         .group(ModGroups.DECORATIONS)
         .name("fancy_gold_mirror")
         .manual()
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.verticalSlabShapes)
         .register(TypeList.of(HorizontalDirectional.class));
      VanillaProps.metal()
         .group(ModGroups.DECORATIONS)
         .name("oxidized_copper_mirror")
         .manual()
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.verticalSlabShapes)
         .register(TypeList.of(HorizontalDirectional.class));
      VanillaProps.planks()
         .group(ModGroups.CLOTH_AND_FIBERS)
         .name("rope_pulley")
         .manual()
         .with("hitBox", BlockVoxelShapes.cubePartialShape)
         .register(TypeList.of(HorizontalDirectional.OffsetXYZ.class));
      VanillaProps.chain().name("iron_chains").manual().register(TypeList.of(Chains.class));
      VanillaProps.chain().name("rusty_chains").manual().register(TypeList.of(Chains.class));
      VanillaProps.chain().name("golden_chains").manual().register(TypeList.of(Chains.class));
      VanillaProps.chain().name("small_iron_chains").manual().register(TypeList.of(Chains.class));
      VanillaProps.chain().name("small_bronze_chains").manual().register(TypeList.of(Chains.class));
      VanillaProps.chain().name("square_iron_chains").manual().register(TypeList.of(Chains.class));
      VanillaProps.chain().name("copper_chains").manual().register(TypeList.of(Chains.class));
      VanillaProps.cloth().group(ModGroups.CLOTH_AND_FIBERS).name("hanging_rope").blocking(false).manual().register(TypeList.of(Chains.class));
      VanillaProps.cloth().group(ModGroups.CLOTH_AND_FIBERS).name("silver_rope").blocking(false).manual().register(TypeList.of(Chains.class));
      VanillaProps.planks()
         .group(ModGroups.UTILITY)
         .name("coracle")
         .manual()
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.cubePartialShape)
         .blocking(false)
         .register(TypeList.of(HorizontalDirectional.OffsetXYZ.Toggle6.class));
   }
}
