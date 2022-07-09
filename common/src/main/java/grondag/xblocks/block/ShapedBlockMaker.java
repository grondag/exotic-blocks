/*
 * This file is part of Exotic Blocks and is licensed to the project under
 * terms that are compatible with the GNU Lesser General Public License.
 * See the NOTICE file distributed with this work for additional information
 * regarding copyright ownership and licensing.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package grondag.xblocks.block;

import java.util.function.Function;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import grondag.xblocks.Xb;
import grondag.xblocks.data.Shapes;
import grondag.xm.api.block.XmBlockRegistry;
import grondag.xm.api.block.XmBlockState;
import grondag.xm.api.block.XmProperties;
import grondag.xm.api.block.base.CubicCutoutBlock;
import grondag.xm.api.block.base.NonCubicFacingBlock;
import grondag.xm.api.block.base.NonCubicPillarBlock;
import grondag.xm.api.collision.CollisionDispatcher;
import grondag.xm.api.collision.CollisionShapes;
import grondag.xm.api.connect.world.BlockConnectors;
import grondag.xm.api.connect.world.BlockTest;
import grondag.xm.api.connect.world.FenceHelper;
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

public class ShapedBlockMaker {
	public static Block squareInsetColumn(String idString, String inlayString, Block template, XmPaint endPaint, XmPaint sidePaint, XmPaint cutPaint, XmPaint innerPaint, int cutCount, int lightLevel) {
		final PrimitiveState defaultState = SquareColumn.INSTANCE.newState()
			.paint(SquareColumn.SURFACE_END, endPaint)
			.paint(SquareColumn.SURFACE_SIDE, sidePaint)
			.paint(SquareColumn.SURFACE_CUT, cutPaint)
			.paint(SquareColumn.SURFACE_INLAY, innerPaint)
			.apply(s -> {
				SquareColumn.setCutCount(cutCount, s);
				SquareColumn.setCutsOnEdge(true, s);
			})
			.releaseToImmutable();

		final Block column = Xb.block(idString + Shapes.SQUARE_COLUMN + inlayString, new NonCubicPillarBlock(Block.Properties.copy(template).lightLevel(b -> lightLevel)) {
			@Override
			public VoxelShape getShape(BlockState blockState, BlockGetter blockView, BlockPos pos, CollisionContext entityContext) {
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
				&& fromBlock.hasProperty(RotatedPillarBlock.AXIS)
				&& fromBlock.getValue(RotatedPillarBlock.AXIS) == toBlock.getValue(RotatedPillarBlock.AXIS);
		};

		final Function<BlockState, PrimitiveStateFunction> stateFunc = bs -> PrimitiveStateFunction.builder()
			.withDefaultState(PrimitiveState.AXIS_FROM_BLOCKSTATE.mutate(defaultState.mutableCopy(), bs))
			.withJoin(joinFunc)
			.build();

		XmBlockRegistry.addBlockStates(column, stateFunc);

		return column;
	}

	public static Block roundColumn(String idString, String inlayString, Block template, XmPaint endPaint, XmPaint sidePaint, XmPaint cutPaint, XmPaint innerPaint) {
		final PrimitiveState defaultState = CylinderWithAxis.INSTANCE.newState()
			.paint(CylinderWithAxis.SURFACE_ENDS, endPaint)
			.paint(CylinderWithAxis.SURFACE_SIDES, sidePaint)
			.releaseToImmutable();

		final Block block = Xb.block(idString + Shapes.ROUND_COLUMN + inlayString, new NonCubicPillarBlock(Block.Properties.copy(template).dynamicShape()) {
			@Override
			public VoxelShape getShape(BlockState blockState, BlockGetter blockView, BlockPos pos, CollisionContext entityContext) {
				return CollisionDispatcher.shapeFor(XmBlockState.modelState(blockState, blockView, pos, false));
			}
		});

		XmBlockRegistry.addBlockStates(block, bs -> PrimitiveStateFunction.builder()
			.withDefaultState(PrimitiveState.AXIS_FROM_BLOCKSTATE.mutate(defaultState.mutableCopy(), bs))
			.build());

		return block;
	}

	public static Block cappedRoundColumn(String idString, String inlayString, Block template, XmPaint endPaint, XmPaint sidePaint, XmPaint cutPaint, XmPaint innerPaint) {
		final PrimitiveState defaultState = CappedRoundColumn.INSTANCE.newState()
			.paint(CappedRoundColumn.SURFACE_ENDS, endPaint)
			.paint(CappedRoundColumn.SURFACE_SIDES, sidePaint)
			.releaseToImmutable();

		final Block block = Xb.block(idString + Shapes.CAPPED_ROUND_COLUMN + inlayString, new NonCubicPillarBlock(Block.Properties.copy(template).dynamicShape()) {
			@Override
			public VoxelShape getShape(BlockState blockState, BlockGetter blockView, BlockPos pos, CollisionContext entityContext) {
				return CollisionDispatcher.shapeFor(XmBlockState.modelState(blockState, blockView, pos, true));
			}
		});

		XmBlockRegistry.addBlockStates(block, bs -> PrimitiveStateFunction.builder()
			.withJoin(BlockConnectors.AXIS_JOIN_SAME_OR_CONNECTABLE)
			.withDefaultState(PrimitiveState.AXIS_FROM_BLOCKSTATE.mutate(defaultState.mutableCopy(), bs))
			.build());

		return block;
	}

	public static Block roundCappedRoundColumn(String idString, String inlayString, Block template, XmPaint endPaint, XmPaint sidePaint, XmPaint cutPaint, XmPaint innerPaint) {
		final PrimitiveState defaultState = RoundCappedRoundColumn.INSTANCE.newState()
			.paint(CutRoundColumn.SURFACE_ENDS, endPaint)
			.paint(CutRoundColumn.SURFACE_CUT, cutPaint)
			.paint(CutRoundColumn.SURFACE_INNER, innerPaint)
			.paint(CutRoundColumn.SURFACE_OUTER, sidePaint)
			.releaseToImmutable();

		final Block block = Xb.block(idString + Shapes.ROUND_CAPPED_ROUND_COLUMN + inlayString, new NonCubicPillarBlock(Block.Properties.copy(template).dynamicShape()) {
			@Override
			public VoxelShape getShape(BlockState blockState, BlockGetter blockView, BlockPos pos, CollisionContext entityContext) {
				return CollisionDispatcher.shapeFor(XmBlockState.modelState(blockState, blockView, pos, true));
			}
		});

		XmBlockRegistry.addBlockStates(block, bs -> PrimitiveStateFunction.builder()
			.withJoin(BlockConnectors.AXIS_JOIN_SAME_OR_CONNECTABLE)
			.withDefaultState(PrimitiveState.AXIS_FROM_BLOCKSTATE.mutate(defaultState.mutableCopy(), bs))
			.build());

		return block;
	}

	public static Block cutRoundColumn(String idString, String inlayString, Block template, XmPaint endPaint, XmPaint sidePaint, XmPaint cutPaint, XmPaint innerPaint, int lightLevel) {
		final PrimitiveState defaultState = CutRoundColumn.INSTANCE.newState()
			.paint(CutRoundColumn.SURFACE_ENDS, endPaint)
			.paint(CutRoundColumn.SURFACE_CUT, cutPaint)
			.paint(CutRoundColumn.SURFACE_INNER, innerPaint)
			.paint(CutRoundColumn.SURFACE_OUTER, sidePaint)
			.releaseToImmutable();

		final Block block = Xb.block(idString + Shapes.CUT_ROUND_COLUMN + inlayString, new NonCubicPillarBlock(Block.Properties.copy(template).dynamicShape().lightLevel(b -> lightLevel)) {
			@Override
			public VoxelShape getShape(BlockState blockState, BlockGetter blockView, BlockPos pos, CollisionContext entityContext) {
				return CollisionDispatcher.shapeFor(XmBlockState.modelState(blockState, blockView, pos, true));
			}
		});

		XmBlockRegistry.addBlockStates(block, bs -> PrimitiveStateFunction.builder()
			.withJoin(BlockConnectors.AXIS_JOIN_SAME_OR_CONNECTABLE)
			.withDefaultState(PrimitiveState.AXIS_FROM_BLOCKSTATE.mutate(defaultState.mutableCopy(), bs))
			.build());

		return block;
	}

	public static Block cappedSquareColumn(String idString, String inlayString, Block template, XmPaint endPaint, XmPaint sidePaint, XmPaint cutPaint, XmPaint innerPaint, int light) {
		final PrimitiveState defaultState = CappedSquareInsetColumn.INSTANCE.newState()
			.paint(CappedSquareInsetColumn.SURFACE_ENDS, endPaint)
			.paint(CappedSquareInsetColumn.SURFACE_CUT, cutPaint)
			.paint(CappedSquareInsetColumn.SURFACE_INNER, innerPaint)
			.paint(CappedSquareInsetColumn.SURFACE_OUTER, sidePaint)
			.releaseToImmutable();

		final Block block = Xb.block(idString + Shapes.CAPPED_SQUARE_COLUMN + inlayString, new NonCubicPillarBlock(Block.Properties.copy(template).dynamicShape().lightLevel(b -> light)) {
			@Override
			public VoxelShape getShape(BlockState blockState, BlockGetter blockView, BlockPos pos, CollisionContext entityContext) {
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

	public static Block insetPanel(String idString, String inlayString, Block template, XmPaint paintOuter, XmPaint paintCut, XmPaint paintInner, int lightLevel) {
		final PrimitiveState defaultState = InsetPanel.INSTANCE.newState()
			.paint(InsetPanel.SURFACE_OUTER, paintOuter)
			.paint(InsetPanel.SURFACE_CUT, paintCut)
			.paint(InsetPanel.SURFACE_INNER, paintInner)
			.releaseToImmutable();

		final Block block = Xb.block(idString + Shapes.INSET_PANEL + inlayString, new CubicCutoutBlock(Block.Properties.copy(template).lightLevel(b -> lightLevel)) {
			@Override
			public VoxelShape getShape(BlockState blockState, BlockGetter blockView, BlockPos pos, CollisionContext entityContext) {
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

	public static Block flatPanel(String idString, String inlayString, Block template, XmPaint paintOuter, XmPaint paintInner, int lightLevel) {
		final PrimitiveState defaultState = FlatPanel.INSTANCE.newState()
			.paint(FlatPanel.SURFACE_OUTER, paintOuter)
			.paint(FlatPanel.SURFACE_INNER, paintInner)
			.releaseToImmutable();

		final Block block = Xb.block(idString + Shapes.FLAT_PANEL + inlayString, new Block(Block.Properties.copy(template).lightLevel(b -> lightLevel)));

		FenceHelper.add(block);

		XmBlockRegistry.addBlockStates(block, bs -> PrimitiveStateFunction.builder()
			.withJoin(BlockConnectors.SAME_BLOCK_OR_CONNECTABLE)
			.withDefaultState(defaultState.mutableCopy())
			.build());

		return block;
	}

	public static Block wedgeCap(String idString, String inlayString, Block template, XmPaint paintBottom, XmPaint paintTop) {
		final PrimitiveState defaultState = WedgeCap.INSTANCE.newState()
			.paint(WedgeCap.SURFACE_BOTTOM, paintBottom)
			.paint(WedgeCap.SURFACE_TOP, paintTop)
			.releaseToImmutable();

		final Block block = Xb.block(idString + Shapes.WEDGE_CAP + inlayString, new NonCubicFacingBlock(Block.Properties.copy(template).dynamicShape(), Direction.DOWN) {
			@Override
			public VoxelShape getShape(BlockState blockState, BlockGetter blockView, BlockPos pos, CollisionContext entityContext) {
				return CollisionDispatcher.shapeFor(XmBlockState.modelState(blockState, blockView, pos, false));
			}
		});

		XmBlockRegistry.addBlockStates(block, bs -> PrimitiveStateFunction.builder()
			.withDefaultState(XmProperties.FACE_MODIFIER.mutate(defaultState.mutableCopy(), bs))
			.build());

		return block;
	}

	public static Block slab(String idString, String inlayString, Block template, XmPaint paintBottom, XmPaint paintTop, XmPaint paintSides) {
		final PrimitiveState defaultState = Slab.INSTANCE.newState()
			.paint(Slab.SURFACE_BOTTOM, paintBottom)
			.paint(Slab.SURFACE_TOP, paintTop)
			.paint(Slab.SURFACE_SIDES, paintSides)
			.releaseToImmutable();

		final Block block = Xb.block(idString + Shapes.SLAB + inlayString, new NonCubicFacingBlock(Block.Properties.copy(template).dynamicShape(), Direction.DOWN) {
			@Override
			public VoxelShape getShape(BlockState blockState, BlockGetter blockView, BlockPos pos, CollisionContext entityContext) {
				return CollisionDispatcher.shapeFor(XmBlockState.modelState(blockState, blockView, pos, false));
			}
		});

		XmBlockRegistry.addBlockStates(block, bs -> PrimitiveStateFunction.builder()
			.withDefaultState(XmProperties.FACE_MODIFIER.mutate(defaultState.mutableCopy(), bs))
			.build());

		return block;
	}
}
