package com.conquestrefabricated.content.blocks.block.decor;

import com.conquestrefabricated.content.blocks.block.VerticalCorner;
import com.conquestrefabricated.content.blocks.block.VerticalSlab;
import com.conquestrefabricated.content.blocks.util.Interactions;
import com.conquestrefabricated.core.asset.annotation.Assets;
import com.conquestrefabricated.core.asset.annotation.Model;
import com.conquestrefabricated.core.asset.annotation.State;
import com.conquestrefabricated.core.block.base.WaterloggedHorizontalDirectionalShape;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

@Assets(
   state = @State(name = "horizontal_%s", template = "parent_railing"),
   item = @Model(name = "item/horizontal_%s", parent = "block/horizontal_%s", template = "item/dragon_egg")
)
public class Railings extends WaterloggedHorizontalDirectionalShape {
   public static final BooleanProperty OPEN = BlockStateProperties.OPEN;

   public Railings(Properties properties) {
      super(properties);
      this.registerDefaultState((BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue(OPEN, true)).setValue(WATERLOGGED, false));
   }

   @Override
   protected void addProperties(Builder<Block, BlockState> builder) {
      builder.add(new Property[]{OPEN});
   }

   @Override
   public VoxelShape getShape(BlockState state) {
      switch ((Direction)state.getValue(DIRECTION)) {
         case NORTH:
         default:
            return VerticalSlab.NORTH_SHAPE[0];
         case SOUTH:
            return VerticalSlab.SOUTH_SHAPE[0];
         case EAST:
            return VerticalSlab.EAST_SHAPE[0];
         case WEST:
            return VerticalSlab.WEST_SHAPE[0];
      }
   }

   public VoxelShape getOcclusionShape(BlockState state, BlockGetter world, BlockPos pos) {
      return Shapes.empty();
   }

   public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
      return Interactions.onUseToggleItem(player, world, pos, state, OPEN);
   }

   @Assets(
      state = @State(name = "horizontal_%s_corner", template = "parent_corner"),
      item = @Model(name = "item/horizontal_%s_corner", parent = "block/horizontal_%s_corner", template = "item/dragon_egg")
   )
   public static class Corner extends Railings {
      public Corner(Properties properties) {
         super(properties);
      }

      @Override
      public VoxelShape getShape(BlockState state) {
         switch ((Direction)state.getValue(DIRECTION)) {
            case NORTH:
            default:
               return VerticalCorner.NORTH_SHAPE[0];
            case SOUTH:
               return VerticalCorner.SOUTH_SHAPE[0];
            case EAST:
               return VerticalCorner.EAST_SHAPE[0];
            case WEST:
               return VerticalCorner.WEST_SHAPE[0];
         }
      }
   }

   @Assets(
      state = @State(name = "diagonal_%s", template = "parent_diagonal"),
      item = @Model(name = "item/diagonal_%s", parent = "block/diagonal_%s", template = "item/dragon_egg")
   )
   public static class Diagonal extends Railings {
      public static final EnumProperty<Railings.HingeHalf> HINGE_hALF = EnumProperty.create("half", Railings.HingeHalf.class);

      public Diagonal(Properties properties) {
         super(properties);
      }

      @NotNull
      @Override
      public BlockState getStateForPlacement(BlockPlaceContext context) {
         return (BlockState)((BlockState)super.getStateForPlacement(context).setValue(DIRECTION, context.getHorizontalDirection().getOpposite()))
            .setValue(HINGE_hALF, this.getHingeSide(context));
      }

      private Railings.HingeHalf getHingeSide(BlockPlaceContext p_208073_1_) {
         BlockGetter iblockreader = p_208073_1_.getLevel();
         BlockPos blockpos = p_208073_1_.getClickedPos();
         Direction direction = p_208073_1_.getHorizontalDirection();
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
               return Railings.HingeHalf.RIGHT;
            }

            int j = direction.getStepX();
            int k = direction.getStepZ();
            Vec3 vec3d = p_208073_1_.getClickLocation();
            double d0 = vec3d.x - blockpos.getX();
            double d1 = vec3d.z - blockpos.getZ();
            return j < 0 && d1 < 0.5 || j > 0 && d1 > 0.5 || k < 0 && d0 > 0.5 || k > 0 && d0 < 0.5 ? Railings.HingeHalf.LEFT : Railings.HingeHalf.RIGHT;
         } else {
            return Railings.HingeHalf.LEFT;
         }
      }

      @Override
      protected void addProperties(Builder<Block, BlockState> builder) {
         builder.add(new Property[]{HINGE_hALF, OPEN});
      }
   }

   public enum HingeHalf implements StringRepresentable {
      LEFT,
      RIGHT;

      HingeHalf() {
      }

      @Override
      public String toString() {
         return this.getSerializedName();
      }

      public String getSerializedName() {
         return this == LEFT ? "top" : "bottom";
      }
   }
}
