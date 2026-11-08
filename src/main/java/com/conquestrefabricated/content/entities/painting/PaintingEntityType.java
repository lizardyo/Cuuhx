package com.conquestrefabricated.content.entities.painting;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.EntityType.Builder;

public class PaintingEntityType {
   public static final EntityType<EntityPainting> PAINTING = build(
      "conquest:painting", Builder.of(EntityPainting::new, MobCategory.MISC).sized(0.5F, 0.5F)
   );

   public PaintingEntityType() {
   }

   public static void paintingEntityTypesInit() {
   }

   private static <T extends Entity> EntityType<T> build(String name, Builder<T> builder) {
      return builder.build(name);
   }
}
