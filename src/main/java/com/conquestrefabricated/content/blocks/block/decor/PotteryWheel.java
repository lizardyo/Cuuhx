package com.conquestrefabricated.content.blocks.block.decor;

import com.conquestrefabricated.content.blocks.BlockSettingsAccessor;
import com.conquestrefabricated.content.blocks.CustomOffsetType;
import com.conquestrefabricated.content.blocks.util.PlacementHelper;
import com.conquestrefabricated.core.asset.annotation.SpecialOffset;
import com.conquestrefabricated.core.block.builder.SpecialOffsetType;
import com.conquestrefabricated.core.block.properties.ModBlockProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.OffsetType;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

@SpecialOffset(offsetType = SpecialOffsetType.XYZ)
public class PotteryWheel extends HorizontalDirectionalBlock {
   public static final BooleanProperty OFFSET_TOGGLE = ModBlockProperties.OFFSET_TOGGLE;
   public static final EnumProperty<BedPart> PART = BlockStateProperties.BED_PART;
   protected static final VoxelShape BASE_X_AXIS = Block.box(0.0, 0.0, 1.0, 16.0, 4.0, 15.0);
   protected static final VoxelShape BASE_Z_AXIS = Block.box(1.0, 0.0, 0.0, 15.0, 4.0, 16.0);
   protected static final VoxelShape MIDDLE_X_AXIS = Block.box(0.0, 4.0, 2.0, 16.0, 10.0, 14.0);
   protected static final VoxelShape MIDDLE_Z_AXIS = Block.box(2.0, 4.0, 0.0, 14.0, 10.0, 16.0);
   protected static final VoxelShape TOP_X_AXIS = Block.box(0.0, 10.0, 4.0, 16.0, 16.0, 12.0);
   protected static final VoxelShape TOP_Z_AXIS = Block.box(4.0, 10.0, 0.0, 12.0, 16.0, 16.0);
   protected static final VoxelShape X_AXIS_AABB = Shapes.or(BASE_X_AXIS, new VoxelShape[]{MIDDLE_X_AXIS, TOP_X_AXIS});
   protected static final VoxelShape Z_AXIS_AABB = Shapes.or(BASE_Z_AXIS, new VoxelShape[]{MIDDLE_Z_AXIS, TOP_Z_AXIS});

   public PotteryWheel(Properties properties) {
      super(((BlockSettingsAccessor)properties).setCustomOffsetter(CustomOffsetType.LAYER_XYZ).offsetType(OffsetType.NONE).dynamicShape());
      this.registerDefaultState((BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue(PART, BedPart.FOOT)).setValue(OFFSET_TOGGLE, false));
   }

   public VoxelShape getShape(BlockState state, BlockGetter blockGetter, BlockPos blockPos, CollisionContext context) {
      Direction direction = (Direction)state.getValue(FACING);
      switch (direction.getAxis()) {
         case Z:
         default:
            return Z_AXIS_AABB;
         case X:
            return X_AXIS_AABB;
      }
   }

   public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
      return state.getValue(OFFSET_TOGGLE) ? Shapes.empty() : super.getCollisionShape(state, worldIn, pos, context);
   }

   public BlockState getStateForPlacement(BlockPlaceContext context) {
      boolean isSlab = PlacementHelper.isFacingSlab(context);
      Direction direction = context.getHorizontalDirection();
      BlockPos blockpos = context.getClickedPos();
      BlockPos blockpos1 = blockpos.relative(direction);
      Level level = context.getLevel();
      return level.getBlockState(blockpos1).canBeReplaced(context) && level.getWorldBorder().isWithinBounds(blockpos1)
         ? (BlockState)((BlockState)this.defaultBlockState().setValue(FACING, direction)).setValue(OFFSET_TOGGLE, isSlab)
         : null;
   }

   public BlockState updateShape(
      BlockState blockState, Direction direction, BlockState facingState, LevelAccessor levelAccessor, BlockPos currentPos, BlockPos facingPos
   ) {
      if (direction != getNeighbourDirection((BedPart)blockState.getValue(PART), (Direction)blockState.getValue(FACING))) {
         return super.updateShape(blockState, direction, facingState, levelAccessor, currentPos, facingPos);
      } else {
         return facingState.is(this) && facingState.getValue(PART) != blockState.getValue(PART)
            ? super.updateShape(blockState, direction, facingState, levelAccessor, currentPos, facingPos)
            : Blocks.AIR.defaultBlockState();
      }
   }

   public boolean isPathfindable(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, PathComputationType type) {
      return false;
   }

   private static Direction getNeighbourDirection(BedPart part, Direction direction) {
      return part == BedPart.FOOT ? direction : direction.getOpposite();
   }

   public void playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
      if (!level.isClientSide && player.isCreative()) {
         BedPart bedpart = (BedPart)state.getValue(PART);
         if (bedpart == BedPart.FOOT) {
            BlockPos blockpos = pos.relative(getNeighbourDirection(bedpart, (Direction)state.getValue(FACING)));
            BlockState blockstate = level.getBlockState(blockpos);
            if (blockstate.is(this) && blockstate.getValue(PART) == BedPart.HEAD) {
               level.setBlock(blockpos, Blocks.AIR.defaultBlockState(), 35);
               level.levelEvent(player, 2001, blockpos, Block.getId(blockstate));
            }
         }
      }

      super.playerWillDestroy(level, pos, state, player);
   }

   public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity entity, ItemStack itemStack) {
      super.setPlacedBy(level, pos, state, entity, itemStack);
      if (!level.isClientSide) {
         BlockPos blockpos = pos.relative((Direction)state.getValue(FACING));
         level.setBlock(blockpos, (BlockState)state.setValue(PART, BedPart.HEAD), 3);
         level.blockUpdated(pos, Blocks.AIR);
         state.updateNeighbourShapes(level, pos, 3);
      }
   }

   public long getSeed(BlockState state, BlockPos pos) {
      BlockPos blockpos = pos.relative((Direction)state.getValue(FACING), state.getValue(PART) == BedPart.HEAD ? 0 : 1);
      return Mth.getSeed(blockpos.getX(), pos.getY(), blockpos.getZ());
   }

   protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
      builder.add(new Property[]{FACING, PART, OFFSET_TOGGLE});
   }
}
