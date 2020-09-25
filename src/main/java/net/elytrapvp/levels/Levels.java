package net.elytrapvp.levels;

import net.elytrapvp.levels.api.LevelsAPI;
import net.elytrapvp.levels.commands.LevelsCMD;
import net.elytrapvp.levels.listeners.ExpGainListener;
import net.elytrapvp.levels.listeners.LevelUpListener;
import net.elytrapvp.levels.listeners.PlayerJoinListener;
import net.elytrapvp.levels.listeners.PlayerQuitListener;
import org.bukkit.plugin.java.JavaPlugin;

public class Levels extends JavaPlugin {

    @Override
    public void onEnable() {
        MySQL.openConnection();

        new LevelsAPI(this);

        getServer().getPluginManager().registerEvents(new ExpGainListener(), this);
        getServer().getPluginManager().registerEvents(new LevelUpListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerQuitListener(), this);

        getCommand("levels").setExecutor(new LevelsCMD());
    }

    @Override
    public void onDisable() {

    }
}
