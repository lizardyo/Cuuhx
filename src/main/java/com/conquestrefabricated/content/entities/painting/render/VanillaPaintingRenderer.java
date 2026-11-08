package com.conquestrefabricated.content.entities.painting.render;

import com.conquestrefabricated.core.client.render.type.RenderTypeInjector;
import com.conquestrefabricated.core.client.render.type.ReplaceFirstInjector;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.world.entity.decoration.Painting;

public class VanillaPaintingRenderer extends net.minecraft.client.renderer.entity.PaintingRenderer {
   public VanillaPaintingRenderer(Context manager) {
      super(manager);
   }

   public void render(Painting entity, float yaw, float ticks, PoseStack stack, MultiBufferSource buffer, int light) {
      RenderTypeInjector injector = new ReplaceFirstInjector(buffer, RenderType.entityCutout(this.getTextureLocation(entity)));
      super.render(entity, yaw, ticks, stack, injector, light);
   }
}
