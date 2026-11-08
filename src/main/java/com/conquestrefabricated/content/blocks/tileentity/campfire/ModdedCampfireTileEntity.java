package com.conquestrefabricated.content.blocks.tileentity.campfire;

import com.conquestrefabricated.content.blocks.block.decor.Campfire;
import com.conquestrefabricated.content.blocks.tileentity.TileEntityTypes;
import java.util.Optional;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Clearable;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.Containers;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CampfireCookingRecipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.RecipeManager.CachedCheck;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class ModdedCampfireTileEntity extends BlockEntity implements Clearable {
   private static final int BURN_COOL_SPEED = 2;
   private static final int NUM_SLOTS = 4;
   private final NonNullList<ItemStack> items = NonNullList.withSize(4, ItemStack.EMPTY);
   private final int[] cookingProgress = new int[4];
   private final int[] cookingTime = new int[4];
   private final CachedCheck<Container, CampfireCookingRecipe> matchGetter = RecipeManager.createCheck(RecipeType.CAMPFIRE_COOKING);

   public ModdedCampfireTileEntity(BlockPos pos, BlockState state) {
      super(TileEntityTypes.CAMPFIRE, pos, state);
   }

   public static void cookTick(Level p_155307_, BlockPos p_155308_, BlockState p_155309_, ModdedCampfireTileEntity p_155310_) {
      boolean flag = false;

      for (int i = 0; i < p_155310_.items.size(); i++) {
         ItemStack itemstack = (ItemStack)p_155310_.items.get(i);
         if (!itemstack.isEmpty()) {
            flag = true;
            p_155310_.cookingProgress[i]++;
            if (p_155310_.cookingProgress[i] >= p_155310_.cookingTime[i]) {
               Container container = new SimpleContainer(new ItemStack[]{itemstack});
               ItemStack itemstack1 = p_155307_.getRecipeManager()
                  .getRecipeFor(RecipeType.CAMPFIRE_COOKING, container, p_155307_)
                  .map(p_155305_ -> p_155305_.assemble(container, RegistryAccess.fromRegistryOfRegistries(BuiltInRegistries.REGISTRY)))
                  .orElse(itemstack);
               Containers.dropItemStack(p_155307_, p_155308_.getX(), p_155308_.getY(), p_155308_.getZ(), itemstack1);
               p_155310_.items.set(i, ItemStack.EMPTY);
               p_155307_.sendBlockUpdated(p_155308_, p_155309_, p_155309_, 3);
            }
         }
      }

      if (flag) {
         setChanged(p_155307_, p_155308_, p_155309_);
      }
   }

   public static void cooldownTick(Level p_155314_, BlockPos p_155315_, BlockState p_155316_, ModdedCampfireTileEntity p_155317_) {
      boolean flag = false;

      for (int i = 0; i < p_155317_.items.size(); i++) {
         if (p_155317_.cookingProgress[i] > 0) {
            flag = true;
            p_155317_.cookingProgress[i] = Mth.clamp(p_155317_.cookingProgress[i] - 2, 0, p_155317_.cookingTime[i]);
         }
      }

      if (flag) {
         setChanged(p_155314_, p_155315_, p_155316_);
      }
   }

   public static void particleTick(Level p_155319_, BlockPos p_155320_, BlockState p_155321_, ModdedCampfireTileEntity p_155322_) {
      RandomSource random = p_155319_.random;
      if (random.nextFloat() < 0.11F) {
         for (int i = 0; i < random.nextInt(2) + 2; i++) {
            Campfire.makeParticles(p_155319_, p_155320_, (Boolean)p_155321_.getValue(Campfire.SIGNAL_FIRE), false);
         }
      }

      int l = ((Direction)p_155321_.getValue(Campfire.FACING)).get2DDataValue();

      for (int j = 0; j < p_155322_.items.size(); j++) {
         if (!((ItemStack)p_155322_.items.get(j)).isEmpty() && random.nextFloat() < 0.2F) {
            Direction direction = Direction.from2DDataValue(Math.floorMod(j + l, 4));
            float f = 0.3125F;
            double d0 = p_155320_.getX() + 0.5 - direction.getStepX() * 0.3125F + direction.getClockWise().getStepX() * 0.3125F;
            double d1 = p_155320_.getY() + 0.5;
            double d2 = p_155320_.getZ() + 0.5 - direction.getStepZ() * 0.3125F + direction.getClockWise().getStepZ() * 0.3125F;

            for (int k = 0; k < 4; k++) {
               p_155319_.addParticle(ParticleTypes.SMOKE, d0, d1, d2, 0.0, 5.0E-4, 0.0);
            }
         }
      }
   }

   public NonNullList<ItemStack> getItems() {
      return this.items;
   }

   public void load(CompoundTag p_155312_) {
      super.load(p_155312_);
      this.items.clear();
      ContainerHelper.loadAllItems(p_155312_, this.items);
      if (p_155312_.contains("CookingTimes", 11)) {
         int[] aint = p_155312_.getIntArray("CookingTimes");
         System.arraycopy(aint, 0, this.cookingProgress, 0, Math.min(this.cookingTime.length, aint.length));
      }

      if (p_155312_.contains("CookingTotalTimes", 11)) {
         int[] aint1 = p_155312_.getIntArray("CookingTotalTimes");
         System.arraycopy(aint1, 0, this.cookingTime, 0, Math.min(this.cookingTime.length, aint1.length));
      }
   }

   protected void saveAdditional(CompoundTag p_187486_) {
      super.saveAdditional(p_187486_);
      ContainerHelper.saveAllItems(p_187486_, this.items, true);
      p_187486_.putIntArray("CookingTimes", this.cookingProgress);
      p_187486_.putIntArray("CookingTotalTimes", this.cookingTime);
   }

   public ClientboundBlockEntityDataPacket toUpdatePacket() {
      return ClientboundBlockEntityDataPacket.create(this);
   }

   public CompoundTag getUpdateTag() {
      CompoundTag compoundtag = new CompoundTag();
      ContainerHelper.saveAllItems(compoundtag, this.items, true);
      return compoundtag;
   }

   public Optional<CampfireCookingRecipe> getCookableRecipe(ItemStack itemStack) {
      return this.items.stream().noneMatch(ItemStack::isEmpty)
         ? Optional.empty()
         : this.matchGetter.getRecipeFor(new SimpleContainer(new ItemStack[]{itemStack}), this.level);
   }

   public boolean placeFood(ItemStack p_59054_, int p_59055_) {
      for (int i = 0; i < this.items.size(); i++) {
         ItemStack itemstack = (ItemStack)this.items.get(i);
         if (itemstack.isEmpty()) {
            this.cookingTime[i] = p_59055_;
            this.cookingProgress[i] = 0;
            this.items.set(i, p_59054_.split(1));
            this.markUpdated();
            return true;
         }
      }

      return false;
   }

   private void markUpdated() {
      this.setChanged();
      this.getLevel().sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 3);
   }

   public void clearContent() {
      this.items.clear();
   }

   public void dowse() {
      if (this.level != null) {
         this.markUpdated();
      }
   }
}
