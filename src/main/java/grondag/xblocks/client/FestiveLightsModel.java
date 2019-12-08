package grondag.xblocks.client;

import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.function.Supplier;

import it.unimi.dsi.fastutil.floats.FloatArrayList;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Direction.Axis;
import net.minecraft.world.BlockRenderView;

import net.fabricmc.fabric.api.renderer.v1.Renderer;
import net.fabricmc.fabric.api.renderer.v1.RendererAccess;
import net.fabricmc.fabric.api.renderer.v1.material.BlendMode;
import net.fabricmc.fabric.api.renderer.v1.material.RenderMaterial;
import net.fabricmc.fabric.api.renderer.v1.mesh.Mesh;
import net.fabricmc.fabric.api.renderer.v1.mesh.MeshBuilder;
import net.fabricmc.fabric.api.renderer.v1.mesh.MutableQuadView;
import net.fabricmc.fabric.api.renderer.v1.mesh.QuadEmitter;
import net.fabricmc.fabric.api.renderer.v1.model.ModelHelper;
import net.fabricmc.fabric.api.renderer.v1.render.RenderContext;

import grondag.fermion.client.models.SimpleModel;
import grondag.fermion.varia.BlueNoise;
import grondag.xblocks.block.FestiveLightsBlock;

public class FestiveLightsModel extends SimpleModel {
	public static final List<SpriteIdentifier> TEXTURES = XbClient.REGISTRAR.spriteIdList(SpriteAtlasTexture.BLOCK_ATLAS_TEX, "block/small_lamps");

