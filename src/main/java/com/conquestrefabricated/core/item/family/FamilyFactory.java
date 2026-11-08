package com.conquestrefabricated.core.item.family;

import com.conquestrefabricated.core.block.factory.TypeList;
import java.util.function.BiFunction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;

public interface FamilyFactory<T> {
   Family<T> create(ResourceLocation var1, CreativeModeTab var2, TypeList var3);

   static <T> FamilyFactory<T> of(BiFunction<CreativeModeTab, TypeList, Family<T>> func) {
      return (n, g, t) -> func.apply(g, t);
   }
}
