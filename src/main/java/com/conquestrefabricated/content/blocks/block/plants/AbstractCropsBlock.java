package com.conquestrefabricated.content.blocks.block.plants;

import com.conquestrefabricated.api.tags.ModTags;
import com.conquestrefabricated.client.gui.config.ConquestConfig;
import com.conquestrefabricated.content.blocks.block.Layer;
import com.conquestrefabricated.content.blocks.block.Slab;
import com.conquestrefabricated.content.blocks.util.PlacementHelper;
import com.conquestrefabricated.content.effects.Effects;
import com.conquestrefabricated.core.block.builder.Props;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;

public abstract class AbstractCropsBlock extends CropBlock {
   public static final IntegerProperty LAYERS = BlockStateProperties.LAYERS;
   protected final int slowness;

   protected AbstractCropsBlock(Props props) {
      super(props.toSettings());
      this.slowness = props.getOrDefault("slowness", Integer.class, 0);
      this.registerDefaultState((BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue(LAYERS, 8)).setValue(AGE, 0));
   }

   public void entityInside(BlockState state, Level world, BlockPos pos, Entity entity) {
      if (entity instanceof LivingEntity livingEntity && state.is(ModTags.PLANT_SLOWNESS) && (Boolean)ConquestConfig.INSTANCE.plantSlowness.get()) {
         int age = (Integer)state.getValue(AGE);
         if (this.slowness > 0 && age > 1) {
            if (livingEntity instanceof Player) {
               livingEntity.addEffect(new MobEffectInstance(Effects.CUSTOM_SLOWNESS, 15, this.slowness + Math.round(age / 3), false, false));
            } else if (this.slowness > 1) {
               livingEntity.addEffect(new MobEffectInstance(Effects.CUSTOM_SLOWNESS, 15, Math.round(this.slowness + Math.round(age / 3) / 2), false, false));
            }
         }
      }
   }

   public boolean canSurvive(BlockState state, LevelReader reader, BlockPos pos) {
      return PlacementHelper.isDuringWorldGen(reader) ? super.canSurvive(state, reader, pos) : true;
   }

   public boolean mayPlaceOn(BlockState state, BlockGetter reader, BlockPos pos) {
      return PlacementHelper.isDuringWorldGen(reader) ? super.mayPlaceOn(state, reader, pos) : true;
   }

   public BlockState getStateForPlacement(BlockPlaceContext context) {
      BlockGetter iblockreader = context.getLevel();
      BlockPos blockpos = context.getClickedPos();
      BlockPos down = blockpos.below();
      BlockState blockStateDown = iblockreader.getBlockState(down);
      return !blockStateDown.hasProperty(Layer.LAYERS) && !blockStateDown.hasProperty(Slab.LAYERS)
         ? (BlockState)super.getStateForPlacement(context).setValue(LAYERS, 8)
         : (BlockState)super.getStateForPlacement(context).setValue(LAYERS, (Integer)blockStateDown.getValue(LAYERS));
   }

   public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn, BlockPos currentPos, BlockPos facingPos) {
      BlockPos down = currentPos.below();
      BlockState blockStateDown = worldIn.getBlockState(down);
      return !blockStateDown.hasProperty(Layer.LAYERS) && !blockStateDown.hasProperty(Slab.LAYERS)
         ? (BlockState)super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos).setValue(LAYERS, 8)
         : (BlockState)super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos).setValue(LAYERS, (Integer)blockStateDown.getValue(LAYERS));
   }

   protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
      builder.add(new Property[]{AGE, LAYERS});
   }

   public void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
      if (world.getRawBrightness(pos, 0) >= 9) {
         int i = this.getAge(state);
         if (i < this.getMaxAge()) {
            float f = getAvailableMoisture1(this, world, pos);
            if (random.nextInt((int)(25.0F / f) + 1) == 0) {
               world.setBlock(pos, (BlockState)state.setValue(AGE, i + 1), 2);
            }
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

   protected static float getAvailableMoisture1(Block block, BlockGetter world, BlockPos pos) {
      float f = 1.0F;
      BlockPos blockPos = pos.below();

      for (int i = -1; i <= 1; i++) {
         for (int j = -1; j <= 1; j++) {
            float g = 0.0F;
            BlockState blockState = world.getBlockState(blockPos.offset(i, 0, j));
            if (blockState.is(Blocks.FARMLAND)
               || blockState.is((Block)BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath("conquest", "directional_farmland")))
               || blockState.is((Block)BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath("conquest", "directional_farmland_layer")))
               || blockState.is((Block)BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath("conquest", "diagonally_plowed_farmland")))
               || blockState.is((Block)BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath("conquest", "diagonally_plowed_farmland_layer")))) {
               g = 1.0F;
               if (blockState.is(Blocks.FARMLAND) && (Integer)blockState.getValue(FarmBlock.MOISTURE) > 0) {
                  g = 3.0F;
               }
            }

            if (i != 0 || j != 0) {
               g /= 4.0F;
            }

            f += g;
         }
      }

      BlockPos blockPos2 = pos.north();
      BlockPos blockPos3 = pos.south();
      BlockPos blockPos4 = pos.west();
      BlockPos blockPos5 = pos.east();
      boolean bl = world.getBlockState(blockPos4).is(block) || world.getBlockState(blockPos5).is(block);
      boolean bl2 = world.getBlockState(blockPos2).is(block) || world.getBlockState(blockPos3).is(block);
      if (bl && bl2) {
         f /= 2.0F;
      } else {
         boolean bl3 = world.getBlockState(blockPos4.north()).is(block)
            || world.getBlockState(blockPos5.north()).is(block)
            || world.getBlockState(blockPos5.south()).is(block)
            || world.getBlockState(blockPos4.south()).is(block);
         if (bl3) {
            f /= 2.0F;
         }
      }

      return f;
   }
}
