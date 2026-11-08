package com.conquestrefabricated.client.gui.palette.component;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractOptionSliderButton;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.components.Button.OnPress;
import net.minecraft.client.gui.components.events.ContainerEventHandler;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.Nullable;

public class ColorPicker2 extends AbstractWidget implements ContainerEventHandler, Renderable {
   private static final OnPress NONE = btn -> {};
   private static final int VERT_SPACING = 2;
   private final List<AbstractOptionSliderButton> sliders;
   private final Consumer<Integer> consumer;
   public int x = 0;
   public int y = 0;
   private boolean dragging = false;
   private GuiEventListener focused;

   public ColorPicker2(Component name, int color, Consumer<Integer> consumer) {
      super(0, 0, 0, 0, name);
      int[] rgb = getComponents(color);
      this.consumer = consumer;
      this.sliders = Arrays.asList();
   }

   public void render(GuiGraphics matrixStack, int mx, int my, float ticks) {
      int top = this.y;

      for (AbstractOptionSliderButton slider : this.sliders) {
         slider.setX(this.x);
         slider.setY(top);
         slider.render(matrixStack, mx, my, ticks);
         top += slider.getHeight() + 2;
      }
   }

   protected void renderWidget(GuiGraphics drawContext, int i, int j, float f) {
   }

   public void setHeight(int value) {
      int totalSpacing = 2 * (this.sliders.size() - 1);
      int sliderHeight = (value - totalSpacing) / 3;

      for (AbstractOptionSliderButton var5 : this.sliders) {
         ;
      }
   }

   public void setWidth(int width) {
      for (AbstractOptionSliderButton slider : this.sliders) {
         slider.setWidth(width);
      }
   }

   protected void updateWidgetNarration(NarrationElementOutput narrationMessageBuilder) {
   }

   public List<? extends GuiEventListener> children() {
      return this.sliders;
   }

   public boolean isDragging() {
      return this.dragging;
   }

   public void setDragging(boolean dragging) {
      this.dragging = dragging;
   }

   @Nullable
   public GuiEventListener getFocused() {
      return this.focused;
   }

   public void setFocused(@Nullable GuiEventListener focused) {
      this.focused = focused;
   }

   private static int[] getComponents(int i) {
      Color color = new Color(i);
      return new int[]{color.getRed(), color.getGreen(), color.getBlue()};
   }

   private static int combine(int r, int g, int b) {
      return new Color(r, g, b).getRGB();
   }
}
