package com.conquestrefabricated.client.gui.palette;

import com.conquestrefabricated.client.ModBinds;
import com.conquestrefabricated.client.gui.CustomCreativeScreen;
import com.conquestrefabricated.client.gui.palette.component.PaletteSettings;
import com.conquestrefabricated.client.gui.render.Render;
import com.conquestrefabricated.client.tutorial.Tutorials;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class PaletteScreen extends CustomCreativeScreen<PaletteContainer> {
   private static final ResourceLocation WHEEL = ResourceLocation.fromNamespaceAndPath("conquest:textures/gui/picker/wheel.png");
   private static final int EXIT = 256;
   private static final int SIZE = 218;
   private final Screen previous;
   private final PaletteSettings settings = new PaletteSettings();
   private Slot hovered = null;

   public PaletteScreen(Player player, Inventory inventory, PaletteContainer container) {
      this(null, player, inventory, container);
   }

   public PaletteScreen(Screen previous, Player player, Inventory inventory, PaletteContainer container) {
      super(container, inventory, Component.literal("Palette Screen"));
      this.previous = previous;
      player.containerMenu = container;
   }

   @Override
   protected void init() {
      super.init();
      this.settings.init(this.minecraft, this.width, this.height);
      this.addRenderableOnly(this.settings);
      this.resize(this.minecraft.getWindow().getGuiScaledWidth(), this.minecraft.getWindow().getGuiScaledHeight());
      Tutorials.openPalette = true;
   }

   public void resize(Minecraft minecraft, int width, int height) {
      super.resize(minecraft, width, height);
      this.resize(width, height);
   }

   public void render(GuiGraphics matrixStack, int mouseX, int mouseY, float partialTicks) {
      this.renderBg(matrixStack, partialTicks, mouseX, mouseY);
      ((PaletteContainer)this.getMenu()).updateStyle(this.settings);
      this.setupRender(matrixStack.pose());
      int mx = mouseX - this.leftPos;
      int my = mouseY - this.topPos;
      ((PaletteContainer)this.getMenu()).visitRadius(mx, my, (slot, depth) -> {
         float scale = slot.getScale(mx, my, this.settings);
         this.renderSlotBackGround(matrixStack, slot, slot.getStyle(), depth, scale);
      });
      ((PaletteContainer)this.getMenu()).visitRadius(mx, my, (slot, depth) -> {
         float scale = slot.getScale(mx, my, this.settings);
         this.renderSlot(matrixStack, slot, slot.getStyle(), mx, my, depth, scale);
      });
      ((PaletteContainer)this.getMenu()).visitCenter(slot -> {
         float scale = slot.getScale(mx, my, this.settings);
         RenderSystem.enableBlend();
         this.renderSlotBackGround(matrixStack, slot, slot.getStyle(), 1.0F, scale);
         this.renderSlot(matrixStack, slot, slot.getStyle(), mx, my, 1.0F, scale);
      });
      ((PaletteContainer)this.getMenu()).visitHotbar(slot -> this.renderSlot(matrixStack, slot, mx, my, 1.0F, 1.0F));
      this.renderDraggedItem(matrixStack, mx, my, 1.0F, ((PaletteContainer)this.getMenu()).getDraggedStyle());
      this.tearDownRender(matrixStack.pose());
      this.settings.render(matrixStack, mouseX, mouseY, partialTicks);
      this.renderLabels(matrixStack, mouseX, mouseY);
   }

   public void renderBg(GuiGraphics drawContext, float partialTicks, int mouseX, int mouseY) {
      this.renderBackground(drawContext);
      Render.drawTexture(WHEEL, drawContext, this.leftPos, this.topPos, 218, 218, 0.0F, 0.0F);
      ((PaletteContainer)this.getMenu()).getHotbar().renderBackground(this, drawContext);
   }

   protected void renderLabels(GuiGraphics matrixStack, int mouseX, int mouseY) {
      super.renderLabels(matrixStack, mouseX, mouseY);
      if (this.minecraft != null) {
         ItemStack display = ((PaletteContainer)this.getMenu()).getCarried();
         if (display.isEmpty()) {
            Slot slot = ((PaletteContainer)this.getMenu()).getClosestSlot(mouseX - this.leftPos, mouseY - this.topPos, true);
            this.hovered = slot;
            if (slot == null) {
               return;
            }

            display = slot.getItem();
         }

         if (display.getItem() != Items.AIR) {
            int top = this.height - 32;
            int left = this.width / 2;
            int color = 16777215;
            String text = display.getHoverName().getString();
            matrixStack.drawCenteredString(this.minecraft.font, text, left, top, color);
         }
      }
   }

   public boolean charTyped(char c, int code) {
      if (c >= '1' && c <= '9' && this.hovered != null && this.hovered.hasItem()) {
         ((PaletteContainer)this.menu).getHotbar().getInventory().setItem(c - '1', this.hovered.getItem());
         super.sendChanges();
         return true;
      } else {
         return super.charTyped(c, code);
      }
   }

   @Override
   protected boolean isContainerSlot(Slot slot) {
      return slot.container == ((PaletteContainer)this.getMenu()).getPaletteInventory();
   }

   public void onClose() {
      this.settings.onClose();
      if (this.previous != null) {
         this.previous.init(Minecraft.getInstance(), this.width, this.height);
      }

      Minecraft.getInstance().setScreen(this.previous);
   }

   private void resize(int width, int height) {
      this.imageWidth = width;
      this.imageHeight = height;
      this.leftPos = (width - 218) / 2;
      this.topPos = (height - 218) / 2;
      ((PaletteContainer)this.getMenu()).init(this);
   }

   public static boolean closesGui(int key) {
      return key == 256 || key == ModBinds.getPaletteBind().getDefaultKey().getValue();
   }
}
