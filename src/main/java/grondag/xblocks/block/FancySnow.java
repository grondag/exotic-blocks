package grondag.xblocks.block;

import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.sound.BlockSoundGroup;

import net.fabricmc.fabric.api.block.FabricBlockSettings;

import grondag.xblocks.Xb;
import grondag.xm.api.block.XmBlockRegistry;
import grondag.xm.api.modelstate.primitive.PrimitiveStateFunction;
import grondag.xm.api.paint.XmPaintRegistry;
import grondag.xm.api.primitive.simple.Cube;

public enum FancySnow {
	;

	static final String ID_BASE = "fancy_snow";
	static final String ID_BLOCK = ID_BASE + "_block";

	public static final Material FANCY_SNOW_MATERIAL = (new Material.Builder(MaterialColor.WHITE)).build();
	public final static Block FANCY_SNOW_BLOCK = Xb.register(new Block(FabricBlockSettings.of(FANCY_SNOW_MATERIAL).strength(0.2F, 0.2F).sounds(BlockSoundGroup.SNOW).breakByHand(true).build()), ID_BLOCK);


	static {
		XmBlockRegistry.addBlock(FANCY_SNOW_BLOCK, PrimitiveStateFunction.ofDefaultState(
				Cube.INSTANCE.newState()
				.paintAll(XmPaintRegistry.INSTANCE.get(Xb.id(ID_BASE)))
				.releaseToImmutable()));

		BlockShapes.register(FANCY_SNOW_BLOCK, ID_BLOCK, ID_BASE, false);

		SpeciesBlock.species(FANCY_SNOW_BLOCK, ID_BLOCK + "_species", XmPaintRegistry.INSTANCE.get(Xb.id(ID_BASE + "_species")));
	}
}
