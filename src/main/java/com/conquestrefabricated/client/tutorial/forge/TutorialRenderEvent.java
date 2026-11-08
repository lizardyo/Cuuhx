package com.conquestrefabricated.client.tutorial.forge;

import com.conquestrefabricated.client.gui.config.ConquestConfig;
import com.conquestrefabricated.client.gui.dependency.Dependency;
import com.conquestrefabricated.client.gui.dependency.DependencyList;
import com.conquestrefabricated.client.gui.dependency.screen.DependencyScreen;
import com.conquestrefabricated.client.gui.intro.IntroScreen;
import com.conquestrefabricated.client.tutorial.Tutorials;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.TitleScreen;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.EventBusSubscriber.Bus;
import net.neoforged.neoforge.client.event.ScreenEvent.Init.Post;

@EventBusSubscriber(value = Dist.CLIENT, bus = Bus.GAME)
public class TutorialRenderEvent {
   private static final DependencyList dependencies = DependencyList.load();

   public TutorialRenderEvent() {
   }

   @SubscribeEvent
   public static void render(Post event) {
      if (event.getScreen() instanceof TitleScreen) {
         List<Dependency> missing = dependencies.getMissingDependencies();
         IntroScreen introScreen = new IntroScreen(event.getScreen());
         DependencyScreen dependencyScreen = new DependencyScreen(event.getScreen(), missing);
         DependencyScreen dependencyScreen2 = new DependencyScreen(introScreen, missing);
         if ((Boolean)ConquestConfig.INSTANCE.ignore_dependencies.get() && (Boolean)ConquestConfig.INSTANCE.ignore_intro.get()
            || missing.isEmpty() && Tutorials.intro
            || Tutorials.dependencies && Tutorials.intro) {
            return;
         }

         if (!Tutorials.intro) {
            if ((Boolean)ConquestConfig.INSTANCE.ignore_intro.get()) {
               if (!missing.isEmpty()) {
                  Minecraft.getInstance().setScreen(dependencyScreen);
               }
            } else if (!(Boolean)ConquestConfig.INSTANCE.ignore_dependencies.get() && !missing.isEmpty()) {
               Minecraft.getInstance().setScreen(dependencyScreen2);
            } else {
               Minecraft.getInstance().setScreen(introScreen);
            }
         }
      }
   }
}
