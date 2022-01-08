/*
 * This file is part of Exotic Blocks and is licensed to the project under
 * terms that are compatible with the GNU Lesser General Public License.
 * See the NOTICE file distributed with this work for additional information
 * regarding copyright ownership and licensing.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package grondag.xblocks.init;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import grondag.xblocks.Xb;
import grondag.xblocks.block.ShapedBlockRegistrator;
import grondag.xblocks.block.SpeciesBlock;
import grondag.xblocks.data.BlockNames;
import grondag.xblocks.data.ShapedBlockNames;
import grondag.xm.api.block.XmBlockRegistry;
import grondag.xm.api.modelstate.primitive.PrimitiveStateFunction;
import grondag.xm.api.paint.VertexProcessors;
import grondag.xm.api.paint.XmPaint;
import grondag.xm.api.primitive.simple.Cube;
import grondag.xm.api.texture.core.CoreTextures;

public abstract class RammedEarth {
	private RammedEarth() { }

	public static void initialize() {
		final XmPaint mainPaint = XmPaint.finder()
				.textureDepth(1)
				.texture(0, CoreTextures.BIGTEX_RAMMED_EARTH)
				.textureColor(0, 0xFF875e47)
				.find();

		final XmPaint connectedPaint = XmPaint.finder()
				.textureDepth(2)
				.texture(0, CoreTextures.BIGTEX_RAMMED_EARTH)
				.textureColor(0, 0xFF875e47)
				.vertexProcessor(0, VertexProcessors.SPECIES_VARIATION)
				.texture(1, CoreTextures.BORDER_WEATHERED_BLEND)
				.textureColor(1, 0xB0402a1e)
				.find();

		final Block block = Xb.REG.block(BlockNames.BLOCK_RAMMED_EARTH, new Block(Block.Properties.copy(Blocks.TERRACOTTA)));

		XmBlockRegistry.addBlock(block, PrimitiveStateFunction.ofDefaultState(
				Cube.INSTANCE.newState()
				.paintAll(mainPaint)
				.releaseToImmutable()));

		SpeciesBlock.species(block, BlockNames.BLOCK_CONNECTED_RAMMED_EARTH, connectedPaint);
		ShapedBlockRegistrator.registerShapes(block, ShapedBlockNames.SHAPED_RAMMED_EARTH, mainPaint, false);
	}
}
