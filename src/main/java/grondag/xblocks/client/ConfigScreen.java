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

package grondag.xblocks.client;

import static grondag.xblocks.XbConfig.DEFAULTS;
import static grondag.xblocks.XbConfig.forceKey;
import static grondag.xblocks.XbConfig.modKey;

import java.util.Optional;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.gui.entries.EnumListEntry;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.resource.language.I18n;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import grondag.xblocks.XbConfig;
import grondag.xblocks.XbConfig.Key;

@Environment(EnvType.CLIENT)
public class ConfigScreen {
	@SuppressWarnings("deprecation")
	static Screen getScreen(Screen parent) {

		final ConfigBuilder builder = ConfigBuilder.create().setParentScreen(parent).setTitle("config.xblocks.title").setSavingRunnable(ConfigScreen::saveUserInput);

		// VANILLA
		final ConfigCategory blocks = builder.getOrCreateCategory("config.xblocks.category.vanilla");

		blocks.addEntry(new EnumListEntry<>(
				"config.xblocks.value.mod_key",
				Key.class,
				modKey,
				"config.xblocks.reset",
				() -> DEFAULTS.modKey,
				b -> modKey = b,
				a -> a.toString(),
				() -> Optional.of(I18n.translate("config.xblocks.help.mod_key").split(";"))));

		blocks.addEntry(new EnumListEntry<>(
				"config.xblocks.value.force_key",
				Key.class,
				forceKey,
				"config.xblocks.reset",
				() -> DEFAULTS.forceKey,
				b -> forceKey = b,
				a -> a.toString(),
				() -> Optional.of(I18n.translate("config.xblocks.help.force_key").split(";"))));

		return builder.build();
	}

	private static void saveUserInput() {
		XbConfig.saveConfig();
	}
}
