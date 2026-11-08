package com.conquestrefabricated.content.blocks.block.topography;

import com.conquestrefabricated.content.blocks.BlockSettingsAccessor;
import com.conquestrefabricated.content.blocks.CustomOffsetType;
import com.conquestrefabricated.content.blocks.block.Layer;
import com.conquestrefabricated.content.blocks.util.Interactions;
import com.conquestrefabricated.core.asset.annotation.Assets;
import com.conquestrefabricated.core.asset.annotation.Model;
import com.conquestrefabricated.core.asset.annotation.State;
import com.conquestrefabricated.core.block.base.WaterloggedShape;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.OffsetType;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

@Assets(
   state = @State(name = "%s_rocks", template = "parent_rocks"),
   item = @Model(name = "item/%s_rocks", parent = "block/%s_rocks_1_8", template = "item/parent_round_arch"),
   block = {}
)
public class Rocks extends WaterloggedShape {
   public static final BooleanProperty EDGE = BooleanProperty.create("edge");
   public static final IntegerProperty LAYERS = BlockStateProperties.LAYERS;
   public static final IntegerProperty DENSITY = IntegerProperty.create("density", 1, 4);
   private static final VoxelShape SHAPE = Block.box(1.0, 0.0, 1.0, 15.0, 15.0, 15.0);

   public Rocks(Properties properties) {
      super(((BlockSettingsAccessor)properties).setCustomOffsetter(CustomOffsetType.LAYERS_STATIC).offsetType(OffsetType.NONE).dynamicShape());
      this.registerDefaultState(
         (BlockState)((BlockState)((BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue(DENSITY, 1)).setValue(EDGE, true))
               .setValue(WATERLOGGED, false))
            .setValue(LAYERS, 8)
      );
   }

   @Override
   public VoxelShape getShape(BlockState state) {
      return SHAPE;
   }

