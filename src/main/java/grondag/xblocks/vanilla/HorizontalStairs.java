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

package grondag.xblocks.vanilla;

import java.util.function.Function;

import grondag.xblocks.Xb;
import grondag.xm.api.block.XmBlockRegistry;
import grondag.xm.api.block.base.HorizontalWedge;
import grondag.xm.api.modelstate.primitive.PrimitiveStateFunction;
import grondag.xm.api.paint.XmPaint;
import grondag.xm.api.primitive.simple.HorizontalStair;
import grondag.xm.api.texture.McTextures;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;

public class HorizontalStairs {

    public static void init() {
        horizontalStair(Blocks.STONE, "stone", XmPaint.finder().texture(0, McTextures.STONE).find());
    }
    
    private static void horizontalStair(Block block, String name, XmPaint paint) {
        final Block newBlock = Xb.register(
                new HorizontalWedge(block.getDefaultState()), name + "_horizontal_stair");
        
        final Function<BlockState, PrimitiveStateFunction> stateFunc = bs -> PrimitiveStateFunction.builder()
                .withDefaultState(HorizontalWedge.MODELSTATE_MUTATOR.mutate(
                        HorizontalStair.INSTANCE.newState()
                            .paintAll(paint), bs))
                .withUpdate(HorizontalWedge.MODELSTATE_MUTATOR)
                .build();
        
        XmBlockRegistry.addBlockStates(newBlock, stateFunc);
    }
}
