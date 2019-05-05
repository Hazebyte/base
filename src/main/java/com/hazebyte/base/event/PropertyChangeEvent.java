package com.hazebyte.base.event;

import com.hazebyte.base.Component;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Represents when a property will be changed.
 */
public class PropertyChangeEvent extends Event implements Cancellable {

    private Component origin;

    private String key;

    private Object newValue;

    private Object oldValue;

    private boolean cancelled = false;

    private static HandlerList handlerList = new HandlerList();

    public PropertyChangeEvent(Component origin, String key, Object newValue, Object oldValue) {
        this.origin = origin;
        this.key = key;
        this.newValue = newValue;
        this.oldValue = oldValue;
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

    public String getKey() {
        return key;
    }

    public Component getOrigin() {
        return origin;
    }

    public Object getNewValue() {
        return newValue;
    }

    public Object getOldValue() {
        return oldValue;
    }

}
