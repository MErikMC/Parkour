package com.merikmc.listeners;

import com.merikmc.Parkour;
import com.merikmc.events.MapCreateEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by codermason on 12/24/14.
 */
public class MapCreateListener implements Listener {

    @EventHandler
    public void onCreate(MapCreateEvent e) {
        if(Parkour.getConnection() == null) {
            Parkour.reloadConnection();
        }
        try {
            Statement statement = Parkour.getConnection().createStatement();
            statement.executeQuery("CREATE TABLE IF NOT EXISTS " + e.getMap() + "_map (place INT, player_name VARCHAR(100), time VARCHAR(100));");
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

}
