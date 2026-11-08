package com.conquestrefabricated.api.painting.vanilla;

import com.conquestrefabricated.api.painting.art.Art;
import com.conquestrefabricated.api.painting.art.ArtRenderer;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.decoration.PaintingVariant;

public class VanillaArt implements Art<PaintingVariant> {
   public static final List<Art<PaintingVariant>> ALL = Collections.unmodifiableList(
      paintingVariants().stream().map(VanillaArt::new).collect(Collectors.toList())
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
      return this.art.width();
   }

   @Override
   public int height() {
      return this.art.height();
   }

   @Override
   public int textureWidth() {
      return this.art.width();
   }

   @Override
   public int textureHeight() {
      return this.art.height();
   }

   public PaintingVariant getReference() {
      return this.art;
   }

   @Override
   public String getName() {
      return paintingVariants().getKey(this.art) + "";
   }

   @Override
   public String getDisplayName(String parent) {
      return paintingVariants().getResourceKey(this.art).orElseThrow().location().getPath();
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
      PaintingVariant type = paintingVariants().get(ResourceLocation.parse(name));
      return Art.find(type, ALL);
   }

   @SuppressWarnings("unchecked")
   private static Registry<PaintingVariant> paintingVariants() {
      return (Registry<PaintingVariant>)BuiltInRegistries.REGISTRY.get(Registries.PAINTING_VARIANT.location());
   }
}
