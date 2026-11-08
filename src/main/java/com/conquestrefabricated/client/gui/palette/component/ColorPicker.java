package com.conquestrefabricated.client.gui.palette.component;

import com.mojang.blaze3d.platform.NativeImage;
import java.awt.Color;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class ColorPicker extends Button {
   private static final int pickerWidth = 128;
   private static final int pickerHeight = 32;
   private static final int sliderWidth = 128;
   private static final int sliderHeight = 10;
   private static final int padding = 1;
   private static final int titleHeight = 10;
   private static final int lineColor = -1711276033;
   private static final float hueOffset = 0.68F;
   private float hue = 0.0F;
   private float saturation = 0.5F;
   private float brightness = 1.0F;
   private boolean dirty = true;
   private final String title;
   private final Consumer<Integer> consumer;
   private final DynamicTexture picker = new DynamicTexture(new NativeImage(128, 32, false));
   private final DynamicTexture slider = new DynamicTexture(new NativeImage(128, 10, false));
   private final ResourceLocation PICKER_ID;
   private final ResourceLocation SLIDER_ID;

   public ColorPicker(String title, int initial, Consumer<Integer> consumer) {
      super(0, 0, 128, 53, Component.translatable(""), button -> {}, supplier -> null);
      this.title = title;
      this.consumer = consumer;
      float[] hsb = new Color(initial).getColorComponents(new float[3]);
      this.hue = hsb[0];
      this.saturation = hsb[1];
      this.brightness = hsb[2];
      this.PICKER_ID = Minecraft.getInstance().getTextureManager().register("picker", this.picker);
      this.SLIDER_ID = Minecraft.getInstance().getTextureManager().register("slider", this.slider);
   }

   public void render(GuiGraphics drawContext, int mx, int my, float ticks) {
      if (this.dirty) {
         this.renderImage();
      }

      int top = this.getY();
      int left = this.getX();
      drawContext.drawString(Minecraft.getInstance().font, "TEST", top, left, 16777215);
      top += 10;
      this.picker.bind();
      drawContext.blit(this.PICKER_ID, left, top, 128, 32, 0.0F, 0.0F, 128, 32, 128, 32);
      top += 33;
      this.slider.bind();
      drawContext.blit(this.SLIDER_ID, left, top, 128, 10, 0.0F, 0.0F, 128, 10, 128, 10);
   }

   public boolean mouseDragged(double x1, double y1, int button, double x2, double y2) {
      return this.interact(x2, y2) ? true : super.mouseDragged(x1, y1, button, x2, y2);
   }

   public boolean mouseClicked(double mx, double my, int button) {
      return this.interact(mx, my - 10.0) ? super.mouseClicked(mx, my, button) : false;
   }

   public void dispose() {
      this.picker.close();
      this.slider.close();
   }

   private boolean interact(double mx, double my) {
      boolean color = this.click(mx, my, this.getX(), this.getY(), this.picker, (x, y) -> {
         this.hue = x;
         this.saturation = y;
         this.consumer.accept(getRGB(this.hue, this.saturation, this.brightness));
         this.dirty = true;
      });
      return color ? true : this.click(mx, my, this.getX(), this.getY() + 32 + 1, this.slider, (x1, y1) -> {
         this.brightness = x1;
         this.consumer.accept(getRGB(this.hue, this.saturation, this.brightness));
         this.dirty = true;
      });
   }

   private boolean click(double mx, double my, int left, int top, DynamicTexture texture, BiConsumer<Float, Float> consumer) {
      NativeImage image = texture.getPixels();
      if (image == null) {
         return false;
      } else if (mx >= left && mx < left + image.getWidth() && my >= top && my < top + image.getHeight()) {
         float dx = (int)mx - left;
         float dy = (int)my - top;
         float x = dx / image.getWidth();
         float y = dy / image.getHeight();
         consumer.accept(x, y);
         return true;
      } else {
         return false;
      }
   }

   private void renderImage() {
      this.renderColor();
      this.renderBrightness();
      this.picker.upload();
      this.slider.upload();
   }

   private void renderColor() {
      NativeImage picker = this.picker.getPixels();
      if (picker != null) {
         int crosshairX = (int)(picker.getWidth() * this.hue);
         int crosshairY = (int)(picker.getHeight() * this.saturation);
         float displayBrightness = 0.5F + 0.5F * this.brightness;

         for (int y = 0; y < picker.getHeight(); y++) {
            float sat = y / 32.0F;

            for (int x = 0; x < picker.getWidth(); x++) {
               float hue = x / 128.0F;
               int rgb = Color.HSBtoRGB(hue, sat, displayBrightness);
               picker.setPixelRGBA(x, y, rgb);
               if (x != crosshairX && y == crosshairY) {
               }
            }
         }
      }
   }

   private void renderBrightness() {
      NativeImage slider = this.slider.getPixels();
      if (slider != null) {
         int slidebarX = (int)(slider.getWidth() * this.brightness);

         for (int x = 0; x < slider.getWidth(); x++) {
            float brightness = (float)x / slider.getWidth();

            for (int y = 0; y < slider.getHeight(); y++) {
               int rgb = Color.HSBtoRGB(this.hue, this.saturation, brightness);
               slider.setPixelRGBA(x, y, rgb);
               if (x == slidebarX) {
               }
            }
         }
      }
   }

   private static int getRGB(float hue, float saturation, float brightness) {
      Color rgb = Color.getHSBColor(hue, saturation, brightness);
      return rgb.getRGB();
   }
}
