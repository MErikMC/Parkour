package com.merikmc.map.trophys;

import com.merikmc.map.highscores.HighscoreTracker;

import java.util.List;

/**
 * Created by codermason on 12/25/14.
 */
public class TrophyTracker {

    private List<TrophyBlock> trophyBlocks;

    public TrophyTracker(List<TrophyBlock> trophyBlocks) {
        this.trophyBlocks = trophyBlocks;
    }

    public List<TrophyBlock> getTrophyBlocks() {
        return trophyBlocks;
    }

    public void update(HighscoreTracker highscores) {
        for(int i = 0; i < 5; i++) {
            TrophyBlock trophyBlock = getTrophyBlocks().get(i);
            trophyBlock.update(highscores.getHighscores().get(i), i + 1);
        }
    }

}
