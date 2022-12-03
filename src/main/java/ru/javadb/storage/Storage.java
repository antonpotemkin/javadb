package ru.javadb.storage;

public interface Storage {
    String get(String key);
    void put(String key, String value);
}
