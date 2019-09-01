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

import grondag.xblocks.Xb;
import grondag.xm.api.block.XmBlockRegistry;
import grondag.xm.api.block.base.StairLike;
import grondag.xm.api.modelstate.primitive.PrimitiveStateFunction;
import grondag.xm.api.paint.XmPaint;
import grondag.xm.api.primitive.simple.Stair;
import grondag.xm.api.primitive.simple.Wedge;
import grondag.xm.api.texture.McTextures;
import net.minecraft.block.Block;
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

        horizontal(Blocks.ANDESITE, "andesite", XmPaint.finder().texture(0, McTextures.ANDESITE).find());
        horizontal(Blocks.BRICKS, "brick", XmPaint.finder().texture(0, McTextures.BRICK).find());
        horizontal(Blocks.COBBLESTONE, "cobblestone", XmPaint.finder().texture(0, McTextures.COBBLESTONE).find());
        horizontal(Blocks.DARK_PRISMARINE, "dark_prismarine", XmPaint.finder().texture(0, McTextures.DARK_PRISMARINE).find());
        horizontal(Blocks.DIORITE, "diorite", XmPaint.finder().texture(0, McTextures.DIORITE).find());
        horizontal(Blocks.END_STONE_BRICKS, "end_stone_brick", XmPaint.finder().texture(0, McTextures.END_STONE_BRICK).find());
        horizontal(Blocks.GRANITE, "granite", XmPaint.finder().texture(0, McTextures.GRANITE).find());
        horizontal(Blocks.MOSSY_COBBLESTONE, "mossy_cobblestone", XmPaint.finder().texture(0, McTextures.MOSSY_COBBLESTONE).find());
        horizontal(Blocks.MOSSY_STONE_BRICKS, "mossy_stone_brick", XmPaint.finder().texture(0, McTextures.MOSSY_STONE_BRICK).find());
        horizontal(Blocks.NETHER_BRICKS, "nether_brick", XmPaint.finder().texture(0, McTextures.NETHER_BRICK).find());
        horizontal(Blocks.POLISHED_ANDESITE, "polished_andesite", XmPaint.finder().texture(0, McTextures.POLISHED_ANDESITE).find());
        horizontal(Blocks.POLISHED_DIORITE, "polished_diorite", XmPaint.finder().texture(0, McTextures.POLISHED_DIORITE).find());
        horizontal(Blocks.POLISHED_GRANITE, "polished_granite", XmPaint.finder().texture(0, McTextures.POLISHED_GRANITE).find());
        horizontal(Blocks.PRISMARINE_BRICKS, "prismarine_brick", XmPaint.finder().texture(0, McTextures.PRISMARINE_BRICK).find());
        horizontal(Blocks.PRISMARINE, "prismarine", XmPaint.finder().texture(0, McTextures.PRISMARINE).find());
        horizontal(Blocks.PURPUR_BLOCK, "purpur", XmPaint.finder().texture(0, McTextures.PURPUR_BLOCK).find());
        horizontal(Blocks.SMOOTH_QUARTZ, "smooth_quartz", XmPaint.finder().texture(0, McTextures.QUARTZ_BLOCK_BOTTOM).find());
        horizontal(Blocks.RED_NETHER_BRICKS, "red_nether_brick", XmPaint.finder().texture(0, McTextures.RED_NETHER_BRICK).find());
        horizontal(Blocks.QUARTZ_BLOCK, "quartz", 
                XmPaint.finder().texture(0, McTextures.QUARTZ_BLOCK_BOTTOM).find(),
                XmPaint.finder().texture(0, McTextures.QUARTZ_BLOCK_TOP).find(),
                XmPaint.finder().texture(0, McTextures.QUARTZ_BLOCK_SIDE).find());
        horizontal(Blocks.SANDSTONE, "sandstone", 
                XmPaint.finder().texture(0, McTextures.SANDSTONE_BOTTOM).find(),
                XmPaint.finder().texture(0, McTextures.SANDSTONE_TOP).find(),
                XmPaint.finder().texture(0, McTextures.SANDSTONE).find());
        horizontal(Blocks.RED_SANDSTONE, "red_sandstone", 
                XmPaint.finder().texture(0, McTextures.RED_SANDSTONE_BOTTOM).find(),
                XmPaint.finder().texture(0, McTextures.RED_SANDSTONE_TOP).find(),
                XmPaint.finder().texture(0, McTextures.RED_SANDSTONE).find());
        horizontal(Blocks.SMOOTH_RED_SANDSTONE, "smooth_red_sandstone", XmPaint.finder().texture(0, McTextures.RED_SANDSTONE_TOP).find());
        horizontal(Blocks.SMOOTH_SANDSTONE, "smooth_sandstone", XmPaint.finder().texture(0, McTextures.SANDSTONE_TOP).find());
        horizontal(Blocks.STONE_BRICKS, "stone_brick", XmPaint.finder().texture(0, McTextures.STONE_BRICK).find());
        horizontal(Blocks.STONE, "stone", XmPaint.finder().texture(0, McTextures.STONE).find());
    }
    
    private static void horizontal(Block block, String name, XmPaint paint) {
        horizontal(block, name, paint, paint, paint);
    }
    
    private static void horizontal(final Block blockIn, String name, XmPaint paintBottom, XmPaint paintTop, XmPaint paintSide) {
        
        Block block = Xb.register(new StairLike(blockIn.getDefaultState(), Block.Settings.copy(blockIn), StairLike.Shape.STRAIGHT),
                name + "_wedge");
        XmBlockRegistry.addBlockStates(block, bs -> PrimitiveStateFunction.builder()
                .withDefaultState(StairLike.MODELSTATE_FROM_BLOCKSTATE.mutate(
                        Wedge.INSTANCE.newState()
                            .paint(Wedge.SURFACE_BOTTOM, paintBottom)
                            .paint(Wedge.SURFACE_BACK, paintSide)
                            .paint(Wedge.SURFACE_TOP, paintTop)
                            .paint(Wedge.SURFACE_SIDES, paintSide), bs))
                .build());
        
        block = Xb.register(new StairLike(blockIn.getDefaultState(), Block.Settings.copy(blockIn), StairLike.Shape.INSIDE_CORNER),
                name + "_wedge_inside");
        XmBlockRegistry.addBlockStates(block, bs -> PrimitiveStateFunction.builder()
                .withDefaultState(StairLike.MODELSTATE_FROM_BLOCKSTATE.mutate(
                        Wedge.INSTANCE.newState()
                            .paint(Wedge.SURFACE_BOTTOM, paintBottom)
                            .paint(Wedge.SURFACE_BACK, paintSide)
                            .paint(Wedge.SURFACE_TOP, paintTop)
                            .paint(Wedge.SURFACE_SIDES, paintSide), bs))
                .build());
        
        block = Xb.register(new StairLike(blockIn.getDefaultState(), Block.Settings.copy(blockIn), StairLike.Shape.OUTSIDE_CORNER),
                name + "_wedge_outside");
        XmBlockRegistry.addBlockStates(block, bs -> PrimitiveStateFunction.builder()
                .withDefaultState(StairLike.MODELSTATE_FROM_BLOCKSTATE.mutate(
                        Wedge.INSTANCE.newState()
                            .paint(Wedge.SURFACE_BOTTOM, paintBottom)
                            .paint(Wedge.SURFACE_BACK, paintSide)
                            .paint(Wedge.SURFACE_TOP, paintTop)
                            .paint(Wedge.SURFACE_SIDES, paintSide), bs))
                .build());
        
        block = Xb.register(new StairLike(blockIn.getDefaultState(), Block.Settings.copy(blockIn), StairLike.Shape.STRAIGHT),
                name + "_omni_stair");
        XmBlockRegistry.addBlockStates(block, bs -> PrimitiveStateFunction.builder()
                .withDefaultState(StairLike.MODELSTATE_FROM_BLOCKSTATE.mutate(
                        Stair.INSTANCE.newState()
                        .paint(Stair.SURFACE_BOTTOM, paintBottom)
                        .paint(Stair.SURFACE_BACK, paintSide)
                        .paint(Stair.SURFACE_TOP, paintTop)
                        .paint(Stair.SURFACE_FRONT, paintSide)
                        .paint(Stair.SURFACE_RIGHT, paintSide)
                        .paint(Stair.SURFACE_LEFT, paintSide), bs))
                .build());
        
        block = Xb.register(new StairLike(blockIn.getDefaultState(), Block.Settings.copy(blockIn), StairLike.Shape.INSIDE_CORNER),
                name + "_omni_stair_inside");
        XmBlockRegistry.addBlockStates(block, bs -> PrimitiveStateFunction.builder()
                .withDefaultState(StairLike.MODELSTATE_FROM_BLOCKSTATE.mutate(
                        Stair.INSTANCE.newState()
                        .paint(Stair.SURFACE_BOTTOM, paintBottom)
                        .paint(Stair.SURFACE_BACK, paintSide)
                        .paint(Stair.SURFACE_TOP, paintTop)
                        .paint(Stair.SURFACE_FRONT, paintSide)
                        .paint(Stair.SURFACE_RIGHT, paintSide)
                        .paint(Stair.SURFACE_LEFT, paintSide), bs))
                .build());
        
        block = Xb.register(new StairLike(blockIn.getDefaultState(), Block.Settings.copy(blockIn), StairLike.Shape.OUTSIDE_CORNER),
                name + "_omni_stair_outside");
        XmBlockRegistry.addBlockStates(block, bs -> PrimitiveStateFunction.builder()
                .withDefaultState(StairLike.MODELSTATE_FROM_BLOCKSTATE.mutate(
                        Stair.INSTANCE.newState()
                        .paint(Stair.SURFACE_BOTTOM, paintBottom)
                        .paint(Stair.SURFACE_BACK, paintSide)
                        .paint(Stair.SURFACE_TOP, paintTop)
                        .paint(Stair.SURFACE_FRONT, paintSide)
                        .paint(Stair.SURFACE_RIGHT, paintSide)
                        .paint(Stair.SURFACE_LEFT, paintSide), bs))
                .build());
        
    }
    
