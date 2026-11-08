package com.conquestrefabricated.content.entities.painting;

import com.conquestrefabricated.api.painting.Painting;
import com.conquestrefabricated.api.painting.PaintingHolder;
import com.conquestrefabricated.api.painting.art.Art;
import com.conquestrefabricated.content.entities.painting.art.ArtType;
import java.util.function.Function;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.decoration.HangingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

public class PaintingItem extends Item implements PaintingHolder {
   private final String name;
   private final Function<String, Art<?>> art;
   private final Function<String, Painting> type;
   private final PaintingFactory<? extends HangingEntity> factory;

   public PaintingItem(String name, Function<String, Painting> type, Function<String, Art<?>> art, PaintingFactory<? extends HangingEntity> factory) {
      super(new Properties());
      this.name = name;
      this.art = art;
      this.type = type;
      this.factory = factory;
   }

   public Component getName(ItemStack stack) {
      CompoundTag painting = stack.getTag();
      if (painting == null) {
         return super.getName(stack);
      }

      CompoundTag data = painting.getCompound("Painting");
      String typeName = data.getString("TypeID");
      String artName = data.getString("ArtID");
      ModPainting type = ModPainting.fromId(typeName);
      String displayName = "";
      if (type.isPresent()) {
         displayName = type.getDisplayName();
         ArtType art = ArtType.fromName(artName);
         if (art != null) {
            displayName = displayName + " " + art.getDisplayName(type.getTranslationKey());
         }
      } else if (!artName.isEmpty()) {
         displayName = artName;
      }

      return Component.literal(displayName);
   }

   public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
      return super.use(world, player, hand);
   }

   public InteractionResult useOn(UseOnContext context) {
      Player player = context.getPlayer();
      if (player == null) {
         return InteractionResult.FAIL;
      }

      Level world = context.getLevel();
      InteractionHand hand = context.getHand();
      Direction side = context.getClickedFace();
      if (player.getPose() == Pose.CROUCHING) {
         this.use(world, player, hand);
         return InteractionResult.FAIL;
      }

      ItemStack stack = player.getItemInHand(hand);
      CompoundTag data = stack.getTag();
      if (data == null) {
         return InteractionResult.FAIL;
      }

      CompoundTag paint = data.getCompound("Painting");
      String paintType = paint.getString("TypeID");
      String paintArt = paint.getString("ArtID");
      if (paintType.isEmpty() || paintArt.isEmpty()) {
         return InteractionResult.FAIL;
      }

      if (side != Direction.DOWN && side != Direction.UP) {
         BlockPos pos = context.getClickedPos().relative(side);
         HangingEntity painting = this.factory.create(world, pos, side, paintType, paintArt);
         if (painting == null) {
            return InteractionResult.FAIL;
         }

         if (!world.isClientSide) {
            world.addFreshEntity(painting);
            painting.playPlacementSound();
         }

         stack.shrink(1);
         return InteractionResult.SUCCESS;
      } else {
         return InteractionResult.FAIL;
      }
   }

   @Override
   public Art<?> getArt(ItemStack stack) {
      if (stack.getTag() != null) {
         String artName = stack.getTag().getCompound("Painting").getString("ArtID");
         return this.art.apply(artName);
      } else {
         return null;
      }
   }

   @Override
   public Painting getType(ItemStack stack) {
      if (stack.getTag() != null) {
         String typeName = stack.getTag().getCompound("Painting").getString("TypeID");
         return this.type.apply(typeName);
      } else {
         return null;
      }
   }
}
