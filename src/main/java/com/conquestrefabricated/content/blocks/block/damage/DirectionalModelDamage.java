package com.conquestrefabricated.content.blocks.block.damage;

import com.conquestrefabricated.content.blocks.BlockVoxelShapes;
import com.conquestrefabricated.content.blocks.block.trees.Log;
import com.conquestrefabricated.core.asset.annotation.Render;
import com.conquestrefabricated.core.util.RenderLayer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

@Render(RenderLayer.CUTOUT)
public class DirectionalModelDamage extends Log {
   public DirectionalModelDamage(Properties properties) {
      super(properties);
   }

   public void entityInside(BlockState state, Level worldIn, BlockPos pos, Entity entityIn) {
      if (entityIn instanceof LivingEntity && entityIn.getType() != EntityType.FOX && entityIn.getType() != EntityType.BEE) {
         entityIn.makeStuckInBlock(state, new Vec3(0.8F, 0.75, 0.8F));
         if (!worldIn.isClientSide && (entityIn.xOld != entityIn.getX() || entityIn.zOld != entityIn.getZ())) {
            double d0 = Math.abs(entityIn.getX() - entityIn.xOld);
            double d1 = Math.abs(entityIn.getZ() - entityIn.zOld);
            if (d0 >= 0.003F || d1 >= 0.003F) {
               entityIn.hurt(worldIn.damageSources().sweetBerryBush(), 1.0F);
            }
         }
      }
   }

   public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
      return BlockVoxelShapes.cubePartialShape.get(0);
   }
}
