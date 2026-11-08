package com.conquestrefabricated.content.blocks.block.tracery;

import com.conquestrefabricated.content.blocks.block.VerticalSlab;
import com.conquestrefabricated.core.asset.annotation.Assets;
import com.conquestrefabricated.core.asset.annotation.Model;
import com.conquestrefabricated.core.asset.annotation.Render;
import com.conquestrefabricated.core.asset.annotation.State;
import com.conquestrefabricated.core.block.builder.Props;
import com.conquestrefabricated.core.util.RenderLayer;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@Assets(
   state = @State(name = "%s_vertical_slab", template = "parent_vertical_slab"),
   item = @Model(name = "item/%s_vertical_slab", parent = "block/%s_vertical_slab_4", template = "item/parent_vertical_slab"),
   render = @Render(RenderLayer.CUTOUT),
   block = {
         @Model(name = "block/%s_vertical_slab_1", template = "block/parent_vertical_slab_1"),
         @Model(name = "block/%s_vertical_slab_2", template = "block/parent_vertical_slab_2"),
         @Model(name = "block/%s_vertical_slab_4", template = "block/parent_vertical_slab_4"),
         @Model(name = "block/%s_vertical_slab_6", template = "block/parent_vertical_slab_6")
   }
)
public class VerticalSlabTracery extends VerticalSlab {
   public VerticalSlabTracery(Props properties) {
      super(properties);
   }

   @OnlyIn(Dist.CLIENT)
   public boolean skipRendering(BlockState state, BlockState adjacentBlockState, Direction side) {
      if (side != Direction.UP && side != Direction.DOWN) {
         if (side == state.getValue(DIRECTION)) {
            return false;
         }

         if (adjacentBlockState.getBlock() instanceof GlassTracery) {
            return true;
         }

         if (adjacentBlockState.getBlock() instanceof VerticalSlabTracery
            && (Integer)adjacentBlockState.getValue(LAYERS) >= (Integer)state.getValue(LAYERS)
            && state.getValue(DIRECTION) == ((Direction)adjacentBlockState.getValue(DIRECTION)).getOpposite()
            && ((Direction)state.getValue(DIRECTION)).getOpposite() == side) {
            return true;
         }

         if (!(adjacentBlockState.getBlock() instanceof VerticalSlabTracery)
            || (Integer)adjacentBlockState.getValue(LAYERS) < (Integer)state.getValue(LAYERS)
            || (
                  state.getValue(DIRECTION) != adjacentBlockState.getValue(DIRECTION)
                     || ((Direction)state.getValue(DIRECTION)).getClockWise() != side && ((Direction)state.getValue(DIRECTION)).getCounterClockWise() != side
               )
               && (
                  ((Direction)state.getValue(DIRECTION)).getClockWise() != side && ((Direction)state.getValue(DIRECTION)).getCounterClockWise() != side
                     || adjacentBlockState.getValue(DIRECTION) != side
               )
               && (
                  state.getValue(DIRECTION) != ((Direction)adjacentBlockState.getValue(DIRECTION)).getOpposite()
                     || ((Direction)state.getValue(DIRECTION)).getOpposite() != side
               )) {
            if (adjacentBlockState.getBlock() instanceof VerticalCornerTracery
               && (Integer)adjacentBlockState.getValue(LAYERS) >= (Integer)state.getValue(LAYERS)
               && (
                  adjacentBlockState.getValue(DIRECTION) == ((Direction)state.getValue(DIRECTION)).getOpposite()
                     || adjacentBlockState.getValue(DIRECTION) == ((Direction)state.getValue(DIRECTION)).getCounterClockWise()
               )
               && side == ((Direction)state.getValue(DIRECTION)).getOpposite()) {
               return true;
            } else if (!(adjacentBlockState.getBlock() instanceof VerticalCornerTracery)
               || (Integer)adjacentBlockState.getValue(LAYERS) < (Integer)state.getValue(LAYERS)
               || (
                     adjacentBlockState.getValue(DIRECTION) == ((Direction)state.getValue(DIRECTION)).getCounterClockWise()
                        || side != ((Direction)state.getValue(DIRECTION)).getClockWise()
                  )
                  && (
                     adjacentBlockState.getValue(DIRECTION) != ((Direction)state.getValue(DIRECTION)).getOpposite()
                           && adjacentBlockState.getValue(DIRECTION) != ((Direction)state.getValue(DIRECTION)).getCounterClockWise()
                        || side != ((Direction)state.getValue(DIRECTION)).getOpposite()
                  )
                  && (
                     adjacentBlockState.getValue(DIRECTION) == ((Direction)state.getValue(DIRECTION)).getOpposite()
                        || side != ((Direction)state.getValue(DIRECTION)).getCounterClockWise()
                  )) {
               return !(adjacentBlockState.getBlock() instanceof VerticalQuarterTracery)
                     || adjacentBlockState.getValue(LAYERS) < state.getValue(LAYERS)
                     || (
                           ((Direction)adjacentBlockState.getValue(DIRECTION)).getCounterClockWise() != side
                              || adjacentBlockState.getValue(DIRECTION) != state.getValue(DIRECTION)
                        )
                        && (
                           adjacentBlockState.getValue(DIRECTION) != side
                              || adjacentBlockState.getValue(DIRECTION) != ((Direction)state.getValue(DIRECTION)).getClockWise()
                        )
                  ? super.skipRendering(state, adjacentBlockState, side)
                  : true;
            } else {
               return true;
            }
         } else {
            return true;
         }
      } else if (adjacentBlockState.getBlock() instanceof GlassTracery) {
         return true;
      } else {
         return adjacentBlockState.getBlock() instanceof VerticalSlabTracery
               && adjacentBlockState.getValue(LAYERS) >= state.getValue(LAYERS)
               && state.getValue(DIRECTION) == adjacentBlockState.getValue(DIRECTION)
            ? true
            : adjacentBlockState.getBlock() instanceof VerticalCornerTracery
               && (Integer)adjacentBlockState.getValue(LAYERS) >= (Integer)state.getValue(LAYERS)
               && (
                  state.getValue(DIRECTION) == adjacentBlockState.getValue(DIRECTION)
                     || state.getValue(DIRECTION) == ((Direction)adjacentBlockState.getValue(DIRECTION)).getCounterClockWise()
               );
      }
   }
}
