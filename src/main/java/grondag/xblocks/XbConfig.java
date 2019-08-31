package grondag.xblocks;

import java.io.File;
import java.io.FileOutputStream;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import blue.endless.jankson.Comment;
import blue.endless.jankson.Jankson;
import blue.endless.jankson.JsonObject;
import net.fabricmc.loader.api.FabricLoader;

public class XbConfig {
    @SuppressWarnings("hiding")
    public static class ConfigData {

        // VANILLA
        @Comment("Fancy glass and stained glass blocks.")
        public boolean fancyGlass = true;

    }

    public static final ConfigData DEFAULTS = new ConfigData();
    private static final Gson GSON = new GsonBuilder().create();
    private static final Jankson JANKSON = Jankson.builder().build();

    // VANILLA
    public static boolean fancyGlass = DEFAULTS.fancyGlass;

    private static File configFile;

    public static void init() {
        configFile = new File(FabricLoader.getInstance().getConfigDirectory(), "exotic-blocks.json5");
        if (configFile.exists()) {
            loadConfig();
        } else {
            saveConfig();
        }
    }

    private static void loadConfig() {
        ConfigData config = new ConfigData();
        try {
            JsonObject configJson = JANKSON.load(configFile);
            String regularized = configJson.toJson(false, false, 0);
            config = GSON.fromJson(regularized, ConfigData.class);
        } catch (Exception e) {
            e.printStackTrace();
            Xb.LOG.error("Unable to load config. Using default values.");
        }

        // VANILLA
        fancyGlass = config.fancyGlass;
    }

    public static void saveConfig() {
        ConfigData config = new ConfigData();

        // VANILLA
        config.fancyGlass = fancyGlass;

        try {
            String result = JANKSON.toJson(config).toJson(true, true, 0);
            if (!configFile.exists())
                configFile.createNewFile();

            try (FileOutputStream out = new FileOutputStream(configFile, false);) {
                out.write(result.getBytes());
                out.flush();
                out.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Xb.LOG.error("Unable to save config.");
            return;
        }
    }
}
