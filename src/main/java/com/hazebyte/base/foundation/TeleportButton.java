package com.hazebyte.base.foundation;

import com.hazebyte.base.Button;
import com.hazebyte.base.util.ItemBuilder;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * Represents a button that will teleport the player upon click.
 */
public class TeleportButton extends Button {

    private static final ItemStack DEFAULT = new ItemBuilder(Material.BOOKSHELF)
            .displayName("&fTeleport!")
            .asItemStack();

    public TeleportButton(Location location) {
        this(DEFAULT, location);
    }

    /**
     * Creates a button with a item icon.
     *
     * @param item
     * @throws NullPointerException if the item is null
     */
    public TeleportButton(ItemStack item, Location location) {
        super(item);

        setAction(event -> event.getEntity().teleport(location));
    }
}
