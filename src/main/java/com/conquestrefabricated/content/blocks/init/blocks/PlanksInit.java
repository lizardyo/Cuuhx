package com.conquestrefabricated.content.blocks.init.blocks;

import com.conquestrefabricated.content.blocks.block.Balustrade;
import com.conquestrefabricated.content.blocks.block.BoardsHorizontal;
import com.conquestrefabricated.content.blocks.block.BoardsVertical;
import com.conquestrefabricated.content.blocks.block.Capital;
import com.conquestrefabricated.content.blocks.block.Cube;
import com.conquestrefabricated.content.blocks.block.FenceCross;
import com.conquestrefabricated.content.blocks.block.Pillar;
import com.conquestrefabricated.content.blocks.block.PlatformHorizontal;
import com.conquestrefabricated.content.blocks.block.SlabCorner;
import com.conquestrefabricated.content.blocks.block.SlabEighth;
import com.conquestrefabricated.content.blocks.block.SlabLessLayers;
import com.conquestrefabricated.content.blocks.block.SlabQuarter;
import com.conquestrefabricated.content.blocks.block.Sphere;
import com.conquestrefabricated.content.blocks.block.Stairs;
import com.conquestrefabricated.content.blocks.block.VerticalCornerExtendable;
import com.conquestrefabricated.content.blocks.block.VerticalQuarterExtendable;
import com.conquestrefabricated.content.blocks.block.VerticalSlabCorner;
import com.conquestrefabricated.content.blocks.block.VerticalSlabExtendable;
import com.conquestrefabricated.content.blocks.block.WallNew;
import com.conquestrefabricated.content.blocks.block.decor.Rail;
import com.conquestrefabricated.content.blocks.block.windows.WindowSmall;
import com.conquestrefabricated.content.blocks.block.windows.WindowSmallHalf;
import com.conquestrefabricated.core.block.builder.VanillaProps;
import com.conquestrefabricated.core.block.factory.TypeList;
import net.minecraft.world.level.block.Blocks;

public class PlanksInit {
   public PlanksInit() {
   }

