/* Author: uhFinn
 * Latest edit: uhFinn
 * */
package uhfinn.tags.Listeners;

import me.rayzr522.jsonmessage.JSONMessage;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import uhfinn.tags.Main;
import uhfinn.tags.Modules.TextProcess;
import uhfinn.tags.Modules.YML.KeyData;
import uhfinn.tags.Modules.YML.YmlHandler;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class UpdateChecker implements Listener
{

    FileConfiguration config = YmlHandler.getConfig();

    //Assigning values if non existent
    private String spigotVersion = "0.0";
    @EventHandler
    public void PlayerJoin(PlayerJoinEvent event) {
        Bukkit.getScheduler().runTaskAsynchronously(Main.INSTANCE(), () -> {
            Player p = event.getPlayer();
            if(p.isOp()) {
                int ID = 0; //todo
                try {
                    final HttpsURLConnection con = (HttpsURLConnection) new URL("https://api.spigotmc.org/legacy/update.php?resource=" + ID).openConnection();
                    con.setRequestMethod("GET");
                    spigotVersion = new BufferedReader(new InputStreamReader(con.getInputStream())).readLine();
                } catch(final IOException error) {
                    if(config.getBoolean("PrintErrorsToConsole")) error.printStackTrace();
                }

                if(!spigotVersion.equals(Main.getPlugin(Main.class).getDescription().getVersion())) {
                    JSONMessage message = JSONMessage.create(TextProcess.ColorProcess( "&fDownload it now at: &6&nhttps://www.spigotmc.org/resources/" + ID))
                            .openURL("https://www.spigotmc.org/resources/" + ID)
                            .tooltip("Click to go to:\nhttps://www.spigotmc.org/resources/" + ID);


                    Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(Main.class), () -> {
                        p.sendMessage(TextProcess.ColorProcess(KeyData.getPluginPrefix() + " &fDailyRewards+ Version " + spigotVersion + " has been released!"));
                        message.send(p);
                        p.playSound(p.getLocation(), Sound.BLOCK_BELL_USE, 5, 1);
                    }, 400L);
                }
            }
        });
    }
}