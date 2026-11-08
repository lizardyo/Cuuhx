package com.conquestrefabricated.content.blocks.block;

import com.conquestrefabricated.core.asset.annotation.Assets;
import com.conquestrefabricated.core.asset.annotation.Model;
import com.conquestrefabricated.core.asset.annotation.State;
import com.conquestrefabricated.core.block.base.WaterloggedHorizontalDirectionalShape;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

@Assets(
   state = @State(name = "%s_vertical_corner_slab", template = "parent_vertical_corner_slab"),
   item = @Model(name = "item/%s_vertical_corner_slab", parent = "block/%s_vertical_corner_slab_left", template = "item/parent_slab_corner"),
   block = {
         @Model(name = "block/%s_vertical_corner_slab_left", template = "block/parent_vertical_corner_slab_left"),
         @Model(name = "block/%s_vertical_corner_slab_bottom_left", template = "block/parent_vertical_corner_slab_bottom_left"),
         @Model(name = "block/%s_vertical_corner_slab_right", template = "block/parent_vertical_corner_slab_right"),
         @Model(name = "block/%s_vertical_corner_slab_bottom_right", template = "block/parent_vertical_corner_slab_bottom_right")
   }
)
public class VerticalSlabCorner extends WaterloggedHorizontalDirectionalShape {
   public static final EnumProperty<Half> TYPE_UPDOWN = EnumProperty.create("type", Half.class);
   public static final EnumProperty<DoorHingeSide> HINGE = BlockStateProperties.DOOR_HINGE;
   public static final BooleanProperty WP_COMPAT = BooleanProperty.create("wp_compat");
   private static final VoxelShape ARCH_NORTH_R_SHAPE = Shapes.or(
      Block.box(0.0, 8.0, 8.0, 16.0, 16.0, 16.0), Block.box(0.0, 0.0, 8.0, 8.0, 8.0, 16.0)
   );
   private static final VoxelShape ARCH_NORTH_L_SHAPE = Shapes.or(
      Block.box(8.0, 0.0, 8.0, 16.0, 16.0, 16.0), Block.box(0.0, 8.0, 8.0, 8.0, 16.0, 16.0)
   );
   private static final VoxelShape ARCH_WEST_L_SHAPE = Shapes.or(
      Block.box(8.0, 8.0, 0.0, 16.0, 16.0, 16.0), Block.box(8.0, 0.0, 8.0, 16.0, 8.0, 16.0)
   );
   private static final VoxelShape ARCH_WEST_R_SHAPE = Shapes.or(
      Block.box(8.0, 0.0, 0.0, 16.0, 16.0, 8.0), Block.box(8.0, 8.0, 8.0, 16.0, 16.0, 16.0)
   );
   private static final VoxelShape ARCH_EAST_R_SHAPE = Shapes.or(
      Block.box(0.0, 8.0, 0.0, 8.0, 16.0, 16.0), Block.box(0.0, 0.0, 0.0, 8.0, 8.0, 8.0)
   );
   private static final VoxelShape ARCH_EAST_L_SHAPE = Shapes.or(
      Block.box(0.0, 0.0, 8.0, 8.0, 16.0, 16.0), Block.box(0.0, 8.0, 0.0, 8.0, 16.0, 8.0)
   );
   private static final VoxelShape ARCH_SOUTH_L_SHAPE = Shapes.or(
      Block.box(0.0, 8.0, 0.0, 16.0, 16.0, 8.0), Block.box(8.0, 0.0, 0.0, 16.0, 8.0, 8.0)
   );
   private static final VoxelShape ARCH_SOUTH_R_SHAPE = Shapes.or(
      Block.box(0.0, 0.0, 0.0, 8.0, 16.0, 8.0), Block.box(8.0, 8.0, 0.0, 16.0, 16.0, 8.0)
   );
   private static final VoxelShape ARCH_NORTH_R_BOTTOM_SHAPE = Shapes.or(
      Block.box(0.0, 0.0, 8.0, 16.0, 8.0, 16.0), Block.box(0.0, 8.0, 8.0, 8.0, 16.0, 16.0)
   );
   private static final VoxelShape ARCH_NORTH_L_BOTTOM_SHAPE = Shapes.or(
      Block.box(8.0, 0.0, 8.0, 16.0, 16.0, 16.0), Block.box(0.0, 0.0, 8.0, 8.0, 8.0, 16.0)
   );
   private static final VoxelShape ARCH_WEST_L_BOTTOM_SHAPE = Shapes.or(
      Block.box(8.0, 0.0, 0.0, 16.0, 8.0, 16.0), Block.box(8.0, 8.0, 8.0, 16.0, 16.0, 16.0)
   );
   private static final VoxelShape ARCH_WEST_R_BOTTOM_SHAPE = Shapes.or(
      Block.box(8.0, 0.0, 0.0, 16.0, 16.0, 8.0), Block.box(8.0, 0.0, 8.0, 16.0, 8.0, 16.0)
   );
   private static final VoxelShape ARCH_EAST_R_BOTTOM_SHAPE = Shapes.or(
      Block.box(0.0, 0.0, 0.0, 8.0, 8.0, 16.0), Block.box(0.0, 8.0, 0.0, 8.0, 16.0, 8.0)
   );
   private static final VoxelShape ARCH_EAST_L_BOTTOM_SHAPE = Shapes.or(
      Block.box(0.0, 0.0, 8.0, 8.0, 16.0, 16.0), Block.box(0.0, 0.0, 0.0, 8.0, 8.0, 8.0)
   );
   private static final VoxelShape ARCH_SOUTH_L_BOTTOM_SHAPE = Shapes.or(
      Block.box(0.0, 0.0, 0.0, 16.0, 8.0, 8.0), Block.box(8.0, 8.0, 0.0, 16.0, 16.0, 8.0)
   );
   private static final VoxelShape ARCH_SOUTH_R_BOTTOM_SHAPE = Shapes.or(
      Block.box(0.0, 0.0, 0.0, 8.0, 16.0, 8.0), Block.box(8.0, 0.0, 0.0, 16.0, 8.0, 8.0)
   );

