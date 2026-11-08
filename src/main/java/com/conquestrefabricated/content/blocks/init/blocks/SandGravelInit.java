package com.conquestrefabricated.content.blocks.init.blocks;

import com.conquestrefabricated.content.blocks.group.ModGroups;
import com.conquestrefabricated.core.block.builder.VanillaProps;
import com.conquestrefabricated.core.block.factory.TypeList;
import com.conquestrefabricated.core.util.RenderLayer;
import net.minecraft.world.level.block.Blocks;

public class SandGravelInit {
   public SandGravelInit() {
   }

   public static void init(TypeList types, TypeList typesVanilla, TypeList typesOverlay) {
      VanillaProps.sand()
         .name("white_concrete_powder")
         .texture("minecraft:block/white_concrete_powder")
         .parent(Blocks.WHITE_CONCRETE_POWDER.defaultBlockState())
         .register(typesVanilla);
      VanillaProps.sand()
         .name("orange_concrete_powder")
         .texture("minecraft:block/orange_concrete_powder")
         .parent(Blocks.ORANGE_CONCRETE_POWDER.defaultBlockState())
         .register(typesVanilla);
      VanillaProps.sand()
         .name("magenta_concrete_powder")
         .texture("minecraft:block/magenta_concrete_powder")
         .parent(Blocks.MAGENTA_CONCRETE_POWDER.defaultBlockState())
         .register(typesVanilla);
      VanillaProps.sand()
         .name("light_blue_concrete_powder")
         .texture("minecraft:block/light_blue_concrete_powder")
         .parent(Blocks.LIGHT_BLUE_CONCRETE_POWDER.defaultBlockState())
         .register(typesVanilla);
      VanillaProps.sand()
         .name("yellow_concrete_powder")
         .texture("minecraft:block/yellow_concrete_powder")
         .parent(Blocks.YELLOW_CONCRETE_POWDER.defaultBlockState())
         .register(typesVanilla);
      VanillaProps.sand()
         .name("lime_concrete_powder")
         .texture("minecraft:block/lime_concrete_powder")
         .parent(Blocks.LIME_CONCRETE_POWDER.defaultBlockState())
         .register(typesVanilla);
      VanillaProps.sand()
         .name("pink_concrete_powder")
         .texture("minecraft:block/pink_concrete_powder")
         .parent(Blocks.PINK_CONCRETE_POWDER.defaultBlockState())
         .register(typesVanilla);
      VanillaProps.sand()
         .name("gray_concrete_powder")
         .texture("minecraft:block/gray_concrete_powder")
         .parent(Blocks.GRAY_CONCRETE_POWDER.defaultBlockState())
         .register(typesVanilla);
      VanillaProps.sand()
         .name("light_gray_concrete_powder")
         .texture("minecraft:block/light_gray_concrete_powder")
         .parent(Blocks.LIGHT_GRAY_CONCRETE_POWDER.defaultBlockState())
         .register(typesVanilla);
      VanillaProps.sand()
         .name("cyan_concrete_powder")
         .texture("minecraft:block/cyan_concrete_powder")
         .parent(Blocks.CYAN_CONCRETE_POWDER.defaultBlockState())
         .register(typesVanilla);
      VanillaProps.sand()
         .name("purple_concrete_powder")
         .texture("minecraft:block/purple_concrete_powder")
         .parent(Blocks.PURPLE_CONCRETE_POWDER.defaultBlockState())
         .register(typesVanilla);
      VanillaProps.sand()
         .name("blue_concrete_powder")
         .texture("minecraft:block/blue_concrete_powder")
         .parent(Blocks.BLUE_CONCRETE_POWDER.defaultBlockState())
         .register(typesVanilla);
      VanillaProps.sand()
         .name("brown_concrete_powder")
         .texture("minecraft:block/brown_concrete_powder")
         .parent(Blocks.BROWN_CONCRETE_POWDER.defaultBlockState())
         .register(typesVanilla);
      VanillaProps.sand()
         .name("green_concrete_powder")
         .texture("minecraft:block/green_concrete_powder")
         .parent(Blocks.GREEN_CONCRETE_POWDER.defaultBlockState())
         .register(typesVanilla);
      VanillaProps.sand().name("red_concrete_powder").texture("minecraft:block/red_concrete_powder").parent(Blocks.RED_CONCRETE_POWDER.defaultBlockState()).register(typesVanilla);
      VanillaProps.sand()
         .name("black_concrete_powder")
         .texture("minecraft:block/black_concrete_powder")
         .parent(Blocks.BLACK_CONCRETE_POWDER.defaultBlockState())
         .register(typesVanilla);
      VanillaProps.sand()
         .group(ModGroups.SAND_AND_GRAVEL)
         .name("sand")
         .texture("minecraft:block/sand")
         .parent(Blocks.SAND.defaultBlockState())
         .register(typesVanilla);
      VanillaProps.grassySand()
         .group(ModGroups.SAND_AND_GRAVEL)
         .name("grass_and_sand")
         .texture("overlay", "block/8_topography/4_grass_leaves/grass_overlay")
         .texture("*", "minecraft:block/sand")
         .parent(Blocks.SAND.defaultBlockState())
         .grassColor()
         .register(typesOverlay);
      VanillaProps.gravel().group(ModGroups.SAND_AND_GRAVEL).name("sand_and_gravel").texture("block/8_topography/3_sand_clay/sand_and_gravel").register(types);
      VanillaProps.sand()
         .group(ModGroups.SAND_AND_GRAVEL)
         .name("sand_and_vegetation")
         .texture("block/8_topography/3_sand_clay/sand_and_vegetation")
         .register(types);
      VanillaProps.sand().group(ModGroups.SAND_AND_GRAVEL).name("wet_sand").texture("block/8_topography/3_sand_clay/wet_sand").register(types);
      VanillaProps.gravel()
         .group(ModGroups.SAND_AND_GRAVEL)
         .name("wet_sand_and_gravel")
         .texture("block/8_topography/3_sand_clay/wet_sand_and_gravel")
         .register(types);
      VanillaProps.sand()
         .group(ModGroups.SAND_AND_GRAVEL)
         .name("wet_sand_and_vegetation")
         .texture("block/8_topography/3_sand_clay/wet_sand_and_vegetation")
         .register(types);
      VanillaProps.sand()
         .group(ModGroups.SAND_AND_GRAVEL)
         .name("red_sand")
         .texture("minecraft:block/red_sand")
         .parent(Blocks.RED_SAND.defaultBlockState())
         .register(typesVanilla);
      VanillaProps.gravel()
         .group(ModGroups.SAND_AND_GRAVEL)
         .name("red_sand_and_gravel")
         .texture("block/8_topography/3_sand_clay/red_sand_and_gravel")
         .register(types);
      VanillaProps.sand()
         .group(ModGroups.SAND_AND_GRAVEL)
         .name("red_sand_and_vegetation")
         .texture("block/8_topography/3_sand_clay/red_sand_and_vegetation")
         .register(types);
      VanillaProps.gravel()
         .group(ModGroups.SAND_AND_GRAVEL)
         .name("sandstone_debris")
         .texture("block/8_topography/6_debris_gravel/sandstone_debris")
         .register(types);
      VanillaProps.gravel()
         .group(ModGroups.SAND_AND_GRAVEL)
         .name("mossy_sandstone_debris")
         .grassColor()
         .render(RenderLayer.CUTOUT_MIPPED_SOLID)
         .texture("overlay", "block/8_topography/6_debris_gravel/mossy_sandstone_debris_overlay")
         .texture("block/8_topography/6_debris_gravel/mossy_sandstone_debris")
         .register(types);
      VanillaProps.grassyStone()
         .group(ModGroups.SAND_AND_GRAVEL)
         .name("overgrown_sandstone_debris")
         .render(RenderLayer.CUTOUT_MIPPED_SOLID)
         .texture("overlay", "block/8_topography/6_debris_gravel/overgrown_sandstone_debris_overlay")
         .texture("*", "block/8_topography/6_debris_gravel/overgrown_sandstone_debris")
         .grassColor()
         .register(types);
      VanillaProps.gravel()
         .group(ModGroups.SAND_AND_GRAVEL)
         .name("brown_sandstone_debris")
         .texture("block/8_topography/6_debris_gravel/brown_sandstone_debris")
         .register(types);
      VanillaProps.gravel().group(ModGroups.SAND_AND_GRAVEL).name("schist_debris").texture("block/8_topography/6_debris_gravel/schist_debris").register(types);
      VanillaProps.gravel()
         .group(ModGroups.SAND_AND_GRAVEL)
         .name("mossy_schist_debris")
         .texture("block/8_topography/6_debris_gravel/mossy_schist_debris")
         .register(types);
      VanillaProps.gravel()
         .group(ModGroups.GRASS_AND_DIRT)
         .name("coarse_sandy_soil")
         .texture("block/8_topography/6_debris_gravel/coarse_sandy_soil")
         .register(types);
      VanillaProps.gravel()
         .group(ModGroups.SAND_AND_GRAVEL)
         .name("dark_gray_limestone_gravel")
         .texture("block/8_topography/6_debris_gravel/dark_gray_limestone_gravel")
         .register(types);
      VanillaProps.gravel()
         .group(ModGroups.SAND_AND_GRAVEL)
         .name("gray_limestone_gravel")
         .texture("block/8_topography/6_debris_gravel/gray_limestone_gravel")
         .register(types);
      VanillaProps.gravel()
         .group(ModGroups.SAND_AND_GRAVEL)
         .name("mossy_gray_limestone_gravel")
         .grassColor()
         .render(RenderLayer.CUTOUT_MIPPED_SOLID)
         .texture("overlay", "block/8_topography/5_dirt/mossy_overlay")
         .texture("block/8_topography/6_debris_gravel/gray_limestone_gravel")
         .register(types);
      VanillaProps.grassyStone()
         .group(ModGroups.SAND_AND_GRAVEL)
         .name("overgrown_gray_limestone_gravel")
         .render(RenderLayer.CUTOUT_MIPPED_SOLID)
         .texture("overlay", "block/8_topography/5_dirt/overgrown_overlay")
         .texture("*", "block/8_topography/6_debris_gravel/gray_limestone_gravel")
         .grassColor()
         .register(types);
      VanillaProps.gravel().group(ModGroups.SAND_AND_GRAVEL).name("dark_gravel").texture("block/8_topography/6_debris_gravel/dark_gravel").register(types);
      VanillaProps.gravel().group(ModGroups.SAND_AND_GRAVEL).name("gray_gravel").texture("block/8_topography/6_debris_gravel/gray_gravel").register(types);
      VanillaProps.gravel()
         .group(ModGroups.SAND_AND_GRAVEL)
         .name("gravel")
         .texture("minecraft:block/gravel")
         .parent(Blocks.GRAVEL.defaultBlockState())
         .register(typesVanilla);
      VanillaProps.gravel().group(ModGroups.SAND_AND_GRAVEL).name("light_gravel").texture("block/8_topography/6_debris_gravel/light_gravel").register(types);
      VanillaProps.gravel().group(ModGroups.SAND_AND_GRAVEL).name("white_gravel").texture("block/8_topography/6_debris_gravel/white_gravel").register(types);
      VanillaProps.grassyGravel()
         .group(ModGroups.SAND_AND_GRAVEL)
         .name("grassy_gravel")
         .texture("overlay", "block/8_topography/4_grass_leaves/grass_overlay")
         .texture("*", "minecraft:block/gravel")
         .parent(Blocks.GRAVEL.defaultBlockState())
         .grassColor()
         .register(typesOverlay);
      VanillaProps.gravel()
         .group(ModGroups.SAND_AND_GRAVEL)
         .name("dark_limestone_debris")
         .texture("block/8_topography/6_debris_gravel/dark_limestone_debris")
         .register(types);
      VanillaProps.gravel()
         .group(ModGroups.SAND_AND_GRAVEL)
         .name("pale_limestone_debris")
         .texture("block/8_topography/6_debris_gravel/pale_limestone_debris")
         .register(types);
      VanillaProps.gravel()
         .group(ModGroups.SAND_AND_GRAVEL)
         .name("mossy_pale_limestone_debris")
         .grassColor()
         .render(RenderLayer.CUTOUT_MIPPED_SOLID)
         .texture("overlay", "block/8_topography/5_dirt/mossy_overlay")
         .texture("block/8_topography/6_debris_gravel/pale_limestone_debris")
         .register(types);
      VanillaProps.grassyStone()
         .group(ModGroups.SAND_AND_GRAVEL)
         .name("overgrown_pale_limestone_debris")
         .render(RenderLayer.CUTOUT_MIPPED_SOLID)
         .texture("overlay", "block/8_topography/5_dirt/overgrown_overlay")
         .texture("*", "block/8_topography/6_debris_gravel/pale_limestone_debris")
         .grassColor()
         .register(types);
      VanillaProps.gravel().group(ModGroups.SAND_AND_GRAVEL).name("small_stones").texture("block/8_topography/6_debris_gravel/small_stones").register(types);
      VanillaProps.grassyStone()
         .group(ModGroups.SAND_AND_GRAVEL)
         .name("grassy_small_stones")
         .texture("overlay", "block/8_topography/4_grass_leaves/grass_overlay")
         .texture("*", "block/8_topography/6_debris_gravel/small_stones")
         .grassColor()
         .parent(Blocks.GRAVEL.defaultBlockState())
         .register(typesOverlay);
      VanillaProps.grassyStone()
         .group(ModGroups.SAND_AND_GRAVEL)
         .name("overgrown_small_stones")
         .render(RenderLayer.CUTOUT_MIPPED_SOLID)
         .texture("overlay", "block/8_topography/6_debris_gravel/overgrown_small_stones_overlay")
         .texture("*", "block/8_topography/6_debris_gravel/overgrown_small_stones")
         .grassColor()
         .register(types);
      VanillaProps.grassyStone()
         .group(ModGroups.SAND_AND_GRAVEL)
         .name("mossy_small_stones")
         .render(RenderLayer.CUTOUT_MIPPED_SOLID)
         .texture("overlay", "block/8_topography/6_debris_gravel/mossy_small_stones_overlay")
         .texture("*", "block/8_topography/6_debris_gravel/mossy_small_stones")
         .grassColor()
         .register(types);
      VanillaProps.gravel().group(ModGroups.SAND_AND_GRAVEL).name("brown_gravel").texture("block/8_topography/6_debris_gravel/brown_gravel").register(types);
      VanillaProps.gravel()
         .group(ModGroups.SAND_AND_GRAVEL)
         .name("mossy_brown_gravel")
         .grassColor()
         .render(RenderLayer.CUTOUT_MIPPED_SOLID)
         .texture("overlay", "block/8_topography/5_dirt/mossy_overlay")
         .texture("block/8_topography/6_debris_gravel/brown_gravel")
         .register(types);
      VanillaProps.grassyStone()
         .group(ModGroups.SAND_AND_GRAVEL)
         .name("overgrown_brown_gravel")
         .render(RenderLayer.CUTOUT_MIPPED_SOLID)
         .texture("overlay", "block/8_topography/5_dirt/overgrown_overlay")
         .texture("*", "block/8_topography/6_debris_gravel/brown_gravel")
         .grassColor()
         .register(types);
      VanillaProps.earth()
         .group(ModGroups.SAND_AND_GRAVEL)
         .name("zen_garden_dirt")
         .texture("block/8_topography/6_debris_gravel/zen_garden_dirt")
         .register(types);
      VanillaProps.gravel()
         .group(ModGroups.SAND_AND_GRAVEL)
         .name("smooth_pebbles")
         .texture("block/8_topography/6_debris_gravel/smooth_pebbles")
         .register(types);
      VanillaProps.stone()
         .group(ModGroups.SAND_AND_GRAVEL)
         .name("netherrack")
         .texture("minecraft:block/netherrack")
         .parent(Blocks.NETHERRACK.defaultBlockState())
         .register(typesVanilla);
      VanillaProps.earth()
         .group(ModGroups.SAND_AND_GRAVEL)
         .name("soul_sand")
         .texture("minecraft:block/soul_sand")
         .parent(Blocks.SOUL_SAND.defaultBlockState())
         .register(typesVanilla);
      VanillaProps.sand().group(ModGroups.SAND_AND_GRAVEL).name("ash").texture("block/8_topography/6_debris_gravel/ash").register(types);
      VanillaProps.sand().group(ModGroups.SAND_AND_GRAVEL).name("dry_river_sand").texture("block/8_topography/3_sand_clay/dry_river_sand").register(types);
      VanillaProps.sand().group(ModGroups.SAND_AND_GRAVEL).name("wet_river_sand").texture("block/8_topography/3_sand_clay/wet_river_sand").register(types);
      VanillaProps.earth()
         .group(ModGroups.SAND_AND_GRAVEL)
         .name("ancient_debris")
         .texture("top", "minecraft:block/ancient_debris_top")
         .texture("bottom", "minecraft:block/ancient_debris_top")
         .texture("*", "minecraft:block/ancient_debris_side")
         .parent(Blocks.ANCIENT_DEBRIS.defaultBlockState())
         .register(typesVanilla);
      VanillaProps.earth()
         .group(ModGroups.SAND_AND_GRAVEL)
         .name("soul_soil")
         .texture("minecraft:block/soul_soil")
         .parent(Blocks.SOUL_SOIL.defaultBlockState())
         .register(typesVanilla);
      VanillaProps.sand().group(ModGroups.SAND_AND_GRAVEL).name("blue_salt").texture("block/8_topography/3_sand_clay/blue_salt").register(types);
      VanillaProps.sand().group(ModGroups.SAND_AND_GRAVEL).name("gray_sand").texture("block/8_topography/3_sand_clay/gray_sand").register(types);
      VanillaProps.sand().group(ModGroups.SAND_AND_GRAVEL).name("light_gray_sand").texture("block/8_topography/3_sand_clay/light_gray_sand").register(types);
      VanillaProps.sand().group(ModGroups.SAND_AND_GRAVEL).name("lime_sand").texture("block/8_topography/3_sand_clay/lime_sand").register(types);
      VanillaProps.sand().group(ModGroups.SAND_AND_GRAVEL).name("pink_salt").texture("block/8_topography/3_sand_clay/pink_salt").register(types);
      VanillaProps.sand().group(ModGroups.SAND_AND_GRAVEL).name("pink_sand").texture("block/8_topography/3_sand_clay/pink_sand").register(types);
      VanillaProps.sand().group(ModGroups.SAND_AND_GRAVEL).name("purple_sand").texture("block/8_topography/3_sand_clay/purple_sand").register(types);
      VanillaProps.sand().group(ModGroups.SAND_AND_GRAVEL).name("wet_gray_sand").texture("block/8_topography/3_sand_clay/wet_gray_sand").register(types);
      VanillaProps.sand()
         .group(ModGroups.SAND_AND_GRAVEL)
         .name("wet_light_gray_sand")
         .texture("block/8_topography/3_sand_clay/wet_light_gray_sand")
         .register(types);
      VanillaProps.sand().group(ModGroups.SAND_AND_GRAVEL).name("wet_lime_sand").texture("block/8_topography/3_sand_clay/wet_lime_sand").register(types);
      VanillaProps.sand().group(ModGroups.SAND_AND_GRAVEL).name("wet_pink_sand").texture("block/8_topography/3_sand_clay/wet_pink_sand").register(types);
      VanillaProps.sand().group(ModGroups.SAND_AND_GRAVEL).name("wet_purple_sand").texture("block/8_topography/3_sand_clay/wet_purple_sand").register(types);
      VanillaProps.sand().group(ModGroups.SAND_AND_GRAVEL).name("white_salt").texture("block/8_topography/3_sand_clay/white_salt").register(types);
      VanillaProps.sand().group(ModGroups.SAND_AND_GRAVEL).name("yellow_sulfur").texture("block/8_topography/3_sand_clay/yellow_sulfur").register(types);
      VanillaProps.sand().group(ModGroups.SAND_AND_GRAVEL).name("dark_brown_sand").texture("block/8_topography/3_sand_clay/dark_brown_sand").register(types);
      VanillaProps.sand().group(ModGroups.SAND_AND_GRAVEL).name("brown_sand").texture("block/8_topography/3_sand_clay/brown_sand").register(types);
      VanillaProps.gravel()
         .group(ModGroups.SAND_AND_GRAVEL)
         .name("mossy_brown_sand")
         .grassColor()
         .render(RenderLayer.CUTOUT_MIPPED_SOLID)
         .texture("overlay", "block/8_topography/6_debris_gravel/mossy_sand_overlay")
         .texture("block/8_topography/6_debris_gravel/brown_sand")
         .register(types);
      VanillaProps.gravel()
         .group(ModGroups.SAND_AND_GRAVEL)
         .name("overgrown_brown_sand")
         .grassColor()
         .render(RenderLayer.CUTOUT_MIPPED_SOLID)
         .texture("overlay", "block/8_topography/6_debris_gravel/overgrown_sand_overlay")
         .texture("*", "block/8_topography/6_debris_gravel/brown_sand")
         .register(types);
      VanillaProps.sand().group(ModGroups.SAND_AND_GRAVEL).name("tan_sand").texture("block/8_topography/3_sand_clay/tan_sand").register(types);
      VanillaProps.sand()
         .group(ModGroups.SAND_AND_GRAVEL)
         .name("mossy_tan_sand")
         .grassColor()
         .render(RenderLayer.CUTOUT_MIPPED_SOLID)
         .texture("overlay", "block/8_topography/6_debris_gravel/mossy_sand_overlay")
         .texture("block/8_topography/6_debris_gravel/tan_sand")
         .register(types);
      VanillaProps.sand()
         .group(ModGroups.SAND_AND_GRAVEL)
         .name("overgrown_tan_sand")
         .render(RenderLayer.CUTOUT_MIPPED_SOLID)
         .texture("overlay", "block/8_topography/6_debris_gravel/overgrown_sandstone_debris_overlay")
         .texture("*", "block/8_topography/6_debris_gravel/tan_sand")
         .grassColor()
         .register(types);
      VanillaProps.sand().group(ModGroups.SAND_AND_GRAVEL).name("wet_brown_sand").texture("block/8_topography/3_sand_clay/wet_brown_sand").register(types);
      VanillaProps.sand().group(ModGroups.SAND_AND_GRAVEL).name("wet_tan_sand").texture("block/8_topography/3_sand_clay/wet_tan_sand").register(types);
      VanillaProps.sand().group(ModGroups.SAND_AND_GRAVEL).name("orange_sand").texture("block/8_topography/3_sand_clay/orange_sand").register(types);
      VanillaProps.sand()
         .group(ModGroups.SAND_AND_GRAVEL)
         .name("mossy_sand")
         .grassColor()
         .render(RenderLayer.CUTOUT_MIPPED_SOLID)
         .texture("overlay", "block/8_topography/6_debris_gravel/mossy_sand_overlay")
         .texture("*", "minecraft:block/sand")
         .register(types);
      VanillaProps.sand()
         .group(ModGroups.SAND_AND_GRAVEL)
         .name("overgrown_sand")
         .grassColor()
         .render(RenderLayer.CUTOUT_MIPPED_SOLID)
         .texture("overlay", "block/8_topography/6_debris_gravel/overgrown_sand_overlay")
         .texture("*", "minecraft:block/sand")
         .register(types);
   }
}
