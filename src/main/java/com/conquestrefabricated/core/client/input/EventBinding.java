package com.conquestrefabricated.core.client.input;

import com.conquestrefabricated.core.client.input.forge.EventBindingImpl;
import com.mojang.blaze3d.platform.InputConstants.Key;
import com.mojang.blaze3d.platform.InputConstants.Type;
import java.util.LinkedList;
import java.util.List;
import net.minecraft.client.KeyMapping;

public class EventBinding extends KeyMapping {
   private final List<BindListener> listeners = new LinkedList<>();
   private boolean down = false;

   public EventBinding(String description, Key input, String category) {
      this(description, input, category, Type.KEYSYM);
   }

   public EventBinding(String description, Key input, String category, Type context) {
      super(description, context, input.getValue(), category);
      registerBinding(this);
   }

   public static void registerBinding(EventBinding binding) {
      EventBindingImpl.registerBinding(binding);
   }

   public EventBinding addListener(BindListener listener) {
      this.listeners.add(listener);
      return this;
   }

   public boolean checkPressed() {
      boolean pressed = super.consumeClick();
      if (pressed) {
         BindEvent event = new BindEvent(this);
         this.listeners.forEach(l -> l.onPress(event));
      }

      return pressed;
   }

   public boolean checkHeld() {
      boolean down = super.isDown();
      if (down) {
         BindEvent event = new BindEvent(this);
         this.listeners.forEach(l -> l.onHold(event));
      } else if (this.down) {
         BindEvent event = new BindEvent(this);
         this.listeners.forEach(l -> l.onRelease(event));
      }

      return this.down = down;
   }
}
