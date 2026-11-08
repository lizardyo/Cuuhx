package com.conquestrefabricated.core.block.properties;

import net.minecraft.util.StringRepresentable;

public enum CapitalDirection implements StringRepresentable {
   FLAT("flat"),
   NORTH("north"),
   SOUTH("south"),
   EAST("east"),
   WEST("west");

   private final String name;

   CapitalDirection(String name) {
      this.name = name;
   }

   @Override
   public String toString() {
      return this.name;
   }

   public String getSerializedName() {
      return this.name;
   }
}
