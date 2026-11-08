package com.conquestrefabricated.content.blocks.block.classical;

import com.conquestrefabricated.content.blocks.BlockSettingsAccessor;
import com.conquestrefabricated.content.blocks.CustomOffsetType;
import com.conquestrefabricated.content.blocks.block.directional.Half;
import com.conquestrefabricated.content.blocks.util.PlacementHelper;
import com.conquestrefabricated.core.asset.annotation.Render;
import com.conquestrefabricated.core.block.builder.Props;
import com.conquestrefabricated.core.block.properties.ModBlockProperties;
import com.conquestrefabricated.core.util.RenderLayer;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.OffsetType;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

@Render(RenderLayer.CUTOUT)
public class SmallCorniceModelCorners extends Half.Directional {
   public static final EnumProperty<DoorHingeSide> HINGE = BlockStateProperties.DOOR_HINGE;
   public static final EnumProperty<net.minecraft.world.level.block.state.properties.Half> TYPE_UPDOWN = EnumProperty.create(
      "type", net.minecraft.world.level.block.state.properties.Half.class
   );
   public static final BooleanProperty OFFSET_TOGGLE = ModBlockProperties.OFFSET_TOGGLE;
   private final List<VoxelShape> hitBox;

   public SmallCorniceModelCorners(Props props) {
      super(((BlockSettingsAccessor)props.toSettings()).setCustomOffsetter(CustomOffsetType.LAYER_XZ).offsetType(OffsetType.NONE).dynamicShape());
      this.registerDefaultState((BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue(OFFSET_TOGGLE, false)).setValue(HINGE, DoorHingeSide.LEFT));
      this.hitBox = props.get("hitBox", List.class);
   }

   @NotNull
   @Override
   public BlockState getStateForPlacement(BlockPlaceContext context) {
      boolean isSlab = PlacementHelper.isFacingSlab(context);
      Direction facingHorizontal = context.getHorizontalDirection().getOpposite();
      BlockState state2 = (BlockState)((BlockState)((BlockState)((BlockState)this.defaultBlockState().setValue(DIRECTION, facingHorizontal))
               .setValue(TYPE_UPDOWN, net.minecraft.world.level.block.state.properties.Half.BOTTOM))
            .setValue(OFFSET_TOGGLE, isSlab))
         .setValue(HINGE, this.getHingeSide(context));
      Direction facing = context.getClickedFace();
      return facing == Direction.DOWN || facing != Direction.UP && context.getClickLocation().y - context.getClickedPos().getY() > 0.5
         ? (BlockState)((BlockState)state2.setValue(TYPE_UPDOWN, net.minecraft.world.level.block.state.properties.Half.TOP)).setValue(OFFSET_TOGGLE, isSlab)
         : state2;
   }

   @Override
   protected void addProperties(Builder<Block, BlockState> builder) {
      builder.add(new Property[]{TYPE_UPDOWN}).add(new Property[]{OFFSET_TOGGLE}).add(new Property[]{HINGE});
   }

   @Override
   public VoxelShape getShape(BlockState state) {
      boolean hasEightShapes = this.hitBox.size() == 8;
      switch ((DoorHingeSide)state.getValue(HINGE)) {
         case LEFT:
         default:
            if (state.getValue(TYPE_UPDOWN) == net.minecraft.world.level.block.state.properties.Half.BOTTOM) {
               switch ((Direction)state.getValue(DIRECTION)) {
                  case NORTH:
                  default:
                     return this.hitBox.get(0);
                  case SOUTH:
                     return this.hitBox.get(hasEightShapes ? 1 : 0);
                  case EAST:
                     return this.hitBox.get(hasEightShapes ? 2 : 0);
                  case WEST:
                     return this.hitBox.get(hasEightShapes ? 3 : 0);
               }
            } else {
               switch ((Direction)state.getValue(DIRECTION)) {
                  case NORTH:
                  default:
                     return this.hitBox.get(hasEightShapes ? 4 : 0);
                  case SOUTH:
                     return this.hitBox.get(hasEightShapes ? 5 : 0);
                  case EAST:
                     return this.hitBox.get(hasEightShapes ? 6 : 0);
                  case WEST:
                     return this.hitBox.get(hasEightShapes ? 7 : 0);
               }
            }
         case RIGHT:
            if (state.getValue(TYPE_UPDOWN) == net.minecraft.world.level.block.state.properties.Half.BOTTOM) {
               switch ((Direction)state.getValue(DIRECTION)) {
                  case NORTH:
                  default:
                     return this.hitBox.get(hasEightShapes ? 2 : 0);
                  case SOUTH:
                     return this.hitBox.get(hasEightShapes ? 3 : 0);
                  case EAST:
                     return this.hitBox.get(hasEightShapes ? 1 : 0);
                  case WEST:
                     return this.hitBox.get(0);
               }
            } else {
               switch ((Direction)state.getValue(DIRECTION)) {
                  case NORTH:
                  default:
                     return this.hitBox.get(hasEightShapes ? 6 : 0);
                  case SOUTH:
                     return this.hitBox.get(hasEightShapes ? 7 : 0);
                  case EAST:
                     return this.hitBox.get(hasEightShapes ? 5 : 0);
                  case WEST:
                     return this.hitBox.get(hasEightShapes ? 4 : 0);
               }
            }
      }
   }

   @Override
   public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
      return state.getValue(OFFSET_TOGGLE) ? Shapes.empty() : super.getCollisionShape(state, worldIn, pos, context);
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
}
