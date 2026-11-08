package com.conquestrefabricated.content.entities.seat;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;

public class SeatRenderer extends EntityRenderer<SeatEntity> {
   public SeatRenderer(Context renderManager) {
      super(renderManager);
   }

   public void render(SeatEntity seat, float yaw, float ticks, PoseStack matrix, MultiBufferSource buffer, int light) {
   }

   public ResourceLocation getTexture(SeatEntity p_110775_1_) {
      return null;
   }
}
