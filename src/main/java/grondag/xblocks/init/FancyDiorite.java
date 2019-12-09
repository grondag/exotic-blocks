package grondag.xblocks.init;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

import grondag.xblocks.Xb;
import grondag.xblocks.block.BlockRegistrator;
import grondag.xblocks.block.SpeciesBlock;
import grondag.xm.api.block.XmBlockRegistry;
import grondag.xm.api.modelstate.primitive.PrimitiveStateFunction;
import grondag.xm.api.paint.PaintBlendMode;
import grondag.xm.api.paint.XmPaint;
import grondag.xm.api.primitive.simple.Cube;
import grondag.xm.api.texture.XmTextures;

public enum FancyDiorite {
	;

	static final String ID = "fancy_diorite";
	static final String ID_BLOCK = ID + "_block";

	static {

		final XmPaint mainPaint = XmPaint.finder()
				.textureDepth(2)
				.texture(0, XmTextures.TILE_NOISE_MODERATE)
				.textureColor(0, 0xFFe8e6e9)
				.texture(1, XmTextures.TILE_NOISE_BLUE_B)
				.textureColor(1, 0xFF2d2d2d)
				.blendMode(1, PaintBlendMode.TRANSLUCENT)
				.find();

		final XmPaint connectedPaint = XmPaint.finder()
				.textureDepth(3)
				.texture(0, XmTextures.TILE_NOISE_MODERATE)
				.textureColor(0, 0xFFe8e6e9)
				.texture(1, XmTextures.TILE_NOISE_BLUE_B)
				.textureColor(1, 0xFF2d2d2d)
				.blendMode(1, PaintBlendMode.TRANSLUCENT)
				.texture(2, XmTextures.BORDER_SINGLE_LINE)
				.textureColor(2, 0xA08a8581)
				.blendMode(2, PaintBlendMode.TRANSLUCENT)
				.find();

		final Block block = Xb.REG.block(ID_BLOCK, new Block(Block.Settings.copy(Blocks.ANDESITE)));
		XmBlockRegistry.addBlock(block, PrimitiveStateFunction.ofDefaultState(
				Cube.INSTANCE.newState()
				.paintAll(mainPaint)
				.releaseToImmutable()));

		BlockRegistrator.register(block, ID_BLOCK, mainPaint, false);

		SpeciesBlock.species(block, ID_BLOCK + "_species", connectedPaint);
	}
}
