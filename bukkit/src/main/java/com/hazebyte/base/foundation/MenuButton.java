package com.hazebyte.base.foundation;

import com.hazebyte.base.Base;
import com.hazebyte.base.Button;
import com.hazebyte.base.util.Lib;
import org.bukkit.inventory.ItemStack;

/**
 * Represents a button to go to a new menu
 */
public class MenuButton extends Button {

    public final String LAZY_RUN = "setupTarget";
    private Base target;

    public MenuButton(ItemStack item) {
        this(item, null);
    }

    public MenuButton(ItemStack item, Base where) {
        super(item);
        this.target = where;

        setAction(event -> {
            // Lazy load target
            run(LAZY_RUN, event);

            Base origin = event.getOrigin();
            Base target = getTarget();

            if (target == null) {
                Lib.info(event.getEntity().getName(), "Target is not setup");
                return;
            }

            origin.setChild(target);
            target.setParent(origin);

            target.open(event.getEntity());
        });
    }

    public void setTarget(Base where) {
        this.target = where;
    }

    public Base getTarget() {
        return target;
    }
}
