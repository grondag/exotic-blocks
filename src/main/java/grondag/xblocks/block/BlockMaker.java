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

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;

import grondag.xblocks.Xb;
import grondag.xm.api.block.XmBlockRegistry;
import grondag.xm.api.block.XmBlockState;
import grondag.xm.api.block.XmProperties;
import grondag.xm.api.block.base.CubicCutoutBlock;
import grondag.xm.api.block.base.NonCubicFacingBlock;
import grondag.xm.api.block.base.NonCubicPillarBlock;
import grondag.xm.api.collision.CollisionDispatcher;
import grondag.xm.api.collision.CollisionShapes;
import grondag.xm.api.connect.world.BlockTest;
import grondag.xm.api.modelstate.primitive.PrimitiveState;
import grondag.xm.api.modelstate.primitive.PrimitiveStateFunction;
import grondag.xm.api.paint.XmPaint;
import grondag.xm.api.primitive.simple.CappedRoundColumn;
import grondag.xm.api.primitive.simple.CappedSquareInsetColumn;
import grondag.xm.api.primitive.simple.CutRoundColumn;
import grondag.xm.api.primitive.simple.CylinderWithAxis;
import grondag.xm.api.primitive.simple.FlatPanel;
import grondag.xm.api.primitive.simple.InsetPanel;
import grondag.xm.api.primitive.simple.RoundCappedRoundColumn;
import grondag.xm.api.primitive.simple.Slab;
import grondag.xm.api.primitive.simple.SquareColumn;
import grondag.xm.api.primitive.simple.WedgeCap;

public class BlockMaker {

	public static Block squareInsetColumn(String idString, Block template, XmPaint endPaint, XmPaint sidePaint, XmPaint cutPaint, XmPaint innerPaint, int cutCount, int lightLevel) {
		final PrimitiveState defaultState = SquareColumn.INSTANCE.newState()
				.paint(SquareColumn.SURFACE_END, endPaint)
				.paint(SquareColumn.SURFACE_SIDE, sidePaint)
				.paint(SquareColumn.SURFACE_CUT, cutPaint)
				.paint(SquareColumn.SURFACE_INLAY, innerPaint)
				.apply(s -> {
					SquareColumn.setCutCount(cutCount, s);
					SquareColumn.setCutsOnEdge(true, s);})
				.releaseToImmutable();

		final Block column = Xb.REG.block(idString + "_square_column", new NonCubicPillarBlock(FabricBlockSettings.copy(template).lightLevel(b -> lightLevel)) {
			@Override
			public VoxelShape getOutlineShape(BlockState blockState, BlockView blockView, BlockPos pos, ShapeContext entityContext) {
				return CollisionShapes.CUBE_WITH_CUTOUTS;
			}
		});

		FenceHelper.add(column);

		final BlockTest<PrimitiveState> joinFunc = ctx -> {
			final BlockState fromBlock = ctx.fromBlockState();
			final BlockState toBlock = ctx.toBlockState();
			final Block a = fromBlock.getBlock();
			final Block b = toBlock.getBlock();
			return (a == b || BlockConnectors.canConnect(a, b))
					&& fromBlock.contains(PillarBlock.AXIS)
					&& fromBlock.get(PillarBlock.AXIS) == toBlock.get(PillarBlock.AXIS);
		};

		final Function<BlockState, PrimitiveStateFunction> stateFunc = bs -> PrimitiveStateFunction.builder()
				.withDefaultState(PrimitiveState.AXIS_FROM_BLOCKSTATE.mutate(defaultState.mutableCopy(), bs))
				.withJoin(joinFunc)
				.build();

		XmBlockRegistry.addBlockStates(column, stateFunc);

		return column;
	}

	public static Block roundColumn(String idString, Block template, XmPaint endPaint, XmPaint sidePaint, XmPaint cutPaint, XmPaint innerPaint) {
		final PrimitiveState defaultState = CylinderWithAxis.INSTANCE.newState()
				.paint(CylinderWithAxis.SURFACE_ENDS, endPaint)
				.paint(CylinderWithAxis.SURFACE_SIDES, sidePaint)
				.releaseToImmutable();

		final Block block = Xb.REG.block(idString + "_round_column", new NonCubicPillarBlock(FabricBlockSettings.copy(template).dynamicBounds()) {
			@Override
			public VoxelShape getOutlineShape(BlockState blockState, BlockView blockView, BlockPos pos, ShapeContext entityContext) {
				return CollisionDispatcher.shapeFor(XmBlockState.modelState(blockState, blockView, pos, false));
			}
		});

		XmBlockRegistry.addBlockStates(block, bs -> PrimitiveStateFunction.builder()
				.withDefaultState(PrimitiveState.AXIS_FROM_BLOCKSTATE.mutate(defaultState.mutableCopy(), bs))
				.build());

		return block;
	}

