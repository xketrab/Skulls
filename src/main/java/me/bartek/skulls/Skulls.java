package me.bartek.skulls;

import me.bartek.skulls.commands.skull.SkullCommand;
import me.bartek.skulls.commands.skull.SkullCommandTabCompleter;
import me.bartek.skulls.listeners.death.DeathListener;
import me.bartek.skulls.listeners.join.JoinListener;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class Skulls extends JavaPlugin implements CommandExecutor {

    private static final Logger log = Logger.getLogger("Minecraft");
    private static Economy econ = null;


    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(new JoinListener(this), this);
        this.getServer().getPluginManager().registerEvents(new DeathListener(this), this);
        this.saveDefaultConfig();

        this.getCommand("skull").setExecutor(new SkullCommand(this));
        this.getCommand("skull").setTabCompleter(new SkullCommandTabCompleter(this));

        if (!setupEconomy() ) {
            log.severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
    }

    @Override
    public void onDisable() {
        log.info(String.format("[%s] Disabled Version %s", getDescription().getName(), getDescription().getVersion()));
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    public static Economy getEconomy() {
        return econ;
    }

}
