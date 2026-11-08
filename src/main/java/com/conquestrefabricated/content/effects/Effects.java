package com.conquestrefabricated.content.effects;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class Effects {
   public static final DeferredRegister<MobEffect> REGISTRY = DeferredRegister.create(Registries.MOB_EFFECT, "conquest");
   public static final DeferredHolder<MobEffect, CustomSlownessEffect> CUSTOM_SLOWNESS =
      REGISTRY.register("custom_slowness", CustomSlownessEffect::new);

   public Effects() {
   }
}
