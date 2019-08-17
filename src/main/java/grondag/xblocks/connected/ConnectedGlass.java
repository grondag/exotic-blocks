package grondag.xblocks.connected;

import grondag.xm.api.block.XmBlockRegistry;
import grondag.xm.api.modelstate.SimpleModelStateFunction;
import grondag.xm.api.paint.XmPaint;
import grondag.xm.api.primitive.simple.CubeWithAxisAndFace;
import net.minecraft.block.BlockRenderLayer;
import net.minecraft.block.Blocks;

public class ConnectedGlass {
    public static void init() {
        
        // Define glass appearance
        final XmPaint paint = XmPaint.finder()
                .texture(0, ConnectedTexture.GLASS_BORDER)
                .blendMode(0, BlockRenderLayer.TRANSLUCENT)
                .textureColor(0, 0xFFAAAAAA)
                .find();
        
        final SimpleModelStateFunction stateFunc = SimpleModelStateFunction.ofDefaultState(
                CubeWithAxisAndFace.INSTANCE.newState()
                    .paintAll(paint)
                    .releaseToImmutable());
        
        // Remap glass block using our painted primitive
        XmBlockRegistry.addBlock(Blocks.GLASS, stateFunc);
    }
}
