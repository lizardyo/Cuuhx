package com.conquestrefabricated.content.blocks.init.blocks.NoVariantBlocks;

import com.conquestrefabricated.content.blocks.BlockVoxelShapes;
import com.conquestrefabricated.content.blocks.block.Fence;
import com.conquestrefabricated.content.blocks.block.FenceDown;
import com.conquestrefabricated.content.blocks.block.FenceGate;
import com.conquestrefabricated.content.blocks.block.FenceVanilla;
import com.conquestrefabricated.content.blocks.block.FourWay;
import com.conquestrefabricated.content.blocks.block.WallOld;
import com.conquestrefabricated.content.blocks.block.decor.FenceLayered;
import com.conquestrefabricated.content.blocks.block.decor.ModelBlock;
import com.conquestrefabricated.content.blocks.block.decor.Rail;
import com.conquestrefabricated.content.blocks.block.decor.RusticFence;
import com.conquestrefabricated.content.blocks.block.decor.SmallDoor;
import com.conquestrefabricated.content.blocks.block.directional.Directional;
import com.conquestrefabricated.content.blocks.block.directional.HorizontalDirectional;
import com.conquestrefabricated.content.blocks.block.glass.Pane;
import com.conquestrefabricated.content.blocks.block.glass.PaneOld;
import com.conquestrefabricated.content.blocks.block.overlay_wall.OverlayVerticalCornerThin;
import com.conquestrefabricated.content.blocks.block.overlay_wall.OverlayVerticalQuarterThin;
import com.conquestrefabricated.content.blocks.block.overlay_wall.OverlayVerticalSlabThin;
import com.conquestrefabricated.content.blocks.group.ModGroups;
import com.conquestrefabricated.core.block.builder.VanillaProps;
import com.conquestrefabricated.core.block.factory.TypeList;
import com.conquestrefabricated.core.util.RenderLayer;

public class FencesInit {
   public FencesInit() {
   }

