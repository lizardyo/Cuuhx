package com.conquestrefabricated.client.gui.painting;

import com.conquestrefabricated.api.painting.Painting;
import com.conquestrefabricated.api.painting.art.Art;
import com.conquestrefabricated.client.gui.PickerScreen;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.item.ItemStack;

public class PaintingScreen<T> extends PickerScreen<Art<T>> {
   private final Painting type;

   public PaintingScreen(ItemStack stack, Painting type, Art<T> art) {
      super("Painting Selector", stack, art, art.getAll());
      this.type = type;
   }

   public int getWidth(Art<T> option) {
      return option.width();
   }

   public int getHeight(Art<T> option) {
      return option.height();
   }

   public String getDisplayName(Art<T> option) {
      return option.getName();
   }

   public void render(Art<T> option, GuiGraphics matrixStack, int x, int y, int width, int height, float scale) {
      option.getRenderer().render(this.type, option, matrixStack, x, y, width, height);
   }

   public ItemStack createItemStack(ItemStack original, Art<T> value) {
      ItemStack stack = this.type.createStack(value);
      stack.setCount(original.getCount());
      return stack;
   }
}
