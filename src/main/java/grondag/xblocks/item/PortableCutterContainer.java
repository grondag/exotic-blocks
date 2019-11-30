package grondag.xblocks.item;

import net.minecraft.container.BlockContext;
import net.minecraft.container.StonecutterContainer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;

public class PortableCutterContainer extends StonecutterContainer {
	final ItemStack cutter;

	public PortableCutterContainer(int i, PlayerInventory playerInventory, BlockContext blockContext, ItemStack cutter) {
		super(i, playerInventory, blockContext);
		this.cutter = cutter;
	}

	@Override
	public boolean canUse(PlayerEntity playerEntity) {
		return true;
	}
}
