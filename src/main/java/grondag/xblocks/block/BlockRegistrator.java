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

import net.minecraft.block.Block;
import net.minecraft.util.Identifier;

import grondag.fermion.color.ColorHelper;
import grondag.xblocks.Xb;
import grondag.xm.api.block.XmBlockRegistry;
import grondag.xm.api.block.base.StairLike;
import grondag.xm.api.modelstate.primitive.PrimitiveStateFunction;
import grondag.xm.api.paint.XmPaint;
import grondag.xm.api.paint.XmPaintRegistry;
import grondag.xm.api.primitive.simple.Stair;
import grondag.xm.api.primitive.simple.Wedge;
import grondag.xm.api.texture.TextureSet;

public class BlockRegistrator {
	public static void register(Block block, String name, String paintName, boolean coarse) {
		final XmPaint paint = XmPaintRegistry.INSTANCE.get(Xb.REG.id(paintName));
		final XmPaint paintInner = XmPaintRegistry.INSTANCE.get(Xb.REG.id(paintName + "_inner"));
		final XmPaint paintCut = XmPaintRegistry.INSTANCE.get(Xb.REG.id(paintName + "_cut"));
		register(block, name, paint, paint, paint, paintInner, paintCut, coarse);
	}

	public static void register(Block block, String name, XmPaint paint, boolean coarse) {
		final XmPaint paintInner = XmPaint.finder().copy(paint).textureColor(0, ColorHelper.multiplyRGB(paint.textureColor(0), 0.85f)).find();
		final XmPaint paintCut = XmPaint.finder().copy(paint).textureColor(0, ColorHelper.multiplyRGB(paint.textureColor(0), 0.92f)).find();
		register(block, name, paint, paint, paint, paintInner, paintCut, coarse);
	}

	public static void register(Block block, String name, TextureSet tex, boolean coarse) {
		final XmPaint paint = XmPaint.finder().texture(0, tex).find();
		final XmPaint paintInner = XmPaint.finder().copy(paint).textureColor(0, ColorHelper.multiplyRGB(paint.textureColor(0), 0.85f)).find();
		final XmPaint paintCut = XmPaint.finder().copy(paint).textureColor(0, ColorHelper.multiplyRGB(paint.textureColor(0), 0.92f)).find();

		register(block, name, paint, paint, paint, paintInner, paintCut, coarse);
	}

	public static void register(
			final Block block,
			String name,
			XmPaint paintBottom,
			XmPaint paintTop,
			XmPaint paintSide,
			boolean coarse) {
		final XmPaint paintInner = XmPaint.finder().copy(paintTop).textureColor(0, ColorHelper.multiplyRGB(paintTop.textureColor(0), 0.85f)).find();
		final XmPaint paintCut = XmPaint.finder().copy(paintSide).textureColor(0, ColorHelper.multiplyRGB(paintSide.textureColor(0), 0.92f)).find();

		register(block, name, paintBottom, paintTop, paintSide, paintInner, paintCut, coarse);
	}

