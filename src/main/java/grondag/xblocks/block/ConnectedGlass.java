/*******************************************************************************
 * Copyright 2019 grondag
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package grondag.xblocks.block;

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

import grondag.xblocks.Xb;
import grondag.xm.api.block.XmBlockRegistry;
import grondag.xm.api.modelstate.primitive.PrimitiveStateFunction;
import grondag.xm.api.paint.XmPaint;
import grondag.xm.api.paint.XmPaintRegistry;
import grondag.xm.api.primitive.simple.Cube;
import grondag.xm.api.texture.TextureLayout;
import grondag.xm.api.texture.TextureLayoutMap;
import grondag.xm.api.texture.TextureSet;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.GlassBlock;
import net.minecraft.util.Identifier;

/**
 * Tests adding connected textures to existing blocks by giving glass a custom connected texture.
 */
public class ConnectedGlass {
	private final static String[] BORDER_MAP = new String[TextureLayout.BORDER_14.textureCount];

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
		.baseTextureName(Xb.idString("blocks/glass"))
		.versionCount(1)
		.scale(SINGLE)
		.layout(BORDER_LAYOUT)
		.transform(IDENTITY)
		.renderIntent(OVERLAY_ONLY)
		.groups(STATIC_BORDERS)
		.renderNoBorderAsTile(true)
		.build(Xb.idString("fancy_glass"));

	public static void init() {
		connectedGlass(Blocks.GLASS, "clear");

		connectedGlass(Blocks.WHITE_STAINED_GLASS, "white");
		connectedGlass(Blocks.ORANGE_STAINED_GLASS, "orange");
		connectedGlass(Blocks.MAGENTA_STAINED_GLASS, "magenta");
		connectedGlass(Blocks.LIGHT_BLUE_STAINED_GLASS, "light_blue");

		connectedGlass(Blocks.YELLOW_STAINED_GLASS, "yellow");
		connectedGlass(Blocks.LIME_STAINED_GLASS, "lime");
		connectedGlass(Blocks.PINK_STAINED_GLASS, "pink");
		connectedGlass(Blocks.GRAY_STAINED_GLASS, "gray");

		connectedGlass(Blocks.LIGHT_GRAY_STAINED_GLASS, "light_gray");
		connectedGlass(Blocks.CYAN_STAINED_GLASS, "cyan");
		connectedGlass(Blocks.PURPLE_STAINED_GLASS, "purple");
		connectedGlass(Blocks.BLUE_STAINED_GLASS, "blue");

		connectedGlass(Blocks.BROWN_STAINED_GLASS, "brown");
		connectedGlass(Blocks.GREEN_STAINED_GLASS, "green");
		connectedGlass(Blocks.RED_STAINED_GLASS, "red");
		connectedGlass(Blocks.BLACK_STAINED_GLASS, "black");
	}

	private static void connectedGlass(Block block, String color) {
		final Identifier paintId = Xb.id(color + "_connected_glass");
		final XmPaint paint = XmPaintRegistry.INSTANCE.get(paintId);

		final Block connectedGlass = Xb.register(
			new GlassBlock(Block.Settings.copy(block)),
			color + "_connected_glass");

		final PrimitiveStateFunction stateFunc = PrimitiveStateFunction.ofDefaultState(
			Cube.INSTANCE.newState()
			.paintAll(paint)
			.releaseToImmutable());

		XmBlockRegistry.addBlock(connectedGlass, stateFunc);
	}
}
