package com.conquestrefabricated.content.blocks.block.plants;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.core.Direction.Plane;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.MultifaceBlock;
import net.minecraft.world.level.block.VineBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.jetbrains.annotations.Nullable;

public class Vine extends VineBlock {
   public Vine(Properties properties) {
      super(properties);
   }

   public boolean canSurvive(BlockState state, LevelReader reader, BlockPos pos) {
      return true;
   }

   public float getMaxHorizontalOffset() {
      return 0.0F;
   }

   public float getMaxVerticalOffset() {
      return 0.0F;
   }

   @Nullable
   public BlockState getStateForPlacement(BlockPlaceContext ctx) {
      BlockState blockState = ctx.getLevel().getBlockState(ctx.getClickedPos());
      boolean bl = blockState.is(this);
      BlockState blockState2 = bl ? blockState : this.defaultBlockState();

      for (Direction direction : ctx.getNearestLookingDirections()) {
         if (direction != Direction.DOWN) {
            BooleanProperty booleanProperty = getPropertyForFace(direction);
            boolean bl2 = bl && (Boolean)blockState.getValue(booleanProperty);
            if (!bl2 && this.shouldHaveSide(ctx.getLevel(), ctx.getClickedPos(), direction)) {
               return (BlockState)blockState2.setValue(booleanProperty, true);
            }
         }
      }

      if (ctx.getLevel().getBlockState(ctx.getClickedPos().above()).isSolid() || ctx.getLevel().getBlockState(ctx.getClickedPos().above()).getBlock() instanceof VineBlock) {
         BooleanProperty booleanProperty = getPropertyForFace(ctx.getHorizontalDirection());
         return (BlockState)blockState2.setValue(booleanProperty, true);
      } else if (!ctx.getLevel().getGameRules().getBoolean(GameRules.RULE_DO_VINES_SPREAD)) {
         BooleanProperty booleanProperty = getPropertyForFace(ctx.getHorizontalDirection());
         return (BlockState)blockState2.setValue(booleanProperty, true);
      } else {
         return bl ? blockState2 : null;
      }
   }

