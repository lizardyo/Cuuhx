package com.conquestrefabricated.content.blocks.init.forge;

import com.conquestrefabricated.content.blocks.init.BlockFamilyInit;
import com.google.gson.Gson;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.EventBusSubscriber.Bus;
import net.neoforged.neoforge.event.AddReloadListenerEvent;

@EventBusSubscriber(modid = "conquest", bus = Bus.GAME)
public class BlockFamilyInitEvent {
   private static final Gson GSON = new Gson();

   public BlockFamilyInitEvent() {
   }

   @SubscribeEvent
   public static void onAddReloadListeners(AddReloadListenerEvent event) {
      event.addListener(BlockFamilyInit::loadBlockFamilies);
   }
}
