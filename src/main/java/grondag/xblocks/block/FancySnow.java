package grondag.xblocks.block;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

import grondag.xblocks.Xb;
import grondag.xm.api.block.XmBlockRegistry;
import grondag.xm.api.modelstate.primitive.PrimitiveStateFunction;
import grondag.xm.api.paint.XmPaintRegistry;
import grondag.xm.api.primitive.simple.Cube;

public enum FancySnow {
	;

	static final String ID_BASE = "fancy_snow";
	static final String ID_BLOCK = ID_BASE + "_block";

	static {

		final Block fancySnow = Xb.register(new Block(Block.Settings.copy(Blocks.SNOW_BLOCK)), ID_BLOCK);
		XmBlockRegistry.addBlock(fancySnow, PrimitiveStateFunction.ofDefaultState(
				Cube.INSTANCE.newState()
				.paintAll(XmPaintRegistry.INSTANCE.get(Xb.id(ID_BASE)))
				.releaseToImmutable()));

		BlockShapes.register(fancySnow, ID_BLOCK, ID_BASE, false);

		SpeciesBlock.species(fancySnow, ID_BLOCK + "_species", XmPaintRegistry.INSTANCE.get(Xb.id(ID_BASE + "_species")));
	}
}
