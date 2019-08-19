package grondag.xblocks.test;

import static grondag.xblocks.test.TestPaints.BLUE;
import static grondag.xblocks.test.TestPaints.GREEN;
import static grondag.xblocks.test.TestPaints.RED;

import grondag.xm.Xm;
import grondag.xm.api.block.XmBlockRegistry;
import grondag.xm.api.block.XmProperties;
import grondag.xm.api.modelstate.SimpleModelStateFunction;
import grondag.xm.api.primitive.simple.Cube;
import grondag.xm.api.primitive.simple.CubeWithAxis;
import grondag.xm.api.primitive.simple.CubeWithCorner;
import grondag.xm.api.primitive.simple.CubeWithEdge;
import grondag.xm.api.primitive.simple.CubeWithEdgeRotation;
import grondag.xm.api.primitive.simple.CubeWithFace;
import grondag.xm.api.primitive.simple.CubeWithHorizontalFace;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.state.StateFactory.Builder;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public class RotationTest {
    private RotationTest(){}
    
    public static void init() {
        Identifier id;
        Block block;
        
        id = Xm.id("rotation_test_none");
        block = new Block(Block.Settings.copy(Blocks.STONE));
        Registry.BLOCK.add(id, block);
        Registry.ITEM.add(id, new BlockItem(block, new Item.Settings().maxCount(64).group(ItemGroup.BUILDING_BLOCKS)));
        
        XmBlockRegistry.addBlock(block, SimpleModelStateFunction.ofDefaultState(
                Cube.INSTANCE.newState()
                .releaseToImmutable()));
        
        // AXIS
        
        id = Xm.id("rotation_test_axis");
        block = new Block(Block.Settings.copy(Blocks.STONE)) {

            @Override
            public boolean activate(BlockState blockState, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
                blockState = (BlockState)blockState.cycle(XmProperties.AXIS);
                world.setBlockState(pos, blockState, 10);
                return true;
            }

            @Override
            protected void appendProperties(Builder<Block, BlockState> builder) {
                super.appendProperties(builder);
                builder.add(XmProperties.AXIS);
            }
        };
        Registry.BLOCK.add(id, block);
        Registry.ITEM.add(id, new BlockItem(block, new Item.Settings().maxCount(64).group(ItemGroup.BUILDING_BLOCKS)));
        
        XmBlockRegistry.addBlockStates(block, bs -> SimpleModelStateFunction.builder()
                .withDefaultState(XmProperties.AXIS_MODIFIER.apply(
                        CubeWithAxis.INSTANCE.newState()
                            .paint(CubeWithAxis.SURFACE_ENDS, RED)
                            .paint(CubeWithAxis.SURFACE_SIDES, BLUE), bs))
                .withUpdate(XmProperties.AXIS_MODIFIER)
                .build());
        
        // FACE
        
        id = Xm.id("rotation_test_face");
        block = new Block(Block.Settings.copy(Blocks.STONE)) {
            @Override
            public boolean activate(BlockState blockState, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
                blockState = (BlockState)blockState.cycle(XmProperties.FACE);
                world.setBlockState(pos, blockState, 10);
                return true;
            }

            @Override
            protected void appendProperties(Builder<Block, BlockState> builder) {
                super.appendProperties(builder);
                builder.add(XmProperties.FACE);
            }
        };
        Registry.BLOCK.add(id, block);
        Registry.ITEM.add(id, new BlockItem(block, new Item.Settings().maxCount(64).group(ItemGroup.BUILDING_BLOCKS)));
        
        XmBlockRegistry.addBlockStates(block, bs -> SimpleModelStateFunction.builder()
                .withDefaultState(XmProperties.FACE_MODIFIER.apply(
                        CubeWithFace.INSTANCE.newState()
                            .paint(CubeWithFace.SURFACE_FRONT, RED)
                            .paint(CubeWithFace.SURFACE_SIDES, BLUE), bs))
                .withUpdate(XmProperties.FACE_MODIFIER)
                .build());
        
        // HORIZONTAL FACE
        
        id = Xm.id("rotation_test_horizontal_face");
        block = new Block(Block.Settings.copy(Blocks.STONE)) {
            @Override
            public boolean activate(BlockState blockState, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
                blockState = (BlockState)blockState.cycle(XmProperties.HORIZONTAL_FACE);
                world.setBlockState(pos, blockState, 10);
                return true;
            }

            @Override
            protected void appendProperties(Builder<Block, BlockState> builder) {
                super.appendProperties(builder);
                builder.add(XmProperties.HORIZONTAL_FACE);
            }
        };
        Registry.BLOCK.add(id, block);
        Registry.ITEM.add(id, new BlockItem(block, new Item.Settings().maxCount(64).group(ItemGroup.BUILDING_BLOCKS)));
        
        XmBlockRegistry.addBlockStates(block, bs -> SimpleModelStateFunction.builder()
                .withDefaultState(XmProperties.HORIZONTAL_FACE_MODIFIER.apply(
                        CubeWithHorizontalFace.INSTANCE.newState()
                            .paint(CubeWithHorizontalFace.SURFACE_FRONT, RED)
                            .paint(CubeWithHorizontalFace.SURFACE_SIDES, BLUE), bs))
                .withUpdate(XmProperties.HORIZONTAL_FACE_MODIFIER)
                .build());
        
        
        // CORNER
        
        id = Xm.id("rotation_test_corner");
        block = new Block(Block.Settings.copy(Blocks.STONE)) {
            @Override
            public boolean activate(BlockState blockState, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
                blockState = (BlockState)blockState.cycle(XmProperties.CORNER);
                world.setBlockState(pos, blockState, 10);
                return true;
            }

            @Override
            protected void appendProperties(Builder<Block, BlockState> builder) {
                super.appendProperties(builder);
                builder.add(XmProperties.CORNER);
            }
        };
        Registry.BLOCK.add(id, block);
        Registry.ITEM.add(id, new BlockItem(block, new Item.Settings().maxCount(64).group(ItemGroup.BUILDING_BLOCKS)));
        
        XmBlockRegistry.addBlockStates(block, bs -> SimpleModelStateFunction.builder()
                .withDefaultState(XmProperties.CORNER_MODIFIER.apply(
                        CubeWithCorner.INSTANCE.newState()
                            .paint(CubeWithCorner.SURFACE_FRONT, RED)
                            .paint(CubeWithCorner.SURFACE_BACK, BLUE), bs))
                .withUpdate(XmProperties.CORNER_MODIFIER)
                .build());
        
        // EDGE
        
        id = Xm.id("rotation_test_edge");
        block = new Block(Block.Settings.copy(Blocks.STONE)) {
            @Override
            public boolean activate(BlockState blockState, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
                blockState = (BlockState)blockState.cycle(XmProperties.EDGE);
                world.setBlockState(pos, blockState, 10);
                return true;
            }

            @Override
            protected void appendProperties(Builder<Block, BlockState> builder) {
                super.appendProperties(builder);
                builder.add(XmProperties.EDGE);
            }
        };
        Registry.BLOCK.add(id, block);
        Registry.ITEM.add(id, new BlockItem(block, new Item.Settings().maxCount(64).group(ItemGroup.BUILDING_BLOCKS)));
        
        XmBlockRegistry.addBlockStates(block, bs -> SimpleModelStateFunction.builder()
                .withDefaultState(XmProperties.EDGE_MODIFIER.apply(
                        CubeWithEdge.INSTANCE.newState()
                            .paint(CubeWithEdge.SURFACE_FRONT, RED)
                            .paint(CubeWithEdge.SURFACE_BACK, BLUE)
                            .paint(CubeWithEdge.SURFACE_SIDE, GREEN), bs))
                .withUpdate(XmProperties.EDGE_MODIFIER)
                .build());
        
        
        // EDGE ROTATION
        
        id = Xm.id("rotation_test_full");
        block = new Block(Block.Settings.copy(Blocks.STONE)) {
            @Override
            public boolean activate(BlockState blockState, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
                blockState = (BlockState)blockState.cycle(XmProperties.ROTATION);
                world.setBlockState(pos, blockState, 10);
                return true;
            }

            @Override
            protected void appendProperties(Builder<Block, BlockState> builder) {
                super.appendProperties(builder);
                builder.add(XmProperties.ROTATION);
            }
        };
        Registry.BLOCK.add(id, block);
        Registry.ITEM.add(id, new BlockItem(block, new Item.Settings().maxCount(64).group(ItemGroup.BUILDING_BLOCKS)));
        
        XmBlockRegistry.addBlockStates(block, bs -> SimpleModelStateFunction.builder()
                .withDefaultState(XmProperties.ROTATION_MODIFIER.apply(
                        CubeWithEdgeRotation.INSTANCE.newState()
                            .paint(CubeWithEdgeRotation.SURFACE_BACK, RED)
                            .paint(CubeWithEdgeRotation.SURFACE_BOTTOM, BLUE)
                            .paint(CubeWithEdgeRotation.SURFACE_FRONT, GREEN)
                            .paint(CubeWithEdgeRotation.SURFACE_LEFT, GREEN)
                            .paint(CubeWithEdgeRotation.SURFACE_RIGHT, GREEN)
                            .paint(CubeWithEdgeRotation.SURFACE_TOP, GREEN), bs))
                .withUpdate(XmProperties.ROTATION_MODIFIER)
                .build());
    }
}