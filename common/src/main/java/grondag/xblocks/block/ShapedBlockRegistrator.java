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

package grondag.xblocks.block;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

import grondag.xblocks.Xb;
import grondag.xblocks.data.Inlays;
import grondag.xblocks.data.Shapes;
import grondag.xm.api.block.XmBlockRegistry;
import grondag.xm.api.block.base.StairLike;
import grondag.xm.api.connect.world.BlockConnectors;
import grondag.xm.api.modelstate.primitive.PrimitiveStateFunction;
import grondag.xm.api.paint.XmPaint;
import grondag.xm.api.paint.XmPaintRegistry;
import grondag.xm.api.primitive.simple.Stair;
import grondag.xm.api.primitive.simple.Wedge;
import grondag.xm.api.texture.TextureSet;
import grondag.xm.api.util.ColorUtil;

public class ShapedBlockRegistrator {
	public static void registerShapes(Block block, String name, String paintName, boolean coarse) {
		final XmPaint paint = XmPaintRegistry.INSTANCE.get(Xb.id(paintName));
		final XmPaint paintInner = XmPaintRegistry.INSTANCE.get(Xb.id(paintName + "_inner"));
		final XmPaint paintCut = XmPaintRegistry.INSTANCE.get(Xb.id(paintName + "_cut"));
		registerShapes(block, name, paint, paint, paint, paintInner, paintCut, coarse);
	}

	public static void registerShapes(Block block, String name, XmPaint paint, boolean coarse) {
		final XmPaint paintInner = XmPaint.finder().copy(paint).textureColor(0, ColorUtil.multiplyRGB(paint.textureColor(0), 0.85f)).find();
		final XmPaint paintCut = XmPaint.finder().copy(paint).textureColor(0, ColorUtil.multiplyRGB(paint.textureColor(0), 0.92f)).find();
		registerShapes(block, name, paint, paint, paint, paintInner, paintCut, coarse);
	}

	public static void registerShapes(Block block, String name, TextureSet tex, boolean coarse) {
		final XmPaint paint = XmPaint.finder().texture(0, tex).find();
		final XmPaint paintInner = XmPaint.finder().copy(paint).textureColor(0, ColorUtil.multiplyRGB(paint.textureColor(0), 0.85f)).find();
		final XmPaint paintCut = XmPaint.finder().copy(paint).textureColor(0, ColorUtil.multiplyRGB(paint.textureColor(0), 0.92f)).find();

		registerShapes(block, name, paint, paint, paint, paintInner, paintCut, coarse);
	}

	public static void registerShapes(
			final Block block,
			String name,
			XmPaint paintBottom,
			XmPaint paintTop,
			XmPaint paintSide,
			boolean coarse) {
		final XmPaint paintInner = XmPaint.finder().copy(paintTop).textureColor(0, ColorUtil.multiplyRGB(paintTop.textureColor(0), 0.85f)).find();
		final XmPaint paintCut = XmPaint.finder().copy(paintSide).textureColor(0, ColorUtil.multiplyRGB(paintSide.textureColor(0), 0.92f)).find();

		registerShapes(block, name, paintBottom, paintTop, paintSide, paintInner, paintCut, coarse);
	}

