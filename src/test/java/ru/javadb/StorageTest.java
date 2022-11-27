package ru.javadb;

import org.junit.jupiter.api.*;
import ru.javadb.utils.FileUtils;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StorageTest {
    private static final String PATH = "./test";
    private static final Storage STORAGE = new Storage(PATH);

    @AfterAll
    public static void remove() {
        FileUtils.deleteDir(Path.of(PATH));
    }

    @Test
    @Order(1)
    public void putAndGetTest() {
        String key1 = "key1";
        String value1 = "value1";
        STORAGE.put(key1, value1);
        assertEquals(value1, STORAGE.get(key1));
    }

    @Test
    @Order(2)
    public void notExistKeyTest() {
        String key1 = "empty";
        assertNull(STORAGE.get(key1));
    }

    @Test
    @Order(3)
    public void updateValueAndGetTest() {
        String key1 = "key1";
        String value1 = "value1";
        String key2 = "key2";
        String value2 = "value2";
        String value3 = "value3";
        STORAGE.put(key1, value1);
        STORAGE.put(key2, value2);
        STORAGE.put(key1, value3);
        assertEquals(value2, STORAGE.get(key2));
        assertEquals(value3, STORAGE.get(key1));
    }

}