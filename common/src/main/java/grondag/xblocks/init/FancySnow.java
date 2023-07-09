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

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MapColor;

import grondag.xblocks.Xb;
import grondag.xblocks.block.ShapedBlockRegistrator;
import grondag.xblocks.block.SpeciesBlock;
import grondag.xblocks.data.BlockNames;
import grondag.xblocks.data.ShapedBlockNames;
import grondag.xm.api.block.XmBlockRegistry;
import grondag.xm.api.modelstate.primitive.PrimitiveStateFunction;
import grondag.xm.api.paint.XmPaint;
import grondag.xm.api.paint.XmPaintRegistry;
import grondag.xm.api.primitive.simple.Cube;

public abstract class FancySnow {
	private FancySnow() { }

	public static final Block FANCY_SNOW_BLOCK = Xb.block(BlockNames.BLOCK_FANCY_SNOW, new Block(settings()));

	//	public static Block basalt_cool_dynamic_height = null;
	//	public static Block basalt_cool_dynamic_filler = null;
	//	public static Block basalt_cool_static_height = null;
	//	public static Block basalt_cool_static_filler = null;
	//	public static Block basalt_cut = null;

	static Block.Properties settings() {
		return Block.Properties.of().strength(0.2F, 0.2F).sound(SoundType.SNOW).mapColor(MapColor.SNOW);
	}

	public static void initialize() {
		final String PAINT_FANCY_SNOW = "fancy_snow";
		final String PAINT_CONNECTED_FANCY_SNOW = "fancy_snow_species";

		final XmPaint paint = XmPaintRegistry.INSTANCE.get(Xb.id(PAINT_FANCY_SNOW));

		XmBlockRegistry.addBlock(FANCY_SNOW_BLOCK, PrimitiveStateFunction.ofDefaultState(
				Cube.INSTANCE.newState()
				.paintAll(paint)
				.releaseToImmutable()));

		ShapedBlockRegistrator.registerShapes(FANCY_SNOW_BLOCK, ShapedBlockNames.SHAPED_FANCY_SNOW, PAINT_FANCY_SNOW, false);

		SpeciesBlock.species(FANCY_SNOW_BLOCK, BlockNames.BLOCK_CONNECTED_FANCY_SNOW, XmPaintRegistry.INSTANCE.get(Xb.id(PAINT_CONNECTED_FANCY_SNOW)));

		//		TerrainModelState.Mutable terrainModel;
		//
		//		terrainModel = TerrainSurface.HEIGHT.newState();
		//		terrainModel.paint(terrainModel.primitive().surfaces(terrainModel).get(0), paint);
		//		terrainModel.paint(terrainModel.primitive().surfaces(terrainModel).get(1), paint);
		//
		//		final Block dynamicHeight = Xb.REG.block("drifting_snow_dynamic_height", new TerrainDynamicBlock(settings(), terrainModel.toImmutable(), false));
		//
		//		terrainModel.setStatic(true);
		//		final Block staticHeight = Xb.REG.block("drifting_snow_static_height", new TerrainDynamicBlock(settings(), terrainModel.toImmutable(), false));
		//
		//		terrainModel.release();
		//
		//		terrainModel = TerrainSurface.FILLER.newState();
		//		terrainModel.paint(terrainModel.primitive().surfaces(terrainModel).get(0), paint);
		//		terrainModel.paint(terrainModel.primitive().surfaces(terrainModel).get(1), paint);
		//
		//		final Block dynamicFiller = Xb.REG.block("drifting_snow_dynamic_filler", new TerrainDynamicBlock(settings(), terrainModel.toImmutable(), true));
		//
		//		terrainModel.setStatic(true);
		//		final Block staticFiller = Xb.REG.block("drifting_snow_static_filler", new TerrainDynamicBlock(settings(), terrainModel.toImmutable(), true));
		//
		//		terrainModel.release();
		//
		//		TerrainBlockRegistry.TERRAIN_STATE_REGISTRY.registerFiller(dynamicHeight, dynamicFiller);
		//		TerrainBlockRegistry.TERRAIN_STATE_REGISTRY.registerFiller(staticHeight, staticFiller);
		//
		//		TerrainBlockRegistry.TERRAIN_STATE_REGISTRY.registerStateTransition(dynamicHeight, staticHeight);
		//		TerrainBlockRegistry.TERRAIN_STATE_REGISTRY.registerStateTransition(dynamicFiller, staticFiller);
		//
		//		//FIXME: probably won't work as cube - 1.12 had a dedicated cubic terrain block
		//		terrainModel = TerrainCubePrimitive.INSTANCE.newState();
		//		terrainModel.paintAll(paint);
		//		terrainModel.setTerrainStateKey(TerrainState.FULL_BLOCK_STATE_KEY);
		//		final Block cubic = Xb.REG.block("drifting_snow_block", new TerrainDynamicBlock(settings(), terrainModel, false));
		//		terrainModel.release();
		//
		//		TerrainBlockRegistry.TERRAIN_STATE_REGISTRY.registerCubic(dynamicHeight, cubic);
		//		TerrainBlockRegistry.TERRAIN_STATE_REGISTRY.registerCubic(dynamicFiller, cubic);
		//		TerrainBlockRegistry.TERRAIN_STATE_REGISTRY.registerCubic(staticHeight, cubic);
		//		TerrainBlockRegistry.TERRAIN_STATE_REGISTRY.registerCubic(staticFiller, cubic);
	}
}
