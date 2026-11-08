package com.conquestrefabricated.api.painting.art;

import com.conquestrefabricated.api.painting.Painting;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;

public enum ArtRenderer {
   MOD {
      @Override
      public void render(Painting painting, Art<?> art, GuiGraphics drawContext, int x, int y, int w, int h) {
         RenderSystem.setShaderTexture(0, painting.getRegistryName());
         drawContext.blit(painting.getRegistryName(), x, y, w, h, art.u(), art.v(), art.width(), art.height(), art.textureWidth(), art.textureHeight());
      }
   },
   VANILLA {
      @Override
      public void render(Painting painting, Art<?> art, GuiGraphics drawContext, int x, int y, int w, int h) {
         String artName = art.getName().replace("minecraft:", "");
         RenderSystem.setShaderTexture(0, ResourceLocation.fromNamespaceAndPath("minecraft:textures/painting/" + artName + ".png"));
         drawContext.blit(
            ResourceLocation.fromNamespaceAndPath("minecraft:textures/painting/" + artName + ".png"),
            x,
            y,
            w,
            h,
            art.u(),
            art.v(),
            art.width(),
            art.height(),
            art.textureWidth(),
            art.textureHeight()
         );
      }
   };

   ArtRenderer() {
   }

   public abstract void render(Painting var1, Art<?> var2, GuiGraphics var3, int var4, int var5, int var6, int var7);
}
