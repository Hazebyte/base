package com.hazebyte.base.foundation;

import com.hazebyte.base.Button;
import com.hazebyte.base.util.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class PreviousButton extends Button {

    private static final ItemStack DEFAULT = new ItemBuilder(Material.matchMaterial("FENCE", true))
            .displayName("&l&6Previous Page")
            .lore("", "&eClick here to go to the previous page.")
            .asItemStack();

    public PreviousButton() {
        this(DEFAULT);
    }

    public PreviousButton(ItemStack item) {
        super(item);
        setAction(event -> event.getOrigin().previousPage(event.getEntity()));
    }

}
