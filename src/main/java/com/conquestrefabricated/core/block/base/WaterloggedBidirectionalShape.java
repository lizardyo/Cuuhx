package com.conquestrefabricated.core.block.base;

import com.conquestrefabricated.core.block.properties.BidirectionalShape;
import com.conquestrefabricated.core.block.properties.Waterloggable;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import org.jetbrains.annotations.NotNull;

public abstract class WaterloggedBidirectionalShape extends Shape implements Waterloggable {
   public static final EnumProperty DIRECTION = EnumProperty.create("direction", BidirectionalShape.class);

   public WaterloggedBidirectionalShape(Properties builder) {
      super(builder);
      this.registerDefaultState((BlockState)((BlockState)this.stateDefinition.any()).setValue(WATERLOGGED, false));
   }

   public BlockState rotate(BlockState state, Rotation rot) {
      return state.getValue(DIRECTION) == BidirectionalShape.NORTH_SOUTH
         ? (BlockState)state.setValue(DIRECTION, BidirectionalShape.EAST_WEST)
         : (BlockState)state.setValue(DIRECTION, BidirectionalShape.NORTH_SOUTH);
   }

   public BlockState mirror(BlockState state, Mirror mirrorIn) {
      return state;
   }

   @NotNull
   public BlockState getStateForPlacement(BlockPlaceContext context) {
      BidirectionalShape facing = BidirectionalShape.EAST_WEST;
      if (context.getHorizontalDirection() == Direction.NORTH || context.getHorizontalDirection() == Direction.SOUTH) {
         facing = BidirectionalShape.NORTH_SOUTH;
      }

      FluidState fluid = context.getLevel().getFluidState(context.getClickedPos());
      return (BlockState)((BlockState)super.getStateForPlacement(context).setValue(DIRECTION, facing)).setValue(WATERLOGGED, fluid.getType() == Fluids.WATER);
   }

   public FluidState getFluidState(BlockState state) {
      return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
   }

   protected final void createBlockStateDefinition(Builder<Block, BlockState> builder) {
      builder.add(new Property[]{DIRECTION, WATERLOGGED});
      this.addProperties(builder);
   }

   protected void addProperties(Builder<Block, BlockState> builder) {
   }
}