	@SuppressWarnings("unused")
	public static void register(
			final Block blockIn,
			String name,
			XmPaint paintBottom,
			XmPaint paintTop,
			XmPaint paintSide,
			XmPaint paintInner,
			XmPaint paintCut,
			boolean coarse) {

		final Block wedge = Xb.REG.block(
				name + "_wedge_straight",
				new StairLike(blockIn.getDefaultState(), Block.Settings.copy(blockIn), StairLike.Shape.STRAIGHT));
		XmBlockRegistry.addBlockStates(wedge, bs -> PrimitiveStateFunction.builder()
				.withDefaultState(StairLike.MODELSTATE_FROM_BLOCKSTATE.mutate(
						Wedge.INSTANCE.newState()
						.paint(Wedge.SURFACE_BOTTOM, paintBottom)
						.paint(Wedge.SURFACE_BACK, paintSide)
						.paint(Wedge.SURFACE_TOP, paintTop)
						.paint(Wedge.SURFACE_SIDES, paintSide), bs))
				.build());

		final Block insideWedge = Xb.REG.block(
				name + "_wedge_inside",
				new StairLike(blockIn.getDefaultState(), Block.Settings.copy(blockIn), StairLike.Shape.INSIDE_CORNER));
		XmBlockRegistry.addBlockStates(insideWedge, bs -> PrimitiveStateFunction.builder()
				.withDefaultState(StairLike.MODELSTATE_FROM_BLOCKSTATE.mutate(
						Wedge.INSTANCE.newState()
						.paint(Wedge.SURFACE_BOTTOM, paintBottom)
						.paint(Wedge.SURFACE_BACK, paintSide)
						.paint(Wedge.SURFACE_TOP, paintTop)
						.paint(Wedge.SURFACE_SIDES, paintSide), bs))
				.build());

		final Block outsideWedge = Xb.REG.block(
				name + "_wedge_outside",
				new StairLike(blockIn.getDefaultState(), Block.Settings.copy(blockIn), StairLike.Shape.OUTSIDE_CORNER));
		XmBlockRegistry.addBlockStates(outsideWedge, bs -> PrimitiveStateFunction.builder()
				.withDefaultState(StairLike.MODELSTATE_FROM_BLOCKSTATE.mutate(
						Wedge.INSTANCE.newState()
						.paint(Wedge.SURFACE_BOTTOM, paintBottom)
						.paint(Wedge.SURFACE_BACK, paintSide)
						.paint(Wedge.SURFACE_TOP, paintTop)
						.paint(Wedge.SURFACE_SIDES, paintSide), bs))
				.build());

		final Block omniStair = Xb.REG.block(
				name + "_omni_stair",
				new StairLike(blockIn.getDefaultState(), Block.Settings.copy(blockIn), StairLike.Shape.STRAIGHT));
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

		final Block omniStairInside = Xb.REG.block(
				name + "_omni_stair_inside",
				new StairLike(blockIn.getDefaultState(), Block.Settings.copy(blockIn), StairLike.Shape.INSIDE_CORNER));
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

		final Block omniStairOutside = Xb.REG.block(
				name + "_omni_stair_outside",
				new StairLike(blockIn.getDefaultState(), Block.Settings.copy(blockIn), StairLike.Shape.OUTSIDE_CORNER));
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

		final Block squareInsetColumn = BlockMaker.squareInsetColumn(name, blockIn, paintTop, paintSide, paintCut, paintInner, coarse ? 1 : 3, 0);
		final Block roundColume = BlockMaker.roundColumn(name, blockIn, paintTop, paintSide, paintCut, paintInner);
		final Block cappedRoundColumn = BlockMaker.cappedRoundColumn(name, blockIn, paintTop, paintSide, paintCut, paintInner);
		final Block cappedSquareColumn = BlockMaker.cappedSquareColumn(name, blockIn, paintTop, paintSide, paintCut, paintInner, 0);
		final Block insetPanel = BlockMaker.insetPanel(name, blockIn, paintTop, paintCut, paintInner, 0);
		final Block cutRoundColumn = BlockMaker.cutRoundColumn(name, blockIn, paintTop, paintSide, paintCut, paintInner, 0);
		final Block roundCappedRoundColumn = BlockMaker.roundCappedRoundColumn(name, blockIn, paintTop, paintSide, paintCut, paintInner);
		final Block wedgeCap =  BlockMaker.wedgeCap(name, blockIn, paintTop, paintBottom);

		XmPaint glow = XmPaintRegistry.INSTANCE.get(new Identifier("exotic-blocks:prismarine_glow"));

		final Block prismarineInsetPanel =  BlockMaker.insetPanel(name + "_prismarine", blockIn, paintTop, paintSide, glow, 0);
		final Block prismarineFlatPanel = BlockMaker.flatPanel(name + "_prismarine", blockIn, paintTop, glow, 0);
		final Block prismarineSquareInsetColumn = BlockMaker.squareInsetColumn(name + "_prismarine", blockIn, paintTop, paintSide, paintSide, glow, coarse ? 2 : 3, 0);
		final Block prismarineCappedSquareColumn = BlockMaker.cappedSquareColumn(name + "_prismarine", blockIn, paintTop, paintSide, paintSide, glow, 0);
		final Block prismarineCutRoundColumn = BlockMaker.cutRoundColumn(name + "_prismarine", blockIn, paintTop, paintSide, paintSide, glow, 0);

		final Block prismarineInsetPanelLamp = BlockMaker.insetPanel(name + "_prismarine_lamp", blockIn, paintTop, paintSide, glow, 15);
		final Block prismarineFlatPanelLamp = BlockMaker.flatPanel(name + "_prismarine_lamp", blockIn, paintTop, glow, 15);
		final Block prismarineSquareInsetColumnLamp = BlockMaker.squareInsetColumn(name + "_prismarine_lamp", blockIn, paintTop, paintSide, paintSide, glow, coarse ? 2 : 3, 15);
		final Block prismarineCappedSquareColumnLamp = BlockMaker.cappedSquareColumn(name + "_prismarine_lamp", blockIn, paintTop, paintSide, paintSide, glow, 15);
		final Block prismarineCutRoundColumnLamp = BlockMaker.cutRoundColumn(name + "_prismarine_lamp", blockIn, paintTop, paintSide, paintSide, glow, 15);

		BlockConnectors.connect(prismarineInsetPanel, prismarineInsetPanelLamp);
		BlockConnectors.connect(prismarineFlatPanel, prismarineFlatPanelLamp);
		BlockConnectors.connect(prismarineSquareInsetColumn, prismarineSquareInsetColumnLamp);
		BlockConnectors.connect(prismarineCappedSquareColumn, prismarineCappedSquareColumnLamp);
		BlockConnectors.connect(prismarineCutRoundColumn, prismarineCutRoundColumnLamp);

		glow = XmPaintRegistry.INSTANCE.get(new Identifier("exotic-blocks:glowstone_glow"));

		final Block glowstoneInsetPanel = BlockMaker.insetPanel(name + "_glowstone", blockIn, paintTop, paintSide, glow, 0);
		final Block glowstoneFlatPanel = BlockMaker.flatPanel(name + "_glowstone", blockIn, paintTop, glow, 0);
		final Block glowstoneSquareInsetColumn = BlockMaker.squareInsetColumn(name + "_glowstone", blockIn, paintTop, paintSide, paintSide, glow, coarse ? 1 : 2, 0);
		final Block glowstoneCappedSquareColumn = BlockMaker.cappedSquareColumn(name + "_glowstone", blockIn, paintTop, paintSide, paintSide, glow, 0);
		final Block glowstoneCutRoundColumn = BlockMaker.cutRoundColumn(name + "_glowstone", blockIn, paintTop, paintSide, paintSide, glow, 0);

		final Block glowstoneInsetPanelLamp = BlockMaker.insetPanel(name + "_glowstone_lamp", blockIn, paintTop, paintSide, glow, 15);
		final Block glowstoneFlatPanelLamp = BlockMaker.flatPanel(name + "_glowstone_lamp", blockIn, paintTop, glow, 15);
		final Block glowstoneSquareInsetColumnLamp = BlockMaker.squareInsetColumn(name + "_glowstone_lamp", blockIn, paintTop, paintSide, paintSide, glow, coarse ? 1 : 2, 15);
		final Block glowstoneCappedSquareColumnLamp = BlockMaker.cappedSquareColumn(name + "_glowstone_lamp", blockIn, paintTop, paintSide, paintSide, glow, 15);
		final Block glowstoneCutRoundColumnLamp = BlockMaker.cutRoundColumn(name + "_glowstone_lamp", blockIn, paintTop, paintSide, paintSide, glow, 15);

		BlockConnectors.connect(glowstoneInsetPanel, glowstoneInsetPanelLamp);
		BlockConnectors.connect(glowstoneFlatPanel, glowstoneFlatPanelLamp);
		BlockConnectors.connect(glowstoneSquareInsetColumn, glowstoneSquareInsetColumnLamp);
		BlockConnectors.connect(glowstoneCappedSquareColumn, glowstoneCappedSquareColumnLamp);
		BlockConnectors.connect(glowstoneCutRoundColumn, glowstoneCutRoundColumnLamp);

		glow = XmPaintRegistry.INSTANCE.get(new Identifier("exotic-blocks:lamp_glow"));

		final Block insetPanelLamp = BlockMaker.insetPanel(name + "_lamp", blockIn, paintTop, paintSide, glow, 15);
		final Block flatPanelLamp = BlockMaker.flatPanel(name + "_lamp", blockIn, paintTop, glow, 15);
		final Block squareInsetColumnLamp = BlockMaker.squareInsetColumn(name + "_lamp", blockIn, paintTop, paintSide, paintSide, glow, coarse ? 1 : 2, 15);
		final Block cappedSquareColumnLamp = BlockMaker.cappedSquareColumn(name + "_lamp", blockIn, paintTop, paintSide, paintSide, glow, 15);
		final Block cutRoundColumnLamp = BlockMaker.cutRoundColumn(name + "_lamp", blockIn, paintTop, paintSide, paintSide, glow, 15);
	}
}
