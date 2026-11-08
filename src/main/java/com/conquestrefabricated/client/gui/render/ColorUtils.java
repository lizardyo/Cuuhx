package com.conquestrefabricated.client.gui.render;

import java.awt.Color;

public class ColorUtils {
   public ColorUtils() {
   }

   public static String toHex(Color color) {
      return toHex(color.getRGB());
   }

   public static String toHex(int color) {
      return String.format("#%06X", 16777215 & color);
   }

   public static int fromHex(String color) {
      return Color.decode(color).getRGB();
   }
}
