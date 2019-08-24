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

import static net.minecraft.util.math.Direction.DOWN;
import static net.minecraft.util.math.Direction.EAST;
import static net.minecraft.util.math.Direction.NORTH;
import static net.minecraft.util.math.Direction.SOUTH;
import static net.minecraft.util.math.Direction.UP;
import static net.minecraft.util.math.Direction.WEST;

import grondag.xblocks.Xb;
import grondag.xm.api.block.XmBlockRegistry;
import grondag.xm.api.block.XmBlockState;
import grondag.xm.api.collision.CollisionDispatcher;
import grondag.xm.api.connect.state.SimpleJoinState;
import grondag.xm.api.connect.world.BlockTest;
import grondag.xm.api.mesh.CSG;
import grondag.xm.api.mesh.CsgMesh;
import grondag.xm.api.mesh.WritableMesh;
import grondag.xm.api.mesh.XmMesh;
import grondag.xm.api.mesh.XmMeshes;
import grondag.xm.api.mesh.polygon.MutablePolygon;
import grondag.xm.api.mesh.polygon.PolyTransform;
import grondag.xm.api.modelstate.SimpleModelState;
import grondag.xm.api.modelstate.SimpleModelStateUpdate;
import grondag.xm.api.modelstate.WorldToSimpleModelState;
import grondag.xm.api.paint.SurfaceTopology;
import grondag.xm.api.paint.XmPaint;
import grondag.xm.api.primitive.SimplePrimitive;
import grondag.xm.api.primitive.surface.XmSurface;
import grondag.xm.api.primitive.surface.XmSurfaceList;
import grondag.xm.api.texture.XmTextures;
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

public class SimpleCables {
    private static final XmSurfaceList SURFACES = XmSurfaceList.builder()
            .add("side", SurfaceTopology.CUBIC, XmSurface.FLAG_NONE)
            .add("end", SurfaceTopology.CUBIC, XmSurface.FLAG_NONE)
            .add("connector", SurfaceTopology.CUBIC, XmSurface.FLAG_NONE)
            .build();
    
    
    private static final XmSurface SURFACE_SIDE = SURFACES.get(0);
    private static final XmSurface SURFACE_END = SURFACES.get(1);
    private static final XmSurface SURFACE_CONNECTOR = SURFACES.get(2);
    
    private static final XmPaint PAINT_SIDE = XmPaint.finder()
            .textureDepth(1)
            .texture(0, XmTextures.TILE_NOISE_LIGHT)
            .textureColor(0, 0xFF808090)
            .find();
    
    private static final XmPaint PAINT_END = XmPaint.finder()
            .textureDepth(1)
            .texture(0, XmTextures.TILE_NOISE_SUBTLE)
            .textureColor(0, 0xFF303030)
            .find();
    
    private static final XmPaint PAINT_CONNECTOR = XmPaint.finder()
            .textureDepth(1)
            .texture(0, XmTextures.TILE_NOISE_SUBTLE)
            .textureColor(0, 0xFF404050)
            .find();
    
    // could be used to configure cable shape
    private static final float THICKNESS = 0.25f;
    private static final float CONNECTOR_DEPTH = 0.1f;
    private static final float CONNECTOR_MARGIN = 0.1f;

    // these are derived
    private static final float MIN = 0.5f - THICKNESS * 0.5f;
    private static final float MAX = 1f - MIN;
    private static final float END_MIN = Math.max(0, MIN - THICKNESS);
    private static final float END_MAX = Math.min(1, MAX + THICKNESS);
    private static final float CONNECTOR_MIN = Math.max(0, MIN - CONNECTOR_MARGIN);
    private static final float CONNECTOR_MAX = Math.min(1, MAX + CONNECTOR_MARGIN);
    
    private static final Direction[] FACES = Direction.values();
    
