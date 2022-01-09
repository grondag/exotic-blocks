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
import grondag.xm.api.paint.XmPaintRegistry;
import grondag.xm.api.primitive.simple.Cube;

public abstract class SimpleBlocks {
	private SimpleBlocks() { }

	public static void initialize() {
		final Block oldStone = simpleBlock(Blocks.STONE, BlockNames.BLOCK_OLD_STONE, "old_stone");
		ShapedBlockRegistrator.registerShapes(oldStone, ShapedBlockNames.SHAPED_OLD_STONE, "old_stone", false);
		SpeciesBlock.species(oldStone, BlockNames.BLOCK_OLD_STONE_MEGABRICK, XmPaintRegistry.INSTANCE.get(Xb.id("old_stone_megabricks")));

		final Block ancientStone = simpleBlock(Blocks.STONE, BlockNames.BLOCK_ANCIENT_STONE, "ancient_stone");
		ShapedBlockRegistrator.registerShapes(ancientStone, ShapedBlockNames.SHAPED_ANCIENT_STONE, "ancient_stone", false);
		SpeciesBlock.species(ancientStone, BlockNames.BLOCK_ANCIENT_STONE_MEGABRICK, XmPaintRegistry.INSTANCE.get(Xb.id("ancient_stone_megabricks")));

		final Block blackIron = simpleBlock(Blocks.IRON_BLOCK, BlockNames.BLOCK_BLACK_IRON, "black_iron");
		ShapedBlockRegistrator.registerShapes(blackIron, ShapedBlockNames.SHAPED_BLACK_IRON, "black_iron", false);
		SpeciesBlock.species(blackIron, BlockNames.BLOCK_BLACK_IRON_PLATE, XmPaintRegistry.INSTANCE.get(Xb.id("black_iron_plate")));

		final Block oldBlackIron = simpleBlock(Blocks.IRON_BLOCK, BlockNames.BLOCK_OLD_BLACK_IRON, "old_black_iron");
		ShapedBlockRegistrator.registerShapes(oldBlackIron, ShapedBlockNames.SHAPED_OLD_BLACK_IRON, "old_black_iron", false);
		SpeciesBlock.species(oldBlackIron, BlockNames.BLOCK_OLD_BLACK_IRON_PLATE, XmPaintRegistry.INSTANCE.get(Xb.id("old_black_iron_plate")));

		final Block rustyBlackIron = simpleBlock(Blocks.IRON_BLOCK, BlockNames.BLOCK_RUSTY_BLACK_IRON, "rusty_black_iron");
		ShapedBlockRegistrator.registerShapes(rustyBlackIron, ShapedBlockNames.SHAPED_RUSTY_BLACK_IRON, "rusty_black_iron", false);
		SpeciesBlock.species(rustyBlackIron, BlockNames.BLOCK_RUSTY_BLACK_IRON_PLATE, XmPaintRegistry.INSTANCE.get(Xb.id("rusty_black_iron_plate")));
	}

	public static Block simpleBlock(Block template, String mainName, String paintName) {
		final Block block = Xb.block(mainName, new Block(Block.Properties.copy(template)));

		XmBlockRegistry.addBlock(block, PrimitiveStateFunction.ofDefaultState(
				Cube.INSTANCE.newState()
				.paintAll(XmPaintRegistry.INSTANCE.get(Xb.id(paintName)))
				.releaseToImmutable()));

		return block;
	}
}
