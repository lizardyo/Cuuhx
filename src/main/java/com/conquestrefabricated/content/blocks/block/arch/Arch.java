package com.conquestrefabricated.content.blocks.block.arch;

import com.conquestrefabricated.content.blocks.BlockVoxelShapes;
import com.conquestrefabricated.content.blocks.util.Interactions;
import com.conquestrefabricated.core.block.properties.ArchShape;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class Arch extends HorizontalDirectionalBlock {
   public static final EnumProperty<ArchShape> FORM = EnumProperty.create("shape", ArchShape.class);
   private static final VoxelShape ARCH_MIDDLE_SHAPE = Block.box(0.0, 8.0, 0.0, 16.0, 16.0, 16.0);

   public Arch(Properties properties) {
      super(properties);
      this.registerDefaultState((BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue(FORM, ArchShape.ONE)).setValue(FACING, Direction.NORTH));
   }

   public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
      if (state.getValue(FORM) == ArchShape.ONE) {
         return Shapes.block();
      }

      if (state.getValue(FORM) == ArchShape.THREE_MIDDLE) {
         return ARCH_MIDDLE_SHAPE;
      }

      switch ((Direction)state.getValue(FACING)) {
         case EAST:
            return BlockVoxelShapes.stairTopShapes.get(1);
         case SOUTH:
            return BlockVoxelShapes.stairTopShapes.get(2);
         case WEST:
            return BlockVoxelShapes.stairTopShapes.get(3);
         default:
            return BlockVoxelShapes.stairTopShapes.get(0);
      }
   }

   public VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
      return Shapes.empty();
   }

   public VoxelShape getOcclusionShape(BlockState state, BlockGetter world, BlockPos pos) {
      return Shapes.empty();
   }

   public VoxelShape getVisualShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
      return Shapes.empty();
   }

   public BlockState getStateForPlacement(BlockPlaceContext context) {
      BlockPos pos = context.getClickedPos();
      Direction facing = context.getClickedFace();
      if (facing == Direction.UP || facing == Direction.DOWN) {
         Direction horizontalFacing = context.getHorizontalDirection();
         facing = this.getFacingUpDown(context, pos, horizontalFacing);
      }

      ArchShape shape = ArchShape.ONE;
      return (BlockState)((BlockState)super.getStateForPlacement(context).setValue(FACING, facing)).setValue(FORM, shape);
   }

   private Direction getFacingUpDown(BlockPlaceContext context, BlockPos pos, Direction horizontalFacing) {
      switch (horizontalFacing) {
         case EAST:
            return !(context.getClickLocation().z - pos.getZ() > 0.5) ? horizontalFacing.getClockWise() : horizontalFacing.getCounterClockWise();
         case SOUTH:
            return !(context.getClickLocation().x - pos.getX() < 0.5) ? horizontalFacing.getClockWise() : horizontalFacing.getCounterClockWise();
         case WEST:
         default:
            return !(context.getClickLocation().z - pos.getZ() < 0.5) ? horizontalFacing.getClockWise() : horizontalFacing.getCounterClockWise();
         case NORTH:
            return !(context.getClickLocation().x - pos.getX() > 0.5) ? horizontalFacing.getClockWise() : horizontalFacing.getCounterClockWise();
      }
   }

   protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
      builder.add(new Property[]{FACING, FORM});
   }

   public InteractionResult use(BlockState state, Level level, BlockPos blockPos, Player player, InteractionHand hand, BlockHitResult hitResult) {
      return Interactions.onUseToggleItem(player, level, blockPos, state, FORM);
   }
}
