package com.conquestrefabricated.content.blocks.block;

import com.conquestrefabricated.api.tags.ModTags;
import com.conquestrefabricated.content.blocks.block.directional.Half;
import com.conquestrefabricated.core.asset.annotation.Assets;
import com.conquestrefabricated.core.asset.annotation.ItemDescription;
import com.conquestrefabricated.core.asset.annotation.Model;
import com.conquestrefabricated.core.asset.annotation.Render;
import com.conquestrefabricated.core.asset.annotation.State;
import com.conquestrefabricated.core.block.builder.Props;
import com.conquestrefabricated.core.util.RenderLayer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.data.models.blockstates.PropertyDispatch;
import net.minecraft.data.models.blockstates.Variant;
import net.minecraft.data.models.blockstates.VariantProperties;
import net.minecraft.data.models.blockstates.VariantProperties.Rotation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

@Assets(
   state = @State(name = "%s_boards", template = "parent_boards_horizontal"),
   item = @Model(name = "item/%s_boards", parent = "block/%s_boards_vertical_long_thin", template = "item/acacia_slab"),
   render = @Render(RenderLayer.CUTOUT)
)
@ItemDescription(description = "board_toggle")
public class BoardsHorizontal extends Half.DirectionalWaterlogged {
   private static final VoxelShape TOP_NORTHSOUTH_SHORT_THIN_SHAPE = Block.box(0.0, 8.0, 0.0, 16.0, 16.0, 16.0);
   private static final VoxelShape TOP_NORTHSOUTH_SHORT_MID_SHAPE = Block.box(-8.0, 8.0, 0.0, 24.0, 16.0, 16.0);
   private static final VoxelShape TOP_NORTHSOUTH_SHORT_WIDE_SHAPE = Block.box(-16.0, 8.0, 0.0, 32.0, 16.0, 16.0);
   private static final VoxelShape TOP_NORTHSOUTH_LONG_THIN_SHAPE = Block.box(0.0, 8.0, -8.0, 16.0, 16.0, 24.0);
   private static final VoxelShape TOP_NORTHSOUTH_LONG_MID_SHAPE = Block.box(-8.0, 8.0, -8.0, 24.0, 16.0, 24.0);
   private static final VoxelShape TOP_NORTHSOUTH_LONG_WIDE_SHAPE = Block.box(-16.0, 8.0, -8.0, 32.0, 16.0, 24.0);
   private static final VoxelShape TOP_EASTWEST_SHORT_THIN_SHAPE = Block.box(0.0, 8.0, 0.0, 16.0, 16.0, 16.0);
   private static final VoxelShape TOP_EASTWEST_SHORT_MID_SHAPE = Block.box(0.0, 8.0, -8.0, 16.0, 16.0, 24.0);
   private static final VoxelShape TOP_EASTWEST_SHORT_WIDE_SHAPE = Block.box(0.0, 8.0, -16.0, 16.0, 16.0, 32.0);
   private static final VoxelShape TOP_EASTWEST_LONG_THIN_SHAPE = Block.box(-8.0, 8.0, 0.0, 24.0, 16.0, 16.0);
   private static final VoxelShape TOP_EASTWEST_LONG_MID_SHAPE = Block.box(-8.0, 8.0, -8.0, 24.0, 16.0, 24.0);
   private static final VoxelShape TOP_EASTWEST_LONG_WIDE_SHAPE = Block.box(-8.0, 8.0, -16.0, 24.0, 16.0, 32.0);
   private static final VoxelShape BOTTOM_NORTHSOUTH_SHORT_THIN_SHAPE = Block.box(0.0, 0.0, 0.0, 16.0, 8.0, 16.0);
   private static final VoxelShape BOTTOM_NORTHSOUTH_SHORT_MID_SHAPE = Block.box(-8.0, 0.0, 0.0, 24.0, 8.0, 16.0);
   private static final VoxelShape BOTTOM_NORTHSOUTH_SHORT_WIDE_SHAPE = Block.box(-16.0, 0.0, 0.0, 32.0, 8.0, 16.0);
   private static final VoxelShape BOTTOM_NORTHSOUTH_LONG_THIN_SHAPE = Block.box(0.0, 0.0, -8.0, 16.0, 8.0, 24.0);
   private static final VoxelShape BOTTOM_NORTHSOUTH_LONG_MID_SHAPE = Block.box(-8.0, 0.0, -8.0, 24.0, 8.0, 24.0);
   private static final VoxelShape BOTTOM_NORTHSOUTH_LONG_WIDE_SHAPE = Block.box(-16.0, 0.0, -8.0, 32.0, 8.0, 24.0);
   private static final VoxelShape BOTTOM_EASTWEST_SHORT_THIN_SHAPE = Block.box(0.0, 0.0, 0.0, 16.0, 8.0, 16.0);
   private static final VoxelShape BOTTOM_EASTWEST_SHORT_MID_SHAPE = Block.box(0.0, 0.0, -8.0, 16.0, 8.0, 24.0);
   private static final VoxelShape BOTTOM_EASTWEST_SHORT_WIDE_SHAPE = Block.box(0.0, 0.0, -16.0, 16.0, 8.0, 32.0);
   private static final VoxelShape BOTTOM_EASTWEST_LONG_THIN_SHAPE = Block.box(-8.0, 0.0, 0.0, 24.0, 8.0, 16.0);
   private static final VoxelShape BOTTOM_EASTWEST_LONG_MID_SHAPE = Block.box(-8.0, 0.0, -8.0, 24.0, 8.0, 24.0);
   private static final VoxelShape BOTTOM_EASTWEST_LONG_WIDE_SHAPE = Block.box(-8.0, 0.0, -16.0, 24.0, 8.0, 32.0);
   public static final IntegerProperty LENGTH = IntegerProperty.create("length", 0, 1);
   public static final IntegerProperty WIDTH = IntegerProperty.create("width", 1, 3);
   public static final EnumProperty<net.minecraft.world.level.block.state.properties.Half> TYPE_UPDOWN = EnumProperty.create(
      "type", net.minecraft.world.level.block.state.properties.Half.class
   );
   private static final VoxelShape BOTTOM_SHAPE = Block.box(0.0, 0.0, 0.0, 16.0, 8.0, 16.0);
   private static final VoxelShape TOP_SHAPE = Block.box(0.0, 8.0, 0.0, 16.0, 16.0, 16.0);
   private static final VoxelShape[][][][] SHAPES = new VoxelShape[][][][]{
      {
            {
                  {TOP_NORTHSOUTH_SHORT_THIN_SHAPE, TOP_NORTHSOUTH_SHORT_MID_SHAPE, TOP_NORTHSOUTH_SHORT_WIDE_SHAPE},
                  {TOP_NORTHSOUTH_LONG_THIN_SHAPE, TOP_NORTHSOUTH_LONG_MID_SHAPE, TOP_NORTHSOUTH_LONG_WIDE_SHAPE}
            },
            {
                  {TOP_EASTWEST_SHORT_THIN_SHAPE, TOP_EASTWEST_SHORT_MID_SHAPE, TOP_EASTWEST_SHORT_WIDE_SHAPE},
                  {TOP_EASTWEST_LONG_THIN_SHAPE, TOP_EASTWEST_LONG_MID_SHAPE, TOP_EASTWEST_LONG_WIDE_SHAPE}
            }
      },
      {
            {
                  {BOTTOM_NORTHSOUTH_SHORT_THIN_SHAPE, BOTTOM_NORTHSOUTH_SHORT_MID_SHAPE, BOTTOM_NORTHSOUTH_SHORT_WIDE_SHAPE},
                  {BOTTOM_NORTHSOUTH_LONG_THIN_SHAPE, BOTTOM_NORTHSOUTH_LONG_MID_SHAPE, BOTTOM_NORTHSOUTH_LONG_WIDE_SHAPE}
            },
            {
                  {BOTTOM_EASTWEST_SHORT_THIN_SHAPE, BOTTOM_EASTWEST_SHORT_MID_SHAPE, BOTTOM_EASTWEST_SHORT_WIDE_SHAPE},
                  {BOTTOM_EASTWEST_LONG_THIN_SHAPE, BOTTOM_EASTWEST_LONG_MID_SHAPE, BOTTOM_EASTWEST_LONG_WIDE_SHAPE}
            }
      }
   };

