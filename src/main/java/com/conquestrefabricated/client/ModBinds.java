package com.conquestrefabricated.client;

import net.minecraft.client.KeyMapping;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ModBinds {
   public static KeyMapping palette;

   public ModBinds() {
   }

   public static KeyMapping getPaletteBind() {
      return palette;
   }
}
