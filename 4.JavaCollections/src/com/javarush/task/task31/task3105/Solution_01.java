package com.javarush.task.task31.task3105;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/* 
Добавление файла в архив
*/

public class Solution_01 {
    public static void main(String[] args) throws IOException {
        String path_to_file = "/Users/mikepol/IdeaProjects/JavaRushTasks/4.JavaCollections/src/com/javarush/task/task31/task3105/hello.txt";
        File path_to_zip = new File("/Users/mikepol/IdeaProjects/JavaRushTasks/4.JavaCollections/src/com/javarush/task/task31/task3105/test01.zip");

        // создаем архив
        FileOutputStream outputStream = new FileOutputStream(path_to_zip);
        ZipOutputStream zip = new ZipOutputStream(outputStream);

        ZipEntry entry = new ZipEntry("hello.txt");
        //кладем в архив ZipEntry – «архивный объект»
        zip.putNextEntry(entry);

        FileInputStream inputStream = new FileInputStream(path_to_file);
        byte[] buffer = new byte[inputStream.available()];
        inputStream.read(buffer);
        zip.write(buffer);
        zip.closeEntry();

        // закрываем архив
        zip.close();

    }
}
