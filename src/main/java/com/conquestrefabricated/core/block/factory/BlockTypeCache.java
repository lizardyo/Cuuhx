package com.conquestrefabricated.core.block.factory;

import com.conquestrefabricated.core.block.builder.Props;
import com.conquestrefabricated.core.block.factory.constructor.BaseConstructor;
import com.conquestrefabricated.core.block.factory.constructor.BlockConstructor;
import com.conquestrefabricated.core.block.factory.constructor.DoorConstructor;
import com.conquestrefabricated.core.block.factory.constructor.DyeConstructor;
import com.conquestrefabricated.core.block.factory.constructor.PropsConstructor;
import com.conquestrefabricated.core.block.factory.constructor.StateConstructor;
import com.conquestrefabricated.core.block.factory.constructor.WoodTypeConstructor;
import com.conquestrefabricated.core.util.cache.Cache;
import java.lang.reflect.Constructor;
import java.util.LinkedList;
import java.util.List;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

public class BlockTypeCache extends Cache<Class<? extends Block>, BlockType> {
   private static final BlockTypeCache instance = new BlockTypeCache();
   private final List<BlockTypeCache.Entry> entries = new LinkedList<>();

   private BlockTypeCache() {
      this.register(PropsConstructor::new, Props.class);
      this.register(BaseConstructor::new, Properties.class);
      this.register(DyeConstructor::new, DyeColor.class, Properties.class);
      this.register(BlockConstructor::new, Block.class, Properties.class);
      this.register(StateConstructor::new, BlockState.class, Properties.class);
      this.register(DoorConstructor::new, Properties.class, BlockSetType.class);
      this.register(WoodTypeConstructor::new, Properties.class, WoodType.class);
   }

   public BlockType compute(Class<? extends Block> type) {
      for (BlockTypeCache.Entry factory : this.entries) {
         try {
            Constructor<? extends Block> constructor = type.getConstructor(factory.argTypes);
            constructor.setAccessible(true);
            return factory.factory.create(constructor);
         } catch (NoSuchMethodException var5) {
         }
      }

      throw new InitializationException("constructor not found for type " + type);
   }

   public void register(BlockType.Factory factory, Class<?>... argTypes) {
      this.entries.add(new BlockTypeCache.Entry(factory, argTypes));
   }

   public static BlockTypeCache getInstance() {
      return instance;
   }

   private static class Entry {
      private final Class<?>[] argTypes;
      private final BlockType.Factory factory;

      private Entry(BlockType.Factory factory, Class<?>[] argTypes) {
         this.argTypes = argTypes;
         this.factory = factory;
      }
   }
}
