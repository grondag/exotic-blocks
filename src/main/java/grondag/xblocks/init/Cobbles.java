package grondag.xblocks.init;

import net.minecraft.block.Blocks;

import grondag.xblocks.block.SpeciesBlock;
import grondag.xblocks.data.BlockNames;
import grondag.xm.api.paint.XmPaint;
import grondag.xm.api.texture.core.CoreTextures;

public enum Cobbles {
	;

	static {
		final XmPaint connectedPaint = XmPaint.finder()
				.textureDepth(2)
				.texture(0, CoreTextures.BIGTEX_COBBLE_SQUARES)
				.textureColor(0, 0xFF949698)
				.texture(1, CoreTextures.BORDER_COBBLE)
				.textureColor(1, 0xFF949698)
				.find();

		SpeciesBlock.species(Blocks.COBBLESTONE, BlockNames.BLOCK_COBBLE_SMALL_SQUARE, connectedPaint);
	}
}
