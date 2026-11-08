package com.conquestrefabricated.mixin;

import com.conquestrefabricated.content.blocks.BlockSettingsAccessor;
import com.conquestrefabricated.content.blocks.CustomOffsetType;
import com.conquestrefabricated.content.blocks.block.BoardsHorizontal;
import com.conquestrefabricated.content.blocks.block.Layer;
import com.conquestrefabricated.content.blocks.block.Slab;
import com.conquestrefabricated.content.blocks.block.SlabCorner;
import com.conquestrefabricated.content.blocks.block.SlabEighth;
import com.conquestrefabricated.content.blocks.block.SlabLessLayers;
import com.conquestrefabricated.content.blocks.block.SlabQuarter;
import com.conquestrefabricated.content.blocks.block.VerticalSlab;
import com.conquestrefabricated.core.block.properties.ModBlockProperties;
import java.util.Optional;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.PipeBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.OffsetType;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Properties.class)
public abstract class BlockSettingsOffsetMixin implements BlockSettingsAccessor {
   @Unique
   private CustomOffsetType customOffsetType = null;

   public BlockSettingsOffsetMixin() {
   }

   @Inject(method = "offset", at = @At("HEAD"), cancellable = true)
   private void customOffset(OffsetType offsetType, CallbackInfoReturnable<Properties> cir) {
      Properties settings = (Properties)this;
      if (this.customOffsetType != null) {
         if (this.customOffsetType == CustomOffsetType.LAYER_STATE_Y) {
            settings.offsetFunction = Optional.of((state, world, pos) -> {
               double layerY = 0.0;
               int layerState = (Integer)state.getValue(BlockStateProperties.LAYERS);

               layerY = switch (layerState) {
                  case 1 -> -0.875;
                  case 2 -> -0.75;
                  case 3 -> -0.625;
                  case 4 -> -0.5;
                  case 5 -> -0.375;
                  case 6 -> -0.25;
                  case 7 -> -0.125;
                  default -> layerY;
               };
               if (offsetType == OffsetType.XYZ) {
                  Block block = state.getBlock();
                  long l = Mth.getSeed(pos.getX(), 0, pos.getZ());
                  double d = ((float)(l >> 4 & 15L) / 15.0F - 1.0) * block.getMaxVerticalOffset() + layerY;
                  float f = block.getMaxHorizontalOffset();
                  double e = Mth.clamp(((float)(l & 15L) / 15.0F - 0.5) * 0.5, -f, f) + 0.001;
                  double g = Mth.clamp(((float)(l >> 8 & 15L) / 15.0F - 0.5) * 0.5, -f, f) + 0.001;
                  return new Vec3(e, d, g);
               } else {
                  return new Vec3(0.0, layerY, 0.0);
               }
            });
            cir.setReturnValue(settings);
         } else if (this.customOffsetType == CustomOffsetType.PLANT_XYZ) {
            settings.offsetFunction = Optional.of((state, world, pos) -> {
               double layerY = 0.0;
               int layerState = (Integer)state.getValue(BlockStateProperties.LAYERS);

               layerY = switch (layerState) {
                  case 1 -> -0.875;
                  case 2 -> -0.75;
                  case 3 -> -0.625;
                  case 4 -> -0.5;
                  case 5 -> -0.375;
                  case 6 -> -0.25;
                  case 7 -> -0.125;
                  default -> layerY;
               };
               if (state.hasProperty(ModBlockProperties.OFFSET_TOGGLE) && (Boolean)state.getValue(ModBlockProperties.OFFSET_TOGGLE)) {
                  layerY += -1.0;
               }

               if (offsetType == OffsetType.XYZ) {
                  Block block = state.getBlock();
                  long l = Mth.getSeed(pos.getX(), 0, pos.getZ());
                  double d = ((float)(l >> 4 & 15L) / 15.0F - 1.0) * block.getMaxVerticalOffset() + layerY;
                  float f = block.getMaxHorizontalOffset();
                  double e = Mth.clamp(((float)(l & 15L) / 15.0F - 0.5) * 0.5, -f, f) + 0.001;
                  double g = Mth.clamp(((float)(l >> 8 & 15L) / 15.0F - 0.5) * 0.5, -f, f) + 0.001;
                  return new Vec3(e, d, g);
               } else {
                  return new Vec3(0.0, layerY, 0.0);
               }
            });
            cir.setReturnValue(settings);
         } else if (this.customOffsetType == CustomOffsetType.PLANT_XZ) {
            settings.offsetFunction = Optional.of((state, world, pos) -> {
               double layerY = 0.0;
               int layerState = (Integer)state.getValue(BlockStateProperties.LAYERS);

               layerY = switch (layerState) {
                  case 1 -> -0.875;
                  case 2 -> -0.75;
                  case 3 -> -0.625;
                  case 4 -> -0.5;
                  case 5 -> -0.375;
                  case 6 -> -0.25;
                  case 7 -> -0.125;
                  default -> layerY;
               };
               if (state.hasProperty(ModBlockProperties.OFFSET_TOGGLE) && (Boolean)state.getValue(ModBlockProperties.OFFSET_TOGGLE)) {
                  layerY += -1.0;
               }

               if (offsetType == OffsetType.XZ) {
                  Block block = state.getBlock();
                  long l = Mth.getSeed(pos.getX(), 0, pos.getZ());
                  double d = ((float)(l >> 4 & 15L) / 15.0F - 1.0) * block.getMaxVerticalOffset() + layerY;
                  float f = block.getMaxHorizontalOffset();
                  double e = Mth.clamp(((float)(l & 15L) / 15.0F - 0.5) * 0.5, -f, f) + 0.001;
                  double g = Mth.clamp(((float)(l >> 8 & 15L) / 15.0F - 0.5) * 0.5, -f, f) + 0.001;
                  return new Vec3(e, d, g);
               } else {
                  return new Vec3(0.0, layerY, 0.0);
               }
            });
            cir.setReturnValue(settings);
         } else if (this.customOffsetType == CustomOffsetType.PLANT) {
            settings.offsetFunction = Optional.of((state, world, pos) -> {
               if ((Boolean)state.getValue(ModBlockProperties.OFFSET_TOGGLE)) {
                  double layerY = -1.0;
                  if (offsetType == OffsetType.XYZ) {
                     Block block = state.getBlock();
                     long l = Mth.getSeed(pos.getX(), 0, pos.getZ());
                     double d = ((float)(l >> 4 & 15L) / 15.0F - 1.0) * block.getMaxVerticalOffset() + layerY;
                     float f = block.getMaxHorizontalOffset();
                     double e = Mth.clamp(((float)(l & 15L) / 15.0F - 0.5) * 0.5, -f, f) + 0.001;
                     double g = Mth.clamp(((float)(l >> 8 & 15L) / 15.0F - 0.5) * 0.5, -f, f) + 0.001;
                     return new Vec3(e, d, g);
                  } else {
                     return new Vec3(0.0, layerY, 0.0);
                  }
               } else {
                  return new Vec3(0.0, 0.0, 0.0);
               }
            });
            cir.setReturnValue(settings);
         } else if (this.customOffsetType == CustomOffsetType.LAYERS_STATIC) {
            settings.offsetFunction = Optional.of((state, world, pos) -> {
               double layerY = 0.0;
               int layerState = (Integer)state.getValue(BlockStateProperties.LAYERS);

               layerY = switch (layerState) {
                  case 1 -> -0.875;
                  case 2 -> -0.75;
                  case 3 -> -0.625;
                  case 4 -> -0.5;
                  case 5 -> -0.375;
                  case 6 -> -0.25;
                  case 7 -> -0.125;
                  default -> layerY;
               };
               return new Vec3(0.0, layerY, 0.0);
            });
            cir.setReturnValue(settings);
         } else if (offsetType == OffsetType.XYZ) {
            settings.offsetFunction = Optional.of((state, world, pos) -> {
               Block block = state.getBlock();
               long l = Mth.getSeed(pos.getX(), 0, pos.getZ());
               double d = ((float)(l >> 4 & 15L) / 15.0F - 1.0) * block.getMaxVerticalOffset();
               float f = block.getMaxHorizontalOffset();
               double e = Mth.clamp(((float)(l & 15L) / 15.0F - 0.5) * 0.5, -f, f);
               double g = Mth.clamp(((float)(l >> 8 & 15L) / 15.0F - 0.5) * 0.5, -f, f);
               return new Vec3(e, d, g);
            });
            cir.setReturnValue(settings);
         } else if (offsetType == OffsetType.XZ) {
            settings.offsetFunction = Optional.of((state, world, pos) -> {
               Block block = state.getBlock();
               long l = Mth.getSeed(pos.getX(), 0, pos.getZ());
               float f = block.getMaxHorizontalOffset();
               double d = Mth.clamp(((float)(l & 15L) / 15.0F - 0.5) * 0.5, -f, f);
               double e = Mth.clamp(((float)(l >> 8 & 15L) / 15.0F - 0.5) * 0.5, -f, f);
               return new Vec3(d, 0.0, e);
            });
            cir.setReturnValue(settings);
         } else if (this.customOffsetType == CustomOffsetType.LAYER_Y) {
            settings.offsetFunction = Optional.of((state, world, pos) -> {
               if ((Boolean)state.getValue(ModBlockProperties.OFFSET_TOGGLE)) {
                  double layerY = 0.0;
                  BlockPos belowPos = pos.below();
                  BlockState belowState = world.getBlockState(belowPos);
                  Block belowBlock = belowState.getBlock();
                  layerY = this.computeTranslationAmount(belowState).floatValue();
                  return new Vec3(0.0, layerY, 0.0);
               } else {
                  return new Vec3(0.0, 0.0, 0.0);
               }
            });
            cir.setReturnValue(settings);
         } else if (this.customOffsetType == CustomOffsetType.LAYER_XYZ) {
            settings.offsetFunction = Optional.of((state, world, pos) -> {
               if ((Boolean)state.getValue(ModBlockProperties.OFFSET_TOGGLE)) {
                  float translationAmountX = 0.0F;
                  float translationAmountZ = 0.0F;
                  float translationAmountY = 0.0F;
                  BlockState blockStateDown = world.getBlockState(pos.below());
                  Block blockDown = blockStateDown.getBlock();
                  if (state.hasProperty(BlockStateProperties.HORIZONTAL_FACING)) {
                     Direction direction = (Direction)state.getValue(BlockStateProperties.HORIZONTAL_FACING);
                     BlockState offsetBlock = world.getBlockState(pos.relative(direction.getOpposite(), 1));
                     if (offsetBlock.getBlock() instanceof VerticalSlab && direction == offsetBlock.getValue(BlockStateProperties.HORIZONTAL_FACING)) {
                        switch (direction) {
                           case NORTH:
                              translationAmountZ = -this.computeVerticalSlabTranslation(offsetBlock);
                              break;
                           case EAST:
                              translationAmountX = this.computeVerticalSlabTranslation(offsetBlock);
                              break;
                           case SOUTH:
                              translationAmountZ = this.computeVerticalSlabTranslation(offsetBlock);
                              break;
                           case WEST:
                              translationAmountX = -this.computeVerticalSlabTranslation(offsetBlock);
                        }
                     }
                  }

                  if (blockDown instanceof SlabBlock && blockStateDown.getValue(BlockStateProperties.SLAB_TYPE) == SlabType.BOTTOM) {
                     translationAmountY = -0.5F;
                  } else {
                     translationAmountY = this.computeYTranslation(blockStateDown);
                  }

                  return new Vec3(translationAmountX, translationAmountY, translationAmountZ);
               } else {
                  return new Vec3(0.0, 0.0, 0.0);
               }
            });
            cir.setReturnValue(settings);
         } else if (this.customOffsetType == CustomOffsetType.LAYER_XYZ_NSEWUD) {
            settings.offsetFunction = Optional.of((state, world, pos) -> {
               if ((Boolean)state.getValue(ModBlockProperties.OFFSET_TOGGLE)) {
                  float translationAmountX = 0.0F;
                  float translationAmountZ = 0.0F;
                  float translationAmountY = 0.0F;
                  BlockState blockStateDown = world.getBlockState(pos.below());
                  Block blockDown = blockStateDown.getBlock();
                  Direction direction = (Direction)state.getValue(DirectionalBlock.FACING);
                  BlockState offsetBlock = world.getBlockState(pos.relative(direction.getOpposite(), 1));
                  if (offsetBlock.getBlock() instanceof VerticalSlab && direction == offsetBlock.getValue(BlockStateProperties.HORIZONTAL_FACING)) {
                     switch (direction) {
                        case NORTH:
                           translationAmountZ = -this.computeVerticalSlabTranslation(offsetBlock);
                           break;
                        case EAST:
                           translationAmountX = this.computeVerticalSlabTranslation(offsetBlock);
                           break;
                        case SOUTH:
                           translationAmountZ = this.computeVerticalSlabTranslation(offsetBlock);
                           break;
                        case WEST:
                           translationAmountX = -this.computeVerticalSlabTranslation(offsetBlock);
                     }
                  }

                  if (blockDown instanceof SlabBlock && blockStateDown.getValue(BlockStateProperties.SLAB_TYPE) == SlabType.BOTTOM) {
                     translationAmountY = -0.5F;
                  } else {
                     translationAmountY = this.computeYTranslation(blockStateDown);
                  }

                  return new Vec3(translationAmountX, translationAmountY, translationAmountZ);
               } else {
                  return new Vec3(0.0, 0.0, 0.0);
               }
            });
            cir.setReturnValue(settings);
         } else if (this.customOffsetType == CustomOffsetType.LAYER_XYZ_LICHEN) {
            settings.offsetFunction = Optional.of((state, world, pos) -> {
               if ((Boolean)state.getValue(ModBlockProperties.OFFSET_TOGGLE)) {
                  float translationAmountX = 0.0F;
                  float translationAmountZ = 0.0F;
                  float translationAmountY = 0.0F;
                  Direction connectedDir = this.getLichenMossConnectionDirection(state);
                  Direction directionXZ = connectedDir.getOpposite();
                  BlockState offsetBlock = world.getBlockState(pos.relative(connectedDir, 1));
                  if (offsetBlock.getBlock() instanceof VerticalSlab && offsetBlock.getValue(BlockStateProperties.HORIZONTAL_FACING) == directionXZ) {
                     switch (connectedDir) {
                        case NORTH:
                           translationAmountZ = this.computeVerticalSlabTranslation(offsetBlock);
                           break;
                        case EAST:
                           translationAmountX = -this.computeVerticalSlabTranslation(offsetBlock);
                           break;
                        case SOUTH:
                           translationAmountZ = -this.computeVerticalSlabTranslation(offsetBlock);
                           break;
                        case WEST:
                           translationAmountX = this.computeVerticalSlabTranslation(offsetBlock);
                     }
                  }

                  if ((Boolean)state.getValue(PipeBlock.DOWN)) {
                     BlockState blockStateDown = world.getBlockState(pos.below());
                     translationAmountY = this.computeYTranslation(blockStateDown);
                  }

                  return new Vec3(translationAmountX, translationAmountY, translationAmountZ);
               } else {
                  return new Vec3(0.0, 0.0, 0.0);
               }
            });
            cir.setReturnValue(settings);
         }
      }
   }

