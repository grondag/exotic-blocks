package grondag.niceblocks.customblock;

import grondag.niceblocks.NiceBlocks;
import grondag.xm.api.block.XmBlockRegistry;
import grondag.xm.api.connect.world.BlockTest;
import grondag.xm.api.modelstate.SimpleModelState;
import grondag.xm.api.modelstate.SimpleModelStateFunction;
import grondag.xm.api.paint.XmPaint;
import grondag.xm.api.texture.XmTextures;
import grondag.xm.init.XmPrimitives;
import grondag.xm.model.primitive.SquareColumnPrimitive;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.block.PillarBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Shapes {
    public static void init() {
        initSquareColumn();
    }

    private static void initSquareColumn() {
        final SimpleModelState defaultState = XmPrimitives.COLUMN_SQUARE.newState()
                .paint(SquareColumnPrimitive.SURFACE_MAIN, XmPaint.finder()
                        .disableAo(0, true)
                        .texture(0, XmTextures.TILE_NOISE_LIGHT)
                        .textureColor(0, 0xFF99BBAA)
                        .find())
                .paint(SquareColumnPrimitive.SURFACE_CUT, XmPaint.finder()
                        .disableAo(0, true)
                        .texture(0, XmTextures.TILE_NOISE_LIGHT)
                        .textureColor(0, 0xFF99BBAA)
                        .find())
                .paint(SquareColumnPrimitive.SURFACE_LAMP, XmPaint.finder()
                        .disableAo(0, true)
                        .disableDiffuse(0, true)
                        .emissive(0, true)
                        .texture(0, XmTextures.TILE_NOISE_LIGHT)
                        .textureColor(0, 0xFFDDFFFF)
                        .find())
                .releaseToImmutable();

        final Block column = new PillarBlock(FabricBlockSettings.of(Material.STONE).build());
        Identifier id = new Identifier(NiceBlocks.MODID, "square_column");
        Registry.BLOCK.add(id, column);
        Registry.ITEM.add(id, new BlockItem(column, new Item.Settings().maxCount(64).group(ItemGroup.BUILDING_BLOCKS)));
        
        BlockTest<SimpleModelState> joinFunc = ctx -> {
            final BlockState fromBlock = ctx.fromBlockState();
            final BlockState toBlock = ctx.toBlockState();
            return fromBlock == toBlock
                    && fromBlock.contains(PillarBlock.AXIS)
                    && fromBlock.get(PillarBlock.AXIS) == fromBlock.get(PillarBlock.AXIS);};
        
        SimpleModelStateFunction stateFunc = SimpleModelStateFunction.builder()
                .withUpdate(SimpleModelStateFunction.UPDATE_AXIS)
                .withJoin(joinFunc)
                .build();
        
        XmBlockRegistry.register(column, b -> defaultState, stateFunc);
    }
}