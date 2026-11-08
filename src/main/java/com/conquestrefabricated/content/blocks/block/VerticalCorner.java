package com.conquestrefabricated.content.blocks.block;

import com.conquestrefabricated.content.blocks.util.PlacementHelper;
import com.conquestrefabricated.core.asset.annotation.Assets;
import com.conquestrefabricated.core.asset.annotation.Model;
import com.conquestrefabricated.core.asset.annotation.SpecialOffset;
import com.conquestrefabricated.core.asset.annotation.State;
import com.conquestrefabricated.core.block.base.WaterloggedHorizontalDirectionalShape;
import com.conquestrefabricated.core.block.builder.Props;
import com.conquestrefabricated.core.block.builder.SpecialOffsetType;
import com.conquestrefabricated.core.block.properties.Waterloggable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

@Assets(
   state = @State(name = "%s_vertical_corner", template = "parent_vertical_corner"),
   item = @Model(name = "item/%s_vertical_corner", parent = "block/%s_vertical_corner_4", template = "item/parent_vertical_corner"),
   block = {
         @Model(name = "block/%s_vertical_corner_1", template = "block/parent_vertical_corner_1"),
         @Model(name = "block/%s_vertical_corner_2", template = "block/parent_vertical_corner_2"),
         @Model(name = "block/%s_vertical_corner_4", template = "block/parent_vertical_corner_4"),
         @Model(name = "block/%s_vertical_corner_6", template = "block/parent_vertical_corner_6")
   }
)
@SpecialOffset(offsetType = SpecialOffsetType.DUPLICATE_DOWN)
public class VerticalCorner extends WaterloggedHorizontalDirectionalShape implements Waterloggable {
   public static final IntegerProperty LAYERS = IntegerProperty.create("layer", 1, 4);
   public static final VoxelShape[] EAST_SHAPE = new VoxelShape[]{
      Shapes.or(VerticalSlab.EAST_SHAPE[0], VerticalSlab.NORTH_SHAPE[0]),
      Shapes.or(VerticalSlab.EAST_SHAPE[1], VerticalSlab.NORTH_SHAPE[1]),
      Shapes.or(VerticalSlab.EAST_SHAPE[2], VerticalSlab.NORTH_SHAPE[2]),
      Shapes.or(VerticalSlab.EAST_SHAPE[3], VerticalSlab.NORTH_SHAPE[3])
   };
   public static final VoxelShape[] WEST_SHAPE = new VoxelShape[]{
      Shapes.or(VerticalSlab.WEST_SHAPE[0], VerticalSlab.SOUTH_SHAPE[0]),
      Shapes.or(VerticalSlab.WEST_SHAPE[1], VerticalSlab.SOUTH_SHAPE[1]),
      Shapes.or(VerticalSlab.WEST_SHAPE[2], VerticalSlab.SOUTH_SHAPE[2]),
      Shapes.or(VerticalSlab.WEST_SHAPE[3], VerticalSlab.SOUTH_SHAPE[3])
   };
   public static final VoxelShape[] NORTH_SHAPE = new VoxelShape[]{
      Shapes.or(VerticalSlab.NORTH_SHAPE[0], VerticalSlab.WEST_SHAPE[0]),
      Shapes.or(VerticalSlab.NORTH_SHAPE[1], VerticalSlab.WEST_SHAPE[1]),
      Shapes.or(VerticalSlab.NORTH_SHAPE[2], VerticalSlab.WEST_SHAPE[2]),
      Shapes.or(VerticalSlab.NORTH_SHAPE[3], VerticalSlab.WEST_SHAPE[3])
   };
   public static final VoxelShape[] SOUTH_SHAPE = new VoxelShape[]{
      Shapes.or(VerticalSlab.SOUTH_SHAPE[0], VerticalSlab.EAST_SHAPE[0]),
      Shapes.or(VerticalSlab.SOUTH_SHAPE[1], VerticalSlab.EAST_SHAPE[1]),
      Shapes.or(VerticalSlab.SOUTH_SHAPE[2], VerticalSlab.EAST_SHAPE[2]),
      Shapes.or(VerticalSlab.SOUTH_SHAPE[3], VerticalSlab.EAST_SHAPE[3])
   };
   protected Block fullBlock;

