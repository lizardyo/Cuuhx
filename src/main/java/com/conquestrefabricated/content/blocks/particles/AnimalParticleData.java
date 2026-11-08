package com.conquestrefabricated.content.blocks.particles;

import java.util.Locale;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.FriendlyByteBuf;

public class AnimalParticleData implements ParticleOptions {
   private ParticleType animalParticleType;

   public AnimalParticleData(ParticleType animalParticleType) {
      this.animalParticleType = animalParticleType;
   }

   public AnimalParticleData() {
   }

   public ParticleType<AnimalParticleData> getType() {
      return this.animalParticleType;
   }

   public void writeToNetwork(FriendlyByteBuf p_197553_1_) {
   }

   public String writeToString() {
      return String.format(Locale.ROOT, "%s %.2f %i %i %i", this.getType());
   }
}
