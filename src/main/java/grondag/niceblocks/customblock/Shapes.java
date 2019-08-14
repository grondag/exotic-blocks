package grondag.niceblocks.customblock;

import java.util.function.Function;

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
import net.minecraft.block.BlockRenderLayer;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Shapes {
    public static void init() {
        initSquareColumn();
        initGraniteExtras();
    }
    
    public static class StairLike extends StairsBlock {
        protected StairLike(BlockState blockState, Settings block$Settings) {
            super(blockState, block$Settings);
        }
    }

    private static void initGraniteExtras() {
//        final Block graniteStairs = new StairsBlock(Blocks.POLISHED_GRANITE.getDefaultState(), Block.Settings.copy(Blocks.POLISHED_GRANITE);
//        Identifier id = new Identifier(NiceBlocks.MODID, "square_column");
//        Registry.BLOCK.add(id, column);
//        Registry.ITEM.add(id, new BlockItem(column, new Item.Settings().maxCount(64).group(ItemGroup.BUILDING_BLOCKS)));
    }
    
    
    private static void initSquareColumn() {
        final SimpleModelState defaultState = XmPrimitives.COLUMN_SQUARE.newState()
                .paint(SquareColumnPrimitive.SURFACE_MAIN, XmPaint.finder()
                        .textureDepth(2)
                        .texture(0, XmTextures.BIGTEX_SANDSTONE)
                        .textureColor(0, 0xFF99BBAA)
                        .texture(1, XmTextures.BORDER_GRITTY_SINGLE_LINE)
                        .blendMode(1, BlockRenderLayer.TRANSLUCENT)
                        .textureColor(1, 0xFF709080)
                        .find())
                .paint(SquareColumnPrimitive.SURFACE_CUT, XmPaint.finder()
                        .texture(0, XmTextures.BIGTEX_SANDSTONE)
                        .textureColor(0, 0xFF99BBAA)
                        .find())
                .paint(SquareColumnPrimitive.SURFACE_LAMP, XmPaint.finder()
                        .disableAo(0, true)
                        .disableDiffuse(0, true)
                        .emissive(0, true)
                        .texture(0, XmTextures.WHITE)
                        .textureColor(0, 0xFFDDFFFF)
                        .find())
                .apply(s -> { 
                        SquareColumnPrimitive.setCutCount(3, s);
                        SquareColumnPrimitive.setCutsOnEdge(false, s);})
                .releaseToImmutable();

        final Block column = new PillarBlock(FabricBlockSettings.of(Material.STONE).build());
        Identifier id = new Identifier(NiceBlocks.MODID, "square_column");
        Registry.BLOCK.add(id, column);
        Registry.ITEM.add(id, new BlockItem(column, new Item.Settings().maxCount(64).group(ItemGroup.BUILDING_BLOCKS)));
        
        BlockTest<SimpleModelState> joinFunc = ctx -> {
            final BlockState fromBlock = ctx.fromBlockState();
            final BlockState toBlock = ctx.toBlockState();
            return fromBlock.getBlock() == toBlock.getBlock()
                    && fromBlock.contains(PillarBlock.AXIS)
                    && fromBlock.get(PillarBlock.AXIS) == toBlock.get(PillarBlock.AXIS);};
        
        final Function<BlockState, SimpleModelStateFunction> stateFunc = bs -> SimpleModelStateFunction.builder()
                    .withDefaultState(SimpleModelState.AXIS_FROM_BLOCKSTATE.apply(defaultState.mutableCopy(), bs))
                    .withJoin(joinFunc)
                    .build();
        
        XmBlockRegistry.addBlockStates(column, stateFunc);
    }
}