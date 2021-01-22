package com.javarush.task.task31.task3106;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

//https://metanit.com/java/tutorial/6.12.php

public class Solution_00 {

    public static void main(String[] args) throws IOException {
        String source_1 = "/Users/mikepol/IdeaProjects/JavaRushTasks/4.JavaCollections/src/com/javarush/task/task31/task3106/1.txt";
        String source_2 = "/Users/mikepol/IdeaProjects/JavaRushTasks/4.JavaCollections/src/com/javarush/task/task31/task3106/2.txt";
        String source_3 = "/Users/mikepol/IdeaProjects/JavaRushTasks/4.JavaCollections/src/com/javarush/task/task31/task3106/3.txt";
        String source_4 = "/Users/mikepol/IdeaProjects/JavaRushTasks/4.JavaCollections/src/com/javarush/task/task31/task3106/4.txt";

        Path[] source = {Paths.get(source_1), Paths.get(source_2), Paths.get(source_3), Paths.get(source_4)};

        String zip_1 = "/Users/mikepol/IdeaProjects/JavaRushTasks/4.JavaCollections/src/com/javarush/task/task31/task3106/1.zip";
        String zip_2 = "/Users/mikepol/IdeaProjects/JavaRushTasks/4.JavaCollections/src/com/javarush/task/task31/task3106/2.zip";
        String zip_3 = "/Users/mikepol/IdeaProjects/JavaRushTasks/4.JavaCollections/src/com/javarush/task/task31/task3106/3.zip";
        String zip_4 = "/Users/mikepol/IdeaProjects/JavaRushTasks/4.JavaCollections/src/com/javarush/task/task31/task3106/4.zip";

        Path[] zip = {Paths.get(zip_1), Paths.get(zip_2), Paths.get(zip_3), Paths.get(zip_4)};

        for (int i = 0; i < source.length; i++) {

            try (ZipOutputStream zipOutputStream = new ZipOutputStream(Files.newOutputStream(zip[i]));
                 InputStream inputStream = Files.newInputStream(source[i]))
            {
                ZipEntry zipEntry = new ZipEntry(source[i].getFileName().toString());
                zipOutputStream.putNextEntry(zipEntry);
                while(inputStream.available() > 0) {
                    zipOutputStream.write(inputStream.read());
                }
                zipOutputStream.closeEntry();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
