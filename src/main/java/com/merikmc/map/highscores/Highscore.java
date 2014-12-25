package com.merikmc.map.highscores;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by codermason on 12/25/14.
 */
public class Highscore {

    private String name;

    private String time;

    public Highscore(String name, String time) {
        this.name = name;
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public String getName() {
        return name;
    }

    public static boolean compareTo(String timeOne, String timeTwo) {
        try {
            Date dOne = new SimpleDateFormat("mm:ss:SSS").parse(timeOne);
            Date dTwo = new SimpleDateFormat("mm:ss:SSS").parse(timeTwo);
            return dOne.after(dTwo);
        } catch(ParseException e) {
            return false;
        }
    }

}
