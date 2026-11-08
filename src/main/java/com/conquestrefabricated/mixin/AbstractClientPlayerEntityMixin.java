package com.conquestrefabricated.mixin;

import com.conquestrefabricated.content.effects.CustomSlownessEffect;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import java.util.Collection;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = AbstractClientPlayer.class, priority = 500)
public abstract class AbstractClientPlayerEntityMixin {
   public AbstractClientPlayerEntityMixin() {
   }

   @ModifyReturnValue(method = "getFieldOfViewModifier", at = @At("RETURN"))
   private float adjustFovWithCustomEffect(float original) {
      AbstractClientPlayer player = (AbstractClientPlayer)(Object)this;
      AttributeInstance speedAttribute = player.getAttribute(Attributes.MOVEMENT_SPEED);
      return speedAttribute != null && speedAttribute.getModifier(CustomSlownessEffect.MODIFIER_ID) != null
         ? this.calculateAdjustedFov(player, speedAttribute)
         : original;
   }

   private float calculateAdjustedFov(AbstractClientPlayer player, AttributeInstance speedAttribute) {
      Collection<AttributeModifier> modifiers = speedAttribute.getModifiers();
      double baseValue = speedAttribute.getBaseValue();
      double valueWithoutOurModifier = baseValue;

      for (AttributeModifier modifier : modifiers) {
         if (!modifier.is(CustomSlownessEffect.MODIFIER_ID)) {
            if (modifier.operation() == Operation.ADD_VALUE) {
               valueWithoutOurModifier += modifier.amount();
            } else if (modifier.operation() == Operation.ADD_MULTIPLIED_BASE) {
               valueWithoutOurModifier += baseValue * modifier.amount();
            }
         }
      }

      double multiplierTotal = 1.0;

      for (AttributeModifier modifier : modifiers) {
         if (!modifier.is(CustomSlownessEffect.MODIFIER_ID) && modifier.operation() == Operation.ADD_MULTIPLIED_TOTAL) {
            multiplierTotal += modifier.amount();
         }
      }

      valueWithoutOurModifier *= multiplierTotal;
      float f = 1.0F;
      if (player.getAbilities().flying) {
         f *= 1.1F;
      }

      f *= ((float)valueWithoutOurModifier / player.getAbilities().getWalkingSpeed() + 1.0F) / 2.0F;
      if (player.getAbilities().getWalkingSpeed() == 0.0F || Float.isNaN(f) || Float.isInfinite(f)) {
         f = 1.0F;
      }

      if (player.isUsingItem()) {
         if (player.getUseItem().is(Items.BOW)) {
            int i = player.getTicksUsingItem();
            float g = i / 20.0F;
            if (g > 1.0F) {
               g = 1.0F;
            } else {
               g *= g;
            }

            f *= 1.0F - g * 0.15F;
         } else if (Minecraft.getInstance().options.getCameraType().isFirstPerson() && player.getUseItem().is(Items.SPYGLASS)) {
            return 0.1F;
         }
      }

      return Mth.lerp(((Double)Minecraft.getInstance().options.fovEffectScale().get()).floatValue(), 1.0F, f);
   }
}
