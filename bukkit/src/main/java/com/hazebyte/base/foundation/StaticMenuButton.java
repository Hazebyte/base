package com.hazebyte.base.foundation;

import com.hazebyte.base.Base;
import org.bukkit.inventory.ItemStack;

/**
 * Represents a button to go to the next menu.
 * This menu will not change parent child relationships.
 */
public class StaticMenuButton extends MenuButton {
    public StaticMenuButton(ItemStack item) {
        this(item, null);
    }

    public StaticMenuButton(ItemStack item, Base where) {
        super(item, where);
        setAction(event -> {
            Base target = getTarget();
            if (target != null) {
                run(LAZY_RUN, event);
                target.open(event.getEntity());
            }
        });
    }
}
