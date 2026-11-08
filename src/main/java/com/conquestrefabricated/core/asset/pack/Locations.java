package com.conquestrefabricated.core.asset.pack;

import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;

public class Locations {
   private Locations() {
   }

   public static ResourceLocation state(ResourceLocation name) {
      return ResourceLocation.fromNamespaceAndPath(name.getNamespace(), "blockstates/" + name.getPath() + ".json");
   }

   public static ResourceLocation model(ResourceLocation name) {
      return ResourceLocation.fromNamespaceAndPath(name.getNamespace(), "models/" + name.getPath() + ".json");
   }

   public static String recipePath(ResourceLocation name) {
      return path("data", "recipes", name);
   }

   public static String modRecipePath(ResourceLocation name) {
      return path("assets", "recipes", name);
   }

   public static String statePath(ResourceLocation name) {
      return path("assets", "blockstates", name);
   }

   public static String modelPath(ModelResourceLocation name) {
      return path("assets", "models", name.id());
   }

   public static String stateTemplatePath(ResourceLocation name) {
      return path("templates", "blockstates", name);
   }

   public static String modelTemplatePath(ModelResourceLocation name) {
      return path("templates", "models", name.id());
   }

   public static String path(ResourceLocation location) {
      return location.getPath().endsWith(".json")
         ? "assets/" + location.getNamespace() + "/" + location.getPath()
         : "assets/" + location.getNamespace() + "/" + location.getPath() + ".json";
   }

   public static String path(String root, String prefix, ResourceLocation location) {
      return location.getPath().endsWith(".json")
         ? root + "/" + location.getNamespace() + "/" + prefix + "/" + location.getPath()
         : root + "/" + location.getNamespace() + "/" + prefix + "/" + location.getPath() + ".json";
   }
}
