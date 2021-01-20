package com.javarush.task.task31.task3101;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Collections;


/* 
Проход по дереву файлов
https://javarush.ru/groups/posts/2275-files-path
Убедись, что в файл allFilesContent.txt не записываются лишние данные.
*/

public class Solution_02 {
    public static void main(String[] args) throws IOException {

//        String path = "/Users/mikepol/IdeaProjects/JavaRushTasks/4.JavaCollections/src/com/javarush/task/task31/task3101";
//        String resultFileAbsolutePath.txt = "/Users/mikepol/IdeaProjects/JavaRushTasks/4.JavaCollections/src/com/javarush/task/task31/task3101/resultFileAbsolutePath.txt";

        String path = args[0];
        String resultFileAbsolutePath = args[1];

        File source = new File(resultFileAbsolutePath);
        File destination = new File(source.getParent() + "/allFilesContent.txt");

        if (FileUtils.isExist(source)) {
            FileUtils.renameFile(source, destination);
        }

        SearchFileVisitor searchFileVisitor = new SearchFileVisitor();
        Files.walkFileTree(Paths.get(path), searchFileVisitor);

        ArrayList<String> list = new ArrayList<>();
        for (String each: searchFileVisitor.foundFiles) {
            if (!each.split(" ")[0].equals("allFilesContent.txt")) {
               list.add(each);
            }
        }

        ArrayList<String> finalList = new ArrayList<>();
        Collections.sort(list);
        for (String each: list) {
            finalList.add(each.split(" ")[1]);
        }


        try (FileOutputStream out = new FileOutputStream(destination)) {
            boolean separator = false;
            for(String file : finalList){
                if(separator) {
                    out.write("\n".getBytes(StandardCharsets.UTF_8));
                }
                FileInputStream in = new FileInputStream(file);
                while (in.available() > 0) {
                    int read = in.read();
                    out.write(read);
                }
                separator = true;
                in.close();
            }
        }
    }

    static class SearchFileVisitor extends SimpleFileVisitor<Path> {
        public ArrayList<String> foundFiles = new ArrayList<>();

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException, IOException {

            // check if file name contains search string
            if (!file.toString().endsWith(".txt")) return FileVisitResult.CONTINUE;

            byte[] content = Files.readAllBytes(file);

            if(content.length <= 50) {
                foundFiles.add(file.getFileName()   + " " + file.toAbsolutePath().toString());
            }

            return FileVisitResult.CONTINUE;
        }
    }
}


