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

/* 
Добавление файла в архив
*/

public class Solution_04 {
    public static void main(String[] args) throws IOException {

        File path_to_zip = new File("/Users/mikepol/IdeaProjects/JavaRushTasks/4.JavaCollections/src/com/javarush/task/task31/task3105/test.zip");

        String path_to_temp = path_to_zip.getParent() + "/";
        System.out.println(path_to_temp);

        ZipInputStream zis = new ZipInputStream(new FileInputStream(path_to_zip));



        ZipFile zipFile = new ZipFile(path_to_zip);
        Enumeration zipEnum = zipFile.entries();
        while (zipEnum.hasMoreElements()) {
            ZipEntry zipEntry = (ZipEntry) zipEnum.nextElement();
            System.out.println(zipEntry.getName());
            System.out.println(zis.read());

            FileOutputStream outputStream = new FileOutputStream(path_to_temp + zipEntry.getName());
            while (zis.read() != -1){
                System.out.println(zis.read());
                outputStream.write(zis.read());
            }

            outputStream.flush();
            outputStream.close();

        }
        zis.close();
    }
}
