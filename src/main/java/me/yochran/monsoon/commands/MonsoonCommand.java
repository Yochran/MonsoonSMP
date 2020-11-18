package me.yochran.monsoon.commands;

import me.yochran.monsoon.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class MonsoonCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            Utils.liner(sender);
            Utils.sendMessage(sender, "&7[&a&lMonsoon&7]:");
            Utils.spacer(sender);
            Utils.sendMessage(sender, "&2Plugin By: &5Yochran");
            Utils.sendMessage(sender, "&2Plugin Version: &a1.3");
            Utils.liner(sender);
        } else if (args.length == 1) {
            if (args[0].equalsIgnoreCase("help")) {
                Utils.liner(sender);
                Utils.sendMessage(sender, "&7[&a&lMonsoon&7]: &2Help:");
                Utils.spacer(sender);
                Utils.sendMessage(sender, "/monsoon, /deaths, /resetdeaths, /afk");
                Utils.liner(sender);
            } else {
                Utils.liner(sender);
                Utils.sendMessage(sender, "&7[&a&lMonsoon&7]:");
                Utils.spacer(sender);
                Utils.sendMessage(sender, "&2Plugin By: &5Yochran");
                Utils.sendMessage(sender, "&2Plugin Version: &a1.3");
                Utils.liner(sender);
            }
        } else {
            Utils.liner(sender);
            Utils.sendMessage(sender, "&7[&a&lMonsoon&7]:");
            Utils.spacer(sender);
            Utils.sendMessage(sender, "&2Plugin By: &5Yochran");
            Utils.sendMessage(sender, "&2Plugin Version: &a1.3");
            Utils.liner(sender);
        }
        return true;
    }
}
