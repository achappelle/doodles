package com.me.itu.example.persistence;

import java.util.concurrent.ConcurrentHashMap;

public class MessageMemoryDao implements MessageDao {
    private ConcurrentHashMap<String, String> messages = new ConcurrentHashMap<>();

    public String get(String key) {
        return messages.get(key);
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
