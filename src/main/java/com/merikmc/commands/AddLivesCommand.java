package com.merikmc.commands;

import com.merikmc.data.PlayerTracker;
import com.merikmc.util.NumberUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by codermason on 12/25/14.
 */
public class AddLivesCommand implements CommandExecutor {

    public AddLivesCommand() {} //??

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            if(!((Player)sender).hasPermission("parkour.addlives")) {
                sender.sendMessage(ChatColor.RED + "No permission.");
                return true;
            }
        }

        if(args.length == 2) {
            Player player = Bukkit.getPlayer(args[0]);
            if(player != null) {
                PlayerTracker pt = PlayerTracker.getPlayerTracker(args[0]);
                if(NumberUtil.isNumber(args[1])) {
                    int number = NumberUtil.parseInteger(args[1]);
                    pt.addLives(number);
                    sender.sendMessage(ChatColor.GREEN + "Added " + number + " lives to player " + args[0] + "!");
                } else {
                    sender.sendMessage(ChatColor.RED + "" + args[1] + " is not a number. :/");
                }
            } else {
                sender.sendMessage(ChatColor.RED + "Unable to find player " + args[0] + "!");
            }
        } else {
            sender.sendMessage(ChatColor.RED + "/addlives <player> <amount>");
        }
        return true;
    }
}
