package com.conquestrefabricated.content.blocks.block.trees;

import com.conquestrefabricated.content.blocks.BlockVoxelShapes;
import com.conquestrefabricated.core.asset.annotation.Render;
import com.conquestrefabricated.core.block.builder.Props;
import com.conquestrefabricated.core.util.RenderLayer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

@Render(RenderLayer.CUTOUT_MIPPED)
public class LeavesLightToggle extends LeavesBlock {
   public static final BooleanProperty LIGHT_PASS_THRU = BooleanProperty.create("light_pass_thru");
   private final ItemLike sapling;

   public LeavesLightToggle(Props props) {
      super(props.toSettings());
      this.sapling = props.get("sapling", ItemLike.class);
      this.registerDefaultState(
         (BlockState)((BlockState)((BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue(LIGHT_PASS_THRU, false)).setValue(DISTANCE, 7))
               .setValue(PERSISTENT, false))
            .setValue(WATERLOGGED, false)
      );
   }

   @Deprecated
   public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
      return state.getValue(LIGHT_PASS_THRU) ? BlockVoxelShapes.cubePartialShape.get(0) : Shapes.block();
   }

   public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
      return state.getValue(LIGHT_PASS_THRU) ? 0 : 1;
   }

   protected void createBlockStateDefinition(Builder<Block, BlockState> stateList) {
      stateList.add(new Property[]{LIGHT_PASS_THRU, DISTANCE, PERSISTENT, WATERLOGGED});
   }
}
