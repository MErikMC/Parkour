package com.merikmc.util;

import com.merikmc.Parkour;
import com.merikmc.map.Map;
import com.merikmc.map.highscores.Highscore;
import com.merikmc.map.highscores.HighscoreTracker;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by codermason on 12/25/14.
 */
public class MySQLUtil {

    private static String statement = "SELECT player_name, time FROM ? ORDER BY place DESC";

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

    public static void updateHighscores(Map map) {
        try {
            Connection conn = Parkour.getConnection();
            conn.createStatement().executeQuery("TRUNCATE TABLE " + map.getName() + "_map");
            for(int i = 1; i <=5; i++) {
                Statement st = conn.createStatement();
                if(map.getHighscoreTracker().getHighscores().size() > (i - 1)) {
                    Highscore highscore = map.getHighscoreTracker().getHighscores().get(i - 1);
                    if(highscore != null) {
                        st.executeQuery("INSERT INTO " + map.getName() + "_map (place, player_name, time) VALUES " +
                                        "('" + i +"', '" + highscore.getName() +"', '" + highscore.getTime() +"')");
                    }
                }
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

}
