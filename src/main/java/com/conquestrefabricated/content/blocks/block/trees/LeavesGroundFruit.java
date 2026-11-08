package com.conquestrefabricated.content.blocks.block.trees;

import com.conquestrefabricated.api.tags.ModTags;
import com.conquestrefabricated.client.gui.config.ConquestConfig;
import com.conquestrefabricated.content.blocks.CustomOffsetType;
import com.conquestrefabricated.content.blocks.block.Layer;
import com.conquestrefabricated.content.blocks.block.Slab;
import com.conquestrefabricated.content.effects.Effects;
import com.conquestrefabricated.core.asset.annotation.Render;
import com.conquestrefabricated.core.block.builder.Props;
import com.conquestrefabricated.core.util.RenderLayer;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

@Render(RenderLayer.CUTOUT_MIPPED)
public class LeavesGroundFruit extends CropBlock {
   public static final IntegerProperty LAYER = BlockStateProperties.LAYERS;
   public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
   private final ItemLike fruit;

   public LeavesGroundFruit(Props props) {
      super(props.customOffsetType(CustomOffsetType.LAYER_STATE_Y).dynamicBounds(true).toSettings());
      this.fruit = props.get("fruit", ItemLike.class);
      this.registerDefaultState((BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue(LAYER, 8)).setValue(WATERLOGGED, false));
   }

   @Nullable
   public BlockState getStateForPlacement(BlockPlaceContext context) {
      BlockGetter iblockreader = context.getLevel();
      BlockPos blockpos = context.getClickedPos();
      BlockPos down = blockpos.below();
      BlockState blockStateDown = iblockreader.getBlockState(down);
      return !blockStateDown.hasProperty(Layer.LAYERS) && (!blockStateDown.hasProperty(Slab.LAYERS) || blockStateDown.getValue(Slab.TYPE_UPDOWN) != Half.BOTTOM)
         ? (BlockState)super.getStateForPlacement(context).setValue(LAYER, 8)
         : (BlockState)super.getStateForPlacement(context).setValue(LAYER, (Integer)blockStateDown.getValue(LAYER));
   }

   public void entityInside(BlockState state, Level world, BlockPos pos, Entity entity) {
      if (entity instanceof LivingEntity livingEntity && state.is(ModTags.PLANT_SLOWNESS) && (Boolean)ConquestConfig.INSTANCE.plantSlowness.get()) {
         if (livingEntity instanceof Player) {
            livingEntity.addEffect(new MobEffectInstance(Effects.CUSTOM_SLOWNESS, 15, 12, false, false));
         } else {
            livingEntity.addEffect(new MobEffectInstance(Effects.CUSTOM_SLOWNESS, 15, 6, false, false));
         }
      }
   }

   public VoxelShape getVisualShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
      return Shapes.empty();
   }

   public VoxelShape getInteractionShape(BlockState state, BlockGetter worldIn, BlockPos pos) {
      return Shapes.block();
   }

   public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
      return Shapes.block();
   }

   public boolean isPathfindable(BlockState state, BlockGetter world, BlockPos pos, PathComputationType type) {
      return true;
   }

   public void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
      if (world.getRawBrightness(pos, 0) >= 9) {
         int i = this.getAge(state);
         if (i < this.getMaxAge() && random.nextInt(9) == 0) {
            world.setBlock(pos, (BlockState)state.setValue(AGE, i + 1), 2);
         }
      }
   }

   public void growCrops(Level worldIn, BlockPos pos, BlockState state) {
      int i = this.getAge(state) + this.getBonemealAgeIncrease(worldIn);
      int j = this.getMaxAge();
      if (i > j) {
         i = j;
      }

      worldIn.setBlock(pos, (BlockState)state.setValue(AGE, i), 2);
   }

   protected void createBlockStateDefinition(Builder<Block, BlockState> stateList) {
      stateList.add(new Property[]{AGE, LAYER, WATERLOGGED});
   }

   public boolean canSurvive(BlockState state, LevelReader reader, BlockPos pos) {
      return true;
   }

   public boolean mayPlaceOn(BlockState p_200014_1_, BlockGetter p_200014_2_, BlockPos p_200014_3_) {
      return true;
   }

   public ItemStack getCloneItemStack(BlockGetter reader, BlockPos pos, BlockState state) {
      return new ItemStack(this, 1);
   }

   public InteractionResult use(BlockState state, Level level, BlockPos blockPos, Player player, InteractionHand hand, BlockHitResult hitResult) {
      if (this.isMaxAge(state)) {
         if (level.isClientSide) {
            return InteractionResult.SUCCESS;
         }

         state = (BlockState)state.setValue(AGE, 0);
         level.setBlock(blockPos, state, 3);
         this.dropFruit(level, blockPos, state);
         return InteractionResult.SUCCESS;
      } else {
         return InteractionResult.FAIL;
      }
   }

   private void dropFruit(Level world, BlockPos pos, BlockState state) {
      if (!world.isClientSide) {
         float f = 0.7F;
         double d0 = world.random.nextFloat() * 0.7F + 0.15F;
         double d1 = world.random.nextFloat() * 0.7F + 0.060000002F + 0.6;
         double d2 = world.random.nextFloat() * 0.7F + 0.15F;
         ItemStack itemstack1 = new ItemStack(this.fruit, 1);
         ItemEntity entityitem = new ItemEntity(world, pos.getX() + d0, pos.getY() + d1, pos.getZ() + d2, itemstack1);
         entityitem.setDefaultPickUpDelay();
         world.addFreshEntity(entityitem);
      }
   }
}
