package com.conquestrefabricated.content.blocks.particles;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;

@OnlyIn(Dist.CLIENT)
public class AnimalParticleFactory implements ParticleProvider<AnimalParticleData> {
   private final SpriteSet sprites;

   public AnimalParticleFactory(SpriteSet sprite) {
      this.sprites = sprite;
   }

   private AnimalParticleFactory() {
      throw new UnsupportedOperationException("Use the AnimalParticleFactory(IAnimatedSprite sprite) constructor");
   }

   @Nullable
   public Particle createParticle(
      AnimalParticleData p_199234_1_, ClientLevel clientWorld, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed
   ) {
      AnimalParticle newParticle = new AnimalParticle(clientWorld, x, y, z, this.sprites);
      newParticle.pickSprite(this.sprites);
      return newParticle;
   }
}