    private static final void emitSection(float from, float to, Axis axis, WritableMesh mesh) {
        final MutablePolygon writer = mesh.writer();
        final PolyTransform transform = PolyTransform.get(axis);
        writer.lockUV(0, true).surface(SURFACE_SIDE);
        mesh.saveDefaults();
        
        writer.setupFaceQuad(EAST, MIN, from, MAX, to, MIN, UP);
        transform.apply(writer);
        mesh.append();
        
        writer.setupFaceQuad(WEST, MIN, from, MAX, to, MIN, UP);
        transform.apply(writer);
        mesh.append();
        
        writer.setupFaceQuad(NORTH, MIN, from, MAX, to, MIN, UP);
        transform.apply(writer);
        mesh.append();
        
        writer.setupFaceQuad(SOUTH, MIN, from, MAX, to, MIN, UP);
        transform.apply(writer);
        mesh.append();
        
        writer.setupFaceQuad(UP, MIN, MIN, MAX, MAX, 1 - to, NORTH);
        writer.surface(to > MAX 
                ? SURFACE_END 
                : SURFACE_SIDE);
        transform.apply(writer);
        mesh.append();
        
        writer.setupFaceQuad(DOWN, MIN, MIN, MAX, MAX, from, NORTH);
        writer.surface(from < MIN 
                ? SURFACE_END 
                : SURFACE_SIDE);
        transform.apply(writer);
        mesh.append();
    }
    
    private static final void emitConnector(Direction face, WritableMesh mesh) {
        final MutablePolygon writer = mesh.writer();
        final PolyTransform transform = PolyTransform.get(face);
        writer.lockUV(0, true).surface(SURFACE_CONNECTOR);
        mesh.saveDefaults();
        
        writer.setupFaceQuad(EAST, CONNECTOR_MIN, 0, CONNECTOR_MAX, CONNECTOR_DEPTH, CONNECTOR_MIN, UP);
        transform.apply(writer);
        mesh.append();
        
        writer.setupFaceQuad(WEST, CONNECTOR_MIN, 0, CONNECTOR_MAX, CONNECTOR_DEPTH, CONNECTOR_MIN, UP);
        transform.apply(writer);
        mesh.append();
        
        writer.setupFaceQuad(NORTH, CONNECTOR_MIN, 0, CONNECTOR_MAX, CONNECTOR_DEPTH, CONNECTOR_MIN, UP);
        transform.apply(writer);
        mesh.append();
        
        writer.setupFaceQuad(SOUTH, CONNECTOR_MIN, 0, CONNECTOR_MAX, CONNECTOR_DEPTH, CONNECTOR_MIN, UP);
        transform.apply(writer);
        mesh.append();
        
        writer.setupFaceQuad(UP, CONNECTOR_MIN, CONNECTOR_MIN, CONNECTOR_MAX, CONNECTOR_MAX, 1 - CONNECTOR_DEPTH, NORTH);
        transform.apply(writer);
        mesh.append();
        
        writer.setupFaceQuad(DOWN, CONNECTOR_MIN, CONNECTOR_MIN, CONNECTOR_MAX, CONNECTOR_MAX, 0, NORTH);
        writer.surface(SURFACE_END);
        transform.apply(writer);
        mesh.append();
    }
    
