package com.merikmc.listeners;

import com.merikmc.data.PlayerTracker;
import com.merikmc.events.PlayerCheckpointReachEvent;
import com.merikmc.events.PlayerFinishCourseEvent;
import com.merikmc.map.Map;
import com.merikmc.map.checkpoints.Checkpoint;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

/**
 * Created by codermason on 12/25/14.
 */
public class MovementListener implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        if(e.getFrom().getBlock() == e.getTo().getBlock()) return;

        PlayerTracker pt = PlayerTracker.getPlayerTracker(e.getPlayer().getName());
        if(pt.isParkouring()) {
            Map map = pt.getActiveMap();

            //CHECK POINTS
            for(Checkpoint cp : map.getCheckpointTracker().getCheckpoints()) {
                if(cp.getLocation().equals(e.getTo())) {
                    Bukkit.getPluginManager().callEvent(new PlayerCheckpointReachEvent(e.getPlayer(), cp, map));
                    return;
                }
            }

            //FINISH LINE
            if(map.getEndPoint().equals(e.getTo())) {
                pt.finishMap();
                Bukkit.getPluginManager().callEvent(new PlayerFinishCourseEvent(e.getPlayer(), map, pt.getTimeFormatted(), true));
                return;
            }
        }
    }

}
