package ru.javadb.storage;

import org.junit.jupiter.api.*;
import ru.javadb.cache.MemTable;
import ru.javadb.utils.FileUtils;

import java.lang.reflect.Field;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StorageTest {
    private static final String PATH = "./test";
    private static final Storage STORAGE = new Storage(PATH);
    private final String key1 = "key1";
    private final String key2 = "key2";
    private final String value1 = "value1";

    @AfterAll
    public static void remove() {
        FileUtils.deleteDir(Path.of(PATH));
    }

    @Test
    @Order(1)
    public void putAndGetTest() {
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
        STORAGE.put(key1, value1);
        String value2 = "value2";
        STORAGE.put(key2, value2);
        String value3 = "value3";
        STORAGE.put(key1, value3);
        assertEquals(value2, STORAGE.get(key2));
        assertEquals(value3, STORAGE.get(key1));
    }

    @Test
    @Order(4)
    public void brokenCacheTest() throws NoSuchFieldException, IllegalAccessException {
        Field declaredField = STORAGE.getClass().getDeclaredField("memTable");
        declaredField.setAccessible(true);
        MemTable brokenCache = new MemTable();
        brokenCache.put(key2, 1);
        declaredField.set(STORAGE, brokenCache);
        assertThrows(RuntimeException.class, () -> STORAGE.get(key2));
        assertNull(STORAGE.get(key1));
    }

}