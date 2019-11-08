package com.hazebyte.base.demo;

import com.hazebyte.base.Base;
import com.hazebyte.base.Size;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Core extends JavaPlugin {

    private JavaPlugin plugin;

    @Override
    public void onEnable() {
        this.plugin = this;

        this.getCommand("base").setExecutor((sender, command, label, args) -> {
            if (sender instanceof Player) {
                if (args.length < 1) {
                    return false;
                } else {
                    Player player = (Player) sender;
                    String requested = args[0];

                    Base page = null;
                    if ("pagination".equalsIgnoreCase(requested)) {
                        page = new PaginationPage(plugin, "Pagination", Size.from(27));
                    } else if ("players".equalsIgnoreCase(requested)) {
                        page = new PlayerListPage(plugin, "Player List", Size.from(27));
                    } else if ("state".equalsIgnoreCase(requested)) {
                        page = new StateChangePage(plugin, "State", Size.from(27));
                    } else {
                        page = new TestPage(plugin, "Test");
                    }
                    page.open(player);
                    return true;
                }
            }
            return false;
        });

    }

    // rest of plugin logic
}
