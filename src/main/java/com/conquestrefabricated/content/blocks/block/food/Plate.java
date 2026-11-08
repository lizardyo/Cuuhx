package com.conquestrefabricated.content.blocks.block.food;

import com.conquestrefabricated.content.blocks.BlockSettingsAccessor;
import com.conquestrefabricated.content.blocks.CustomOffsetType;
import com.conquestrefabricated.content.blocks.util.PlacementHelper;
import com.conquestrefabricated.core.asset.annotation.Render;
import com.conquestrefabricated.core.asset.annotation.SpecialOffset;
import com.conquestrefabricated.core.block.builder.SpecialOffsetType;
import com.conquestrefabricated.core.block.properties.ModBlockProperties;
import com.conquestrefabricated.core.block.properties.PropertyVariant;
import com.conquestrefabricated.core.util.RenderLayer;
import net.minecraft.core.BlockPos;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CakeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.OffsetType;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

@Render(RenderLayer.CUTOUT)
@SpecialOffset(offsetType = SpecialOffsetType.XYZ)
public class Plate extends CakeBlock implements PropertyVariant {
   public static final BooleanProperty OFFSET_TOGGLE = ModBlockProperties.OFFSET_TOGGLE;
   protected static final VoxelShape SHAPE = Block.box(0.0, 0.0, 0.0, 16.0, 1.0, 16.0);

   public Plate(Properties properties) {
      super(((BlockSettingsAccessor)properties).setCustomOffsetter(CustomOffsetType.LAYER_XYZ).offsetType(OffsetType.NONE).dynamicShape());
      this.registerDefaultState((BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue(BITES, 6)).setValue(OFFSET_TOGGLE, false));
   }

   public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
      return true;
   }

   @Override
   public Property<?> getVariantProperty() {
      return CakeBlock.BITES;
   }

   public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
      return SHAPE;
   }

   protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
      builder.add(new Property[]{BITES, OFFSET_TOGGLE});
   }

   public InteractionResult use(BlockState state, Level level, BlockPos blockPos, Player player, InteractionHand hand, BlockHitResult hitResult) {
      ItemStack itemstack = player.getItemInHand(hand);
      if (!level.isClientSide) {
         if (this.eatActionResult(level, blockPos, state, itemstack, player) == InteractionResult.SUCCESS) {
            return InteractionResult.SUCCESS;
         }

         if (itemstack.isEmpty()) {
            return InteractionResult.CONSUME;
         }
      }

      return this.eatActionResult(level, blockPos, state, itemstack, player);
   }

   private InteractionResult eatActionResult(Level world, BlockPos pos, BlockState state, ItemStack itemStack, Player playerEntity) {
      if (!playerEntity.canEat(false)) {
         return InteractionResult.PASS;
      }

      playerEntity.awardStat(Stats.EAT_CAKE_SLICE);
      playerEntity.getFoodData().eat(2, 0.1F);
      int i = (Integer)state.getValue(BITES);
      if (itemStack.getItem().isEdible() && (Integer)state.getValue(BITES) != 0) {
         if (!playerEntity.getAbilities().instabuild) {
            itemStack.shrink(1);
         }

         world.setBlock(pos, (BlockState)state.setValue(BITES, (Integer)state.getValue(BITES) - 1), 3);
         return InteractionResult.SUCCESS;
      } else if (i < 6) {
         world.setBlock(pos, (BlockState)state.setValue(BITES, i + 1), 3);
         return InteractionResult.SUCCESS;
      } else {
         return InteractionResult.PASS;
      }
   }

   @NotNull
   public BlockState getStateForPlacement(BlockPlaceContext context) {
      boolean isSlab = PlacementHelper.isFacingSlab(context);
      return (BlockState)super.getStateForPlacement(context).setValue(OFFSET_TOGGLE, isSlab);
   }

   public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
      return state.getValue(OFFSET_TOGGLE) ? Shapes.empty() : super.getCollisionShape(state, worldIn, pos, context);
   }
}
