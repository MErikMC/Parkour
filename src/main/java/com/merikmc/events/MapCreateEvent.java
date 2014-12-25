package com.merikmc.events;

import com.merikmc.map.Map;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Created by codermason on 12/24/14.
 */
public class MapCreateEvent extends Event{

    private static final HandlerList handlers = new HandlerList();

    private String map;

    public MapCreateEvent(String map) {
        this.map = map;
    }

    public String getMap() {
        return map;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
