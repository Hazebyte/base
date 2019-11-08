package com.hazebyte.base;

import com.hazebyte.base.event.PropertyChangeEvent;
import com.hazebyte.base.event.PropertyRemoveEvent;
import com.hazebyte.base.event.StateChangeEvent;
import com.hazebyte.base.event.StateRemoveEvent;
import com.hazebyte.base.util.Lib;
import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;

import java.util.HashMap;
import java.util.Map;

public abstract class Component {

    protected Map<String, Object> properties;
    protected Map<String, Object> states;

    public Component() {
        this.properties = new HashMap<>();
        this.states = new HashMap<>();
    }

    public <T> T getProperty(String key) {
        return getProperty(key, null);
    }

    public <T> T getProperty(String key, T defaultValue) {
        return properties.containsKey(key) ? (T) properties.get(key) : defaultValue;
    }

    public <T> T getState(String key) {
        return getState(key, null);
    }

    public <T> T getState(String key, T defaultValue) {
        return states.containsKey(key) ? (T) states.get(key) : defaultValue;
    }

    public Component setProperty(String key, Object value) {
        PropertyChangeEvent event = new PropertyChangeEvent(this, key, value, getProperty(key));
        Bukkit.getPluginManager().callEvent(event);
        if (!event.isCancelled()) {
            properties.put(key, value);
        }
        return this;
    }

    public Component setState(HumanEntity entity, String key, Object value) {
        Object original = getState(key);
        if (original == null || !original.equals(value)) {
            StateChangeEvent event = new StateChangeEvent(this, key, value, original, entity);
            Bukkit.getPluginManager().callEvent(event);
            if (!event.isCancelled()) {
                Lib.debug(String.format("State Update %s:%s", key, value));
                states.put(key, value);
                onUpdate(entity, key);
            }
        }
        return this;
    }

    public Object removeProperty(String key) {
        PropertyRemoveEvent event = new PropertyRemoveEvent(this, key, getProperty(key));
        Bukkit.getPluginManager().callEvent(event);
        if (!event.isCancelled()) {
            properties.remove(key);
        }
        return this;
    }

    // todo entity is not being used atm
    public Component removeState(HumanEntity entity, String key) {
        StateRemoveEvent event = new StateRemoveEvent(this, key, getState(key), entity);
        Bukkit.getPluginManager().callEvent(event);
        if (!event.isCancelled()) {
            states.remove(key);
        }
        return this;
    }


    protected abstract void onUpdate(HumanEntity entity, String state);
}
