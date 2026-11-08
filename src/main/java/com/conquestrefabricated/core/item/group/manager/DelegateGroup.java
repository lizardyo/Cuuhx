package com.conquestrefabricated.core.item.group.manager;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

public class DelegateGroup extends CreativeModeTab {
   private final CreativeModeTab group;

   DelegateGroup(int index, CreativeModeTab group) {
      super(
         CreativeModeTab.builder(group.row(), group.column())
            .title(group.getDisplayName())
            .icon(group::getIconItem)
            .displayItems((displayContext, entries) -> {})
      );
      this.group = group;
   }

   @OnlyIn(Dist.CLIENT)
   public Component getDisplayName() {
      return this.group.getDisplayName();
   }

   @OnlyIn(Dist.CLIENT)
   public ItemStack getIconItem() {
      return this.group.getIconItem();
   }

   @OnlyIn(Dist.CLIENT)
   public String getBackgroundSuffix() {
      return this.group.getBackgroundTexture().getPath();
   }

   @OnlyIn(Dist.CLIENT)
   public boolean showTitle() {
      return this.group.showTitle();
   }

   @OnlyIn(Dist.CLIENT)
   public boolean canScroll() {
      return this.group.canScroll();
   }

   @OnlyIn(Dist.CLIENT)
   public int column() {
      return super.column();
   }

   @OnlyIn(Dist.CLIENT)
   public boolean isAlignedRight() {
      return super.isAlignedRight();
   }
}
