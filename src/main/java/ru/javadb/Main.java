package ru.javadb;

import ru.javadb.utils.FileUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException {
        byte[] bytes = "ДомРаботаУлица".getBytes(StandardCharsets.UTF_8);
        Path filePath = Paths.get("test.txt");
        Files.write(filePath, bytes);
        String read = FileUtils.read(filePath, 3, 6);
        System.out.println(read);
    }
}
