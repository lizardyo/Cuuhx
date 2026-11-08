package com.conquestrefabricated.core.client.render.type;

import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;

public class RenderTypeInjector implements MultiBufferSource {
   protected final MultiBufferSource delegate;

   public RenderTypeInjector(MultiBufferSource delegate) {
      this.delegate = delegate;
   }

   public VertexConsumer getBuffer(RenderType type) {
      return this.delegate.getBuffer(this.getRenderType(type));
   }

   protected RenderType getRenderType(RenderType type) {
      return type;
   }
}
