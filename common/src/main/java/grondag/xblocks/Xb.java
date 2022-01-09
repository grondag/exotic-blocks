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

package grondag.xblocks;

import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

import io.vram.modkeys.api.ModKey;

import grondag.xblocks.data.BlockNames;
import grondag.xblocks.init.Cobbles;
import grondag.xblocks.init.ConnectedGlass;
import grondag.xblocks.init.FancyAndesite;
import grondag.xblocks.init.FancyDiorite;
import grondag.xblocks.init.FancyGranite;
import grondag.xblocks.init.FancySnow;
import grondag.xblocks.init.FancyStone;
import grondag.xblocks.init.FestiveLights;
import grondag.xblocks.init.RammedEarth;
import grondag.xblocks.init.ShapedBlocks;
import grondag.xblocks.init.SimpleBlocks;
import grondag.xblocks.init.XbItems;

public class Xb {
	public static final String MODID = "xb";
	public static final ResourceLocation FORCE_KEY_NAME = new ResourceLocation(MODID, "force");
	public static final ResourceLocation MODIFY_KEY_NAME = new ResourceLocation(MODID, "modify");

	public static Logger LOG = LogManager.getLogger("Exotic Blocks");

	public static ModKey forceKey;
	public static ModKey modifyKey;
	private static CreativeModeTab itemGroup;
	private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(MODID, Registry.ITEM_REGISTRY);
	private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(MODID, Registry.BLOCK_REGISTRY);

	public static void initialize() {
		final ResourceLocation itemGroupItemId = new ResourceLocation(MODID, BlockNames.BLOCK_CONNECTED_FANCY_STONE);
		itemGroup = CreativeTabRegistry.create(new ResourceLocation(MODID, "group"), () -> new ItemStack(Registry.ITEM.get(itemGroupItemId)));

		forceKey = ModKey.getOrCreate(FORCE_KEY_NAME);
		modifyKey = ModKey.getOrCreate(MODIFY_KEY_NAME);

		ConnectedGlass.initialize();
		FancySnow.initialize();
		FancyStone.initialize();
		RammedEarth.initialize();
		FancyGranite.initialize();
		FancyDiorite.initialize();
		FancyAndesite.initialize();
		Cobbles.initialize();
		SimpleBlocks.initialize();
		ShapedBlocks.initialize();
		FestiveLights.initialize();
		XbItems.initialize();

		ITEMS.register();
		BLOCKS.register();
	}

	public static ResourceLocation id(String name) {
		return new ResourceLocation(MODID, name);
	}

	public static Item.Properties itemSettings() {
		return new Item.Properties().tab(itemGroup);
	}

	public static <T extends Block> T block(String name, T block, Item.Properties settings) {
		return block(name, block, new BlockItem(block, settings));
	}

	public static <T extends Block> T block(String name, T block) {
		return block(name, block, itemSettings());
	}

	public static <T extends Block> T block(String name, T block, BlockItem item) {
		BLOCKS.register(id(name), () -> block);

		if (item != null) {
			final BlockItem bi = item(name, item);
			bi.registerBlocks(BlockItem.BY_BLOCK, bi);
		}

		return block;
	}

	public static <T extends Item> T item(String name, T item) {
		ITEMS.register(id(name), () -> item);
		return item;
	}
}