   public static void init(TypeList types, TypeList typesHorizontal, TypeList typesVertical, TypeList typesVerticalCrossFence) {
      VanillaProps.planks()
         .name("oak_wood_planks", "oak_wood_plank")
         .texture("minecraft:block/oak_planks")
         .parent(Blocks.OAK_PLANKS.defaultBlockState())
         .register(
            TypeList.of(
               WindowSmall.class,
               WindowSmallHalf.class,
               Balustrade.class,
               Capital.class,
               Sphere.class,
               BoardsHorizontal.class,
               SlabLessLayers.class,
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
      VanillaProps.planks().name("ash_wood_planks", "ash_wood_plank").texture("block/1_basic_refined/3_wood/ash_wood_planks").register(typesVertical);
      VanillaProps.planks().name("ash_wood_plank_rail").family("ash_wood_planks").register(TypeList.of(Rail.class));
      VanillaProps.planks()
         .name("narrow_oak_wood_planks", "narrow_oak_wood_plank")
         .texture("block/1_basic_refined/3_wood/oak/narrow_oak_wood_planks")
         .register(
            TypeList.of(
               Cube.class,
               WindowSmall.class,
               WindowSmallHalf.class,
               Balustrade.class,
               Capital.class,
               Sphere.class,
               BoardsHorizontal.class,
               SlabLessLayers.class,
               SlabQuarter.class,
               SlabCorner.class,
               SlabEighth.class,
               VerticalSlabCorner.class,
               VerticalSlabExtendable.class,
               VerticalCornerExtendable.class,
               VerticalQuarterExtendable.class,
               Stairs.class,
               WallNew.class,
               Pillar.class,
               PlatformHorizontal.class
            )
         );
      VanillaProps.planks()
         .name("old_oak_wood_planks", "old_oak_wood_plank")
         .texture("block/1_basic_refined/3_wood/oak/old_oak_wood_planks")
         .register(typesHorizontal);
      VanillaProps.planks()
         .name("rustic_spruce_wood_planks", "rustic_spruce_wood_plank")
         .texture("block/1_basic_refined/3_wood/spruce/rustic_spruce_wood_planks")
         .register(
            TypeList.of(
               Cube.class,
               WindowSmall.class,
               WindowSmallHalf.class,
               Balustrade.class,
               Capital.class,
               Sphere.class,
               BoardsHorizontal.class,
               SlabLessLayers.class,
               SlabQuarter.class,
               SlabCorner.class,
               SlabEighth.class,
               VerticalSlabCorner.class,
               VerticalSlabExtendable.class,
               VerticalCornerExtendable.class,
               VerticalQuarterExtendable.class,
               Stairs.class,
               WallNew.class,
               Pillar.class,
               PlatformHorizontal.class
            )
         );
      VanillaProps.planks()
         .name("vertical_narrow_oak_wood_planks", "vertical_narrow_oak_wood_plank")
         .texture("block/1_basic_refined/3_wood/oak/vertical_narrow_oak_wood_planks")
         .register(typesVertical);
      VanillaProps.planks()
         .name("oak_platform")
         .texture("top", "block/1_basic_refined/3_wood/oak/oak_platform_top")
         .texture("*", "block/1_basic_refined/3_wood/oak/oak_platform")
         .register(typesHorizontal);
      VanillaProps.planks().name("staves", "stave").texture("block/1_basic_refined/3_wood/staves").register(types);
      VanillaProps.planks()
         .name("spruce_wood_planks", "spruce_wood_plank")
         .texture("minecraft:block/spruce_planks")
         .parent(Blocks.SPRUCE_PLANKS.defaultBlockState())
         .register(
            TypeList.of(
               WindowSmall.class,
               WindowSmallHalf.class,
               Balustrade.class,
               Capital.class,
               Sphere.class,
               BoardsHorizontal.class,
               SlabLessLayers.class,
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
      VanillaProps.planks().name("spruce_wood_plank_rail").family("spruce_wood_plank_small_window").register(TypeList.of(Rail.class));
      VanillaProps.planks()
         .name("reinforced_spruce_wood_planks", "reinforced_spruce_wood_plank")
         .texture("block/1_basic_refined/3_wood/spruce/reinforced_spruce_wood_planks")
         .register(typesHorizontal);
      VanillaProps.planks()
         .name("spruce_platform")
         .texture("top", "block/1_basic_refined/3_wood/spruce/spruce_platform_top")
         .texture("*", "block/1_basic_refined/3_wood/spruce/spruce_platform")
         .register(typesHorizontal);
      VanillaProps.planks().name("spruce_paneling").texture("block/1_basic_refined/3_wood/spruce/spruce_paneling").register(typesVertical);
      VanillaProps.planks()
         .name("birch_wood_planks", "birch_wood_plank")
         .texture("minecraft:block/birch_planks")
         .parent(Blocks.BIRCH_PLANKS.defaultBlockState())
         .register(
            TypeList.of(
               WindowSmall.class,
               WindowSmallHalf.class,
               Balustrade.class,
               Capital.class,
               Sphere.class,
               BoardsHorizontal.class,
               SlabLessLayers.class,
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
      VanillaProps.planks()
         .name("light_birch_wood_planks", "light_birch_wood_plank")
         .texture("block/1_basic_refined/3_wood/birch/light_birch_wood_planks")
         .register(typesHorizontal);
      VanillaProps.planks().name("pine_wood_planks", "pine_wood_plank").texture("block/1_basic_refined/3_wood/pine_wood_planks").register(typesHorizontal);
      VanillaProps.planks()
         .name("wall_of_birch_logs")
         .texture("top", "minecraft:block/birch_planks")
         .texture("bottom", "minecraft:block/birch_planks")
         .texture("*", "block/1_basic_refined/3_wood/birch/wall_of_birch_logs")
         .register(types);
      VanillaProps.planks().name("wall_of_spruce_logs").texture("block/1_basic_refined/3_wood/spruce/wall_of_spruce_logs").register(types);
      VanillaProps.planks()
         .name("wall_of_oak_logs")
         .texture("top", "block/1_basic_refined/3_wood/oak/wall_of_oak_logs_topbottom")
         .texture("bottom", "block/1_basic_refined/3_wood/oak/wall_of_oak_logs_topbottom")
         .texture("*", "block/1_basic_refined/3_wood/oak/wall_of_oak_logs")
         .register(types);
      VanillaProps.planks()
         .name("wall_of_old_spruce_logs")
         .texture("top", "block/1_basic_refined/3_wood/spruce/wall_of_old_spruce_logs_topbottom")
         .texture("bottom", "block/1_basic_refined/3_wood/spruce/wall_of_old_spruce_logs_topbottom")
         .texture("*", "block/1_basic_refined/3_wood/spruce/wall_of_old_spruce_logs")
         .register(types);
      VanillaProps.planks()
         .name("rough_palm_wood_planks", "rough_palm_wood_plank")
         .texture("minecraft:block/jungle_planks")
         .parent(Blocks.JUNGLE_PLANKS.defaultBlockState())
         .register(
            TypeList.of(
               WindowSmall.class,
               WindowSmallHalf.class,
               Balustrade.class,
               Capital.class,
               Sphere.class,
               BoardsVertical.class,
               SlabLessLayers.class,
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
      VanillaProps.planks()
         .name("vertical_alder_wood_planks", "vertical_alder_wood_plank")
         .texture("block/1_basic_refined/3_wood/vertical_alder_wood_planks")
         .register(typesVertical);
      VanillaProps.planks()
         .name("acacia_wood_planks", "acacia_wood_plank")
         .texture("top", "minecraft:block/acacia_planks_top")
         .texture("*", "minecraft:block/acacia_planks")
         .parent(Blocks.ACACIA_PLANKS.defaultBlockState())
         .register(
            TypeList.of(
               WindowSmall.class,
               WindowSmallHalf.class,
               Balustrade.class,
               Capital.class,
               Sphere.class,
               BoardsVertical.class,
               SlabLessLayers.class,
               SlabQuarter.class,
               SlabCorner.class,
               SlabEighth.class,
               VerticalSlabCorner.class,
               VerticalSlabExtendable.class,
               VerticalCornerExtendable.class,
               VerticalQuarterExtendable.class,
               FenceCross.class,
               WallNew.class,
               Pillar.class
            )
         );
      VanillaProps.planks()
         .name("red_acacia_wood_planks", "red_acacia_wood_plank")
         .texture("top", "block/1_basic_refined/3_wood/red_acacia_wood_planks_top")
         .texture("*", "block/1_basic_refined/3_wood/red_acacia_wood_planks")
         .register(typesHorizontal);
      VanillaProps.planks()
         .name("dark_oak_wood_planks", "dark_oak_wood_plank")
         .texture("minecraft:block/dark_oak_planks")
         .parent(Blocks.DARK_OAK_PLANKS.defaultBlockState())
         .register(
            TypeList.of(
               WindowSmall.class,
               WindowSmallHalf.class,
               Balustrade.class,
               Capital.class,
               Sphere.class,
               BoardsVertical.class,
               SlabLessLayers.class,
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
      VanillaProps.planks().name("apple_wood_planks", "apple_wood_plank").texture("block/1_basic_refined/3_wood/apple_wood_planks").register(typesHorizontal);
      VanillaProps.planks().name("alder_wood_planks", "alder_wood_plank").texture("block/1_basic_refined/3_wood/alder_wood_planks").register(typesHorizontal);
      VanillaProps.planks().name("aspen_wood_planks", "aspen_wood_plank").texture("block/1_basic_refined/3_wood/aspen_wood_planks").register(typesVertical);
      VanillaProps.planks().name("beech_wood_planks", "beech_wood_plank").texture("block/1_basic_refined/3_wood/beech_wood_planks").register(typesHorizontal);
      VanillaProps.planks()
         .name("goat_sallow_wood_planks", "goat_sallow_wood_plank")
         .texture("block/1_basic_refined/3_wood/goat_sallow_wood_planks")
         .register(typesVertical);
      VanillaProps.planks().name("goat_sallow_wood_plank_rail").family("goat_sallow_wood_planks").register(TypeList.of(Rail.class));
      VanillaProps.planks()
         .name("horse_chestnut_wood_planks", "horse_chestnut_wood_plank")
         .texture("block/1_basic_refined/3_wood/horse_chestnut_wood_planks")
         .register(typesVertical);
      VanillaProps.planks().name("larch_wood_planks", "larch_wood_plank").texture("block/1_basic_refined/3_wood/larch_wood_planks").register(typesVertical);
      VanillaProps.planks()
         .name("mallorn_wood_planks", "mallorn_wood_plank")
         .texture("block/1_basic_refined/3_wood/mallorn_wood_planks")
         .register(typesHorizontal);
      VanillaProps.planks()
         .name("norway_spruce_wood_planks", "norway_spruce_wood_plank")
         .texture("block/1_basic_refined/3_wood/norway_spruce_wood_planks")
         .register(typesHorizontal);
      VanillaProps.planks().name("rowan_wood_planks", "rowan_wood_plank").texture("block/1_basic_refined/3_wood/rowan_wood_planks").register(typesVertical);
      VanillaProps.planks()
         .name("willow_wood_planks", "willow_wood_plank")
         .texture("block/1_basic_refined/3_wood/willow_wood_planks")
         .register(typesHorizontal);
      VanillaProps.planks().name("willow_wood_plank_rail").family("willow_wood_planks").register(TypeList.of(Rail.class));
      VanillaProps.planks()
         .name("barnacle_covered_palm_wood_planks", "barnacle_covered_palm_wood_plank")
         .texture("block/1_basic_refined/3_wood/barnacle_covered_palm_wood_planks")
         .register(typesVertical);
      VanillaProps.planks()
         .name("mossy_palm_wood_planks", "mossy_palm_wood_plank")
         .texture("block/1_basic_refined/3_wood/mossy_palm_wood_planks")
         .register(typesVertical);
      VanillaProps.planks()
         .name("weathered_red_painted_planks", "weathered_red_painted_plank")
         .texture("block/2_advanced_refined/7_wood/1_painted/weathered_red_painted_planks")
         .register(typesVerticalCrossFence);
      VanillaProps.planks()
         .name("red_painted_planks", "red_painted_plank")
         .texture("block/2_advanced_refined/7_wood/1_painted/red_painted_planks")
         .register(typesVerticalCrossFence);
      VanillaProps.planks()
         .name("weathered_light_red_painted_planks", "weathered_light_red_painted_plank")
         .texture("block/2_advanced_refined/7_wood/1_painted/weathered_light_red_painted_planks")
         .register(typesVerticalCrossFence);
      VanillaProps.planks()
         .name("light_red_painted_planks", "light_red_painted_plank")
         .texture("block/2_advanced_refined/7_wood/1_painted/light_red_painted_planks")
         .register(typesVerticalCrossFence);
      VanillaProps.planks()
         .name("weathered_orange_painted_planks", "weathered_orange_painted_plank")
         .texture("block/2_advanced_refined/7_wood/1_painted/weathered_orange_painted_planks")
         .register(typesVerticalCrossFence);
      VanillaProps.planks()
         .name("orange_painted_planks", "orange_painted_plank")
         .texture("block/2_advanced_refined/7_wood/1_painted/orange_painted_planks")
         .register(typesVerticalCrossFence);
      VanillaProps.planks()
         .name("weathered_yellow_painted_planks", "weathered_yellow_painted_plank")
         .texture("block/2_advanced_refined/7_wood/1_painted/weathered_yellow_painted_planks")
         .register(typesVerticalCrossFence);
      VanillaProps.planks()
         .name("yellow_painted_planks", "yellow_painted_plank")
         .texture("block/2_advanced_refined/7_wood/1_painted/yellow_painted_planks")
         .register(typesVerticalCrossFence);
      VanillaProps.planks()
         .name("weathered_green_painted_planks", "weathered_green_painted_plank")
         .texture("block/2_advanced_refined/7_wood/1_painted/weathered_green_painted_planks")
         .register(typesVerticalCrossFence);
      VanillaProps.planks()
         .name("green_painted_planks", "green_painted_plank")
         .texture("block/2_advanced_refined/7_wood/1_painted/green_painted_planks")
         .register(typesVerticalCrossFence);
      VanillaProps.planks()
         .name("weathered_light_green_painted_planks", "weathered_light_green_painted_plank")
         .texture("block/2_advanced_refined/7_wood/1_painted/weathered_light_green_painted_planks")
         .register(typesVerticalCrossFence);
      VanillaProps.planks()
         .name("light_green_painted_planks", "light_green_painted_plank")
         .texture("block/2_advanced_refined/7_wood/1_painted/light_green_painted_planks")
         .register(typesVerticalCrossFence);
      VanillaProps.planks()
         .name("weathered_cyan_painted_planks", "weathered_cyan_painted_plank")
         .texture("block/2_advanced_refined/7_wood/1_painted/weathered_cyan_painted_planks")
         .register(typesVerticalCrossFence);
      VanillaProps.planks()
         .name("cyan_painted_planks", "cyan_painted_plank")
         .texture("block/2_advanced_refined/7_wood/1_painted/cyan_painted_planks")
         .register(typesVerticalCrossFence);
      VanillaProps.planks()
         .name("weathered_dark_blue_painted_planks", "weathered_dark_blue_painted_plank")
         .texture("block/2_advanced_refined/7_wood/1_painted/weathered_dark_blue_painted_planks")
         .register(typesVerticalCrossFence);
      VanillaProps.planks()
         .name("dark_blue_painted_planks", "dark_blue_painted_plank")
         .texture("block/2_advanced_refined/7_wood/1_painted/dark_blue_painted_planks")
         .register(typesVerticalCrossFence);
      VanillaProps.planks()
         .name("weathered_blue_painted_planks", "weathered_blue_painted_plank")
         .texture("block/2_advanced_refined/7_wood/1_painted/weathered_blue_painted_planks")
         .register(typesVerticalCrossFence);
      VanillaProps.planks()
         .name("blue_painted_planks", "blue_painted_plank")
         .texture("block/2_advanced_refined/7_wood/1_painted/blue_painted_planks")
         .register(typesVerticalCrossFence);
      VanillaProps.planks()
         .name("weathered_light_blue_painted_planks", "weathered_light_blue_painted_plank")
         .texture("block/2_advanced_refined/7_wood/1_painted/weathered_light_blue_painted_planks")
         .register(typesVerticalCrossFence);
      VanillaProps.planks()
         .name("light_blue_painted_planks", "light_blue_painted_plank")
         .texture("block/2_advanced_refined/7_wood/1_painted/light_blue_painted_planks")
         .register(typesVerticalCrossFence);
      VanillaProps.planks()
         .name("weathered_purple_painted_planks", "weathered_purple_painted_plank")
         .texture("block/2_advanced_refined/7_wood/1_painted/weathered_purple_painted_planks")
         .register(typesVerticalCrossFence);
      VanillaProps.planks()
         .name("purple_painted_planks", "purple_painted_plank")
         .texture("block/2_advanced_refined/7_wood/1_painted/purple_painted_planks")
         .register(typesVerticalCrossFence);
      VanillaProps.planks()
         .name("weathered_brown_painted_planks", "weathered_brown_painted_plank")
         .texture("block/2_advanced_refined/7_wood/1_painted/weathered_brown_painted_planks")
         .register(typesVerticalCrossFence);
      VanillaProps.planks()
         .name("brown_painted_planks", "brown_painted_plank")
         .texture("block/2_advanced_refined/7_wood/1_painted/brown_painted_planks")
         .register(typesVerticalCrossFence);
      VanillaProps.planks()
         .name("weathered_white_painted_planks", "weathered_white_painted_plank")
         .texture("block/2_advanced_refined/7_wood/1_painted/weathered_white_painted_planks")
         .register(typesVerticalCrossFence);
      VanillaProps.planks()
         .name("white_painted_planks", "white_painted_plank")
         .texture("block/2_advanced_refined/7_wood/1_painted/white_painted_planks")
         .register(typesVerticalCrossFence);
      VanillaProps.planks()
         .name("white_painted_horizontal_planks", "white_painted_horizontal_plank")
         .texture("block/2_advanced_refined/7_wood/1_painted/white_painted_horizontal_planks")
         .register(typesHorizontal);
      VanillaProps.planks()
         .name("gray_painted_horizontal_planks", "gray_painted_horizontal_plank")
         .texture("block/2_advanced_refined/7_wood/1_painted/gray_painted_horizontal_planks")
         .register(typesHorizontal);
      VanillaProps.planks()
         .name("bamboo_wood_plank")
         .texture("minecraft:block/bamboo_planks")
         .parent(Blocks.BAMBOO_PLANKS.defaultBlockState())
         .register(
            TypeList.of(
               WindowSmall.class,
               WindowSmallHalf.class,
               Balustrade.class,
               Capital.class,
               Sphere.class,
               SlabLessLayers.class,
               SlabQuarter.class,
               SlabCorner.class,
               SlabEighth.class,
               VerticalSlabCorner.class,
               VerticalSlabExtendable.class,
               VerticalCornerExtendable.class,
               VerticalQuarterExtendable.class,
               WallNew.class,
               Pillar.class,
               BoardsHorizontal.class
            )
         );
      VanillaProps.planks()
         .name("bamboo_mosaic")
         .texture("minecraft:block/bamboo_mosaic")
         .parent(Blocks.BAMBOO_MOSAIC.defaultBlockState())
         .register(
            TypeList.of(
               SlabLessLayers.class,
               SlabQuarter.class,
               SlabCorner.class,
               SlabEighth.class,
               VerticalSlabCorner.class,
               VerticalSlabExtendable.class,
               VerticalCornerExtendable.class,
               VerticalQuarterExtendable.class
            )
         );
      VanillaProps.planks()
         .name("mangrove_wood_plank")
         .texture("minecraft:block/mangrove_planks")
         .parent(Blocks.MANGROVE_PLANKS.defaultBlockState())
         .register(
            TypeList.of(
               WindowSmall.class,
               WindowSmallHalf.class,
               Balustrade.class,
               Capital.class,
               Sphere.class,
               SlabLessLayers.class,
               SlabQuarter.class,
               SlabCorner.class,
               SlabEighth.class,
               VerticalSlabCorner.class,
               VerticalSlabExtendable.class,
               VerticalCornerExtendable.class,
               VerticalQuarterExtendable.class,
               WallNew.class,
               Pillar.class,
               BoardsHorizontal.class
            )
         );
      VanillaProps.planks()
         .name("cherry_wood_plank")
         .texture("minecraft:block/cherry_planks")
         .parent(Blocks.CHERRY_PLANKS.defaultBlockState())
         .register(
            TypeList.of(
               WindowSmall.class,
               WindowSmallHalf.class,
               Balustrade.class,
               Capital.class,
               Sphere.class,
               SlabLessLayers.class,
               SlabQuarter.class,
               SlabCorner.class,
               SlabEighth.class,
               VerticalSlabCorner.class,
               VerticalSlabExtendable.class,
               VerticalCornerExtendable.class,
               VerticalQuarterExtendable.class,
               WallNew.class,
               Pillar.class,
               BoardsVertical.class
            )
         );
   }
}
