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

import net.minecraft.resources.ResourceLocation;

import grondag.xblocks.Xb;
import grondag.xblocks.block.FestiveLightsBlock;
import grondag.xblocks.data.LightColors;
import grondag.xblocks.data.LightShapes;

public abstract class FestiveLights {
	private FestiveLights() { }

	public static final ArrayList<ResourceLocation> FESTIVE_LIGHTS = new ArrayList<>();

	public static void initialize() {
		festiveLights(LightColors.WARM_WHITE, FestiveLightsBlock.All::new, WARM_WHITE);
		festiveLights(LightColors.COOL_WHITE, FestiveLightsBlock.All::new, COOL_WHITE);
		festiveLights(LightColors.BLUE, FestiveLightsBlock.All::new, BLUE);
		festiveLights(LightColors.CYAN, FestiveLightsBlock.All::new, CYAN);
		festiveLights(LightColors.MAGENTA, FestiveLightsBlock.All::new, MAGENTA);
		festiveLights(LightColors.PURPLE, FestiveLightsBlock.All::new, PURPLE);
		festiveLights(LightColors.RED, FestiveLightsBlock.All::new, RED);
		festiveLights(LightColors.YELLOW, FestiveLightsBlock.All::new, YELLOW);
		festiveLights(LightColors.GREEN, FestiveLightsBlock.All::new, GREEN);
		festiveLights(LightColors.MIXED_COLORS, FestiveLightsBlock.All::new, BLUE, CYAN, MAGENTA, PURPLE, RED, YELLOW, GREEN);
		festiveLights(LightColors.HOT_MIX, FestiveLightsBlock.All::new, RED, YELLOW, MAGENTA);
		festiveLights(LightColors.COOL_MIX, FestiveLightsBlock.All::new, BLUE, CYAN, MAGENTA, PURPLE, GREEN);
		festiveLights(LightColors.BLUISH_MIX, FestiveLightsBlock.All::new, BLUE, CYAN, GREEN);
		festiveLights(LightColors.ERIE_MIX, FestiveLightsBlock.All::new, GREEN, YELLOW, PURPLE);
		festiveLights(LightColors.GREEN_RED, FestiveLightsBlock.All::new, GREEN, RED);
		festiveLights(LightColors.PRIMARY_COLORS, FestiveLightsBlock.All::new, GREEN, RED, BLUE);
		festiveLights(LightColors.BLUE_WHITE, FestiveLightsBlock.All::new, BLUE, COOL_WHITE);
		festiveLights(LightColors.RED_WHITE, FestiveLightsBlock.All::new, RED, WARM_WHITE);
		festiveLights(LightColors.RED_WHITE_BLUE, FestiveLightsBlock.All::new, BLUE, RED, WARM_WHITE);
	}

	static void festiveLights(String colorName, Function<int[], FestiveLightsBlock> func, int... colors) {
		final String name = "f" + colorName;
		Xb.REG.block(addLightName(name + LightShapes.ALL_FACES), new FestiveLightsBlock.All(colors));
		Xb.REG.block(addLightName(name + LightShapes.HORIZONTAL_FACES), new FestiveLightsBlock.Horizontal(colors));
		Xb.REG.block(addLightName(name + LightShapes.SINGLE_FACE), new FestiveLightsBlock.Single(colors));
		Xb.REG.block(addLightName(name + LightShapes.PENDANT), new FestiveLightsBlock.Pendant(colors));
	}

	static String addLightName(String name) {
		FESTIVE_LIGHTS.add(Xb.REG.id(name));
		return name;
	}
}
