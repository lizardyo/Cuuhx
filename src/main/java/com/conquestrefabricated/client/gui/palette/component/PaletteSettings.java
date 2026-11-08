package com.conquestrefabricated.client.gui.palette.component;

import com.conquestrefabricated.client.gui.render.Curve;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PaletteSettings extends Screen {
   private static final boolean test = false;
   public transient Curve zoomCurve = Curve.SQUARE;
   public float zoomScale = 1.1F;
   public float zoomSpread = 1.0F;
   public float highlightScale = 1.1F;
   private final Panel left = Panel.left(true);
   private final Panel right = Panel.right(true);

   public PaletteSettings() {
      super(Component.literal("Settings"));
   }

   protected void init() {
      super.init();
   }

   public void render(GuiGraphics matrixStack, int mx, int my, float ticks) {
      this.left.tick();
      this.right.tick();
      super.render(matrixStack, mx, my, ticks);
   }

   private void add(Panel panel, AbstractWidget widget) {
      super.addRenderableWidget(widget);
      panel.add(widget);
   }
}
