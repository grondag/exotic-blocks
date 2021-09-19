/*******************************************************************************
 * Copyright 2020 grondag
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

import java.util.IdentityHashMap;
import net.minecraft.world.level.block.Block;

public class FenceHelper {
	private static final IdentityHashMap<Block, Boolean> CONNECTABLES = new IdentityHashMap<>();

	public static void add(Block block) {
		CONNECTABLES.put(block, true);
	}

	public static boolean shouldConnect(Block block) {
		return CONNECTABLES.containsKey(block);
	}
}
