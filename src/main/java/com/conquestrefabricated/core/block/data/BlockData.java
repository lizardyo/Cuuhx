package com.conquestrefabricated.core.block.data;

import com.conquestrefabricated.core.asset.annotation.ItemDescription;
import com.conquestrefabricated.core.asset.pack.VirtualResourcepack;
import com.conquestrefabricated.core.block.builder.BlockName;
import com.conquestrefabricated.core.block.builder.Props;
import com.conquestrefabricated.core.block.data.forge.BlockDataImpl;
import com.conquestrefabricated.core.block.factory.InitializationException;
import java.util.List;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

public class BlockData {
   public final Block block;
   private final Props props;
   private final BlockName blockName;
   private final BlockTemplate template;
   public final ResourceLocation registryName;
   private Item item = null;

   public BlockData(Block block, BlockTemplate template, BlockName blockName, Props props) {
      this.template = template;
      this.registryName = template.getRegistryName(blockName);
      this.blockName = blockName;
      this.block = block;
      this.props = props;
      registerBlock(this);
   }

   public static void registerBlock(BlockData blockData) {
      BlockDataImpl.registerBlock(blockData);
   }

   public Block getBlock() {
      return this.block;
   }

   public Item getItem() throws InitializationException {
      if (this.item == null) {
         Properties properties = new Properties();

         try {
            this.item = new BlockItem(this.getBlock(), properties) {
               public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag context) {
                  ItemDescription tooltipAnnotation = this.getBlock().getClass().getAnnotation(ItemDescription.class);
                  if (tooltipAnnotation != null) {
                     tooltip.add(Component.translatable("tooltip.conquest.block." + tooltipAnnotation.description()));
                  }
               }
            };
            return this.item;
         } catch (Throwable t) {
            throw new InitializationException(t);
         }
      } else {
         return this.item;
      }
   }

   public Props getProps() {
      return this.props;
   }

   public BlockName getBlockName() {
      return this.blockName;
   }

   public ResourceLocation getRegistryName() {
      return this.registryName;
   }

   public void addClientResources(VirtualResourcepack.Builder builder) {
      if (!this.props.isManual()) {
         this.template.addClientResources(builder, this.blockName, this.props.textures(), this.registryName);
      }
   }

   public void addServerResources(VirtualResourcepack.Builder builder) {
      if (!this.props.isManual()) {
         this.template.addServerResources(builder, this.blockName, this.registryName);
      }
   }

   public void addRenders() {
      this.template.registerRenders(this.block, this.props);
   }
}
