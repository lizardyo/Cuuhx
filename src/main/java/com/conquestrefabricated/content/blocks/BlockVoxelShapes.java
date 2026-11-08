package com.conquestrefabricated.content.blocks;

import java.util.Arrays;
import java.util.List;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BlockVoxelShapes {
   public static final VoxelShape pipeShape = Block.box(5.0, 0.0, 5.0, 11.0, 16.0, 11.0);
   public static final VoxelShape pipeShapeHorizontal_EW = Block.box(5.0, 5.0, 0.0, 11.0, 11.0, 16.0);
   public static final VoxelShape pipeShapeHorizontal_NS = Block.box(0.0, 5.0, 5.0, 16.0, 11.0, 11.0);
   public static final List<VoxelShape> crossFlangeShapes = Arrays.asList(
      Shapes.or(pipeShape, pipeShapeHorizontal_NS),
      Shapes.or(pipeShape, pipeShapeHorizontal_EW),
      Shapes.or(pipeShapeHorizontal_EW, pipeShapeHorizontal_NS)
   );
   public static final List<VoxelShape> urnShape = Arrays.asList(
      Shapes.or(
         Block.box(0.0, 14.0, 0.0, 16.0, 16.0, 16.0),
         Shapes.or(
            Block.box(3.0, 5.5, 3.0, 13.0, 13.0, 13.0),
            Shapes.or(
               Block.box(1.5, 7.0, 1.5, 14.5, 12.0, 14.5),
               Shapes.or(
                  Block.box(2.0, 13.0, 2.0, 14.0, 14.0, 14.0),
                  Shapes.or(
                     Block.box(5.0, 3.5, 5.0, 11.0, 5.5, 11.0),
                     Shapes.or(
                        Block.box(5.0, 3.5, 5.0, 11.0, 5.5, 11.0),
                        Shapes.or(
                           Block.box(5.0, 3.5, 5.0, 11.0, 5.5, 11.0),
                           Shapes.or(Block.box(2.0, 0.0, 2.0, 14.0, 2.0, 14.0), Block.box(4.0, 2.0, 4.0, 12.0, 3.5, 12.0))
                        )
                     )
                  )
               )
            )
         )
      )
   );
   public static final VoxelShape TOP_NORTHSOUTH_SHAPE = Block.box(-10.5, 8.0, 0.0, 26.5, 16.0, 16.0);
   public static final VoxelShape TOP_EASTWEST_SHAPE = Block.box(0.0, 8.0, -10.5, 16.0, 16.0, 26.5);
   public static final List<VoxelShape> tableShapes = Arrays.asList(TOP_NORTHSOUTH_SHAPE, TOP_EASTWEST_SHAPE, TOP_NORTHSOUTH_SHAPE, TOP_EASTWEST_SHAPE);
   public static final List<VoxelShape> fiveWayFlangeShapes = Arrays.asList(
      Shapes.or(pipeShape, Shapes.or(pipeShapeHorizontal_NS, Block.box(5.0, 5.0, 8.0, 11.0, 11.0, 16.0))),
      Shapes.or(pipeShape, Shapes.or(Block.box(0.0, 5.0, 5.0, 8.0, 11.0, 11.0), pipeShapeHorizontal_EW)),
      Shapes.or(pipeShape, Shapes.or(pipeShapeHorizontal_NS, Block.box(5.0, 5.0, 0.0, 11.0, 11.0, 8.0))),
      Shapes.or(pipeShape, Shapes.or(Block.box(8.0, 5.0, 5.0, 16.0, 11.0, 11.0), pipeShapeHorizontal_EW)),
      Shapes.or(Block.box(5.0, 0.0, 5.0, 11.0, 8.0, 11.0), Shapes.or(pipeShapeHorizontal_NS, pipeShapeHorizontal_EW)),
      Shapes.or(Block.box(5.0, 8.0, 5.0, 11.0, 16.0, 11.0), Shapes.or(pipeShapeHorizontal_NS, pipeShapeHorizontal_EW))
   );
   public static final List<VoxelShape> threeWayFlangeShapes = Arrays.asList(
      Shapes.or(
         Block.box(5.0, 0.0, 5.0, 11.0, 11.0, 11.0),
         Shapes.or(Block.box(8.0, 5.0, 5.0, 16.0, 11.0, 11.0), Block.box(5.0, 5.0, 8.0, 11.0, 11.0, 16.0))
      ),
      Shapes.or(
         Block.box(5.0, 0.0, 5.0, 11.0, 11.0, 11.0),
         Shapes.or(Block.box(0.0, 5.0, 5.0, 8.0, 11.0, 11.0), Block.box(5.0, 5.0, 8.0, 11.0, 11.0, 16.0))
      ),
      Shapes.or(
         Block.box(5.0, 0.0, 5.0, 11.0, 11.0, 11.0),
         Shapes.or(Block.box(0.0, 5.0, 5.0, 8.0, 11.0, 11.0), Block.box(5.0, 5.0, 0.0, 11.0, 11.0, 8.0))
      ),
      Shapes.or(
         Block.box(5.0, 0.0, 5.0, 11.0, 11.0, 11.0),
         Shapes.or(Block.box(8.0, 5.0, 5.0, 16.0, 11.0, 11.0), Block.box(5.0, 5.0, 0.0, 11.0, 11.0, 8.0))
      ),
      Shapes.or(
         Block.box(5.0, 5.0, 5.0, 11.0, 16.0, 11.0),
         Shapes.or(Block.box(8.0, 5.0, 5.0, 16.0, 11.0, 11.0), Block.box(5.0, 5.0, 8.0, 11.0, 11.0, 16.0))
      ),
      Shapes.or(
         Block.box(5.0, 5.0, 5.0, 11.0, 16.0, 11.0),
         Shapes.or(Block.box(0.0, 5.0, 5.0, 8.0, 11.0, 11.0), Block.box(5.0, 5.0, 8.0, 11.0, 11.0, 16.0))
      ),
      Shapes.or(
         Block.box(5.0, 5.0, 5.0, 11.0, 16.0, 11.0),
         Shapes.or(Block.box(0.0, 5.0, 5.0, 8.0, 11.0, 11.0), Block.box(5.0, 5.0, 0.0, 11.0, 11.0, 8.0))
      ),
      Shapes.or(
         Block.box(5.0, 5.0, 5.0, 11.0, 16.0, 11.0),
         Shapes.or(Block.box(8.0, 5.0, 5.0, 16.0, 11.0, 11.0), Block.box(5.0, 5.0, 0.0, 11.0, 11.0, 8.0))
      )
   );
   public static final List<VoxelShape> elbowFlangeShapes = Arrays.asList(
      Shapes.or(Block.box(5.0, 0.0, 5.0, 11.0, 11.0, 11.0), Block.box(5.0, 5.0, 8.0, 11.0, 11.0, 16.0)),
      Shapes.or(Block.box(5.0, 0.0, 5.0, 11.0, 11.0, 11.0), Block.box(0.0, 5.0, 5.0, 8.0, 11.0, 11.0)),
      Shapes.or(Block.box(5.0, 0.0, 5.0, 11.0, 11.0, 11.0), Block.box(5.0, 5.0, 0.0, 11.0, 11.0, 8.0)),
      Shapes.or(Block.box(5.0, 0.0, 5.0, 11.0, 11.0, 11.0), Block.box(8.0, 5.0, 5.0, 16.0, 11.0, 11.0)),
      Shapes.or(Block.box(5.0, 5.0, 5.0, 11.0, 16.0, 11.0), Block.box(5.0, 5.0, 8.0, 11.0, 11.0, 16.0)),
      Shapes.or(Block.box(5.0, 5.0, 5.0, 11.0, 16.0, 11.0), Block.box(0.0, 5.0, 5.0, 8.0, 11.0, 11.0)),
      Shapes.or(Block.box(5.0, 5.0, 5.0, 11.0, 16.0, 11.0), Block.box(5.0, 5.0, 0.0, 11.0, 11.0, 8.0)),
      Shapes.or(Block.box(5.0, 5.0, 5.0, 11.0, 16.0, 11.0), Block.box(8.0, 5.0, 5.0, 16.0, 11.0, 11.0)),
      Shapes.or(Block.box(5.0, 5.0, 5.0, 16.0, 11.0, 11.0), Block.box(5.0, 5.0, 8.0, 11.0, 11.0, 16.0)),
      Shapes.or(Block.box(0.0, 5.0, 5.0, 11.0, 11.0, 11.0), Block.box(5.0, 5.0, 8.0, 11.0, 11.0, 16.0)),
      Shapes.or(Block.box(0.0, 5.0, 5.0, 11.0, 11.0, 11.0), Block.box(5.0, 5.0, 0.0, 11.0, 11.0, 8.0)),
      Shapes.or(Block.box(5.0, 5.0, 5.0, 16.0, 11.0, 11.0), Block.box(5.0, 5.0, 0.0, 11.0, 11.0, 8.0))
   );
   public static final List<VoxelShape> fourWayFlangeShapes = Arrays.asList(
      Shapes.or(pipeShape, Shapes.or(Block.box(5.0, 5.0, 5.0, 16.0, 11.0, 11.0), Block.box(5.0, 5.0, 8.0, 11.0, 11.0, 16.0))),
      Shapes.or(pipeShape, Shapes.or(Block.box(5.0, 5.0, 5.0, 11.0, 11.0, 16.0), Block.box(0.0, 5.0, 5.0, 8.0, 11.0, 11.0))),
      Shapes.or(pipeShape, Shapes.or(Block.box(0.0, 5.0, 5.0, 11.0, 11.0, 11.0), Block.box(5.0, 5.0, 0.0, 11.0, 11.0, 8.0))),
      Shapes.or(pipeShape, Shapes.or(Block.box(5.0, 5.0, 0.0, 11.0, 11.0, 11.0), Block.box(8.0, 5.0, 5.0, 16.0, 11.0, 11.0))),
      Shapes.or(
         Block.box(5.0, 0.0, 5.0, 11.0, 11.0, 11.0),
         Shapes.or(Block.box(0.0, 5.0, 5.0, 16.0, 11.0, 11.0), Block.box(5.0, 5.0, 8.0, 11.0, 11.0, 16.0))
      ),
      Shapes.or(
         Block.box(5.0, 0.0, 5.0, 11.0, 11.0, 11.0),
         Shapes.or(Block.box(0.0, 5.0, 5.0, 8.0, 11.0, 11.0), Block.box(5.0, 5.0, 0.0, 11.0, 11.0, 16.0))
      ),
      Shapes.or(
         Block.box(5.0, 0.0, 5.0, 11.0, 11.0, 11.0),
         Shapes.or(Block.box(0.0, 5.0, 5.0, 16.0, 11.0, 11.0), Block.box(5.0, 5.0, 0.0, 11.0, 11.0, 8.0))
      ),
      Shapes.or(
         Block.box(5.0, 0.0, 5.0, 11.0, 11.0, 11.0),
         Shapes.or(Block.box(8.0, 5.0, 5.0, 16.0, 11.0, 11.0), Block.box(5.0, 5.0, 0.0, 11.0, 11.0, 16.0))
      ),
      Shapes.or(
         Block.box(5.0, 5.0, 5.0, 11.0, 16.0, 11.0),
         Shapes.or(Block.box(0.0, 5.0, 5.0, 16.0, 11.0, 11.0), Block.box(5.0, 5.0, 8.0, 11.0, 11.0, 16.0))
      ),
      Shapes.or(
         Block.box(5.0, 5.0, 5.0, 11.0, 16.0, 11.0),
         Shapes.or(Block.box(0.0, 5.0, 5.0, 8.0, 11.0, 11.0), Block.box(5.0, 5.0, 0.0, 11.0, 11.0, 16.0))
      ),
      Shapes.or(
         Block.box(5.0, 5.0, 5.0, 11.0, 16.0, 11.0),
         Shapes.or(Block.box(0.0, 5.0, 5.0, 16.0, 11.0, 11.0), Block.box(5.0, 5.0, 0.0, 11.0, 11.0, 8.0))
      ),
      Shapes.or(
         Block.box(5.0, 5.0, 5.0, 11.0, 16.0, 11.0),
         Shapes.or(Block.box(8.0, 5.0, 5.0, 16.0, 11.0, 11.0), Block.box(5.0, 5.0, 0.0, 11.0, 11.0, 16.0))
      )
   );
   public static final List<VoxelShape> t_FlangeShapes = Arrays.asList(
      Shapes.or(pipeShape, Block.box(5.0, 5.0, 5.0, 16.0, 11.0, 11.0)),
      Shapes.or(pipeShape, Block.box(5.0, 5.0, 5.0, 11.0, 11.0, 16.0)),
      Shapes.or(pipeShape, Block.box(0.0, 5.0, 5.0, 11.0, 11.0, 11.0)),
      Shapes.or(pipeShape, Block.box(5.0, 5.0, 0.0, 11.0, 11.0, 11.0)),
      Shapes.or(
         Block.box(5.0, 5.0, 5.0, 11.0, 11.0, 11.0),
         Shapes.or(Block.box(0.0, 5.0, 5.0, 16.0, 11.0, 11.0), Block.box(5.0, 5.0, 8.0, 11.0, 11.0, 16.0))
      ),
      Shapes.or(
         Block.box(5.0, 5.0, 5.0, 11.0, 11.0, 11.0),
         Shapes.or(Block.box(0.0, 5.0, 5.0, 8.0, 11.0, 11.0), Block.box(5.0, 5.0, 0.0, 11.0, 11.0, 16.0))
      ),
      Shapes.or(
         Block.box(5.0, 5.0, 5.0, 11.0, 11.0, 11.0),
         Shapes.or(Block.box(0.0, 5.0, 5.0, 16.0, 11.0, 11.0), Block.box(5.0, 5.0, 0.0, 11.0, 11.0, 8.0))
      ),
      Shapes.or(
         Block.box(5.0, 5.0, 5.0, 11.0, 11.0, 11.0),
         Shapes.or(Block.box(8.0, 5.0, 5.0, 16.0, 11.0, 11.0), Block.box(5.0, 5.0, 0.0, 11.0, 11.0, 16.0))
      ),
      Shapes.or(Block.box(5.0, 5.0, 5.0, 11.0, 16.0, 11.0), Shapes.or(Block.box(0.0, 5.0, 5.0, 16.0, 11.0, 11.0), new VoxelShape[0])),
      Shapes.or(Block.box(5.0, 5.0, 5.0, 11.0, 16.0, 11.0), Shapes.or(Block.box(5.0, 5.0, 0.0, 11.0, 11.0, 16.0), new VoxelShape[0])),
      Shapes.or(Block.box(5.0, 5.0, 5.0, 11.0, 16.0, 11.0), Shapes.or(Block.box(0.0, 5.0, 5.0, 16.0, 11.0, 11.0), new VoxelShape[0])),
      Shapes.or(Block.box(5.0, 5.0, 5.0, 11.0, 16.0, 11.0), Shapes.or(Block.box(5.0, 5.0, 0.0, 11.0, 11.0, 16.0), new VoxelShape[0])),
      Shapes.or(Block.box(5.0, 0.0, 5.0, 11.0, 11.0, 11.0), Block.box(0.0, 5.0, 5.0, 16.0, 11.0, 11.0)),
      Shapes.or(Block.box(5.0, 0.0, 5.0, 11.0, 11.0, 11.0), Block.box(5.0, 5.0, 0.0, 11.0, 11.0, 16.0)),
      Shapes.or(Block.box(5.0, 0.0, 5.0, 11.0, 11.0, 11.0), Block.box(0.0, 5.0, 5.0, 16.0, 11.0, 11.0)),
      Shapes.or(Block.box(5.0, 0.0, 5.0, 11.0, 11.0, 11.0), Block.box(5.0, 5.0, 0.0, 11.0, 11.0, 16.0))
   );
   public static final List<VoxelShape> sixWayFlangeShape = Arrays.asList(
      Shapes.or(pipeShape, Shapes.or(pipeShapeHorizontal_NS, pipeShapeHorizontal_EW))
   );
   public static final List<VoxelShape> cubeFullShape = Arrays.asList(Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 16.0));
   public static final List<VoxelShape> cubePartialShape = Arrays.asList(Block.box(1.0, 0.0, 1.0, 15.0, 16.0, 15.0));
   public static final List<VoxelShape> cubeMediumLargePartialShape = Arrays.asList(Block.box(2.0, 0.0, 2.0, 14.0, 16.0, 14.0));
   public static final List<VoxelShape> cubeMediumPartialShape = Arrays.asList(Block.box(3.0, 0.0, 3.0, 13.0, 10.0, 13.0));
   public static final List<VoxelShape> cubeSmallPartialShape = Arrays.asList(Block.box(5.0, 0.0, 5.0, 11.0, 6.0, 11.0));
   public static final List<VoxelShape> playerHeadShape = Arrays.asList(Block.box(4.0, 0.0, 4.0, 12.0, 8.0, 12.0));
   public static final List<VoxelShape> slabBottomShape = Arrays.asList(Block.box(0.0, 0.0, 0.0, 16.0, 8.0, 16.0));
   public static final List<VoxelShape> smallChairShape = Arrays.asList(Block.box(0.0, 0.0, 0.0, 16.0, 6.0, 16.0));
   public static final List<VoxelShape> pillarShape = Arrays.asList(Block.box(6.0, 0.0, 6.0, 10.0, 16.0, 10.0));
   public static final List<VoxelShape> pillarShape2 = Arrays.asList(Block.box(4.0, 0.0, 4.0, 12.0, 16.0, 12.0));
   public static final List<VoxelShape> pillarHorizontalShape = Arrays.asList(
      Block.box(6.0, 6.0, 0.0, 10.0, 10.0, 16.0),
      Block.box(0.0, 6.0, 6.0, 16.0, 10.0, 10.0),
      Block.box(6.0, 6.0, 0.0, 10.0, 10.0, 16.0),
      Block.box(0.0, 6.0, 6.0, 16.0, 10.0, 10.0)
   );
   public static final List<VoxelShape> coverShape = Arrays.asList(Block.box(0.0, 0.0, 0.0, 16.0, 1.0, 16.0));
   public static final List<VoxelShape> axisShapes = Arrays.asList(
      Block.box(6.0, 0.0, 0.0, 10.0, 16.0, 16.0), Block.box(1.0, 0.0, 1.0, 15.0, 16.0, 15.0), Block.box(0.0, 0.0, 6.0, 16.0, 16.0, 10.0)
   );
   public static final List<VoxelShape> trapdoorShapes = Arrays.asList(
      Block.box(0.0, 0.0, 13.0, 16.0, 16.0, 16.0),
      Block.box(0.0, 0.0, 0.0, 3.0, 16.0, 16.0),
      Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 3.0),
      Block.box(13.0, 0.0, 0.0, 16.0, 16.0, 16.0),
      Block.box(0.0, 0.0, 0.0, 16.0, 3.0, 16.0),
      Block.box(0.0, 13.0, 0.0, 16.0, 16.0, 16.0)
   );
   public static final List<VoxelShape> dragonHeadShapes = Arrays.asList(
      Shapes.or(Block.box(1.0, 0.0, 1.0, 15.0, 16.0, 15.0), Block.box(3.5, 6.5, -12.5, 12.0, 13.5, 2.0)),
      Shapes.or(Block.box(1.0, 0.0, 1.0, 15.0, 16.0, 15.0), Block.box(14.0, 6.5, 3.5, 28.5, 13.5, 12.0)),
      Shapes.or(Block.box(1.0, 0.0, 1.0, 15.0, 16.0, 15.0), Block.box(4.0, 6.5, 14.0, 12.0, 13.5, 28.5)),
      Shapes.or(Block.box(1.0, 0.0, 1.0, 15.0, 16.0, 15.0), Block.box(-12.5, 6.5, 4.0, 2.0, 13.5, 12.0))
   );
   public static final List<VoxelShape> stairTopShapes = Arrays.asList(
      Shapes.or(Block.box(0.0, 0.0, 8.0, 16.0, 16.0, 16.0), Block.box(0.0, 8.0, 0.0, 16.0, 16.0, 8.0)),
      Shapes.or(Block.box(0.0, 0.0, 0.0, 8.0, 16.0, 16.0), Block.box(8.0, 8.0, 0.0, 16.0, 16.0, 16.0)),
      Shapes.or(Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 8.0), Block.box(0.0, 8.0, 8.0, 16.0, 16.0, 16.0)),
      Shapes.or(Block.box(8.0, 0.0, 0.0, 16.0, 16.0, 16.0), Block.box(0.0, 8.0, 0.0, 8.0, 16.0, 16.0))
   );
   public static final List<VoxelShape> stairBottomShapes = Arrays.asList(
      Shapes.or(Block.box(0.0, 0.0, 8.0, 16.0, 16.0, 16.0), Block.box(0.0, 0.0, 0.0, 16.0, 8.0, 8.0)),
      Shapes.or(Block.box(0.0, 0.0, 0.0, 8.0, 16.0, 16.0), Block.box(8.0, 0.0, 0.0, 16.0, 8.0, 16.0)),
      Shapes.or(Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 8.0), Block.box(0.0, 0.0, 8.0, 16.0, 8.0, 16.0)),
      Shapes.or(Block.box(8.0, 0.0, 0.0, 16.0, 16.0, 16.0), Block.box(0.0, 0.0, 0.0, 8.0, 8.0, 16.0))
   );
   public static final List<VoxelShape> stairInnerCornerTopShapes = Arrays.asList(
      Shapes.or(
         Block.box(0.0, 8.0, 0.0, 16.0, 16.0, 16.0),
         new VoxelShape[]{Block.box(0.0, 0.0, 8.0, 16.0, 8.0, 16.0), Block.box(8.0, 0.0, 0.0, 16.0, 8.0, 8.0)}
      ),
      Shapes.or(
         Block.box(0.0, 8.0, 0.0, 16.0, 16.0, 16.0),
         new VoxelShape[]{Block.box(0.0, 0.0, 0.0, 8.0, 8.0, 16.0), Block.box(8.0, 0.0, 8.0, 16.0, 8.0, 16.0)}
      ),
      Shapes.or(
         Block.box(0.0, 8.0, 0.0, 16.0, 16.0, 16.0),
         new VoxelShape[]{Block.box(0.0, 0.0, 0.0, 16.0, 8.0, 8.0), Block.box(0.0, 0.0, 8.0, 8.0, 8.0, 16.0)}
      ),
      Shapes.or(
         Block.box(0.0, 8.0, 0.0, 16.0, 16.0, 16.0),
         new VoxelShape[]{Block.box(8.0, 0.0, 0.0, 16.0, 8.0, 16.0), Block.box(0.0, 0.0, 0.0, 8.0, 8.0, 8.0)}
      )
   );
   public static final List<VoxelShape> stairOuterCornerTopShapes = Arrays.asList(
      Shapes.or(Block.box(0.0, 8.0, 0.0, 16.0, 16.0, 16.0), Block.box(8.0, 0.0, 8.0, 16.0, 8.0, 16.0)),
      Shapes.or(Block.box(0.0, 8.0, 0.0, 16.0, 16.0, 16.0), Block.box(0.0, 0.0, 8.0, 8.0, 8.0, 16.0)),
      Shapes.or(Block.box(0.0, 8.0, 0.0, 16.0, 16.0, 16.0), Block.box(0.0, 0.0, 0.0, 8.0, 8.0, 8.0)),
      Shapes.or(Block.box(0.0, 8.0, 0.0, 16.0, 16.0, 16.0), Block.box(8.0, 0.0, 0.0, 16.0, 8.0, 8.0))
   );
   public static final List<VoxelShape> verticalSlabShapes = Arrays.asList(
      Block.box(0.0, 0.0, 8.0, 16.0, 16.0, 16.0),
      Block.box(0.0, 0.0, 0.0, 8.0, 16.0, 16.0),
      Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 8.0),
      Block.box(8.0, 0.0, 0.0, 16.0, 16.0, 16.0)
   );
   public static final List<VoxelShape> verticalQuarterShapes = Arrays.asList(
      Block.box(8.0, 0.0, 8.0, 16.0, 16.0, 16.0),
      Block.box(0.0, 0.0, 8.0, 8.0, 16.0, 16.0),
      Block.box(0.0, 0.0, 0.0, 8.0, 16.0, 8.0),
      Block.box(8.0, 0.0, 0.0, 16.0, 16.0, 8.0)
   );
   public static final List<VoxelShape> clothesHangarShape = Arrays.asList(
      Block.box(0.0, 8.0, 8.0, 16.0, 16.0, 16.0),
      Block.box(0.0, 8.0, 0.0, 8.0, 16.0, 16.0),
      Block.box(0.0, 8.0, 0.0, 16.0, 16.0, 8.0),
      Block.box(8.0, 8.0, 0.0, 16.0, 16.0, 16.0)
   );
   public static final List<VoxelShape> shelfShape = makeNSEWShapes(Block.box(0.0, 12.0, 6.0, 16.0, 16.0, 16.0));
   public static final List<VoxelShape> doorKnocker = makeNSEWShapes(Block.box(4.0, 4.0, 13.0, 12.0, 12.0, 16.0));
   public static final List<VoxelShape> mirrorShape = makeNSEWShapes(Block.box(2.0, 0.0, 13.0, 14.0, 16.0, 16.0));
   public static final List<VoxelShape> broomShape = makeNSEWShapes(Block.box(6.0, 0.0, 7.0, 10.0, 16.0, 16.0));
   public static final List<VoxelShape> paneShape = makeNSEWShapes(Block.box(0.0, 0.0, 6.0, 16.0, 16.0, 10.0));
   public static final List<VoxelShape> knobShapes = Arrays.asList(
      Block.box(4.0, 4.0, 13.0, 12.0, 12.0, 16.0),
      Block.box(0.0, 4.0, 4.0, 3.0, 12.0, 12.0),
      Block.box(4.0, 4.0, 0.0, 12.0, 12.0, 3.0),
      Block.box(13.0, 4.0, 4.0, 16.0, 12.0, 12.0),
      Block.box(4.0, 0.0, 4.0, 12.0, 3.0, 12.0),
      Block.box(4.0, 13.0, 4.0, 12.0, 16.0, 12.0)
   );
   public static final List<VoxelShape> bellowsShape = makeNSEWShapes(Block.box(3.0, 0.0, 0.0, 13.0, 12.0, 16.0));
   public static final List<VoxelShape> slabShape = Arrays.asList(
      Block.box(0.0, 0.0, 0.0, 16.0, 8.0, 16.0), Block.box(0.0, 8.0, 0.0, 16.0, 16.0, 16.0)
   );
   public static final List<VoxelShape> spinningWheelShape = makeNSEWShapes(Block.box(0.0, 0.0, 3.0, 16.0, 16.0, 13.0));
   public static final List<VoxelShape> cabinetShape = makeNSEWShapes(Block.box(0.0, 0.0, 6.0, 16.0, 16.0, 16.0));
   public static final List<VoxelShape> smallCorniceCornerShape = Arrays.asList(
      Shapes.or(Block.box(0.0, 0.0, 8.0, 8.0, 8.0, 16.0), Block.box(8.0, 0.0, 0.0, 16.0, 8.0, 16.0)),
      Shapes.or(Block.box(0.0, 0.0, 0.0, 8.0, 8.0, 16.0), Block.box(8.0, 0.0, 0.0, 16.0, 8.0, 8.0)),
      Shapes.or(Block.box(0.0, 0.0, 8.0, 16.0, 8.0, 16.0), Block.box(0.0, 0.0, 0.0, 8.0, 8.0, 8.0)),
      Shapes.or(Block.box(0.0, 0.0, 0.0, 16.0, 8.0, 8.0), Block.box(8.0, 0.0, 8.0, 16.0, 8.0, 16.0)),
      Shapes.or(Block.box(0.0, 8.0, 8.0, 8.0, 16.0, 16.0), Block.box(8.0, 8.0, 0.0, 16.0, 16.0, 16.0)),
      Shapes.or(Block.box(0.0, 8.0, 0.0, 8.0, 16.0, 16.0), Block.box(8.0, 8.0, 0.0, 16.0, 16.0, 8.0)),
      Shapes.or(Block.box(0.0, 8.0, 8.0, 16.0, 16.0, 16.0), Block.box(0.0, 8.0, 0.0, 8.0, 16.0, 8.0)),
      Shapes.or(Block.box(0.0, 8.0, 0.0, 16.0, 16.0, 8.0), Block.box(8.0, 8.0, 8.0, 16.0, 16.0, 16.0))
   );
   public static final List<VoxelShape> smallCorniceQuarterShape = Arrays.asList(
      Block.box(8.0, 0.0, 8.0, 16.0, 8.0, 16.0),
      Block.box(0.0, 0.0, 0.0, 8.0, 8.0, 8.0),
      Block.box(0.0, 0.0, 8.0, 8.0, 8.0, 16.0),
      Block.box(8.0, 0.0, 0.0, 16.0, 8.0, 8.0),
      Block.box(8.0, 8.0, 8.0, 16.0, 16.0, 16.0),
      Block.box(0.0, 8.0, 0.0, 8.0, 16.0, 8.0),
      Block.box(0.0, 8.0, 8.0, 8.0, 16.0, 16.0),
      Block.box(8.0, 8.0, 0.0, 16.0, 16.0, 8.0)
   );
   public static final List<VoxelShape> simpleCauldronShape = Arrays.asList(
      Shapes.join(Shapes.block(), Block.box(2.0, 4.0, 2.0, 14.0, 16.0, 14.0), BooleanOp.ONLY_FIRST)
   );
   public static final List<VoxelShape> halfCauldronShape = Arrays.asList(
      Shapes.join(Block.box(0.0, 0.0, 0.0, 16.0, 8.0, 16.0), Block.box(2.0, 4.0, 2.0, 14.0, 16.0, 14.0), BooleanOp.ONLY_FIRST)
   );
   public static final List<VoxelShape> halfCauldronShape1 = Arrays.asList(
      Shapes.join(Block.box(0.0, 0.0, 0.0, 15.0, 8.0, 15.0), Block.box(2.0, 4.0, 2.0, 14.0, 16.0, 14.0), BooleanOp.ONLY_FIRST)
   );

   public BlockVoxelShapes() {
   }

   public static List<VoxelShape> makeNSEWShapes(VoxelShape shape) {
      return Arrays.asList(shape, rotateShape(Direction.EAST, shape), rotateShape(Direction.SOUTH, shape), rotateShape(Direction.WEST, shape));
   }

   public static VoxelShape rotateShape(Direction to, VoxelShape shape) {
      VoxelShape[] buffer = new VoxelShape[]{shape, Shapes.empty()};
      int times = (to.get2DDataValue() - Direction.NORTH.get2DDataValue() + 4) % 4;

      for (int i = 0; i < times; i++) {
         buffer[0]
            .forAllBoxes(
               (minX, minY, minZ, maxX, maxY, maxZ) -> buffer[1] = Shapes.or(buffer[1], Shapes.box(1.0 - maxZ, minY, minX, 1.0 - minZ, maxY, maxX))
            );
         buffer[0] = buffer[1];
         buffer[1] = Shapes.empty();
      }

      return buffer[0];
   }
}
