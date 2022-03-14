package uhfinn.tags.Modules.YML;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import uhfinn.tags.Main;

import java.io.File;
import java.util.logging.Logger;

public class YmlHandler {

    private static final Logger logger = Bukkit.getLogger();

    private static FileConfiguration Config = YamlConfiguration.loadConfiguration(new File(Main.INSTANCE().getDataFolder() + "/Config.yml"));
    private static FileConfiguration Tags = YamlConfiguration.loadConfiguration(new File(Main.INSTANCE().getDataFolder() + "/Tags.yml"));

    public static FileConfiguration getConfig() {
        return Config;
    }
    public static FileConfiguration getTags() {
        return Tags;
    }

    public static void reload() {
        File Configur = new File(Main.INSTANCE().getDataFolder() + "/Config.yml");
        if(!Configur.exists()) {
            Main.getPlugin(Main.class).saveResource("Config.yml", false);
            logger.info("[Tags] Generated Config.yml File");
        }
        File Messageser = new File(Main.INSTANCE().getDataFolder() + "/Tags.yml");
        if(!Messageser.exists()) {
            Main.getPlugin(Main.class).saveResource("Tags.yml", false);
            logger.info("[Tags] Generated Tags.yml File");
        }

        Config = YamlConfiguration.loadConfiguration(new File(Main.INSTANCE().getDataFolder() + "/Config.yml"));
        Tags = YamlConfiguration.loadConfiguration(new File(Main.INSTANCE().getDataFolder() + "/Tags.yml"));
    }

}
