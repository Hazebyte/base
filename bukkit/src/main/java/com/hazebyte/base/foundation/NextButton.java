package com.hazebyte.base.foundation;

import com.hazebyte.base.Button;
import com.hazebyte.base.util.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * Represents a button that goes to the next page.
 */
public class NextButton extends Button {

    private static final ItemStack DEFAULT = new ItemBuilder(Material.ARROW)
            .displayName("&l&6Next Page")
            .lore("", "&eClick here to go to the next page.")
            .asItemStack();

    public NextButton() {
        this(DEFAULT);
    }

    public NextButton(ItemStack item) {
        super(item);
        setAction(event -> event.getOrigin().nextPage(event.getEntity()));
    }

}
