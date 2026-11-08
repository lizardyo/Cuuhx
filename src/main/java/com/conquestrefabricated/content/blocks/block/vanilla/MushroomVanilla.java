package com.conquestrefabricated.content.blocks.block.vanilla;

import com.conquestrefabricated.content.blocks.block.Layer;
import com.conquestrefabricated.content.blocks.block.Slab;
import com.conquestrefabricated.content.blocks.block.plants.Bush;
import com.conquestrefabricated.content.blocks.util.PlacementHelper;
import com.conquestrefabricated.core.block.builder.Props;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;

public class MushroomVanilla extends Bush {
   public MushroomVanilla(Props properties) {
      super(properties);
   }

   @Override
   public boolean mayPlaceOn(BlockState state, BlockGetter world, BlockPos pos) {
      return PlacementHelper.isDuringWorldGen(world) ? super.mayPlaceOn(state, world, pos) : true;
   }

   @Override
   public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
      return PlacementHelper.isDuringWorldGen(world) ? super.canSurvive(state, world, pos) : true;
   }

   @Override
   public BlockState getStateForPlacement(BlockPlaceContext context) {
      BlockGetter iblockreader = context.getLevel();
      BlockPos blockpos = context.getClickedPos();
      BlockPos down = blockpos.below();
      BlockState blockStateDown = iblockreader.getBlockState(down);
      return !blockStateDown.hasProperty(Layer.LAYERS) && !blockStateDown.hasProperty(Slab.LAYERS)
         ? (BlockState)super.getStateForPlacement(context).setValue(LAYERS, 8)
         : (BlockState)super.getStateForPlacement(context).setValue(LAYERS, (Integer)blockStateDown.getValue(LAYERS));
   }
}
