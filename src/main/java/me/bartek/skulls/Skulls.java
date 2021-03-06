package me.bartek.skulls;

import me.bartek.skulls.listeners.death.DeathListener;
import me.bartek.skulls.listeners.join.JoinListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Skulls extends JavaPlugin {

    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(new JoinListener(), this);
        this.getServer().getPluginManager().registerEvents(new DeathListener(), this);
        this.saveDefaultConfig();



    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