   public BoardsHorizontal(Props properties) {
      super(properties);
      this.registerDefaultState(
         (BlockState)((BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue(WIDTH, 1)).setValue(LENGTH, 1)).setValue(WATERLOGGED, false)
      );
   }

   @Override
   protected void addProperties(Builder<Block, BlockState> builder) {
      super.addProperties(builder);
      builder.add(new Property[]{LENGTH, WIDTH});
   }

   @NotNull
   @Override
   public BlockState getStateForPlacement(BlockPlaceContext context) {
      return (BlockState)super.getStateForPlacement(context).setValue(LENGTH, 1);
   }

   public InteractionResult use(BlockState state, Level level, BlockPos blockPos, Player player, InteractionHand hand, BlockHitResult hitResult) {
      if (player.getAbilities().instabuild) {
         if (player.isShiftKeyDown()) {
            level.setBlock(blockPos, (BlockState)state.cycle(WIDTH), 3);
            return InteractionResult.SUCCESS;
         } else {
            level.setBlock(blockPos, (BlockState)state.cycle(LENGTH), 3);
            return InteractionResult.SUCCESS;
         }
      } else if (player.getMainHandItem().is(ModTags.CYCLING_TOOLS)) {
         if (player.isShiftKeyDown()) {
            level.setBlock(blockPos, (BlockState)state.cycle(WIDTH), 3);
            return InteractionResult.SUCCESS;
         } else {
            level.setBlock(blockPos, (BlockState)state.cycle(LENGTH), 3);
            return InteractionResult.SUCCESS;
         }
      } else {
         return InteractionResult.PASS;
      }
   }

