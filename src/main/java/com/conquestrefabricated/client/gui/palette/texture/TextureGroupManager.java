package com.conquestrefabricated.client.gui.palette.texture;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;

@OnlyIn(Dist.CLIENT)
public class TextureGroupManager {
   private static final RandomSource random = RandomSource.create();
   private static final MeshDefinition nodata = new MeshDefinition();
   private static final TextureGroupManager INSTANCE = new TextureGroupManager();
   private final Map<Item, ItemGroup> blockGroups = new HashMap<>();
   private final Map<String, TextureGroup> textureGroups = new HashMap<>();

   public TextureGroupManager() {
   }

   public ItemGroup getGroup(Block block) {
      return this.getGroup(block.asItem());
   }

   public ItemGroup getGroup(BlockState state) {
      return this.getGroup(state.getBlock());
   }

   public ItemGroup getGroup(ItemStack stack) {
      return this.getGroup(stack.getItem());
   }

   public ItemGroup getGroup(Item item) {
      return this.blockGroups.getOrDefault(item, ItemGroup.NONE);
   }

   private void rebuild() {
      this.textureGroups.clear();
      this.blockGroups.clear();

      for (Item item : BuiltInRegistries.ITEM) {
         if (item instanceof BlockItem blockItem) {
            this.addState(item, blockItem.getBlock().defaultBlockState());
         }
      }
   }

   private void addState(Item item, BlockState state) {
      Map<String, Integer> textures = getTextures(state);
      int count = Integer.MAX_VALUE;
      TextureGroup main = null;
      List<TextureGroup> itemGroups = new LinkedList<>();

      for (Entry<String, Integer> entry : textures.entrySet()) {
         TextureGroup textureGroup = this.textureGroups.computeIfAbsent(entry.getKey(), TextureGroup::new);
         textureGroup.add(item);
         itemGroups.add(textureGroup);
         if (entry.getValue() < count) {
            main = textureGroup;
         }
      }

      if (main != null) {
         this.blockGroups.put(item, new ItemGroup(main, itemGroups));
      }
   }

   private static Map<String, Integer> getTextures(BlockState state) {
      BakedModel model = Minecraft.getInstance().getBlockRenderer().getBlockModelShaper().getBlockModel(state);
      return getTextures(state, model);
   }

   private static Map<String, Integer> getTextures(BlockState state, @Nullable BakedModel model) {
      if (model != null && model != Minecraft.getInstance().getModelManager().getMissingModel()) {
         List<BakedQuad> quads = model.getQuads(state, null, random);
         Map<String, Integer> textures = new HashMap<>();
         addTexture(model.getParticleIcon(), textures);

         for (BakedQuad quad : quads) {
            addTexture(quad.getSprite(), textures);
         }

         return textures;
      } else {
         return Collections.emptyMap();
      }
   }

   private static void addTexture(@Nullable TextureAtlasSprite sprite, Map<String, Integer> textures) {
      String texture = getTextureName(sprite);
      if (!texture.isEmpty()) {
         int count = textures.getOrDefault(texture, 0);
         textures.put(texture, count + 1);
      }
   }

   private static String getTextureName(@Nullable TextureAtlasSprite sprite) {
      if (sprite != null) {
         try {
            return getResourceName(sprite.atlasLocation());
         } catch (Throwable t) {
            return "";
         }
      } else {
         return "";
      }
   }

   private static String getResourceName(@Nullable ResourceLocation location) {
      return location != null ? location.toString() : "";
   }

   public static TextureGroupManager getInstance() {
      return INSTANCE;
   }
}
