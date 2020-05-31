package grondag.xblocks.init;

import static grondag.xblocks.block.FestiveLightsBlock.BLUE;
import static grondag.xblocks.block.FestiveLightsBlock.COOL_WHITE;
import static grondag.xblocks.block.FestiveLightsBlock.CYAN;
import static grondag.xblocks.block.FestiveLightsBlock.GREEN;
import static grondag.xblocks.block.FestiveLightsBlock.MAGENTA;
import static grondag.xblocks.block.FestiveLightsBlock.PURPLE;
import static grondag.xblocks.block.FestiveLightsBlock.RED;
import static grondag.xblocks.block.FestiveLightsBlock.WARM_WHITE;
import static grondag.xblocks.block.FestiveLightsBlock.YELLOW;

import java.util.ArrayList;
import java.util.function.Function;

import grondag.xblocks.Xb;
import grondag.xblocks.block.FestiveLightsBlock;
import grondag.xblocks.data.LightColors;
import grondag.xblocks.data.LightShapes;

public enum FestiveLights {
	;

	public static final ArrayList<String> FESTIVE_LIGHTS = new ArrayList<>();

	static {
		festiveLights(LightColors.WARM_WHITE, FestiveLightsBlock.All::new, WARM_WHITE);
		festiveLights(LightColors.COOL_WHITE, FestiveLightsBlock.All::new, COOL_WHITE);
		festiveLights(LightColors.BLUE, FestiveLightsBlock.All::new, BLUE);
		festiveLights(LightColors.CYAN, FestiveLightsBlock.All::new, CYAN);
		festiveLights(LightColors.MAGENTA, FestiveLightsBlock.All::new, MAGENTA);
		festiveLights(LightColors.PURPLE, FestiveLightsBlock.All::new, PURPLE);
		festiveLights(LightColors.RED, FestiveLightsBlock.All::new, RED);
		festiveLights(LightColors.YELLOW, FestiveLightsBlock.All::new, YELLOW);
		festiveLights(LightColors.GREEN, FestiveLightsBlock.All::new, GREEN);
		festiveLights(LightColors.MIXED_COLORS, FestiveLightsBlock.All::new, BLUE, CYAN, MAGENTA, PURPLE, RED, YELLOW, GREEN);
		festiveLights(LightColors.HOT_MIX, FestiveLightsBlock.All::new, RED, YELLOW, MAGENTA);
		festiveLights(LightColors.COOL_MIX, FestiveLightsBlock.All::new, BLUE, CYAN, MAGENTA, PURPLE, GREEN);
		festiveLights(LightColors.BLUISH_MIX, FestiveLightsBlock.All::new, BLUE, CYAN, GREEN);
		festiveLights(LightColors.ERIE_MIX, FestiveLightsBlock.All::new, GREEN, YELLOW, PURPLE);
		festiveLights(LightColors.GREEN_RED, FestiveLightsBlock.All::new, GREEN, RED);
		festiveLights(LightColors.PRIMARY_COLORS, FestiveLightsBlock.All::new, GREEN, RED, BLUE);
		festiveLights(LightColors.BLUE_WHITE, FestiveLightsBlock.All::new, BLUE, COOL_WHITE);
		festiveLights(LightColors.RED_WHITE, FestiveLightsBlock.All::new, RED, WARM_WHITE);
		festiveLights(LightColors.RED_WHITE_BLUE, FestiveLightsBlock.All::new, BLUE, RED, WARM_WHITE);
	}

	static void festiveLights(String colorName, Function<int[], FestiveLightsBlock> func, int... colors) {
		final String name = "f" + colorName;
		Xb.REG.block(addLightName(name  + LightShapes.ALL_FACES), new  FestiveLightsBlock.All(colors));
		Xb.REG.block(addLightName(name  + LightShapes.HORIZONTAL_FACES), new  FestiveLightsBlock.Horizontal(colors));
		Xb.REG.block(addLightName(name  + LightShapes.SINGLE_FACE), new  FestiveLightsBlock.Single(colors));
		Xb.REG.block(addLightName(name  + LightShapes.PENDANT), new  FestiveLightsBlock.Pendant(colors));
	}

	static String addLightName(String name) {
		FESTIVE_LIGHTS.add(name);
		return name;
	}
}
