package com.conquestrefabricated.core.block.base;

import com.conquestrefabricated.core.block.properties.Waterloggable;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;

public abstract class WaterloggedShape extends Shape implements Waterloggable {
   public WaterloggedShape(Properties properties) {
      super(properties);
   }

   public BlockState getStateForPlacement(BlockPlaceContext context) {
      FluidState fluid = context.getLevel().getFluidState(context.getClickedPos());
      BlockState base = super.getStateForPlacement(context);
      if (base == null) {
         base = this.defaultBlockState();
      }

      return (BlockState)base.setValue(WATERLOGGED, fluid.getType() == Fluids.WATER);
   }

   public FluidState getFluidState(BlockState state) {
      return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
   }

   protected final void createBlockStateDefinition(Builder<Block, BlockState> builder) {
      builder.add(new Property[]{WATERLOGGED});
      this.addProperties(builder);
   }

   protected void addProperties(Builder<Block, BlockState> builder) {
   }
}
