package com.conquestrefabricated.client.gui.palette.component;

import net.minecraft.resources.ResourceLocation;

public class Style {
   private static final float CENTER_SCALE = 1.75F;
   private static final float RADIAL_SCALE = 1.15F;
   private static final int SCALE_THRESHOLD = 18;
   public final float scale;
   public final boolean fixedScale;
   public int highlightColor;
   public int hoveredColor;
   public float highlightScale;
   public final ResourceLocation background;

   private Style(float scale, boolean fixedScale, ResourceLocation background) {
      this.scale = scale;
      this.background = background;
      this.hoveredColor = 16777215;
      this.highlightColor = 16777215;
      this.fixedScale = fixedScale;
      this.highlightScale = 1.075F;
   }

   public static Style center(ResourceLocation background) {
      return new Style(1.75F, true, background);
   }

   public static Style radial(int count, ResourceLocation background) {
      if (count > 18) {
         float dif = (count - 18) / 18.0F;
         float scale = 1.15F - Math.min(dif, 0.45F);
         return new Style(scale, false, background);
      } else {
         return new Style(1.15F, false, background);
      }
   }
}
