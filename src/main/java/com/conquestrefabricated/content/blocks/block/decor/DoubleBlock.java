package com.conquestrefabricated.content.blocks.block.decor;

import com.conquestrefabricated.core.block.base.Shape;
import com.conquestrefabricated.core.block.builder.Props;
import java.util.List;
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
import net.minecraft.world.phys.shapes.VoxelShape;

public class DoubleBlock extends Shape {
   private final List<VoxelShape> hitBox;
   public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;

   public DoubleBlock(Props props) {
      super(props.toSettings());
      this.registerDefaultState((BlockState)((BlockState)this.stateDefinition.any()).setValue(HALF, DoubleBlockHalf.LOWER));
      this.hitBox = props.get("hitBox", List.class);
   }

   @Override
   public VoxelShape getShape(BlockState state) {
      return this.hitBox.get(0);
   }

   public boolean useShapeForLightOcclusion(BlockState state) {
      return true;
   }

   public BlockState getStateForPlacement(BlockPlaceContext context) {
      BlockPos blockpos = context.getClickedPos();
      Level level = context.getLevel();
      return blockpos.getY() < level.getMaxBuildHeight() - 1 && level.getBlockState(blockpos.above()).canBeReplaced(context) ? super.getStateForPlacement(context) : null;
   }

   public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos blockPos, BlockPos facingPos) {
      DoubleBlockHalf doubleblockhalf = (DoubleBlockHalf)state.getValue(HALF);
      if (facing.getAxis() != Axis.Y
         || doubleblockhalf == DoubleBlockHalf.LOWER != (facing == Direction.UP)
         || facingState.is(this) && facingState.getValue(HALF) != doubleblockhalf) {
         return doubleblockhalf == DoubleBlockHalf.LOWER && facing == Direction.DOWN && !state.canSurvive(level, blockPos)
            ? Blocks.AIR.defaultBlockState()
            : super.updateShape(state, facing, facingState, level, blockPos, facingPos);
      } else {
         return Blocks.AIR.defaultBlockState();
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

   public boolean canSurvive(BlockState state, LevelReader level, BlockPos blockPos) {
      if (state.getValue(HALF) != DoubleBlockHalf.UPPER) {
         return super.canSurvive(state, level, blockPos);
      }

      BlockState blockstate = level.getBlockState(blockPos.below());
      return state.getBlock() != this ? super.canSurvive(state, level, blockPos) : blockstate.is(this) && blockstate.getValue(HALF) == DoubleBlockHalf.LOWER;
   }

   public long getSeed(BlockState blockState, BlockPos pos) {
      return Mth.getSeed(pos.getX(), pos.below(blockState.getValue(HALF) == DoubleBlockHalf.LOWER ? 0 : 1).getY(), pos.getZ());
   }

   protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
      builder.add(new Property[]{HALF});
   }
}
