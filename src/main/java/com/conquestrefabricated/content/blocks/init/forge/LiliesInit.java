package com.conquestrefabricated.content.blocks.init.forge;

import com.conquestrefabricated.content.blocks.block.plants.LilyPad;
import com.conquestrefabricated.content.blocks.block.plants.LilyPadToggle2;
import com.conquestrefabricated.content.items.item.LilypadItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class LiliesInit {
   public static final Block WHITE_WATER_LILIES = createBlock("white_water_lilies", SoundType.WET_GRASS, false);
   public static final Block BIG_WATER_LILIES = createBlock("big_water_lilies", SoundType.WET_GRASS, false);
   public static final Block DUCKWEED = createLilyToggle2Block("duckweed", SoundType.WET_GRASS, false);
   public static final Block FLOATING_ICE = createBlock("floating_ice", SoundType.GLASS, false);

   public LiliesInit() {
   }

   public static void registerBlocks() {
      // Deferred until the NeoForge registry migration is complete.
   }

   public static void registerItems() {
      // Deferred until the NeoForge registry migration is complete.
   }

   private static LilyPadToggle2 createLilyToggle2Block(String name, SoundType soundType, boolean hasCollision) {
      Properties props = Properties.of().strength(0.5F).sound(soundType);
      if (!hasCollision) {
         props = props.noCollission();
      }
      return new LilyPadToggle2(props);
   }

   private static LilyPad createBlock(String name, SoundType soundType, boolean hasCollision) {
      Properties props = Properties.of().strength(0.5F).sound(soundType);
      if (!hasCollision) {
         props = props.noCollission();
      }
      return new LilyPad(props);
   }

   private static Item createItem(Block block) {
      return new LilypadItem(block, new net.minecraft.world.item.Item.Properties());
   }
}
