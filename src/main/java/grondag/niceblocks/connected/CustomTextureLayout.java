package grondag.niceblocks.connected;

import static grondag.xm.api.texture.TextureNameFunction.BORDER_CORNERS_ALL;
import static grondag.xm.api.texture.TextureNameFunction.BORDER_CORNERS_BL_TR;
import static grondag.xm.api.texture.TextureNameFunction.BORDER_CORNERS_BL_TR_BR;
import static grondag.xm.api.texture.TextureNameFunction.BORDER_CORNERS_TL_TR;
import static grondag.xm.api.texture.TextureNameFunction.BORDER_CORNER_TR;
import static grondag.xm.api.texture.TextureNameFunction.BORDER_MIXED_TOP_BL_BR;
import static grondag.xm.api.texture.TextureNameFunction.BORDER_MIXED_TOP_BR;
import static grondag.xm.api.texture.TextureNameFunction.BORDER_MIXED_TOP_RIGHT_BL;
import static grondag.xm.api.texture.TextureNameFunction.BORDER_SIDES_ALL;
import static grondag.xm.api.texture.TextureNameFunction.BORDER_SIDES_TOP_BOTTOM;
import static grondag.xm.api.texture.TextureNameFunction.BORDER_SIDES_TOP_LEFT_RIGHT;
import static grondag.xm.api.texture.TextureNameFunction.BORDER_SIDES_TOP_RIGHT;
import static grondag.xm.api.texture.TextureNameFunction.BORDER_SIDE_TOP;

import grondag.xm.api.texture.TextureLayout;
import grondag.xm.api.texture.TextureLayoutMap;

/**
 * Example of a custom texture layout map.  Handles converting the texture
 * ordinals expected by a texture layout to physical file names.
 */
public class CustomTextureLayout {
    
    private final static String[] BORDER_MAP = new String[TextureLayout.BORDER_13.textureCount];
    
    static {
        BORDER_MAP[BORDER_SIDES_ALL] = "all_edges";
        BORDER_MAP[BORDER_SIDE_TOP] = "top_edge";
        BORDER_MAP[BORDER_SIDES_TOP_RIGHT] = "top_right_edges";
        BORDER_MAP[BORDER_SIDES_TOP_BOTTOM] = "top_bottom_edges";
        BORDER_MAP[BORDER_SIDES_TOP_LEFT_RIGHT] = "top_left_right_edges";
        BORDER_MAP[BORDER_MIXED_TOP_BR] = "top_edge_br_corner";
        BORDER_MAP[BORDER_MIXED_TOP_BL_BR] = "top_edge_bottom_corners";
        BORDER_MAP[BORDER_MIXED_TOP_RIGHT_BL] = "top_right_edge_bl_corner";
        BORDER_MAP[BORDER_CORNER_TR] = "one_corner";
        BORDER_MAP[BORDER_CORNERS_TL_TR] = "two_corners";
        BORDER_MAP[BORDER_CORNERS_BL_TR] = "opposite_corners";
        BORDER_MAP[BORDER_CORNERS_BL_TR_BR] = "three_corners";
        BORDER_MAP[BORDER_CORNERS_ALL] = "all_corners";
    }
    
    public static final TextureLayoutMap BORDER_LAYOUT = TextureLayoutMap.create(TextureLayout.BORDER_13, (s, v, i) -> s + "_" + BORDER_MAP[i]);
}
