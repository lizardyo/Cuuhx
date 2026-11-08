package com.conquestrefabricated.content.blocks.block.plants;

import com.conquestrefabricated.content.blocks.BlockVoxelShapes;
import com.conquestrefabricated.content.blocks.util.PlacementHelper;
import com.conquestrefabricated.core.asset.annotation.Render;
import com.conquestrefabricated.core.block.builder.Props;
import com.conquestrefabricated.core.util.RenderLayer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

@Render(RenderLayer.CUTOUT)
public class BeanPole extends CropBlock {
   private final ItemLike seeds;
   private final ItemLike crop;

   public BeanPole(Props props) {
      super(props.toSettings());
      this.seeds = props.get("seeds", ItemLike.class);
      this.crop = props.get("crop", ItemLike.class);
   }

   public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
      return BlockVoxelShapes.pillarShape.get(0);
   }

   public boolean canSurvive(BlockState state, LevelReader reader, BlockPos pos) {
      return PlacementHelper.isDuringWorldGen(reader) ? super.canSurvive(state, reader, pos) : true;
   }

   public boolean mayPlaceOn(BlockState state, BlockGetter reader, BlockPos pos) {
      return PlacementHelper.isDuringWorldGen(reader) ? super.mayPlaceOn(state, reader, pos) : true;
   }

   protected ItemLike getBaseSeedId() {
      return this.seeds;
   }

   public ItemStack getCloneItemStack(BlockGetter world, BlockPos pos, BlockState state) {
      return new ItemStack(this);
   }

   public void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource rand) {
      if (world.getRawBrightness(pos, 0) >= 9 && world.getRawBrightness(pos.above(), 0) >= 9) {
         int i = this.getAge(state);
         if (i < this.getMaxAge()) {
            int height = 1;

            while (world.getBlockState(pos.below(height)).getBlock() == this) {
               height++;
            }

            if (height < 3) {
               float f = getGrowthSpeed(this, world, pos);
               if (rand.nextInt((int)(25.0F / f) + 1) == 0
                  && (world.getBlockState(pos.below()).getBlock() != this || (Integer)world.getBlockState(pos.below()).getValue(this.getAgeProperty()) > i)) {
                  world.setBlock(pos, (BlockState)state.setValue(AGE, i + 1), 2);
                  if (world.getBlockState(pos.above()).getBlock() == BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath("conquest", "wooden_pole"))) {
                     world.setBlockAndUpdate(pos.above(), this.defaultBlockState());
                  }
               }
            }
         }
      }
   }
}