	@SuppressWarnings("unused")
	public static void registerShapes(
			final Block blockIn,
			String name,
			XmPaint paintBottom,
			XmPaint paintTop,
			XmPaint paintSide,
			XmPaint paintInner,
			XmPaint paintCut,
			boolean coarse
	) {
		final Block wedge = Xb.block(
				name + Shapes.WEDGE,
				new StairLike(blockIn.defaultBlockState(), Block.Properties.copy(blockIn), StairLike.Shape.STRAIGHT, Xb.modifyKey::isPressed, Xb.forceKey::isPressed));
		XmBlockRegistry.addBlockStates(wedge, bs -> PrimitiveStateFunction.builder()
				.withDefaultState(StairLike.MODELSTATE_FROM_BLOCKSTATE.mutate(
						Wedge.INSTANCE.newState()
						.paint(Wedge.SURFACE_BOTTOM, paintBottom)
						.paint(Wedge.SURFACE_BACK, paintSide)
						.paint(Wedge.SURFACE_TOP, paintTop)
						.paint(Wedge.SURFACE_SIDES, paintSide), bs))
				.build());

		final Block insideWedge = Xb.block(
				name + Shapes.INSIDE_WEDGE,
				new StairLike(blockIn.defaultBlockState(), Block.Properties.copy(blockIn), StairLike.Shape.INSIDE_CORNER, Xb.modifyKey::isPressed, Xb.forceKey::isPressed));
		XmBlockRegistry.addBlockStates(insideWedge, bs -> PrimitiveStateFunction.builder()
				.withDefaultState(StairLike.MODELSTATE_FROM_BLOCKSTATE.mutate(
						Wedge.INSTANCE.newState()
						.paint(Wedge.SURFACE_BOTTOM, paintBottom)
						.paint(Wedge.SURFACE_BACK, paintSide)
						.paint(Wedge.SURFACE_TOP, paintTop)
						.paint(Wedge.SURFACE_SIDES, paintSide), bs))
				.build());

		final Block outsideWedge = Xb.block(
				name + Shapes.OUTSIDE_WEDGE,
				new StairLike(blockIn.defaultBlockState(), Block.Properties.copy(blockIn), StairLike.Shape.OUTSIDE_CORNER, Xb.modifyKey::isPressed, Xb.forceKey::isPressed));
		XmBlockRegistry.addBlockStates(outsideWedge, bs -> PrimitiveStateFunction.builder()
				.withDefaultState(StairLike.MODELSTATE_FROM_BLOCKSTATE.mutate(
						Wedge.INSTANCE.newState()
						.paint(Wedge.SURFACE_BOTTOM, paintBottom)
						.paint(Wedge.SURFACE_BACK, paintSide)
						.paint(Wedge.SURFACE_TOP, paintTop)
						.paint(Wedge.SURFACE_SIDES, paintSide), bs))
				.build());

		final Block omniStair = Xb.block(
				name + Shapes.STAIR,
				new StairLike(blockIn.defaultBlockState(), Block.Properties.copy(blockIn), StairLike.Shape.STRAIGHT, Xb.modifyKey::isPressed, Xb.forceKey::isPressed));
		XmBlockRegistry.addBlockStates(omniStair, bs -> PrimitiveStateFunction.builder()
				.withDefaultState(StairLike.MODELSTATE_FROM_BLOCKSTATE.mutate(
						Stair.INSTANCE.newState()
						.paint(Stair.SURFACE_BOTTOM, paintBottom)
						.paint(Stair.SURFACE_BACK, paintSide)
						.paint(Stair.SURFACE_TOP, paintTop)
						.paint(Stair.SURFACE_FRONT, paintSide)
						.paint(Stair.SURFACE_RIGHT, paintSide)
						.paint(Stair.SURFACE_LEFT, paintSide), bs))
				.build());

		final Block omniStairInside = Xb.block(
				name + Shapes.INSIDE_STAIR,
				new StairLike(blockIn.defaultBlockState(), Block.Properties.copy(blockIn), StairLike.Shape.INSIDE_CORNER, Xb.modifyKey::isPressed, Xb.forceKey::isPressed));
		XmBlockRegistry.addBlockStates(omniStairInside, bs -> PrimitiveStateFunction.builder()
				.withDefaultState(StairLike.MODELSTATE_FROM_BLOCKSTATE.mutate(
						Stair.INSTANCE.newState()
						.paint(Stair.SURFACE_BOTTOM, paintBottom)
						.paint(Stair.SURFACE_BACK, paintSide)
						.paint(Stair.SURFACE_TOP, paintTop)
						.paint(Stair.SURFACE_FRONT, paintSide)
						.paint(Stair.SURFACE_RIGHT, paintSide)
						.paint(Stair.SURFACE_LEFT, paintSide), bs))
				.build());

		final Block omniStairOutside = Xb.block(
				name + Shapes.OUTSIDE_STAIR,
				new StairLike(blockIn.defaultBlockState(), Block.Properties.copy(blockIn), StairLike.Shape.OUTSIDE_CORNER, Xb.modifyKey::isPressed, Xb.forceKey::isPressed));
		XmBlockRegistry.addBlockStates(omniStairOutside, bs -> PrimitiveStateFunction.builder()
				.withDefaultState(StairLike.MODELSTATE_FROM_BLOCKSTATE.mutate(
						Stair.INSTANCE.newState()
						.paint(Stair.SURFACE_BOTTOM, paintBottom)
						.paint(Stair.SURFACE_BACK, paintSide)
						.paint(Stair.SURFACE_TOP, paintTop)
						.paint(Stair.SURFACE_FRONT, paintSide)
						.paint(Stair.SURFACE_RIGHT, paintSide)
						.paint(Stair.SURFACE_LEFT, paintSide), bs))
				.build());

		final Block squareInsetColumn = ShapedBlockMaker.squareInsetColumn(name, "", blockIn, paintTop, paintSide, paintCut, paintInner, coarse ? 1 : 3, 0);
		final Block roundColume = ShapedBlockMaker.roundColumn(name, "", blockIn, paintTop, paintSide, paintCut, paintInner);
		final Block cappedRoundColumn = ShapedBlockMaker.cappedRoundColumn(name, "", blockIn, paintTop, paintSide, paintCut, paintInner);
		final Block cappedSquareColumn = ShapedBlockMaker.cappedSquareColumn(name, "", blockIn, paintTop, paintSide, paintCut, paintInner, 0);
		final Block insetPanel = ShapedBlockMaker.insetPanel(name, "", blockIn, paintTop, paintCut, paintInner, 0);
		final Block cutRoundColumn = ShapedBlockMaker.cutRoundColumn(name, "", blockIn, paintTop, paintSide, paintCut, paintInner, 0);
		final Block roundCappedRoundColumn = ShapedBlockMaker.roundCappedRoundColumn(name, "", blockIn, paintTop, paintSide, paintCut, paintInner);
		final Block wedgeCap = ShapedBlockMaker.wedgeCap(name, "", blockIn, paintTop, paintBottom);
		final Block slab = ShapedBlockMaker.slab(name, "", blockIn, paintTop, paintBottom, paintSide);

		XmPaint glow = XmPaintRegistry.INSTANCE.get(new ResourceLocation("xb:prismarine_glow"));

		final Block prismarineInsetPanel = ShapedBlockMaker.insetPanel(name, Inlays.PRISMARINE_GLOW, blockIn, paintTop, paintSide, glow, 0);
		final Block prismarineFlatPanel = ShapedBlockMaker.flatPanel(name, Inlays.PRISMARINE_GLOW, blockIn, paintTop, glow, 0);
		final Block prismarineSquareInsetColumn = ShapedBlockMaker.squareInsetColumn(name, Inlays.PRISMARINE_GLOW, blockIn, paintTop, paintSide, paintSide, glow, coarse ? 2 : 3, 0);
		final Block prismarineCappedSquareColumn = ShapedBlockMaker.cappedSquareColumn(name, Inlays.PRISMARINE_GLOW, blockIn, paintTop, paintSide, paintSide, glow, 0);
		final Block prismarineCutRoundColumn = ShapedBlockMaker.cutRoundColumn(name, Inlays.PRISMARINE_GLOW, blockIn, paintTop, paintSide, paintSide, glow, 0);

		final Block prismarineInsetPanelLamp = ShapedBlockMaker.insetPanel(name, Inlays.PRISMARINE_LAMP, blockIn, paintTop, paintSide, glow, 15);
		final Block prismarineFlatPanelLamp = ShapedBlockMaker.flatPanel(name, Inlays.PRISMARINE_LAMP, blockIn, paintTop, glow, 15);
		final Block prismarineSquareInsetColumnLamp = ShapedBlockMaker.squareInsetColumn(name, Inlays.PRISMARINE_LAMP, blockIn, paintTop, paintSide, paintSide, glow, coarse ? 2 : 3, 15);
		final Block prismarineCappedSquareColumnLamp = ShapedBlockMaker.cappedSquareColumn(name, Inlays.PRISMARINE_LAMP, blockIn, paintTop, paintSide, paintSide, glow, 15);
		final Block prismarineCutRoundColumnLamp = ShapedBlockMaker.cutRoundColumn(name, Inlays.PRISMARINE_LAMP, blockIn, paintTop, paintSide, paintSide, glow, 15);

		BlockConnectors.connect(prismarineInsetPanel, prismarineInsetPanelLamp);
		BlockConnectors.connect(prismarineFlatPanel, prismarineFlatPanelLamp);
		BlockConnectors.connect(prismarineSquareInsetColumn, prismarineSquareInsetColumnLamp);
		BlockConnectors.connect(prismarineCappedSquareColumn, prismarineCappedSquareColumnLamp);
		BlockConnectors.connect(prismarineCutRoundColumn, prismarineCutRoundColumnLamp);

		glow = XmPaintRegistry.INSTANCE.get(new ResourceLocation("xb:glowstone_glow"));

		final Block glowstoneInsetPanel = ShapedBlockMaker.insetPanel(name, Inlays.GLOWSTONE_GLOW, blockIn, paintTop, paintSide, glow, 0);
		final Block glowstoneFlatPanel = ShapedBlockMaker.flatPanel(name, Inlays.GLOWSTONE_GLOW, blockIn, paintTop, glow, 0);
		final Block glowstoneSquareInsetColumn = ShapedBlockMaker.squareInsetColumn(name, Inlays.GLOWSTONE_GLOW, blockIn, paintTop, paintSide, paintSide, glow, coarse ? 1 : 2, 0);
		final Block glowstoneCappedSquareColumn = ShapedBlockMaker.cappedSquareColumn(name, Inlays.GLOWSTONE_GLOW, blockIn, paintTop, paintSide, paintSide, glow, 0);
		final Block glowstoneCutRoundColumn = ShapedBlockMaker.cutRoundColumn(name, Inlays.GLOWSTONE_GLOW, blockIn, paintTop, paintSide, paintSide, glow, 0);

		final Block glowstoneInsetPanelLamp = ShapedBlockMaker.insetPanel(name, Inlays.GLOWSTONE_LAMP, blockIn, paintTop, paintSide, glow, 15);
		final Block glowstoneFlatPanelLamp = ShapedBlockMaker.flatPanel(name, Inlays.GLOWSTONE_LAMP, blockIn, paintTop, glow, 15);
		final Block glowstoneSquareInsetColumnLamp = ShapedBlockMaker.squareInsetColumn(name, Inlays.GLOWSTONE_LAMP, blockIn, paintTop, paintSide, paintSide, glow, coarse ? 1 : 2, 15);
		final Block glowstoneCappedSquareColumnLamp = ShapedBlockMaker.cappedSquareColumn(name, Inlays.GLOWSTONE_LAMP, blockIn, paintTop, paintSide, paintSide, glow, 15);
		final Block glowstoneCutRoundColumnLamp = ShapedBlockMaker.cutRoundColumn(name, Inlays.GLOWSTONE_LAMP, blockIn, paintTop, paintSide, paintSide, glow, 15);

		BlockConnectors.connect(glowstoneInsetPanel, glowstoneInsetPanelLamp);
		BlockConnectors.connect(glowstoneFlatPanel, glowstoneFlatPanelLamp);
		BlockConnectors.connect(glowstoneSquareInsetColumn, glowstoneSquareInsetColumnLamp);
		BlockConnectors.connect(glowstoneCappedSquareColumn, glowstoneCappedSquareColumnLamp);
		BlockConnectors.connect(glowstoneCutRoundColumn, glowstoneCutRoundColumnLamp);

		glow = XmPaintRegistry.INSTANCE.get(new ResourceLocation("xb:lamp_glow"));

		final Block insetPanelLamp = ShapedBlockMaker.insetPanel(name, Inlays.WHITE_LAMP, blockIn, paintTop, paintSide, glow, 15);
		final Block flatPanelLamp = ShapedBlockMaker.flatPanel(name, Inlays.WHITE_LAMP, blockIn, paintTop, glow, 15);
		final Block squareInsetColumnLamp = ShapedBlockMaker.squareInsetColumn(name, Inlays.WHITE_LAMP, blockIn, paintTop, paintSide, paintSide, glow, coarse ? 1 : 2, 15);
		final Block cappedSquareColumnLamp = ShapedBlockMaker.cappedSquareColumn(name, Inlays.WHITE_LAMP, blockIn, paintTop, paintSide, paintSide, glow, 15);
		final Block cutRoundColumnLamp = ShapedBlockMaker.cutRoundColumn(name, Inlays.WHITE_LAMP, blockIn, paintTop, paintSide, paintSide, glow, 15);
	}
}
