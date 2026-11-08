package com.conquestrefabricated.content.blocks.init.blocks;

import com.conquestrefabricated.content.blocks.BlockVoxelShapes;
import com.conquestrefabricated.content.blocks.block.Balustrade;
import com.conquestrefabricated.content.blocks.block.Capital;
import com.conquestrefabricated.content.blocks.block.Pillar;
import com.conquestrefabricated.content.blocks.block.Slab;
import com.conquestrefabricated.content.blocks.block.SlabCorner;
import com.conquestrefabricated.content.blocks.block.SlabEighth;
import com.conquestrefabricated.content.blocks.block.SlabQuarter;
import com.conquestrefabricated.content.blocks.block.Sphere;
import com.conquestrefabricated.content.blocks.block.Stairs;
import com.conquestrefabricated.content.blocks.block.VerticalCornerExtendable;
import com.conquestrefabricated.content.blocks.block.VerticalQuarterExtendable;
import com.conquestrefabricated.content.blocks.block.VerticalSlabCorner;
import com.conquestrefabricated.content.blocks.block.VerticalSlabExtendable;
import com.conquestrefabricated.content.blocks.block.WallNew;
import com.conquestrefabricated.content.blocks.block.arch.ArchSmall;
import com.conquestrefabricated.content.blocks.block.arch.ArchSmallHalf;
import com.conquestrefabricated.content.blocks.block.arch.ArchTwoMeter;
import com.conquestrefabricated.content.blocks.block.arch.ArchTwoMeterHalf;
import com.conquestrefabricated.content.blocks.block.directional.HorizontalDirectional;
import com.conquestrefabricated.content.blocks.block.overlay_top.inverted.TopOverlayInvertedArrowSlit;
import com.conquestrefabricated.content.blocks.block.overlay_top.inverted.TopOverlayInvertedBalustrade;
import com.conquestrefabricated.content.blocks.block.overlay_top.inverted.TopOverlayInvertedCapital;
import com.conquestrefabricated.content.blocks.block.overlay_top.inverted.TopOverlayInvertedPillar;
import com.conquestrefabricated.content.blocks.block.overlay_top.inverted.TopOverlayInvertedSlab;
import com.conquestrefabricated.content.blocks.block.overlay_top.inverted.TopOverlayInvertedSlabCorner;
import com.conquestrefabricated.content.blocks.block.overlay_top.inverted.TopOverlayInvertedSlabEighth;
import com.conquestrefabricated.content.blocks.block.overlay_top.inverted.TopOverlayInvertedSlabQuarter;
import com.conquestrefabricated.content.blocks.block.overlay_top.inverted.TopOverlayInvertedStairs;
import com.conquestrefabricated.content.blocks.block.overlay_top.inverted.TopOverlayInvertedVerticalCorner;
import com.conquestrefabricated.content.blocks.block.overlay_top.inverted.TopOverlayInvertedVerticalQuarter;
import com.conquestrefabricated.content.blocks.block.overlay_top.inverted.TopOverlayInvertedVerticalSlab;
import com.conquestrefabricated.content.blocks.block.overlay_top.inverted.TopOverlayInvertedVerticalSlabCorner;
import com.conquestrefabricated.content.blocks.block.overlay_top.inverted.TopOverlayInvertedWindowSmall;
import com.conquestrefabricated.content.blocks.block.overlay_top.inverted.TopOverlayInvertedWindowSmallHalf;
import com.conquestrefabricated.content.blocks.block.windows.ArrowSlit;
import com.conquestrefabricated.content.blocks.block.windows.WindowSmall;
import com.conquestrefabricated.content.blocks.block.windows.WindowSmallHalf;
import com.conquestrefabricated.core.block.builder.VanillaProps;
import com.conquestrefabricated.core.block.factory.TypeList;
import com.conquestrefabricated.core.util.RenderLayer;
import net.minecraft.world.level.block.Blocks;

public class RefinedStoneCobbleBrickInit {
   public RefinedStoneCobbleBrickInit() {
   }

