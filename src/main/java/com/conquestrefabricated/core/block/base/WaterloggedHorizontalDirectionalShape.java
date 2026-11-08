package com.conquestrefabricated.core.block.base;

import com.conquestrefabricated.core.block.properties.Waterloggable;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import org.jetbrains.annotations.NotNull;

public abstract class WaterloggedHorizontalDirectionalShape extends Shape implements Waterloggable {
   public static final DirectionProperty DIRECTION = BlockStateProperties.HORIZONTAL_FACING;

   public WaterloggedHorizontalDirectionalShape(Properties builder) {
      super(builder);
      this.registerDefaultState((BlockState)((BlockState)this.stateDefinition.any()).setValue(WATERLOGGED, false));
   }

   public BlockState rotate(BlockState state, Rotation rot) {
      return (BlockState)state.setValue(DIRECTION, rot.rotate((Direction)state.getValue(DIRECTION)));
   }

   public BlockState mirror(BlockState state, Mirror mirrorIn) {
      return (BlockState)state.setValue(DIRECTION, mirrorIn.mirror((Direction)state.getValue(DIRECTION)));
   }

   @NotNull
   public BlockState getStateForPlacement(BlockPlaceContext context) {
      Direction facing = context.getHorizontalDirection().getOpposite();
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
