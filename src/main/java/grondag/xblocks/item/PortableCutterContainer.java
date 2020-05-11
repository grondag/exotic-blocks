package grondag.xblocks.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.StonecutterScreenHandler;

public class PortableCutterContainer extends StonecutterScreenHandler {
	final ItemStack cutter;

	public PortableCutterContainer(int i, PlayerInventory playerInventory, ScreenHandlerContext blockContext, ItemStack cutter) {
		super(i, playerInventory, blockContext);
		this.cutter = cutter;
	}

	@Override
	public boolean canUse(PlayerEntity playerEntity) {
		return true;
	}
}
