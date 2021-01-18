package com.javarush.task.task31.task3112;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/* 
Загрузчик файлов
*/

public class Solution {

    public static void main(String[] args) throws IOException {

//        String destination = "/Users/mikepol/IdeaProjects/JavaRushTasks/4.JavaCollections/src/com/javarush/task/task31/task3112";
//        Path passwords = downloadFile("https://javarush.ru/testdata/secretPasswords.txt", Paths.get(destination));

        Path passwords = downloadFile("https://javarush.ru/testdata/secretPasswords.txt", Paths.get("D:/MyDownloads"));

        for (String line : Files.readAllLines(passwords)) {
            System.out.println(line);
        }
    }

    public static Path downloadFile(String urlString, Path downloadDirectory) throws IOException {
        // implement this method

        URL url = new URL(urlString);

        Path fileName = Paths.get(url.getFile()).getFileName();
        // java
//        String fileName = urlString.split("/")[urlString.split("/").length - 1];

        Path destination = downloadDirectory.resolve(fileName);

        InputStream inputStream = url.openStream();

        Path tempFile = Files.createTempFile("temp-",".tmp");
        // java
//        Path tempFile = Files.createTempFile(null, null);

        Files.copy(inputStream, tempFile, StandardCopyOption.REPLACE_EXISTING);

        return Files.move(tempFile, destination, StandardCopyOption.REPLACE_EXISTING);

        // java
//        Files.move(tempFile, destination);
//        return destination;

    }
}