   public VoxelShape getOcclusionShape(BlockState state, BlockGetter world, BlockPos pos) {
      return state.getValue(TYPE_UPDOWN) == net.minecraft.world.level.block.state.properties.Half.BOTTOM ? BOTTOM_SHAPE : TOP_SHAPE;
   }

   @Override
   public VoxelShape getShape(BlockState state) {
      int half = state.getValue(Half.TYPE_UPDOWN) == net.minecraft.world.level.block.state.properties.Half.BOTTOM ? 1 : 0;
      int axis = ((Direction)state.getValue(DIRECTION)).getAxis() == Axis.Z ? 1 : 0;
      int length = (Integer)state.getValue(LENGTH);
      int width = (Integer)state.getValue(WIDTH) - 1;
      return SHAPES[half][axis][length][width];
   }

   @NotNull
   @Override
   public VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
      return this.getShape(state);
   }

   @NotNull
   @Override
   public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
      return state.getValue(TYPE_UPDOWN) == net.minecraft.world.level.block.state.properties.Half.BOTTOM ? BOTTOM_SHAPE : TOP_SHAPE;
   }

   public static PropertyDispatch createEastDefaultSymmetricalRotationStates() {
      return PropertyDispatch.property(BlockStateProperties.HORIZONTAL_FACING)
         .select(Direction.EAST, Variant.variant())
         .select(Direction.SOUTH, Variant.variant().with(VariantProperties.Y_ROT, Rotation.R90))
         .select(Direction.WEST, Variant.variant())
         .select(Direction.NORTH, Variant.variant().with(VariantProperties.Y_ROT, Rotation.R90));
   }
}
