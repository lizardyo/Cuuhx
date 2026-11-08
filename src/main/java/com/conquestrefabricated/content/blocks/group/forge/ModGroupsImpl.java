package com.conquestrefabricated.content.blocks.group.forge;

import com.conquestrefabricated.content.blocks.group.ModGroups;
import com.conquestrefabricated.core.group.forge.FamilyGroup;
import java.util.function.Supplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTab.Type;

public class ModGroupsImpl {
   public ModGroupsImpl() {
   }

   public static CreativeModeTab createFamilyGroup(int order, String label, Supplier<ItemStack> icon) {
      ResourceKey<CreativeModeTab> groupKey = ResourceKey.create(Registries.CREATIVE_MODE_TAB, ResourceLocation.fromNamespaceAndPath("conquest", label));
      ModGroups.toggleRow(order);
      return new FamilyGroup(order, label, icon, ModGroups.rowValue, order % 5 + 1, Type.CATEGORY, Component.nullToEmpty(label), (displayContext, entries) -> {});
   }
}
