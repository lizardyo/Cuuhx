package com.conquestrefabricated.client.gui.search;

import com.conquestrefabricated.client.gui.search.query.Index;
import com.conquestrefabricated.client.gui.search.query.Result;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.TagKey;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class SearchScreen extends Screen implements Consumer<String> {
   private static final float scale = 2.0F;
   private static final LazyLoadedValue<Index<ItemStack>> index = new LazyLoadedValue(SearchScreen::buildIndex);
   private final EditBox search = new EditBox(Minecraft.getInstance().font, 0, 0, 200, 20, Component.translatable("Search"));
   private final Index<ItemStack> itemIndex;
   private final SearchList searchList;

   public SearchScreen() {
      super(Component.literal("search"));
      this.search.setResponder(this);
      this.itemIndex = (Index<ItemStack>)index.get();
      this.searchList = new SearchList(5, 6);
   }

   public boolean mouseClicked(double mx, double my, int button) {
      if (!super.mouseClicked(mx, my, button)) {
         Minecraft.getInstance().setScreen(null);
         return false;
      } else {
         return true;
      }
   }

   public void tick() {
      super.tick();
      this.check();
   }

   protected void init() {
      this.addRenderableWidget(this.search);
      int centerX = this.width / 2;
      this.search.setX(centerX - this.search.getWidth() / 2);
      this.search.setY(100);
      this.addWidget(this.searchList);
   }

   public void render(GuiGraphics drawContext, int mx, int my, float ticks) {
      this.update();
      super.renderBackground(drawContext);
      super.render(drawContext, mx, my, ticks);
      this.searchList.render(drawContext, mx, my, this.width, this.height, ticks);
   }

   public void accept(String term) {
      this.searchList.clear();
      this.itemIndex.parallelSearch(term, this.searchList.maxSize()).sequential().map(Result::getResult).forEach(this.searchList::add);
      if (this.searchList.size() == 0) {
         this.search.setY(this.height / 2 - this.search.getHeight() / 2);
      } else {
         this.search.setY(20);
      }
   }

   private void update() {
      int pad = 8;
      int centerX = this.width / 2;
      int centerY = this.height / 2;
      int guiHeight = this.search.getHeight() + pad + this.searchList.getHeight();
      this.setFocused(this.search);
      this.search.setFocused(true);
      this.search.setX(centerX - this.search.getWidth() / 2);
      this.search.setY(Math.max(10, centerY - guiHeight / 2));
      this.searchList.x = centerX - this.searchList.getWidth() / 2.0F;
      this.searchList.y = this.search.getY() + this.search.getHeight() + pad;
      this.searchList.scale = 2.0F;
   }

   private void check() {
      LocalPlayer player = Minecraft.getInstance().player;
      if (player == null || !player.getAbilities().instabuild) {
         Minecraft.getInstance().setScreen(null);
      }
   }

   private static Index<ItemStack> buildIndex() {
      Index.Builder<ItemStack> builder = Index.builder();

      for (Item item : BuiltInRegistries.ITEM.stream().toList()) {
         if (item != Items.AIR) {
            ItemStack stack = new ItemStack(item);
            builder.with(stack, stack.getDisplayName().getString(), getTags(stack));
         }
      }

      return builder.build();
   }

   private static Collection<String> getTags(ItemStack item) {
      List<String> tags = new LinkedList<>();
      tags.add(BuiltInRegistries.ITEM.getKey(item.getItem()).getPath());

      for (TagKey<Item> tag : (List)item.getTags().collect(Collectors.toList())) {
         tags.add(tag.location().getPath());
      }

      return tags;
   }
}
