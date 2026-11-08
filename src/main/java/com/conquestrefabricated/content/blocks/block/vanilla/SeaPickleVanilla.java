package com.conquestrefabricated.content.blocks.block.vanilla;

import com.conquestrefabricated.content.blocks.block.Layer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.SeaPickleBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class SeaPickleVanilla extends SeaPickleBlock {
   public SeaPickleVanilla(Properties properties) {
      super(properties);
      this.registerDefaultState((BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue(PICKLES, 1)).setValue(WATERLOGGED, true));
   }

   public boolean mayPlaceOn(BlockState state, BlockGetter worldIn, BlockPos pos) {
      return !state.getCollisionShape(worldIn, pos).getFaceShape(Direction.UP).isEmpty() || state.getBlock() instanceof Layer;
   }
}
