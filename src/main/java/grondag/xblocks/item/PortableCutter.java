package grondag.xblocks.item;

import net.minecraft.client.network.ClientDummyContainerProvider;
import net.minecraft.container.BlockContext;
import net.minecraft.container.NameableContainerProvider;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stat.Stats;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class PortableCutter extends Item {

	private static final TranslatableText CONTAINER_NAME = new TranslatableText("container.stonecutter", new Object[0]);

	public PortableCutter(Settings settings) {
		super(settings);
	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
		if (!world.isClient) {
			final NameableContainerProvider provider = new ClientDummyContainerProvider((i, playerInventory, player) -> {
				return new PortableCutterContainer(i, playerInventory, BlockContext.create(world, playerEntity.getBlockPos()), player.getStackInHand(hand));
			}, CONTAINER_NAME);

			playerEntity.openContainer(provider);
			playerEntity.incrementStat(Stats.INTERACT_WITH_STONECUTTER);
		}
		return TypedActionResult.success(playerEntity.getStackInHand(hand));
	}
}