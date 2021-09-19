package grondag.xblocks.item;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.StonecutterMenu;
import net.minecraft.world.item.ItemStack;

public class PortableCutterContainer extends StonecutterMenu {
	final ItemStack cutter;

	public PortableCutterContainer(int i, Inventory playerInventory, ContainerLevelAccess blockContext, ItemStack cutter) {
		super(i, playerInventory, blockContext);
		this.cutter = cutter;
	}

	@Override
	public boolean stillValid(Player playerEntity) {
		return true;
	}
}
