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
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

@Render(RenderLayer.CUTOUT_MIPPED)
public class LeavesGround extends Block implements SimpleWaterloggedBlock {
   public static final IntegerProperty LAYER = BlockStateProperties.LAYERS;
   public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

   public LeavesGround(Props props) {
      super(props.customOffsetType(CustomOffsetType.LAYER_STATE_Y).dynamicBounds(true).toSettings());
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

   protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
      builder.add(new Property[]{WATERLOGGED, LAYER});
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
}
