package com.conquestrefabricated.core.block.data;

import com.conquestrefabricated.core.asset.annotation.Assets;
import com.conquestrefabricated.core.asset.annotation.Ingredient;
import com.conquestrefabricated.core.asset.annotation.Model;
import com.conquestrefabricated.core.asset.annotation.Recipe;
import com.conquestrefabricated.core.asset.annotation.Render;
import com.conquestrefabricated.core.asset.annotation.State;
import com.conquestrefabricated.core.asset.override.EmptyOverride;
import com.conquestrefabricated.core.asset.override.MapOverride;
import com.conquestrefabricated.core.asset.override.SingleOverride;
import com.conquestrefabricated.core.asset.pack.Locations;
import com.conquestrefabricated.core.asset.pack.VirtualResourcepack;
import com.conquestrefabricated.core.asset.template.JsonOverride;
import com.conquestrefabricated.core.asset.template.JsonTemplate;
import com.conquestrefabricated.core.asset.template.TemplateCache;
import com.conquestrefabricated.core.asset.template.TemplateResource;
import com.conquestrefabricated.core.block.builder.BlockName;
import com.conquestrefabricated.core.block.builder.Props;
import com.conquestrefabricated.core.block.builder.Textures;
import com.conquestrefabricated.core.client.render.RenderLayerHelper;
import com.conquestrefabricated.core.util.RenderLayer;
import com.conquestrefabricated.core.util.log.Log;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

public class BlockTemplate {
   private final State state;
   private final Model itemModel;
   private final Model[] blockModels;
   private final Recipe[] recipes;
   private final Render render;
   private final boolean plural;

   BlockTemplate(Class<?> type) {
      Assets assets = type.getAnnotation(Assets.class);
      this.state = assets != null ? assets.state() : null;
      this.itemModel = assets != null ? assets.item() : null;
      this.blockModels = assets != null ? assets.block() : null;
      this.recipes = assets != null ? assets.recipe() : null;
      this.render = getRender(type, assets);
      this.plural = this.state != null && this.state.plural();
   }

   public RenderLayer getRenderLayer() {
      return this.render == null ? RenderLayer.UNDEFINED : this.render.value();
   }

   public ResourceLocation getRegistryName(BlockName name) {
      return this.state == null
         ? ResourceLocation.fromNamespaceAndPath(name.getNamespace(), name.format("%s", this.plural))
         : ResourceLocation.fromNamespaceAndPath(name.getNamespace(), name.format(this.state.name(), this.plural));
   }

   public void addClientResources(VirtualResourcepack.Builder builder, BlockName name, Textures textures, ResourceLocation regName) {
      this.addState(builder, name, regName);
      this.addItem(builder, name, regName);
      this.addModel(builder, name, textures, regName);
   }

   public void registerRenders(Block block, Props props) {
      if (props.getRenderLayer() != RenderLayer.UNDEFINED) {
         RenderLayerHelper.register(block, props.getRenderLayer());
      } else {
         if (this.render != null) {
            RenderLayer layer = this.render.value();
            RenderLayerHelper.register(block, layer);
         }
      }
   }

   public void addServerResources(VirtualResourcepack.Builder builder, BlockName name, ResourceLocation regName) {
      this.addRecipe(builder, name, regName);
   }

   private void addState(VirtualResourcepack.Builder builder, BlockName name, ResourceLocation regName) {
      if (this.state == null) {
         Log.debug("No state template for {}", regName);
      } else {
         Log.debug("Generating state for {}", regName);
         String templateName = this.state.template();
         String virtualName = name.namespaceFormat(this.state.name(), this.state.plural());
         String templatePath = Locations.statePath(ResourceLocation.parse(templateName));
         String virtualPath = Locations.statePath(ResourceLocation.parse(virtualName));
         JsonTemplate template = TemplateCache.getInstance().get(templatePath);
         JsonOverride overrides = this.getOverrides(name, this.blockModels);
         builder.add(TemplateResource.asset(name.getNamespace(), virtualPath, overrides, template));
      }
   }

   private void addModel(VirtualResourcepack.Builder builder, BlockName name, Textures textures, ResourceLocation regName) {
      if (this.blockModels == null) {
         Log.debug("No model template for {}", regName);
      } else {
         Log.debug("Generating model(s) for {}", regName);

         for (Model model : this.blockModels) {
            String templateName = model.template();
            String virtualName = name.namespaceFormat(model.name(), model.plural());
            String templatePath = Locations.modelPath(new ModelResourceLocation(regName, templateName));
            String virtualPath = Locations.modelPath(new ModelResourceLocation(regName, virtualName));
            JsonTemplate template = TemplateCache.getInstance().get(templatePath);
            builder.add(TemplateResource.asset(name.getNamespace(), virtualPath, textures, template));
         }
      }
   }

