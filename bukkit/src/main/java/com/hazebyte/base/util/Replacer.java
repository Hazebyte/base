package com.hazebyte.base.util;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.List;

public class Replacer {
    public Replacer() {
    }

    public static String replace(String string) {
        string = string.replace("{prefix}", "Base").replace("{p}", "Base");
        string = string.replace("{aqua}", "&b").replace("{black}", "&0").replace("{blue}", "&9").replace("{dark_aqua}", "&3").replace("{dark_blue}", "&1").replace("{dark_grey}", "&8").replace("{dark_green}", "&2").replace("{dark_purple}", "&5").replace("{dark_red}", "&4").replace("{gold}", "&6").replace("{gray}", "&7").replace("{green}", "&a").replace("{light_purple}", "&d").replace("{red}", "&c").replace("{white}", "&f").replace("{yellow}", "&e").replace("{bold}", "&l").replace("{italic}", "&o").replace("{magic}", "&k").replace("{reset}", "&r").replace("{strike}", "&m").replace("{strikethrough}", "&m").replace("{underline}", "&n");
        string = replace(string, '&');
        return string;
    }

    public static String replace(String string, Player player) {
        return string.replace("{player}", player.getName()).replace("{playerUUID}", player.getUniqueId().toString());
    }

    public static String replace(String string, char color) {
        return ChatColor.translateAlternateColorCodes(color, string);
    }

    public static List<String> replace(List<String> strings) {
        for (int i = 0; i < strings.size(); ++i) {
            String string = (String) strings.get(i);
            strings.set(i, replace(string));
        }

        return strings;
    }
}

