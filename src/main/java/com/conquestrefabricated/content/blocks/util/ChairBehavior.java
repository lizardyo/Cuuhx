package com.conquestrefabricated.content.blocks.util;

import com.conquestrefabricated.content.blocks.block.BoardsHorizontal;
import com.conquestrefabricated.content.blocks.block.Layer;
import com.conquestrefabricated.content.blocks.block.Slab;
import com.conquestrefabricated.content.blocks.block.SlabLessLayers;
import com.conquestrefabricated.content.blocks.block.VerticalSlab;
import com.conquestrefabricated.content.blocks.tileentity.seat.SeatTileEntity;
import com.conquestrefabricated.content.entities.seat.SeatEntity;
import com.conquestrefabricated.core.block.properties.ModBlockProperties;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ChairBehavior {
   public static final BooleanProperty OFFSET_TOGGLE = ModBlockProperties.OFFSET_TOGGLE;
   private final double chairHeight;

   public ChairBehavior(double chairHeight) {
      this.chairHeight = chairHeight;
   }

   public double getSeatHeight() {
      return this.chairHeight;
   }

   public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context, VoxelShape defaultShape) {
      return state.getValue(OFFSET_TOGGLE) ? Shapes.empty() : defaultShape;
   }

   public boolean calculateSlabOffset(Direction facing, Block block, BlockState state, BlockPlaceContext context) {
      if (facing == Direction.DOWN) {
         return block instanceof Slab
            || block instanceof SlabBlock
            || block instanceof Layer
            || block instanceof SnowLayerBlock
            || block instanceof SlabLessLayers
            || block instanceof BoardsHorizontal;
      } else {
         return facing == Direction.UP ? false : block instanceof VerticalSlab && state.getValue(VerticalSlab.DIRECTION) == context.getClickedFace();
      }
   }

   public InteractionResult handleSittingInteraction(
      BlockState state,
      Level level,
      BlockPos blockPos,
      Player player,
      InteractionHand hand,
      BlockHitResult hitResult,
      ChairBehavior.FallbackAction fallbackAction,
      Integer maxToggleValue
   ) {
      SeatTileEntity seat = (SeatTileEntity)level.getBlockEntity(blockPos);
      if (level.isClientSide) {
         return InteractionResult.SUCCESS;
      }

      if (player.isShiftKeyDown()) {
         return fallbackAction.execute();
      }

      if (maxToggleValue != null && state.hasProperty(this.getToggleProperty(state))) {
         int toggleValue = (Integer)state.getValue(this.getToggleProperty(state));
         return toggleValue <= maxToggleValue ? this.createNewSeat(seat, player, level, blockPos) : fallbackAction.execute();
      }

      List<SeatEntity> seats = level.getEntitiesOfClass(SeatEntity.class, new AABB(blockPos));
      if (!seats.isEmpty()) {
         SeatEntity seatEntity = seats.get(0);
         List<Entity> passengers = seatEntity.getPassengers();
         if (!passengers.isEmpty() && passengers.get(0) instanceof Player) {
            return InteractionResult.SUCCESS;
         }

         seatEntity.ejectPassengers();
         boolean success = player.startRiding(seatEntity);
         if (success) {
            level.blockUpdated(blockPos, level.getBlockState(blockPos).getBlock());
            seat.setChanged();
         }

         return success ? InteractionResult.SUCCESS : InteractionResult.PASS;
      } else {
         return this.createNewSeat(seat, player, level, blockPos);
      }
   }

   private InteractionResult createNewSeat(SeatTileEntity seat, Player player, Level level, BlockPos blockPos) {
      boolean success = seat.rideSeat(player);
      if (success) {
         level.blockUpdated(blockPos, level.getBlockState(blockPos).getBlock());
         seat.setChanged();
      }

      return success ? InteractionResult.SUCCESS : InteractionResult.PASS;
   }

   private IntegerProperty getToggleProperty(BlockState state) {
      for (Property<?> property : state.getProperties()) {
         if (property.getName().equals("toggle")) {
            return (IntegerProperty)property;
         }
      }

      return null;
   }

   public BlockEntity createBlockEntity(BlockPos blockPos, BlockState blockState) {
      return new SeatTileEntity(blockPos, blockState);
   }

   @FunctionalInterface
   public interface FallbackAction {
      InteractionResult execute();
   }
}
