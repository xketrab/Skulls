package me.bartek.skulls;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Skulls extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage("hello world");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
