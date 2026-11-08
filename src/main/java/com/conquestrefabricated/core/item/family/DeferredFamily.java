package com.conquestrefabricated.core.item.family;

import com.conquestrefabricated.core.util.OptimizedList;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

public class DeferredFamily<T extends ItemLike> extends Family<T> {
   private final T empty;
   private final ResourceLocation name;
   private final FamilyRegistry<T> registry;

   DeferredFamily(ResourceLocation name, T empty, FamilyRegistry<T> registry) {
      super(CreativeModeTabs.searchTab(), new OptimizedList<>());
      this.name = name;
      this.empty = empty;
      this.registry = registry;
   }

   protected T emptyValue() {
      return this.empty;
   }

   protected void addItem(CreativeModeTab group, NonNullList<ItemStack> list, T item) {
   }

   @Override
   public boolean isAbsent() {
      return true;
   }

   public void register() {
      if (this.name != null) {
         Family<T> family = this.registry.getFamily(this.name);

         for (T block : this.getMembers()) {
            family.add(block);
            this.registry.register(block, family);
         }
      }
   }
}
