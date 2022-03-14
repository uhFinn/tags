package uhfinn.tags.Modules.PAPI;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class Placeholders extends PlaceholderExpansion {


    private final Plugin plugin;

    public Placeholders(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getAuthor() {
        return "ItsUhFinn"; //I usually go by 'uhFinn' but I for some reason made this my spigot name so it kind of has to be here
    }

    @Override
    public String getIdentifier() {
        return "tags";
    }

    @Override
    public String getVersion() {
        return "1.0.0";
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public String onRequest(OfflinePlayer offlinePlayer, String params) {
        Player p = offlinePlayer.getPlayer();

        if(params.equals("active")) {

        }
        if(params.equals("active_id")) {

        }
        if(params.equals("active_description")) {

        }
        if(params.startsWith("tag")) {
            if(params.contains("_")) {
                String[] args = params.split("_");
                if(args.length == 3) {

                } else return null;
            }
        }
        if(params.equals("available")) {

        }
        if(params.startsWith("hastag_")) {
            String tagID = params.split("hastag_")[1];

        }

        return null;
    }


}