   public VerticalCorner(Props props) {
      super(props.toSettings());
      this.registerDefaultState((BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue(DIRECTION, Direction.NORTH)).setValue(WATERLOGGED, false));
      this.fullBlock = props.getParent().getBlock();
   }

   @Override
   public BlockState mirror(BlockState state, Mirror mirrorIn) {
      switch (mirrorIn) {
         case FRONT_BACK:
            switch ((Direction)state.getValue(DIRECTION)) {
               case NORTH:
                  return (BlockState)state.setValue(DIRECTION, Direction.EAST);
               case EAST:
                  return (BlockState)state.setValue(DIRECTION, Direction.NORTH);
               case SOUTH:
                  return (BlockState)state.setValue(DIRECTION, Direction.WEST);
               case WEST:
                  return (BlockState)state.setValue(DIRECTION, Direction.SOUTH);
               default:
                  return super.mirror(state, mirrorIn);
            }
         case LEFT_RIGHT:
            switch ((Direction)state.getValue(DIRECTION)) {
               case NORTH:
                  return (BlockState)state.setValue(DIRECTION, Direction.WEST);
               case EAST:
                  return (BlockState)state.setValue(DIRECTION, Direction.SOUTH);
               case SOUTH:
                  return (BlockState)state.setValue(DIRECTION, Direction.EAST);
               case WEST:
                  return (BlockState)state.setValue(DIRECTION, Direction.NORTH);
               default:
                  return super.mirror(state, mirrorIn);
            }
         default:
            return super.mirror(state, mirrorIn);
      }
   }

   public boolean useShapeForLightOcclusion(BlockState state) {
      return true;
   }

   @Override
   public VoxelShape getShape(BlockState state) {
      switch ((Direction)state.getValue(DIRECTION)) {
         case NORTH:
         default:
            return NORTH_SHAPE[state.getValue(LAYERS) - 1];
         case EAST:
            return EAST_SHAPE[state.getValue(LAYERS) - 1];
         case SOUTH:
            return SOUTH_SHAPE[state.getValue(LAYERS) - 1];
         case WEST:
            return WEST_SHAPE[state.getValue(LAYERS) - 1];
      }
   }

   public boolean canBeReplaced(BlockState state, BlockPlaceContext context) {
      int i = (Integer)state.getValue(LAYERS);
      if (context.getItemInHand().getItem() != this.asItem() || i > 4) {
         return false;
      } else {
         return !PlacementHelper.replacingClickedOnBlock(context)
            ? true
            : context.getClickedFace() == state.getValue(DIRECTION) || context.getClickedFace() == ((Direction)state.getValue(DIRECTION)).getCounterClockWise();
      }
   }

   @Override
   public BlockState getStateForPlacement(BlockPlaceContext context) {
      BlockPos blockpos = context.getClickedPos();
      Direction facing = PlacementHelper.getHitVecHorizontalAxisDirection(context.getHorizontalDirection().getOpposite(), blockpos, context);
      BlockState blockstate = context.getLevel().getBlockState(context.getClickedPos());
      if (blockstate.getBlock() == this) {
         int i = (Integer)blockstate.getValue(LAYERS);
         return i == 4 ? this.fullBlock.defaultBlockState() : (BlockState)blockstate.setValue(LAYERS, Math.min(4, i + 1));
      } else {
         return (BlockState)super.getStateForPlacement(context).setValue(DIRECTION, facing);
      }
   }

   @Override
   protected void addProperties(Builder<Block, BlockState> builder) {
      builder.add(new Property[]{LAYERS});
   }
}
