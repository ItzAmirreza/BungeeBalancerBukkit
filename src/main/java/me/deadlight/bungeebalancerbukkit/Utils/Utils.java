package me.deadlight.bungeebalancerbukkit.Utils;

import me.deadlight.bungeebalancerbukkit.Main;
import org.bukkit.ChatColor;

public class Utils {

    public static String colorize(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public static String getConfig(String path) {
        return Main.getInstance().getConfig().getString(path);
    }

}
