package me.yochran.monsoon;

import me.yochran.monsoon.commands.AfkCommand;
import me.yochran.monsoon.commands.DeathsCommand;
import me.yochran.monsoon.commands.MonsoonCommand;
import me.yochran.monsoon.commands.ResetDeathsCommand;
import me.yochran.monsoon.data.PlayerData;
import me.yochran.monsoon.listeners.*;
import me.yochran.monsoon.runnables.AfkRunnable;
import me.yochran.monsoon.runnables.PhantomRunnable;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public final class MonsoonSMP extends JavaPlugin {

    public PlayerData data;

    @Override
    public void onEnable() {
        // Plugin startup logic
        registerCommands();
        registerEvents();
        runRunnables();
        registerData();
        startupAnnouncements();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        shutdownAnnouncements();
    }

    public HashMap<UUID, Integer> afk_timer = new HashMap<>();
    public ArrayList<UUID> afk = new ArrayList<>();
    public ArrayList<UUID> in_beds = new ArrayList<>();

    private void startupAnnouncements() {
        System.out.println("[Monsoon]: Monsoon SMP Core v1.3 by Yochran is loading...");
        System.out.println("[Monsoon]: Monsoon SMP Core v1.3 by Yochran has successfully loaded.");
    }

    private void shutdownAnnouncements() {
        System.out.println("[Monsoon]: Monsoon SMP Core v1.3 by Yochran is unloading...");
        System.out.println("[Monsoon]: Monsoon SMP Core v1.3 by Yochran has successfully unloaded.");
    }

    private void registerCommands() {
        getCommand("Monsoon").setExecutor(new MonsoonCommand());
        getCommand("Afk").setExecutor(new AfkCommand());
        getCommand("Deaths").setExecutor(new DeathsCommand());
        getCommand("ResetDeaths").setExecutor(new ResetDeathsCommand());
    }

    private void registerEvents() {
        PluginManager manager = getServer().getPluginManager();
        manager.registerEvents(new PlayerLogListener(), this);
        manager.registerEvents(new AfkListener(), this);
        manager.registerEvents(new BedListener(), this);
        manager.registerEvents(new PlayerDeathEvent(), this);
    }

    private void runRunnables() {
        new AfkRunnable().runTaskTimer(this, 0, 20);
        new PhantomRunnable().runTaskTimer(this, 0, 20);
    }

    private void registerData() {
        data = new PlayerData();
        data.setupData();
        data.saveData();
        data.reloadData();
    }
}
