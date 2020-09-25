package net.elytrapvp.levels.api;

import net.elytrapvp.levels.Levels;

import java.util.*;

public class LevelsAPI {
    static Levels plugin;
    private static Map<UUID, LevelsPlayer> levelsPlayers = new HashMap<>();

    public LevelsAPI(Levels plugin) {
        LevelsAPI.plugin = plugin;
    }

    static Levels getPlugin() {
        return plugin;
    }

    public static Map<UUID, LevelsPlayer> getLevelsPlayers() {
        return levelsPlayers;
    }

    public static int getRequiredExp(int level) {
        return level * 2500;
    }
}
