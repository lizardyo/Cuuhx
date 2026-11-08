package com.conquestrefabricated;

import com.conquestrefabricated.client.gui.config.ConquestConfig;
import com.conquestrefabricated.client.gui.config.ConquestConfigScreen;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.EventBusSubscriber.Bus;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.client.event.ClientPlayerNetworkEvent.LoggingOut;
import net.neoforged.neoforge.common.NeoForge;

@EventBusSubscriber(bus = Bus.MOD, modid = "conquest", value = Dist.CLIENT)
public class ConfigScreenEvents {
   public ConfigScreenEvents() {
   }

   @SubscribeEvent
   public static void clientSetup(FMLClientSetupEvent event) {
      ModLoadingContext.get().registerExtensionPoint(IConfigScreenFactory.class, () -> (mc, screen) -> new ConquestConfigScreen(screen, ConquestConfig.INSTANCE));
      NeoForge.EVENT_BUS.register(ConfigScreenEvents.ForgeEventListener.class);
   }

   public static class ForgeEventListener {
      public ForgeEventListener() {
      }

      @SubscribeEvent
      public static void onClientLoggedOut(LoggingOut event) {
         if (RefabricatedMod.localPlantSlowness != null) {
            ConquestConfig.INSTANCE.plantSlowness.set(RefabricatedMod.localPlantSlowness);
            ConquestConfig.INSTANCE.plantBreaking.set(RefabricatedMod.localPlantBreaking);
            ConquestConfig.INSTANCE.passThroughLeaves.set(RefabricatedMod.localPassThroughLeaves);
            ConquestConfig.INSTANCE.save();
            RefabricatedMod.localPlantSlowness = null;
            RefabricatedMod.localPlantBreaking = null;
            RefabricatedMod.localPassThroughLeaves = null;
         }
      }
   }
}
