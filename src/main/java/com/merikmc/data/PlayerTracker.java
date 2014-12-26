package com.merikmc.data;

import com.merikmc.Parkour;
import com.merikmc.map.Map;
import com.merikmc.map.checkpoints.Checkpoint;
import com.merikmc.util.NumberUtil;
import com.sun.tools.javac.comp.Check;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachmentInfo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by codermason on 12/25/14.
 */
public class PlayerTracker {

    private static List<PlayerTracker> playerList = new ArrayList<PlayerTracker>();

    public static boolean hasLocalData(String playerName) {
        return getPlayerTracker(playerName) != null;
    }

    public static PlayerTracker getPlayerTracker(String playerName) {
        for(PlayerTracker pt : playerList) {
            if(pt.getName().equals(playerName)) {
                return pt;
            }
        }
        return null;
    }

    public static void addPlayerTracker(String playerName) {
        if(!hasLocalData(playerName)) {
            playerList.add(new PlayerTracker(playerName));
        }
    }

    private String name;

    private int lives;

    private PlayerMapTracker mapTracker;

    public PlayerTracker(String playerName) {
        this.name = playerName;
        this.lives = NumberUtil.getLives(playerName);
        this.mapTracker = null;
    }

    public String getName() {
        return name;
    }

    public int getLives() {
        return lives;
    }

    public void addLives(int lives) {
        this.lives += lives;
    }

    public void removeLives(int lives) {
        this.lives -= lives;
    }

    public boolean hasLivesRemaining() {
        return getLives() > 0;
    }

    public boolean isParkouring() {
        return this.mapTracker != null;
    }

    public void startCourse(Map map) {
        this.mapTracker = new PlayerMapTracker(map);
    }

    public void finishCourse() {
        this.mapTracker.finishMap();
    }

    public PlayerMapTracker getMapTracker() {
        return mapTracker;
    }

}
