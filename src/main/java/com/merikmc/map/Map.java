package com.merikmc.map;

import com.merikmc.map.checkpoints.CheckpointTracker;
import com.merikmc.map.highscores.Highscore;
import com.merikmc.map.highscores.HighscoreTracker;
import com.merikmc.map.trophys.TrophyTracker;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by codermason on 12/24/14.
 */
public class Map {

    private static List<Map> mapList = new ArrayList<Map>();

    public static void addMap(Map map) {
        mapList.add(map);
    }

    public static Map getMap(String name) {
        for(Map m : mapList) {
            if(m.getName().equalsIgnoreCase(name)) {
                return m;
            }
        }
        return null;
    }

    public static List<Map> getMaps() {
        return mapList;
    }

    private String name;

    private Location startPoint, endPoint;

    private CheckpointTracker checkpointTracker;

    private HighscoreTracker highscoreTracker;

    private TrophyTracker trophyTracker;

    public Map(String name, Location startPoint, Location endPoint, CheckpointTracker checkpointTracker, HighscoreTracker highscoreTracker, TrophyTracker trophyTracker) {
        this.name = name;
        this.startPoint = startPoint;
        this.checkpointTracker = checkpointTracker;
        this.highscoreTracker = highscoreTracker;
        this.trophyTracker = trophyTracker;
        this.trophyTracker.update(this.highscoreTracker);
    }

    public String getName() {
        return name;
    }

    public Location getStartPoint() {
        return startPoint;
    }

    public Location getEndPoint() {
        return endPoint;
    }

    public CheckpointTracker getCheckpointTracker() {
        return checkpointTracker;
    }

    public HighscoreTracker getHighscoreTracker() {
        return highscoreTracker;
    }

    public TrophyTracker getTrophyTracker() {
        return trophyTracker;
    }

}
