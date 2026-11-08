package com.conquestrefabricated.core.asset;

import com.google.gson.JsonElement;
import java.io.IOException;
import java.io.InputStream;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.ResourceManager;

public interface VirtualResource {
   String getPath();

   String getNamespace();

   PackType getType();

   JsonElement getJson(ResourceManager var1) throws IOException;

   InputStream getInputStream(ResourceManager var1) throws IOException;
}
