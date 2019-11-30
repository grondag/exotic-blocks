package grondag.xblocks.item;

import net.minecraft.item.Item;

import grondag.xblocks.Xb;

public enum XbItems {
	;

	public static final Item PORTABLE_CUTTER = Xb.REG.item("iron_cutter", new PortableCutter(Xb.REG.itemSettings().maxCount(1)));
}
