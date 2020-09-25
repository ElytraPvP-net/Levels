package net.elytrapvp.levels.listeners;

import net.elytrapvp.levels.api.events.ExpGainEvent;
import net.elytrapvp.levels.utils.ChatUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ExpGainListener implements Listener {
    @EventHandler
    public void onExpGain(ExpGainEvent e) {
        Player p = e.getPlayer();

        //ChatUtils.chat(p, "&3+" + e.getExpGained() + " Experience");
    }
}
