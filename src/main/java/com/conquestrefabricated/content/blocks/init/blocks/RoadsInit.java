package com.conquestrefabricated.content.blocks.init.blocks;

import com.conquestrefabricated.content.blocks.block.Slab;
import com.conquestrefabricated.content.blocks.block.SlabCorner;
import com.conquestrefabricated.content.blocks.block.SlabEighth;
import com.conquestrefabricated.content.blocks.block.SlabQuarter;
import com.conquestrefabricated.content.blocks.block.Stairs;
import com.conquestrefabricated.content.blocks.block.VerticalCornerExtendable;
import com.conquestrefabricated.content.blocks.block.VerticalQuarterExtendable;
import com.conquestrefabricated.content.blocks.block.VerticalSlabCorner;
import com.conquestrefabricated.content.blocks.block.VerticalSlabExtendable;
import com.conquestrefabricated.core.block.builder.VanillaProps;
import com.conquestrefabricated.core.block.factory.TypeList;
import net.minecraft.world.level.block.Blocks;

public class RoadsInit {
   public RoadsInit() {
   }

   public static void init(TypeList types) {
      VanillaProps.stone().name("limestone_fanpattern_setts").texture("block/1_basic_refined/1_stone/3_limestone/limestone_fanpattern_setts").register(types);
      VanillaProps.stone().name("mixed_fanpattern_setts").texture("block/1_basic_refined/1_stone/mixed_fanpattern_setts").register(types);
      VanillaProps.stone().name("clay_brick_fanpattern_setts").texture("block/1_basic_refined/1_stone/1_clay/clay_brick_fanpattern_setts").register(types);
      VanillaProps.stone()
         .name("small_light_gray_flagstones", "small_light_gray_flagstone")
         .texture("block/1_basic_refined/1_stone/small_light_gray_flagstones")
         .register(types);
      VanillaProps.stone().name("small_gray_flagstones", "small_gray_flagstone").texture("block/1_basic_refined/1_stone/small_gray_flagstones").register(types);
      VanillaProps.stone()
         .name("small_dark_gray_flagstones", "small_dark_gray_flagstone")
         .texture("block/1_basic_refined/1_stone/small_dark_gray_flagstones")
         .register(types);
      VanillaProps.stone()
         .name("clean_light_gray_flagstones", "clean_light_gray_flagstone")
         .texture("block/1_basic_refined/1_stone/clean_light_gray_flagstones")
         .register(types);
      VanillaProps.stone()
         .name("dirty_light_gray_flagstones", "dirty_light_gray_flagstone")
         .texture("block/1_basic_refined/1_stone/dirty_light_gray_flagstones")
         .register(types);
      VanillaProps.stone()
         .name("muddy_light_gray_flagstones", "muddy_light_gray_flagstone")
         .texture("block/1_basic_refined/1_stone/muddy_light_gray_flagstones")
         .register(types);
      VanillaProps.stone()
         .name("mossy_light_gray_flagstones", "mossy_light_gray_flagstone")
         .texture("block/1_basic_refined/1_stone/mossy_light_gray_flagstones")
         .register(types);
      VanillaProps.stone().name("clean_gray_flagstones", "clean_gray_flagstone").texture("block/1_basic_refined/1_stone/clean_gray_flagstones").register(types);
      VanillaProps.stone().name("dirty_gray_flagstones", "dirty_gray_flagstone").texture("block/1_basic_refined/1_stone/dirty_gray_flagstones").register(types);
      VanillaProps.stone().name("muddy_gray_flagstones", "muddy_gray_flagstone").texture("block/1_basic_refined/1_stone/muddy_gray_flagstones").register(types);
      VanillaProps.stone().name("mossy_gray_flagstones", "mossy_gray_flagstone").texture("block/1_basic_refined/1_stone/mossy_gray_flagstones").register(types);
      VanillaProps.stone()
         .name("overgrown_gray_flagstones", "overgrown_gray_flagstone")
         .texture("block/1_basic_refined/1_stone/overgrown_gray_flagstones")
         .register(types);
      VanillaProps.stone()
         .name("chiseled_deepslate")
         .texture("top", "minecraft:block/chiseled_deepslate_top")
         .texture("bottom", "minecraft:block/chiseled_deepslate")
         .texture("minecraft:block/chiseled_deepslate")
         .parent(Blocks.CHISELED_DEEPSLATE.defaultBlockState())
         .register(
            TypeList.of(
               Slab.class,
               SlabQuarter.class,
               SlabCorner.class,
               SlabEighth.class,
               VerticalSlabCorner.class,
               VerticalSlabExtendable.class,
               VerticalCornerExtendable.class,
               VerticalQuarterExtendable.class,
               Stairs.class
            )
         );
   }
}
