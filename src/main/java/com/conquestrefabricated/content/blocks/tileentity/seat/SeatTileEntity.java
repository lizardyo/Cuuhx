package com.conquestrefabricated.content.blocks.tileentity.seat;

import com.conquestrefabricated.content.blocks.tileentity.TileEntityTypes;
import com.conquestrefabricated.content.entities.seat.SeatEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class SeatTileEntity extends BlockEntity {
   private double height;

   public SeatTileEntity(BlockPos blockPos, BlockState blockState) {
      super(TileEntityTypes.SEAT, blockPos, blockState);
   }

   public void setRemoved() {
      super.setRemoved();
   }

   public boolean rideSeat(Player player) {
      if (this.level.isClientSide) {
         return false;
      }

      SeatEntity seat = new SeatEntity(this.level);
      seat.absMoveTo(
         this.worldPosition.getX() + 0.5,
         this.worldPosition.getY(),
         this.worldPosition.getZ() + 0.5,
         this.getBlockState().hasProperty(HorizontalDirectionalBlock.FACING)
            ? ((Direction)this.getBlockState().getValue(HorizontalDirectionalBlock.FACING)).toYRot()
            : 0.0F,
         0.0F
      );
      this.level.addFreshEntity(seat);
      player.startRiding(seat, true);
      return true;
   }
}
