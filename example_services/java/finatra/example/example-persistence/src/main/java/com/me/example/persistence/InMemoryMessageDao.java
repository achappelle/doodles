package com.me.example.persistence;

import javax.inject.Singleton;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Singleton
public class InMemoryMessageDao implements MessageDao {
    ConcurrentHashMap<String, String> model = new ConcurrentHashMap<>();

    @Override
    public Optional<String> get(String key) {
        return Optional.ofNullable(model.get(key));
    }

    @Override
    public void set(String key, String value) {
        model.put(key, value);
    }

    @Override
    public void remove(String key) {
        model.remove(key);
    }

    @Override
    public void update(String key, String value) {
        model.put(key, value);
    }
}
