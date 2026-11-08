package com.conquestrefabricated.content.blocks.block.plants;

import com.conquestrefabricated.core.asset.annotation.Render;
import com.conquestrefabricated.core.util.RenderLayer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.WaterlilyBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

@Render(RenderLayer.CUTOUT)
public class LilyPad extends WaterlilyBlock {
   public LilyPad(Properties properties) {
      super(properties);
   }

   public void entityInside(BlockState state, Level world, BlockPos pos, Entity entity) {
   }

   public VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
      return Shapes.empty();
   }

   protected boolean mayPlaceOn(BlockState blockState, BlockGetter blockView, BlockPos blockPos) {
      return true;
   }
}
