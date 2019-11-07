package com.hazebyte.base.foundation;

import com.hazebyte.base.Button;
import com.hazebyte.base.util.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * Representing a button that closes the inventory.
 */
public class PageButton extends Button {

    private final ItemStack DEFAULT;

    public PageButton(ItemStack item, int page) {
        super(item);

        DEFAULT = new ItemBuilder(Material.CAULDRON)
                .displayName("&l&fChange Page")
                .lore("", "&eClick here to go to page &f" + page)
                .asItemStack();
        setAction(event -> event.getOrigin().changePage(event.getEntity(), page));
    }

}
