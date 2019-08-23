package grondag.xblocks.test;

import grondag.xblocks.Xb;
import grondag.xm.api.block.XmBlockRegistry;
import grondag.xm.api.collision.CollisionDispatcher;
import grondag.xm.api.modelstate.WorldToSimpleModelState;
import grondag.xm.api.primitive.simple.IcosahedralSphere;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

/**
 * Test various primitives
 */
public class Shapes {
    
    public static void init() {
        Block block;
        
        block = new Block(Block.Settings.copy(Blocks.STONE)) {
            @Override
            public VoxelShape getOutlineShape(BlockState blockState, BlockView blockView, BlockPos pos, EntityContext entityContext) {
                return CollisionDispatcher.shapeFor(WorldToSimpleModelState.ofDefaultState(IcosahedralSphere.INSTANCE.defaultState())
                        .apply(blockState, blockView, pos, false));
            }  
        };
        
        Xb.register(block, "icosphere");
        
        XmBlockRegistry.addBlock(block, WorldToSimpleModelState.ofDefaultState(
                IcosahedralSphere.INSTANCE.newState()
                    .paintAll(TestPaints.STUFF)
                    .releaseToImmutable()));
    }
}
