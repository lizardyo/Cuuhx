package com.conquestrefabricated.core.block.data;

import com.conquestrefabricated.core.util.cache.Cache;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ItemTypeCache extends Cache<Class<? extends Block>, Class<? extends Item>> {
   private static final ItemTypeCache instance = new ItemTypeCache();

   public ItemTypeCache() {
   }

   public static ItemTypeCache getInstance() {
      return instance;
   }

   public Class<? extends Item> compute(Class<? extends Block> aClass) {
      com.conquestrefabricated.core.asset.annotation.Item type = aClass.getAnnotation(com.conquestrefabricated.core.asset.annotation.Item.class);
      return type == null ? BlockItem.class : type.value();
   }
}
