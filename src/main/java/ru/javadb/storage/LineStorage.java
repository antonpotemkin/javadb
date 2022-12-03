package ru.javadb.storage;

import ru.javadb.cache.MemTable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.StandardOpenOption.*;

public class LineStorage implements Storage {
    private final Path path;
    private final MemTable<Integer> memTable = new MemTable<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    public LineStorage(String path) {
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
        try {
            List<String> pairs = Files.readAllLines(path);
            Integer lineNumber = memTable.get(key);
            String pair = pairs.get(lineNumber);
            int index = pair.indexOf(",");
            String pairKey = pair.substring(0, index);
            if (Objects.equals(key, pairKey)) {
                return pair.substring(index + 1);
            } else {
                throw new RuntimeException("Key isn't equals");
            }
        } catch (IOException e) {
            System.out.println("Error while readString " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void put(String key, String value) {
        try {
            String put = key + "," + value + "\n";
            Files.writeString(path, put, UTF_8, CREATE, WRITE, APPEND);
            memTable.put(key, counter.getAndIncrement());
        } catch (IOException e) {
            System.out.println("Error while writeString " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
