package grondag.xblocks.init;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

import grondag.xblocks.Xb;
import grondag.xblocks.block.ShapedBlockRegistrator;
import grondag.xblocks.block.SpeciesBlock;
import grondag.xblocks.data.BlockNames;
import grondag.xblocks.data.ShapedBlockNames;
import grondag.xm.api.block.XmBlockRegistry;
import grondag.xm.api.modelstate.primitive.PrimitiveStateFunction;
import grondag.xm.api.paint.XmPaintRegistry;
import grondag.xm.api.primitive.simple.Cube;

public enum SimpleBlocks {
	;

	static {
		final Block oldStone = simpleBlock(Blocks.STONE, BlockNames.BLOCK_OLD_STONE, "old_stone");
		ShapedBlockRegistrator.registerShapes(oldStone, ShapedBlockNames.SHAPED_OLD_STONE, "old_stone", false);
		SpeciesBlock.species(oldStone, BlockNames.BLOCK_OLD_STONE_MEGABRICK, XmPaintRegistry.INSTANCE.get(Xb.REG.id("old_stone_megabricks")));

		final Block ancientStone = simpleBlock(Blocks.STONE, BlockNames.BLOCK_ANCIENT_STONE, "ancient_stone");
		ShapedBlockRegistrator.registerShapes(ancientStone, ShapedBlockNames.SHAPED_ANCIENT_STONE, "ancient_stone", false);
		SpeciesBlock.species(ancientStone, BlockNames.BLOCK_ANCIENT_STONE_MEGABRICK, XmPaintRegistry.INSTANCE.get(Xb.REG.id("ancient_stone_megabricks")));

		final Block blackIron = simpleBlock(Blocks.IRON_BLOCK, BlockNames.BLOCK_BLACK_IRON, "black_iron");
		ShapedBlockRegistrator.registerShapes(blackIron, ShapedBlockNames.SHAPED_BLACK_IRON, "black_iron", false);
		SpeciesBlock.species(blackIron, BlockNames.BLOCK_BLACK_IRON_PLATE, XmPaintRegistry.INSTANCE.get(Xb.REG.id("black_iron_plate")));

		final Block oldBlackIron = simpleBlock(Blocks.IRON_BLOCK, BlockNames.BLOCK_OLD_BLACK_IRON, "old_black_iron");
		ShapedBlockRegistrator.registerShapes(oldBlackIron, ShapedBlockNames.SHAPED_OLD_BLACK_IRON, "old_black_iron", false);
		SpeciesBlock.species(oldBlackIron, BlockNames.BLOCK_OLD_BLACK_IRON_PLATE, XmPaintRegistry.INSTANCE.get(Xb.REG.id("old_black_iron_plate")));

		final Block rustyBlackIron = simpleBlock(Blocks.IRON_BLOCK, BlockNames.BLOCK_RUSTY_BLACK_IRON, "rusty_black_iron");
		ShapedBlockRegistrator.registerShapes(rustyBlackIron, ShapedBlockNames.SHAPED_RUSTY_BLACK_IRON, "rusty_black_iron", false);
		SpeciesBlock.species(rustyBlackIron, BlockNames.BLOCK_RUSTY_BLACK_IRON_PLATE, XmPaintRegistry.INSTANCE.get(Xb.REG.id("rusty_black_iron_plate")));
	}

	public static Block simpleBlock(Block template, String mainName, String paintName) {
		final Block block = Xb.REG.block(mainName, new Block(Block.Settings.copy(template)));

		XmBlockRegistry.addBlock(block, PrimitiveStateFunction.ofDefaultState(
				Cube.INSTANCE.newState()
				.paintAll(XmPaintRegistry.INSTANCE.get(Xb.REG.id(paintName)))
				.releaseToImmutable()));


		return block;
	}
}
