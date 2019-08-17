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
