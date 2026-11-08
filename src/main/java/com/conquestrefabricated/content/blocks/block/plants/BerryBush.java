package com.conquestrefabricated.content.blocks.block.plants;

import com.conquestrefabricated.api.tags.ModTags;
import com.conquestrefabricated.client.gui.config.ConquestConfig;
import com.conquestrefabricated.content.blocks.BlockVoxelShapes;
import com.conquestrefabricated.content.effects.Effects;
import com.conquestrefabricated.core.asset.annotation.Assets;
import com.conquestrefabricated.core.asset.annotation.Model;
import com.conquestrefabricated.core.asset.annotation.Render;
import com.conquestrefabricated.core.asset.annotation.State;
import com.conquestrefabricated.core.block.builder.Props;
import com.conquestrefabricated.core.util.RenderLayer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.OffsetType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

@Assets(
   state = @State(name = "%s", template = "parent_berry_bush"),
   item = @Model(name = "item/%s", parent = "block/%s_pane_ns", template = "item/parent_round_arch"),
   render = @Render(RenderLayer.CUTOUT),
   block = {
         @Model(name = "block/%s_shrub_model_1_a", template = "block/parent_shrub_model_1_a"),
         @Model(name = "block/%s_shrub_model_1_b", template = "block/parent_shrub_model_1_b"),
         @Model(name = "block/%s_shrub_model_1_c", template = "block/parent_shrub_model_1_c"),
         @Model(name = "block/%s_berry_bush_model_1_a", template = "block/parent_berry_bush_model_1_a"),
         @Model(name = "block/%s_berry_bush_model_1_b", template = "block/parent_berry_bush_model_1_b"),
         @Model(name = "block/%s_berry_bush_model_1_c", template = "block/parent_berry_bush_model_1_c"),
         @Model(name = "block/%s_shrub_model_2_a", template = "block/parent_shrub_model_2_a"),
         @Model(name = "block/%s_shrub_model_2_b", template = "block/parent_shrub_model_2_b"),
         @Model(name = "block/%s_shrub_model_2_c", template = "block/parent_shrub_model_2_c"),
         @Model(name = "block/%s_berry_bush_model_2_a", template = "block/parent_berry_bush_model_2_a"),
         @Model(name = "block/%s_berry_bush_model_2_b", template = "block/parent_berry_bush_model_2_b"),
         @Model(name = "block/%s_berry_bush_model_2_c", template = "block/parent_berry_bush_model_2_c"),
         @Model(name = "block/%s_shrub_model_3_a", template = "block/parent_shrub_model_3_a"),
         @Model(name = "block/%s_shrub_model_3_b", template = "block/parent_shrub_model_3_b"),
         @Model(name = "block/%s_shrub_model_3_c", template = "block/parent_shrub_model_3_c"),
         @Model(name = "block/%s_berry_bush_model_3_a", template = "block/parent_berry_bush_model_3_a"),
         @Model(name = "block/%s_berry_bush_model_3_b", template = "block/parent_berry_bush_model_3_b"),
         @Model(name = "block/%s_berry_bush_model_3_c", template = "block/parent_berry_bush_model_3_c"),
         @Model(name = "block/%s_shrub_model_4_a", template = "block/parent_shrub_model_4_a"),
         @Model(name = "block/%s_shrub_model_4_b", template = "block/parent_shrub_model_4_b"),
         @Model(name = "block/%s_shrub_model_4_c", template = "block/parent_shrub_model_4_c"),
         @Model(name = "block/%s_berry_bush_model_4_a", template = "block/parent_berry_bush_model_4_a"),
         @Model(name = "block/%s_berry_bush_model_4_b", template = "block/parent_berry_bush_model_4_b"),
         @Model(name = "block/%s_berry_bush_model_4_c", template = "block/parent_berry_bush_model_4_c"),
         @Model(name = "block/%s_shrub_model_5_a", template = "block/parent_shrub_model_5_a"),
         @Model(name = "block/%s_shrub_model_5_b", template = "block/parent_shrub_model_5_b"),
         @Model(name = "block/%s_shrub_model_5_c", template = "block/parent_shrub_model_5_c"),
         @Model(name = "block/%s_berry_bush_model_5_a", template = "block/parent_berry_bush_model_5_a"),
         @Model(name = "block/%s_berry_bush_model_5_b", template = "block/parent_berry_bush_model_5_b"),
         @Model(name = "block/%s_berry_bush_model_5_c", template = "block/parent_berry_bush_model_5_c"),
         @Model(name = "block/%s_shrub_model_6_a", template = "block/parent_shrub_model_6_a"),
         @Model(name = "block/%s_shrub_model_6_b", template = "block/parent_shrub_model_6_b"),
         @Model(name = "block/%s_shrub_model_6_c", template = "block/parent_shrub_model_6_c"),
         @Model(name = "block/%s_berry_bush_model_6_a", template = "block/parent_berry_bush_model_6_a"),
         @Model(name = "block/%s_berry_bush_model_6_b", template = "block/parent_berry_bush_model_6_b"),
         @Model(name = "block/%s_berry_bush_model_6_c", template = "block/parent_berry_bush_model_6_c"),
         @Model(name = "block/%s_shrub_model_7_a", template = "block/parent_shrub_model_7_a"),
         @Model(name = "block/%s_shrub_model_7_b", template = "block/parent_shrub_model_7_b"),
         @Model(name = "block/%s_shrub_model_7_c", template = "block/parent_shrub_model_7_c"),
         @Model(name = "block/%s_berry_bush_model_7_a", template = "block/parent_berry_bush_model_7_a"),
         @Model(name = "block/%s_berry_bush_model_7_b", template = "block/parent_berry_bush_model_7_b"),
         @Model(name = "block/%s_berry_bush_model_7_c", template = "block/parent_berry_bush_model_7_c"),
         @Model(name = "block/%s_shrub_model_8_a", template = "block/parent_shrub_model_8_a"),
         @Model(name = "block/%s_shrub_model_8_b", template = "block/parent_shrub_model_8_b"),
         @Model(name = "block/%s_shrub_model_8_c", template = "block/parent_shrub_model_8_c"),
         @Model(name = "block/%s_berry_bush_model_8_a", template = "block/parent_berry_bush_model_8_a"),
         @Model(name = "block/%s_berry_bush_model_8_b", template = "block/parent_berry_bush_model_8_b"),
         @Model(name = "block/%s_berry_bush_model_8_c", template = "block/parent_berry_bush_model_8_c"),
         @Model(name = "block/%s_pane_ns", template = "block/parent_flatpane_ns")
   }
)
public class BerryBush extends AbstractCropsBlock {
   private final ItemLike fruit;

