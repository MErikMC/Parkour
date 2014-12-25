package com.merikmc.events;

import com.merikmc.map.Map;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Created by codermason on 12/25/14.
 */
public class PlayerFallOnCourseEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private Player player;

    private Map map;

    public PlayerFallOnCourseEvent(Player player, Map map) {
        this.player = player;
        this.map = map;
    }

    public Player getPlayer() {
        return player;
    }

    public Map getMap() {
        return map;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

}
