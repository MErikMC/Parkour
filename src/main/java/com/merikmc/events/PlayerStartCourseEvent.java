package com.merikmc.events;

import com.merikmc.map.Map;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Created by codermason on 12/24/14.
 */
public class PlayerStartCourseEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();

    private Player player;

    private Map map;

    private boolean cancelled;

    public PlayerStartCourseEvent(Player player, Map map) {
        this.player = player;
        this.map = map;
    }

    public Player getPlayer() {
        return player;
    }

    public Map getMap() {
        return map;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean b) {
        this.cancelled = b;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
