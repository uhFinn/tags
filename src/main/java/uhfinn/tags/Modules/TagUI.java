package uhfinn.tags.Modules;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import uhfinn.tags.Modules.YML.PlayerData;
import uhfinn.tags.Modules.YML.YmlHandler;

import java.util.ArrayList;
import java.util.List;

public class TagUI implements InventoryHolder {

    private Inventory inv;

    Player p;

    int avTags;
    int rows;
    int pages;

    public TagUI(Player p) {
        this.avTags = TagGrabber.getAmountAvailableTags(p);
        this.rows = (int) Math.ceil(this.avTags / 7f);
        this.pages = (int) Math.ceil(this.rows / 4f);
        this.p = p;

        int tempRows = this.rows;
        if(tempRows > 4) tempRows = 4;

        init(0);
    }

    private void init(int page) {
        FileConfiguration config = YmlHandler.getConfig();
        String activeTag = PlayerData.getActiveTag(this.p);

        List<String> pageTags = TagGrabber.getPageAvailableTags(this.p, page);
        int rows = (int) Math.ceil(pageTags.size()/7f);

        this.inv = Bukkit.createInventory(this, (rows+2)*9, TextProcess.PapiProcess(config.getString("GUI.name"), this.p));
        List<Integer> borderNums = getBorderNumbers(rows+2);
        for(Integer num : borderNums) {
            this.inv.setItem(num, getBorder());
        }


    }

    @Override
    public Inventory getInventory() {
        return inv;
    }




    private List<Integer> getBorderNumbers(int rows) {
        List<Integer> indexes = new ArrayList<>();
        for(int i = 0; i < 9; i++) {
            indexes.add(i);
            indexes.add((rows*9)-i-1);
        }
        for(int i = 0; i < rows; i++) {
            if(i != 0 && i != rows-1) {
                indexes.add((i*9));
                indexes.add((i*9)+8);
            }
        }
        return indexes;
    }
    private ItemStack getBorder() {
        ConfigurationSection borderConfig = YmlHandler.getConfig().getConfigurationSection("GUI.GUI Border");
        ItemStack border = new ItemStack(Parse.parseMaterial(borderConfig.getString("icon")), 1);
        ItemMeta borderMeta = border.getItemMeta();
        if(borderConfig.contains("lore")) borderMeta.setLore(borderConfig.getStringList("lore"));
        if(borderConfig.contains("name")) {
            borderMeta.setDisplayName(borderConfig.getString("name"));
        } else borderMeta.setDisplayName(" ");
        if(borderConfig.contains("enchanted")) {
            if(borderConfig.getBoolean("enchanted")) {
                borderMeta.addEnchant(Enchantment.OXYGEN, 100, true);
                borderMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            }
        }
        border.setItemMeta(borderMeta);
        return border;
    }
    private ItemStack getNextPage() { //todo
        ConfigurationSection nextConfig = YmlHandler.getConfig().getConfigurationSection("GUI.GUI Border");
        ItemStack border = new ItemStack(Parse.parseMaterial(borderConfig.getString("icon")), 1);
        ItemMeta borderMeta = border.getItemMeta();
        if(borderConfig.contains("lore")) borderMeta.setLore(borderConfig.getStringList("lore"));
        if(borderConfig.contains("name")) {
            borderMeta.setDisplayName(borderConfig.getString("name"));
        } else borderMeta.setDisplayName(" ");
        if(borderConfig.contains("enchanted")) {
            if(borderConfig.getBoolean("enchanted")) {
                borderMeta.addEnchant(Enchantment.OXYGEN, 100, true);
                borderMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            }
        }
        border.setItemMeta(borderMeta);
        return border;
    }

}
