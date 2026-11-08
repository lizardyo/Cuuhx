package com.conquestrefabricated.core.asset.template;

import com.conquestrefabricated.core.asset.VirtualResource;
import com.google.gson.JsonElement;
import java.io.IOException;
import java.io.InputStream;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.ResourceManager;

public class TemplateResource implements VirtualResource {
   private final String path;
   private final String namespace;
   private final JsonTemplate template;
   private final JsonOverride overrides;
   private final PackType packType;

   public TemplateResource(PackType type, String namespace, String path, JsonOverride overrides, JsonTemplate template) {
      this.namespace = namespace;
      this.overrides = overrides;
      this.template = template;
      this.packType = type;
      this.path = path;
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
      return this.packType;
   }

   @Override
   public JsonElement getJson(ResourceManager resourceManager) throws IOException {
      return this.template.getJson(resourceManager, this.overrides);
   }

   @Override
   public InputStream getInputStream(ResourceManager resourceManager) throws IOException {
      return this.template.getInputStream(resourceManager, this.overrides);
   }

   @Override
   public String toString() {
      return "TemplateResource{path=" + this.path + ", template=" + this.template + "}";
   }

   public static TemplateResource asset(String namespace, String path, JsonOverride overrides, JsonTemplate template) {
      return new TemplateResource(PackType.CLIENT_RESOURCES, namespace, path, overrides, template);
   }

   public static TemplateResource data(String namespace, String path, JsonOverride overrides, JsonTemplate template) {
      return new TemplateResource(PackType.SERVER_DATA, namespace, path, overrides, template);
   }
}
