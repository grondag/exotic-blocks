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
        //TODO: wood after woodcutter available
//        acacia_stairs.json
//        birch_stairs.json
//        dark_oak_stairs.json
//        jungle_stairs.json
//        oak_stairs.json
//        spruce_stairs.json

        horizontalStair(Blocks.ANDESITE, "andesite", XmPaint.finder().texture(0, McTextures.ANDESITE).find());
        horizontalStair(Blocks.BRICKS, "brick", XmPaint.finder().texture(0, McTextures.BRICK).find());
        horizontalStair(Blocks.COBBLESTONE, "cobblestone", XmPaint.finder().texture(0, McTextures.COBBLESTONE).find());
        horizontalStair(Blocks.DARK_PRISMARINE, "dark_prismarine", XmPaint.finder().texture(0, McTextures.DARK_PRISMARINE).find());
        horizontalStair(Blocks.DIORITE, "diorite", XmPaint.finder().texture(0, McTextures.DIORITE).find());
        horizontalStair(Blocks.END_STONE_BRICKS, "end_stone_brick", XmPaint.finder().texture(0, McTextures.END_STONE_BRICK).find());
        horizontalStair(Blocks.GRANITE, "granite", XmPaint.finder().texture(0, McTextures.GRANITE).find());
        horizontalStair(Blocks.MOSSY_COBBLESTONE, "mossy_cobblestone", XmPaint.finder().texture(0, McTextures.MOSSY_COBBLESTONE).find());
        horizontalStair(Blocks.MOSSY_STONE_BRICKS, "mossy_stone_brick", XmPaint.finder().texture(0, McTextures.MOSSY_STONE_BRICK).find());
        horizontalStair(Blocks.NETHER_BRICKS, "nether_brick", XmPaint.finder().texture(0, McTextures.NETHER_BRICK).find());
        horizontalStair(Blocks.POLISHED_ANDESITE, "polished_andesite", XmPaint.finder().texture(0, McTextures.POLISHED_ANDESITE).find());
        horizontalStair(Blocks.POLISHED_DIORITE, "polished_diorite", XmPaint.finder().texture(0, McTextures.POLISHED_DIORITE).find());
        horizontalStair(Blocks.POLISHED_GRANITE, "polished_granite", XmPaint.finder().texture(0, McTextures.POLISHED_GRANITE).find());
        horizontalStair(Blocks.PRISMARINE_BRICKS, "prismarine_brick", XmPaint.finder().texture(0, McTextures.PRISMARINE_BRICK).find());
        horizontalStair(Blocks.PRISMARINE, "prismarine", XmPaint.finder().texture(0, McTextures.PRISMARINE).find());
        horizontalStair(Blocks.PURPUR_BLOCK, "purpur", XmPaint.finder().texture(0, McTextures.PURPUR_BLOCK).find());
        horizontalStair(Blocks.SMOOTH_QUARTZ, "smooth_quartz", XmPaint.finder().texture(0, McTextures.QUARTZ_BLOCK_BOTTOM).find());
        horizontalStair(Blocks.RED_NETHER_BRICKS, "red_nether_brick", XmPaint.finder().texture(0, McTextures.RED_NETHER_BRICK).find());
//        horizontalStair(Blocks.QUARTZ_BLOCK, "quartz", 
//                XmPaint.finder().texture(0, McTextures.QUARTZ_BLOCK_BOTTOM).find(),
//                XmPaint.finder().texture(0, McTextures.QUARTZ_BLOCK_TOP).find(),
//                XmPaint.finder().texture(0, McTextures.QUARTZ_BLOCK_SIDE).find());
        horizontalStair(Blocks.SMOOTH_RED_SANDSTONE, "smooth_red_sandstone", XmPaint.finder().texture(0, McTextures.RED_SANDSTONE_TOP).find());
        horizontalStair(Blocks.SMOOTH_SANDSTONE, "smooth_sandstone", XmPaint.finder().texture(0, McTextures.SANDSTONE_TOP).find());
        horizontalStair(Blocks.STONE_BRICKS, "stone_brick", XmPaint.finder().texture(0, McTextures.STONE_BRICK).find());
        horizontalStair(Blocks.STONE, "stone", XmPaint.finder().texture(0, McTextures.STONE).find());
    }
    
    private static void horizontalStair(Block block, String name, XmPaint paint) {
        horizontalStair(block, name, paint, paint, paint);
    }
    
    private static void horizontalStair(Block block, String name, 
            XmPaint paintBack, XmPaint paintFront, XmPaint paintSide) {
        final Block newBlock = Xb.register(
                new HorizontalWedge(block.getDefaultState()), name + "_horizontal_stair");
        
        final Function<BlockState, PrimitiveStateFunction> stateFunc = bs -> PrimitiveStateFunction.builder()
                .withDefaultState(HorizontalWedge.MODELSTATE_MUTATOR.mutate(
                        HorizontalStair.INSTANCE.newState()
                            .paint(HorizontalStair.SURFACE_BACK, paintBack)
                            .paint(HorizontalStair.SURFACE_FRONT, paintFront)
                            .paint(HorizontalStair.SURFACE_SIDE, paintSide),
                            bs))
                .withUpdate(HorizontalWedge.MODELSTATE_MUTATOR)
                .build();
        
        XmBlockRegistry.addBlockStates(newBlock, stateFunc);
    }
}
