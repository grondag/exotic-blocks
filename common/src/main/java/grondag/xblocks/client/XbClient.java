/*
`````TGGG * This file is part of Exotic Blocks and is licensed to the project under
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

package grondag.xblocks.client;

import dev.architectury.registry.client.keymappings.KeyMappingRegistry;
import org.lwjgl.glfw.GLFW;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;

import io.vram.frex.base.client.model.ProceduralModel;
import io.vram.modkeys.api.client.ModKeyBinding;

import grondag.xblocks.Xb;
import grondag.xblocks.init.FestiveLights;

public class XbClient {
	public static void initialize() {
		ProceduralModel.createAndRegisterProvider(FestiveLightsModel::build, FestiveLights.FESTIVE_LIGHTS.toArray(new ResourceLocation[FestiveLights.FESTIVE_LIGHTS.size()]));

		final var forceKey = new KeyMapping("key.xb.force", GLFW.GLFW_KEY_LEFT_CONTROL, "key.xb.category");
		KeyMappingRegistry.register(forceKey);
		ModKeyBinding.setBinding(Xb.FORCE_KEY_NAME, forceKey);

		final var modifyKey = new KeyMapping("key.xb.modify", Minecraft.ON_OSX ? GLFW.GLFW_KEY_LEFT_SUPER : GLFW.GLFW_KEY_LEFT_ALT, "key.xb.category");
		KeyMappingRegistry.register(modifyKey);
		ModKeyBinding.setBinding(Xb.MODIFY_KEY_NAME, modifyKey);
	}
}
