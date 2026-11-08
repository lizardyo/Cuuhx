package com.conquestrefabricated.core.block.builder;

import com.conquestrefabricated.content.blocks.CustomOffsetType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour.OffsetType;

public class VanillaProps {
   public VanillaProps() {
   }

   public static Props stone() {
      return Props.create(Blocks.STONE).getProps();
   }

   public static Props mosaic() {
      return Props.create(Blocks.GRAY_GLAZED_TERRACOTTA);
   }

   public static Props wood() {
      return planks();
   }

   public static Props woodLike() {
      return Props.create(Blocks.OAK_WOOD);
   }

   public static Props bricks() {
      return Props.create(Blocks.BRICKS).group((CreativeModeTab)BuiltInRegistries.CREATIVE_MODE_TAB.get(CreativeModeTabs.BUILDING_BLOCKS));
   }

   public static Props planks() {
      return Props.create(Blocks.OAK_PLANKS).group((CreativeModeTab)BuiltInRegistries.CREATIVE_MODE_TAB.get(CreativeModeTabs.BUILDING_BLOCKS));
   }

   public static Props logs() {
      return Props.create(Blocks.OAK_PLANKS).group((CreativeModeTab)BuiltInRegistries.CREATIVE_MODE_TAB.get(CreativeModeTabs.BUILDING_BLOCKS));
   }

   public static Props sand() {
      return Props.create(Blocks.SAND).group((CreativeModeTab)BuiltInRegistries.CREATIVE_MODE_TAB.get(CreativeModeTabs.BUILDING_BLOCKS));
   }

   public static Props gravel() {
      return Props.create(Blocks.GRAVEL).group((CreativeModeTab)BuiltInRegistries.CREATIVE_MODE_TAB.get(CreativeModeTabs.BUILDING_BLOCKS));
   }

   public static Props grass() {
      return Props.create(Blocks.GRASS_BLOCK).group((CreativeModeTab)BuiltInRegistries.CREATIVE_MODE_TAB.get(CreativeModeTabs.BUILDING_BLOCKS)).grassColor();
   }

   public static Props grassLike() {
      return Props.create(Blocks.DIRT)
         .group((CreativeModeTab)BuiltInRegistries.CREATIVE_MODE_TAB.get(CreativeModeTabs.BUILDING_BLOCKS))
         .strength(0.6F, 0.6F)
         .sound(SoundType.GRASS)
         .grassColor()
         .offset(OffsetType.NONE);
   }

   public static Props glass() {
      return Props.create(Blocks.GLASS).group((CreativeModeTab)BuiltInRegistries.CREATIVE_MODE_TAB.get(CreativeModeTabs.NATURAL_BLOCKS));
   }

   public static Props cloth() {
      return Props.create(Blocks.WHITE_WOOL).group((CreativeModeTab)BuiltInRegistries.CREATIVE_MODE_TAB.get(CreativeModeTabs.NATURAL_BLOCKS));
   }

   public static Props plants() {
      return Props.create(Blocks.SHORT_GRASS)
         .group((CreativeModeTab)BuiltInRegistries.CREATIVE_MODE_TAB.get(CreativeModeTabs.NATURAL_BLOCKS))
         .strength(0.0, 0.0)
         .offset(OffsetType.XYZ)
         .customOffsetType(CustomOffsetType.PLANT_XYZ)
         .dynamicBounds(true);
   }

   public static Props hangingPlants() {
      return Props.create(Blocks.SHORT_GRASS)
         .group((CreativeModeTab)BuiltInRegistries.CREATIVE_MODE_TAB.get(CreativeModeTabs.NATURAL_BLOCKS))
         .strength(0.0, 0.0)
         .offset(OffsetType.XYZ);
   }

   public static Props plantsPlain() {
      return Props.create(Blocks.SHORT_GRASS).group((CreativeModeTab)BuiltInRegistries.CREATIVE_MODE_TAB.get(CreativeModeTabs.NATURAL_BLOCKS)).strength(0.0, 0.0);
   }

   public static Props flowers() {
      return Props.create(Blocks.SHORT_GRASS)
         .group((CreativeModeTab)BuiltInRegistries.CREATIVE_MODE_TAB.get(CreativeModeTabs.NATURAL_BLOCKS))
         .strength(0.0, 0.0)
         .offset(OffsetType.XZ)
         .customOffsetType(CustomOffsetType.PLANT_XZ)
         .dynamicBounds(true);
   }

   public static Props saplings() {
      return Props.create(Blocks.CHERRY_SAPLING)
         .group((CreativeModeTab)BuiltInRegistries.CREATIVE_MODE_TAB.get(CreativeModeTabs.NATURAL_BLOCKS))
         .strength(0.0, 0.0)
         .offset(OffsetType.XYZ)
         .customOffsetType(CustomOffsetType.PLANT_XYZ)
         .dynamicBounds(true);
   }

   public static Props crops() {
      return Props.create(Blocks.WHEAT)
         .group((CreativeModeTab)BuiltInRegistries.CREATIVE_MODE_TAB.get(CreativeModeTabs.NATURAL_BLOCKS))
         .strength(0.0, 0.0)
         .offset(OffsetType.XZ)
         .customOffsetType(CustomOffsetType.PLANT_XZ)
         .dynamicBounds(true);
   }

   public static Props earth() {
      return Props.create(Blocks.DIRT).group((CreativeModeTab)BuiltInRegistries.CREATIVE_MODE_TAB.get(CreativeModeTabs.BUILDING_BLOCKS));
   }

   public static Props plantLike() {
      return Props.create(Blocks.OAK_PLANKS).sound(SoundType.GRASS).group((CreativeModeTab)BuiltInRegistries.CREATIVE_MODE_TAB.get(CreativeModeTabs.BUILDING_BLOCKS));
   }

   public static Props ice() {
      return Props.create(Blocks.PACKED_ICE).group((CreativeModeTab)BuiltInRegistries.CREATIVE_MODE_TAB.get(CreativeModeTabs.BUILDING_BLOCKS));
   }

   public static Props metal() {
      return Props.create(Blocks.IRON_BLOCK).group((CreativeModeTab)BuiltInRegistries.CREATIVE_MODE_TAB.get(CreativeModeTabs.BUILDING_BLOCKS));
   }

   public static Props leaves() {
      return Props.create(Blocks.OAK_LEAVES).group((CreativeModeTab)BuiltInRegistries.CREATIVE_MODE_TAB.get(CreativeModeTabs.BUILDING_BLOCKS));
   }

   public static Props leafLike() {
      return Props.create(Blocks.SHORT_GRASS).group((CreativeModeTab)BuiltInRegistries.CREATIVE_MODE_TAB.get(CreativeModeTabs.BUILDING_BLOCKS));
   }

   public static Props bamboo() {
      return Props.create(Blocks.BAMBOO).group((CreativeModeTab)BuiltInRegistries.CREATIVE_MODE_TAB.get(CreativeModeTabs.BUILDING_BLOCKS));
   }

   public static Props chain() {
      return Props.create(Blocks.CHAIN).group((CreativeModeTab)BuiltInRegistries.CREATIVE_MODE_TAB.get(CreativeModeTabs.BUILDING_BLOCKS));
   }

   public static Props grassyStone() {
      return stone().grassColor();
   }

   public static Props grassyEarth() {
      return earth().grassColor();
   }

   public static Props grassySand() {
      return sand().grassColor();
   }

   public static Props grassyGravel() {
      return gravel().grassColor();
   }
}
