package grondag.xblocks.test;

import grondag.xblocks.Xb;
import grondag.xm.api.block.XmBlockRegistry;
import grondag.xm.api.collision.CollisionDispatcher;
import grondag.xm.api.modelstate.WorldToSimpleModelState;
import grondag.xm.api.paint.XmPaint;
import grondag.xm.api.primitive.simple.IcosahedralSphere;
import grondag.xm.api.texture.XmTextures;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderLayer;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

/**
 * Test various primitives
 */
public class Shapes {
    
    public static final XmPaint STUFF = XmPaint.finder()
            .textureDepth(2)
            .texture(0, XmTextures.TILE_NOISE_MODERATE)
            .textureColor(0, 0xFF9090A0)
            .texture(1, XmTextures.TILE_NOISE_BLUE_A)
            .textureColor(1, 0x50507070)
            .blendMode(1, BlockRenderLayer.TRANSLUCENT)
            .find();
    
    public static void init() {
        Block block;
        
        block = new Block(Block.Settings.copy(Blocks.STONE)) {
            @Override
            public VoxelShape getOutlineShape(BlockState blockState, BlockView blockView, BlockPos pos, EntityContext entityContext) {
                return CollisionDispatcher.shapeFor(WorldToSimpleModelState.ofDefaultState(IcosahedralSphere.INSTANCE.defaultState())
                        .apply(blockState, blockView, pos, false));
            }  
        };
        
        Xb.register(block, "icosphere");
        
        XmBlockRegistry.addBlock(block, WorldToSimpleModelState.ofDefaultState(
                IcosahedralSphere.INSTANCE.newState()
                    .paintAll(STUFF)
                    .releaseToImmutable()));
    }
}
