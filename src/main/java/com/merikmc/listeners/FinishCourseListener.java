package com.merikmc.listeners;

import com.merikmc.Parkour;
import com.merikmc.data.PlayerTracker;
import com.merikmc.events.PlayerFinishCourseEvent;
import com.merikmc.map.highscores.Highscore;
import com.merikmc.util.MySQLUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * Created by codermason on 12/24/14.
 */
public class FinishCourseListener implements Listener {

    @EventHandler
    public void onCompletion(PlayerFinishCourseEvent e) {
        if(e.didCompleteCourse()) {
            int num = e.getMap().getHighscoreTracker().update(new Highscore(e.getPlayer().getName(), e.getTime()));
            if(num > 0 && num < 6) {
                Bukkit.broadcastMessage(ChatColor.GREEN + "" + ChatColor.BOLD + e.getPlayer().getName() + " has placed #" + num + " on map " + e.getMap().getName() + "!");
            } else {
                e.getPlayer().sendMessage(ChatColor.RED + "You did not place in the top 5 on map " + e.getMap().getName() + ".");
            }
            e.getMap().getTrophyTracker().update(e.getMap().getHighscoreTracker());
            MySQLUtil.updateHighscores(e.getMap());
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "addcoins " + e.getPlayer().getName() + " " + Parkour.COINS_FOR_COMPLETION);
        }
        if(e.getPlayer() != null) {
            e.getPlayer().setHealth(0.0); //reset the player at spawn
        }
    }

}
