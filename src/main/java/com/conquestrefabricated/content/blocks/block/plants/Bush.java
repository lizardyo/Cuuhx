package com.conquestrefabricated.content.blocks.block.plants;

import com.conquestrefabricated.api.tags.ModTags;
import com.conquestrefabricated.client.gui.config.ConquestConfig;
import com.conquestrefabricated.content.blocks.CustomOffsetType;
import com.conquestrefabricated.content.blocks.block.Layer;
import com.conquestrefabricated.content.blocks.block.Slab;
import com.conquestrefabricated.content.effects.Effects;
import com.conquestrefabricated.core.asset.annotation.Render;
import com.conquestrefabricated.core.block.builder.Props;
import com.conquestrefabricated.core.block.properties.ModBlockProperties;
import com.conquestrefabricated.core.block.properties.Waterloggable;
import com.conquestrefabricated.core.util.RenderLayer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
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
import net.minecraft.world.level.block.state.BlockBehaviour.OffsetType;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

@Render(RenderLayer.CUTOUT)
public class Bush extends AbstractBush implements Waterloggable {
   public static final BooleanProperty OFFSET_TOGGLE = ModBlockProperties.OFFSET_TOGGLE;
   public static final IntegerProperty LAYERS = BlockStateProperties.LAYERS;
   private final int slowness;

   public Bush(Props properties) {
      super(properties.customOffsetType(CustomOffsetType.PLANT_XYZ).dynamicBounds(true).toSettings());
      this.slowness = properties.getOrDefault("slowness", Integer.class, 0);
      this.registerDefaultState(
         (BlockState)((BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue(LAYERS, 8)).setValue(WATERLOGGED, false))
            .setValue(OFFSET_TOGGLE, false)
      );
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

   public void entityInside(BlockState state, Level world, BlockPos pos, Entity entity) {
      if (entity instanceof LivingEntity livingEntity
         && state.is(ModTags.PLANT_SLOWNESS)
         && (Boolean)ConquestConfig.INSTANCE.plantSlowness.get()
         && this.slowness > 0) {
         if (livingEntity instanceof Player) {
            livingEntity.addEffect(new MobEffectInstance(Effects.CUSTOM_SLOWNESS, 15, this.slowness, false, false));
         } else if (this.slowness > 1) {
            livingEntity.addEffect(new MobEffectInstance(Effects.CUSTOM_SLOWNESS, 15, this.slowness / 2, false, false));
         }
      }
   }

   public BlockState getStateForPlacement(BlockPlaceContext context) {
      BlockGetter iblockreader = context.getLevel();
      BlockPos blockpos = context.getClickedPos();
      BlockPos down = blockpos.below();
      BlockState blockStateDown = iblockreader.getBlockState(down);
      return !blockStateDown.hasProperty(Layer.LAYERS) && (!blockStateDown.hasProperty(Slab.LAYERS) || blockStateDown.getValue(Slab.TYPE_UPDOWN) != Half.BOTTOM)
         ? (BlockState)super.getStateForPlacement(context).setValue(LAYERS, 8)
         : (BlockState)super.getStateForPlacement(context).setValue(LAYERS, (Integer)blockStateDown.getValue(LAYERS));
   }

   public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn, BlockPos currentPos, BlockPos facingPos) {
      if (facing == Direction.DOWN && facingState.isAir() && (Boolean)ConquestConfig.INSTANCE.plantBreaking.get()) {
         return Blocks.AIR.defaultBlockState();
      }

      BlockPos down = currentPos.below();
      BlockState blockStateDown = worldIn.getBlockState(down);
      return !blockStateDown.hasProperty(Layer.LAYERS) && (!blockStateDown.hasProperty(Slab.LAYERS) || blockStateDown.getValue(Slab.TYPE_UPDOWN) != Half.BOTTOM)
         ? (BlockState)super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos).setValue(LAYERS, 8)
         : (BlockState)super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos).setValue(LAYERS, (Integer)blockStateDown.getValue(LAYERS));
   }

   public OffsetType getOffsetType() {
      return OffsetType.XZ;
   }

   public FluidState getFluidState(BlockState state) {
      return Waterloggable.getFluidState(state);
   }

   protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
      builder.add(new Property[]{WATERLOGGED, LAYERS, OFFSET_TOGGLE});
   }
}
