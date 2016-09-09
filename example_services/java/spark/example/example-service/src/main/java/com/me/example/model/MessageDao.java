package com.me.example.model;

import java.util.Optional;

public interface MessageDao extends Dao<String, String> {
    Optional<String> get(String key);
    void create(String key, String value);
    void update(String key, String value);
    void delete(String key);
}
