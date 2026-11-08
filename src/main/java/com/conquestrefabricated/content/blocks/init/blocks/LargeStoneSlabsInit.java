package com.conquestrefabricated.content.blocks.init.blocks;

import com.conquestrefabricated.content.blocks.block.Balustrade;
import com.conquestrefabricated.content.blocks.block.Capital;
import com.conquestrefabricated.content.blocks.block.Cube;
import com.conquestrefabricated.content.blocks.block.Pillar;
import com.conquestrefabricated.content.blocks.block.Slab;
import com.conquestrefabricated.content.blocks.block.SlabCorner;
import com.conquestrefabricated.content.blocks.block.SlabEighth;
import com.conquestrefabricated.content.blocks.block.SlabQuarter;
import com.conquestrefabricated.content.blocks.block.Stairs;
import com.conquestrefabricated.content.blocks.block.VerticalCornerExtendable;
import com.conquestrefabricated.content.blocks.block.VerticalQuarterExtendable;
import com.conquestrefabricated.content.blocks.block.VerticalSlabCorner;
import com.conquestrefabricated.content.blocks.block.VerticalSlabExtendable;
import com.conquestrefabricated.content.blocks.block.WallNew;
import com.conquestrefabricated.content.blocks.block.windows.ArrowSlit;
import com.conquestrefabricated.content.blocks.block.windows.WindowSmall;
import com.conquestrefabricated.content.blocks.block.windows.WindowSmallHalf;
import com.conquestrefabricated.core.block.builder.VanillaProps;
import com.conquestrefabricated.core.block.factory.TypeList;
import net.minecraft.world.level.block.Blocks;

public class LargeStoneSlabsInit {
   public LargeStoneSlabsInit() {
   }

