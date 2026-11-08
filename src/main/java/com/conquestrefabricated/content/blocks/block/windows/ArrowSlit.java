package com.conquestrefabricated.content.blocks.block.windows;

import com.conquestrefabricated.core.asset.annotation.Assets;
import com.conquestrefabricated.core.asset.annotation.Model;
import com.conquestrefabricated.core.asset.annotation.State;
import com.conquestrefabricated.core.block.base.WaterloggedHorizontalDirectionalShape;
import com.conquestrefabricated.core.block.properties.Waterloggable;
import java.util.stream.Stream;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

@Assets(
   state = @State(name = "%s_arrowslit", template = "parent_arrowslit"),
   item = @Model(name = "item/%s_arrowslit", parent = "block/%s_arrowslit", template = "item/parent_arrowslit"),
   block = @Model(name = "block/%s_arrowslit", template = "block/parent_arrowslit")
)
public class ArrowSlit extends WaterloggedHorizontalDirectionalShape implements Waterloggable {
   private static final VoxelShape NORTH_FRONT = Stream.of(
         Block.box(0.0, 0.0, 8.0, 3.0, 16.0, 16.0),
         Block.box(0.0, 0.0, 15.0, 7.0, 16.0, 16.0),
         Block.box(13.0, 0.0, 8.0, 16.0, 16.0, 16.0),
         Block.box(9.0, 0.0, 15.0, 16.0, 16.0, 16.0)
      )
      .reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR))
      .get();
   private static final VoxelShape EAST_FRONT = Stream.of(
         Block.box(0.0, 0.0, 0.0, 8.0, 16.0, 3.0),
         Block.box(0.0, 0.0, 0.0, 1.0, 16.0, 7.0),
         Block.box(0.0, 0.0, 13.0, 8.0, 16.0, 16.0),
         Block.box(0.0, 0.0, 9.0, 1.0, 16.0, 16.0)
      )
      .reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR))
      .get();
   private static final VoxelShape SOUTH_FRONT = Stream.of(
         Block.box(13.0, 0.0, 0.0, 16.0, 16.0, 8.0),
         Block.box(9.0, 0.0, 0.0, 16.0, 16.0, 1.0),
         Block.box(0.0, 0.0, 0.0, 3.0, 16.0, 8.0),
         Block.box(0.0, 0.0, 0.0, 7.0, 16.0, 1.0)
      )
      .reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR))
      .get();
   private static final VoxelShape WEST_FRONT = Stream.of(
         Block.box(8.0, 0.0, 13.0, 16.0, 16.0, 16.0),
         Block.box(15.0, 0.0, 9.0, 16.0, 16.0, 16.0),
         Block.box(8.0, 0.0, 0.0, 16.0, 16.0, 3.0),
         Block.box(15.0, 0.0, 0.0, 16.0, 16.0, 7.0)
      )
      .reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR))
      .get();
   private static final VoxelShape NORTH_BACK = Stream.of(
         Block.box(0.0, 0.0, 0.0, 3.0, 16.0, 8.0),
         Block.box(0.0, 0.0, 7.0, 7.0, 16.0, 8.0),
         Block.box(13.0, 0.0, 0.0, 16.0, 16.0, 8.0),
         Block.box(9.0, 0.0, 7.0, 16.0, 16.0, 8.0)
      )
      .reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR))
      .get();
   private static final VoxelShape EAST_BACK = Stream.of(
         Block.box(8.0, 0.0, 0.0, 16.0, 16.0, 3.0),
         Block.box(8.0, 0.0, 0.0, 9.0, 16.0, 7.0),
         Block.box(8.0, 0.0, 13.0, 16.0, 16.0, 16.0),
         Block.box(8.0, 0.0, 9.0, 9.0, 16.0, 16.0)
      )
      .reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR))
      .get();
   private static final VoxelShape SOUTH_BACK = Stream.of(
         Block.box(13.0, 0.0, 8.0, 16.0, 16.0, 16.0),
         Block.box(9.0, 0.0, 8.0, 16.0, 16.0, 9.0),
         Block.box(0.0, 0.0, 8.0, 3.0, 16.0, 16.0),
         Block.box(0.0, 0.0, 8.0, 7.0, 16.0, 9.0)
      )
      .reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR))
      .get();
   private static final VoxelShape WEST_BACK = Stream.of(
         Block.box(0.0, 0.0, 13.0, 8.0, 16.0, 16.0),
         Block.box(7.0, 0.0, 9.0, 8.0, 16.0, 16.0),
         Block.box(0.0, 0.0, 0.0, 8.0, 16.0, 3.0),
         Block.box(7.0, 0.0, 0.0, 8.0, 16.0, 7.0)
      )
      .reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR))
      .get();
   public static final EnumProperty<ArrowSlit.Position> POSITION = EnumProperty.create("position", ArrowSlit.Position.class);

   public ArrowSlit(Properties properties) {
      super(properties);
      this.registerDefaultState((BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue(DIRECTION, Direction.NORTH)).setValue(WATERLOGGED, false));
   }

   @NotNull
   @Override
   public BlockState getStateForPlacement(BlockPlaceContext context) {
      return (BlockState)((BlockState)super.getStateForPlacement(context).setValue(DIRECTION, context.getHorizontalDirection().getOpposite()))
         .setValue(POSITION, this.getBlockDirectionalHalf(context));
   }

   private ArrowSlit.Position getBlockDirectionalHalf(BlockPlaceContext context) {
      BlockPos blockPos = context.getClickedPos();
      Direction facing = context.getHorizontalDirection();
      Vec3 hit = context.getClickLocation();
      Direction hitFace = context.getClickedFace();
      if (hitFace.getAxis().isHorizontal()) {
         return ArrowSlit.Position.FRONT;
      }

      double dx = hit.x - blockPos.getX();
      double dz = hit.z - blockPos.getZ();

      return switch (facing) {
         case NORTH -> dz < 0.5 ? ArrowSlit.Position.FRONT : ArrowSlit.Position.BACK;
         case SOUTH -> dz > 0.5 ? ArrowSlit.Position.FRONT : ArrowSlit.Position.BACK;
         case WEST -> dx < 0.5 ? ArrowSlit.Position.FRONT : ArrowSlit.Position.BACK;
         case EAST -> dx > 0.5 ? ArrowSlit.Position.FRONT : ArrowSlit.Position.BACK;
         default -> ArrowSlit.Position.FRONT;
      };
   }

   @Override
   public VoxelShape getShape(BlockState state) {
      if (state.getValue(POSITION) == ArrowSlit.Position.FRONT) {
         return switch ((Direction)state.getValue(DIRECTION)) {
            case SOUTH -> SOUTH_FRONT;
            case WEST -> WEST_FRONT;
            case EAST -> EAST_FRONT;
            default -> NORTH_FRONT;
         };
      } else {
         return switch ((Direction)state.getValue(DIRECTION)) {
            case SOUTH -> SOUTH_BACK;
            case WEST -> WEST_BACK;
            case EAST -> EAST_BACK;
            default -> NORTH_BACK;
         };
      }
   }

   @Override
   protected void addProperties(Builder<Block, BlockState> builder) {
      builder.add(new Property[]{POSITION});
   }

   public enum Position implements StringRepresentable {
      FRONT,
      BACK;

      Position() {
      }

      @Override
      public String toString() {
         return this.getSerializedName();
      }

      public String getSerializedName() {
         return this == BACK ? "back" : "front";
      }
   }
}
