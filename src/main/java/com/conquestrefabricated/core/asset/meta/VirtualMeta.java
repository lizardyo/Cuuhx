package com.conquestrefabricated.core.asset.meta;

import com.conquestrefabricated.core.asset.VirtualResource;
import com.conquestrefabricated.core.util.ByteStream;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.metadata.pack.PackMetadataSection;
import net.minecraft.server.packs.resources.ResourceManager;

public class VirtualMeta implements VirtualResource {
   public static final int PACK_FORMAT = 5;
   private final String description;
   private final String namespace;

   public VirtualMeta(String namespace, String description) {
      this.description = description;
      this.namespace = namespace;
   }

   public PackMetadataSection toMetadata() {
      return (PackMetadataSection)PackMetadataSection.TYPE.fromJson(this.toJson());
   }

   @Override
   public String getPath() {
      return "pack.mcmeta";
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
      return this.toJson();
   }

   @Override
   public InputStream getInputStream(ResourceManager resourceManager) throws IOException {
      ByteStream.Output out = new ByteStream.Output();
      JsonWriter writer = new JsonWriter(new OutputStreamWriter(out));

      try {
         Streams.write(this.toJson(), writer);
      } catch (Throwable var7) {
         try {
            writer.close();
         } catch (Throwable var6) {
            var7.addSuppressed(var6);
         }

         throw var7;
      }

      writer.close();
      return out.toInputStream();
   }

   private JsonObject toJson() {
      JsonObject pack = new JsonObject();
      pack.addProperty("description", this.description);
      pack.addProperty("pack_format", 5);
      JsonObject meta = new JsonObject();
      meta.add("pack", pack);
      meta.add("language", new JsonObject());
      return pack;
   }
}
