package com.conquestrefabricated;

import com.conquestrefabricated.content.gamerules.ModGamerules;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.GameRules.BooleanValue;
import net.minecraft.world.level.GameRules.Category;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.EventBusSubscriber.Bus;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

@EventBusSubscriber(bus = Bus.MOD)
public class ModGameRulesInit {
   public ModGameRulesInit() {
   }

   @SubscribeEvent
   public static void onCommonSetup(FMLCommonSetupEvent event) {
      event.enqueueWork(() -> ModGamerules.BREAK_BLOCKS_ABOVE_DIRT = GameRules.register("breakFoliageCover", Category.UPDATES, BooleanValue.create(true)));
   }
}
