package com.merikmc.commands;

import com.merikmc.Parkour;
import com.merikmc.data.Config;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * Created by codermason on 12/25/14.
 */
public class CreateMapCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(sender instanceof Player) {
            if(!((Player)sender).hasPermission("parkour.createmap")) {
                sender.sendMessage(ChatColor.RED + "No permission.");
                return true;
            }
        }

        if(args.length == 1) {
            Config config = Parkour.getInstance().getConfigClass();
            config.getMapsConfig().set("maps." + args[0], true);
            //TODO
        } else {
            sender.sendMessage(ChatColor.RED + "/createmap <mapname>");
        }

        return true;
    }
}
