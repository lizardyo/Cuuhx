package com.conquestrefabricated.content.blocks.block.plants;

import com.conquestrefabricated.core.asset.annotation.Render;
import com.conquestrefabricated.core.block.builder.Props;
import com.conquestrefabricated.core.util.RenderLayer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

@Render(RenderLayer.CUTOUT_MIPPED)
public class Crops extends AbstractCropsBlock {
   private final ItemLike seeds;
   private final ItemLike crop;

   public Crops(Props props) {
      super(props);
      this.seeds = props.get("seeds", ItemLike.class);
      this.crop = props.get("crop", ItemLike.class);
   }

   public VoxelShape getInteractionShape(BlockState state, BlockGetter world, BlockPos pos) {
      return Shapes.block();
   }

   protected ItemLike getBaseSeedId() {
      return this.seeds;
   }

   public ItemStack getCloneItemStack(BlockGetter world, BlockPos pos, BlockState state) {
      return new ItemStack(this);
   }
}
