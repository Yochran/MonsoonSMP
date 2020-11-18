package me.yochran.monsoon.listeners;

import me.yochran.monsoon.MonsoonSMP;
import me.yochran.monsoon.management.PlayerManagement;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerDeathEvent implements Listener {

    private MonsoonSMP plugin;

    public PlayerDeathEvent() {
        plugin = MonsoonSMP.getPlugin(MonsoonSMP.class);
    }

    @EventHandler
    public void onPlayerDeath(org.bukkit.event.entity.PlayerDeathEvent event) {
        Player player = event.getEntity();
        PlayerManagement playerManagement = new PlayerManagement();
        playerManagement.addPlayerDeath(player);
        playerManagement.addServerDeath();
        plugin.data.config.set(player.getUniqueId().toString() + ".Name", player.getName());
        plugin.data.saveData();
    }
}