	public static FestiveLightsModel create(Function<SpriteIdentifier, Sprite> spriteMap) {
		return new FestiveLightsModel(spriteMap.apply(TEXTURES.get(0)));
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
	private static final RangeFilter FILTER_ENDS = f -> f > STEP  && f < 1 - STEP;

	private static final float UV_STEP = 5f / 16f;
	private static final float[] COLOR_UV = {
			0, 0, 0, UV_STEP, 0, UV_STEP * 2,
			UV_STEP, 0, UV_STEP, UV_STEP, UV_STEP, UV_STEP *  2,
			UV_STEP * 2, 0, UV_STEP * 2, UV_STEP, UV_STEP * 2, UV_STEP * 2};

	private static final float[][][] NOISE = new float[16][16][];

	static {
		final  BlueNoise noise = BlueNoise.create(256, 4, 1225);
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

	protected final Renderer renderer = RendererAccess.INSTANCE.getRenderer();
	protected final RenderMaterial material = renderer.materialFinder().emissive(0, true).disableAo(0, true).disableDiffuse(0, true).blendMode(0, BlendMode.SOLID).find();

	public FestiveLightsModel(Sprite sprite) {
		super(sprite, ModelHelper.MODEL_TRANSFORM_BLOCK);
	}

	@Override
	public final void emitBlockQuads(BlockRenderView blockView, BlockState state, BlockPos pos, Supplier<Random> randomSupplier, RenderContext context) {

		if (!(state.getBlock() instanceof FestiveLightsBlock)) {
			return;
		}

		final FestiveLightsBlock block = (FestiveLightsBlock) state.getBlock();

		final int[] colors = block.colors;

		final QuadEmitter qe = context.getEmitter();
		final Random rand = randomSupplier.get();

		if (block.isPendant()) {
			if (block.hasFace(state, Direction.EAST)) {
				emitPendantQuadsForFace(qe, Direction.EAST, colors, rand);
			}

			if (block.hasFace(state, Direction.WEST)) {
				emitPendantQuadsForFace(qe, Direction.WEST, colors, rand);
			}

			if (block.hasFace(state, Direction.NORTH)) {
				emitPendantQuadsForFace(qe, Direction.NORTH, colors, rand);
			}

			if (block.hasFace(state, Direction.SOUTH)) {
				emitPendantQuadsForFace(qe, Direction.SOUTH, colors, rand);
			}
		}  else  {
			final boolean hasUp = block.hasFace(state, Direction.UP);
			final boolean hasDown = block.hasFace(state, Direction.DOWN);
			final boolean hasEast = block.hasFace(state, Direction.EAST);
			final boolean hasWest = block.hasFace(state, Direction.WEST);
			final boolean hasNorth = block.hasFace(state, Direction.NORTH);
			final boolean hasSouth = block.hasFace(state, Direction.SOUTH);

			if (hasUp) {
				emitQuadsForFace(qe, Direction.UP, NOISE[pos.getX() & 15][pos.getZ() & 15], colors, rand, STEP, FILTER_NONE, FILTER_NONE);
			}

			if (hasDown) {
				emitQuadsForFace(qe, Direction.DOWN, NOISE[(pos.getX() + 8) & 15][(pos.getZ() + 8) & 15], colors, rand, STEP, FILTER_NONE, FILTER_NONE);
			}

			final RangeFilter filterY = hasUp
					? hasDown ?  FILTER_ENDS : FILTER_HIGH
							: hasDown ? FILTER_LOW : FILTER_NONE;


			if (hasEast) {
				final RangeFilter filterX = hasSouth ? FILTER_HIGH : FILTER_NONE;
				emitQuadsForFace(qe, Direction.EAST, NOISE[(pos.getZ() + 8) & 15][(pos.getY() + 8) & 15], colors, rand, STEP, filterX, filterY);
			}

			if (hasWest) {
				final RangeFilter filterX = hasSouth ? FILTER_HIGH : FILTER_NONE;
				emitQuadsForFace(qe, Direction.WEST, NOISE[pos.getZ() & 15][pos.getY() & 15], colors, rand, STEP, filterX, filterY);
			}

			if (hasNorth) {
				final RangeFilter filterX = hasEast ? FILTER_HIGH : FILTER_NONE;
				emitQuadsForFace(qe, Direction.NORTH, NOISE[pos.getX() & 15][pos.getY() & 15], colors, rand, STEP, filterX, filterY);
			}

			if (hasSouth) {
				final RangeFilter filterX = hasEast ? FILTER_HIGH : FILTER_NONE;
				emitQuadsForFace(qe, Direction.SOUTH, NOISE[(pos.getX() + 8) & 15][(pos.getY() + 8) & 15], colors, rand, STEP, filterX, filterY);
			}
		}
	}

	@Override
	protected Mesh createMesh() {
		final MeshBuilder mb = renderer.meshBuilder();
		emitQuads(mb.getEmitter(), 0, 0.4f, 0.4f, 0, 0.25f);
		return mb.build();
	}

	@Override
	public void emitItemQuads(ItemStack stack, Supplier<Random> randomSupplier, RenderContext context) {
		if (stack.getItem() instanceof BlockItem) {
			final Block block = ((BlockItem) stack.getItem()).getBlock();
			final float step =  0.25f;

			if (block instanceof FestiveLightsBlock) {
				final FestiveLightsBlock myBlock = (FestiveLightsBlock) block;
				final int[] colors = myBlock.colors;
				final int colorCount = colors.length;
				final QuadEmitter qe = context.getEmitter();

				if (myBlock.isPendant()) {
					emitPendantQuads(qe, colors[0], 0.0f, 0.5f, 0.375f, 0.08f);
					emitPendantQuads(qe, colors[1 % colorCount], 0.15f, 0.3f, 0.375f, 0.08f);
					emitPendantQuads(qe, colors[2 % colorCount], 0.30f, 0.7f, 0.375f, 0.08f);
					emitPendantQuads(qe, colors[3 % colorCount], 0.45f, 0.4f, 0.375f, 0.08f);
					emitPendantQuads(qe, colors[4 % colorCount], 0.60f, 0.3f, 0.375f, 0.08f);
					emitPendantQuads(qe, colors[5 % colorCount], 0.75f, 0.5f, 0.375f, 0.08f);
					emitPendantQuads(qe, colors[6 % colorCount], 0.90f, 0.3f, 0.375f, 0.08f);
				} else {
					emitQuads(qe, colors[0], 0.03f, 0.07f, 0.375f, step);
					emitQuads(qe, colors[1 % colorCount], 0.35f, 0.0f, 0.375f, step);
					emitQuads(qe, colors[2 % colorCount], 0.65f, 0.09f, 0.375f, step);
					emitQuads(qe, colors[3 % colorCount], 0.375f, 0.375f, 0.375f, step);
					emitQuads(qe, colors[4 % colorCount], 0.05f, 0.6f, 0.375f, step);
					emitQuads(qe, colors[5 % colorCount], 0.4f, 0.72f, 0.375f, step);
					emitQuads(qe, colors[6 % colorCount], 0.72f, 0.45f, 0.375f, step);
				}

				return;
			}
		}

		super.emitItemQuads(stack, randomSupplier, context);
	}

	protected final void emitQuadsForFace(QuadEmitter qe, Direction face, float[] noise, int[] colors, Random rand, float step, RangeFilter xFilter, RangeFilter yFilter) {
		final int pointSize = noise.length;
		final int colorCount = colors.length;

		for (int i = 0; i < pointSize; i++) {
			final int color = colors[rand.nextInt(colorCount)];

			final float x = noise[i++];
			final float y  = noise[i];

			if(xFilter.test(x) && yFilter.test(y)) {
				switch(face) {
				case UP:
					emitQuads(qe, color, x, 1 - step, y, step);
					break;
				case DOWN:
					emitQuads(qe, color, x, 0, y, step);
					break;
				case EAST:
					emitQuads(qe, color, 1 - step, y, x, step);
					break;
				case NORTH:
					emitQuads(qe, color, x, y, 0, step);
					break;
				case SOUTH:
					emitQuads(qe, color, x, y, 1 - step, step);
					break;
				case WEST:
					emitQuads(qe, color, 0, y, x, step);
					break;
				default:
					break;

				}
			}
		}
	}

	protected final void emitQuads(QuadEmitter qe, int color, float x, float y, float z, float step) {
		emitFace(qe, Direction.UP, color, x, 1 - z - step, 1 - y - step, step);
		emitFace(qe, Direction.DOWN, color, x, z, y, step);
		emitFace(qe, Direction.EAST, color, 1 - z - step, y, 1 - x - step, step);
		emitFace(qe, Direction.WEST, color, z, y, x, step);
		emitFace(qe, Direction.NORTH, color, x, y, z, step);
		emitFace(qe, Direction.SOUTH, color, 1 - x - step, y, 1 - z - step, step);
	}

	protected final void emitFace(QuadEmitter qe, Direction face, int color, float x, float y, float d, float step) {
		final float u = COLOR_UV[color * 2];
		final float v = COLOR_UV[color * 2 + 1];

		qe.material(material)
		.square(face, x, y, x + step, y + step, d)
		.sprite(0, 0, u,  v)
		.sprite(1, 0, u + UV_STEP, v)
		.sprite(2, 0, u + UV_STEP, v + UV_STEP)
		.sprite(3, 0, u, v + UV_STEP)
		.spriteColor(0, -1, -1, -1, -1)
		.spriteBake(0, modelSprite, MutableQuadView.BAKE_NORMALIZED);
		//SimpleModels.contractUVs(0, modelSprite, qe);
		qe.emit();
	}

	protected final void emitPendantQuadsForFace(QuadEmitter qe, Direction face, int[] colors, Random rand) {
		final int colorCount = colors.length;

		for (int i = 0; i < 4; i++) {
			final int color = colors[rand.nextInt(colorCount)];
			final float x = (1 + (i * 4)) * SCALE;
			final float y  = rand.nextFloat() * 0.5f + 0.25f;

			switch(face) {
			case EAST:
				emitPendantQuads(qe, color, 1 - STEP, y, x, STEP);
				break;
			case NORTH:
				emitPendantQuads(qe, color, x, y, 0, STEP);
				break;
			case SOUTH:
				emitPendantQuads(qe, color, x, y, 1 - STEP, STEP);
				break;
			case WEST:
				emitPendantQuads(qe, color, 0, y, x, STEP);
				break;
			default:
				break;
			}
		}
	}

	protected final void emitPendantQuads(QuadEmitter qe, int color, float x, float y, float z, float step) {
		emitPendantFace(qe, Direction.UP, color, x, 1 - z - step, 0, step);
		emitPendantFace(qe, Direction.DOWN, color, x, z, 1 - y, step);
		emitPendantFace(qe, Direction.EAST, color, 1 - z - step, 1 - y, 1 - x - step, step);
		emitPendantFace(qe, Direction.WEST, color, z, 1 - y, x, step);
		emitPendantFace(qe, Direction.NORTH, color, x, 1 - y, z, step);
		emitPendantFace(qe, Direction.SOUTH, color, 1 - x - step, 1 - y, 1 - z - step, step);
	}

	protected final void emitPendantFace(QuadEmitter qe, Direction face, int color, float x, float y, float d, float step) {
		final float u = COLOR_UV[color * 2];
		final float v = COLOR_UV[color * 2 + 1];
		final float yMax  = face.getAxis() == Axis.Y ?  y + step : 1;
		qe.material(material)
		.square(face, x, y, x + step, yMax, d)
		.sprite(0, 0, u,  v)
		.sprite(1, 0, u + UV_STEP, v)
		.sprite(2, 0, u + UV_STEP, v + UV_STEP)
		.sprite(3, 0, u, v + UV_STEP)
		.spriteColor(0, -1, -1, -1, -1)
		.spriteBake(0, modelSprite, MutableQuadView.BAKE_NORMALIZED);
		//SimpleModels.contractUVs(0, modelSprite, qe);
		qe.emit();
	}
}
