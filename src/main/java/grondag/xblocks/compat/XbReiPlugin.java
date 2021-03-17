package grondag.xblocks.compat;

import net.minecraft.util.Identifier;

import grondag.xblocks.Xb;

// FIX: restore when REI available

public class XbReiPlugin { //implements REIPluginV0 {
	public static final Identifier ID = Xb.REG.id("rei_plugin");

	//	@Override
	//	public Identifier getPluginIdentifier() {
	//		return ID;
	//	}
	//
	//	@Override
	//	public void registerOthers(RecipeHelper recipeHelper) {
	//		recipeHelper.registerWorkingStations(DefaultPlugin.STONE_CUTTING, EntryStack.create(new ItemStack(XbItems.PORTABLE_CUTTER)));
	//	}
	//
	//	@Override
	//	public SemanticVersion getMinimumVersion() throws VersionParsingException {
	//		return SemanticVersion.parse("3.0");
	//	}
	//
	//	@Override
	//	public void registerEntries(EntryRegistry entryRegistry) {
	//		// TODO Auto-generated method stub
	//		REIPluginV0.super.registerEntries(entryRegistry);
	//	}
}
