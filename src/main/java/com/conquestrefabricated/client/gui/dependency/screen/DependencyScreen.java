package com.conquestrefabricated.client.gui.dependency.screen;

import com.conquestrefabricated.client.gui.config.ConquestConfig;
import com.conquestrefabricated.client.gui.dependency.Dependency;
import com.conquestrefabricated.client.gui.dependency.DependencyType;
import com.conquestrefabricated.client.tutorial.Tutorials;
import com.conquestrefabricated.client.utils.TextUtils;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Checkbox;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.components.Button.Builder;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.resources.ResourceLocation;

public class DependencyScreen extends Screen {
   private static final ResourceLocation CTM = ResourceLocation.parse("conquest:textures/gui/dependency/ctm.png");
   private static final int CTM_HEIGHT = 256;
   private static final int CTM_WIDTH = 432;
   private static final int LIST_HEIGHT = 64;
   private static final int TITLE_HEIGHT = 22;
   private static final int MARGIN_TOP = 10;
   private static final int MARGIN_BOTTOM = 28;
   private final Screen screen;
   private final List<Dependency> missing;
   private final Checkbox check = Checkbox.builder(
         Component.translatableWithFallback("conquest.dependency.checkbox", "Do not show again"), Minecraft.getInstance().font
      )
      .pos(0, 0)
      .selected(false)
      .build();

   public DependencyScreen(Screen parent, List<Dependency> missing) {
      super(Component.nullToEmpty("Dependencies"));
      this.screen = parent;
      this.missing = missing;
   }

   public boolean shouldCloseOnEsc() {
      return true;
   }

   public void onClose() {
      ConquestConfig.INSTANCE.ignore_dependencies.set(this.check.selected());
      ConquestConfig.INSTANCE.save();
      Minecraft.getInstance().setScreen(this.screen);
   }

   protected void init() {
      Tutorials.dependencies = true;
      super.init();
      int center = this.width / 2;
      int imageHeight = this.getImageHeight();
      int imageWidth = this.getImageWidth(imageHeight);
      int paddingTop = this.getPaddingTop(imageHeight);
      int listTop = paddingTop + imageHeight;
      int listBottom = listTop + 22 + 64;
      this.addRenderableWidget(
         new Builder(Component.translatable("conquest.dependency.close", new Object[]{"Continue"}), b -> this.onClose())
            .bounds(center - 50, this.height - 24, 100, 20)
            .build()
      );
      this.check.setY(this.height - 24);
      this.check.setX(center + 50 + 8);
      this.addRenderableWidget(this.check);
      if (!(Boolean)ConquestConfig.INSTANCE.using_modpack.get()) {
         int topDist = 7;
         Component[] recommendationOriginal = new Component[]{
            Component.translatableWithFallback("conquest.dependency.1", "It appears you're not using the Conquest Reforged Modpack!").withStyle(ChatFormatting.GOLD),
            Component.literal(""),
            Component.translatableWithFallback("conquest.dependency.2", "Our modpack adds all of the required dependencies,"),
            Component.translatableWithFallback("conquest.dependency.3", "along with optimization mods and proper configs for the best experience."),
            Component.translatableWithFallback("conquest.dependency.4", "Getting all of the right versions of every mod is hard, this takes care of that for you."),
            Component.translatableWithFallback("conquest.dependency.5", "If you're making your own modpack, you can use ours as a base."),
            Component.translatableWithFallback("conquest.dependency.6", "Otherwise, this screen will show you which of the most essential dependencies are missing.")
         };
         int maxWidth = (int)(this.width * 0.95);
         List<Component> wrappedLines = new ArrayList<>();

         for (Component line : recommendationOriginal) {
            if (this.font.width(line) <= maxWidth) {
               wrappedLines.add(line);
            } else {
               TextUtils.wrapComplexTextToList(line, this.font, maxWidth, wrappedLines);
            }
         }

         int dist = 12;

         for (int i = 0; i < wrappedLines.size(); i++) {
            topDist += dist;
         }

         topDist += 12;
         this.addRenderableWidget(
            new Builder(Component.translatableWithFallback("conquest.dependency.modpack", "Modpack"), btn -> {
                  try {
                     Util.getPlatform().openUrl(new URL("https://modrinth.com/modpack/conquest-reforged-modpack"));
                  } catch (MalformedURLException e) {
                     e.printStackTrace();
                  }
               })
               .bounds(center - 85, topDist, 170, 20)
               .tooltip(
                  Tooltip.create(
                     Component.translatableWithFallback("conquest.dependency.tooltip.modpack", "Page for the Modpack is here, install via ATLauncher or Modrinth launcher!")
                  )
               )
               .build()
         );
      }

      int bottomDist = this.height - 50;
      int buttonsPerRow = 3;
      int buttonWidth = 120;
      if (this.missing.size() > buttonsPerRow) {
         bottomDist -= 24;
      }

      for (int i = 0; i < this.missing.size(); i++) {
         int row = i / buttonsPerRow;
         int col = i % buttonsPerRow;
         int buttonsInThisRow = Math.min(buttonsPerRow, this.missing.size() - row * buttonsPerRow);
         int rowWidth = buttonsInThisRow * buttonWidth;
         int rowLeftEdge = center - rowWidth / 2;
         int xPosition = rowLeftEdge + col * buttonWidth + buttonWidth / 2;
         int yPosition = bottomDist + row * 24;
         this.addRenderableWidget(createButton(this.missing.get(i), yPosition, xPosition));
      }
   }

