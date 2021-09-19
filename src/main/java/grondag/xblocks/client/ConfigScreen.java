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

import java.util.Arrays;
import java.util.stream.Collectors;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import grondag.xblocks.XbConfig;
import grondag.xblocks.XbConfig.Key;

@Environment(EnvType.CLIENT)
public class ConfigScreen {
	private static ConfigEntryBuilder ENTRY_BUILDER = ConfigEntryBuilder.create();

	static Component[] parse(String key) {
		return Arrays.stream(I18n.get("config.xb.help.force_key").split(";")).map(s ->  new TextComponent(s)).collect(Collectors.toList()).toArray(new Component[0]);
	}

	static Screen getScreen(Screen parent) {

		final ConfigBuilder builder = ConfigBuilder.create().setParentScreen(parent).setTitle(new TranslatableComponent("config.xb.title")).setSavingRunnable(ConfigScreen::saveUserInput);
		// VANILLA
		final ConfigCategory blocks = builder.getOrCreateCategory(new TranslatableComponent("config.xb.category.vanilla"));

		blocks.addEntry(ENTRY_BUILDER.startEnumSelector(
				new TranslatableComponent("config.xb.value.mod_key"),
				Key.class,
				modKey)
				.setDefaultValue(DEFAULTS.modKey)
				.setSaveConsumer(b -> modKey = b)
				.setEnumNameProvider(a -> new TextComponent(a.toString()))
				.setTooltip(parse("config.xb.help.mod_key"))
				.build());

		blocks.addEntry(ENTRY_BUILDER.startEnumSelector(
				new TranslatableComponent("config.xb.value.force_key"),
				Key.class,
				forceKey)
				.setDefaultValue(DEFAULTS.forceKey)
				.setSaveConsumer(b -> forceKey = b)
				.setEnumNameProvider(a -> new TextComponent(a.toString()))
				.setTooltip(parse("config.xb.help.force_key"))
				.build());

		return builder.build();
	}

	private static void saveUserInput() {
		XbConfig.saveConfig();
	}
}
