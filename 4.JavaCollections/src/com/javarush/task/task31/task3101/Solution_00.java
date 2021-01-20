package com.javarush.task.task31.task3101;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;


/* 
Проход по дереву файлов
https://javarush.ru/groups/posts/2275-files-path
Убедись, что в файл allFilesContent.txt не записываются лишние данные.
*/

public class Solution_00 {
    public static void main(String[] args) throws IOException {
//        String path = args[0];
//        String resultFileAbsolutePath.txt = args[1];

        String path = "/Users/mikepol/IdeaProjects/JavaRushTasks/4.JavaCollections/src/com/javarush/task/task31/task3101";
        String resultFileAbsolutePath = "/Users/mikepol/IdeaProjects/JavaRushTasks/4.JavaCollections/src/com/javarush/task/task31/task3101/resultFileAbsolutePath.txt";

        File source = new File(resultFileAbsolutePath);
        File destination = new File(source.getParent() + "/allFilesContent.txt");

        if (FileUtils.isExist(source)) {
            FileUtils.renameFile(source, destination);
        }

        SearchFileVisitor searchFileVisitor = new SearchFileVisitor();
        Files.walkFileTree(Paths.get(path), searchFileVisitor);

        for (Map.Entry entry: searchFileVisitor.foundFiles.entrySet()) {
            System.out.println(entry.getValue());
        }

        try(FileWriter fileWriter = new FileWriter(destination);){
            for (Map.Entry entry: searchFileVisitor.foundFiles.entrySet()) {
                fileWriter.write(entry.getValue().toString());
            }
        }
        catch(IOException e){
            // handle the exception
        }

    }

    static class SearchFileVisitor extends SimpleFileVisitor<Path> {
        public TreeMap<String, String> foundFiles = new TreeMap<>();

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException, IOException {

            // check if file name contains search string
            if (!file.toString().endsWith(".txt")) return FileVisitResult.CONTINUE;

            byte[] content = Files.readAllBytes(file);
            String contentString = new String(content);

            if(content.length <= 50 && content.length > 0) {
                foundFiles.put(file.getFileName().toString(), contentString + "\n");
            }

            return FileVisitResult.CONTINUE;
        }
    }
}


