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
import grondag.xm.api.paint.XmPaint;
import grondag.xm.api.primitive.simple.Cube;
import grondag.xm.api.texture.XmTextures;
import grondag.xm.api.texture.core.CoreTextures;

public enum FancyDiorite {
	;

	static {
		final XmPaint mainPaint = XmPaint.finder()
				.textureDepth(2)
				.texture(0, CoreTextures.BIGTEX_GRANITE)
				.textureColor(0, 0xFFFFFFFF)
				.texture(1, XmTextures.TILE_NOISE_BLUE)
				.textureColor(1, 0x40000000)
				.find();

		final XmPaint connectedPaint = XmPaint.finder()
				.textureDepth(3)
				.texture(0, CoreTextures.BIGTEX_GRANITE)
				.textureColor(0, 0xFFFFFFFF)
				.texture(1, XmTextures.TILE_NOISE_BLUE)
				.textureColor(1, 0x40000000)
				.texture(2, CoreTextures.BORDER_SMOOTH_BLEND)
				.textureColor(2, 0x60000000)
				.find();

		final Block block = Xb.REG.block(BlockNames.BLOCK_FANCY_DIORITE, new Block(Block.Properties.copy(Blocks.DIORITE)));
		XmBlockRegistry.addBlock(block, PrimitiveStateFunction.ofDefaultState(
				Cube.INSTANCE.newState()
				.paintAll(mainPaint)
				.releaseToImmutable()));

		SpeciesBlock.species(block, BlockNames.BLOCK_CONNECTED_FANCY_DIORITE, connectedPaint);
		ShapedBlockRegistrator.registerShapes(block, ShapedBlockNames.SHAPED_FANCY_DIORITE, mainPaint, false);
	}
}
