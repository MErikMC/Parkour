package com.merikmc.listeners;

import com.merikmc.data.PlayerTracker;
import com.merikmc.events.PlayerFinishCourseEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Created by codermason on 12/25/14.
 */
public class UtilityListeners implements Listener {

    @EventHandler
    public void onFoodLoss(FoodLevelChangeEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        e.setCancelled(e.getEntityType() == EntityType.PLAYER);
    }

    @EventHandler
    public void onDamageByPlayer(EntityDamageByEntityEvent e) {
        e.setCancelled(e.getEntity().getType() == EntityType.PLAYER && e.getDamager().getType() == EntityType.PLAYER);
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        PlayerTracker pt = PlayerTracker.getPlayerTracker(e.getPlayer().getName());
        if(pt.isParkouring()) {
            Bukkit.getPluginManager().callEvent(new PlayerFinishCourseEvent(e.getPlayer(), pt.getActiveMap(), null, false));
            pt.finishMap();
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if(!PlayerTracker.hasLocalData(e.getPlayer().getName())) {
            PlayerTracker.addPlayerTracker(e.getPlayer().getName());
        }
    }

}
