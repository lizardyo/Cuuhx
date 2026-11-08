package com.conquestrefabricated.core.client.render.type;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;

public class ReplaceFirstInjector extends RenderTypeInjector {
   private final RenderType type;
   private volatile boolean first = true;

   public ReplaceFirstInjector(MultiBufferSource delegate, RenderType type) {
      super(delegate);
      this.type = type;
   }

   @Override
   protected RenderType getRenderType(RenderType type) {
      if (this.first) {
         this.first = false;
         return this.type;
      } else {
         return type;
      }
   }
}
