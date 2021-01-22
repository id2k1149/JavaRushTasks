package com.javarush.task.task31.task3106;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.zip.ZipInputStream;

/* 
Разархивируем файл
*/

public class Solution_01 {
    public static void main(String[] args) throws IOException {
//        String resultFileName = args[0];
        String resultFileName = "/Users/mikepol/IdeaProjects/JavaRushTasks/4.JavaCollections/src/com/javarush/task/task31/task3106/result.txt";

        String[] zip_parts =
            {"/Users/mikepol/IdeaProjects/JavaRushTasks/4.JavaCollections/src/com/javarush/task/task31/task3106/3.zip",
            "/Users/mikepol/IdeaProjects/JavaRushTasks/4.JavaCollections/src/com/javarush/task/task31/task3106/1.zip",
            "/Users/mikepol/IdeaProjects/JavaRushTasks/4.JavaCollections/src/com/javarush/task/task31/task3106/4.zip",
            "/Users/mikepol/IdeaProjects/JavaRushTasks/4.JavaCollections/src/com/javarush/task/task31/task3106/2.zip"};

        List<String> files = new ArrayList<>();
        Collections.addAll(files, zip_parts);
        files.remove(files.get(0));
        Collections.sort(files);


        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        for (String s : files) {
            FileInputStream fileInputStream = new FileInputStream(s);

            byte[] b = new byte[fileInputStream.available()];
            fileInputStream.read(b);
            byteArray.write(b);
            fileInputStream.close();
        }

        ZipInputStream in = new ZipInputStream(new ByteArrayInputStream(byteArray.toByteArray()));
        FileOutputStream out = new FileOutputStream(resultFileName);

        if (in.getNextEntry() != null) {
            int len;
            byte[] buffer = new byte[2048];
            while ((len = in.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
            out.close();
        }
        in.close();

    }
}
