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

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import grondag.xblocks.XbConfig;
import grondag.xblocks.XbConfig.Key;

@Environment(EnvType.CLIENT)
public class ConfigScreen {
	private static ConfigEntryBuilder ENTRY_BUILDER = ConfigEntryBuilder.create();

	static Text[] parse(String key) {
		return Arrays.stream(I18n.translate("config.xb.help.force_key").split(";")).map(s ->  new LiteralText(s)).collect(Collectors.toList()).toArray(new Text[0]);
	}

	static Screen getScreen(Screen parent) {

		final ConfigBuilder builder = ConfigBuilder.create().setParentScreen(parent).setTitle(new TranslatableText("config.xb.title")).setSavingRunnable(ConfigScreen::saveUserInput);
		// VANILLA
		final ConfigCategory blocks = builder.getOrCreateCategory(new TranslatableText("config.xb.category.vanilla"));

		blocks.addEntry(ENTRY_BUILDER.startEnumSelector(
				new TranslatableText("config.xb.value.mod_key"),
				Key.class,
				modKey)
				.setDefaultValue(DEFAULTS.modKey)
				.setSaveConsumer(b -> modKey = b)
				.setEnumNameProvider(a -> new LiteralText(a.toString()))
				.setTooltip(parse("config.xb.help.mod_key"))
				.build());

		blocks.addEntry(ENTRY_BUILDER.startEnumSelector(
				new TranslatableText("config.xb.value.force_key"),
				Key.class,
				forceKey)
				.setDefaultValue(DEFAULTS.forceKey)
				.setSaveConsumer(b -> forceKey = b)
				.setEnumNameProvider(a -> new LiteralText(a.toString()))
				.setTooltip(parse("config.xb.help.force_key"))
				.build());

		return builder.build();
	}

	private static void saveUserInput() {
		XbConfig.saveConfig();
	}
}
