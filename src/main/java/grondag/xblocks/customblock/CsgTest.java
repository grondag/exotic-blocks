package grondag.xblocks.customblock;

import java.util.function.Function;

import grondag.xblocks.XB;
import grondag.xm.api.block.XmBlockRegistry;
import grondag.xm.api.collision.CollisionDispatcher;
import grondag.xm.api.mesh.PolyTransform;
import grondag.xm.api.modelstate.SimpleModelStateFunction;
import grondag.xm.api.paint.XmPaint;
import grondag.xm.api.primitive.SimplePrimitive;
import grondag.xm.api.primitive.surface.XmSurface;
import grondag.xm.api.primitive.surface.XmSurfaceList;
import grondag.xm.api.texture.XmTextures;
import grondag.xm.mesh.stream.CsgPolyStream;
import grondag.xm.mesh.stream.PolyStream;
import grondag.xm.mesh.stream.PolyStreams;
import grondag.xm.mesh.stream.WritablePolyStream;
import grondag.xm.model.varia.CSG;
import grondag.xm.model.varia.MeshHelper;
import grondag.xm.painting.SurfaceTopology;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityContext;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class CsgTest {
    static final XmPaint RED = XmPaint.finder()
            .textureDepth(1)
            .texture(0, XmTextures.TILE_NOISE_SUBTLE)
            .textureColor(0, 0xFFFF8080)
            .find();
    
    static final XmPaint BLUE = XmPaint.finder()
            .textureDepth(1)
            .texture(0, XmTextures.TILE_NOISE_SUBTLE)
            .textureColor(0, 0xFF8080FF)
            .find();
    
    static final XmPaint GREEN = XmPaint.finder()
            .textureDepth(1)
            .texture(0, XmTextures.TILE_NOISE_SUBTLE)
            .textureColor(0, 0xFF80FF80)
            .find();
    
    static final XmSurfaceList SURFACES = XmSurfaceList.builder()
            .add("red", SurfaceTopology.CUBIC, XmSurface.FLAG_NONE)
            .add("green", SurfaceTopology.CUBIC, XmSurface.FLAG_NONE)
            .add("blue", SurfaceTopology.CUBIC, XmSurface.FLAG_NONE)
            .build();
    
    
    static final Function<PolyTransform, PolyStream> POLY_FACTORY = transform -> {
        CsgPolyStream quadsA = PolyStreams.claimCsg();
        CsgPolyStream quadsB = PolyStreams.claimCsg();
        CsgPolyStream quadsC = PolyStreams.claimCsg();

        quadsA.writer()
            .lockUV(0, true)
            .surface(SURFACES.get(0));
        quadsA.saveDefaults();
        MeshHelper.makePaintableBox(0f, 0.4f, 0.4f, 1.0f, 0.6f, 0.6f, quadsA);

        quadsB.writer()
            .lockUV(0, true)
            .surface(SURFACES.get(1));
        quadsB.saveDefaults();
        MeshHelper.makeIcosahedron(new Vec3d(.5, .5, .5), 0.4, quadsB, false);

        CSG.difference(quadsB, quadsA, quadsC);

        quadsA.clear();
        quadsA.writer()
            .lockUV(0, false)
            .surface(SURFACES.get(2));
        quadsA.saveDefaults();
        MeshHelper.makePaintableBox(0.35f, 0.35f, 0f, 0.5f, 0.65f, 1f, quadsA);
        
        WritablePolyStream output = PolyStreams.claimWritable();
        CSG.difference(quadsC, quadsA, output);
        
        quadsA.release();
        quadsB.release();
        quadsC.release();

        return output.releaseToReader();
        
//        return PolyStreams.claimRecoloredCopy(output);
    };
    
    static final SimplePrimitive CSG_TEST_PRIMITIVE = SimplePrimitive.builder()
            .surfaceList(SURFACES)
            .polyFactory(POLY_FACTORY)
            .build(XB.idString("csg_test"));
    
    public static void init() {
        final Block csgTest = new Block(Block.Settings.copy(Blocks.ACACIA_LOG)) {
//            //TODO: remove
//            @Override
//            public boolean hasDynamicBounds() {
//                return false;
//            }
            
            @Override
            public VoxelShape getOutlineShape(BlockState blockState, BlockView blockView, BlockPos pos, EntityContext entityContext) {
                return CollisionDispatcher.shapeFor(CSG_TEST_PRIMITIVE.defaultState());
            }
            
            @Override
            public boolean hasSidedTransparency(BlockState blockState_1) {
                return true;
            }
        };
        Identifier id = XB.id("csgtest");
        Registry.BLOCK.add(id, csgTest);
        Registry.ITEM.add(id, new BlockItem(csgTest, new Item.Settings().maxCount(64).group(ItemGroup.BUILDING_BLOCKS)));
        
        XmBlockRegistry.addBlock(csgTest, SimpleModelStateFunction.ofDefaultState(
                CSG_TEST_PRIMITIVE.newState()
                    .paint(SURFACES.get(0), RED)
                    .paint(SURFACES.get(1), BLUE)
                    .paint(SURFACES.get(2), GREEN)
                    .releaseToImmutable()));
    }

}
