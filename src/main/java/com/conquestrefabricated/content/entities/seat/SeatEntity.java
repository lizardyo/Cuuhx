package com.conquestrefabricated.content.entities.seat;

import com.conquestrefabricated.content.blocks.block.Layer;
import com.conquestrefabricated.content.blocks.block.Slab;
import com.conquestrefabricated.content.blocks.block.SlabLessLayers;
import com.conquestrefabricated.content.blocks.util.Sittable;
import com.conquestrefabricated.content.entities.EntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.Vec3;

public class SeatEntity extends Entity {
   private BlockState seat;

   public SeatEntity(EntityType<?> type, Level world) {
      super(type, world);
   }

   public SeatEntity(Level world) {
      this(EntityTypes.SEAT, world);
   }

   public boolean canCollideWith(Entity entity) {
      return false;
   }

   public double getPassengersRidingOffset() {
      if (!(this.getSeat().getBlock() instanceof Sittable seat)) {
         this.kill();
         return 0.0;
      } else {
         double baseHeight = seat.getSeatHeight();
         BlockPos belowPos = this.blockPosition().relative(Direction.DOWN);
         BlockState belowState = this.level().getBlockState(belowPos);
         Block belowBlock = belowState.getBlock();
         if (belowBlock instanceof Layer || belowBlock instanceof Slab) {
            int layers = (Integer)belowState.getValue(BlockStateProperties.LAYERS);
            double layerHeight = (8 - layers) * 0.125;
            return baseHeight - layerHeight;
         }

         if (belowState.getBlock() instanceof SlabLessLayers) {
            int layers = (Integer)belowState.getValue(SlabLessLayers.LAYERS);

            return baseHeight - switch (layers) {
               case 1 -> 0.875;
               case 2 -> 0.75;
               case 3 -> 0.5;
               case 4 -> 0.25;
               default -> 0.0;
            };
         } else {
            return baseHeight;
         }
      }
   }

   public float getPickRadius() {
      return 0.0F;
   }

   public int hashCode() {
      return super.hashCode() + this.getSeat().hashCode();
   }

   public boolean isPickable() {
      return false;
   }

   public void move(MoverType type, Vec3 pos) {
   }

   public void playerTouch(Player player) {
   }

   public boolean shouldRiderSit() {
      return true;
   }

   public void tick() {
      super.tick();
      if (!this.level().isClientSide) {
         if (this.getSeat().isAir() || !this.isVehicle()) {
            this.kill();
         }
      }
   }

   protected boolean canRide(Entity entity) {
      return entity instanceof Player;
   }

   protected void checkInsideBlocks() {
   }

   protected void defineSynchedData() {
   }

   protected void readAdditionalSaveData(CompoundTag p_70037_1_) {
   }

   protected void addAdditionalSaveData(CompoundTag p_213281_1_) {
   }

   public ClientboundAddEntityPacket createSpawnPacket() {
      return new ClientboundAddEntityPacket(this);
   }

   public BlockState getSeat() {
      if (this.seat == null) {
         this.seat = this.getCommandSenderWorld().getBlockState(this.blockPosition());
      }

      return this.seat;
   }
}
