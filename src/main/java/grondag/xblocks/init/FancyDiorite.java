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
				.texture(0, XmTextures.BIGTEX_GRANITE)
				.textureColor(0, 0xFFFFFFFF)
				.texture(1, XmTextures.TILE_NOISE_BLUE)
				.textureColor(1, 0x40000000)
				.blendMode(1, PaintBlendMode.TRANSLUCENT)
				.find();

		final XmPaint connectedPaint = XmPaint.finder()
				.textureDepth(3)
				.texture(0, XmTextures.BIGTEX_GRANITE)
				.textureColor(0, 0xFFFFFFFF)
				.texture(1, XmTextures.TILE_NOISE_BLUE)
				.textureColor(1, 0x40000000)
				.blendMode(1, PaintBlendMode.TRANSLUCENT)
				.texture(2, XmTextures.BORDER_SMOOTH_BLEND)
				.textureColor(2, 0x60000000)
				.blendMode(2, PaintBlendMode.TRANSLUCENT)
				.find();

		final Block block = Xb.REG.block(ID_BLOCK, new Block(Block.Settings.copy(Blocks.DIORITE)));
		XmBlockRegistry.addBlock(block, PrimitiveStateFunction.ofDefaultState(
				Cube.INSTANCE.newState()
				.paintAll(mainPaint)
				.releaseToImmutable()));

		BlockRegistrator.register(block, ID_BLOCK, mainPaint, false);

		SpeciesBlock.species(block, ID_BLOCK + "_species", connectedPaint);
	}
}
