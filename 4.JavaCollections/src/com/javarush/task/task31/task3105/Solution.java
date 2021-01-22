package com.javarush.task.task31.task3105;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
        String path_to_file = "/Users/mikepol/IdeaProjects/JavaRushTasks/4.JavaCollections/src/com/javarush/task/task31/task3105/result.txt";
        File path_to_zip = new File("/Users/mikepol/IdeaProjects/JavaRushTasks/4.JavaCollections/src/com/javarush/task/task31/task3105/test.zip");

//        полный путь к файлу fileName.
//        String path_to_file = args[0];
//        путь к zip-архиву.
//        File path_to_zip = args[1];


//        Чтение архивов. ZipInputStream
        ZipInputStream inputStream = new ZipInputStream(new FileInputStream(path_to_zip));

        ZipEntry entry;
        String name;

        while((entry = inputStream.getNextEntry()) != null){

            name = entry.getName() + "_01"; // получим название файла
//            System.out.printf("File name: %s \t ", name);


            Path tempFile = Files.createTempFile("temp-",".tmp");



//            String path = "/Users/mikepol/IdeaProjects/JavaRushTasks/4.JavaCollections/src/com/javarush/task/task31/task3105/";

//            FileOutputStream outputStream = new FileOutputStream(path + name);
            while (inputStream.read() != -1) {
                Files.copy(inputStream, tempFile, StandardCopyOption.REPLACE_EXISTING);
            }

            while (inputStream.read() != -1) {
                Files.copy(inputStream, Paths.get(path_to_file));
            }


            inputStream.closeEntry();

            Path file00 = tempFile;
            for (String line : Files.readAllLines(file00)) {
                System.out.println(line);
            }

            // создаем архив
            FileOutputStream outputStream = new FileOutputStream(path_to_zip);
            ZipOutputStream zip = new ZipOutputStream(outputStream);

            ZipEntry entry1 = new ZipEntry(tempFile.getFileName().toString());
            //кладем в архив ZipEntry – «архивный объект»
            zip.putNextEntry(entry1);

            //копируем файл «document-for-archive.txt» в архив под именем «document.txt»
            File file1 = new File(tempFile.toAbsolutePath().toString());
            Files.copy(file1.toPath(), zip);



            // закрываем архив
            zip.close();




        }
    }
}
