package com.conquestrefabricated.content.blocks.block.decor;

import com.conquestrefabricated.content.blocks.CustomOffsetType;
import com.conquestrefabricated.content.blocks.util.PlacementHelper;
import com.conquestrefabricated.core.asset.annotation.Render;
import com.conquestrefabricated.core.asset.annotation.SpecialOffset;
import com.conquestrefabricated.core.block.base.WaterloggedHorizontalDirectionalShape;
import com.conquestrefabricated.core.block.builder.Props;
import com.conquestrefabricated.core.block.builder.SpecialOffsetType;
import com.conquestrefabricated.core.block.properties.ModBlockProperties;
import com.conquestrefabricated.core.block.properties.ParallelConnectionShape2;
import com.conquestrefabricated.core.util.RenderLayer;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.OffsetType;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

@Render(RenderLayer.CUTOUT)
@SpecialOffset(offsetType = SpecialOffsetType.XYZ)
public class ParallelConnecting extends WaterloggedHorizontalDirectionalShape {
   public static final BooleanProperty OFFSET_TOGGLE = ModBlockProperties.OFFSET_TOGGLE;
   protected static final VoxelShape SHAPE = Block.box(1.0, 0.0, 1.0, 15.0, 16.0, 15.0);
   public static final EnumProperty<ParallelConnectionShape2> FORM = EnumProperty.create("shape", ParallelConnectionShape2.class);

   public ParallelConnecting(Props props) {
      super(props.customOffsetType(CustomOffsetType.LAYER_XYZ).offset(OffsetType.NONE).dynamicBounds(true).toSettings());
      this.registerDefaultState(
         (BlockState)((BlockState)((BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue(FORM, ParallelConnectionShape2.ONE))
                  .setValue(DIRECTION, Direction.NORTH))
               .setValue(WATERLOGGED, false))
            .setValue(OFFSET_TOGGLE, false)
      );
   }

   @Override
   public VoxelShape getShape(BlockState state) {
      return SHAPE;
   }

