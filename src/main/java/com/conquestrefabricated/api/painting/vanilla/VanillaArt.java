package com.conquestrefabricated.api.painting.vanilla;

import com.conquestrefabricated.api.painting.art.Art;
import com.conquestrefabricated.api.painting.art.ArtRenderer;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.decoration.PaintingVariant;

public class VanillaArt implements Art<PaintingVariant> {
   public static final List<Art<PaintingVariant>> ALL = Collections.unmodifiableList(
      BuiltInRegistries.PAINTING_VARIANT.stream().map(VanillaArt::new).collect(Collectors.toList())
   );
   private final PaintingVariant art;

   private VanillaArt(PaintingVariant art) {
      this.art = art;
   }

   @Override
   public int u() {
      return 0;
   }

   @Override
   public int v() {
      return 0;
   }

   @Override
   public int width() {
      return this.art.getWidth();
   }

   @Override
   public int height() {
      return this.art.getHeight();
   }

   @Override
   public int textureWidth() {
      return this.art.getWidth();
   }

   @Override
   public int textureHeight() {
      return this.art.getHeight();
   }

   public PaintingVariant getReference() {
      return this.art;
   }

   @Override
   public String getName() {
      return BuiltInRegistries.PAINTING_VARIANT.getKey(this.art) + "";
   }

   @Override
   public String getDisplayName(String parent) {
      return ((ResourceKey)BuiltInRegistries.PAINTING_VARIANT.getResourceKey(this.art).get()).location().getPath();
   }

   @Override
   public List<Art<PaintingVariant>> getAll() {
      return ALL;
   }

   @Override
   public ArtRenderer getRenderer() {
      return ArtRenderer.VANILLA;
   }

   public static Art<PaintingVariant> fromName(String name) {
      PaintingVariant type = (PaintingVariant)BuiltInRegistries.PAINTING_VARIANT.get(ResourceLocation.fromNamespaceAndPath(name));
      return Art.find(type, ALL);
   }
}
