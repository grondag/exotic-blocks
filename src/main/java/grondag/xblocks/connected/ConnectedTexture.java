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
package grondag.xblocks.connected;

import static grondag.xm.api.texture.TextureGroup.STATIC_BORDERS;
import static grondag.xm.api.texture.TextureRenderIntent.OVERLAY_ONLY;
import static grondag.xm.api.texture.TextureRotation.ROTATE_NONE;
import static grondag.xm.api.texture.TextureScale.SINGLE;

import grondag.xm.api.texture.TextureSet;

public class ConnectedTexture {
    public static final TextureSet GLASS_BORDER = TextureSet.builder()
            .displayNameToken("fancy_glass")
            .baseTextureName("exotic-blocks:blocks/glass")
            .versionCount(1)
            .scale(SINGLE)
            .layout(CustomTextureLayout.BORDER_LAYOUT)
            .rotation(ROTATE_NONE)
            .renderIntent(OVERLAY_ONLY)
            .groups(STATIC_BORDERS)
            .build("exotic-blocks:fancy_glass");
}