   public BerryBush(Props props) {
      super(props);
      this.fruit = props.get("fruit", ItemLike.class);
   }

   @Override
   public void entityInside(BlockState state, Level world, BlockPos pos, Entity entity) {
      if (entity instanceof LivingEntity livingEntity && state.is(ModTags.PLANT_SLOWNESS) && (Boolean)ConquestConfig.INSTANCE.plantSlowness.get()) {
         int age = (Integer)state.getValue(AGE);
         if (this.slowness > 0) {
            if (livingEntity instanceof Player) {
               livingEntity.addEffect(new MobEffectInstance(Effects.CUSTOM_SLOWNESS, 15, this.slowness, false, false));
            } else if (this.slowness > 1) {
               livingEntity.addEffect(new MobEffectInstance(Effects.CUSTOM_SLOWNESS, 15, this.slowness - 1, false, false));
            }
         }
      }
   }

   public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
      return BlockVoxelShapes.cubeMediumLargePartialShape.get(0);
   }

   public VoxelShape getOcclusionShape(BlockState state, BlockGetter world, BlockPos pos) {
      return BlockVoxelShapes.cubeMediumLargePartialShape.get(0);
   }

   public ItemStack getCloneItemStack(BlockGetter reader, BlockPos pos, BlockState state) {
      return new ItemStack(this, 1);
   }

   public InteractionResult use(BlockState state, Level level, BlockPos blockPos, Player player, InteractionHand hand, BlockHitResult hitResult) {
      if (this.isMaxAge(state)) {
         if (level.isClientSide) {
            return InteractionResult.SUCCESS;
         }

         state = (BlockState)state.setValue(AGE, 0);
         level.setBlock(blockPos, state, 3);
         this.dropFruit(level, blockPos, state);
         return InteractionResult.SUCCESS;
      } else {
         return InteractionResult.FAIL;
      }
   }

   private void dropFruit(Level world, BlockPos pos, BlockState state) {
      if (!world.isClientSide) {
         float f = 0.7F;
         double d0 = world.random.nextFloat() * 0.7F + 0.15F;
         double d1 = world.random.nextFloat() * 0.7F + 0.060000002F + 0.6;
         double d2 = world.random.nextFloat() * 0.7F + 0.15F;
         ItemStack itemstack1 = new ItemStack(this.fruit, 1);
         ItemEntity entityitem = new ItemEntity(world, pos.getX() + d0, pos.getY() + d1, pos.getZ() + d2, itemstack1);
         entityitem.setDefaultPickUpDelay();
         world.addFreshEntity(entityitem);
      }
   }

   public OffsetType getOffsetType() {
      return OffsetType.XZ;
   }
}
