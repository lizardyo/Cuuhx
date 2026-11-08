package com.conquestrefabricated.api.painting;

import com.conquestrefabricated.api.painting.art.Art;
import net.minecraft.world.item.ItemStack;

public interface PaintingHolder {
   Art<?> getArt(ItemStack var1);

   Painting getType(ItemStack var1);

   static String getArtData(ItemStack stack) {
      return stack.getTag() == null ? null : stack.getTag().getCompound("Painting").getString("ArtID");
   }

   static String getTypeData(ItemStack stack) {
      return stack.getTag() == null ? null : stack.getTag().getCompound("Painting").getString("TypeID");
   }
}
