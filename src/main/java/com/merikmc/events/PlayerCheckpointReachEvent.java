package com.merikmc.events;

import com.merikmc.map.Map;
import com.merikmc.map.checkpoints.Checkpoint;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Created by codermason on 12/24/14.
 */
public class PlayerCheckpointReachEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private Player player;

    private Map map;

    private Checkpoint checkpoint;

    public PlayerCheckpointReachEvent(Player player, Checkpoint checkpoint, Map map) {
        this.player = player;
        this.map = map;
        this.checkpoint = checkpoint;
    }

    public Player getPlayer() {
        return player;
    }

    public Checkpoint getCheckpoint() {
        return checkpoint;
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
