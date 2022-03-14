package uhfinn.tags.Modules.YML;

import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import uhfinn.tags.Main;

import java.io.File;
import java.io.IOException;

public class PlayerData {

    /**
     * Gets a players user data
     * @param op The {@link OfflinePlayer} of the queried user
     * @return The {@link FileConfiguration} user data
     */
    public static FileConfiguration getPlayer(OfflinePlayer op) {
        String UUID = op.getUniqueId().toString();

        File playerFile = new File(Main.INSTANCE().getDataFolder() + "/PlayerData/" + UUID + ".yml");
        if(playerFile.exists()) {
            return YamlConfiguration.loadConfiguration(playerFile);
        } else {
            return genPlayer(op);
        }
    }

    /**
     * Generates a players' user data file
     * @param op The {@link OfflinePlayer} of the queried user
     * @return The {@link FileConfiguration} user data that has been generated
     */
    public static FileConfiguration genPlayer(OfflinePlayer op) {
        String UUID = op.getUniqueId().toString();

        File playerFile = new File(Main.INSTANCE().getDataFolder() + "/PlayerData/" + UUID + ".yml");
        if(!playerFile.exists()) {
            try {
                playerFile.createNewFile();
                FileConfiguration userFile = getPlayer(op);
                userFile.set("A", "");
                userFile.save(playerFile);
                return userFile;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    /**
     * Gets a players active tag ID
     * @param op The {@link OfflinePlayer} of the queried user
     * @return The active String tagID of the queried user
     */
    public static String getActiveTag(OfflinePlayer op) {
        return getPlayer(op).getString("A");
    }

}
