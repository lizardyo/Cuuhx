package com.conquestrefabricated.content.blocks.block.directional;

import com.conquestrefabricated.content.blocks.util.Interactions;
import com.conquestrefabricated.content.blocks.util.PlacementHelper;
import com.conquestrefabricated.core.asset.annotation.Assets;
import com.conquestrefabricated.core.asset.annotation.ItemDescription;
import com.conquestrefabricated.core.asset.annotation.Model;
import com.conquestrefabricated.core.asset.annotation.State;
import com.conquestrefabricated.core.block.base.HorizontalDirectionalShape;
import com.conquestrefabricated.core.block.base.Shape;
import com.conquestrefabricated.core.block.base.WaterloggedHorizontalDirectionalShape;
import com.conquestrefabricated.core.block.builder.Props;
import java.util.Arrays;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.VoxelShape;

public class Half extends Shape {
   public static final EnumProperty<net.minecraft.world.level.block.state.properties.Half> TYPE_UPDOWN = EnumProperty.create(
      "type", net.minecraft.world.level.block.state.properties.Half.class
   );
   private static final VoxelShape BOTTOM_SHAPE = Block.box(1.0, 0.0, 1.0, 15.0, 8.0, 15.0);
   private static final VoxelShape TOP_SHAPE = Block.box(1.0, 8.0, 1.0, 15.0, 16.0, 15.0);

   public Half(Properties properties) {
      super(properties);
      this.registerDefaultState((BlockState)((BlockState)this.stateDefinition.any()).setValue(TYPE_UPDOWN, net.minecraft.world.level.block.state.properties.Half.BOTTOM));
   }

   public BlockState getStateForPlacement(BlockPlaceContext context) {
      BlockState state2 = (BlockState)this.defaultBlockState().setValue(TYPE_UPDOWN, net.minecraft.world.level.block.state.properties.Half.BOTTOM);
      Direction facing = context.getClickedFace();
      return facing == Direction.DOWN || facing != Direction.UP && context.getClickLocation().y - context.getClickedPos().getY() > 0.5
         ? (BlockState)state2.setValue(TYPE_UPDOWN, net.minecraft.world.level.block.state.properties.Half.TOP)
         : state2;
   }

   protected final void createBlockStateDefinition(Builder<Block, BlockState> builder) {
      builder.add(new Property[]{TYPE_UPDOWN});
   }

   @Override
   public VoxelShape getShape(BlockState state) {
      return state.getValue(TYPE_UPDOWN) == net.minecraft.world.level.block.state.properties.Half.BOTTOM ? BOTTOM_SHAPE : TOP_SHAPE;
   }

   public static class Directional extends HorizontalDirectionalShape {
      public Directional(Properties properties) {
         super(properties);
         this.registerDefaultState(
            (BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue(DIRECTION, Direction.NORTH))
               .setValue(Half.TYPE_UPDOWN, net.minecraft.world.level.block.state.properties.Half.BOTTOM)
         );
      }

      @Override
      public VoxelShape getInteractionShape(BlockState state, BlockGetter world, BlockPos pos) {
         return this.getShape(state);
      }

      @Override
      public BlockState getStateForPlacement(BlockPlaceContext context) {
         Direction facingHorizontal = context.getHorizontalDirection().getOpposite();
         BlockState state2 = (BlockState)((BlockState)this.defaultBlockState().setValue(DIRECTION, facingHorizontal))
            .setValue(Half.TYPE_UPDOWN, net.minecraft.world.level.block.state.properties.Half.BOTTOM);
         Direction facing = context.getClickedFace();
         return facing == Direction.DOWN || facing != Direction.UP && context.getClickLocation().y - context.getClickedPos().getY() > 0.5
            ? (BlockState)state2.setValue(Half.TYPE_UPDOWN, net.minecraft.world.level.block.state.properties.Half.TOP)
            : state2;
      }

      @Override
      protected void addProperties(Builder<Block, BlockState> container) {
         container.add(new Property[]{Half.TYPE_UPDOWN});
      }

      @Override
      public VoxelShape getShape(BlockState state) {
         return state.getValue(Half.TYPE_UPDOWN) == net.minecraft.world.level.block.state.properties.Half.BOTTOM ? Half.BOTTOM_SHAPE : Half.TOP_SHAPE;
      }

      @ItemDescription(description = "toggle_2")
      public static class Toggle2 extends Half.Directional {
         public static final IntegerProperty TOGGLE = IntegerProperty.create("toggle", 1, 2);

         public Toggle2(Properties properties) {
            super(properties);
         }

         @Override
         protected void addProperties(Builder<Block, BlockState> builder) {
            super.addProperties(builder);
            builder.add(new Property[]{TOGGLE});
         }

         public InteractionResult use(BlockState state, Level level, BlockPos blockPos, Player player, InteractionHand hand, BlockHitResult hitResult) {
            return Interactions.onUseToggleItem(player, level, blockPos, state, TOGGLE);
         }

         @ItemDescription(description = "toggle_2")
         public static class Corner extends Half.Directional.Toggle2 {
            public Corner(Properties props) {
               super(props);
            }

            @Override
            public BlockState mirror(BlockState state, Mirror mirrorIn) {
               return PlacementHelper.mirrorCornerDirection(state, mirrorIn, DIRECTION, (x$0, x$1) -> super.mirror(x$0, x$1));
            }
         }
      }

