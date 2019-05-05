package com.hazebyte.example;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Core extends JavaPlugin {

    private JavaPlugin plugin;

    @Override
    public void onEnable() {
        this.plugin = this;
        this.getCommand("base").setExecutor((sender, command, label, args) -> {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                TestPage page = new TestPage(plugin, "Test Page");
                page.open(player);
                return true;
            }
            return false;
        });
    }

    // rest of plugin logic
}
