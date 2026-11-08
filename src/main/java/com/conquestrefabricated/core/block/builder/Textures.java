package com.conquestrefabricated.core.block.builder;

import com.conquestrefabricated.core.asset.template.JsonOverride;
import com.google.common.collect.ImmutableMap;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Textures implements JsonOverride {
   public static final Textures NONE = new Textures(Collections.emptyMap());
   private final Map<String, String> textures;
   private final String matchAll;

   public Textures(Map<String, String> textures) {
      this.textures = textures;
      this.matchAll = textures.get("*");
   }

   public Map<String, String> getTextures() {
      return this.textures;
   }

   @Override
   public boolean appliesTo(String key, JsonElement value) {
      return key.equals("textures") && value.isJsonObject();
   }

   @Override
   public boolean apply(JsonElement input, JsonWriter output) throws IOException {
      JsonObject object = input.getAsJsonObject();
      output.beginObject();

      for (Entry<String, JsonElement> entry : object.entrySet()) {
         output.name(entry.getKey());
         String texture = this.textures.getOrDefault(entry.getKey(), this.matchAll);
         if (texture == null) {
            Streams.write(entry.getValue(), output);
         } else {
            output.value(texture);
         }
      }

      output.endObject();
      return true;
   }

   public static Textures.Builder builder() {
      return new Textures.Builder();
   }

   public static class Builder {
      private final Map<String, String> textures = new HashMap<>();

      public Builder() {
      }

      public boolean isEmpty() {
         return this.textures.isEmpty();
      }

      public Textures.Builder add(String name, String texture) {
         this.textures.put(name, texture);
         return this;
      }

      public Textures build() {
         return new Textures(ImmutableMap.copyOf(this.textures));
      }
   }
}