      @ItemDescription(description = "toggle_3")
      public static class Toggle3 extends Half.Directional {
         public static final IntegerProperty TOGGLE = IntegerProperty.create("toggle", 1, 3);

         public Toggle3(Properties properties) {
            super(properties);
         }

         @Override
         protected void addProperties(Builder<Block, BlockState> builder) {
            super.addProperties(builder);
            builder.add(new Property[]{TOGGLE});
         }

         public InteractionResult use(BlockState state, Level level, BlockPos blockPos, Player player, InteractionHand hand, BlockHitResult hitResult) {
            return Interactions.onUseToggleItem(player, level, blockPos, state, TOGGLE);
         }

         @ItemDescription(description = "toggle_3")
         public static class Corner extends Half.Directional.Toggle3 {
            public Corner(Properties props) {
               super(props);
            }

            @Override
            public BlockState mirror(BlockState state, Mirror mirrorIn) {
               return PlacementHelper.mirrorCornerDirection(state, mirrorIn, DIRECTION, (x$0, x$1) -> super.mirror(x$0, x$1));
            }
         }
      }

      @ItemDescription(description = "toggle_4")
      public static class Toggle4 extends Half.Directional {
         public static final IntegerProperty TOGGLE = IntegerProperty.create("toggle", 1, 4);

         public Toggle4(Properties properties) {
            super(properties);
         }

         @Override
         protected void addProperties(Builder<Block, BlockState> builder) {
            super.addProperties(builder);
            builder.add(new Property[]{TOGGLE});
         }

         public InteractionResult use(BlockState state, Level level, BlockPos blockPos, Player player, InteractionHand hand, BlockHitResult hitResult) {
            return Interactions.onUseToggleItem(player, level, blockPos, state, TOGGLE);
         }
      }
   }

   public static class DirectionalWaterlogged extends WaterloggedHorizontalDirectionalShape {
      private final List<VoxelShape> hitBox;

      public DirectionalWaterlogged(Props props) {
         super(props.toSettings());
         this.hitBox = props.getOrDefault("hitBox", List.class, Arrays.asList(Half.BOTTOM_SHAPE, Half.TOP_SHAPE));
         this.registerDefaultState(
            (BlockState)((BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue(DIRECTION, Direction.NORTH))
                  .setValue(Half.TYPE_UPDOWN, net.minecraft.world.level.block.state.properties.Half.BOTTOM))
               .setValue(WATERLOGGED, false)
         );
      }

      @Override
      public BlockState getStateForPlacement(BlockPlaceContext context) {
         FluidState fluid = context.getLevel().getFluidState(context.getClickedPos());
         Direction facingHorizontal = context.getHorizontalDirection().getOpposite();
         BlockState state2 = (BlockState)((BlockState)((BlockState)this.defaultBlockState().setValue(DIRECTION, facingHorizontal))
               .setValue(Half.TYPE_UPDOWN, net.minecraft.world.level.block.state.properties.Half.BOTTOM))
            .setValue(WATERLOGGED, fluid.getType() == Fluids.WATER);
         Direction facing = context.getClickedFace();
         return facing == Direction.DOWN || facing != Direction.UP && context.getClickLocation().y - context.getClickedPos().getY() > 0.5
            ? (BlockState)state2.setValue(Half.TYPE_UPDOWN, net.minecraft.world.level.block.state.properties.Half.TOP)
            : state2;
      }

      @Override
      protected void addProperties(Builder<Block, BlockState> container) {
         container.add(new Property[]{Half.TYPE_UPDOWN});
      }

      @Override
      public VoxelShape getShape(BlockState state) {
         int shapesAmount = this.hitBox.size();
         switch (shapesAmount) {
            case 0:
            default:
               return this.hitBox.get(0);
            case 2:
               if (state.getValue(Half.TYPE_UPDOWN) == net.minecraft.world.level.block.state.properties.Half.BOTTOM) {
                  return this.hitBox.get(0);
               }

               return this.hitBox.get(1);
            case 8:
               if (state.getValue(Half.TYPE_UPDOWN) == net.minecraft.world.level.block.state.properties.Half.BOTTOM) {
                  return switch ((Direction)state.getValue(DIRECTION)) {
                     case EAST -> (VoxelShape)this.hitBox.get(1);
                     case SOUTH -> (VoxelShape)this.hitBox.get(2);
                     case WEST -> (VoxelShape)this.hitBox.get(3);
                     default -> (VoxelShape)this.hitBox.get(0);
                  };
               } else {
                  return switch ((Direction)state.getValue(DIRECTION)) {
                     case EAST -> (VoxelShape)this.hitBox.get(5);
                     case SOUTH -> (VoxelShape)this.hitBox.get(6);
                     case WEST -> (VoxelShape)this.hitBox.get(7);
                     default -> (VoxelShape)this.hitBox.get(4);
                  };
               }
         }
      }

      @Assets(
         state = @State(name = "%s_slab_directional", template = "parent_slab"),
         item = @Model(name = "item/%s_slab_directional", parent = "block/%s_slab", template = "item/acacia_slab"),
         block = @Model(name = "block/%s_slab_directional", template = "block/orientable")
      )
      public static class FamilyVariant extends Half.DirectionalWaterlogged {
         public FamilyVariant(Props properties) {
            super(properties);
         }
      }
   }
}
