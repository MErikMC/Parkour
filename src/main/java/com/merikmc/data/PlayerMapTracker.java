package com.merikmc.data;

import com.merikmc.map.Map;
import com.merikmc.map.checkpoints.Checkpoint;
import com.sun.tools.javac.comp.Check;

/**
 * Created by codermason on 12/26/14.
 */
public class PlayerMapTracker {

    private long startTime, elapsedTime;

    private Checkpoint lastCheckpoint;

    private Map map;

    public PlayerMapTracker(Map map) {
        this.map = map;
        this.lastCheckpoint = null;
        this.startTime = System.currentTimeMillis();
    }

    public Map getMap() {
        return map;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getElapsedTime() {
        return System.currentTimeMillis() - startTime;
    }

    public long getFinalizedTime() {
        return elapsedTime;
    }

    public void finishMap() {
        this.elapsedTime = getElapsedTime();
    }

    public void activateCheckpoint(Checkpoint checkpoint) {
        this.lastCheckpoint = checkpoint;
    }

    public Checkpoint getLastCheckpoint() {
        return lastCheckpoint;
    }

}
