package com.conquestrefabricated.content.blocks.block.classical;

import com.conquestrefabricated.content.blocks.block.VerticalQuarterLessLayers;
import com.conquestrefabricated.core.asset.annotation.Assets;
import com.conquestrefabricated.core.asset.annotation.Model;
import com.conquestrefabricated.core.asset.annotation.State;
import com.conquestrefabricated.core.block.builder.Props;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Property;

@Assets(
   state = @State(name = "%s_vertical_quarter", template = "parent_doric_capital_vertical_quarter"),
   item = @Model(name = "item/%s_vertical_quarter", parent = "block/%s_vertical_quarter_4", template = "item/parent_vertical_quarter"),
   block = {
         @Model(name = "block/%s_vertical_quarter_2", template = "block/parent_doric_capital_vertical_quarter_2"),
         @Model(name = "block/%s_vertical_quarter_2_e", template = "block/parent_doric_capital_vertical_quarter_2_e"),
         @Model(name = "block/%s_vertical_quarter_2_es", template = "block/parent_doric_capital_vertical_quarter_2_es"),
         @Model(name = "block/%s_vertical_quarter_2_s", template = "block/parent_doric_capital_vertical_quarter_2_s"),
         @Model(name = "block/%s_vertical_quarter_4", template = "block/parent_doric_capital_vertical_quarter_4"),
         @Model(name = "block/%s_vertical_quarter_4_e", template = "block/parent_doric_capital_vertical_quarter_4_e"),
         @Model(name = "block/%s_vertical_quarter_4_es", template = "block/parent_doric_capital_vertical_quarter_4_es"),
         @Model(name = "block/%s_vertical_quarter_4_s", template = "block/parent_doric_capital_vertical_quarter_4_s"),
         @Model(name = "block/%s_vertical_quarter_6", template = "block/parent_doric_capital_vertical_quarter_6"),
         @Model(name = "block/%s_vertical_quarter_6_e", template = "block/parent_doric_capital_vertical_quarter_6_e"),
         @Model(name = "block/%s_vertical_quarter_6_es", template = "block/parent_doric_capital_vertical_quarter_6_es"),
         @Model(name = "block/%s_vertical_quarter_6_s", template = "block/parent_doric_capital_vertical_quarter_6_s")
   }
)
public class VerticalQuarterCapital extends VerticalQuarterLessLayers {
   public static final BooleanProperty EAST = BooleanProperty.create("e");
   public static final BooleanProperty SOUTH = BooleanProperty.create("s");

   public VerticalQuarterCapital(Props properties) {
      super(properties);
      this.registerDefaultState(
         (BlockState)((BlockState)((BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue(DIRECTION, Direction.NORTH)).setValue(EAST, false))
               .setValue(SOUTH, false))
            .setValue(WATERLOGGED, false)
      );
   }

   public boolean canConnectTo(BlockState state, Direction stateDirectionOriginal) {
      Block block = state.getBlock();
      boolean flag1 = block instanceof CubeCapital || block instanceof VerticalSlabCapital || block instanceof VerticalCornerCapital;
      boolean flag2 = block instanceof VerticalQuarterCapital
         && (state.getValue(DIRECTION) == stateDirectionOriginal.getClockWise() || state.getValue(DIRECTION) == stateDirectionOriginal.getCounterClockWise());
      return flag2 || flag1;
   }

   @Override
   public BlockState getStateForPlacement(BlockPlaceContext context) {
      BlockState blockstate = context.getLevel().getBlockState(context.getClickedPos());
      Direction facing = context.getHorizontalDirection().getOpposite();
      BlockGetter iblockreader = context.getLevel();
      BlockPos blockpos = context.getClickedPos();
      BlockPos blockpos2;
      BlockPos blockpos3;
      switch (facing) {
         case SOUTH:
            blockpos2 = blockpos.west();
            blockpos3 = blockpos.north();
            break;
         case EAST:
            blockpos2 = blockpos.south();
            blockpos3 = blockpos.west();
            break;
         case WEST:
            blockpos2 = blockpos.north();
            blockpos3 = blockpos.east();
            break;
         default:
            blockpos2 = blockpos.east();
            blockpos3 = blockpos.south();
      }

      BlockState blockstate1 = iblockreader.getBlockState(blockpos2);
      BlockState blockstate2 = iblockreader.getBlockState(blockpos3);
      if (blockstate.getBlock() == this) {
         int i = (Integer)blockstate.getValue(LAYERS);
         if (i == 3) {
            return this.fullBlock.defaultBlockState();
         }
      }

      return (BlockState)((BlockState)super.getStateForPlacement(context).setValue(EAST, this.canConnectTo(blockstate1, facing)))
         .setValue(SOUTH, this.canConnectTo(blockstate2, facing));
   }

   public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor world, BlockPos currentPos, BlockPos facingPos) {
      Direction stateDirection = (Direction)stateIn.getValue(DIRECTION);
      if (stateDirection == Direction.NORTH) {
         if (facing == Direction.NORTH) {
            return super.updateShape(stateIn, facing, facingState, world, currentPos, facingPos);
         }

         if (facing == Direction.SOUTH) {
            return (BlockState)stateIn.setValue(SOUTH, this.canConnectTo(facingState, stateDirection));
         }

         if (facing == Direction.EAST) {
            return (BlockState)stateIn.setValue(EAST, this.canConnectTo(facingState, stateDirection));
         }
      }

      if (stateDirection == Direction.EAST) {
         if (facing == Direction.EAST) {
            return super.updateShape(stateIn, facing, facingState, world, currentPos, facingPos);
         }

         if (facing == Direction.WEST) {
            return (BlockState)stateIn.setValue(SOUTH, this.canConnectTo(facingState, stateDirection));
         }

         if (facing == Direction.SOUTH) {
            return (BlockState)stateIn.setValue(EAST, this.canConnectTo(facingState, stateDirection));
         }
      }

      if (stateDirection == Direction.SOUTH) {
         if (facing == Direction.SOUTH) {
            return super.updateShape(stateIn, facing, facingState, world, currentPos, facingPos);
         }

         if (facing == Direction.NORTH) {
            return (BlockState)stateIn.setValue(SOUTH, this.canConnectTo(facingState, stateDirection));
         }

         if (facing == Direction.WEST) {
            return (BlockState)stateIn.setValue(EAST, this.canConnectTo(facingState, stateDirection));
         }
      }

      if (stateDirection == Direction.WEST) {
         if (facing == Direction.WEST) {
            return super.updateShape(stateIn, facing, facingState, world, currentPos, facingPos);
         }

         if (facing == Direction.EAST) {
            return (BlockState)stateIn.setValue(SOUTH, this.canConnectTo(facingState, stateDirection));
         }

         if (facing == Direction.NORTH) {
            return (BlockState)stateIn.setValue(EAST, this.canConnectTo(facingState, stateDirection));
         }
      }

      return super.updateShape(stateIn, facing, facingState, world, currentPos, facingPos);
   }

   @Override
   protected void addProperties(Builder<Block, BlockState> builder) {
      builder.add(new Property[]{LAYERS, EAST, SOUTH});
   }
}
