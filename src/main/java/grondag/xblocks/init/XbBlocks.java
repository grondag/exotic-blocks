package grondag.xblocks.init;

import static grondag.xblocks.block.BlockRegistrator.register;
import static grondag.xblocks.block.FestiveLightsBlock.BLUE;
import static grondag.xblocks.block.FestiveLightsBlock.COOL_WHITE;
import static grondag.xblocks.block.FestiveLightsBlock.CYAN;
import static grondag.xblocks.block.FestiveLightsBlock.GREEN;
import static grondag.xblocks.block.FestiveLightsBlock.MAGENTA;
import static grondag.xblocks.block.FestiveLightsBlock.PURPLE;
import static grondag.xblocks.block.FestiveLightsBlock.RED;
import static grondag.xblocks.block.FestiveLightsBlock.WARM_WHITE;
import static grondag.xblocks.block.FestiveLightsBlock.YELLOW;

import java.util.ArrayList;
import java.util.function.Function;

import net.minecraft.block.Blocks;

import grondag.xblocks.Xb;
import grondag.xblocks.block.FestiveLightsBlock;
import grondag.xm.api.paint.XmPaint;
import grondag.xm.api.texture.McTextures;

public enum XbBlocks {
	;
	public static final ArrayList<String> FESTIVE_LIGHTS = new ArrayList<>();

