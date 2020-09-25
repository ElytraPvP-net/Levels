package net.elytrapvp.levels.listeners;

import net.elytrapvp.levels.api.LevelsAPI;
import net.elytrapvp.levels.api.events.LevelUpEvent;
import net.elytrapvp.levels.utils.ChatUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class LevelUpListener implements Listener {
    @EventHandler
    public void onLevelUp(LevelUpEvent e) {
        Player p = e.getPlayer();

        ChatUtils.chat(p, "&8&m-----------------------------------------------------");
        ChatUtils.centeredChat(p,"&2&lLevel Up!");
        ChatUtils.chat(p, "");
        ChatUtils.centeredChat(p, "  &aYour level has increased to level &f" + e.getLevel() + "&a!");
        ChatUtils.chat(p, "");
        ChatUtils.centeredChat(p, "&7You need &a" + (LevelsAPI.getRequiredExp(e.getLevel()) - e.getLevelsPlayer().getExperience()) + " &7more experience to level up again.");
        ChatUtils.chat(p, "&8&m-----------------------------------------------------");
    }
}