    private static XmMesh polyFactory(SimpleModelState modelState) {
        CsgMesh quadsA = null;
        CsgMesh quadsB = null;
        CsgMesh output = null;
        

        SimpleJoinState state = modelState.simpleJoin();
        
        if(state == SimpleJoinState.NO_JOINS) {
            quadsA = XmMeshes.claimCsg();
            quadsB = XmMeshes.claimCsg();
            output = XmMeshes.claimCsg();
            
            emitSection(END_MIN, END_MAX, Axis.X, quadsA);
            emitSection(END_MIN, END_MAX, Axis.Y, quadsB);
            CSG.union(quadsA, quadsB, output);
            quadsB.release();
            quadsB = output;
            output = XmMeshes.claimCsg();
            quadsA.clear();
            emitSection(END_MIN, END_MAX, Axis.Z, quadsA);
            CSG.union(quadsA, quadsB, output);
        } else {
            final boolean hasX = state.hasJoins(Axis.X);
            final boolean hasY = state.hasJoins(Axis.Y);
            final boolean hasZ = state.hasJoins(Axis.Z);
            final boolean hasMultiple = (hasX ? 1 : 0) + (hasY ? 1 : 0) + (hasZ ? 1 : 0)  > 1;
            
            if(hasX) {
                output = XmMeshes.claimCsg();
                final float top = state.isJoined(EAST) ? 1 : (hasMultiple ? MAX : END_MAX);
                final float bottom = state.isJoined(WEST) ? 0 : (hasMultiple ? MIN : END_MIN);
                emitSection(bottom, top, Axis.X, output);
            }
            
            if(hasY) {
                quadsA = XmMeshes.claimCsg();
                final float top = state.isJoined(UP) ? 1 : (hasMultiple ? MAX : END_MAX);
                final float bottom = state.isJoined(DOWN) ? 0 : (hasMultiple ? MIN : END_MIN);
                emitSection(bottom, top, Axis.Y, quadsA);
                if(output == null) {
                    output = quadsA;
                    quadsA = null;
                } else {
                    quadsB = output;
                    output = XmMeshes.claimCsg();
                    CSG.union(quadsA, quadsB, output);
                    quadsA.clear();
                    quadsB.release();
                    quadsB = null;
                }
            }
            
            if(hasZ) {
                if(quadsA == null) quadsA = XmMeshes.claimCsg();
                final float top = state.isJoined(NORTH) ? 1 : (hasMultiple ? MAX : END_MAX);
                final float bottom = state.isJoined(SOUTH) ? 0 : (hasMultiple ? MIN : END_MIN);
                emitSection(bottom, top, Axis.Z, quadsA);
                
                if(output == null) {
                    output = quadsA;
                    quadsA = null;
                } else {
                    quadsB = output;
                    output = XmMeshes.claimCsg();
                    CSG.union(quadsA, quadsB, output);
                    quadsA.clear();
                    quadsB.release();
                    quadsB = null;
                }
            }
        }
        
        final int connectorBits = modelState.primitiveBits();
        if(connectorBits != 0) {
            if(quadsA == null) quadsA = XmMeshes.claimCsg();
            for(Direction face : FACES) {
                if((connectorBits & (1 << face.ordinal())) != 0) {
                    emitConnector(face, quadsA);
                }
            }
            quadsB = output;
            output = XmMeshes.claimCsg();
            CSG.union(quadsA, quadsB, output);
        }
        
        if(quadsA != null) quadsA.release();
        if(quadsB != null) quadsB.release();
        return output.releaseToReader();
    };
    
    private static final SimplePrimitive PRIMITIVE = SimplePrimitive.builder()
            .surfaceList(SURFACES)
            .polyFactory(SimpleCables::polyFactory)
            .primitiveBitCount(6)
            .simpleJoin(true)
            .build(Xb.idString("simple_cable_test"));
    
    private static final SimpleModelStateUpdate MODEL_STATE_UPDATE = (modelState, xmBlockState, world, pos, neighbors, refreshFromWorld) -> {
        // join should already be handled, so we just need to check if neighbors are inventory
        if(refreshFromWorld) {
            int bits = 0;
            final SimpleJoinState join = modelState.simpleJoin();
            for(Direction face : FACES) {
                if(join.isJoined(face)) {
                    if(neighbors.blockState(face).getBlock() instanceof InventoryProvider || neighbors.blockEntity(face) instanceof Inventory) {
                        bits |= 1 << face.ordinal();
                    };
                }
            }
            modelState.primitiveBits(bits);
        }
    };
    
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
    private static final WorldToSimpleModelState MODEL_STATE_FUNC = WorldToSimpleModelState.builder()
            .withDefaultState(PRIMITIVE.newState()
                    .paint(SURFACES.get(0), PAINT_SIDE)
                    .paint(SURFACES.get(1), PAINT_END)
                    .paint(SURFACES.get(2), PAINT_CONNECTOR)
                    .releaseToImmutable())
            .withJoin(JOIN_TEST)
            .withUpdate(MODEL_STATE_UPDATE)
            .build();
    
    
    public static void init() {
        Identifier id = Xb.id("simple_cable_test");
        Registry.BLOCK.add(id, CABLE_BLOCK);
        Registry.ITEM.add(id, new BlockItem(CABLE_BLOCK, new Item.Settings().maxCount(64).group(ItemGroup.BUILDING_BLOCKS)));
        XmBlockRegistry.addBlock(CABLE_BLOCK, MODEL_STATE_FUNC);
    }
}
