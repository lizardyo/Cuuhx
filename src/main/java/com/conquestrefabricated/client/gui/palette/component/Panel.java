package com.conquestrefabricated.client.gui.palette.component;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.screens.Screen;

public class Panel {
   private static final int LEFT = -1;
   private static final int RIGHT = 1;
   private int left = 0;
   private int top = 0;
   private int width = 0;
   private int height = 0;
   private int offsetX = 0;
   private final int side;
   private final boolean offScreen;
   private final List<AbstractWidget> children = new ArrayList<>();

   private Panel(int side, boolean offScreen) {
      this.side = side;
      this.offScreen = offScreen;
   }

   public void reset() {
      this.children.clear();
   }

   public void add(AbstractWidget widget) {
      this.children.add(widget);
   }

   public void tick() {
      if (this.offsetX != 0) {
         this.offsetX = this.offsetX - this.side;
      }

      int top = this.top;

      for (AbstractWidget widget : this.children) {
         widget.setWidth(this.width);
         widget.setX(this.left + this.offsetX);
         widget.setY(top);
         top += widget.getHeight() + 1;
      }
   }

   public void setSize(Screen parent, int width, int height) {
      this.width = width;
      this.height = height;
      this.left = this.side == -1 ? 0 : parent.width - width;
      this.offsetX = this.offScreen ? width * this.side : 0;
   }

   public static Panel left(boolean offScreen) {
      return new Panel(-1, offScreen);
   }

   public static Panel right(boolean offScreen) {
      return new Panel(1, offScreen);
   }
}
