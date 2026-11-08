package com.conquestrefabricated.content.blocks.init.blocks;

import com.conquestrefabricated.content.blocks.block.Cube;
import com.conquestrefabricated.content.blocks.block.Layer;
import com.conquestrefabricated.content.blocks.block.Pillar;
import com.conquestrefabricated.content.blocks.block.Slab;
import com.conquestrefabricated.content.blocks.block.SlabCorner;
import com.conquestrefabricated.content.blocks.block.SlabEighth;
import com.conquestrefabricated.content.blocks.block.SlabQuarter;
import com.conquestrefabricated.content.blocks.block.Stairs;
import com.conquestrefabricated.content.blocks.block.VerticalCorner;
import com.conquestrefabricated.content.blocks.block.VerticalQuarter;
import com.conquestrefabricated.content.blocks.block.VerticalSlab;
import com.conquestrefabricated.content.blocks.block.VerticalSlabCorner;
import com.conquestrefabricated.content.blocks.block.WallNew;
import com.conquestrefabricated.content.blocks.block.grass.Grass;
import com.conquestrefabricated.content.blocks.block.trees.LogVerticalQuarter;
import com.conquestrefabricated.content.blocks.block.trees.Stump;
import com.conquestrefabricated.content.blocks.group.ModGroups;
import com.conquestrefabricated.core.block.builder.VanillaProps;
import com.conquestrefabricated.core.block.factory.TypeList;
import com.conquestrefabricated.core.util.RenderLayer;
import net.minecraft.world.level.block.Blocks;

public class GrassGroundInit {
   public GrassGroundInit() {
   }

