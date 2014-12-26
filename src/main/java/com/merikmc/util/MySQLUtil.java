package com.merikmc.util;

import com.merikmc.Parkour;
import com.merikmc.map.highscores.Highscore;
import com.merikmc.map.highscores.HighscoreTracker;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by codermason on 12/25/14.
 */
public class MySQLUtil {

    private static String statement = "SELECT * FROM ? ORDER BY time DESC LIMIT 5";

    public static HighscoreTracker getHighscoreTracker(String map_name) {
        String query = statement.replace("?", map_name);
        List<Highscore> highscoreList = new ArrayList<Highscore>();
        try {
            ResultSet rs = Parkour.getConnection().createStatement().executeQuery(query);
            while(rs.next()) {
                highscoreList.add(new Highscore(rs.getString(1), rs.getString(2)));
            }
        } catch(SQLException e) {
            return null;
        }
        return new HighscoreTracker(highscoreList);
    }



}
