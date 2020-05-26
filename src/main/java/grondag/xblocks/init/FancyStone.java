package grondag.xblocks.init;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

import grondag.xblocks.Xb;
import grondag.xblocks.block.BlockRegistrator;
import grondag.xblocks.block.SpeciesBlock;
import grondag.xblocks.block.VertexProcessors;
import grondag.xm.api.block.XmBlockRegistry;
import grondag.xm.api.modelstate.primitive.PrimitiveStateFunction;
import grondag.xm.api.paint.PaintBlendMode;
import grondag.xm.api.paint.XmPaint;
import grondag.xm.api.primitive.simple.Cube;
import grondag.xm.api.texture.content.CoreTextures;
import grondag.xm.api.texture.content.XmTextures;

public enum FancyStone {
	;

	static final String ID = "fancy_stone";
	static final String ID_BLOCK = ID + "_block";

	static {

		final XmPaint mainPaint = XmPaint.finder()
				.textureDepth(2)
				.texture(0, CoreTextures.BIGTEX_SANDSTONE)
				.textureColor(0, 0xFF9090A0)
				.texture(1, XmTextures.TILE_NOISE_BLUE_A)
				.blendMode(1, PaintBlendMode.TRANSLUCENT)
				.textureColor(1, 0xA0656d75)
				.find();

		final XmPaint connectedPaint = XmPaint.finder()
				.textureDepth(3)
				.texture(0, CoreTextures.BIGTEX_SANDSTONE)
				.vertexProcessor(0, VertexProcessors.SPECIES_VARIATION)
				.textureColor(0, 0xFF9090A0)
				.texture(1, XmTextures.TILE_NOISE_BLUE_A)
				.textureColor(1, 0xA0656d75)
				.blendMode(1, PaintBlendMode.TRANSLUCENT)
				.texture(2, CoreTextures.BORDER_SMOOTH_BLEND)
				.textureColor(2, 0x802b2f33)
				.blendMode(2, PaintBlendMode.TRANSLUCENT)
				.find();

		final Block fancyStone = Xb.REG.block(ID_BLOCK, new Block(Block.Settings.copy(Blocks.STONE)));
		XmBlockRegistry.addBlock(fancyStone, PrimitiveStateFunction.ofDefaultState(
				Cube.INSTANCE.newState()
				.paintAll(mainPaint)
				.releaseToImmutable()));

		BlockRegistrator.register(fancyStone, ID_BLOCK, mainPaint, false);

		SpeciesBlock.species(fancyStone, ID_BLOCK + "_species", connectedPaint);
	}
}
