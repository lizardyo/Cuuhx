package com.conquestrefabricated.content.blocks.block.trees;

import com.conquestrefabricated.content.blocks.block.Layer;
import com.conquestrefabricated.content.blocks.block.Slab;
import com.conquestrefabricated.content.blocks.util.PlacementHelper;
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
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.shapes.VoxelShape;

@Assets(
   state = @State(name = "%s_stump", template = "parent_stump"),
   item = @Model(name = "item/%s_stump", parent = "block/%s_stump_4", template = "item/dragon_egg"),
   block = {
         @Model(name = "block/%s_stump_2", template = "block/parent_pillar_2"),
         @Model(name = "block/%s_stump_4", template = "block/parent_pillar_4"),
         @Model(name = "block/%s_stump_6", template = "block/parent_pillar_6"),
         @Model(name = "block/%s_stump_down_2", template = "block/parent_stump_down_2"),
         @Model(name = "block/%s_stump_down_4", template = "block/parent_stump_down_4"),
         @Model(name = "block/%s_stump_down_6", template = "block/parent_stump_down_6")
   }
)
public class StumpCypressDead extends StumpCypress {
   public static final DirectionProperty DIRECTION = BlockStateProperties.HORIZONTAL_FACING;
   public static final IntegerProperty LAYERS = IntegerProperty.create("layer", 1, 4);
   public static final BooleanProperty DOWN = BlockStateProperties.DOWN;
   protected static final VoxelShape[] SHAPE_CYPRESS = new VoxelShape[]{
      Block.box(6.0, 0.0, 6.0, 10.0, 16.0, 10.0),
      Block.box(5.0, 0.0, 5.0, 11.0, 16.0, 11.0),
      Block.box(4.0, 0.0, 4.0, 12.0, 16.0, 12.0),
      Block.box(2.0, 0.0, 2.0, 14.0, 16.0, 14.0)
   };
   private Block fullBlock;

   public StumpCypressDead(Props properties) {
      super(properties);
      this.fullBlock = properties.getParent().getBlock();
   }

   @Override
   public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn, BlockPos currentPos, BlockPos facingPos) {
      BlockState down = worldIn.getBlockState(currentPos.below());
      return !(down.getBlock() instanceof Layer) && (!(down.getBlock() instanceof Slab) || down.getValue(Slab.TYPE_UPDOWN) != Half.BOTTOM)
         ? super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos)
         : (BlockState)stateIn.setValue(DOWN, true);
   }

   public BlockState rotate(BlockState state, Rotation rot) {
      return (BlockState)state.setValue(DIRECTION, rot.rotate((Direction)state.getValue(DIRECTION)));
   }

   public BlockState mirror(BlockState state, Mirror mirrorIn) {
      return state.rotate(mirrorIn.getRotation((Direction)state.getValue(DIRECTION)));
   }

   @Override
   public VoxelShape getShape(BlockState state) {
      return SHAPE_CYPRESS[state.getValue(LAYERS) - 1];
   }

   @Override
   public boolean canBeReplaced(BlockState state, BlockPlaceContext context) {
      int i = (Integer)state.getValue(LAYERS);
      Direction facing = context.getClickedFace();
      if (context.getItemInHand().getItem() != this.asItem() || i > 5) {
         return false;
      } else {
         return !PlacementHelper.replacingClickedOnBlock(context) ? true : facing != Direction.UP && facing != Direction.DOWN;
      }
   }

   @Override
   public BlockState getStateForPlacement(BlockPlaceContext context) {
      BlockGetter iblockreader = context.getLevel();
      BlockPos blockpos = context.getClickedPos();
      Direction facing = context.getHorizontalDirection().getOpposite();
      BlockPos blockposDown = blockpos.below();
      BlockState blockstateDown = iblockreader.getBlockState(blockposDown);
      boolean isDown = false;
      BlockState blockstate = context.getLevel().getBlockState(context.getClickedPos());
      if (blockstateDown.getBlock() instanceof Layer || blockstateDown.getBlock() instanceof Slab && blockstateDown.getValue(Slab.TYPE_UPDOWN) == Half.BOTTOM) {
         isDown = true;
      }

      if (blockstate.getBlock() == this) {
         int i = (Integer)blockstate.getValue(LAYERS);
         return i == 4
            ? (BlockState)((BlockState)this.fullBlock.defaultBlockState().setValue(DIRECTION, facing)).setValue(Leaves.PERSISTENT, true)
            : (BlockState)blockstate.setValue(LAYERS, Math.min(4, i + 1));
      } else {
         return (BlockState)((BlockState)super.getStateForPlacement(context).setValue(DIRECTION, facing)).setValue(DOWN, isDown);
      }
   }

   @Override
   protected void addProperties(Builder<Block, BlockState> builder) {
      builder.add(new Property[]{LAYERS, DOWN, DIRECTION});
   }
}
