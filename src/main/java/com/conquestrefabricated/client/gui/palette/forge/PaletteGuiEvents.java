package com.conquestrefabricated.client.gui.palette.forge;

import com.conquestrefabricated.client.ModBinds;
import com.conquestrefabricated.client.gui.palette.Palette;
import com.conquestrefabricated.client.gui.palette.PaletteContainer;
import com.conquestrefabricated.client.gui.palette.PaletteScreen;
import java.util.Optional;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.EventBusSubscriber.Bus;
import net.neoforged.neoforge.client.event.InputEvent.Key;
import net.neoforged.neoforge.client.event.ScreenEvent;

@EventBusSubscriber(value = Dist.CLIENT, bus = Bus.GAME)
public class PaletteGuiEvents {
   public PaletteGuiEvents() {
   }

   @SubscribeEvent
   public static void onKeyPress(Key event) {
      if ((event.getAction() == 1 || event.getAction() == 2) && Minecraft.getInstance().screen instanceof AbstractContainerScreen<?> screen) {
         Player player = Minecraft.getInstance().player;
         if (player == null || !player.getAbilities().instabuild) {
            return;
         }

         if (screen instanceof CreativeModeInventoryScreen creativeScreen) {
            int tabIndex = BuiltInRegistries.CREATIVE_MODE_TAB.getId(CreativeModeInventoryScreen.selectedTab);
            if (tabIndex == BuiltInRegistries.CREATIVE_MODE_TAB.getId(CreativeModeTabs.searchTab())) {
               return;
            }
         }

         if (screen instanceof PaletteScreen) {
            if (PaletteScreen.closesGui(event.getKey())) {
               screen.onClose();
               return;
            }

            if (event.getKey() == Minecraft.getInstance().options.keyInventory.getDefaultKey().getValue()) {
               event.setCanceled(true);
               Minecraft.getInstance().setScreen(new CreativeModeInventoryScreen(player, FeatureFlagSet.of(), true));
               return;
            }
         }

         if (event.getKey() != ModBinds.getPaletteBind().getDefaultKey().getValue()) {
            return;
         }

         Slot slot = screen.getSlotUnderMouse();
         if (slot == null || !slot.hasItem()) {
            return;
         }

         ItemStack stack = slot.getItem();
         Optional<Container> palette = Palette.getPalette(stack);
         if (!palette.isPresent()) {
            return;
         }

         PaletteContainer container = new PaletteContainer(player.getInventory(), palette.get());
         PaletteScreen paletteScreen = new PaletteScreen(screen, player, player.getInventory(), container);
         Minecraft.getInstance().setScreen(paletteScreen);
      }
   }

   @SubscribeEvent
   public static void onRender(ScreenEvent.Render.Pre event) {
      if (Minecraft.getInstance().screen instanceof PaletteScreen) {
         event.setCanceled(true);
      }
   }
}
