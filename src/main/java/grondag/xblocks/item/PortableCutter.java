package grondag.xblocks.item;

import net.minecraft.network.chat.TranslatableComponent;
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

	private static final TranslatableComponent CONTAINER_NAME = new TranslatableComponent("container.stonecutter", new Object[0]);

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
