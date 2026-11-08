package com.conquestrefabricated.content.blocks.block.decor;

import com.conquestrefabricated.core.block.base.WaterloggedHorizontalDirectionalShape;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.phys.shapes.VoxelShape;

public class DiagonalBeamCentered extends WaterloggedHorizontalDirectionalShape {
   public static final VoxelShape EAST_WEST_SHAPE = Block.box(6.0, 0.0, 0.0, 10.0, 16.0, 16.0);
   public static final VoxelShape NORTH_SOUTH_SHAPE = Block.box(0.0, 0.0, 6.0, 16.0, 16.0, 10.0);

   public DiagonalBeamCentered(Properties properties) {
      super(properties);
   }

   @Override
   public VoxelShape getShape(BlockState state) {
      return state.getValue(DIRECTION) != Direction.NORTH && state.getValue(DIRECTION) != Direction.SOUTH ? EAST_WEST_SHAPE : NORTH_SOUTH_SHAPE;
   }

   @Override
   public BlockState getStateForPlacement(BlockPlaceContext context) {
      Direction facing = getDirection(context.getHorizontalDirection().getOpposite(), context.getClickedPos(), context);
      return (BlockState)super.getStateForPlacement(context).setValue(DIRECTION, facing);
   }

   public static Direction getDirection(Direction facing, BlockPos pos, BlockPlaceContext context) {
      switch (facing) {
         case NORTH:
            return !(context.getClickLocation().x - pos.getX() > 0.5) ? facing.getOpposite() : facing;
         case SOUTH:
            return !(context.getClickLocation().x - pos.getX() < 0.5) ? facing.getOpposite() : facing;
         case EAST:
            return !(context.getClickLocation().z - pos.getZ() > 0.5) ? facing.getOpposite() : facing;
         default:
            return !(context.getClickLocation().z - pos.getZ() < 0.5) ? facing.getOpposite() : facing;
      }
   }
}
