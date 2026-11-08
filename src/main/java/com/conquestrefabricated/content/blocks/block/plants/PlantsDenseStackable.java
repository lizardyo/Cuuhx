package com.conquestrefabricated.content.blocks.block.plants;

import com.conquestrefabricated.api.tags.ModTags;
import com.conquestrefabricated.client.gui.config.ConquestConfig;
import com.conquestrefabricated.content.blocks.block.Layer;
import com.conquestrefabricated.content.blocks.block.Slab;
import com.conquestrefabricated.content.effects.Effects;
import com.conquestrefabricated.core.asset.annotation.Assets;
import com.conquestrefabricated.core.asset.annotation.Model;
import com.conquestrefabricated.core.asset.annotation.Render;
import com.conquestrefabricated.core.asset.annotation.State;
import com.conquestrefabricated.core.block.builder.Props;
import com.conquestrefabricated.core.util.RenderLayer;
import java.util.Random;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;

@Assets(
   state = @State(name = "%s", template = "parent_plant_dense_stackable"),
   item = @Model(name = "item/%s", parent = "block/%s_pane_ns", template = "item/parent_round_arch"),
   render = @Render(RenderLayer.CUTOUT),
   block = {
         @Model(name = "block/%s_plant_dense_model_1_a", template = "block/parent_plant_dense_model_1_a"),
         @Model(name = "block/%s_plant_dense_model_1_b", template = "block/parent_plant_dense_model_1_b"),
         @Model(name = "block/%s_plant_dense_model_1_c", template = "block/parent_plant_dense_model_1_c"),
         @Model(name = "block/%s_plant_dense_model_2_a", template = "block/parent_plant_dense_model_2_a"),
         @Model(name = "block/%s_plant_dense_model_2_b", template = "block/parent_plant_dense_model_2_b"),
         @Model(name = "block/%s_plant_dense_model_2_c", template = "block/parent_plant_dense_model_2_c"),
         @Model(name = "block/%s_plant_dense_model_3_a", template = "block/parent_plant_dense_model_3_a"),
         @Model(name = "block/%s_plant_dense_model_3_b", template = "block/parent_plant_dense_model_3_b"),
         @Model(name = "block/%s_plant_dense_model_3_c", template = "block/parent_plant_dense_model_3_c"),
         @Model(name = "block/%s_plant_dense_model_4_a", template = "block/parent_plant_dense_model_4_a"),
         @Model(name = "block/%s_plant_dense_model_4_b", template = "block/parent_plant_dense_model_4_b"),
         @Model(name = "block/%s_plant_dense_model_4_c", template = "block/parent_plant_dense_model_4_c"),
         @Model(name = "block/%s_plant_dense_model_5_a", template = "block/parent_plant_dense_model_5_a"),
         @Model(name = "block/%s_plant_dense_model_5_b", template = "block/parent_plant_dense_model_5_b"),
         @Model(name = "block/%s_plant_dense_model_5_c", template = "block/parent_plant_dense_model_5_c"),
         @Model(name = "block/%s_plant_dense_model_6_a", template = "block/parent_plant_dense_model_6_a"),
         @Model(name = "block/%s_plant_dense_model_6_b", template = "block/parent_plant_dense_model_6_b"),
         @Model(name = "block/%s_plant_dense_model_6_c", template = "block/parent_plant_dense_model_6_c"),
         @Model(name = "block/%s_plant_dense_model_7_a", template = "block/parent_plant_dense_model_7_a"),
         @Model(name = "block/%s_plant_dense_model_7_b", template = "block/parent_plant_dense_model_7_b"),
         @Model(name = "block/%s_plant_dense_model_7_c", template = "block/parent_plant_dense_model_7_c"),
         @Model(name = "block/%s_plant_dense_model_8_a", template = "block/parent_plant_dense_model_8_a"),
         @Model(name = "block/%s_plant_dense_model_8_b", template = "block/parent_plant_dense_model_8_b"),
         @Model(name = "block/%s_plant_dense_model_8_c", template = "block/parent_plant_dense_model_8_c"),
         @Model(name = "block/%s_pane_ns", template = "block/parent_flatpane_ns")
   }
)
public class PlantsDenseStackable extends Bush {
   private static final IntegerProperty RANDOM = IntegerProperty.create("random", 0, 2);

   public PlantsDenseStackable(Props properties) {
      super(properties);
   }

   @Override
   public void entityInside(BlockState state, Level world, BlockPos pos, Entity entity) {
      if (entity instanceof LivingEntity livingEntity && state.is(ModTags.PLANT_SLOWNESS) && (Boolean)ConquestConfig.INSTANCE.plantSlowness.get()) {
         if (livingEntity instanceof Player) {
            livingEntity.addEffect(new MobEffectInstance(Effects.CUSTOM_SLOWNESS, 15, 3, false, false));
         } else {
            livingEntity.addEffect(new MobEffectInstance(Effects.CUSTOM_SLOWNESS, 15, 0, false, false));
         }
      }
   }

   @Override
   public BlockState getStateForPlacement(BlockPlaceContext context) {
      BlockGetter iblockreader = context.getLevel();
      BlockPos blockpos = context.getClickedPos();
      BlockPos up = blockpos.above();
      BlockPos down = blockpos.below();
      BlockState blockStateUp = iblockreader.getBlockState(up);
      BlockState blockStateDown = iblockreader.getBlockState(down);
      Random rand = new Random();
      int randomBlockstate = rand.nextInt(3);
      int layerBlockState = 8;
      if (blockStateDown.hasProperty(Layer.LAYERS) || blockStateDown.hasProperty(Slab.LAYERS) || blockStateDown.hasProperty(LAYERS)) {
         layerBlockState = (Integer)blockStateDown.getValue(LAYERS);
      } else if (blockStateUp.hasProperty(Layer.LAYERS) || blockStateUp.hasProperty(Slab.LAYERS) || blockStateUp.hasProperty(LAYERS)) {
         layerBlockState = (Integer)blockStateUp.getValue(LAYERS);
      }

      if (blockStateUp.getBlock() instanceof PlantsDenseStackable) {
         randomBlockstate = (Integer)blockStateUp.getValue(RANDOM);
      } else if (blockStateDown.getBlock() instanceof PlantsDenseStackable) {
         randomBlockstate = (Integer)blockStateDown.getValue(RANDOM);
      }

      return (BlockState)((BlockState)super.getStateForPlacement(context).setValue(LAYERS, layerBlockState)).setValue(RANDOM, randomBlockstate);
   }

   @Override
   protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
      builder.add(new Property[]{RANDOM, LAYERS, WATERLOGGED, OFFSET_TOGGLE});
   }
}
