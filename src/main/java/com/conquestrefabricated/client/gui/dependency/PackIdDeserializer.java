package com.conquestrefabricated.client.gui.dependency;

import com.google.gson.JsonObject;
import net.minecraft.server.packs.metadata.MetadataSectionSerializer;

public class PackIdDeserializer implements MetadataSectionSerializer<String> {
   public static final PackIdDeserializer INSTANCE = new PackIdDeserializer();

   public PackIdDeserializer() {
   }

   public String getMetadataSectionName() {
      return "pack";
   }

   public String fromJson(JsonObject json) {
      return json.has("pack_id") ? json.get("pack_id").getAsString() : "";
   }
}
