package me.yochran.monsoon.runnables;

import me.yochran.monsoon.MonsoonSMP;
import net.vectromc.vnitrogen.vNitrogen;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class AfkRunnable extends BukkitRunnable {

    private MonsoonSMP plugin;
    private vNitrogen nitrogen;

    public AfkRunnable() {
        plugin = MonsoonSMP.getPlugin(MonsoonSMP.class);
        nitrogen = vNitrogen.getPlugin(vNitrogen.class);
    }

    @Override
    public void run() {
        for (Player players : Bukkit.getOnlinePlayers()) {
            if (!plugin.afk.contains(players.getUniqueId())) {
                if (plugin.afk_timer.containsKey(players.getUniqueId())) {
                    if (plugin.afk_timer.get(players.getUniqueId()) < 300) {
                        plugin.afk_timer.put(players.getUniqueId(), plugin.afk_timer.get(players.getUniqueId()) + 1);
                    } else {
                        plugin.afk_timer.remove(players.getUniqueId());
                        plugin.afk.add(players.getUniqueId());
                        for (Player onlinePlayers : Bukkit.getOnlinePlayers()) {
                            onlinePlayers.sendMessage(ChatColor.translateAlternateColorCodes('&', players.getDisplayName() + "&a is now &2AFK&a."));
                        }
                    }
                }
            }
        }
    }
}
