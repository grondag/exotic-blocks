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
import static grondag.xblocks.XbConfig.fancyGlass;

import java.util.Optional;

import grondag.xblocks.XbConfig;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.gui.entries.BooleanListEntry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.resource.language.I18n;

@Environment(EnvType.CLIENT)
public class ConfigScreen {
    static Screen getScreen(Screen parent) {

        ConfigBuilder builder = ConfigBuilder.create().setParentScreen(parent).setTitle("config.xblocks.title").setSavingRunnable(ConfigScreen::saveUserInput);

        // VANILLA
        ConfigCategory blocks = builder.getOrCreateCategory("config.xblocks.category.vanilla");
        
        blocks.addEntry(new BooleanListEntry("config.xblocks.value.fancy_glass", fancyGlass, "config.xblocks.reset", () -> DEFAULTS.fancyGlass,
                b -> fancyGlass = b, () -> Optional.of(I18n.translate("config.xblocks.help.fancy_glass").split(";"))));

        return builder.build();
    }

    private static void saveUserInput() {
        XbConfig.saveConfig();
    }
}
