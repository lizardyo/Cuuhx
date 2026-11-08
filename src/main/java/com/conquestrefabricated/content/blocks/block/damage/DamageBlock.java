package com.conquestrefabricated.content.blocks.block.damage;

import com.conquestrefabricated.core.asset.annotation.Assets;
import com.conquestrefabricated.core.asset.annotation.Model;
import com.conquestrefabricated.core.asset.annotation.Render;
import com.conquestrefabricated.core.asset.annotation.State;
import com.conquestrefabricated.core.util.RenderLayer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

@Assets(
   state = @State(name = "%s", template = "parent_cube", plural = true),
   item = @Model(name = "item/%s", parent = "block/%s", template = "item/parent_cube", plural = true),
   render = @Render(RenderLayer.CUTOUT),
   block = @Model(name = "block/%s", template = "block/parent_cube", plural = true)
)
public class DamageBlock extends Block {
   public DamageBlock(Properties properties) {
      super(properties);
   }

   public void stepOn(Level world, BlockPos pos, BlockState state, Entity entity) {
      if (!entity.fireImmune() && entity instanceof LivingEntity && !EnchantmentHelper.hasFrostWalker((LivingEntity)entity)) {
         entity.hurt(world.damageSources().hotFloor(), 1.0F);
      }

      super.stepOn(world, pos, state, entity);
   }
}
