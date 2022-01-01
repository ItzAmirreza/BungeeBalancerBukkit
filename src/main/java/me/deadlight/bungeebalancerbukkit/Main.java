package me.deadlight.bungeebalancerbukkit;

import me.deadlight.bungeebalancerbukkit.Events.LoginListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private static Main plugin;

    @Override
    public void onEnable() {
        plugin = this;
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new LoginListener(), this);
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
    }

    @Override
    public void onDisable() {
        plugin = null;
    }

    public static Main getInstance() {
        return plugin;
    }

}
