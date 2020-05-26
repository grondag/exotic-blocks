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

public enum RammedEarth {
	;

	static final String ID_BLOCK = "rammed_earth";

	static {

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
				.blendMode(1, PaintBlendMode.TRANSLUCENT)
				.find();

		final Block block = Xb.REG.block(ID_BLOCK, new Block(Block.Settings.copy(Blocks.TERRACOTTA)));
		XmBlockRegistry.addBlock(block, PrimitiveStateFunction.ofDefaultState(
				Cube.INSTANCE.newState()
				.paintAll(mainPaint)
				.releaseToImmutable()));

		BlockRegistrator.register(block, ID_BLOCK, mainPaint, false);

		SpeciesBlock.species(block, ID_BLOCK + "_species", connectedPaint);
	}
}
