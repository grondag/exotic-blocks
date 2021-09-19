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

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;

import grondag.xblocks.Xb;
import grondag.xblocks.XbConfig;
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
		final SpeciesMode mode = XbConfig.modKey.test(context.getPlayer())
				? SpeciesMode.COUNTER_MOST : SpeciesMode.MATCH_MOST;
		final int species = Species.speciesForPlacement(context.getLevel(), onPos, onFace, mode, speciesFunc);
		return defaultBlockState().setValue(SpeciesProperty.SPECIES, species);
	}

	public static Block species(Block template, String id, XmPaint paint) {

		Block block = new SpeciesBlock(Block.Properties.copy(template));

		block = Xb.REG.block(id, block);

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
