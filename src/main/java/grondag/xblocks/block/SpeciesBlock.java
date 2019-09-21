package grondag.xblocks.block;

import grondag.fermion.modkeys.api.ModKeys;
import grondag.xm.api.connect.species.Species;
import grondag.xm.api.connect.species.SpeciesFunction;
import grondag.xm.api.connect.species.SpeciesMode;
import grondag.xm.api.connect.species.SpeciesProperty;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateFactory.Builder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

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
         final SpeciesMode mode = (context.isPlayerSneaking() || ModKeys.isSuperPressed(context.getPlayer()))
                 ? SpeciesMode.COUNTER_MOST : SpeciesMode.MATCH_MOST;
         final int species = Species.speciesForPlacement(context.getWorld(), onPos, onFace, mode, speciesFunc);
         return this.getDefaultState().with(SpeciesProperty.SPECIES, species);
     }
}
