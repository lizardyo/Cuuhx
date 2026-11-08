package com.conquestrefabricated.core.block.data;

import com.conquestrefabricated.core.block.factory.InitializationException;
import com.conquestrefabricated.core.util.cache.Disposable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Stream;

public class BlockDataRegistry implements Disposable, Iterable<BlockData> {
   private static final BlockDataRegistry instance = new BlockDataRegistry();
   private final ArrayList<BlockData> data = new ArrayList<>(500);
   private boolean disposed = false;

   private BlockDataRegistry() {
   }

   public Stream<String> getNamespaces() {
      return this.data.stream().map(data -> data.getRegistryName().getNamespace()).distinct();
   }

   public Stream<BlockData> getData(String namespace) {
      return this.data.stream().filter(data -> data.getRegistryName().getNamespace().equals(namespace));
   }

   public void register(BlockData block) {
      this.data.add(block);
   }

   @Override
   public void dispose() {
      this.data.clear();
      this.data.trimToSize();
      this.disposed = true;
   }

   public static BlockDataRegistry getInstance() {
      if (instance.disposed) {
         throw new InitializationException("Accessed BlockDataRegistry after it has been disposed!");
      } else {
         return instance;
      }
   }

   @Override
   public Iterator<BlockData> iterator() {
      return this.data.iterator();
   }
}
