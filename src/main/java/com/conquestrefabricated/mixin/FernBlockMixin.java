package com.conquestrefabricated.mixin;

import com.conquestrefabricated.client.gui.config.ConquestConfig;
import com.conquestrefabricated.content.effects.Effects;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.TallGrassBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(TallGrassBlock.class)
public abstract class FernBlockMixin extends Block {
   public FernBlockMixin(Properties settings) {
      super(settings);
   }

   public void entityInside(BlockState state, Level world, BlockPos pos, Entity entity) {
      super.entityInside(state, world, pos, entity);
      if (entity instanceof LivingEntity livingEntity && (Boolean)ConquestConfig.INSTANCE.plantSlowness.get() && livingEntity instanceof Player) {
         livingEntity.addEffect(new MobEffectInstance(Effects.CUSTOM_SLOWNESS, 15, 1, false, false));
      }
   }
}
