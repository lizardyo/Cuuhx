package com.conquestrefabricated.client.gui.palette.shape;

public class Edge {
   private final float angle;
   private final float partialAngle;
   private final float length;
   private final float outerLength;
   private final float innerLength;
   final Point start;
   final Point end;
   final Point outerStart;
   final Point outerEnd;
   final Point innerStart;
   final Point innerEnd;

   public Edge(
      Point cStart, Point oStart, Point iStart, int centerX, int centerY, int radius, int outerPadding, int innerPadding, float angle, float angleIncrement
   ) {
      float rads = (float)Math.toRadians(angle);
      this.start = cStart;
      this.end = toPoint(centerX, centerY, radius, rads);
      this.outerStart = oStart;
      this.outerEnd = toPoint(centerX, centerY, radius + outerPadding, rads);
      this.innerStart = iStart;
      this.innerEnd = toPoint(centerX, centerY, radius - innerPadding, rads);
      this.angle = angle;
      this.partialAngle = angleIncrement;
      this.length = this.start.distance(this.end);
      this.outerLength = this.outerStart.distance(this.outerEnd);
      this.innerLength = this.innerStart.distance(this.innerEnd);
   }

   public double getAngle() {
      return this.angle;
   }

   public Point getPosition(float angle) {
      return getPosition(this.start, this.end, this.length, angle, this.partialAngle);
   }

   public Point getOuterPosition(float angle) {
      return getPosition(this.outerStart, this.outerEnd, this.outerLength, angle, this.partialAngle);
   }

   public Point getInnerPosition(float angle) {
      return getPosition(this.innerStart, this.innerEnd, this.innerLength, angle, this.partialAngle);
   }

   private static Point getPosition(Point start, Point end, float length, float angle, float partialAngle) {
      float progress = angle % partialAngle / partialAngle;
      float dl = progress * length;
      float t = dl / length;
      int x = FloatMath.round((1.0F - t) * start.x + t * end.x);
      int y = FloatMath.round((1.0F - t) * start.y + t * end.y);
      return new Point(x, y);
   }

   static Point toPoint(int cX, int cY, int radius, float rads) {
      int x = (int)Math.round(cX + Math.cos(rads) * radius);
      int y = (int)Math.round(cY + Math.sin(rads) * radius);
      return new Point(x, y);
   }
}
