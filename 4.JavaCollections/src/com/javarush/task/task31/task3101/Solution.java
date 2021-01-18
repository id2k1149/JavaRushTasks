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

public class Solution {
    public static void main(String[] args) throws IOException {
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
            int count = 0;
            for(String file : finalList){

                if(count !=0 ) {
                    out.write("\n".getBytes());
                }
                FileInputStream in = new FileInputStream(file);
                count = 1;
                while (in.available() > 0) {
                    int read = in.read();
                    out.write(read);
                }
                out.write("\n".getBytes(StandardCharsets.UTF_8));
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

            if(content.length <= 50 && content.length > 0) {
                foundFiles.add(file.getFileName()   + " " + file.toAbsolutePath().toString());
            }

            return FileVisitResult.CONTINUE;
        }
    }
}


