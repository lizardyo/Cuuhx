package com.conquestrefabricated.content.entities.painting;

import com.conquestrefabricated.content.entities.painting.art.ArtType;
import com.conquestrefabricated.content.entities.painting.art.ModArt;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.decoration.HangingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.entity.IEntityWithComplexSpawn;
import org.jetbrains.annotations.Nullable;

public class EntityPainting extends HangingEntity implements IEntityWithComplexSpawn {
   private ModPainting type;
   private ArtType art = ArtType.A1x1_0;

   public EntityPainting(EntityType<EntityPainting> type, Level world) {
      super(type, world);
   }

   public EntityPainting(Level world, BlockPos pos, Direction facing, ModPainting type, ArtType art) {
      super(PaintingEntityType.PAINTING, world, pos);
      this.direction = facing;
      this.type = type;
      this.art = art;
      this.setDirection(facing);
   }

   public ModPainting getPaintingType() {
      return this.type;
   }

   public ArtType getArt() {
      return this.art;
   }

   public void setType(ModPainting type) {
      this.type = type;
   }

   public void setArt(ArtType art) {
      this.art = art;
      if (this.direction != null) {
         this.setDirection(this.direction);
      }
   }

   @Nullable
   public ItemStack getPickResult() {
      return this.type.createStack(ModArt.of(this.art));
   }

   public void dropItem(@Nullable Entity brokenEntity) {
      if (this.level().getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS)) {
         this.playSound(SoundEvents.PAINTING_BREAK, 1.0F, 1.0F);
         if (brokenEntity instanceof Player entityplayer && entityplayer.getAbilities().instabuild) {
            return;
         }

         ItemStack drop = this.type.createStack(ModArt.of(this.art));
         if (drop != ItemStack.EMPTY) {
            this.spawnAtLocation(drop, 0.0F);
         }
      }
   }

   public boolean survives() {
      return true;
   }

   public int getWidth() {
      return this.art.sizeX;
   }

   public int getHeight() {
      return this.art.sizeY;
   }

   public void playPlacementSound() {
      this.playSound(SoundEvents.PAINTING_PLACE, 1.0F, 1.0F);
   }

   public void addAdditionalSaveData(CompoundTag compound) {
      compound.putByte("Facing", (byte)this.direction.get2DDataValue());
      super.addAdditionalSaveData(compound);
      compound.putString("TypeID", this.type.getName());
      compound.putInt("ArtID", this.art.index());
   }

   public void readAdditionalSaveData(CompoundTag compound) {
      String type = compound.getString("TypeID");
      int id = compound.getInt("ArtID");
      this.type = ModPainting.fromId(type);
      this.art = ArtType.fromId(id);
      this.direction = Direction.from2DDataValue(compound.getByte("Facing"));
      super.readAdditionalSaveData(compound);
      this.setDirection(this.direction);
   }

   public void moveTo(double x, double y, double z, float yaw, float pitch) {
      this.setPos(x, y, z);
   }

   public void lerpTo(double x, double y, double z, float a, float b, int c, boolean bl) {
      BlockPos pos = this.pos;
      this.setPos(pos.getX(), pos.getY(), pos.getZ());
   }

   public Packet<ClientGamePacketListener> getAddEntityPacket() {
      return this.createSpawnPacket();
   }

   public void writeSpawnData(RegistryFriendlyByteBuf buffer) {
      buffer.writeInt(this.pos.getX());
      buffer.writeInt(this.pos.getY());
      buffer.writeInt(this.pos.getZ());
      buffer.writeInt(this.direction.get2DDataValue());
      buffer.writeUtf(this.getPaintingType().getName());
      buffer.writeUtf(this.getArt().shapeId);
   }

   public void readSpawnData(RegistryFriendlyByteBuf additionalData) {
      int x = additionalData.readInt();
      int y = additionalData.readInt();
      int z = additionalData.readInt();
      int index = additionalData.readInt();
      String type = additionalData.readUtf();
      String art = additionalData.readUtf();
      this.type = ModPainting.fromId(type);
      this.art = ArtType.fromName(art);
      this.direction = Direction.from2DDataValue(index);
      this.setDirection(Direction.from2DDataValue(index));
      this.setPos(x, y, z);
   }
}
