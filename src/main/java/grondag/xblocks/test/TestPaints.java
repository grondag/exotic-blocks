package grondag.xblocks.test;

import grondag.xm.api.paint.XmPaint;
import grondag.xm.api.texture.XmTextures;

public class TestPaints {
    
    private TestPaints() {}
    
    public static final XmPaint RED = XmPaint.finder()
            .textureDepth(1)
            .texture(0, XmTextures.TILE_NOISE_SUBTLE)
            .textureColor(0, 0xFFFF8080)
            .find();
    
    public static final XmPaint BLUE = XmPaint.finder()
            .textureDepth(1)
            .texture(0, XmTextures.TILE_NOISE_SUBTLE)
            .textureColor(0, 0xFF8080FF)
            .find();
    
    public static final XmPaint GREEN = XmPaint.finder()
            .textureDepth(1)
            .texture(0, XmTextures.TILE_NOISE_SUBTLE)
            .textureColor(0, 0xFF80FF80)
            .find();
}
