package com.merikmc.util;

import com.merikmc.data.Config;
import com.merikmc.map.checkpoints.Checkpoint;
import com.merikmc.map.checkpoints.CheckpointTracker;
import com.merikmc.map.trophys.TrophyBlock;
import com.merikmc.map.trophys.TrophyTracker;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by codermason on 12/25/14.
 */
public class ConfigUtil {

    public static Location getLocation(ConfigurationSection configurationSection) {
        double x = configurationSection.getDouble("x");
        double y = configurationSection.getDouble("y");
        double z = configurationSection.getDouble("z");
        World world = Bukkit.getWorld(configurationSection.getString("world"));
        float yaw = (float) configurationSection.getDouble("yaw");
        float pitch = (float) configurationSection.getDouble("pitch");
        return new Location(world, x, y, z, yaw, pitch);
    }

    public static TrophyTracker getTrophyTracker(ConfigurationSection configurationSection) {
        List<TrophyBlock> trophyBlocks = new ArrayList<TrophyBlock>();
        for(int i = 1; i <= 5; i++) {
            ConfigurationSection section = configurationSection.getConfigurationSection("trophyblock_" + i);
            Location block = getLocation(section.getConfigurationSection("block"));
            Location sign = getLocation(section.getConfigurationSection("section"));
            Location head = getLocation(section.getConfigurationSection("head"));
            trophyBlocks.add(new TrophyBlock(block, sign, head));
        }
        return new TrophyTracker(trophyBlocks);
    }

    public static CheckpointTracker getCheckpointTracker(ConfigurationSection configurationSection) {
        List<Checkpoint> list = new ArrayList<Checkpoint>();
        for(int i = 1; i <= 5; i++) {
            ConfigurationSection section = configurationSection.getConfigurationSection("checkpoint_" + i);
            Location loc = getLocation(section);
            list.add(new Checkpoint(loc));
        }
        return new CheckpointTracker(list);
    }

}
