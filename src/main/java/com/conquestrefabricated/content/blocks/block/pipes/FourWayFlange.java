package com.conquestrefabricated.content.blocks.block.pipes;

import com.conquestrefabricated.content.blocks.BlockVoxelShapes;
import com.conquestrefabricated.content.blocks.block.directional.HorizontalDirectional;
import com.conquestrefabricated.core.block.builder.Props;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;

public class FourWayFlange extends HorizontalDirectional.Waterlogged.Toggle3 {
   public FourWayFlange(Props props) {
      super(props);
   }

   @Override
   public VoxelShape getShape(BlockState state) {
      switch (state.getValue(TOGGLE)) {
         case 1:
         default:
            switch ((Direction)state.getValue(DIRECTION)) {
               case NORTH:
               default:
                  return BlockVoxelShapes.fourWayFlangeShapes.get(0);
               case EAST:
                  return BlockVoxelShapes.fourWayFlangeShapes.get(1);
               case SOUTH:
                  return BlockVoxelShapes.fourWayFlangeShapes.get(2);
               case WEST:
                  return BlockVoxelShapes.fourWayFlangeShapes.get(3);
            }
         case 2:
            switch ((Direction)state.getValue(DIRECTION)) {
               case NORTH:
               default:
                  return BlockVoxelShapes.fourWayFlangeShapes.get(8);
               case EAST:
                  return BlockVoxelShapes.fourWayFlangeShapes.get(9);
               case SOUTH:
                  return BlockVoxelShapes.fourWayFlangeShapes.get(10);
               case WEST:
                  return BlockVoxelShapes.fourWayFlangeShapes.get(11);
            }
         case 3:
            switch ((Direction)state.getValue(DIRECTION)) {
               case NORTH:
               default:
                  return BlockVoxelShapes.fourWayFlangeShapes.get(4);
               case EAST:
                  return BlockVoxelShapes.fourWayFlangeShapes.get(5);
               case SOUTH:
                  return BlockVoxelShapes.fourWayFlangeShapes.get(6);
               case WEST:
                  return BlockVoxelShapes.fourWayFlangeShapes.get(7);
            }
      }
   }
}
