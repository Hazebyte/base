package com.hazebyte.base.foundation;

import com.hazebyte.base.Base;
import com.hazebyte.base.Button;
import com.hazebyte.base.util.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * Represents a button to go to a new menu
 */
public class NextMenuButton extends MenuButton {

    private static final ItemStack DEFAULT = new ItemBuilder(Material.ARROW)
            .displayName("&l&4Next Menu")
            .lore("", "&eClick here to go to the next menu.")
            .asItemStack();

    public NextMenuButton(Base target) {
        this(DEFAULT, target);
    }

    public NextMenuButton(ItemStack item, Base target) {
        super(item, target);
    }
}
