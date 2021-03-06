package me.bartek.skulls.commands.skull;

import me.bartek.skulls.Skulls;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SkullCommandTabCompleter implements TabCompleter {

    private final Skulls plugin;

    public SkullCommandTabCompleter(Skulls plugin) {
        this.plugin = plugin;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

        if (command.getName().equalsIgnoreCase("skull")) {

            List<String> argList = new ArrayList<>();

            if (args.length == 1) {
                if(hasPermission(sender, "skull.admin")){
                    argList.add("info");
                    argList.add("add");
                    argList.add("remove");
                    argList.add("reload");
                }else{
                    argList.add("info");
                }

                return argList.stream().filter(a -> a.startsWith(args[0])).collect(Collectors.toList());
            }
            return argList;
        }
        return null;
    }

    private boolean hasPermission(CommandSender sender, String permission) {
        if (!(sender instanceof Player)) {
            return true;
        } else {
            return sender.hasPermission(permission);
        }
    }
}
