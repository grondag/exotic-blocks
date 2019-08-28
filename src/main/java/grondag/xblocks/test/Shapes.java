/*******************************************************************************
 * Copyright 2019 grondag
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package grondag.xblocks.test;

import grondag.xblocks.Xb;
import grondag.xm.api.block.XmBlockRegistry;
import grondag.xm.api.block.XmBlockState;
import grondag.xm.api.collision.CollisionDispatcher;
import grondag.xm.api.modelstate.primitive.PrimitiveState;
import grondag.xm.api.modelstate.primitive.PrimitiveStateFunction;
import grondag.xm.api.primitive.simple.CylinderWithAxis;
import grondag.xm.api.primitive.simple.IcosahedralSphere;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.PillarBlock;
import net.minecraft.entity.EntityContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

/**
 * Test various primitives
 */
public class Shapes {
    
    public static void init() {
        Block block;
        
        block = new Block(Block.Settings.copy(Blocks.STONE)) {
            @Override
            public VoxelShape getOutlineShape(BlockState blockState, BlockView blockView, BlockPos pos, EntityContext entityContext) {
                return CollisionDispatcher.shapeFor(PrimitiveStateFunction.ofDefaultState(IcosahedralSphere.INSTANCE.defaultState())
                        .apply(blockState, blockView, pos, false));
            }  
        };
        
        Xb.register(block, "icosphere");
        
        XmBlockRegistry.addBlock(block, PrimitiveStateFunction.ofDefaultState(
                IcosahedralSphere.INSTANCE.newState()
                    .paintAll(TestPaints.WHITE)
                    .releaseToImmutable()));
        
        
        ////  CYLINDER
        
        block = new PillarBlock(FabricBlockSettings.copy(Blocks.STONE).dynamicBounds().build()) {
            @Override
            public VoxelShape getOutlineShape(BlockState blockState, BlockView blockView, BlockPos pos, EntityContext entityContext) {
                return CollisionDispatcher.shapeFor(XmBlockState.modelState(blockState, blockView, pos, false));
            }
        };
        
        Xb.register(block, "cylinder");
        
        XmBlockRegistry.addBlockStates(block, bs -> PrimitiveStateFunction.builder()
                .withDefaultState(PrimitiveState.AXIS_FROM_BLOCKSTATE.mutate(
                        CylinderWithAxis.INSTANCE.newState()
                            .paint(CylinderWithAxis.SURFACE_ENDS, TestPaints.STUFF)
                            .paint(CylinderWithAxis.SURFACE_SIDES, TestPaints.STUFF), bs))
                .build());
    }
}
