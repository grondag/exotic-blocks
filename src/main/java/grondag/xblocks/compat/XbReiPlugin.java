package grondag.xblocks.compat;

import me.shedaniel.rei.api.EntryRegistry;
import me.shedaniel.rei.api.EntryStack;
import me.shedaniel.rei.api.RecipeHelper;
import me.shedaniel.rei.api.plugins.REIPluginV0;
import me.shedaniel.rei.plugin.DefaultPlugin;

import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import net.fabricmc.loader.api.SemanticVersion;
import net.fabricmc.loader.util.version.VersionParsingException;

import grondag.xblocks.Xb;
import grondag.xblocks.init.XbItems;

public class XbReiPlugin implements REIPluginV0 {
	public static final Identifier ID = Xb.REG.id("rei_plugin");

	@Override
	public Identifier getPluginIdentifier() {
		return ID;
	}

	@Override
	public void registerOthers(RecipeHelper recipeHelper) {
		recipeHelper.registerWorkingStations(DefaultPlugin.STONE_CUTTING, EntryStack.create(new ItemStack(XbItems.PORTABLE_CUTTER)));
	}

	@Override
	public SemanticVersion getMinimumVersion() throws VersionParsingException {
		return SemanticVersion.parse("3.0");
	}

	@Override
	public void registerEntries(EntryRegistry entryRegistry) {
		// TODO Auto-generated method stub
		REIPluginV0.super.registerEntries(entryRegistry);
	}


}