   public static void init() {
      VanillaProps.planks().name("wicker_fence").manual().register(TypeList.of(FenceLayered.class));
      VanillaProps.planks().name("wicker_fence_half").family("wicker_fence").manual().register(TypeList.of(FenceLayered.Half.class));
      VanillaProps.planks().name("straight_wicker_fence").family("wicker_fence").manual().register(TypeList.of(FenceLayered.class));
      VanillaProps.planks().name("straight_wicker_fence_half").family("wicker_fence").manual().register(TypeList.of(FenceLayered.Half.class));
      VanillaProps.planks().name("wicker_fence_thin_vertical_corner").family("wicker_fence").manual().register(TypeList.of(OverlayVerticalCornerThin.class));
      VanillaProps.planks().name("wicker_fence_thin_vertical_slab").family("wicker_fence").manual().register(TypeList.of(OverlayVerticalSlabThin.class));
      VanillaProps.planks().name("wicker_fence_thin_vertical_quarter").family("wicker_fence").manual().register(TypeList.of(OverlayVerticalQuarterThin.class));
      VanillaProps.planks()
         .name("wicker_fence_half_thin_vertical_corner")
         .family("wicker_fence")
         .manual()
         .register(TypeList.of(OverlayVerticalCornerThin.Half.class));
      VanillaProps.planks()
         .name("wicker_fence_half_thin_vertical_slab")
         .family("wicker_fence")
         .manual()
         .register(TypeList.of(OverlayVerticalSlabThin.Half.class));
      VanillaProps.planks()
         .name("wicker_fence_half_thin_vertical_quarter")
         .family("wicker_fence")
         .manual()
         .register(TypeList.of(OverlayVerticalQuarterThin.Half.class));
      VanillaProps.planks().name("old_wicker_fence").manual().register(TypeList.of(FenceLayered.class));
      VanillaProps.planks().name("old_wicker_fence_half").family("old_wicker_fence").manual().register(TypeList.of(FenceLayered.Half.class));
      VanillaProps.planks().name("straight_old_wicker_fence").family("old_wicker_fence").manual().register(TypeList.of(FenceLayered.class));
      VanillaProps.planks().name("straight_old_wicker_fence_half").family("old_wicker_fence").manual().register(TypeList.of(FenceLayered.Half.class));
      VanillaProps.planks()
         .name("old_wicker_fence_thin_vertical_corner")
         .family("old_wicker_fence")
         .manual()
         .register(TypeList.of(OverlayVerticalCornerThin.class));
      VanillaProps.planks()
         .name("old_wicker_fence_thin_vertical_slab")
         .family("old_wicker_fence")
         .manual()
         .register(TypeList.of(OverlayVerticalSlabThin.class));
      VanillaProps.planks()
         .name("old_wicker_fence_thin_vertical_quarter")
         .family("old_wicker_fence")
         .manual()
         .register(TypeList.of(OverlayVerticalQuarterThin.class));
      VanillaProps.planks()
         .name("old_wicker_fence_half_thin_vertical_corner")
         .family("old_wicker_fence")
         .manual()
         .register(TypeList.of(OverlayVerticalCornerThin.Half.class));
      VanillaProps.planks()
         .name("old_wicker_fence_half_thin_vertical_slab")
         .family("old_wicker_fence")
         .manual()
         .register(TypeList.of(OverlayVerticalSlabThin.Half.class));
      VanillaProps.planks()
         .name("old_wicker_fence_half_thin_vertical_quarter")
         .family("old_wicker_fence")
         .manual()
         .register(TypeList.of(OverlayVerticalQuarterThin.Half.class));
      VanillaProps.planks().name("ash_wicker_fence").manual().register(TypeList.of(FenceLayered.class));
      VanillaProps.planks().name("ash_wicker_fence_half").family("ash_wicker_fence").manual().register(TypeList.of(FenceLayered.Half.class));
      VanillaProps.planks().name("straight_ash_wicker_fence").family("ash_wicker_fence").manual().register(TypeList.of(FenceLayered.class));
      VanillaProps.planks().name("straight_ash_wicker_fence_half").family("ash_wicker_fence").manual().register(TypeList.of(FenceLayered.Half.class));
      VanillaProps.planks()
         .name("ash_wicker_fence_thin_vertical_corner")
         .family("ash_wicker_fence")
         .manual()
         .register(TypeList.of(OverlayVerticalCornerThin.class));
      VanillaProps.planks()
         .name("ash_wicker_fence_thin_vertical_slab")
         .family("ash_wicker_fence")
         .manual()
         .register(TypeList.of(OverlayVerticalSlabThin.class));
      VanillaProps.planks()
         .name("ash_wicker_fence_thin_vertical_quarter")
         .family("ash_wicker_fence")
         .manual()
         .register(TypeList.of(OverlayVerticalQuarterThin.class));
      VanillaProps.planks()
         .name("ash_wicker_fence_half_thin_vertical_corner")
         .family("ash_wicker_fence")
         .manual()
         .register(TypeList.of(OverlayVerticalCornerThin.Half.class));
      VanillaProps.planks()
         .name("ash_wicker_fence_half_thin_vertical_slab")
         .family("ash_wicker_fence")
         .manual()
         .register(TypeList.of(OverlayVerticalSlabThin.Half.class));
      VanillaProps.planks()
         .name("ash_wicker_fence_half_thin_vertical_quarter")
         .family("ash_wicker_fence")
         .manual()
         .register(TypeList.of(OverlayVerticalQuarterThin.Half.class));
      VanillaProps.planks().name("birch_wicker_fence").manual().register(TypeList.of(FenceLayered.class));
      VanillaProps.planks().name("birch_wicker_fence_half").family("birch_wicker_fence").manual().register(TypeList.of(FenceLayered.Half.class));
      VanillaProps.planks().name("straight_birch_wicker_fence").family("birch_wicker_fence").manual().register(TypeList.of(FenceLayered.class));
      VanillaProps.planks().name("straight_birch_wicker_fence_half").family("birch_wicker_fence").manual().register(TypeList.of(FenceLayered.Half.class));
      VanillaProps.planks()
         .name("birch_wicker_fence_thin_vertical_corner")
         .family("birch_wicker_fence")
         .manual()
         .register(TypeList.of(OverlayVerticalCornerThin.class));
      VanillaProps.planks()
         .name("birch_wicker_fence_thin_vertical_slab")
         .family("birch_wicker_fence")
         .manual()
         .register(TypeList.of(OverlayVerticalSlabThin.class));
      VanillaProps.planks()
         .name("birch_wicker_fence_thin_vertical_quarter")
         .family("birch_wicker_fence")
         .manual()
         .register(TypeList.of(OverlayVerticalQuarterThin.class));
      VanillaProps.planks()
         .name("birch_wicker_fence_half_thin_vertical_corner")
         .family("birch_wicker_fence")
         .manual()
         .register(TypeList.of(OverlayVerticalCornerThin.Half.class));
      VanillaProps.planks()
         .name("birch_wicker_fence_half_thin_vertical_slab")
         .family("birch_wicker_fence")
         .manual()
         .register(TypeList.of(OverlayVerticalSlabThin.Half.class));
      VanillaProps.planks()
         .name("birch_wicker_fence_half_thin_vertical_quarter")
         .family("birch_wicker_fence")
         .manual()
         .register(TypeList.of(OverlayVerticalQuarterThin.Half.class));
      VanillaProps.planks().name("oak_wicker_fence").manual().register(TypeList.of(FenceLayered.class));
      VanillaProps.planks().name("oak_wicker_fence_half").family("oak_wicker_fence").manual().register(TypeList.of(FenceLayered.Half.class));
      VanillaProps.planks().name("straight_oak_wicker_fence").family("oak_wicker_fence").manual().register(TypeList.of(FenceLayered.class));
      VanillaProps.planks().name("straight_oak_wicker_fence_half").family("oak_wicker_fence").manual().register(TypeList.of(FenceLayered.Half.class));
      VanillaProps.planks()
         .name("oak_wicker_fence_thin_vertical_corner")
         .family("oak_wicker_fence")
         .manual()
         .register(TypeList.of(OverlayVerticalCornerThin.class));
      VanillaProps.planks()
         .name("oak_wicker_fence_thin_vertical_slab")
         .family("oak_wicker_fence")
         .manual()
         .register(TypeList.of(OverlayVerticalSlabThin.class));
      VanillaProps.planks()
         .name("oak_wicker_fence_thin_vertical_quarter")
         .family("oak_wicker_fence")
         .manual()
         .register(TypeList.of(OverlayVerticalQuarterThin.class));
      VanillaProps.planks()
         .name("oak_wicker_fence_half_thin_vertical_corner")
         .family("oak_wicker_fence")
         .manual()
         .register(TypeList.of(OverlayVerticalCornerThin.Half.class));
      VanillaProps.planks()
         .name("oak_wicker_fence_half_thin_vertical_slab")
         .family("oak_wicker_fence")
         .manual()
         .register(TypeList.of(OverlayVerticalSlabThin.Half.class));
      VanillaProps.planks()
         .name("oak_wicker_fence_half_thin_vertical_quarter")
         .family("oak_wicker_fence")
         .manual()
         .register(TypeList.of(OverlayVerticalQuarterThin.Half.class));
      VanillaProps.planks()
         .group(ModGroups.TOOL_BLOCKS)
         .name("wooden_pole")
         .manual()
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.pillarShape)
         .register(TypeList.of(ModelBlock.class));
      VanillaProps.planks().name("wooden_cross_railing").manual().register(TypeList.of(FourWay.class));
      VanillaProps.planks().name("simple_picket_fence").manual().register(TypeList.of(FenceDown.class));
      VanillaProps.planks().name("simple_oak").texture("block/1_basic_refined/3_wood/oak/simple_oak_fence").register(TypeList.of(FenceVanilla.class));
      VanillaProps.planks().name("old_simple_oak").texture("block/1_basic_refined/3_wood/oak/old_simple_oak_fence").register(TypeList.of(FenceVanilla.class));
      VanillaProps.planks().name("newel_capped_oak_wood").manual().register(TypeList.of(FenceVanilla.class, FenceGate.class));
      VanillaProps.planks().name("wooden_rail").manual().register(TypeList.of(Rail.class));
      VanillaProps.planks()
         .name("wooden_support_frame")
         .manual()
         .with("hitBox", BlockVoxelShapes.cubePartialShape)
         .register(TypeList.of(Directional.WaterloggedToggle2.class));
      VanillaProps.planks()
         .name("wooden_roof_supports")
         .manual()
         .solid(false)
         .with("hitBox", BlockVoxelShapes.stairTopShapes)
         .register(TypeList.of(HorizontalDirectional.class));
      VanillaProps.planks().name("rustic_fence").manual().register(TypeList.of(RusticFence.class));
      VanillaProps.planks().name("rustic_cross").family("rustic_fence").manual().register(TypeList.of(FenceGate.class));
      VanillaProps.planks().name("rustic_fence_gate").family("rustic_fence").manual().solid(false).register(TypeList.of(SmallDoor.class));
      VanillaProps.stone()
         .name("schist_dragon_head")
         .manual()
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.dragonHeadShapes)
         .register(TypeList.of(HorizontalDirectional.OffsetXZ.class));
      VanillaProps.stone()
         .name("sandstone_dragon_head")
         .manual()
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.dragonHeadShapes)
         .register(TypeList.of(HorizontalDirectional.OffsetXZ.class));
      VanillaProps.metal().name("bronze_lattice").texture("block/2_advanced_refined/2_metal/bronze_lattice").register(TypeList.of(Pane.class));
      VanillaProps.metal()
         .name("dark_wrought_iron_fence")
         .texture("middle", "block/3_decoration/2_metal/dark_wrought_iron_fence_middle")
         .texture("*", "block/3_decoration/2_metal/dark_wrought_iron_fence")
         .register(TypeList.of(Pane.class));
      VanillaProps.metal()
         .name("rusted_iron_fence")
         .texture("middle", "block/3_decoration/2_metal/rusted_iron_fence_middle")
         .texture("*", "block/3_decoration/2_metal/rusted_iron_fence")
         .register(TypeList.of(Pane.class));
      VanillaProps.metal().name("iron_spikes").texture("block/3_decoration/2_metal/iron_spikes").register(TypeList.of(PaneOld.class));
      VanillaProps.metal()
         .name("wrought_iron_fence")
         .texture("middle", "block/3_decoration/2_metal/wrought_iron_fence_middle")
         .texture("*", "block/3_decoration/2_metal/wrought_iron_fence")
         .register(TypeList.of(Pane.class));
      VanillaProps.metal().name("cell_bars").texture("block/3_decoration/2_metal/cell_bars").register(TypeList.of(Pane.class));
      VanillaProps.metal()
         .name("dark_wrought_iron_fence_vertical_corner")
         .family("dark_wrought_iron_fence_pane")
         .manual()
         .register(TypeList.of(OverlayVerticalCornerThin.class));
      VanillaProps.metal()
         .name("dark_wrought_iron_fence_vertical_slab")
         .family("dark_wrought_iron_fence_pane")
         .manual()
         .register(TypeList.of(OverlayVerticalSlabThin.class));
      VanillaProps.metal()
         .name("wrought_iron_fence_vertical_corner")
         .family("wrought_iron_fence_pane")
         .manual()
         .register(TypeList.of(OverlayVerticalCornerThin.class));
      VanillaProps.metal()
         .name("wrought_iron_fence_vertical_slab")
         .family("wrought_iron_fence_pane")
         .manual()
         .register(TypeList.of(OverlayVerticalSlabThin.class));
      VanillaProps.metal()
         .name("rusted_iron_fence_vertical_corner")
         .family("rusted_iron_fence_pane")
         .manual()
         .register(TypeList.of(OverlayVerticalCornerThin.class));
      VanillaProps.metal()
         .name("rusted_iron_fence_vertical_slab")
         .family("rusted_iron_fence_pane")
         .manual()
         .register(TypeList.of(OverlayVerticalSlabThin.class));
      VanillaProps.metal().name("iron_cage_bars_vertical_corner").family("iron_cage_bars").manual().register(TypeList.of(OverlayVerticalCornerThin.class));
      VanillaProps.metal().name("iron_cage_bars_vertical_slab").family("iron_cage_bars").manual().register(TypeList.of(OverlayVerticalSlabThin.class));
      VanillaProps.metal().name("cell_bars_vertical_corner").family("cell_bars_pane").manual().register(TypeList.of(OverlayVerticalCornerThin.class));
      VanillaProps.metal().name("cell_bars_vertical_slab").family("cell_bars_pane").manual().register(TypeList.of(OverlayVerticalSlabThin.class));
      VanillaProps.metal().name("bronze_lattice_vertical_corner").family("bronze_lattice_pane").manual().register(TypeList.of(OverlayVerticalCornerThin.class));
      VanillaProps.metal().name("bronze_lattice_vertical_slab").family("bronze_lattice_pane").manual().register(TypeList.of(OverlayVerticalSlabThin.class));
      VanillaProps.planks().name("white_painted_wooden_railing").manual().render(RenderLayer.CUTOUT).register(TypeList.of(Fence.class));
      VanillaProps.planks()
         .name("white_painted_wood_trellis")
         .manual()
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.trapdoorShapes)
         .register(TypeList.of(Directional.class));
      VanillaProps.planks()
         .family("white_painted_wood_trellis")
         .name("white_painted_wood_trellis")
         .manual()
         .render(RenderLayer.CUTOUT)
         .register(TypeList.of(Fence.class));
      VanillaProps.planks()
         .name("oak_wood_trellis")
         .manual()
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.trapdoorShapes)
         .register(TypeList.of(Directional.class));
      VanillaProps.stone().name("fancy_marble_balustrade").manual().render(RenderLayer.CUTOUT).register(TypeList.of(WallOld.class));
      VanillaProps.stone().name("fancy_travertine_balustrade").manual().render(RenderLayer.CUTOUT).register(TypeList.of(WallOld.class));
      VanillaProps.stone().name("fancy_dark_sandstone_balustrade").manual().render(RenderLayer.CUTOUT).register(TypeList.of(WallOld.class));
      VanillaProps.stone().name("fancy_limestone_balustrade").manual().render(RenderLayer.CUTOUT).register(TypeList.of(WallOld.class));
   }
}