   @Unique
   private Direction getLichenMossConnectionDirection(BlockState state) {
      if ((Boolean)state.getValue(PipeBlock.NORTH)) {
         return Direction.NORTH;
      } else if ((Boolean)state.getValue(PipeBlock.EAST)) {
         return Direction.EAST;
      } else if ((Boolean)state.getValue(PipeBlock.SOUTH)) {
         return Direction.SOUTH;
      } else {
         return state.getValue(PipeBlock.WEST) ? Direction.WEST : Direction.NORTH;
      }
   }

   @Unique
   private float computeVerticalSlabTranslation(BlockState offsetBlock) {
      int layers = (Integer)offsetBlock.getValue(VerticalSlab.LAYERS);

      return switch (layers) {
         case 1 -> -0.875F;
         case 2 -> -0.75F;
         case 3 -> -0.5F;
         case 4 -> -0.25F;
         default -> 0.0F;
      };
   }

   @Unique
   private float computeYTranslation(BlockState downState) {
      Block block = downState.getBlock();
      if (block instanceof SlabBlock && downState.getValue(BlockStateProperties.SLAB_TYPE) == SlabType.BOTTOM) {
         return -0.5F;
      }

      if (!(block instanceof Layer) && !(block instanceof SnowLayerBlock)) {
         if (block instanceof Slab && downState.getValue(Slab.TYPE_UPDOWN) == Half.BOTTOM) {
            int layers = (Integer)downState.getValue(Slab.LAYERS);

            return switch (layers) {
               case 1 -> -0.875F;
               case 2 -> -0.75F;
               case 3 -> -0.625F;
               case 4 -> -0.5F;
               case 5 -> -0.375F;
               case 6 -> -0.25F;
               case 7 -> -0.125F;
               default -> 0.0F;
            };
         } else if (block instanceof SlabQuarter && downState.getValue(SlabQuarter.TYPE_UPDOWN) == Half.BOTTOM) {
            int layers = (Integer)downState.getValue(SlabQuarter.LAYERS);

            return switch (layers) {
               case 1 -> -0.75F;
               case 2 -> -0.5F;
               case 3 -> -0.25F;
               default -> 0.0F;
            };
         } else {
            if (block instanceof SlabEighth && downState.getValue(SlabEighth.TYPE_UPDOWN) == Half.BOTTOM) {
               return -0.5F;
            }

            if (block instanceof SlabCorner && downState.getValue(SlabCorner.TYPE_UPDOWN) == Half.BOTTOM) {
               return -0.5F;
            }

            if (block instanceof SlabLessLayers && downState.getValue(SlabLessLayers.TYPE_UPDOWN) == Half.BOTTOM) {
               int layers = (Integer)downState.getValue(SlabLessLayers.LAYERS);

               return switch (layers) {
                  case 1 -> -0.875F;
                  case 2 -> -0.75F;
                  case 3 -> -0.5F;
                  case 4 -> -0.25F;
                  default -> 0.0F;
               };
            } else {
               return block instanceof BoardsHorizontal
                     && downState.getValue(com.conquestrefabricated.content.blocks.block.directional.Half.TYPE_UPDOWN) == Half.BOTTOM
                  ? -0.53125F
                  : 0.0F;
            }
         }
      } else {
         int layers = (Integer)downState.getValue(BlockStateProperties.LAYERS);

         return switch (layers) {
            case 1 -> -0.875F;
            case 2 -> -0.75F;
            case 3 -> -0.625F;
            case 4 -> -0.5F;
            case 5 -> -0.375F;
            case 6 -> -0.25F;
            case 7 -> -0.125F;
            default -> 0.0F;
         };
      }
   }

