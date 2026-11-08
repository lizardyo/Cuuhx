package com.conquestrefabricated.mixin;

import net.minecraft.world.level.block.state.BlockBehaviour;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(BlockBehaviour.class)
public interface BlockOffsetInvoker {
   @Invoker("getMaxHorizontalOffset")
   float invokeGetMaxHorizontalOffset();

   @Invoker("getMaxVerticalOffset")
   float invokeGetMaxVerticalOffset();
}
