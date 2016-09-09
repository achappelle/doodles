package com.me.example.model;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class MessageMemoryDao implements MessageDao {
    private ConcurrentHashMap<String, String> messages = new ConcurrentHashMap<>();

    public Optional<String> get(String key) {
        return Optional.ofNullable(key).map(k -> Optional.ofNullable(messages.get(k)))
                .orElseGet(Optional::empty);
    }

    public void create(String key, String value) {
        messages.put(key, value);
    }

    public void update(String key, String value) {
        messages.put(key, value);
    }

    public void delete(String key) {
        messages.remove(key);
    }
}
