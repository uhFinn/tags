package uhfinn.tags.Modules.PAPI;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;

public class PapiReplacement {

    public static boolean enabled = false;

    /**
     * Converts all PAPI placeholders within a string into their value using PAPI API
     * @param p The player of which to obtain the PAPI variables for
     * @param str The raw input string
     * @return The finalized string
     */
    public static String ReplacePlaceholders(Player p, String str) {
        if(enabled){
            return PlaceholderAPI.setPlaceholders(p, str);
        } else return str;
    }

}
