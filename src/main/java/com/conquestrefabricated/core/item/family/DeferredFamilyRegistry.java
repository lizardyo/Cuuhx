package com.conquestrefabricated.core.item.family;

import com.conquestrefabricated.core.block.factory.TypeList;
import com.conquestrefabricated.core.util.cache.Cache;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class DeferredFamilyRegistry<T extends ItemLike> extends Cache<ResourceLocation, DeferredFamily<T>> implements FamilyFactory<T> {
   public static final DeferredFamilyRegistry<Block> BLOCKS = new DeferredFamilyRegistry((T)Blocks.AIR, (FamilyRegistry<T>)FamilyRegistry.BLOCKS);
   private final T empty;
   private final DeferredFamily<T> none;
   private final FamilyRegistry<T> registry;

   private DeferredFamilyRegistry(T empty, FamilyRegistry<T> registry) {
      this.empty = empty;
      this.registry = registry;
      this.none = new DeferredFamily<>(null, empty, registry);
   }

   public DeferredFamily<T> compute(ResourceLocation name) {
      return new DeferredFamily<>(name, this.empty, this.registry);
   }

   @Override
   public Family<T> create(ResourceLocation name, CreativeModeTab group, TypeList types) {
      return name == null ? this.none : this.get(name);
   }

   public void registerAll() {
      this.forEach((name, family) -> family.register());
   }
}
