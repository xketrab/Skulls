package me.bartek.skulls.listeners.join;

import me.bartek.skulls.Skulls;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    private final Skulls plugin;

    public JoinListener(Skulls plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void OnJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();

        if(!player.hasPlayedBefore()){
            plugin.getConfig().set("data." + player.getUniqueId().toString() + ".nick", player.getDisplayName());
            plugin.getConfig().set("data." + player.getUniqueId().toString() + ".skull", false);
            plugin.saveConfig();
            plugin.reloadConfig();
        }else{
            if(!plugin.getConfig().contains("data." + player.getUniqueId().toString())){
                plugin.getConfig().set("data." + player.getUniqueId().toString() + ".nick", player.getDisplayName());
                plugin.getConfig().set("data." + player.getUniqueId().toString() + ".skull" , false);
                plugin.saveConfig();
                plugin.reloadConfig();
            }
        }
    }
}
