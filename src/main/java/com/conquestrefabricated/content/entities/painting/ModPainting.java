package com.conquestrefabricated.content.entities.painting;

import com.conquestrefabricated.api.painting.Painting;
import com.conquestrefabricated.api.util.Translateable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;
import net.minecraft.resources.ResourceLocation;

public class ModPainting implements Translateable, Painting {
   private static final ResourceLocation ITEM = ResourceLocation.fromNamespaceAndPath("conquest", "painting");
   private static final ModPainting UNKNOWN = new ModPainting("unknown");
   private static final Map<String, ModPainting> types = new ConcurrentHashMap<>();
   private final String name;
   private final ResourceLocation texture;

   private ModPainting(String name) {
      this.name = name;
      this.texture = ResourceLocation.fromNamespaceAndPath("conquest", "textures/entity/painting/" + name + ".png");
   }

   public boolean isPresent() {
      return this != UNKNOWN;
   }

   @Override
   public String getName() {
      return this.name;
   }

   @Override
   public ResourceLocation getItemName() {
      return ITEM;
   }

   @Override
   public ResourceLocation getRegistryName() {
      return this.texture;
   }

   @Override
   public String getTranslationKey() {
      return this.getTranslationKey("conquest");
   }

   @Override
   public String getTranslationKey(String parent) {
      return parent + "." + this.getName();
   }

   public static ModPainting fromId(String id) {
      return id == null ? UNKNOWN : types.getOrDefault(id, UNKNOWN);
   }

   public static ModPainting fromName(String name) {
      ModPainting type = fromId(name);
      if (type != UNKNOWN) {
         return type;
      }

      for (ModPainting t : types.values()) {
         if (name.equalsIgnoreCase(t.getName())) {
            return t;
         }

         if (name.equalsIgnoreCase(t.getDisplayName())) {
            return t;
         }
      }

      return UNKNOWN;
   }

   public static ModPainting register(String id) {
      ModPainting painting = new ModPainting(id);
      types.put(id, painting);
      return painting;
   }

   public static Stream<String> getIds() {
      return types.values().stream().map(ModPainting::getName);
   }
}
