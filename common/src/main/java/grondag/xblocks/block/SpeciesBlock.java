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

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;

import grondag.xblocks.Xb;
import grondag.xm.api.block.XmBlockRegistry;
import grondag.xm.api.connect.species.Species;
import grondag.xm.api.connect.species.SpeciesFunction;
import grondag.xm.api.connect.species.SpeciesMode;
import grondag.xm.api.connect.species.SpeciesProperty;
import grondag.xm.api.modelstate.primitive.PrimitiveStateFunction;
import grondag.xm.api.paint.XmPaint;
import grondag.xm.api.primitive.simple.Cube;

public class SpeciesBlock extends Block {
	public SpeciesBlock(Properties settings) {
		super(settings);
	}

	@Override
	protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(SpeciesProperty.SPECIES);
	}

	private final SpeciesFunction speciesFunc = SpeciesProperty.speciesForBlock(this);

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		final Direction onFace = context.getClickedFace();
		final BlockPos onPos = context.getClickedPos().relative(onFace.getOpposite());
		final SpeciesMode mode = Xb.modifyKey.isPressed(context.getPlayer())
				? SpeciesMode.COUNTER_MOST : SpeciesMode.MATCH_MOST;
		final int species = Species.speciesForPlacement(context.getLevel(), onPos, onFace, mode, speciesFunc);
		return defaultBlockState().setValue(SpeciesProperty.SPECIES, species);
	}

	public static Block species(Block template, String id, XmPaint paint) {
		Block block = new SpeciesBlock(Block.Properties.copy(template));

		block = Xb.block(id, block);

		XmBlockRegistry.addBlockStates(block, bs -> PrimitiveStateFunction.builder()
				.withJoin(SpeciesProperty.matchBlockAndSpecies())
				.withUpdate(SpeciesProperty.SPECIES_MODIFIER)
				.withDefaultState(SpeciesProperty.SPECIES_MODIFIER.mutate(
						Cube.INSTANCE.newState()
						.paintAll(paint), bs))
				.build());

		return block;
	}
}
