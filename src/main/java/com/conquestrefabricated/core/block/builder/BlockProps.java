package com.conquestrefabricated.core.block.builder;

import com.conquestrefabricated.content.blocks.BlockSettingsAccessor;
import com.conquestrefabricated.content.blocks.CustomOffsetType;
import com.conquestrefabricated.core.block.factory.InitializationException;
import java.util.function.Consumer;
import java.util.function.ToIntFunction;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.OffsetType;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.material.MapColor;

public abstract class BlockProps<T extends BlockProps<T>> {
   private final Block block;
   private final MapColor color;
   private CustomOffsetType customOffsetType = null;
   private DyeColor dyeColor = null;
   private SoundType sound = null;
   private CreativeModeTab group = CreativeModeTabs.searchTab();
   private ToIntFunction<BlockState> light = null;
   private Float resistance = null;
   private Float hardness = null;
   private Float slipperiness = null;
   private Boolean randomTick = null;
   private Boolean variableOpacity = null;
   private Boolean dynamicBounds = null;
   private Boolean blocksMovement = null;
   private Boolean solid = null;
   private OffsetType offset = OffsetType.NONE;

   protected BlockProps(Block block) {
      this.block = block;
      this.color = null;
   }

   protected BlockProps(BlockProps<T> props) {
      this.block = props.block;
      this.color = props.color;
      this.dyeColor = props.dyeColor;
      this.sound = props.sound;
      this.group = props.group;
      this.light = props.light;
      this.resistance = props.resistance;
      this.hardness = props.hardness;
      this.slipperiness = props.slipperiness;
      this.randomTick = props.randomTick;
      this.variableOpacity = props.variableOpacity;
      this.blocksMovement = props.blocksMovement;
      this.dynamicBounds = props.dynamicBounds;
      this.solid = props.solid;
      this.customOffsetType = props.customOffsetType;
      this.offset = props.offset;
   }

   public abstract T getProps();

   public T customOffsetType(CustomOffsetType offsetType) {
      this.customOffsetType = offsetType;
      return this.getProps();
   }

   public T dye(DyeColor color) {
      this.dyeColor = color;
      return this.getProps();
   }

   public T sound(SoundType sound) {
      this.sound = sound;
      return this.getProps();
   }

   public T light(ToIntFunction<BlockState> light) {
      this.light = light;
      return (T)this;
   }

   public T strength(double hardness, double resistance) {
      this.hardness = (float)hardness;
      this.resistance = (float)resistance;
      return this.getProps();
   }

   public T slipperiness(double slipperiness) {
      this.slipperiness = (float)slipperiness;
      return this.getProps();
   }

   public T randomTick(boolean randomTick) {
      this.randomTick = randomTick;
      return this.getProps();
   }

   public T dynamicBounds(boolean dynamicBounds) {
      this.dynamicBounds = dynamicBounds;
      return this.getProps();
   }

   public T opacity(boolean variableOpacity) {
      this.variableOpacity = variableOpacity;
      return this.getProps();
   }

   public T blocking(boolean blocksMovement) {
      this.blocksMovement = blocksMovement;
      return this.getProps();
   }

   public T solid(boolean solid) {
      this.solid = solid;
      return this.getProps();
   }

   public T group(CreativeModeTab group) {
      this.group = group;
      return this.getProps();
   }

   public T offset(OffsetType offsetType) {
      this.offset = offsetType;
      return this.getProps();
   }

   public CreativeModeTab group() {
      return this.group;
   }

   public DyeColor dye() {
      return this.dyeColor == null ? DyeColor.BLACK : this.dyeColor;
   }

   public MapColor color() {
      return this.color == null ? MapColor.COLOR_BLACK : this.color;
   }

   public Properties toSettings() throws InitializationException {
      Properties builder = this.createBuilder();
      applyNonNull(this.sound, builder::sound);
      applyNonNull(this.light, builder::lightLevel);
      applyNonNull(this.slipperiness, builder::friction);
      applyNonNull(this.solid, false, builder::noOcclusion);
      applyNonNull(this.randomTick, true, builder::randomTicks);
      applyNonNull(this.dynamicBounds, true, builder::dynamicShape);
      applyNonNull(this.variableOpacity, true, builder::dynamicShape);
      applyNonNull(this.blocksMovement, false, builder::noCollission);
      if (this.customOffsetType != null) {
         builder = ((BlockSettingsAccessor)builder).setCustomOffsetter(this.customOffsetType);
      }

      applyNonNull(this.offset, builder::offsetType);
      if (this.hardness != null && this.resistance != null) {
         builder.strength(this.hardness, this.resistance);
      }

      return builder;
   }

   private Properties createBuilder() throws InitializationException {
      Properties props;
      if (this.block != null) {
         props = Properties.copy(this.block);
      } else {
         if (this.color == null) {
            throw new InitializationException("Block.Builder requires a Material");
         }

         props = Properties.of().mapColor(this.color);
      }

      return props;
   }

   private static <T> void applyNonNull(Boolean value, boolean condition, Runnable runnable) {
      if (value != null && value == condition) {
         runnable.run();
      }
   }

   private static <T> void applyNonNull(T value, Consumer<T> consumer) {
      if (value != null) {
         consumer.accept(value);
      }
   }

   protected abstract <T> void applyNonNull(Integer var1, Consumer<T> var2);
}
