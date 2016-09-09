package com.me.itu.example.persistence;

public interface Dao<K,V> {
    V get(K key);
    void create(K key, V value);
    void update(K key, V value);
    void delete(K key);
}
