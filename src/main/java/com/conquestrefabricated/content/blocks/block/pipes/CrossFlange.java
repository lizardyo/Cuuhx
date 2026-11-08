package com.conquestrefabricated.content.blocks.block.pipes;

import com.conquestrefabricated.content.blocks.BlockVoxelShapes;
import com.conquestrefabricated.content.blocks.block.directional.HorizontalDirectional;
import com.conquestrefabricated.core.block.base.WaterloggedHorizontalDirectionalShape;
import com.conquestrefabricated.core.block.builder.Props;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CrossFlange extends HorizontalDirectional.Waterlogged.Toggle2 {
   public CrossFlange(Props props) {
      super(props);
   }

   @Override
   public VoxelShape getShape(BlockState state) {
      switch (state.getValue(TOGGLE)) {
         case 1:
         default:
            switch ((Direction)state.getValue(WaterloggedHorizontalDirectionalShape.DIRECTION)) {
               case NORTH:
               case SOUTH:
               default:
                  return BlockVoxelShapes.crossFlangeShapes.get(0);
               case EAST:
               case WEST:
                  return BlockVoxelShapes.crossFlangeShapes.get(1);
            }
         case 2:
            return BlockVoxelShapes.crossFlangeShapes.get(2);
      }
   }
}
