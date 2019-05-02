package com.hazebyte.base;

import org.bukkit.entity.HumanEntity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.inventory.Inventory;

public class ButtonClickEvent extends Event implements Cancellable {

    private HumanEntity entity;
    private Base origin;
    private InventoryAction action;
    private ClickType click;
    private int slot;
    private int rawSlot;
    private Inventory clickedInventory;
    private boolean cancelled;

    private static HandlerList handlerList = new HandlerList();

    public ButtonClickEvent(Base base, Button button, HumanEntity entity, InventoryAction action, ClickType click, int slot, int rawSlot, Inventory clickedInventory) {
        this.origin = base;
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

    public HumanEntity getEntity() {
        return entity;
    }

    public Base getOrigin() {
        return origin;
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
