/*******************************************************************************
 * Copyright 2019 grondag
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/

package grondag.xblocks.block;

import java.util.function.Function;

import grondag.xblocks.Xb;
import grondag.xm.api.block.XmBlockRegistry;
import grondag.xm.api.connect.world.BlockTest;
import grondag.xm.api.modelstate.primitive.PrimitiveState;
import grondag.xm.api.modelstate.primitive.PrimitiveStateFunction;
import grondag.xm.api.paint.XmPaint;
import grondag.xm.api.primitive.simple.SquareColumn;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.entity.EntityContext;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateFactory.Builder;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.IWorld;

public class ColumnHelper {
    
    private static final VoxelShape SHAPE;
    
    static {
        
        final double p = 1.0/16.0;
        final double q = 1 - p;
        
        VoxelShape shape = VoxelShapes.cuboid(p, p, p, q, q, q);
        
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0, 0, 0, p, p, 1));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0, 0, 0, p, 1, p));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0, 0, 0, 1, p, p));
        
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(q, q, 0, 1, 1, 1));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(q, 0, q, 1, 1, 1));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0, q, q, 1, 1, 1));
        
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(q, 0, 0, 1, p, 1));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(q, 0, 0, 1, 1, p));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0, q, 0, 1, 1, p));
        
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0, q, 0, p, 1, 1));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0, 0, q, p, 1, 1));
        shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0, 0, q, 1, p, 1));
                
        SHAPE = shape;
    }
    
    public static Block register(String idString, Block template, XmPaint endPaint, XmPaint sidePaint, XmPaint cutPaint, XmPaint innerPaint, int cutCount) {
        final PrimitiveState defaultState = SquareColumn.INSTANCE.newState()
                .paint(SquareColumn.SURFACE_END, endPaint)
                .paint(SquareColumn.SURFACE_SIDE, sidePaint)
                .paint(SquareColumn.SURFACE_CUT, cutPaint)
                .paint(SquareColumn.SURFACE_LAMP, innerPaint)
                .apply(s -> { 
                        SquareColumn.setCutCount(cutCount, s);
                        SquareColumn.setCutsOnEdge(true, s);})
                .releaseToImmutable();
    
        final Block column = Xb.register(new PillarBlock(FabricBlockSettings.copy(template).build()) {

            @Override
            public BlockState getPlacementState(ItemPlacementContext ctx) {
                FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());
                return this.getDefaultState()
                        .with(AXIS, ctx.getSide().getAxis())
                        .with(Properties.WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
             }
            
            @Override
            protected void appendProperties(Builder<Block, BlockState> builder) {
                super.appendProperties(builder);
                builder.add(Properties.WATERLOGGED);
            }
            
            @Override
            public boolean hasSidedTransparency(BlockState blockState_1) {
                return true;
            }

            @Override
            public VoxelShape getOutlineShape(BlockState blockState, BlockView blockView, BlockPos pos, EntityContext entityContext) {
                return SHAPE;
            }
            
            @Override
            public BlockState getStateForNeighborUpdate(BlockState blockState, Direction face, BlockState blockState2, IWorld world, BlockPos pos, BlockPos pos2) {
                if (blockState.get(Properties.WATERLOGGED)) {
                   world.getFluidTickScheduler().schedule(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
                }
                return super.getStateForNeighborUpdate(blockState, face, blockState2, world, pos, pos2);
             }
            
            @Override
            public FluidState getFluidState(BlockState blockState) {
                return blockState.get(Properties.WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(blockState);
             }
            
        }, idString + "_square_column");
        
        BlockTest<PrimitiveState> joinFunc = ctx -> {
            final BlockState fromBlock = ctx.fromBlockState();
            final BlockState toBlock = ctx.toBlockState();
            return fromBlock.getBlock() == toBlock.getBlock()
                    && fromBlock.contains(PillarBlock.AXIS)
                    && fromBlock.get(PillarBlock.AXIS) == toBlock.get(PillarBlock.AXIS);};
        
        final Function<BlockState, PrimitiveStateFunction> stateFunc = bs -> PrimitiveStateFunction.builder()
                    .withDefaultState(PrimitiveState.AXIS_FROM_BLOCKSTATE.mutate(defaultState.mutableCopy(), bs))
                    .withJoin(joinFunc)
                    .build();
        
        XmBlockRegistry.addBlockStates(column, stateFunc);
        
        return column;
    }
}
