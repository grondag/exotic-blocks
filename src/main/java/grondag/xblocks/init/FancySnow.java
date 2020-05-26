package grondag.xblocks.init;

import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.sound.BlockSoundGroup;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;

import grondag.xblocks.Xb;
import grondag.xblocks.block.BlockRegistrator;
import grondag.xblocks.block.SpeciesBlock;
import grondag.xm.api.block.XmBlockRegistry;
import grondag.xm.api.modelstate.primitive.PrimitiveStateFunction;
import grondag.xm.api.paint.XmPaint;
import grondag.xm.api.paint.XmPaintRegistry;
import grondag.xm.api.primitive.simple.Cube;

public enum FancySnow {
	;

	static final String ID_BASE = "fancy_snow";
	static final String ID_BLOCK = ID_BASE + "_block";

	public static final Material FANCY_SNOW_MATERIAL = (new Material.Builder(MaterialColor.WHITE)).build();
	public final static Block FANCY_SNOW_BLOCK = Xb.REG.block(ID_BLOCK, new Block(settings()));

	//	public static Block basalt_cool_dynamic_height = null;
	//	public static Block basalt_cool_dynamic_filler = null;
	//	public static Block basalt_cool_static_height = null;
	//	public static Block basalt_cool_static_filler = null;
	//	public static Block basalt_cut = null;

	static Block.Settings settings() {
		return FabricBlockSettings.of(FANCY_SNOW_MATERIAL).strength(0.2F, 0.2F).sounds(BlockSoundGroup.SNOW).breakByHand(true);
	}

	static {

		final XmPaint paint = XmPaintRegistry.INSTANCE.get(Xb.REG.id(ID_BASE));

		XmBlockRegistry.addBlock(FANCY_SNOW_BLOCK, PrimitiveStateFunction.ofDefaultState(
				Cube.INSTANCE.newState()
				.paintAll(paint)
				.releaseToImmutable()));

		BlockRegistrator.register(FANCY_SNOW_BLOCK, ID_BLOCK, ID_BASE, false);

		SpeciesBlock.species(FANCY_SNOW_BLOCK, ID_BLOCK + "_species", XmPaintRegistry.INSTANCE.get(Xb.REG.id(ID_BASE + "_species")));

		//		TerrainModelState.Mutable terrainModel;
		//
		//		terrainModel = TerrainSurface.HEIGHT.newState();
		//		terrainModel.paint(terrainModel.primitive().surfaces(terrainModel).get(0), paint);
		//		terrainModel.paint(terrainModel.primitive().surfaces(terrainModel).get(1), paint);
		//
		//		basalt_cool_dynamic_height = Xb.REG.block("drifting_snow_dynamic_height", new TerrainDynamicBlock(settings(), terrainModel.toImmutable(), false));
		//
		//		terrainModel.setStatic(true);
		//		basalt_cool_static_height = Xb.REG.block("drifting_snow_static_height", new TerrainDynamicBlock(settings(), terrainModel.toImmutable(), false));
		//
		//		terrainModel.release();
		//
		//
		//		terrainModel = TerrainSurface.FILLER.newState();
		//		terrainModel.paint(terrainModel.primitive().surfaces(terrainModel).get(0), paint);
		//		terrainModel.paint(terrainModel.primitive().surfaces(terrainModel).get(1), paint);
		//
		//		basalt_cool_dynamic_filler = Xb.REG.block("drifting_snow_dynamic_filler", new TerrainDynamicBlock(settings(), terrainModel.toImmutable(), true));
		//
		//		terrainModel.setStatic(true);
		//		basalt_cool_static_filler = Xb.REG.block("drifting_snow_static_filler", new TerrainDynamicBlock(settings(), terrainModel.toImmutable(), true));
		//
		//		terrainModel.release();
		//
		//		TerrainBlockRegistry.TERRAIN_STATE_REGISTRY.registerFiller(basalt_cool_dynamic_height, basalt_cool_dynamic_filler);
		//		TerrainBlockRegistry.TERRAIN_STATE_REGISTRY.registerFiller(basalt_cool_static_height, basalt_cool_static_filler);
		//
		//		TerrainBlockRegistry.TERRAIN_STATE_REGISTRY.registerStateTransition(basalt_cool_dynamic_height, basalt_cool_static_height);
		//		TerrainBlockRegistry.TERRAIN_STATE_REGISTRY.registerStateTransition(basalt_cool_dynamic_filler, basalt_cool_static_filler);
		//
		//		//FIXME: probably won't work as cube - 1.12 had a dedicated cubic terrain block
		//		terrainModel = TerrainCubePrimitive.INSTANCE.newState();
		//		terrainModel.paintAll(paint);
		//		terrainModel.setTerrainStateKey(TerrainState.FULL_BLOCK_STATE_KEY);
		//		basalt_cut = Xb.REG.block("drifting_snow_block", new TerrainDynamicBlock(settings(), terrainModel, false));
		//		terrainModel.release();
		//
		//		TerrainBlockRegistry.TERRAIN_STATE_REGISTRY.registerCubic(basalt_cool_dynamic_height, basalt_cut);
		//		TerrainBlockRegistry.TERRAIN_STATE_REGISTRY.registerCubic(basalt_cool_dynamic_filler, basalt_cut);
		//		TerrainBlockRegistry.TERRAIN_STATE_REGISTRY.registerCubic(basalt_cool_static_height, basalt_cut);
		//		TerrainBlockRegistry.TERRAIN_STATE_REGISTRY.registerCubic(basalt_cool_static_filler, basalt_cut);

	}
}
