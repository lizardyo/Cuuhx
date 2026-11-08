package com.conquestrefabricated.content.blocks.block.decor;

import com.conquestrefabricated.content.blocks.CustomOffsetType;
import com.conquestrefabricated.content.blocks.block.directional.HorizontalDirectional;
import com.conquestrefabricated.content.blocks.util.ChairBehavior;
import com.conquestrefabricated.content.blocks.util.Interactions;
import com.conquestrefabricated.content.blocks.util.Sittable;
import com.conquestrefabricated.core.asset.annotation.ItemDescription;
import com.conquestrefabricated.core.asset.annotation.Render;
import com.conquestrefabricated.core.asset.annotation.SpecialOffset;
import com.conquestrefabricated.core.block.base.ShapeBlock;
import com.conquestrefabricated.core.block.builder.Props;
import com.conquestrefabricated.core.block.builder.SpecialOffsetType;
import com.conquestrefabricated.core.block.properties.ParallelConnectionShape2;
import com.conquestrefabricated.core.util.RenderLayer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.OffsetType;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Chairs {
   public Chairs() {
   }

   @Render(RenderLayer.CUTOUT)
   @SpecialOffset(offsetType = SpecialOffsetType.XYZ)
   public static class Bench extends ParallelConnecting implements EntityBlock, Sittable {
      protected static final VoxelShape SHAPE = Block.box(1.0, 10.0, 1.0, 15.0, 16.0, 15.0);
      public static final VoxelShape SHAPE_NORTH = Block.box(0.0, 0.0, 4.0, 16.0, 12.0, 12.0);
      public static final VoxelShape SHAPE_EAST = Block.box(4.0, 0.0, 0.0, 12.0, 12.0, 16.0);
      public static final EnumProperty<ParallelConnectionShape2> FORM = EnumProperty.create("shape", ParallelConnectionShape2.class);
      private final ChairBehavior behavior;

      public Bench(Props props) {
         super(props);
         double chairHeight = props.get("seatHeight", Double.class);
         this.behavior = new ChairBehavior(chairHeight);
      }

      @Override
      public double getSeatHeight() {
         return this.behavior.getSeatHeight();
      }

      @Override
      public VoxelShape getShape(BlockState state) {
         return switch ((Direction)state.getValue(DIRECTION)) {
            case EAST, WEST -> SHAPE_EAST;
            default -> SHAPE_NORTH;
         };
      }

      public InteractionResult use(BlockState state, Level level, BlockPos blockPos, Player player, InteractionHand hand, BlockHitResult hitResult) {
         return this.behavior
            .handleSittingInteraction(
               state, level, blockPos, player, hand, hitResult, () -> super.use(state, level, blockPos, player, hand, hitResult), null
            );
      }

      @Nullable
      public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
         return this.behavior.createBlockEntity(blockPos, blockState);
      }

      public static class Toggle4 extends Chairs.Bench {
         public static final IntegerProperty TOGGLE = IntegerProperty.create("toggle", 1, 4);
         private final ChairBehavior behavior;

         public Toggle4(Props props) {
            super(props);
            this.behavior = new ChairBehavior(props.get("seatHeight", Double.class));
         }

         @Override
         protected void addProperties(Builder<Block, BlockState> builder) {
            super.addProperties(builder);
            builder.add(new Property[]{TOGGLE});
         }

         @Override
         public InteractionResult use(BlockState state, Level level, BlockPos blockPos, Player player, InteractionHand hand, BlockHitResult hitResult) {
            return this.behavior
               .handleSittingInteraction(
                  state, level, blockPos, player, hand, hitResult, () -> Interactions.onUseToggleItem(player, level, blockPos, state, TOGGLE), 4
               );
         }
      }
   }

   @Render(RenderLayer.CUTOUT)
   @SpecialOffset(offsetType = SpecialOffsetType.XYZ)
   public static class Directional extends HorizontalDirectional.Waterlogged implements EntityBlock, Sittable {
      private final ChairBehavior behavior;

      public Directional(Props props) {
         super(props.customOffsetType(CustomOffsetType.LAYER_XYZ).offset(OffsetType.NONE).dynamicBounds(true));
         double chairHeight = props.get("seatHeight", Double.class);
         this.behavior = new ChairBehavior(chairHeight);
         this.registerDefaultState((BlockState)((BlockState)this.stateDefinition.any()).setValue(ChairBehavior.OFFSET_TOGGLE, false));
      }

      @Override
      public double getSeatHeight() {
         return this.behavior.getSeatHeight();
      }

      @Override
      public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
         return this.behavior.getCollisionShape(state, worldIn, pos, context, super.getCollisionShape(state, worldIn, pos, context));
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
         return (BlockState)super.getStateForPlacement(context).setValue(ChairBehavior.OFFSET_TOGGLE, isSlab);
      }

      public InteractionResult use(BlockState state, Level level, BlockPos blockPos, Player player, InteractionHand hand, BlockHitResult hitResult) {
         return this.behavior
            .handleSittingInteraction(
               state, level, blockPos, player, hand, hitResult, () -> super.use(state, level, blockPos, player, hand, hitResult), null
            );
      }

      @Nullable
      public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
         return this.behavior.createBlockEntity(blockPos, blockState);
      }

      @Override
      protected void addProperties(Builder<Block, BlockState> builder) {
         builder.add(new Property[]{ChairBehavior.OFFSET_TOGGLE});
      }
   }

   @Render(RenderLayer.CUTOUT)
   @SpecialOffset(offsetType = SpecialOffsetType.Y)
   public static class NonDirectional extends ShapeBlock implements EntityBlock, Sittable {
      private final ChairBehavior behavior;

      public NonDirectional(Props properties) {
         super(properties.customOffsetType(CustomOffsetType.LAYER_Y).offset(OffsetType.NONE).dynamicBounds(true));
         double chairHeight = properties.get("seatHeight", Double.class);
         this.behavior = new ChairBehavior(chairHeight);
         this.registerDefaultState((BlockState)((BlockState)this.stateDefinition.any()).setValue(ChairBehavior.OFFSET_TOGGLE, false));
      }

      @Override
      public double getSeatHeight() {
         return this.behavior.getSeatHeight();
      }

      @Override
      public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
         return this.behavior.getCollisionShape(state, worldIn, pos, context, super.getCollisionShape(state, worldIn, pos, context));
      }

      @NotNull
      public BlockState getStateForPlacement(BlockPlaceContext context) {
         Direction facing = context.getClickedFace().getOpposite();
         BlockGetter blockreader = context.getLevel();
         BlockPos pos = context.getClickedPos();
         BlockState state = blockreader.getBlockState(pos.relative(facing));
         Block block = state.getBlock();
         boolean isSlab = this.behavior.calculateSlabOffset(facing, block, state, context);
         return (BlockState)super.getStateForPlacement(context).setValue(ChairBehavior.OFFSET_TOGGLE, isSlab);
      }

      public InteractionResult use(BlockState state, Level level, BlockPos blockPos, Player player, InteractionHand hand, BlockHitResult hitResult) {
         return this.behavior
            .handleSittingInteraction(
               state, level, blockPos, player, hand, hitResult, () -> super.use(state, level, blockPos, player, hand, hitResult), null
            );
      }

      @Nullable
      public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
         return this.behavior.createBlockEntity(blockPos, blockState);
      }

      protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
         builder.add(new Property[]{ChairBehavior.OFFSET_TOGGLE});
      }
   }

   @ItemDescription(description = "toggle_2")
   @Render(RenderLayer.CUTOUT)
   @SpecialOffset(offsetType = SpecialOffsetType.XYZ)
   public static class Toggle2 extends HorizontalDirectional.Waterlogged.Toggle2 implements EntityBlock, Sittable {
      private final ChairBehavior behavior;
      private final double chairHeight;

      public Toggle2(Props props) {
         super(props.customOffsetType(CustomOffsetType.LAYER_XYZ).offset(OffsetType.NONE).dynamicBounds(true));
         this.chairHeight = props.get("seatHeight", Double.class);
         this.behavior = new ChairBehavior(this.chairHeight);
         this.registerDefaultState((BlockState)((BlockState)this.stateDefinition.any()).setValue(ChairBehavior.OFFSET_TOGGLE, false));
      }

      @Override
      public double getSeatHeight() {
         return this.chairHeight;
      }

      @Override
      public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
         return this.behavior.getCollisionShape(state, worldIn, pos, context, super.getCollisionShape(state, worldIn, pos, context));
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
         return (BlockState)super.getStateForPlacement(context).setValue(ChairBehavior.OFFSET_TOGGLE, isSlab);
      }

      @Override
      public InteractionResult use(BlockState state, Level level, BlockPos blockPos, Player player, InteractionHand hand, BlockHitResult hitResult) {
         return this.behavior
            .handleSittingInteraction(
               state, level, blockPos, player, hand, hitResult, () -> Interactions.onUseToggleItem(player, level, blockPos, state, TOGGLE), 3
            );
      }

      @Nullable
      public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
         return this.behavior.createBlockEntity(blockPos, blockState);
      }

      @Override
      protected void addProperties(Builder<Block, BlockState> builder) {
         builder.add(new Property[]{TOGGLE, ChairBehavior.OFFSET_TOGGLE});
      }
   }

   @ItemDescription(description = "toggle_3")
   @Render(RenderLayer.CUTOUT)
   @SpecialOffset(offsetType = SpecialOffsetType.XYZ)
   public static class Toggle3 extends HorizontalDirectional.Waterlogged.Toggle3 implements EntityBlock, Sittable {
      private final ChairBehavior behavior;
      private final double chairHeight;

      public Toggle3(Props props) {
         super(props.customOffsetType(CustomOffsetType.LAYER_XYZ).offset(OffsetType.NONE).dynamicBounds(true));
         this.chairHeight = props.get("seatHeight", Double.class);
         this.behavior = new ChairBehavior(this.chairHeight);
         this.registerDefaultState(
            (BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue(ChairBehavior.OFFSET_TOGGLE, false)).setValue(WATERLOGGED, false)
         );
      }

      @Override
      public double getSeatHeight() {
         return this.chairHeight;
      }

      @Override
      public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
         return this.behavior.getCollisionShape(state, worldIn, pos, context, super.getCollisionShape(state, worldIn, pos, context));
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
         return (BlockState)super.getStateForPlacement(context).setValue(ChairBehavior.OFFSET_TOGGLE, isSlab);
      }

      @Override
      public InteractionResult use(BlockState state, Level level, BlockPos blockPos, Player player, InteractionHand hand, BlockHitResult hitResult) {
         return this.behavior
            .handleSittingInteraction(
               state, level, blockPos, player, hand, hitResult, () -> Interactions.onUseToggleItem(player, level, blockPos, state, TOGGLE), 2
            );
      }

      @Nullable
      public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
         return this.behavior.createBlockEntity(blockPos, blockState);
      }

      @Override
      protected void addProperties(Builder<Block, BlockState> builder) {
         builder.add(new Property[]{TOGGLE, ChairBehavior.OFFSET_TOGGLE});
      }
   }

   @ItemDescription(description = "toggle_4")
   @Render(RenderLayer.CUTOUT)
   @SpecialOffset(offsetType = SpecialOffsetType.XYZ)
   public static class Toggle4 extends HorizontalDirectional.Waterlogged.Toggle4 implements EntityBlock, Sittable {
      private final ChairBehavior behavior;
      private final double chairHeight;

      public Toggle4(Props props) {
         super(props.customOffsetType(CustomOffsetType.LAYER_XYZ).offset(OffsetType.NONE).dynamicBounds(true));
         this.chairHeight = props.get("seatHeight", Double.class);
         this.behavior = new ChairBehavior(this.chairHeight);
         this.registerDefaultState((BlockState)((BlockState)this.stateDefinition.any()).setValue(ChairBehavior.OFFSET_TOGGLE, false));
      }

      @Override
      public double getSeatHeight() {
         return this.chairHeight;
      }

      @Override
      public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
         return this.behavior.getCollisionShape(state, worldIn, pos, context, super.getCollisionShape(state, worldIn, pos, context));
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
         return (BlockState)super.getStateForPlacement(context).setValue(ChairBehavior.OFFSET_TOGGLE, isSlab);
      }

      @Override
      public InteractionResult use(BlockState state, Level level, BlockPos blockPos, Player player, InteractionHand hand, BlockHitResult hitResult) {
         return this.behavior
            .handleSittingInteraction(
               state, level, blockPos, player, hand, hitResult, () -> Interactions.onUseToggleItem(player, level, blockPos, state, TOGGLE), 2
            );
      }

      @Nullable
      public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
         return this.behavior.createBlockEntity(blockPos, blockState);
      }

      @Override
      protected void addProperties(Builder<Block, BlockState> builder) {
         builder.add(new Property[]{TOGGLE, ChairBehavior.OFFSET_TOGGLE});
      }
   }

   @ItemDescription(description = "toggle_5")
   @Render(RenderLayer.CUTOUT)
   @SpecialOffset(offsetType = SpecialOffsetType.XYZ)
   public static class Toggle5 extends HorizontalDirectional.Toggle5 implements EntityBlock, Sittable {
      private final ChairBehavior behavior;
      private final double chairHeight;

      public Toggle5(Props props) {
         super(props.customOffsetType(CustomOffsetType.LAYER_XYZ).offset(OffsetType.NONE).dynamicBounds(true));
         this.chairHeight = props.get("seatHeight", Double.class);
         this.behavior = new ChairBehavior(props.get("seatHeight", Double.class));
         this.registerDefaultState((BlockState)((BlockState)this.stateDefinition.any()).setValue(ChairBehavior.OFFSET_TOGGLE, false));
      }

      @Override
      public double getSeatHeight() {
         return this.chairHeight;
      }

      @Override
      public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
         return this.behavior.getCollisionShape(state, worldIn, pos, context, super.getCollisionShape(state, worldIn, pos, context));
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
         return (BlockState)super.getStateForPlacement(context).setValue(ChairBehavior.OFFSET_TOGGLE, isSlab);
      }

      @Override
      public InteractionResult use(BlockState state, Level level, BlockPos blockPos, Player player, InteractionHand hand, BlockHitResult hitResult) {
         return this.behavior
            .handleSittingInteraction(
               state, level, blockPos, player, hand, hitResult, () -> Interactions.onUseToggleItem(player, level, blockPos, state, TOGGLE), 5
            );
      }

      @Nullable
      public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
         return this.behavior.createBlockEntity(blockPos, blockState);
      }

      @Override
      protected void addProperties(Builder<Block, BlockState> builder) {
         builder.add(new Property[]{ChairBehavior.OFFSET_TOGGLE, TOGGLE});
      }
   }

   @ItemDescription(description = "toggle_6")
   @Render(RenderLayer.CUTOUT)
   @SpecialOffset(offsetType = SpecialOffsetType.XYZ)
   public static class Toggle6 extends HorizontalDirectional.Toggle6 implements EntityBlock, Sittable {
      private final ChairBehavior behavior;
      private final double chairHeight;

      public Toggle6(Props props) {
         super(props.customOffsetType(CustomOffsetType.LAYER_XYZ).offset(OffsetType.NONE).dynamicBounds(true));
         this.chairHeight = props.get("seatHeight", Double.class);
         this.behavior = new ChairBehavior(props.get("seatHeight", Double.class));
         this.registerDefaultState((BlockState)((BlockState)this.stateDefinition.any()).setValue(ChairBehavior.OFFSET_TOGGLE, false));
      }

      @Override
      public double getSeatHeight() {
         return this.chairHeight;
      }

      @Override
      public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
         return this.behavior.getCollisionShape(state, worldIn, pos, context, super.getCollisionShape(state, worldIn, pos, context));
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
         return (BlockState)super.getStateForPlacement(context).setValue(ChairBehavior.OFFSET_TOGGLE, isSlab);
      }

      @Override
      public InteractionResult use(BlockState state, Level level, BlockPos blockPos, Player player, InteractionHand hand, BlockHitResult hitResult) {
         return this.behavior
            .handleSittingInteraction(
               state, level, blockPos, player, hand, hitResult, () -> Interactions.onUseToggleItem(player, level, blockPos, state, TOGGLE), 7
            );
      }

      @Nullable
      public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
         return this.behavior.createBlockEntity(blockPos, blockState);
      }

      @Override
      protected void addProperties(Builder<Block, BlockState> builder) {
         builder.add(new Property[]{ChairBehavior.OFFSET_TOGGLE, TOGGLE});
      }
   }

   @Render(RenderLayer.CUTOUT)
   @SpecialOffset(offsetType = SpecialOffsetType.XYZ)
   public static class WideBench extends Chairs.Bench {
      protected static final VoxelShape SHAPE_FULLPARTIAL = Block.box(1.0, 0.0, 1.0, 15.0, 16.0, 15.0);

      public WideBench(Props props) {
         super(props);
      }

      @Override
      public VoxelShape getShape(BlockState state) {
         return SHAPE_FULLPARTIAL;
      }
   }
}
