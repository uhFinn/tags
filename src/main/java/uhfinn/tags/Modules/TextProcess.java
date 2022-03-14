package uhfinn.tags.Modules;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import uhfinn.tags.Modules.PAPI.PapiReplacement;

public class TextProcess {

    /**
     * Converts an input string and player into its papi and color code translated form
     * @param input The raw input string
     * @param p The player to apply the PAPI operation to, if null no papi
     * @return The finalized string
     */
    public static String PapiProcess(String input, Player p) {
        input = ChatColor.translateAlternateColorCodes('&', input);
        if(p != null) input = PapiReplacement.ReplacePlaceholders(p, input);
        return input;
    }

    /**
     * Converts an input string into its colour code translated form
     * @param input The raw input string
     * @return The finalized string
     */
    public static String ColorProcess(String input) {
        return ChatColor.translateAlternateColorCodes('&', input);
    }


}
