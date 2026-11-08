package com.conquestrefabricated.content.blocks.block.decor;

import com.conquestrefabricated.content.blocks.block.VerticalSlab;
import com.conquestrefabricated.content.blocks.block.VerticalSlabCorner;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.phys.shapes.VoxelShape;

public class DiagonalBeamUpDown extends VerticalSlabCorner {
   public DiagonalBeamUpDown(Properties properties) {
      super(properties);
   }

   @Override
   public VoxelShape getShape(BlockState state) {
      switch ((Direction)state.getValue(DIRECTION)) {
         case NORTH:
            return VerticalSlab.NORTH_SHAPE[1];
         case SOUTH:
            return VerticalSlab.SOUTH_SHAPE[1];
         case EAST:
            return VerticalSlab.EAST_SHAPE[1];
         default:
            return VerticalSlab.WEST_SHAPE[1];
      }
   }
}
