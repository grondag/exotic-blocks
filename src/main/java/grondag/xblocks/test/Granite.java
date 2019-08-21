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

import grondag.xblocks.XB;
import grondag.xm.api.block.XmBlockRegistry;
import grondag.xm.api.block.base.StairLike;
import grondag.xm.api.modelstate.WorldToSimpleModelState;
import grondag.xm.api.paint.XmPaint;
import grondag.xm.api.primitive.simple.Cube;
import grondag.xm.api.primitive.simple.Sphere;
import grondag.xm.api.primitive.simple.Wedge;
import grondag.xm.api.texture.XmTextures;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderLayer;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

/**
 * Example of multi-layer texture plus remodeling and adding new vanilla-like blocks,
 */
public class Granite {
    
    public static final XmPaint GRANITE_RAW = XmPaint.finder()
            .textureDepth(2)
            .texture(0, XmTextures.TILE_NOISE_STRONG)
            .textureColor(0, 0xFF936655)
            .texture(1, XmTextures.TILE_NOISE_BLUE_A)
            .textureColor(1, 0x50FFEEDD)
            .blendMode(1, BlockRenderLayer.TRANSLUCENT)
            .find();
    
    public static final XmPaint GRANITE_POLISHED = XmPaint.finder()
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
    
    public static void init() {
        
        XmBlockRegistry.addBlock(Blocks.GRANITE, WorldToSimpleModelState.ofDefaultState(
                Cube.INSTANCE.newState()
                    .paintAll(GRANITE_RAW)
                    .releaseToImmutable()));
        
        XmBlockRegistry.addBlock(Blocks.POLISHED_GRANITE, WorldToSimpleModelState.ofDefaultState(
                Cube.INSTANCE.newState()
                    .paintAll(GRANITE_POLISHED)
                    .releaseToImmutable()));

        Block block;
        
        block = register(StairLike.ofAxisY(Blocks.GRANITE.getDefaultState(), Block.Settings.copy(Blocks.GRANITE), Wedge.INSTANCE::newState),
                "granite_wedge_y");
        XmBlockRegistry.addBlockStates(block, bs -> WorldToSimpleModelState.builder()
                .withDefaultState(StairLike.MODELSTATE_FROM_BLOCKSTATE.apply(
                        Wedge.INSTANCE.newState().paintAll(GRANITE_RAW), bs))
                .build());
        
        block = register(StairLike.ofAxisX(Blocks.GRANITE.getDefaultState(), Block.Settings.copy(Blocks.GRANITE), Wedge.INSTANCE::newState),
                "granite_wedge_x");
        XmBlockRegistry.addBlockStates(block, bs -> WorldToSimpleModelState.builder()
                .withDefaultState(StairLike.MODELSTATE_FROM_BLOCKSTATE.apply(
                        Wedge.INSTANCE.newState().paintAll(GRANITE_RAW), bs))
                .build());
        
        block = register(StairLike.ofAxisY(Blocks.GRANITE.getDefaultState(), Block.Settings.copy(Blocks.GRANITE), Wedge.INSTANCE::newState),
                "granite_wedge_z");
        XmBlockRegistry.addBlockStates(block, bs -> WorldToSimpleModelState.builder()
                .withDefaultState(StairLike.MODELSTATE_FROM_BLOCKSTATE.apply(
                        Wedge.INSTANCE.newState().paintAll(GRANITE_RAW), bs))
                .build());
        
        
        block = register(StairLike.ofAxisY(Blocks.POLISHED_GRANITE.getDefaultState(), Block.Settings.copy(Blocks.POLISHED_GRANITE), Wedge.INSTANCE::newState),
                "polished_granite_wedge_y");
        XmBlockRegistry.addBlockStates(block, bs -> WorldToSimpleModelState.builder()
                .withDefaultState(StairLike.MODELSTATE_FROM_BLOCKSTATE.apply(
                        Wedge.INSTANCE.newState().paintAll(GRANITE_POLISHED), bs))
                .build());
        
        block = register(StairLike.ofAxisX(Blocks.POLISHED_GRANITE.getDefaultState(), Block.Settings.copy(Blocks.POLISHED_GRANITE), Wedge.INSTANCE::newState),
                "polished_granite_wedge_x");
        XmBlockRegistry.addBlockStates(block, bs -> WorldToSimpleModelState.builder()
                .withDefaultState(StairLike.MODELSTATE_FROM_BLOCKSTATE.apply(
                        Wedge.INSTANCE.newState().paintAll(GRANITE_POLISHED), bs))
                .build());
        
        block = register(StairLike.ofAxisY(Blocks.POLISHED_GRANITE.getDefaultState(), Block.Settings.copy(Blocks.POLISHED_GRANITE), Wedge.INSTANCE::newState),
                "polished_granite_wedge_z");
        XmBlockRegistry.addBlockStates(block, bs -> WorldToSimpleModelState.builder()
                .withDefaultState(StairLike.MODELSTATE_FROM_BLOCKSTATE.apply(
                        Wedge.INSTANCE.newState().paintAll(GRANITE_POLISHED), bs))
                .build());
        
        
        block = register(new Block(Block.Settings.copy(Blocks.GRANITE)), "granite_dodec");
        XmBlockRegistry.addBlock(block, WorldToSimpleModelState.ofDefaultState(
                Sphere.INSTANCE.newState()
                    .paintAll(GRANITE_RAW)
                    .releaseToImmutable()));
        
        block = register(new Block(Block.Settings.copy(Blocks.POLISHED_GRANITE)), "polished_granite_dodec");
        XmBlockRegistry.addBlock(block, WorldToSimpleModelState.ofDefaultState(
                Sphere.INSTANCE.newState()
                    .paintAll(GRANITE_POLISHED)
                    .releaseToImmutable()));
        
    }
    
    private static Block register(Block block, String idString) {
        Identifier id = XB.id(idString);
        Registry.BLOCK.add(id, block);
        Registry.ITEM.add(id, new BlockItem(block, new Item.Settings().maxCount(64).group(ItemGroup.BUILDING_BLOCKS)));
        return block;
    }
}
