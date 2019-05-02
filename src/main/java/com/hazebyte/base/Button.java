package com.hazebyte.base;

import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

public class Button extends Component {

    private ItemStack item;
    private final String DEFAULT = "default";

    public Button(ItemStack item) {
        this.item = item;
    }

    public void runDefault(ButtonClickEvent bce) {
        run(DEFAULT, bce);
    }

    public void run(String action, ButtonClickEvent bce) {
        Consumer<ButtonClickEvent> consumer = this.getProperty(action);
        if (consumer != null) consumer.accept(bce);
    }

    public void setAction(Consumer<ButtonClickEvent> consumer) {
        setProperty(DEFAULT, consumer);
    }

    public void setAction(String action, Consumer<ButtonClickEvent> consumer) {
        setProperty(action, consumer);
    }

    public ItemStack getItem() {
        return this.item;
    }

    @Override
    protected void onUpdate(String state) {

    }
}
