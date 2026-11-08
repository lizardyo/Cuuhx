package com.conquestrefabricated.content.blocks.block.decor;

import com.conquestrefabricated.content.blocks.block.directional.HorizontalDirectional;
import com.conquestrefabricated.core.block.builder.Props;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Property;

public class DoubleHorizontalDirectional extends HorizontalDirectional {
   public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;

   public DoubleHorizontalDirectional(Props properties) {
      super(properties);
      this.registerDefaultState((BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue(DIRECTION, Direction.NORTH)).setValue(HALF, DoubleBlockHalf.LOWER));
   }

   @Override
   public BlockState getStateForPlacement(BlockPlaceContext context) {
      BlockPos blockpos = context.getClickedPos();
      Level level = context.getLevel();
      return blockpos.getY() < level.getMaxBuildHeight() - 1 && level.getBlockState(blockpos.above()).canBeReplaced(context)
         ? (BlockState)((BlockState)this.defaultBlockState().setValue(DIRECTION, context.getHorizontalDirection().getOpposite())).setValue(HALF, DoubleBlockHalf.LOWER)
         : null;
   }

   public BlockState updateShape(BlockState blockState, Direction direction, BlockState state, LevelAccessor levelAccessor, BlockPos blockPos, BlockPos facing) {
      DoubleBlockHalf doubleblockhalf = (DoubleBlockHalf)blockState.getValue(HALF);
      if (direction.getAxis() == Axis.Y && doubleblockhalf == DoubleBlockHalf.LOWER == (direction == Direction.UP)) {
         return state.is(this) && state.getValue(HALF) != doubleblockhalf
            ? (BlockState)blockState.setValue(DIRECTION, (Direction)state.getValue(DIRECTION))
            : Blocks.AIR.defaultBlockState();
      } else {
         return doubleblockhalf == DoubleBlockHalf.LOWER && direction == Direction.DOWN && !blockState.canSurvive(levelAccessor, blockPos)
            ? Blocks.AIR.defaultBlockState()
            : super.updateShape(blockState, direction, state, levelAccessor, blockPos, facing);
      }
   }

   protected static void preventCreativeDropFromBottomPart(Level level, BlockPos pos, BlockState state, Player player) {
      DoubleBlockHalf doubleblockhalf = (DoubleBlockHalf)state.getValue(HALF);
      if (doubleblockhalf == DoubleBlockHalf.UPPER) {
         BlockPos blockpos = pos.below();
         BlockState blockstate = level.getBlockState(blockpos);
         if (blockstate.is(state.getBlock()) && blockstate.getValue(HALF) == DoubleBlockHalf.LOWER) {
            BlockState blockstate1 = blockstate.hasProperty(BlockStateProperties.WATERLOGGED) && blockstate.getValue(BlockStateProperties.WATERLOGGED)
               ? Blocks.WATER.defaultBlockState()
               : Blocks.AIR.defaultBlockState();
            level.setBlock(blockpos, blockstate1, 35);
            level.levelEvent(player, 2001, blockpos, Block.getId(blockstate));
         }
      }
   }

   public void playerWillDestroy(Level level, BlockPos pos, BlockState blockState, Player player) {
      if (!level.isClientSide && player.isCreative()) {
         preventCreativeDropFromBottomPart(level, pos, blockState, player);
      }

      super.playerWillDestroy(level, pos, blockState, player);
   }

   public void setPlacedBy(Level level, BlockPos pos, BlockState blockState, LivingEntity livingEntity, ItemStack itemStack) {
      level.setBlock(pos.above(), (BlockState)blockState.setValue(HALF, DoubleBlockHalf.UPPER), 3);
   }

   public boolean canSurvive(BlockState state, LevelReader levelReader, BlockPos pos) {
      BlockPos blockpos = pos.below();
      BlockState blockstate = levelReader.getBlockState(blockpos);
      return state.getValue(HALF) == DoubleBlockHalf.UPPER ? blockstate.is(this) : super.canSurvive(state, levelReader, pos);
   }

   public long getSeed(BlockState blockState, BlockPos pos) {
      return Mth.getSeed(pos.getX(), pos.below(blockState.getValue(HALF) == DoubleBlockHalf.LOWER ? 0 : 1).getY(), pos.getZ());
   }

   @Override
   protected void addProperties(Builder<Block, BlockState> builder) {
      builder.add(new Property[]{HALF});
   }
}
