package com.hazebyte.base.event;

import com.hazebyte.base.Component;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Represents a state that will be removed.
 */
public class StateRemoveEvent extends Event implements Cancellable {

    private Component component;

    private String key;

    private String oldValue;

    private HumanEntity entity;

    private boolean cancelled = false;

    private static final HandlerList handlerList = new HandlerList();

    public StateRemoveEvent(Component component, String key, String oldValue, HumanEntity entity) {
        this.component = component;
        this.key = key;
        this.oldValue = oldValue;
        this.entity = entity;
    }

    public Component getComponent() {
        return component;
    }

    public String getKey() {
        return key;
    }

    public String getOldValue() {
        return oldValue;
    }

    public HumanEntity getEntity() {
        return entity;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = true;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }
}
