package grondag.niceblocks;

import static grondag.xm.api.texture.TextureGroup.STATIC_BORDERS;
import static grondag.xm.api.texture.TextureRenderIntent.OVERLAY_ONLY;
import static grondag.xm.api.texture.TextureRotation.ROTATE_NONE;
import static grondag.xm.api.texture.TextureScale.SINGLE;

import java.util.function.Function;

import grondag.xm.api.block.XmBlockRegistry;
import grondag.xm.api.connect.world.BlockTest;
import grondag.xm.api.modelstate.ModelState;
import grondag.xm.api.paint.XmPaint;
import grondag.xm.api.texture.TextureSet;
import grondag.xm.init.XmPrimitives;
import grondag.xm.model.state.PrimitiveModelState;
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.BlockRenderLayer;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;

public class NiceBlocks implements ModInitializer {
    static final String MODID = "niceblocks";
    @Override
    public void onInitialize() {
        borderedGlass();
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

    private static void borderedGlass() {
        
        final TextureSet fancyGlass = TextureSet.builder().displayNameToken("fancy_glass")
                .baseTextureName("niceblocks:blocks/glass")
                .versionCount(1)
                .scale(SINGLE)
                .layout(NiceTextureLayoutMap.BORDER_LAYOUT)
                .rotation(ROTATE_NONE)
                .renderIntent(OVERLAY_ONLY).groups(STATIC_BORDERS).build("niceblocks:fancy_glass");
        
        // Define our paint
        final XmPaint paint = XmPaint.finder().textureDepth(1)
                .texture(0, fancyGlass)
                .blendMode(0, BlockRenderLayer.TRANSLUCENT)
                .textureColor(0, 0xFFAAAAAA)
                .find();
        
        // Function to map block states to default model state
        final Function<BlockState, ModelState> defaultStateFunc = b -> {
            PrimitiveModelState ms = XmPrimitives.CUBE.newState();
            ms.paintAll(paint);
            return ms.releaseToImmutable();
        };
        
        // Function to determine if blocks are joined for connected textures/shapes
        BlockTest<PrimitiveModelState> blockJoinTest = ctx -> {
            return ctx.fromBlockState().getBlock() == ctx.toBlockState().getBlock();
        };
        
        // Remap glass block using our new model
        XmBlockRegistry.register(Blocks.GLASS, defaultStateFunc, PrimitiveModelState.DEFAULT_PRIMITIVE, blockJoinTest);
        
    }
}
