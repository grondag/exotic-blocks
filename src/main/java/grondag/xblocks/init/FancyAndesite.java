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
import grondag.xm.api.paint.PaintBlendMode;
import grondag.xm.api.paint.XmPaint;
import grondag.xm.api.primitive.simple.Cube;
import grondag.xm.api.texture.XmTextures;

public enum FancyAndesite {
	;

	static {
		final XmPaint mainPaint = XmPaint.finder()
				.textureDepth(2)
				.texture(0, XmTextures.TILE_NOISE_MODERATE)
				.textureColor(0, 0xFF6f7474)
				.texture(1, XmTextures.TILE_NOISE_BLUE_A)
				.textureColor(1, 0xFFb6bcbc)
				.blendMode(1, PaintBlendMode.TRANSLUCENT)
				.find();

		final XmPaint connectedPaint = XmPaint.finder()
				.textureDepth(3)
				.texture(0, XmTextures.TILE_NOISE_MODERATE)
				.textureColor(0, 0xFF6f7474)
				.texture(1, XmTextures.TILE_NOISE_BLUE_A)
				.textureColor(1, 0xFFb6bcbc)
				.blendMode(1, PaintBlendMode.TRANSLUCENT)
				.texture(2, XmTextures.BORDER_SINGLE_LINE)
				.textureColor(2, 0xA0b6bcbc)
				.blendMode(2, PaintBlendMode.TRANSLUCENT)
				.find();

		final Block block = Xb.REG.block(BlockNames.BLOCK_FANCY_ANDESITE, new Block(Block.Settings.copy(Blocks.ANDESITE)));
		XmBlockRegistry.addBlock(block, PrimitiveStateFunction.ofDefaultState(
				Cube.INSTANCE.newState()
				.paintAll(mainPaint)
				.releaseToImmutable()));

		SpeciesBlock.species(block, BlockNames.BLOCK_CONNECTED_FANCY_ANDESITE, connectedPaint);
		ShapedBlockRegistrator.registerShapes(block, ShapedBlockNames.SHAPED_FANCY_ANDESITE, mainPaint, false);
	}
}
