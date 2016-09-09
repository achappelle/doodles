package com.me.example.persistence;

import java.util.Optional;

public interface Dao<K,V> {
    abstract public Optional<V> get(K key);
    abstract public void set(K key, V data);
    abstract public void remove(K key);
    abstract public void update(K key, V value);
}
