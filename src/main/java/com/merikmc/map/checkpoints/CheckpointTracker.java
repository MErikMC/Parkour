package com.merikmc.map.checkpoints;

import java.util.List;

/**
 * Created by codermason on 12/25/14.
 */
public class CheckpointTracker {

    private List<Checkpoint> checkpoints;

    public CheckpointTracker(List<Checkpoint> checkpoints) {
        this.checkpoints = checkpoints;
    }

    public List<Checkpoint> getCheckpoints() {
        return checkpoints;
    }

    public int getCheckpointNumber(Checkpoint checkpoint) {
        int number = 1;
        for(Checkpoint cp : checkpoints) {
            if(cp == checkpoint) {
                return number;
            } else {
                number ++;
            }
        }
        return -1;
    }

}
