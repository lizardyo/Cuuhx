package com.conquestrefabricated.content.blocks.particles;

import com.mojang.serialization.Codec;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import com.mojang.serialization.MapCodec;

public class AnimalParticleType extends ParticleType<AnimalParticleData> {
   private static boolean ALWAYS_SHOW_REGARDLESS_OF_DISTANCE_FROM_PLAYER = true;

   public AnimalParticleType() {
      super(ALWAYS_SHOW_REGARDLESS_OF_DISTANCE_FROM_PLAYER);
   }

   public MapCodec<AnimalParticleData> codec() {
      return MapCodec.unit(new AnimalParticleData(this));
   }

   public StreamCodec<? super RegistryFriendlyByteBuf, AnimalParticleData> streamCodec() {
      return StreamCodec.unit(new AnimalParticleData(this));
   }
}