   public void render(GuiGraphics drawContext, int mx, int my, float ticks) {
      this.renderBackground(drawContext, mx, my, ticks);
      int center = this.width / 2;
      int imageHeight = this.getImageHeight();
      int imageWidth = this.getImageWidth(imageHeight);
      int imageLeft = this.getImageLeft(imageWidth);
      int paddingTop = this.getPaddingTop(imageHeight);
      if ((Boolean)ConquestConfig.INSTANCE.using_modpack.get()) {
         Component[] recommendationOriginal = new Component[]{
            Component.translatableWithFallback("conquest.dependency.1", "It appears you're not using the Conquest Reforged Modpack!").withStyle(ChatFormatting.GOLD),
            Component.literal(""),
            Component.translatableWithFallback("conquest.dependency.2", "Our modpack adds all of the required dependencies,"),
            Component.translatableWithFallback("conquest.dependency.3", "along with optimization mods and proper configs for the best experience."),
            Component.translatableWithFallback("conquest.dependency.4", "Getting all of the right versions of every mod is hard, this takes care of that for you."),
            Component.translatableWithFallback("conquest.dependency.5", "If you're making your own modpack, you can use ours as a base."),
            Component.translatableWithFallback("conquest.dependency.6", "Otherwise, this screen will show you which of the most essential dependencies are missing.")
         };
         int maxWidth = (int)(this.width * 0.95);
         List<Component> wrappedLines = new ArrayList<>();

         for (Component line : recommendationOriginal) {
            if (this.font.width(line) <= maxWidth) {
               wrappedLines.add(line);
            } else {
               TextUtils.wrapComplexTextToList(line, this.font, maxWidth, wrappedLines);
            }
         }

         int topDist = 7;
         int dist = 12;

         for (int i = 0; i < wrappedLines.size(); i++) {
            int paragraphWidth = this.font.width((FormattedText)wrappedLines.get(i));
            int paragraphOffset = paragraphWidth / 2;
            topDist += dist;
            drawContext.drawString(this.font, wrappedLines.get(i).getVisualOrderText(), (int)(this.width / 2.0F - paragraphOffset), topDist, 16777215);
         }
      }

      int bottomDist = this.height - 50;
      if (this.missing.size() > 3) {
         bottomDist -= 24;
      }

      Component message = Component.translatableWithFallback("conquest.dependency.missing", "Missing Dependencies:").withStyle(ChatFormatting.GOLD);
      int titleWidth = this.font.width(message);
      int titleOffset = titleWidth / 2;
      drawContext.drawString(this.font, message, (int)(this.width / 2.0F - titleOffset), bottomDist - 14, 16777215);
      super.render(drawContext, mx, my, ticks);
   }

   private int getImageHeight() {
      return Math.min(256, this.height - 10 - 22 - 64 - 28);
   }

   private int getImageWidth(int imageHeight) {
      return Math.round(432.0F * (imageHeight / 256.0F));
   }

   private int getImageLeft(int imageWidth) {
      return this.width / 2 - imageWidth / 2;
   }

   private int getPaddingTop(int imageHeight) {
      int elementsHeight = imageHeight + 22 + 64;
      int paddingTop = (this.height - elementsHeight) / 2;
      int dif = this.height - (paddingTop + elementsHeight);
      if (dif < 28) {
         paddingTop -= dif;
         paddingTop = Math.max(paddingTop, 2);
      }

      return paddingTop;
   }

   private static Button createButton(Dependency dependency, int heightIn, int center) {
      return new Builder(Component.translatable(dependency.getDisplayName()), btn -> {
            try {
               if (dependency.getType() == DependencyType.RESOURCEPACK) {
                  return;
               }

               Util.getPlatform().openUrl(new URL(dependency.getURL()));
            } catch (MalformedURLException e) {
               e.printStackTrace();
            }
         })
         .bounds(center - 60, heightIn, 120, 20)
         .tooltip(Tooltip.create(Component.translatable("conquest.dependency.tooltip." + dependency.getId())))
         .build();
   }
}
