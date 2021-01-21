package com.javarush.task.task31.task3110;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;

public class Archiver {

    public static void main(String[] args) throws Exception {

        // полный путь архива
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Enter path to archive");
//        String path_to_archive = reader.readLine();
        String path_to_archive = "/Users/mikepol/IdeaProjects/JavaRushTasks/3.JavaMultithreading/src/com/javarush/task/task31/task3110/test.zip";
        ZipFileManager zipFileManager = new ZipFileManager(Paths.get(path_to_archive));

        System.out.println("Enter path to file");
//        String path_to_file = reader.readLine();
        String path_to_file = "/Users/mikepol/IdeaProjects/JavaRushTasks/3.JavaMultithreading/src/com/javarush/task/task31/task3110/a3110.txt";


        zipFileManager.createZip(Paths.get(path_to_file));
    }
}
