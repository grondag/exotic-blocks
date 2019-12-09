package grondag.xblocks.init;

import net.minecraft.block.Blocks;

import grondag.xblocks.block.SpeciesBlock;
import grondag.xm.api.paint.PaintBlendMode;
import grondag.xm.api.paint.XmPaint;
import grondag.xm.api.texture.XmTextures;

public enum Cobbles {
	;

	static {

		final XmPaint connectedPaint = XmPaint.finder()
				.textureDepth(2)
				.texture(0, XmTextures.BIGTEX_COBBLE_SQUARES)
				.textureColor(0, 0xFF949698)
				.texture(1, XmTextures.BORDER_COBBLE)
				.textureColor(1, 0xFF949698)
				.blendMode(1, PaintBlendMode.TRANSLUCENT)
				.find();

		SpeciesBlock.species(Blocks.COBBLESTONE, "cobble_squares_block", connectedPaint);
	}
}
