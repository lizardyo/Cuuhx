package com.conquestrefabricated.client.bind;

import com.conquestrefabricated.api.painting.Painting;
import com.conquestrefabricated.api.painting.PaintingHolder;
import com.conquestrefabricated.api.painting.art.Art;
import com.conquestrefabricated.api.painting.vanilla.VanillaArt;
import com.conquestrefabricated.api.painting.vanilla.VanillaPainting;
import com.conquestrefabricated.client.gui.painting.PaintingScreen;
import com.conquestrefabricated.core.client.input.BindEvent;
import com.conquestrefabricated.core.client.input.BindListener;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.decoration.PaintingVariants;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class PaintingBindListener implements BindListener {
   public PaintingBindListener() {
   }

   @Override
   public void onPress(BindEvent e) {
      if (e.inGame && !e.inGui && e.player.isPresent()) {
         e.player.<ItemStack>map(LivingEntity::getMainHandItem).ifPresent(stack -> {
            if (stack.getItem() instanceof PaintingHolder) {
               PaintingHolder holder = (PaintingHolder)stack.getItem();
               Art<?> art = holder.getArt(stack);
               Painting type = holder.getType(stack);
               if (art != null && type != null) {
                  PaintingScreen<?> screen = new PaintingScreen<>(stack, type, art);
                  Minecraft.getInstance().setScreen(screen);
               }
            } else {
               if (stack.getItem() == Items.PAINTING) {
                  String name = PaintingVariants.ALBAN.registry() + "";
                  Art<?> art = VanillaArt.fromName(name);
                  Painting type = VanillaPainting.INSTANCE;
                  PaintingScreen<?> screen = new PaintingScreen<>(stack, type, art);
                  Minecraft.getInstance().setScreen(screen);
               }
            }
         });
      }
   }
}
