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

package grondag.xblocks.init;

import static grondag.xm.api.texture.TextureGroup.STATIC_BORDERS;
import static grondag.xm.api.texture.TextureNameFunction.BORDER_CORNERS_ALL;
import static grondag.xm.api.texture.TextureNameFunction.BORDER_CORNERS_BL_TR;
import static grondag.xm.api.texture.TextureNameFunction.BORDER_CORNERS_BL_TR_BR;
import static grondag.xm.api.texture.TextureNameFunction.BORDER_CORNERS_TL_TR;
import static grondag.xm.api.texture.TextureNameFunction.BORDER_CORNER_TR;
import static grondag.xm.api.texture.TextureNameFunction.BORDER_MIXED_TOP_BL_BR;
import static grondag.xm.api.texture.TextureNameFunction.BORDER_MIXED_TOP_BR;
import static grondag.xm.api.texture.TextureNameFunction.BORDER_MIXED_TOP_RIGHT_BL;
import static grondag.xm.api.texture.TextureNameFunction.BORDER_NONE;
import static grondag.xm.api.texture.TextureNameFunction.BORDER_SIDES_ALL;
import static grondag.xm.api.texture.TextureNameFunction.BORDER_SIDES_TOP_BOTTOM;
import static grondag.xm.api.texture.TextureNameFunction.BORDER_SIDES_TOP_LEFT_RIGHT;
import static grondag.xm.api.texture.TextureNameFunction.BORDER_SIDES_TOP_RIGHT;
import static grondag.xm.api.texture.TextureNameFunction.BORDER_SIDE_TOP;
import static grondag.xm.api.texture.TextureRenderIntent.OVERLAY_ONLY;
import static grondag.xm.api.texture.TextureScale.SINGLE;
import static grondag.xm.api.texture.TextureTransform.IDENTITY;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GlassBlock;

import grondag.xblocks.Xb;
import grondag.xblocks.data.ColorNames;
import grondag.xm.api.block.XmBlockRegistry;
import grondag.xm.api.modelstate.primitive.PrimitiveStateFunction;
import grondag.xm.api.paint.XmPaint;
import grondag.xm.api.paint.XmPaintRegistry;
import grondag.xm.api.primitive.simple.Cube;
import grondag.xm.api.texture.TextureLayout;
import grondag.xm.api.texture.TextureLayoutMap;
import grondag.xm.api.texture.TextureSet;

/**
 * Tests adding connected textures to existing blocks by giving glass a custom connected texture.
 */
public abstract class ConnectedGlass {
	private ConnectedGlass() { }

	private static final String[] BORDER_MAP = new String[TextureLayout.BORDER_14.textureCount];

	static {
		BORDER_MAP[BORDER_SIDES_ALL] = "all_edges";
		BORDER_MAP[BORDER_SIDE_TOP] = "top_edge";
		BORDER_MAP[BORDER_SIDES_TOP_RIGHT] = "top_right_edges";
		BORDER_MAP[BORDER_SIDES_TOP_BOTTOM] = "top_bottom_edges";
		BORDER_MAP[BORDER_SIDES_TOP_LEFT_RIGHT] = "top_left_right_edges";
		BORDER_MAP[BORDER_MIXED_TOP_BR] = "top_edge_br_corner";
		BORDER_MAP[BORDER_MIXED_TOP_BL_BR] = "top_edge_bottom_corners";
		BORDER_MAP[BORDER_MIXED_TOP_RIGHT_BL] = "top_right_edge_bl_corner";
		BORDER_MAP[BORDER_CORNER_TR] = "one_corner";
		BORDER_MAP[BORDER_CORNERS_TL_TR] = "two_corners";
		BORDER_MAP[BORDER_CORNERS_BL_TR] = "opposite_corners";
		BORDER_MAP[BORDER_CORNERS_BL_TR_BR] = "three_corners";
		BORDER_MAP[BORDER_CORNERS_ALL] = "all_corners";
		BORDER_MAP[BORDER_NONE] = "full";
	}

	public static final TextureLayoutMap BORDER_LAYOUT = TextureLayoutMap.create(TextureLayout.BORDER_14, (s, v, i) -> s + "_" + BORDER_MAP[i]);

	public static final TextureSet GLASS_BORDER = TextureSet.builder()
			.displayNameToken("fancy_glass")
			.baseTextureName(Xb.id("block/glass").toString())
			.versionCount(1)
			.scale(SINGLE)
			.layout(BORDER_LAYOUT)
			.transform(IDENTITY)
			.renderIntent(OVERLAY_ONLY)
			.groups(STATIC_BORDERS)
			.renderNoBorderAsTile(true)
			.build(Xb.id("fancy_glass").toString());

	public static void initialize() {
		connectedGlass(Blocks.GLASS, "clear", "");

		connectedGlass(Blocks.WHITE_STAINED_GLASS, "white", ColorNames.WHITE);
		connectedGlass(Blocks.ORANGE_STAINED_GLASS, "orange", ColorNames.ORANGE);
		connectedGlass(Blocks.MAGENTA_STAINED_GLASS, "magenta", ColorNames.MAGENTA);
		connectedGlass(Blocks.LIGHT_BLUE_STAINED_GLASS, "light_blue", ColorNames.LIGHT_BLUE);

		connectedGlass(Blocks.YELLOW_STAINED_GLASS, "yellow", ColorNames.YELLOW);
		connectedGlass(Blocks.LIME_STAINED_GLASS, "lime", ColorNames.LIME);
		connectedGlass(Blocks.PINK_STAINED_GLASS, "pink", ColorNames.PINK);
		connectedGlass(Blocks.GRAY_STAINED_GLASS, "gray", ColorNames.GRAY);

		connectedGlass(Blocks.LIGHT_GRAY_STAINED_GLASS, "light_gray", ColorNames.LIGHT_GRAY);
		connectedGlass(Blocks.CYAN_STAINED_GLASS, "cyan", ColorNames.CYAN);
		connectedGlass(Blocks.PURPLE_STAINED_GLASS, "purple", ColorNames.PURPLE);
		connectedGlass(Blocks.BLUE_STAINED_GLASS, "blue", ColorNames.BLUE);

		connectedGlass(Blocks.BROWN_STAINED_GLASS, "brown", ColorNames.BROWN);
		connectedGlass(Blocks.GREEN_STAINED_GLASS, "green", ColorNames.GREEN);
		connectedGlass(Blocks.RED_STAINED_GLASS, "red", ColorNames.RED);
		connectedGlass(Blocks.BLACK_STAINED_GLASS, "black", ColorNames.BLACK);
	}

	private static void connectedGlass(Block block, String color, String id) {
		final ResourceLocation paintId = Xb.id(color + "_connected_glass");
		final XmPaint paint = XmPaintRegistry.INSTANCE.get(paintId);

		final Block connectedGlass = Xb.block(
				"g" + id,
				new GlassBlock(Block.Properties.copy(block)));

		final PrimitiveStateFunction stateFunc = PrimitiveStateFunction.ofDefaultState(
				Cube.INSTANCE.newState()
				.paintAll(paint)
				.releaseToImmutable());

		XmBlockRegistry.addBlock(connectedGlass, stateFunc);
	}
}
