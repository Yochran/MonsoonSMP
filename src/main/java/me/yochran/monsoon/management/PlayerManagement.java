package me.yochran.monsoon.management;

import me.yochran.monsoon.MonsoonSMP;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class PlayerManagement {

    private MonsoonSMP plugin;

    public PlayerManagement() {
        plugin = MonsoonSMP.getPlugin(MonsoonSMP.class);
    }

    public int getPlayerDeaths(OfflinePlayer player) {
        return plugin.data.config.getInt(player.getUniqueId().toString() + ".Deaths");
    }

    public int getServerDeaths() {
        return plugin.data.config.getInt("Server.Deaths");
    }

    public void addPlayerDeath(OfflinePlayer player) {
        plugin.data.config.set(player.getUniqueId().toString() + ".Deaths", getPlayerDeaths(player) + 1);
        plugin.data.config.set(player.getUniqueId().toString() + ".Name", player.getName());
        plugin.data.saveData();
    }

    public void addServerDeath() {
        plugin.data.config.set("Server.Deaths", getServerDeaths() + 1);
        plugin.data.saveData();
    }

    public void resetPlayerDeaths(OfflinePlayer player) {
        int deathsToRemove = getPlayerDeaths(player);
        plugin.data.config.set(player.getUniqueId().toString() + ".Deaths", null);
        plugin.data.config.set("Server.Deaths", getServerDeaths() - deathsToRemove);
        plugin.data.saveData();
    }
}
