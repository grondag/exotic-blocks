package grondag.xblocks.block;

import it.unimi.dsi.fastutil.HashCommon;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

import net.fabricmc.fabric.api.renderer.v1.material.BlendMode;

import grondag.fermion.color.ColorHelper;
import grondag.xblocks.Xb;
import grondag.xm.api.block.XmBlockRegistry;
import grondag.xm.api.connect.species.SpeciesProperty;
import grondag.xm.api.modelstate.primitive.PrimitiveStateFunction;
import grondag.xm.api.paint.VertexProcessor;
import grondag.xm.api.paint.XmPaint;
import grondag.xm.api.primitive.simple.Cube;
import grondag.xm.api.texture.XmTextures;

public class StoneSpecies {
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

	public static void init() {
		XmPaint paint = XmPaint.finder()
				.textureDepth(2)
				.texture(0, XmTextures.BIGTEX_SANDSTONE)
				.vertexProcessor(0, SPECIES_VARIATION)
				.textureColor(0, 0xFF9090A0)
				//	            .texture(1, XmTextures.TILE_NOISE_BLUE_A)
				//	            .textureColor(1, 0x803B5303)
				//	            .blendMode(1, BlockRenderLayer.TRANSLUCENT)
				//	            .texture(1, XmTextures.BORDER_SMOOTH_BLEND)
				//	            .textureColor(1, 0x90303535)
				.texture(1, XmTextures.BORDER_QUADRANT_TEST)
				.textureColor(1, 0xFFFFFFFF)
				.blendMode(1, BlendMode.TRANSLUCENT)
				.find();

		species(Blocks.STONE, "spectest0", paint);

		paint = XmPaint.finder()
				.textureDepth(3)
				.texture(0, XmTextures.TILE_NOISE_MODERATE)
				.vertexProcessor(0, SPECIES_VARIATION)
				.textureColor(0, 0xFF9090A0)
				.texture(1, XmTextures.TILE_NOISE_BLUE_A)
				.textureColor(1, 0x80404040)
				.blendMode(1, BlendMode.TRANSLUCENT)
				.texture(2, XmTextures.BORDER_GRITTY_SINGLE_LINE)
				.textureColor(2, 0x90A0A0A0)
				.blendMode(2, BlendMode.TRANSLUCENT)
				.find();

		species(Blocks.STONE, "spectest1", paint);
	}



	public static Block species(Block template, String id, XmPaint paint) {

		Block block = new SpeciesBlock(Block.Settings.copy(template));

		block = Xb.register(block, id);

		XmBlockRegistry.addBlockStates(block, bs -> PrimitiveStateFunction.builder()
				.withJoin(SpeciesProperty.matchBlockAndSpecies())
				.withUpdate(SpeciesProperty.SPECIES_MODIFIER)
				.withDefaultState(SpeciesProperty.SPECIES_MODIFIER.mutate(
						Cube.INSTANCE.newState()
						.paintAll(paint), bs))
				.build());

		return block;
	}
}
