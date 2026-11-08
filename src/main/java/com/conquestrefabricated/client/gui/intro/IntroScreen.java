package com.conquestrefabricated.client.gui.intro;

import com.conquestrefabricated.client.ModBinds;
import com.conquestrefabricated.client.gui.config.ConquestConfig;
import com.conquestrefabricated.client.tutorial.Tutorials;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Checkbox;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.components.Button.Builder;
import net.minecraft.client.gui.components.Button.OnPress;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class IntroScreen extends Screen {
   private static final ResourceLocation LOGO = ResourceLocation.fromNamespaceAndPath("conquest:textures/gui/intro/logosmall.png");
   private static final int LOGO_FRAME_SIZE = 145;
   private static final int LOGO_FRAME_COUNT = 111;
   private static final int LOGO_FRAME_TICKS = 2;
   private static final int LOGO_DRAW_SIZE = 64;
   private static final int LOGO_MARGIN = 12;
   private static final ResourceLocation PATREON_LOGO = ResourceLocation.fromNamespaceAndPath("conquest:textures/gui/intro/patreon.png");
   private static final int PATREON_SIZE = 52;
   private static final int PATREON_FRAME_SIZE = 145;
   private static final int PATREON_FRAME_COUNT = 14;
   private static final int PATREON_FRAME_TICKS = 4;
   private static final float HOVER_BRIGHTNESS = 1.25F;
   private static final String PATREON_URL = "https://www.patreon.com/c/ConquestReforged?vanity=user";
   private final Screen screen;
   private final Checkbox check = new Checkbox(0, 0, 20, 20, Component.translatableWithFallback("conquest.intro.checkbox", "Do not show again"), false);
   private IntroScreen.KeybindWidget paletteKeybindWidget;
   private IntroScreen.KeybindWidget pickBlockKeybindWidget;
   private IntroScreen.TextLinkWidget patreonHintWidget;

   public IntroScreen(Screen parent) {
      super(Component.literal("Intro"));
      this.screen = parent;
   }

   public boolean shouldCloseOnEsc() {
      return true;
   }

   public void onClose() {
      ConquestConfig.INSTANCE.ignore_intro.set(this.check.selected());
      ConquestConfig.INSTANCE.save();
      Minecraft.getInstance().setScreen(this.screen);
   }

   protected void init() {
      Tutorials.intro = true;
      super.init();
      int center = this.width / 2;
      int bottomRowShift = 32;
      int bottomRowCenter = center - bottomRowShift;
      this.addRenderableWidget(
         new Builder(Component.translatableWithFallback("conquest.intro.close", "Continue"), b -> this.onClose())
            .bounds(bottomRowCenter - 50, this.height - 24, 100, 20)
            .build()
      );
      this.check.setY(this.height - 24);
      this.check.setX(bottomRowCenter + 50 + 8);
      this.addRenderableWidget(this.check);
      IntroScreen.PatreonButton patreonButton = new IntroScreen.PatreonButton(
         this.width - 52 - 3, this.height - 52 - 3, 52, 52, b -> Util.getPlatform().openUri("https://www.patreon.com/c/ConquestReforged?vanity=user")
      );
      patreonButton.setTooltip(
         Tooltip.create(
            Component.translatableWithFallback("conquest.intro.patreon.tooltip", "Your support helps us keep updating to new versions and adding more blocks and features!")
         )
      );
      this.addRenderableWidget(patreonButton);
      Component paletteKeyLabel = Component.literal(ModBinds.getPaletteBind().getTranslatedKeyMessage().getString().toUpperCase())
         .withStyle(ChatFormatting.GOLD)
         .append(Component.translatableWithFallback("conquest.intro.2.short", " - shows texture shape variants in the block palette").withStyle(ChatFormatting.WHITE));
      Component paletteTooltip = Component.literal("(Creative Mode only) ")
         .withStyle(ChatFormatting.GREEN)
         .append(
            Component.translatableWithFallback("conquest.intro.3", "Works while hovering over a block in the creative menu or when selected in the hotbar.")
               .withStyle(ChatFormatting.GRAY)
         );
      this.paletteKeybindWidget = new IntroScreen.KeybindWidget(0, 0, 0, 20, paletteKeyLabel, b -> {});
      this.paletteKeybindWidget.setTooltip(Tooltip.create(paletteTooltip));
      this.addRenderableWidget(this.paletteKeybindWidget);
      Component pickBlockKeyLabel = Component.translatableWithFallback("conquest.intro.pickblock", "CTRL+MIDDLE-MOUSE-BUTTON")
         .withStyle(ChatFormatting.GOLD)
         .append(Component.translatableWithFallback("conquest.intro.4.short", " - press while looking at a block").withStyle(ChatFormatting.WHITE));
      Component pickBlockTooltip = Component.literal("(Creative Mode only) ")
         .withStyle(ChatFormatting.GREEN)
         .append(
            Component.translatableWithFallback("conquest.intro.5", "Gives the exact shape you're looking at as a block item in your hotbar.").withStyle(ChatFormatting.GRAY)
         );
      this.pickBlockKeybindWidget = new IntroScreen.KeybindWidget(0, 0, 0, 20, pickBlockKeyLabel, b -> {});
      this.pickBlockKeybindWidget.setTooltip(Tooltip.create(pickBlockTooltip));
      this.addRenderableWidget(this.pickBlockKeybindWidget);
      Component patreonHint = Component.translatableWithFallback("conquest.intro.patreon", "Enjoying the mod? Support continued development on our Patreon!")
         .withStyle(ChatFormatting.LIGHT_PURPLE);
      this.patreonHintWidget = new IntroScreen.TextLinkWidget(
         0, 0, 0, 10, patreonHint, b -> Util.getPlatform().openUri("https://www.patreon.com/c/ConquestReforged?vanity=user")
      );
      this.addRenderableWidget(this.patreonHintWidget);
   }

   public void render(GuiGraphics drawContext, int mx, int my, float ticks) {
      this.renderDirtBackground(drawContext);
      Component welcomeString = Component.translatableWithFallback("conquest.intro.welcome", "Welcome to Conquest Reforged!").withStyle(ChatFormatting.GOLD);
      Component intro = Component.translatableWithFallback("conquest.intro.1", "This screen will introduce you to keybinds for making building faster.");
      int dist = 12;
      int logoFrame = (int)(System.currentTimeMillis() / 100L % 111L);
      int logoV = logoFrame * 145;
      RenderSystem.setShaderTexture(0, LOGO);
      RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
      drawContext.blit(LOGO, (this.width - 64) / 2, 12, 64, 64, 0.0F, logoV, 145, 145, 145, 16095);
      int y = 76 + dist;
      y = this.drawCentered(drawContext, welcomeString, y, dist);
      y = this.drawCentered(drawContext, intro, y, dist);
      y += dist;
      y = this.positionButtonWidget(this.paletteKeybindWidget, y);
      y += 6;
      y = this.positionButtonWidget(this.pickBlockKeybindWidget, y);
      y += dist;
      this.positionTextWidget(this.patreonHintWidget, y, 10);
      super.render(drawContext, mx, my, ticks);
   }

   private int drawCentered(GuiGraphics drawContext, Component text, int y, int dist) {
      int titleWidth = this.font.width(text);
      drawContext.drawString(this.font, text.getVisualOrderText(), (this.width - titleWidth) / 2, y, 16777215);
      return y + dist;
   }

   private int positionButtonWidget(Button widget, int y) {
      int padding = 16;
      int textWidth = this.font.width(widget.getMessage());
      int widgetWidth = textWidth + padding;
      widget.setWidth(widgetWidth);
      widget.setX((this.width - widgetWidth) / 2);
      widget.setY(y);
      return y + widget.getHeight();
   }

   private void positionTextWidget(Button widget, int y, int height) {
      int textWidth = this.font.width(widget.getMessage());
      widget.setWidth(textWidth);
      widget.setX((this.width - textWidth) / 2);
      widget.setY(y);
   }

   private static class KeybindWidget extends Button {
      protected KeybindWidget(int x, int y, int width, int height, Component message, OnPress onPress) {
         super(x, y, width, height, message, onPress, DEFAULT_NARRATION);
      }

      public void updateWidgetNarration(NarrationElementOutput builder) {
         this.defaultButtonNarrationText(builder);
      }
   }

   private static class PatreonButton extends Button {
      protected PatreonButton(int x, int y, int width, int height, OnPress onPress) {
         super(x, y, width, height, Component.empty(), onPress, DEFAULT_NARRATION);
      }

      protected void renderWidget(GuiGraphics context, int mouseX, int mouseY, float delta) {
         int frame = (int)(System.currentTimeMillis() / 200L % 14L);
         int v = frame * 145;
         RenderSystem.setShaderTexture(0, IntroScreen.PATREON_LOGO);
         float brightness = this.isHovered() ? 1.25F : 1.0F;
         RenderSystem.setShaderColor(brightness, brightness, brightness, this.alpha);
         context.blit(IntroScreen.PATREON_LOGO, this.getX(), this.getY(), this.width, this.height, 0.0F, v, 145, 145, 145, 2030);
         RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
      }

      public void updateWidgetNarration(NarrationElementOutput builder) {
         this.defaultButtonNarrationText(builder);
      }
   }

   private static class TextLinkWidget extends Button {
      protected TextLinkWidget(int x, int y, int width, int height, Component message, OnPress onPress) {
         super(x, y, width, height, message, onPress, DEFAULT_NARRATION);
      }

      protected void renderWidget(GuiGraphics context, int mouseX, int mouseY, float delta) {
         Minecraft client = Minecraft.getInstance();
         context.drawString(client.font, this.getMessage().getVisualOrderText(), this.getX(), this.getY(), 16777215);
         if (this.isHovered()) {
            context.fill(
               this.getX(), this.getY() + this.getHeight(), this.getX() + this.getWidth(), this.getY() + this.getHeight() + 1, -2130706433
            );
         }
      }

      public void updateWidgetNarration(NarrationElementOutput builder) {
         this.defaultButtonNarrationText(builder);
      }
   }
}
