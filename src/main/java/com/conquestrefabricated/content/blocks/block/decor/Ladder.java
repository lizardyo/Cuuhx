package com.conquestrefabricated.content.blocks.block.decor;

import com.conquestrefabricated.content.blocks.block.Trapdoor;
import com.conquestrefabricated.content.blocks.util.Interactions;
import com.conquestrefabricated.core.asset.annotation.Render;
import com.conquestrefabricated.core.util.RenderLayer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.block.LadderBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

@Render(RenderLayer.CUTOUT)
public class Ladder extends LadderBlock {
   public Ladder(Properties properties) {
      super(properties);
   }

   public boolean canSurvive(BlockState state, LevelReader reader, BlockPos pos) {
      return true;
   }

   public boolean isLadder(BlockState state, LevelReader reader, BlockPos pos, LivingEntity entity) {
      return true;
   }

   @Render(RenderLayer.CUTOUT)
   public static class LadderPane extends IronBarsBlock {
      public LadderPane(Properties properties) {
         super(properties);
      }

      public VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
         return Shapes.empty();
      }
   }

   @Render(RenderLayer.CUTOUT)
   public static class LadderTrapdoor extends Trapdoor {
      public LadderTrapdoor(Properties properties, BlockSetType type) {
         super(properties, type);
      }

      public InteractionResult use(BlockState state, Level level, BlockPos blockPos, Player player, InteractionHand hand, BlockHitResult blockHitResult) {
         return Interactions.onUseToggleItem(player, level, blockPos, state, OPEN);
      }
   }
}
