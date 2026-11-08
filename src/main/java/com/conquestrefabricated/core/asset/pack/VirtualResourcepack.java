package com.conquestrefabricated.core.asset.pack;

import com.conquestrefabricated.core.asset.VirtualResource;
import com.conquestrefabricated.core.asset.meta.VirtualMeta;
import com.google.gson.Gson;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.AbstractPackResources;
import net.minecraft.server.packs.PackResources;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.PackResources.ResourceOutput;
import net.minecraft.server.packs.resources.IoSupplier;
import net.minecraft.server.packs.resources.ResourceManager;
import org.jetbrains.annotations.Nullable;

public class VirtualResourcepack extends AbstractPackResources implements Supplier<PackResources> {
   private final ResourceManager resourceManager;
   private final Map<String, VirtualResource> resources;

   private VirtualResourcepack(PackType type, ResourceManager resourceManager, String name, Map<String, VirtualResource> resources) {
      super(name, true);
      this.resources = resources;
      this.resourceManager = resourceManager;
      PackFinder.getInstance(type).register(this);
   }

   public int size() {
      return this.resources.size();
   }

   public boolean isEmpty() {
      return this.resources.isEmpty();
   }

   public ResourceManager getResourceManager() {
      return this.resourceManager;
   }

   @Nullable
   public IoSupplier<InputStream> getRootResource(String... strings) {
      return null;
   }

   @Nullable
   public IoSupplier<InputStream> getResource(PackType resourceType, ResourceLocation identifier) {
      return null;
   }

   public void listResources(PackType resourceType, String string, String string2, ResourceOutput resultConsumer) {
   }

   public Set<String> getNamespaces(PackType type) {
      return this.resources.values().stream().filter(r -> r.getType() == type).map(VirtualResource::getNamespace).collect(Collectors.toSet());
   }

   public void close() {
   }

   public void forEach(BiConsumer<String, VirtualResource> visitor) {
      this.resources.forEach(visitor);
   }

   public List<Future<?>> export(Path dir, Gson gson) {
      List<Future<?>> tasks = new ArrayList<>(this.resources.size());

      for (Entry<String, VirtualResource> entry : this.resources.entrySet()) {
         Path path = dir.resolve(entry.getKey());
         VirtualResource resource = entry.getValue();
         tasks.add(ForkJoinPool.commonPool().submit(() -> {
            try {
               Files.createDirectories(path.getParent());

               try (BufferedWriter writer = Files.newBufferedWriter(path)) {
                  gson.toJson(resource.getJson(this.resourceManager), writer);
               }
            } catch (Throwable var9) {
            }
         }));
      }

      return tasks;
   }

   public static VirtualResourcepack.Builder builder(String name) {
      return new VirtualResourcepack.Builder(name);
   }

   public PackResources get() {
      return null;
   }

   public static class Builder {
      private final String namespace;
      private final List<VirtualResource> resources = new LinkedList<>();
      private PackType type = PackType.CLIENT_RESOURCES;

      private Builder(String namespace) {
         this.namespace = namespace;
      }

      public VirtualResourcepack.Builder type(PackType type) {
         this.type = type;
         return this;
      }

      public VirtualResourcepack.Builder add(VirtualResource resource) {
         this.resources.add(resource);
         return this;
      }

      public VirtualResourcepack build(ResourceManager resourceManager) {
         Map<String, VirtualResource> map = new HashMap<>();
         map.put("pack.mcmeta", new VirtualMeta(this.namespace, this.namespace));
         this.resources.forEach(r -> map.put(r.getPath(), r));
         String suffix = this.type == PackType.CLIENT_RESOURCES ? "_resources" : "_data";
         return new VirtualResourcepack(this.type, resourceManager, this.namespace + suffix, map);
      }
   }
}
