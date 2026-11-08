package com.conquestrefabricated.content.blocks.block.decor;

import com.conquestrefabricated.api.tags.ModTags;
import com.conquestrefabricated.content.blocks.CustomOffsetType;
import com.conquestrefabricated.content.blocks.block.directional.HorizontalDirectional;
import com.conquestrefabricated.content.blocks.tileentity.TileEntityTypes;
import com.conquestrefabricated.content.blocks.tileentity.loom.LoomBlockEntity;
import com.conquestrefabricated.content.blocks.util.PlacementHelper;
import com.conquestrefabricated.core.asset.annotation.ItemDescription;
import com.conquestrefabricated.core.block.builder.Props;
import com.conquestrefabricated.core.block.properties.ModBlockProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.OffsetType;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@ItemDescription(description = "loom_toggle_4")
public class Loom extends HorizontalDirectional implements EntityBlock {
   public static final IntegerProperty SIZE = IntegerProperty.create("size", 1, 3);
   public static final IntegerProperty POSITION = IntegerProperty.create("position", 1, 4);
   public static final BooleanProperty HAS_THREAD = BooleanProperty.create("has_thread");
   public static final BooleanProperty OFFSET_TOGGLE = ModBlockProperties.OFFSET_TOGGLE;

   public Loom(Props props) {
      super(props.customOffsetType(CustomOffsetType.LAYER_XYZ).offset(OffsetType.NONE).dynamicBounds(true));
      this.registerDefaultState((BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue(HAS_THREAD, false)).setValue(OFFSET_TOGGLE, false));
   }

