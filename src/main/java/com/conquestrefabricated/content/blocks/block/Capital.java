package com.conquestrefabricated.content.blocks.block;

import com.conquestrefabricated.core.asset.annotation.Assets;
import com.conquestrefabricated.core.asset.annotation.Model;
import com.conquestrefabricated.core.asset.annotation.State;
import com.conquestrefabricated.core.block.base.WaterloggedShape;
import com.conquestrefabricated.core.block.properties.CapitalDirection;
import com.conquestrefabricated.core.block.properties.Waterloggable;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

@Assets(
   state = @State(name = "%s_capital", template = "parent_capital"),
   item = @Model(name = "item/%s_capital", parent = "block/%s_capital_down_flat", template = "item/parent_capital"),
   block = {
         @Model(name = "block/%s_capital_down_flat", template = "block/parent_capital_down_flat"),
         @Model(name = "block/%s_capital_down_side", template = "block/parent_capital_down_side"),
         @Model(name = "block/%s_capital_up_flat", template = "block/parent_capital_up_flat"),
         @Model(name = "block/%s_capital_up_side", template = "block/parent_capital_up_side")
   }
)
public class Capital extends WaterloggedShape implements Waterloggable {
   public static final EnumProperty<CapitalDirection> FACING = EnumProperty.create("facing", CapitalDirection.class);
   public static final EnumProperty<Half> TYPE = EnumProperty.create("type", Half.class);
   private static final VoxelShape TOP_FLAT_BIG = Block.box(0.0, 8.0, 0.0, 16.0, 16.0, 16.0);
   private static final VoxelShape TOP_FLAT_MIDDLE = Block.box(4.0, 0.0, 4.0, 12.0, 10.0, 12.0);
   private static final VoxelShape TOP_FLAT = Shapes.or(TOP_FLAT_BIG, TOP_FLAT_MIDDLE);
   private static final VoxelShape BOTTOM_FLAT_BIG = Block.box(0.0, 0.0, 0.0, 16.0, 8.0, 16.0);
   private static final VoxelShape BOTTOM_FLAT_MIDDLE = Block.box(4.0, 6.0, 4.0, 12.0, 16.0, 12.0);
   private static final VoxelShape BOTTOM_FLAT = Shapes.or(BOTTOM_FLAT_BIG, BOTTOM_FLAT_MIDDLE);
   private static final VoxelShape BOTTOM_SIDE_BIG = Block.box(0.0, 0.0, 0.0, 16.0, 6.0, 16.0);
   private static final VoxelShape BOTTOM_SIDE_MIDDLE_N = Block.box(3.0, 6.0, 0.0, 13.0, 12.0, 13.0);
   private static final VoxelShape BOTTOM_SIDE_MIDDLE_S = Block.box(3.0, 6.0, 3.0, 13.0, 12.0, 16.0);
   private static final VoxelShape BOTTOM_SIDE_MIDDLE_E = Block.box(3.0, 6.0, 3.0, 16.0, 12.0, 13.0);
   private static final VoxelShape BOTTOM_SIDE_MIDDLE_W = Block.box(0.0, 6.0, 3.0, 13.0, 12.0, 13.0);
   private static final VoxelShape BOTTOM_SIDE_N = Shapes.or(BOTTOM_SIDE_BIG, BOTTOM_SIDE_MIDDLE_N);
   private static final VoxelShape BOTTOM_SIDE_S = Shapes.or(BOTTOM_SIDE_BIG, BOTTOM_SIDE_MIDDLE_S);
   private static final VoxelShape BOTTOM_SIDE_E = Shapes.or(BOTTOM_SIDE_BIG, BOTTOM_SIDE_MIDDLE_E);
   private static final VoxelShape BOTTOM_SIDE_W = Shapes.or(BOTTOM_SIDE_BIG, BOTTOM_SIDE_MIDDLE_W);
   private static final VoxelShape TOP_SIDE_BIG = Block.box(0.0, 10.0, 0.0, 16.0, 16.0, 16.0);
   private static final VoxelShape TOP_SIDE_MIDDLE_N = Block.box(12.0, 4.0, 0.0, 16.0, 10.0, 12.0);
   private static final VoxelShape TOP_SIDE_MIDDLE_S = Block.box(4.0, 4.0, 4.0, 12.0, 10.0, 16.0);
   private static final VoxelShape TOP_SIDE_MIDDLE_E = Block.box(4.0, 4.0, 4.0, 16.0, 10.0, 12.0);
   private static final VoxelShape TOP_SIDE_MIDDLE_W = Block.box(0.0, 4.0, 4.0, 12.0, 10.0, 12.0);
   private static final VoxelShape TOP_SIDE_N = Shapes.or(TOP_SIDE_BIG, TOP_SIDE_MIDDLE_N);
   private static final VoxelShape TOP_SIDE_S = Shapes.or(TOP_SIDE_BIG, TOP_SIDE_MIDDLE_S);
   private static final VoxelShape TOP_SIDE_E = Shapes.or(TOP_SIDE_BIG, TOP_SIDE_MIDDLE_E);
   private static final VoxelShape TOP_SIDE_W = Shapes.or(TOP_SIDE_BIG, TOP_SIDE_MIDDLE_W);

