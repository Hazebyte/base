package com.hazebyte.base.event;

import com.hazebyte.base.Component;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Represents a state that will be changed.
 */
public class StateChangeEvent extends Event implements Cancellable {

    private Component origin;

    private String key;

    private Object oldValue;

    private Object newValue;

    private HumanEntity entity;

    private boolean cancelled = false;

    private static final HandlerList handlerList = new HandlerList();

    public StateChangeEvent(Component origin, String key, Object newValue, Object oldValue, HumanEntity entity) {
        this.origin = origin;
        this.key = key;
        this.oldValue = oldValue;
        this.newValue = newValue;
        this.entity = entity;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

    public Component getOrigin() {
        return origin;
    }

    public String getKey() {
        return key;
    }

    public Object getOldValue() {
        return oldValue;
    }

    public Object getNewValue() {
        return newValue;
    }

    public HumanEntity getEntity() {
        return entity;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }
}
