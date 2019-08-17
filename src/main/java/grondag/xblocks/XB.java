package grondag.xblocks;

import grondag.xblocks.basics.Granite;
import grondag.xblocks.connected.ConnectedGlass;
import grondag.xblocks.customblock.Shapes;
import net.fabricmc.api.ModInitializer;

public class XB implements ModInitializer {
    public static final String MODID = "exotic-blocks";
    @Override
    public void onInitialize() {
        Granite.init();
        ConnectedGlass.init();
        Shapes.init();
    }
}
