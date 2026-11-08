package com.conquestrefabricated.content.blocks.block.directional;

import com.conquestrefabricated.content.blocks.util.Interactions;
import com.conquestrefabricated.core.asset.annotation.ItemDescription;
import com.conquestrefabricated.core.block.base.DirectionalShape;
import com.conquestrefabricated.core.block.base.WaterloggedDirectionalShape;
import com.conquestrefabricated.core.block.builder.Props;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.VoxelShape;

public class Directional extends DirectionalShape {
   private final List<VoxelShape> hitBox;

   public Directional(Props props) {
      super(props.toSettings());
      this.hitBox = props.get("hitBox", List.class);
   }

   @Override
   public VoxelShape getShape(BlockState state) {
      return getVoxelShape(this.hitBox, state, DIRECTION);
   }

   private static VoxelShape getVoxelShape(List<VoxelShape> hitBox, BlockState state, DirectionProperty direction) {
      boolean hasSixShapes = hitBox.size() == 6;

      return switch ((Direction)state.getValue(direction)) {
         case EAST -> (VoxelShape)hitBox.get(hasSixShapes ? 1 : 0);
         case SOUTH -> (VoxelShape)hitBox.get(hasSixShapes ? 2 : 0);
         case WEST -> (VoxelShape)hitBox.get(hasSixShapes ? 3 : 0);
         case UP -> (VoxelShape)hitBox.get(hasSixShapes ? 4 : 0);
         case DOWN -> (VoxelShape)hitBox.get(hasSixShapes ? 5 : 0);
         default -> (VoxelShape)hitBox.get(0);
      };
   }

   @ItemDescription(description = "toggle_2")
   public static class Toggle2 extends Directional {
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

   public static class Waterlogged extends WaterloggedDirectionalShape {
      private final List<VoxelShape> hitBox;

      public Waterlogged(Props props) {
         super(props.toSettings());
         this.registerDefaultState((BlockState)((BlockState)this.stateDefinition.any()).setValue(WATERLOGGED, false));
         this.hitBox = props.get("hitBox", List.class);
      }

      @Override
      public VoxelShape getShape(BlockState state) {
         return Directional.getVoxelShape(this.hitBox, state, DIRECTION);
      }
   }

   @ItemDescription(description = "toggle_2")
   public static class WaterloggedToggle2 extends Directional.Waterlogged {
      public static final IntegerProperty TOGGLE = IntegerProperty.create("toggle", 1, 2);

      public WaterloggedToggle2(Props props) {
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
}