   public Capital(Properties properties) {
      super(properties);
      this.registerDefaultState(
         (BlockState)((BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue(TYPE, Half.TOP)).setValue(FACING, CapitalDirection.NORTH))
            .setValue(WATERLOGGED, false)
      );
   }

   @Override
   public VoxelShape getShape(BlockState state) {
      if (state.getValue(TYPE) == Half.TOP) {
         if (state.getValue(FACING) == CapitalDirection.NORTH) {
            return TOP_SIDE_N;
         } else if (state.getValue(FACING) == CapitalDirection.SOUTH) {
            return TOP_SIDE_S;
         } else if (state.getValue(FACING) == CapitalDirection.EAST) {
            return TOP_SIDE_E;
         } else {
            return state.getValue(FACING) == CapitalDirection.WEST ? TOP_SIDE_W : TOP_FLAT;
         }
      } else if (state.getValue(FACING) == CapitalDirection.NORTH) {
         return BOTTOM_SIDE_N;
      } else if (state.getValue(FACING) == CapitalDirection.SOUTH) {
         return BOTTOM_SIDE_S;
      } else if (state.getValue(FACING) == CapitalDirection.EAST) {
         return BOTTOM_SIDE_E;
      } else {
         return state.getValue(FACING) == CapitalDirection.WEST ? BOTTOM_SIDE_W : BOTTOM_FLAT;
      }
   }

   @Override
   public BlockState getStateForPlacement(BlockPlaceContext context) {
      FluidState ifluidstate = context.getLevel().getFluidState(context.getClickedPos());
      Direction facing = context.getClickedFace();

      CapitalDirection horizontalFacing = switch (facing) {
         case NORTH -> CapitalDirection.SOUTH;
         case SOUTH -> CapitalDirection.NORTH;
         case WEST -> CapitalDirection.EAST;
         case EAST -> CapitalDirection.WEST;
         default -> CapitalDirection.FLAT;
      };
      Half verticalFacing;
      if (facing == Direction.DOWN || facing != Direction.UP && !(context.getClickLocation().y <= 0.5)) {
         verticalFacing = Half.TOP;
      } else {
         verticalFacing = Half.BOTTOM;
      }

      return (BlockState)((BlockState)((BlockState)super.getStateForPlacement(context).setValue(TYPE, verticalFacing)).setValue(FACING, horizontalFacing))
         .setValue(WATERLOGGED, ifluidstate.getType() == Fluids.WATER);
   }

   @Override
   protected void addProperties(Builder<Block, BlockState> builder) {
      builder.add(new Property[]{TYPE, FACING});
   }
}
