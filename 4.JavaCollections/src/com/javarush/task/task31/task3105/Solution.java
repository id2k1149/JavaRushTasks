package com.javarush.task.task31.task3105;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* 
Добавление файла в архив
https://www.youtube.com/watch?v=aE_w3c-rnSo
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        String path_to_file = "/Users/mikepol/IdeaProjects/JavaRushTasks/4.JavaCollections/src/com/javarush/task/task31/task3105/result.mp3";
        File path_to_zip = new File("/Users/mikepol/IdeaProjects/JavaRushTasks/4.JavaCollections/src/com/javarush/task/task31/task3105/test.zip");

//        полный путь к файлу fileName.
//        String path_to_file = args[0];
//        путь к zip-архиву.
//        File path_to_zip = args[1];


//        Чтение архивов. ZipInputStream
        ZipInputStream zin = new ZipInputStream(new FileInputStream(path_to_zip));

        ZipEntry entry;
        String name;

        while((entry = zin.getNextEntry()) != null){

            name = entry.getName() + "_01"; // получим название файла
//            System.out.printf("File name: %s \t ", name);

            // распаковка

            String path = "/Users/mikepol/IdeaProjects/JavaRushTasks/4.JavaCollections/src/com/javarush/task/task31/task3105/";

            FileOutputStream fos = new FileOutputStream(path + name);
            while (zin.read() != -1) {
                fos.write(zin.read());
            }

            fos.flush();
            zin.closeEntry();
            fos.close();
        }
    }
}
