package com.conquestrefabricated.content.blocks.init.blocks;

import com.conquestrefabricated.content.blocks.block.Cube;
import com.conquestrefabricated.content.blocks.block.Door;
import com.conquestrefabricated.content.blocks.block.Pillar;
import com.conquestrefabricated.content.blocks.block.VerticalCorner;
import com.conquestrefabricated.content.blocks.block.VerticalQuarter;
import com.conquestrefabricated.content.blocks.block.VerticalSlab;
import com.conquestrefabricated.content.blocks.block.WallNew;
import com.conquestrefabricated.content.blocks.block.trees.Log;
import com.conquestrefabricated.core.block.builder.VanillaProps;
import com.conquestrefabricated.core.block.factory.TypeList;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockSetType;

public class WallDesignsNoWallInit {
   public WallDesignsNoWallInit() {
   }

   public static void init(TypeList types, TypeList typesOverlay, TypeList typesOverlayPillar) {
      VanillaProps.stone()
         .name("chiseled_polished_parian_marble_design")
         .texture("top", "minecraft:block/chiseled_quartz_block_top")
         .texture("bottom", "minecraft:block/quartz_block_bottom")
         .texture("*", "minecraft:block/chiseled_quartz_block")
         .parent(Blocks.CHISELED_QUARTZ_BLOCK.defaultBlockState())
         .register(TypeList.of(VerticalSlab.class, VerticalCorner.class, VerticalQuarter.class));
      VanillaProps.stone()
         .name("orange_plaster_with_sandstone_base")
         .texture("top", "block/2_advanced_refined/3_plaster/orange_plaster")
         .texture("bottom", "minecraft:block/sandstone_bottom")
         .texture("*", "block/2_advanced_refined/3_plaster/orange_plaster_with_sandstone_base")
         .register(types);
      VanillaProps.stone().name("red_spiral_wall_design").texture("block/2_advanced_refined/3_plaster/red_spiral_wall_design").register(types);
      VanillaProps.stone().name("wallpaper_with_gilded_border").texture("block/2_advanced_refined/3_plaster/wallpaper_with_gilded_border").register(types);
      VanillaProps.stone().name("fancy_blue_marble_design").texture("block/2_advanced_refined/1_stone/3_marble/fancy_blue_marble_design").register(types);
      VanillaProps.stone().name("wallpaper_with_fleur_de_lis").texture("block/2_advanced_refined/3_plaster/wallpaper_with_fleur_de_lis").register(types);
      VanillaProps.stone().name("blue_baroque_wallpaper").texture("block/2_advanced_refined/3_plaster/blue_baroque_wallpaper").register(types);
      VanillaProps.stone().name("red_baroque_wallpaper").texture("block/2_advanced_refined/3_plaster/red_baroque_wallpaper").register(types);
      VanillaProps.stone().name("yellow_baroque_wallpaper").texture("block/2_advanced_refined/3_plaster/yellow_baroque_wallpaper").register(types);
      VanillaProps.stone().name("blue_wallpaper").texture("block/2_advanced_refined/3_plaster/blue_wallpaper").register(types);
      VanillaProps.stone().name("green_wallpaper").texture("block/2_advanced_refined/3_plaster/green_wallpaper").register(types);
      VanillaProps.stone().name("red_wallpaper").texture("block/2_advanced_refined/3_plaster/red_wallpaper").register(types);
      VanillaProps.stone()
         .name("fancy_gray_elven_plaster")
         .texture("top", "block/2_advanced_refined/3_plaster/fancy_gray_elven_plaster_topbottom")
         .texture("bottom", "block/2_advanced_refined/3_plaster/fancy_gray_elven_plaster_topbottom")
         .texture("*", "block/2_advanced_refined/3_plaster/fancy_gray_elven_plaster")
         .register(types);
      VanillaProps.stone()
         .name("red_yellow_and_blue_checker_pattern")
         .texture("block/2_advanced_refined/3_plaster/red_yellow_and_blue_checker_pattern")
         .register(types);
      VanillaProps.stone()
         .name("white_baroque_wall")
         .texture("top", "block/2_advanced_refined/3_plaster/golden_top")
         .texture("bottom", "block/1_basic_refined/1_stone/4_marble/nero_africano_black_marble")
         .texture("*", "block/2_advanced_refined/3_plaster/white_baroque_wall")
         .register(types);
      VanillaProps.stone()
         .name("green_baroque_wall")
         .texture("top", "block/2_advanced_refined/3_plaster/golden_top")
         .texture("bottom", "block/1_basic_refined/1_stone/4_marble/nero_africano_black_marble")
         .texture("*", "block/2_advanced_refined/3_plaster/green_baroque_wall")
         .register(types);
      VanillaProps.stone()
         .name("black_baroque_wall")
         .texture("top", "block/2_advanced_refined/3_plaster/golden_top")
         .texture("bottom", "block/1_basic_refined/1_stone/4_marble/nero_africano_black_marble")
         .texture("*", "block/2_advanced_refined/3_plaster/black_baroque_wall")
         .register(types);
      VanillaProps.stone()
         .name("simple_red_socle")
         .texture("bottom", "block/2_advanced_refined/3_plaster/magenta_stucco")
         .texture("top", "minecraft:block/quartz_block_top")
         .texture("overlay", "block/2_advanced_refined/3_plaster/simple_red_socle")
         .texture("*", "block/2_advanced_refined/3_plaster/red_stucco")
         .register(typesOverlayPillar);
      VanillaProps.stone()
         .name("fancy_magenta_socle")
         .texture("bottom", "block/2_advanced_refined/3_plaster/magenta_stucco")
         .texture("top", "block/2_advanced_refined/3_plaster/golden_top")
         .texture("overlay", "block/2_advanced_refined/3_plaster/fancy_magenta_socle")
         .texture("*", "block/2_advanced_refined/3_plaster/magenta_stucco")
         .register(typesOverlayPillar);
      VanillaProps.stone()
         .name("fancy_black_socle")
         .texture("bottom", "block/2_advanced_refined/3_plaster/black_stucco")
         .texture("top", "block/2_advanced_refined/3_plaster/golden_top")
         .texture("overlay", "block/2_advanced_refined/3_plaster/fancy_black_socle")
         .texture("*", "block/2_advanced_refined/3_plaster/black_stucco")
         .register(typesOverlay);
      VanillaProps.stone()
         .name("black_painted_frieze")
         .texture("bottom", "block/2_advanced_refined/3_plaster/black_stucco")
         .texture("top", "block/2_advanced_refined/3_plaster/black_stucco")
         .texture("overlay", "block/2_advanced_refined/3_plaster/black_painted_frieze")
         .texture("*", "block/2_advanced_refined/3_plaster/black_stucco")
         .register(typesOverlay);
      VanillaProps.stone()
         .name("third_style_black_wall")
         .texture("bottom", "block/2_advanced_refined/3_plaster/black_stucco")
         .texture("top", "block/2_advanced_refined/3_plaster/black_stucco")
         .texture("overlay", "block/2_advanced_refined/3_plaster/third_style_black_wall")
         .texture("*", "block/2_advanced_refined/3_plaster/black_stucco")
         .register(typesOverlay);
      VanillaProps.stone()
         .name("third_style_red_wall_1")
         .texture("bottom", "block/2_advanced_refined/3_plaster/red_stucco")
         .texture("top", "block/2_advanced_refined/3_plaster/red_stucco")
         .texture("overlay", "block/2_advanced_refined/3_plaster/third_style_red_wall_1")
         .texture("*", "block/2_advanced_refined/3_plaster/red_stucco")
         .register(typesOverlay);
      VanillaProps.stone()
         .name("third_style_red_wall_2")
         .texture("bottom", "block/2_advanced_refined/3_plaster/red_stucco")
         .texture("top", "block/2_advanced_refined/3_plaster/golden_top")
         .texture("overlay", "block/2_advanced_refined/3_plaster/third_style_red_wall_2")
         .texture("*", "block/2_advanced_refined/3_plaster/red_stucco")
         .register(typesOverlay);
      VanillaProps.stone()
         .name("third_style_red_wall_3")
         .texture("bottom", "block/2_advanced_refined/3_plaster/red_stucco")
         .texture("top", "block/2_advanced_refined/3_plaster/golden_top")
         .texture("overlay", "block/2_advanced_refined/3_plaster/third_style_red_wall_3")
         .texture("*", "block/2_advanced_refined/3_plaster/red_stucco")
         .register(typesOverlay);
      VanillaProps.stone()
         .name("black_wall_and_magenta_cornice")
         .texture("bottom", "block/2_advanced_refined/3_plaster/black_stucco")
         .texture("top", "block/2_advanced_refined/3_plaster/golden_top")
         .texture("overlay", "block/2_advanced_refined/3_plaster/black_wall_and_magenta_cornice")
         .texture("*", "block/2_advanced_refined/3_plaster/black_stucco")
         .register(typesOverlay);
      VanillaProps.stone()
         .name("etruscan_wall_design_1")
         .texture("top", "block/2_advanced_refined/3_plaster/golden_top")
         .texture("overlay", "block/2_advanced_refined/3_plaster/etruscan_wall_design_1")
         .texture("*", "block/2_advanced_refined/3_plaster/white_plaster")
         .register(typesOverlay);
      VanillaProps.stone()
         .name("etruscan_blue_meander_pattern")
         .texture("top", "block/2_advanced_refined/3_plaster/white_plaster")
         .texture("overlay", "block/2_advanced_refined/3_plaster/etruscan_blue_meander_pattern")
         .texture("*", "block/2_advanced_refined/3_plaster/white_plaster")
         .register(typesOverlayPillar);
      VanillaProps.stone()
         .name("fourth_style_white_upper_module_1")
         .texture("top", "block/2_advanced_refined/3_plaster/white_plaster")
         .texture("bottom", "block/2_advanced_refined/3_plaster/white_plaster")
         .texture("*", "block/2_advanced_refined/3_plaster/fourth_style_white_upper_module_1")
         .register(types);
      VanillaProps.stone()
         .name("fourth_style_white_upper_module_2")
         .texture("top", "block/2_advanced_refined/3_plaster/white_plaster")
         .texture("bottom", "block/2_advanced_refined/3_plaster/white_plaster")
         .texture("*", "block/2_advanced_refined/3_plaster/fourth_style_white_upper_module_2")
         .register(types);
      VanillaProps.stone()
         .name("red_tooth_pattern_on_white_plaster")
         .texture("top", "block/2_advanced_refined/3_plaster/white_plaster")
         .texture("bottom", "block/2_advanced_refined/3_plaster/red_stucco")
         .texture("overlay", "block/2_advanced_refined/3_plaster/red_tooth_pattern_on_white_plaster")
         .texture("*", "block/2_advanced_refined/3_plaster/white_plaster")
         .register(typesOverlay);
      VanillaProps.stone()
         .name("white_meander_on_red_stucco")
         .texture("top", "minecraft:block/quartz_block_top")
         .texture("overlay", "block/2_advanced_refined/3_plaster/white_meander_on_red_stucco")
         .texture("*", "block/2_advanced_refined/3_plaster/red_stucco")
         .register(typesOverlayPillar);
      VanillaProps.stone()
         .name("white_plaster_with_red_lines")
         .texture("overlay", "block/2_advanced_refined/3_plaster/white_plaster_with_red_lines")
         .texture("*", "block/2_advanced_refined/3_plaster/white_plaster")
         .register(typesOverlay);
      VanillaProps.stone()
         .name("simple_black_socle")
         .texture("overlay", "block/2_advanced_refined/3_plaster/simple_black_socle")
         .texture("*", "block/2_advanced_refined/3_plaster/black_stucco")
         .register(typesOverlayPillar);
      VanillaProps.stone()
         .name("orange_stucco_with_red_diamond")
         .texture("bottom", "block/2_advanced_refined/3_plaster/red_stucco")
         .texture("top", "block/2_advanced_refined/3_plaster/red_stucco")
         .texture("overlay", "block/2_advanced_refined/3_plaster/orange_stucco_with_red_diamond")
         .texture("*", "block/2_advanced_refined/3_plaster/orange_stucco")
         .register(typesOverlay);
      VanillaProps.stone()
         .name("red_wall_and_black_cornice")
         .texture("bottom", "block/2_advanced_refined/3_plaster/red_stucco")
         .texture("top", "block/2_advanced_refined/3_plaster/golden_top")
         .texture("overlay", "block/2_advanced_refined/3_plaster/red_wall_and_black_cornice")
         .texture("*", "block/2_advanced_refined/3_plaster/red_stucco")
         .register(typesOverlayPillar);
      VanillaProps.stone()
         .name("red_wall_and_fancy_black_cornice")
         .texture("bottom", "block/2_advanced_refined/3_plaster/red_stucco")
         .texture("top", "block/2_advanced_refined/3_plaster/golden_top")
         .texture("overlay", "block/2_advanced_refined/3_plaster/red_wall_and_fancy_black_cornice")
         .texture("*", "block/2_advanced_refined/3_plaster/red_stucco")
         .register(typesOverlayPillar);
      VanillaProps.stone()
         .name("red_stucco_with_gold_line")
         .texture("overlay", "block/2_advanced_refined/3_plaster/red_stucco_with_gold_line")
         .texture("*", "block/2_advanced_refined/3_plaster/red_stucco")
         .register(typesOverlayPillar);
      VanillaProps.planks().name("ornate_roman_door_block").texture("block/2_advanced_refined/7_wood/ornate_roman_door_block").register(types);
      VanillaProps.planks().family("ornate_roman_door_block").name("ornate_roman_door").blockSetType(BlockSetType.IRON).register(TypeList.of(Door.class));
      VanillaProps.planks()
         .family("ornate_roman_door_block")
         .name("tall_ornate_roman_door")
         .blockSetType(BlockSetType.IRON)
         .register(TypeList.of(Door.class));
      VanillaProps.planks()
         .name("spruce_wood_panel")
         .texture("top", "minecraft:block/spruce_planks")
         .texture("bottom", "minecraft:block/spruce_planks")
         .texture("*", "block/2_advanced_refined/7_wood/spruce_wood_panel")
         .register(types);
      VanillaProps.planks()
         .name("fancy_birch_wood_panel")
         .texture("top", "block/1_basic_refined/3_wood/birch/fancy_birch_wood_topbottom")
         .texture("bottom", "block/1_basic_refined/3_wood/birch/fancy_birch_wood_topbottom")
         .texture("*", "block/2_advanced_refined/7_wood/fancy_birch_wood_panel")
         .register(types);
      VanillaProps.planks()
         .name("fancy_birch_wood_arch_panel")
         .texture("top", "block/1_basic_refined/3_wood/birch/fancy_birch_wood_topbottom")
         .texture("bottom", "block/1_basic_refined/3_wood/birch/fancy_birch_wood_topbottom")
         .texture("*", "block/2_advanced_refined/7_wood/fancy_birch_wood_arch_panel")
         .register(types);
      VanillaProps.stone()
         .name("verde_antico_orthostates")
         .texture("top", "minecraft:block/quartz_block_top")
         .texture("overlay", "block/2_advanced_refined/1_stone/3_marble/verde_antico_orthostates_overlay")
         .texture("bottom", "minecraft:block/quartz_block_bottom")
         .texture("*", "block/2_advanced_refined/1_stone/3_marble/verde_antico_orthostates")
         .register(typesOverlay);
      VanillaProps.stone()
         .name("first_style_red_stucco_panel")
         .texture("top", "block/2_advanced_refined/3_plaster/first_style_red_stucco_panel")
         .texture("overlay", "block/2_advanced_refined/3_plaster/first_style_red_stucco_panel")
         .texture("bottom", "block/2_advanced_refined/3_plaster/first_style_red_stucco_panel")
         .texture("*", "block/2_advanced_refined/3_plaster/red_stucco")
         .register(typesOverlay);
      VanillaProps.stone()
         .name("limestone_dwarven_pillar_1")
         .texture("top", "block/2_advanced_refined/1_stone/2_limestone/limestone_dwarven_pillar_topbottom")
         .texture("bottom", "block/2_advanced_refined/1_stone/2_limestone/limestone_dwarven_pillar_topbottom")
         .texture("*", "block/2_advanced_refined/1_stone/2_limestone/limestone_dwarven_pillar_1")
         .register(TypeList.of(Cube.class, VerticalSlab.class, VerticalCorner.class, VerticalQuarter.class, WallNew.class, Pillar.class));
      VanillaProps.stone()
         .name("limestone_dwarven_pillar_2")
         .texture("top", "block/2_advanced_refined/1_stone/2_limestone/limestone_dwarven_pillar_topbottom")
         .texture("bottom", "block/2_advanced_refined/1_stone/2_limestone/limestone_dwarven_pillar_topbottom")
         .texture("*", "block/2_advanced_refined/1_stone/2_limestone/limestone_dwarven_pillar_2")
         .register(types);
      VanillaProps.stone()
         .name("dark_rough_marble_dwarven_pillar_1")
         .texture("top", "block/2_advanced_refined/1_stone/3_marble/dark_rough_marble_dwarven_pillar_topbottom")
         .texture("bottom", "block/2_advanced_refined/1_stone/3_marble/dark_rough_marble_dwarven_pillar_topbottom")
         .texture("*", "block/2_advanced_refined/1_stone/3_marble/dark_rough_marble_dwarven_pillar_1")
         .register(TypeList.of(Cube.class, VerticalSlab.class, VerticalCorner.class, VerticalQuarter.class, WallNew.class, Pillar.class));
      VanillaProps.stone()
         .name("dark_rough_marble_dwarven_pillar_2")
         .texture("top", "block/2_advanced_refined/1_stone/3_marble/dark_rough_marble_dwarven_pillar_topbottom")
         .texture("bottom", "block/2_advanced_refined/1_stone/3_marble/dark_rough_marble_dwarven_pillar_topbottom")
         .texture("*", "block/2_advanced_refined/1_stone/3_marble/dark_rough_marble_dwarven_pillar_2")
         .register(types);
      VanillaProps.stone().name("gothic_checkered_plaster").texture("block/2_advanced_refined/3_plaster/gothic_checkered_plaster").register(types);
      VanillaProps.stone().name("romanesque_checkered_plaster").texture("block/2_advanced_refined/3_plaster/romanesque_checkered_plaster").register(types);
      VanillaProps.stone()
         .name("red_white_green_gothic_frieze")
         .manual()
         .register(TypeList.of(Log.class, VerticalSlab.class, VerticalCorner.class, VerticalQuarter.class));
      VanillaProps.stone()
         .name("red_white_romanesque_frieze")
         .manual()
         .register(TypeList.of(Log.class, VerticalSlab.class, VerticalCorner.class, VerticalQuarter.class));
      VanillaProps.stone()
         .name("white_red_ribbon_frieze")
         .texture("top", "block/2_advanced_refined/3_plaster/golden_top")
         .texture("overlay", "block/2_advanced_refined/3_plaster/white_red_ribbon_frieze")
         .texture("*", "block/2_advanced_refined/3_plaster/white_plaster")
         .register(typesOverlay);
      VanillaProps.stone()
         .name("white_blue_floral_frieze")
         .texture("top", "block/2_advanced_refined/3_plaster/golden_top")
         .texture("overlay", "block/2_advanced_refined/3_plaster/white_blue_floral_frieze")
         .texture("*", "block/2_advanced_refined/3_plaster/white_plaster")
         .register(typesOverlay);
      VanillaProps.stone()
         .name("red_white_floral_frieze")
         .texture("top", "block/2_advanced_refined/3_plaster/red_plaster_with_white_masonry_lines")
         .texture("bottom", "block/2_advanced_refined/3_plaster/white_plaster")
         .texture("*", "block/2_advanced_refined/3_plaster/red_white_floral_frieze")
         .register(types);
      VanillaProps.stone()
         .name("white_gray_floral_frieze")
         .texture("top", "block/2_advanced_refined/3_plaster/red_plaster_with_white_masonry_lines")
         .texture("bottom", "block/2_advanced_refined/3_plaster/light_gray_plaster")
         .texture("*", "block/2_advanced_refined/3_plaster/white_gray_floral_frieze")
         .register(types);
      VanillaProps.stone()
         .name("gray_gothic_arch_frieze")
         .texture("top", "block/2_advanced_refined/3_plaster/red_plaster_with_white_masonry_lines")
         .texture("bottom", "block/2_advanced_refined/3_plaster/light_gray_plaster")
         .texture("*", "block/2_advanced_refined/3_plaster/gray_gothic_arch_frieze")
         .register(types);
   }
}
