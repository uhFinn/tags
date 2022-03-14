package uhfinn.tags.Modules;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import uhfinn.tags.Modules.YML.KeyData;

import java.util.Locale;
import java.util.logging.Logger;

public class Parse {

    private static Logger logger = Bukkit.getLogger();

    public static Material parseMaterial(String input) {
        Material mat;
        try {
            mat = Material.valueOf(input.toUpperCase(Locale.ROOT));
            if(input.equals("") || input.equals(" ") || input.equalsIgnoreCase("air")) throw new Exception("Invalid Item Exception");
        } catch(Exception e) {
            if(KeyData.getLogErrors()) {
                logger.info("[Tags] Attempted to parse invalid material from '" + input + "'\n       Error handling set material to Barrier block");
            }
            mat = Material.BARRIER;
        }
        return mat;
    }

}
