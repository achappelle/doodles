package com.me.itu.example.persistence;

public interface MessageDao extends Dao<String, String> {
    String get(String key);
    void create(String key, String value);
    void update(String key, String value);
    void delete(String key);
}
