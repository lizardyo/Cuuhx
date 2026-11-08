package com.conquestrefabricated.client.gui.render;

import com.conquestrefabricated.client.gui.palette.component.Style;
import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

public class Render {
   private static final int HIDE_MOUSE_MODE = 212995;
   private static final int SHOW_MOUSE_MODE = 212993;

   public Render() {
   }

   public static void hideMouse() {
      double mx = Minecraft.getInstance().mouseHandler.xpos();
      double my = Minecraft.getInstance().mouseHandler.ypos();
      InputConstants.grabOrReleaseMouse(Minecraft.getInstance().getWindow().getWindow(), 212995, mx, my);
   }

   public static void showMouse() {
      double mx = Minecraft.getInstance().getWindow().getScreenWidth() / 2.0;
      double my = Minecraft.getInstance().getWindow().getScreenHeight() / 2.0;
      InputConstants.grabOrReleaseMouse(Minecraft.getInstance().getWindow().getWindow(), 212993, mx, my);
   }

   public static void drawTexture(ResourceLocation texture, GuiGraphics drawContext, int left, int top, int width, int height, float u, float v) {
      drawTexture(texture, drawContext, left, top, width, height, u, v, width, height);
   }

   public static void drawTexture(
      ResourceLocation texture, GuiGraphics drawContext, int left, int top, int width, int height, float u, float v, int umax, int vmax
   ) {
      RenderSystem.setShaderTexture(0, texture);
      RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
      drawContext.blit(texture, left, top, u, v, width, height, umax, vmax);
   }

   public static void drawTexture(ResourceLocation texture, GuiGraphics drawContext, int left, int top, int blitOffset, float u, float v, int umax, int vmax) {
      RenderSystem.setShaderTexture(0, texture);
      RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
      drawContext.blit(texture, left, top, blitOffset, u, v, umax, vmax, 256, 256);
   }

   public static void drawItemStackHighlight(PoseStack poseStack, ItemStack stack, int x, int y, Style style) {
      drawItemStackHighlight(poseStack, stack, x, y, style.highlightScale, style.highlightColor);
   }

   public static void drawItemStackHighlight(PoseStack poseStack, ItemStack stack, int x, int y, float scale, int color) {
      poseStack.pushPose();
      poseStack.scale(scale, scale, 1.0F);
      BakedModel model = Minecraft.getInstance().getItemRenderer().getItemModelShaper().getItemModel(stack);
      ModelRender.renderModel(poseStack, model, x, y, color);
      poseStack.popPose();
   }

   public static void drawBlockModel(PoseStack poseStack, BlockState state, int x, int y, float scale) {
      poseStack.pushPose();
      poseStack.translate(x, y, 0.0F);
      poseStack.scale(scale, scale, 1.0F);
      BakedModel model = Minecraft.getInstance().getBlockRenderer().getBlockModel(state);
      ModelRender.renderModel(poseStack, state, model, 0, 0, 16777215);
      poseStack.popPose();
   }
}
