package com.javarush.task.task31.task3102;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

/* 
Находим все файлы
https://habr.com/ru/post/437694/
*/

public class Solution {
    public static List<String> getFileTree(String root) throws IOException {
        SearchFileVisitor searchFileVisitor = new SearchFileVisitor();
        Files.walkFileTree(Paths.get(root), searchFileVisitor);

        return searchFileVisitor.getFoundFiles();
    }

    public static void main(String[] args) throws IOException {
        String folder = "/Users/mikepol/IdeaProjects/JavaRushTasks/3.JavaMultithreading/src/com/javarush/task/task36/task3608";
        List<String> fileList = getFileTree(folder);
        for (String each: fileList) {
            System.out.println(each);
        }
    }
}


