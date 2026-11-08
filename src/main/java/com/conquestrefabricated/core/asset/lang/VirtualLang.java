package com.conquestrefabricated.core.asset.lang;

import com.conquestrefabricated.core.asset.VirtualResource;
import com.conquestrefabricated.core.util.ByteStream;
import com.google.gson.JsonElement;
import com.google.gson.internal.bind.JsonTreeWriter;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;
import net.minecraft.core.DefaultedRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.ResourceManager;

public class VirtualLang implements VirtualResource {
   private final String path;
   private final String namespace;

   public VirtualLang(String namespace) {
      this.namespace = namespace;
      this.path = "assets/" + namespace + "/lang/en_us.json";
   }

   @Override
   public String getPath() {
      return this.path;
   }

   @Override
   public String getNamespace() {
      return this.namespace;
   }

   @Override
   public PackType getType() {
      return PackType.CLIENT_RESOURCES;
   }

   @Override
   public JsonElement getJson(ResourceManager resourceManager) throws IOException {
      JsonTreeWriter writer = new JsonTreeWriter();
      this.write(writer);
      return writer.get();
   }

   @Override
   public InputStream getInputStream(ResourceManager resourceManager) throws IOException {
      ByteStream.Output output = new ByteStream.Output();
      JsonWriter writer = new JsonWriter(new OutputStreamWriter(output));
      this.write(writer);
      writer.flush();
      return output.toInputStream();
   }

   private void write(JsonWriter writer) throws IOException {
      writer.setIndent("  ");
      writer.beginObject();
      Set<String> visited = new HashSet<>();
      Translations.getInstance().forEach((key, value) -> this.writeSafe(key, value, writer, visited));
      this.writeTranslations(BuiltInRegistries.BLOCK, "block", writer, visited);
      this.writeTranslations(BuiltInRegistries.ITEM, "item", writer, visited);
      this.writeTranslations(BuiltInRegistries.ENTITY_TYPE, "entity", writer, visited);
      writer.endObject();
   }

   private void writeSafe(String key, String value, JsonWriter writer, Set<String> visited) {
      if (visited.add(key)) {
         try {
            writer.name(key).value(value);
         } catch (IOException var6) {
         }
      }
   }

   private void writeTranslations(DefaultedRegistry<?> registry, String type, JsonWriter writer, Set<String> visited) throws IOException {
      for (ResourceLocation entry : registry.keySet()) {
         ResourceLocation name = entry;
         if (name != null && name.getNamespace().equals(this.getNamespace())) {
            String key = Translations.getKey(type, name);
            if (visited.add(key)) {
               String value = Translations.translate(name.getPath());
               writer.name(key);
               writer.value(value);
            }
         }
      }
   }
}
