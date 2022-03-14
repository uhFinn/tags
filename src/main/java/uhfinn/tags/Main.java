package uhfinn.tags;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class Main extends JavaPlugin {

    private static Main _instance;
    public static Main INSTANCE()
    {
        return _instance;
    }
    Logger logger = Bukkit.getLogger();

    @Override
    public void onEnable() {
        _instance=this;
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    // adds a listener.
    public void addListener(Listener listener)
    {
        Bukkit.getPluginManager().registerEvents(listener, this);
    }
}
