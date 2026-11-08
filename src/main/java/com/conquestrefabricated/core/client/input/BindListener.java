package com.conquestrefabricated.core.client.input;

public interface BindListener {
   void onPress(BindEvent var1);

   default void onHold(BindEvent event) {
   }

   default void onRelease(BindEvent event) {
   }
}
