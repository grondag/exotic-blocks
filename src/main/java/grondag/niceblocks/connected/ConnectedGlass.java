package grondag.niceblocks.connected;

import grondag.xm.api.block.XmBlockRegistry;
import grondag.xm.api.connect.world.BlockTest;
import grondag.xm.api.modelstate.SimpleModelState;
import grondag.xm.api.paint.XmPaint;
import grondag.xm.init.XmPrimitives;
import net.minecraft.block.BlockRenderLayer;
import net.minecraft.block.Blocks;

public class ConnectedGlass {
    public static void init() {
        
        // Define glass appearance
        final XmPaint paint = XmPaint.finder()
                .textureDepth(1)
                .texture(0, ConnectedTexture.GLASS_BORDER)
                .blendMode(0, BlockRenderLayer.TRANSLUCENT)
                .textureColor(0, 0xFFAAAAAA)
                .find();
        
        final SimpleModelState defaultState = XmPrimitives.CUBE.newState()
                .paintAll(paint)
                .releaseToImmutable();
        
        // Remap glass block using our painted primitive
        XmBlockRegistry.register(Blocks.GLASS, b -> defaultState, SimpleModelState.FROM_WORLD, BlockTest.SAME_BLOCK);
    }
}
