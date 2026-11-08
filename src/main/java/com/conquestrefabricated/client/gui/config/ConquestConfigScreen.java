package com.conquestrefabricated.client.gui.config;

import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.components.Button.Builder;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;

public class ConquestConfigScreen extends Screen {
   private final Screen parent;
   private final ConquestConfig config;
   private List<ConquestConfigScreen.Value<?>> values;

   public ConquestConfigScreen(Screen parent, ConquestConfig config) {
      super(Component.translatable(getTranslationKey("title")));
      this.parent = parent;
      this.config = config;
   }

   protected void init() {
      ConquestConfigScreen.Value<Boolean> plantBreaking = ConquestConfigScreen.Value.of(
         this.config.plantBreaking, ConquestConfigScreen.Value.Flag.RELOAD_WORLD_RENDERER
      );
      ConquestConfigScreen.Value<Boolean> plantSlowness = ConquestConfigScreen.Value.of(
         this.config.plantSlowness, ConquestConfigScreen.Value.Flag.RELOAD_WORLD_RENDERER
      );
      ConquestConfigScreen.Value<Boolean> passThroughLeaves = ConquestConfigScreen.Value.of(
         this.config.passThroughLeaves, ConquestConfigScreen.Value.Flag.RELOAD_WORLD_RENDERER
      );
      this.values = List.of(plantBreaking, plantSlowness, passThroughLeaves);
      boolean serverControlled = Minecraft.getInstance().level != null && !Minecraft.getInstance().isLocalServer();
      Button plantBreakingBtn = this.startBooleanValueButton(plantBreaking, serverControlled)
         .bounds(this.width / 2 - 100 - 110, this.height / 2 - 10 - 12, 200, 20)
         .build();
      plantBreakingBtn.active = !serverControlled;
      this.addRenderableWidget(plantBreakingBtn);
      Button plantSlownessBtn = this.startBooleanValueButton(plantSlowness, serverControlled)
         .bounds(this.width / 2 - 100 + 110, this.height / 2 - 10 - 12, 200, 20)
         .build();
      plantSlownessBtn.active = !serverControlled;
      this.addRenderableWidget(plantSlownessBtn);
      Button passThroughLeavesBtn = this.startBooleanValueButton(passThroughLeaves, serverControlled)
         .bounds(this.width / 2 - 100 - 110, this.height / 2 - 10 + 12, 200, 20)
         .build();
      passThroughLeavesBtn.active = !serverControlled;
      this.addRenderableWidget(passThroughLeavesBtn);
      this.addRenderableWidget(Button.builder(CommonComponents.GUI_DONE, button -> {
         this.saveValues();
         this.onClose();
      }).bounds(this.width / 2 - 75 - 79, this.height - 40, 150, 20).build());
      this.addRenderableWidget(
         Button.builder(CommonComponents.GUI_CANCEL, button -> this.onClose()).bounds(this.width / 2 - 75 + 79, this.height - 40, 150, 20).build()
      );
   }

   public void render(GuiGraphics context, int mouseX, int mouseY, float delta) {
      this.renderBackground(context, mouseX, mouseY, delta);
      context.drawCenteredString(this.font, this.title, this.width / 2, 30, 16777215);
      super.render(context, mouseX, mouseY, delta);
   }

   public void onClose() {
      this.minecraft.setScreen(this.parent);
   }

   private void saveValues() {
      EnumSet<ConquestConfigScreen.Value.Flag> flags = EnumSet.noneOf(ConquestConfigScreen.Value.Flag.class);

      for (ConquestConfigScreen.Value<?> value : this.values) {
         if (value.isChanged()) {
            value.saveToOption();
            flags.addAll(value.getFlags());
         }
      }

      this.config.save();

      for (ConquestConfigScreen.Value.Flag flag : flags) {
         flag.onSave();
      }
   }

   private static String getTranslationKey(String optionKey) {
      return "options.conquest." + optionKey;
   }

   private static String getTooltipKey(String translationKey) {
      return translationKey + ".tooltip";
   }

   private Builder startBooleanValueButton(ConquestConfigScreen.Value<Boolean> value, boolean serverControlled) {
      String translationKey = getTranslationKey(value.getOption().getKey());
      Component text = Component.translatable(translationKey);
      Component tooltipText = serverControlled
         ? Component.literal("Server-controlled setting").withStyle(ChatFormatting.GRAY)
         : Component.translatable(getTooltipKey(translationKey));
      return Button.builder(CommonComponents.optionNameValue(text, CommonComponents.optionStatus(value.get())), button -> {
         boolean newValue = !value.get();
         value.set(newValue);
         Component valueText = CommonComponents.optionStatus(newValue);
         if (value.isChanged()) {
            valueText = valueText.copy().withStyle(style -> style.withBold(true));
         }

         button.setMessage(CommonComponents.optionNameValue(text, valueText));
      }).tooltip(Tooltip.create(tooltipText));
   }

   private static class Value<T> {
      private final Option<T> option;
      private final Set<ConquestConfigScreen.Value.Flag> flags;
      private final T originalValue;
      private T value;

      public Value(Option<T> option, Set<ConquestConfigScreen.Value.Flag> flags) {
         this.option = option;
         this.flags = flags;
         this.originalValue = this.option.get();
         this.value = this.originalValue;
      }

      public static <T> ConquestConfigScreen.Value<T> of(Option<T> option, ConquestConfigScreen.Value.Flag... flags) {
         EnumSet<ConquestConfigScreen.Value.Flag> flagSet = EnumSet.noneOf(ConquestConfigScreen.Value.Flag.class);
         Collections.addAll(flagSet, flags);
         return new ConquestConfigScreen.Value<>(option, flagSet);
      }

      public Option<T> getOption() {
         return this.option;
      }

      public Set<ConquestConfigScreen.Value.Flag> getFlags() {
         return this.flags;
      }

      public T get() {
         return this.value;
      }

      public void set(T value) {
         this.value = value;
      }

      public boolean isChanged() {
         return !this.value.equals(this.originalValue);
      }

      public void saveToOption() {
         this.option.set(this.value);
      }

      public enum Flag {
         RELOAD_WORLD_RENDERER {
            @Override
            public void onSave() {
               Minecraft.getInstance().levelRenderer.allChanged();
            }
         };

         Flag() {
         }

         public abstract void onSave();
      }
   }
}