   public static void init(TypeList types, TypeList typesVanilla, TypeList typesVanillaNoWall, TypeList typesOverlayTop, TypeList typesMinimal) {
      VanillaProps.stone()
         .name("worn_dark_lime_mortar_masonry")
         .texture("*", "block/1_basic_refined/1_stone/3_limestone/worn_dark_lime_mortar_masonry")
         .register(types);
      VanillaProps.stone().name("dark_lime_mortar_masonry").texture("*", "block/1_basic_refined/1_stone/3_limestone/dark_lime_mortar_masonry").register(types);
      VanillaProps.stone().name("lime_mortar_masonry").texture("*", "block/1_basic_refined/1_stone/3_limestone/lime_mortar_masonry").register(types);
      VanillaProps.stone()
         .name("light_lime_mortar_masonry")
         .texture("*", "block/1_basic_refined/1_stone/3_limestone/light_lime_mortar_masonry")
         .register(types);
      VanillaProps.stone()
         .name("worn_plastered_lime_mortar_masonry")
         .texture("*", "block/1_basic_refined/1_stone/3_limestone/worn_plastered_lime_mortar_masonry")
         .register(types);
      VanillaProps.stone()
         .name("plastered_lime_mortar_masonry")
         .texture("*", "block/1_basic_refined/1_stone/3_limestone/plastered_lime_mortar_masonry")
         .register(types);
      VanillaProps.stone()
         .name("muddy_limestone_fanpattern_setts")
         .texture("top", "block/1_basic_refined/1_stone/3_limestone/muddy_limestone_fanpattern_setts_top")
         .texture("*", "block/1_basic_refined/1_stone/3_limestone/muddy_limestone_fanpattern_setts")
         .register(types);
      VanillaProps.stone().name("limestone_cobble").texture("minecraft:block/cobblestone").parent(Blocks.COBBLESTONE.defaultBlockState()).register(typesVanillaNoWall);
      VanillaProps.stone()
         .name("limestone_bricks", "limestone_brick")
         .texture("minecraft:block/stone_bricks")
         .parent(Blocks.STONE_BRICKS.defaultBlockState())
         .register(typesVanillaNoWall);
      VanillaProps.grassyStone()
         .name("mossy_limestone_bricks", "mossy_limestone_brick")
         .manual()
         .grassColor()
         .parent(Blocks.MOSSY_STONE_BRICKS.defaultBlockState())
         .register(
            TypeList.of(
               ArchSmall.class,
               ArchSmallHalf.class,
               ArchTwoMeter.class,
               ArchTwoMeterHalf.class,
               TopOverlayInvertedArrowSlit.class,
               TopOverlayInvertedWindowSmall.class,
               TopOverlayInvertedWindowSmallHalf.class,
               TopOverlayInvertedBalustrade.class,
               TopOverlayInvertedCapital.class,
               Sphere.class,
               TopOverlayInvertedSlab.class,
               TopOverlayInvertedSlabQuarter.class,
               TopOverlayInvertedSlabCorner.class,
               TopOverlayInvertedSlabEighth.class,
               TopOverlayInvertedVerticalSlabCorner.class,
               TopOverlayInvertedVerticalSlab.class,
               TopOverlayInvertedVerticalCorner.class,
               TopOverlayInvertedVerticalQuarter.class,
               TopOverlayInvertedStairs.class,
               WallNew.class,
               TopOverlayInvertedPillar.class
            )
         );
      VanillaProps.stone()
         .name("cracked_limestone_bricks", "cracked_limestone_brick")
         .texture("minecraft:block/cracked_stone_bricks")
         .parent(Blocks.CRACKED_STONE_BRICKS.defaultBlockState())
         .register(
            TypeList.of(
               ArchSmall.class,
               ArchSmallHalf.class,
               ArchTwoMeter.class,
               ArchTwoMeterHalf.class,
               ArrowSlit.class,
               WindowSmall.class,
               WindowSmallHalf.class,
               Balustrade.class,
               Capital.class,
               Sphere.class,
               Slab.class,
               SlabQuarter.class,
               SlabCorner.class,
               SlabEighth.class,
               VerticalSlabCorner.class,
               VerticalSlabExtendable.class,
               VerticalCornerExtendable.class,
               VerticalQuarterExtendable.class,
               Stairs.class,
               WallNew.class,
               Pillar.class
            )
         );
      VanillaProps.stone()
         .name("clay_bricks", "clay_brick")
         .texture("top", "minecraft:block/bricks_topbottom")
         .texture("bottom", "minecraft:block/bricks_topbottom")
         .texture("*", "minecraft:block/bricks")
         .parent(Blocks.BRICKS.defaultBlockState())
         .register(typesVanillaNoWall);
      VanillaProps.stone()
         .name("red_sandstone_cobble")
         .texture("top", "minecraft:block/red_sandstone_top")
         .texture("bottom", "minecraft:block/red_sandstone_bottom")
         .texture("minecraft:block/red_sandstone")
         .parent(Blocks.RED_SANDSTONE.defaultBlockState())
         .register(typesVanillaNoWall);
      VanillaProps.stone()
         .name("sandstone")
         .texture("top", "minecraft:block/sandstone_top")
         .texture("bottom", "minecraft:block/sandstone_bottom")
         .texture("minecraft:block/sandstone")
         .parent(Blocks.SANDSTONE.defaultBlockState())
         .register(typesVanillaNoWall);
      VanillaProps.stone()
         .name("marble_bricks", "marble_brick")
         .texture("top", "minecraft:block/quartz_block_top")
         .texture("bottom", "minecraft:block/quartz_block_bottom")
         .texture("minecraft:block/quartz_block_side")
         .parent(Blocks.QUARTZ_BLOCK.defaultBlockState())
         .register(typesVanilla);
      VanillaProps.stone()
         .name("prismarine_bricks", "prismarine_brick")
         .texture("minecraft:block/prismarine_bricks")
         .parent(Blocks.PRISMARINE_BRICKS.defaultBlockState())
         .register(typesVanilla);
      VanillaProps.stone().name("dark_prismarine").texture("minecraft:block/dark_prismarine").parent(Blocks.DARK_PRISMARINE.defaultBlockState()).register(typesVanilla);
      VanillaProps.stone()
         .name("end_stone_bricks", "end_stone_brick")
         .texture("minecraft:block/end_stone_bricks")
         .parent(Blocks.END_STONE_BRICKS.defaultBlockState())
         .register(typesVanillaNoWall);
      VanillaProps.stone().name("carved_limestone").texture("block/1_basic_refined/1_stone/3_limestone/carved_limestone").register(types);
      VanillaProps.stone().name("rough_carved_limestone").texture("block/1_basic_refined/1_stone/3_limestone/rough_carved_limestone").register(types);
      VanillaProps.stone()
         .name("dry_limestone_wall")
         .texture("top", "block/1_basic_refined/1_stone/3_limestone/dry_limestone_wall_top")
         .texture("*", "block/1_basic_refined/1_stone/3_limestone/dry_limestone_wall")
         .register(types);
      VanillaProps.stone().name("dry_limestone_wall_1").texture("*", "block/1_basic_refined/1_stone/3_limestone/dry_limestone_wall_1").register(types);
      VanillaProps.grassyStone()
         .name("heavily_overgrown_limestone_cobble")
         .texture("top", "block/1_basic_refined/1_stone/3_limestone/heavily_overgrown_limestone_cobble_top")
         .texture("bottom", "block/1_basic_refined/1_stone/3_limestone/heavily_overgrown_limestone_cobble_bottom")
         .texture("*", "block/1_basic_refined/1_stone/3_limestone/heavily_overgrown_limestone_cobble")
         .register(types);
      VanillaProps.stone().name("light_limestone_brick").texture("block/1_basic_refined/1_stone/3_limestone/light_limestone_brick").register(types);
      VanillaProps.stone().name("light_limestone_cobble").texture("block/1_basic_refined/1_stone/3_limestone/light_limestone_cobble").register(types);
      VanillaProps.stone().name("light_small_limestone_brick").texture("block/1_basic_refined/1_stone/3_limestone/light_small_limestone_brick").register(types);
      VanillaProps.stone()
         .name("dark_limestone_ashlars", "dark_limestone_ashlar")
         .texture("block/1_basic_refined/1_stone/3_limestone/dark_limestone_ashlar")
         .register(types);
      VanillaProps.stone().name("loose_small_limestones").texture("block/1_basic_refined/1_stone/3_limestone/loose_small_limestones").register(types);
      VanillaProps.stone()
         .name("mossy_limestone_cobble")
         .texture("minecraft:block/mossy_cobblestone")
         .parent(Blocks.MOSSY_COBBLESTONE.defaultBlockState())
         .register(typesVanillaNoWall);
      VanillaProps.grassyStone().name("overgrown_limestone_cobble").manual().render(RenderLayer.CUTOUT_MIPPED_SOLID).grassColor().register(typesOverlayTop);
      VanillaProps.stone().name("overgrown_small_limestones").texture("block/1_basic_refined/1_stone/3_limestone/overgrown_small_limestones").register(types);
      VanillaProps.stone().name("patterned_limestone_brick").texture("block/1_basic_refined/1_stone/3_limestone/patterned_limestone_brick").register(types);
      VanillaProps.stone()
         .name("snow_covered_limestone_cobble")
         .texture("top", "block/1_basic_refined/1_stone/3_limestone/snow_covered_limestone_cobble_top")
         .texture("*", "block/1_basic_refined/1_stone/3_limestone/snow_covered_limestone_cobble")
         .register(types);
      VanillaProps.stone().name("weathered_limestone_brick").texture("block/1_basic_refined/1_stone/3_limestone/weathered_limestone_brick").register(types);
      VanillaProps.stone().name("worn_light_limestone_cobble").texture("block/1_basic_refined/1_stone/3_limestone/worn_light_limestone_cobble").register(types);
      VanillaProps.stone().name("lime_mortar_clay_brick").texture("block/1_basic_refined/1_stone/1_clay/lime_mortar_clay_brick").register(types);
      VanillaProps.stone().name("lime_mortar_orange_clay_brick").texture("block/1_basic_refined/1_stone/1_clay/lime_mortar_orange_clay_brick").register(types);
      VanillaProps.stone()
         .name("vertical_lime_mortar_orange_clay_brick")
         .texture("block/1_basic_refined/1_stone/1_clay/vertical_lime_mortar_orange_clay_brick")
         .register(types);
      VanillaProps.stone()
         .name("lime_mortar_red_clay_brick")
         .texture("top", "block/1_basic_refined/1_stone/1_clay/lime_mortar_red_clay_brick_topbottom")
         .texture("bottom", "block/1_basic_refined/1_stone/1_clay/lime_mortar_red_clay_brick_topbottom")
         .texture("*", "block/1_basic_refined/1_stone/1_clay/lime_mortar_red_clay_brick")
         .register(types);
      VanillaProps.stone()
         .name("mossy_clay_brick")
         .texture("top", "block/1_basic_refined/1_stone/1_clay/mossy_clay_brick_topbottom")
         .texture("bottom", "block/1_basic_refined/1_stone/1_clay/mossy_clay_brick_topbottom")
         .texture("*", "block/1_basic_refined/1_stone/1_clay/mossy_clay_brick")
         .register(types);
      VanillaProps.stone()
         .name("mossy_lime_mortar_red_clay_brick")
         .texture("top", "block/1_basic_refined/1_stone/1_clay/mossy_lime_mortar_red_clay_brick_topbottom")
         .texture("bottom", "block/1_basic_refined/1_stone/1_clay/mossy_lime_mortar_red_clay_brick_topbottom")
         .texture("*", "block/1_basic_refined/1_stone/1_clay/mossy_lime_mortar_red_clay_brick")
         .register(types);
      VanillaProps.stone()
         .name("mossy_red_clay_brick")
         .texture("top", "block/1_basic_refined/1_stone/1_clay/mossy_red_clay_brick_topbottom")
         .texture("bottom", "block/1_basic_refined/1_stone/1_clay/mossy_red_clay_brick_topbottom")
         .texture("*", "block/1_basic_refined/1_stone/1_clay/mossy_red_clay_brick")
         .register(types);
      VanillaProps.stone()
         .name("red_clay_brick")
         .texture("top", "block/1_basic_refined/1_stone/1_clay/red_clay_brick_topbottom")
         .texture("bottom", "block/1_basic_refined/1_stone/1_clay/red_clay_brick_topbottom")
         .texture("*", "block/1_basic_refined/1_stone/1_clay/red_clay_brick")
         .register(types);
      VanillaProps.stone().name("roman_clay_brick").texture("block/1_basic_refined/1_stone/1_clay/roman_clay_brick").register(types);
      VanillaProps.stone().name("roman_orange_clay_brick").texture("block/1_basic_refined/1_stone/1_clay/roman_orange_clay_brick").register(types);
      VanillaProps.stone()
         .name("vertical_roman_orange_clay_brick")
         .texture("block/1_basic_refined/1_stone/1_clay/vertical_roman_orange_clay_brick")
         .register(types);
      VanillaProps.stone().name("roman_tan_clay_brick").texture("block/1_basic_refined/1_stone/1_clay/roman_tan_clay_brick").register(types);
      VanillaProps.stone().name("granite_brick").texture("block/1_basic_refined/1_stone/2_granite/granite_brick").register(types);
      VanillaProps.stone().name("granite_cobble").texture("block/1_basic_refined/1_stone/2_granite/granite_cobble").register(types);
      VanillaProps.stone()
         .name("granite_cyclopean_masonry")
         .texture("top", "block/1_basic_refined/1_stone/2_granite/granite_cyclopean_masonry_topbottom")
         .texture("bottom", "block/1_basic_refined/1_stone/2_granite/granite_cyclopean_masonry_topbottom")
         .texture("*", "block/1_basic_refined/1_stone/2_granite/granite_cyclopean_masonry")
         .register(types);
      VanillaProps.stone().name("dokimeion_blue_marble").texture("block/1_basic_refined/1_stone/4_marble/dokimeion_blue_marble").register(types);
      VanillaProps.stone().name("carrara_white_marble").texture("block/1_basic_refined/1_stone/4_marble/carrara_white_marble").register(types);
      VanillaProps.stone().name("fior_de_bosco_gray_marble").texture("block/1_basic_refined/1_stone/4_marble/fior_de_bosco_gray_marble").register(types);
      VanillaProps.stone().name("nero_africano_black_marble").texture("block/1_basic_refined/1_stone/4_marble/nero_africano_black_marble").register(types);
      VanillaProps.stone().name("portasanta_pink_marble").texture("block/1_basic_refined/1_stone/4_marble/portasanta_pink_marble").register(types);
      VanillaProps.stone().name("rosso_antico_red_marble").texture("block/1_basic_refined/1_stone/4_marble/rosso_antico_red_marble").register(types);
      VanillaProps.stone().name("travertine_brick").texture("block/1_basic_refined/1_stone/4_marble/travertine_brick").register(types);
      VanillaProps.stone()
         .name("travertine_slab")
         .texture("top", "block/1_basic_refined/1_stone/4_marble/travertine_slab_topbottom")
         .texture("bottom", "block/1_basic_refined/1_stone/4_marble/travertine_slab_topbottom")
         .texture("*", "block/1_basic_refined/1_stone/4_marble/travertine_slab")
         .register(types);
      VanillaProps.stone().name("verde_antico_green_marble").texture("block/1_basic_refined/1_stone/4_marble/verde_antico_green_marble").register(types);
      VanillaProps.stone().name("brown_sandstone_brick").texture("block/1_basic_refined/1_stone/5_sandstone/brown_sandstone_brick").register(types);
      VanillaProps.stone().name("tan_sandstone_cobble").texture("block/1_basic_refined/1_stone/5_sandstone/tan_sandstone_cobble").register(types);
      VanillaProps.stone()
         .name("brown_sandstone_cobble")
         .texture("top", "minecraft:block/red_sandstone_top")
         .texture("bottom", "minecraft:block/red_sandstone_top")
         .texture("*", "block/1_basic_refined/1_stone/5_sandstone/brown_sandstone_cobble")
         .register(types);
      VanillaProps.stone().name("irregular_sandstone_tiles").texture("block/1_basic_refined/1_stone/5_sandstone/irregular_sandstone_tiles").register(types);
      VanillaProps.stone()
         .name("mossy_sandstone")
         .texture("top", "block/1_basic_refined/1_stone/5_sandstone/mossy_sandstone_topbottom")
         .texture("bottom", "block/1_basic_refined/1_stone/5_sandstone/mossy_sandstone_topbottom")
         .texture("*", "block/1_basic_refined/1_stone/5_sandstone/mossy_sandstone")
         .register(types);
      VanillaProps.stone().name("orange_sandstone_cobble").texture("block/1_basic_refined/1_stone/5_sandstone/orange_sandstone_cobble").register(types);
      VanillaProps.stone().name("red_sandstone_brick").texture("block/1_basic_refined/1_stone/5_sandstone/red_sandstone_brick").register(types);
      VanillaProps.stone().name("sandstone_brick").texture("block/1_basic_refined/1_stone/5_sandstone/sandstone_brick").register(types);
      VanillaProps.stone().name("small_brown_sandstone_brick").texture("block/1_basic_refined/1_stone/5_sandstone/small_brown_sandstone_brick").register(types);
      VanillaProps.stone().name("tan_sandstone_brick").texture("block/1_basic_refined/1_stone/5_sandstone/tan_sandstone_brick").register(types);
      VanillaProps.stone()
         .name("dry_slate_wall")
         .texture("top", "block/1_basic_refined/1_stone/6_slate/dry_slate_wall_topbottom")
         .texture("bottom", "block/1_basic_refined/1_stone/6_slate/dry_slate_wall_topbottom")
         .texture("*", "block/1_basic_refined/1_stone/6_slate/dry_slate_wall")
         .register(types);
      VanillaProps.stone().name("mixed_slate_wall").texture("block/1_basic_refined/1_stone/6_slate/mixed_slate_wall").register(types);
      VanillaProps.stone().name("slate_light_mortar_wall").texture("block/1_basic_refined/1_stone/6_slate/slate_light_mortar_wall").register(types);
      VanillaProps.stone()
         .name("slate_mortar_wall")
         .texture("top", "block/1_basic_refined/1_stone/6_slate/slate_mortar_wall_topbottom")
         .texture("bottom", "block/1_basic_refined/1_stone/6_slate/slate_mortar_wall_topbottom")
         .texture("*", "block/1_basic_refined/1_stone/6_slate/slate_mortar_wall")
         .register(types);
      VanillaProps.stone().name("broken_reticulated_brick").texture("block/1_basic_refined/1_stone/broken_reticulated_brick").register(types);
      VanillaProps.stone().name("reticulated_brick").texture("block/1_basic_refined/1_stone/reticulated_brick").register(types);
      VanillaProps.stone().name("graywacke_mortar_wall").texture("block/1_basic_refined/1_stone/graywacke_mortar_wall").register(types);
      VanillaProps.stone().name("irregular_andesite_stone_wall").texture("block/1_basic_refined/1_stone/irregular_andesite_stone_wall").register(types);
      VanillaProps.stone().name("mud_brick").texture("block/1_basic_refined/1_stone/mud_brick").register(types);
      VanillaProps.stone().name("red_granite_cobble").texture("block/1_basic_refined/1_stone/2_granite/red_granite_cobble").register(types);
      VanillaProps.stone().name("graywacke_cobble").texture("block/1_basic_refined/1_stone/graywacke_cobble").register(types);
      VanillaProps.stone().name("green_schist_cobble").texture("block/1_basic_refined/1_stone/green_schist_cobble").register(types);
      VanillaProps.stone().name("blue_schist_cobble").texture("block/1_basic_refined/1_stone/blue_schist_cobble").register(types);
      VanillaProps.stone().name("cliffstone_cobble").texture("block/1_basic_refined/1_stone/cliffstone_cobble").register(types);
      VanillaProps.stone()
         .name("light_red_sandstone_bricks", "light_red_sandstone_brick")
         .texture("block/1_basic_refined/1_stone/5_sandstone/light_red_sandstone_bricks")
         .register(types);
      VanillaProps.stone().name("brown_oven_tiles").texture("block/1_basic_refined/1_stone/1_clay/brown_oven_tiles").register(types);
      VanillaProps.stone().name("green_oven_tiles").texture("block/1_basic_refined/1_stone/1_clay/green_oven_tiles").register(types);
      VanillaProps.stone()
         .name("polished_blackstone")
         .texture("minecraft:block/polished_blackstone")
         .parent(Blocks.POLISHED_BLACKSTONE.defaultBlockState())
         .register(typesVanillaNoWall);
      VanillaProps.stone()
         .name("polished_blackstone_bricks")
         .texture("minecraft:block/polished_blackstone_bricks")
         .parent(Blocks.POLISHED_BLACKSTONE_BRICKS.defaultBlockState())
         .register(typesVanillaNoWall);
      VanillaProps.stone()
         .name("cracked_polished_blackstone_bricks")
         .texture("minecraft:block/polished_blackstone_bricks")
         .parent(Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS.defaultBlockState())
         .register(
            TypeList.of(
               ArchSmall.class,
               ArchSmallHalf.class,
               ArchTwoMeter.class,
               ArchTwoMeterHalf.class,
               ArrowSlit.class,
               WindowSmall.class,
               WindowSmallHalf.class,
               Balustrade.class,
               Capital.class,
               Sphere.class,
               Slab.class,
               Stairs.class,
               SlabQuarter.class,
               SlabCorner.class,
               SlabEighth.class,
               VerticalSlabCorner.class,
               VerticalSlabExtendable.class,
               VerticalCornerExtendable.class,
               VerticalQuarterExtendable.class,
               WallNew.class,
               Pillar.class
            )
         );
      VanillaProps.stone()
         .name("polished_deepslate")
         .texture("minecraft:block/polished_deepslate")
         .parent(Blocks.POLISHED_DEEPSLATE.defaultBlockState())
         .register(typesVanillaNoWall);
      VanillaProps.stone().name("light_granite_cobble").texture("block/1_basic_refined/1_stone/2_granite/light_granite_cobble").register(types);
      VanillaProps.stone().name("tan_limestone_bricks").texture("block/1_basic_refined/1_stone/3_limestone/tan_limestone_bricks").register(types);
      VanillaProps.stone().name("white_limestone_bricks").texture("block/1_basic_refined/1_stone/3_limestone/white_limestone_bricks").register(types);
      VanillaProps.stone()
         .name("medium_light_red_sandstone_bricks", "medium_light_red_sandstone_brick")
         .texture("block/1_basic_refined/1_stone/5_sandstone/medium_light_red_sandstone_bricks")
         .register(types);
      VanillaProps.stone()
         .name("dark_light_red_sandstone_bricks", "dark_light_red_sandstone_brick")
         .texture("block/1_basic_refined/1_stone/5_sandstone/dark_light_red_sandstone_bricks")
         .register(types);
      VanillaProps.stone()
         .name("dark_brown_mortar_cobbles", "dark_brown_mortar_cobble")
         .texture("block/1_basic_refined/1_stone/dark_brown_mortar_cobbles")
         .register(types);
      VanillaProps.stone()
         .name("light_brown_mortar_cobbles", "light_brown_mortar_cobble")
         .texture("block/1_basic_refined/1_stone/light_brown_mortar_cobbles")
         .register(types);
      VanillaProps.stone()
         .name("eroded_off_white_ashlars", "eroded_off_white_ashlar")
         .texture("block/1_basic_refined/1_stone/eroded_off_white_ashlars")
         .register(types);
      VanillaProps.stone()
         .name("weathered_off_white_ashlars", "weathered_off_white_ashlar")
         .texture("block/1_basic_refined/1_stone/weathered_off_white_ashlars")
         .register(types);
      VanillaProps.stone().name("off_white_ashlars", "off_white_ashlar").texture("block/1_basic_refined/1_stone/off_white_ashlars").register(types);
      VanillaProps.stone().name("eroded_white_ashlars", "eroded_white_ashlar").texture("block/1_basic_refined/1_stone/eroded_white_ashlars").register(types);
      VanillaProps.stone()
         .name("weathered_white_ashlars", "weathered_white_ashlar")
         .texture("block/1_basic_refined/1_stone/weathered_white_ashlars")
         .register(types);
      VanillaProps.stone().name("stained_white_ashlars", "stained_white_ashlar").texture("block/1_basic_refined/1_stone/stained_white_ashlars").register(types);
      VanillaProps.stone().name("white_ashlars", "white_ashlar").texture("block/1_basic_refined/1_stone/white_ashlars").register(types);
      VanillaProps.stone().name("lightly_plastered_brick").texture("block/1_basic_refined/1_stone/1_clay/lightly_plastered_brick").register(types);
      VanillaProps.stone().name("worn_lightly_plastered_brick").texture("block/1_basic_refined/1_stone/1_clay/worn_lightly_plastered_brick").register(types);
      VanillaProps.stone().name("dark_roman_orange_clay_brick").texture("block/1_basic_refined/1_stone/1_clay/dark_roman_orange_clay_brick").register(types);
      VanillaProps.stone()
         .name("light_lime_mortar_orange_clay_brick")
         .texture("block/1_basic_refined/1_stone/1_clay/light_lime_mortar_orange_clay_brick")
         .register(types);
      VanillaProps.stone()
         .name("dark_lime_mortar_orange_clay_brick")
         .texture("block/1_basic_refined/1_stone/1_clay/dark_lime_mortar_orange_clay_brick")
         .register(types);
      VanillaProps.stone()
         .name("brick_pile")
         .manual()
         .render(RenderLayer.CUTOUT)
         .blocking(false)
         .with("hitBox", BlockVoxelShapes.cubePartialShape)
         .register(TypeList.of(HorizontalDirectional.OffsetXYZ.Toggle10.class));
      VanillaProps.stone().name("dark_lime_mortar_clay_brick").texture("block/1_basic_refined/1_stone/1_clay/dark_lime_mortar_clay_brick").register(types);
      VanillaProps.stone().name("red_lime_mortar_clay_brick").texture("block/1_basic_refined/1_stone/1_clay/red_lime_mortar_clay_brick").register(types);
      VanillaProps.stone()
         .name("reddish_lime_mortar_clay_brick")
         .texture("block/1_basic_refined/1_stone/1_clay/reddish_lime_mortar_clay_brick")
         .register(types);
      VanillaProps.stone().name("light_limestone_spolia").texture("block/1_basic_refined/1_stone/3_limestone/light_limestone_spolia").register(types);
      VanillaProps.stone()
         .name("mossy_dark_brown_mortar_cobbles", "mossy_dark_brown_mortar_cobble")
         .texture("block/1_basic_refined/1_stone/mossy_dark_brown_mortar_cobbles")
         .register(types);
      VanillaProps.stone()
         .name("overgrown_dark_brown_mortar_cobbles", "overgrown_dark_brown_mortar_cobble")
         .texture("block/1_basic_refined/1_stone/overgrown_dark_brown_mortar_cobbles")
         .register(types);
      VanillaProps.stone()
         .name("heavily_overgrown_dark_brown_mortar_cobbles", "heavily_overgrown_dark_brown_mortar_cobble")
         .texture("block/1_basic_refined/1_stone/heavily_overgrown_dark_brown_mortar_cobbles")
         .register(types);
      VanillaProps.stone()
         .name("worn_light_brown_mortar_cobbles", "worn_light_brown_mortar_cobble")
         .texture("block/1_basic_refined/1_stone/worn_light_brown_mortar_cobbles")
         .register(types);
      VanillaProps.stone()
         .name("clay_brick_mixed_dark_brown_mortar_cobbles", "clay_brick_mixed_dark_brown_mortar_cobble")
         .texture("block/1_basic_refined/1_stone/light_lime_mortar_orange_clay_brick_mixed_dark_brown_mortar_cobbles")
         .register(types);
      VanillaProps.stone()
         .name("clay_brick_banded_dark_brown_mortar_cobbles", "clay_brick_banded_dark_brown_mortar_cobble")
         .texture("top", "block/1_basic_refined/1_stone/1_clay/light_lime_mortar_orange_clay_brick")
         .texture("bottom", "block/1_basic_refined/1_stone/dark_brown_mortar_cobbles")
         .texture("*", "block/1_basic_refined/1_stone/light_lime_mortar_orange_clay_brick_banded_dark_brown_mortar_cobbles")
         .register(types);
      VanillaProps.stone()
         .name("gray_limestone_cobbles", "gray_limestone_cobble")
         .texture("block/1_basic_refined/1_stone/3_limestone/gray_limestone_cobbles")
         .register(types);
      VanillaProps.stone()
         .name("dark_gray_limestone_cobbles", "dark_gray_limestone_cobble")
         .texture("block/1_basic_refined/1_stone/3_limestone/dark_gray_limestone_cobbles")
         .register(types);
      VanillaProps.stone()
         .name("dirty_dark_gray_limestone_cobbles", "dirty_dark_gray_limestone_cobble")
         .texture("block/1_basic_refined/1_stone/3_limestone/dirty_dark_gray_limestone_cobbles")
         .register(types);
      VanillaProps.stone()
         .name("mossy_dark_gray_limestone_cobbles", "mossy_dark_gray_limestone_cobble")
         .texture("block/1_basic_refined/1_stone/3_limestone/mossy_dark_gray_limestone_cobbles")
         .register(types);
      VanillaProps.stone()
         .name("overgrown_dark_gray_limestone_cobbles", "overgrown_dark_gray_limestone_cobble")
         .texture("block/1_basic_refined/1_stone/3_limestone/overgrown_dark_gray_limestone_cobbles")
         .register(types);
      VanillaProps.stone()
         .name("overgrown_dark_red_lime_mortar_clay_brick")
         .texture("block/1_basic_refined/1_stone/1_clay/overgrown_dark_red_lime_mortar_clay_brick")
         .register(typesMinimal);
      VanillaProps.stone()
         .name("mossy_dark_red_lime_mortar_clay_brick")
         .texture("block/1_basic_refined/1_stone/1_clay/mossy_dark_red_lime_mortar_clay_brick")
         .register(types);
      VanillaProps.stone()
         .name("dark_red_lime_mortar_clay_brick")
         .texture("block/1_basic_refined/1_stone/1_clay/dark_red_lime_mortar_clay_brick")
         .register(typesMinimal);
      VanillaProps.stone()
         .name("dark_gray_lime_mortar_clay_brick")
         .texture("block/1_basic_refined/1_stone/1_clay/dark_gray_lime_mortar_clay_brick")
         .register(typesMinimal);
      VanillaProps.stone().name("gray_lime_mortar_clay_brick").texture("block/1_basic_refined/1_stone/1_clay/gray_lime_mortar_clay_brick").register(types);
      VanillaProps.stone()
         .name("light_gray_lime_mortar_clay_brick")
         .texture("block/1_basic_refined/1_stone/1_clay/light_gray_lime_mortar_clay_brick")
         .register(typesMinimal);
      VanillaProps.stone()
         .name("pale_lime_mortar_orange_clay_brick")
         .texture("block/1_basic_refined/1_stone/1_clay/pale_lime_mortar_orange_clay_brick")
         .register(typesMinimal);
      VanillaProps.stone()
         .name("weathered_lightly_plastered_brick")
         .texture("block/1_basic_refined/1_stone/1_clay/weathered_lightly_plastered_brick")
         .register(typesMinimal);
      VanillaProps.stone().name("plastered_brick").texture("block/1_basic_refined/1_stone/1_clay/plastered_brick").register(typesMinimal);
   }
}
