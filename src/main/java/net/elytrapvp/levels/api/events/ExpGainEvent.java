package net.elytrapvp.levels.api.events;

import net.elytrapvp.levels.api.LevelsPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ExpGainEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();
    private int expGained;
    private Player player;
    private LevelsPlayer levelsPlayer;

    public ExpGainEvent(LevelsPlayer levelsPlayer, int expGained) {
        this.player = Bukkit.getPlayer(levelsPlayer.getUniqueID());
        this.levelsPlayer = levelsPlayer;
        this.expGained = expGained;
    }

    public int getExpGained() {
        return expGained;
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
