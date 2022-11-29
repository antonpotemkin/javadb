package ru.javadb;

import ru.javadb.storage.Storage;

public class Main {
    public static void main(String[] args) {
        Storage storage = new Storage(".");
        storage.put("key1", "value1");
        storage.put("key2", "value2");
        storage.put("key1", "value3");
        System.out.println(storage.get("key1"));
    }
}
