package com.hazebyte.base;

import com.google.common.base.Preconditions;
import com.hazebyte.base.event.ButtonClickEvent;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;
import java.util.function.Consumer;

/**
 * A button representing an icon with actions.
 */
public class Button extends Component {

    private final ItemStack original;
    private ItemStack item;
    private final String DEFAULT = "default";

    // Dirty Writes
    private final Set<String> dirtyWrites = new HashSet<>();

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
     * @param state
     */
    @Override
    protected void onUpdate(HumanEntity entity, String state) {
        Base base = this.getProperty("menu");
        if (base == null) return;

        // Add dirty keys
        Object tmp = base.getState(state, this.getState(state));
        if (tmp == null) {
            dirtyWrites.remove(state);
        } else {
            dirtyWrites.add(state);
        }

        ItemMeta meta = original.clone().getItemMeta();

        for (String K: dirtyWrites) {
            System.out.println(K);
            if (K.equals("%name")) {
                String value = base.getState(K, this.getState(K, "Missing %name value"));
                meta.setDisplayName(value);
            }
            if (K.equals("%lore")) {
                List<String> arr = base.getState(K, this.getState(K, Arrays.asList("Missing %lore value")));
                meta.setLore(arr);
            }
            if (K.equals("%amount")) {
                int amount = base.getState(K, this.getState(K, 1));
                item.setAmount(amount);
            }
            if (K.equals("%material")) {
                Material material = Material.matchMaterial(base.getState(K, this.getState(K, "dirt")));
                item.setType(material);
            }

            for (Map.Entry<String, Object> entry : states.entrySet()) {
                replace(meta, entry);
            }
            for (Map.Entry<String, Object> entry : base.states.entrySet()) {
                replace(meta, entry);
            }
        }
        System.out.println(meta);
        item.setItemMeta(meta);

    }

    private void replace(ItemMeta meta, Map.Entry<String, Object> entry) {
        String K = "%".concat(entry.getKey());
        String V = entry.getValue().toString();
        if (meta.getDisplayName().contains(K)) {
            meta.setDisplayName(meta.getDisplayName().replaceAll(K, V));
        }
        if (meta.getLore() != null) {
            List<String> list = meta.getLore();
            ListIterator<String> iter = list.listIterator();
            while (iter != null && iter.hasNext()) {
                String lore = iter.next();
                if (lore.contains(K)) {
                    String replaced = lore.replaceAll(K, V);
                    iter.set(replaced);
                }
            }
            meta.setLore(list);
        }
    }
}
