package ru.vayflare.config;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import ru.vayflare.UwUfications;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ButtonConfig {
    private static boolean enabled = true;
    private static boolean appendRandomEmoticons = true;
    private static final File CONFIG_FILE = new File("config/uwufications.json");

    public static void loadConfig() {
        try {
            if (CONFIG_FILE.exists()) {
                JsonObject json = JsonParser.parseReader(new FileReader(CONFIG_FILE)).getAsJsonObject();
                enabled = json.get("enabled").getAsBoolean();
                appendRandomEmoticons = json.get("randomEmoticons").getAsBoolean();
            }
        } catch (Exception e) {
            UwUfications.LOGGER.error("Failed to load Config. Lol", e);
        }
    }

    public static void save(boolean on, boolean random) {
        JsonObject json = new JsonObject();
        json.addProperty("enabled", on);
        json.addProperty("randomEmoticons", random);

        File parentDir = CONFIG_FILE.getParentFile();
        if (!parentDir.exists()) {
            parentDir.mkdirs();
        }

        try (FileWriter writer = new FileWriter(CONFIG_FILE)) {
            writer.write(json.toString());
            enabled = on;
            appendRandomEmoticons = random;
        } catch (IOException e) {
            UwUfications.LOGGER.error("Failed to save Config. Fucking chicken");
            throw new RuntimeException(e);
        }
    }

    public static boolean getEnabled() {
        return enabled;
    }

    public static boolean getAppendRandomEmoticons() {
        return appendRandomEmoticons;
    }
}