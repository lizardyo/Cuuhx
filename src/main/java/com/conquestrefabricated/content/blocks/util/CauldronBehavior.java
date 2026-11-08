package com.conquestrefabricated.content.blocks.util;

import com.conquestrefabricated.content.blocks.block.BoardsHorizontal;
import com.conquestrefabricated.content.blocks.block.Layer;
import com.conquestrefabricated.content.blocks.block.Slab;
import com.conquestrefabricated.content.blocks.block.SlabLessLayers;
import com.conquestrefabricated.content.blocks.block.VerticalSlab;
import com.conquestrefabricated.core.block.properties.ModBlockProperties;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.BannerItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biome.Precipitation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ShulkerBoxBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.block.entity.BannerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CauldronBehavior {
   public static final BooleanProperty OFFSET_TOGGLE = ModBlockProperties.OFFSET_TOGGLE;
   public static final IntegerProperty LEVEL = IntegerProperty.create("level", 0, 3);
   private final List<VoxelShape> hitBox;

   public CauldronBehavior(List<VoxelShape> hitBox) {
      this.hitBox = hitBox;
   }

   public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
      return state.getValue(OFFSET_TOGGLE) ? Shapes.empty() : Shapes.block();
   }

   public VoxelShape getOutlineShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
      return this.hitBox.get(0);
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

   public void onEntityCollision(BlockState state, Level world, BlockPos pos, Entity entity) {
      int level = (Integer)state.getValue(LEVEL);
      float waterLevel = pos.getY() + (6.0F + 3 * level) / 16.0F;
      if (!world.isClientSide && entity.isOnFire() && level > 0 && entity.getY() <= waterLevel) {
         entity.clearFire();
         world.setBlockAndUpdate(pos, (BlockState)state.setValue(LEVEL, level - 1));
      }
   }

   public InteractionResult onUse(BlockState state, Level level, BlockPos blockPos, Player player, InteractionHand hand, BlockHitResult hitResult) {
      ItemStack itemStack = player.getItemInHand(hand);
      if (itemStack.isEmpty()) {
         return InteractionResult.PASS;
      } else {
         int waterLevel = (Integer)state.getValue(LEVEL);
         Item item = itemStack.getItem();
         if (item == Items.WATER_BUCKET) {
            return this.handleWaterBucket(state, level, blockPos, player, hand, waterLevel);
         } else if (item == Items.BUCKET) {
            return this.handleEmptyBucket(state, level, blockPos, player, hand, itemStack, waterLevel);
         } else if (item == Items.GLASS_BOTTLE) {
            return this.handleGlassBottle(state, level, blockPos, player, hand, itemStack, waterLevel);
         } else {
            return item == Items.POTION && itemStack.has(DataComponents.POTION_CONTENTS)
               ? this.handleWaterPotion(state, level, blockPos, player, hand, waterLevel)
               : this.handleCleaningInteractions(state, level, blockPos, player, hand, itemStack, item, waterLevel);
         }
      }
   }

   private InteractionResult handleWaterBucket(BlockState state, Level level, BlockPos blockPos, Player player, InteractionHand hand, int waterLevel) {
      if (waterLevel < 3 && !level.isClientSide) {
         if (!player.getAbilities().instabuild) {
            player.setItemInHand(hand, new ItemStack(Items.BUCKET));
         }

         player.awardStat(Stats.FILL_CAULDRON);
         level.setBlockAndUpdate(blockPos, (BlockState)state.setValue(LEVEL, 3));
         level.playSound(null, blockPos, SoundEvents.BUCKET_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
      }

      return InteractionResult.sidedSuccess(level.isClientSide);
   }

   private InteractionResult handleEmptyBucket(
      BlockState state, Level level, BlockPos blockPos, Player player, InteractionHand hand, ItemStack itemStack, int waterLevel
   ) {
      if (waterLevel == 3 && !level.isClientSide) {
         if (!player.getAbilities().instabuild) {
            itemStack.shrink(1);
            if (itemStack.isEmpty()) {
               player.setItemInHand(hand, new ItemStack(Items.WATER_BUCKET));
            } else if (!player.getInventory().add(new ItemStack(Items.WATER_BUCKET))) {
               player.drop(new ItemStack(Items.WATER_BUCKET), false);
            }
         }

         player.awardStat(Stats.USE_CAULDRON);
         level.setBlockAndUpdate(blockPos, (BlockState)state.setValue(LEVEL, 0));
         level.playSound(null, blockPos, SoundEvents.BUCKET_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
      }

      return InteractionResult.sidedSuccess(level.isClientSide);
   }

   private InteractionResult handleGlassBottle(
      BlockState state, Level level, BlockPos blockPos, Player player, InteractionHand hand, ItemStack itemStack, int waterLevel
   ) {
      if (waterLevel > 0 && !level.isClientSide) {
         if (!player.getAbilities().instabuild) {
            ItemStack waterBottle = new ItemStack(Items.POTION);
            waterBottle.set(DataComponents.POTION_CONTENTS, PotionContents.EMPTY);
            player.awardStat(Stats.USE_CAULDRON);
            itemStack.shrink(1);
            if (itemStack.isEmpty()) {
               player.setItemInHand(hand, waterBottle);
            } else if (!player.getInventory().add(waterBottle)) {
               player.drop(waterBottle, false);
            } else if (player instanceof ServerPlayer) {
               ((ServerPlayer)player).inventoryMenu.sendAllDataToRemote();
            }
         }

         level.playSound(null, blockPos, SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
         level.setBlockAndUpdate(blockPos, (BlockState)state.setValue(LEVEL, waterLevel - 1));
      }

      return InteractionResult.sidedSuccess(level.isClientSide);
   }

   private InteractionResult handleWaterPotion(BlockState state, Level level, BlockPos blockPos, Player player, InteractionHand hand, int waterLevel) {
      if (waterLevel < 3 && !level.isClientSide) {
         if (!player.getAbilities().instabuild) {
            ItemStack glassBottle = new ItemStack(Items.GLASS_BOTTLE);
            player.awardStat(Stats.USE_CAULDRON);
            player.setItemInHand(hand, glassBottle);
            if (player instanceof ServerPlayer) {
               ((ServerPlayer)player).inventoryMenu.sendAllDataToRemote();
            }
         }

         level.playSound(null, blockPos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
         level.setBlockAndUpdate(blockPos, (BlockState)state.setValue(LEVEL, waterLevel + 1));
      }

      return InteractionResult.sidedSuccess(level.isClientSide);
   }

   private InteractionResult handleCleaningInteractions(
      BlockState state, Level level, BlockPos blockPos, Player player, InteractionHand hand, ItemStack itemStack, Item item, int waterLevel
   ) {
      if (waterLevel <= 0) {
         return InteractionResult.PASS;
      } else if (itemStack.has(DataComponents.DYED_COLOR) && !level.isClientSide) {
         itemStack.remove(DataComponents.DYED_COLOR);
         level.setBlockAndUpdate(blockPos, (BlockState)state.setValue(LEVEL, waterLevel - 1));
         player.awardStat(Stats.CLEAN_ARMOR);
         return InteractionResult.SUCCESS;
      } else if (item instanceof BannerItem) {
         if (BannerBlockEntity.getPatternCount(itemStack) > 0 && !level.isClientSide) {
            ItemStack cleanBanner = itemStack.copy();
            cleanBanner.setCount(1);
            BannerBlockEntity.removeLastPattern(cleanBanner);
            player.awardStat(Stats.CLEAN_BANNER);
            if (!player.getAbilities().instabuild) {
               itemStack.shrink(1);
               level.setBlockAndUpdate(blockPos, (BlockState)state.setValue(LEVEL, waterLevel - 1));
            }

            if (itemStack.isEmpty()) {
               player.setItemInHand(hand, cleanBanner);
            } else if (!player.getInventory().add(cleanBanner)) {
               player.drop(cleanBanner, false);
            } else if (player instanceof ServerPlayer) {
               ((ServerPlayer)player).inventoryMenu.sendAllDataToRemote();
            }
         }

         return InteractionResult.sidedSuccess(level.isClientSide);
      } else if (item instanceof BlockItem) {
         Block block = ((BlockItem)item).getBlock();
         if (block instanceof ShulkerBoxBlock && !level.isClientSide()) {
            ItemStack cleanShulkerBox = new ItemStack(Blocks.SHULKER_BOX, 1);
            if (itemStack.getTag() != null) {
               cleanShulkerBox.setTag(itemStack.getTag().copy());
            }

            player.setItemInHand(hand, cleanShulkerBox);
            level.setBlockAndUpdate(blockPos, (BlockState)state.setValue(LEVEL, waterLevel - 1));
            player.awardStat(Stats.CLEAN_SHULKER_BOX);
            return InteractionResult.SUCCESS;
         } else {
            return InteractionResult.CONSUME;
         }
      } else {
         return InteractionResult.PASS;
      }
   }

   public void precipitationTick(BlockState state, Level world, BlockPos pos, Precipitation precipitation) {
      if (world.random.nextInt(20) == 1) {
         float temperature = ((Biome)world.getBiome(pos).value()).getBaseTemperature();
         if (temperature >= 0.15F) {
            BlockState currentState = world.getBlockState(pos);
            if ((Integer)currentState.getValue(LEVEL) < 3) {
               world.setBlock(pos, (BlockState)currentState.cycle(LEVEL), 2);
            }
         }
      }
   }

   public boolean hasComparatorOutput(BlockState state) {
      return true;
   }

   public int getComparatorOutput(BlockState state, Level world, BlockPos pos) {
      return (Integer)state.getValue(LEVEL);
   }
}
