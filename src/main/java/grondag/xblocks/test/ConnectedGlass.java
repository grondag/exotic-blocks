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

import grondag.xm.api.block.XmBlockRegistry;
import grondag.xm.api.modelstate.primitive.PrimitiveStateFunction;
import grondag.xm.api.paint.XmPaint;
import grondag.xm.api.primitive.simple.Cube;
import net.minecraft.block.BlockRenderLayer;
import net.minecraft.block.Blocks;

/**
 * Tests adding connected textures to existing blocks by giving glass a custom connected texture.
 */
public class ConnectedGlass {
    public static void init() {
        // Define glass appearance
        final XmPaint paint = XmPaint.finder()
                .texture(0, CustomTextureLayout.GLASS_BORDER)
                .blendMode(0, BlockRenderLayer.TRANSLUCENT)
                .textureColor(0, 0xFFAAAAAA)
                .find();
        
        final PrimitiveStateFunction stateFunc = PrimitiveStateFunction.ofDefaultState(
                Cube.INSTANCE.newState()
                    .paintAll(paint)
                    .releaseToImmutable());
        
        // Remap glass block using our painted primitive
        XmBlockRegistry.addBlock(Blocks.GLASS, stateFunc);
    }
}
