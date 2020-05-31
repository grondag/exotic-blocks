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
package grondag.xblocks;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.fabricmc.api.ModInitializer;

import grondag.fermion.registrar.Registrar;
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
import grondag.xblocks.init.XbItems;

public class Xb implements ModInitializer {
	public static final String MODID = "xb";

	public static Logger LOG = LogManager.getLogger("Exotic Blocks");

	public static Registrar REG  = new Registrar(MODID, BlockNames.BLOCK_CONNECTED_FANCY_STONE);

	@Override
	public void onInitialize() {
		ConnectedGlass.values();
		FancySnow.values();
		FancyStone.values();
		RammedEarth.values();
		FancyGranite.values();
		FancyDiorite.values();
		FancyAndesite.values();
		Cobbles.values();
		ShapedBlocks.values();
		FestiveLights.values();
		XbItems.values();
	}
}
