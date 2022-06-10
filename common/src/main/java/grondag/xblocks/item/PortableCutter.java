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

package grondag.xblocks.item;

import net.minecraft.network.chat.Component;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class PortableCutter extends Item {
	private static final Component CONTAINER_NAME = Component.translatable("container.stonecutter", new Object[0]);

	public PortableCutter(Properties settings) {
		super(settings);
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player playerEntity, InteractionHand hand) {
		if (!world.isClientSide) {
			final MenuProvider provider = new SimpleMenuProvider((i, playerInventory, player) -> {
				return new PortableCutterContainer(i, playerInventory, ContainerLevelAccess.create(world, playerEntity.blockPosition()), player.getItemInHand(hand));
			}, CONTAINER_NAME);

			playerEntity.openMenu(provider);
			playerEntity.awardStat(Stats.INTERACT_WITH_STONECUTTER);
		}

		return InteractionResultHolder.success(playerEntity.getItemInHand(hand));
	}
}
