package com.javarush.task.task31.task3106;

import java.io.*;
import java.util.Arrays;
import java.util.Vector;
import java.util.zip.ZipInputStream;

/* 
Разархивируем файл
*/

public class Solution_02 {
    public static void main(String[] args)  {
//        String resultFileName = args[0];
        String resultFileName = "/Users/mikepol/IdeaProjects/JavaRushTasks/4.JavaCollections/src/com/javarush/task/task31/task3106/result.txt";

        String[] zip_parts =
            {"/Users/mikepol/IdeaProjects/JavaRushTasks/4.JavaCollections/src/com/javarush/task/task31/task3106/3.zip",
            "/Users/mikepol/IdeaProjects/JavaRushTasks/4.JavaCollections/src/com/javarush/task/task31/task3106/1.zip",
            "/Users/mikepol/IdeaProjects/JavaRushTasks/4.JavaCollections/src/com/javarush/task/task31/task3106/4.zip",
            "/Users/mikepol/IdeaProjects/JavaRushTasks/4.JavaCollections/src/com/javarush/task/task31/task3106/2.zip"};

        Vector<FileInputStream> files = new Vector<>();
        Arrays.sort(zip_parts, 1, zip_parts.length);

        try {
            for(int i = 1; i < zip_parts.length; i++) {
                files.addElement(new FileInputStream(zip_parts[i]));
            }
        } catch (FileNotFoundException exc) {}

        try (ZipInputStream zip = new ZipInputStream(new SequenceInputStream(files.elements()));
             FileOutputStream fos = new FileOutputStream(resultFileName)) {
            byte[] buffer = new byte[2048];
            int len;
            while (zip.getNextEntry() != null) {
                while ((len = zip.read(buffer)) != -1) {
                    fos.write(buffer, 0, len);
                    fos.flush();
                }
                zip.closeEntry();
            }
        } catch (IOException exc) {}
    }
}
