package com.conquestrefabricated.api.painting;

import com.conquestrefabricated.api.painting.art.Art;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public interface Painting {
   String getName();

   String getTranslationKey();

   ResourceLocation getRegistryName();

   default ResourceLocation getItemName() {
      return this.getRegistryName();
   }

   default ItemStack createStack(Art art) {
      return this.createStack(art, 1);
   }

   default ItemStack createStack(Art art, int count) {
      Item item = (Item)BuiltInRegistries.ITEM.get(this.getItemName());
      CompoundTag painting = new CompoundTag();
      painting.putString("TypeID", this.getName());
      painting.putString("ArtID", art.getName());
      CompoundTag data = new CompoundTag();
      data.put("Painting", painting);
      ItemStack stack = new ItemStack(item, count);
      stack.setTag(data);
      return stack;
   }
}
