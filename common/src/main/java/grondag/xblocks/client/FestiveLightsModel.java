/*
 * This file is part of Exotic Blocks and is licensed to the project under
 * terms that are compatible with the GNU Lesser General Public License.
 * See the NOTICE file distributed with this work for additional information
 * regarding copyright ownership and licensing.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package grondag.xblocks.client;

import it.unimi.dsi.fastutil.floats.FloatArrayList;

import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;

import io.vram.dtk.BlueNoise;
import io.vram.frex.api.buffer.QuadEmitter;
import io.vram.frex.api.material.MaterialConstants;
import io.vram.frex.api.material.MaterialFinder;
import io.vram.frex.api.material.RenderMaterial;
import io.vram.frex.api.model.BlockModel;
import io.vram.frex.api.model.ItemModel;
import io.vram.frex.base.client.model.MeshFactory;
import io.vram.frex.base.client.model.ProceduralModel;
import io.vram.frex.base.client.model.SpriteProvider;

import grondag.xblocks.Xb;
import grondag.xblocks.block.FestiveLightsBlock;

public class FestiveLightsModel {
	public static final ResourceLocation SPRITE_ID = new ResourceLocation(Xb.MODID, "block/small_lamps");

	public static void build(ProceduralModel.Builder builder) {
		builder
			.blockModel(BLOCK_MODEL)
			.itemModel(ITEM_MODEL)
			.defaultMeshFactory(DEFAULT_MESH_FACTORY)
			.defaultParticleSprite(SPRITE_ID)
			.isGui3d(true)
			.useAmbientOcclusion(false)
			.usesBlockLight(false)
			.withSprites(SPRITE_ID);
	}

	@FunctionalInterface
	private interface RangeFilter {
		boolean test(float f);
	}

	private static final float SCALE = 1f / 16f;
	private static final float STEP = SCALE;

	private static final RangeFilter FILTER_NONE = f -> true;
	private static final RangeFilter FILTER_HIGH = f -> f < 1 - STEP;
	private static final RangeFilter FILTER_LOW = f -> f > STEP;
	private static final RangeFilter FILTER_ENDS = f -> f > STEP && f < 1 - STEP;

	private static final float UV_STEP = 5f / 16f;
	private static final float[] COLOR_UV = {
		0, 0, 0, UV_STEP, 0, UV_STEP * 2,
		UV_STEP, 0, UV_STEP, UV_STEP, UV_STEP, UV_STEP * 2,
		UV_STEP * 2, 0, UV_STEP * 2, UV_STEP, UV_STEP * 2, UV_STEP * 2
	};

	private static final float[][][] NOISE = new float[16][16][];

	static {
		final BlueNoise noise = BlueNoise.create(256, 4, 1225);
		final FloatArrayList list = new FloatArrayList();

		for (int x = 0; x < 256; x += 16) {
			for (int y = 0; y < 256; y += 16) {
				for (int i = 0; i < 16; i++) {
					for (int j = 0; j < 16; j++) {
						if (noise.isSet(x + i, y + j)) {
							list.add(i / 16f);
							list.add(j / 16f);
						}
					}
				}

				NOISE[x >> 4][y >> 4] = list.toFloatArray();
				list.clear();
			}
		}
	}

	protected static final RenderMaterial material = MaterialFinder.threadLocal().emissive(true).disableAo(true).disableDiffuse(true).preset(MaterialConstants.PRESET_SOLID).find();

	static final BlockModel BLOCK_MODEL = (in, out) -> {
		final var state = in.blockState();

		if (!(state.getBlock() instanceof FestiveLightsBlock)) {
			return;
		}

		final FestiveLightsBlock block = (FestiveLightsBlock) state.getBlock();

		final int[] colors = block.colors;

		final QuadEmitter qe = out.asQuadEmitter();
		final RandomSource rand = in.random();
		final TextureAtlasSprite sprite = SpriteProvider.forBlocksAndItems().getSprite(SPRITE_ID);

		if (block.isPendant()) {
			if (block.hasFace(state, Direction.EAST)) {
				emitPendantQuadsForFace(sprite, qe, Direction.EAST, colors, rand);
			}

			if (block.hasFace(state, Direction.WEST)) {
				emitPendantQuadsForFace(sprite, qe, Direction.WEST, colors, rand);
			}

			if (block.hasFace(state, Direction.NORTH)) {
				emitPendantQuadsForFace(sprite, qe, Direction.NORTH, colors, rand);
			}

			if (block.hasFace(state, Direction.SOUTH)) {
				emitPendantQuadsForFace(sprite, qe, Direction.SOUTH, colors, rand);
			}
		} else {
			final boolean hasUp = block.hasFace(state, Direction.UP);
			final boolean hasDown = block.hasFace(state, Direction.DOWN);
			final boolean hasEast = block.hasFace(state, Direction.EAST);
			final boolean hasWest = block.hasFace(state, Direction.WEST);
			final boolean hasNorth = block.hasFace(state, Direction.NORTH);
			final boolean hasSouth = block.hasFace(state, Direction.SOUTH);
			final var pos = in.pos();

			if (hasUp) {
				emitQuadsForFace(sprite, qe, Direction.UP, NOISE[pos.getX() & 15][pos.getZ() & 15], colors, rand, STEP, FILTER_NONE, FILTER_NONE);
			}

			if (hasDown) {
				emitQuadsForFace(sprite, qe, Direction.DOWN, NOISE[(pos.getX() + 8) & 15][(pos.getZ() + 8) & 15], colors, rand, STEP, FILTER_NONE, FILTER_NONE);
			}

			final RangeFilter filterY = hasUp
				? hasDown ? FILTER_ENDS : FILTER_HIGH
				: hasDown ? FILTER_LOW : FILTER_NONE;

			if (hasEast) {
				final RangeFilter filterX = hasSouth ? FILTER_HIGH : FILTER_NONE;
				emitQuadsForFace(sprite, qe, Direction.EAST, NOISE[(pos.getZ() + 8) & 15][(pos.getY() + 8) & 15], colors, rand, STEP, filterX, filterY);
			}

			if (hasWest) {
				final RangeFilter filterX = hasSouth ? FILTER_HIGH : FILTER_NONE;
				emitQuadsForFace(sprite, qe, Direction.WEST, NOISE[pos.getZ() & 15][pos.getY() & 15], colors, rand, STEP, filterX, filterY);
			}

			if (hasNorth) {
				final RangeFilter filterX = hasEast ? FILTER_HIGH : FILTER_NONE;
				emitQuadsForFace(sprite, qe, Direction.NORTH, NOISE[pos.getX() & 15][pos.getY() & 15], colors, rand, STEP, filterX, filterY);
			}

			if (hasSouth) {
				final RangeFilter filterX = hasEast ? FILTER_HIGH : FILTER_NONE;
				emitQuadsForFace(sprite, qe, Direction.SOUTH, NOISE[(pos.getX() + 8) & 15][(pos.getY() + 8) & 15], colors, rand, STEP, filterX, filterY);
			}
		}
	};

	static final MeshFactory DEFAULT_MESH_FACTORY = (mb, materialFinder, spriteProvider) -> {
		emitQuads(spriteProvider.getSprite(SPRITE_ID), mb.getEmitter(), 0, 0.4f, 0.4f, 0, 0.25f);
		return mb.build();
	};

	static final ItemModel ITEM_MODEL = (in, out) -> {
		final var stack = in.itemStack();

		if (stack.getItem() instanceof BlockItem) {
			final Block block = ((BlockItem) stack.getItem()).getBlock();
			final float step = 0.25f;

			if (block instanceof final FestiveLightsBlock myBlock) {
				final int[] colors = myBlock.colors;
				final int colorCount = colors.length;
				final QuadEmitter qe = out.asQuadEmitter();
				final TextureAtlasSprite sprite = SpriteProvider.forBlocksAndItems().getSprite(SPRITE_ID);

				if (myBlock.isPendant()) {
					emitPendantQuads(sprite, qe, colors[0], 0.0f, 0.5f, 0.375f, 0.08f);
					emitPendantQuads(sprite, qe, colors[1 % colorCount], 0.15f, 0.3f, 0.375f, 0.08f);
					emitPendantQuads(sprite, qe, colors[2 % colorCount], 0.30f, 0.7f, 0.375f, 0.08f);
					emitPendantQuads(sprite, qe, colors[3 % colorCount], 0.45f, 0.4f, 0.375f, 0.08f);
					emitPendantQuads(sprite, qe, colors[4 % colorCount], 0.60f, 0.3f, 0.375f, 0.08f);
					emitPendantQuads(sprite, qe, colors[5 % colorCount], 0.75f, 0.5f, 0.375f, 0.08f);
					emitPendantQuads(sprite, qe, colors[6 % colorCount], 0.90f, 0.3f, 0.375f, 0.08f);
				} else {
					emitQuads(sprite, qe, colors[0], 0.03f, 0.07f, 0.375f, step);
					emitQuads(sprite, qe, colors[1 % colorCount], 0.35f, 0.0f, 0.375f, step);
					emitQuads(sprite, qe, colors[2 % colorCount], 0.65f, 0.09f, 0.375f, step);
					emitQuads(sprite, qe, colors[3 % colorCount], 0.375f, 0.375f, 0.375f, step);
					emitQuads(sprite, qe, colors[4 % colorCount], 0.05f, 0.6f, 0.375f, step);
					emitQuads(sprite, qe, colors[5 % colorCount], 0.4f, 0.72f, 0.375f, step);
					emitQuads(sprite, qe, colors[6 % colorCount], 0.72f, 0.45f, 0.375f, step);
				}
			}
		}
	};

	protected static void emitQuadsForFace(TextureAtlasSprite sprite, QuadEmitter qe, Direction face, float[] noise, int[] colors, RandomSource rand, float step, RangeFilter xFilter, RangeFilter yFilter) {
		final int pointSize = noise.length;
		final int colorCount = colors.length;

		for (int i = 0; i < pointSize; i++) {
			final int color = colors[rand.nextInt(colorCount)];

			final float x = noise[i++];
			final float y = noise[i];

			if (xFilter.test(x) && yFilter.test(y)) {
				switch (face) {
					case UP:
						emitQuads(sprite, qe, color, x, 1 - step, y, step);
						break;
					case DOWN:
						emitQuads(sprite, qe, color, x, 0, y, step);
						break;
					case EAST:
						emitQuads(sprite, qe, color, 1 - step, y, x, step);
						break;
					case NORTH:
						emitQuads(sprite, qe, color, x, y, 0, step);
						break;
					case SOUTH:
						emitQuads(sprite, qe, color, x, y, 1 - step, step);
						break;
					case WEST:
						emitQuads(sprite, qe, color, 0, y, x, step);
						break;
					default:
						break;
				}
			}
		}
	}

	protected static void emitQuads(TextureAtlasSprite sprite, QuadEmitter qe, int color, float x, float y, float z, float step) {
		emitFace(sprite, qe, Direction.UP, color, x, 1 - z - step, 1 - y - step, step);
		emitFace(sprite, qe, Direction.DOWN, color, x, z, y, step);
		emitFace(sprite, qe, Direction.EAST, color, 1 - z - step, y, 1 - x - step, step);
		emitFace(sprite, qe, Direction.WEST, color, z, y, x, step);
		emitFace(sprite, qe, Direction.NORTH, color, 1 - x - step, y, z, step);
		emitFace(sprite, qe, Direction.SOUTH, color, x, y, 1 - z - step, step);
	}

	protected static void emitFace(TextureAtlasSprite sprite, QuadEmitter qe, Direction face, int color, float x, float y, float d, float step) {
		final float u = COLOR_UV[color * 2];
		final float v = COLOR_UV[color * 2 + 1];

		qe
			.material(material)
			.square(face, x, y, x + step, y + step, d)
			.uv(0, u, v)
			.uv(1, u + UV_STEP, v)
			.uv(2, u + UV_STEP, v + UV_STEP)
			.uv(3, u, v + UV_STEP)
			.vertexColor(-1, -1, -1, -1)
			.spriteBake(sprite, QuadEmitter.BAKE_NORMALIZED);
		//SimpleModels.contractUVs(0, modelSprite, qe);
		qe.emit();
	}

	protected static void emitPendantQuadsForFace(TextureAtlasSprite sprite, QuadEmitter qe, Direction face, int[] colors, RandomSource rand) {
		final int colorCount = colors.length;

		for (int i = 0; i < 4; i++) {
			final int color = colors[rand.nextInt(colorCount)];
			final float x = (1 + (i * 4)) * SCALE;
			final float y = rand.nextFloat() * 0.5f + 0.25f;

			switch (face) {
				case EAST:
					emitPendantQuads(sprite, qe, color, 1 - STEP, y, x, STEP);
					break;
				case NORTH:
					emitPendantQuads(sprite, qe, color, x, y, 0, STEP);
					break;
				case SOUTH:
					emitPendantQuads(sprite, qe, color, x, y, 1 - STEP, STEP);
					break;
				case WEST:
					emitPendantQuads(sprite, qe, color, 0, y, x, STEP);
					break;
				default:
					break;
			}
		}
	}

	protected static void emitPendantQuads(TextureAtlasSprite sprite, QuadEmitter qe, int color, float x, float y, float z, float step) {
		emitPendantFace(sprite, qe, Direction.UP, color, x, 1 - z - step, 0, step);
		emitPendantFace(sprite, qe, Direction.DOWN, color, x, z, 1 - y, step);
		emitPendantFace(sprite, qe, Direction.EAST, color, 1 - z - step, 1 - y, 1 - x - step, step);
		emitPendantFace(sprite, qe, Direction.WEST, color, z, 1 - y, x, step);
		emitPendantFace(sprite, qe, Direction.NORTH, color, 1 - x - step, 1 - y, z, step);
		emitPendantFace(sprite, qe, Direction.SOUTH, color, x, 1 - y, 1 - z - step, step);
	}

	protected static void emitPendantFace(TextureAtlasSprite sprite, QuadEmitter qe, Direction face, int color, float x, float y, float d, float step) {
		final float u = COLOR_UV[color * 2];
		final float v = COLOR_UV[color * 2 + 1];
		final float yMax = face.getAxis() == Axis.Y ? y + step : 1;
		qe
			.material(material)
			.square(face, x, y, x + step, yMax, d)
			.uv(0, u, v)
			.uv(1, u + UV_STEP, v)
			.uv(2, u + UV_STEP, v + UV_STEP)
			.uv(3, u, v + UV_STEP)
			.vertexColor(-1, -1, -1, -1)
			.spriteBake(sprite, QuadEmitter.BAKE_NORMALIZED);
		//SimpleModels.contractUVs(0, modelSprite, qe);
		qe.emit();
	}
}
