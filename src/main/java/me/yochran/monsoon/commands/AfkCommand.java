package me.yochran.monsoon.commands;

import me.yochran.monsoon.MonsoonSMP;
import me.yochran.monsoon.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AfkCommand implements CommandExecutor {

    private MonsoonSMP plugin;

    public AfkCommand() {
        plugin = MonsoonSMP.getPlugin(MonsoonSMP.class);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Utils.sendMessage(sender, "&7[&a&lMonsoon&7] &cYou must be a player to use that command.");
        } else {
            Player player = (Player) sender;
            if (plugin.afk.contains(player.getUniqueId())) {
                plugin.afk.remove(player.getUniqueId());
                plugin.afk_timer.remove(player.getUniqueId());
                for (Player onlinePlayers : Bukkit.getOnlinePlayers()) {
                    onlinePlayers.sendMessage(ChatColor.translateAlternateColorCodes('&', player.getDisplayName() + "&a is no longer &2AFK&a."));
                }
            } else {
                plugin.afk.add(player.getUniqueId());
                plugin.afk_timer.remove(player.getUniqueId());
                for (Player onlinePlayers : Bukkit.getOnlinePlayers()) {
                    onlinePlayers.sendMessage(ChatColor.translateAlternateColorCodes('&', player.getDisplayName() + "&a is now &2AFK&a."));
                }
            }
        }
        return true;
    }
}
