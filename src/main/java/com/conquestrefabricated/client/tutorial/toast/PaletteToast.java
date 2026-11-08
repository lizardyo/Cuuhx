package com.conquestrefabricated.client.tutorial.toast;

import com.conquestrefabricated.client.ModBinds;
import com.conquestrefabricated.client.tutorial.Tutorials;
import net.minecraft.client.gui.components.toasts.ToastComponent;
import net.minecraft.client.gui.components.toasts.Toast.Visibility;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;

public class PaletteToast extends AbstractToast {
   private static final String line1 = "Press '%s' whilst hovering over";
   private static final String line2 = "a block to see its variants!";

   public PaletteToast() {
      super(-16777216, -16777216);
   }

   @Override
   public boolean shouldRender(ToastComponent gui) {
      return gui.getMinecraft().screen instanceof InventoryScreen && !Tutorials.openPalette;
   }

   @Override
   public Visibility getVisibility() {
      return Tutorials.openPalette ? Visibility.HIDE : Visibility.SHOW;
   }

   @Override
   public String getLine1() {
      return String.format("Press '%s' whilst hovering over", ModBinds.getPaletteBind().getTranslatedKeyMessage().getString());
   }

   @Override
   public String getLine2() {
      return "a block to see its variants!";
   }
}
