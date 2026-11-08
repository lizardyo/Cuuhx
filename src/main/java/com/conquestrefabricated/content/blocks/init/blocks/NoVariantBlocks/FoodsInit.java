package com.conquestrefabricated.content.blocks.init.blocks.NoVariantBlocks;

import com.conquestrefabricated.content.blocks.BlockVoxelShapes;
import com.conquestrefabricated.content.blocks.block.FourWay;
import com.conquestrefabricated.content.blocks.block.VerticalCorner;
import com.conquestrefabricated.content.blocks.block.VerticalQuarter;
import com.conquestrefabricated.content.blocks.block.VerticalSlab;
import com.conquestrefabricated.content.blocks.block.decor.AxisFurniture;
import com.conquestrefabricated.content.blocks.block.decor.ModelBlock;
import com.conquestrefabricated.content.blocks.block.decor.Rack;
import com.conquestrefabricated.content.blocks.block.directional.AxisDirectional;
import com.conquestrefabricated.content.blocks.block.directional.HorizontalDirectional;
import com.conquestrefabricated.content.blocks.block.food.Cake;
import com.conquestrefabricated.content.blocks.block.food.CastIronCauldron;
import com.conquestrefabricated.content.blocks.block.food.CastIronPots;
import com.conquestrefabricated.content.blocks.block.food.DrinkingGlass;
import com.conquestrefabricated.content.blocks.block.food.GenericFood;
import com.conquestrefabricated.content.blocks.block.food.Plate;
import com.conquestrefabricated.content.blocks.block.food.Tankard;
import com.conquestrefabricated.content.blocks.group.ModGroups;
import com.conquestrefabricated.core.block.builder.VanillaProps;
import com.conquestrefabricated.core.block.factory.TypeList;
import com.conquestrefabricated.core.util.RenderLayer;

public class FoodsInit {
   public FoodsInit() {
   }

