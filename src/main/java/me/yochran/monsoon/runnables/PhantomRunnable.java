package me.yochran.monsoon.runnables;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Phantom;
import org.bukkit.scheduler.BukkitRunnable;

public class PhantomRunnable extends BukkitRunnable {

    @Override
    public void run() {
        World world = Bukkit.getWorld("world");
        if (world.getEntities().size() > 0) {
            for (Entity entity : world.getEntities()) {
                if (entity instanceof Phantom) {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "kill @e[type=phantom]");
                }
            }
        }
    }
}
