package com.merikmc.map.checkpoints;

import org.bukkit.Location;

/**
 * Created by codermason on 12/24/14.
 */
public class Checkpoint {

    private Location location;

    public Checkpoint(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

}
