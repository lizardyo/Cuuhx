package com.conquestrefabricated.content.effects;

import java.util.UUID;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.world.entity.player.Player;

public class CustomSlownessEffect extends MobEffect {
   public static final UUID MODIFIER_ID = UUID.fromString("0e19c602-a936-4fa8-a6a5-8f9980c1e196");

   public CustomSlownessEffect() {
      super(MobEffectCategory.HARMFUL, 5926017);
   }

   public boolean isDurationEffectTick(int duration, int amplifier) {
      return true;
   }

   public void addAttributeModifiers(LivingEntity entity, AttributeMap attributes, int amplifier) {
      entity.getAttribute(Attributes.MOVEMENT_SPEED)
         .addTransientModifier(new AttributeModifier(MODIFIER_ID, "Custom Slowness modifier", this.getMovementSpeedModifierValue(amplifier), Operation.MULTIPLY_TOTAL));
   }

   public void removeAttributeModifiers(LivingEntity entity, AttributeMap attributes, int amplifier) {
      entity.getAttribute(Attributes.MOVEMENT_SPEED).removeModifier(MODIFIER_ID);
   }

   public void applyEffectTick(LivingEntity entity, int amplifier) {
      if (entity instanceof Player player) {
         if (player.jumping && player.isSprinting()) {
            double sprintPenalty = this.getSprintPenaltyValue(amplifier);
            if (player.getDeltaMovement().y > 0.0) {
               player.setDeltaMovement(
                  player.getDeltaMovement().x * (1.0 - sprintPenalty), player.getDeltaMovement().y, player.getDeltaMovement().z * (1.0 - sprintPenalty)
               );
            }
         } else if (player.jumping) {
            double jumpReduction = this.getJumpReductionValue(amplifier);
            if (player.getDeltaMovement().y > 0.0) {
               player.setDeltaMovement(
                  player.getDeltaMovement().x * (1.0 - jumpReduction), player.getDeltaMovement().y, player.getDeltaMovement().z * (1.0 - jumpReduction)
               );
            }
         }
      }
   }

   private double getMovementSpeedModifierValue(int amplifier) {
      return -0.05 * (amplifier + 1);
   }

   private double getJumpReductionValue(int amplifier) {
      return 0.1 * amplifier;
   }

   private double getSprintPenaltyValue(int amplifier) {
      return 0.1 * amplifier;
   }
}
