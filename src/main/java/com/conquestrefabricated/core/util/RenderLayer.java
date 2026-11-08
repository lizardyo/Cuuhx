package com.conquestrefabricated.core.util;

public enum RenderLayer {
   UNDEFINED,
   SOLID,
   CUTOUT,
   CUTOUT_MIPPED,
   CUTOUT_MIPPED_SOLID,
   TRANSLUCENT;

   RenderLayer() {
   }

   public boolean isDefault() {
      return this == UNDEFINED || this == SOLID;
   }

   public boolean isCutout() {
      return this == CUTOUT || this == CUTOUT_MIPPED;
   }

   public boolean isCutoutSolid() {
      return this == CUTOUT_MIPPED_SOLID;
   }
}
