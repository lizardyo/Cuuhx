package com.conquestrefabricated.content.entities;

import com.conquestrefabricated.content.entities.seat.SeatEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.EntityType.Builder;

public class EntityTypes {
   public static final EntityType<SeatEntity> SEAT = build("conquest:seat", Builder.of(SeatEntity::new, MobCategory.MISC).sized(0.0F, 0.0F));

   public EntityTypes() {
   }

   public static void entityTypesInit() {
   }

   private static <T extends Entity> EntityType<T> build(String name, Builder<T> builder) {
      return builder.build(name);
   }
}
