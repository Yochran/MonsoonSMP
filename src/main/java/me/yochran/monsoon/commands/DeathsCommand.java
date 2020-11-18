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
import org.bukkit.entity.Player;

public class DeathsCommand implements CommandExecutor {

    private MonsoonSMP plugin;
    private vNitrogen nitrogen;

    public DeathsCommand() {
        plugin = MonsoonSMP.getPlugin(MonsoonSMP.class);
        nitrogen = vNitrogen.getPlugin(vNitrogen.class);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        PlayerManagement playerManagement = new PlayerManagement();
        if (args.length == 0) {
            if (!(sender instanceof Player)) {
                Utils.sendMessage(sender, "[Monsoon]: Console has no deaths.");
            } else {
                Player player = (Player) sender;
                int deaths = playerManagement.getPlayerDeaths(player);
                Utils.sendMessage(player, "&aYour death count: &2" + deaths);
            }
        } else {
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("server")) {
                    int deaths = playerManagement.getServerDeaths();
                    Utils.sendMessage(sender, "&aServer death count: &2" + deaths);
                } else {
                    OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
                    if (!plugin.data.config.contains(target.getUniqueId().toString()) || !nitrogen.pData.config.contains(target.getUniqueId().toString())) {
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
                        int deaths = playerManagement.getPlayerDeaths(target);
                        Utils.sendMessage(sender, targetDisplay + "&a's death count: &2" + deaths);
                    }
                }
            } else {
                Utils.sendMessage(sender, "&7[&a&lMonsoon&7] &cIncorrect Usage! /deaths [player/server]");
            }
        }
        return true;
    }
}
