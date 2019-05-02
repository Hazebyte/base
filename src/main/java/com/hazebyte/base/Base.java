package com.hazebyte.base;

import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 */
public abstract class Base extends Component implements InventoryHolder {

    protected JavaPlugin plugin;
    private final Size size;
    private final String title;
    private final List<Button[]> buttons;

    public Base(JavaPlugin plugin, String title, Size size) {
        this.plugin = plugin;
        this.size = size;
        this.title = title;
        this.buttons = new ArrayList<>();
        this.buttons.add(new Button[size.toInt()]);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (title == null ? 0 : title.hashCode());
        hash = 31 * hash + (size == null ? 0 : size.hashCode());
        hash = 31 * hash + buttons.size();
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Base)) {
            return false;
        }

        Base base = (Base) object;

        if (!(base.getTitle().equals(this.getTitle()))) {
            return false;
        }
        if (!(base.getSize().equals(this.getSize())) {
            return false;
        }
        if (!(this.buttons.size() == base.buttons.size()) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Menu{");
        builder.append("title:" + title).append("slots:" + size).append("}");
        return builder.toString();
    }

    public String getTitle() {
        return title;
    }

    public Size getSize() {
        return size;
    }

    @Override
    public Inventory getInventory() {
        return Bukkit.createInventory(this, size.toInt(), title);
    }

    public void open(HumanEntity entity) {
        open(entity, 0);
    }

    public void open(HumanEntity entity, int page) {
        if (!(InventoryListener.getInstance().registered())) {
            InventoryListener.getInstance().register(plugin);
        }
        Inventory inventory = Bukkit.createInventory(this, size.toInt(), title);
        this.setProperty(entity.getUniqueId().toString(), page);
        Button[] barr = buttons.get(page);
        for (int i = 0; i < barr.length; i++) {
            if (barr[i] != null) inventory.setItem(i, barr[i].getItem());
        }
        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            entity.openInventory(inventory);
//            startTask(); TODO handle tasks
        }, 3);
    }

    public void nextPage(HumanEntity entity) {
        int page = this.getProperty(entity.getUniqueId().toString(), 0) + 1;
        changePage(entity, page);
    }

    public void previousPage(HumanEntity entity) {
        int page = this.getProperty(entity.getUniqueId().toString(), 0) - 1;
        changePage(entity, page);
    }

    private void changePage(HumanEntity entity, int page) {
        if (entity.getOpenInventory().getTopInventory().getHolder() instanceof Base
                && (page < buttons.size() && page >= 0)) {
            this.setProperty(entity.getUniqueId().toString(), page);
            Inventory inventory = entity.getOpenInventory().getTopInventory();
            Button[] barr = buttons.get(page);
            for (int i = 0; i < barr.length; i++) {
                if (barr[i] != null) inventory.setItem(i, barr[i].getItem());
                else inventory.setItem(i, null);
            }
        }
    }

    public boolean close(HumanEntity entity) {
        if (entity.getOpenInventory().getTopInventory().getHolder() instanceof Base) {
            Base origin = (Base) entity.getOpenInventory().getTopInventory().getHolder();
            if (this.equals(origin)) {
                Bukkit.getScheduler().runTaskLater(plugin, entity::closeInventory, 1);
                return true;
            }
        }
        return false;
    }

    public void setIcon(int slot, Button button) {
        setIcon(0, slot, button);
    }

    public void setIcon(int page, int slot, Button button) {
        while (page >= buttons.size()) {
            buttons.add(new Button[size.toInt()]);
        }
        Button[] barr = buttons.get(page);
        barr[slot] = button;
    }

    public Optional<Button> getIcon(int page, int slot) {
        if (page < buttons.size()) {
            return Optional.ofNullable(buttons.get(page)[slot]);
        }
        return null;
    }

    public void onInventoryClick(InventoryClickEvent event) {
        int slot = event.getRawSlot();
        int page = this.getProperty(event.getWhoClicked().getUniqueId().toString(), 0);
        getIcon(page, slot).ifPresent((button) -> {
            ButtonClickEvent bce = new ButtonClickEvent(
                    this,
                    button,
                    event.getWhoClicked(),
                    event.getAction(),
                    event.getClick(),
                    event.getSlot(),
                    event.getRawSlot(),
                    event.getClickedInventory()
            );
            Bukkit.getPluginManager().callEvent(bce);
            if (!bce.isCancelled()) {
                button.run(event.getClick().name(), bce); // Run the action specific to the click
                button.runDefault(bce); // Run the default action
            }
        });
    }

    /**
     * Override this to specify what you want the inventory to do
     * on every tick.
     *
     * @param entity
     */
    public void update(HumanEntity entity) {
    }

    /**
     * When a player calls setState, this should handle and
     * update all items with the same key to the new value.
     * <p>
     * Newly opened inventories and currently opened inventories
     * should all have the updated state
     *
     * @param key
     */
    @Override
    protected void onUpdate(String key) {

    }
}
