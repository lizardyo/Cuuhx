package com.conquestrefabricated.client.tutorial.toast;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.toasts.Toast;
import net.minecraft.client.gui.components.toasts.ToastComponent;
import net.minecraft.client.gui.components.toasts.Toast.Visibility;
import net.minecraft.resources.ResourceLocation;

public abstract class AbstractToast implements Toast {
   protected static final int TITLE = -11534256;
   protected static final int SUBTITLE = -16777216;
   private final int line1Color;
   private final int line2Color;

   public AbstractToast(int line1Color, int line2Color) {
      this.line1Color = line1Color;
      this.line2Color = line2Color;
   }

   public Visibility render(GuiGraphics drawContext, ToastComponent toastGui, long delta) {
      if (this.shouldRender(toastGui)) {
         RenderSystem.setShaderTexture(0, 0);
         RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
         drawContext.blit(ResourceLocation.fromNamespaceAndPath("conquest", "toast"), 0, 0, 0, 96, 160, 32);
         if (this.getLine2().isEmpty()) {
            drawContext.drawString(toastGui.getMinecraft().font, this.getLine1(), 5, 12, this.line1Color, true);
         } else {
            drawContext.drawString(toastGui.getMinecraft().font, this.getLine1(), 5, 7, this.line1Color, true);
            drawContext.drawString(toastGui.getMinecraft().font, this.getLine2(), 5, 18, this.line2Color, true);
         }
      }

      return this.getVisibility();
   }

   public abstract String getLine1();

   public abstract String getLine2();

   public abstract boolean shouldRender(ToastComponent var1);

   public abstract Visibility getVisibility();
}
