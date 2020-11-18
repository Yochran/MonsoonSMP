package me.yochran.monsoon.commands;

import me.yochran.monsoon.MonsoonSMP;
import me.yochran.monsoon.management.PlayerManagement;
import me.yochran.monsoon.utils.Utils;
import net.vectromc.vnitrogen.vNitrogen;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ResetDeathsCommand implements CommandExecutor {

    private MonsoonSMP plugin;
    private vNitrogen nitrogen;

    public ResetDeathsCommand() {
        plugin = MonsoonSMP.getPlugin(MonsoonSMP.class);
        nitrogen = vNitrogen.getPlugin(vNitrogen.class);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("monsoon.resetdeaths")) {
            Utils.sendMessage(sender, "&7[&a&lMonsoon&7] &cYou do not have permission to use that command.");
        } else {
            if (args.length != 1) {
                Utils.sendMessage(sender, "&7[&a&lMonsoon&7] &cIncorrect Usage! /resetdeaths <player>");
            } else {
                OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
                if (!nitrogen.pData.config.contains(target.getUniqueId().toString()) || !plugin.data.config.contains(target.getUniqueId().toString())) {
                    Utils.sendMessage(sender, "&7[&a&lMonsoon&7] &cInvalid Player!");
                } else {
                    String targetName = target.getName();
                    String targetColor = "";
                    for (String rank : nitrogen.getConfig().getConfigurationSection("Ranks").getKeys(false)) {
                        if (nitrogen.pData.config.getString(target.getUniqueId().toString() + ".Rank").equalsIgnoreCase(rank)) {
                            targetColor = nitrogen.getConfig().getString("Ranks." + rank + ".color");
                        }
                    }
                    String targetDisplay = targetColor + targetName;
                    Utils.sendMessage(sender, "&aYou have reset " + targetDisplay + "&a's deaths.");
                    PlayerManagement playerManagement = new PlayerManagement();
                    playerManagement.resetPlayerDeaths(target);
                    plugin.data.config.set(target.getUniqueId().toString() + ".Name", targetName);
                    plugin.data.saveData();
                }
            }
        }
        return true;
    }
}
