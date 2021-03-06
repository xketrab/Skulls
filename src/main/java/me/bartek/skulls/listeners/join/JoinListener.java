package me.bartek.skulls.listeners.join;

import me.bartek.skulls.Skulls;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    static Skulls main = new Skulls();

    @EventHandler
    public static void OnJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();

        if(!player.hasPlayedBefore()){
            main.getConfig().set("data." + player.getUniqueId().toString() + ".nick", player.getDisplayName());
            main.getConfig().set("data." + player.getUniqueId().toString() + ".skull", false);
        }
    }
}
