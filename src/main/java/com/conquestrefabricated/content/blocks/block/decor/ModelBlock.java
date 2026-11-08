package com.conquestrefabricated.content.blocks.block.decor;

import com.conquestrefabricated.content.blocks.CustomOffsetType;
import com.conquestrefabricated.content.blocks.util.Interactions;
import com.conquestrefabricated.content.blocks.util.PlacementHelper;
import com.conquestrefabricated.core.asset.annotation.ItemDescription;
import com.conquestrefabricated.core.asset.annotation.SpecialOffset;
import com.conquestrefabricated.core.block.base.Shape;
import com.conquestrefabricated.core.block.base.WaterloggedShape;
import com.conquestrefabricated.core.block.builder.Props;
import com.conquestrefabricated.core.block.builder.SpecialOffsetType;
import com.conquestrefabricated.core.block.properties.ModBlockProperties;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.OffsetType;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class ModelBlock extends Shape {
   private final List<VoxelShape> hitBox;

   public ModelBlock(Props props) {
      super(props.toSettings());
      this.hitBox = props.get("hitBox", List.class);
   }

   @Override
   public VoxelShape getShape(BlockState state) {
      return this.hitBox.get(0);
   }

   @SpecialOffset(offsetType = SpecialOffsetType.XYZ)
   public static class OffsetXYZ extends ModelBlock.OffsetY {
      public OffsetXYZ(Props props) {
         super(props.customOffsetType(CustomOffsetType.LAYER_XYZ));
      }
   }

   @SpecialOffset(offsetType = SpecialOffsetType.Y)
   public static class OffsetY extends ModelBlock {
      public static final BooleanProperty OFFSET_TOGGLE = ModBlockProperties.OFFSET_TOGGLE;

      public OffsetY(Props props) {
         super(props.customOffsetType(CustomOffsetType.LAYER_Y).offset(OffsetType.NONE).dynamicBounds(true));
         this.registerDefaultState((BlockState)((BlockState)this.stateDefinition.any()).setValue(OFFSET_TOGGLE, false));
      }

      @Override
      public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
         return state.getValue(OFFSET_TOGGLE) ? Shapes.empty() : super.getCollisionShape(state, worldIn, pos, context);
      }

      @NotNull
      public BlockState getStateForPlacement(BlockPlaceContext context) {
         boolean isSlab = PlacementHelper.isFacingSlab(context);
         return (BlockState)super.getStateForPlacement(context).setValue(OFFSET_TOGGLE, isSlab);
      }

      protected final void createBlockStateDefinition(Builder<Block, BlockState> builder) {
         builder.add(new Property[]{OFFSET_TOGGLE});
      }
   }

   @ItemDescription(description = "toggle_2")
   public static class Toggle2 extends ModelBlock.Waterlogged {
      public static final IntegerProperty TOGGLE = IntegerProperty.create("toggle", 1, 2);

      public Toggle2(Props props) {
         super(props);
      }

      @Override
      protected void addProperties(Builder<Block, BlockState> builder) {
         builder.add(new Property[]{TOGGLE});
      }

      public InteractionResult use(BlockState state, Level level, BlockPos blockPos, Player player, InteractionHand hand, BlockHitResult hitResult) {
         return Interactions.onUseToggleItem(player, level, blockPos, state, TOGGLE);
      }
   }

   @ItemDescription(description = "toggle_3")
   public static class Toggle3 extends ModelBlock.Waterlogged {
      public static final IntegerProperty TOGGLE = IntegerProperty.create("toggle", 1, 3);

      public Toggle3(Props props) {
         super(props);
      }

      @Override
      protected void addProperties(Builder<Block, BlockState> builder) {
         builder.add(new Property[]{TOGGLE});
      }

      public InteractionResult use(BlockState state, Level level, BlockPos blockPos, Player player, InteractionHand hand, BlockHitResult hitResult) {
         return Interactions.onUseToggleItem(player, level, blockPos, state, TOGGLE);
      }
   }

   @ItemDescription(description = "toggle_4")
   public static class Toggle4 extends ModelBlock.Waterlogged {
      public static final IntegerProperty TOGGLE = IntegerProperty.create("toggle", 1, 4);

      public Toggle4(Props props) {
         super(props);
      }

      @Override
      protected void addProperties(Builder<Block, BlockState> builder) {
         builder.add(new Property[]{TOGGLE});
      }

      public InteractionResult use(BlockState state, Level level, BlockPos blockPos, Player player, InteractionHand hand, BlockHitResult hitResult) {
         return Interactions.onUseToggleItem(player, level, blockPos, state, TOGGLE);
      }
   }

   public static class Waterlogged extends WaterloggedShape {
      private final List<VoxelShape> hitBox;

      public Waterlogged(Props props) {
         super(props.toSettings());
         this.hitBox = props.get("hitBox", List.class);
         this.registerDefaultState((BlockState)((BlockState)this.stateDefinition.any()).setValue(WATERLOGGED, false));
      }

      @Override
      public VoxelShape getShape(BlockState state) {
         return this.hitBox.get(0);
      }
   }
}
