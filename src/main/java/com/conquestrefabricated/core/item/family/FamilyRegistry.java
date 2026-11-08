package com.conquestrefabricated.core.item.family;

import com.conquestrefabricated.core.item.family.block.BlockFamily;
import com.conquestrefabricated.core.item.family.item.ItemFamily;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

public class FamilyRegistry<T extends ItemLike> {
   public static final FamilyRegistry<Block> BLOCKS = new FamilyRegistry(BlockFamily.EMPTY);
   public static final FamilyRegistry<Item> ITEMS = new FamilyRegistry(ItemFamily.EMPTY);
   private final Family<T> empty;
   private final Map<ResourceLocation, Family<T>> families = new HashMap<>();

   public FamilyRegistry(Family<T> empty) {
      this.empty = empty;
   }

   public void register(Family<T> family) {
      for (T member : family.getMembers()) {
         this.register(member, family);
      }
   }

   public void register(T member, Family<T> family) {
      if (!family.isAbsent()) {
         this.families.put(BuiltInRegistries.ITEM.getKey(member.asItem()), family);
      }
   }

   public void registerToFamily(ResourceLocation parent, T child) {
      Family<T> family = this.getFamily(parent);
      if (family.isPresent()) {
         family.add(child);
      }
   }

   public Family<T> getFamily(T member) {
      return this.getFamily(BuiltInRegistries.ITEM.getKey(member.asItem()));
   }

   public Family<T> getFamily(ResourceLocation name) {
      return this.families.getOrDefault(name, this.empty);
   }

   public Stream<Family<T>> values() {
      return this.families.values().stream().distinct();
   }

   public static void bake() {
      BLOCKS.values().forEach(Family::trim);
      ITEMS.values().forEach(Family::trim);
   }
}
