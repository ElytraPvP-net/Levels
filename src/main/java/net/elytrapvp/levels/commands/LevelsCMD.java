package net.elytrapvp.levels.commands;

import net.elytrapvp.levels.api.LevelsAPI;
import net.elytrapvp.levels.api.LevelsPlayer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LevelsCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!sender.hasPermission("levels.admin")) {
            return true;
        }

        if(args.length != 3) {
            return true;
        }

        switch (args[0]) {
            case "addexp":
                Player p = Bukkit.getPlayer(args[1]);

                if(p == null) {
                    return true;
                }

                int exp = Integer.parseInt(args[2]);

                LevelsPlayer lp = LevelsAPI.getLevelsPlayers().get(p.getUniqueId());
                lp.addExperience(exp);

                break;
        }

        return true;
    }
}
