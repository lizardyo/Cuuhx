package com.conquestrefabricated.core.util;

import java.util.Optional;
import java.util.function.Supplier;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;

public class Provider<T extends ItemLike> implements ItemLike {
   private final String name;
   private final Supplier<T> supplier;
   private final Supplier<T> defaultValue;
   private T value;

   private Provider(String name, Supplier<T> supplier, Supplier<T> defaultValue) {
      this.name = name;
      this.supplier = supplier;
      this.defaultValue = defaultValue;
   }

   public T get() {
      if (this.value == null) {
         this.value = this.supplier.get();
         if (this.value == null) {
            new NullPointerException("Invalid item: " + this.name).printStackTrace();
            this.value = this.defaultValue.get();
         }
      }

      return this.value;
   }

   public Provider.Stack toStack() {
      return new Provider.Stack(this);
   }

   public net.minecraft.world.item.Item asItem() {
      T t = this.get();
      if (t == null) {
         throw new NullPointerException("Invalid item: " + this.name);
      } else {
         return t.asItem();
      }
   }

   public static Provider.Block block(String name) {
      return block(ResourceLocation.fromNamespaceAndPath(name));
   }

   public static Provider.Block block(ResourceLocation name) {
      return block(name + "", () -> (net.minecraft.world.level.block.Block)BuiltInRegistries.BLOCK.get(name));
   }

   public static Provider.Block block(String name, Supplier<net.minecraft.world.level.block.Block> getter) {
      return new Provider.Block(name, getter);
   }

   public static Provider.Item item(String name) {
      return item(ResourceLocation.fromNamespaceAndPath(name));
   }

   public static Provider.Item item(ResourceLocation name) {
      return item(name + "", () -> (net.minecraft.world.item.Item)BuiltInRegistries.ITEM.get(name));
   }

   public static Provider.Item item(String name, Supplier<net.minecraft.world.item.Item> getter) {
      return new Provider.Item(name, getter);
   }

   public static class Block extends Provider<net.minecraft.world.level.block.Block> {
      public Block(String name, Supplier<net.minecraft.world.level.block.Block> supplier) {
         super(name, supplier, () -> Blocks.AIR);
      }
   }

   public static class Item extends Provider<net.minecraft.world.item.Item> {
      public Item(String name, Supplier<net.minecraft.world.item.Item> supplier) {
         super(name, supplier, () -> Items.AIR);
      }
   }

   public static class Stack implements Supplier<ItemStack> {
      private final ItemLike provider;

      public Stack(ItemLike provider) {
         this.provider = provider;
      }

      public ItemStack get() {
         return new ItemStack(this.provider.asItem());
      }

      public Optional<ItemStack> getSafely() {
         net.minecraft.world.item.Item item = this.provider.asItem();
         return item == Items.AIR ? Optional.empty() : Optional.of(new ItemStack(item));
      }
   }
}
