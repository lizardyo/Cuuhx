package com.conquestrefabricated.api.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
   public static final TagKey<Block> PLANT_SLOWNESS = blockTag("plant_slowness");
   public static final TagKey<Item> GARDENING_TOOLS = itemTag("gardening_tools");
   public static final TagKey<Item> CYCLING_TOOLS = itemTag("cycling_tools");

   public ModTags() {
   }

   private static TagKey<Block> blockTag(String name) {
      return TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("conquest", name));
   }

   private static TagKey<Item> itemTag(String name) {
      return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("conquest", name));
   }
}
