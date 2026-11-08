package com.conquestrefabricated.client.gui.palette.texture;

import com.conquestrefabricated.core.util.OptionalValue;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class TextureGroup implements OptionalValue {
   protected static final TextureGroup NONE = new TextureGroup("none");
   private final String name;
   private final LinkedList<ItemStack> itemStacks = new LinkedList<>();
   private Comparator<Item> order;
   private static final Comparator<Item> BY_NAME = (i1, i2) -> BuiltInRegistries.ITEM.getKey(i1) != null
         && BuiltInRegistries.ITEM.getKey(i2) != null
      ? BuiltInRegistries.ITEM.getKey(i1).compareTo(BuiltInRegistries.ITEM.getKey(i2))
      : 0;

   public TextureGroup(String name) {
      this.order = BY_NAME;
      this.name = name;
   }

   @Override
   public boolean isAbsent() {
      return this == NONE;
   }

   public String getName() {
      return this.name;
   }

   public void addTo(NonNullList<ItemStack> list) {
      for (ItemStack stack : this.itemStacks) {
         list.add(stack.copy());
      }
   }

   public ItemStack getHead() {
      return this.itemStacks.isEmpty() ? ItemStack.EMPTY : this.itemStacks.getFirst().copy();
   }

   public List<ItemStack> getItems() {
      return this.itemStacks;
   }

   public NonNullList<ItemStack> getItemsCopy() {
      NonNullList<ItemStack> list = NonNullList.create();
      this.addTo(list);
      return list;
   }

   protected void sort(Comparator<Item> order) {
      this.order = order;
      this.itemStacks.sort(wrap(order));
   }

   protected void add(Item item) {
      for (int i = 0; i < this.itemStacks.size(); i++) {
         ItemStack current = this.itemStacks.get(i);
         if (this.order.compare(item, current.getItem()) < 0) {
            this.itemStacks.add(i, item.getDefaultInstance());
            return;
         }
      }

      this.itemStacks.add(item.getDefaultInstance());
   }

   private static Comparator<ItemStack> wrap(Comparator<Item> comparator) {
      return (s1, s2) -> comparator.compare(s1.getItem(), s2.getItem());
   }
}
