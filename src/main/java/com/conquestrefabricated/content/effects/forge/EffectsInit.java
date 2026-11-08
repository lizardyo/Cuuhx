package com.conquestrefabricated.content.effects.forge;

import com.conquestrefabricated.content.effects.Effects;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.EventBusSubscriber.Bus;
import net.neoforged.neoforge.registries.RegisterEvent;

@EventBusSubscriber(bus = Bus.MOD)
public class EffectsInit {
   public EffectsInit() {
   }

   @SubscribeEvent
   public static void init(RegisterEvent event) {
      event.register(Registries.MOB_EFFECT, registry -> registry.register(ResourceLocation.fromNamespaceAndPath("conquest", "custom_slowness"), Effects.CUSTOM_SLOWNESS));
   }
}
