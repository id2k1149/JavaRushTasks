package com.javarush.task.task31.task3113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/* 
Что внутри папки?
*/

public class Solution extends SimpleFileVisitor<Path> {
    private int filesCount;
    private int dirCount;
    private int bytesCount;


    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        dirCount ++;
        return super.preVisitDirectory(dir, attrs);
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

        filesCount ++;
        byte[] content = Files.readAllBytes(file);
        bytesCount += content.length;

        return super.visitFile(file, attrs);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String path_to_folder = reader.readLine();

        while (true) {
//            String path_to_folder = "/Users/mikepol/IdeaProjects/JavaRushTasks/4.JavaCollections/src/com/javarush/task/task31/task3113";
            Path pathName = Paths.get(path_to_folder);

            if (!Files.isDirectory(pathName)) {
                System.out.println(pathName + " - не папка");
                break;
            }

            final Solution solution = new Solution();
            Files.walkFileTree(Paths.get(path_to_folder), solution);

            System.out.println("Всего папок - " + (solution.dirCount - 1));

            System.out.println("Всего файлов - " + solution.filesCount);

            System.out.println("Общий размер - " + solution.bytesCount);

            break;
        }
    }
}
