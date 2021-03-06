package me.bartek.skulls.listeners.death;

import me.bartek.skulls.Skulls;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener implements Listener {

    static Skulls main = new Skulls();


    @EventHandler
    public void DeathEvent(PlayerDeathEvent event){
        Player player = event.getEntity().getPlayer();
        Player killer = event.getEntity().getKiller();
        boolean player_skull = main.getConfig().getBoolean("data." + player.getUniqueId().toString() + ".skull");
        boolean killer_skull = main.getConfig().getBoolean("data." + killer.getUniqueId().toString() + ".skull");


        if(!killer_skull && player_skull){
            main.getConfig().set("data." + player.getUniqueId().toString() + ".skull",false);
            //add balance to killer account
        }

        if(!killer_skull && !player_skull){
            main.getConfig().set("data." + killer.getUniqueId().toString() + ".skull",true);
            //remove balance from killer account
        }

        if(killer_skull && !player_skull){
            //remove balance from killer account
        }

        if(killer_skull && player_skull){
            main.getConfig().set("data." + player.getUniqueId().toString() + ".skull",false);
            //add balance to killer account
        }


    }
}
