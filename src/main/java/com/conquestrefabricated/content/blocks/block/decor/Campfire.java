package com.conquestrefabricated.content.blocks.block.decor;

import com.conquestrefabricated.content.blocks.CustomOffsetType;
import com.conquestrefabricated.content.blocks.tileentity.TileEntityTypes;
import com.conquestrefabricated.content.blocks.tileentity.campfire.ModdedCampfireTileEntity;
import com.conquestrefabricated.content.blocks.util.PlacementHelper;
import com.conquestrefabricated.core.asset.annotation.Render;
import com.conquestrefabricated.core.block.builder.Props;
import com.conquestrefabricated.core.block.properties.ModBlockProperties;
import com.conquestrefabricated.core.util.RenderLayer;
import java.util.Optional;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.crafting.CampfireCookingRecipe;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Render(RenderLayer.CUTOUT)
public class Campfire extends CampfireBlock {
   public static final BooleanProperty OFFSET_TOGGLE = ModBlockProperties.OFFSET_TOGGLE;

   public Campfire(Props properties) {
      super(true, 1, properties.customOffsetType(CustomOffsetType.LAYER_Y).toSettings());
      this.registerDefaultState(
         (BlockState)((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue(LIT, true))
                     .setValue(SIGNAL_FIRE, false))
                  .setValue(WATERLOGGED, false))
               .setValue(FACING, Direction.NORTH))
            .setValue(OFFSET_TOGGLE, false)
      );
   }

   public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
      return state.getValue(OFFSET_TOGGLE) ? Shapes.empty() : super.getCollisionShape(state, worldIn, pos, context);
   }

   @NotNull
   public BlockState getStateForPlacement(BlockPlaceContext context) {
      boolean isSlab = PlacementHelper.isFacingSlab(context);
      return (BlockState)super.getStateForPlacement(context).setValue(OFFSET_TOGGLE, isSlab);
   }

   public InteractionResult use(BlockState state, Level level, BlockPos blockPos, Player player, InteractionHand hand, BlockHitResult hitResult) {
      if ((Boolean)state.getValue(LIT) && level.getBlockEntity(blockPos) instanceof ModdedCampfireTileEntity campfiretileentity) {
         ItemStack itemstack = player.getItemInHand(hand);
         Optional<CampfireCookingRecipe> optional = campfiretileentity.getCookableRecipe(itemstack);
         if (optional.isPresent()) {
            if (!level.isClientSide && campfiretileentity.placeFood(player.getAbilities().instabuild ? itemstack.copy() : itemstack, optional.get().getCookingTime())) {
               player.awardStat(Stats.INTERACT_WITH_CAMPFIRE);
               return InteractionResult.SUCCESS;
            }

            return InteractionResult.CONSUME;
         }
      }

      return InteractionResult.PASS;
   }

   public void onRemove(BlockState state, Level worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
      if (state.getBlock() != newState.getBlock()) {
         BlockEntity tileentity = worldIn.getBlockEntity(pos);
         if (tileentity instanceof ModdedCampfireTileEntity) {
            Containers.dropContents(worldIn, pos, ((ModdedCampfireTileEntity)tileentity).getItems());
         }

         super.onRemove(state, worldIn, pos, newState, isMoving);
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
                  pos.getX() + 0.5F,
                  pos.getY() + 0.5F,
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
      return new ModdedCampfireTileEntity(pos, state);
   }

   @Nullable
   public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
      if (level.isClientSide) {
         return state.getValue(LIT) ? createTickerHelper(blockEntityType, TileEntityTypes.CAMPFIRE, ModdedCampfireTileEntity::particleTick) : null;
      } else {
         return state.getValue(LIT)
            ? createTickerHelper(blockEntityType, TileEntityTypes.CAMPFIRE, ModdedCampfireTileEntity::cookTick)
            : createTickerHelper(blockEntityType, TileEntityTypes.CAMPFIRE, ModdedCampfireTileEntity::cooldownTick);
      }
   }

   protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
      builder.add(new Property[]{LIT, SIGNAL_FIRE, WATERLOGGED, FACING, OFFSET_TOGGLE});
   }
}
