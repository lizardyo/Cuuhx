package com.conquestrefabricated.core.block.base;

import net.minecraft.core.Direction.Axis;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Property;

public abstract class AxisShape extends Shape {
   public static final EnumProperty<Axis> AXIS = BlockStateProperties.AXIS;

   public AxisShape(Properties builder) {
      super(builder);
   }

   public BlockState rotate(BlockState state, Rotation rot) {
      switch (rot) {
         case COUNTERCLOCKWISE_90:
         case CLOCKWISE_90:
            switch ((Axis)state.getValue(AXIS)) {
               case X:
                  return (BlockState)state.setValue(AXIS, Axis.Z);
               case Z:
                  return (BlockState)state.setValue(AXIS, Axis.X);
               default:
                  return state;
            }
         default:
            return state;
      }
   }

   protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
      builder.add(new Property[]{AXIS});
      this.addProperties(builder);
   }

   public BlockState getStateForPlacement(BlockPlaceContext context) {
      return (BlockState)this.defaultBlockState().setValue(AXIS, context.getClickedFace().getAxis());
   }

   protected void addProperties(Builder<Block, BlockState> builder) {
   }
}
