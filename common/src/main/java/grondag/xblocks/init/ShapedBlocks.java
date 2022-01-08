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

import static grondag.xblocks.block.ShapedBlockRegistrator.registerShapes;

import net.minecraft.world.level.block.Blocks;

import grondag.xblocks.data.ShapedBlockNames;
import grondag.xm.api.paint.XmPaint;
import grondag.xm.api.texture.McTextures;

public abstract class ShapedBlocks {
	private ShapedBlocks() { }

	public static void initialize() {
		registerShapes(Blocks.ACACIA_PLANKS, ShapedBlockNames.SHAPED_ACACIA, McTextures.ACACIA_PLANKS, false);
		registerShapes(Blocks.BIRCH_PLANKS, ShapedBlockNames.SHAPED_BIRCH, McTextures.BIRCH_PLANKS, false);
		registerShapes(Blocks.OAK_PLANKS, ShapedBlockNames.SHAPED_OAK, McTextures.OAK_PLANKS, false);
		registerShapes(Blocks.DARK_OAK_PLANKS, ShapedBlockNames.SHAPED_DARK_OAK, McTextures.DARK_OAK_PLANKS, false);
		registerShapes(Blocks.JUNGLE_PLANKS, ShapedBlockNames.SHAPED_JUNGLE, McTextures.JUNGLE_PLANKS, false);
		registerShapes(Blocks.SPRUCE_PLANKS, ShapedBlockNames.SHAPED_SPRUCE, McTextures.SPRUCE_PLANKS, false);

		// We use planks as settings template for these because otherwise the map color factory
		// breaks because AXIS isn't a property of the new block

		registerShapes(Blocks.ACACIA_PLANKS, ShapedBlockNames.SHAPED_ACACIA_WOOD, McTextures.ACACIA_LOG, false);

		registerShapes(Blocks.ACACIA_PLANKS, ShapedBlockNames.SHAPED_ACACIA_LOG,
				XmPaint.finder().texture(0, McTextures.ACACIA_LOG_TOP).find(),
				XmPaint.finder().texture(0, McTextures.ACACIA_LOG_TOP).find(),
				XmPaint.finder().texture(0, McTextures.ACACIA_LOG).find(), true);

		registerShapes(Blocks.ACACIA_PLANKS, ShapedBlockNames.SHAPED_STRIPPED_ACACIA_LOG,
				XmPaint.finder().texture(0, McTextures.STRIPPED_ACACIA_LOG_TOP).find(),
				XmPaint.finder().texture(0, McTextures.STRIPPED_ACACIA_LOG_TOP).find(),
				XmPaint.finder().texture(0, McTextures.STRIPPED_ACACIA_LOG).find(), true);

		registerShapes(Blocks.BIRCH_PLANKS, ShapedBlockNames.SHAPED_BIRCH_WOOD, McTextures.BIRCH_LOG, false);

		registerShapes(Blocks.BIRCH_PLANKS, ShapedBlockNames.SHAPED_BIRCH_LOG,
				XmPaint.finder().texture(0, McTextures.BIRCH_LOG_TOP).find(),
				XmPaint.finder().texture(0, McTextures.BIRCH_LOG_TOP).find(),
				XmPaint.finder().texture(0, McTextures.BIRCH_LOG).find(), true);

		registerShapes(Blocks.BIRCH_PLANKS, ShapedBlockNames.SHAPED_STRIPPED_BIRCH_LOG,
				XmPaint.finder().texture(0, McTextures.STRIPPED_BIRCH_LOG_TOP).find(),
				XmPaint.finder().texture(0, McTextures.STRIPPED_BIRCH_LOG_TOP).find(),
				XmPaint.finder().texture(0, McTextures.STRIPPED_BIRCH_LOG).find(), true);

		registerShapes(Blocks.OAK_PLANKS, ShapedBlockNames.SHAPED_OAK_WOOD, McTextures.OAK_LOG, false);

		registerShapes(Blocks.OAK_PLANKS, ShapedBlockNames.SHAPED_OAK_LOG,
				XmPaint.finder().texture(0, McTextures.OAK_LOG_TOP).find(),
				XmPaint.finder().texture(0, McTextures.OAK_LOG_TOP).find(),
				XmPaint.finder().texture(0, McTextures.OAK_LOG).find(), true);

		registerShapes(Blocks.OAK_PLANKS, ShapedBlockNames.SHAPED_STRIPPED_OAK_LOG,
				XmPaint.finder().texture(0, McTextures.STRIPPED_OAK_LOG_TOP).find(),
				XmPaint.finder().texture(0, McTextures.STRIPPED_OAK_LOG_TOP).find(),
				XmPaint.finder().texture(0, McTextures.STRIPPED_OAK_LOG).find(), true);

		registerShapes(Blocks.DARK_OAK_PLANKS, ShapedBlockNames.SHAPED_DARK_OAK_WOOD, McTextures.DARK_OAK_LOG, false);

		registerShapes(Blocks.DARK_OAK_PLANKS, ShapedBlockNames.SHAPED_DARK_OAK_LOG,
				XmPaint.finder().texture(0, McTextures.DARK_OAK_LOG_TOP).find(),
				XmPaint.finder().texture(0, McTextures.DARK_OAK_LOG_TOP).find(),
				XmPaint.finder().texture(0, McTextures.DARK_OAK_LOG).find(), true);

		registerShapes(Blocks.DARK_OAK_PLANKS, ShapedBlockNames.SHAPED_STRIPPED_DARK_OAK_LOG,
				XmPaint.finder().texture(0, McTextures.STRIPPED_DARK_OAK_LOG_TOP).find(),
				XmPaint.finder().texture(0, McTextures.STRIPPED_DARK_OAK_LOG_TOP).find(),
				XmPaint.finder().texture(0, McTextures.STRIPPED_DARK_OAK_LOG).find(), true);

		registerShapes(Blocks.JUNGLE_PLANKS, ShapedBlockNames.SHAPED_JUNGLE_WOOD, McTextures.JUNGLE_LOG, false);

		registerShapes(Blocks.JUNGLE_PLANKS, ShapedBlockNames.SHAPED_JUNGLE_LOG,
				XmPaint.finder().texture(0, McTextures.JUNGLE_LOG_TOP).find(),
				XmPaint.finder().texture(0, McTextures.JUNGLE_LOG_TOP).find(),
				XmPaint.finder().texture(0, McTextures.JUNGLE_LOG).find(), true);

		registerShapes(Blocks.JUNGLE_PLANKS, ShapedBlockNames.SHAPED_STRIPPED_JUNGLE_LOG,
				XmPaint.finder().texture(0, McTextures.STRIPPED_JUNGLE_LOG_TOP).find(),
				XmPaint.finder().texture(0, McTextures.STRIPPED_JUNGLE_LOG_TOP).find(),
				XmPaint.finder().texture(0, McTextures.STRIPPED_JUNGLE_LOG).find(), true);

		registerShapes(Blocks.SPRUCE_PLANKS, ShapedBlockNames.SHAPED_SPRUCE_WOOD, McTextures.SPRUCE_LOG, false);

		registerShapes(Blocks.SPRUCE_PLANKS, ShapedBlockNames.SHAPED_SPRUCE_LOG,
				XmPaint.finder().texture(0, McTextures.SPRUCE_LOG_TOP).find(),
				XmPaint.finder().texture(0, McTextures.SPRUCE_LOG_TOP).find(),
				XmPaint.finder().texture(0, McTextures.SPRUCE_LOG).find(), true);

		registerShapes(Blocks.SPRUCE_PLANKS, ShapedBlockNames.SHAPED_STRIPPED_SPRUCE_LOG,
				XmPaint.finder().texture(0, McTextures.STRIPPED_SPRUCE_LOG_TOP).find(),
				XmPaint.finder().texture(0, McTextures.STRIPPED_SPRUCE_LOG_TOP).find(),
				XmPaint.finder().texture(0, McTextures.STRIPPED_SPRUCE_LOG).find(), true);

		registerShapes(Blocks.TERRACOTTA, ShapedBlockNames.SHAPED_TERRACOTTA, McTextures.TERRACOTTA, false);
		registerShapes(Blocks.WHITE_TERRACOTTA, ShapedBlockNames.SHAPED_WHITE_TERRACOTTA, McTextures.WHITE_TERRACOTTA, false);
		registerShapes(Blocks.LIGHT_GRAY_TERRACOTTA, ShapedBlockNames.SHAPED_LIGHT_GRAY_TERRACOTTA, McTextures.LIGHT_GRAY_TERRACOTTA, false);
		registerShapes(Blocks.GRAY_TERRACOTTA, ShapedBlockNames.SHAPED_GRAY_TERRACOTTA, McTextures.GRAY_TERRACOTTA, false);
		registerShapes(Blocks.BLACK_TERRACOTTA, ShapedBlockNames.SHAPED_BLACK_TERRACOTTA, McTextures.BLACK_TERRACOTTA, false);
		registerShapes(Blocks.RED_TERRACOTTA, ShapedBlockNames.SHAPED_RED_TERRACOTTA, McTextures.RED_TERRACOTTA, false);
		registerShapes(Blocks.BROWN_TERRACOTTA, ShapedBlockNames.SHAPED_BROWN_TERRACOTTA, McTextures.BROWN_TERRACOTTA, false);
		registerShapes(Blocks.ORANGE_TERRACOTTA, ShapedBlockNames.SHAPED_ORANGE_TERRACOTTA, McTextures.ORANGE_TERRACOTTA, false);
		registerShapes(Blocks.YELLOW_TERRACOTTA, ShapedBlockNames.SHAPED_YELLOW_TERRACOTTA, McTextures.YELLOW_TERRACOTTA, false);
		registerShapes(Blocks.LIME_TERRACOTTA, ShapedBlockNames.SHAPED_LIME_TERRACOTTA, McTextures.LIME_TERRACOTTA, false);
		registerShapes(Blocks.GREEN_TERRACOTTA, ShapedBlockNames.SHAPED_GREEN_TERRACOTTA, McTextures.GREEN_TERRACOTTA, false);
		registerShapes(Blocks.CYAN_TERRACOTTA, ShapedBlockNames.SHAPED_CYAN_TERRACOTTA, McTextures.CYAN_TERRACOTTA, false);
		registerShapes(Blocks.LIGHT_BLUE_TERRACOTTA, ShapedBlockNames.SHAPED_LIGHT_BLUE_TERRACOTTA, McTextures.LIGHT_BLUE_TERRACOTTA, false);
		registerShapes(Blocks.BLUE_TERRACOTTA, ShapedBlockNames.SHAPED_BLUE_TERRACOTTA, McTextures.BLUE_TERRACOTTA, false);
		registerShapes(Blocks.PINK_TERRACOTTA, ShapedBlockNames.SHAPED_PINK_TERRACOTTA, McTextures.PINK_TERRACOTTA, false);
		registerShapes(Blocks.MAGENTA_TERRACOTTA, ShapedBlockNames.SHAPED_MAGENTA_TERRACOTTA, McTextures.MAGENTA_TERRACOTTA, false);
		registerShapes(Blocks.PURPLE_TERRACOTTA, ShapedBlockNames.SHAPED_PURPLE_TERRACOTTA, McTextures.PURPLE_TERRACOTTA, false);

		registerShapes(Blocks.WHITE_CONCRETE, ShapedBlockNames.SHAPED_WHITE_CONCRETE, McTextures.WHITE_CONCRETE, false);
		registerShapes(Blocks.LIGHT_GRAY_CONCRETE, ShapedBlockNames.SHAPED_LIGHT_GRAY_CONCRETE, McTextures.LIGHT_GRAY_CONCRETE, false);
		registerShapes(Blocks.GRAY_CONCRETE, ShapedBlockNames.SHAPED_GRAY_CONCRETE, McTextures.GRAY_CONCRETE, false);
		registerShapes(Blocks.BLACK_CONCRETE, ShapedBlockNames.SHAPED_BLACK_CONCRETE, McTextures.BLACK_CONCRETE, false);
		registerShapes(Blocks.RED_CONCRETE, ShapedBlockNames.SHAPED_RED_CONCRETE, McTextures.RED_CONCRETE, false);
		registerShapes(Blocks.BROWN_CONCRETE, ShapedBlockNames.SHAPED_BROWN_CONCRETE, McTextures.BROWN_CONCRETE, false);
		registerShapes(Blocks.ORANGE_CONCRETE, ShapedBlockNames.SHAPED_ORANGE_CONCRETE, McTextures.ORANGE_CONCRETE, false);
		registerShapes(Blocks.YELLOW_CONCRETE, ShapedBlockNames.SHAPED_YELLOW_CONCRETE, McTextures.YELLOW_CONCRETE, false);
		registerShapes(Blocks.LIME_CONCRETE, ShapedBlockNames.SHAPED_LIME_CONCRETE, McTextures.LIME_CONCRETE, false);
		registerShapes(Blocks.GREEN_CONCRETE, ShapedBlockNames.SHAPED_GREEN_CONCRETE, McTextures.GREEN_CONCRETE, false);
		registerShapes(Blocks.CYAN_CONCRETE, ShapedBlockNames.SHAPED_CYAN_CONCRETE, McTextures.CYAN_CONCRETE, false);
		registerShapes(Blocks.LIGHT_BLUE_CONCRETE, ShapedBlockNames.SHAPED_LIGHT_BLUE_CONCRETE, McTextures.LIGHT_BLUE_CONCRETE, false);
		registerShapes(Blocks.BLUE_CONCRETE, ShapedBlockNames.SHAPED_BLUE_CONCRETE, McTextures.BLUE_CONCRETE, false);
		registerShapes(Blocks.PINK_CONCRETE, ShapedBlockNames.SHAPED_PINK_CONCRETE, McTextures.PINK_CONCRETE, false);
		registerShapes(Blocks.MAGENTA_CONCRETE, ShapedBlockNames.SHAPED_MAGENTA_CONCRETE, McTextures.MAGENTA_CONCRETE, false);
		registerShapes(Blocks.PURPLE_CONCRETE, ShapedBlockNames.SHAPED_PURPLE_CONCRETE, McTextures.PURPLE_CONCRETE, false);

		registerShapes(Blocks.STONE, ShapedBlockNames.SHAPED_STONE, McTextures.STONE, false);
		registerShapes(Blocks.STONE_BRICKS, ShapedBlockNames.SHAPED_STONE_BRICKS, McTextures.STONE_BRICKS, true);
		registerShapes(Blocks.MOSSY_STONE_BRICKS, ShapedBlockNames.SHAPED_MOSSY_STONE_BRICKS, McTextures.MOSSY_STONE_BRICKS, true);
		registerShapes(Blocks.COBBLESTONE, ShapedBlockNames.SHAPED_COBBLESTONE, McTextures.COBBLESTONE, true);
		registerShapes(Blocks.MOSSY_COBBLESTONE, ShapedBlockNames.SHAPED_MOSSY_COBBLESTONE, McTextures.MOSSY_COBBLESTONE, true);

		registerShapes(Blocks.ANDESITE, ShapedBlockNames.SHAPED_ANDESITE, McTextures.ANDESITE, false);
		registerShapes(Blocks.POLISHED_ANDESITE, ShapedBlockNames.SHAPED_POLISHED_ANDESITE, McTextures.POLISHED_ANDESITE, true);
		registerShapes(Blocks.DIORITE, ShapedBlockNames.SHAPED_DIORITE, McTextures.DIORITE, false);
		registerShapes(Blocks.POLISHED_DIORITE, ShapedBlockNames.SHAPED_POLISHED_DIORITE, McTextures.POLISHED_DIORITE, true);
		registerShapes(Blocks.GRANITE, ShapedBlockNames.SHAPED_GRANITE, McTextures.GRANITE, false);
		registerShapes(Blocks.POLISHED_GRANITE, ShapedBlockNames.SHAPED_POLISHED_GRANITE, McTextures.POLISHED_GRANITE, true);

		registerShapes(Blocks.BRICKS, ShapedBlockNames.SHAPED_BRICKS, McTextures.BRICKS, true);

		registerShapes(Blocks.SANDSTONE, ShapedBlockNames.SHAPED_SANDSTONE,
				XmPaint.finder().texture(0, McTextures.SANDSTONE_BOTTOM).find(),
				XmPaint.finder().texture(0, McTextures.SANDSTONE_TOP).find(),
				XmPaint.finder().texture(0, McTextures.SANDSTONE).find(), true);

		registerShapes(Blocks.RED_SANDSTONE, ShapedBlockNames.SHAPED_RED_SANDSTONE,
				XmPaint.finder().texture(0, McTextures.RED_SANDSTONE_BOTTOM).find(),
				XmPaint.finder().texture(0, McTextures.RED_SANDSTONE_TOP).find(),
				XmPaint.finder().texture(0, McTextures.RED_SANDSTONE).find(), true);

		registerShapes(Blocks.SMOOTH_SANDSTONE, ShapedBlockNames.SHAPED_SMOOTH_SANDSTONE, McTextures.SANDSTONE_TOP, true);
		registerShapes(Blocks.SMOOTH_RED_SANDSTONE, ShapedBlockNames.SHAPED_SMOOTH_RED_SANDSTONE, McTextures.RED_SANDSTONE_TOP, true);

		registerShapes(Blocks.PRISMARINE, ShapedBlockNames.SHAPED_PRISMARINE, McTextures.PRISMARINE, true);
		registerShapes(Blocks.PRISMARINE_BRICKS, ShapedBlockNames.SHAPED_PRISMARINE_BRICKS, McTextures.PRISMARINE_BRICKS, true);
		registerShapes(Blocks.DARK_PRISMARINE, ShapedBlockNames.SHAPED_DARK_PRISMARINE, McTextures.DARK_PRISMARINE, true);

		registerShapes(Blocks.NETHER_BRICKS, ShapedBlockNames.SHAPED_NETHER_BRICKS, McTextures.NETHER_BRICKS, true);
		registerShapes(Blocks.RED_NETHER_BRICKS, ShapedBlockNames.SHAPED_RED_NETHER_BRICKS, McTextures.RED_NETHER_BRICKS, true);
		registerShapes(Blocks.SMOOTH_QUARTZ, ShapedBlockNames.SHAPED_SMOOTH_QUARTZ, McTextures.QUARTZ_BLOCK_BOTTOM, false);

		registerShapes(Blocks.QUARTZ_BLOCK, ShapedBlockNames.SHAPED_QUARTZ,
				XmPaint.finder().texture(0, McTextures.QUARTZ_BLOCK_BOTTOM).find(),
				XmPaint.finder().texture(0, McTextures.QUARTZ_BLOCK_TOP).find(),
				XmPaint.finder().texture(0, McTextures.QUARTZ_BLOCK_SIDE).find(), false);

		registerShapes(Blocks.BLACKSTONE, ShapedBlockNames.SHAPED_BLACKSTONE,
				XmPaint.finder().texture(0, McTextures.BLACKSTONE_TOP).find(),
				XmPaint.finder().texture(0, McTextures.BLACKSTONE_TOP).find(),
				XmPaint.finder().texture(0, McTextures.BLACKSTONE).find(), true);

		registerShapes(Blocks.GILDED_BLACKSTONE, ShapedBlockNames.SHAPED_GILDED_BLACKSTONE, McTextures.GILDED_BLACKSTONE, true);
		registerShapes(Blocks.POLISHED_BLACKSTONE, ShapedBlockNames.SHAPED_POLISHED_BLACKSTONE, McTextures.POLISHED_BLACKSTONE, true);
		registerShapes(Blocks.POLISHED_BLACKSTONE_BRICKS, ShapedBlockNames.SHAPED_POLISHED_BLACKSTONE_BRICKS, McTextures.POLISHED_BLACKSTONE_BRICKS, true);

		registerShapes(Blocks.CRIMSON_PLANKS, ShapedBlockNames.SHAPED_CRIMSON_PLANKS, McTextures.CRIMSON_PLANKS, true);
		registerShapes(Blocks.CRIMSON_HYPHAE, ShapedBlockNames.SHAPED_CRIMSON_HYPHAE, McTextures.CRIMSON_STEM, true);
		registerShapes(Blocks.STRIPPED_CRIMSON_HYPHAE, ShapedBlockNames.SHAPED_STIPPED_CRIMSON_HYPHAE, McTextures.STRIPPED_CRIMSON_STEM, true);

		registerShapes(Blocks.CRIMSON_STEM, ShapedBlockNames.SHAPED_CRIMSON_STEM,
				XmPaint.finder().texture(0, McTextures.CRIMSON_STEM_TOP).find(),
				XmPaint.finder().texture(0, McTextures.CRIMSON_STEM_TOP).find(),
				XmPaint.finder().texture(0, McTextures.CRIMSON_STEM).find(), true);

		registerShapes(Blocks.STRIPPED_CRIMSON_STEM, ShapedBlockNames.SHAPED_STRIPPED_CRIMSON_STEM,
				XmPaint.finder().texture(0, McTextures.STRIPPED_CRIMSON_STEM_TOP).find(),
				XmPaint.finder().texture(0, McTextures.STRIPPED_CRIMSON_STEM_TOP).find(),
				XmPaint.finder().texture(0, McTextures.STRIPPED_CRIMSON_STEM).find(), true);

		registerShapes(Blocks.WARPED_PLANKS, ShapedBlockNames.SHAPED_WARPED_PLANKS, McTextures.WARPED_PLANKS, true);
		registerShapes(Blocks.WARPED_HYPHAE, ShapedBlockNames.SHAPED_WARPED_HYPHAE, McTextures.WARPED_STEM, true);
		registerShapes(Blocks.STRIPPED_WARPED_HYPHAE, ShapedBlockNames.SHAPED_STIPPED_WARPED_HYPHAE, McTextures.STRIPPED_WARPED_STEM, true);

		registerShapes(Blocks.WARPED_STEM, ShapedBlockNames.SHAPED_WARPED_STEM,
				XmPaint.finder().texture(0, McTextures.WARPED_STEM_TOP).find(),
				XmPaint.finder().texture(0, McTextures.WARPED_STEM_TOP).find(),
				XmPaint.finder().texture(0, McTextures.WARPED_STEM).find(), true);

		registerShapes(Blocks.STRIPPED_WARPED_STEM, ShapedBlockNames.SHAPED_STRIPPED_WARPED_STEM,
				XmPaint.finder().texture(0, McTextures.STRIPPED_WARPED_STEM_TOP).find(),
				XmPaint.finder().texture(0, McTextures.STRIPPED_WARPED_STEM_TOP).find(),
				XmPaint.finder().texture(0, McTextures.STRIPPED_WARPED_STEM).find(), true);

		registerShapes(Blocks.LAPIS_BLOCK, ShapedBlockNames.SHAPED_LAPIS, McTextures.LAPIS_BLOCK, true);

		registerShapes(Blocks.NETHERRACK, ShapedBlockNames.SHAPED_NETHERRACK, McTextures.NETHERRACK, true);
		registerShapes(Blocks.NETHER_WART_BLOCK, ShapedBlockNames.SHAPED_NETHER_WART, McTextures.NETHER_WART_BLOCK, true);
		registerShapes(Blocks.NETHERITE_BLOCK, ShapedBlockNames.SHAPED_NETHERITE, McTextures.NETHERITE_BLOCK, false);

		registerShapes(Blocks.BONE_BLOCK, ShapedBlockNames.SHAPED_BONE,
				XmPaint.finder().texture(0, McTextures.BONE_BLOCK_TOP).find(),
				XmPaint.finder().texture(0, McTextures.BONE_BLOCK_TOP).find(),
				XmPaint.finder().texture(0, McTextures.BONE_BLOCK_SIDE).find(), true);

		registerShapes(Blocks.BRAIN_CORAL_BLOCK, ShapedBlockNames.SHAPED_BRAIN_CORAL, McTextures.BRAIN_CORAL_BLOCK, true);
		registerShapes(Blocks.DEAD_BRAIN_CORAL_BLOCK, ShapedBlockNames.SHAPED_DEAD_BRAIN_CORAL, McTextures.DEAD_BRAIN_CORAL_BLOCK, true);
		registerShapes(Blocks.BUBBLE_CORAL_BLOCK, ShapedBlockNames.SHAPED_BUBBLE_CORAL, McTextures.BUBBLE_CORAL_BLOCK, true);
		registerShapes(Blocks.DEAD_BUBBLE_CORAL_BLOCK, ShapedBlockNames.SHAPED_DEAD_BUBBLE_CORAL, McTextures.DEAD_BUBBLE_CORAL_BLOCK, true);
		registerShapes(Blocks.FIRE_CORAL_BLOCK, ShapedBlockNames.SHAPED_FIRE_CORAL, McTextures.FIRE_CORAL_BLOCK, true);
		registerShapes(Blocks.DEAD_FIRE_CORAL_BLOCK, ShapedBlockNames.SHAPED_DEAD_FIRE_CORAL, McTextures.DEAD_FIRE_CORAL_BLOCK, true);
		registerShapes(Blocks.HORN_CORAL_BLOCK, ShapedBlockNames.SHAPED_HORN_CORAL, McTextures.HORN_CORAL_BLOCK, true);
		registerShapes(Blocks.DEAD_HORN_CORAL_BLOCK, ShapedBlockNames.SHAPED_DEAD_HORN_CORAL, McTextures.DEAD_HORN_CORAL_BLOCK, true);
		registerShapes(Blocks.TUBE_CORAL_BLOCK, ShapedBlockNames.SHAPED_TUBE_CORAL, McTextures.TUBE_CORAL_BLOCK, true);
		registerShapes(Blocks.DEAD_TUBE_CORAL_BLOCK, ShapedBlockNames.SHAPED_DEAD_TUBE_CORAL, McTextures.DEAD_TUBE_CORAL_BLOCK, true);

		registerShapes(Blocks.BASALT, ShapedBlockNames.SHAPED_BASALT,
				XmPaint.finder().texture(0, McTextures.BASALT_TOP).find(),
				XmPaint.finder().texture(0, McTextures.BASALT_TOP).find(),
				XmPaint.finder().texture(0, McTextures.BASALT_SIDE).find(), true);

		registerShapes(Blocks.POLISHED_BASALT, ShapedBlockNames.SHAPED_POLISHED_BASALT,
				XmPaint.finder().texture(0, McTextures.POLISHED_BASALT_TOP).find(),
				XmPaint.finder().texture(0, McTextures.POLISHED_BASALT_TOP).find(),
				XmPaint.finder().texture(0, McTextures.POLISHED_BASALT_SIDE).find(), true);

		registerShapes(Blocks.OBSIDIAN, ShapedBlockNames.SHAPED_OBSIDIAN, McTextures.OBSIDIAN, true);
		registerShapes(Blocks.CRYING_OBSIDIAN, ShapedBlockNames.SHAPED_CRYING_OBSIDIAN, McTextures.CRYING_OBSIDIAN, true);
		registerShapes(Blocks.GOLD_BLOCK, ShapedBlockNames.SHAPED_GOLD, McTextures.GOLD_BLOCK, false);
		registerShapes(Blocks.DIAMOND_BLOCK, ShapedBlockNames.SHAPED_DIAMOND, McTextures.DIAMOND_BLOCK, false);
		registerShapes(Blocks.EMERALD_BLOCK, ShapedBlockNames.SHAPED_EMERALD, McTextures.EMERALD_BLOCK, false);
		registerShapes(Blocks.IRON_BLOCK, ShapedBlockNames.SHAPED_IRON, McTextures.IRON_BLOCK, false);
		registerShapes(Blocks.COAL_BLOCK, ShapedBlockNames.SHAPED_COAL, McTextures.COAL_BLOCK, true);
		registerShapes(Blocks.REDSTONE_BLOCK, ShapedBlockNames.SHAPED_REDSTONE, McTextures.REDSTONE_BLOCK, true);
		registerShapes(Blocks.CLAY, ShapedBlockNames.SHAPED_CLAY, McTextures.CLAY, false);
		registerShapes(Blocks.DIRT, ShapedBlockNames.SHAPED_DIRT, McTextures.DIRT, true);
		registerShapes(Blocks.PACKED_ICE, ShapedBlockNames.SHAPED_PACKED_ICE, McTextures.PACKED_ICE, true);
		registerShapes(Blocks.BLUE_ICE, ShapedBlockNames.SHAPED_BLUE_ICE, McTextures.BLUE_ICE, true);

		registerShapes(Blocks.MAGMA_BLOCK, ShapedBlockNames.SHAPED_MAGMA, McTextures.MAGMA, true);
		registerShapes(Blocks.WARPED_WART_BLOCK, ShapedBlockNames.SHAPED_WARPED_WART, McTextures.WARPED_WART_BLOCK, true);

		registerShapes(Blocks.BLACK_WOOL, ShapedBlockNames.SHAPED_BLACK_WOOL, McTextures.BLACK_WOOL, true);
		registerShapes(Blocks.BLUE_WOOL, ShapedBlockNames.SHAPED_BLUE_WOOL, McTextures.BLUE_WOOL, true);
		registerShapes(Blocks.BROWN_WOOL, ShapedBlockNames.SHAPED_BROWN_WOOL, McTextures.BROWN_WOOL, true);
		registerShapes(Blocks.CYAN_WOOL, ShapedBlockNames.SHAPED_CYAN_WOOL, McTextures.CYAN_WOOL, true);
		registerShapes(Blocks.GRAY_WOOL, ShapedBlockNames.SHAPED_GRAY_WOOL, McTextures.GRAY_WOOL, true);
		registerShapes(Blocks.GREEN_WOOL, ShapedBlockNames.SHAPED_GREEN_WOOL, McTextures.GREEN_WOOL, true);
		registerShapes(Blocks.LIGHT_BLUE_WOOL, ShapedBlockNames.SHAPED_LIGHT_BLUE_WOOL, McTextures.LIGHT_BLUE_WOOL, true);
		registerShapes(Blocks.LIGHT_GRAY_WOOL, ShapedBlockNames.SHAPED_LIGHT_GRAY_WOOL, McTextures.LIGHT_GRAY_WOOL, true);
		registerShapes(Blocks.LIME_WOOL, ShapedBlockNames.SHAPED_LIME_WOOL, McTextures.LIME_WOOL, true);
		registerShapes(Blocks.MAGENTA_WOOL, ShapedBlockNames.SHAPED_MAGENTA_WOOL, McTextures.MAGENTA_WOOL, true);
		registerShapes(Blocks.ORANGE_WOOL, ShapedBlockNames.SHAPED_ORANGE_WOOL, McTextures.ORANGE_WOOL, true);
		registerShapes(Blocks.PINK_WOOL, ShapedBlockNames.SHAPED_PINK_WOOL, McTextures.PINK_WOOL, true);
		registerShapes(Blocks.PURPLE_WOOL, ShapedBlockNames.SHAPED_PURPLE_WOOL, McTextures.PURPLE_WOOL, true);
		registerShapes(Blocks.RED_WOOL, ShapedBlockNames.SHAPED_RED_WOOL, McTextures.RED_WOOL, true);
		registerShapes(Blocks.WHITE_WOOL, ShapedBlockNames.SHAPED_WHITE_WOOL, McTextures.WHITE_WOOL, true);
		registerShapes(Blocks.YELLOW_WOOL, ShapedBlockNames.SHAPED_YELLOW_WOOL, McTextures.YELLOW_WOOL, true);

		registerShapes(Blocks.END_STONE, ShapedBlockNames.SHAPED_END_STONE, McTextures.END_STONE, true);
		registerShapes(Blocks.END_STONE_BRICKS, ShapedBlockNames.SHAPED_END_STONE_BRICKS, McTextures.END_STONE_BRICKS, true);
		registerShapes(Blocks.PURPUR_BLOCK, ShapedBlockNames.SHAPED_PURPUR_BLOCK, McTextures.PURPUR_BLOCK, true);

		registerShapes(FancySnow.FANCY_SNOW_BLOCK, ShapedBlockNames.SHAPED_SNOW_BLOCK, McTextures.SNOW, true);
	}
}
