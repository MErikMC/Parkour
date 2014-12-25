package com.merikmc.listeners;

import com.merikmc.data.PlayerTracker;
import com.merikmc.events.PlayerFallOnCourseEvent;
import com.merikmc.events.PlayerFinishCourseEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * Created by codermason on 12/25/14.
 */
public class FallOnCourseListener implements Listener {

    public FallOnCourseListener() {} //TODO load in local data

    @EventHandler
    public void onFail(PlayerFallOnCourseEvent e) {
        PlayerTracker pt = PlayerTracker.getPlayerTracker(e.getPlayer().getName());
        if(pt != null) {
            pt.removeLives(1);
            if(pt.hasLivesRemaining()) {
                e.getPlayer().teleport(pt.getLastCheckpoint().getLocation());
                e.getPlayer().sendMessage(ChatColor.GREEN + "You have " + ChatColor.GRAY + "" + ChatColor.UNDERLINE + ""
                        + pt.getLives() + "" + ChatColor.RESET + "" + ChatColor.GREEN + " lives left!");
            } else {
                e.getPlayer().sendMessage(ChatColor.RED + "You have ran out of lives!");
                Bukkit.getPluginManager().callEvent(new PlayerFinishCourseEvent(e.getPlayer(), e.getMap(), null, false));
            }
        }
    }

}
