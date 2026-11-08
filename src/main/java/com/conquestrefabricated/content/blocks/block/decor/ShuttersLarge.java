package com.conquestrefabricated.content.blocks.block.decor;

import com.conquestrefabricated.content.blocks.BlockVoxelShapes;
import com.conquestrefabricated.content.blocks.block.VerticalSlab;
import com.conquestrefabricated.content.blocks.block.directional.HorizontalDirectional;
import com.conquestrefabricated.core.block.base.WaterloggedHorizontalDirectionalShape;
import com.conquestrefabricated.core.block.builder.Props;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ShuttersLarge extends WaterloggedHorizontalDirectionalShape {
   public static final EnumProperty<DoorHingeSide> HINGE = BlockStateProperties.DOOR_HINGE;

   public ShuttersLarge(Props props) {
      super(props.toSettings());
   }

   @Override
   public VoxelShape getShape(BlockState state) {
      if ((Integer)state.getValue(HorizontalDirectional.Waterlogged.Toggle3.TOGGLE) == 1) {
         switch ((Direction)state.getValue(DIRECTION)) {
            case SOUTH:
            default:
               return VerticalSlab.NORTH_SHAPE[0];
            case NORTH:
               return VerticalSlab.SOUTH_SHAPE[0];
            case EAST:
               return VerticalSlab.WEST_SHAPE[0];
            case WEST:
               return VerticalSlab.EAST_SHAPE[0];
         }
      } else {
         if ((Integer)state.getValue(HorizontalDirectional.Waterlogged.Toggle3.TOGGLE) == 2) {
            return BlockVoxelShapes.cubeMediumLargePartialShape.get(0);
         }

         switch ((Direction)state.getValue(DIRECTION)) {
            case SOUTH:
            default:
               return state.getValue(HINGE) == DoorHingeSide.LEFT ? VerticalSlab.EAST_SHAPE[0] : VerticalSlab.WEST_SHAPE[0];
            case NORTH:
               return state.getValue(HINGE) == DoorHingeSide.LEFT ? VerticalSlab.WEST_SHAPE[0] : VerticalSlab.EAST_SHAPE[0];
            case EAST:
               return state.getValue(HINGE) == DoorHingeSide.LEFT ? VerticalSlab.NORTH_SHAPE[0] : VerticalSlab.SOUTH_SHAPE[0];
            case WEST:
               return state.getValue(HINGE) == DoorHingeSide.LEFT ? VerticalSlab.SOUTH_SHAPE[0] : VerticalSlab.NORTH_SHAPE[0];
         }
      }
   }

   @Override
   public BlockState getStateForPlacement(BlockPlaceContext context) {
      return (BlockState)((BlockState)((BlockState)super.getStateForPlacement(context).setValue(HorizontalDirectional.Waterlogged.Toggle3.TOGGLE, 1))
            .setValue(DIRECTION, context.getHorizontalDirection()))
         .setValue(HINGE, this.getHingeSide(context));
   }

   @Override
   public VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
      return state.getValue(HorizontalDirectional.Waterlogged.Toggle3.TOGGLE) == 2 ? Shapes.empty() : this.getShape(state);
   }

   private DoorHingeSide getHingeSide(BlockPlaceContext p_208073_1_) {
      BlockGetter iblockreader = p_208073_1_.getLevel();
      BlockPos blockpos = p_208073_1_.getClickedPos();
      Direction direction = p_208073_1_.getHorizontalDirection();
      BlockPos blockpos1 = blockpos.above();
      Direction direction1 = direction.getCounterClockWise();
      BlockPos blockpos2 = blockpos.relative(direction1);
      BlockState blockstate = iblockreader.getBlockState(blockpos2);
      BlockPos blockpos3 = blockpos1.relative(direction1);
      BlockState blockstate1 = iblockreader.getBlockState(blockpos3);
      Direction direction2 = direction.getClockWise();
      BlockPos blockpos4 = blockpos.relative(direction2);
      BlockState blockstate2 = iblockreader.getBlockState(blockpos4);
      BlockPos blockpos5 = blockpos1.relative(direction2);
      BlockState blockstate3 = iblockreader.getBlockState(blockpos5);
      int i = (blockstate.isCollisionShapeFullBlock(iblockreader, blockpos2) ? -1 : 0)
         + (blockstate1.isCollisionShapeFullBlock(iblockreader, blockpos3) ? -1 : 0)
         + (blockstate2.isCollisionShapeFullBlock(iblockreader, blockpos4) ? 1 : 0)
         + (blockstate3.isCollisionShapeFullBlock(iblockreader, blockpos5) ? 1 : 0);
      if (i <= 0) {
         if (i < 0) {
            return DoorHingeSide.RIGHT;
         }

         int j = direction.getStepX();
         int k = direction.getStepZ();
         Vec3 vec3d = p_208073_1_.getClickLocation();
         double d0 = vec3d.x - blockpos.getX();
         double d1 = vec3d.z - blockpos.getZ();
         return j < 0 && d1 < 0.5 || j > 0 && d1 > 0.5 || k < 0 && d0 > 0.5 || k > 0 && d0 < 0.5 ? DoorHingeSide.LEFT : DoorHingeSide.RIGHT;
      } else {
         return DoorHingeSide.LEFT;
      }
   }

   @Override
   protected void addProperties(Builder<Block, BlockState> builder) {
      builder.add(new Property[]{HINGE}).add(new Property[]{HorizontalDirectional.Waterlogged.Toggle3.TOGGLE});
   }

   public InteractionResult use(BlockState state, Level level, BlockPos blockPos, Player player, InteractionHand hand, BlockHitResult hitResult) {
      if (!player.getAbilities().mayBuild) {
         return InteractionResult.FAIL;
      }

      level.setBlock(blockPos, (BlockState)state.cycle(HorizontalDirectional.Waterlogged.Toggle3.TOGGLE), 3);
      return InteractionResult.SUCCESS;
   }

   public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn, BlockPos currentPos, BlockPos facingPos) {
      if (facingState.getBlock() instanceof ShuttersLarge && (facing == Direction.UP || facing == Direction.DOWN)) {
         return facingState.getValue(HINGE) == stateIn.getValue(HINGE)
            ? (BlockState)stateIn.setValue(
               HorizontalDirectional.Waterlogged.Toggle3.TOGGLE, (Integer)facingState.getValue(HorizontalDirectional.Waterlogged.Toggle3.TOGGLE)
            )
            : stateIn;
      } else {
         return stateIn;
      }
   }
}
