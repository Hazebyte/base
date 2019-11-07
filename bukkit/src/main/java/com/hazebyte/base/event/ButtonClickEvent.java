package com.hazebyte.base.event;

import com.hazebyte.base.Base;
import com.hazebyte.base.Button;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.inventory.Inventory;

/**
 * Represents an event where a button was clicked.
 */
public class ButtonClickEvent extends Event implements Cancellable {

    private HumanEntity entity;
    private Base origin;
    private Button button;
    private InventoryAction action;
    private ClickType click;
    private int slot;
    private int rawSlot;
    private Inventory clickedInventory;
    private boolean cancelled;

    private static final HandlerList handlerList = new HandlerList();

    public ButtonClickEvent(Base base, Button button, HumanEntity entity, InventoryAction action, ClickType click, int slot, int rawSlot, Inventory clickedInventory) {
        this.origin = base;
        this.button = button;
        this.entity = entity;
        this.action = action;
        this.click = click;
        this.slot = slot;
        this.rawSlot = rawSlot;
        this.clickedInventory = clickedInventory;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }

    public HumanEntity getEntity() {
        return entity;
    }

    public Base getOrigin() {
        return origin;
    }

    public Button getButton() {
        return button;
    }

    public InventoryAction getAction() {
        return action;
    }

    public ClickType getClick() {
        return click;
    }

    public int getSlot() {
        return slot;
    }

    public int getRawSlot() {
        return rawSlot;
    }

    public Inventory getClickedInventory() {
        return clickedInventory;
    }
}
