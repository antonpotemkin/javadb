package ru.javadb.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MemTable<V> {
    private final Map<String, V> cache = new ConcurrentHashMap<>();

    public V get(String key) {
        return cache.get(key);
    }
    
    public boolean contains(String key) {
        return cache.containsKey(key);
    }

    public void put(String key, V value) {
        cache.put(key, value);
    }
}