	public static Block cappedRoundColumn(String idString, Block template, XmPaint endPaint, XmPaint sidePaint, XmPaint cutPaint, XmPaint innerPaint) {
		final PrimitiveState defaultState = CappedRoundColumn.INSTANCE.newState()
				.paint(CappedRoundColumn.SURFACE_ENDS, endPaint)
				.paint(CappedRoundColumn.SURFACE_SIDES, sidePaint)
				.releaseToImmutable();

		final Block block = Xb.REG.block(idString + "_capped_round_column", new NonCubicPillarBlock(FabricBlockSettings.copy(template).dynamicBounds()) {
			@Override
			public VoxelShape getOutlineShape(BlockState blockState, BlockView blockView, BlockPos pos, ShapeContext entityContext) {
				return CollisionDispatcher.shapeFor(XmBlockState.modelState(blockState, blockView, pos, true));
			}
		});


		XmBlockRegistry.addBlockStates(block, bs -> PrimitiveStateFunction.builder()
				.withJoin(BlockConnectors.AXIS_JOIN_SAME_OR_CONNECTABLE)
				.withDefaultState(PrimitiveState.AXIS_FROM_BLOCKSTATE.mutate(defaultState.mutableCopy(), bs))
				.build());

		return block;
	}

	public static Block roundCappedRoundColumn(String idString, Block template, XmPaint endPaint, XmPaint sidePaint, XmPaint cutPaint, XmPaint innerPaint) {
		final PrimitiveState defaultState = RoundCappedRoundColumn.INSTANCE.newState()
				.paint(CutRoundColumn.SURFACE_ENDS, endPaint)
				.paint(CutRoundColumn.SURFACE_CUT, cutPaint)
				.paint(CutRoundColumn.SURFACE_INNER, innerPaint)
				.paint(CutRoundColumn.SURFACE_OUTER, sidePaint)
				.releaseToImmutable();

		final Block block = Xb.REG.block(idString + "_round_capped_round_column", new NonCubicPillarBlock(FabricBlockSettings.copy(template).dynamicBounds()) {
			@Override
			public VoxelShape getOutlineShape(BlockState blockState, BlockView blockView, BlockPos pos, ShapeContext entityContext) {
				return CollisionDispatcher.shapeFor(XmBlockState.modelState(blockState, blockView, pos, true));
			}
		});

		XmBlockRegistry.addBlockStates(block, bs -> PrimitiveStateFunction.builder()
				.withJoin(BlockConnectors.AXIS_JOIN_SAME_OR_CONNECTABLE)
				.withDefaultState(PrimitiveState.AXIS_FROM_BLOCKSTATE.mutate(defaultState.mutableCopy(), bs))
				.build());

		return block;
	}

	public static Block cutRoundColumn(String idString, Block template, XmPaint endPaint, XmPaint sidePaint, XmPaint cutPaint, XmPaint innerPaint, int lightLevel) {
		final PrimitiveState defaultState = CutRoundColumn.INSTANCE.newState()
				.paint(CutRoundColumn.SURFACE_ENDS, endPaint)
				.paint(CutRoundColumn.SURFACE_CUT, cutPaint)
				.paint(CutRoundColumn.SURFACE_INNER, innerPaint)
				.paint(CutRoundColumn.SURFACE_OUTER, sidePaint)
				.releaseToImmutable();

		final Block block = Xb.REG.block(idString + "_cut_round_column", new NonCubicPillarBlock(FabricBlockSettings.copy(template).dynamicBounds().lightLevel(b -> lightLevel)) {
			@Override
			public VoxelShape getOutlineShape(BlockState blockState, BlockView blockView, BlockPos pos, ShapeContext entityContext) {
				return CollisionDispatcher.shapeFor(XmBlockState.modelState(blockState, blockView, pos, true));
			}
		});


		XmBlockRegistry.addBlockStates(block, bs -> PrimitiveStateFunction.builder()
				.withJoin(BlockConnectors.SAME_BLOCK_OR_CONNECTABLE)
				.withDefaultState(PrimitiveState.AXIS_FROM_BLOCKSTATE.mutate(defaultState.mutableCopy(), bs))
				.build());

		return block;
	}

	public static Block cappedSquareColumn(String idString, Block template, XmPaint endPaint, XmPaint sidePaint, XmPaint cutPaint, XmPaint innerPaint, int light) {
		final PrimitiveState defaultState = CappedSquareInsetColumn.INSTANCE.newState()
				.paint(CappedSquareInsetColumn.SURFACE_ENDS, endPaint)
				.paint(CappedSquareInsetColumn.SURFACE_CUT, cutPaint)
				.paint(CappedSquareInsetColumn.SURFACE_INNER, innerPaint)
				.paint(CappedSquareInsetColumn.SURFACE_OUTER, sidePaint)
				.releaseToImmutable();

		final Block block = Xb.REG.block(idString + "_capped_square_column", new NonCubicPillarBlock(FabricBlockSettings.copy(template).dynamicBounds().lightLevel(b -> light)) {
			@Override
			public VoxelShape getOutlineShape(BlockState blockState, BlockView blockView, BlockPos pos, ShapeContext entityContext) {
				return CollisionDispatcher.shapeFor(XmBlockState.modelState(blockState, blockView, pos, true));
			}
		});

		FenceHelper.add(block);

		XmBlockRegistry.addBlockStates(block, bs -> PrimitiveStateFunction.builder()
				.withJoin(BlockConnectors.AXIS_JOIN_SAME_OR_CONNECTABLE)
				.withDefaultState(PrimitiveState.AXIS_FROM_BLOCKSTATE.mutate(defaultState.mutableCopy(), bs))
				.build());

		return block;
	}

