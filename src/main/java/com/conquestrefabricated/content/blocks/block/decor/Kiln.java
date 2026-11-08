package com.conquestrefabricated.content.blocks.block.decor;

import com.conquestrefabricated.content.blocks.block.directional.HorizontalDirectional;
import com.conquestrefabricated.content.blocks.tileentity.KilnTileEntity;
import com.conquestrefabricated.content.blocks.tileentity.TileEntityTypes;
import com.conquestrefabricated.core.asset.annotation.Render;
import com.conquestrefabricated.core.block.builder.Props;
import com.conquestrefabricated.core.util.RenderLayer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;

@Render(RenderLayer.CUTOUT)
public class Kiln extends HorizontalDirectional.OffsetXYZ.Toggle2 implements EntityBlock {
   public static final BooleanProperty LIT = BlockStateProperties.LIT;

   public Kiln(Props properties) {
      super(properties);
      this.registerDefaultState((BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue(LIT, false)).setValue(OFFSET_TOGGLE, false));
   }

   @Override
   public InteractionResult use(BlockState state, Level level, BlockPos blockPos, Player player, InteractionHand hand, BlockHitResult hitResult) {
      if (!player.getAbilities().mayBuild) {
         return InteractionResult.FAIL;
      } else if (player.getItemInHand(hand).getItem() == Items.FLINT_AND_STEEL) {
         level.setBlock(blockPos, (BlockState)state.setValue(LIT, true), 3);
         return InteractionResult.SUCCESS;
      } else {
         return super.use(state, level, blockPos, player, hand, hitResult);
      }
   }

   @OnlyIn(Dist.CLIENT)
   public void animateTick(BlockState stateIn, Level worldIn, BlockPos pos, RandomSource rand) {
      if ((Boolean)stateIn.getValue(LIT)) {
         if (rand.nextInt(10) == 0) {
            worldIn.playLocalSound(
               pos.getX() + 0.5,
               pos.getY() + 0.5,
               pos.getZ() + 0.5,
               SoundEvents.CAMPFIRE_CRACKLE,
               SoundSource.BLOCKS,
               0.5F + rand.nextFloat(),
               rand.nextFloat() * 0.7F + 0.6F,
               false
            );
         }

         if (rand.nextInt(5) == 0) {
            for (int i = 0; i < rand.nextInt(1) + 1; i++) {
               worldIn.addParticle(
                  ParticleTypes.LAVA,
                  pos.getX() + 0.25F,
                  pos.getY() + 1.0F,
                  pos.getZ() + 0.5F,
                  rand.nextFloat() / 2.0F,
                  5.0E-5,
                  rand.nextFloat() / 2.0F
               );
            }
         }
      }
   }

   public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
      return new KilnTileEntity(pos, state);
   }

   @Nullable
   public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
      if (level.isClientSide) {
         return state.getValue(LIT) ? checkType(blockEntityType, TileEntityTypes.KILN, KilnTileEntity::particleTick) : null;
      } else {
         return null;
      }
   }

   @Nullable
   protected static <E extends BlockEntity, T extends BlockEntity> BlockEntityTicker<T> checkType(
      BlockEntityType<T> givenType, BlockEntityType<E> expectedType, BlockEntityTicker<? super E> ticker
   ) {
      return expectedType == givenType ? ticker : null;
   }

   public static void spawnSmokeParticle(Level world, BlockPos pos) {
      RandomSource random = world.getRandom();
      SimpleParticleType defaultParticleType = ParticleTypes.CAMPFIRE_COSY_SMOKE;
      world.addAlwaysVisibleParticle(
         defaultParticleType,
         true,
         pos.getX() + 0.5 + random.nextDouble() / 3.0 * (random.nextBoolean() ? 1 : -1),
         pos.getY() + 0.75 + random.nextDouble() + random.nextDouble(),
         pos.getZ() + 0.5 + random.nextDouble() / 3.0 * (random.nextBoolean() ? 1 : -1),
         0.0,
         0.07,
         0.0
      );
   }

   @Override
   protected void addProperties(Builder<Block, BlockState> builder) {
      super.addProperties(builder);
      builder.add(new Property[]{LIT});
   }
}
