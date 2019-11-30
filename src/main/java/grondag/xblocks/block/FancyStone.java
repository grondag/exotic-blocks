package grondag.xblocks.block;

import it.unimi.dsi.fastutil.HashCommon;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

import net.fabricmc.fabric.api.renderer.v1.material.BlendMode;

import grondag.fermion.color.ColorHelper;
import grondag.xblocks.Xb;
import grondag.xm.api.block.XmBlockRegistry;
import grondag.xm.api.modelstate.primitive.PrimitiveStateFunction;
import grondag.xm.api.paint.VertexProcessor;
import grondag.xm.api.paint.XmPaint;
import grondag.xm.api.primitive.simple.Cube;
import grondag.xm.api.texture.XmTextures;

public enum FancyStone {
	;

	public static final VertexProcessor SPECIES_VARIATION = (poly, modelState, surface, paint, textureIndex) -> {
		final int mix = HashCommon.mix(modelState.species());
		final int value = mix & 0xF;
		final int mixColor = 0xFFFFFFFF - (mix & 0x0F0000) - value - (value << 8);
		final int color = ColorHelper.multiplyColor(mixColor, paint.textureColor(textureIndex));

		for (int i = 0; i < poly.vertexCount(); i++) {
			final int c = ColorHelper.multiplyColor(color, poly.color(i, textureIndex));
			poly.color(i, textureIndex, c);
		}
	};

	static final String ID_BASE = "fancy_stone";
	static final String ID_BLOCK = ID_BASE + "_block";

	static {

		final XmPaint mainPaint = XmPaint.finder()
				.textureDepth(2)
				.texture(0, XmTextures.BIGTEX_SANDSTONE)
				.textureColor(0, 0xFF9090A0)
				.texture(1, XmTextures.TILE_NOISE_BLUE_A)
				.blendMode(1, BlendMode.TRANSLUCENT)
				.textureColor(1, 0xA0656d75)
				.find();

		final XmPaint connectedPaint = XmPaint.finder()
				.textureDepth(3)
				.texture(0, XmTextures.BIGTEX_SANDSTONE)
				.vertexProcessor(0, SPECIES_VARIATION)
				.textureColor(0, 0xFF9090A0)
				.texture(1, XmTextures.TILE_NOISE_BLUE_A)
				.textureColor(1, 0xA0656d75)
				.blendMode(1, BlendMode.TRANSLUCENT)
				.texture(2, XmTextures.BORDER_SMOOTH_BLEND)
				.textureColor(2, 0x802b2f33)
				.blendMode(2, BlendMode.TRANSLUCENT)
				.find();

		final Block fancyStone = Xb.REG.block(ID_BLOCK, new Block(Block.Settings.copy(Blocks.STONE)));
		XmBlockRegistry.addBlock(fancyStone, PrimitiveStateFunction.ofDefaultState(
				Cube.INSTANCE.newState()
				.paintAll(mainPaint)
				.releaseToImmutable()));

		BlockShapes.register(fancyStone, ID_BLOCK, mainPaint, false);

		SpeciesBlock.species(fancyStone, ID_BLOCK + "_species", connectedPaint);
	}
}