   public void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
      if (world.getGameRules().getBoolean(GameRules.RULE_DO_VINES_SPREAD) && random.nextInt(4) == 0) {
         Direction direction = Direction.getRandom(random);
         BlockPos blockPos = pos.above();
         if (direction.getAxis().isHorizontal() && !(Boolean)state.getValue(getPropertyForFace(direction))) {
            if (this.canGrowAt(world, pos)) {
               BlockPos blockPos2 = pos.relative(direction);
               BlockState blockState = world.getBlockState(blockPos2);
               if (blockState.isAir()) {
                  Direction direction2 = direction.getClockWise();
                  Direction direction3 = direction.getCounterClockWise();
                  boolean bl = (Boolean)state.getValue(getPropertyForFace(direction2));
                  boolean bl2 = (Boolean)state.getValue(getPropertyForFace(direction3));
                  BlockPos blockPos3 = blockPos2.relative(direction2);
                  BlockPos blockPos4 = blockPos2.relative(direction3);
                  if (bl && shouldConnectTo(world, blockPos3, direction2)) {
                     world.setBlock(blockPos2, (BlockState)this.defaultBlockState().setValue(getPropertyForFace(direction2), true), 2);
                  } else if (bl2 && shouldConnectTo(world, blockPos4, direction3)) {
                     world.setBlock(blockPos2, (BlockState)this.defaultBlockState().setValue(getPropertyForFace(direction3), true), 2);
                  } else {
                     Direction direction4 = direction.getOpposite();
                     if (bl && world.isEmptyBlock(blockPos3) && shouldConnectTo(world, pos.relative(direction2), direction4)) {
                        world.setBlock(blockPos3, (BlockState)this.defaultBlockState().setValue(getPropertyForFace(direction4), true), 2);
                     } else if (bl2 && world.isEmptyBlock(blockPos4) && shouldConnectTo(world, pos.relative(direction3), direction4)) {
                        world.setBlock(blockPos4, (BlockState)this.defaultBlockState().setValue(getPropertyForFace(direction4), true), 2);
                     } else if (random.nextFloat() < 0.05 && shouldConnectTo(world, blockPos2.above(), Direction.UP)) {
                        world.setBlock(blockPos2, (BlockState)this.defaultBlockState().setValue(UP, true), 2);
                     }
                  }
               } else if (shouldConnectTo(world, blockPos2, direction)) {
                  world.setBlock(pos, (BlockState)state.setValue(getPropertyForFace(direction), true), 2);
               }
            }
         } else {
            if (direction == Direction.UP && pos.getY() < world.getMaxBuildHeight() - 1) {
               if (this.shouldHaveSide(world, pos, direction)) {
                  world.setBlock(pos, (BlockState)state.setValue(UP, true), 2);
                  return;
               }

               if (world.isEmptyBlock(blockPos)) {
                  if (!this.canGrowAt(world, pos)) {
                     return;
                  }

                  BlockState blockState2 = state;

                  for (Direction direction2 : Plane.HORIZONTAL) {
                     if (random.nextBoolean() || !shouldConnectTo(world, blockPos.relative(direction2), direction2)) {
                        blockState2 = (BlockState)blockState2.setValue(getPropertyForFace(direction2), false);
                     }
                  }

                  if (this.hasHorizontalSide(blockState2)) {
                     world.setBlock(blockPos, blockState2, 2);
                  }

                  return;
               }
            }

            if (pos.getY() > world.getMinBuildHeight()) {
               BlockPos blockPos2 = pos.below();
               BlockState blockState = world.getBlockState(blockPos2);
               if (blockState.isAir() || blockState.is(this)) {
                  BlockState blockState3 = blockState.isAir() ? this.defaultBlockState() : blockState;
                  BlockState blockState4 = this.getGrownState(state, blockState3, random);
                  if (blockState3 != blockState4 && this.hasHorizontalSide(blockState4)) {
                     world.setBlock(blockPos2, blockState4, 2);
                  }
               }
            }
         }
      }
   }

   private BlockState getGrownState(BlockState above, BlockState state, RandomSource random) {
      for (Direction direction : Plane.HORIZONTAL) {
         if (random.nextBoolean()) {
            BooleanProperty booleanProperty = getPropertyForFace(direction);
            if ((Boolean)above.getValue(booleanProperty)) {
               state = (BlockState)state.setValue(booleanProperty, true);
            }
         }
      }

      return state;
   }

   private boolean hasHorizontalSide(BlockState state) {
      return (Boolean)state.getValue(NORTH) || (Boolean)state.getValue(EAST) || (Boolean)state.getValue(SOUTH) || (Boolean)state.getValue(WEST);
   }

   private boolean shouldHaveSide(BlockGetter world, BlockPos pos, Direction side) {
      if (side == Direction.DOWN) {
         return false;
      }

      BlockPos blockPos = pos.relative(side);
      if (shouldConnectTo(world, blockPos, side)) {
         return true;
      }

      if (side.getAxis() == Axis.Y) {
         return false;
      }

      BooleanProperty booleanProperty = (BooleanProperty)PROPERTY_BY_DIRECTION.get(side);
      BlockState blockState = world.getBlockState(pos.above());
      return blockState.getBlock() instanceof VineBlock && (Boolean)blockState.getValue(booleanProperty) || blockState.isSolid();
   }

   public static boolean shouldConnectTo(BlockGetter world, BlockPos pos, Direction direction) {
      return MultifaceBlock.canAttachTo(world, direction, pos, world.getBlockState(pos)) || Block.canSupportRigidBlock(world, pos);
   }

   public boolean canBeReplaced(BlockState state, BlockPlaceContext context) {
      BlockState blockState = context.getLevel().getBlockState(context.getClickedPos());
      if (blockState.is(this)) {
         return context.getClickedFace() != Direction.UP && context.getClickedFace() != Direction.DOWN ? this.getAdjacentBlockCount(blockState) < PROPERTY_BY_DIRECTION.size() : false;
      } else {
         return super.canBeReplaced(state, context);
      }
   }

   public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor world, BlockPos pos, BlockPos neighborPos) {
      if (direction == Direction.DOWN) {
         return super.updateShape(state, direction, neighborState, world, pos, neighborPos);
      }

      BlockState blockState = this.getPlacementShape(state, world, pos);
      return !this.hasAdjacentBlocks(blockState)
            && !world.getBlockState(pos.above()).isSolid()
            && !(world.getBlockState(pos.above()).getBlock() instanceof VineBlock)
         ? Blocks.AIR.defaultBlockState()
         : blockState;
   }

   private BlockState getPlacementShape(BlockState state, BlockGetter world, BlockPos pos) {
      BlockPos blockPos = pos.above();
      if ((Boolean)state.getValue(UP)) {
         state = (BlockState)state.setValue(UP, shouldConnectTo(world, blockPos, Direction.DOWN));
      }

      BlockState blockState = null;

      for (Direction direction : Plane.HORIZONTAL) {
         BooleanProperty booleanProperty = getPropertyForFace(direction);
         if ((Boolean)state.getValue(booleanProperty)) {
            boolean bl = this.shouldHaveSide(world, pos, direction);
            if (!bl) {
               if (blockState == null) {
                  blockState = world.getBlockState(blockPos);
               }

               bl = blockState.is(this) && (Boolean)blockState.getValue(booleanProperty);
            }

            state = (BlockState)state.setValue(booleanProperty, bl);
         }
      }

      return state;
   }

   private boolean hasAdjacentBlocks(BlockState state) {
      return this.getAdjacentBlockCount(state) > 0;
   }

   private int getAdjacentBlockCount(BlockState state) {
      int i = 0;

      for (BooleanProperty booleanProperty : PROPERTY_BY_DIRECTION.values()) {
         if ((Boolean)state.getValue(booleanProperty)) {
            i++;
         }
      }

      return i;
   }

   private boolean canGrowAt(BlockGetter world, BlockPos pos) {
      if (world.getBlockState(pos.above()).isSolid()) {
         return true;
      }

      int i = 4;
      Iterable<BlockPos> iterable = BlockPos.betweenClosed(
         pos.getX() - 4, pos.getY() - 1, pos.getZ() - 4, pos.getX() + 4, pos.getY() + 1, pos.getZ() + 4
      );
      int j = 5;

      for (BlockPos blockPos : iterable) {
         if (world.getBlockState(blockPos).is(this)) {
            if (--j <= 0) {
               return false;
            }
         }
      }

      return true;
   }
}
