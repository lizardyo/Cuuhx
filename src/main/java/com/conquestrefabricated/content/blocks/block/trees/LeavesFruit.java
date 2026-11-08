package com.conquestrefabricated.content.blocks.block.trees;

import com.conquestrefabricated.api.tags.ModTags;
import com.conquestrefabricated.client.gui.config.ConquestConfig;
import com.conquestrefabricated.content.effects.Effects;
import com.conquestrefabricated.core.asset.annotation.Render;
import com.conquestrefabricated.core.block.builder.Props;
import com.conquestrefabricated.core.util.RenderLayer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos.MutableBlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
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
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.OffsetType;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

@Render(RenderLayer.CUTOUT_MIPPED)
public class LeavesFruit extends CropBlock {
   private static final VoxelShape EMPTY_SHAPE = Shapes.empty();
   public static final IntegerProperty DISTANCE = BlockStateProperties.DISTANCE;
   public static final BooleanProperty PERSISTENT = BlockStateProperties.PERSISTENT;
   private final ItemLike fruit;

   public LeavesFruit(Props props) {
      super(props.toSettings());
      this.fruit = props.get("fruit", ItemLike.class);
      this.registerDefaultState(
         (BlockState)((BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue(DISTANCE, 7)).setValue(PERSISTENT, false)).setValue(AGE, 0)
      );
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

   public VoxelShape getVisualShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
      return EMPTY_SHAPE;
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

   public BlockState updateShape(BlockState state, Direction facing, BlockState state1, LevelAccessor world, BlockPos pos, BlockPos pos1) {
      int i = getDistance(state1) + 1;
      if (i != 1 || (Integer)state.getValue(DISTANCE) != i) {
         world.scheduleTick(pos, this, 1);
      }

      return state;
   }

   private static BlockState updateDistance(BlockState state, LevelAccessor worldIn, BlockPos pos) {
      int i = 7;
      MutableBlockPos blockPosMutable = new MutableBlockPos();

      for (Direction direction : Direction.values()) {
         blockPosMutable.setWithOffset(pos, direction);
         i = Math.min(i, getDistance(worldIn.getBlockState(blockPosMutable)) + 1);
         if (i == 1) {
            break;
         }
      }

      return (BlockState)state.setValue(DISTANCE, i);
   }

   private static int getDistance(BlockState neighbor) {
      if (neighbor.is(BlockTags.LOGS)) {
         return 0;
      } else {
         return !(neighbor.getBlock() instanceof LeavesBlock) && !(neighbor.getBlock() instanceof LeavesFruit) ? 7 : (Integer)neighbor.getValue(DISTANCE);
      }
   }

   protected void createBlockStateDefinition(Builder<Block, BlockState> stateList) {
      stateList.add(new Property[]{DISTANCE, PERSISTENT, AGE});
   }

   public BlockState getStateForPlacement(BlockPlaceContext context) {
      return updateDistance((BlockState)this.defaultBlockState().setValue(PERSISTENT, true), context.getLevel(), context.getClickedPos());
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

   public OffsetType getOffsetType() {
      return OffsetType.NONE;
   }
}
