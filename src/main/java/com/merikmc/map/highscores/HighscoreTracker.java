package com.merikmc.map.highscores;

import java.util.List;

/**
 * Created by codermason on 12/25/14.
 */
public class HighscoreTracker {

    private List<Highscore> highscores; //MAX amount is 5

    public HighscoreTracker(List<Highscore> list) {
        this.highscores = list;
    }

    public List<Highscore> getHighscores() {
        return highscores;
    }

    public int update(Highscore hs) {
        int spot = 0;
        for(Highscore hss : highscores) {
            if(Highscore.compareTo(hs.getTime(), hss.getTime())) {
                placeScore(hs, spot);
                return spot + 1;
            } else {
                spot++;
            }
        }
        return -1;
    }

    private void placeScore(Highscore hs, int spot) {
        if(spot < 0 || spot > 4) return;

        if(!highscores.isEmpty()) {
            highscores.remove(highscores.size() - 1);
        }
        highscores.add(spot, hs);
    }

}
