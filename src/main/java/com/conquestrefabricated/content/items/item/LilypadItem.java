package com.conquestrefabricated.content.items.item;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PlaceOnWaterBlockItem;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ClipContext.Fluid;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.HitResult.Type;

public class LilypadItem extends PlaceOnWaterBlockItem {
   private Block blockIn;

   public LilypadItem(Block blockIn, Properties builder) {
      super(blockIn, builder);
      this.blockIn = blockIn;
   }

   public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
      ItemStack itemstack = playerIn.getItemInHand(handIn);
      HitResult raytraceresult = getPlayerPOVHitResult(worldIn, playerIn, Fluid.SOURCE_ONLY);
      if (raytraceresult.getType() == Type.MISS) {
         return new InteractionResultHolder(InteractionResult.PASS, itemstack);
      }

      if (raytraceresult.getType() == Type.BLOCK) {
         BlockHitResult blockraytraceresult = (BlockHitResult)raytraceresult;
         BlockPos blockpos = blockraytraceresult.getBlockPos();
         Direction direction = blockraytraceresult.getDirection();
         if (!worldIn.mayInteract(playerIn, blockpos) || !playerIn.mayUseItemAt(blockpos.relative(direction), direction, itemstack)) {
            return new InteractionResultHolder(InteractionResult.FAIL, itemstack);
         }

         BlockPos blockpos1 = blockpos.above();
         BlockState blockstate = worldIn.getBlockState(blockpos);
         FluidState ifluidstate = worldIn.getFluidState(blockpos);
         if ((ifluidstate.getType() == Fluids.WATER || blockstate.getTags().anyMatch(blockTagKey -> blockTagKey.equals(BlockTags.ICE)))
            && worldIn.isEmptyBlock(blockpos1)) {
            worldIn.setBlock(blockpos1, this.blockIn.defaultBlockState(), 11);
            if (playerIn instanceof ServerPlayer) {
               CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayer)playerIn, blockpos1, itemstack);
            }

            if (!playerIn.getAbilities().instabuild) {
               itemstack.shrink(1);
            }

            playerIn.awardStat(Stats.ITEM_USED.get(this));
            worldIn.playSound(playerIn, blockpos, SoundEvents.LILY_PAD_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F);
            return new InteractionResultHolder(InteractionResult.SUCCESS, itemstack);
         }

         if (worldIn.isEmptyBlock(blockpos1)) {
            worldIn.setBlock(blockpos1, this.blockIn.defaultBlockState(), 11);
            playerIn.awardStat(Stats.ITEM_USED.get(this));
            worldIn.playSound(playerIn, blockpos, SoundEvents.LILY_PAD_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F);
            return new InteractionResultHolder(InteractionResult.SUCCESS, itemstack);
         }
      }

      return new InteractionResultHolder(InteractionResult.FAIL, itemstack);
   }
}
