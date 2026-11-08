package com.conquestrefabricated.content.blocks.init.blocks.NoVariantBlocks;

import com.conquestrefabricated.content.blocks.BlockVoxelShapes;
import com.conquestrefabricated.content.blocks.block.Cube;
import com.conquestrefabricated.content.blocks.block.FourWayFull;
import com.conquestrefabricated.content.blocks.block.Slab;
import com.conquestrefabricated.content.blocks.block.decor.EnchantmentTable;
import com.conquestrefabricated.content.blocks.block.decor.ModelBlock;
import com.conquestrefabricated.content.blocks.block.decor.ParallelConnecting;
import com.conquestrefabricated.content.blocks.block.directional.HorizontalDirectional;
import com.conquestrefabricated.content.blocks.group.ModGroups;
import com.conquestrefabricated.core.block.builder.VanillaProps;
import com.conquestrefabricated.core.block.factory.TypeList;
import com.conquestrefabricated.core.util.RenderLayer;
import net.minecraft.world.level.block.Blocks;

public class TablesInit {
   public TablesInit() {
   }

   public static void init() {
      VanillaProps.planks().group(ModGroups.FURNITURE).name("carpentry_table").manual().register(TypeList.of(ParallelConnecting.class));
      VanillaProps.planks()
         .group(ModGroups.FURNITURE)
         .family("carpentry_table")
         .name("carpentry_table_top")
         .manual()
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.verticalSlabShapes)
         .register(TypeList.of(HorizontalDirectional.OffsetXYZ.Toggle3.class));
      VanillaProps.planks()
         .group(ModGroups.APPLIANCES)
         .name("cutting_table")
         .manual()
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.cubePartialShape)
         .register(TypeList.of(ModelBlock.class));
      VanillaProps.metal()
         .group(ModGroups.FURNITURE)
         .name("bronze_3_legged_table")
         .manual()
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.cubePartialShape)
         .register(TypeList.of(HorizontalDirectional.class));
      VanillaProps.metal()
         .group(ModGroups.FURNITURE)
         .name("standing_two_legged_table")
         .manual()
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.cubePartialShape)
         .register(TypeList.of(HorizontalDirectional.Toggle4.class));
      VanillaProps.planks().group(ModGroups.FURNITURE).name("oak_wood_desk").manual().register(TypeList.of(ParallelConnecting.class));
      VanillaProps.planks().group(ModGroups.FURNITURE).name("fancy_wooden_desk").manual().register(TypeList.of(ParallelConnecting.class));
      VanillaProps.planks()
         .group(ModGroups.FURNITURE)
         .name("spruce_wood_table")
         .manual()
         .render(RenderLayer.CUTOUT)
         .solid(false)
         .with("hitBox", BlockVoxelShapes.tableShapes)
         .register(TypeList.of(HorizontalDirectional.class));
      VanillaProps.planks().group(ModGroups.FURNITURE).name("oak_wood_table").manual().solid(false).register(TypeList.of(FourWayFull.class));
      VanillaProps.planks().group(ModGroups.FURNITURE).name("stage_floor").manual().solid(false).register(TypeList.of(FourWayFull.class));
      VanillaProps.planks().group(ModGroups.FURNITURE).name("large_antique_wooden_table").manual().solid(false).register(TypeList.of(FourWayFull.class));
      VanillaProps.planks()
         .group(ModGroups.FURNITURE)
         .name("fancy_table")
         .texture("top", "block/1_basic_refined/3_wood/birch/fancy_birch_wood_topbottom")
         .texture("bottom", "block/1_basic_refined/3_wood/birch/fancy_birch_wood_topbottom")
         .texture("*", "block/4_furniture/3_wood/fancy_table")
         .render(RenderLayer.CUTOUT)
         .register(TypeList.of(Cube.class));
      VanillaProps.planks()
         .group(ModGroups.FURNITURE)
         .name("round_wooden_table")
         .manual()
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.cubePartialShape)
         .register(TypeList.of(ModelBlock.OffsetXYZ.class));
      VanillaProps.planks()
         .group(ModGroups.FURNITURE)
         .name("blue_table_stool")
         .manual()
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.cubePartialShape)
         .register(TypeList.of(HorizontalDirectional.OffsetXYZ.class));
      VanillaProps.planks()
         .group(ModGroups.FURNITURE)
         .name("moroccan_table_stool")
         .manual()
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.cubePartialShape)
         .register(TypeList.of(HorizontalDirectional.OffsetXYZ.class));
      VanillaProps.planks()
         .group(ModGroups.FURNITURE)
         .name("map_table")
         .texture("top", "block/4_furniture/3_wood/map_table_top")
         .texture("bottom", "block/4_furniture/3_wood/map_table_top")
         .texture("*", "block/4_furniture/3_wood/map_table")
         .parent(Blocks.OAK_PLANKS.defaultBlockState())
         .register(TypeList.of(Slab.class));
      VanillaProps.planks().group(ModGroups.FURNITURE).name("enchantment_table").manual().register(TypeList.of(EnchantmentTable.class));
      VanillaProps.planks()
         .group(ModGroups.FURNITURE)
         .name("bronze_side_table")
         .manual()
         .render(RenderLayer.CUTOUT)
         .solid(false)
         .with("hitBox", BlockVoxelShapes.cubePartialShape)
         .register(TypeList.of(HorizontalDirectional.OffsetXYZ.Toggle6.class));
      VanillaProps.planks()
         .group(ModGroups.FURNITURE)
         .name("rustic_square_wooden_table")
         .manual()
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.cubePartialShape)
         .register(TypeList.of(HorizontalDirectional.OffsetXYZ.class));
      VanillaProps.planks()
         .group(ModGroups.FURNITURE)
         .name("carved_relief_table")
         .manual()
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.cubePartialShape)
         .register(TypeList.of(HorizontalDirectional.OffsetXYZ.Toggle2.class));
   }
}
