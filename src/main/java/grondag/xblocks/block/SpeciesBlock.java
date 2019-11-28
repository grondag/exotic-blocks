package grondag.xblocks.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager.Builder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

import grondag.fermion.modkeys.api.ModKeys;
import grondag.xm.api.connect.species.Species;
import grondag.xm.api.connect.species.SpeciesFunction;
import grondag.xm.api.connect.species.SpeciesMode;
import grondag.xm.api.connect.species.SpeciesProperty;

public class SpeciesBlock extends Block {
	public SpeciesBlock(Settings settings) {
		super(settings);
	}

	@Override
	protected void appendProperties(Builder<Block, BlockState> builder) {
		super.appendProperties(builder);
		builder.add(SpeciesProperty.SPECIES);
	}

	private final SpeciesFunction speciesFunc = SpeciesProperty.speciesForBlock(this);

	@Override
	public BlockState getPlacementState(ItemPlacementContext context) {
		final Direction onFace = context.getSide();
		final BlockPos onPos = context.getBlockPos().offset(onFace.getOpposite());
		final SpeciesMode mode = (context.getPlayer().isSneaking() || ModKeys.isSuperPressed(context.getPlayer()))
				? SpeciesMode.COUNTER_MOST : SpeciesMode.MATCH_MOST;
		final int species = Species.speciesForPlacement(context.getWorld(), onPos, onFace, mode, speciesFunc);
		return getDefaultState().with(SpeciesProperty.SPECIES, species);
	}
}
