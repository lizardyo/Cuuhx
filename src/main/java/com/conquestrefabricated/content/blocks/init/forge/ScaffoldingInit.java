package com.conquestrefabricated.content.blocks.init.forge;

import com.conquestrefabricated.content.blocks.block.decor.Scaffolding;
import com.conquestrefabricated.content.items.item.ScaffoldingItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class ScaffoldingInit {
   public static final Block METAL_SCAFFOLDING = createBlock("metal_scaffolding", SoundType.METAL);
   public static final Block WOOD_SCAFFOLDING = createBlock("wood_scaffolding", SoundType.WOOD);

   public ScaffoldingInit() {
   }

   public static void registerBlocks() {
      // Deferred until the NeoForge registry migration is complete.
   }

   public static void registerItems() {
      // Deferred until the NeoForge registry migration is complete.
   }

   private static Scaffolding createBlock(String name, SoundType soundType) {
      return new Scaffolding(Properties.of().strength(0.5F).sound(soundType));
   }

   private static Item createItem(Block block) {
      return new ScaffoldingItem(block, new net.minecraft.world.item.Item.Properties());
   }
}
