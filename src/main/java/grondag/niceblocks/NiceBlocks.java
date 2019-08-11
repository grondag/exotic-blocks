package grondag.niceblocks;

import grondag.niceblocks.connected.ConnectedGlass;
import net.fabricmc.api.ModInitializer;

public class NiceBlocks implements ModInitializer {
    static final String MODID = "niceblocks";
    @Override
    public void onInitialize() {
        ConnectedGlass.init();
        initSimpleBlocks();
    }
    
    private static final void initSimpleBlocks() {
  
//        WorldToModelStateFunction worldStateFunc,
//        BlockTest blockJoinTest
        
//        model = XmPrimitives.WEDGE.newState();
//        model.paintAll(paint);
//        register(new XmSimpleBlock(FabricBlockSettings.of(Material.STONE).strength(1, 1).build(), model), "test_wedge");
//        model.release();
//
//        paint = paintFinder.texture(0, XmTextures.WHITE).textureColor(0, 0xFFFFFFFF).find();
//        model = XmPrimitives.CUBE.newState();
//        model.paintAll(paint);
//        register(new XmSimpleBlock(FabricBlockSettings.of(Material.STONE).strength(1, 1).build(), model), "test_cube");
//        model.release();
    }

//    private static void register(Block block, String name) {
//        Identifier id = new Identifier(MODID, name);
//        Registry.BLOCK.add(id, block);
//        Registry.ITEM.add(id, new BlockItem(block, new Item.Settings().maxCount(64).group(ItemGroup.BUILDING_BLOCKS)));
//    }

    
}
