package grondag.xblocks.init;

import grondag.xblocks.Xb;
import grondag.xblocks.item.PortableCutter;
import net.minecraft.world.item.Item;

public enum XbItems {
	;

	public static final Item PORTABLE_CUTTER = Xb.REG.item("iron_cutter", new PortableCutter(Xb.REG.itemSettings().stacksTo(1)));
}
