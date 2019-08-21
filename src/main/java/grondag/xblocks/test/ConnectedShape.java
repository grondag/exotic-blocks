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

import java.util.function.Function;

import grondag.xblocks.XB;
import grondag.xm.api.block.XmBlockRegistry;
import grondag.xm.api.connect.world.BlockTest;
import grondag.xm.api.modelstate.SimpleModelState;
import grondag.xm.api.modelstate.WorldToSimpleModelState;
import grondag.xm.api.paint.XmPaint;
import grondag.xm.api.primitive.simple.SquareColumn;
import grondag.xm.api.texture.XmTextures;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderLayer;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.block.PillarBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

/**
 * Test of square column primitive, setting primitive parameters, connected shapes
 */
public class ConnectedShape {
    public static void init() {
        final SimpleModelState defaultState = SquareColumn.INSTANCE.newState()
                .paint(SquareColumn.SURFACE_MAIN, XmPaint.finder()
                        .textureDepth(2)
                        .texture(0, XmTextures.BIGTEX_SANDSTONE)
                        .textureColor(0, 0xFF99BBAA)
                        .texture(1, XmTextures.BORDER_GRITTY_SINGLE_LINE)
                        .blendMode(1, BlockRenderLayer.TRANSLUCENT)
                        .textureColor(1, 0xFF709080)
                        .find())
                .paint(SquareColumn.SURFACE_CUT, XmPaint.finder()
                        .texture(0, XmTextures.BIGTEX_SANDSTONE)
                        .textureColor(0, 0xFF99BBAA)
                        .find())
                .paint(SquareColumn.SURFACE_LAMP, XmPaint.finder()
                        .disableAo(0, true)
                        .disableDiffuse(0, true)
                        .emissive(0, true)
                        .texture(0, XmTextures.WHITE)
                        .textureColor(0, 0xFFDDFFFF)
                        .find())
                .apply(s -> { 
                        SquareColumn.setCutCount(3, s);
                        SquareColumn.setCutsOnEdge(false, s);})
                .releaseToImmutable();

        final Block column = new PillarBlock(FabricBlockSettings.of(Material.STONE).build());
        Identifier id = XB.id("square_column");
        Registry.BLOCK.add(id, column);
        Registry.ITEM.add(id, new BlockItem(column, new Item.Settings().maxCount(64).group(ItemGroup.BUILDING_BLOCKS)));
        
        BlockTest<SimpleModelState> joinFunc = ctx -> {
            final BlockState fromBlock = ctx.fromBlockState();
            final BlockState toBlock = ctx.toBlockState();
            return fromBlock.getBlock() == toBlock.getBlock()
                    && fromBlock.contains(PillarBlock.AXIS)
                    && fromBlock.get(PillarBlock.AXIS) == toBlock.get(PillarBlock.AXIS);};
        
        final Function<BlockState, WorldToSimpleModelState> stateFunc = bs -> WorldToSimpleModelState.builder()
                    .withDefaultState(SimpleModelState.AXIS_FROM_BLOCKSTATE.apply(defaultState.mutableCopy(), bs))
                    .withJoin(joinFunc)
                    .build();
        
        XmBlockRegistry.addBlockStates(column, stateFunc);
    }
}
