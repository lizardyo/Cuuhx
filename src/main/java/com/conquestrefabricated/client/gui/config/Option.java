package com.conquestrefabricated.client.gui.config;

import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;

public interface Option<T> {
   String getKey();

   T get();

   void set(T var1);

   JsonElement toJson();

   void fromJson(JsonElement var1) throws JsonParseException;

   abstract class BaseOption<T> implements Option<T> {
      protected final String key;
      protected T value;

      public BaseOption(String key, T defaultValue) {
         this.key = key;
         this.value = defaultValue;
      }

      @Override
      public String getKey() {
         return this.key;
      }

      @Override
      public T get() {
         return this.value;
      }

      @Override
      public void set(T value) {
         this.value = value;
      }
   }

   class BooleanOption extends Option.BaseOption<Boolean> {
      public BooleanOption(String key, Boolean defaultValue) {
         super(key, defaultValue);
      }

      @Override
      public JsonElement toJson() {
         return new JsonPrimitive((Boolean)this.get());
      }

      @Override
      public void fromJson(JsonElement json) throws JsonParseException {
         if (json.isJsonPrimitive()) {
            this.set(json.getAsBoolean());
         } else {
            throw new JsonParseException("Json must be a primitive");
         }
      }
   }
}
