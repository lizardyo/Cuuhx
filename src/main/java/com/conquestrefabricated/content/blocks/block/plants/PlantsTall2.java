package com.conquestrefabricated.content.blocks.block.plants;

import com.conquestrefabricated.api.tags.ModTags;
import com.conquestrefabricated.client.gui.config.ConquestConfig;
import com.conquestrefabricated.content.blocks.block.Layer;
import com.conquestrefabricated.content.blocks.block.Slab;
import com.conquestrefabricated.content.blocks.util.Interactions;
import com.conquestrefabricated.content.effects.Effects;
import com.conquestrefabricated.core.asset.annotation.Assets;
import com.conquestrefabricated.core.asset.annotation.Model;
import com.conquestrefabricated.core.asset.annotation.Render;
import com.conquestrefabricated.core.asset.annotation.State;
import com.conquestrefabricated.core.block.builder.Props;
import com.conquestrefabricated.core.util.RenderLayer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

@Assets(
   state = @State(name = "%s", template = "parent_plant_dense_stackable"),
   item = @Model(name = "item/%s", parent = "block/%s_pane_ns", template = "item/parent_round_arch"),
   render = @Render(RenderLayer.CUTOUT),
   block = {
         @Model(name = "block/%s_plant_dense_model_1_a", template = "block/parent_plant_dense_model_1_a"),
         @Model(name = "block/%s_plant_dense_model_1_b", template = "block/parent_plant_dense_model_1_b"),
         @Model(name = "block/%s_plant_dense_model_1_c", template = "block/parent_plant_dense_model_1_c"),
         @Model(name = "block/%s_plant_dense_model_2_a", template = "block/parent_plant_dense_model_2_a"),
         @Model(name = "block/%s_plant_dense_model_2_b", template = "block/parent_plant_dense_model_2_b"),
         @Model(name = "block/%s_plant_dense_model_2_c", template = "block/parent_plant_dense_model_2_c"),
         @Model(name = "block/%s_plant_dense_model_3_a", template = "block/parent_plant_dense_model_3_a"),
         @Model(name = "block/%s_plant_dense_model_3_b", template = "block/parent_plant_dense_model_3_b"),
         @Model(name = "block/%s_plant_dense_model_3_c", template = "block/parent_plant_dense_model_3_c"),
         @Model(name = "block/%s_plant_dense_model_4_a", template = "block/parent_plant_dense_model_4_a"),
         @Model(name = "block/%s_plant_dense_model_4_b", template = "block/parent_plant_dense_model_4_b"),
         @Model(name = "block/%s_plant_dense_model_4_c", template = "block/parent_plant_dense_model_4_c"),
         @Model(name = "block/%s_plant_dense_model_5_a", template = "block/parent_plant_dense_model_5_a"),
         @Model(name = "block/%s_plant_dense_model_5_b", template = "block/parent_plant_dense_model_5_b"),
         @Model(name = "block/%s_plant_dense_model_5_c", template = "block/parent_plant_dense_model_5_c"),
         @Model(name = "block/%s_plant_dense_model_6_a", template = "block/parent_plant_dense_model_6_a"),
         @Model(name = "block/%s_plant_dense_model_6_b", template = "block/parent_plant_dense_model_6_b"),
         @Model(name = "block/%s_plant_dense_model_6_c", template = "block/parent_plant_dense_model_6_c"),
         @Model(name = "block/%s_plant_dense_model_7_a", template = "block/parent_plant_dense_model_7_a"),
         @Model(name = "block/%s_plant_dense_model_7_b", template = "block/parent_plant_dense_model_7_b"),
         @Model(name = "block/%s_plant_dense_model_7_c", template = "block/parent_plant_dense_model_7_c"),
         @Model(name = "block/%s_plant_dense_model_8_a", template = "block/parent_plant_dense_model_8_a"),
         @Model(name = "block/%s_plant_dense_model_8_b", template = "block/parent_plant_dense_model_8_b"),
         @Model(name = "block/%s_plant_dense_model_8_c", template = "block/parent_plant_dense_model_8_c"),
         @Model(name = "block/%s_pane_ns", template = "block/parent_flatpane_ns")
   }
)
public class PlantsTall2 extends Bush {
   public static final VoxelShape TWOTALL = Shapes.box(0.0, 0.0, 0.0, 16.0, 32.0, 16.0);
   private static final IntegerProperty HEIGHT = IntegerProperty.create("height", 1, 2);

