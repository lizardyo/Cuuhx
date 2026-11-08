package com.conquestrefabricated.client.events;

import com.conquestrefabricated.core.item.ItemUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.HitResult.Type;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.EventBusSubscriber.Bus;
import net.neoforged.neoforge.client.event.InputEvent.MouseButton;

@EventBusSubscriber(value = Dist.CLIENT, bus = Bus.GAME)
public class BlockPicker {
   public BlockPicker() {
   }

   @SubscribeEvent
   public static void onPick(MouseButton event) {
      if (event.getButton() == 2) {
         if (event.getAction() == 1) {
            if (Screen.hasControlDown()) {
               Player player = Minecraft.getInstance().player;
               if (player != null && player.getAbilities().instabuild) {
                  HitResult result = Minecraft.getInstance().hitResult;
                  if (result != null) {
                     if (result.getType() == Type.BLOCK) {
                        BlockPos pos = ((BlockHitResult)result).getBlockPos();
                        BlockState state = player.level().getBlockState(pos);
                        if (!state.hasBlockEntity()) {
                           ItemStack stack;
                           if (Screen.hasAltDown()) {
                              stack = ItemUtils.fromState(state);
                           } else {
                              stack = ItemUtils.fromStateNoFacing(state);
                           }

                           player.getInventory().setPickedItem(stack);
                           Minecraft.getInstance().gameMode.handleCreativeModeItemAdd(player.getItemInHand(InteractionHand.MAIN_HAND), 36 + player.getInventory().selected);
                           event.setCanceled(true);
                        }
                     }
                  }
               }
            }
         }
      }
   }
}
