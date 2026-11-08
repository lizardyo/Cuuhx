package com.conquestrefabricated.content.blocks.block;

import com.conquestrefabricated.core.asset.annotation.Assets;
import com.conquestrefabricated.core.asset.annotation.Model;
import com.conquestrefabricated.core.asset.annotation.State;
import com.conquestrefabricated.core.block.properties.Waterloggable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

@Assets(
   state = @State(name = "%s_fence_gate", template = "parent_fence_gate"),
   item = @Model(name = "item/%s_fence_gate", parent = "block/%s_fence_gate", template = "item/acacia_fence_gate"),
   block = {
         @Model(name = "block/%s_fence_gate", template = "block/acacia_fence_gate"),
         @Model(name = "block/%s_fence_gate_open", template = "block/acacia_fence_gate_open"),
         @Model(name = "block/%s_fence_gate_wall", template = "block/acacia_fence_gate_wall"),
         @Model(name = "block/%s_fence_gate_wall_open", template = "block/acacia_fence_gate_wall_open")
   }
)
public class FenceGate extends FenceGateBlock implements Waterloggable {
   private static final BooleanProperty OPEN = BlockStateProperties.OPEN;
   private static final BooleanProperty IN_WALL = BlockStateProperties.IN_WALL;
   private static final VoxelShape AABB_HITBOX_ZAXIS = Block.box(0.0, 0.0, 6.0, 16.0, 16.0, 10.0);
   private static final VoxelShape AABB_HITBOX_XAXIS = Block.box(6.0, 0.0, 0.0, 10.0, 16.0, 16.0);
   private static final VoxelShape AABB_HITBOX_ZAXIS_INWALL = Block.box(0.0, 0.0, 6.0, 16.0, 13.0, 10.0);
   private static final VoxelShape AABB_HITBOX_XAXIS_INWALL = Block.box(6.0, 0.0, 0.0, 10.0, 13.0, 16.0);
   private static final VoxelShape field_208068_x = Block.box(0.0, 0.0, 6.0, 16.0, 24.0, 10.0);
   private static final VoxelShape AABB_COLLISION_BOX_XAXIS = Block.box(6.0, 0.0, 0.0, 10.0, 24.0, 16.0);
   private static final VoxelShape field_208069_z = Shapes.or(
      Block.box(0.0, 5.0, 7.0, 2.0, 16.0, 9.0), Block.box(14.0, 5.0, 7.0, 16.0, 16.0, 9.0)
   );
   private static final VoxelShape AABB_COLLISION_BOX_ZAXIS = Shapes.or(
      Block.box(7.0, 5.0, 0.0, 9.0, 16.0, 2.0), Block.box(7.0, 5.0, 14.0, 9.0, 16.0, 16.0)
   );
   private static final VoxelShape field_208066_B = Shapes.or(
      Block.box(0.0, 2.0, 7.0, 2.0, 13.0, 9.0), Block.box(14.0, 2.0, 7.0, 16.0, 13.0, 9.0)
   );
   private static final VoxelShape field_208067_C = Shapes.or(
      Block.box(7.0, 2.0, 0.0, 9.0, 13.0, 2.0), Block.box(7.0, 2.0, 14.0, 9.0, 13.0, 16.0)
   );

   public FenceGate(Properties properties, WoodType type) {
      super(properties, type);
      this.registerDefaultState(
         (BlockState)((BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue(OPEN, false)).setValue(IN_WALL, false))
            .setValue(WATERLOGGED, false)
      );
   }

   public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
      if ((Boolean)state.getValue(IN_WALL)) {
         return ((Direction)state.getValue(FACING)).getAxis() == Axis.X ? AABB_HITBOX_XAXIS_INWALL : AABB_HITBOX_ZAXIS_INWALL;
      } else {
         return ((Direction)state.getValue(FACING)).getAxis() == Axis.X ? AABB_HITBOX_XAXIS : AABB_HITBOX_ZAXIS;
      }
   }

   public VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
      if ((Boolean)state.getValue(OPEN)) {
         return Shapes.empty();
      } else {
         return ((Direction)state.getValue(FACING)).getAxis() == Axis.Z ? field_208068_x : AABB_COLLISION_BOX_XAXIS;
      }
   }

   public VoxelShape getOcclusionShape(BlockState state, BlockGetter world, BlockPos pos) {
      if ((Boolean)state.getValue(IN_WALL)) {
         return ((Direction)state.getValue(FACING)).getAxis() == Axis.X ? field_208067_C : field_208066_B;
      } else {
         return ((Direction)state.getValue(FACING)).getAxis() == Axis.X ? AABB_COLLISION_BOX_ZAXIS : field_208069_z;
      }
   }

   public boolean isPathfindable(BlockState state, BlockGetter world, BlockPos pos, PathComputationType type) {
      switch (type) {
         case LAND:
         case AIR:
            return (Boolean)state.getValue(OPEN);
         case WATER:
         default:
            return false;
      }
   }

   public BlockState getStateForPlacement(BlockPlaceContext context) {
      Level world = context.getLevel();
      BlockPos blockpos = context.getClickedPos();
      Direction direction = context.getHorizontalDirection();
      Axis axis = direction.getAxis();
      FluidState ifluidstate = context.getLevel().getFluidState(context.getClickedPos());
      boolean flag1 = axis == Axis.Z && (this.isWall(world.getBlockState(blockpos.west())) || this.isWall(world.getBlockState(blockpos.east())))
         || axis == Axis.X && (this.isWall(world.getBlockState(blockpos.north())) || this.isWall(world.getBlockState(blockpos.south())));
      return (BlockState)((BlockState)((BlockState)this.defaultBlockState().setValue(FACING, direction)).setValue(IN_WALL, flag1))
         .setValue(WATERLOGGED, ifluidstate.getType() == Fluids.WATER);
   }

   public InteractionResult use(BlockState state, Level level, BlockPos blockPos, Player player, InteractionHand hand, BlockHitResult hitResult) {
      if ((Boolean)state.getValue(OPEN)) {
         state = (BlockState)state.setValue(OPEN, false);
         level.setBlock(blockPos, state, 10);
      } else {
         Direction Direction = player.getDirection();
         if (state.getValue(FACING) == Direction.getOpposite()) {
            state = (BlockState)state.setValue(FACING, Direction);
         }

         state = (BlockState)state.setValue(OPEN, true);
         level.setBlock(blockPos, state, 10);
      }

      level.levelEvent(player, state.getValue(OPEN) ? 1008 : 1014, blockPos, 0);
      return InteractionResult.SUCCESS;
   }

   protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
      builder.add(new Property[]{FACING, OPEN, IN_WALL, POWERED, WATERLOGGED});
   }

   public FluidState getFluidState(BlockState state) {
      return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
   }

   public void neighborChanged(BlockState blockState, Level level, BlockPos blockPos, Block block, BlockPos blockPos1, boolean bool) {
   }

   private boolean isWall(BlockState blockstate) {
      return blockstate.is(BlockTags.WALLS);
   }
}
