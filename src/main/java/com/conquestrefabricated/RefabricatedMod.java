package com.conquestrefabricated;

import com.conquestrefabricated.client.gui.config.ConquestConfig;
import com.conquestrefabricated.content.effects.Effects;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;

@Mod("conquest")
public class RefabricatedMod {
   public static Boolean localPlantSlowness;
   public static Boolean localPlantBreaking;
   public static Boolean localPassThroughLeaves;

   public RefabricatedMod(IEventBus modEventBus) {
      ConquestConfig.INSTANCE.getClass();
      Effects.REGISTRY.register(modEventBus);
      modEventBus.register(this);
      NeoForge.EVENT_BUS.register(this);
   }
}
