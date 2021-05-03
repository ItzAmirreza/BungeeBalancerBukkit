package me.deadlight.bungeebalancerbukkit;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class BungeeBalancerBukkit extends JavaPlugin {

    private static BungeeBalancerBukkit plugin;

    public static BungeeBalancerBukkit getPlugin() {
        return plugin;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        getServer().getPluginManager().registerEvents(new onLogin(), this);
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        logConsole("&6Bungee Balancer Load Shod. By Dead_Light\"");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void logConsole(String str) {
        this.getServer().getConsoleSender().sendMessage(utils.color(str));
    }
}
