package com.conquestrefabricated.content.blocks.block.trees;

import com.conquestrefabricated.content.blocks.block.Layer;
import com.conquestrefabricated.content.blocks.block.Slab;
import com.conquestrefabricated.content.blocks.util.PlacementHelper;
import com.conquestrefabricated.core.asset.annotation.Assets;
import com.conquestrefabricated.core.asset.annotation.Model;
import com.conquestrefabricated.core.asset.annotation.State;
import com.conquestrefabricated.core.block.base.WaterloggedShape;
import com.conquestrefabricated.core.block.builder.Props;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.shapes.VoxelShape;

@Assets(
   state = @State(name = "%s_pillar", template = "parent_pillar"),
   item = @Model(name = "item/%s_pillar", parent = "block/%s_pillar_4", template = "item/dragon_egg"),
   block = {
         @Model(name = "block/%s_pillar_2", template = "block/parent_pillar_2"),
         @Model(name = "block/%s_pillar_4", template = "block/parent_pillar_4"),
         @Model(name = "block/%s_pillar_6", template = "block/parent_pillar_6")
   }
)
public class LogPillar extends WaterloggedShape {
   public static final BooleanProperty DOWN = BlockStateProperties.DOWN;
   public static final IntegerProperty LAYERS = IntegerProperty.create("layer", 1, 5);
   protected static final VoxelShape[] SHAPE = new VoxelShape[]{
      Block.box(6.0, 0.0, 6.0, 10.0, 16.0, 10.0),
      Block.box(4.0, 0.0, 4.0, 12.0, 16.0, 12.0),
      Block.box(2.0, 0.0, 2.0, 14.0, 16.0, 14.0),
      Block.box(5.0, 0.0, 5.0, 11.0, 16.0, 11.0),
      Block.box(7.0, 0.0, 7.0, 9.0, 16.0, 9.0)
   };
   private Block fullBlock;

   public LogPillar(Props properties) {
      super(properties.toSettings());
      this.registerDefaultState((BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue(WATERLOGGED, false)).setValue(DOWN, false));
      this.fullBlock = properties.getParent().getBlock();
   }

   @Override
   public VoxelShape getShape(BlockState state) {
      return SHAPE[state.getValue(LAYERS) - 1];
   }

   public boolean canBeReplaced(BlockState state, BlockPlaceContext context) {
      int i = (Integer)state.getValue(LAYERS);
      Direction facing = context.getClickedFace();
      if (context.getItemInHand().getItem() != this.asItem() || i > 5) {
         return false;
      } else {
         return !PlacementHelper.replacingClickedOnBlock(context) ? true : facing != Direction.UP && facing != Direction.DOWN;
      }
   }

   public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn, BlockPos currentPos, BlockPos facingPos) {
      BlockState down = worldIn.getBlockState(currentPos.below());
      return !(down.getBlock() instanceof Layer) && (!(down.getBlock() instanceof Slab) || down.getValue(Slab.TYPE_UPDOWN) != Half.BOTTOM)
         ? super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos)
         : (BlockState)stateIn.setValue(DOWN, true);
   }

   @Override
   public BlockState getStateForPlacement(BlockPlaceContext context) {
      BlockGetter iblockreader = context.getLevel();
      BlockPos blockpos = context.getClickedPos();
      BlockPos blockposDown = blockpos.below();
      BlockState blockstateDown = iblockreader.getBlockState(blockposDown);
      boolean isDown = false;
      BlockState blockstate = context.getLevel().getBlockState(context.getClickedPos());
      if (blockstateDown.getBlock() instanceof Layer || blockstateDown.getBlock() instanceof Slab && blockstateDown.getValue(Slab.TYPE_UPDOWN) == Half.BOTTOM) {
         isDown = true;
      }

      if (blockstate.getBlock() == this) {
         int i = (Integer)blockstate.getValue(LAYERS);
         if (i == 5) {
            return (BlockState)blockstate.setValue(LAYERS, 1);
         } else if (i == 1) {
            return (BlockState)blockstate.setValue(LAYERS, 4);
         } else if (i == 2) {
            return (BlockState)blockstate.setValue(LAYERS, 3);
         } else if (i == 4) {
            return (BlockState)blockstate.setValue(LAYERS, 2);
         } else {
            return i == 3 ? this.fullBlock.defaultBlockState() : (BlockState)blockstate.setValue(LAYERS, 3);
         }
      } else {
         return (BlockState)((BlockState)super.getStateForPlacement(context).setValue(DOWN, isDown)).setValue(LAYERS, 5);
      }
   }

   @Override
   protected void addProperties(Builder<Block, BlockState> builder) {
      builder.add(new Property[]{LAYERS, DOWN});
   }
}
