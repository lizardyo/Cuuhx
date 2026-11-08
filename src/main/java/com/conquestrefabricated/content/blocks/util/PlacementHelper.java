package com.conquestrefabricated.content.blocks.util;

import com.conquestrefabricated.content.blocks.block.BoardsHorizontal;
import com.conquestrefabricated.content.blocks.block.Layer;
import com.conquestrefabricated.content.blocks.block.Slab;
import com.conquestrefabricated.content.blocks.block.SlabCorner;
import com.conquestrefabricated.content.blocks.block.SlabEighth;
import com.conquestrefabricated.content.blocks.block.SlabLessLayers;
import com.conquestrefabricated.content.blocks.block.SlabQuarter;
import com.conquestrefabricated.content.blocks.block.VerticalSlab;
import java.util.function.BiFunction;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.DirectionalPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DirectionProperty;

public class PlacementHelper {
   public PlacementHelper() {
   }

   public static boolean replacingClickedOnBlock(BlockPlaceContext context) {
      return context instanceof DirectionalPlaceContext ? false : context.replacingClickedOnBlock();
   }

   public static boolean isDuringWorldGen(BlockGetter reader) {
      return !(reader instanceof Level);
   }

   public static Direction getHitVecHorizontalAxisDirection(Direction facing, BlockPos pos, BlockPlaceContext context) {
      switch (facing) {
         case NORTH:
            return !(context.getClickLocation().x - pos.getX() > 0.5) ? facing.getClockWise() : facing;
         case SOUTH:
            return !(context.getClickLocation().x - pos.getX() < 0.5) ? facing.getClockWise() : facing;
         case EAST:
            return !(context.getClickLocation().z - pos.getZ() > 0.5) ? facing.getClockWise() : facing;
         default:
            return !(context.getClickLocation().z - pos.getZ() < 0.5) ? facing.getClockWise() : facing;
      }
   }

   public static int getOrdinalDirection(BlockPlaceContext ctx, int ordinalState) {
      Player player = ctx.getPlayer();
      if (player != null) {
         float yaw = player.getYRot() % 360.0F;
         if (yaw < 0.0F) {
            yaw += 360.0F;
         }

         boolean isOrdinal = false;
         float tolerance = 25.0F;
         isOrdinal = Math.abs(yaw - 45.0F) <= tolerance
            || Math.abs(yaw - 135.0F) <= tolerance
            || Math.abs(yaw - 225.0F) <= tolerance
            || Math.abs(yaw - 315.0F) <= tolerance;
         if (isOrdinal) {
            return ordinalState;
         }
      }

      return 1;
   }

   public static boolean isFacingSlab(BlockPlaceContext context) {
      Direction facing = context.getClickedFace().getOpposite();
      BlockGetter blockreader = context.getLevel();
      BlockPos pos = context.getClickedPos();
      BlockState state = blockreader.getBlockState(pos.relative(facing));
      Block block = state.getBlock();
      boolean isSlab = false;
      if (facing == Direction.DOWN) {
         isSlab = block instanceof Slab
            || block instanceof SlabBlock
            || block instanceof Layer
            || block instanceof SlabEighth
            || block instanceof SlabCorner
            || block instanceof SlabQuarter
            || block instanceof SnowLayerBlock
            || block instanceof SlabLessLayers
            || block instanceof BoardsHorizontal;
      } else if (facing != Direction.UP) {
         isSlab = block instanceof VerticalSlab && state.getValue(VerticalSlab.DIRECTION) == context.getClickedFace();
      }

      return isSlab;
   }

   public static BlockState mirrorCornerDirection(
      BlockState state, Mirror mirrorIn, DirectionProperty directionProperty, BiFunction<BlockState, Mirror, BlockState> fallbackMirror
   ) {
      switch (mirrorIn) {
         case FRONT_BACK:
            switch ((Direction)state.getValue(directionProperty)) {
               case NORTH:
                  return (BlockState)state.setValue(directionProperty, Direction.EAST);
               case SOUTH:
                  return (BlockState)state.setValue(directionProperty, Direction.WEST);
               case EAST:
                  return (BlockState)state.setValue(directionProperty, Direction.NORTH);
               case WEST:
                  return (BlockState)state.setValue(directionProperty, Direction.SOUTH);
               default:
                  return fallbackMirror.apply(state, mirrorIn);
            }
         case LEFT_RIGHT:
            switch ((Direction)state.getValue(directionProperty)) {
               case NORTH:
                  return (BlockState)state.setValue(directionProperty, Direction.WEST);
               case SOUTH:
                  return (BlockState)state.setValue(directionProperty, Direction.EAST);
               case EAST:
                  return (BlockState)state.setValue(directionProperty, Direction.SOUTH);
               case WEST:
                  return (BlockState)state.setValue(directionProperty, Direction.NORTH);
               default:
                  return fallbackMirror.apply(state, mirrorIn);
            }
         default:
            return fallbackMirror.apply(state, mirrorIn);
      }
   }
}
