package grondag.niceblocks.basics;

import grondag.xm.api.block.XmBlockRegistry;
import grondag.xm.api.modelstate.SimpleModelStateFunction;
import grondag.xm.api.paint.XmPaint;
import grondag.xm.api.texture.XmTextures;
import grondag.xm.init.XmPrimitives;
import net.minecraft.block.BlockRenderLayer;
import net.minecraft.block.Blocks;

public class Granite {
    public static void init() {
        initGranite();
        initPolishedGranite();
    }
    
    private static void initGranite() {
        final XmPaint paint = XmPaint.finder()
                .textureDepth(2)
                .texture(0, XmTextures.TILE_NOISE_STRONG)
                .textureColor(0, 0xFF936655)
                .texture(1, XmTextures.TILE_NOISE_BLUE_A)
                .textureColor(1, 0x50FFEEDD)
                .blendMode(1, BlockRenderLayer.TRANSLUCENT)
                .find();
        
        final SimpleModelStateFunction stateFunc = SimpleModelStateFunction.ofDefaultState(
                XmPrimitives.CUBE.newState()
                .paintAll(paint)
                .releaseToImmutable());
        
        XmBlockRegistry.addBlock(Blocks.GRANITE, stateFunc);
    }
    
    private static void initPolishedGranite() {
        final XmPaint paint = XmPaint.finder()
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
        
        final SimpleModelStateFunction stateFunc = SimpleModelStateFunction.ofDefaultState(
                XmPrimitives.CUBE.newState()
                .paintAll(paint)
                .releaseToImmutable());
        
        XmBlockRegistry.addBlock(Blocks.POLISHED_GRANITE, stateFunc);
    }
}
