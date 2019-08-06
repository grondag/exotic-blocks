package grondag.niceblocks;

import grondag.fermion.color.Chroma;
import grondag.fermion.color.Color;
import grondag.fermion.color.Hue;
import grondag.fermion.color.Luminance;
import grondag.xm.api.modelstate.OwnedModelState;
import grondag.xm.api.paint.XmPaint;
import grondag.xm.api.paint.XmPaintFinder;
import grondag.xm.block.XmSimpleBlock;
import grondag.xm.init.XmPrimitives;
import grondag.xm.init.XmTextures;
import grondag.xm.placement.XmBlockItem;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderLayer;
import net.minecraft.block.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class NiceBlocks implements ModInitializer {
    static final String MODID = "niceblocks";
    @Override
    public void onInitialize() {
        initSimpleBlocks();
    }
    
    private static final void initSimpleBlocks() {

        final XmPaintFinder paintFinder = XmPaint.finder();
        XmPaint paint = paintFinder.textureDepth(2)
                .texture(0, XmTextures.SANDSTONE_ZOOM)
                .textureColor(0, Color.fromHCL(Hue.AZURE, Chroma.NEUTRAL, Luminance.LIGHT).ARGB)
                .texture(1, XmTextures.BORDER_SMOOTH_BLEND)
                .blendMode(1, BlockRenderLayer.TRANSLUCENT)
                .emissive(1, true)
                .textureColor(1, 0xFFFFD300)
                .find();
        
        OwnedModelState model = XmPrimitives.CUBE.newState();
        model.paintAll(paint);
        register(new XmSimpleBlock(FabricBlockSettings.of(Material.STONE).strength(1, 1).build(), model), "test_borders");
        model.release();
        
        model = XmPrimitives.WEDGE.newState();
        model.paintAll(paint);
        register(new XmSimpleBlock(FabricBlockSettings.of(Material.STONE).strength(1, 1).build(), model), "test_wedge");
        model.release();

        paint = paintFinder.texture(0, XmTextures.WHITE).textureColor(0, 0xFFFFFFFF).find();
        model = XmPrimitives.CUBE.newState();
        model.paintAll(paint);
        register(new XmSimpleBlock(FabricBlockSettings.of(Material.STONE).strength(1, 1).build(), model), "test_cube");
        model.release();
    }

    private static void register(Block block, String name) {
        Identifier id = new Identifier(MODID, name);
        Registry.BLOCK.add(id, block);
        Registry.ITEM.add(id, new XmBlockItem(block, new Item.Settings().maxCount(64).group(ItemGroup.BUILDING_BLOCKS)));
    }

}
