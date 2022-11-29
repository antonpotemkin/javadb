package ru.javadb.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MemTable {
    private final Map<String, Integer> cache = new ConcurrentHashMap<>();

    public Integer get(String key) {
        return cache.get(key);
    }
    
    public boolean contains(String key) {
        return cache.containsKey(key);
    }

    public void put(String key, int value) {
        cache.put(key, value);
    }
}
