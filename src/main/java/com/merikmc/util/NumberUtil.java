package com.merikmc.util;

import com.merikmc.Parkour;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachmentInfo;

/**
 * Created by codermason on 12/25/14.
 */
public class NumberUtil {

    public static int parseInteger(String input) {
        if(isNumber(input)) {
            return Integer.parseInt(input);
        } else {
            return 0;
        }
    }

    public static boolean isNumber(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch(NumberFormatException nfe) {
            return false;
        }
    }

    public static int getLives(String playerName) {
        Player p = Bukkit.getPlayer(playerName);
        if(p != null) {
            for(PermissionAttachmentInfo pai : p.getEffectivePermissions()) {
                String permission = pai.getPermission();
                if(permission.contains("parkour.lives.")) {
                    String newString = permission.substring(0, permission.lastIndexOf('.'));
                    if(NumberUtil.isNumber(newString)) {
                        return NumberUtil.parseInteger(newString);
                    }
                }
            }
        }
        return Parkour.DEFAULT_LIVES_PER_DAY;
    }

}