   public VerticalSlabCorner(Properties properties) {
      super(properties);
      this.registerDefaultState(
         (BlockState)((BlockState)((BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue(DIRECTION, Direction.NORTH))
                  .setValue(TYPE_UPDOWN, Half.BOTTOM))
               .setValue(WATERLOGGED, false))
            .setValue(WP_COMPAT, false)
      );
   }

   @Override
   public BlockState getStateForPlacement(BlockPlaceContext context) {
      BlockPos blockpos = context.getClickedPos();
      FluidState fluid = context.getLevel().getFluidState(blockpos);
      Direction facingHorizontal = context.getHorizontalDirection().getOpposite();
      BlockState state2 = (BlockState)((BlockState)((BlockState)((BlockState)this.defaultBlockState().setValue(WP_COMPAT, true)).setValue(DIRECTION, facingHorizontal))
            .setValue(TYPE_UPDOWN, Half.BOTTOM))
         .setValue(WATERLOGGED, fluid.getType() == Fluids.WATER);
      Direction facing = context.getClickedFace();
      return facing == Direction.DOWN || facing != Direction.UP && context.getClickLocation().y - context.getClickedPos().getY() > 0.5
         ? (BlockState)((BlockState)state2.setValue(TYPE_UPDOWN, Half.TOP)).setValue(HINGE, this.getHingeSide(facingHorizontal, blockpos, context))
         : (BlockState)state2.setValue(HINGE, this.getHingeSide(facingHorizontal, blockpos, context));
   }

   @Override
   public BlockState rotate(BlockState state, Rotation rot) {
      if (!(Boolean)state.getValue(WP_COMPAT)) {
         switch (rot) {
            case CLOCKWISE_90:
               switch ((Direction)state.getValue(DIRECTION)) {
                  case EAST:
                  case WEST:
                     return (BlockState)((BlockState)state.cycle(HINGE)).setValue(DIRECTION, rot.rotate((Direction)state.getValue(DIRECTION)));
                  case NORTH:
                  case SOUTH:
                  default:
                     return (BlockState)state.setValue(DIRECTION, rot.rotate((Direction)state.getValue(DIRECTION)));
               }
            case CLOCKWISE_180:
               switch ((Direction)state.getValue(DIRECTION)) {
                  case EAST:
                  case WEST:
                  case NORTH:
                  case SOUTH:
                     return (BlockState)((BlockState)state.cycle(HINGE)).setValue(DIRECTION, rot.rotate((Direction)state.getValue(DIRECTION)));
                  default:
                     return (BlockState)state.setValue(DIRECTION, rot.rotate((Direction)state.getValue(DIRECTION)));
               }
            case COUNTERCLOCKWISE_90:
               switch ((Direction)state.getValue(DIRECTION)) {
                  case EAST:
                  case WEST:
                     return (BlockState)state.setValue(DIRECTION, rot.rotate((Direction)state.getValue(DIRECTION)));
                  case NORTH:
                  case SOUTH:
                     return (BlockState)((BlockState)state.cycle(HINGE)).setValue(DIRECTION, rot.rotate((Direction)state.getValue(DIRECTION)));
               }
            default:
               return state;
         }
      } else {
         return super.rotate(state, rot);
      }
   }

   @Override
   public BlockState mirror(BlockState state, Mirror mirrorIn) {
      switch (mirrorIn) {
         case FRONT_BACK:
            switch ((Direction)state.getValue(DIRECTION)) {
               case EAST:
               case WEST:
               default:
                  return super.mirror(state, mirrorIn);
               case NORTH:
               case SOUTH:
                  return (BlockState)state.cycle(HINGE);
            }
         case LEFT_RIGHT:
            switch ((Direction)state.getValue(DIRECTION)) {
               case EAST:
               case WEST:
                  return (BlockState)state.cycle(HINGE);
               case NORTH:
               case SOUTH:
               default:
                  return super.mirror(state, mirrorIn);
            }
         default:
            return super.mirror(state, mirrorIn);
      }
   }

