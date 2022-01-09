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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.resources.ResourceLocation;

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
	public static ModKey forceKey;
	public static ModKey modifyKey;

	public static Logger LOG = LogManager.getLogger("Exotic Blocks");

	public static Registrar REG = new Registrar(MODID, BlockNames.BLOCK_CONNECTED_FANCY_STONE);

	public static void initialize() {
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
	}
}
