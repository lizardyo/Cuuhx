package com.conquestrefabricated.content.blocks.block.vanilla;

import com.conquestrefabricated.content.blocks.util.PlacementHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.TallSeagrassBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;

public class TallSeaGrassVanilla extends TallSeagrassBlock {
   public TallSeaGrassVanilla(Properties properties) {
      super(properties);
      this.registerDefaultState((BlockState)((BlockState)this.stateDefinition.any()).setValue(HALF, DoubleBlockHalf.LOWER));
   }

   public boolean mayPlaceOn(BlockState state, BlockGetter world, BlockPos pos) {
      return PlacementHelper.isDuringWorldGen(world) ? super.mayPlaceOn(state, world, pos) : true;
   }

   public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos) {
      if (state.getValue(HALF) != DoubleBlockHalf.UPPER) {
         return super.canSurvive(state, worldIn, pos);
      } else {
         BlockState blockstate = worldIn.getBlockState(pos.below());
         if (state.getBlock() != this) {
            return super.canSurvive(state, worldIn, pos);
         } else {
            return blockstate.getBlock() == this && blockstate.getValue(HALF) == DoubleBlockHalf.LOWER ? true : true;
         }
      }
   }
}
