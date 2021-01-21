package com.javarush.task.task31.task3105;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* 
Добавление файла в архив
*/

public class Solution_03 {
    public static void main(String[] args) throws IOException {

        File path_to_zip = new File("/Users/mikepol/IdeaProjects/JavaRushTasks/4.JavaCollections/src/com/javarush/task/task31/task3105/test.zip");

        String path_to_temp = path_to_zip.getParent();

        ZipInputStream zis = new ZipInputStream(new FileInputStream(path_to_zip));


        ZipFile zipFile = new ZipFile(path_to_zip);
        Enumeration zipEnum = zipFile.entries();
        while (zipEnum.hasMoreElements()) {
            ZipEntry zipEntry2 = (ZipEntry) zipEnum.nextElement();
            System.out.println(zipEntry2.getName());
            System.out.println(zipEntry2.getSize());
        }
        zis.close();
    }
}