   public static void init() {
      VanillaProps.planks()
         .group(ModGroups.FOOD_BLOCKS)
         .name("apples")
         .manual()
         .blocking(false)
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.cubePartialShape)
         .register(TypeList.of(ModelBlock.OffsetXYZ.class));
      VanillaProps.planks()
         .group(ModGroups.FOOD_BLOCKS)
         .name("wine_rack")
         .texture("end", "block/4_furniture/3_wood/oak_storage_top")
         .texture("top", "block/4_furniture/3_wood/oak_storage_top")
         .texture("bottom", "block/4_furniture/3_wood/oak_storage_top")
         .texture("*", "block/6_foodstuffs/3_shelfs/wine_rack")
         .register(TypeList.of(AxisFurniture.class, VerticalSlab.class, VerticalCorner.class, VerticalQuarter.class));
      VanillaProps.planks()
         .group(ModGroups.FOOD_BLOCKS)
         .name("diamond_frame_wine_rack")
         .texture("end", "block/1_basic_refined/3_wood/birch/light_birch_wood_planks")
         .texture("top", "block/1_basic_refined/3_wood/birch/light_birch_wood_planks")
         .texture("bottom", "block/1_basic_refined/3_wood/birch/light_birch_wood_planks")
         .texture("*", "block/6_foodstuffs/3_shelfs/diamond_frame_wine_rack")
         .register(TypeList.of(AxisFurniture.class, VerticalSlab.class, VerticalCorner.class, VerticalQuarter.class));
      VanillaProps.planks()
         .group(ModGroups.FOOD_BLOCKS)
         .name("cut_cheese")
         .manual()
         .render(RenderLayer.CUTOUT)
         .variantFamily()
         .register(TypeList.of(GenericFood.class));
      VanillaProps.planks().group(ModGroups.FOOD_BLOCKS).name("cheese_wheel").manual().variantFamily().register(TypeList.of(Cake.class));
      VanillaProps.planks().group(ModGroups.FOOD_BLOCKS).name("apple_pie").manual().variantFamily().register(TypeList.of(Cake.class));
      VanillaProps.planks().group(ModGroups.FOOD_BLOCKS).name("big_fruit_cake_with_icing").manual().variantFamily().register(TypeList.of(Cake.class));
      VanillaProps.planks().group(ModGroups.FOOD_BLOCKS).name("big_chocolate_cake").manual().variantFamily().register(TypeList.of(Cake.class));
      VanillaProps.planks().group(ModGroups.FOOD_BLOCKS).name("big_bread").manual().variantFamily().register(TypeList.of(Cake.class));
      VanillaProps.planks().group(ModGroups.FOOD_BLOCKS).name("bread_plate").manual().blocking(false).variantFamily().register(TypeList.of(Plate.class));
      VanillaProps.planks()
         .group(ModGroups.FOOD_BLOCKS)
         .name("sliced_bread_loaf")
         .manual()
         .blocking(false)
         .variantFamily()
         .register(TypeList.of(Cake.Directional.class));
      VanillaProps.planks()
         .group(ModGroups.FOOD_BLOCKS)
         .name("round_bread_loaf")
         .manual()
         .blocking(false)
         .variantFamily()
         .register(TypeList.of(Cake.Directional.class));
      VanillaProps.planks()
         .group(ModGroups.FOOD_BLOCKS)
         .name("milk_jug")
         .manual()
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.cubePartialShape)
         .register(TypeList.of(HorizontalDirectional.OffsetXYZ.Toggle4.class));
      VanillaProps.planks()
         .group(ModGroups.FOOD_BLOCKS)
         .name("jam_jars")
         .manual()
         .blocking(false)
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.axisShapes)
         .register(TypeList.of(AxisDirectional.class));
      VanillaProps.planks()
         .group(ModGroups.FOOD_BLOCKS)
         .name("terracotta_drinking_cups")
         .manual()
         .blocking(false)
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.slabBottomShape)
         .register(TypeList.of(HorizontalDirectional.OffsetXYZ.Toggle4.class));
      VanillaProps.planks()
         .group(ModGroups.FOOD_BLOCKS)
         .name("wooden_drinking_cups")
         .manual()
         .blocking(false)
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.slabBottomShape)
         .register(TypeList.of(HorizontalDirectional.OffsetXYZ.Toggle4.class));
      VanillaProps.planks()
         .render(RenderLayer.CUTOUT)
         .group(ModGroups.FOOD_BLOCKS)
         .name("pig_on_a_spit")
         .with("hitBox", BlockVoxelShapes.paneShape)
         .register(TypeList.of(HorizontalDirectional.class));
      VanillaProps.planks()
         .group(ModGroups.FOOD_BLOCKS)
         .name("sausages_on_hooks")
         .texture("block/6_foodstuffs/4_food/sausages_on_hooks")
         .register(TypeList.of(FourWay.class));
      VanillaProps.planks()
         .group(ModGroups.FOOD_BLOCKS)
         .name("dried_tobacco_rack")
         .with("hitBox", BlockVoxelShapes.verticalSlabShapes)
         .register(TypeList.of(Rack.class));
      VanillaProps.planks()
         .group(ModGroups.FOOD_BLOCKS)
         .name("brown_hanging_rabbit")
         .manual()
         .blocking(false)
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.cubePartialShape)
         .register(TypeList.of(ModelBlock.class));
      VanillaProps.planks()
         .group(ModGroups.FOOD_BLOCKS)
         .name("white_hanging_rabbit")
         .manual()
         .blocking(false)
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.cubePartialShape)
         .register(TypeList.of(ModelBlock.class));
      VanillaProps.planks()
         .group(ModGroups.FOOD_BLOCKS)
         .name("hanging_rabbit")
         .manual()
         .blocking(false)
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.cubePartialShape)
         .register(TypeList.of(ModelBlock.class));
      VanillaProps.planks()
         .group(ModGroups.FOOD_BLOCKS)
         .name("hanging_beef_shank")
         .manual()
         .blocking(false)
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.cubePartialShape)
         .register(TypeList.of(ModelBlock.class));
      VanillaProps.planks()
         .group(ModGroups.FOOD_BLOCKS)
         .name("hanging_salami")
         .manual()
         .blocking(false)
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.cubePartialShape)
         .register(TypeList.of(ModelBlock.class));
      VanillaProps.planks()
         .group(ModGroups.FOOD_BLOCKS)
         .name("hanging_sausages")
         .manual()
         .blocking(false)
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.cubePartialShape)
         .register(TypeList.of(ModelBlock.class));
      VanillaProps.planks()
         .group(ModGroups.FOOD_BLOCKS)
         .name("hanging_fish")
         .manual()
         .blocking(false)
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.cubePartialShape)
         .register(TypeList.of(ModelBlock.class));
      VanillaProps.planks()
         .group(ModGroups.FOOD_BLOCKS)
         .name("hanging_sockeye_salmon")
         .manual()
         .blocking(false)
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.cubePartialShape)
         .register(TypeList.of(ModelBlock.class));
      VanillaProps.planks()
         .group(ModGroups.FOOD_BLOCKS)
         .name("hanging_clownfish")
         .manual()
         .blocking(false)
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.cubePartialShape)
         .register(TypeList.of(ModelBlock.class));
      VanillaProps.planks()
         .group(ModGroups.FOOD_BLOCKS)
         .name("hanging_sardines")
         .manual()
         .blocking(false)
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.cubePartialShape)
         .register(TypeList.of(ModelBlock.class));
      VanillaProps.planks()
         .group(ModGroups.FOOD_BLOCKS)
         .name("hanging_mentula_fish")
         .manual()
         .blocking(false)
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.cubePartialShape)
         .register(TypeList.of(ModelBlock.class));
      VanillaProps.planks()
         .group(ModGroups.FOOD_BLOCKS)
         .name("hanging_carrots")
         .manual()
         .blocking(false)
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.cubePartialShape)
         .register(TypeList.of(ModelBlock.class));
      VanillaProps.planks()
         .group(ModGroups.FOOD_BLOCKS)
         .name("hanging_onions")
         .manual()
         .blocking(false)
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.cubePartialShape)
         .register(TypeList.of(ModelBlock.class));
      VanillaProps.planks()
         .group(ModGroups.FOOD_BLOCKS)
         .name("banana_bundle")
         .manual()
         .blocking(false)
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.cubePartialShape)
         .register(TypeList.of(ModelBlock.class));
      VanillaProps.planks()
         .group(ModGroups.FOOD_BLOCKS)
         .name("hanging_herbs")
         .manual()
         .blocking(false)
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.cubePartialShape)
         .register(TypeList.of(ModelBlock.class));
      VanillaProps.planks()
         .group(ModGroups.STORAGE)
         .name("spice_rack")
         .texture("end", "block/4_furniture/3_wood/oak_storage_top")
         .texture("top", "block/4_furniture/3_wood/oak_storage_top")
         .texture("bottom", "block/4_furniture/3_wood/oak_storage_top")
         .texture("*", "block/4_furniture/3_wood/spice_rack")
         .register(TypeList.of(AxisFurniture.class, VerticalSlab.class, VerticalCorner.class, VerticalQuarter.class));
      VanillaProps.planks()
         .group(ModGroups.FOOD_BLOCKS)
         .name("large_wooden_crate_of_food")
         .manual()
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.cubePartialShape)
         .register(TypeList.of(ModelBlock.Toggle4.class));
      VanillaProps.planks()
         .group(ModGroups.FOOD_BLOCKS)
         .name("empty_crate")
         .manual()
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.cubePartialShape)
         .register(TypeList.of(HorizontalDirectional.OffsetXYZ.Toggle2.class));
      VanillaProps.planks()
         .group(ModGroups.FOOD_BLOCKS)
         .name("crate_of_pipeweed")
         .manual()
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.cubePartialShape)
         .register(TypeList.of(HorizontalDirectional.OffsetXYZ.Toggle2.class));
      VanillaProps.planks()
         .group(ModGroups.FOOD_BLOCKS)
         .name("crate_of_grapes")
         .manual()
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.cubePartialShape)
         .register(TypeList.of(HorizontalDirectional.OffsetXYZ.Toggle2.class));
      VanillaProps.planks()
         .group(ModGroups.FOOD_BLOCKS)
         .name("crate_of_cabbages")
         .manual()
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.cubePartialShape)
         .register(TypeList.of(HorizontalDirectional.OffsetXYZ.Toggle2.class));
      VanillaProps.planks()
         .group(ModGroups.FOOD_BLOCKS)
         .name("crate_of_hops")
         .manual()
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.cubePartialShape)
         .register(TypeList.of(HorizontalDirectional.OffsetXYZ.Toggle2.class));
      VanillaProps.planks()
         .group(ModGroups.FOOD_BLOCKS)
         .name("crate_of_beetroot")
         .manual()
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.cubePartialShape)
         .register(TypeList.of(HorizontalDirectional.OffsetXYZ.Toggle2.class));
      VanillaProps.planks()
         .group(ModGroups.FOOD_BLOCKS)
         .name("crate_of_apples")
         .manual()
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.cubePartialShape)
         .register(TypeList.of(HorizontalDirectional.OffsetXYZ.Toggle2.class));
      VanillaProps.stone().group(ModGroups.FOOD_BLOCKS).name("stone_plate").manual().variantFamily().register(TypeList.of(Plate.class));
      VanillaProps.stone()
         .group(ModGroups.FOOD_BLOCKS)
         .name("stone_plate_with_water")
         .manual()
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.coverShape)
         .register(TypeList.of(ModelBlock.OffsetXYZ.class));
      VanillaProps.stone()
         .group(ModGroups.FOOD_BLOCKS)
         .name("stone_sacrificial_plate")
         .manual()
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.coverShape)
         .register(TypeList.of(ModelBlock.OffsetXYZ.class));
      VanillaProps.metal().group(ModGroups.FOOD_BLOCKS).name("metal_plate").manual().variantFamily().register(TypeList.of(Plate.class));
      VanillaProps.stone().group(ModGroups.FOOD_BLOCKS).name("porcelain_plate").manual().variantFamily().register(TypeList.of(Plate.class));
      VanillaProps.stone().group(ModGroups.FOOD_BLOCKS).name("ceramic_plate").manual().variantFamily().register(TypeList.of(Plate.class));
      VanillaProps.wood().group(ModGroups.FOOD_BLOCKS).name("wood_plate").manual().variantFamily().register(TypeList.of(Plate.class));
      VanillaProps.wood()
         .group(ModGroups.FOOD_BLOCKS)
         .name("wooden_bowls")
         .manual()
         .blocking(false)
         .solid(false)
         .with("hitBox", BlockVoxelShapes.slabBottomShape)
         .register(TypeList.of(HorizontalDirectional.OffsetXYZ.Toggle10.class));
      VanillaProps.wood()
         .group(ModGroups.FOOD_BLOCKS)
         .name("dark_wooden_bowls")
         .manual()
         .blocking(false)
         .solid(false)
         .with("hitBox", BlockVoxelShapes.slabBottomShape)
         .register(TypeList.of(HorizontalDirectional.OffsetXYZ.Toggle10.class));
      VanillaProps.stone()
         .group(ModGroups.FOOD_BLOCKS)
         .name("terracotta_bowls")
         .manual()
         .blocking(false)
         .solid(false)
         .with("hitBox", BlockVoxelShapes.slabBottomShape)
         .register(TypeList.of(HorizontalDirectional.OffsetXYZ.Toggle10.class));
      VanillaProps.stone()
         .group(ModGroups.FOOD_BLOCKS)
         .name("glazed_terracotta_bowls")
         .manual()
         .blocking(false)
         .solid(false)
         .with("hitBox", BlockVoxelShapes.slabBottomShape)
         .register(TypeList.of(HorizontalDirectional.OffsetXYZ.Toggle10.class));
      VanillaProps.stone()
         .group(ModGroups.FOOD_BLOCKS)
         .name("ash_glazed_terracotta_bowls")
         .manual()
         .blocking(false)
         .solid(false)
         .with("hitBox", BlockVoxelShapes.slabBottomShape)
         .register(TypeList.of(HorizontalDirectional.OffsetXYZ.Toggle10.class));
      VanillaProps.stone().group(ModGroups.FOOD_BLOCKS).name("goblet").manual().blocking(false).variantFamily().register(TypeList.of(DrinkingGlass.class));
      VanillaProps.stone().group(ModGroups.FOOD_BLOCKS).name("wine_goblet").manual().blocking(false).variantFamily().register(TypeList.of(DrinkingGlass.class));
      VanillaProps.glass()
         .group(ModGroups.FOOD_BLOCKS)
         .name("tall_crystal_wine_glass")
         .manual()
         .blocking(false)
         .variantFamily()
         .register(TypeList.of(DrinkingGlass.class));
      VanillaProps.glass()
         .group(ModGroups.FOOD_BLOCKS)
         .name("short_crystal_wine_glass")
         .manual()
         .blocking(false)
         .variantFamily()
         .register(TypeList.of(DrinkingGlass.class));
      VanillaProps.planks()
         .group(ModGroups.FOOD_BLOCKS)
         .name("wooden_tankard")
         .manual()
         .blocking(false)
         .with("hitBox", BlockVoxelShapes.cubePartialShape)
         .register(TypeList.of(Tankard.class));
      VanillaProps.metal()
         .group(ModGroups.FOOD_BLOCKS)
         .name("metal_tankard")
         .manual()
         .blocking(false)
         .with("hitBox", BlockVoxelShapes.cubePartialShape)
         .register(TypeList.of(Tankard.class));
      VanillaProps.stone()
         .group(ModGroups.FOOD_BLOCKS)
         .name("broken_bottle")
         .manual()
         .blocking(false)
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.cubeSmallPartialShape)
         .register(TypeList.of(ModelBlock.OffsetXYZ.class));
      VanillaProps.stone()
         .group(ModGroups.FOOD_BLOCKS)
         .name("bottle")
         .manual()
         .blocking(false)
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.cubeMediumPartialShape)
         .register(TypeList.of(ModelBlock.OffsetXYZ.class));
      VanillaProps.stone()
         .group(ModGroups.FOOD_BLOCKS)
         .name("brown_bottle")
         .manual()
         .blocking(false)
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.cubeMediumPartialShape)
         .register(TypeList.of(ModelBlock.OffsetXYZ.class));
      VanillaProps.stone()
         .group(ModGroups.FOOD_BLOCKS)
         .name("soda_bottle")
         .manual()
         .blocking(false)
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.cubeMediumPartialShape)
         .register(TypeList.of(ModelBlock.OffsetXYZ.class));
      VanillaProps.stone()
         .group(ModGroups.FOOD_BLOCKS)
         .name("small_bottle")
         .manual()
         .blocking(false)
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.cubeMediumPartialShape)
         .register(TypeList.of(ModelBlock.OffsetXYZ.class));
      VanillaProps.stone()
         .group(ModGroups.FOOD_BLOCKS)
         .name("bottle_of_frankincense")
         .manual()
         .blocking(false)
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.cubeSmallPartialShape)
         .register(TypeList.of(ModelBlock.OffsetXYZ.class));
      VanillaProps.stone()
         .group(ModGroups.FOOD_BLOCKS)
         .name("water_jug")
         .manual()
         .blocking(false)
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.cubeMediumPartialShape)
         .register(TypeList.of(ModelBlock.OffsetXYZ.class));
      VanillaProps.metal()
         .group(ModGroups.FOOD_BLOCKS)
         .name("cast_iron_pot")
         .manual()
         .with("hitBox", BlockVoxelShapes.cubePartialShape)
         .register(TypeList.of(CastIronPots.class));
      VanillaProps.metal()
         .group(ModGroups.FOOD_BLOCKS)
         .name("cast_iron_cauldron")
         .manual()
         .with("hitBox", BlockVoxelShapes.cubePartialShape)
         .register(TypeList.of(CastIronCauldron.class));
      VanillaProps.metal()
         .group(ModGroups.FOOD_BLOCKS)
         .name("cast_iron_pan")
         .manual()
         .with("hitBox", BlockVoxelShapes.cubePartialShape)
         .register(TypeList.of(CastIronCauldron.class));
      VanillaProps.metal()
         .group(ModGroups.FOOD_BLOCKS)
         .name("cast_iron_bowl")
         .manual()
         .with("hitBox", BlockVoxelShapes.cubePartialShape)
         .register(TypeList.of(CastIronPots.class));
      VanillaProps.metal()
         .group(ModGroups.FOOD_BLOCKS)
         .name("small_hanging_cauldron")
         .manual()
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.cubePartialShape)
         .register(TypeList.of(ModelBlock.class));
      VanillaProps.stone()
         .group(ModGroups.POTTERY)
         .name("cooking_brazier")
         .manual()
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.cubePartialShape)
         .register(TypeList.of(HorizontalDirectional.OffsetXYZ.Toggle3.class));
      VanillaProps.stone()
         .group(ModGroups.FOOD_BLOCKS)
         .name("terracotta_cooking_pot")
         .manual()
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.cubePartialShape)
         .register(TypeList.of(HorizontalDirectional.OffsetXYZ.Toggle5.class));
      VanillaProps.metal()
         .group(ModGroups.FOOD_BLOCKS)
         .name("bronze_cooking_pot")
         .manual()
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.cubePartialShape)
         .register(TypeList.of(HorizontalDirectional.OffsetXYZ.Toggle5.class));
      VanillaProps.metal()
         .group(ModGroups.FOOD_BLOCKS)
         .name("cast_iron_cooking_pot")
         .manual()
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.cubePartialShape)
         .register(TypeList.of(HorizontalDirectional.OffsetXYZ.Toggle5.class));
      VanillaProps.metal()
         .group(ModGroups.FOOD_BLOCKS)
         .name("iron_skillet")
         .manual()
         .blocking(false)
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.cubePartialShape)
         .register(TypeList.of(HorizontalDirectional.OffsetXYZ.Toggle8.class));
      VanillaProps.metal()
         .group(ModGroups.FOOD_BLOCKS)
         .name("iron_tripod_pot")
         .manual()
         .blocking(false)
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.cubePartialShape)
         .register(TypeList.of(HorizontalDirectional.OffsetXYZ.Toggle3.class));
      VanillaProps.metal()
         .group(ModGroups.FOOD_BLOCKS)
         .name("small_terracotta_cooking_pot")
         .manual()
         .blocking(false)
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.cubePartialShape)
         .register(TypeList.of(HorizontalDirectional.OffsetXYZ.Toggle4.class));
      VanillaProps.metal()
         .group(ModGroups.FOOD_BLOCKS)
         .name("silver_arabian_ewer_and_salver")
         .manual()
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.slabShape)
         .register(TypeList.of(HorizontalDirectional.OffsetXYZ.class));
      VanillaProps.metal()
         .group(ModGroups.FOOD_BLOCKS)
         .name("copper_arabian_ewer_and_salver")
         .manual()
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.slabShape)
         .register(TypeList.of(HorizontalDirectional.OffsetXYZ.class));
      VanillaProps.metal()
         .group(ModGroups.FOOD_BLOCKS)
         .name("pewter_jug_and_cups")
         .manual()
         .blocking(false)
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.cubePartialShape)
         .register(TypeList.of(HorizontalDirectional.OffsetXYZ.Toggle3.class));
      VanillaProps.stone()
         .group(ModGroups.FOOD_BLOCKS)
         .name("blue_bottle")
         .manual()
         .blocking(false)
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.cubeMediumPartialShape)
         .register(TypeList.of(ModelBlock.OffsetXYZ.class));
      VanillaProps.stone()
         .group(ModGroups.FOOD_BLOCKS)
         .name("white_bottle")
         .manual()
         .blocking(false)
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.cubeMediumPartialShape)
         .register(TypeList.of(ModelBlock.OffsetXYZ.class));
      VanillaProps.stone()
         .group(ModGroups.FOOD_BLOCKS)
         .name("bottle_of_wine")
         .manual()
         .blocking(false)
         .render(RenderLayer.CUTOUT)
         .with("hitBox", BlockVoxelShapes.cubeMediumPartialShape)
         .register(TypeList.of(ModelBlock.OffsetXYZ.class));
   }
}
