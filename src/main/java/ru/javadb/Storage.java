package ru.javadb;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.StandardOpenOption.*;

public class Storage {
    private final Path path;

    public Storage(String path) {
        try {
            Path directory = Path.of(path);
            Files.createDirectories(directory);
            this.path = Path.of(path + "/storage");
        } catch (IOException e) {
            System.out.println("Error while createDirectories " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public String get(String key) {
        try {
            List<String> pairs = Files.readAllLines(path);
            for (int i = pairs.size() - 1; i >=0; i--) {
                String pair = pairs.get(i);
                int index = pair.indexOf(",");
                String pairKey = pair.substring(0, index);
                if (Objects.equals(key, pairKey)) {
                    return pair.substring(index + 1);
                }
            }
            return null;
        } catch (IOException e) {
            System.out.println("Error while readString " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void put(String key, String value) {
        try {
            String put = key + "," + value + "\n";
            Files.writeString(path, put, UTF_8, CREATE, WRITE, APPEND);
        } catch (IOException e) {
            System.out.println("Error while writeString " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
