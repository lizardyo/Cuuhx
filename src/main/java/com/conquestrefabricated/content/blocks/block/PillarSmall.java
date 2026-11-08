package com.conquestrefabricated.content.blocks.block;

import com.conquestrefabricated.core.asset.annotation.Assets;
import com.conquestrefabricated.core.asset.annotation.Model;
import com.conquestrefabricated.core.asset.annotation.State;
import com.conquestrefabricated.core.block.base.WaterloggedShape;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.phys.shapes.VoxelShape;

@Assets(
   state = @State(name = "%s_small_pillar", template = "parent_pillar_small"),
   item = @Model(name = "item/%s_small_pillar", parent = "block/%s_fence_post", template = "item/dragon_egg"),
   block = @Model(name = "block/%s_fence_post", template = "block/acacia_fence_post")
)
public class PillarSmall extends WaterloggedShape {
   protected static final VoxelShape SHAPE = Block.box(6.0, 0.0, 6.0, 10.0, 16.0, 10.0);

   public PillarSmall(Properties properties) {
      super(properties);
      this.registerDefaultState((BlockState)((BlockState)this.stateDefinition.any()).setValue(WATERLOGGED, false));
   }

   @Override
   public VoxelShape getShape(BlockState state) {
      return SHAPE;
   }
}
