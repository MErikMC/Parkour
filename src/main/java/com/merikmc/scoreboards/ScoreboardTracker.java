package com.merikmc.scoreboards;

import com.merikmc.data.PlayerTracker;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by codermason on 12/26/14.
 */
public class ScoreboardTracker {

    private static ScoreboardManager manager;

    private static Plugin plugin;

    private static Scoreboard scoreboard;

    private static Map<PlayerTracker, Scoreboard> scoreboardMap;

    private static final String OBJ_TITLE = ChatColor.BOLD + "" + ChatColor.GRAY + "Timer";
    private static final String OBJ_NAME = "timer";

    public static void init(Plugin p) {
        plugin = p;
        manager = Bukkit.getScoreboardManager();
        scoreboardMap = new HashMap<PlayerTracker, Scoreboard>();
        startTask();
    }

    private static void startTask() {
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {

            public void run() {
                for(PlayerTracker pt : PlayerTracker.getPlayerTrackers()) {

                    Player player = Bukkit.getPlayer(pt.getName());
                    if(player == null || !player.isOnline()) {
                        continue;
                    }

                    if(scoreboardMap.containsKey(pt)) {
                        scoreboardMap.remove(pt);
                        if(pt.isParkouring()) {
                            Scoreboard board = scoreboardMap.get(pt);
                            for(OfflinePlayer offlinePlayer : board.getPlayers()) {
                                board.resetScores(offlinePlayer);
                            }
                            Score score = board.getObjective(OBJ_NAME).getScore(Bukkit.getOfflinePlayer(pt.getMapTracker().getElapsedTimeFormatted()));
                            score.setScore(1);
                            player.setScoreboard(board);
                            scoreboardMap.put(pt, board);
                        } else {
                            player.setScoreboard(null);
                        }
                    } else if(pt.isParkouring()) {
                        if(pt.isParkouring()) {
                            createNewScoreboard(pt);
                        }
                    }
                }
            }

        }, 0L, 20L);
    }

    private static void createNewScoreboard(PlayerTracker playerTracker) {
        Player player = Bukkit.getPlayer(playerTracker.getName());
        if(player != null && player.isOnline()) {
            Scoreboard board = manager.getNewScoreboard();
            Objective obj = board.registerNewObjective(OBJ_NAME, "dummy");
            obj.setDisplaySlot(DisplaySlot.SIDEBAR);
            obj.setDisplayName(OBJ_TITLE);
            Score score = obj.getScore(Bukkit.getOfflinePlayer(playerTracker.getMapTracker().getElapsedTimeFormatted()));
            score.setScore(1);
            player.setScoreboard(board);
            scoreboardMap.put(playerTracker, board);
        }
    }

}
