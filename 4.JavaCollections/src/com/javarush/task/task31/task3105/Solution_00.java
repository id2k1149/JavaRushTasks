package com.javarush.task.task31.task3105;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* 
Добавление файла в архив
*/

public class Solution_00 {
    public static void main(String[] args) throws IOException {
        String path_to_file1 = "/Users/mikepol/IdeaProjects/JavaRushTasks/4.JavaCollections/src/com/javarush/task/task31/task3105/a.txt";
        String path_to_file2 = "/Users/mikepol/IdeaProjects/JavaRushTasks/4.JavaCollections/src/com/javarush/task/task31/task3105/b.txt";

        File path_to_zip = new File("/Users/mikepol/IdeaProjects/JavaRushTasks/4.JavaCollections/src/com/javarush/task/task31/task3105/test.zip");

        // создаем архив
        FileOutputStream outputStream = new FileOutputStream(path_to_zip);
        ZipOutputStream zip = new ZipOutputStream(outputStream);

        ZipEntry entry1 = new ZipEntry("a.txt");
        //кладем в архив ZipEntry – «архивный объект»
        zip.putNextEntry(entry1);

        //копируем файл «document-for-archive.txt» в архив под именем «document.txt»
        File file1 = new File(path_to_file1);
        Files.copy(file1.toPath(), zip);

        ZipEntry entry2 = new ZipEntry("b.txt");
        //кладем в архив ZipEntry – «архивный объект»
        zip.putNextEntry(entry2);

        //копируем файл «document-for-archive.txt» в архив под именем «document.txt»
        File file2 = new File(path_to_file2);
        Files.copy(file2.toPath(), zip);

        // закрываем архив
        zip.close();



//        ZipInputStream zis = new ZipInputStream(new FileInputStream(path_to_zip));
//        ZipEntry zipEntry1;
//        while ((zipEntry1 = zis.getNextEntry()) != null) {
//            System.out.println(zipEntry1.getName());
//            System.out.println(zipEntry1.getSize());
//        }
//        zis.close();
//
//        ZipFile zipFile = new ZipFile(path_to_zip);
//        Enumeration zipEnum = zipFile.entries();
//        while (zipEnum.hasMoreElements()) {
//            ZipEntry zipEntry2 = (ZipEntry) zipEnum.nextElement();
//            System.out.println(zipEntry2.getSize());
//        }



    }
}