   public PlantsTall2(Props properties) {
      super(properties);
   }

   @Override
   public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor world, BlockPos pos, BlockPos neighborPos) {
      return direction == Direction.DOWN && neighborState.isAir() && ConquestConfig.INSTANCE.plantBreaking.get()
         ? Blocks.AIR.defaultBlockState()
         : super.updateShape(state, direction, neighborState, world, pos, neighborPos);
   }

   @Override
   public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
      if (context instanceof EntityCollisionContext entityContext) {
         if (!(entityContext.getEntity() instanceof Player player)) {
            return super.getShape(state, worldIn, pos, context);
         } else {
            return !player.getAbilities().instabuild && (!player.getMainHandItem().is(ModTags.GARDENING_TOOLS) || !player.getAbilities().mayBuild)
               ? Shapes.empty()
               : super.getShape(state, worldIn, pos, context);
         }
      } else {
         return super.getShape(state, worldIn, pos, context);
      }
   }

   public VoxelShape getInteractionShape(BlockState state, BlockGetter world, BlockPos pos) {
      if ((Integer)state.getValue(HEIGHT) == 1) {
         return Shapes.block();
      } else {
         return state.getValue(HEIGHT) == 2 ? TWOTALL : super.getInteractionShape(state, world, pos);
      }
   }

   @Override
   public void entityInside(BlockState state, Level world, BlockPos pos, Entity entity) {
      if (entity instanceof LivingEntity livingEntity && state.is(ModTags.PLANT_SLOWNESS) && (Boolean)ConquestConfig.INSTANCE.plantSlowness.get()) {
         if (livingEntity instanceof Player) {
            if ((Integer)state.getValue(HEIGHT) == 1) {
               livingEntity.addEffect(new MobEffectInstance(Effects.CUSTOM_SLOWNESS, 15, 9, false, false));
            } else if ((Integer)state.getValue(HEIGHT) == 2) {
               livingEntity.addEffect(new MobEffectInstance(Effects.CUSTOM_SLOWNESS, 15, 12, false, false));
            }
         } else if ((Integer)state.getValue(HEIGHT) == 1) {
            livingEntity.addEffect(new MobEffectInstance(Effects.CUSTOM_SLOWNESS, 15, 4, false, false));
         } else if ((Integer)state.getValue(HEIGHT) == 2) {
            livingEntity.addEffect(new MobEffectInstance(Effects.CUSTOM_SLOWNESS, 15, 6, false, false));
         }
      }
   }

   @Override
   public BlockState getStateForPlacement(BlockPlaceContext context) {
      BlockGetter iblockreader = context.getLevel();
      BlockPos blockpos = context.getClickedPos();
      BlockPos down = blockpos.below();
      BlockState blockStateDown = iblockreader.getBlockState(down);
      int layerBlockState = 8;
      if (blockStateDown.hasProperty(Layer.LAYERS) || blockStateDown.hasProperty(Slab.LAYERS) || blockStateDown.hasProperty(LAYERS)) {
         layerBlockState = (Integer)blockStateDown.getValue(LAYERS);
      }

      return (BlockState)super.getStateForPlacement(context).setValue(LAYERS, layerBlockState);
   }

   public InteractionResult use(BlockState state, Level level, BlockPos blockPos, Player player, InteractionHand hand, BlockHitResult hitResult) {
      return Interactions.onUseToggleItem(player, level, blockPos, state, HEIGHT);
   }

   @Override
   protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
      builder.add(new Property[]{HEIGHT, LAYERS, WATERLOGGED, OFFSET_TOGGLE});
   }
}
