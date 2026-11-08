package com.conquestrefabricated.core.item.group.sort;

import net.minecraft.core.NonNullList;

public interface Sorter<T> {
   Sorter NONE = new Sorter() {
      @Override
      public void apply(NonNullList list) {
      }

      @Override
      public void sort(NonNullList list) {
      }
   };

   void apply(NonNullList<T> var1);

   void sort(NonNullList<T> var1);

   static <T> Sorter<T> none() {
      return NONE;
   }
}
