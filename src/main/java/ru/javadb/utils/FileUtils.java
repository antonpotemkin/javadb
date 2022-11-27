package ru.javadb.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public final class FileUtils {
    private FileUtils(){}

    public static void deleteDir(Path root) {
        try {
            if (Files.isDirectory(root)) {
                Files.list(root).forEach(FileUtils::deleteDir);
            }
            Files.delete(root);
        } catch (IOException e) {
            System.out.println("error while delete file " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
