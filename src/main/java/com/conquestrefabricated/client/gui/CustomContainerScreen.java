package com.conquestrefabricated.client.gui;

import com.conquestrefabricated.client.gui.palette.component.Style;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public abstract class CustomContainerScreen<T extends AbstractContainerMenu> extends AbstractContainerScreen<T> {
   private ResourceLocation SLOT_BACKGROUND = ResourceLocation.fromNamespaceAndPath("conquest:textures/gui/picker/slot.png");
   private Slot clickedSlot;
   private boolean isRightMouseClick;
   private boolean isOverSlot = false;

   public CustomContainerScreen(T screenContainer, Inventory inv, Component titleIn) {
      super(screenContainer, inv, titleIn);
   }

   protected void onSlotClick(@Nullable Slot slot, int index, int button, ClickType type) {
      this.clickedSlot = slot;
      this.isRightMouseClick = button == 2;
   }

   protected void slotClicked(@Nullable Slot slot, int index, int button, ClickType type) {
      super.slotClicked(slot, index, button, type);
      this.onSlotClick(slot, index, button, type);
   }

   protected void setupRender(PoseStack poseStack) {
      this.isOverSlot = false;
      RenderSystem.enableBlend();
      poseStack.pushPose();
      poseStack.translate(this.leftPos, this.topPos, 0.0F);
      RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
      RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
   }

   protected void tearDownRender(PoseStack poseStack) {
      poseStack.popPose();
      RenderSystem.disableBlend();
   }

   public void renderDraggedItem(GuiGraphics drawContext, int mx, int my, float depth, Style style) {
      int zlevel = 250;
      ItemStack held = this.getMenu().getCarried();
      if (!held.isEmpty()) {
         PoseStack posestack = drawContext.pose();
         posestack.pushPose();
         RenderSystem.disableBlend();
         posestack.translate(mx, my, zlevel);
         drawContext.renderItem(held, -8, -8);
         drawContext.renderItemDecorations(this.font, held, -8, -8);
         posestack.popPose();
         RenderSystem.enableDepthTest();
      }
   }

   public void renderSlotBackGround(GuiGraphics drawContext, Slot slot, Style style, float depth, float scale) {
      int x = slot.x + 8;
      int y = slot.y + 8;
      drawContext.pose().pushPose();
      drawContext.pose().translate(x, y, 1.0F);
      drawContext.pose().scale(scale, scale, 1.0F);
      if (style != null && style.background != null) {
         RenderSystem.setShaderTexture(0, style.background);
         RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
         drawContext.blit(this.SLOT_BACKGROUND, -8, -6, 16, 16, 0.0F, 0.0F, 72, 72, 72, 72);
      }

      drawContext.pose().popPose();
   }

   public void renderSlot(GuiGraphics drawContext, Slot slot, int mx, int my, float depth, float scale) {
      this.renderSlot(drawContext, slot, null, mx, my, depth, scale);
   }

   public void renderSlot(GuiGraphics drawContext, Slot slot, Style style, int mx, int my, float depth, float scale) {
      int x = slot.x + 8;
      int y = slot.y + 8;
      int zlevel = depth == 1.0F ? 60 : 0;
      ItemStack itemstack = slot.getItem();
      PoseStack posestack = drawContext.pose();
      posestack.pushPose();
      RenderSystem.disableBlend();
      posestack.translate(x, y, 1.0F);
      posestack.scale(scale, scale, 1.0F);
      drawContext.renderItem(itemstack, -8, -8);
      drawContext.renderItemDecorations(this.font, itemstack, -8, -8, null);
      posestack.popPose();
      RenderSystem.enableDepthTest();
   }

   public static boolean isMouseOver(Slot slot, int mx, int my, int size, float scale) {
      float delta = size * scale;
      return mx >= slot.x - delta && mx <= slot.x + delta && my >= slot.y - delta && my <= slot.y + delta;
   }
}