   @Override
   public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
      return state.getValue(OFFSET_TOGGLE) ? Shapes.empty() : super.getCollisionShape(state, worldIn, pos, context);
   }

   public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor world, BlockPos currentPos, BlockPos facingPos) {
      if ((Boolean)stateIn.getValue(WATERLOGGED)) {
         world.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
      }

      if (stateIn.getBlock() == this) {
         BlockState north = world.getBlockState(currentPos.north());
         BlockState east = world.getBlockState(currentPos.east());
         BlockState south = world.getBlockState(currentPos.south());
         BlockState west = world.getBlockState(currentPos.west());
         Direction stateInDirection = (Direction)stateIn.getValue(DIRECTION);
         switch (stateInDirection) {
            case NORTH:
               if (this.attachesTo(east, stateInDirection)) {
                  if (this.attachesTo(west, stateInDirection)) {
                     return (BlockState)stateIn.setValue(FORM, ParallelConnectionShape2.MIDDLE);
                  }

                  return (BlockState)stateIn.setValue(FORM, ParallelConnectionShape2.EDGE_R);
               }

               if (this.attachesTo(west, stateInDirection)) {
                  return (BlockState)stateIn.setValue(FORM, ParallelConnectionShape2.EDGE_L);
               }

               return (BlockState)stateIn.setValue(FORM, ParallelConnectionShape2.ONE);
            case SOUTH:
               if (this.attachesTo(west, stateInDirection)) {
                  if (this.attachesTo(east, stateInDirection)) {
                     return (BlockState)stateIn.setValue(FORM, ParallelConnectionShape2.MIDDLE);
                  }

                  return (BlockState)stateIn.setValue(FORM, ParallelConnectionShape2.EDGE_R);
               }

               if (this.attachesTo(east, stateInDirection)) {
                  return (BlockState)stateIn.setValue(FORM, ParallelConnectionShape2.EDGE_L);
               }

               return (BlockState)stateIn.setValue(FORM, ParallelConnectionShape2.ONE);
            case EAST:
               if (this.attachesTo(north, stateInDirection)) {
                  if (this.attachesTo(south, stateInDirection)) {
                     return (BlockState)stateIn.setValue(FORM, ParallelConnectionShape2.MIDDLE);
                  }

                  return (BlockState)stateIn.setValue(FORM, ParallelConnectionShape2.EDGE_L);
               }

               if (this.attachesTo(south, stateInDirection)) {
                  return (BlockState)stateIn.setValue(FORM, ParallelConnectionShape2.EDGE_R);
               }

               return (BlockState)stateIn.setValue(FORM, ParallelConnectionShape2.ONE);
            case WEST:
               if (this.attachesTo(south, stateInDirection)) {
                  if (this.attachesTo(north, stateInDirection)) {
                     return (BlockState)stateIn.setValue(FORM, ParallelConnectionShape2.MIDDLE);
                  }

                  return (BlockState)stateIn.setValue(FORM, ParallelConnectionShape2.EDGE_L);
               }

               if (this.attachesTo(north, stateInDirection)) {
                  return (BlockState)stateIn.setValue(FORM, ParallelConnectionShape2.EDGE_R);
               }

               return (BlockState)stateIn.setValue(FORM, ParallelConnectionShape2.ONE);
         }
      }

      return stateIn;
   }

   @Override
   public BlockState getStateForPlacement(BlockPlaceContext context) {
      BlockPos pos = context.getClickedPos();
      boolean isSlab = PlacementHelper.isFacingSlab(context);
      FluidState ifluidstate = context.getLevel().getFluidState(context.getClickedPos());
      BlockGetter iblockreader = context.getLevel();
      BlockState north = iblockreader.getBlockState(pos.north());
      BlockState east = iblockreader.getBlockState(pos.east());
      BlockState south = iblockreader.getBlockState(pos.south());
      BlockState west = iblockreader.getBlockState(pos.west());
      Direction facing = context.getHorizontalDirection().getOpposite();
      BlockState stateDefault = (BlockState)((BlockState)((BlockState)this.defaultBlockState().setValue(DIRECTION, facing))
            .setValue(WATERLOGGED, ifluidstate.getType() == Fluids.WATER))
         .setValue(OFFSET_TOGGLE, isSlab);
      BlockState stateOne = (BlockState)stateDefault.setValue(FORM, ParallelConnectionShape2.ONE);
      BlockState stateMiddle = (BlockState)stateDefault.setValue(FORM, ParallelConnectionShape2.MIDDLE);
      BlockState stateRight = (BlockState)stateDefault.setValue(FORM, ParallelConnectionShape2.EDGE_R);
      BlockState stateLeft = (BlockState)stateDefault.setValue(FORM, ParallelConnectionShape2.EDGE_L);
      switch (facing) {
         case NORTH:
         default:
            if (this.attachesTo(east, facing)) {
               if (this.attachesTo(west, facing)) {
                  return stateMiddle;
               }

               return stateRight;
            } else {
               if (this.attachesTo(west, facing)) {
                  return stateLeft;
               }

               return stateOne;
            }
         case SOUTH:
            if (this.attachesTo(west, facing)) {
               if (this.attachesTo(east, facing)) {
                  return stateMiddle;
               }

               return stateRight;
            } else {
               if (this.attachesTo(east, facing)) {
                  return stateLeft;
               }

               return stateOne;
            }
         case EAST:
            if (this.attachesTo(north, facing)) {
               if (this.attachesTo(south, facing)) {
                  return stateMiddle;
               }

               return stateLeft;
            } else {
               if (this.attachesTo(south, facing)) {
                  return stateRight;
               }

               return stateOne;
            }
         case WEST:
            if (this.attachesTo(south, facing)) {
               return this.attachesTo(north, facing) ? stateMiddle : stateLeft;
            } else {
               return this.attachesTo(north, facing) ? stateRight : stateOne;
            }
      }
   }

   @Override
   protected void addProperties(Builder<Block, BlockState> builder) {
      builder.add(new Property[]{FORM, OFFSET_TOGGLE});
   }

   protected boolean attachesTo(BlockState state, Direction facing) {
      Block block = state.getBlock();
      return block instanceof ParallelConnecting && state.getValue(DIRECTION) == facing;
   }

   public static class Unique extends ParallelConnecting {
      private final List<VoxelShape> hitBox;

      public Unique(Props props) {
         super(props.customOffsetType(CustomOffsetType.LAYER_XYZ).offset(OffsetType.NONE).dynamicBounds(true));
         this.registerDefaultState(
            (BlockState)((BlockState)((BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue(FORM, ParallelConnectionShape2.ONE))
                     .setValue(DIRECTION, Direction.NORTH))
                  .setValue(WATERLOGGED, false))
               .setValue(OFFSET_TOGGLE, false)
         );
         this.hitBox = props.get("hitBox", List.class);
      }

      @Override
      public VoxelShape getShape(BlockState state) {
         boolean hasFourShapes = this.hitBox.size() == 4;
         switch ((Direction)state.getValue(DIRECTION)) {
            case SOUTH:
               return this.hitBox.get(hasFourShapes ? 2 : 0);
            case EAST:
               return this.hitBox.get(hasFourShapes ? 1 : 0);
            case WEST:
               return this.hitBox.get(hasFourShapes ? 3 : 0);
            default:
               return this.hitBox.get(0);
         }
      }
   }
}
