package com.conquestrefabricated.client.gui.render;

public enum Curve {
   LINEAR {
      @Override
      public float apply(float input) {
         return input;
      }
   },
   SQUARE {
      @Override
      public float apply(float input) {
         return input * input;
      }
   },
   CUBIC {
      @Override
      public float apply(float input) {
         return input * input * input;
      }
   };

   Curve() {
   }

   public abstract float apply(float var1);
}