   @Override
   public VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
      return Shapes.empty();
   }

   public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor world, BlockPos currentPos, BlockPos facingPos) {
      BlockPos down = currentPos.below();
      BlockState blockStateDown = world.getBlockState(down);
      if (blockStateDown.hasProperty(Layer.LAYERS)) {
         if (blockStateDown.getBlock() instanceof Layer && (Integer)blockStateDown.getValue(Layer.LAYERS) == 1) {
            boolean northBool = this.canConnectTo(world, currentPos.north().below(), blockStateDown)
               || this.canConnectTo(world, currentPos.north().below().below(), blockStateDown);
            boolean eastBool = this.canConnectTo(world, currentPos.east().below(), blockStateDown)
               || this.canConnectTo(world, currentPos.east().below().below(), blockStateDown);
            boolean southBool = this.canConnectTo(world, currentPos.south().below(), blockStateDown)
               || this.canConnectTo(world, currentPos.south().below().below(), blockStateDown);
            boolean westBool = this.canConnectTo(world, currentPos.west().below(), blockStateDown)
               || this.canConnectTo(world, currentPos.west().below().below(), blockStateDown);
            return (BlockState)((BlockState)super.updateShape(stateIn, facing, facingState, world, currentPos, facingPos)
                  .setValue(EDGE, !northBool || !eastBool || !southBool || !westBool))
               .setValue(LAYERS, (Integer)blockStateDown.getValue(LAYERS));
         } else {
            return (BlockState)((BlockState)super.updateShape(stateIn, facing, facingState, world, currentPos, facingPos)
                  .setValue(
                     EDGE,
                     !this.canConnectTo(world, currentPos.north().below(), blockStateDown)
                        || !this.canConnectTo(world, currentPos.east().below(), blockStateDown)
                        || !this.canConnectTo(world, currentPos.south().below(), blockStateDown)
                        || !this.canConnectTo(world, currentPos.west().below(), blockStateDown)
                  ))
               .setValue(LAYERS, (Integer)blockStateDown.getValue(LAYERS));
         }
      } else {
         return (BlockState)((BlockState)super.updateShape(stateIn, facing, facingState, world, currentPos, facingPos)
               .setValue(
                  EDGE,
                  !this.canConnectTo(world, currentPos.north().below(), blockStateDown)
                     || !this.canConnectTo(world, currentPos.east().below(), blockStateDown)
                     || !this.canConnectTo(world, currentPos.south().below(), blockStateDown)
                     || !this.canConnectTo(world, currentPos.west().below(), blockStateDown)
               ))
            .setValue(LAYERS, 8);
      }
   }

   private boolean canConnectTo(LevelAccessor world, BlockPos pos, BlockState downLayer) {
      BlockState blockstate = world.getBlockState(pos);
      if (blockstate.getBlock() instanceof Layer && downLayer.getBlock() instanceof Layer) {
         if ((Integer)blockstate.getValue(LAYERS) > (Integer)downLayer.getValue(LAYERS)) {
            return blockstate.canOcclude();
         } else {
            return downLayer.getValue(LAYERS) == 1 && blockstate.getValue(LAYERS) == 8
               ? true
               : Math.abs((Integer)downLayer.getValue(LAYERS) - (Integer)blockstate.getValue(LAYERS)) < 2;
         }
      } else {
         return blockstate.isCollisionShapeFullBlock(world, pos);
      }
   }

   private boolean canConnect(BlockState directionLayer, BlockState downLayer) {
      if ((Integer)directionLayer.getValue(LAYERS) > (Integer)downLayer.getValue(LAYERS)) {
         return directionLayer.canOcclude();
      } else {
         return downLayer.getValue(LAYERS) == 1 && directionLayer.getValue(LAYERS) == 8
            ? true
            : Math.abs((Integer)downLayer.getValue(LAYERS) - (Integer)directionLayer.getValue(LAYERS)) < 2;
      }
   }

   @Override
   public BlockState getStateForPlacement(BlockPlaceContext context) {
      FluidState ifluidstate = context.getLevel().getFluidState(context.getClickedPos());
      BlockPos pos = context.getClickedPos();
      BlockGetter iblockreader = context.getLevel();
      BlockState northBlock = iblockreader.getBlockState(pos.north().below());
      BlockState eastBlock = iblockreader.getBlockState(pos.east().below());
      BlockState southBlock = iblockreader.getBlockState(pos.south().below());
      BlockState westBlock = iblockreader.getBlockState(pos.west().below());
      boolean north = northBlock.isCollisionShapeFullBlock(context.getLevel(), pos.north().below())
         || northBlock.getBlock() instanceof Layer && (Integer)northBlock.getValue(LAYERS) == 8;
      boolean east = eastBlock.isCollisionShapeFullBlock(context.getLevel(), pos.east().below())
         || eastBlock.getBlock() instanceof Layer && (Integer)eastBlock.getValue(LAYERS) == 8;
      boolean south = southBlock.isCollisionShapeFullBlock(context.getLevel(), pos.south().below())
         || southBlock.getBlock() instanceof Layer && (Integer)southBlock.getValue(LAYERS) == 8;
      boolean west = westBlock.isCollisionShapeFullBlock(context.getLevel(), pos.west().below())
         || westBlock.getBlock() instanceof Layer && (Integer)westBlock.getValue(LAYERS) == 8;
      BlockPos down = pos.below();
      BlockState blockStateDown = iblockreader.getBlockState(down);
      if (blockStateDown.hasProperty(Layer.LAYERS)) {
         north = northBlock.getBlock() instanceof Layer ? this.canConnect(northBlock, blockStateDown) : north;
         east = eastBlock.getBlock() instanceof Layer ? this.canConnect(eastBlock, blockStateDown) : east;
         south = southBlock.getBlock() instanceof Layer ? this.canConnect(southBlock, blockStateDown) : south;
         west = westBlock.getBlock() instanceof Layer ? this.canConnect(westBlock, blockStateDown) : west;
         return blockStateDown.getBlock() instanceof Layer && blockStateDown.getValue(Layer.LAYERS) == 1
            ? (BlockState)((BlockState)((BlockState)super.getStateForPlacement(context)
                     .setValue(
                        EDGE,
                        !north
                              && !iblockreader.getBlockState(pos.north().below().below()).isCollisionShapeFullBlock(context.getLevel(), pos.north().below().below())
                              && (
                                 !(iblockreader.getBlockState(pos.north().below().below()).getBlock() instanceof Layer)
                                    || (Integer)iblockreader.getBlockState(pos.north().below().below()).getValue(LAYERS) != 8
                              )
                           || !east
                              && !iblockreader.getBlockState(pos.east().below().below()).isCollisionShapeFullBlock(context.getLevel(), pos.east().below().below())
                              && (
                                 !(iblockreader.getBlockState(pos.east().below().below()).getBlock() instanceof Layer)
                                    || (Integer)iblockreader.getBlockState(pos.east().below().below()).getValue(LAYERS) != 8
                              )
                           || !south
                              && !iblockreader.getBlockState(pos.south().below().below()).isCollisionShapeFullBlock(context.getLevel(), pos.south().below().below())
                              && (
                                 !(iblockreader.getBlockState(pos.south().below().below()).getBlock() instanceof Layer)
                                    || (Integer)iblockreader.getBlockState(pos.south().below().below()).getValue(LAYERS) != 8
                              )
                           || !west
                              && !iblockreader.getBlockState(pos.west().below().below()).isCollisionShapeFullBlock(context.getLevel(), pos.west().below().below())
                              && (
                                 !(iblockreader.getBlockState(pos.west().below().below()).getBlock() instanceof Layer)
                                    || (Integer)iblockreader.getBlockState(pos.west().below().below()).getValue(LAYERS) != 8
                              )
                     ))
                  .setValue(WATERLOGGED, ifluidstate.getType() == Fluids.WATER))
               .setValue(LAYERS, (Integer)blockStateDown.getValue(LAYERS))
            : (BlockState)((BlockState)((BlockState)super.getStateForPlacement(context).setValue(EDGE, !north || !east || !south || !west))
                  .setValue(WATERLOGGED, ifluidstate.getType() == Fluids.WATER))
               .setValue(LAYERS, (Integer)blockStateDown.getValue(LAYERS));
      } else {
         return (BlockState)((BlockState)((BlockState)super.getStateForPlacement(context).setValue(EDGE, !north || !east || !south || !west))
               .setValue(WATERLOGGED, ifluidstate.getType() == Fluids.WATER))
            .setValue(LAYERS, 8);
      }
   }

   public InteractionResult use(BlockState state, Level level, BlockPos blockPos, Player player, InteractionHand hand, BlockHitResult hitResult) {
      return Interactions.onUseToggleItem(player, level, blockPos, state, DENSITY);
   }

   @Override
   protected void addProperties(Builder<Block, BlockState> builder) {
      builder.add(new Property[]{EDGE, DENSITY, LAYERS});
   }

   public OffsetType getOffsetType() {
      return OffsetType.XZ;
   }
}
