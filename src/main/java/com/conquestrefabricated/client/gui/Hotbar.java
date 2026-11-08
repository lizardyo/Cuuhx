package com.conquestrefabricated.client.gui;

import com.conquestrefabricated.client.gui.render.Render;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;

public class Hotbar {
   private static final ResourceLocation HOTBAR = ResourceLocation.fromNamespaceAndPath("minecraft:textures/gui/widgets.png");
   private final Container inventory;

   public Hotbar(Container inventory) {
      this.inventory = inventory;
   }

   public Container getInventory() {
      return this.inventory;
   }

   public int getSlotSize() {
      return 20;
   }

   public int getHeight() {
      return this.getSlotSize() - 1;
   }

   public void renderBackground(Screen screen, GuiGraphics drawContext) {
      int u = 0;
      int v = 0;
      int uMax = 182;
      int vMax = 22;
      int left = screen.width / 2 - uMax / 2;
      int top = screen.height - vMax;
      RenderSystem.enableBlend();
      Render.drawTexture(HOTBAR, drawContext, left, top, screen.getTabOrderGroup(), u, v, uMax, vMax);
      RenderSystem.disableBlend();
   }

   public void addTo(AbstractContainer container, int left, int top) {
      int hotbarWidth = 9 * this.getSlotSize();
      int x = left - hotbarWidth / 2 + 2;

      for (int i = 0; i < 9; i++) {
         int dx = i * this.getSlotSize();
         container.addSlot(new Slot(this.inventory, i, x + dx, top));
      }
   }
}
