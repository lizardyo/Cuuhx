package com.conquestrefabricated.core.asset.template;

import com.conquestrefabricated.core.asset.override.EmptyOverride;
import com.google.gson.JsonElement;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

public interface JsonOverride {
   boolean apply(JsonElement var1, JsonWriter var2) throws IOException;

   boolean appliesTo(String var1, JsonElement var2);

   default JsonOverride add(JsonOverride other) {
      if (this == EmptyOverride.EMPTY) {
         return other;
      }

      if (other == EmptyOverride.EMPTY) {
         return this;
      }

      final JsonOverride self = this;
      return new JsonOverride() {
         private final JsonOverride a = self;
         private final JsonOverride b = other;

         @Override
         public boolean apply(JsonElement input, JsonWriter output) throws IOException {
            return this.a.apply(input, output) || this.b.apply(input, output);
         }

         @Override
         public boolean appliesTo(String key, JsonElement value) {
            return this.a.appliesTo(key, value) || this.b.appliesTo(key, value);
         }
      };
   }
}
