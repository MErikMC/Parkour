package com.merikmc.events;

import com.merikmc.map.Map;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Created by codermason on 12/24/14.
 */
public class PlayerFinishCourseEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private Player player;

    private Map map;

    private String time;

    private boolean completedCourse;

    public PlayerFinishCourseEvent(Player player, Map map, String time, boolean completedCourse) {
        this.player = player;
        this.map = map;
        this.completedCourse = completedCourse;
    }

    public Player getPlayer() {
        return player;
    }

    public boolean didCompleteCourse() {
        return completedCourse;
    }

    public String getTime() {
        return time;
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
