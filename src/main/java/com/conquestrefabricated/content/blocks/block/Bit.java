package com.conquestrefabricated.content.blocks.block;

import com.conquestrefabricated.content.blocks.util.Interactions;
import com.conquestrefabricated.core.asset.annotation.Assets;
import com.conquestrefabricated.core.asset.annotation.Model;
import com.conquestrefabricated.core.asset.annotation.State;
import com.conquestrefabricated.core.block.base.WaterloggedShape;
import com.conquestrefabricated.core.block.builder.Props;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.VoxelShape;

@Assets(
   state = @State(name = "%s_bit", template = "parent_bit"),
   item = @Model(name = "item/%s_bit", parent = "block/%s_bit", template = "item/dragon_egg"),
   block = {@Model(name = "block/%s_bit_1", template = "block/parent_bit_1"), @Model(name = "block/%s_bit_2", template = "block/parent_bit_2")}
)
public class Bit extends WaterloggedShape {
   public static final IntegerProperty TOGGLE = IntegerProperty.create("toggle", 1, 2);
   public static final EnumProperty<Half> TYPE_UPDOWN = EnumProperty.create("type", Half.class);
   private static final VoxelShape TOP_SHAPE = Block.box(4.0, 8.0, 4.0, 12.0, 16.0, 12.0);
   private static final VoxelShape BOTTOM_SHAPE = Block.box(4.0, 0.0, 4.0, 12.0, 8.0, 12.0);

   public Bit(Props properties) {
      super(properties.toSettings());
      this.registerDefaultState((BlockState)((BlockState)this.stateDefinition.any()).setValue(WATERLOGGED, false));
   }

   @Override
   protected void addProperties(Builder<Block, BlockState> builder) {
      builder.add(new Property[]{TOGGLE, TYPE_UPDOWN});
   }

   @Override
   public BlockState getStateForPlacement(BlockPlaceContext context) {
      Direction facing = context.getHorizontalDirection().getOpposite();
      BlockPos pos = context.getClickedPos();
      Half topBottom = Half.TOP;
      if (facing != Direction.DOWN && (facing == Direction.UP || !(context.getClickLocation().y - pos.getY() > 0.5))) {
         topBottom = Half.BOTTOM;
      }

      return (BlockState)((BlockState)super.getStateForPlacement(context).setValue(TOGGLE, 1)).setValue(TYPE_UPDOWN, topBottom);
   }

   @Override
   public VoxelShape getShape(BlockState state) {
      return state.getValue(TYPE_UPDOWN) == Half.TOP ? TOP_SHAPE : BOTTOM_SHAPE;
   }

   public InteractionResult use(BlockState state, Level level, BlockPos blockPos, Player player, InteractionHand hand, BlockHitResult hitResult) {
      return Interactions.onUseToggleItem(player, level, blockPos, state, TOGGLE);
   }
}
