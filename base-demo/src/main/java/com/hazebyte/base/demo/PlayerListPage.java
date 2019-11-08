package com.hazebyte.base.demo;

import com.hazebyte.base.Base;
import com.hazebyte.base.Button;
import com.hazebyte.base.Size;
import com.hazebyte.base.util.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.Optional;

public class PlayerListPage extends Base implements Listener {

    /**
     * Creates a inventory that lists players
     */
    public PlayerListPage(JavaPlugin plugin, String title, Size size) {
        super(plugin, title, size);

        for (Player player: Bukkit.getOnlinePlayers()) {
            // Create a new item that shows the player name
            ItemStack item = new ItemBuilder(Material.SKULL_ITEM)
                    .displayName(player.getName())
                    .lore(
                            String.format("&fHealth: %s", Double.toString(player.getHealth()))
                    )
                    .asItemStack();

            // Create a new button with the item stack
            Button button = new Button(item);

            // Attach a state change listener
            // When we call #setState, this should update any variables
            this.attach(button);

            // Add the item to this menu
            // Handles pagination automatically!
            this.addIcon(button);
        }
    }

}
