package com.conquestrefabricated.core.client.input.forge;

import com.conquestrefabricated.client.ModBinds;
import com.conquestrefabricated.client.bind.PaintingBindListener;
import com.conquestrefabricated.client.bind.PaletteBindListener;
import com.conquestrefabricated.client.bind.SearchBindListener;
import com.conquestrefabricated.core.asset.lang.Translations;
import com.conquestrefabricated.core.util.log.Log;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.EventBusSubscriber.Bus;

@EventBusSubscriber(value = Dist.CLIENT, bus = Bus.MOD)
public class BindInit {
   public BindInit() {
   }

   @SubscribeEvent
   public static void init(RegisterKeyMappingsEvent event) {
      Log.info("Registering keybinds");
      String category = "key.category.conquest";
      Translations.getInstance().add(category, "Conquest Reforged");
      event.register(Bindings.create("key.search.title", "key.keyboard.unknown", category, new SearchBindListener()));
      ModBinds.palette = Bindings.create("key.palette.title", "key.keyboard.v", category)
         .addListener(new PaletteBindListener())
         .addListener(new PaintingBindListener());
      event.register(ModBinds.palette);
   }
}
