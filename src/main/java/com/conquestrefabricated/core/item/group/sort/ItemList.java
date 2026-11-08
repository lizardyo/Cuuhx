package com.conquestrefabricated.core.item.group.sort;

import com.conquestrefabricated.core.util.Provider;
import java.io.BufferedReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ItemList implements Sorter<ItemStack>, Comparator<ItemStack> {
   private final Map<String, ItemList.Entry> index;

   private ItemList(Map<String, ItemList.Entry> index) {
      this.index = index;
   }

   public int compare(ItemStack o1, ItemStack o2) {
      int i1 = getIndex(o1, this.index);
      int i2 = getIndex(o2, this.index);
      return Integer.compare(i1, i2);
   }

   @Override
   public void apply(NonNullList<ItemStack> items) {
      this.fill(items);
   }

   @Override
   public void sort(NonNullList<ItemStack> items) {
      items.sort(this);
   }

   private void fill(NonNullList<ItemStack> items) {
      for (Map.Entry<String, ItemList.Entry> e : this.index.entrySet()) {
         if (!contains(items, e.getKey())) {
            int size = items.size();
            Item item = (Item)e.getValue().stack.get();
            if (items.size() == size) {
               items.add(new ItemStack(item));
            }
         }
      }
   }

   private static int getIndex(ItemStack stack, Map<String, ItemList.Entry> index) {
      String name = BuiltInRegistries.ITEM.getKey(stack.getItem()) + "";
      ItemList.Entry entry = index.get(name);
      return entry == null ? index.size() : entry.index;
   }

   private static boolean contains(List<ItemStack> items, String find) {
      for (ItemStack stack : items) {
         if ((BuiltInRegistries.ITEM.getKey(stack.getItem()) + "").equals(find)) {
            return true;
         }
      }

      return false;
   }

   public static ItemList read(BufferedReader reader) {
      AtomicInteger order = new AtomicInteger(0);
      Map<String, ItemList.Entry> index = new HashMap<>(50);
      reader.lines().forEach(item -> {
         if (!item.isEmpty()) {
            index.put(item, new ItemList.Entry(order.get(), Provider.item(item)));
            order.addAndGet(1);
         }
      });
      return new ItemList(index);
   }

   private static class Entry {
      private final int index;
      private final Provider<Item> stack;

      private Entry(int index, Provider<Item> provider) {
         this.index = index;
         this.stack = provider;
      }
   }
}
