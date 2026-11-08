package com.conquestrefabricated.content.blocks.block.vanilla;

import com.conquestrefabricated.content.blocks.util.PlacementHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.SeagrassBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class SeaGrassVanilla extends SeagrassBlock {
   public SeaGrassVanilla(Properties properties) {
      super(properties);
      this.registerDefaultState((BlockState)this.stateDefinition.any());
   }

   public boolean mayPlaceOn(BlockState state, BlockGetter world, BlockPos pos) {
      return PlacementHelper.isDuringWorldGen(world) ? super.mayPlaceOn(state, world, pos) : true;
   }
}