//    private static void horizontalStair(Block block, String name, XmPaint paintBack, XmPaint paintFront, XmPaint paintSide) {
//        
//        Block newBlock = Xb.register(
//                new HorizontalWedge.Straight(block.getDefaultState()), name + "_horizontal_stair");
//        
//        Function<BlockState, PrimitiveStateFunction> stateFunc = bs -> PrimitiveStateFunction.builder()
//                .withDefaultState(HorizontalWedge.STRAIGHT_MUTATOR.mutate(
//                        HorizontalStair.STRAIGHT.newState()
//                            .paint(HorizontalStair.SURFACE_BACK, paintBack)
//                            .paint(HorizontalStair.SURFACE_FRONT, paintFront)
//                            .paint(HorizontalStair.SURFACE_SIDE, paintSide),
//                            bs))
//                .withUpdate(HorizontalWedge.STRAIGHT_MUTATOR)
//                .build();
//        
//        XmBlockRegistry.addBlockStates(newBlock, stateFunc);
//    }
//    
//    private static void horizontalInsideCorner(Block block, String name, 
//            XmPaint paintBack, XmPaint paintFront, XmPaint paintSide) {
//        
//        Block newBlock = Xb.register(
//                new HorizontalWedge.Corner(block.getDefaultState()), name + "_horizontal_inside_corner");
//        
//        Function<BlockState, PrimitiveStateFunction> stateFunc = bs -> PrimitiveStateFunction.builder()
//                .withDefaultState(HorizontalWedge.CORNER_MUTATOR.mutate(
//                        HorizontalStair.INSIDE_CORNER.newState()
//                            .paint(HorizontalStair.SURFACE_BACK, paintBack)
//                            .paint(HorizontalStair.SURFACE_FRONT, paintFront)
//                            .paint(HorizontalStair.SURFACE_SIDE, paintSide),
//                            bs))
//                .withUpdate(HorizontalWedge.CORNER_MUTATOR)
//                .build();
//        
//        XmBlockRegistry.addBlockStates(newBlock, stateFunc);
//    }
//    
//    private static void horizontalOutsideCorner(Block block, String name, 
//            XmPaint paintBack, XmPaint paintFront, XmPaint paintSide) {
//        
//        Block newBlock = Xb.register(
//                new HorizontalWedge.Corner(block.getDefaultState()), name + "_horizontal_outside_corner");
//        
//        Function<BlockState, PrimitiveStateFunction> stateFunc = bs -> PrimitiveStateFunction.builder()
//                .withDefaultState(HorizontalWedge.CORNER_MUTATOR.mutate(
//                        HorizontalStair.OUTSIDE_CORNER.newState()
//                            .paint(HorizontalStair.SURFACE_BACK, paintBack)
//                            .paint(HorizontalStair.SURFACE_FRONT, paintFront)
//                            .paint(HorizontalStair.SURFACE_SIDE, paintSide),
//                            bs))
//                .withUpdate(HorizontalWedge.CORNER_MUTATOR)
//                .build();
//        
//        XmBlockRegistry.addBlockStates(newBlock, stateFunc);
//    }
}