   private DoorHingeSide getHingeSide(Direction facing, BlockPos pos, BlockPlaceContext context) {
      BlockPos blockPos = context.getClickedPos();
      Direction direction = context.getHorizontalDirection();
      int j = direction.getStepX();
      int k = direction.getStepZ();
      Vec3 vec3d = context.getClickLocation();
      double d = vec3d.x - blockPos.getX();
      double e = vec3d.z - blockPos.getZ();
      return j < 0 && e < 0.5 || j > 0 && e > 0.5 || k < 0 && d > 0.5 || k > 0 && d < 0.5 ? DoorHingeSide.RIGHT : DoorHingeSide.LEFT;
   }

   @Override
   protected void addProperties(Builder<Block, BlockState> container) {
      container.add(new Property[]{TYPE_UPDOWN}).add(new Property[]{HINGE}).add(new Property[]{WP_COMPAT});
   }

   @Override
   public VoxelShape getShape(BlockState state) {
      if ((Boolean)state.getValue(WP_COMPAT)) {
         if (state.getValue(TYPE_UPDOWN) == Half.TOP) {
            if (state.getValue(HINGE) == DoorHingeSide.LEFT) {
               switch ((Direction)state.getValue(DIRECTION)) {
                  case EAST:
                     return ARCH_EAST_R_SHAPE;
                  case WEST:
                     return ARCH_WEST_L_SHAPE;
                  case NORTH:
                  default:
                     return ARCH_NORTH_R_SHAPE;
                  case SOUTH:
                     return ARCH_SOUTH_L_SHAPE;
               }
            } else {
               switch ((Direction)state.getValue(DIRECTION)) {
                  case EAST:
                     return ARCH_EAST_L_SHAPE;
                  case WEST:
                     return ARCH_WEST_R_SHAPE;
                  case NORTH:
                  default:
                     return ARCH_NORTH_L_SHAPE;
                  case SOUTH:
                     return ARCH_SOUTH_R_SHAPE;
               }
            }
         } else if (state.getValue(HINGE) == DoorHingeSide.LEFT) {
            switch ((Direction)state.getValue(DIRECTION)) {
               case EAST:
                  return ARCH_EAST_R_BOTTOM_SHAPE;
               case WEST:
                  return ARCH_WEST_L_BOTTOM_SHAPE;
               case NORTH:
               default:
                  return ARCH_NORTH_R_BOTTOM_SHAPE;
               case SOUTH:
                  return ARCH_SOUTH_L_BOTTOM_SHAPE;
            }
         } else {
            switch ((Direction)state.getValue(DIRECTION)) {
               case EAST:
                  return ARCH_EAST_L_BOTTOM_SHAPE;
               case WEST:
                  return ARCH_WEST_R_BOTTOM_SHAPE;
               case NORTH:
               default:
                  return ARCH_NORTH_L_BOTTOM_SHAPE;
               case SOUTH:
                  return ARCH_SOUTH_R_BOTTOM_SHAPE;
            }
         }
      } else if (state.getValue(TYPE_UPDOWN) == Half.TOP) {
         if (state.getValue(HINGE) == DoorHingeSide.LEFT) {
            switch ((Direction)state.getValue(DIRECTION)) {
               case EAST:
                  return ARCH_EAST_L_SHAPE;
               case WEST:
                  return ARCH_WEST_L_SHAPE;
               case NORTH:
               default:
                  return ARCH_NORTH_L_SHAPE;
               case SOUTH:
                  return ARCH_SOUTH_L_SHAPE;
            }
         } else {
            switch ((Direction)state.getValue(DIRECTION)) {
               case EAST:
                  return ARCH_EAST_R_SHAPE;
               case WEST:
                  return ARCH_WEST_R_SHAPE;
               case NORTH:
               default:
                  return ARCH_NORTH_R_SHAPE;
               case SOUTH:
                  return ARCH_SOUTH_R_SHAPE;
            }
         }
      } else if (state.getValue(HINGE) == DoorHingeSide.LEFT) {
         switch ((Direction)state.getValue(DIRECTION)) {
            case EAST:
               return ARCH_EAST_L_BOTTOM_SHAPE;
            case WEST:
               return ARCH_WEST_L_BOTTOM_SHAPE;
            case NORTH:
            default:
               return ARCH_NORTH_L_BOTTOM_SHAPE;
            case SOUTH:
               return ARCH_SOUTH_L_BOTTOM_SHAPE;
         }
      } else {
         switch ((Direction)state.getValue(DIRECTION)) {
            case EAST:
               return ARCH_EAST_R_BOTTOM_SHAPE;
            case WEST:
               return ARCH_WEST_R_BOTTOM_SHAPE;
            case NORTH:
            default:
               return ARCH_NORTH_R_BOTTOM_SHAPE;
            case SOUTH:
               return ARCH_SOUTH_R_BOTTOM_SHAPE;
         }
      }
   }
}
