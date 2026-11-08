package com.conquestrefabricated.api.painting;

import com.conquestrefabricated.api.painting.art.Art;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;

public interface PaintingHolder {
   Art<?> getArt(ItemStack var1);

   Painting getType(ItemStack var1);

   static String getArtData(ItemStack stack) {
      CustomData data = stack.get(DataComponents.CUSTOM_DATA);
      return data == null ? null : data.copyTag().getCompound("Painting").getString("ArtID");
   }

   static String getTypeData(ItemStack stack) {
      CustomData data = stack.get(DataComponents.CUSTOM_DATA);
      return data == null ? null : data.copyTag().getCompound("Painting").getString("TypeID");
   }
}
