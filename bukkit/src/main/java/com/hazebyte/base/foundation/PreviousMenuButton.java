package com.hazebyte.base.foundation;

import com.hazebyte.base.Base;
import com.hazebyte.base.util.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * Represents a button to go to a new menu
 */
public class PreviousMenuButton extends MenuButton {

    private static final ItemStack DEFAULT = new ItemBuilder(Material.ARROW)
            .displayName("&l&4Previous Menu")
            .lore("", "&eClick here to go to the previous menu.")
            .asItemStack();

    public PreviousMenuButton(Base target) {
        this(DEFAULT, target);
    }

    public PreviousMenuButton(ItemStack item, Base target) {
        super(item, target);
    }
}
