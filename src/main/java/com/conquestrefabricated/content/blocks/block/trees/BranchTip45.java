package com.conquestrefabricated.content.blocks.block.trees;

import com.conquestrefabricated.core.asset.annotation.ItemDescription;
import com.conquestrefabricated.core.block.builder.Props;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.state.BlockState;

@ItemDescription(description = "toggle_2")
public class BranchTip45 extends BranchTip {
   public BranchTip45(Props props) {
      super(props);
      this.registerDefaultState((BlockState)((BlockState)this.stateDefinition.any()).setValue(DOWN, false));
   }

   @Override
   public BlockState mirror(BlockState state, Mirror mirrorIn) {
      switch (mirrorIn) {
         case FRONT_BACK:
            switch ((Direction)state.getValue(DIRECTION)) {
               case NORTH:
                  return (BlockState)state.setValue(DIRECTION, Direction.EAST);
               case EAST:
                  return (BlockState)state.setValue(DIRECTION, Direction.NORTH);
               case SOUTH:
                  return (BlockState)state.setValue(DIRECTION, Direction.WEST);
               case WEST:
                  return (BlockState)state.setValue(DIRECTION, Direction.SOUTH);
               default:
                  return super.mirror(state, mirrorIn);
            }
         case LEFT_RIGHT:
            switch ((Direction)state.getValue(DIRECTION)) {
               case NORTH:
                  return (BlockState)state.setValue(DIRECTION, Direction.WEST);
               case EAST:
                  return (BlockState)state.setValue(DIRECTION, Direction.SOUTH);
               case SOUTH:
                  return (BlockState)state.setValue(DIRECTION, Direction.EAST);
               case WEST:
                  return (BlockState)state.setValue(DIRECTION, Direction.NORTH);
               default:
                  return super.mirror(state, mirrorIn);
            }
         default:
            return super.mirror(state, mirrorIn);
      }
   }
}
