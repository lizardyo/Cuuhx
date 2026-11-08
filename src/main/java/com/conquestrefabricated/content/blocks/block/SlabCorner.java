package com.conquestrefabricated.content.blocks.block;

import com.conquestrefabricated.content.blocks.util.PlacementHelper;
import com.conquestrefabricated.core.asset.annotation.Assets;
import com.conquestrefabricated.core.asset.annotation.Model;
import com.conquestrefabricated.core.asset.annotation.State;
import com.conquestrefabricated.core.block.base.WaterloggedHorizontalDirectionalShape;
import com.conquestrefabricated.core.block.properties.Waterloggable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
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
   state = @State(name = "%s_corner_slab", template = "parent_slab_corner"),
   item = @Model(name = "item/%s_corner_slab", parent = "block/%s_slab_corner", template = "item/parent_slab_corner"),
   block = {
         @Model(name = "block/%s_slab_corner", template = "block/parent_slab_corner"),
         @Model(name = "block/%s_slab_corner_top", template = "block/parent_slab_corner_top")
   }
)
public class SlabCorner extends WaterloggedHorizontalDirectionalShape implements Waterloggable {
   public static final EnumProperty<Half> TYPE_UPDOWN = EnumProperty.create("type", Half.class);
   private static final VoxelShape BOTTOM_QTR_SOUTH_SHAPE = Block.box(0.0, 0.0, 8.0, 8.0, 8.0, 16.0);
   private static final VoxelShape BOTTOM_MAIN_SOUTH_SHAPE = Block.box(0.0, 0.0, 0.0, 16.0, 8.0, 8.0);
   private static final VoxelShape BOTTOM_SOUTH_SHAPE = Shapes.or(BOTTOM_QTR_SOUTH_SHAPE, BOTTOM_MAIN_SOUTH_SHAPE);
   private static final VoxelShape BOTTOM_QTR_NORTH_SHAPE = Block.box(8.0, 0.0, 0.0, 16.0, 8.0, 8.0);
   private static final VoxelShape BOTTOM_MAIN_NORTH_SHAPE = Block.box(0.0, 0.0, 8.0, 16.0, 8.0, 16.0);
   private static final VoxelShape BOTTOM_NORTH_SHAPE = Shapes.or(BOTTOM_QTR_NORTH_SHAPE, BOTTOM_MAIN_NORTH_SHAPE);
   private static final VoxelShape BOTTOM_QTR_WEST_SHAPE = Block.box(0.0, 0.0, 0.0, 8.0, 8.0, 8.0);
   private static final VoxelShape BOTTOM_MAIN_WEST_SHAPE = Block.box(8.0, 0.0, 0.0, 16.0, 8.0, 16.0);
   private static final VoxelShape BOTTOM_WEST_SHAPE = Shapes.or(BOTTOM_QTR_WEST_SHAPE, BOTTOM_MAIN_WEST_SHAPE);
   private static final VoxelShape BOTTOM_QTR_EAST_SHAPE = Block.box(8.0, 0.0, 8.0, 16.0, 8.0, 16.0);
   private static final VoxelShape BOTTOM_MAIN_EAST_SHAPE = Block.box(0.0, 0.0, 0.0, 8.0, 8.0, 16.0);
   private static final VoxelShape BOTTOM_EAST_SHAPE = Shapes.or(BOTTOM_QTR_EAST_SHAPE, BOTTOM_MAIN_EAST_SHAPE);
   private static final VoxelShape TOP_QTR_SOUTH_SHAPE = Block.box(0.0, 8.0, 8.0, 8.0, 16.0, 16.0);
   private static final VoxelShape TOP_MAIN_SOUTH_SHAPE = Block.box(0.0, 8.0, 0.0, 16.0, 16.0, 8.0);
   private static final VoxelShape TOP_SOUTH_SHAPE = Shapes.or(TOP_QTR_SOUTH_SHAPE, TOP_MAIN_SOUTH_SHAPE);
   private static final VoxelShape TOP_QTR_NORTH_SHAPE = Block.box(8.0, 8.0, 0.0, 16.0, 16.0, 8.0);
   private static final VoxelShape TOP_MAIN_NORTH_SHAPE = Block.box(0.0, 8.0, 8.0, 16.0, 16.0, 16.0);
   private static final VoxelShape TOP_NORTH_SHAPE = Shapes.or(TOP_QTR_NORTH_SHAPE, TOP_MAIN_NORTH_SHAPE);
   private static final VoxelShape TOP_QTR_WEST_SHAPE = Block.box(0.0, 8.0, 0.0, 8.0, 16.0, 8.0);
   private static final VoxelShape TOP_MAIN_WEST_SHAPE = Block.box(8.0, 8.0, 0.0, 16.0, 16.0, 16.0);
   private static final VoxelShape TOP_WEST_SHAPE = Shapes.or(TOP_QTR_WEST_SHAPE, TOP_MAIN_WEST_SHAPE);
   private static final VoxelShape TOP_QTR_EAST_SHAPE = Block.box(8.0, 8.0, 8.0, 16.0, 16.0, 16.0);
   private static final VoxelShape TOP_MAIN_EAST_SHAPE = Block.box(0.0, 8.0, 0.0, 8.0, 16.0, 16.0);
   private static final VoxelShape TOP_EAST_SHAPE = Shapes.or(TOP_QTR_EAST_SHAPE, TOP_MAIN_EAST_SHAPE);

   public SlabCorner(Properties properties) {
      super(properties);
      this.registerDefaultState(
         (BlockState)((BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue(DIRECTION, Direction.NORTH)).setValue(TYPE_UPDOWN, Half.BOTTOM))
            .setValue(WATERLOGGED, false)
      );
   }

   @Override
   public BlockState getStateForPlacement(BlockPlaceContext context) {
      BlockPos pos = context.getClickedPos();
      FluidState fluid = context.getLevel().getFluidState(pos);
      Direction facingHorizontal = PlacementHelper.getHitVecHorizontalAxisDirection(context.getHorizontalDirection().getOpposite(), pos, context);
      BlockState state2 = (BlockState)((BlockState)((BlockState)this.defaultBlockState().setValue(DIRECTION, facingHorizontal)).setValue(TYPE_UPDOWN, Half.BOTTOM))
         .setValue(WATERLOGGED, fluid.getType() == Fluids.WATER);
      Direction facing = context.getClickedFace();
      return facing == Direction.DOWN || facing != Direction.UP && context.getClickLocation().y - pos.getY() > 0.5
         ? (BlockState)state2.setValue(TYPE_UPDOWN, Half.TOP)
         : state2;
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

   @Override
   protected void addProperties(Builder<Block, BlockState> container) {
      container.add(new Property[]{TYPE_UPDOWN});
   }

   @Override
   public VoxelShape getShape(BlockState state) {
      if (state.getValue(TYPE_UPDOWN) == Half.BOTTOM) {
         switch ((Direction)state.getValue(DIRECTION)) {
            case NORTH:
            default:
               return BOTTOM_NORTH_SHAPE;
            case EAST:
               return BOTTOM_EAST_SHAPE;
            case SOUTH:
               return BOTTOM_SOUTH_SHAPE;
            case WEST:
               return BOTTOM_WEST_SHAPE;
         }
      } else {
         switch ((Direction)state.getValue(DIRECTION)) {
            case NORTH:
            default:
               return TOP_NORTH_SHAPE;
            case EAST:
               return TOP_EAST_SHAPE;
            case SOUTH:
               return TOP_SOUTH_SHAPE;
            case WEST:
               return TOP_WEST_SHAPE;
         }
      }
   }
}
