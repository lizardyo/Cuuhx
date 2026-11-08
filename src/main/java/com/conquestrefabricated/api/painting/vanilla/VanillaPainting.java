package com.conquestrefabricated.api.painting.vanilla;

import com.conquestrefabricated.api.painting.Painting;
import net.minecraft.resources.ResourceLocation;

public class VanillaPainting implements Painting {
   public static final Painting INSTANCE = new VanillaPainting();
   private final ResourceLocation name = ResourceLocation.fromNamespaceAndPath("conquest:vanilla_painting");

   public VanillaPainting() {
   }

   @Override
   public String getName() {
      return "Vanilla";
   }

   @Override
   public String getTranslationKey() {
      return "";
   }

   @Override
   public ResourceLocation getRegistryName() {
      return this.name;
   }

   public static Painting fromName(String name) {
      return INSTANCE;
   }
}
