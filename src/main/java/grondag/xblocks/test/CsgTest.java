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

import static grondag.xblocks.test.TestPaints.BLUE;
import static grondag.xblocks.test.TestPaints.GREEN;
import static grondag.xblocks.test.TestPaints.RED;

import java.util.function.Function;

import grondag.xblocks.Xb;
import grondag.xm.api.block.XmBlockRegistry;
import grondag.xm.api.collision.CollisionDispatcher;
import grondag.xm.api.mesh.CsgMesh;
import grondag.xm.api.mesh.CsgMeshBuilder;
import grondag.xm.api.mesh.MeshHelper;
import grondag.xm.api.mesh.XmMesh;
import grondag.xm.api.modelstate.primitive.PrimitiveState;
import grondag.xm.api.modelstate.primitive.PrimitiveStateFunction;
import grondag.xm.api.paint.SurfaceTopology;
import grondag.xm.api.primitive.SimplePrimitive;
import grondag.xm.api.primitive.simple.IcosahedralSphere;
import grondag.xm.api.primitive.surface.XmSurface;
import grondag.xm.api.primitive.surface.XmSurfaceList;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityContext;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class CsgTest {
    static final XmSurfaceList SURFACES = XmSurfaceList.builder()
            .add("red", SurfaceTopology.CUBIC, XmSurface.FLAG_NONE)
            .add("green", SurfaceTopology.CUBIC, XmSurface.FLAG_NONE)
            .add("blue", SurfaceTopology.CUBIC, XmSurface.FLAG_NONE)
            .build();
    
    
    static final Function<PrimitiveState, XmMesh> POLY_FACTORY = modelState -> {
        final CsgMeshBuilder csg = CsgMeshBuilder.threadLocal();
        
        CsgMesh input = csg.input();
        input.writer().lockUV(0, true).surface(SURFACES.get(1)).saveDefaults();
        IcosahedralSphere.sphere(input);
        csg.union();

        input = csg.input();
        input.writer().lockUV(0, true).surface(SURFACES.get(0)).saveDefaults();
        MeshHelper.box(0f, 0.4f, 0.4f, 1.0f, 0.6f, 0.6f, input);
        csg.difference();;
        

        input = csg.input();
        input.writer().lockUV(0, false).surface(SURFACES.get(2)).saveDefaults();
        MeshHelper.box(0.35f, 0.35f, 0f, 0.5f, 0.65f, 1f, input);
        csg.difference();
        
        return csg.build();
        
//        return XmMeshes.claimRecoloredCopy(output);
    };
    
    static final SimplePrimitive CSG_TEST_PRIMITIVE = SimplePrimitive.builder()
            .surfaceList(SURFACES)
            .polyFactory(POLY_FACTORY)
            .build(Xb.idString("csg_test"));
    
    public static void init() {
        final Block csgTest = new Block(Block.Settings.copy(Blocks.ACACIA_LOG)) {
            @Override
            public VoxelShape getOutlineShape(BlockState blockState, BlockView blockView, BlockPos pos, EntityContext entityContext) {
                return CollisionDispatcher.shapeFor(CSG_TEST_PRIMITIVE.defaultState());
            }
            
            @Override
            public boolean hasSidedTransparency(BlockState blockState_1) {
                return true;
            }
        };
        Identifier id = Xb.id("csgtest");
        Registry.BLOCK.add(id, csgTest);
        Registry.ITEM.add(id, new BlockItem(csgTest, new Item.Settings().maxCount(64).group(ItemGroup.BUILDING_BLOCKS)));
        
        XmBlockRegistry.addBlock(csgTest, PrimitiveStateFunction.ofDefaultState(
                CSG_TEST_PRIMITIVE.newState()
                    .paint(SURFACES.get(0), RED)
                    .paint(SURFACES.get(1), BLUE)
                    .paint(SURFACES.get(2), GREEN)
                    .releaseToImmutable()));
    }

}
