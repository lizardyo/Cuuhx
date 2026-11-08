package com.conquestrefabricated.content.blocks.init.blocks;

import com.conquestrefabricated.content.blocks.block.Capital;
import com.conquestrefabricated.content.blocks.block.Pillar;
import com.conquestrefabricated.content.blocks.block.SlabCorner;
import com.conquestrefabricated.content.blocks.block.SlabEighth;
import com.conquestrefabricated.content.blocks.block.SlabLessLayers;
import com.conquestrefabricated.content.blocks.block.SlabQuarter;
import com.conquestrefabricated.content.blocks.block.VerticalCorner;
import com.conquestrefabricated.content.blocks.block.VerticalQuarter;
import com.conquestrefabricated.content.blocks.block.VerticalSlab;
import com.conquestrefabricated.content.blocks.block.VerticalSlabCorner;
import com.conquestrefabricated.content.blocks.block.WallNew;
import com.conquestrefabricated.core.block.builder.VanillaProps;
import com.conquestrefabricated.core.block.factory.TypeList;
import net.minecraft.world.level.block.Blocks;

public class RoofTilesInit {
   public RoofTilesInit() {
   }

   public static void init(TypeList types, TypeList typesVanilla) {
      VanillaProps.stone()
         .name("dirty_blue_clay_beaver_tail_tiles", "dirty_blue_clay_beaver_tail_tile")
         .texture("minecraft:block/light_blue_terracotta")
         .parent(Blocks.LIGHT_BLUE_TERRACOTTA.defaultBlockState())
         .register(typesVanilla);
      VanillaProps.stone()
         .name("light_blue_clay_beaver_tail_tiles")
         .texture("block/1_basic_refined/2_roof/1_clay/light_blue_clay_beaver_tail_tiles")
         .register(types);
      VanillaProps.stone()
         .name("blue_clay_beaver_tail_tiles", "blue_clay_beaver_tail_tile")
         .texture("minecraft:block/blue_terracotta")
         .parent(Blocks.BLUE_TERRACOTTA.defaultBlockState())
         .register(typesVanilla);
      VanillaProps.stone()
         .name("blue_green_diamond_clay_shingles")
         .texture("block/1_basic_refined/2_roof/1_clay/blue_green_diamond_clay_shingles")
         .register(types);
      VanillaProps.planks()
         .name("overgrown_green_clay_shingles", "overgrown_green_clay_shingle")
         .texture("minecraft:block/lime_terracotta")
         .parent(Blocks.LIME_TERRACOTTA.defaultBlockState())
         .register(typesVanilla);
      VanillaProps.planks().name("brown_gray_wood_shingles").texture("block/1_basic_refined/2_roof/3_wood/brown_gray_wood_shingles").register(types);
      VanillaProps.planks().name("brown_red_wood_shingles").texture("block/1_basic_refined/2_roof/3_wood/brown_red_wood_shingles").register(types);
      VanillaProps.planks()
         .name("green_clay_shingles", "green_clay_shingle")
         .texture("minecraft:block/green_terracotta")
         .parent(Blocks.GREEN_TERRACOTTA.defaultBlockState())
         .register(typesVanilla);
      VanillaProps.planks()
         .name("pink_clay_tiles", "pink_clay_tile")
         .texture("minecraft:block/pink_terracotta")
         .parent(Blocks.PINK_TERRACOTTA.defaultBlockState())
         .register(typesVanilla);
      VanillaProps.planks().name("red_clay_tiles").texture("block/1_basic_refined/2_roof/1_clay/red_clay_tiles").register(types);
      VanillaProps.planks().name("red_clay_beaver_tail_tiles").texture("block/1_basic_refined/2_roof/1_clay/red_clay_beaver_tail_tiles").register(types);
      VanillaProps.planks().name("brown_clay_beaver_tail_tiles").texture("block/1_basic_refined/2_roof/1_clay/brown_clay_beaver_tail_tiles").register(types);
      VanillaProps.planks().name("orange_clay_beaver_tail_tiles").texture("block/1_basic_refined/2_roof/1_clay/orange_clay_beaver_tail_tiles").register(types);
      VanillaProps.planks().name("pink_clay_beaver_tail_tiles").texture("block/1_basic_refined/2_roof/1_clay/pink_clay_beaver_tail_tiles").register(types);
      VanillaProps.planks()
         .name("light_red_clay_beaver_tail_tiles")
         .texture("block/1_basic_refined/2_roof/1_clay/light_red_clay_beaver_tail_tiles")
         .register(types);
      VanillaProps.planks().name("light_cotswold_slate_tiles").texture("block/1_basic_refined/2_roof/1_clay/light_cotswold_slate_tiles").register(types);
      VanillaProps.planks()
         .name("dark_red_diamond_clay_shingles")
         .texture("block/1_basic_refined/2_roof/1_clay/dark_red_diamond_clay_shingles")
         .register(types);
      VanillaProps.stone().name("dark_cotswold_slate_tiles").texture("block/1_basic_refined/2_roof/1_clay/dark_cotswold_slate_tiles").register(types);
      VanillaProps.stone()
         .name("terracotta_imbrices_and_tegulae")
         .texture("block/1_basic_refined/2_roof/1_clay/terracotta_imbrices_and_tegulae")
         .register(types);
      VanillaProps.planks()
         .name("old_slate_roof_tiles", "old_slate_roof_tile")
         .texture("minecraft:block/cyan_terracotta")
         .parent(Blocks.CYAN_TERRACOTTA.defaultBlockState())
         .register(typesVanilla);
      VanillaProps.planks().name("dark_slate_roof_tiles").texture("block/1_basic_refined/2_roof/dark_slate_roof_tiles").register(types);
      VanillaProps.planks().name("blue_diamond_clay_shingles").texture("block/1_basic_refined/2_roof/1_clay/blue_diamond_clay_shingles").register(types);
      VanillaProps.planks().name("gray_wood_diamond_shingles").texture("block/1_basic_refined/2_roof/3_wood/gray_wood_diamond_shingles").register(types);
      VanillaProps.planks().name("brown_wood_diamond_shingles").texture("block/1_basic_refined/2_roof/3_wood/brown_wood_diamond_shingles").register(types);
      VanillaProps.planks().name("gray_wood_shingles").texture("block/1_basic_refined/2_roof/3_wood/gray_wood_shingles").register(types);
      VanillaProps.planks().name("brown_wood_shingles").texture("block/1_basic_refined/2_roof/3_wood/brown_wood_shingles").register(types);
      VanillaProps.planks().name("orange_diamond_clay_shingles").texture("block/1_basic_refined/2_roof/1_clay/orange_diamond_clay_shingles").register(types);
      VanillaProps.metal().name("oxidized_copper_roof").texture("block/1_basic_refined/2_roof/oxidized_copper_roof").register(types);
      VanillaProps.planks().name("red_diamond_clay_shingles").texture("block/1_basic_refined/2_roof/1_clay/red_diamond_clay_shingles").register(types);
      VanillaProps.planks().name("red_diamond_slate_shingles").texture("block/1_basic_refined/2_roof/1_clay/red_diamond_slate_shingles").register(types);
      VanillaProps.planks().name("brown_diamond_clay_shingles").texture("block/1_basic_refined/2_roof/1_clay/brown_diamond_clay_shingles").register(types);
      VanillaProps.metal()
         .name("cut_copper")
         .texture("minecraft:block/cut_copper")
         .parent(Blocks.WAXED_CUT_COPPER.defaultBlockState())
         .register(
            TypeList.of(
               SlabLessLayers.class,
               SlabQuarter.class,
               SlabCorner.class,
               SlabEighth.class,
               VerticalSlabCorner.class,
               VerticalSlab.class,
               VerticalCorner.class,
               VerticalQuarter.class,
               WallNew.class,
               Pillar.class,
               Capital.class
            )
         );
      VanillaProps.metal()
         .name("exposed_cut_copper")
         .texture("minecraft:block/exposed_cut_copper")
         .parent(Blocks.WAXED_EXPOSED_CUT_COPPER.defaultBlockState())
         .register(
            TypeList.of(
               SlabLessLayers.class,
               SlabQuarter.class,
               SlabCorner.class,
               SlabEighth.class,
               VerticalSlabCorner.class,
               VerticalSlab.class,
               VerticalCorner.class,
               VerticalQuarter.class,
               WallNew.class,
               Pillar.class,
               Capital.class
            )
         );
      VanillaProps.metal()
         .name("weathered_cut_copper")
         .texture("minecraft:block/weathered_cut_copper")
         .parent(Blocks.WAXED_WEATHERED_CUT_COPPER.defaultBlockState())
         .register(
            TypeList.of(
               SlabLessLayers.class,
               SlabQuarter.class,
               SlabCorner.class,
               SlabEighth.class,
               VerticalSlabCorner.class,
               VerticalSlab.class,
               VerticalCorner.class,
               VerticalQuarter.class,
               WallNew.class,
               Pillar.class,
               Capital.class
            )
         );
      VanillaProps.metal()
         .name("oxidized_cut_copper")
         .texture("minecraft:block/oxidized_cut_copper")
         .parent(Blocks.WAXED_OXIDIZED_CUT_COPPER.defaultBlockState())
         .register(
            TypeList.of(
               SlabLessLayers.class,
               SlabQuarter.class,
               SlabCorner.class,
               SlabEighth.class,
               VerticalSlabCorner.class,
               VerticalSlab.class,
               VerticalCorner.class,
               VerticalQuarter.class,
               WallNew.class,
               Pillar.class,
               Capital.class
            )
         );
      VanillaProps.stone()
         .name("deepslate_tile")
         .texture("minecraft:block/deepslate_tiles")
         .parent(Blocks.DEEPSLATE_TILES.defaultBlockState())
         .register(
            TypeList.of(
               SlabLessLayers.class,
               SlabQuarter.class,
               SlabCorner.class,
               SlabEighth.class,
               VerticalSlabCorner.class,
               VerticalSlab.class,
               VerticalCorner.class,
               VerticalQuarter.class,
               Pillar.class,
               Capital.class
            )
         );
      VanillaProps.stone()
         .name("cracked_deepslate_tile")
         .texture("minecraft:block/cracked_deepslate_tiles")
         .parent(Blocks.CRACKED_DEEPSLATE_TILES.defaultBlockState())
         .register(typesVanilla);
      VanillaProps.stone().name("brown_imbrices_and_tegulae").texture("block/1_basic_refined/2_roof/1_clay/brown_imbrices_and_tegulae").register(types);
      VanillaProps.stone().name("dark_imbrices_and_tegulae").texture("block/1_basic_refined/2_roof/1_clay/dark_imbrices_and_tegulae").register(types);
      VanillaProps.stone().name("dark_red_imbrices_and_tegulae").texture("block/1_basic_refined/2_roof/1_clay/dark_red_imbrices_and_tegulae").register(types);
      VanillaProps.stone().name("red_imbrices_and_tegulae").texture("block/1_basic_refined/2_roof/1_clay/red_imbrices_and_tegulae").register(types);
   }
}
