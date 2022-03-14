package uhfinn.tags.Modules.YML;

import uhfinn.tags.Modules.TextProcess;

public class KeyData {

    public static String getPluginPrefix() {
        return TextProcess.ColorProcess(YmlHandler.getConfig().getString("PluginPrefix"));
    }
    public static boolean getLogErrors() {
        return YmlHandler.getConfig().getBoolean("PrintErrorsToConsole");
    }

}
