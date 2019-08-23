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

import grondag.xm.api.paint.XmPaint;
import grondag.xm.api.texture.XmTextures;
import net.minecraft.block.BlockRenderLayer;

/** paints used for test models */
public class TestPaints {
    private TestPaints() {}
    
    public static final XmPaint STUFF = XmPaint.finder()
        .textureDepth(2)
        .texture(0, XmTextures.BIGTEX_SANDSTONE)
        .textureColor(0, 0xFF9090A0)
        .texture(1, XmTextures.TILE_NOISE_BLUE_A)
        .textureColor(1, 0x50507070)
        .blendMode(1, BlockRenderLayer.TRANSLUCENT)
        .find();
    
    
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
