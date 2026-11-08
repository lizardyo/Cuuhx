package com.conquestrefabricated.content.blocks.tileentity.enchantment;

import com.conquestrefabricated.content.blocks.tileentity.TileEntityTypes;
import java.util.Random;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Component.Serializer;
import net.minecraft.util.Mth;
import net.minecraft.world.Nameable;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class ModdedEnchantingTableTileEntity extends BlockEntity implements Nameable {
   public int time;
   public float flip;
   public float oFlip;
   public float flipT;
   public float flipA;
   public float open;
   public float oOpen;
   public float rot;
   public float oRot;
   public float tRot;
   private static final Random RANDOM = new Random();
   private Component name;

   public ModdedEnchantingTableTileEntity(BlockPos pos, BlockState state) {
      super(TileEntityTypes.ENCHANTING_TABLE, pos, state);
   }

   public void saveAdditional(CompoundTag p_189515_1_) {
      super.saveAdditional(p_189515_1_);
      if (this.hasCustomName()) {
         p_189515_1_.putString("CustomName", Serializer.toJson(this.name));
      }
   }

   public void load(CompoundTag p_155312_) {
      super.load(p_155312_);
      if (p_155312_.contains("CustomName", 8)) {
         this.name = Serializer.fromJson(p_155312_.getString("CustomName"));
      }
   }

   public static void bookAnimationTick(Level p_155504_, BlockPos p_155505_, BlockState p_155506_, ModdedEnchantingTableTileEntity p_155507_) {
      p_155507_.oOpen = p_155507_.open;
      p_155507_.oRot = p_155507_.rot;
      Player player = p_155504_.getNearestPlayer(p_155505_.getX() + 0.5, p_155505_.getY() + 0.5, p_155505_.getZ() + 0.5, 3.0, false);
      if (player != null) {
         double d0 = player.getX() - (p_155505_.getX() + 0.5);
         double d1 = player.getZ() - (p_155505_.getZ() + 0.5);
         p_155507_.tRot = (float)Mth.atan2(d1, d0);
         p_155507_.open += 0.1F;
         if (p_155507_.open < 0.5F || RANDOM.nextInt(40) == 0) {
            float f1 = p_155507_.flipT;

            do {
               p_155507_.flipT = p_155507_.flipT + (RANDOM.nextInt(4) - RANDOM.nextInt(4));
            } while (f1 == p_155507_.flipT);
         }
      } else {
         p_155507_.tRot += 0.02F;
         p_155507_.open -= 0.1F;
      }

      while (p_155507_.rot >= (float) Math.PI) {
         p_155507_.rot -= (float) (Math.PI * 2);
      }

      while (p_155507_.rot < (float) -Math.PI) {
         p_155507_.rot += (float) (Math.PI * 2);
      }

      while (p_155507_.tRot >= (float) Math.PI) {
         p_155507_.tRot -= (float) (Math.PI * 2);
      }

      while (p_155507_.tRot < (float) -Math.PI) {
         p_155507_.tRot += (float) (Math.PI * 2);
      }

      float f2 = p_155507_.tRot - p_155507_.rot;

      while (f2 >= (float) Math.PI) {
         f2 -= (float) (Math.PI * 2);
      }

      while (f2 < (float) -Math.PI) {
         f2 += (float) (Math.PI * 2);
      }

      p_155507_.rot += f2 * 0.4F;
      p_155507_.open = Mth.clamp(p_155507_.open, 0.0F, 1.0F);
      p_155507_.time++;
      p_155507_.oFlip = p_155507_.flip;
      float f = (p_155507_.flipT - p_155507_.flip) * 0.4F;
      float f3 = 0.2F;
      f = Mth.clamp(f, -0.2F, 0.2F);
      p_155507_.flipA = p_155507_.flipA + (f - p_155507_.flipA) * 0.9F;
      p_155507_.flip = p_155507_.flip + p_155507_.flipA;
   }

   public Component getName() {
      return (Component)(this.name != null ? this.name : Component.translatable("container.enchant"));
   }

   public void setCustomName(@Nullable Component name) {
      this.name = name;
   }

   @Nullable
   public Component getCustomName() {
      return this.name;
   }
}
