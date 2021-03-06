package me.bartek.skulls.listeners.death;

import me.bartek.skulls.Skulls;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener implements Listener {

    private final Skulls plugin;

    public DeathListener(Skulls plugin) {
        this.plugin = plugin;
    }


    @EventHandler
    public void DeathEvent(PlayerDeathEvent event) {
        Player player = event.getEntity().getPlayer();
        Player killer = event.getEntity().getKiller();
        boolean player_skull = plugin.getConfig().getBoolean("data." + player.getUniqueId().toString() + ".skull");
        boolean killer_skull = plugin.getConfig().getBoolean("data." + killer.getUniqueId().toString() + ".skull");
        int addmoney = plugin.getConfig().getInt("options.addmoney");
        int removemoney = plugin.getConfig().getInt("options.removemoney");


        if (!killer_skull && player_skull) {
            plugin.getConfig().set("data." + player.getUniqueId().toString() + ".skull", false);
            reloadConfig();

            EconomyResponse r = plugin.getEconomy().depositPlayer(killer, addmoney);
            if (r.transactionSuccess()) {
                Bukkit.broadcastMessage(ChatColor.GREEN + "Player " + killer.getDisplayName() + " killed aggressive player. " + ChatColor.DARK_GREEN + "Reward: " + addmoney + "$");
            }
        }

        if (!killer_skull && !player_skull) {
            plugin.getConfig().set("data." + killer.getUniqueId().toString() + ".skull", true);
            reloadConfig();

            EconomyResponse r = plugin.getEconomy().withdrawPlayer(killer, removemoney);
            if (r.transactionSuccess()) {
                Bukkit.broadcastMessage(ChatColor.RED + "Player " + killer.getDisplayName() + " killed peaceful player." + ChatColor.DARK_RED + "Punishment: " + removemoney + "$");
            }
        }

        if (killer_skull && !player_skull) {

            EconomyResponse r = plugin.getEconomy().withdrawPlayer(killer, removemoney);
            if (r.transactionSuccess()) {
                Bukkit.broadcastMessage(ChatColor.RED + "Aggressive player " + killer.getDisplayName() + " killed peaceful player." + ChatColor.DARK_RED + "Punishment: " + removemoney + "$");
            }
        }

        if (killer_skull && player_skull) {
            plugin.getConfig().set("data." + player.getUniqueId().toString() + ".skull", false);
            reloadConfig();

            EconomyResponse r = plugin.getEconomy().depositPlayer(killer, addmoney);
            if (r.transactionSuccess()) {
                Bukkit.broadcastMessage(ChatColor.GREEN + "Aggressive player " + killer.getDisplayName() + " killed aggressive player." + ChatColor.DARK_GREEN + "Reward: " + addmoney + "$");
            }
        }


        //sender.sendMessage(String.format("You have %s", econ.format(econ.getBalance(player.getName()))));
//        EconomyResponse r = econ.depositPlayer(player, 1.05);
//        if (r.transactionSuccess()) {
//            sender.sendMessage(String.format("You were given %s and now have %s", econ.format(r.amount), econ.format(r.balance)));


    }
    public void reloadConfig(){
        plugin.reloadConfig();
        plugin.saveConfig();
    }
}
