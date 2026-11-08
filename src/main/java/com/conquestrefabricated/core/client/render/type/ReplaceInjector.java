package com.conquestrefabricated.core.client.render.type;

import java.util.function.UnaryOperator;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;

public class ReplaceInjector extends RenderTypeInjector {
   private final UnaryOperator<RenderType> operator;

   public ReplaceInjector(MultiBufferSource delegate, UnaryOperator<RenderType> operator) {
      super(delegate);
      this.operator = operator;
   }

   @Override
   protected RenderType getRenderType(RenderType type) {
      return this.operator.apply(type);
   }
}
