package com.conquestrefabricated;

import com.conquestrefabricated.api.tags.ModTags;
import com.conquestrefabricated.content.blocks.block.BoardsHorizontal;
import com.conquestrefabricated.content.blocks.block.decor.Chairs;
import com.conquestrefabricated.content.blocks.block.trees.Underbranches;
import com.conquestrefabricated.content.blocks.block.trees.UnderbranchesThinToggle2;
import com.conquestrefabricated.content.blocks.block.trees.UnderbranchesThinToggle3;
import com.conquestrefabricated.content.blocks.block.trees.UnderbranchesThinToggle5;
import com.conquestrefabricated.content.blocks.block.trees.UnderbranchesToggle2;
import com.conquestrefabricated.content.blocks.block.trees.UnderbranchesToggle3;
import com.conquestrefabricated.content.blocks.block.trees.UnderbranchesToggle5;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent.RightClickBlock;

@EventBusSubscriber(modid = "conquest")
public class ModEvents {
   public ModEvents() {
   }

   @SubscribeEvent
   public static void onRightClickBlock(RightClickBlock event) {
      Level world = event.getLevel();
      BlockPos pos = event.getPos();
      BlockState state = world.getBlockState(pos);
      Player player = event.getEntity();
      if ((
            state.getBlock() instanceof BoardsHorizontal
               || state.getBlock() instanceof Chairs.Toggle2
               || state.getBlock() instanceof Chairs.Toggle3
               || state.getBlock() instanceof Chairs.Toggle4
               || state.getBlock() instanceof Chairs.Toggle5
               || state.getBlock() instanceof Chairs.Toggle6
               || state.getBlock() instanceof Underbranches
               || state.getBlock() instanceof UnderbranchesToggle2
               || state.getBlock() instanceof UnderbranchesToggle3
               || state.getBlock() instanceof UnderbranchesToggle5
               || state.getBlock() instanceof UnderbranchesThinToggle2
               || state.getBlock() instanceof UnderbranchesThinToggle3
               || state.getBlock() instanceof UnderbranchesThinToggle5
         )
         && player.isCrouching()
         && !player.getMainHandItem().is(ModTags.CYCLING_TOOLS)
         && !player.getMainHandItem().isEmpty()) {
         state.getBlock().useWithoutItem(state, world, pos, player, event.getHand(), event.getHitVec());
         event.setCanceled(true);
      }
   }
}
