package com.conquestrefabricated.content.blocks.init.forge;

import com.conquestrefabricated.content.blocks.init.ParticleRegistrar;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.EventBusSubscriber.Bus;
import net.neoforged.neoforge.registries.RegisterEvent;

@EventBusSubscriber(bus = Bus.MOD)
public class ParticleRegistrarEvent {
   public ParticleRegistrarEvent() {
   }

   @SubscribeEvent
   public static void onIParticleTypeRegistration(RegisterEvent event) {
      ParticleRegistrar.raven1ParticleType.getClass();
   }
}
