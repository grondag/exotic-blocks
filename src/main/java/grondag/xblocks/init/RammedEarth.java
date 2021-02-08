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
import grondag.xm.api.paint.VertexProcessors;
import grondag.xm.api.paint.XmPaint;
import grondag.xm.api.primitive.simple.Cube;
import grondag.xm.api.texture.core.CoreTextures;

public enum RammedEarth {
	;

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
				.find();

		final Block block = Xb.REG.block(BlockNames.BLOCK_RAMMED_EARTH, new Block(Block.Settings.copy(Blocks.TERRACOTTA)));

		XmBlockRegistry.addBlock(block, PrimitiveStateFunction.ofDefaultState(
				Cube.INSTANCE.newState()
				.paintAll(mainPaint)
				.releaseToImmutable()));

		SpeciesBlock.species(block, BlockNames.BLOCK_CONNECTED_RAMMED_EARTH, connectedPaint);
		ShapedBlockRegistrator.registerShapes(block, ShapedBlockNames.SHAPED_RAMMED_EARTH, mainPaint, false);
	}
}
