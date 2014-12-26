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
    public void onCompletion(PlayerFinishCourseEvent e) { //if completed course, check if time is in top 5, update trophies, insert if the time is less, addcoins
        if(e.didCompleteCourse()) {
            
        }
    }

}
