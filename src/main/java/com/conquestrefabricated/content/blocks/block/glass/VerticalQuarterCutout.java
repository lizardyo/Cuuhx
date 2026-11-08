package com.conquestrefabricated.content.blocks.block.glass;

import com.conquestrefabricated.content.blocks.block.VerticalQuarter;
import com.conquestrefabricated.core.asset.annotation.Assets;
import com.conquestrefabricated.core.asset.annotation.Model;
import com.conquestrefabricated.core.asset.annotation.Render;
import com.conquestrefabricated.core.asset.annotation.State;
import com.conquestrefabricated.core.block.builder.Props;
import com.conquestrefabricated.core.util.RenderLayer;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.TransparentBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@Assets(
   state = @State(name = "%s_vertical_quarter", template = "parent_vertical_quarter"),
   item = @Model(name = "item/%s_vertical_quarter", parent = "block/%s_vertical_quarter_4", template = "item/parent_vertical_quarter"),
   render = @Render(RenderLayer.CUTOUT),
   block = {
         @Model(name = "block/%s_vertical_quarter_1", template = "block/parent_vertical_quarter_1"),
         @Model(name = "block/%s_vertical_quarter_2", template = "block/parent_vertical_quarter_2"),
         @Model(name = "block/%s_vertical_quarter_4", template = "block/parent_vertical_quarter_4"),
         @Model(name = "block/%s_vertical_quarter_6", template = "block/parent_vertical_quarter_6")
   }
)
public class VerticalQuarterCutout extends VerticalQuarter {
   public VerticalQuarterCutout(Props properties) {
      super(properties);
   }

   @OnlyIn(Dist.CLIENT)
   public boolean skipRendering(BlockState state, BlockState adjacentBlockState, Direction side) {
      if (side != Direction.UP && side != Direction.DOWN) {
         if (side == state.getValue(DIRECTION) || side == ((Direction)state.getValue(DIRECTION)).getCounterClockWise()) {
            return false;
         }

         if (adjacentBlockState.getBlock() instanceof GlassBlock) {
            return true;
         }

         if (!(adjacentBlockState.getBlock() instanceof VerticalSlabCutout)
            || (Integer)adjacentBlockState.getValue(LAYERS) < (Integer)state.getValue(LAYERS)
            || (
                  ((Direction)state.getValue(DIRECTION)).getOpposite() != adjacentBlockState.getValue(DIRECTION)
                        && state.getValue(DIRECTION) != ((Direction)adjacentBlockState.getValue(DIRECTION)).getClockWise()
                     || side != ((Direction)state.getValue(DIRECTION)).getOpposite()
               )
               && (
                  state.getValue(DIRECTION) != adjacentBlockState.getValue(DIRECTION)
                        && state.getValue(DIRECTION) != ((Direction)adjacentBlockState.getValue(DIRECTION)).getCounterClockWise()
                     || side != ((Direction)state.getValue(DIRECTION)).getClockWise()
               )) {
            if (adjacentBlockState.getBlock() instanceof VerticalCornerCutout
               && (Integer)adjacentBlockState.getValue(LAYERS) >= (Integer)state.getValue(LAYERS)
               && (
                  adjacentBlockState.getValue(DIRECTION) != ((Direction)state.getValue(DIRECTION)).getClockWise()
                        && side == ((Direction)state.getValue(DIRECTION)).getOpposite()
                     || adjacentBlockState.getValue(DIRECTION) != ((Direction)state.getValue(DIRECTION)).getCounterClockWise()
                        && side == ((Direction)state.getValue(DIRECTION)).getClockWise()
               )) {
               return true;
            } else {
               return !(adjacentBlockState.getBlock() instanceof VerticalQuarterCutout)
                     || adjacentBlockState.getValue(LAYERS) < state.getValue(LAYERS)
                     || (
                           adjacentBlockState.getValue(DIRECTION) != ((Direction)state.getValue(DIRECTION)).getCounterClockWise()
                              || side != ((Direction)state.getValue(DIRECTION)).getOpposite()
                        )
                        && (
                           adjacentBlockState.getValue(DIRECTION) != ((Direction)state.getValue(DIRECTION)).getClockWise()
                              || side != ((Direction)state.getValue(DIRECTION)).getClockWise()
                        )
                  ? super.skipRendering(state, adjacentBlockState, side)
                  : true;
            }
         } else {
            return true;
         }
      } else if (adjacentBlockState.getBlock() instanceof GlassBlock) {
         return true;
      } else if (adjacentBlockState.getBlock() instanceof VerticalQuarterCutout
         && (Integer)adjacentBlockState.getValue(LAYERS) >= (Integer)state.getValue(LAYERS)
         && state.getValue(DIRECTION) == adjacentBlockState.getValue(DIRECTION)) {
         return true;
      } else {
         return !(adjacentBlockState.getBlock() instanceof VerticalSlabCutout)
               || adjacentBlockState.getValue(LAYERS) < state.getValue(LAYERS)
               || state.getValue(DIRECTION) != adjacentBlockState.getValue(DIRECTION)
                  && state.getValue(DIRECTION) != ((Direction)adjacentBlockState.getValue(DIRECTION)).getClockWise()
            ? adjacentBlockState.getBlock() instanceof VerticalCornerCutout
               && (Integer)adjacentBlockState.getValue(LAYERS) >= (Integer)state.getValue(LAYERS)
               && (
                  state.getValue(DIRECTION) == adjacentBlockState.getValue(DIRECTION)
                     || state.getValue(DIRECTION) == ((Direction)adjacentBlockState.getValue(DIRECTION)).getClockWise()
                     || state.getValue(DIRECTION) == ((Direction)adjacentBlockState.getValue(DIRECTION)).getCounterClockWise()
               )
            : true;
      }
   }
}
