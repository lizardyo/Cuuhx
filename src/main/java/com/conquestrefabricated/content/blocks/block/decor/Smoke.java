package com.conquestrefabricated.content.blocks.block.decor;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

public class Smoke extends Block {
   public static final IntegerProperty SMOKE_0_2 = IntegerProperty.create("smoke", 0, 2);

   public Smoke(Properties propertiesIn) {
      super(propertiesIn);
      this.registerDefaultState((BlockState)((BlockState)this.stateDefinition.any()).setValue(SMOKE_0_2, 0));
   }

   public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
      return true;
   }

   public RenderShape getRenderShape(BlockState state) {
      return RenderShape.INVISIBLE;
   }

   @OnlyIn(Dist.CLIENT)
   public float getAmbientOcclusionLightValue(BlockState state, BlockGetter worldIn, BlockPos pos) {
      return 1.0F;
   }

   @OnlyIn(Dist.CLIENT)
   public void animateTick(BlockState stateIn, Level worldIn, BlockPos pos, RandomSource rand) {
      RandomSource random = worldIn.getRandom();
      if ((Integer)stateIn.getValue(SMOKE_0_2) == 0) {
         worldIn.addParticle(
            ParticleTypes.LARGE_SMOKE,
            pos.getX() + 0.25 + random.nextDouble() / 2.0 * (random.nextBoolean() ? 1 : -1),
            pos.getY() + 0.4,
            pos.getZ() + 0.25 + random.nextDouble() / 2.0 * (random.nextBoolean() ? 1 : -1),
            0.0,
            0.005,
            0.0
         );
      } else if ((Integer)stateIn.getValue(SMOKE_0_2) == 1) {
         worldIn.addParticle(
            ParticleTypes.CAMPFIRE_COSY_SMOKE,
            true,
            pos.getX() + 0.5 + random.nextDouble() / 3.0 * (random.nextBoolean() ? 1 : -1),
            pos.getY() + random.nextDouble() + random.nextDouble(),
            pos.getZ() + 0.5 + random.nextDouble() / 3.0 * (random.nextBoolean() ? 1 : -1),
            0.0,
            0.07,
            0.0
         );
      } else {
         worldIn.addParticle(
            ParticleTypes.CAMPFIRE_SIGNAL_SMOKE,
            true,
            pos.getX() + 0.5 + random.nextDouble() / 3.0 * (random.nextBoolean() ? 1 : -1),
            pos.getY() + random.nextDouble() + random.nextDouble(),
            pos.getZ() + 0.5 + random.nextDouble() / 3.0 * (random.nextBoolean() ? 1 : -1),
            0.0,
            0.07,
            0.0
         );
      }

      ItemStack itemstack = Minecraft.getInstance().player.getMainHandItem();
      boolean flag = !itemstack.isEmpty() && itemstack.getItem() == this.asItem();
      if (flag) {
         for (int i = 0; i < 2; i++) {
            worldIn.addParticle(ParticleTypes.LAVA, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 0.0, 1.0E-5, 0.0);
         }
      }
   }

   protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
      builder.add(new Property[]{SMOKE_0_2});
   }
}
