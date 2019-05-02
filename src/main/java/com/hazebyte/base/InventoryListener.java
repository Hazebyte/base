package com.hazebyte.base;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.inventory.InventoryView;
import org.bukkit.plugin.java.JavaPlugin;

public class InventoryListener implements Listener {

    private JavaPlugin plugin;
    private boolean registered = false;
    private static InventoryListener INST = new InventoryListener();

    public static InventoryListener getInstance() {
        return INST;
    }

    protected void register(JavaPlugin plugin) {
        this.plugin = plugin;
        registered = true;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    protected boolean registered() {
        return registered;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getWhoClicked() instanceof Player && event.getInventory().getHolder() instanceof Base) {
            Base base = ((Base) event.getInventory().getHolder());
            event.setCancelled(true);
            base.onInventoryClick(event);
        }
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onDisable(PluginDisableEvent event) {
        if (event.getPlugin().equals(plugin)) {
            plugin = null;
            for (Player player : Bukkit.getOnlinePlayers()) {
                InventoryView view = player.getOpenInventory();
                if (view != null && view.getTopInventory().getHolder() != null
                        && view.getTopInventory().getHolder() instanceof Base) {
                    player.closeInventory();
                }
            }
        }
    }
}
