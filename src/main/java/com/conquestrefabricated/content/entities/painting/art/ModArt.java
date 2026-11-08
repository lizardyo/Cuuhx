package com.conquestrefabricated.content.entities.painting.art;

import com.conquestrefabricated.api.painting.art.Art;
import com.conquestrefabricated.api.painting.art.ArtRenderer;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ModArt implements Art<ArtType> {
   public static final List<Art<ArtType>> ALL = Collections.unmodifiableList(Stream.of(ArtType.values()).map(ModArt::new).collect(Collectors.toList()));
   private final ArtType art;

   public ModArt(ArtType art) {
      this.art = art;
   }

   @Override
   public int u() {
      return this.art.offsetX;
   }

   @Override
   public int v() {
      return this.art.offsetY;
   }

   @Override
   public int width() {
      return this.art.sizeX;
   }

   @Override
   public int height() {
      return this.art.sizeY;
   }

   @Override
   public int textureWidth() {
      return 256;
   }

   @Override
   public int textureHeight() {
      return 256;
   }

   public ArtType getReference() {
      return this.art;
   }

   @Override
   public String getName() {
      return this.art.shapeId;
   }

   @Override
   public String getDisplayName(String parent) {
      return this.art.getDisplayName(parent);
   }

   @Override
   public List<Art<ArtType>> getAll() {
      return ALL;
   }

   @Override
   public ArtRenderer getRenderer() {
      return ArtRenderer.MOD;
   }

   public static Art<ArtType> of(ArtType art) {
      return Art.find(art, ALL);
   }

   public static Art<ArtType> fromName(String name) {
      return Art.find(ArtType.fromName(name), ALL);
   }
}
