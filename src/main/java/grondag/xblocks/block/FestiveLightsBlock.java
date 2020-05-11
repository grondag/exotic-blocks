package grondag.xblocks.block;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.Waterloggable;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Direction.Axis;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.IWorld;
import net.minecraft.world.WorldView;

import net.fabricmc.fabric.api.block.FabricBlockSettings;

public abstract class FestiveLightsBlock extends Block implements Waterloggable {
	public static final BooleanProperty UP = Properties.UP;
	public static final BooleanProperty DOWN = Properties.DOWN;
	public static final BooleanProperty NORTH = Properties.NORTH;
	public static final BooleanProperty EAST = Properties.EAST;
	public static final BooleanProperty SOUTH = Properties.SOUTH;
	public static final BooleanProperty WEST = Properties.WEST;

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

	protected static final VoxelShape UP_SHAPE = Block.createCuboidShape(0.0D, 15.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	protected static final VoxelShape DOWN_SHAPE = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 1.0D, 16.0D);
	protected static final VoxelShape WEST_SHAPE = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 1.0D, 16.0D, 16.0D);
	protected static final VoxelShape EAST_SHAPE = Block.createCuboidShape(15.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	protected static final VoxelShape NORTH_SHAPE = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 1.0D);
	protected static final VoxelShape SOUTH_SHAPE = Block.createCuboidShape(0.0D, 0.0D, 15.0D, 16.0D, 16.0D, 16.0D);

	public static final int WARM_WHITE = 0;
	public static final int BLUE = 1;
	public static final int MAGENTA = 2;
	public static final int CYAN = 3;
	public static final int GREEN = 4;
	public static final int PURPLE  = 5;
	public static final int RED = 6;
	public static final int YELLOW = 7;
	public static final int COOL_WHITE = 8;

	public final int colors[];

	public FestiveLightsBlock(int... colors) {
		super(FabricBlockSettings.of(Material.PART)
				.breakByHand(true)
				.strength(0.2F, 0.2F)
				.noCollision()
				.lightLevel(4)
				.sounds(BlockSoundGroup.LANTERN).build());
		setDefaultState(defaultState());
		this.colors = colors;
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(Properties.WATERLOGGED);
	}

	protected BlockState defaultState() {
		return stateManager.getDefaultState().with(Properties.WATERLOGGED, false);
	}

	public abstract boolean hasFace(BlockState state, Direction face);

	public boolean isPendant() {
		return false;
	}

	@Override
	public VoxelShape getOutlineShape(BlockState blockState, BlockView blockView, BlockPos blockPos, ShapeContext entityContext) {
		VoxelShape voxelShape = VoxelShapes.empty();

		if (hasFace(blockState, Direction.UP)) {
			voxelShape = VoxelShapes.union(voxelShape, UP_SHAPE);
		}

		if (hasFace(blockState, Direction.NORTH)) {
			voxelShape = VoxelShapes.union(voxelShape, NORTH_SHAPE);
		}

		if (hasFace(blockState, Direction.EAST)) {
			voxelShape = VoxelShapes.union(voxelShape, EAST_SHAPE);
		}

		if (hasFace(blockState, Direction.SOUTH)) {
			voxelShape = VoxelShapes.union(voxelShape, SOUTH_SHAPE);
		}

		if (hasFace(blockState, Direction.WEST)) {
			voxelShape = VoxelShapes.union(voxelShape, WEST_SHAPE);
		}

		if (hasFace(blockState, Direction.DOWN)) {
			voxelShape = VoxelShapes.union(voxelShape, DOWN_SHAPE);
		}

		return voxelShape;
	}

	@Override
	public boolean canPlaceAt(BlockState blockState, WorldView worldView, BlockPos blockPos) {
		for(final Direction face : FACES) {
			if (shouldHaveSide(worldView, blockPos,  face)) {
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

		for(final Direction face : FACES) {
			if (hasFace(blockState, face)) {
				++count;
			}
		}

		return count;
	}

	protected boolean shouldHaveSide(BlockView blockView, BlockPos blockPos, Direction direction) {
		return direction != null && shouldConnectTo(blockView, blockPos.offset(direction), direction);
	}

	protected boolean shouldConnectTo(BlockView blockView, BlockPos blockPos, Direction direction) {
		final BlockState blockState = blockView.getBlockState(blockPos);
		return Block.isFaceFullSquare(blockState.getCollisionShape(blockView, blockPos), direction.getOpposite());
	}

	@Override
	public BlockState getStateForNeighborUpdate(BlockState blockState, Direction direction, BlockState blockState2, IWorld iWorld, BlockPos blockPos, BlockPos blockPos2) {
		for(int i = 0; i < 6; i++) {
			final Direction face = FACES[i];
			final boolean needsFace = shouldHaveSide(iWorld, blockPos, face);

			if (hasFace(blockState, face) != needsFace)  {
				blockState = blockState.with(FACING_PROPERTIES[i], needsFace);
			}
		}

		if (blockState.get(Properties.WATERLOGGED)) {
			iWorld.getFluidTickScheduler().schedule(blockPos, Fluids.WATER, Fluids.WATER.getTickRate(iWorld));
		}

		return !hasAdjacentBlocks(blockState) ? Blocks.AIR.getDefaultState() : blockState;
	}

	@Override
	@Nullable
	public BlockState getPlacementState(ItemPlacementContext itemPlacementContext) {
		BlockState result = getDefaultState();
		boolean valid = false;

		final WorldView worldView = itemPlacementContext.getWorld();
		final BlockPos blockPos = itemPlacementContext.getBlockPos();
		final FluidState fluidState = worldView.getFluidState(blockPos);

		for(final Direction direction : itemPlacementContext.getPlacementDirections()) {
			final BooleanProperty prop = getFacingProperty(direction);

			if (shouldHaveSide(worldView, blockPos, direction)) {
				result = result.with(prop, true);
				valid = true;
			}
		}

		return valid ? result.with(Properties.WATERLOGGED, fluidState.getFluid() == Fluids.WATER) : null;
	}

	@Override
	public FluidState getFluidState(BlockState blockState) {
		return blockState.get(Properties.WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(blockState);
	}

	@Override
	public BlockState rotate(BlockState blockState, BlockRotation blockRotation) {
		switch(blockRotation) {
		case CLOCKWISE_180:
			return blockState.with(NORTH, blockState.get(SOUTH)).with(EAST, blockState.get(WEST)).with(SOUTH, blockState.get(NORTH)).with(WEST, blockState.get(EAST));
		case COUNTERCLOCKWISE_90:
			return blockState.with(NORTH, blockState.get(EAST)).with(EAST, blockState.get(SOUTH)).with(SOUTH, blockState.get(WEST)).with(WEST, blockState.get(NORTH));
		case CLOCKWISE_90:
			return blockState.with(NORTH, blockState.get(WEST)).with(EAST, blockState.get(NORTH)).with(SOUTH, blockState.get(EAST)).with(WEST, blockState.get(SOUTH));
		default:
			return blockState;
		}
	}

	@Override
	public BlockState mirror(BlockState blockState, BlockMirror blockMirror) {
		switch(blockMirror) {
		case LEFT_RIGHT:
			return blockState.with(NORTH, blockState.get(SOUTH)).with(SOUTH, blockState.get(NORTH));
		case FRONT_BACK:
			return blockState.with(EAST, blockState.get(WEST)).with(WEST, blockState.get(EAST));
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
			return super.defaultState().with(UP, false).with(NORTH, false).with(EAST, false).with(SOUTH, false).with(WEST, false).with(DOWN, false);
		}

		@Override
		public boolean hasFace(BlockState state, Direction face) {
			return state.get(getFacingProperty(face));
		}

		@Override
		protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
			super.appendProperties(builder);
			builder.add(UP, DOWN, NORTH, EAST, SOUTH, WEST);
		}
	}

	public static class Horizontal extends FestiveLightsBlock {
		public Horizontal(int[] colors) {
			super(colors);
		}

		@Override
		protected BlockState defaultState() {
			return super.defaultState().with(NORTH, false).with(EAST, false).with(SOUTH, false).with(WEST, false);
		}

		@Override
		public boolean hasFace(BlockState state, Direction face) {
			if (face == Direction.UP || face == Direction.DOWN) {
				return false;
			} else {
				return state.get(getFacingProperty(face));
			}
		}

		@Override
		protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
			super.appendProperties(builder);
			builder.add(NORTH, EAST, SOUTH, WEST);
		}

		@Override
		protected boolean shouldConnectTo(BlockView blockView, BlockPos blockPos, Direction direction) {
			return direction.getAxis() != Axis.Y &&  super.shouldConnectTo(blockView, blockPos, direction);
		}
	}

	public static class Single extends FestiveLightsBlock {
		public Single(int[] colors) {
			super(colors);
		}

		@Override
		public boolean hasFace(BlockState state, Direction face) {
			return state.get(Properties.FACING) == face;
		}

		@Override
		protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
			super.appendProperties(builder);
			builder.add(Properties.FACING);
		}

		@Override
		public BlockState rotate(BlockState blockState, BlockRotation blockRotation) {
			return blockState.with(Properties.FACING, blockRotation.rotate(blockState.get(Properties.FACING)));
		}

		@Override
		public BlockState mirror(BlockState blockState, BlockMirror blockMirror) {
			return blockState.rotate(blockMirror.getRotation(blockState.get(Properties.FACING)));
		}

		@Override
		@Nullable
		public BlockState getPlacementState(ItemPlacementContext itemPlacementContext) {
			final Direction direction = itemPlacementContext.getPlacementDirections()[0];

			if (shouldHaveSide(itemPlacementContext.getWorld(), itemPlacementContext.getBlockPos(), direction)) {
				return  getDefaultState().with(Properties.FACING, direction);
			}  else {
				return null;
			}
		}

		@Override
		public BlockState getStateForNeighborUpdate(BlockState blockState, Direction direction, BlockState blockState2, IWorld iWorld, BlockPos blockPos, BlockPos blockPos2) {
			if (direction != blockState.get(Properties.FACING)) {
				return blockState;
			}else {
				return shouldConnectTo(iWorld, blockPos2, direction) ? blockState : Blocks.AIR.getDefaultState();
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