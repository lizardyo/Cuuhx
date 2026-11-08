package com.conquestrefabricated.core.block.builder;

import com.conquestrefabricated.core.block.builder.forge.PropsImpl;
import com.conquestrefabricated.core.block.data.BlockData;
import com.conquestrefabricated.core.block.data.BlockTemplate;
import com.conquestrefabricated.core.block.data.ColorType;
import com.conquestrefabricated.core.block.factory.BlockFactory;
import com.conquestrefabricated.core.block.factory.InitializationException;
import com.conquestrefabricated.core.block.factory.TypeList;
import com.conquestrefabricated.core.item.family.DeferredFamilyRegistry;
import com.conquestrefabricated.core.item.family.Family;
import com.conquestrefabricated.core.item.family.FamilyFactory;
import com.conquestrefabricated.core.item.family.block.BlockFamily;
import com.conquestrefabricated.core.item.family.block.VariantFamily;
import com.conquestrefabricated.core.util.RenderLayer;
import com.google.common.base.Preconditions;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

public class Props extends BlockProps<Props> implements BlockFactory {
   private BlockState parent = null;
   private BlockName name = null;
   private ColorType colorType = ColorType.NONE;
   private RenderLayer renderLayer = RenderLayer.UNDEFINED;
   private Textures.Builder textures;
   private Map<String, Object> extradata = Collections.emptyMap();
   private FamilyFactory<Block> familyFactory = FamilyFactory.of(BlockFamily::new);
   private ResourceLocation family = null;
   private BlockSetType blockSetType = BlockSetType.OAK;
   private WoodType woodType = WoodType.OAK;
   private boolean manual = false;

   private Props(Block block) {
      super(block);
   }

   private Props(Props props) {
      super(props);
      this.name = props.name;
      this.manual = props.manual;
      this.parent = props.parent;
      this.textures = props.textures;
      this.colorType = props.colorType;
      this.extradata = props.extradata;
      this.renderLayer = props.renderLayer;
      this.familyFactory = props.familyFactory;
      this.family = props.family;
   }

   @Override
   public void registerItem(BlockData data) {
      registerItemByPlatform(data);
   }

   public static void registerItemByPlatform(BlockData data) {
      PropsImpl.registerItemByPlatform(data);
   }

   @Override
   public Props getProps() {
      return this;
   }

   @Override
   protected <T> void applyNonNull(Integer light, Consumer<T> lightLevel) {
   }

   @Override
   public BlockName getName() {
      if (this.name == null) {
         throw new InitializationException("Block name is null");
      } else {
         return this.name;
      }
   }

   @Override
   public BlockState getParent() throws InitializationException {
      if (this.parent == null) {
         throw new InitializationException("Parent state is null");
      } else {
         return this.parent;
      }
   }

   @Override
   public Family<Block> createFamily(TypeList types) {
      ResourceLocation name = this.family == null ? (this.parent == null ? null : BuiltInRegistries.BLOCK.getKey(this.parent.getBlock())) : this.family;
      return this.familyFactory.create(name, this.group(), types);
   }

   public Optional<ResourceLocation> getFamily() {
      return Optional.ofNullable(this.family);
   }

   public ColorType getColorType() {
      return this.colorType;
   }

   public RenderLayer getRenderLayer() {
      return this.renderLayer;
   }

   public BlockSetType getBlockSetType() {
      return this.blockSetType;
   }

   public WoodType getWoodType() {
      return this.woodType;
   }

   public Textures textures() {
      return this.textures != null && !this.textures.isEmpty() ? this.textures.build() : Textures.NONE;
   }

   public boolean isManual() {
      return this.manual;
   }

   public boolean hasParent() {
      return this.parent != null;
   }

   public <T> T get(String key, Class<T> type) {
      Object o = this.extradata.get(key);
      if (o == null) {
         throw new InitializationException(new NullPointerException(key + ": value is null"));
      } else if (!type.isInstance(o)) {
         throw new InitializationException(new ClassCastException(key + ": expected " + type + " but found " + o.getClass()));
      } else {
         return type.cast(o);
      }
   }

   public <T> T getOrDefault(String key, Class<T> type, T defaultValue) {
      Object o = this.extradata.get(key);
      if (o == null) {
         return defaultValue;
      } else if (!type.isInstance(o)) {
         throw new InitializationException(new ClassCastException(key + ": expected " + type + " but found " + o.getClass()));
      } else {
         return type.cast(o);
      }
   }

   public Props manual() {
      this.manual = true;
      return this;
   }

   public Props parent(BlockState state) {
      this.parent = state;
      return this;
   }

   public Props family(String namespace, String name) {
      if (this.parent == null) {
         this.parent = ((Block)BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(namespace, name))).defaultBlockState();
      }

      this.family = ResourceLocation.fromNamespaceAndPath(namespace, name);
      this.familyFactory = DeferredFamilyRegistry.BLOCKS;
      return this;
   }

   public Props family(String name) {
      String[] parts = name.split(":");
      return parts.length == 2 ? this.family(parts[0], parts[1]) : this.family("conquest", name);
   }

   public Props name(String namespace, String plural, String singular) {
      return this.name(BlockName.of(namespace, plural, singular));
   }

   public Props name(String plural, String singular) {
      return this.name("conquest", plural, singular);
   }

   public Props name(String name) {
      return this.name("conquest", name, name);
   }

   public Props name(BlockName name) {
      this.name = name;
      return this;
   }

   public Props grassColor() {
      this.colorType = ColorType.GRASS;
      return this;
   }

   public Props foliageColor() {
      this.colorType = ColorType.FOLIAGE;
      return this;
   }

   public Props waterColor() {
      this.colorType = ColorType.WATER;
      return this;
   }

   public Props render(RenderLayer layer) {
      this.renderLayer = layer;
      return this;
   }

   public Props blockSetType(BlockSetType type) {
      this.blockSetType = type;
      return this;
   }

   public Props woodType(WoodType type) {
      this.woodType = type;
      return this;
   }

   public Props texture(String texture) {
      return this.texture("*", texture);
   }

   public Props texture(String name, String texture) {
      String namespace = "conquest";
      String path = texture;
      int i = texture.indexOf(58);
      if (i != -1) {
         namespace = texture.substring(0, i);
         path = texture.substring(i + 1);
      }

      int j = path.indexOf(47);
      if (j == -1) {
         path = "block/" + path;
      }

      if (this.textures == null) {
         this.textures = Textures.builder();
      }

      this.textures.add(name, withNamespace(namespace, path));
      return this;
   }

   public Props template(BlockTemplate template) {
      if (!this.getRenderLayer().isCutout() && !template.getRenderLayer().isCutout()) {
         return this;
      }

      Props props = new Props(this);
      props.solid(false);
      return props;
   }

   public Props with(String key, Object data) {
      if (this.extradata.isEmpty()) {
         this.extradata = new HashMap<>();
      }

      this.extradata.put(key, data);
      return this;
   }

   public Props variantFamily() {
      this.familyFactory = FamilyFactory.of(VariantFamily::new);
      return this;
   }

   public static Props create(Block block) {
      Preconditions.checkNotNull(block, "Block must not be null");
      return new Props(block);
   }

   public static Props create(BlockState state) {
      Preconditions.checkNotNull(state, "BlockState must not be null");
      return create(state.getBlock());
   }

   private static String withNamespace(String namespace, String name) {
      return name.indexOf(58) != -1 ? name : namespace + ":" + name;
   }
}
