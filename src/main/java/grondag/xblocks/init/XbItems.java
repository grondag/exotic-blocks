package grondag.xblocks.init;

import net.minecraft.item.Item;

import grondag.xblocks.Xb;
import grondag.xblocks.item.PortableCutter;

public enum XbItems {
	;

	public static final Item PORTABLE_CUTTER = Xb.REG.item("iron_cutter", new PortableCutter(Xb.REG.itemSettings().maxCount(1)));
}
