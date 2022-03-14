package uhfinn.tags.Modules;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import uhfinn.tags.Modules.YML.YmlHandler;

import java.util.*;

public class TagGrabber {

    /**
     * Scans the Tags.yml file for any non-duplicate tags
     * @return The integer sum of all tags
     */
    public static Integer getTotal() {
        List<String> tagIDs = new ArrayList<>();
        FileConfiguration tagConf = YmlHandler.getTags();
        Set<String> tags = tagConf.getKeys(false);

        for(String tag : tags) {
            if(!tagIDs.contains(tag)) tagIDs.add(tag);
        }
        return tagIDs.size();
    }


    /**
     * Gets the total number of available tags for a user
     * @param p The {@link Player} of whom to calculate the number of available tags
     * @return The integer sum of all available tags
     */
    public static Integer getAmountAvailableTags(Player p) {
        int available = 0;
        List<String> tagIDs = new ArrayList<>();
        FileConfiguration tagConf = YmlHandler.getTags();
        Set<String> tags = tagConf.getKeys(false);

        for(String tag : tags) {
            if(!tagIDs.contains(tag) && (p.hasPermission("tags.tag." + tag) || p.hasPermission("tags.tag.*") || p.isOp())) {
                available++;
                tagIDs.add(tag);
            }
        }
        return available;
    }


    /**
     * Gets the string list of all TagID's available to a player
     * @param p The {@link Player} to accumulate the list of available tags from
     * @return The {@link List<String>} of available tags to this Player
     */
    public static List<String> getListAvailableTags(Player p) {
        List<String> tagIDs = new ArrayList<>();
        FileConfiguration tagConf = YmlHandler.getTags();
        Set<String> tags = tagConf.getKeys(false);

        for(String tag : tags) {
            if(!tagIDs.contains(tag) && (p.hasPermission("tags.tag." + tag) || p.hasPermission("tags.tag.*") || p.isOp())) tagIDs.add(tag);
        }
        return tagIDs;
    }


    /**
     * Gets the players available tags within that GUI page while sorting tags based on their priority
     * @param p The {@link Player} to accumulate the list of available tags from
     * @param page The page of tags to gather from
     * @return The {@link List<String>} of available tags to this Player for the specified page
     */
    public static List<String> getPageAvailableTags(Player p, int page) {
        FileConfiguration Tags = YmlHandler.getTags();
        List<String> tagIDs = getListAvailableTags(p); //lpp 28
        LinkedHashMap<String, Integer> tagsOrder = new LinkedHashMap<>();
        for(String tag : tagIDs) {
            tagsOrder.put(tag, Tags.getInt(tag + ".priority"));
        }

        List<String> sortedTags = new ArrayList<>();
        tagsOrder.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .forEach(entry -> sortedTags.add(entry.getKey())); //todo idk if this works https://stackoverflow.com/questions/12184378/sorting-linkedhashmap

        int tagsLength = sortedTags.size();
        int pagesAvailable = (int) Math.ceil(tagsLength/28f);
        if(page > pagesAvailable) page = pagesAvailable;

        List<String> finalTagList = new ArrayList<>();
        for(int i = page*28; i<(page+1)*28; i++) {
            if(i < tagsLength) {
                finalTagList.add(sortedTags.get(i));
            }
        }
        return finalTagList;
    }


    /**
     * Gets the specified tagID's data
     * @param tagID The string ID of the tag
     * @return The {@link ConfigurationSection} of the tag ID
     */
    public static ConfigurationSection getTagData(String tagID) {
        return YmlHandler.getTags().getConfigurationSection(tagID);
    }

}
