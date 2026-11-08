package com.conquestrefabricated.mixin;

import net.minecraft.world.level.pathfinder.PathType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PathType.class)
public class PathNodeTypeMixin {
   @Shadow
   @Final
   @Mutable
   private float malus;

   public PathNodeTypeMixin() {
   }

   @Inject(method = "<init>", at = @At("RETURN"))
   private void modifyLeavesPenalty(String enumName, int ordinal, float defaultPenalty, CallbackInfo ci) {
      if (enumName.equals("LEAVES")) {
         this.malus = 0.0F;
      }
   }
}
