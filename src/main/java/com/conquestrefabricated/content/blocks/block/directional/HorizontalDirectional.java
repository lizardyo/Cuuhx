package com.conquestrefabricated.content.blocks.block.directional;

import com.conquestrefabricated.api.tags.ModTags;
import com.conquestrefabricated.content.blocks.BlockVoxelShapes;
import com.conquestrefabricated.content.blocks.CustomOffsetType;
import com.conquestrefabricated.content.blocks.util.Interactions;
import com.conquestrefabricated.content.blocks.util.PlacementHelper;
import com.conquestrefabricated.core.asset.annotation.ItemDescription;
import com.conquestrefabricated.core.block.base.HorizontalDirectionalShape;
import com.conquestrefabricated.core.block.base.WaterloggedHorizontalDirectionalShape;
import com.conquestrefabricated.core.block.builder.Props;
import com.conquestrefabricated.core.block.properties.ModBlockProperties;
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
import net.minecraft.world.level.block.state.BlockBehaviour.OffsetType;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class HorizontalDirectional extends HorizontalDirectionalShape {
   private final List<VoxelShape> hitBox;

   public HorizontalDirectional(Props props) {
      super(props.toSettings());
      this.hitBox = props.getOrDefault("hitBox", List.class, BlockVoxelShapes.cubePartialShape);
   }

   @Override
   public VoxelShape getShape(BlockState state) {
      boolean hasFourShapes = this.hitBox.size() == 4;
      switch ((Direction)state.getValue(DIRECTION)) {
         case EAST:
            return this.hitBox.get(hasFourShapes ? 1 : 0);
         case SOUTH:
            return this.hitBox.get(hasFourShapes ? 2 : 0);
         case WEST:
            return this.hitBox.get(hasFourShapes ? 3 : 0);
         default:
            return this.hitBox.get(0);
      }
   }

   public static class OffsetXYZ extends HorizontalDirectional {
      public static final BooleanProperty OFFSET_TOGGLE = ModBlockProperties.OFFSET_TOGGLE;

      public OffsetXYZ(Props props) {
         super(props.customOffsetType(CustomOffsetType.LAYER_XYZ).offset(OffsetType.NONE).dynamicBounds(true));
         this.registerDefaultState((BlockState)((BlockState)this.stateDefinition.any()).setValue(OFFSET_TOGGLE, false));
      }

      @Override
      public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
         return state.getValue(OFFSET_TOGGLE) ? Shapes.empty() : super.getCollisionShape(state, worldIn, pos, context);
      }

      @NotNull
      @Override
      public BlockState getStateForPlacement(BlockPlaceContext context) {
         boolean isSlab = PlacementHelper.isFacingSlab(context);
         return (BlockState)super.getStateForPlacement(context).setValue(OFFSET_TOGGLE, isSlab);
      }

      @Override
      protected void addProperties(Builder<Block, BlockState> builder) {
         builder.add(new Property[]{OFFSET_TOGGLE});
      }

      @ItemDescription(description = "toggle_10")
      public static class Toggle10 extends HorizontalDirectional.OffsetXYZ {
         public static final IntegerProperty TOGGLE = IntegerProperty.create("toggle", 1, 10);

         public Toggle10(Props props) {
            super(props);
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

      @ItemDescription(description = "toggle_2")
      public static class Toggle2 extends HorizontalDirectional.OffsetXYZ {
         public static final IntegerProperty TOGGLE = IntegerProperty.create("toggle", 1, 2);

         public Toggle2(Props props) {
            super(props);
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
         public static class Positions3 extends HorizontalDirectional.OffsetXYZ.Toggle2 {
            public static final IntegerProperty POSITION = IntegerProperty.create("position", 1, 3);

            public Positions3(Props props) {
               super(props);
            }

            @Override
            protected void addProperties(Builder<Block, BlockState> builder) {
               super.addProperties(builder);
               builder.add(new Property[]{POSITION});
            }

            @Override
            public InteractionResult use(BlockState state, Level level, BlockPos blockPos, Player player, InteractionHand hand, BlockHitResult hitResult) {
               if (player.getAbilities().instabuild) {
                  if (player.isShiftKeyDown()) {
                     level.setBlock(blockPos, (BlockState)state.cycle(POSITION), 3);
                     return InteractionResult.SUCCESS;
                  } else {
                     level.setBlock(blockPos, (BlockState)state.cycle(TOGGLE), 3);
                     return InteractionResult.SUCCESS;
                  }
               } else if (player.getMainHandItem().is(ModTags.CYCLING_TOOLS)) {
                  if (player.isShiftKeyDown()) {
                     level.setBlock(blockPos, (BlockState)state.cycle(POSITION), 3);
                     return InteractionResult.SUCCESS;
                  } else {
                     level.setBlock(blockPos, (BlockState)state.cycle(TOGGLE), 3);
                     return InteractionResult.SUCCESS;
                  }
               } else {
                  return InteractionResult.FAIL;
               }
            }
         }
      }

      @ItemDescription(description = "toggle_3")
      public static class Toggle3 extends HorizontalDirectional.OffsetXYZ {
         public static final IntegerProperty TOGGLE = IntegerProperty.create("toggle", 1, 3);

         public Toggle3(Props props) {
            super(props);
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
         public static class Positions4 extends HorizontalDirectional.OffsetXYZ.Toggle3 {
            public static final IntegerProperty POSITION = IntegerProperty.create("position", 1, 4);

            public Positions4(Props props) {
               super(props);
            }

            @Override
            protected void addProperties(Builder<Block, BlockState> builder) {
               super.addProperties(builder);
               builder.add(new Property[]{POSITION});
            }

            @Override
            public InteractionResult use(BlockState state, Level level, BlockPos blockPos, Player player, InteractionHand hand, BlockHitResult hitResult) {
               if (player.getAbilities().instabuild) {
                  if (player.isShiftKeyDown()) {
                     level.setBlock(blockPos, (BlockState)state.cycle(POSITION), 3);
                     return InteractionResult.SUCCESS;
                  } else {
                     level.setBlock(blockPos, (BlockState)state.cycle(TOGGLE), 3);
                     return InteractionResult.SUCCESS;
                  }
               } else if (player.getMainHandItem().is(ModTags.CYCLING_TOOLS)) {
                  if (player.isShiftKeyDown()) {
                     level.setBlock(blockPos, (BlockState)state.cycle(POSITION), 3);
                     return InteractionResult.SUCCESS;
                  } else {
                     level.setBlock(blockPos, (BlockState)state.cycle(TOGGLE), 3);
                     return InteractionResult.SUCCESS;
                  }
               } else {
                  return InteractionResult.FAIL;
               }
            }
         }
      }

      @ItemDescription(description = "toggle_4")
      public static class Toggle4 extends HorizontalDirectional.OffsetXYZ {
         public static final IntegerProperty TOGGLE = IntegerProperty.create("toggle", 1, 4);

         public Toggle4(Props props) {
            super(props);
         }

         @Override
         protected void addProperties(Builder<Block, BlockState> builder) {
            super.addProperties(builder);
            builder.add(new Property[]{TOGGLE});
         }

         public InteractionResult use(BlockState state, Level level, BlockPos blockPos, Player player, InteractionHand hand, BlockHitResult hitResult) {
            return Interactions.onUseToggleItem(player, level, blockPos, state, TOGGLE);
         }

         @ItemDescription(description = "toggle_4")
         public static class Positions5 extends HorizontalDirectional.OffsetXYZ.Toggle4 {
            public static final IntegerProperty POSITION = IntegerProperty.create("position", 1, 5);

            public Positions5(Props props) {
               super(props);
            }

            @Override
            protected void addProperties(Builder<Block, BlockState> builder) {
               super.addProperties(builder);
               builder.add(new Property[]{POSITION});
            }

            @Override
            public InteractionResult use(BlockState state, Level level, BlockPos blockPos, Player player, InteractionHand hand, BlockHitResult hitResult) {
               if (player.getAbilities().instabuild) {
                  if (player.isShiftKeyDown()) {
                     level.setBlock(blockPos, (BlockState)state.cycle(POSITION), 3);
                     return InteractionResult.SUCCESS;
                  } else {
                     level.setBlock(blockPos, (BlockState)state.cycle(TOGGLE), 3);
                     return InteractionResult.SUCCESS;
                  }
               } else if (player.getMainHandItem().is(ModTags.CYCLING_TOOLS)) {
                  if (player.isShiftKeyDown()) {
                     level.setBlock(blockPos, (BlockState)state.cycle(POSITION), 3);
                     return InteractionResult.SUCCESS;
                  } else {
                     level.setBlock(blockPos, (BlockState)state.cycle(TOGGLE), 3);
                     return InteractionResult.SUCCESS;
                  }
               } else {
                  return InteractionResult.FAIL;
               }
            }
         }
      }

      @ItemDescription(description = "toggle_5")
      public static class Toggle5 extends HorizontalDirectional.OffsetXYZ {
         public static final IntegerProperty TOGGLE = IntegerProperty.create("toggle", 1, 5);

         public Toggle5(Props props) {
            super(props);
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

      @ItemDescription(description = "toggle_6")
      public static class Toggle6 extends HorizontalDirectional.OffsetXYZ {
         public static final IntegerProperty TOGGLE = IntegerProperty.create("toggle", 1, 6);

         public Toggle6(Props props) {
            super(props);
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

      @ItemDescription(description = "toggle_7")
      public static class Toggle7 extends HorizontalDirectional.OffsetXYZ {
         public static final IntegerProperty TOGGLE = IntegerProperty.create("toggle", 1, 7);

         public Toggle7(Props props) {
            super(props);
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

      @ItemDescription(description = "toggle_8")
      public static class Toggle8 extends HorizontalDirectional.OffsetXYZ {
         public static final IntegerProperty TOGGLE = IntegerProperty.create("toggle", 1, 8);

         public Toggle8(Props props) {
            super(props);
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

      @ItemDescription(description = "toggle_8")
      public static class Toggle8Position2 extends HorizontalDirectional.OffsetXYZ {
         public static final IntegerProperty TOGGLE = IntegerProperty.create("toggle", 1, 8);
         public static final IntegerProperty POSITION = IntegerProperty.create("position", 1, 2);

         public Toggle8Position2(Props props) {
            super(props);
         }

         @Override
         protected void addProperties(Builder<Block, BlockState> builder) {
            super.addProperties(builder);
            builder.add(new Property[]{TOGGLE, POSITION});
         }

         public InteractionResult use(BlockState state, Level level, BlockPos blockPos, Player player, InteractionHand hand, BlockHitResult hitResult) {
            if (player.getAbilities().instabuild) {
               if (player.isShiftKeyDown()) {
                  level.setBlock(blockPos, (BlockState)state.cycle(POSITION), 3);
                  return InteractionResult.SUCCESS;
               } else {
                  level.setBlock(blockPos, (BlockState)state.cycle(TOGGLE), 3);
                  return InteractionResult.SUCCESS;
               }
            } else if (player.getMainHandItem().is(ModTags.CYCLING_TOOLS)) {
               if (player.isShiftKeyDown()) {
                  level.setBlock(blockPos, (BlockState)state.cycle(POSITION), 3);
                  return InteractionResult.SUCCESS;
               } else {
                  level.setBlock(blockPos, (BlockState)state.cycle(TOGGLE), 3);
                  return InteractionResult.SUCCESS;
               }
            } else {
               return InteractionResult.FAIL;
            }
         }
      }
   }

   public static class OffsetXZ extends HorizontalDirectional.OffsetXYZ {
      public OffsetXZ(Props props) {
         super(props.customOffsetType(CustomOffsetType.LAYER_XZ));
      }

      public static class Hinge extends HorizontalDirectional.OffsetXZ {
         public static final EnumProperty<DoorHingeSide> HINGE = BlockStateProperties.DOOR_HINGE;
         private final List<VoxelShape> hitBox;

         public Hinge(Props props) {
            super(props);
            this.registerDefaultState((BlockState)((BlockState)this.stateDefinition.any()).setValue(HINGE, DoorHingeSide.LEFT));
            this.hitBox = props.get("hitBox", List.class);
         }

         @NotNull
         @Override
         public BlockState getStateForPlacement(BlockPlaceContext context) {
            return (BlockState)((BlockState)super.getStateForPlacement(context).setValue(DIRECTION, context.getHorizontalDirection().getOpposite()))
               .setValue(HINGE, this.getHingeSide(context));
         }

         @Override
         public VoxelShape getShape(BlockState state) {
            boolean hasFourShapes = this.hitBox.size() == 4;
            if (state.getValue(HINGE) == DoorHingeSide.LEFT) {
               switch ((Direction)state.getValue(DIRECTION)) {
                  case EAST:
                     return this.hitBox.get(hasFourShapes ? 1 : 0);
                  case SOUTH:
                     return this.hitBox.get(hasFourShapes ? 2 : 0);
                  case WEST:
                     return this.hitBox.get(hasFourShapes ? 3 : 0);
                  case NORTH:
                  default:
                     return this.hitBox.get(0);
               }
            } else {
               switch ((Direction)state.getValue(DIRECTION)) {
                  case EAST:
                     return this.hitBox.get(hasFourShapes ? 2 : 0);
                  case SOUTH:
                     return this.hitBox.get(hasFourShapes ? 3 : 0);
                  case WEST:
                     return this.hitBox.get(0);
                  case NORTH:
                  default:
                     return this.hitBox.get(hasFourShapes ? 1 : 0);
               }
            }
         }

         private DoorHingeSide getHingeSide(BlockPlaceContext context) {
            BlockGetter iblockreader = context.getLevel();
            BlockPos blockpos = context.getClickedPos();
            Direction direction = context.getHorizontalDirection();
            BlockPos blockpos1 = blockpos.above();
            Direction direction1 = direction.getCounterClockWise();
            BlockPos blockpos2 = blockpos.relative(direction1);
            BlockState blockstate = iblockreader.getBlockState(blockpos2);
            BlockPos blockpos3 = blockpos1.relative(direction1);
            BlockState blockstate1 = iblockreader.getBlockState(blockpos3);
            Direction direction2 = direction.getClockWise();
            BlockPos blockpos4 = blockpos.relative(direction2);
            BlockState blockstate2 = iblockreader.getBlockState(blockpos4);
            BlockPos blockpos5 = blockpos1.relative(direction2);
            BlockState blockstate3 = iblockreader.getBlockState(blockpos5);
            int i = (blockstate.isCollisionShapeFullBlock(iblockreader, blockpos2) ? -1 : 0)
               + (blockstate1.isCollisionShapeFullBlock(iblockreader, blockpos3) ? -1 : 0)
               + (blockstate2.isCollisionShapeFullBlock(iblockreader, blockpos4) ? 1 : 0)
               + (blockstate3.isCollisionShapeFullBlock(iblockreader, blockpos5) ? 1 : 0);
            if (i <= 0) {
               if (i != 0) {
                  return DoorHingeSide.LEFT;
               }

               int j = direction.getStepX();
               int k = direction.getStepZ();
               Vec3 vec3d = context.getClickLocation();
               double d0 = vec3d.x - blockpos.getX();
               double d1 = vec3d.z - blockpos.getZ();
               return j < 0 && d1 < 0.5 || j > 0 && d1 > 0.5 || k < 0 && d0 > 0.5 || k > 0 && d0 < 0.5 ? DoorHingeSide.RIGHT : DoorHingeSide.LEFT;
            } else {
               return DoorHingeSide.RIGHT;
            }
         }

         @Override
         protected void addProperties(Builder<Block, BlockState> builder) {
            super.addProperties(builder);
            builder.add(new Property[]{HINGE});
         }
      }

      @ItemDescription(description = "toggle_5")
      public static class Toggle5 extends HorizontalDirectional.OffsetXZ {
         public static final IntegerProperty TOGGLE = IntegerProperty.create("toggle", 1, 5);

         public Toggle5(Props props) {
            super(props);
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

   @ItemDescription(description = "toggle_2")
   public static class Toggle2 extends HorizontalDirectional {
      public static final IntegerProperty TOGGLE = IntegerProperty.create("toggle", 1, 2);

      public Toggle2(Props props) {
         super(props);
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
      public static class Corner extends HorizontalDirectional.Toggle2 {
         public Corner(Props props) {
            super(props);
         }

         @Override
         public BlockState mirror(BlockState state, Mirror mirrorIn) {
            return PlacementHelper.mirrorCornerDirection(state, mirrorIn, DIRECTION, (x$0, x$1) -> super.mirror(x$0, x$1));
         }
      }
   }

   @ItemDescription(description = "toggle_3")
   public static class Toggle3 extends HorizontalDirectional {
      public static final IntegerProperty TOGGLE = IntegerProperty.create("toggle", 1, 3);

      public Toggle3(Props props) {
         super(props);
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

   @ItemDescription(description = "toggle_4")
   public static class Toggle4 extends HorizontalDirectional {
      public static final IntegerProperty TOGGLE = IntegerProperty.create("toggle", 1, 4);

      public Toggle4(Props props) {
         super(props);
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

   @ItemDescription(description = "toggle_5")
   public static class Toggle5 extends HorizontalDirectional {
      public static final IntegerProperty TOGGLE = IntegerProperty.create("toggle", 1, 5);

      public Toggle5(Props props) {
         super(props);
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

   @ItemDescription(description = "toggle_6")
   public static class Toggle6 extends HorizontalDirectional {
      public static final IntegerProperty TOGGLE = IntegerProperty.create("toggle", 1, 6);

      public Toggle6(Props props) {
         super(props);
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

   public static class Waterlogged extends WaterloggedHorizontalDirectionalShape {
      private final List<VoxelShape> hitBox;

      public Waterlogged(Props props) {
         super(props.toSettings());
         this.hitBox = props.get("hitBox", List.class);
      }

      @Override
      public VoxelShape getShape(BlockState state) {
         boolean hasFourShapes = this.hitBox.size() == 4;

         return switch ((Direction)state.getValue(DIRECTION)) {
            case EAST -> (VoxelShape)this.hitBox.get(hasFourShapes ? 1 : 0);
            case SOUTH -> (VoxelShape)this.hitBox.get(hasFourShapes ? 2 : 0);
            case WEST -> (VoxelShape)this.hitBox.get(hasFourShapes ? 3 : 0);
            default -> (VoxelShape)this.hitBox.get(0);
         };
      }

      public static class OffsetXYZ extends HorizontalDirectional.Waterlogged {
         public static final BooleanProperty OFFSET_TOGGLE = ModBlockProperties.OFFSET_TOGGLE;

         public OffsetXYZ(Props props) {
            super(props.customOffsetType(CustomOffsetType.LAYER_XYZ).offset(OffsetType.NONE).dynamicBounds(true));
            this.registerDefaultState((BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue(OFFSET_TOGGLE, false)).setValue(WATERLOGGED, false));
         }

         @Override
         public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
            return state.getValue(OFFSET_TOGGLE) ? Shapes.empty() : super.getCollisionShape(state, worldIn, pos, context);
         }

         @NotNull
         @Override
         public BlockState getStateForPlacement(BlockPlaceContext context) {
            boolean isSlab = PlacementHelper.isFacingSlab(context);
            return (BlockState)super.getStateForPlacement(context).setValue(OFFSET_TOGGLE, isSlab);
         }

         @Override
         protected void addProperties(Builder<Block, BlockState> builder) {
            super.addProperties(builder);
            builder.add(new Property[]{OFFSET_TOGGLE});
         }

         @ItemDescription(description = "toggle_2")
         public static class Toggle2 extends HorizontalDirectional.Waterlogged.OffsetXYZ {
            public static final IntegerProperty TOGGLE = IntegerProperty.create("toggle", 1, 2);

            public Toggle2(Props props) {
               super(props);
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

         @ItemDescription(description = "toggle_3")
         public static class Toggle3 extends HorizontalDirectional.Waterlogged.OffsetXYZ {
            public static final IntegerProperty TOGGLE = IntegerProperty.create("toggle", 1, 3);

            public Toggle3(Props props) {
               super(props);
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

         @ItemDescription(description = "toggle_4")
         public static class Toggle4 extends HorizontalDirectional.Waterlogged.OffsetXYZ {
            public static final IntegerProperty TOGGLE = IntegerProperty.create("toggle", 1, 4);

            public Toggle4(Props props) {
               super(props);
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

         @ItemDescription(description = "toggle_5")
         public static class Toggle5 extends HorizontalDirectional.Waterlogged.OffsetXYZ {
            public static final IntegerProperty TOGGLE = IntegerProperty.create("toggle", 1, 5);

            public Toggle5(Props props) {
               super(props);
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

         @ItemDescription(description = "toggle_6")
         public static class Toggle6 extends HorizontalDirectional.Waterlogged.OffsetXYZ {
            public static final IntegerProperty TOGGLE = IntegerProperty.create("toggle", 1, 6);

            public Toggle6(Props props) {
               super(props);
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

         @ItemDescription(description = "toggle_7")
         public static class Toggle7 extends HorizontalDirectional.Waterlogged.OffsetXYZ {
            public static final IntegerProperty TOGGLE = IntegerProperty.create("toggle", 1, 7);

            public Toggle7(Props props) {
               super(props);
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

         @ItemDescription(description = "toggle_8")
         public static class Toggle8 extends HorizontalDirectional.Waterlogged.OffsetXYZ {
            public static final IntegerProperty TOGGLE = IntegerProperty.create("toggle", 1, 8);

            public Toggle8(Props props) {
               super(props);
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

      @ItemDescription(description = "toggle_2")
      public static class Toggle2 extends HorizontalDirectional.Waterlogged {
         public static final IntegerProperty TOGGLE = IntegerProperty.create("toggle", 1, 2);

         public Toggle2(Props props) {
            super(props);
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

      @ItemDescription(description = "toggle_3")
      public static class Toggle3 extends HorizontalDirectional.Waterlogged {
         public static final IntegerProperty TOGGLE = IntegerProperty.create("toggle", 1, 3);

         public Toggle3(Props props) {
            super(props);
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

      @ItemDescription(description = "toggle_4")
      public static class Toggle4 extends HorizontalDirectional.Waterlogged {
         public static final IntegerProperty TOGGLE = IntegerProperty.create("toggle", 1, 4);

         public Toggle4(Props props) {
            super(props);
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

      @ItemDescription(description = "toggle_5")
      public static class Toggle5 extends HorizontalDirectional.Waterlogged {
         public static final IntegerProperty TOGGLE = IntegerProperty.create("toggle", 1, 5);

         public Toggle5(Props props) {
            super(props);
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

      @ItemDescription(description = "toggle_6")
      public static class Toggle6 extends HorizontalDirectional.Waterlogged {
         public static final IntegerProperty TOGGLE = IntegerProperty.create("toggle", 1, 6);

         public Toggle6(Props props) {
            super(props);
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

      @ItemDescription(description = "toggle_7")
      public static class Toggle7 extends HorizontalDirectional.Waterlogged {
         public static final IntegerProperty TOGGLE = IntegerProperty.create("toggle", 1, 7);

         public Toggle7(Props props) {
            super(props);
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

      @ItemDescription(description = "toggle_8")
      public static class Toggle8 extends HorizontalDirectional.Waterlogged {
         public static final IntegerProperty TOGGLE = IntegerProperty.create("toggle", 1, 8);

         public Toggle8(Props props) {
            super(props);
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
}
