package com.me.example.model;

import java.util.Optional;

public interface Dao<K,V> {
    Optional<V> get(K key);
    void create(K key, V value);
    void update(K key, V value);
    void delete(K key);
}
