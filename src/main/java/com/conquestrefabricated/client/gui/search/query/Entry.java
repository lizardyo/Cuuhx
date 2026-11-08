package com.conquestrefabricated.client.gui.search.query;

public class Entry<T> {
   private final T value;
   private final String name;
   private final String tags;

   public Entry(T value, String name, String tags) {
      this.value = value;
      this.name = name.toLowerCase();
      this.tags = tags;
   }

   public T getValue() {
      return this.value;
   }

   public String getName() {
      return this.name;
   }

   public String getTags() {
      return this.tags;
   }
}
