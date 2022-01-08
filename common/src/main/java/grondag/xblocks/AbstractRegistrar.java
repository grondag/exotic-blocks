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

import java.util.List;

import com.google.common.collect.ImmutableList;

import net.minecraft.resources.ResourceLocation;

public abstract class AbstractRegistrar {
	public final String modId;

	protected AbstractRegistrar(String modId) {
		this.modId = modId;
	}

	public ResourceLocation id(String name) {
		return new ResourceLocation(modId, name);
	}

	public List<ResourceLocation> idList(String... ids) {
		final ImmutableList.Builder<ResourceLocation> builder = ImmutableList.builder();

		for (final String id : ids) {
			builder.add(id(id));
		}

		return builder.build();
	}
}
