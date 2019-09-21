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
package grondag.xblocks;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import grondag.xblocks.block.ConnectedGlass;
import grondag.xblocks.block.StoneBlocks;
import grondag.xblocks.block.StoneSpecies;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Xb implements ModInitializer {
    public static final String MODID = "exotic-blocks";
    
    public static Logger LOG = LogManager.getLogger("Exotic Blocks");

    private static ItemGroup itemGroup;
    
    private static final ArrayList<Item> items = new ArrayList<>();
    
    static ItemGroup itemGroup() {
        return itemGroup;
    }
    
    private static final Identifier stackId = id("clear_connected_glass");

    private static void stackAppender(List<ItemStack> list) {
        for(Item item : items) {
            list.add(new ItemStack(item));
        }
    }
    
    @Override
    public void onInitialize() {
        itemGroup = FabricItemGroupBuilder.create(id("item_group"))
                .icon(() -> new ItemStack(Registry.ITEM.get(stackId)))
                .appendItems(Xb::stackAppender)
                .build();
        ConnectedGlass.init();
        StoneBlocks.init();
        StoneSpecies.init();
    }

    public static String idString(String path) {
        return MODID + ":" + path;
    }
    
    public static Identifier id(String path) {
        return new Identifier(MODID, path);
    }
    
    public static Block register(Block block, String idString) {
        Identifier id = id(idString);
        block = Registry.BLOCK.add(id, block);
        items.add(Registry.ITEM.add(id, new BlockItem(block, new Item.Settings().maxCount(64).group(itemGroup))));
        return block;
    }
}
