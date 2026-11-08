package com.conquestrefabricated.core.asset.template;

import com.conquestrefabricated.core.util.ByteStream;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.internal.Streams;
import com.google.gson.internal.bind.JsonTreeWriter;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.util.Map.Entry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;

public class JsonTemplate {
   private static final JsonParser parser = new JsonParser();
   private static final Object lock = new Object();
   private final String path;
   private final ResourceLocation location;
   private JsonObject cached;

   JsonTemplate(String location) {
      String root = "";
      if (location.startsWith("assets/")) {
         root = "assets/";
         location = location.substring("assets/".length());
      } else if (location.startsWith("data/")) {
         root = "data/";
         location = location.substring("data/".length());
      }

      int i = location.indexOf(47);
      String domain = location.substring(0, i);
      String path = location.substring(i + 1);
      this.location = ResourceLocation.fromNamespaceAndPath(domain, path);
      this.path = root + domain + "/" + path;
   }

   @Override
   public String toString() {
      return "JsonTemplate{location=" + this.location + "}";
   }

   public JsonElement getJson(ResourceManager resourceManager, JsonOverride overrides) throws IOException {
      JsonTreeWriter writer = new JsonTreeWriter();
      this.apply(resourceManager, writer, overrides);
      return writer.get();
   }

   public void apply(ResourceManager resourceManager, JsonWriter writer, JsonOverride overrides) throws IOException {
      JsonObject object = this.getJson(resourceManager);
      this.write(writer, overrides, object);
   }

   public InputStream getInputStream(ResourceManager resourceManager, JsonOverride overrides) throws IOException {
      try (ByteStream.Output out = new ByteStream.Output()) {
         JsonWriter writer = new JsonWriter(new OutputStreamWriter(out));

         try {
            this.apply(resourceManager, writer, overrides);
         } catch (Throwable var9) {
            try {
               writer.close();
            } catch (Throwable var8) {
               var9.addSuppressed(var8);
            }

            throw var9;
         }

         writer.close();
         return out.toInputStream();
      }
   }

   private JsonObject getJson(ResourceManager resourceManager) throws IOException {
      synchronized (lock) {
         if (this.cached == null) {
            try {
               Resource resource = (Resource)resourceManager.getResource(this.location).get();

               try (
                  InputStream in = resource.open();
                  Reader reader = new InputStreamReader(in);
               ) {
                  JsonElement element = parser.parse(reader);
                  if (!element.isJsonObject()) {
                     throw new IOException("resource is not a template object");
                  }

                  this.cached = element.getAsJsonObject();
               }
            } catch (Throwable t) {
               throw new IOException(this.path, t);
            }
         }
      }

      return this.cached;
   }

   private void write(JsonWriter writer, JsonOverride overrides, JsonElement element) throws IOException {
      if (element.isJsonArray()) {
         writer.beginArray();

         for (JsonElement entry : element.getAsJsonArray()) {
            this.write(writer, overrides, entry);
         }

         writer.endArray();
      } else if (!element.isJsonObject()) {
         Streams.write(element, writer);
      } else {
         writer.beginObject();

         for (Entry<String, JsonElement> entry : element.getAsJsonObject().entrySet()) {
            String key = entry.getKey();
            JsonElement value = entry.getValue();
            writer.name(key);
            boolean overwritten = overrides.appliesTo(key, value) && overrides.apply(value, writer);
            if (!overwritten) {
               this.write(writer, overrides, value);
            }
         }

         writer.endObject();
      }
   }
}
