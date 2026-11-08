package com.conquestrefabricated.core.block.base;

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
import org.jetbrains.annotations.NotNull;

public abstract class HorizontalDirectionalShape extends Shape {
   public static final DirectionProperty DIRECTION = BlockStateProperties.HORIZONTAL_FACING;

   public HorizontalDirectionalShape(Properties builder) {
      super(builder);
   }

   public BlockState rotate(BlockState state, Rotation rot) {
      return (BlockState)state.setValue(DIRECTION, rot.rotate((Direction)state.getValue(DIRECTION)));
   }

   public BlockState mirror(BlockState state, Mirror mirrorIn) {
      return state.rotate(mirrorIn.getRotation((Direction)state.getValue(DIRECTION)));
   }

   @NotNull
   public BlockState getStateForPlacement(BlockPlaceContext context) {
      Direction facing = context.getHorizontalDirection().getOpposite();
      return (BlockState)super.getStateForPlacement(context).setValue(DIRECTION, facing);
   }

   protected final void createBlockStateDefinition(Builder<Block, BlockState> builder) {
      builder.add(new Property[]{DIRECTION});
      this.addProperties(builder);
   }

   protected void addProperties(Builder<Block, BlockState> builder) {
   }
}
