package me.yochran.monsoon.listeners;

import me.yochran.monsoon.MonsoonSMP;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerLogListener implements Listener {

    private MonsoonSMP plugin;

    public PlayerLogListener() {
        plugin = MonsoonSMP.getPlugin(MonsoonSMP.class);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        plugin.data.config.set(player.getUniqueId().toString() + ".Name", player.getName());
        if (!plugin.data.config.contains(player.getUniqueId().toString())) {
            plugin.data.config.set(player.getUniqueId().toString() + ".Deaths", 0);
        }
        plugin.data.saveData();
    }
}
