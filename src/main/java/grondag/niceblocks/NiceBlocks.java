package grondag.niceblocks;

import grondag.niceblocks.basics.Granite;
import grondag.niceblocks.connected.ConnectedGlass;
import grondag.niceblocks.customblock.Shapes;
import net.fabricmc.api.ModInitializer;

public class NiceBlocks implements ModInitializer {
    public static final String MODID = "niceblocks";
    @Override
    public void onInitialize() {
        Granite.init();
        ConnectedGlass.init();
        Shapes.init();
    }
}
