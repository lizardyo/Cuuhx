package com.conquestrefabricated.content.blocks.init.forge;

import com.conquestrefabricated.content.blocks.init.BlockRegistrar;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.EventBusSubscriber.Bus;
import net.neoforged.neoforge.registries.RegisterEvent;

@EventBusSubscriber(bus = Bus.MOD)
public class BlockRegistrarEvent {
   public BlockRegistrarEvent() {
   }

   @SubscribeEvent
   public static void blocks(RegisterEvent event) {
      // Block registration is being ported to NeoForge's registry event system.
      BlockRegistrar.blocks();
   }

   @SubscribeEvent
   public static void items(RegisterEvent event) {
      // Item registration remains pending the NeoForge port.
      LiliesInit.registerItems();
      ScaffoldingInit.registerItems();
   }
}
