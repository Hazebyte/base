package com.hazebyte.base.foundation;

import com.hazebyte.base.Button;
import com.hazebyte.base.util.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class CloseButton extends Button {

    private static final ItemStack DEFAULT = new ItemBuilder(Material.matchMaterial("RECORD_12", true))
            .displayName("&l&4Close Button")
            .lore("", "&eClick here to close the inventory.")
            .asItemStack();

    public CloseButton() {
        this(DEFAULT);
    }

    public CloseButton(ItemStack item) {
        super(item);
        setAction(event -> event.getOrigin().close(event.getEntity()));
    }

}
