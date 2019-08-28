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

import grondag.fermion.color.ColorHelper;
import grondag.fermion.modkeys.api.ModKeys;
import grondag.xblocks.Xb;
import grondag.xm.api.block.XmBlockRegistry;
import grondag.xm.api.connect.species.Species;
import grondag.xm.api.connect.species.SpeciesFunction;
import grondag.xm.api.connect.species.SpeciesMode;
import grondag.xm.api.connect.species.SpeciesProperty;
import grondag.xm.api.modelstate.primitive.WorldToPrimitiveStateMap;
import grondag.xm.api.paint.VertexProcessor;
import grondag.xm.api.paint.XmPaint;
import grondag.xm.api.primitive.simple.Cube;
import grondag.xm.api.texture.XmTextures;
import it.unimi.dsi.fastutil.HashCommon;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderLayer;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateFactory.Builder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

/**
 * Example of varying connected textures via a species attribute.
 */
public class SpeciesBlocks {
    public static final VertexProcessor SPECIES_VARIATION = (poly, modelState, surface, paint, textureIndex) -> {
        final int mix = HashCommon.mix(modelState.species());
        final int value = mix & 0xF;
        final int mixColor = 0xFFFFFFFF - (mix & 0x0F0000) - value - (value << 8); 
        int color = ColorHelper.multiplyColor(mixColor, paint.textureColor(textureIndex));
        for (int i = 0; i < poly.vertexCount(); i++) {
            final int c = ColorHelper.multiplyColor(color, poly.color(i, textureIndex));
            poly.color(i, textureIndex, c);
        }
    };
    
    public static final XmPaint VARIED_WITH_BORDER = XmPaint.finder()
            .textureDepth(3)
            .texture(0, XmTextures.BIGTEX_SANDSTONE)
            .vertexProcessor(0, SPECIES_VARIATION)
            .textureColor(0, 0xFF9090A0)
            .texture(1, XmTextures.TILE_NOISE_BLUE_A)
            .textureColor(1, 0x803B5303)
            .blendMode(1, BlockRenderLayer.TRANSLUCENT)
            .texture(2, XmTextures.BORDER_SMOOTH_BLEND)
            .textureColor(2, 0x90303535)
            .blendMode(2, BlockRenderLayer.TRANSLUCENT)
            .find();

    
    public static void init() {
        
        Block block = new Block(Block.Settings.copy(Blocks.STONE)) {
            @Override
            protected void appendProperties(Builder<Block, BlockState> builder) {
                super.appendProperties(builder);
                builder.add(SpeciesProperty.SPECIES);
            }
            
            private final SpeciesFunction speciesFunc = SpeciesProperty.speciesForBlock(this);
            
            @Override
            public BlockState getPlacementState(ItemPlacementContext context) {
                final Direction onFace = context.getSide();
                final BlockPos onPos = context.getBlockPos().offset(onFace.getOpposite());
                final SpeciesMode mode = (context.isPlayerSneaking() || ModKeys.isSuperPressed(context.getPlayer()))
                        ? SpeciesMode.COUNTER_MOST : SpeciesMode.MATCH_MOST;
                final int species = Species.speciesForPlacement(context.getWorld(), onPos, onFace, mode, speciesFunc);
                return this.getDefaultState().with(SpeciesProperty.SPECIES, species);
            }
            
        };
        
        Xb.register(block, "species_test");
        
        XmBlockRegistry.addBlockStates(block, bs -> WorldToPrimitiveStateMap.builder()
                .withJoin(SpeciesProperty.matchBlockAndSpecies())
                .withUpdate(SpeciesProperty.SPECIES_MODIFIER)
                .withDefaultState(SpeciesProperty.SPECIES_MODIFIER.apply(
                        Cube.INSTANCE.newState()
                        .paintAll(VARIED_WITH_BORDER), bs))
                    .build());
    }
}
