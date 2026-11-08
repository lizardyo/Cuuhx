package com.conquestrefabricated.content.blocks.block.decor;

import com.conquestrefabricated.core.asset.annotation.Assets;
import com.conquestrefabricated.core.asset.annotation.Model;
import com.conquestrefabricated.core.asset.annotation.State;
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

@Assets(
   state = @State(name = "%s", template = "parent_log_furniture"),
   item = @Model(name = "item/%s", parent = "block/%s", template = "item/acacia_log"),
   block = {
         @Model(name = "block/%s", template = "block/acacia_log"),
         @Model(name = "block/%s_side", template = "block/parent_log_furniture_side_2"),
         @Model(name = "block/%s_side_1", template = "block/parent_log_furniture_side_1")
   }
)
public class AxisFurniture extends Block {
   public static final EnumProperty<Axis> AXIS = BlockStateProperties.AXIS;

   public AxisFurniture(Properties properties) {
      super(properties);
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
   }

   public BlockState getStateForPlacement(BlockPlaceContext context) {
      return (BlockState)this.defaultBlockState().setValue(AXIS, context.getClickedFace().getAxis());
   }
}
