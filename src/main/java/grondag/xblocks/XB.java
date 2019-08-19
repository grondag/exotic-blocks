package grondag.xblocks;

import grondag.xblocks.basics.Granite;
import grondag.xblocks.connected.ConnectedGlass;
import grondag.xblocks.customblock.Shapes;
import grondag.xblocks.test.CsgTest;
import grondag.xblocks.test.RotationTest;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

public class XB implements ModInitializer {
    public static final String MODID = "exotic-blocks";
    @Override
    public void onInitialize() {
        Granite.init();
        ConnectedGlass.init();
        Shapes.init();
        CsgTest.init();
        RotationTest.init();
    }
    
    public static String idString(String path) {
        return MODID + ":" + path;
    }
    
    public static Identifier id(String path) {
        return new Identifier(MODID, path);
    }
}
