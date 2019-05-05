package com.hazebyte.base;

import com.google.common.base.Preconditions;
import com.hazebyte.base.event.ButtonClickEvent;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.function.Consumer;

/**
 * A button representing an icon with actions.
 */
public class Button extends Component {

    private final ItemStack original;
    private ItemStack item;
    private final String DEFAULT = "default";

    /**
     * Creates a button with a item icon.
     *
     * @throws NullPointerException if the item is null
     */
    public Button(ItemStack item) {
        Preconditions.checkNotNull(item);
        this.original = item;
        this.item = item.clone();
    }

    public void runDefault(ButtonClickEvent bce) {
        run(DEFAULT, bce);
    }

    public void run(String action, ButtonClickEvent bce) {
        Consumer<ButtonClickEvent> consumer = this.getProperty(action);
        if (consumer != null) consumer.accept(bce);
    }

    /**
     * Sets the default action.
     *
     * @param consumer
     */
    public void setAction(Consumer<ButtonClickEvent> consumer) {
        setProperty(DEFAULT, consumer);
    }

    /**
     * Sets an action for a specific state. This supports all click types.
     * See {@link org.bukkit.event.inventory.ClickType}
     *
     * @throws NullPointerException if action is null.
     *
     * @param action
     * @param consumer
     */
    public void setAction(String action, Consumer<ButtonClickEvent> consumer) {
        Preconditions.checkNotNull(action, "Action provided is null");
        setProperty(action, consumer);
    }

    public ItemStack getItem() {
        return this.item;
    }

    /**
     * @param entity
     * @param key
     */
    @Override
    protected void onUpdate(HumanEntity entity, String key) {
        Base base = this.getProperty("menu");
        if (base == null) return;

        ItemMeta meta = original.clone().getItemMeta();

        if (key.equals("%name")) {
            String value = base.getState(key, this.getState(key, "Missing %name value"));
            meta.setDisplayName(value);
        }
        if (key.equals("%lore")) {
            List<String> arr = base.getState(key, this.getState(key, Arrays.asList("Missing %lore value")));
            meta.setLore(arr);
        }
        if (key.equals("%amount")) {
            int amount = base.getState(key, this.getState(key, 1));
            item.setAmount(amount);
        }
        if (key.equals("%material")) {
            Material material = Material.matchMaterial(base.getState(key, this.getState(key, "dirt")));
            item.setType(material);
        }

        for (Map.Entry<String, Object> entry : states.entrySet()) {
            replace(meta, entry);
        }
        for (Map.Entry<String, Object> entry : base.states.entrySet()) {
            replace(meta, entry);
        }
        item.setItemMeta(meta);
    }

    private void replace(ItemMeta meta, Map.Entry<String, Object> entry) {
        String K = "%".concat(entry.getKey());
        String V = entry.getValue().toString();
        if (meta.getDisplayName().contains(K)) {
            meta.setDisplayName(meta.getDisplayName().replaceAll(K, V));
        }
        if (meta.getLore() != null) {
            ListIterator<String> iter = meta.getLore().listIterator();
            while (iter != null && iter.hasNext()) {
                String lore = iter.next();
                if (lore.contains(K)) {
                    iter.set(lore.replaceAll(K, V));
                }
            }
        }
    }
}
