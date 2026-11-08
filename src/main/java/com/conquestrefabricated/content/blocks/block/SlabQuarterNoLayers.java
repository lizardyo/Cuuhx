package com.conquestrefabricated.content.blocks.block;

import com.conquestrefabricated.core.asset.annotation.Assets;
import com.conquestrefabricated.core.asset.annotation.Model;
import com.conquestrefabricated.core.asset.annotation.State;
import com.conquestrefabricated.core.block.base.WaterloggedHorizontalDirectionalShape;
import net.minecraft.core.BlockPos;
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
import net.minecraft.world.phys.shapes.VoxelShape;

@Assets(
   state = @State(name = "%s_quarter_slab", template = "parent_slab_quarter_no_layers"),
   item = @Model(name = "item/%s_quarter_slab", parent = "block/%s_slab_quarter_4", template = "item/parent_slab_quarter"),
   block = {
         @Model(name = "block/%s_slab_quarter_4", template = "block/parent_slab_quarter_4"),
         @Model(name = "block/%s_slab_quarter_4_top", template = "block/parent_slab_quarter_4_top")
   }
)
public class SlabQuarterNoLayers extends WaterloggedHorizontalDirectionalShape {
   public static final EnumProperty<Half> TYPE_UPDOWN = EnumProperty.create("type", Half.class);
   public static final VoxelShape BOTTOM_SOUTH_SHAPE = Block.box(0.0, 0.0, 0.0, 16.0, 8.0, 8.0);
   public static final VoxelShape BOTTOM_NORTH_SHAPE = Block.box(0.0, 0.0, 8.0, 16.0, 8.0, 16.0);
   public static final VoxelShape BOTTOM_WEST_SHAPE = Block.box(8.0, 0.0, 0.0, 16.0, 8.0, 16.0);
   public static final VoxelShape BOTTOM_EAST_SHAPE = Block.box(0.0, 0.0, 0.0, 8.0, 8.0, 16.0);
   public static final VoxelShape TOP_SOUTH_SHAPE = Block.box(0.0, 8.0, 0.0, 16.0, 16.0, 8.0);
   public static final VoxelShape TOP_NORTH_SHAPE = Block.box(0.0, 8.0, 8.0, 16.0, 16.0, 16.0);
   public static final VoxelShape TOP_WEST_SHAPE = Block.box(8.0, 8.0, 0.0, 16.0, 16.0, 16.0);
   public static final VoxelShape TOP_EAST_SHAPE = Block.box(0.0, 8.0, 0.0, 8.0, 16.0, 16.0);

   public SlabQuarterNoLayers(Properties properties) {
      super(properties);
      this.registerDefaultState(
         (BlockState)((BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue(DIRECTION, Direction.NORTH)).setValue(TYPE_UPDOWN, Half.BOTTOM))
            .setValue(WATERLOGGED, false)
      );
   }

   @Override
   public BlockState getStateForPlacement(BlockPlaceContext context) {
      BlockPos blockpos = context.getClickedPos();
      FluidState fluid = context.getLevel().getFluidState(blockpos);
      Direction facingHorizontal = context.getHorizontalDirection().getOpposite();
      BlockState state2 = (BlockState)((BlockState)((BlockState)this.defaultBlockState().setValue(DIRECTION, facingHorizontal)).setValue(TYPE_UPDOWN, Half.BOTTOM))
         .setValue(WATERLOGGED, fluid.getType() == Fluids.WATER);
      Direction facing = context.getClickedFace();
      return facing == Direction.DOWN || facing != Direction.UP && context.getClickLocation().y - blockpos.getY() > 0.5
         ? (BlockState)state2.setValue(TYPE_UPDOWN, Half.TOP)
         : state2;
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
            case SOUTH:
               return BOTTOM_SOUTH_SHAPE;
            case WEST:
               return BOTTOM_WEST_SHAPE;
            case EAST:
               return BOTTOM_EAST_SHAPE;
         }
      } else {
         switch ((Direction)state.getValue(DIRECTION)) {
            case NORTH:
            default:
               return TOP_NORTH_SHAPE;
            case SOUTH:
               return TOP_SOUTH_SHAPE;
            case WEST:
               return TOP_WEST_SHAPE;
            case EAST:
               return TOP_EAST_SHAPE;
         }
      }
   }
}
