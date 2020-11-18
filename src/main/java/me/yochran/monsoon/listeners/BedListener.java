package me.yochran.monsoon.listeners;

import me.yochran.monsoon.MonsoonSMP;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerBedLeaveEvent;

public class BedListener implements Listener {

    private MonsoonSMP plugin;

    public BedListener() {
        plugin = MonsoonSMP.getPlugin(MonsoonSMP.class);
    }

    @EventHandler
    public void onBedEnter(PlayerBedEnterEvent event) {
        Player player = event.getPlayer();
        World world = player.getWorld();
        plugin.in_beds.add(player.getUniqueId());
        System.out.println(plugin.in_beds.size());
        double percent = (double) plugin.in_beds.size() / (double) Bukkit.getOnlinePlayers().size();
        double percentUpdated = percent - plugin.afk.size();
        if (percentUpdated >= 0.3) {
            if (world.getTime() >= 12550) {
                world.setTime(0);
                plugin.in_beds.clear();
            }
        }
    }

    @EventHandler
    public void onBedLeave(PlayerBedLeaveEvent event) {
        Player player = event.getPlayer();
        plugin.in_beds.remove(player.getUniqueId());
        System.out.println(plugin.in_beds.size());
    }
}
