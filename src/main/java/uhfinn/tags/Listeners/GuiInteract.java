package uhfinn.tags.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import uhfinn.tags.Modules.TagUI;

public class GuiInteract implements Listener {

    @EventHandler
    public void onGuiClick(InventoryClickEvent event) {
        if (event.getClickedInventory() == null) { return; }
        if (event.getClickedInventory().getHolder() instanceof TagUI) {
            event.setCancelled(true);
            Player p = (Player) event.getWhoClicked();
            if (event.getCurrentItem() == null) { return; }


        }
    }

}
