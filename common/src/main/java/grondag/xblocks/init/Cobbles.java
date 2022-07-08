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

import net.minecraft.world.level.block.Blocks;

import grondag.xblocks.block.SpeciesBlock;
import grondag.xblocks.data.BlockNames;
import grondag.xm.api.paint.XmPaint;
import grondag.xm.api.texture.core.CoreTextures;

public abstract class Cobbles {
	private Cobbles() { }

	public static void initialize() {
		final XmPaint connectedPaint = XmPaint.finder()
				.textureDepth(2)
				.texture(0, CoreTextures.BIGTEX_COBBLE_SQUARES)
				.textureColor(0, 0xFF949698)
				.texture(1, CoreTextures.BORDER_COBBLE)
				.textureColor(1, 0xFF949698)
				.find();

		SpeciesBlock.species(Blocks.COBBLESTONE, BlockNames.BLOCK_COBBLE_SMALL_SQUARE, connectedPaint);
	}
}