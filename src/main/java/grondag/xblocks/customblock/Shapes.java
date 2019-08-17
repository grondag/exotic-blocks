package grondag.xblocks.customblock;

import java.util.function.Function;

import grondag.xblocks.XB;
import grondag.xblocks.basics.Granite;
import grondag.xm.api.block.XmBlockRegistry;
import grondag.xm.api.block.base.StairLike;
import grondag.xm.api.connect.world.BlockTest;
import grondag.xm.api.modelstate.SimpleModelState;
import grondag.xm.api.modelstate.SimpleModelStateFunction;
import grondag.xm.api.modelstate.SimpleModelStateMap;
import grondag.xm.api.paint.XmPaint;
import grondag.xm.api.primitive.simple.CubeWithAxis;
import grondag.xm.api.primitive.simple.Sphere;
import grondag.xm.api.primitive.simple.SquareColumn;
import grondag.xm.api.primitive.simple.Wedge;
import grondag.xm.api.texture.XmTextures;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderLayer;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.block.PillarBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction.Axis;
import net.minecraft.util.registry.Registry;

public class Shapes {
    public static void init() {
        initSquareColumn();
        initGraniteExtras();
    }
    
    public static SimpleModelState.Mutable testShape(SimpleModelState.Mutable state, BlockState bs) {
        state.orientationIndex(Axis.Z.ordinal());
        return state;
    }
    
    public static final XmPaint RED = XmPaint.finder()
            .textureDepth(1)
            .texture(0, XmTextures.TILE_NOISE_SUBTLE)
            .textureColor(0, 0xFFFF8080)
            .find();
    
    public static final XmPaint BLUE = XmPaint.finder()
            .textureDepth(1)
            .texture(0, XmTextures.TILE_NOISE_SUBTLE)
            .textureColor(0, 0xFF8080FF)
            .find();
    
    
    private static void initGraniteExtras() {
//        final Block graniteWedge = new StairLike(Blocks.POLISHED_GRANITE.getDefaultState(), Block.Settings.copy(Blocks.POLISHED_GRANITE), Axis.Y, XmPrimitives.WEDGE::newState);
//        Identifier id = new Identifier(NiceBlocks.MODID, "polished_granite_wedge");
//        Registry.BLOCK.add(id, graniteWedge);
//        Registry.ITEM.add(id, new BlockItem(graniteWedge, new Item.Settings().maxCount(64).group(ItemGroup.BUILDING_BLOCKS)));
//        
//        XmBlockRegistry.addBlockStates(graniteWedge, bs -> SimpleModelStateFunction.builder()
//                .withDefaultState(SimpleModelState.STAIRS_FROM_BLOCKSTATE.apply(
//                        XmPrimitives.WEDGE.newState().paintAll(Granite.GRANITE_POLISHED), bs))
//                .build());
        Identifier id;
        
        final Block shapeTest = new Block(Block.Settings.copy(Blocks.ACACIA_LOG));
        id = new Identifier(XB.MODID, "shapetest");
        Registry.BLOCK.add(id, shapeTest);
        Registry.ITEM.add(id, new BlockItem(shapeTest, new Item.Settings().maxCount(64).group(ItemGroup.BUILDING_BLOCKS)));
        
        XmBlockRegistry.addBlockStates(shapeTest, bs -> SimpleModelStateFunction.builder()
                .withDefaultState(((SimpleModelStateMap.Modifier)Shapes::testShape).apply(
                        CubeWithAxis.INSTANCE.newState()
                            .paint(CubeWithAxis.SURFACE_ENDS, RED)
                            .paint(CubeWithAxis.SURFACE_SIDES, BLUE), bs))
                .withUpdate((SimpleModelStateMap.Modifier)Shapes::testShape)
                .build());
        
//        XmBlockRegistry.addBlockStates(shapeTest, bs -> SimpleModelStateFunction.builder()
//                .withDefaultState(((SimpleModelStateMap.Modifier)Shapes::testShape).apply(
//                        XmPrimitives.CUBE.newState()
//                            .paint(CubePrimitive.SURFACE_BOTTOM, RED)
//                            .paint(CubePrimitive.SURFACE_FRONT, BLUE), bs))
//                .withUpdate((SimpleModelStateMap.Modifier)Shapes::testShape)
//                .build());
        
        final Block graniteWedgeX = StairLike.ofAxisX(Blocks.POLISHED_GRANITE.getDefaultState(), Block.Settings.copy(Blocks.POLISHED_GRANITE), Wedge.INSTANCE::newState);
        id = new Identifier(XB.MODID, "polished_granite_wedge_x");
        Registry.BLOCK.add(id, graniteWedgeX);
        Registry.ITEM.add(id, new BlockItem(graniteWedgeX, new Item.Settings().maxCount(64).group(ItemGroup.BUILDING_BLOCKS)));
        
        XmBlockRegistry.addBlockStates(graniteWedgeX, bs -> SimpleModelStateFunction.builder()
                .withDefaultState(StairLike.MODELSTATE_FROM_BLOCKSTATE.apply(
                        Wedge.INSTANCE.newState().paintAll(Granite.GRANITE_POLISHED), bs))
                .build());
        
        
        final Block graniteDodec = new Block(Block.Settings.copy(Blocks.POLISHED_GRANITE));
        id = new Identifier(XB.MODID, "polished_granite_dodec");
        Registry.BLOCK.add(id, graniteDodec);
        Registry.ITEM.add(id, new BlockItem(graniteDodec, new Item.Settings().maxCount(64).group(ItemGroup.BUILDING_BLOCKS)));
        
        XmBlockRegistry.addBlock(graniteDodec, SimpleModelStateFunction.ofDefaultState(
                Sphere.INSTANCE.newState()
                    .paintAll(Granite.GRANITE_POLISHED)
                    .releaseToImmutable()));
    }
    
    
    private static void initSquareColumn() {
        final SimpleModelState defaultState = SquareColumn.INSTANCE.newState()
                .paint(SquareColumn.SURFACE_MAIN, XmPaint.finder()
                        .textureDepth(2)
                        .texture(0, XmTextures.BIGTEX_SANDSTONE)
                        .textureColor(0, 0xFF99BBAA)
                        .texture(1, XmTextures.BORDER_GRITTY_SINGLE_LINE)
                        .blendMode(1, BlockRenderLayer.TRANSLUCENT)
                        .textureColor(1, 0xFF709080)
                        .find())
                .paint(SquareColumn.SURFACE_CUT, XmPaint.finder()
                        .texture(0, XmTextures.BIGTEX_SANDSTONE)
                        .textureColor(0, 0xFF99BBAA)
                        .find())
                .paint(SquareColumn.SURFACE_LAMP, XmPaint.finder()
                        .disableAo(0, true)
                        .disableDiffuse(0, true)
                        .emissive(0, true)
                        .texture(0, XmTextures.WHITE)
                        .textureColor(0, 0xFFDDFFFF)
                        .find())
                .apply(s -> { 
                        SquareColumn.setCutCount(3, s);
                        SquareColumn.setCutsOnEdge(false, s);})
                .releaseToImmutable();

        final Block column = new PillarBlock(FabricBlockSettings.of(Material.STONE).build());
        Identifier id = new Identifier(XB.MODID, "square_column");
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