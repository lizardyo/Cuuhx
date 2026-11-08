package com.conquestrefabricated.client.bind;

import com.conquestrefabricated.client.gui.search.SearchScreen;
import com.conquestrefabricated.core.client.input.BindEvent;
import com.conquestrefabricated.core.client.input.BindListener;
import net.minecraft.client.Minecraft;

public class SearchBindListener implements BindListener {
   public SearchBindListener() {
   }

   @Override
   public void onPress(BindEvent e) {
      if (e.inGame && !e.inGui && e.player.map(p -> p.getAbilities().instabuild).orElse(false)) {
         Minecraft.getInstance().setScreen(new SearchScreen());
      }
   }
}