   public static void init(TypeList types, TypeList typesVanilla, TypeList typesVanillaNoStairs) {
      VanillaProps.stone()
         .name("large_limestone_block")
         .texture("top", "block/1_basic_refined/1_stone/3_limestone/large_limestone_block_topbottom")
         .texture("bottom", "block/1_basic_refined/1_stone/3_limestone/large_limestone_block_topbottom")
         .texture("*", "block/1_basic_refined/1_stone/3_limestone/large_limestone_block")
         .register(types);
      VanillaProps.stone()
         .name("large_white_marble_block")
         .texture("top", "block/1_basic_refined/1_stone/4_marble/large_white_marble_block_topbottom")
         .texture("bottom", "block/1_basic_refined/1_stone/4_marble/large_white_marble_block_topbottom")
         .texture("*", "block/1_basic_refined/1_stone/4_marble/large_white_marble_block")
         .register(types);
      VanillaProps.stone()
         .name("smooth_large_white_marble_block")
         .texture("top", "block/1_basic_refined/1_stone/4_marble/large_white_marble_block_topbottom")
         .texture("bottom", "block/1_basic_refined/1_stone/4_marble/large_white_marble_block_topbottom")
         .texture("*", "block/1_basic_refined/1_stone/4_marble/smooth_large_white_marble_block")
         .register(types);
      VanillaProps.stone()
         .name("large_sandstone_block")
         .texture("top", "block/1_basic_refined/1_stone/5_sandstone/large_sandstone_block_topbottom")
         .texture("bottom", "block/1_basic_refined/1_stone/5_sandstone/large_sandstone_block_topbottom")
         .texture("*", "block/1_basic_refined/1_stone/5_sandstone/large_sandstone_block")
         .register(types);
      VanillaProps.stone()
         .name("large_inscribed_sandstone_block")
         .texture("top", "block/1_basic_refined/1_stone/5_sandstone/large_sandstone_block_topbottom")
         .texture("bottom", "block/1_basic_refined/1_stone/5_sandstone/large_sandstone_block_topbottom")
         .texture("*", "block/1_basic_refined/1_stone/5_sandstone/large_inscribed_sandstone_block")
         .register(
            TypeList.of(
               Cube.class,
               ArrowSlit.class,
               WindowSmall.class,
               WindowSmallHalf.class,
               Balustrade.class,
               Capital.class,
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
         .name("smooth_large_sandstone_block")
         .texture("top", "block/1_basic_refined/1_stone/5_sandstone/large_sandstone_block_topbottom")
         .texture("bottom", "block/1_basic_refined/1_stone/5_sandstone/large_sandstone_block_topbottom")
         .texture("*", "block/1_basic_refined/1_stone/5_sandstone/smooth_large_sandstone_block")
         .register(types);
      VanillaProps.stone()
         .name("smooth_large_inscribed_sandstone_block")
         .texture("top", "block/1_basic_refined/1_stone/5_sandstone/large_sandstone_block_topbottom")
         .texture("bottom", "block/1_basic_refined/1_stone/5_sandstone/large_sandstone_block_topbottom")
         .texture("*", "block/1_basic_refined/1_stone/5_sandstone/smooth_large_inscribed_sandstone_block")
         .register(
            TypeList.of(
               Cube.class,
               ArrowSlit.class,
               WindowSmall.class,
               WindowSmallHalf.class,
               Balustrade.class,
               Capital.class,
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
         .name("large_tan_sandstone_block")
         .texture("top", "block/1_basic_refined/1_stone/5_sandstone/large_tan_sandstone_block_topbottom")
         .texture("bottom", "block/1_basic_refined/1_stone/5_sandstone/large_tan_sandstone_block_topbottom")
         .texture("*", "block/1_basic_refined/1_stone/5_sandstone/large_tan_sandstone_block")
         .register(types);
      VanillaProps.stone().name("large_andesite_brick").texture("block/1_basic_refined/1_stone/large_andesite_brick").register(types);
      VanillaProps.stone()
         .name("large_eroded_pentelic_marble_block")
         .texture("block/1_basic_refined/1_stone/4_marble/large_eroded_pentelic_marble_block")
         .register(types);
      VanillaProps.stone()
         .name("large_weathered_pentelic_marble_block")
         .texture("block/1_basic_refined/1_stone/4_marble/large_weathered_pentelic_marble_block")
         .register(types);
      VanillaProps.stone().name("large_pentelic_marble_block").texture("block/1_basic_refined/1_stone/4_marble/large_pentelic_marble_block").register(types);
      VanillaProps.stone()
         .name("large_black_painted_block")
         .texture("top", "block/1_basic_refined/1_stone/large_black_painted_block_topbottom")
         .texture("bottom", "block/1_basic_refined/1_stone/large_black_painted_block_topbottom")
         .texture("*", "block/1_basic_refined/1_stone/large_black_painted_block")
         .register(types);
      VanillaProps.stone()
         .name("large_inscribed_black_painted_block")
         .texture("top", "block/1_basic_refined/1_stone/large_black_painted_block_topbottom")
         .texture("bottom", "block/1_basic_refined/1_stone/large_black_painted_block_topbottom")
         .texture("*", "block/1_basic_refined/1_stone/large_inscribed_black_painted_block")
         .register(
            TypeList.of(
               Cube.class,
               ArrowSlit.class,
               WindowSmall.class,
               WindowSmallHalf.class,
               Balustrade.class,
               Capital.class,
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
         .name("smooth_large_black_painted_block")
         .texture("top", "block/1_basic_refined/1_stone/large_black_painted_block_topbottom")
         .texture("bottom", "block/1_basic_refined/1_stone/large_black_painted_block_topbottom")
         .texture("*", "block/1_basic_refined/1_stone/smooth_large_black_painted_block")
         .register(types);
      VanillaProps.stone()
         .name("smooth_large_inscribed_black_painted_block")
         .texture("top", "block/1_basic_refined/1_stone/large_black_painted_block_topbottom")
         .texture("bottom", "block/1_basic_refined/1_stone/large_black_painted_block_topbottom")
         .texture("*", "block/1_basic_refined/1_stone/smooth_large_inscribed_black_painted_block")
         .register(
            TypeList.of(
               Cube.class,
               ArrowSlit.class,
               WindowSmall.class,
               WindowSmallHalf.class,
               Balustrade.class,
               Capital.class,
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
      VanillaProps.stone().name("concrete_wall").texture("minecraft:block/light_gray_concrete").parent(Blocks.LIGHT_GRAY_CONCRETE.defaultBlockState()).register(typesVanilla);
      VanillaProps.stone().name("damaged_concrete_wall").texture("block/2_advanced_refined/8_concrete/damaged_concrete_wall").register(types);
      VanillaProps.stone().name("white_concrete").texture("minecraft:block/white_concrete").parent(Blocks.WHITE_CONCRETE.defaultBlockState()).register(typesVanilla);
      VanillaProps.stone().name("orange_concrete").texture("minecraft:block/orange_concrete").parent(Blocks.ORANGE_CONCRETE.defaultBlockState()).register(typesVanilla);
      VanillaProps.stone().name("black_concrete").texture("minecraft:block/black_concrete").parent(Blocks.BLACK_CONCRETE.defaultBlockState()).register(typesVanilla);
      VanillaProps.stone().name("light_blue_concrete").texture("minecraft:block/light_blue_concrete").parent(Blocks.LIGHT_BLUE_CONCRETE.defaultBlockState()).register(typesVanilla);
      VanillaProps.stone().name("yellow_concrete").texture("minecraft:block/yellow_concrete").parent(Blocks.YELLOW_CONCRETE.defaultBlockState()).register(typesVanilla);
      VanillaProps.stone().name("green_concrete").texture("minecraft:block/green_concrete").parent(Blocks.GREEN_CONCRETE.defaultBlockState()).register(typesVanilla);
      VanillaProps.stone().name("pink_concrete").texture("minecraft:block/pink_concrete").parent(Blocks.PINK_CONCRETE.defaultBlockState()).register(typesVanilla);
      VanillaProps.stone().name("gray_concrete").texture("minecraft:block/gray_concrete").parent(Blocks.GRAY_CONCRETE.defaultBlockState()).register(typesVanilla);
      VanillaProps.stone().name("carbonite_paneling").texture("minecraft:block/purpur_block").parent(Blocks.PURPUR_BLOCK.defaultBlockState()).register(typesVanilla);
      VanillaProps.stone().name("purple_concrete").texture("minecraft:block/purple_concrete").parent(Blocks.PURPLE_CONCRETE.defaultBlockState()).register(typesVanilla);
      VanillaProps.stone().name("brown_concrete").texture("minecraft:block/brown_concrete").parent(Blocks.BROWN_CONCRETE.defaultBlockState()).register(typesVanilla);
      VanillaProps.stone()
         .name("large_andesite_masonry")
         .texture("minecraft:block/polished_andesite")
         .parent(Blocks.POLISHED_ANDESITE.defaultBlockState())
         .register(typesVanillaNoStairs);
      VanillaProps.stone()
         .name("large_granite_brick")
         .texture("minecraft:block/polished_granite")
         .parent(Blocks.POLISHED_GRANITE.defaultBlockState())
         .register(typesVanillaNoStairs);
      VanillaProps.stone()
         .name("large_smooth_limestone_brick")
         .texture("minecraft:block/smooth_stone")
         .parent(Blocks.SMOOTH_STONE.defaultBlockState())
         .register(typesVanilla);
      VanillaProps.stone()
         .name("large_light_limestone_brick")
         .texture("minecraft:block/polished_diorite")
         .parent(Blocks.POLISHED_DIORITE.defaultBlockState())
         .register(typesVanillaNoStairs);
      VanillaProps.stone().name("large_marble_blocks").texture("minecraft:block/quartz_bricks").parent(Blocks.QUARTZ_BRICKS.defaultBlockState()).register(typesVanilla);
   }
}
