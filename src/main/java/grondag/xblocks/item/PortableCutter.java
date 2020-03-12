package grondag.xblocks.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.BlockContext;
import net.minecraft.screen.NameableScreenHandlerFactory;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
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
			final NameableScreenHandlerFactory provider = new SimpleNamedScreenHandlerFactory((i, playerInventory, player) -> {
				return new PortableCutterContainer(i, playerInventory, BlockContext.create(world, playerEntity.getSenseCenterPos()), player.getStackInHand(hand));
			}, CONTAINER_NAME);

			playerEntity.openHandledScreen(provider);
			playerEntity.incrementStat(Stats.INTERACT_WITH_STONECUTTER);
		}
		return TypedActionResult.success(playerEntity.getStackInHand(hand));
	}
}
