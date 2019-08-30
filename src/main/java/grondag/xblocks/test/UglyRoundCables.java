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

import static grondag.xblocks.test.Cables.SURFACE_CONNECTOR;
import static grondag.xblocks.test.Cables.SURFACE_END;
import static grondag.xblocks.test.Cables.SURFACE_SIDE;
import static net.minecraft.util.math.Direction.DOWN;
import static net.minecraft.util.math.Direction.EAST;
import static net.minecraft.util.math.Direction.NORTH;
import static net.minecraft.util.math.Direction.SOUTH;
import static net.minecraft.util.math.Direction.UP;
import static net.minecraft.util.math.Direction.WEST;

import java.util.function.Consumer;

import grondag.xblocks.Xb;
import grondag.xm.api.block.XmBlockRegistry;
import grondag.xm.api.block.XmBlockState;
import grondag.xm.api.collision.CollisionDispatcher;
import grondag.xm.api.connect.state.SimpleJoinState;
import grondag.xm.api.connect.world.BlockTest;
import grondag.xm.api.mesh.CsgMeshBuilder;
import grondag.xm.api.mesh.MeshHelper;
import grondag.xm.api.mesh.WritableMesh;
import grondag.xm.api.mesh.XmMesh;
import grondag.xm.api.mesh.polygon.MutablePolygon;
import grondag.xm.api.mesh.polygon.PolyTransform;
import grondag.xm.api.modelstate.primitive.PrimitiveState;
import grondag.xm.api.modelstate.primitive.PrimitiveStateFunction;
import grondag.xm.api.primitive.SimplePrimitive;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.InventoryProvider;
import net.minecraft.entity.EntityContext;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Direction.Axis;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class UglyRoundCables {
    
    // could be used to configure cable shape
    private static final float THICKNESS = 0.35f;
    private static final float CONNECTOR_DEPTH = 0.1f;
    private static final float CONNECTOR_MARGIN = 0.1f;

    // these are derived
    private static final float MIN = 0.5f - THICKNESS * 0.5f;
    private static final float MAX = 1f - MIN;
    private static final float END_DIAMETER = Math.max(0, THICKNESS + CONNECTOR_MARGIN);
    private static final float END_OFFSET = -0.5f - CONNECTOR_DEPTH * 0.5f;
    
    private static final void emitRoundSection(float from, float to, Axis axis, WritableMesh mesh) {
        final MutablePolygon writer = mesh.writer();
        final PolyTransform pt = PolyTransform.get(axis);
        final float span = to - from;
        final float nudge = 0.5f * span - 0.5f + from;
        final Consumer<MutablePolygon> transform = p -> {
            p.scaleFromBlockCenter(THICKNESS, span, THICKNESS).translate(0, nudge, 0).apply(pt);
        };
        writer.lockUV(0, false).surface(SURFACE_SIDE).saveDefaults();
        
        MeshHelper.unitCylinder(mesh, 16, transform, SURFACE_SIDE, 
                to > MAX ? SURFACE_END : SURFACE_SIDE,
                from < MIN ? SURFACE_END : SURFACE_SIDE);
    }
    
    private static final void emitRoundConnector(Direction face, WritableMesh mesh) {
        final MutablePolygon writer = mesh.writer();
        final PolyTransform pt = PolyTransform.get(face);
        final Consumer<MutablePolygon> transform = p -> {
            p.scaleFromBlockCenter(END_DIAMETER, CONNECTOR_DEPTH, END_DIAMETER).translate(0, END_OFFSET, 0).apply(pt);
        };
        writer.lockUV(0, false).surface(SURFACE_CONNECTOR).saveDefaults();
        MeshHelper.unitCylinder(mesh, 16, transform, SURFACE_CONNECTOR, SURFACE_CONNECTOR, SURFACE_END);
    }
    
    
    private static XmMesh polyFactoryRound(PrimitiveState modelState) {
        final CsgMeshBuilder csg = CsgMeshBuilder.threadLocal();

        SimpleJoinState state = modelState.simpleJoin();
        
        if(state == SimpleJoinState.NO_JOINS) {
            emitRoundSection(0, 1, Axis.Y, csg.input());
            csg.union();
            emitRoundSection(0, 1, Axis.X, csg.input());
            csg.union();
            emitRoundSection(0, 1, Axis.Z, csg.input());
            csg.union();
        } else {
            final boolean hasX = state.hasJoins(Axis.X);
            final boolean hasY = state.hasJoins(Axis.Y);
            final boolean hasZ = state.hasJoins(Axis.Z);
            final boolean hasMultiple = (hasX ? 1 : 0) + (hasY ? 1 : 0) + (hasZ ? 1 : 0) > 0;
            
            if(hasMultiple & state != SimpleJoinState.ALL_JOINS) {
                if(hasX && hasY & !hasZ) {
                    emitRoundSection(0, 1, Axis.X, csg.input());
                    csg.union();
                    emitRoundSection(0, 1, Axis.Y, csg.input());
                    csg.intersect();
                    csg.push();
                }
                
                if(hasX & hasZ) {
                    emitRoundSection(0, 1, Axis.X, csg.input());
                    csg.union();
                    emitRoundSection(0, 1, Axis.Z, csg.input());
                    csg.intersect();
                    csg.push();
                }
                    
                if(hasY & hasZ & !hasX) {
                    emitRoundSection(0, 1, Axis.Y, csg.input());
                    csg.union();
                    emitRoundSection(0, 1, Axis.Z, csg.input());
                    csg.intersect();
                    csg.push();
                }
                
                // union all the intersections
                while(csg.hasOutputStack()) {
                    csg.pop();
                    csg.union();
                }
            }
            
            if(hasX) {
                final float top = state.isJoined(WEST) ? 1 : 0.5f;
                final float bottom = state.isJoined(EAST) ? 0 : 0.5f;
                emitRoundSection(bottom, top, Axis.X, csg.input());
                csg.union();
            }
            
            if(hasY) {
                final float top = state.isJoined(UP) ? 1 : 0.5f;
                final float bottom = state.isJoined(DOWN) ? 0 : 0.5f;
                emitRoundSection(bottom, top, Axis.Y, csg.input());
                csg.union();
            }
            
            if(hasZ) {
                final float top = state.isJoined(SOUTH) ? 1 : 0.5f;
                final float bottom = state.isJoined(NORTH) ? 0 : 0.5f;
                emitRoundSection(bottom, top, Axis.Z, csg.input());
                csg.union();
            }
        }
        
        final int connectorBits = modelState.primitiveBits();
        if(connectorBits != 0) {
            for(Direction face : Cables.FACES) {
                if((connectorBits & (1 << face.ordinal())) != 0) {
                    emitRoundConnector(face, csg.input());
                    csg.union();
                }
            }
        }
        
        return csg.build();
    };
    
    private static final SimplePrimitive PRIMITIVE = SimplePrimitive.builder()
            .surfaceList(Cables.SURFACES)
            .polyFactory(UglyRoundCables::polyFactoryRound)
            .primitiveBitCount(6)
            .simpleJoin(true)
            .build(Xb.idString("round_cable_test"));
    
    
    public static final Block CABLE_BLOCK = new Block(FabricBlockSettings.copy(Blocks.IRON_BARS).dynamicBounds().build()) {
        @Override
        public VoxelShape getOutlineShape(BlockState blockState, BlockView blockView, BlockPos pos, EntityContext entityContext) {
            return CollisionDispatcher.shapeFor(XmBlockState.modelState(blockState, blockView, pos, true));
        }
    };
    
    @SuppressWarnings("rawtypes")
    private static final BlockTest JOIN_TEST = ctx -> {
        if(ctx.fromBlockState().getBlock() == CABLE_BLOCK) {
            final Block toBlock = ctx.toBlockState().getBlock();
            return toBlock == CABLE_BLOCK || toBlock instanceof InventoryProvider || ctx.toBlockEntity() instanceof Inventory;
        } else {
            return false;
        }
    };
    
    @SuppressWarnings("unchecked")
    private static final PrimitiveStateFunction MODEL_STATE_FUNC = PrimitiveStateFunction.builder()
            .withDefaultState(PRIMITIVE.newState()
                    .paint(Cables.SURFACES.get(0), Cables.PAINT_SIDE)
                    .paint(Cables.SURFACES.get(1), Cables.PAINT_END)
                    .paint(Cables.SURFACES.get(2), Cables.PAINT_CONNECTOR)
                    .releaseToImmutable())
            .withJoin(JOIN_TEST)
            .withUpdate(Cables.MODEL_STATE_UPDATE)
            .build();
    
    
    public static void init() {
        Identifier id = Xb.id("round_cable_test");
        Registry.BLOCK.add(id, CABLE_BLOCK);
        Registry.ITEM.add(id, new BlockItem(CABLE_BLOCK, new Item.Settings().maxCount(64).group(ItemGroup.BUILDING_BLOCKS)));
        XmBlockRegistry.addBlock(CABLE_BLOCK, MODEL_STATE_FUNC);
    }
}
