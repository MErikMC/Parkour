package com.merikmc;

import com.merikmc.commands.AddLivesCommand;
import com.merikmc.commands.RemoveLivesCommand;
import com.merikmc.data.Config;
import com.merikmc.events.MapCreateEvent;
import com.merikmc.listeners.*;
import com.merikmc.map.Map;
import com.merikmc.map.checkpoints.CheckpointTracker;
import com.merikmc.map.highscores.HighscoreTracker;
import com.merikmc.map.trophys.TrophyTracker;
import com.merikmc.mysql.mysql.MySQL;
import com.merikmc.util.ConfigUtil;
import com.merikmc.util.MySQLUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;

/**
 * Created by codermason on 12/24/14.
 */
public class Parkour extends JavaPlugin {

    public static int COINS_FOR_COMPLETION = 50, DEFAULT_LIVES_PER_DAY = 100;

    private static MySQL mySQL;

    private static Connection c;

    private static Parkour instance;

    private Listener[] listeners = {new CheckpointReachListener(), new FallOnCourseListener(), new FinishCourseListener(),
                                    new MapCreateListener(), new StartCourseListener(), new UtilityListeners()};

    private Config config;

    public void onEnable() {
        instance = this;

        config = new Config(this);

        mySQL = new MySQL(instance, config.getDBConfig().getString("host"), config.getDBConfig().getString("port"),
                config.getDBConfig().getString("database"), config.getDBConfig().getString("user"), config.getDBConfig().getString("pass"));
        reloadConnection();

        loadMaps();

        registerListeners();
        registerCommands();
    }

    public void onDisable() {}

    public static Parkour getInstance() {
        return instance;
    }

    public Config getConfigClass() {
        return config;
    }

    public static Connection getConnection() {
        return c;
    }

    public static void reloadConnection() {
        try {
            c = mySQL.openConnection();
        } catch(Exception e) {
            //.;bhkjnklm
        }
    }

    private void registerListeners() {
        for(Listener l : listeners) {
            this.getServer().getPluginManager().registerEvents(l, this);
        }
    }

    private void registerCommands() {
        getCommand("addlives").setExecutor(new AddLivesCommand());
        getCommand("removelives").setExecutor(new RemoveLivesCommand());
    }

    private void loadMaps() {
        for(String mapName : config.getMapsConfig().getStringList("map")) {
            Bukkit.getPluginManager().callEvent(new MapCreateEvent(mapName));

            ConfigurationSection section = config.getMapsConfig().getConfigurationSection("maps." + mapName);
            Location startPoint = ConfigUtil.getLocation(section.getConfigurationSection("start_point"));
            Location endPoint = ConfigUtil.getLocation(section.getConfigurationSection("end_point"));
            CheckpointTracker ct = ConfigUtil.getCheckpointTracker(section.getConfigurationSection("checkpoints"));
            HighscoreTracker ht = MySQLUtil.getHighscoreTracker(mapName);
            TrophyTracker tt = ConfigUtil.getTrophyTracker(section.getConfigurationSection("trophys"));
            Map.addMap(new Map(mapName, startPoint, endPoint, ct, ht, tt));
        }
    }

}
