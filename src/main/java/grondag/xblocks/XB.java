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

import grondag.xblocks.basics.Granite;
import grondag.xblocks.connected.ConnectedGlass;
import grondag.xblocks.customblock.Shapes;
import grondag.xblocks.test.CsgTest;
import grondag.xblocks.test.RotationTest;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

public class XB implements ModInitializer {
    public static final String MODID = "exotic-blocks";
    @Override
    public void onInitialize() {
        Granite.init();
        ConnectedGlass.init();
        Shapes.init();
        CsgTest.init();
        RotationTest.init();
    }
    
    public static String idString(String path) {
        return MODID + ":" + path;
    }
    
    public static Identifier id(String path) {
        return new Identifier(MODID, path);
    }
}
