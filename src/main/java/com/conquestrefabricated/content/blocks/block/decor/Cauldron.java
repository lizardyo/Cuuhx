package com.conquestrefabricated.content.blocks.block.decor;

import com.conquestrefabricated.content.blocks.CustomOffsetType;
import com.conquestrefabricated.content.blocks.block.directional.HorizontalDirectional;
import com.conquestrefabricated.content.blocks.util.CauldronBehavior;
import com.conquestrefabricated.core.asset.annotation.Render;
import com.conquestrefabricated.core.asset.annotation.SpecialOffset;
import com.conquestrefabricated.core.block.builder.Props;
import com.conquestrefabricated.core.block.builder.SpecialOffsetType;
import com.conquestrefabricated.core.util.RenderLayer;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome.Precipitation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.OffsetType;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

@Render(RenderLayer.CUTOUT)
@SpecialOffset(offsetType = SpecialOffsetType.XYZ)
public class Cauldron extends Block {
   private final CauldronBehavior behavior;

   public Cauldron(Props props) {
      super(props.customOffsetType(CustomOffsetType.LAYER_XYZ).offset(OffsetType.NONE).dynamicBounds(true).toSettings());
      List<VoxelShape> hitBox = props.get("hitBox", List.class);
      this.behavior = new CauldronBehavior(hitBox);
      this.registerDefaultState((BlockState)((BlockState)this.stateDefinition.any()).setValue(CauldronBehavior.OFFSET_TOGGLE, false));
   }

   public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
      return state.getValue(CauldronBehavior.OFFSET_TOGGLE)
         ? this.behavior.getCollisionShape(state, worldIn, pos, context)
         : super.getCollisionShape(state, worldIn, pos, context);
   }

   @NotNull
   public BlockState getStateForPlacement(BlockPlaceContext context) {
      Direction facing = context.getClickedFace().getOpposite();
      BlockGetter blockreader = context.getLevel();
      BlockPos pos = context.getClickedPos();
      BlockState state = blockreader.getBlockState(pos.relative(facing));
      Block block = state.getBlock();
      boolean isSlab = this.behavior.calculateSlabOffset(facing, block, state, context);
      return (BlockState)super.getStateForPlacement(context).setValue(CauldronBehavior.OFFSET_TOGGLE, isSlab);
   }

   public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
      return this.behavior.getOutlineShape(state, world, pos, context);
   }

   public void entityInside(BlockState state, Level world, BlockPos pos, Entity entity) {
      this.behavior.onEntityCollision(state, world, pos, entity);
   }

   public InteractionResult use(BlockState state, Level level, BlockPos blockPos, Player player, InteractionHand hand, BlockHitResult hitResult) {
      return this.behavior.onUse(state, level, blockPos, player, hand, hitResult);
   }

   public void handlePrecipitation(BlockState state, Level world, BlockPos pos, Precipitation precipitation) {
      this.behavior.precipitationTick(state, world, pos, precipitation);
   }

   public boolean hasAnalogOutputSignal(BlockState state) {
      return this.behavior.hasComparatorOutput(state);
   }

   public int getAnalogOutputSignal(BlockState state, Level world, BlockPos pos) {
      return this.behavior.getComparatorOutput(state, world, pos);
   }

   protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
      builder.add(new Property[]{CauldronBehavior.LEVEL, CauldronBehavior.OFFSET_TOGGLE});
   }

   public boolean isPathfindable(BlockState state, BlockGetter world, BlockPos pos, PathComputationType navigationType) {
      return false;
   }

   @Render(RenderLayer.CUTOUT)
   @SpecialOffset(offsetType = SpecialOffsetType.XYZ)
   public static class CauldronDirectional extends HorizontalDirectional {
      private final CauldronBehavior behavior;

      public CauldronDirectional(Props props) {
         super(props.customOffsetType(CustomOffsetType.LAYER_XYZ).offset(OffsetType.NONE).dynamicBounds(true));
         List<VoxelShape> hitBox = props.get("hitBox", List.class);
         this.behavior = new CauldronBehavior(hitBox);
         this.registerDefaultState((BlockState)((BlockState)this.stateDefinition.any()).setValue(CauldronBehavior.OFFSET_TOGGLE, false));
      }

      @Override
      public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
         return state.getValue(CauldronBehavior.OFFSET_TOGGLE)
            ? this.behavior.getCollisionShape(state, worldIn, pos, context)
            : super.getCollisionShape(state, worldIn, pos, context);
      }

      @NotNull
      @Override
      public BlockState getStateForPlacement(BlockPlaceContext context) {
         Direction facing = context.getClickedFace().getOpposite();
         BlockGetter blockreader = context.getLevel();
         BlockPos pos = context.getClickedPos();
         BlockState state = blockreader.getBlockState(pos.relative(facing));
         Block block = state.getBlock();
         boolean isSlab = this.behavior.calculateSlabOffset(facing, block, state, context);
         return (BlockState)super.getStateForPlacement(context).setValue(CauldronBehavior.OFFSET_TOGGLE, isSlab);
      }

      @Override
      public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
         return this.behavior.getOutlineShape(state, world, pos, context);
      }

      public void entityInside(BlockState state, Level world, BlockPos pos, Entity entity) {
         this.behavior.onEntityCollision(state, world, pos, entity);
      }

      public InteractionResult use(BlockState state, Level level, BlockPos blockPos, Player player, InteractionHand hand, BlockHitResult hitResult) {
         return this.behavior.onUse(state, level, blockPos, player, hand, hitResult);
      }

      public void handlePrecipitation(BlockState state, Level world, BlockPos pos, Precipitation precipitation) {
         this.behavior.precipitationTick(state, world, pos, precipitation);
      }

      public boolean hasAnalogOutputSignal(BlockState state) {
         return this.behavior.hasComparatorOutput(state);
      }

      public int getAnalogOutputSignal(BlockState state, Level world, BlockPos pos) {
         return this.behavior.getComparatorOutput(state, world, pos);
      }

      @Override
      protected void addProperties(Builder<Block, BlockState> builder) {
         builder.add(new Property[]{CauldronBehavior.LEVEL, CauldronBehavior.OFFSET_TOGGLE});
      }

      public boolean isPathfindable(BlockState state, BlockGetter world, BlockPos pos, PathComputationType navigationType) {
         return false;
      }
   }
}
