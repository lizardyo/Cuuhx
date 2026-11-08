package com.conquestrefabricated.content.blocks.block.decor;

import com.conquestrefabricated.content.blocks.BlockVoxelShapes;
import com.conquestrefabricated.content.blocks.CustomOffsetType;
import com.conquestrefabricated.content.blocks.util.CauldronBehavior;
import com.conquestrefabricated.core.asset.annotation.SpecialOffset;
import com.conquestrefabricated.core.block.builder.Props;
import com.conquestrefabricated.core.block.builder.SpecialOffsetType;
import com.conquestrefabricated.core.block.properties.ModBlockProperties;
import java.util.List;
import java.util.stream.Stream;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.biome.Biome.Precipitation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.OffsetType;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

@SpecialOffset(offsetType = SpecialOffsetType.XYZ)
public class Bathtub extends HorizontalDirectionalBlock {
   public static final BooleanProperty OFFSET_TOGGLE = ModBlockProperties.OFFSET_TOGGLE;
   public static final EnumProperty<BedPart> PART = BlockStateProperties.BED_PART;
   public static final List<VoxelShape> tubShapes = BlockVoxelShapes.makeNSEWShapes(
      Stream.of(
            Block.box(0.0, 0.0, 0.0, 16.0, 5.0, 16.0),
            Block.box(0.0, 5.0, 0.0, 2.0, 15.0, 16.0),
            Block.box(14.0, 5.0, 0.0, 16.0, 15.0, 16.0),
            Block.box(2.0, 5.0, 14.0, 14.0, 15.0, 16.0)
         )
         .reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR))
         .get()
   );
   private final CauldronBehavior behavior = new CauldronBehavior(tubShapes);

   public Bathtub(Props props) {
      super(props.customOffsetType(CustomOffsetType.LAYER_XYZ).offset(OffsetType.NONE).dynamicBounds(true).toSettings());
      this.registerDefaultState((BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue(PART, BedPart.FOOT)).setValue(OFFSET_TOGGLE, false));
   }

   public VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
      if (state.getValue(PART) == BedPart.FOOT) {
         switch ((Direction)state.getValue(FACING)) {
            case NORTH:
               return tubShapes.get(0);
            case EAST:
               return tubShapes.get(1);
            case SOUTH:
               tubShapes.get(2);
            case WEST:
               tubShapes.get(3);
         }
      } else {
         switch ((Direction)state.getValue(FACING)) {
            case NORTH:
               return tubShapes.get(2);
            case EAST:
               return tubShapes.get(3);
            case SOUTH:
               tubShapes.get(0);
            case WEST:
               tubShapes.get(1);
         }
      }

      return super.getCollisionShape(state, world, pos, context);
   }

   public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
      if (state.getValue(PART) == BedPart.FOOT) {
         switch ((Direction)state.getValue(FACING)) {
            case NORTH:
               return tubShapes.get(0);
            case EAST:
               return tubShapes.get(1);
            case SOUTH:
               tubShapes.get(2);
            case WEST:
               tubShapes.get(3);
         }
      } else {
         switch ((Direction)state.getValue(FACING)) {
            case NORTH:
               return tubShapes.get(2);
            case EAST:
               return tubShapes.get(3);
            case SOUTH:
               tubShapes.get(0);
            case WEST:
               tubShapes.get(1);
         }
      }

      return super.getShape(state, world, pos, context);
   }

   @Nullable
   private BlockPos getOtherHalfPos(BlockState state, BlockPos pos) {
      Direction dir = getNeighbourDirection((BedPart)state.getValue(PART), (Direction)state.getValue(FACING));
      return pos.relative(dir);
   }

   private void syncLevelToOtherHalf(Level world, BlockPos pos, BlockState state) {
      if (!world.isClientSide) {
         BlockPos otherPos = this.getOtherHalfPos(state, pos);
         if (otherPos != null) {
            BlockState otherState = world.getBlockState(otherPos);
            if (otherState.is(this)) {
               if (otherState.getValue(PART) != state.getValue(PART)) {
                  int currentLevel = (Integer)state.getValue(CauldronBehavior.LEVEL);
                  if ((Integer)otherState.getValue(CauldronBehavior.LEVEL) != currentLevel) {
                     world.setBlock(otherPos, (BlockState)otherState.setValue(CauldronBehavior.LEVEL, currentLevel), 3);
                  }
               }
            }
         }
      }
   }

   public InteractionResult use(BlockState state, Level level, BlockPos blockPos, Player player, InteractionHand hand, BlockHitResult hitResult) {
      InteractionResult result = this.behavior.onUse(state, level, blockPos, player, hand, hitResult);
      if (result != InteractionResult.PASS) {
         this.syncLevelToOtherHalf(level, blockPos, level.getBlockState(blockPos));
      }

      return result;
   }

   public void entityInside(BlockState state, Level world, BlockPos pos, Entity entity) {
      this.behavior.onEntityCollision(state, world, pos, entity);
      this.syncLevelToOtherHalf(world, pos, world.getBlockState(pos));
   }

   public void handlePrecipitation(BlockState state, Level world, BlockPos pos, Precipitation precipitation) {
      this.behavior.precipitationTick(state, world, pos, precipitation);
      this.syncLevelToOtherHalf(world, pos, world.getBlockState(pos));
   }

   public BlockState updateShape(
      BlockState blockState, Direction direction, BlockState facingState, LevelAccessor levelAccessor, BlockPos currentPos, BlockPos facingPos
   ) {
      BlockState updated = this.super_getStateForNeighborUpdate(blockState, direction, facingState, levelAccessor, currentPos, facingPos);
      Direction partnerDir = getNeighbourDirection((BedPart)blockState.getValue(PART), (Direction)blockState.getValue(FACING));
      if (direction == partnerDir && facingState.is(this) && facingState.getValue(PART) != blockState.getValue(PART)) {
         int partnerLevel = (Integer)facingState.getValue(CauldronBehavior.LEVEL);
         if ((Integer)updated.getValue(CauldronBehavior.LEVEL) != partnerLevel) {
            updated = (BlockState)updated.setValue(CauldronBehavior.LEVEL, partnerLevel);
         }
      }

      return updated;
   }

   private BlockState super_getStateForNeighborUpdate(
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
      builder.add(new Property[]{FACING, PART, OFFSET_TOGGLE, CauldronBehavior.LEVEL});
   }

   public boolean hasAnalogOutputSignal(BlockState state) {
      return this.behavior.hasComparatorOutput(state);
   }

   public int getAnalogOutputSignal(BlockState state, Level world, BlockPos pos) {
      return this.behavior.getComparatorOutput(state, world, pos);
   }
}
