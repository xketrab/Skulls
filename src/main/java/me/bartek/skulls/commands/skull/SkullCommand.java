package me.bartek.skulls.commands.skull;

import me.bartek.skulls.Skulls;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SkullCommand implements CommandExecutor {

    private final Skulls plugin;

    public SkullCommand(Skulls plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;
        //boolean skull = plugin.getConfig().getBoolean("data." + n.getUniqueId().toString() + ".skull");
        //String name = (String) plugin.getConfig().get("data." + n.getUniqueId().toString() + ".name");

        if(args[0].equalsIgnoreCase("info")){
            Player n = Bukkit.getPlayer(args[1]);
            boolean skull = plugin.getConfig().getBoolean("data." + n.getUniqueId().toString() + ".skull");
            if(skull){
                player.sendMessage(ChatColor.RED + "Player " + plugin.getConfig().get("data." + n.getUniqueId().toString() + ".nick") + " have skull.");
            }else{
                player.sendMessage(ChatColor.GREEN + "Player " + plugin.getConfig().get("data." + n.getUniqueId().toString() + ".nick") + " doesn't have skull.");
            }
        }else if(args[0].equalsIgnoreCase("add")){
            Player n = Bukkit.getPlayer(args[1]);
            boolean skull = plugin.getConfig().getBoolean("data." + n.getUniqueId().toString() + ".skull");
            if(skull){
                player.sendMessage(ChatColor.RED + "This player already have skull!");
            }else{
                plugin.getConfig().set("data." + n.getUniqueId().toString() + ".skull", true);
                player.sendMessage(ChatColor.GREEN + "Success! Player " + plugin.getConfig().get("data." + n.getUniqueId().toString() + ".nick") + " have skull now.");
                reloadConfig();
            }
        }else if(args[0].equalsIgnoreCase("remove")){
            Player n = Bukkit.getPlayer(args[1]);
            player.sendMessage("uuid args 1 -> " + n.getUniqueId().toString());
            player.sendMessage("uuid args1 without .toString -> " + n.getUniqueId());
            player.sendMessage("skull args1 -> " + plugin.getConfig().getBoolean("data." + n.getUniqueId().toString() + ".skull"));
            player.sendMessage("nickname args1 -> " + plugin.getConfig().get("data." + n.getUniqueId().toString() + ".nick"));
            boolean skull = plugin.getConfig().getBoolean("data." + n.getUniqueId().toString() + ".skull");
            if(!skull){
                player.sendMessage(ChatColor.RED + "This player doesn't have skull.");
            }else{
                String path = "data." + n.getUniqueId().toString() + ".skull";
                player.sendMessage(path);
                plugin.getConfig().set(path,false);
                player.sendMessage(ChatColor.GREEN + "Success! Player " + plugin.getConfig().get("data." + n.getUniqueId().toString() + ".nick") + " doesn't have skull now.");
                //reloadConfig();
            }
        }else if(args[0].equalsIgnoreCase("reload")){
            //reloadConfig();
            plugin.reloadConfig();
        }


        return true;
    }

    public void reloadConfig(){
        plugin.reloadConfig();
        plugin.saveConfig();
    }
    //skull info xketrab
    //skull chceck xketrab
    //skull add/remove xketrab
}
