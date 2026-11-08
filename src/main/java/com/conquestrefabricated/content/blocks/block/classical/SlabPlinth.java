package com.conquestrefabricated.content.blocks.block.classical;

import com.conquestrefabricated.core.asset.annotation.Assets;
import com.conquestrefabricated.core.asset.annotation.Model;
import com.conquestrefabricated.core.asset.annotation.State;
import com.conquestrefabricated.core.block.base.WaterloggedShape;
import java.util.Map;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Plane;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.PipeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.VoxelShape;

@Assets(
   state = @State(name = "%s_slab", template = "parent_doric_base_vertical_slab"),
   item = @Model(name = "item/%s_vertical_slab", parent = "block/%s_vertical_slab", template = "item/parent_vertical_slab"),
   block = @Model(name = "block/%s_vertical_slab", template = "block/parent_doric_base_vertical_slab")
)
public class SlabPlinth extends WaterloggedShape {
   public static final BooleanProperty NORTH = PipeBlock.NORTH;
   public static final BooleanProperty EAST = PipeBlock.EAST;
   public static final BooleanProperty SOUTH = PipeBlock.SOUTH;
   public static final BooleanProperty WEST = PipeBlock.WEST;
   protected static final Map<Direction, BooleanProperty> PROPERTY_BY_DIRECTION = PipeBlock.PROPERTY_BY_DIRECTION
      .entrySet()
      .stream()
      .filter(p_52346_ -> ((Direction)p_52346_.getKey()).getAxis().isHorizontal())
      .collect(Util.toMap());
   private static final VoxelShape BOTTOM_SHAPE = Block.box(0.1, 0.1, 0.1, 15.9, 8.0, 15.9);
   private static final VoxelShape TOP_SHAPE = Block.box(0.1, 8.0, 0.1, 15.9, 15.9, 15.9);
   public static final EnumProperty<Half> TYPE_UPDOWN = EnumProperty.create("type", Half.class);

   public SlabPlinth(Properties properties) {
      super(properties);
      this.registerDefaultState((BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue(TYPE_UPDOWN, Half.BOTTOM)).setValue(WATERLOGGED, false));
   }

   public boolean propagatesSkylightDown(BlockState p_200123_1_, BlockGetter p_200123_2_, BlockPos p_200123_3_) {
      return false;
   }

   @Override
   public VoxelShape getShape(BlockState state) {
      return state.getValue(TYPE_UPDOWN) == Half.TOP ? TOP_SHAPE : BOTTOM_SHAPE;
   }

   public boolean func_220111_a(BlockState state, BlockState stateOriginal) {
      Block block = state.getBlock();
      return block instanceof CubeCapital || block instanceof SlabPlinth && state.getValue(TYPE_UPDOWN) == stateOriginal.getValue(TYPE_UPDOWN);
   }

   public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor world, BlockPos currentPos, BlockPos facingPos) {
      return facing.getAxis().getPlane() == Plane.HORIZONTAL
         ? (BlockState)stateIn.setValue((Property)PROPERTY_BY_DIRECTION.get(facing), this.func_220111_a(facingState, stateIn))
         : super.updateShape(stateIn, facing, facingState, world, currentPos, facingPos);
   }

   @Override
   public BlockState getStateForPlacement(BlockPlaceContext context) {
      BlockGetter iblockreader = context.getLevel();
      BlockPos blockpos = context.getClickedPos();
      BlockPos blockpos1 = blockpos.north();
      BlockPos blockpos2 = blockpos.east();
      BlockPos blockpos3 = blockpos.south();
      BlockPos blockpos4 = blockpos.west();
      BlockState blockstate = iblockreader.getBlockState(blockpos1);
      BlockState blockstate1 = iblockreader.getBlockState(blockpos2);
      BlockState blockstate2 = iblockreader.getBlockState(blockpos3);
      BlockState blockstate3 = iblockreader.getBlockState(blockpos4);
      FluidState fluid = context.getLevel().getFluidState(context.getClickedPos());
      BlockState state2 = (BlockState)((BlockState)this.defaultBlockState().setValue(TYPE_UPDOWN, Half.BOTTOM))
         .setValue(WATERLOGGED, fluid.getType() == Fluids.WATER);
      Direction facing = context.getClickedFace();
      BlockState halfState = facing == Direction.DOWN || facing != Direction.UP && context.getClickLocation().y - blockpos.getY() > 0.5
         ? (BlockState)state2.setValue(TYPE_UPDOWN, Half.TOP)
         : state2;
      return (BlockState)((BlockState)((BlockState)((BlockState)halfState.setValue(NORTH, this.func_220111_a(blockstate, halfState)))
               .setValue(EAST, this.func_220111_a(blockstate1, halfState)))
            .setValue(SOUTH, this.func_220111_a(blockstate2, halfState)))
         .setValue(WEST, this.func_220111_a(blockstate3, halfState));
   }

   @Override
   protected void addProperties(Builder<Block, BlockState> container) {
      container.add(new Property[]{TYPE_UPDOWN})
         .add(new Property[]{NORTH})
         .add(new Property[]{WEST})
         .add(new Property[]{SOUTH})
         .add(new Property[]{EAST});
   }
}
