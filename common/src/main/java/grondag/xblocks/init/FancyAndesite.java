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
import grondag.xm.api.paint.XmPaint;
import grondag.xm.api.primitive.simple.Cube;
import grondag.xm.api.texture.XmTextures;

public abstract class FancyAndesite {
	private FancyAndesite() { }

	public static void initialize() {
		final XmPaint mainPaint = XmPaint.finder()
				.textureDepth(2)
				.texture(0, XmTextures.TILE_NOISE_MODERATE)
				.textureColor(0, 0xFF6f7474)
				.texture(1, XmTextures.TILE_NOISE_BLUE_A)
				.textureColor(1, 0xFFb6bcbc)
				.find();

		final XmPaint connectedPaint = XmPaint.finder()
				.textureDepth(3)
				.texture(0, XmTextures.TILE_NOISE_MODERATE)
				.textureColor(0, 0xFF6f7474)
				.texture(1, XmTextures.TILE_NOISE_BLUE_A)
				.textureColor(1, 0xFFb6bcbc)
				.texture(2, XmTextures.BORDER_SINGLE_LINE)
				.textureColor(2, 0xA0b6bcbc)
				.find();

		final Block block = Xb.REG.block(BlockNames.BLOCK_FANCY_ANDESITE, new Block(Block.Properties.copy(Blocks.ANDESITE)));
		XmBlockRegistry.addBlock(block, PrimitiveStateFunction.ofDefaultState(
				Cube.INSTANCE.newState()
				.paintAll(mainPaint)
				.releaseToImmutable()));

		SpeciesBlock.species(block, BlockNames.BLOCK_CONNECTED_FANCY_ANDESITE, connectedPaint);
		ShapedBlockRegistrator.registerShapes(block, ShapedBlockNames.SHAPED_FANCY_ANDESITE, mainPaint, false);
	}
}
