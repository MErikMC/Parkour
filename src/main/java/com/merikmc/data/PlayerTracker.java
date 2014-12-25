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

    private Map activeMap;

    private long startTime;

    private long elapsedtime;

    private Checkpoint lastCheckpoint;

    private boolean isParkouring;

    public PlayerTracker(String playerName) {
        this.name = name;
        this.lives = computeLives();
        this.activeMap = null;
        this.startTime = 0;
        this.lastCheckpoint = null;
        this.isParkouring = false;
        this.elapsedtime = 0;
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

    public Map getActiveMap() {
        return activeMap;
    }

    public void setActiveMap(Map activeMap) {
        this.activeMap = activeMap;
    }

    public Checkpoint getLastCheckpoint() {
        return lastCheckpoint;
    }

    public void setLastCheckpoint(Checkpoint checkpoint) {
        this.lastCheckpoint = checkpoint;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getElapsedtime() {
        return elapsedtime;
    }

    public boolean isParkouring() {
        return isParkouring;
    }

    public String getTimeFormatted() {
        return new SimpleDateFormat("mm:ss:SSS").format(new Date(elapsedtime)).toString();
    }

    public void startMap(Map map) {
        setActiveMap(map);
        setLastCheckpoint(null);
        this.startTime = System.currentTimeMillis();
        this.isParkouring = true;
    }

    public void finishMap() {
        setActiveMap(null);
        setLastCheckpoint(null);
        this.isParkouring = false;
        this.elapsedtime = System.currentTimeMillis() - startTime;
    }

    private int computeLives() {
        Player p = Bukkit.getPlayer(name);
        if(p != null) {
            for(PermissionAttachmentInfo pai : p.getEffectivePermissions()) {
                String permission = pai.getPermission();
                if(permission.contains("parkour.lives.")) {
                    String newString = permission.substring(0, permission.lastIndexOf('.'));
                    if(NumberUtil.isNumber(newString)) {
                        return NumberUtil.parseInteger(newString);
                    }
                }
            }
        }
        return Parkour.DEFAULT_LIVES_PER_DAY;
    }

}
