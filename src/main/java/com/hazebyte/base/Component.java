package com.hazebyte.base;

import java.util.HashMap;
import java.util.Map;

public abstract class Component {

    private Map<String, Object> properties;
    private Map<String, Object> states;

    public Component() {
        this.properties = new HashMap<>();
        this.states = new HashMap<>();
    }

    public <T> T getProperty(String key) {
        return (T) properties.get(key);
    }

    public <T> T getProperty(String key, T defaultValue) {
        return properties.containsKey(key) ? (T) properties.get(key) : defaultValue;
    }

    public <T> T getState(String key) {
        return (T) states.get(key);
    }

    public <T> T getState(String key, T defaultValue) {
        return states.containsKey(key) ? (T) states.get(key) : defaultValue;
    }

    public Component setProperty(String key, Object value) {
        properties.put(key, value);
        return this;
    }

    public Component setState(String key, Object value) {
        Object original = getState(key);
        states.put(key, value);
        if (original == null || !original.equals(value)) { // If state has not yet been set or has been changed
            Lib.debug(String.format("State Update %s", key));
            onUpdate(key);
        }
        return this;
    }

    protected abstract void onUpdate(String state);
}
