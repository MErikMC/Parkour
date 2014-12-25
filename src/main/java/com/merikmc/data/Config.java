package com.merikmc.data;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

/**
 * Created by codermason on 12/25/14.
 */
public class Config {

    private Plugin plugin;

    private File dbFile, mapsFile, pluginFolder;

    private FileConfiguration dbConfig, mapConfig;

    public Config(Plugin plugin) {
        this.plugin = plugin;

        this.pluginFolder = plugin.getDataFolder();
        this.dbFile = new File(pluginFolder, "db.yml");
        this.mapsFile = new File(pluginFolder, "maps.yml");

        this.mapConfig = YamlConfiguration.loadConfiguration(mapsFile);
        this.dbConfig = YamlConfiguration.loadConfiguration(dbFile);

        checkFiles(new File[]{dbFile, mapsFile}, new FileConfiguration[]{dbConfig, mapConfig});
    }

    private void checkFiles(File[] files, FileConfiguration[] configs) {
        for(int i = 0;i < files.length; i++) {
            File f = files[i];
            FileConfiguration config = configs[i];
            if (!f.exists()) {
                saveFile(f, config);
            }
        }
    }

    public FileConfiguration getMapsConfig() {
        return mapConfig;
    }

    public FileConfiguration getDBConfig() {
        return dbConfig;
    }

    public File getMapsFile() {
        return mapsFile;
    }

    public File getDBFile() {
        return dbFile;
    }

    public void saveFile(File f, FileConfiguration config) {
        try {
            config.save(f);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

}
