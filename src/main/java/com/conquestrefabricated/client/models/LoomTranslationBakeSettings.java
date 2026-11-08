package com.conquestrefabricated.client.models;

import com.mojang.math.Transformation;
import net.minecraft.client.resources.model.ModelState;
import org.joml.Vector3f;

/** A ModelState that applies a translation (in the model's own space) on top of the variant's rotation. */
public class LoomTranslationBakeSettings implements ModelState {
   private final ModelState parent;
   private final Transformation transformation;

   public LoomTranslationBakeSettings(ModelState parent, Vector3f translation) {
      this.parent = parent;
      this.transformation = new Transformation(translation, null, null, null);
   }

   @Override
   public Transformation getRotation() {
      Transformation parentTransform = this.parent.getRotation();
      return parentTransform == null ? this.transformation : parentTransform.compose(this.transformation);
   }

   @Override
   public boolean isUvLocked() {
      return this.parent.isUvLocked();
   }
}
