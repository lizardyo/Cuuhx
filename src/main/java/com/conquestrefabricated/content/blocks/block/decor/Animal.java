package com.conquestrefabricated.content.blocks.block.decor;

import com.conquestrefabricated.content.blocks.tileentity.AnimalTileEntity;
import com.conquestrefabricated.content.blocks.tileentity.TileEntityTypes;
import com.conquestrefabricated.content.blocks.util.Interactions;
import java.util.Random;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;

public class Animal extends BaseEntityBlock {
   public static final IntegerProperty TOGGLE = IntegerProperty.create("toggle", 1, 4);

   public Animal(Properties properties) {
      super(properties);
   }

   public BlockState getStateForPlacement(BlockPlaceContext context) {
      Random rand = new Random();
      int toggleState = rand.nextInt(4) + 1;
      return (BlockState)this.defaultBlockState().setValue(TOGGLE, toggleState);
   }

   public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
      return true;
   }

   public RenderShape getRenderShape(BlockState state) {
      return RenderShape.INVISIBLE;
   }

   @OnlyIn(Dist.CLIENT)
   public float getShadeBrightness(BlockState state, BlockGetter blockReader, BlockPos pos) {
      return 1.0F;
   }

   protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
      builder.add(new Property[]{TOGGLE});
   }

   public BlockEntity newBlockEntity(BlockPos blockPos, BlockState state) {
      return new AnimalTileEntity(blockPos, state);
   }

   @Nullable
   public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState p_153213_, BlockEntityType<T> blockEntityType) {
      return level.isClientSide
         ? createTickerHelper(blockEntityType, TileEntityTypes.ANIMAL, AnimalTileEntity::particleTick)
         : createTickerHelper(blockEntityType, TileEntityTypes.ANIMAL, AnimalTileEntity::particleTickServer);
   }

   public static class Bird extends Animal {
      public static final BooleanProperty FLYING = BooleanProperty.create("flying");

      public Bird(Properties settings) {
         super(settings);
      }

      public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
         return Interactions.onUseToggleItem(player, world, pos, state, FLYING);
      }

      @Override
      protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
         builder.add(new Property[]{TOGGLE, FLYING});
      }
   }
}
