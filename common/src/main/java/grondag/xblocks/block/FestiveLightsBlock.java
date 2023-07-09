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

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.PipeBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public abstract class FestiveLightsBlock extends Block implements SimpleWaterloggedBlock {
	public static final BooleanProperty UP = PipeBlock.UP;
	public static final BooleanProperty DOWN = PipeBlock.DOWN;
	public static final BooleanProperty NORTH = PipeBlock.NORTH;
	public static final BooleanProperty EAST = PipeBlock.EAST;
	public static final BooleanProperty SOUTH = PipeBlock.SOUTH;
	public static final BooleanProperty WEST = PipeBlock.WEST;

	private static final Direction[] FACES = Direction.values();
	public static final BooleanProperty[] FACING_PROPERTIES;

	static {
		FACING_PROPERTIES = new BooleanProperty[FACES.length];
		FACING_PROPERTIES[Direction.UP.ordinal()] = UP;
		FACING_PROPERTIES[Direction.DOWN.ordinal()] = DOWN;
		FACING_PROPERTIES[Direction.NORTH.ordinal()] = NORTH;
		FACING_PROPERTIES[Direction.EAST.ordinal()] = EAST;
		FACING_PROPERTIES[Direction.SOUTH.ordinal()] = SOUTH;
		FACING_PROPERTIES[Direction.WEST.ordinal()] = WEST;
	}

	protected static final VoxelShape UP_SHAPE = Block.box(0.0D, 15.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	protected static final VoxelShape DOWN_SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 1.0D, 16.0D);
	protected static final VoxelShape WEST_SHAPE = Block.box(0.0D, 0.0D, 0.0D, 1.0D, 16.0D, 16.0D);
	protected static final VoxelShape EAST_SHAPE = Block.box(15.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	protected static final VoxelShape NORTH_SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 1.0D);
	protected static final VoxelShape SOUTH_SHAPE = Block.box(0.0D, 0.0D, 15.0D, 16.0D, 16.0D, 16.0D);

	public static final int WARM_WHITE = 0;
	public static final int BLUE = 1;
	public static final int MAGENTA = 2;
	public static final int CYAN = 3;
	public static final int GREEN = 4;
	public static final int PURPLE = 5;
	public static final int RED = 6;
	public static final int YELLOW = 7;
	public static final int COOL_WHITE = 8;

	public final int[] colors;

	public FestiveLightsBlock(int... colors) {
		super(Block.Properties.of()
			.mapColor(MapColor.NONE)
			.pushReaction(PushReaction.DESTROY)
			.lightLevel(bs -> 4)
			.strength(0.2F, 0.2F)
			.noCollission()
			.sound(SoundType.LANTERN));
		registerDefaultState(defaultState());
		this.colors = colors;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(BlockStateProperties.WATERLOGGED);
	}

	protected BlockState defaultState() {
		return stateDefinition.any().setValue(BlockStateProperties.WATERLOGGED, false);
	}

	public abstract boolean hasFace(BlockState state, Direction face);

	public boolean isPendant() {
		return false;
	}

	@Override
	public VoxelShape getShape(BlockState blockState, BlockGetter blockView, BlockPos blockPos, CollisionContext entityContext) {
		VoxelShape voxelShape = Shapes.empty();

		if (hasFace(blockState, Direction.UP)) {
			voxelShape = Shapes.or(voxelShape, UP_SHAPE);
		}

		if (hasFace(blockState, Direction.NORTH)) {
			voxelShape = Shapes.or(voxelShape, NORTH_SHAPE);
		}

		if (hasFace(blockState, Direction.EAST)) {
			voxelShape = Shapes.or(voxelShape, EAST_SHAPE);
		}

		if (hasFace(blockState, Direction.SOUTH)) {
			voxelShape = Shapes.or(voxelShape, SOUTH_SHAPE);
		}

		if (hasFace(blockState, Direction.WEST)) {
			voxelShape = Shapes.or(voxelShape, WEST_SHAPE);
		}

		if (hasFace(blockState, Direction.DOWN)) {
			voxelShape = Shapes.or(voxelShape, DOWN_SHAPE);
		}

		return voxelShape;
	}

	@Override
	public boolean canSurvive(BlockState blockState, LevelReader worldView, BlockPos blockPos) {
		for (final Direction face : FACES) {
			if (shouldHaveSide(worldView, blockPos, face)) {
				return true;
			}
		}

		return false;
	}

	private boolean hasAdjacentBlocks(BlockState blockState) {
		return getAdjacentBlockCount(blockState) > 0;
	}

	private int getAdjacentBlockCount(BlockState blockState) {
		int count = 0;

		for (final Direction face : FACES) {
			if (hasFace(blockState, face)) {
				++count;
			}
		}

		return count;
	}

	protected boolean shouldHaveSide(BlockGetter blockView, BlockPos blockPos, Direction direction) {
		return direction != null && shouldConnectTo(blockView, blockPos.relative(direction), direction);
	}

	protected boolean shouldConnectTo(BlockGetter blockView, BlockPos blockPos, Direction direction) {
		final BlockState blockState = blockView.getBlockState(blockPos);
		return Block.isFaceFull(blockState.getCollisionShape(blockView, blockPos), direction.getOpposite());
	}

	@Override
	public BlockState updateShape(BlockState blockState, Direction direction, BlockState blockState2, LevelAccessor iWorld, BlockPos blockPos, BlockPos blockPos2) {
		for (int i = 0; i < 6; i++) {
			final Direction face = FACES[i];
			final boolean needsFace = shouldHaveSide(iWorld, blockPos, face);

			if (hasFace(blockState, face) != needsFace) {
				blockState = blockState.setValue(FACING_PROPERTIES[i], needsFace);
			}
		}

		if (blockState.getValue(BlockStateProperties.WATERLOGGED)) {
			iWorld.scheduleTick(blockPos, Fluids.WATER, Fluids.WATER.getTickDelay(iWorld));
		}

		return !hasAdjacentBlocks(blockState) ? Blocks.AIR.defaultBlockState() : blockState;
	}

	@Override
	@Nullable
	public BlockState getStateForPlacement(BlockPlaceContext itemPlacementContext) {
		BlockState result = defaultBlockState();
		boolean valid = false;

		final LevelReader worldView = itemPlacementContext.getLevel();
		final BlockPos blockPos = itemPlacementContext.getClickedPos();
		final FluidState fluidState = worldView.getFluidState(blockPos);

		for (final Direction direction : itemPlacementContext.getNearestLookingDirections()) {
			final BooleanProperty prop = getFacingProperty(direction);

			if (shouldHaveSide(worldView, blockPos, direction)) {
				result = result.setValue(prop, true);
				valid = true;
			}
		}

		return valid ? result.setValue(BlockStateProperties.WATERLOGGED, fluidState.getType() == Fluids.WATER) : null;
	}

	@SuppressWarnings("deprecation")
	@Override
	public FluidState getFluidState(BlockState blockState) {
		return blockState.getValue(BlockStateProperties.WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(blockState);
	}

	@Override
	public BlockState rotate(BlockState blockState, Rotation blockRotation) {
		switch (blockRotation) {
			case CLOCKWISE_180:
				return blockState.setValue(NORTH, blockState.getValue(SOUTH)).setValue(EAST, blockState.getValue(WEST)).setValue(SOUTH, blockState.getValue(NORTH)).setValue(WEST, blockState.getValue(EAST));
			case COUNTERCLOCKWISE_90:
				return blockState.setValue(NORTH, blockState.getValue(EAST)).setValue(EAST, blockState.getValue(SOUTH)).setValue(SOUTH, blockState.getValue(WEST)).setValue(WEST, blockState.getValue(NORTH));
			case CLOCKWISE_90:
				return blockState.setValue(NORTH, blockState.getValue(WEST)).setValue(EAST, blockState.getValue(NORTH)).setValue(SOUTH, blockState.getValue(EAST)).setValue(WEST, blockState.getValue(SOUTH));
			default:
				return blockState;
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public BlockState mirror(BlockState blockState, Mirror blockMirror) {
		switch (blockMirror) {
			case LEFT_RIGHT:
				return blockState.setValue(NORTH, blockState.getValue(SOUTH)).setValue(SOUTH, blockState.getValue(NORTH));
			case FRONT_BACK:
				return blockState.setValue(EAST, blockState.getValue(WEST)).setValue(WEST, blockState.getValue(EAST));
			default:
				return super.mirror(blockState, blockMirror);
		}
	}

	public static BooleanProperty getFacingProperty(Direction direction) {
		return direction == null ? null : FACING_PROPERTIES[direction.ordinal()];
	}

	public static class All extends FestiveLightsBlock {
		public All(int[] colors) {
			super(colors);
		}

		@Override
		protected BlockState defaultState() {
			return super.defaultState().setValue(UP, false).setValue(NORTH, false).setValue(EAST, false).setValue(SOUTH, false).setValue(WEST, false).setValue(DOWN, false);
		}

		@Override
		public boolean hasFace(BlockState state, Direction face) {
			return state.getValue(getFacingProperty(face));
		}

		@Override
		protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
			super.createBlockStateDefinition(builder);
			builder.add(UP, DOWN, NORTH, EAST, SOUTH, WEST);
		}
	}

	public static class Horizontal extends FestiveLightsBlock {
		public Horizontal(int[] colors) {
			super(colors);
		}

		@Override
		protected BlockState defaultState() {
			return super.defaultState().setValue(NORTH, false).setValue(EAST, false).setValue(SOUTH, false).setValue(WEST, false);
		}

		@Override
		public boolean hasFace(BlockState state, Direction face) {
			if (face == Direction.UP || face == Direction.DOWN) {
				return false;
			} else {
				return state.getValue(getFacingProperty(face));
			}
		}

		@Override
		protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
			super.createBlockStateDefinition(builder);
			builder.add(NORTH, EAST, SOUTH, WEST);
		}

		@Override
		protected boolean shouldConnectTo(BlockGetter blockView, BlockPos blockPos, Direction direction) {
			return direction.getAxis() != Axis.Y && super.shouldConnectTo(blockView, blockPos, direction);
		}
	}

	public static class Single extends FestiveLightsBlock {
		public Single(int[] colors) {
			super(colors);
		}

		@Override
		public boolean hasFace(BlockState state, Direction face) {
			return state.getValue(BlockStateProperties.FACING) == face;
		}

		@Override
		protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
			super.createBlockStateDefinition(builder);
			builder.add(BlockStateProperties.FACING);
		}

		@Override
		public BlockState rotate(BlockState blockState, Rotation blockRotation) {
			return blockState.setValue(BlockStateProperties.FACING, blockRotation.rotate(blockState.getValue(BlockStateProperties.FACING)));
		}

		@Override
		public BlockState mirror(BlockState blockState, Mirror blockMirror) {
			return blockState.rotate(blockMirror.getRotation(blockState.getValue(BlockStateProperties.FACING)));
		}

		@Override
		@Nullable
		public BlockState getStateForPlacement(BlockPlaceContext itemPlacementContext) {
			final Direction direction = itemPlacementContext.getNearestLookingDirections()[0];

			if (shouldHaveSide(itemPlacementContext.getLevel(), itemPlacementContext.getClickedPos(), direction)) {
				return defaultBlockState().setValue(BlockStateProperties.FACING, direction);
			} else {
				return null;
			}
		}

		@Override
		public BlockState updateShape(BlockState blockState, Direction direction, BlockState blockState2, LevelAccessor iWorld, BlockPos blockPos, BlockPos blockPos2) {
			if (direction != blockState.getValue(BlockStateProperties.FACING)) {
				return blockState;
			} else {
				return shouldConnectTo(iWorld, blockPos2, direction) ? blockState : Blocks.AIR.defaultBlockState();
			}
		}
	}

	public static class Pendant extends Horizontal {
		public Pendant(int[] colors) {
			super(colors);
		}

		@Override
		public boolean isPendant() {
			return true;
		}
	}
}
