package net.elytrapvp.levels.listeners;

import net.elytrapvp.levels.api.LevelsAPI;
import net.elytrapvp.levels.api.LevelsPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        LevelsPlayer lp = new LevelsPlayer(p.getUniqueId());
        LevelsAPI.getLevelsPlayers().put(p.getUniqueId(), lp);
    }
}
