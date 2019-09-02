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
import grondag.xm.api.texture.TextureSet;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

public class StoneBlocks {

    public static void init() {
        //TODO: wood after woodcutter available
//        acacia_stairs.json
//        birch_stairs.json
//        dark_oak_stairs.json
//        jungle_stairs.json
//        oak_stairs.json
//        spruce_stairs.json
        
        register(Blocks.TERRACOTTA, "terracotta", McTextures.TERRACOTTA);
        register(Blocks.WHITE_TERRACOTTA, "white_terracotta", McTextures.WHITE_TERRACOTTA);
        register(Blocks.LIGHT_GRAY_TERRACOTTA, "light_gray_terracotta", McTextures.LIGHT_GRAY_TERRACOTTA);
        register(Blocks.GRAY_TERRACOTTA, "gray_terracotta", McTextures.GRAY_TERRACOTTA);
        register(Blocks.BLACK_TERRACOTTA, "black_terracotta", McTextures.BLACK_TERRACOTTA);
        register(Blocks.RED_TERRACOTTA, "red_terracotta", McTextures.RED_TERRACOTTA);
        register(Blocks.BROWN_TERRACOTTA, "brown_terracotta", McTextures.BROWN_TERRACOTTA);
        register(Blocks.ORANGE_TERRACOTTA, "orange_terracotta", McTextures.ORANGE_TERRACOTTA);
        register(Blocks.YELLOW_TERRACOTTA, "yellow_terracotta", McTextures.YELLOW_TERRACOTTA);
        register(Blocks.LIME_TERRACOTTA, "lime_terracotta", McTextures.LIME_TERRACOTTA);
        register(Blocks.GREEN_TERRACOTTA, "green_terracotta", McTextures.GREEN_TERRACOTTA);
        register(Blocks.CYAN_TERRACOTTA, "cyan_terracotta", McTextures.CYAN_TERRACOTTA);
        register(Blocks.LIGHT_BLUE_TERRACOTTA, "light_blue_terracotta", McTextures.LIGHT_BLUE_TERRACOTTA);
        register(Blocks.BLUE_TERRACOTTA, "blue_terracotta", McTextures.BLUE_TERRACOTTA);
        register(Blocks.PINK_TERRACOTTA, "pink_terracotta", McTextures.PINK_TERRACOTTA);
        register(Blocks.MAGENTA_TERRACOTTA, "magenta_terracotta", McTextures.MAGENTA_TERRACOTTA);
        register(Blocks.PURPLE_TERRACOTTA, "purple_terracotta", McTextures.PURPLE_TERRACOTTA);

        register(Blocks.WHITE_CONCRETE, "white_concrete", McTextures.WHITE_CONCRETE);
        register(Blocks.LIGHT_GRAY_CONCRETE, "light_gray_concrete", McTextures.LIGHT_GRAY_CONCRETE);
        register(Blocks.GRAY_CONCRETE, "gray_concrete", McTextures.GRAY_CONCRETE);
        register(Blocks.BLACK_CONCRETE, "black_concrete", McTextures.BLACK_CONCRETE);
        register(Blocks.RED_CONCRETE, "red_concrete", McTextures.RED_CONCRETE);
        register(Blocks.BROWN_CONCRETE, "brown_concrete", McTextures.BROWN_CONCRETE);
        register(Blocks.ORANGE_CONCRETE, "orange_concrete", McTextures.ORANGE_CONCRETE);
        register(Blocks.YELLOW_CONCRETE, "yellow_concrete", McTextures.YELLOW_CONCRETE);
        register(Blocks.LIME_CONCRETE, "lime_concrete", McTextures.LIME_CONCRETE);
        register(Blocks.GREEN_CONCRETE, "green_concrete", McTextures.GREEN_CONCRETE);
        register(Blocks.CYAN_CONCRETE, "cyan_concrete", McTextures.CYAN_CONCRETE);
        register(Blocks.LIGHT_BLUE_CONCRETE, "light_blue_concrete", McTextures.LIGHT_BLUE_CONCRETE);
        register(Blocks.BLUE_CONCRETE, "blue_concrete", McTextures.BLUE_CONCRETE);
        register(Blocks.PINK_CONCRETE, "pink_concrete", McTextures.PINK_CONCRETE);
        register(Blocks.MAGENTA_CONCRETE, "magenta_concrete", McTextures.MAGENTA_CONCRETE);
        register(Blocks.PURPLE_CONCRETE, "purple_concrete", McTextures.PURPLE_CONCRETE);
        
        register(Blocks.STONE, "stone", McTextures.STONE);
        register(Blocks.STONE_BRICKS, "stone_bricks", McTextures.STONE_BRICK);
        register(Blocks.MOSSY_STONE_BRICKS, "mossy_stone_bricks", McTextures.MOSSY_STONE_BRICK);
        register(Blocks.COBBLESTONE, "cobblestone", McTextures.COBBLESTONE);
        register(Blocks.MOSSY_COBBLESTONE, "mossy_cobblestone", McTextures.MOSSY_COBBLESTONE);

        register(Blocks.ANDESITE, "andesite", McTextures.ANDESITE);
        register(Blocks.POLISHED_ANDESITE, "polished_andesite", McTextures.POLISHED_ANDESITE);
        register(Blocks.DIORITE, "diorite", McTextures.DIORITE);
        register(Blocks.POLISHED_DIORITE, "polished_diorite", McTextures.POLISHED_DIORITE);
        register(Blocks.GRANITE, "granite", McTextures.GRANITE);
        register(Blocks.POLISHED_GRANITE, "polished_granite", McTextures.POLISHED_GRANITE);

        register(Blocks.BRICKS, "bricks", McTextures.BRICK);
        register(Blocks.SANDSTONE, "sandstone", 
                XmPaint.finder().texture(0, McTextures.SANDSTONE_BOTTOM).find(),
                XmPaint.finder().texture(0, McTextures.SANDSTONE_TOP).find(),
                XmPaint.finder().texture(0, McTextures.SANDSTONE).find());
        register(Blocks.SMOOTH_SANDSTONE, "smooth_sandstone", McTextures.SANDSTONE_TOP);
        register(Blocks.RED_SANDSTONE, "red_sandstone", 
                XmPaint.finder().texture(0, McTextures.RED_SANDSTONE_BOTTOM).find(),
                XmPaint.finder().texture(0, McTextures.RED_SANDSTONE_TOP).find(),
                XmPaint.finder().texture(0, McTextures.RED_SANDSTONE).find());
        register(Blocks.SMOOTH_RED_SANDSTONE, "smooth_red_sandstone", McTextures.RED_SANDSTONE_TOP);

        register(Blocks.PRISMARINE, "prismarine", McTextures.PRISMARINE);
        register(Blocks.PRISMARINE_BRICKS, "prismarine_brick", McTextures.PRISMARINE_BRICK);
        register(Blocks.DARK_PRISMARINE, "dark_prismarine", McTextures.DARK_PRISMARINE);

        register(Blocks.NETHER_BRICKS, "nether_bricks", McTextures.NETHER_BRICK);
        register(Blocks.RED_NETHER_BRICKS, "red_nether_bricks", McTextures.RED_NETHER_BRICK);
        register(Blocks.SMOOTH_QUARTZ, "smooth_quartz", McTextures.QUARTZ_BLOCK_BOTTOM);
        register(Blocks.QUARTZ_BLOCK, "quartz", 
                XmPaint.finder().texture(0, McTextures.QUARTZ_BLOCK_BOTTOM).find(),
                XmPaint.finder().texture(0, McTextures.QUARTZ_BLOCK_TOP).find(),
                XmPaint.finder().texture(0, McTextures.QUARTZ_BLOCK_SIDE).find());

        register(Blocks.END_STONE_BRICKS, "end_stone_bricks", McTextures.END_STONE_BRICK);
        register(Blocks.PURPUR_BLOCK, "purpur_block", McTextures.PURPUR_BLOCK);
    }
    
    private static void register(Block block, String name, TextureSet tex) {
        final XmPaint paint = XmPaint.finder().texture(0, tex).find();
        register(block, name, paint, paint, paint);
    }
    
    private static void register(final Block blockIn, String name, XmPaint paintBottom, XmPaint paintTop, XmPaint paintSide) {
        
        Block block = Xb.register(new StairLike(blockIn.getDefaultState(), Block.Settings.copy(blockIn), StairLike.Shape.STRAIGHT),
                name + "_wedge_straight");
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
}
