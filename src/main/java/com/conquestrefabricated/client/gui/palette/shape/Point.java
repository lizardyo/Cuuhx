package com.conquestrefabricated.client.gui.palette.shape;

public class Point {
   public static final Point ZERO = new Point(0, 0);
   public final int x;
   public final int y;

   public Point(int x, int y) {
      this.x = x;
      this.y = y;
   }

   public float distance(Point other) {
      return this.distance(other.x, other.y);
   }

   public float distance(int x, int y) {
      return FloatMath.sqrt(this.distance2(x, y));
   }

   public int distance2(Point other) {
      return this.distance2(other.x, other.y);
   }

   public int distance2(int ox, int oy) {
      return distance2(this.x, this.y, ox, oy);
   }

   public static int distance2(int x0, int y0, int x1, int y1) {
      int dx = x0 - x1;
      int dy = y0 - y1;
      return dx * dx + dy * dy;
   }
}