	public static Block insetPanel(String idString, Block template, XmPaint paintOuter, XmPaint paintCut, XmPaint paintInner, int lightLevel) {
		final PrimitiveState defaultState = InsetPanel.INSTANCE.newState()
				.paint(InsetPanel.SURFACE_OUTER, paintOuter)
				.paint(InsetPanel.SURFACE_CUT, paintCut)
				.paint(InsetPanel.SURFACE_INNER, paintInner)
				.releaseToImmutable();

		final Block block = Xb.REG.block(idString + "_inset_panel", new CubicCutoutBlock(FabricBlockSettings.copy(template).lightLevel(b -> lightLevel)) {
			@Override
			public VoxelShape getOutlineShape(BlockState blockState, BlockView blockView, BlockPos pos, ShapeContext entityContext) {
				return CollisionShapes.CUBE_WITH_CUTOUTS;
			}
		});

		FenceHelper.add(block);

		XmBlockRegistry.addBlockStates(block, bs -> PrimitiveStateFunction.builder()
				.withJoin(BlockConnectors.SAME_BLOCK_OR_CONNECTABLE)
				.withDefaultState(defaultState.mutableCopy())
				.build());

		return block;
	}

	public static Block flatPanel(String idString, Block template, XmPaint paintOuter, XmPaint paintInner, int lightLevel) {
		final PrimitiveState defaultState = FlatPanel.INSTANCE.newState()
				.paint(FlatPanel.SURFACE_OUTER, paintOuter)
				.paint(FlatPanel.SURFACE_INNER, paintInner)
				.releaseToImmutable();

		final Block block = Xb.REG.block(idString + "_flat_panel", new Block(FabricBlockSettings.copy(template).lightLevel(b -> lightLevel)));

		FenceHelper.add(block);

		XmBlockRegistry.addBlockStates(block, bs -> PrimitiveStateFunction.builder()
				.withJoin(BlockConnectors.SAME_BLOCK_OR_CONNECTABLE)
				.withDefaultState(defaultState.mutableCopy())
				.build());

		return block;
	}

	public static Block wedgeCap(String idString, Block template, XmPaint paintBottom, XmPaint paintTop) {
		final PrimitiveState defaultState = WedgeCap.INSTANCE.newState()
				.paint(WedgeCap.SURFACE_BOTTOM, paintBottom)
				.paint(WedgeCap.SURFACE_TOP, paintTop)
				.releaseToImmutable();

		final Block block = Xb.REG.block(idString + "_wedge_cap", new NonCubicFacingBlock(FabricBlockSettings.copy(template).dynamicBounds(), Direction.DOWN) {
			@Override
			public VoxelShape getOutlineShape(BlockState blockState, BlockView blockView, BlockPos pos, ShapeContext entityContext) {
				return CollisionDispatcher.shapeFor(XmBlockState.modelState(blockState, blockView, pos, false));
			}
		});

		XmBlockRegistry.addBlockStates(block, bs -> PrimitiveStateFunction.builder()
				.withDefaultState(XmProperties.FACE_MODIFIER.mutate(defaultState.mutableCopy(), bs))
				.build());

		return block;
	}

	public static Block slab(String idString, Block template, XmPaint paintBottom, XmPaint paintTop, XmPaint paintSides) {
		final PrimitiveState defaultState = Slab.INSTANCE.newState()
				.paint(Slab.SURFACE_BOTTOM, paintBottom)
				.paint(Slab.SURFACE_TOP, paintTop)
				.paint(Slab.SURFACE_SIDES, paintSides)
				.releaseToImmutable();

		final Block block = Xb.REG.block(idString + "_omni_slab", new NonCubicFacingBlock(FabricBlockSettings.copy(template).dynamicBounds(), Direction.DOWN) {
			@Override
			public VoxelShape getOutlineShape(BlockState blockState, BlockView blockView, BlockPos pos, ShapeContext entityContext) {
				return CollisionDispatcher.shapeFor(XmBlockState.modelState(blockState, blockView, pos, false));
			}
		});

		XmBlockRegistry.addBlockStates(block, bs -> PrimitiveStateFunction.builder()
				.withDefaultState(XmProperties.FACE_MODIFIER.mutate(defaultState.mutableCopy(), bs))
				.build());

		return block;
	}
}
