package net.elytrapvp.levels.api.events;

import net.elytrapvp.levels.api.LevelsPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class LevelUpEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();
    private int level;
    private Player player;
    private LevelsPlayer levelsPlayer;

    public LevelUpEvent(LevelsPlayer levelsPlayer, int level) {
        this.player = Bukkit.getPlayer(levelsPlayer.getUniqueID());
        this.levelsPlayer = levelsPlayer;
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public Player getPlayer() {
        return player;
    }

    public LevelsPlayer getLevelsPlayer() {
        return levelsPlayer;
    }

    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}