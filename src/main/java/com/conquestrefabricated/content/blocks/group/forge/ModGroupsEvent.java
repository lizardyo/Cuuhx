package com.conquestrefabricated.content.blocks.group.forge;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.EventBusSubscriber.Bus;
import net.neoforged.neoforge.registries.RegisterEvent;

@EventBusSubscriber(bus = Bus.MOD)
public class ModGroupsEvent {
   public ModGroupsEvent() {
   }

   @SubscribeEvent
   public static void registerItemGroups(RegisterEvent event) {
      // Creative-mode tab registration is being migrated to NeoForge's event-driven registry.
   }
}
