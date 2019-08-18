package grondag.xblocks.basics;

import grondag.xm.api.block.XmBlockRegistry;
import grondag.xm.api.modelstate.SimpleModelStateFunction;
import grondag.xm.api.paint.XmPaint;
import grondag.xm.api.primitive.simple.Cube;
import grondag.xm.api.texture.XmTextures;
import net.minecraft.block.BlockRenderLayer;
import net.minecraft.block.Blocks;

public class Granite {
    
    public static final XmPaint GRANITE_RAW = XmPaint.finder()
            .textureDepth(2)
            .texture(0, XmTextures.TILE_NOISE_STRONG)
            .textureColor(0, 0xFF936655)
            .texture(1, XmTextures.TILE_NOISE_BLUE_A)
            .textureColor(1, 0x50FFEEDD)
            .blendMode(1, BlockRenderLayer.TRANSLUCENT)
            .find();
    
    public static final XmPaint GRANITE_POLISHED = XmPaint.finder()
            .textureDepth(3)
            .texture(0, XmTextures.TILE_NOISE_MODERATE)
            .textureColor(0, 0xFF936655)
            .texture(1, XmTextures.TILE_NOISE_BLUE_A)
            .textureColor(1, 0x70FFEEDD)
            .blendMode(1, BlockRenderLayer.TRANSLUCENT)
            .texture(2, XmTextures.BORDER_GRITTY_SINGLE_LINE)
            .textureColor(2, 0x80604030)
            .blendMode(2, BlockRenderLayer.TRANSLUCENT)
            .find();
    
    public static void init() {
        XmBlockRegistry.addBlock(Blocks.GRANITE, SimpleModelStateFunction.ofDefaultState(
                Cube.INSTANCE.newState()
                    .paintAll(GRANITE_RAW)
                    .releaseToImmutable()));
        
        XmBlockRegistry.addBlock(Blocks.POLISHED_GRANITE, SimpleModelStateFunction.ofDefaultState(
                Cube.INSTANCE.newState()
                    .paintAll(GRANITE_POLISHED)
                    .releaseToImmutable()));

//        XmBlockRegistry.addBlockStates(Blocks.GRANITE_STAIRS, bs -> SimpleModelStateFunction.builder()
//                .withDefaultState(SimpleModelState.STAIRS_FROM_BLOCKSTATE.apply(
//                        XmPrimitives.STAIR.newState().paintAll(GRANITE_RAW), bs))
//                .build());
//        
//        XmBlockRegistry.addBlockStates(Blocks.POLISHED_GRANITE_STAIRS, bs -> SimpleModelStateFunction.builder()
//                .withDefaultState(SimpleModelState.STAIRS_FROM_BLOCKSTATE.apply(
//                        XmPrimitives.STAIR.newState().paintAll(GRANITE_POLISHED), bs))
//                .build());
    }
}