   public static void init(TypeList types) {
      VanillaProps.grass().group(ModGroups.GRASS_AND_DIRT).name("grass_covered_limestone").manual().grassColor().register(TypeList.of(Grass.class));
      VanillaProps.grassLike()
         .group(ModGroups.GRASS_AND_DIRT)
         .name("grass_covered_limestone")
         .family("grass_covered_limestone")
         .parent(Blocks.GRASS_BLOCK.defaultBlockState())
         .solid(true)
         .blocking(true)
         .manual()
         .grassColor()
         .register(TypeList.of(Stairs.class, VerticalSlab.class, VerticalCorner.class, VerticalQuarter.class));
      VanillaProps.grassLike()
         .group(ModGroups.GRASS_AND_DIRT)
         .name("grass_block")
         .manual()
         .parent(Blocks.GRASS_BLOCK.defaultBlockState())
         .render(RenderLayer.CUTOUT_MIPPED_SOLID)
         .grassColor()
         .register(
            TypeList.of(
               Layer.class,
               Stairs.class,
               VerticalSlab.class,
               VerticalCorner.class,
               VerticalQuarter.class,
               SlabCorner.class,
               SlabEighth.class,
               SlabQuarter.class,
               VerticalSlabCorner.class
            )
         );
      VanillaProps.grass().group(ModGroups.GRASS_AND_DIRT).name("clover_covered_grass").manual().grassColor().register(TypeList.of(Grass.class));
      VanillaProps.grassLike()
         .group(ModGroups.GRASS_AND_DIRT)
         .name("clover_covered_grass")
         .family("clover_covered_grass")
         .parent(Blocks.GRASS_BLOCK.defaultBlockState())
         .render(RenderLayer.CUTOUT_MIPPED_SOLID)
         .manual()
         .grassColor()
         .register(
            TypeList.of(
               Layer.class,
               Stairs.class,
               VerticalSlab.class,
               VerticalCorner.class,
               VerticalQuarter.class,
               SlabCorner.class,
               SlabEighth.class,
               SlabQuarter.class,
               VerticalSlabCorner.class
            )
         );
      VanillaProps.grass()
         .group(ModGroups.GRASS_AND_DIRT)
         .name("brown_sphagnum_moss_block")
         .texture("block/8_topography/4_grass_leaves/brown_sphagnum_moss")
         .register(
            TypeList.of(
               Cube.class,
               Layer.class,
               Stairs.class,
               VerticalSlab.class,
               VerticalCorner.class,
               LogVerticalQuarter.class,
               Stump.class,
               SlabEighth.class,
               SlabQuarter.class,
               SlabCorner.class,
               VerticalSlabCorner.class
            )
         );
      VanillaProps.grass()
         .group(ModGroups.GRASS_AND_DIRT)
         .name("green_sphagnum_moss_block")
         .texture("block/8_topography/4_grass_leaves/green_sphagnum_moss")
         .register(
            TypeList.of(
               Cube.class,
               Layer.class,
               Stairs.class,
               VerticalSlab.class,
               VerticalCorner.class,
               LogVerticalQuarter.class,
               Stump.class,
               SlabEighth.class,
               SlabQuarter.class,
               SlabCorner.class,
               VerticalSlabCorner.class
            )
         );
      VanillaProps.grass()
         .group(ModGroups.GRASS_AND_DIRT)
         .name("light_green_sphagnum_moss_block")
         .texture("block/8_topography/4_grass_leaves/light_green_sphagnum_moss")
         .register(
            TypeList.of(
               Cube.class,
               Layer.class,
               Stairs.class,
               VerticalSlab.class,
               VerticalCorner.class,
               LogVerticalQuarter.class,
               Stump.class,
               SlabEighth.class,
               SlabQuarter.class,
               SlabCorner.class,
               VerticalSlabCorner.class
            )
         );
      VanillaProps.grass()
         .group(ModGroups.GRASS_AND_DIRT)
         .name("red_sphagnum_moss_block")
         .texture("block/8_topography/4_grass_leaves/red_sphagnum_moss")
         .register(
            TypeList.of(
               Cube.class,
               Layer.class,
               Stairs.class,
               VerticalSlab.class,
               VerticalCorner.class,
               LogVerticalQuarter.class,
               Stump.class,
               SlabEighth.class,
               SlabQuarter.class,
               SlabCorner.class,
               VerticalSlabCorner.class
            )
         );
      VanillaProps.grass()
         .group(ModGroups.GRASS_AND_DIRT)
         .name("yellow_sphagnum_moss_block")
         .texture("block/8_topography/4_grass_leaves/yellow_sphagnum_moss")
         .register(
            TypeList.of(
               Cube.class,
               Layer.class,
               Stairs.class,
               VerticalSlab.class,
               VerticalCorner.class,
               LogVerticalQuarter.class,
               Stump.class,
               SlabEighth.class,
               SlabQuarter.class,
               SlabCorner.class,
               VerticalSlabCorner.class
            )
         );
      VanillaProps.grass()
         .group(ModGroups.GRASS_AND_DIRT)
         .name("mycelium")
         .texture("top", "minecraft:block/mycelium_top")
         .texture("bottom", "minecraft:block/dirt")
         .texture("texture", "minecraft:block/mycelium_top")
         .texture("*", "minecraft:block/mycelium_side")
         .parent(Blocks.MYCELIUM.defaultBlockState())
         .register(TypeList.of(Layer.class, Stairs.class, VerticalSlab.class, VerticalCorner.class, VerticalQuarter.class));
      VanillaProps.grass()
         .group(ModGroups.GRASS_AND_DIRT)
         .name("vibrant_autumnal_forest_floor")
         .texture("top", "minecraft:block/podzol_top")
         .texture("bottom", "minecraft:block/dirt")
         .texture("texture", "minecraft:block/podzol_top")
         .texture("*", "minecraft:block/podzol_side")
         .parent(Blocks.PODZOL.defaultBlockState())
         .register(TypeList.of(Layer.class, Stairs.class, VerticalSlab.class, VerticalCorner.class, VerticalQuarter.class));
      VanillaProps.grass()
         .group(ModGroups.GRASS_AND_DIRT)
         .name("taiga_forest_floor")
         .texture("top", "block/8_topography/4_grass_leaves/taiga_forest_floor")
         .texture("bottom", "minecraft:block/dirt")
         .texture("texture", "block/8_topography/4_grass_leaves/taiga_forest_floor")
         .texture("*", "block/8_topography/4_grass_leaves/taiga_forest_floor_side")
         .register(types);
      VanillaProps.grass()
         .group(ModGroups.GRASS_AND_DIRT)
         .name("fir_forest_floor")
         .texture("bottom", "minecraft:block/dirt")
         .texture("*", "block/8_topography/4_grass_leaves/fir_forest_floor")
         .register(types);
      VanillaProps.grass()
         .group(ModGroups.GRASS_AND_DIRT)
         .name("mossy_fir_forest_floor")
         .grassColor()
         .render(RenderLayer.CUTOUT_MIPPED_SOLID)
         .texture("bottom", "minecraft:block/dirt")
         .texture("*", "block/8_topography/4_grass_leaves/fir_forest_floor")
         .register(types);
      VanillaProps.grass()
         .group(ModGroups.GRASS_AND_DIRT)
         .name("overgrown_fir_forest_floor")
         .grassColor()
         .render(RenderLayer.CUTOUT_MIPPED_SOLID)
         .texture("bottom", "minecraft:block/dirt")
         .texture("*", "block/8_topography/4_grass_leaves/fir_forest_floor")
         .register(types);
      VanillaProps.grass()
         .group(ModGroups.GRASS_AND_DIRT)
         .name("autumnal_forest_floor_with_roots")
         .texture("bottom", "minecraft:block/dirt")
         .texture("*", "block/8_topography/4_grass_leaves/autumnal_forest_floor_with_roots")
         .register(types);
      VanillaProps.grass()
         .group(ModGroups.GRASS_AND_DIRT)
         .name("mossy_forest_floor_with_roots")
         .texture("bottom", "minecraft:block/dirt")
         .texture("*", "block/8_topography/4_grass_leaves/mossy_forest_floor_with_roots")
         .register(types);
      VanillaProps.grass()
         .group(ModGroups.GRASS_AND_DIRT)
         .name("muddy_autumnal_forest_floor")
         .texture("bottom", "minecraft:block/dirt")
         .texture("*", "block/8_topography/4_grass_leaves/muddy_autumnal_forest_floor")
         .register(types);
      VanillaProps.grass()
         .group(ModGroups.GRASS_AND_DIRT)
         .name("autumnal_forest_floor")
         .texture("bottom", "minecraft:block/dirt")
         .texture("*", "block/8_topography/4_grass_leaves/autumnal_forest_floor")
         .register(types);
      VanillaProps.grass()
         .group(ModGroups.GRASS_AND_DIRT)
         .name("lorien_forest_floor")
         .texture("bottom", "minecraft:block/dirt")
         .texture("*", "block/8_topography/4_grass_leaves/lorien_forest_floor")
         .register(types);
      VanillaProps.grass()
         .group(ModGroups.GRASS_AND_DIRT)
         .name("mossy_lorien_forest_floor")
         .texture("bottom", "minecraft:block/dirt")
         .texture("*", "block/8_topography/4_grass_leaves/mossy_lorien_forest_floor")
         .register(types);
      VanillaProps.grass()
         .group(ModGroups.GRASS_AND_DIRT)
         .name("heather_covered_moss")
         .texture("bottom", "minecraft:block/dirt")
         .texture("*", "block/8_topography/4_grass_leaves/heather_covered_moss")
         .register(types);
      VanillaProps.earth()
         .group(ModGroups.GRASS_AND_DIRT)
         .name("crimson_nylium")
         .texture("top", "minecraft:block/crimson_nylium")
         .texture("bottom", "minecraft:block/soul_sand")
         .texture("texture", "minecraft:block/crimson_nylium")
         .texture("*", "minecraft:block/crimson_nylium_side")
         .parent(Blocks.CRIMSON_NYLIUM.defaultBlockState())
         .register(TypeList.of(Layer.class, Stairs.class, VerticalSlab.class, VerticalCorner.class, VerticalQuarter.class));
      VanillaProps.earth()
         .group(ModGroups.GRASS_AND_DIRT)
         .name("warped_nylium")
         .texture("top", "minecraft:block/warped_nylium")
         .texture("bottom", "minecraft:block/soul_sand")
         .texture("texture", "minecraft:block/warped_nylium")
         .texture("*", "minecraft:block/warped_nylium_side")
         .parent(Blocks.WARPED_NYLIUM.defaultBlockState())
         .register(TypeList.of(Layer.class, Stairs.class, VerticalSlab.class, VerticalCorner.class, VerticalQuarter.class));
      VanillaProps.earth()
         .group(ModGroups.GRASSES_AND_SHRUBS)
         .name("nether_wart_block")
         .texture("*", "minecraft:block/nether_wart_block")
         .parent(Blocks.NETHER_WART_BLOCK.defaultBlockState())
         .register(TypeList.of(Layer.class, Stairs.class, VerticalSlab.class, VerticalCorner.class, VerticalQuarter.class));
      VanillaProps.earth()
         .group(ModGroups.GRASSES_AND_SHRUBS)
         .name("warped_wart_block")
         .texture("*", "minecraft:block/warped_wart_block")
         .parent(Blocks.WARPED_WART_BLOCK.defaultBlockState())
         .register(TypeList.of(Layer.class, Stairs.class, VerticalSlab.class, VerticalCorner.class, VerticalQuarter.class));
      VanillaProps.logs()
         .group(ModGroups.GRASSES_AND_SHRUBS)
         .name("crimson_stem")
         .texture("*", "minecraft:block/crimson_stem")
         .parent(Blocks.CRIMSON_HYPHAE.defaultBlockState())
         .register(TypeList.of(VerticalSlab.class, VerticalCorner.class, VerticalQuarter.class));
      VanillaProps.logs()
         .group(ModGroups.GRASSES_AND_SHRUBS)
         .name("stripped_crimson_stem")
         .texture("*", "minecraft:block/stripped_crimson_stem")
         .parent(Blocks.STRIPPED_CRIMSON_HYPHAE.defaultBlockState())
         .register(TypeList.of(VerticalSlab.class, VerticalCorner.class, VerticalQuarter.class));
      VanillaProps.logs()
         .group(ModGroups.GRASSES_AND_SHRUBS)
         .name("warped_stem")
         .texture("*", "minecraft:block/warped_stem")
         .parent(Blocks.WARPED_HYPHAE.defaultBlockState())
         .register(TypeList.of(VerticalSlab.class, VerticalCorner.class, VerticalQuarter.class));
      VanillaProps.logs()
         .group(ModGroups.GRASSES_AND_SHRUBS)
         .name("stripped_warped_stem")
         .texture("*", "minecraft:block/stripped_warped_stem")
         .parent(Blocks.STRIPPED_WARPED_HYPHAE.defaultBlockState())
         .register(TypeList.of(VerticalSlab.class, VerticalCorner.class, VerticalQuarter.class));
      VanillaProps.grass().group(ModGroups.GRASS_AND_DIRT).name("taiga_grass").manual().grassColor().register(TypeList.of(Grass.class));
      VanillaProps.grassLike()
         .group(ModGroups.GRASS_AND_DIRT)
         .name("taiga_grass")
         .family("taiga_grass")
         .parent(Blocks.GRASS_BLOCK.defaultBlockState())
         .manual()
         .render(RenderLayer.CUTOUT_MIPPED_SOLID)
         .grassColor()
         .register(
            TypeList.of(
               Layer.class,
               Stairs.class,
               VerticalSlab.class,
               VerticalCorner.class,
               VerticalQuarter.class,
               SlabCorner.class,
               SlabEighth.class,
               SlabQuarter.class,
               VerticalSlabCorner.class
            )
         );
      VanillaProps.grassLike()
         .group(ModGroups.GRASS_AND_DIRT)
         .name("shredded_leaves")
         .texture("block/8_topography/4_grass_leaves/shredded_leaves")
         .register(types);
      VanillaProps.stone()
         .group(ModGroups.ANIMALS)
         .name("fire_coral_block")
         .texture("minecraft:block/fire_coral_block")
         .parent(Blocks.FIRE_CORAL_BLOCK.defaultBlockState())
         .register(
            TypeList.of(
               Slab.class,
               SlabQuarter.class,
               SlabCorner.class,
               SlabEighth.class,
               VerticalSlabCorner.class,
               VerticalSlab.class,
               VerticalCorner.class,
               VerticalQuarter.class,
               Stairs.class,
               Pillar.class,
               WallNew.class
            )
         );
      VanillaProps.stone()
         .group(ModGroups.ANIMALS)
         .name("bubble_coral_block")
         .texture("minecraft:block/bubble_coral_block")
         .parent(Blocks.BUBBLE_CORAL_BLOCK.defaultBlockState())
         .register(
            TypeList.of(
               Slab.class,
               SlabQuarter.class,
               SlabCorner.class,
               SlabEighth.class,
               VerticalSlabCorner.class,
               VerticalSlab.class,
               VerticalCorner.class,
               VerticalQuarter.class,
               Stairs.class,
               Pillar.class,
               WallNew.class
            )
         );
      VanillaProps.stone()
         .group(ModGroups.ANIMALS)
         .name("brain_coral_block")
         .texture("minecraft:block/brain_coral_block")
         .parent(Blocks.BRAIN_CORAL_BLOCK.defaultBlockState())
         .register(
            TypeList.of(
               Slab.class,
               SlabQuarter.class,
               SlabCorner.class,
               SlabEighth.class,
               VerticalSlabCorner.class,
               VerticalSlab.class,
               VerticalCorner.class,
               VerticalQuarter.class,
               Stairs.class,
               Pillar.class,
               WallNew.class
            )
         );
      VanillaProps.stone()
         .group(ModGroups.ANIMALS)
         .name("horn_coral_block")
         .texture("minecraft:block/horn_coral_block")
         .parent(Blocks.HORN_CORAL_BLOCK.defaultBlockState())
         .register(
            TypeList.of(
               Slab.class,
               SlabQuarter.class,
               SlabCorner.class,
               SlabEighth.class,
               VerticalSlabCorner.class,
               VerticalSlab.class,
               VerticalCorner.class,
               VerticalQuarter.class,
               Stairs.class,
               Pillar.class,
               WallNew.class
            )
         );
      VanillaProps.stone()
         .group(ModGroups.ANIMALS)
         .name("tube_coral_block")
         .texture("minecraft:block/tube_coral_block")
         .parent(Blocks.TUBE_CORAL_BLOCK.defaultBlockState())
         .register(
            TypeList.of(
               Slab.class,
               SlabQuarter.class,
               SlabCorner.class,
               SlabEighth.class,
               VerticalSlabCorner.class,
               VerticalSlab.class,
               VerticalCorner.class,
               VerticalQuarter.class,
               Stairs.class,
               Pillar.class,
               WallNew.class
            )
         );
      VanillaProps.stone()
         .group(ModGroups.ANIMALS)
         .name("dead_fire_coral_block")
         .texture("minecraft:block/dead_fire_coral_block")
         .parent(Blocks.DEAD_FIRE_CORAL_BLOCK.defaultBlockState())
         .register(
            TypeList.of(
               Slab.class,
               SlabQuarter.class,
               SlabCorner.class,
               SlabEighth.class,
               VerticalSlabCorner.class,
               VerticalSlab.class,
               VerticalCorner.class,
               VerticalQuarter.class,
               Stairs.class,
               Pillar.class,
               WallNew.class
            )
         );
      VanillaProps.stone()
         .group(ModGroups.ANIMALS)
         .name("dead_bubble_coral_block")
         .texture("minecraft:block/dead_bubble_coral_block")
         .parent(Blocks.DEAD_BUBBLE_CORAL_BLOCK.defaultBlockState())
         .register(
            TypeList.of(
               Slab.class,
               SlabQuarter.class,
               SlabCorner.class,
               SlabEighth.class,
               VerticalSlabCorner.class,
               VerticalSlab.class,
               VerticalCorner.class,
               VerticalQuarter.class,
               Stairs.class,
               Pillar.class,
               WallNew.class
            )
         );
      VanillaProps.stone()
         .group(ModGroups.ANIMALS)
         .name("dead_brain_coral_block")
         .texture("minecraft:block/dead_brain_coral_block")
         .parent(Blocks.DEAD_BRAIN_CORAL_BLOCK.defaultBlockState())
         .register(
            TypeList.of(
               Slab.class,
               SlabQuarter.class,
               SlabCorner.class,
               SlabEighth.class,
               VerticalSlabCorner.class,
               VerticalSlab.class,
               VerticalCorner.class,
               VerticalQuarter.class,
               Stairs.class,
               Pillar.class,
               WallNew.class
            )
         );
      VanillaProps.stone()
         .group(ModGroups.ANIMALS)
         .name("dead_horn_coral_block")
         .texture("minecraft:block/dead_horn_coral_block")
         .parent(Blocks.DEAD_HORN_CORAL_BLOCK.defaultBlockState())
         .register(
            TypeList.of(
               Slab.class,
               SlabQuarter.class,
               SlabCorner.class,
               SlabEighth.class,
               VerticalSlabCorner.class,
               VerticalSlab.class,
               VerticalCorner.class,
               VerticalQuarter.class,
               Stairs.class,
               Pillar.class,
               WallNew.class
            )
         );
      VanillaProps.stone()
         .group(ModGroups.ANIMALS)
         .name("dead_tube_coral_block")
         .texture("minecraft:block/dead_tube_coral_block")
         .parent(Blocks.DEAD_TUBE_CORAL_BLOCK.defaultBlockState())
         .register(
            TypeList.of(
               Slab.class,
               SlabQuarter.class,
               SlabCorner.class,
               SlabEighth.class,
               VerticalSlabCorner.class,
               VerticalSlab.class,
               VerticalCorner.class,
               VerticalQuarter.class,
               Stairs.class,
               Pillar.class,
               WallNew.class
            )
         );
      VanillaProps.stone()
         .group(ModGroups.GRASSES_AND_SHRUBS)
         .name("red_mushroom_block")
         .texture("minecraft:block/red_mushroom_block")
         .parent(Blocks.RED_MUSHROOM_BLOCK.defaultBlockState())
         .register(
            TypeList.of(
               Slab.class,
               SlabQuarter.class,
               SlabCorner.class,
               SlabEighth.class,
               VerticalSlabCorner.class,
               VerticalSlab.class,
               VerticalCorner.class,
               VerticalQuarter.class,
               Stairs.class,
               Pillar.class,
               WallNew.class
            )
         );
      VanillaProps.stone()
         .group(ModGroups.GRASSES_AND_SHRUBS)
         .name("mushroom_stem")
         .texture("minecraft:block/mushroom_stem")
         .parent(Blocks.MUSHROOM_STEM.defaultBlockState())
         .register(
            TypeList.of(
               Slab.class,
               SlabQuarter.class,
               SlabCorner.class,
               SlabEighth.class,
               VerticalSlabCorner.class,
               VerticalSlab.class,
               VerticalCorner.class,
               VerticalQuarter.class,
               Stairs.class,
               Pillar.class,
               WallNew.class
            )
         );
      VanillaProps.stone()
         .group(ModGroups.GRASSES_AND_SHRUBS)
         .name("brown_mushroom_block")
         .texture("minecraft:block/brown_mushroom_block")
         .parent(Blocks.BROWN_MUSHROOM_BLOCK.defaultBlockState())
         .register(
            TypeList.of(
               Slab.class,
               SlabQuarter.class,
               SlabCorner.class,
               SlabEighth.class,
               VerticalSlabCorner.class,
               VerticalSlab.class,
               VerticalCorner.class,
               VerticalQuarter.class,
               Stairs.class,
               Pillar.class,
               WallNew.class
            )
         );
   }
}
