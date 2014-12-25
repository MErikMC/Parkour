package com.merikmc.listeners;

import com.merikmc.data.PlayerTracker;
import com.merikmc.events.PlayerCheckpointReachEvent;
import com.merikmc.map.Map;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * Created by codermason on 12/25/14.
 */
public class CheckpointReachListener implements Listener {

    public CheckpointReachListener() {} //TODO import local data

    @EventHandler
    public void onReach(PlayerCheckpointReachEvent e) {
        PlayerTracker pt = PlayerTracker.getPlayerTracker(e.getPlayer().getName());
        pt.setLastCheckpoint(e.getCheckpoint());

        int checkpointNum = e.getMap().getCheckpointTracker().getCheckpointNumber(e.getCheckpoint());
        e.getPlayer().sendMessage(ChatColor.GRAY + "" + ChatColor.BOLD + "Checkpoint #" + checkpointNum + " activated");
    }

}
