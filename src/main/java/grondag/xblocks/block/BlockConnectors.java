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

import com.google.common.collect.ImmutableSet;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction.Axis;

import grondag.xm.api.connect.world.BlockTest;
import grondag.xm.api.modelstate.primitive.PrimitiveState;

public enum BlockConnectors {
	;

	private static final Object2ObjectOpenHashMap<Block, ImmutableSet<Block>>  SETS = new Object2ObjectOpenHashMap<>();

	public static void connect(Block... blocks) {
		final ImmutableSet<Block> set = ImmutableSet.copyOf(blocks);
		for (final Block b : blocks) {
			SETS.put(b, set);
		}
	}

	public static boolean canConnect(Block a, Block b) {
		final ImmutableSet<Block> set = SETS.get(a);
		return set != null && set.contains(b);
	}

	public static BlockTest<PrimitiveState> SAME_BLOCK_OR_CONNECTABLE = ctx -> {
		final Block a = ctx.fromBlockState().getBlock();
		final Block b = ctx.toBlockState().getBlock();
		return a == b || canConnect(a, b);
	};


	public static BlockTest<PrimitiveState> AXIS_JOIN_SAME_BLOCK = ctx -> {
		// must be an axis block, obviously.
		final BlockState fromBlock = ctx.fromBlockState();
		if (!fromBlock.contains(PillarBlock.AXIS)) {
			return false;
		}

		// must be same block
		final BlockState toBlock = ctx.toBlockState();
		if (fromBlock.getBlock() != toBlock.getBlock()) {
			return false;
		}

		// must be same axis
		final Axis axis = fromBlock.get(PillarBlock.AXIS);
		if (axis != toBlock.get(PillarBlock.AXIS)) {
			return false;
		}

		// must be adjacent along that axis
		final BlockPos fromPos = ctx.fromPos();
		final BlockPos toPos = ctx.toPos();
		final int dist = axis.choose(fromPos.getX(), fromPos.getY(), fromPos.getZ())
				- axis.choose(toPos.getX(), toPos.getY(), toPos.getZ());
		return Math.abs(dist) == 1;
	};

	public static BlockTest<PrimitiveState> AXIS_JOIN_SAME_OR_CONNECTABLE = ctx -> {
		// must be an axis block, obviously.
		final BlockState fromBlock = ctx.fromBlockState();
		if (!fromBlock.contains(PillarBlock.AXIS)) {
			return false;
		}

		// must be same block or connectable
		final BlockState toBlock = ctx.toBlockState();
		final Block a = fromBlock.getBlock();
		final Block b = toBlock.getBlock();
		if (a != b && !canConnect(a, b)) {
			return false;
		}

		// must be same axis
		final Axis axis = fromBlock.get(PillarBlock.AXIS);
		if (axis != toBlock.get(PillarBlock.AXIS)) {
			return false;
		}

		// must be adjacent along that axis
		final BlockPos fromPos = ctx.fromPos();
		final BlockPos toPos = ctx.toPos();
		final int dist = axis.choose(fromPos.getX(), fromPos.getY(), fromPos.getZ())
				- axis.choose(toPos.getX(), toPos.getY(), toPos.getZ());
		return Math.abs(dist) == 1;
	};
}
