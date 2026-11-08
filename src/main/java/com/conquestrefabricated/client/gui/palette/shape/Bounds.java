package com.conquestrefabricated.client.gui.palette.shape;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat.Mode;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Bounds {
   public static final Bounds NONE = new Bounds();
   private final List<List<Point>> bounds = new LinkedList<>();
   private List<Point> points = Collections.emptyList();

   public Bounds() {
   }

   public Bounds startNew() {
      this.bounds.add(this.points = new LinkedList<>());
      return this;
   }

   public Bounds add(Point point) {
      this.points.add(point);
      return this;
   }

   public void draw(float red, float green, float blue, float opacity, float ticks) {
      RenderSystem.setShaderColor(red, green, blue, opacity);
      Tesselator tessellator = Tesselator.getInstance();
      BufferBuilder buffer = tessellator.getBuilder();

      for (List<Point> points : this.bounds) {
         buffer.begin(Mode.QUADS, DefaultVertexFormat.POSITION);

         for (Point point : points) {
            buffer.vertex(point.x, point.y, 0.0).endVertex();
         }

         tessellator.end();
      }
   }
}
