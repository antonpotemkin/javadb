package ru.javadb.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.CharBuffer;
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

    public static String read(Path path, long offset, int size) {
        try {
            BufferedReader bufferedReader = Files.newBufferedReader(path);
            long skip = bufferedReader.skip(offset);
            if (offset != skip) {
                System.out.println("error while skip offset");
                throw new RuntimeException("error while skip offset");
            }
            CharBuffer buffer = CharBuffer.allocate(size);
            int read = bufferedReader.read(buffer);
            if (read != size) {
                System.out.println("error while read offset");
                throw new RuntimeException("error while read offset");
            }
            buffer.flip();
            return buffer.toString();
        } catch (IOException e) {
            System.out.println("error while read file " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
