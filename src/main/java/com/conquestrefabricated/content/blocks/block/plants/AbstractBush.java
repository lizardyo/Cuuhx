package com.conquestrefabricated.content.blocks.block.plants;

import com.conquestrefabricated.content.blocks.BlockVoxelShapes;
import com.conquestrefabricated.content.blocks.util.PlacementHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public abstract class AbstractBush extends BushBlock {
   protected AbstractBush(Properties properties) {
      super(properties);
   }

   public boolean mayPlaceOn(BlockState state, BlockGetter reader, BlockPos pos) {
      return PlacementHelper.isDuringWorldGen(reader) ? super.mayPlaceOn(state, reader, pos) : true;
   }

   public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
      return BlockVoxelShapes.cubeMediumLargePartialShape.get(0);
   }

   public boolean canSurvive(BlockState state, LevelReader reader, BlockPos pos) {
      return PlacementHelper.isDuringWorldGen(reader) ? super.canSurvive(state, reader, pos) : true;
   }
}
