package net.elytrapvp.levels.api;

import net.elytrapvp.levels.MySQL;
import net.elytrapvp.levels.api.events.ExpGainEvent;
import net.elytrapvp.levels.api.events.LevelUpEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Represents a player with stored level and experience data.
 *
 * @author  partykid4
 * @version 1.0
 * @since   2020-09-20
 */
public class LevelsPlayer {
    private UUID uuid;
    private int level;
    private int experience;

    /**
     * Create a LevelsPlayer object with UUID.
     * @param uuid The player's uuid.
     */
    public LevelsPlayer(UUID uuid) {
        this.uuid = uuid;

        if(LevelsAPI.getLevelsPlayers().containsKey(uuid)) {
            LevelsPlayer lp = LevelsAPI.getLevelsPlayers().get(uuid);
            this.level = lp.getLevel();
            this.experience = lp.getExperience();
            LevelsAPI.getLevelsPlayers().put(uuid, this);
        }
        else {
            Bukkit.getScheduler().runTaskAsynchronously(LevelsAPI.getPlugin(), () -> {

                if(exists()) {
                    try {
                        PreparedStatement statement = MySQL.getConnection().prepareStatement("SELECT * from players WHERE uuid = ?");
                        statement.setString(1,uuid.toString());
                        ResultSet results = statement.executeQuery();
                        results.next();

                        level = results.getInt(4);
                        experience = results.getInt(5);
                    }
                    catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    level = 1;
                    experience = 0;

                    try {
                        PreparedStatement statement = MySQL.getConnection().prepareStatement("INSERT INTO players (uuid,username) VALUES (?,?)");
                        statement.setString(1, uuid.toString());
                        statement.setString(2, Bukkit.getPlayer(uuid).getName());
                        statement.executeUpdate();
                    }
                    catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if(level == 0) {
                    setLevel(1);
                }
            });
        }
    }

    /**
     * Create a LevelsPlayer object with a Player object.
     * @param p Player object.
     */
    public LevelsPlayer(Player p) {
        new LevelsPlayer(p.getUniqueId());
    }

    /**
     * Add experience to the player.
     * @param experience Experience to add.
     */
    public void addExperience(int experience) {
        setExperience(getExperience() + experience);

        Bukkit.getPluginManager().callEvent(new ExpGainEvent(this, experience));

        if(getExperience() >= LevelsAPI.getRequiredExp(getLevel())) {
            setExperience(getExperience() -  LevelsAPI.getRequiredExp(getLevel()));
            setLevel(getLevel() + 1);
            Bukkit.getPluginManager().callEvent(new LevelUpEvent(this, getLevel()));
        }
    }

    /**
     * Check whether or not a player exists in the database.
     * @return Whether or not they exist in the database.
     */
    public boolean exists() {
        try {
            PreparedStatement statement = MySQL.getConnection().prepareStatement("SELECT * from players WHERE uuid = ?");
            statement.setString(1,uuid.toString());
            ResultSet results = statement.executeQuery();

            return results.next();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Get the player's current experience.
     * @return Experience
     */
    public int getExperience() {
        return experience;
    }

    /**
     * Get the player's current level.
     * @return Level
     */
    public int getLevel() {
        return level;
    }

    /**
     * Get the UUID of the player.
     * @return UUID of player.
     */
    public UUID getUniqueID() {
        return uuid;
    }

    /**
     * Set the player's experience.
     * @param experience Experience to set.
     */
    private void setExperience(int experience) {
        this.experience = experience;
        try {
            PreparedStatement statement = MySQL.getConnection().prepareStatement("UPDATE players SET experience = ? WHERE uuid = ?");
            statement.setInt(1, experience);
            statement.setString(2, uuid.toString());
            statement.executeUpdate();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Set the player's level.
     * @param level Level to set.
     */
    private void setLevel(int level) {
        this.level = level;

        try {
            PreparedStatement statement = MySQL.getConnection().prepareStatement("UPDATE players SET level = ? WHERE uuid = ?");
            statement.setInt(1, level);
            statement.setString(2, uuid.toString());
            statement.executeUpdate();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }

}