	static {
		register(Blocks.ACACIA_PLANKS, "acacia", McTextures.ACACIA_PLANKS, false);
		register(Blocks.BIRCH_PLANKS, "birch", McTextures.BIRCH_PLANKS, false);
		register(Blocks.OAK_PLANKS, "oak", McTextures.OAK_PLANKS, false);
		register(Blocks.DARK_OAK_PLANKS, "dark_oak", McTextures.DARK_OAK_PLANKS, false);
		register(Blocks.JUNGLE_PLANKS, "jungle", McTextures.JUNGLE_PLANKS, false);
		register(Blocks.SPRUCE_PLANKS, "spruce", McTextures.SPRUCE_PLANKS, false);


		///
		register(Blocks.ACACIA_WOOD, "acacia_wood", McTextures.ACACIA_LOG, false);

		register(Blocks.ACACIA_LOG, "acacia_log",
				XmPaint.finder().texture(0, McTextures.ACACIA_LOG_TOP).find(),
				XmPaint.finder().texture(0, McTextures.ACACIA_LOG_TOP).find(),
				XmPaint.finder().texture(0, McTextures.ACACIA_LOG).find(), true);

		register(Blocks.STRIPPED_ACACIA_LOG, "stripped_acacia_log",
				XmPaint.finder().texture(0, McTextures.STRIPPED_ACACIA_LOG_TOP).find(),
				XmPaint.finder().texture(0, McTextures.STRIPPED_ACACIA_LOG_TOP).find(),
				XmPaint.finder().texture(0, McTextures.STRIPPED_ACACIA_LOG).find(), true);

		register(Blocks.BIRCH_WOOD, "birch_wood", McTextures.BIRCH_LOG, false);

		register(Blocks.BIRCH_LOG, "birch_log",
				XmPaint.finder().texture(0, McTextures.BIRCH_LOG_TOP).find(),
				XmPaint.finder().texture(0, McTextures.BIRCH_LOG_TOP).find(),
				XmPaint.finder().texture(0, McTextures.BIRCH_LOG).find(), true);

		register(Blocks.STRIPPED_BIRCH_LOG, "stripped_birch_log",
				XmPaint.finder().texture(0, McTextures.STRIPPED_BIRCH_LOG_TOP).find(),
				XmPaint.finder().texture(0, McTextures.STRIPPED_BIRCH_LOG_TOP).find(),
				XmPaint.finder().texture(0, McTextures.STRIPPED_BIRCH_LOG).find(), true);

		register(Blocks.OAK_WOOD, "oak_wood", McTextures.OAK_LOG, false);

		register(Blocks.OAK_LOG, "oak_log",
				XmPaint.finder().texture(0, McTextures.OAK_LOG_TOP).find(),
				XmPaint.finder().texture(0, McTextures.OAK_LOG_TOP).find(),
				XmPaint.finder().texture(0, McTextures.OAK_LOG).find(), true);

		register(Blocks.STRIPPED_OAK_LOG, "stripped_oak_log",
				XmPaint.finder().texture(0, McTextures.STRIPPED_OAK_LOG_TOP).find(),
				XmPaint.finder().texture(0, McTextures.STRIPPED_OAK_LOG_TOP).find(),
				XmPaint.finder().texture(0, McTextures.STRIPPED_OAK_LOG).find(), true);

		register(Blocks.DARK_OAK_WOOD, "dark_oak_wood", McTextures.DARK_OAK_LOG, false);

		register(Blocks.DARK_OAK_LOG, "dark_oak_log",
				XmPaint.finder().texture(0, McTextures.DARK_OAK_LOG_TOP).find(),
				XmPaint.finder().texture(0, McTextures.DARK_OAK_LOG_TOP).find(),
				XmPaint.finder().texture(0, McTextures.DARK_OAK_LOG).find(), true);

		register(Blocks.STRIPPED_DARK_OAK_LOG, "stripped_dark_oak_log",
				XmPaint.finder().texture(0, McTextures.STRIPPED_DARK_OAK_LOG_TOP).find(),
				XmPaint.finder().texture(0, McTextures.STRIPPED_DARK_OAK_LOG_TOP).find(),
				XmPaint.finder().texture(0, McTextures.STRIPPED_DARK_OAK_LOG).find(), true);

		register(Blocks.JUNGLE_WOOD, "jungle_wood", McTextures.JUNGLE_LOG, false);

		register(Blocks.JUNGLE_LOG, "jungle_log",
				XmPaint.finder().texture(0, McTextures.JUNGLE_LOG_TOP).find(),
				XmPaint.finder().texture(0, McTextures.JUNGLE_LOG_TOP).find(),
				XmPaint.finder().texture(0, McTextures.JUNGLE_LOG).find(), true);

		register(Blocks.STRIPPED_JUNGLE_LOG, "stripped_jungle_log",
				XmPaint.finder().texture(0, McTextures.STRIPPED_JUNGLE_LOG_TOP).find(),
				XmPaint.finder().texture(0, McTextures.STRIPPED_JUNGLE_LOG_TOP).find(),
				XmPaint.finder().texture(0, McTextures.STRIPPED_JUNGLE_LOG).find(), true);

		register(Blocks.SPRUCE_WOOD, "spruce_wood", McTextures.SPRUCE_LOG, false);

		register(Blocks.SPRUCE_LOG, "spruce_log",
				XmPaint.finder().texture(0, McTextures.SPRUCE_LOG_TOP).find(),
				XmPaint.finder().texture(0, McTextures.SPRUCE_LOG_TOP).find(),
				XmPaint.finder().texture(0, McTextures.SPRUCE_LOG).find(), true);

		register(Blocks.STRIPPED_SPRUCE_LOG, "stripped_spruce_log",
				XmPaint.finder().texture(0, McTextures.STRIPPED_SPRUCE_LOG_TOP).find(),
				XmPaint.finder().texture(0, McTextures.STRIPPED_SPRUCE_LOG_TOP).find(),
				XmPaint.finder().texture(0, McTextures.STRIPPED_SPRUCE_LOG).find(), true);

		///

		register(Blocks.TERRACOTTA, "terracotta", McTextures.TERRACOTTA, false);
		register(Blocks.WHITE_TERRACOTTA, "white_terracotta", McTextures.WHITE_TERRACOTTA, false);
		register(Blocks.LIGHT_GRAY_TERRACOTTA, "light_gray_terracotta", McTextures.LIGHT_GRAY_TERRACOTTA, false);
		register(Blocks.GRAY_TERRACOTTA, "gray_terracotta", McTextures.GRAY_TERRACOTTA, false);
		register(Blocks.BLACK_TERRACOTTA, "black_terracotta", McTextures.BLACK_TERRACOTTA, false);
		register(Blocks.RED_TERRACOTTA, "red_terracotta", McTextures.RED_TERRACOTTA, false);
		register(Blocks.BROWN_TERRACOTTA, "brown_terracotta", McTextures.BROWN_TERRACOTTA, false);
		register(Blocks.ORANGE_TERRACOTTA, "orange_terracotta", McTextures.ORANGE_TERRACOTTA, false);
		register(Blocks.YELLOW_TERRACOTTA, "yellow_terracotta", McTextures.YELLOW_TERRACOTTA, false);
		register(Blocks.LIME_TERRACOTTA, "lime_terracotta", McTextures.LIME_TERRACOTTA, false);
		register(Blocks.GREEN_TERRACOTTA, "green_terracotta", McTextures.GREEN_TERRACOTTA, false);
		register(Blocks.CYAN_TERRACOTTA, "cyan_terracotta", McTextures.CYAN_TERRACOTTA, false);
		register(Blocks.LIGHT_BLUE_TERRACOTTA, "light_blue_terracotta", McTextures.LIGHT_BLUE_TERRACOTTA, false);
		register(Blocks.BLUE_TERRACOTTA, "blue_terracotta", McTextures.BLUE_TERRACOTTA, false);
		register(Blocks.PINK_TERRACOTTA, "pink_terracotta", McTextures.PINK_TERRACOTTA, false);
		register(Blocks.MAGENTA_TERRACOTTA, "magenta_terracotta", McTextures.MAGENTA_TERRACOTTA, false);
		register(Blocks.PURPLE_TERRACOTTA, "purple_terracotta", McTextures.PURPLE_TERRACOTTA, false);

		register(Blocks.WHITE_CONCRETE, "white_concrete", McTextures.WHITE_CONCRETE, false);
		register(Blocks.LIGHT_GRAY_CONCRETE, "light_gray_concrete", McTextures.LIGHT_GRAY_CONCRETE, false);
		register(Blocks.GRAY_CONCRETE, "gray_concrete", McTextures.GRAY_CONCRETE, false);
		register(Blocks.BLACK_CONCRETE, "black_concrete", McTextures.BLACK_CONCRETE, false);
		register(Blocks.RED_CONCRETE, "red_concrete", McTextures.RED_CONCRETE, false);
		register(Blocks.BROWN_CONCRETE, "brown_concrete", McTextures.BROWN_CONCRETE, false);
		register(Blocks.ORANGE_CONCRETE, "orange_concrete", McTextures.ORANGE_CONCRETE, false);
		register(Blocks.YELLOW_CONCRETE, "yellow_concrete", McTextures.YELLOW_CONCRETE, false);
		register(Blocks.LIME_CONCRETE, "lime_concrete", McTextures.LIME_CONCRETE, false);
		register(Blocks.GREEN_CONCRETE, "green_concrete", McTextures.GREEN_CONCRETE, false);
		register(Blocks.CYAN_CONCRETE, "cyan_concrete", McTextures.CYAN_CONCRETE, false);
		register(Blocks.LIGHT_BLUE_CONCRETE, "light_blue_concrete", McTextures.LIGHT_BLUE_CONCRETE, false);
		register(Blocks.BLUE_CONCRETE, "blue_concrete", McTextures.BLUE_CONCRETE, false);
		register(Blocks.PINK_CONCRETE, "pink_concrete", McTextures.PINK_CONCRETE, false);
		register(Blocks.MAGENTA_CONCRETE, "magenta_concrete", McTextures.MAGENTA_CONCRETE, false);
		register(Blocks.PURPLE_CONCRETE, "purple_concrete", McTextures.PURPLE_CONCRETE, false);

		register(Blocks.STONE, "stone", McTextures.STONE, false);
		register(Blocks.STONE_BRICKS, "stone_bricks", McTextures.STONE_BRICKS, true);
		register(Blocks.MOSSY_STONE_BRICKS, "mossy_stone_bricks", McTextures.MOSSY_STONE_BRICKS, true);
		register(Blocks.COBBLESTONE, "cobblestone", McTextures.COBBLESTONE, true);
		register(Blocks.MOSSY_COBBLESTONE, "mossy_cobblestone", McTextures.MOSSY_COBBLESTONE, true);

		register(Blocks.ANDESITE, "andesite", McTextures.ANDESITE, false);
		register(Blocks.POLISHED_ANDESITE, "polished_andesite", McTextures.POLISHED_ANDESITE, true);
		register(Blocks.DIORITE, "diorite", McTextures.DIORITE, false);
		register(Blocks.POLISHED_DIORITE, "polished_diorite", McTextures.POLISHED_DIORITE, true);
		register(Blocks.GRANITE, "granite", McTextures.GRANITE, false);
		register(Blocks.POLISHED_GRANITE, "polished_granite", McTextures.POLISHED_GRANITE, true);

		register(Blocks.BRICKS, "bricks", McTextures.BRICKS, true);
		register(Blocks.SANDSTONE, "sandstone",
				XmPaint.finder().texture(0, McTextures.SANDSTONE_BOTTOM).find(),
				XmPaint.finder().texture(0, McTextures.SANDSTONE_TOP).find(),
				XmPaint.finder().texture(0, McTextures.SANDSTONE).find(), true);
		register(Blocks.SMOOTH_SANDSTONE, "smooth_sandstone", McTextures.SANDSTONE_TOP, true);
		register(Blocks.RED_SANDSTONE, "red_sandstone",
				XmPaint.finder().texture(0, McTextures.RED_SANDSTONE_BOTTOM).find(),
				XmPaint.finder().texture(0, McTextures.RED_SANDSTONE_TOP).find(),
				XmPaint.finder().texture(0, McTextures.RED_SANDSTONE).find(), true);
		register(Blocks.SMOOTH_RED_SANDSTONE, "smooth_red_sandstone", McTextures.RED_SANDSTONE_TOP, true);

		register(Blocks.PRISMARINE, "prismarine", McTextures.PRISMARINE, true);
		register(Blocks.PRISMARINE_BRICKS, "prismarine_bricks", McTextures.PRISMARINE_BRICKS, true);
		register(Blocks.DARK_PRISMARINE, "dark_prismarine", McTextures.DARK_PRISMARINE, true);

		register(Blocks.NETHER_BRICKS, "nether_bricks", McTextures.NETHER_BRICKS, true);
		register(Blocks.RED_NETHER_BRICKS, "red_nether_bricks", McTextures.RED_NETHER_BRICKS, true);
		register(Blocks.SMOOTH_QUARTZ, "smooth_quartz", McTextures.QUARTZ_BLOCK_BOTTOM, false);
		register(Blocks.QUARTZ_BLOCK, "quartz",
				XmPaint.finder().texture(0, McTextures.QUARTZ_BLOCK_BOTTOM).find(),
				XmPaint.finder().texture(0, McTextures.QUARTZ_BLOCK_TOP).find(),
				XmPaint.finder().texture(0, McTextures.QUARTZ_BLOCK_SIDE).find(), false);

		/////
		register(Blocks.BLACKSTONE, "blackstone",
				XmPaint.finder().texture(0, McTextures.BLACKSTONE_TOP).find(),
				XmPaint.finder().texture(0, McTextures.BLACKSTONE_TOP).find(),
				XmPaint.finder().texture(0, McTextures.BLACKSTONE).find(), true);

		register(Blocks.GILDED_BLACKSTONE, "gilded_blackstone", McTextures.GILDED_BLACKSTONE, true);
		register(Blocks.POLISHED_BLACKSTONE, "polished_blackstone", McTextures.POLISHED_BLACKSTONE, true);
		register(Blocks.POLISHED_BLACKSTONE_BRICKS, "polished_blackstone_bricks", McTextures.POLISHED_BLACKSTONE_BRICKS, true);

		register(Blocks.CRIMSON_PLANKS, "crimson_planks", McTextures.CRIMSON_PLANKS, true);
		register(Blocks.CRIMSON_HYPHAE, "crimson_hyphae", McTextures.CRIMSON_STEM, true);
		register(Blocks.STRIPPED_CRIMSON_HYPHAE, "stipped_crimson_hyphae", McTextures.STRIPPED_CRIMSON_STEM, true);

		register(Blocks.CRIMSON_STEM, "crimson_stem",
				XmPaint.finder().texture(0, McTextures.CRIMSON_STEM_TOP).find(),
				XmPaint.finder().texture(0, McTextures.CRIMSON_STEM_TOP).find(),
				XmPaint.finder().texture(0, McTextures.CRIMSON_STEM).find(), true);

		register(Blocks.STRIPPED_CRIMSON_STEM, "stripped_crimson_stem",
				XmPaint.finder().texture(0, McTextures.STRIPPED_CRIMSON_STEM_TOP).find(),
				XmPaint.finder().texture(0, McTextures.STRIPPED_CRIMSON_STEM_TOP).find(),
				XmPaint.finder().texture(0, McTextures.STRIPPED_CRIMSON_STEM).find(), true);

		register(Blocks.WARPED_PLANKS, "warped_planks", McTextures.WARPED_PLANKS, true);
		register(Blocks.WARPED_HYPHAE, "warped_hyphae", McTextures.WARPED_STEM, true);
		register(Blocks.STRIPPED_WARPED_HYPHAE, "stipped_warped_hyphae", McTextures.STRIPPED_WARPED_STEM, true);

		register(Blocks.WARPED_STEM, "warped_stem",
				XmPaint.finder().texture(0, McTextures.WARPED_STEM_TOP).find(),
				XmPaint.finder().texture(0, McTextures.WARPED_STEM_TOP).find(),
				XmPaint.finder().texture(0, McTextures.WARPED_STEM).find(), true);

		register(Blocks.STRIPPED_WARPED_STEM, "stripped_warped_stem",
				XmPaint.finder().texture(0, McTextures.STRIPPED_WARPED_STEM_TOP).find(),
				XmPaint.finder().texture(0, McTextures.STRIPPED_WARPED_STEM_TOP).find(),
				XmPaint.finder().texture(0, McTextures.STRIPPED_WARPED_STEM).find(), true);

		register(Blocks.LAPIS_BLOCK, "lapis", McTextures.LAPIS_BLOCK, true);

		////

		register(Blocks.END_STONE_BRICKS, "end_stone_bricks", McTextures.END_STONE_BRICKS, true);
		register(Blocks.PURPUR_BLOCK, "purpur_block", McTextures.PURPUR_BLOCK, true);

		register(FancySnow.FANCY_SNOW_BLOCK, "snow_block", McTextures.SNOW, true);

		festiveLights("warm_white", FestiveLightsBlock.All::new, WARM_WHITE);
		festiveLights("cool_white", FestiveLightsBlock.All::new, COOL_WHITE);
		festiveLights("blue", FestiveLightsBlock.All::new, BLUE);
		festiveLights("cyan", FestiveLightsBlock.All::new, CYAN);
		festiveLights("magenta", FestiveLightsBlock.All::new, MAGENTA);
		festiveLights("purple", FestiveLightsBlock.All::new, PURPLE);
		festiveLights("red", FestiveLightsBlock.All::new, RED);
		festiveLights("yellow", FestiveLightsBlock.All::new, YELLOW);
		festiveLights("green", FestiveLightsBlock.All::new, GREEN);
		festiveLights("multi", FestiveLightsBlock.All::new, BLUE, CYAN, MAGENTA, PURPLE, RED, YELLOW, GREEN);
		festiveLights("hot_mix", FestiveLightsBlock.All::new, RED, YELLOW, MAGENTA);
		festiveLights("cool_mix", FestiveLightsBlock.All::new, BLUE, CYAN, MAGENTA, PURPLE, GREEN);
		festiveLights("bluish_mix", FestiveLightsBlock.All::new, BLUE, CYAN, GREEN);
		festiveLights("erie_mix", FestiveLightsBlock.All::new, GREEN, YELLOW, PURPLE);
		festiveLights("green_red_mix", FestiveLightsBlock.All::new, GREEN, RED);
		festiveLights("primary_mix", FestiveLightsBlock.All::new, GREEN, RED, BLUE);
		festiveLights("blue_white_mix", FestiveLightsBlock.All::new, BLUE, COOL_WHITE);
		festiveLights("red_white_mix", FestiveLightsBlock.All::new, RED, WARM_WHITE);
		festiveLights("red_white_blue_mix", FestiveLightsBlock.All::new, BLUE, RED, WARM_WHITE);
	}

	static void festiveLights(String colorName, Function<int[], FestiveLightsBlock> func, int... colors) {
		final String name = "festive_lights_" + colorName;
		Xb.REG.block(addLightName(name  + "_all"), new  FestiveLightsBlock.All(colors));
		Xb.REG.block(addLightName(name  + "_horizontal"), new  FestiveLightsBlock.Horizontal(colors));
		Xb.REG.block(addLightName(name  + "_single"), new  FestiveLightsBlock.Single(colors));
		Xb.REG.block(addLightName(name  + "_pendant"), new  FestiveLightsBlock.Pendant(colors));
	}

	static String addLightName(String name) {
		FESTIVE_LIGHTS.add(name);
		return name;
	}
}
