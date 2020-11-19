package me.yochran.monsoon.listeners;

import me.yochran.monsoon.MonsoonSMP;
import me.yochran.monsoon.utils.Utils;
import net.vectromc.vnitrogen.vNitrogen;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class AfkListener implements Listener {

    private MonsoonSMP plugin;
    private vNitrogen nitrogen;

    public AfkListener() {
        plugin = MonsoonSMP.getPlugin(MonsoonSMP.class);
        nitrogen = vNitrogen.getPlugin(vNitrogen.class);
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (plugin.afk.contains(player.getUniqueId())) {
            plugin.afk.remove(player.getUniqueId());
            nitrogen.setPlayerColor(player);
            for (Player players : Bukkit.getOnlinePlayers()) {
                Utils.sendMessage(players, player.getDisplayName() + " &ais no longer &2AFK&a.");
            }
        }
        plugin.afk_timer.remove(player.getUniqueId());
        plugin.afk_timer.put(player.getUniqueId(), 0);
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        if (plugin.afk.contains(player.getUniqueId())) {
            plugin.afk.remove(player.getUniqueId());
            nitrogen.setPlayerColor(player);
            for (Player players : Bukkit.getOnlinePlayers()) {
                Utils.sendMessage(players, player.getDisplayName() + " &ais no longer &2AFK&a.");
            }
        }
        plugin.afk_timer.remove(player.getUniqueId());
        plugin.afk_timer.put(player.getUniqueId(), 0);
    }
}
