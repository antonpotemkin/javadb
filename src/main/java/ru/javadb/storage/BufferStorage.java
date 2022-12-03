package ru.javadb.storage;

import ru.javadb.cache.MemTable;
import ru.javadb.utils.FileUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.atomic.AtomicLong;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.StandardOpenOption.*;

public class BufferStorage implements Storage {

    private final Path path;
    private final MemTable<ValuePosition> memTable = new MemTable<>();
    private final AtomicLong position = new AtomicLong(0);
    private final String sep = ",";
    private final String nl = "\n";

    public BufferStorage(String path) {
        try {
            Path directory = Path.of(path);
            Files.createDirectories(directory);
            this.path = Path.of(path + "/storage");
        } catch (IOException e) {
            System.out.println("Error while createDirectories " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
    @Override
    public String get(String key) {
        boolean contains = memTable.contains(key);
        if (!contains) {
            return null;
        }
        ValuePosition vp = memTable.get(key);
        return FileUtils.read(path, vp.getOffset(), vp.getSize());
    }

    // todo sync write operation write, calculate offset, write to memTable;
    @Override
    public void put(String key, String value) {
        try {
            String put = key + "," + value + "\n";
            Files.writeString(path, put, UTF_8, CREATE, WRITE, APPEND);
            long lastPos = position.getAndUpdate(last -> last + sep.length() + key.length() + value.length() + nl.length());
            memTable.put(key, new ValuePosition(lastPos + key.length() + sep.length(), value.length()));
        } catch (IOException e) {
            System.out.println("Error while writeString " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
