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

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager.Builder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

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
		final BlockPos onPos = context.getBlockPos().method_35851(onFace.getOpposite());
		final SpeciesMode mode = XbConfig.modKey.test(context.getPlayer())
				? SpeciesMode.COUNTER_MOST : SpeciesMode.MATCH_MOST;
		final int species = Species.speciesForPlacement(context.getWorld(), onPos, onFace, mode, speciesFunc);
		return getDefaultState().with(SpeciesProperty.SPECIES, species);
	}

	public static Block species(Block template, String id, XmPaint paint) {

		Block block = new SpeciesBlock(Block.Settings.copy(template));

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
