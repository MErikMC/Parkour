package com.merikmc.listeners;

import com.merikmc.data.PlayerTracker;
import com.merikmc.events.PlayerStartCourseEvent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * Created by codermason on 12/24/14.
 */
public class StartCourseListener implements Listener {

    @EventHandler
    public void onStart(PlayerStartCourseEvent e) {
        PlayerTracker pt = PlayerTracker.getPlayerTracker(e.getPlayer().getName());
        if(pt.hasLivesRemaining()) {
            e.getPlayer().sendMessage(ChatColor.BOLD + "" + ChatColor.GRAY + "Timer on! Finish the course as fast as possible!");
            e.getPlayer().sendMessage(ChatColor.GREEN + "You have " + ChatColor.GRAY + "" + ChatColor.UNDERLINE + ""
                    + pt.getLives() + "" + ChatColor.RESET + "" + ChatColor.GREEN + " lives left!");
            pt.startMap(e.getMap());
            e.getPlayer().teleport(e.getMap().getStartPoint());
        } else {
            e.getPlayer().sendMessage(ChatColor.RED + "You have ran out of lives!");
            e.getPlayer().sendMessage(ChatColor.RED + "Buy more at http://store.merikmc.com or wait until tomorrow!");
            e.setCancelled(true);
        }
    }

}
