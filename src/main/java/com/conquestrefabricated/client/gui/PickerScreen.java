package com.conquestrefabricated.client.gui;

import com.conquestrefabricated.client.gui.render.Render;
import com.conquestrefabricated.client.utils.CreativeUtils;
import com.mojang.blaze3d.systems.RenderSystem;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

public abstract class PickerScreen<T> extends Screen {
   private final ItemStack stack;
   private final T selected;
   private final List<T> options;
   private int index = -1;

   public PickerScreen(String title, ItemStack stack, T selected, List<T> options) {
      super(Component.literal(title));
      this.stack = stack;
      this.options = options;
      this.selected = selected;
   }

   public boolean match(T a, T b) {
      return a.equals(b);
   }

   public void init() {
      super.init();
      this.index = this.indexOf(this.selected, this.options);
      Render.hideMouse();
   }

   public boolean isPauseScreen() {
      return false;
   }

   public boolean mouseClicked(double mouseX, double mouseY, int mouseButton) {
      Minecraft.getInstance().setScreen(null);
      return super.mouseClicked(mouseX, mouseY, mouseButton);
   }

   public boolean keyPressed(int typedChar, int keyCode, int what) {
      if (typedChar == 263 && --this.index < 0) {
         this.index = this.options.size() - 1;
      }

      if (typedChar == 262 && ++this.index >= this.options.size()) {
         this.index = 0;
      }

      return super.keyPressed(typedChar, keyCode, what);
   }

   public void render(GuiGraphics matrixStack, int mouseX, int mouseY, float partialTicks) {
      this.renderDirtBackground(matrixStack);
      int centerX = this.width / 2;
      int centerY = this.height / 2;
      int maxWidth = this.options.size() / 2;
      int size = Math.min(maxWidth, this.getSize());
      int i = size;
      int visited = 0;

      while (i >= 0) {
         if (i == 0) {
            this.renderOption(matrixStack, centerX, centerY, i);
         } else {
            this.renderOption(matrixStack, centerX, centerY, i);
            if (++visited < this.options.size() - 1) {
               this.renderOption(matrixStack, centerX, centerY, -i);
            }
         }

         i--;
      }

      this.drawLabel(matrixStack, centerX, centerY);
   }

   public boolean mouseScrolled(double mx, double my, double scroll) {
      if (scroll > 0.0 && --this.index < 0) {
         this.index = this.options.size() - 1;
      }

      if (scroll < 0.0 && ++this.index >= this.options.size()) {
         this.index = 0;
      }

      return false;
   }

   public void removed() {
      Render.showMouse();
      if (this.minecraft != null && this.minecraft.player != null && this.minecraft.player.isCreative()) {
         T option = this.options.get(this.index);
         CreativeUtils.replaceItemStack(this.stack, this.createItemStack(this.stack, option));
      }
   }

   private void renderOption(GuiGraphics matrixStack, int cx, int cy, int di) {
      int index = this.index + di;
      if (index < 0) {
         index += this.options.size();
      }

      if (index >= this.options.size()) {
         index -= this.options.size();
      }

      if (index >= 0 && index < this.options.size()) {
         float scale = 2.0F - Math.abs(di) / 4.0F;
         float count = this.getSize() * 2 + 1;
         int size = Math.round(this.width / count * scale);
         int left = cx + 1 + di * (size + 1) - size / 2;
         int top = cy - size / 2;
         T option = this.options.get(index);
         float w = 1.0F;
         float h = 1.0F;
         int width = this.getWidth(option);
         int height = this.getHeight(option);
         if (width != height) {
            float scale1 = 1.0F / Math.max(width, height);
            w = width * scale1;
            h = height * scale1;
         }

         int tw = Math.round(size * w);
         int th = Math.round(size * h);
         int tl = left + (size - tw) / 2;
         int tt = top + (size - th) / 2;
         float alpha = Math.min(1.0F, 0.4F + Math.max(0.0F, 1.0F - Math.abs(di) / 2.0F));
         RenderSystem.setShaderColor(alpha, alpha, alpha, 1.0F);
         matrixStack.pose().pushPose();
         matrixStack.pose().translate(0.0F, 0.0F, scale * 50.0F);
         this.render(option, matrixStack, tl, tt, tw, th, scale);
         matrixStack.pose().popPose();
      }
   }

   private void drawLabel(GuiGraphics matrixStack, int centerX, int centerY) {
      if (this.index >= 0 && this.index < this.options.size()) {
         int height = this.width / (this.getSize() * 2 + 1) + 10;
         int barWidth = 150;
         int barLeft = this.width / 2 - barWidth / 2;
         int barTop = centerY + height + this.getYOffset();
         int barRight = barLeft + barWidth;
         int barBottom = barTop + 3;
         float position = (float)this.index / (this.options.size() - 1);
         int posLeft = barLeft + Math.round(position * barWidth) - 1;
         int posRight = posLeft + 2;
         matrixStack.fillGradient(barLeft, barTop, barRight, barBottom, 1140850688, 1140850688);
         matrixStack.fillGradient(posLeft, barTop, posRight, barBottom, 1728053247, 1728053247);
         T option = this.options.get(this.index);
         String text = this.getDisplayName(option);
         int width = this.font.width(text);
         float top = barTop + 15;
         float left = centerX - width / 2.0F;
         matrixStack.drawString(this.font, text, (int)left, (int)top, 16777215);
      }
   }

   private int indexOf(T value, List<T> options) {
      for (int i = 0; i < options.size(); i++) {
         if (this.match(value, options.get(i))) {
            return i;
         }
      }

      return 0;
   }

   public int getSize() {
      return 5;
   }

   public int getYOffset() {
      return 0;
   }

   public abstract int getWidth(T var1);

   public abstract int getHeight(T var1);

   public abstract String getDisplayName(T var1);

   public abstract void render(T var1, GuiGraphics var2, int var3, int var4, int var5, int var6, float var7);

   public abstract ItemStack createItemStack(ItemStack var1, T var2);
}