   @Override
   public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
      return state.getValue(OFFSET_TOGGLE) ? Shapes.empty() : super.getCollisionShape(state, worldIn, pos, context);
   }

   @Override
   protected void addProperties(Builder<Block, BlockState> builder) {
      builder.add(new Property[]{SIZE, POSITION, HAS_THREAD, OFFSET_TOGGLE});
   }

   @NotNull
   @Override
   public BlockState getStateForPlacement(BlockPlaceContext context) {
      boolean isSlab = PlacementHelper.isFacingSlab(context);
      return (BlockState)((BlockState)((BlockState)((BlockState)super.getStateForPlacement(context).setValue(SIZE, 1)).setValue(POSITION, 1)).setValue(HAS_THREAD, false))
         .setValue(OFFSET_TOGGLE, isSlab);
   }

   public void setPlacedBy(Level world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
      super.setPlacedBy(world, pos, state, placer, itemStack);
      if (itemStack.hasTag() && world.getBlockEntity(pos) instanceof LoomBlockEntity loom) {
         CompoundTag itemNbt = itemStack.getTag();
         if (itemNbt.contains("BlockEntityTag")) {
            CompoundTag beNbt = itemNbt.getCompound("BlockEntityTag");
            loom.load(beNbt);
            loom.setChanged();
         }
      }
   }

   public InteractionResult use(BlockState state, Level level, BlockPos blockPos, Player player, InteractionHand hand, BlockHitResult hitResult) {
      if (!player.getAbilities().mayBuild) {
         return InteractionResult.FAIL;
      }

      if (player.getItemInHand(hand).getItem().equals(Items.DEBUG_STICK)) {
         return InteractionResult.PASS;
      }

      if (player.getMainHandItem().is(ModTags.CYCLING_TOOLS)) {
         level.setBlock(blockPos, (BlockState)state.cycle(SIZE), 3);
         return InteractionResult.SUCCESS;
      }

      LoomBlockEntity blockEntity = (LoomBlockEntity)level.getBlockEntity(blockPos);
      if (blockEntity != null) {
         String itemId = player.getItemInHand(hand).getItem().toString();
         if (itemId.contains("red_canvas")) {
            return this.setProduct(level, blockPos, player, state, hand, blockEntity, "conquest:red_canvas");
         }

         if (itemId.contains("black_canvas")) {
            return this.setProduct(level, blockPos, player, state, hand, blockEntity, "conquest:black_canvas");
         }

         if (itemId.contains("light_gray_canvas")) {
            return this.setProduct(level, blockPos, player, state, hand, blockEntity, "conquest:light_gray_canvas");
         }

         if (itemId.contains("gray_canvas")) {
            return this.setProduct(level, blockPos, player, state, hand, blockEntity, "conquest:gray_canvas");
         }

         if (itemId.contains("white_canvas")) {
            return this.setProduct(level, blockPos, player, state, hand, blockEntity, "conquest:white_canvas");
         }

         if (itemId.contains("brown_canvas")) {
            return this.setProduct(level, blockPos, player, state, hand, blockEntity, "conquest:brown_canvas");
         }

         if (itemId.contains("yellow_canvas")) {
            return this.setProduct(level, blockPos, player, state, hand, blockEntity, "conquest:yellow_canvas");
         }

         if (itemId.contains("orange_canvas")) {
            return this.setProduct(level, blockPos, player, state, hand, blockEntity, "conquest:orange_canvas");
         }

         if (itemId.contains("pink_canvas")) {
            return this.setProduct(level, blockPos, player, state, hand, blockEntity, "conquest:pink_canvas");
         }

         if (itemId.contains("magenta_canvas")) {
            return this.setProduct(level, blockPos, player, state, hand, blockEntity, "conquest:magenta_canvas");
         }

         if (itemId.contains("purple_canvas")) {
            return this.setProduct(level, blockPos, player, state, hand, blockEntity, "conquest:purple_canvas");
         }

         if (itemId.contains("light_blue_canvas")) {
            return this.setProduct(level, blockPos, player, state, hand, blockEntity, "conquest:light_blue_canvas");
         }

         if (itemId.contains("blue_canvas")) {
            return this.setProduct(level, blockPos, player, state, hand, blockEntity, "conquest:blue_canvas");
         }

         if (itemId.contains("cyan_canvas")) {
            return this.setProduct(level, blockPos, player, state, hand, blockEntity, "conquest:cyan_canvas");
         }

         if (itemId.contains("green_canvas")) {
            return this.setProduct(level, blockPos, player, state, hand, blockEntity, "conquest:green_canvas");
         }

         if (itemId.contains("lime_canvas")) {
            return this.setProduct(level, blockPos, player, state, hand, blockEntity, "conquest:lime_canvas");
         }

         if (itemId.contains("baotuo_rug")) {
            return this.setProduct(level, blockPos, player, state, hand, blockEntity, "conquest:baotuo_rug");
         }

         if (itemId.contains("berber_rug")) {
            return this.setProduct(level, blockPos, player, state, hand, blockEntity, "conquest:berber_rug");
         }

         if (itemId.contains("black_persian_rug")) {
            return this.setProduct(level, blockPos, player, state, hand, blockEntity, "conquest:black_persian_rug");
         }

         if (itemId.contains("blue_nain_rug")) {
            return this.setProduct(level, blockPos, player, state, hand, blockEntity, "conquest:blue_nain_rug");
         }

         if (itemId.contains("brown_oriental_carpet")) {
            return this.setProduct(level, blockPos, player, state, hand, blockEntity, "conquest:brown_oriental_carpet");
         }

         if (itemId.contains("celtic_knot_rug")) {
            return this.setProduct(level, blockPos, player, state, hand, blockEntity, "conquest:celtic_knot_rug");
         }

         if (itemId.contains("kashmiri_carpet")) {
            return this.setProduct(level, blockPos, player, state, hand, blockEntity, "conquest:kashmiri_carpet");
         }

         if (itemId.contains("kazakh_rug")) {
            return this.setProduct(level, blockPos, player, state, hand, blockEntity, "conquest:kazakh_rug");
         }

         if (itemId.contains("kilim_rug")) {
            return this.setProduct(level, blockPos, player, state, hand, blockEntity, "conquest:kilim_rug");
         }

         if (itemId.contains("nahavand_rug")) {
            return this.setProduct(level, blockPos, player, state, hand, blockEntity, "conquest:nahavand_rug");
         }

         if (itemId.contains("red_and_blue_sarouk_rug")) {
            return this.setProduct(level, blockPos, player, state, hand, blockEntity, "conquest:red_and_blue_sarouk_rug");
         }

         if (itemId.contains("red_oriental_carpet")) {
            return this.setProduct(level, blockPos, player, state, hand, blockEntity, "conquest:red_oriental_carpet");
         }

         if (itemId.contains("red_pazyryk_rug")) {
            return this.setProduct(level, blockPos, player, state, hand, blockEntity, "conquest:red_pazyryk_rug");
         }

         if (itemId.contains("shirishabad_rug")) {
            return this.setProduct(level, blockPos, player, state, hand, blockEntity, "conquest:shirishabad_rug");
         }

         if (itemId.contains("william_morris_rug")) {
            return this.setProduct(level, blockPos, player, state, hand, blockEntity, "conquest:william_morris_rug");
         }

         if (itemId.contains("yellow_red_persian_rug")) {
            return this.setProduct(level, blockPos, player, state, hand, blockEntity, "conquest:yellow_red_persian_rug");
         }

         if (player.isShiftKeyDown()) {
            if (!player.getAbilities().instabuild) {
               return this.dropProduct(level, blockPos, state, blockEntity);
            }

            level.setBlock(blockPos, (BlockState)state.setValue(HAS_THREAD, false), 3);
            blockEntity.setProduct("");
            blockEntity.setChanged();
            level.sendBlockUpdated(blockPos, state, state, 3);
            return InteractionResult.SUCCESS;
         } else {
            level.setBlock(blockPos, (BlockState)state.cycle(POSITION), 3);
            return InteractionResult.SUCCESS;
         }
      } else {
         return InteractionResult.FAIL;
      }
   }

   @Nullable
   public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
      return new LoomBlockEntity(pos, state);
   }

   @Nullable
   public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level world, BlockState state, BlockEntityType<T> type) {
      return type == TileEntityTypes.LOOM ? (world1, pos, state1, blockEntity) -> ((LoomBlockEntity)blockEntity).tick(world1, pos) : null;
   }

   private InteractionResult setProduct(
      Level level, BlockPos blockPos, Player player, BlockState state, InteractionHand hand, LoomBlockEntity blockEntity, String product
   ) {
      if (!player.getAbilities().instabuild) {
         this.dropProduct(level, blockPos, state, blockEntity);
         player.getItemInHand(hand).shrink(1);
      }

      level.setBlock(blockPos, (BlockState)state.setValue(HAS_THREAD, true), 3);
      blockEntity.setProduct(product);
      blockEntity.setChanged();
      level.sendBlockUpdated(blockPos, state, state, 3);
      return InteractionResult.SUCCESS;
   }

   private InteractionResult dropProduct(Level level, BlockPos blockPos, BlockState state, LoomBlockEntity blockEntity) {
      String product = blockEntity.getProduct();
      if (product != null && !product.isEmpty()) {
         ItemStack itemToSpawn = new ItemStack((ItemLike)BuiltInRegistries.ITEM.get(ResourceLocation.parse(product)), 1);
         Vec3 spawnPos = Vec3.atCenterOf(blockPos).add(0.0, 1.3, 0.0);
         ItemEntity itemEntity = new ItemEntity(level, spawnPos.x, spawnPos.y, spawnPos.z, itemToSpawn);
         itemEntity.setPickUpDelay(10);
         level.addFreshEntity(itemEntity);
         level.playSound(null, blockPos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1.0F, 1.0F);
         level.setBlock(blockPos, (BlockState)state.setValue(HAS_THREAD, false), 3);
         blockEntity.setProduct("");
         blockEntity.setChanged();
         level.sendBlockUpdated(blockPos, state, state, 3);
      }

      return InteractionResult.SUCCESS;
   }
}
