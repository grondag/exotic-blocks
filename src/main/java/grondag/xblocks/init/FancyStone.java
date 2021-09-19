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
import grondag.xm.api.paint.VertexProcessors;
import grondag.xm.api.paint.XmPaint;
import grondag.xm.api.primitive.simple.Cube;
import grondag.xm.api.texture.XmTextures;
import grondag.xm.api.texture.core.CoreTextures;

public enum FancyStone {
	;

	static {

		final XmPaint mainPaint = XmPaint.finder()
				.textureDepth(2)
				.texture(0, CoreTextures.BIGTEX_SANDSTONE)
				.textureColor(0, 0xFF9090A0)
				.texture(1, XmTextures.TILE_NOISE_BLUE_A)
				.textureColor(1, 0xA0656d75)
				.find();

		final XmPaint connectedPaint = XmPaint.finder()
				.textureDepth(3)
				.texture(0, CoreTextures.BIGTEX_SANDSTONE)
				.vertexProcessor(0, VertexProcessors.SPECIES_VARIATION)
				.textureColor(0, 0xFF9090A0)
				.texture(1, XmTextures.TILE_NOISE_BLUE_A)
				.textureColor(1, 0xA0656d75)
				.texture(2, CoreTextures.BORDER_SMOOTH_BLEND)
				.textureColor(2, 0x802b2f33)
				.find();

		final Block fancyStone = Xb.REG.block(BlockNames.BLOCK_FANCY_STONE, new Block(Block.Properties.copy(Blocks.STONE)));
		XmBlockRegistry.addBlock(fancyStone, PrimitiveStateFunction.ofDefaultState(
				Cube.INSTANCE.newState()
				.paintAll(mainPaint)
				.releaseToImmutable()));

		SpeciesBlock.species(fancyStone, BlockNames.BLOCK_CONNECTED_FANCY_STONE, connectedPaint);
		ShapedBlockRegistrator.registerShapes(fancyStone, ShapedBlockNames.SHAPED_FANCY_STONE, mainPaint, false);
	}
}
