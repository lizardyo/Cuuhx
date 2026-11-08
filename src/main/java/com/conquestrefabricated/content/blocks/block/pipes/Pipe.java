package com.conquestrefabricated.content.blocks.block.pipes;

import com.conquestrefabricated.content.blocks.BlockVoxelShapes;
import com.conquestrefabricated.content.blocks.block.directional.HorizontalDirectional;
import com.conquestrefabricated.core.block.builder.Props;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;

public class Pipe extends HorizontalDirectional.Waterlogged.Toggle2 {
   public Pipe(Props props) {
      super(props);
   }

   @Override
   public VoxelShape getShape(BlockState state) {
      if ((Integer)state.getValue(TOGGLE) == 1) {
         return BlockVoxelShapes.pipeShape;
      }

      switch ((Direction)state.getValue(DIRECTION)) {
         case NORTH:
         case SOUTH:
         default:
            return BlockVoxelShapes.pipeShapeHorizontal_NS;
         case EAST:
         case WEST:
            return BlockVoxelShapes.pipeShapeHorizontal_EW;
      }
   }
}
