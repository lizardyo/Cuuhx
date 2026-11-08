package com.conquestrefabricated.core.item.group.manager;

import com.conquestrefabricated.core.item.group.ConquestGroup;
import com.google.common.collect.Lists;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ItemGroupManager {
   private static final ItemGroupManager instance = new ItemGroupManager();
   public static final List<CreativeModeTab> fixed = Lists.newArrayList(
      new CreativeModeTab[]{
         CreativeModeTabs.searchTab(),
         (CreativeModeTab)BuiltInRegistries.CREATIVE_MODE_TAB.get(CreativeModeTabs.INVENTORY),
         (CreativeModeTab)BuiltInRegistries.CREATIVE_MODE_TAB.get(CreativeModeTabs.HOTBAR)
      }
   );
   private final Map<Class<?>, Set<CreativeModeTab>> conquestGroups = new HashMap<>();
   private final Map<GroupType, Set<CreativeModeTab>> groups = new EnumMap<>(GroupType.class);

   public static void init() {
      instance.storeVanillaGroups();
      instance.storeModGroups();
   }

   private ItemGroupManager() {
      for (GroupType type : GroupType.values()) {
         this.groups.put(type, new HashSet<>());
      }
   }

   public void register(CreativeModeTab group) {
      this.conquestGroups.computeIfAbsent(group.getClass(), k -> new HashSet<>()).add(group);
      this.groups.put(GroupType.CONQUEST, this.conquestGroups.get(group.getClass()));
   }

   public void setVisibleItemGroups(GroupType... types) {
   }

   private void storeVanillaGroups() {
      Set<CreativeModeTab> groups = this.groups.get(GroupType.VANILLA);

      for (CreativeModeTab group : CreativeModeTabs.allTabs()) {
         if (!fixed.contains(group) && !(group instanceof ConquestGroup) && !(group instanceof DelegateGroup)) {
            groups.add(group);
         }
      }
   }

   private void storeModGroups() {
      Set<CreativeModeTab> vanilla = this.groups.get(GroupType.VANILLA);
      Set<CreativeModeTab> other = this.groups.get(GroupType.OTHER);

      for (CreativeModeTab group : CreativeModeTabs.allTabs()) {
         if (!vanilla.contains(group) && !(group instanceof ConquestGroup) && !(group instanceof DelegateGroup)) {
            other.add(group);
         }
      }
   }

   public static ItemGroupManager getInstance() {
      return instance;
   }

   static {
      fixed.sort(Comparator.comparing(BuiltInRegistries.CREATIVE_MODE_TAB::getId));
   }
}