   @Unique
   private Float computeTranslationAmount(BlockState downState) {
      Block blockDown = downState.getBlock();
      if (blockDown instanceof SlabBlock && downState.getValue(BlockStateProperties.SLAB_TYPE) == SlabType.BOTTOM) {
         return -0.5F;
      }

      if (!(blockDown instanceof Layer) && !(blockDown instanceof SnowLayerBlock)) {
         if (blockDown instanceof Slab && downState.getValue(Slab.TYPE_UPDOWN) == Half.BOTTOM) {
            int layers = (Integer)downState.getValue(Slab.LAYERS);
            return this.getTranslationForStandardLayers(layers);
         } else if (blockDown instanceof SlabLessLayers && downState.getValue(SlabLessLayers.TYPE_UPDOWN) == Half.BOTTOM) {
            int layers = (Integer)downState.getValue(SlabLessLayers.LAYERS);
            return this.getTranslationForSlabLessLayers(layers);
         } else {
            return blockDown instanceof BoardsHorizontal
                  && downState.getValue(com.conquestrefabricated.content.blocks.block.directional.Half.TYPE_UPDOWN) == Half.BOTTOM
               ? -0.53125F
               : 0.0F;
         }
      } else {
         int layers = (Integer)downState.getValue(BlockStateProperties.LAYERS);
         return this.getTranslationForStandardLayers(layers);
      }
   }

   @Unique
   private float getTranslationForStandardLayers(int layers) {
      return switch (layers) {
         case 1 -> -0.875F;
         case 2 -> -0.75F;
         case 3 -> -0.625F;
         case 4 -> -0.5F;
         case 5 -> -0.375F;
         case 6 -> -0.25F;
         case 7 -> -0.125F;
         default -> 0.0F;
      };
   }

   @Unique
   private float getTranslationForSlabLessLayers(int layers) {
      return switch (layers) {
         case 1 -> -0.875F;
         case 2 -> -0.75F;
         case 3 -> -0.5F;
         case 4 -> -0.25F;
         default -> 0.0F;
      };
   }

   @Unique
   @Override
   public Properties setCustomOffsetter(CustomOffsetType offsetter) {
      this.customOffsetType = offsetter;
      return (Properties)this;
   }
}