   private void addItem(VirtualResourcepack.Builder builder, BlockName name, ResourceLocation regName) {
      if (this.itemModel == null) {
         Log.debug("No item model template for {}", regName);
      } else {
         Log.debug("Generating item model for {}", regName);
         String templateName = this.itemModel.template();
         String itemModelName = name.namespaceFormat(this.itemModel.name(), this.plural);
         String parentModelName = name.namespaceFormat(this.itemModel.parent(), this.plural);
         String templatePath = Locations.modelPath(new ModelResourceLocation(regName, templateName));
         String virtualPath = Locations.modelPath(new ModelResourceLocation(regName, itemModelName));
         JsonOverride overrides = new SingleOverride("parent", new JsonPrimitive(parentModelName));
         JsonTemplate template = TemplateCache.getInstance().get(templatePath);
         builder.add(TemplateResource.asset(name.getNamespace(), virtualPath, overrides, template));
      }
   }

   private void addRecipe(VirtualResourcepack.Builder builder, BlockName name, ResourceLocation regName) {
      if (this.recipes != null && this.recipes.length == 1) {
         Recipe recipe = this.recipes[0];
         String templateName = recipe.template();
         String recipeName = name.namespaceFormat(recipe.name(), this.plural);
         String output = recipe.output().isEmpty() ? recipe.template() : recipe.output();
         String item = ResourceLocation.parse(output).toString();
         Ingredient result = createIngredient(recipe.name(), item, this.plural);
         Ingredient[] ingredients = this.push(recipe.ingredients(), result);
         String templatePath = Locations.recipePath(ResourceLocation.parse(templateName));
         String virtualPath = Locations.recipePath(ResourceLocation.parse(recipeName));
         JsonOverride overrides = this.getOverrides(name, ingredients);
         if (overrides == EmptyOverride.EMPTY) {
            Log.error("Unable to generate recipe for {} (invalid ingredients)", regName);
         } else {
            Log.debug("Generating recipe for {}", regName);
            JsonTemplate template = TemplateCache.getInstance().get(templatePath);
            builder.add(TemplateResource.data(name.getNamespace(), virtualPath, overrides, template));
         }
      } else {
         Log.debug("No recipe template for {}", regName);
      }
   }

   private JsonOverride getOverrides(BlockName name, Model[] replacements) {
      if (replacements.length == 0) {
         return EmptyOverride.EMPTY;
      }

      if (replacements.length == 1) {
         String find = replacements[0].template();
         String replace = name.namespaceFormat(replacements[0].name(), this.plural);
         return new SingleOverride("model", new JsonPrimitive(find), new JsonPrimitive(replace));
      }

      Map<JsonElement, JsonElement> overrides = new HashMap<>(replacements.length);

      for (Model model : replacements) {
         String find = model.template();
         String replace = name.namespaceFormat(model.name(), model.plural());
         overrides.put(new JsonPrimitive(find), new JsonPrimitive(replace));
      }

      return new MapOverride("model", overrides);
   }

   private JsonOverride getOverrides(BlockName name, Ingredient[] ingredients) {
      if (ingredients.length == 0) {
         return EmptyOverride.EMPTY;
      }

      if (ingredients.length == 1) {
         String find = ingredients[0].template();
         String replace = this.getIngredient(name, ingredients[0]);
         return replace.isEmpty() ? EmptyOverride.EMPTY : new SingleOverride("item", new JsonPrimitive(find), new JsonPrimitive(replace));
      }

      Map<JsonElement, JsonElement> overrides = new HashMap<>(ingredients.length);

      for (Ingredient ingredient : ingredients) {
         String find = ResourceLocation.parse(ingredient.template()).toString();
         String replace = this.getIngredient(name, ingredient);
         if (replace.isEmpty()) {
            return EmptyOverride.EMPTY;
         }

         overrides.put(new JsonPrimitive(find), new JsonPrimitive(replace));
      }

      return new MapOverride("item", overrides);
   }

   private String getIngredient(BlockName name, Ingredient ingredient) {
      String itemName = name.format(ingredient.name(), ingredient.plural());
      if (BuiltInRegistries.ITEM.containsKey(ResourceLocation.fromNamespaceAndPath(name.getNamespace(), itemName))) {
         Log.debug(" Found ingredient {}:{}", name.getNamespace(), itemName);
         return name.getNamespace() + ":" + itemName;
      } else if (BuiltInRegistries.ITEM.containsKey(ResourceLocation.parse(itemName))) {
         Log.debug(" Found vanilla ingredient minecraft:{}", itemName);
         return "minecraft:" + itemName;
      } else {
         Log.error(" Unknown ingredient {}", itemName);
         return "";
      }
   }

   private <T> T[] push(T[] t, T value) {
      T[] array = (T[])Arrays.copyOf(t, t.length + 1);
      array[array.length - 1] = value;
      return array;
   }

   private static Render getRender(Class<?> type, @Nullable Assets assets) {
      while (type != Object.class) {
         Render render = type.getAnnotation(Render.class);
         if (render != null) {
            return render;
         }

         if (assets != null && assets.render().value() != RenderLayer.UNDEFINED) {
            return assets.render();
         }

         type = type.getSuperclass();
         assets = type.getAnnotation(Assets.class);
      }

      return null;
   }

   private static Ingredient createIngredient(String name, String template, boolean plrual) {
      return new Ingredient() {
         @Override
         public Class<? extends Annotation> annotationType() {
            return Ingredient.class;
         }

         @Override
         public String name() {
            return name;
         }

         @Override
         public String template() {
            return template;
         }

         @Override
         public boolean plural() {
            return plrual;
         }
      };
   }
}
